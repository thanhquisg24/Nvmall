package vn.vmall.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class CatgoryNews_Entitty {

	@Column(name="id")
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="isvisible")
	private int isvisible;
	@Column(name="create_date")
	private String create_date;
	@Column(name="modify_date")
	private String modify_date;
	@Column(name="creator")
	private String creator;
	@Column(name="modifyer")
	private String modifyer;
	
	@Column(name="img")
	private String img;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIsvisible() {
		return isvisible;
	}
	public void setIsvisible(int isvisible) {
		this.isvisible = isvisible;
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
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getModifyer() {
		return modifyer;
	}
	public void setModifyer(String modifyer) {
		this.modifyer = modifyer;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	
	
}
