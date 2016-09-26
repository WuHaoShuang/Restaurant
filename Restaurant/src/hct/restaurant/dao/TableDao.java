package hct.restaurant.dao;

import hct.restaurant.vo.Table;

import java.util.ArrayList;
import java.util.Map;

public interface TableDao {
	public String add(Table t);
	public String delete(Table t);
	public void update(Table t);
	public ArrayList<Table> select(Map<String, String> map);
}
