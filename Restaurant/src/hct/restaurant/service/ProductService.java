package hct.restaurant.service;

import java.util.ArrayList;

import hct.restaurant.vo.Product;
import hct.restaurant.vo.Storage;

public interface ProductService {
public String addProduct(Product p);
public String storage(Storage s);
public ArrayList<Product> selectAll();
public ArrayList<Storage> selectStorages(String starttime ,String endtime ,String restname ,String productid);
}
