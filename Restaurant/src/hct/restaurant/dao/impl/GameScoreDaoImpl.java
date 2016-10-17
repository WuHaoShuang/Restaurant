package hct.restaurant.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hct.restaurant.dao.GameScoreDao;
import hct.restaurant.vo.Dish;
import hct.restaurant.vo.GameScore;
@Repository
public class GameScoreDaoImpl implements GameScoreDao {
	@Autowired
	SessionFactory sessionFactory ;
	Session session = null;
	@Override
	public String add(GameScore score) {
		// TODO Auto-generated method stub
		String id = "";
		session = sessionFactory.getCurrentSession();			
		try {
			id = (String) session.save(score);
			session.beginTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return id ;
	}

	@Override
	public String delete(GameScore score) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(GameScore score) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<GameScore> select(Map<String, String> map) {
		// TODO Auto-generated method stub
		ArrayList<GameScore> list  = new ArrayList<GameScore>() ;
		session = sessionFactory.getCurrentSession();
		StringBuffer sb = new StringBuffer();
		sb.append("From GameScore where 1=1");
		//循环添加查询条件
		Iterator it = map.keySet().iterator();
		while(it.hasNext()){
		String key = (String) it.next();
		String value = map.get(key)==null?"":map.get(key);
		if(!value.equals(""))
		{ 
			sb.append("and "+key+" ='"+value+"' ");
		}
		}
		
		try
		{
			list = (ArrayList<GameScore>) session.createQuery(sb.toString()).list();
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<GameScore> selectTop10()
	{
		ArrayList<GameScore> list  = new ArrayList<GameScore>() ;
		session = sessionFactory.getCurrentSession();
		StringBuffer sb = new StringBuffer();
		sb.append("From GameScore where 1=1 order by score desc");	
		list = (ArrayList<GameScore>) session.createQuery(sb.toString()).setMaxResults(10).list();
		return list;
	}
}
