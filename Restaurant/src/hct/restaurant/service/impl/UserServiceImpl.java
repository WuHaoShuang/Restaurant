package hct.restaurant.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hct.restaurant.dao.RestDao;
import hct.restaurant.service.UserService;
import hct.restaurant.vo.Restaurant;
@Service
public class UserServiceImpl implements UserService{
@Autowired
private RestDao dao ;
	public void editPassword(String name, String password) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", name);
		Restaurant r = dao.select(map).get(0);
		r.setPassword(password);
		dao.update(r);
	}

	public boolean login(String name, String password) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("password", password);
		if (dao.select(map).size()>0) {
			return true;
		}
		else	return false;
	}

}
