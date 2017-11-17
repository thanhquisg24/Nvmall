package vn.vmall.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class Brand_Entity {

	@Column(name="id")
	private String id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="image")
	private String image;
	
	@Column(name="country")
	private String country;
	
	
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
	

	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
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

	
	
	
}
