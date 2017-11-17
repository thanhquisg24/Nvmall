package vn.vmall.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class CatgoryCus_Entity {
	
	@Column(name="customer_id")
	private String customer_id;
	
	@Column(name="email")
	private String customer_email;
	
	@Column(name="product_type_id")
	private String product_type_id;
	
	@Column(name="product_type_name")
	private String product_type_name;
	
	@Column(name="category_img")
	private String category_img;
	
	@Column(name="title_img")
	private String title_img;
	
	@Column(name="group_category_id")
	private String group_category_id;
	
	@Column(name="product_type_name_parent")
	private String group_category_name;
	
	@Column(name="category_id")
	private String category_id;

	@Column(name="product_type_name_sub")
	private String category_name;
	
	@Column(name="isvisible")
	private int isvisible;
	
	@Column(name="isapprove")
	private int isapprove;
	
	@Column(name="isdelete")
	private int isdelete;
	
	@Column(name="isstate")
	private int isstate;
	
	@Column(name="issyn")
	private int issyn;

	@Column(name="product_id_vmall")
	private String product_id_vmall;

	
	
	
	public String getProduct_id_vmall() {
		return product_id_vmall;
	}

	public void setProduct_id_vmall(String product_id_vmall) {
		this.product_id_vmall = product_id_vmall;
	}

	public String getCustomer_email() {
		return customer_email;
	}

	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}

	public String getGroup_category_name() {
		return group_category_name;
	}

	public void setGroup_category_name(String group_category_name) {
		this.group_category_name = group_category_name;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
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

	public String getCategory_img() {
		return category_img;
	}

	public void setCategory_img(String category_img) {
		this.category_img = category_img;
	}

	public String getTitle_img() {
		return title_img;
	}

	public void setTitle_img(String title_img) {
		this.title_img = title_img;
	}

	public String getGroup_category_id() {
		return group_category_id;
	}

	public void setGroup_category_id(String group_category_id) {
		this.group_category_id = group_category_id;
	}

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

	public int getIsvisible() {
		return isvisible;
	}

	public void setIsvisible(int isvisible) {
		this.isvisible = isvisible;
	}

	public int getIsapprove() {
		return isapprove;
	}

	public void setIsapprove(int isapprove) {
		this.isapprove = isapprove;
	}

	public int getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}

	public int getIsstate() {
		return isstate;
	}

	public void setIsstate(int isstate) {
		this.isstate = isstate;
	}

	public int getIssyn() {
		return issyn;
	}

	public void setIssyn(int issyn) {
		this.issyn = issyn;
	}
	
	
		
	
}
