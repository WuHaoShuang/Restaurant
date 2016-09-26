package hct.restaurant.dao;

import hct.restaurant.vo.Bill;

import java.util.ArrayList;
import java.util.Map;

public interface BillDao {
	public void add(Bill b);
	public String delete(Bill b);
	public void update(Bill b);
	public ArrayList<Bill> select(Map<String, String> map);
}
