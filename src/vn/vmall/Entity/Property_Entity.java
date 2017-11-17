package vn.vmall.Entity;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Property_Entity {

	@Column(name="id")
	private String id;
	
	@Column(name="product_type_sub_id")
	private String product_type_sub_id;
	
	@Column(name="property_name")
	private String property_name;
	
	@Column(name="isvisible")
	private int isvisible;
	
	@Column(name="isdelete")
	private int isdelete;
	
	@Column(name="parent")
	private String parent;
	
	@Column(name="tb_product_type_sub_0_product_type_name")
	private String tb_product_type_sub_0_product_type_name;
	

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

	public String getProperty_name() {
		return property_name;
	}

	public void setProperty_name(String property_name) {
		this.property_name = property_name;
	}

	public int getIsvisible() {
		return isvisible;
	}

	public void setIsvisible(int isvisible) {
		this.isvisible = isvisible;
	}

	public int getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getTb_product_type_sub_0_product_type_name() {
		return tb_product_type_sub_0_product_type_name;
	}

	public void setTb_product_type_sub_0_product_type_name(
			String tb_product_type_sub_0_product_type_name) {
		this.tb_product_type_sub_0_product_type_name = tb_product_type_sub_0_product_type_name;
	}
	ArrayList<Property_Entity> list_property;


	public ArrayList<Property_Entity> getList_property() {
		return list_property;
	}

	public void setList_property(ArrayList<Property_Entity> list_property) {
		this.list_property = list_property;
	}

}
