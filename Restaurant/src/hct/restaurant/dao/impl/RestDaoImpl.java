package hct.restaurant.dao.impl;

import hct.restaurant.dao.RestDao;
import hct.restaurant.vo.Restaurant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class RestDaoImpl implements RestDao {
	@Autowired
	SessionFactory sessionFactory ;
	Session session = null;
	public String add(Restaurant r) {
		// TODO Auto-generated method stub
		return null;
	}

	public String delete(Restaurant r) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Restaurant> select(Map<String, String> map) {
		// TODO Auto-generated method stub
		ArrayList<Restaurant> list  = new ArrayList<Restaurant>() ;
		session = sessionFactory.getCurrentSession();
		StringBuffer sb = new StringBuffer();
		sb.append("From Restaurant where 1=1 ");
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
			list = (ArrayList<Restaurant>) session.createQuery(sb.toString()).list();
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	public void update(Restaurant r) 
	{
		// TODO Auto-generated method stub
		session = sessionFactory.getCurrentSession();
		try {
			session.update(r);
			session.beginTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
