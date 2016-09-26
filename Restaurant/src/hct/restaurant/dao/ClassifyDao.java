package hct.restaurant.dao;

import hct.restaurant.vo.Classify;

import java.util.ArrayList;
import java.util.Map;

public interface ClassifyDao {
public String add(Classify c);
public String delete(Classify c);
public void update(Classify c);
public ArrayList<Classify> select(Map<String, String> map);
}
