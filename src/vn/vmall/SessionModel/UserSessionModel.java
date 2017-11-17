package vn.vmall.SessionModel;

import java.util.Date;

public class UserSessionModel {
	private String id;
	private String name;
	private String role;
	private String jsession;
	private Date date_login;
	
	private int  f;
	
	public int getF() {
		return f;
	}
	public void setF(int f) {
		this.f = f;
	}
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getJsession() {
		return jsession;
	}
	public void setJsession(String jsession) {
		this.jsession = jsession;
	}
	public Date getDate_login() {
		return date_login;
	}
	public void setDate_login(Date date) {
		this.date_login = date;
	}
	
	
}
