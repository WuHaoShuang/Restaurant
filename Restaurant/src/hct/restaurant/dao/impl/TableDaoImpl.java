package hct.restaurant.dao.impl;

import hct.restaurant.dao.TableDao;
import hct.restaurant.vo.Table;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class TableDaoImpl implements TableDao {
	@Autowired
	SessionFactory sessionFactory ;
	Session session = null;
	public String add(Table t) {
		// TODO Auto-generated method stub
		String id = "";
		session = sessionFactory.getCurrentSession();			
		try {
			id = (String) session.save(t);
			session.beginTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
		}
		return id ;
	}

	public String delete(Table t) {
		// TODO Auto-generated method stub
		session = sessionFactory.getCurrentSession();			
		try {
			session.delete(t);
			session.beginTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
			return "error";
		}
		return "success";
	}
	@SuppressWarnings("unchecked")
	public ArrayList<Table> select(Map<String, String> map) {
		// TODO Auto-generated method stub
		ArrayList<Table> list  = new ArrayList<Table>() ;
		session = sessionFactory.getCurrentSession();
		StringBuffer sb = new StringBuffer();
		sb.append("From Table where 1=1");
		//循环添加查询条件
		Iterator it = map.keySet().iterator();
		while(it.hasNext()){
		String key = (String) it.next();
		String value = map.get(key)==null?"":map.get(key);
		if(!value.equals(""))
		{ 
			sb.append("and "+key+" = '"+value+"' ");
		}
		}
		try
		{
			list = (ArrayList<Table>) session.createQuery(sb.toString()).list();
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	public void update(Table t) {
		// TODO Auto-generated method stub
		session = sessionFactory.getCurrentSession();
		try {
			session.update(t);
			session.beginTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
		}		
	}

}
