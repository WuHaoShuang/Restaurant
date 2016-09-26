package hct.restaurant.dao;

import hct.restaurant.vo.Product;

import java.util.ArrayList;
import java.util.Map;

public interface ProductDao {
	public String add(Product p);
	public String delete(Product p);
	public void update(Product p);
	public ArrayList<Product> select(Map<String, String> map);
}
