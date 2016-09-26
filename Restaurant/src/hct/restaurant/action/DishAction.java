package hct.restaurant.action;

import hct.restaurant.service.DishService;
import hct.restaurant.vo.Dish;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sun.misc.BASE64Decoder;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
@Controller
@Namespace("dish")
@ParentPackage("json-default")   
public class DishAction implements ModelDriven<Dish> {
	@Autowired
	private DishService ds ;
	private Dish dish = new Dish();
	private ArrayList<Dish> list ;
	private String tableId ;
	private String message ;
	private String filename ;
	private String file ;
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
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
		dish.setRestname(ActionContext.getContext().getSession().get("restname").toString());
		return dish;
	}

	@Action(value="/dish",results={@Result(name="success",type = "json", params = { "root", "list" })})
	public String selectAll()
	{	
		String restname = dish.getRestname();
		list = ds.selectAll(restname);
		System.out.println(111);
		return "success";
	}
	@Action(value="/adddish",results={@Result(name="success",type = "json", params = { "root", "message" })})
	public String addDish()
	{	
        if (file == null) //图像数据为空  
            message = "图像数据为空";  
        BASE64Decoder decoder = new BASE64Decoder();  
        try   
        {  
        	file = file.replace("data:image/png;base64,","");
        	file = file.replaceAll("\\s", "+");
            byte[] b = decoder.decodeBuffer(file);  
            for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0)  
                { 
                    b[i]+=256;  
                }  
            }  
            DateFormat df = new SimpleDateFormat("YYYY-MM-dd_HH-ss");
            HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
            String path = request.getSession().getServletContext().getRealPath(""); 
            filename = df.format(new Date())+".png";
            String imgFilePath = path+"/dishimg/"+filename;//新生成的图片  
            OutputStream out = new FileOutputStream(imgFilePath);      
            out.write(b);  
            out.flush();  
            out.close();
            dish.setImg("/Restaurant/dishimg/"+filename);
            ds.addDish(dish);
            message = "添加成功";
        }   
        catch (Exception e)   
        {  
            System.err.println(e);
        }  
		return "success";
	}
	@Action(value="/findbyclass",results={@Result(name="success",type = "json", params = { "root", "list" })})
	public String byClass()
	{	
		list = ds.findByClass(dish.getRestname(), dish.getClassname());
		return "success";
	}
	@Action(value="/findbyid",results={@Result(name="success",type = "json", params = { "root", "list" })})
	public String byId()
	{	
		list = ds.findById(dish.getId());
		return "success";
	}
	@Action(value="/editdish",results={@Result(name="success",type = "json", params = { "root", "message" })})
	public String editDish()
	{	
		if (file == null) //图像数据为空  
		{
           ds.updateDish(dish);  
			message = "修改成功";
		}
		else {
	        BASE64Decoder decoder = new BASE64Decoder();  
	        try   
	        {  
	            //Base64解码  
	        	file = file.replace("data:image/png;base64,","");
	        	file = file.replaceAll("\\s", "+");
	            byte[] b = decoder.decodeBuffer(file);  
	            for(int i=0;i<b.length;++i)  
	            {  
	                if(b[i]<0)  
	                { 
	                    b[i]+=256;  
	                }  
	            }  
	            DateFormat df = new SimpleDateFormat("YYYY-MM-dd_HH-ss");
	            HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
	            String path = request.getSession().getServletContext().getRealPath(""); 
	            filename = df.format(new Date())+".png";
	            String imgFilePath = path+"/dishimg/"+filename;//新生成的图片  
	            OutputStream out = new FileOutputStream(imgFilePath);      
	            out.write(b);  
	            out.flush();  
	            out.close();
	            dish.setImg("/Restaurant/dishimg/"+filename);
	            ds.updateDish(dish);
	            message = "修改成功";
	        }   
	        catch (Exception e)   
	        {  
	            message = "修改出错";  
	        }  
		}
		return "success";
	}
	@Action(value="/delete",results={@Result(name="success",type = "json", params = { "root", "message" })})
	public String delete(){
		ds.deleteDish(dish);
		message="删除成功";
		return "success";
	}
}
