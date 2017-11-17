package vn.vmall.Entity;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class CatgorySub_Entity {
	
	@Column(name="ID")
	private String id;
	
	@Column(name="product_type_vmall")
	private String product_type_vmall;
	
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
	
	@Column(name="parent")
	private String parent;
	
	@Column(name="islink")
	private int islink;
	
	@Column(name="store_name")
	private String store_name;
	
	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProduct_type_vmall() {
		return product_type_vmall;
	}

	public void setProduct_type_vmall(String product_type_vmall) {
		this.product_type_vmall = product_type_vmall;
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

	public String getProduct_type_id() {
		return product_type_id;
	}

	public void setProduct_type_id(String product_type_id) {
		this.product_type_id = product_type_id;
	}
	ArrayList<CatgorySub_Entity> list_cate_sub;

	public ArrayList<CatgorySub_Entity> getList_cate_sub() {
		return list_cate_sub;
	}

	public void setList_cate_sub(ArrayList<CatgorySub_Entity> list_cate_sub) {
		this.list_cate_sub = list_cate_sub;
	}
	ArrayList<Brand_Entity> list_branch;

	public ArrayList<Brand_Entity> getList_branch() {
		return list_branch;
	}

	public void setList_branch(ArrayList<Brand_Entity> list_branch) {
		this.list_branch = list_branch;
	}

	public int getIslink() {
		return islink;
	}

	public void setIslink(int islink) {
		this.islink = islink;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	
	
}
