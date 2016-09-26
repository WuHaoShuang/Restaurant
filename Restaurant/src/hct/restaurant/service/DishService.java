package hct.restaurant.service;

import hct.restaurant.vo.Dish;

import java.util.ArrayList;

public interface DishService {
public ArrayList<Dish> selectAll(String restname);
public ArrayList<Dish> findByClass(String classname ,String restname);
public ArrayList<Dish> findById(String id);
public String addDish(Dish dish);
public void updateDish(Dish dish);
public void deleteDish(Dish dish);
}
