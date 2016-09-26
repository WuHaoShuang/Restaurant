package hct.restaurant.service;

import java.util.ArrayList;

import hct.restaurant.vo.Classify;

public interface ClassifyService {
public String add(Classify c);
public ArrayList<Classify> findByRest(String restname); 
}
