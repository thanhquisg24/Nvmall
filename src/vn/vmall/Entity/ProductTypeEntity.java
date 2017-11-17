package vn.vmall.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class ProductTypeEntity {
	
	@Column(name="customer_id")
	private String customer_id;
	
	@Column(name="product_type_id")
	private String product_type_id;
	
	@Column(name="product_type_name")
	private String product_type_name;
	
	@Column(name="category_img")
	private String category_img;
	
	@Column(name="title_img")
	private String title_img;
	
	@Column(name="isvisible")
	private int isvisible;
	
	@Column(name="isdelete")
	private int isdelete;
	
	@Column(name="isstate")
	private int isstate;
	
	@Column(name="issyn")
	private int issyn;

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
