package vn.vmall.Entity;



public class Fee_Entity {
	private String id_fee;
	private String fee_name;
	private float  fee; 
	private float moption;
	public String getId_fee() {
		return id_fee;
	}
	public void setId_fee(String id_fee) {
		this.id_fee = id_fee;
	}
	public String getFee_name() {
		return fee_name;
	}
	public void setFee_name(String fee_name) {
		this.fee_name = fee_name;
	}
	public float getFee() {
		return fee;
	}
	public void setFee(float fee) {
		this.fee = fee;
	}
	public float getMoption() {
		return moption;
	}
	public void setMoption(float moption) {
		this.moption = moption;
	}
	
}
