package vn.vmall.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class News_Entity {
	
	@Column(name="id")
	private String id;
	@Column(name="title")
	private String title;
	@Column(name="short_description")
	private String short_description;
	@Column(name="content")
	private String content;
	@Column(name="create_date")
	private String create_date;
	@Column(name="modify_date")
	private String modify_date;
	@Column(name="creator")
	private String creator;
	@Column(name="modifyer")
	private String modifyer;
	@Column(name="news_catgory")
	private String news_catgory;
	@Column(name="isvisible")
	private int isvisible;
	@Column(name="isdelete")
	private int isdelete;
	@Column(name="img")
	private String img;
	@Column(name="news_catgory_name")
	private String news_catgory_name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getNews_catgory() {
		return news_catgory;
	}
	public void setNews_catgory(String news_catgory) {
		this.news_catgory = news_catgory;
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
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getShort_description() {
		return short_description;
	}
	public void setShort_description(String short_description) {
		this.short_description = short_description;
	}
	public String getNews_catgory_name() {
		return news_catgory_name;
	}
	public void setNews_catgory_name(String news_catgory_name) {
		this.news_catgory_name = news_catgory_name;
	}
	
	
	
}
