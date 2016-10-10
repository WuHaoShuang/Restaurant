package hct.restaurant.action;

import hct.restaurant.service.TableService;
import hct.restaurant.vo.Table;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@ParentPackage("struts-default")
public class RouteAction implements ModelDriven<Table>{
private Table table = new Table();
@Autowired
private TableService ts ;
	public Table getModel() {
		// TODO Auto-generated method stub
		return table;
	}
	@Action(value="/route",results={@Result(name="dish",location="/dish.jsp",type="dispatcher"),
			@Result(name="cart",location="/cart.jsp",type="dispatcher")})
	public String route()
	{
		ActionContext.getContext().getSession().put("restname", table.getRestname());
		ActionContext.getContext().getSession().put("tableid", table.getId());
		if (ts.validateTable(table.getId())) 
		{
			ts.updateStruts(table.getId());
			return "dish";
		}
		else {
			return "cart";
		}
	}

}
