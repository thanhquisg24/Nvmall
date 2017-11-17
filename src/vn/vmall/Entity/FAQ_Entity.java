package vn.vmall.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class FAQ_Entity {

	@Column(name="id")
	private String id;
	@Column(name="content")
	private String content;
	@Column(name="isvisible")
	private int isvisible;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getIsvisible() {
		return isvisible;
	}
	public void setIsvisible(int isvisible) {
		this.isvisible = isvisible;
	}
	
	
	
}
