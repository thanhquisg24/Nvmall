package vn.vmall.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class Color_Entity {

	@Column(name="id")
	private String id;
	
	@Column(name="product_type_sub_id")
	private String product_type_sub_id;
	
	@Column(name="product_type_name")
	private String product_type_name;
	
	@Column(name="color_name")
	private String color_name;
	
	@Column(name="color")
	private String color;
	
	@Column(name="img")
	private String img;
	
	@Column(name="type")
	private String type;
	
	@Column(name="isvisible")
	private String isvisible;
	
	@Column(name="isdelete")
	private String isdelete;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProduct_type_sub_id() {
		return product_type_sub_id;
	}

	public void setProduct_type_sub_id(String product_type_sub_id) {
		this.product_type_sub_id = product_type_sub_id;
	}
	
	public String getProduct_type_name() {
		return product_type_name;
	}

	public void setProduct_type_name(String product_type_name) {
		this.product_type_name = product_type_name;
	}

	public String getColor_name() {
		return color_name;
	}

	public void setColor_name(String color_name) {
		this.color_name = color_name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsvisible() {
		return isvisible;
	}

	public void setIsvisible(String isvisible) {
		this.isvisible = isvisible;
	}

	public String getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete;
	}
	
	
}
