package vn.vmall.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class LevelPrice_Entity {
	@Column(name="id")
	int id;
	
	@Column(name="name")
	String name;
	
	@Column(name="pquery")
	String pquery;

	@Column(name="product_type_vmall_id")
	String product_type_vmall_id;
	
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

	public String getPquery() {
		return pquery;
	}

	public void setPquery(String pquery) {
		this.pquery = pquery;
	}

	public String getProduct_type_vmall_id() {
		return product_type_vmall_id;
	}

	public void setProduct_type_vmall_id(String product_type_vmall_id) {
		this.product_type_vmall_id = product_type_vmall_id;
	}
	
	
	
	
	
}
