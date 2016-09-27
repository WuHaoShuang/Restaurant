package hct.restaurant.action;

import hct.restaurant.service.BillService;
import hct.restaurant.vo.Bill;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Namespace("bill")
@ParentPackage("json-default")
public class BillAction implements ModelDriven<Bill> {
	private Bill bill = new Bill();
	@Autowired
	private BillService bs;
	private String message = "";
	private String starttime = "";
	private String endtime = ""; 
	private ArrayList<Bill> list  = new ArrayList<Bill>();
	private ArrayList<Map<String,String>> salelist  = new ArrayList<Map<String,String>>();

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	@JSON
	public ArrayList<Bill> getList() {
		return list;
	}

	public void setList(ArrayList<Bill> list) {
		this.list = list;
	}
	@JSON
	public ArrayList<Map<String, String>> getSalelist() {
		return salelist;
	}

	public void setSalelist(ArrayList<Map<String, String>> salelist) {
		this.salelist = salelist;
	}


	@JSON
	public String getMessage(){
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Bill getModel(){
		// TODO Auto-generated method stub
		bill.setRestname(ActionContext.getContext().getSession().get("restname").toString());
		return bill;
	}
	@Action(value="/selectbill",results={@Result(name="success",type = "json", params = { "root", "list" })})
	public String selectBill(){
		DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
		if (starttime==null&&endtime==null) {
			  Calendar   cal   =   Calendar.getInstance();
			  cal.add(Calendar.DATE,-7);
			  starttime = df.format(cal.getTime());			
		}
		list = bs.selectBill(bill.getTableid(), starttime, endtime,bill.getRestname());
		return "success" ; 
	}
	@Action(value="/sales",results={@Result(name="success",type = "json", params = { "root", "salelist" })})
	public String sales(){
		DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
		if (starttime==null&&endtime==null) {
			  Calendar   cal   =   Calendar.getInstance();
			  cal.add(Calendar.DATE,-30);
			  starttime = df.format(cal.getTime());			
		}
		if(bill.getContent()==null)
		{
			bill.setContent("");
		}
		Map<String, Integer> map = bs.selectSales(bill.getContent(), starttime, endtime,bill.getRestname());
		salelist = new ArrayList<Map<String,String>>();
		 for (Map.Entry entry : map.entrySet()) 
		 {
			 String key  = entry.getKey().toString();
			 Map<String, String> map2 = new HashMap<String, String>();
			 String num = entry.getValue().toString();
			 map2.put("name", key);
			 map2.put("num", num);
			 salelist.add(map2);
		 }
		return "success" ; 
	}
}
