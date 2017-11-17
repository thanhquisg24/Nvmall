package vn.vmall.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class proRecommended_Entity {

	@Column(name="product_type")
	private String product_type_id;
	
	@Column(name="product_type_name")
	private String product_type_name;
	
	@Column(name="customer_id")
	private String customer_id;
	
	@Column(name="email")
	private String email;
	
	@Column(name="product_id")
	private String product_id;
	
	@Column(name="product_name")
	private String product_name;
	
	@Column(name="isvisible")
	private String isvisible;
	
	@Column(name="creator")
	private String creator;
	
	@Column(name="date_create")
	private String date_create;
	
	@Column(name="modifier")
	private String modifier;
	
	@Column(name="date_modify")
	private String date_modify;
	
	@Column(name="group_category_id")
	private String group_category_id;
	
	@Column(name="customer_type_name")
	private String customer_type_name;
	
	
	
	public String getCustomer_type_name() {
		return customer_type_name;
	}

	public void setCustomer_type_name(String customer_type_name) {
		this.customer_type_name = customer_type_name;
	}

	public String getGroup_category_id() {
		return group_category_id;
	}

	public void setGroup_category_id(String group_category_id) {
		this.group_category_id = group_category_id;
	}

	public String getProduct_type_id() {
		return product_type_id;
	}

	public void setProduct_type_id(String product_type_id) {
		this.product_type_id = product_type_id;
	}

	public String getProduct_type_name() {
		return product_type_name;
	}

	public void setProduct_type_name(String product_type_name) {
		this.product_type_name = product_type_name;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getIsvisible() {
		return isvisible;
	}

	public void setIsvisible(String isvisible) {
		this.isvisible = isvisible;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getDate_create() {
		return date_create;
	}

	public void setDate_create(String date_create) {
		this.date_create = date_create;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getDate_modify() {
		return date_modify;
	}

	public void setDate_modify(String date_modify) {
		this.date_modify = date_modify;
	}
	
	
}
