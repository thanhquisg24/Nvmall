package vn.vmall.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Slide_Entity {

	@Column(name="id")
	private String id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="create_date")
	private String create_date;
	
	@Column(name="creator")
	private String creator;
	
	@Column(name="isvisible")
	private String isvisible;
	
	@Column(name="modifyer")
	private String modifyer;
	
	@Column(name="modify_date")
	private String modify_date;
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
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getIsvisible() {
		return isvisible;
	}
	public void setIsvisible(String isvisible) {
		this.isvisible = isvisible;
	}
	public String getModifyer() {
		return modifyer;
	}
	public void setModifyer(String modifyer) {
		this.modifyer = modifyer;
	}
	public String getModify_date() {
		return modify_date;
	}
	public void setModify_date(String modify_date) {
		this.modify_date = modify_date;
	}
	List<Slide_Entity> list_sub;
	public List<Slide_Entity> getList_sub() {
		return list_sub;
	}
	public void setList_sub(List<Slide_Entity> list_sub) {
		this.list_sub = list_sub;
	}
	
	
}
