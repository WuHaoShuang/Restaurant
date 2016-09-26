package hct.restaurant.service;

import hct.restaurant.vo.Table;

import java.util.ArrayList;

public interface TableService 
{
 public void addCart(Table table);
 public boolean validateTable(String tableid);
 public void updateStruts(String tableid);
 public ArrayList<Table> allTable(String restname);
 public ArrayList<Table> selectTable(String id);
 public void serve (String dishname,int servenum,Table table);
 public void end(String tableid);
 public String addTable (Table table);
 public void deleteTable (Table table);
}
