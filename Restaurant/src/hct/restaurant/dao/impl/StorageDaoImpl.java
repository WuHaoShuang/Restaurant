package hct.restaurant.dao.impl;

import hct.restaurant.dao.StorageDao;
import hct.restaurant.vo.Storage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;
@Repository
public class StorageDaoImpl implements StorageDao {
	Configuration config = new Configuration().configure();
	SessionFactory sessionFactory = config.buildSessionFactory();
	Session session = null;
	public String add(Storage s) {
		// TODO Auto-generated method stub
		String id = "" ;
		session = sessionFactory.openSession();			
		try {
			id = (String) session.save(s);
			session.beginTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
		}
		return id ;
	}

	public String delete(Storage s) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Storage> select(Map<String, String> map) {
		// TODO Auto-generated method stub
		ArrayList<Storage> list  = new ArrayList<Storage>() ;
		session = sessionFactory.openSession();
		StringBuffer sb = new StringBuffer();
		sb.append("From Storage where 1=1");
		//循环添加查询条件
		Iterator it = map.keySet().iterator();
		while(it.hasNext()){
		String key = (String) it.next();
		String value = map.get(key)==null?"":map.get(key);
		if(!value.equals(""))
		{ 
			if(key.equals("starttime"))
			sb.append("and time > '"+value+"' ");
			else if(key.equals("endtime"))
			sb.append("and time < '"+value+"' ");
			else 
			sb.append("and "+key+" ='"+value+"' ");
		}
		}
		try
		{
			list = (ArrayList<Storage>) session.createQuery(sb.toString()).list();
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	public void update(Storage s) {
		// TODO Auto-generated method stub

	}

}
