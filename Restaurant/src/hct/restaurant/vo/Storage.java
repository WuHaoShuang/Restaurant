package hct.restaurant.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
@Entity
public class Storage {
private String id ;
private String productid ;
private String restname ;
private String time ;
private String num ;
private float price ;
@Id
@GenericGenerator(name = "idGenerator", strategy = "uuid")
@GeneratedValue(generator = "idGenerator")
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getProductid() {
	return productid;
}
public void setProductid(String productid) {
	this.productid = productid;
}
public String getRestname() {
	return restname;
}
public void setRestname(String restname) {
	this.restname = restname;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
public String getNum() {
	return num;
}
public void setNum(String num) {
	this.num = num;
}
public float getPrice() {
	return price;
}
public void setPrice(float price) {
	this.price = price;
}

}
