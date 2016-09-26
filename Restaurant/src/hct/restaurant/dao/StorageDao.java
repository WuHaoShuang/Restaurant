package hct.restaurant.dao;

import hct.restaurant.vo.Storage;

import java.util.ArrayList;
import java.util.Map;

public interface StorageDao {
	public String add(Storage s);
	public String delete(Storage s);
	public void update(Storage s);
	public ArrayList<Storage> select(Map<String, String> map);
}
