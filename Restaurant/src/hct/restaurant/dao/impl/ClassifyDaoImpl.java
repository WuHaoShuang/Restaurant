package hct.restaurant.dao.impl;

import hct.restaurant.dao.ClassifyDao;
import hct.restaurant.vo.Classify;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class ClassifyDaoImpl implements ClassifyDao {
	@Autowired
	SessionFactory sessionFactory ;
	Session session = null;
	public String add(Classify c) {
		// TODO Auto-generated method stub
		String id = "";
		session = sessionFactory.getCurrentSession();			
		try {
			id = (String) session.save(c);
			session.beginTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
		}
		return id ;
	}

	public String delete(Classify c) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Classify> select(Map<String, String> map) {
		// TODO Auto-generated method stub
		ArrayList<Classify> list  = new ArrayList<Classify>() ;
		session = sessionFactory.getCurrentSession();
		StringBuffer sb = new StringBuffer();
		sb.append("From Classify where 1=1");
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
			list = (ArrayList<Classify>) session.createQuery(sb.toString()).list();
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	public void update(Classify c) {
		// TODO Auto-generated method stub

	}

}
