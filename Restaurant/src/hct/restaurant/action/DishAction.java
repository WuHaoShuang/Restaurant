package hct.restaurant.action;

import hct.restaurant.service.DishService;
import hct.restaurant.vo.Dish;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
@Namespace("dish")
@ParentPackage("json-default")
public class DishAction implements ModelDriven<Dish> {
	@Autowired
	private DishService ds;
	private Dish dish = new Dish();
	private ArrayList<Dish> list = new ArrayList<Dish>();
	private String tableId = "";
	private String message = "";
	private String file = "";

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	@JSON
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	@JSON
	public ArrayList<Dish> getList() {
		return list;
	}

	public void setList(ArrayList<Dish> list) {
		this.list = list;
	}

	public Dish getModel() {
		// TODO Auto-generated method stub
		dish.setRestname(ActionContext.getContext().getSession()
				.get("restname").toString());
		return dish;
	}

	@Action(value = "/dish", results = { @Result(name = "success", type = "json", params = {
			"root", "list" }) })
	public String selectAll() {
		String restname = dish.getRestname();
		list = ds.selectAll(restname);
		System.out.println(111);
		return "success";
	}

	@Action(value = "/adddish", results = { @Result(name = "success", type = "json", params = {
			"root", "message" }) })
	public String addDish() {
		Pattern pattern = Pattern.compile("src=\"../..(.*?)\"");
		Matcher m = pattern.matcher(file);
		m.find();
		dish.setImg(m.group(1));
		ds.addDish(dish);
		message = "添加成功";
		return "success";
	}

	@Action(value = "/findbyclass", results = { @Result(name = "success", type = "json", params = {
			"root", "list" }) })
	public String byClass() {
		list = ds.findByClass(dish.getRestname(), dish.getClassname());
		return "success";
	}

	@Action(value = "/findbyid", results = { @Result(name = "success", type = "json", params = {
			"root", "list" }) })
	public String byId() {
		list = ds.findById(dish.getId());
		return "success";
	}

	@Action(value = "/editdish", results = { @Result(name = "success", type = "json", params = {
			"root", "message" }) })
	public String editDish() {
		Pattern pattern = Pattern.compile("src=\"../..(.*?)\"");
		Matcher m = pattern.matcher(file);
		m.find();
		dish.setImg(m.group(1));
		ds.updateDish(dish);
		message = "修改成功";

		return "success";
	}

	@Action(value = "/delete", results = { @Result(name = "success", type = "json", params = {
			"root", "message" }) })
	public String delete() {
		ds.deleteDish(dish);
		message = "删除成功";
		return "success";
	}
}
