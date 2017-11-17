package vn.vmall.Imp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.vmall.Service.ReadServiceMail;
import vn.vmall.DAL.CustomerDAL;
import vn.vmall.Entity.Customer_Entity;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.Interface.CustomerInterface;


@Component(value="CustomerImp")
public class CustomerImp implements CustomerInterface {

	@Autowired
	private CustomerDAL DAL;
	
	@Override
	public List<Customer_Entity> get_list_search_pagg(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.get_list_search_pagg(searchmodel);
	}

	@Override
	public int count_get_list_search_pagg(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.count_get_list_search_pagg(searchmodel);
	}

	@Override
	public int add_update_customer(String ptype, Customer_Entity d) {
		// TODO Auto-generated method stub
		return DAL.add_update_customer(ptype, d);

	}

	@Override
	public Customer_Entity get_customer_by_id(String id) {
		return DAL.get_customer_by_id(id);
	}

	@Override
	public int delete_customer(String str_id) {
		return DAL.delete_customer(str_id);
	}

	@Override
	public int visivled_customer(String str_id,String visible) {
		return DAL.visivled_customer(str_id,visible);
	}

	
	@Override
	public int do_approve(String str_id, String approve) {
		return DAL.do_approve( str_id,  approve);
	}

	@Override
	public List<Customer_Entity> get_allcustomer() {
		return DAL.get_allcustomer();
	}
	
	
	//chua lam from Email
	@Override
	public  int send_mail_to_when_approve(String customerid,String approve,String email, HttpServletRequest request) {//chua lam from Email
		try {
			int fapprove=Integer.parseInt(approve);
			String title="";
			String content="";
			if(fapprove==1){
				title="approve customer";
				content="cofirm approve";
			}else{
				title="Unapprove customer";
				content="cofirm Unapprove";
			}
			int rs = ReadServiceMail.SendingFromgmail(email, title,content, request);
			
		} catch (Exception ex) {
			return 1;
		}
		return 1;
	}

}
