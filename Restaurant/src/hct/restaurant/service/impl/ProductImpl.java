package hct.restaurant.service.impl;

import hct.restaurant.dao.ProductDao;
import hct.restaurant.dao.StorageDao;
import hct.restaurant.service.ProductService;
import hct.restaurant.vo.Product;
import hct.restaurant.vo.Storage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ProductImpl implements ProductService {
@Autowired
private ProductDao pdao ;
@Autowired 
private StorageDao sdao ;
	public String addProduct(Product p) {
		// TODO Auto-generated method stub
		return pdao.add(p);
	}

	public String storage(Storage s) {
		// TODO Auto-generated method stub
		DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		s.setTime(df.format(new Date()));
		return sdao.add(s);
	}

	public ArrayList<Product> selectAll() {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		return pdao.select(map);
	}

	public ArrayList<Storage> selectStorages(String starttime, String endtime,
			String restname, String productid) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("starttime", starttime);
		map.put("endtime", endtime);
		map.put("productid", productid);
		map.put("restname", restname);
		return sdao.select(map);
	}
}
