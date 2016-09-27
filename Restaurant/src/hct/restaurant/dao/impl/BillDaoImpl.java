package hct.restaurant.dao.impl;

import hct.restaurant.dao.BillDao;
import hct.restaurant.vo.Bill;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;
@Repository
public class BillDaoImpl implements BillDao {
	Configuration config = new Configuration().configure();
	SessionFactory sessionFactory = config.buildSessionFactory();
	Session session = null;
	public void add(Bill b) {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();			
		try {
			session.save(b);
			session.beginTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
		}
	}

	public String delete(Bill b) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Bill> select(Map<String, String> map) {
		// TODO Auto-generated method stub
		ArrayList<Bill> list  = new ArrayList<Bill>() ;
		session = sessionFactory.openSession();
		StringBuffer sb = new StringBuffer();
		sb.append("From Bill where 1=1");
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
			sb.append("and "+key+" like '%"+value+"%' ");
		}
		}
		try
		{
			list = (ArrayList<Bill>) session.createQuery(sb.toString()).list();
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	public void update(Bill b) {
		// TODO Auto-generated method stub

	}

}
