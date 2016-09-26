package hct.restaurant.action;

import java.util.ArrayList;

import hct.restaurant.service.ClassifyService;
import hct.restaurant.vo.Classify;

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
@Namespace("classify")
@ParentPackage("json-default")
public class ClassifyAction implements ModelDriven<Classify> {
	private Classify classify = new Classify();
	@Autowired
	private ClassifyService cs;
	private String message;
	private ArrayList<Classify> list;

	@JSON
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@JSON
	public ArrayList<Classify> getList() {
		return list;
	}

	public void setList(ArrayList<Classify> list) {
		this.list = list;
	}

	public Classify getModel() {
		// TODO Auto-generated method stub
		classify.setRestname(ActionContext.getContext().getSession().get("restname").toString());
		return classify;
	}

	@Action(value = "/add", results = { @Result(name = "success", type = "json", params = {
			"root", "message" }) })
	public String add() {
		cs.add(classify);
		message = "添加成功";
		return "success";
	}

	@Action(value = "/findbyrest", results = { @Result(name = "success", type = "json", params = {
			"root", "list" }) })
	public String findbyrest() {
		list = cs.findByRest(classify.getRestname());
		return "success";
	}
}
