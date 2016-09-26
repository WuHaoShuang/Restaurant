package hct.restaurant.action;

import hct.restaurant.service.UserService;
import hct.restaurant.vo.Restaurant;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
@Controller
@ParentPackage("json-default")
public class UserAction implements ModelDriven<Restaurant>{
	@Autowired
	private UserService us ;
	private String message ;
public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
private Restaurant restaurant = new Restaurant();
	public Restaurant getModel() {
		// TODO Auto-generated method stub
		return restaurant;
	}
	
	@Action(value="login",results={@Result(name="success",type="json",params={"root","message"})})
	public String login()
	{
		if(us.login(restaurant.getName(), restaurant.getPassword()))
		{
			ActionContext.getContext().getSession().put("restname", restaurant.getName());
			ActionContext.getContext().getSession().put("login", "success");
			message = "success";
		}
		else message = "用户名或密码错误";
		return "success";
	}
	@Action(value="logout",results={@Result(name="success",type="json",params={"root","message"})})
	public String logout()
	{

			ActionContext.getContext().getSession().clear();
			message = "退出成功";
		return "success";
	}
	@Action(value="editpwd",results={@Result(name="success",type="json",params={"root","message"})})
	public String editpwd()
	{

		us.editPassword(ActionContext.getContext().getSession().get("restname").toString(),restaurant.getPassword());
		message = "success";
		return "success";
	}
}
