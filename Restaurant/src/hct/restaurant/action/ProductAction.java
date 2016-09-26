package hct.restaurant.action;

import java.util.ArrayList;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import hct.restaurant.service.ProductService;
import hct.restaurant.vo.Product;
import hct.restaurant.vo.Storage;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@ParentPackage("json-default")
@Namespace("product")
public class ProductAction implements ModelDriven<Storage> {
	private Storage s = new Storage();
	@Autowired
	private ProductService ps;
	private String message;
	private String name;
	private ArrayList<Product> plist ;
	private String starttime ;
	private String endtime ;
	private ArrayList<Storage> slist ;
	@JSON
	public ArrayList<Storage> getSlist() {
		return slist;
	}

	public void setSlist(ArrayList<Storage> slist) {
		this.slist = slist;
	}

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
	public ArrayList<Product> getPlist() {
		return plist;
	}

	public void setPlist(ArrayList<Product> plist) {
		this.plist = plist;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JSON
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Storage getModel() {
		// TODO Auto-generated method stub
		s.setRestname(ActionContext.getContext().getSession().get("restname")
				.toString());
		return s;
	}

	@Action(value = "/addproduct", results = { @Result(name = "success", type = "json", params = {
			"root", "message" }) })
	public String addProduct() {
		Product p = new Product();
		p.setName(name);
		p.setRestname(s.getRestname());
		ps.addProduct(p);
		message = "添加成功";
		return "success";
	}
	@Action(value = "/selectall", results = { @Result(name = "success", type = "json", params = {
			"root", "plist" }) })
	public String selectAll() {
		plist = ps.selectAll();
		return "success";
	}
	@Action(value = "/storage", results = { @Result(name = "success", type = "json", params = {
			"root", "message" }) })
	public String storage() {
		ps.storage(s);
		message = "添加成功";
		return "success";
	}
	@Action(value = "/selectstorage", results = { @Result(name = "success", type = "json", params = {
			"root", "slist" }) })
	public String selectStorage() {
		slist = ps.selectStorages(starttime, endtime, s.getRestname(), s.getProductid());
		return "success";
	}
	
}
