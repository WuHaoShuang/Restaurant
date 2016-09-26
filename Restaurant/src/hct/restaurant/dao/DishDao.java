package hct.restaurant.dao;

import hct.restaurant.vo.Dish;

import java.util.ArrayList;
import java.util.Map;

public interface DishDao {
public String add(Dish d);
public String delete(Dish d);
public void update(Dish d);
public ArrayList<Dish> select(Map<String, String> map);
}
