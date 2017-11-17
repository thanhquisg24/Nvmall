package vn.vmall.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class Brand_productype_detail_Entity {
	
	@Column(name="id")
	private String id;
	
	@Column(name="product_type_id")
	private String product_type_id;
	
	@Column(name="brand_id")
	private String brand_id;
	
	@Column(name="create_date")
	private String create_date;
	
	@Column(name="modify_date")
	private String modify_date;
	
	@Column(name="create_user")
	private String create_user;
	
	@Column(name="modify_user")
	private String modify_user;
	
	@Column(name="isvisible")
	private int isvisible;
	
	@Column(name="isdelete")
	private int isdelete;

	@Column(name="name")
	private String brand_0_name;
	
	@Column(name="image")
	private String brand_0_image;
	
	@Column(name="country")
	private String brand_0_country;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProduct_type_id() {
		return product_type_id;
	}

	public void setProduct_type_id(String product_type_id) {
		this.product_type_id = product_type_id;
	}

	public String getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(String brand_id) {
		this.brand_id = brand_id;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getModify_date() {
		return modify_date;
	}

	public void setModify_date(String modify_date) {
		this.modify_date = modify_date;
	}

	public String getCreate_user() {
		return create_user;
	}

	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}

	public String getModify_user() {
		return modify_user;
	}

	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
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

	public String getBrand_0_name() {
		return brand_0_name;
	}

	public void setBrand_0_name(String brand_0_name) {
		this.brand_0_name = brand_0_name;
	}

	public String getBrand_0_image() {
		return brand_0_image;
	}

	public void setBrand_0_image(String brand_0_image) {
		this.brand_0_image = brand_0_image;
	}

	public String getBrand_0_country() {
		return brand_0_country;
	}

	public void setBrand_0_country(String brand_0_country) {
		this.brand_0_country = brand_0_country;
	}
	
	
	
}
