package hct.restaurant.action;

import hct.restaurant.service.BillService;
import hct.restaurant.service.TableService;
import hct.restaurant.service.impl.BillServiceImpl;
import hct.restaurant.vo.Table;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.net.httpserver.Authenticator.Success;

@Controller
@ParentPackage("json-default")
public class TableAction implements ModelDriven<Table>{
private Table table = new Table();
@Autowired
private TableService ts ;
@Autowired
private BillService bs ;
private ArrayList<Table> list = new ArrayList<Table>();
private String dishname = "";
private int servenum = 0;  
private String password = "";
private String name = "";
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDishname() {
	return dishname;
}
public void setDishname(String dishname) {
	this.dishname = dishname;
}
public int getServenum() {
	return servenum;
}
public void setServenum(int servenum) {
	this.servenum = servenum;
}
@JSON
public ArrayList<Table> getList() {
	return list;
}
public void setList(ArrayList<Table> list) {
	this.list = list;
}
@JSON
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
private float total ;
private String message ;
	public float getTotal() {
	return total;
}
public void setTotal(float total) {
	this.total = total;
}
	public Table getModel() 
	{
		// TODO Auto-generated method stub
		table.setRestname(ActionContext.getContext().getSession().get("restname").toString());
		return table;
	}
	@Action(value="order",results={@Result(name="success",type="json",params={"root","message"})})
	public String order()
	{
		if(bs.validate(table.getCart(), total, table.getRestname()))
		{
			table.setId(ActionContext.getContext().getSession().get("tableid").toString());
			ts.addCart(table);
			message = "下单成功";
		}
		else {
			message = "价格出错";
		}
		return "success";
	}
	@Action(value="alltable",results={@Result(name="success",type="json",params={"root","list"})})
	public String alltable()
	{
		list = ts.allTable(table.getRestname());
		return "success";
	}
	@Action(value="selectTable",results={@Result(name="success",type="json",params={"root","list"})})
	public String selectTable()
	{
		list = ts.selectTable(table.getId());
		return "success";
	}
	@Action(value="serve",results={@Result(name="success",type="json",params={"root","message"})})
	public String serve()
	{
		ts.serve(dishname,servenum,table);
		message = "上菜成功";
		return "success";
		
	}
	@Action(value="end",results={@Result(name="success",type="json",params={"root","message"})})
	public String end()
	{
		bs.newBill(table.getId());
		ts.end(table.getId());	
		message = "操作成功";
		return "success";
	}
	@Action(value="/table/add",results={@Result(name="success",type="json",params={"root","message"})})
	public String addTable()
	{
		ts.addTable(table);
		message = "添加成功";
		return "success";
	}
	@Action(value="/table/delete",results={@Result(name="success",type="json",params={"root","message"})})
	public String deletetable()
	{
		ts.deleteTable(table);
		message = "删除成功";
		return "success";
	}
	@SuppressWarnings("rawtypes")
	@Action(value="nowbill",results={@Result(name="success",type="json",params={"root","list"})})
	public String nowBill()
	{
		list = ts.getNowBill(table.getRestname());
		return "success";
	}
}
