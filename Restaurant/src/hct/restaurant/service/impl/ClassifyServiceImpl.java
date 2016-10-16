package hct.restaurant.service.impl;

import hct.restaurant.dao.ClassifyDao;
import hct.restaurant.service.ClassifyService;
import hct.restaurant.vo.Classify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class ClassifyServiceImpl implements ClassifyService {
@Autowired
private ClassifyDao cdao ;
	public String add(Classify c) {
		// TODO Auto-generated method stub
		return cdao.add(c);
	}
	public ArrayList<Classify> findByRest(String restname) {
		// TODO Auto-generated method stub
		Map<String, String> query = new HashMap<String, String>();
		query.put("restname", restname);
		return cdao.select(query);
	}

}
