package vn.vmall.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class DemoCRUD_Entity {

	@Column(name="id")
	private String id;

	@Column(name="address")
	private String address;

	@Column(name="create_date")
	private String create_date;
	
	@Column(name="creator")
	private String creator;

	@Column(name="email")
	private String email;

	@Column(name="is_enabled")
	private byte is_enabled;
	
	@Column(name="name")
	private String name;

	@Column(name="phone")
	private String phone;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte getIs_enabled() {
		return is_enabled;
	}

	public void setIs_enabled(byte is_enabled) {
		this.is_enabled = is_enabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
