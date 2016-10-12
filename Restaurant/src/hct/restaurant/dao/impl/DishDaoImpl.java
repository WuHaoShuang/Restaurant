package hct.restaurant.dao.impl;

import hct.restaurant.dao.DishDao;
import hct.restaurant.vo.Dish;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.persistence.Id;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DishDaoImpl implements DishDao {

	@Autowired
	SessionFactory sessionFactory ;
	Session session = null;

	public String add(Dish d) {
		// TODO Auto-generated method stub
		String id = "";
		session = sessionFactory.openSession();			
		try {
			id = (String) session.save(d);
			session.beginTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
		}
		return id ;
	}

	public String delete(Dish d) {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();			
		try {
			session.delete(d);
			session.beginTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
			return "error";
		}
		return "success";
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Dish> select(Map<String, String> map) {
		// TODO Auto-generated method stub
		ArrayList<Dish> list  = new ArrayList<Dish>() ;
		session = sessionFactory.openSession();
		StringBuffer sb = new StringBuffer();
		sb.append("From Dish where 1=1");
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
			list = (ArrayList<Dish>) session.createQuery(sb.toString()).list();
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	public void update(Dish d) {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		try {
			session.update(d);
			session.beginTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
