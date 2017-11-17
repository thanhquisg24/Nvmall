package vn.vmall.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;



@Entity
public class Member_Entity  {
	@Column(name="email")
	private String email;

	@Column(name="address")
	private String address;

	@Column(name="address_delivery")
	private String address_delivery;

	@Column(name="birthday")
	private String birthday;

	@Column(name="confirm")
	private int confirm;

	@Column(name="confirm_forget")
	private int confirm_forget;

	@Column(name="fullname")
	private String fullname;

	@Column(name="gender")
	private String gender;

	@Column(name="id")
	private String id;

	@Column(name="pass")
	private String pass;
	
	private String passnew;
	
	
	public String getPassnew() {
		return passnew;
	}

	public void setPassnew(String passnew) {
		this.passnew = passnew;
	}

	@Column(name="pass_forget")
	private String pass_forget;
	
	@Column(name="phone")
	private String phone;

	@Column(name="register_date")
	private String register_date;
	
	@Column(name="status")
	private String status;

	@Column(name="syn_status")
	private String syn_status;

	public Member_Entity() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress_delivery() {
		return address_delivery;
	}

	public void setAddress_delivery(String address_delivery) {
		this.address_delivery = address_delivery;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public int getConfirm() {
		return confirm;
	}

	public void setConfirm(int confirm) {
		this.confirm = confirm;
	}



	public int getConfirm_forget() {
		return confirm_forget;
	}

	public void setConfirm_forget(int confirm_forget) {
		this.confirm_forget = confirm_forget;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPass_forget() {
		return pass_forget;
	}

	public void setPass_forget(String pass_forget) {
		this.pass_forget = pass_forget;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRegister_date() {
		return register_date;
	}

	public void setRegister_date(String register_date) {
		this.register_date = register_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSyn_status() {
		return syn_status;
	}

	public void setSyn_status(String syn_status) {
		this.syn_status = syn_status;
	}

	@Override
	public String toString() {
		return "Member_Entity [email=" + email + ", address=" + address
				+ ", address_delivery=" + address_delivery + ", birthday="
				+ birthday + ", confirm=" + confirm + ", confirm_forget="
				+ confirm_forget + ", fullname=" + fullname + ", gender="
				+ gender + ", id=" + id + ", pass=" + pass + ", pass_forget="
				+ pass_forget + ", phone=" + phone + ", register_date="
				+ register_date + ", status=" + status + ", syn_status="
				+ syn_status + "]";
	}
	
	

}