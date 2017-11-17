package vn.vmall.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class RuleModel {
	
	@Column(name="id")
	private String id;
	
	@Column(name="content_page")
	private String content_page;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent_page() {
		return content_page;
	}
	public void setContent_page(String content_page) {
		this.content_page = content_page;
	}
	
	
}
