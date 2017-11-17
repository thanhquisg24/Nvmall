package vn.vmall.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class Customer_Entity {

	@Column(name="id")
	private String id;
	
	@Column(name="email")
	private String email;
	
	@Column(name="pass")
	private String pass;
	
	@Column(name="shop_name")
	private String shop_name;
	
	@Column(name="shop_url")
	private String shop_url;
	
	@Column(name="isvisible")
	private int isvisible;
	
	@Column(name="isconfirm")
	private int isconfirm;
	
	@Column(name="isapprove")
	private int isapprove;
	
	@Column(name="db_name")
	private String db_name;
	
	@Column(name="server_id")
	private String server_id;
	
	@Column(name="domain")
	private String domain;
	
	@Column(name="url_admin")
	private String url_admin;
	
	@Column(name="fn_signin")
	private String fn_signin;
	
	@Column(name="fn_signin_c")
	private String fn_signin_c;
	
	@Column(name="project_name")
	private String project_name;
	
	@Column(name="pass_forget")
	private String pass_forget;
	
	@Column(name="confirm_forget")
	private int confirm_forget;
	
	@Column(name="filepdf")
	private String filepdf;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public String getShop_url() {
		return shop_url;
	}
	public void setShop_url(String shop_url) {
		this.shop_url = shop_url;
	}
	public int getIsvisible() {
		return isvisible;
	}
	public void setIsvisible(int isvisible) {
		this.isvisible = isvisible;
	}
	public int getIsconfirm() {
		return isconfirm;
	}
	public void setIsconfirm(int isconfirm) {
		this.isconfirm = isconfirm;
	}
	public int getIsapprove() {
		return isapprove;
	}
	public void setIsapprove(int isapprove) {
		this.isapprove = isapprove;
	}
	public String getDb_name() {
		return db_name;
	}
	public void setDb_name(String db_name) {
		this.db_name = db_name;
	}
	public String getServer_id() {
		return server_id;
	}
	public void setServer_id(String server_id) {
		this.server_id = server_id;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getUrl_admin() {
		return url_admin;
	}
	public void setUrl_admin(String url_admin) {
		this.url_admin = url_admin;
	}
	public String getFn_signin() {
		return fn_signin;
	}
	public void setFn_signin(String fn_signin) {
		this.fn_signin = fn_signin;
	}
	public String getFn_signin_c() {
		return fn_signin_c;
	}
	public void setFn_signin_c(String fn_signin_c) {
		this.fn_signin_c = fn_signin_c;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getPass_forget() {
		return pass_forget;
	}
	public void setPass_forget(String pass_forget) {
		this.pass_forget = pass_forget;
	}
	public int getConfirm_forget() {
		return confirm_forget;
	}
	public void setConfirm_forget(int confirm_forget) {
		this.confirm_forget = confirm_forget;
	}
	public String getFilepdf() {
		return filepdf;
	}
	public void setFilepdf(String filepdf) {
		this.filepdf = filepdf;
	}
		
		
		
	
	
	
}
