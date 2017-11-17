package vn.vmall.AdminController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.vmall.Entity.Customer_Entity;
import vn.vmall.Helper.ErrorMesage;
import vn.vmall.Helper.ErrorMessageModel;
import vn.vmall.Helper.JsonDataGripModel;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.Interface.CustomerInterface;

@RestController
@RequestMapping(value="ad/CustomerController")
public class ad_CustomerController {
		
	

	 @Autowired
	 @Qualifier("CustomerImp")
	 private CustomerInterface CustomerImp;
	 
	 
	 @RequestMapping(value="/get_customer_by_id",
				method=RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

		public Customer_Entity get_customer_by_id(	
				@RequestParam(value ="id",required = true) String id){
			return CustomerImp.get_customer_by_id(id);			
		}
		
		@RequestMapping(value="/get_json_append_to_datagrip.json",
				method=RequestMethod.POST,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

		public JsonDataGripModel get_json_append_to_datagrip(
				@RequestParam(value ="page",required = false,defaultValue="1") int page,
				@RequestParam(value ="rows",required = false,defaultValue="10") int rows,
				@RequestParam(value ="col",required = false,defaultValue="") String col,
				@RequestParam(value ="val",required = false,defaultValue="") String val){
			SearchPaggModel searchmodel=new SearchPaggModel();
			searchmodel.setPage(page);
			searchmodel.setRows(rows);
			searchmodel.setCol(col);
			searchmodel.setVal(val);
			JsonDataGripModel<Customer_Entity> GripModel =new JsonDataGripModel<Customer_Entity>();
			GripModel.setRows(CustomerImp.get_list_search_pagg(searchmodel));
			GripModel.setTotal(CustomerImp.count_get_list_search_pagg(searchmodel));
			return GripModel;
		}
		
		
		@RequestMapping(value="/add_update_customer",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel add_update_customer(
				@RequestParam(value ="ptype",required = true) String ptype,
				@RequestParam(value ="product_type_vmall",required = false,defaultValue="0") String product_type_vmall,
				@RequestParam(value ="product_type_name",required = true) String product_type_name,
				@RequestParam(value ="category_img",required = false,defaultValue="") String category_img,
				@RequestParam(value ="title_img",required = false,defaultValue="") String title_img){
			Customer_Entity d=new Customer_Entity();
			//d.setProduct_type_vmall(product_type_vmall);
			//d.setProduct_type_name(product_type_name);
			//d.setTitle_img(title_img);
			//d.setCategory_img(category_img);
			//System.out.print(id);
		//	System.out.print(ptype+"-"+product_type_vmall+"-"+product_type_name+"-"+category_img+"-"+title_img);
			ErrorMessageModel e=new ErrorMessageModel();
			int f= CustomerImp.add_update_customer(ptype, d);
			e=ErrorMesage.get_json_mes_error(f);
			return e;	
		}
		@RequestMapping(value="/detele_customer",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel detele_customer(@RequestParam(value ="str_id",required = true) String str_id){
			
			ErrorMessageModel e=new ErrorMessageModel();
			int f=CustomerImp.delete_customer(str_id);
			e=ErrorMesage.get_json_mes_error(f);
			return e;
			
			
		}
		@RequestMapping(value="/do_visible",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel visivled_customer(
				@RequestParam(value ="str_id",required = true) String str_id,
				@RequestParam(value ="visible",required = true) String visible){
			
			ErrorMessageModel e=new ErrorMessageModel();
			int f=CustomerImp.visivled_customer(str_id,visible);
			e=ErrorMesage.get_json_mes_error(f);
			return e;
		}
		@RequestMapping(value="/do_approve",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel do_approve(
				@RequestParam(value ="str_id",required = true) String str_id,
				@RequestParam(value ="approve",required = true) String approve,
				@RequestParam(value ="email",required = true) String email,
				 HttpServletRequest request){
			
			ErrorMessageModel e=new ErrorMessageModel();
			int f=11;//error send mail in tb_error
			int flag_sendmail=CustomerImp.send_mail_to_when_approve(str_id,approve,email,request);//chua lam from Email
			if(flag_sendmail==1){
				f=CustomerImp.do_approve(str_id,approve);
			}
			e=ErrorMesage.get_json_mes_error(f);
			return e;
		}
			
		
	@RequestMapping(value="/get_allcustomer",
				method=RequestMethod.GET,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Customer_Entity> get_allcustomer(){
		return CustomerImp.get_allcustomer();
		
	}
}
