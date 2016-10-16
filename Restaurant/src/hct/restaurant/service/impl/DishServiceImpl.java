package hct.restaurant.service.impl;

import hct.restaurant.dao.DishDao;
import hct.restaurant.service.DishService;
import hct.restaurant.vo.Dish;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class DishServiceImpl implements DishService {
@Autowired
private DishDao dao ;
	public ArrayList<Dish> selectAll(String restname) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("restname", restname);
		return dao.select(map);
	}
	public ArrayList<Dish> findByClass(String restname, String classname) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("restname", restname);
		map.put("classname", classname);
		return dao.select(map);
	}
	public ArrayList<Dish> findById(String id) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		return dao.select(map);
	}
	public String addDish(Dish dish) {
		// TODO Auto-generated method stub
		return dao.add(dish);
	}
	public void updateDish(Dish dish) {
		// TODO Auto-generated method stub
		dao.update(dish);
	}
	public void deleteDish(Dish d) {
		// TODO Auto-generated method stub
		dao.delete(d);
	}
	
	
}
