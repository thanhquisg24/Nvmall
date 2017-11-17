package vn.vmall.Entity;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class OrderDetail_Entity {

	
		@Column(name="order_id")
		private String order_id;
		
		@Column(name="email")
		private String email;
		
		@Column(name="payment_method_id")
		private String payment_method_id;
		
		@Column(name="order_date")
		private String order_date;
		
		@Column(name="order_status")
		private String order_status;
		
		@Column(name="delivery_method_id")
		private String delivery_method_id;
		
		@Column(name="address_delivery")
		private String address_delivery;
		
		@Column(name="note")
		private String note;
		
		@Column(name="invoice")
		private String invoice;
		
		@Column(name="isstate")
		private String isstate;
		
		@Column(name="issyn")
		private String issyn;
		
		@Column(name="status_nganluong")
		private String status_nganluong;
		
		@Column(name="payment_type_nl")
		private String payment_type_nl;
		
		@Column(name="refund_type_nl")
		private String refund_type_nl;
		
		@Column(name="phone")
		private String phone;
		
		
		

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getOrder_id() {
			return order_id;
		}

		public void setOrder_id(String order_id) {
			this.order_id = order_id;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPayment_method_id() {
			return payment_method_id;
		}

		public void setPayment_method_id(String payment_method_id) {
			this.payment_method_id = payment_method_id;
		}

		public String getOrder_date() {
			return order_date;
		}

		public void setOrder_date(String order_date) {
			this.order_date = order_date;
		}

		public String getOrder_status() {
			return order_status;
		}

		public void setOrder_status(String order_status) {
			this.order_status = order_status;
		}

		public String getDelivery_method_id() {
			return delivery_method_id;
		}

		public void setDelivery_method_id(String delivery_method_id) {
			this.delivery_method_id = delivery_method_id;
		}

		public String getAddress_delivery() {
			return address_delivery;
		}

		public void setAddress_delivery(String address_delivery) {
			this.address_delivery = address_delivery;
		}

		public String getNote() {
			return note;
		}

		public void setNote(String note) {
			this.note = note;
		}

		public String getInvoice() {
			return invoice;
		}

		public void setInvoice(String invoice) {
			this.invoice = invoice;
		}

		public String getIsstate() {
			return isstate;
		}

		public void setIsstate(String isstate) {
			this.isstate = isstate;
		}

		public String getIssyn() {
			return issyn;
		}

		public void setIssyn(String issyn) {
			this.issyn = issyn;
		}

		public String getStatus_nganluong() {
			return status_nganluong;
		}

		public void setStatus_nganluong(String status_nganluong) {
			this.status_nganluong = status_nganluong;
		}

		public String getPayment_type_nl() {
			return payment_type_nl;
		}

		public void setPayment_type_nl(String payment_type_nl) {
			this.payment_type_nl = payment_type_nl;
		}

		public String getRefund_type_nl() {
			return refund_type_nl;
		}

		public void setRefund_type_nl(String refund_type_nl) {
			this.refund_type_nl = refund_type_nl;
		}
		
		@Column(name="customer_id")
		private String customer_id;
		
		@Column(name="product_id")
		private String product_id;
		
		@Column(name="quantity")
		private String quantity;
		
		@Column(name="price")
		private float price;
		
		@Column(name="old_price")
		private float old_price;
		
		@Column(name="amount")
		private float amount;

		public String getCustomer_id() {
			return customer_id;
		}

		public void setCustomer_id(String customer_id) {
			this.customer_id = customer_id;
		}

		public String getProduct_id() {
			return product_id;
		}

		public void setProduct_id(String product_id) {
			this.product_id = product_id;
		}

		public String getQuantity() {
			return quantity;
		}

		public void setQuantity(String quantity) {
			this.quantity = quantity;
		}

		public float getPrice() {
			return price;
		}

		public void setPrice(float price) {
			this.price = price;
		}

		public float getOld_price() {
			return old_price;
		}

		public void setOld_price(float old_price) {
			this.old_price = old_price;
		}

		public float getAmount() {
			return amount;
		}

		public void setAmount(float amount) {
			this.amount = amount;
		}
		
		@Column(name="product_name")
		private String product_name;
		
		@Column(name="customer_name")
		private String customer_name;

		@Column(name="shop_name")
		private String shop_name;
		
		@Column(name="shop_url")
		private String shop_url;

		
		public String getProduct_name() {
			return product_name;
		}

		public void setProduct_name(String product_name) {
			this.product_name = product_name;
		}

		public String getCustomer_name() {
			return customer_name;
		}

		public void setCustomer_name(String customer_name) {
			this.customer_name = customer_name;
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
		

}
