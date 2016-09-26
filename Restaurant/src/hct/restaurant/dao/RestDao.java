package hct.restaurant.dao;

import hct.restaurant.vo.Restaurant;

import java.util.ArrayList;
import java.util.Map;

public interface RestDao {
	public String add(Restaurant r);
	public String delete(Restaurant r);
	public void update(Restaurant r);
	public ArrayList<Restaurant> select(Map<String, String> map);
}
