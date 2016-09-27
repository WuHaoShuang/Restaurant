package hct.restaurant.service;

import hct.restaurant.vo.Bill;

import java.util.ArrayList;
import java.util.Map;



public interface BillService 
{
public void newBill(String tableid);
public boolean validate(String bill,float total,String restname);
public ArrayList<Bill> selectBill(String tableid,String starttime,String endtime,String restname );
public Map<String,Integer> selectSales(String name,String starttime,String endtime ,String restname );
}
