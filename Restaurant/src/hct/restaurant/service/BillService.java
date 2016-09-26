package hct.restaurant.service;

import hct.restaurant.vo.Bill;

import java.util.ArrayList;



public interface BillService 
{
public void newBill(String tableid);
public boolean validate(String bill,float total,String restname);
public ArrayList<Bill> selectBill(String tableid,String starttime,String endtime,String restname );
}
