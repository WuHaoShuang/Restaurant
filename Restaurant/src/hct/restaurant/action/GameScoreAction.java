package hct.restaurant.action;

import hct.restaurant.service.GameScoreService;
import hct.restaurant.vo.GameScore;

import java.util.ArrayList;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sun.security.jgss.GSSCaller;

import com.opensymphony.xwork2.ModelDriven;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

@Controller
@Namespace("score")
@ParentPackage("json-default")
public class GameScoreAction implements ModelDriven<GameScore>{

	private GameScore score = new GameScore();
	private ArrayList<GameScore> list = new ArrayList<GameScore>();
	private String message = "";
	@Autowired
	private GameScoreService gs ;
	@JSON
	public ArrayList<GameScore> getList() {
		return list;
	}
	public void setList(ArrayList<GameScore> list) {
		this.list = list;
	}
	@Override
	public GameScore getModel() {
		// TODO Auto-generated method stub
		return score;
	}
	@Action(value = "/add", results = { @Result(name = "success", type = "json", params = {
			"root", "list" }) })
	public String addScore(){
		gs.addScore(score);
		list = gs.selectTop10();
		return "success";
	}
	@Action(value = "/selecttop10", results = { @Result(name = "success", type = "json", params = {
			"root", "list" }) })
	public String top10(){
		list = gs.selectTop10();
		return "success";
	}
}
