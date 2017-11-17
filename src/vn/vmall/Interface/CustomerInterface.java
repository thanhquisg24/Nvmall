package vn.vmall.Interface;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import vn.vmall.Entity.Customer_Entity;
import vn.vmall.Helper.SearchPaggModel;




public interface CustomerInterface {
	
	List<Customer_Entity> get_list_search_pagg(SearchPaggModel searchmodel);
	 int count_get_list_search_pagg(SearchPaggModel searchmodel);
	 int add_update_customer(String ptype,Customer_Entity d);
	 Customer_Entity get_customer_by_id(String id);	
	 int delete_customer(String str_id);
	int visivled_customer(String str_id,String visible);
	List<Customer_Entity> get_allcustomer();
	int do_approve(String str_id, String approve);
	int send_mail_to_when_approve(String customerid,String approve,String email, HttpServletRequest request);
	

}
