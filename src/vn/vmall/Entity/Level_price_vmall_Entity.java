package vn.vmall.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Level_price_vmall_Entity {
	
	
	@Column(name="level_price_id")
	String level_price_id;
	
	@Column(name="product_type_vmall_id")
	String product_type_vmall_id;
	
	@Column(name="tb_level_price_0_name")
	private String tb_level_price_0_name;
	
	@Column(name="tb_level_price_0_pquery")
	private String tb_level_price_0_pquery;
	
	public String getLevel_price_id() {
		return level_price_id;
	}

	public void setLevel_price_id(String level_price_id) {
		this.level_price_id = level_price_id;
	}

	public String getProduct_type_vmall_id() {
		return product_type_vmall_id;
	}

	public void setProduct_type_vmall_id(String product_type_vmall_id) {
		this.product_type_vmall_id = product_type_vmall_id;
	}

	public String getTb_level_price_0_name() {
		return tb_level_price_0_name;
	}

	public void setTb_level_price_0_name(String tb_level_price_0_name) {
		this.tb_level_price_0_name = tb_level_price_0_name;
	}

	public String getTb_level_price_0_pquery() {
		return tb_level_price_0_pquery;
	}

	public void setTb_level_price_0_pquery(String tb_level_price_0_pquery) {
		this.tb_level_price_0_pquery = tb_level_price_0_pquery;
	}

	
	
	
	
}
