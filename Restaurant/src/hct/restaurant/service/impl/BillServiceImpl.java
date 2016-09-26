package hct.restaurant.service.impl;

import hct.restaurant.dao.BillDao;
import hct.restaurant.dao.DishDao;
import hct.restaurant.dao.TableDao;
import hct.restaurant.service.BillService;
import hct.restaurant.vo.Bill;
import hct.restaurant.vo.Table;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
@Service
public class BillServiceImpl implements BillService {
@Autowired
private BillDao dao ;
@Autowired
private DishDao ddao ;
@Autowired
private TableDao tdao ;
	public void newBill(String tableid) 
	{		// TODO Auto-generated method stub
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Bill bill = new Bill();
		Map<String, String> query = new HashMap<String, String>();
		query.put("id",tableid);
		Table t = tdao.select(query).get(0);
		bill.setContent(getContent(t));
		bill.setRestname(t.getRestname());
		bill.setTableid(t.getId());
		bill.setPrice(getTotal(t));
		bill.setTime(df.format(new Date()));
		dao.add(bill);
	}
	@SuppressWarnings("unchecked")
	public boolean validate(String bill,float total,String restname) {
		// TODO Auto-generated method stub
		float total1  = 0.00f;
	    Map<String, Map<String, String>> map =  (Map<String, Map<String, String>>) JSON.parse(bill);	
	    for (Map.Entry entry : map.entrySet()) 
	    {
	        String key = entry.getKey().toString();
	        Map<String,String> query = new HashMap<String, String>();
	        query.put("restname", restname);
	        query.put("name", key);
	        float price =  ddao.select(query).get(0).getPrice();
	        Map<String,String> dish = (Map<String, String>) entry.getValue();
	        total1 += price*Integer.parseInt(dish.get("num"));
	     }
	    if (total == total1) 
	    	return true;
	    else return false;
	}
	@SuppressWarnings("unchecked")
	public  float getTotal(Table t){
		float total1  = 0.00f;
	    Map<String, Integer> map =  (Map<String, Integer>) JSON.parse(t.getAlready());	
	    for (Map.Entry entry : map.entrySet()) 
	    {
	        String key = entry.getKey().toString();
	        Map<String,String> query = new HashMap<String, String>();
	        query.put("restname", t.getRestname());
	        query.put("name", key);
	        float price =  ddao.select(query).get(0).getPrice();
	        int num =  Integer.parseInt(entry.getValue().toString()) ;
	        total1 += price*num;
	     }
	    return total1;
	}
	@SuppressWarnings("unchecked")
	public String getContent(Table t)
	{
		 
		 Map<String, Integer> map =  (Map<String,Integer>) JSON.parse(t.getAlready());	
		 Map<String, Map<String, String>> map1 =  new HashMap<String, Map<String,String>>();
		    for (Map.Entry entry : map.entrySet()) 
		    {
		        String key = entry.getKey().toString();
		        Map<String,String> query = new HashMap<String, String>();
		        query.put("restname", t.getRestname());
		        query.put("name", key);
		        DecimalFormat df = new DecimalFormat("0.00");
		        float f = ddao.select(query).get(0).getPrice();  
		        Map<String, String> map2 = new HashMap<String, String>();
		        map2.put("num", entry.getValue().toString());
		        map2.put("price", df.format(f));
		        map1.put(key, map2);
		     }
		    return JSON.toJSONString(map1);
	}
	public ArrayList<Bill> selectBill(String tableid, String starttime,
			String endtime,String restname) {
		// TODO Auto-generated method stub 
		Map<String, String> map = new HashMap<String, String>();
		map.put("starttime", starttime);
		map.put("endtime", endtime);
		map.put("tableid", tableid);
		map.put("restname", restname);
		return dao.select(map);
	}

}
