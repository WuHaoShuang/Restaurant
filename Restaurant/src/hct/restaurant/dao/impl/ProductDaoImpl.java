package hct.restaurant.dao.impl;

import hct.restaurant.dao.ProductDao;
import hct.restaurant.vo.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class ProductDaoImpl implements ProductDao{
	@Autowired
	SessionFactory sessionFactory ;
	Session session = null;
	public String add(Product p) {
		// TODO Auto-generated method stub
		session = sessionFactory.getCurrentSession();		
		String id ="";
		try {
			id=(String)session.save(p);
			session.beginTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
		}
		return id ;
	}

	public String delete(Product p) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Product> select(Map<String, String> map) {
		// TODO Auto-generated method stub
		ArrayList<Product> list  = new ArrayList<Product>() ;
		session = sessionFactory.getCurrentSession();
		StringBuffer sb = new StringBuffer();
		sb.append("From Product where 1=1");
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
			list = (ArrayList<Product>) session.createQuery(sb.toString()).list();
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
		return list;
	}

	public void update(Product p) {
		// TODO Auto-generated method stub
	}

}
