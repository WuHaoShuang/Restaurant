package hct.restaurant.service.impl;

import hct.restaurant.dao.TableDao;
import hct.restaurant.service.TableService;
import hct.restaurant.vo.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.uwantsoft.goeasy.client.goeasyclient.GoEasy;
@Service
public class TableServiceImpl implements TableService {
@Autowired
private TableDao dao;
	@SuppressWarnings("unchecked")
	public void addCart(Table table) {
		// TODO Auto-generated method stub
		String id = table.getId();
		Map<String, String> query = new HashMap<String, String>();
		query.put("id", id);
		Table t = dao.select(query).get(0);
		if(t.getCart().equals("{}"))
		{
			t.setCart(table.getCart());
			dao.update(t);
			GoEasy goEasy = new GoEasy("45ec7ca2-d450-4411-bfbd-8e6ee9ea6ac3");
			goEasy.publish("restaurant",table.getSeatnum()+"已下单");
		}
		else 
		{
			 Map<String, Map<String, String>> map1 =  (Map<String, Map<String, String>>) JSON.parse(t.getCart());
			 Map<String, Map<String, String>> map2 =  (Map<String, Map<String, String>>) JSON.parse(table.getCart());
			 for (Map.Entry entry : map2.entrySet()) {
			        String key = entry.getKey().toString();
			        Map<String,String> dish = (Map<String, String>) entry.getValue();
			        if (map1.get(key)==null) {
						map1.put(key, (Map<String, String>) entry.getValue());
					}
			        else {
			        	int num = Integer.parseInt(map1.get(key).get("num"))+Integer.parseInt(dish.get("num"));
						map1.get(key).put("num",String.valueOf(num)); 
					}
			       }
			 String json = JSON.toJSONString(map1);
			 t.setCart(json);
			 dao.update(t);
			 GoEasy goEasy = new GoEasy("45ec7ca2-d450-4411-bfbd-8e6ee9ea6ac3");
		     goEasy.publish("restaurant",table.getId());
		}		
	}
	public boolean validateTable(String tableid) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", tableid);
		if(dao.select(map).get(0).getStatus()==0)
		{
			return true;
		}
		else return false;
	}
	public void updateStruts(String tableid) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", tableid);
		Table t = dao.select(map).get(0);
		t.setStatus(1);
		dao.update(t);
	}
	public ArrayList<Table> allTable(String restname) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("restname", restname);
		return dao.select(map);
	}
	public ArrayList<Table> selectTable(String id) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		return dao.select(map);
	}
	@SuppressWarnings("unchecked")
	public void serve(String dishname, int servenum,Table table) {
		// TODO Auto-generated method stub
		String id = table.getId();
		Map<String, String> query = new HashMap<String, String>();
		query.put("id", id);
		Table t = dao.select(query).get(0);
		if(t.getAlready().equals("{}"))
		{
			t.setAlready("{\""+dishname+"\":"+servenum+"}");
			dao.update(t);
		}
		else 
		{
			 Map<String, Integer> map1 =  (Map<String,Integer>) JSON.parse(t.getAlready());
			        if (map1.get(dishname)==null) {
						map1.put(dishname, servenum);
					}
			        else {
						map1.remove(dishname); 
						map1.put(dishname, servenum);
					}
			       
			 String json = JSON.toJSONString(map1);
			 t.setAlready(json);
			 dao.update(t);
		}
	}
	public void end(String tableid) {
		// TODO Auto-generated method stub
		Map<String, String> query = new HashMap<String, String>();
		query.put("id", tableid);
		Table t = dao.select(query).get(0);
		t.setAlready("{}");
		t.setCart("{}");
		t.setStatus(0);
		dao.update(t);
	}
	public String addTable(Table table) {
		// TODO Auto-generated method stub
		return dao.add(table);
	}
	public void deleteTable(Table table) {
		// TODO Auto-generated method stub
		dao.delete(table);
	}
}
