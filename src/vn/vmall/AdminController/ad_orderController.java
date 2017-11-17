package vn.vmall.AdminController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.vmall.Entity.Order_Entity;
import vn.vmall.Helper.ErrorMesage;
import vn.vmall.Helper.ErrorMessageModel;
import vn.vmall.Helper.JsonDataGripModel;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.Interface.OrderInterface;






@RestController
@RequestMapping(value="ad/orderController")
public class ad_orderController {
		
	

	 @Autowired
	 @Qualifier("OrderImp")
	 private OrderInterface OrderImp;
	 
	 @RequestMapping(value="/set_order_status",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
				public ErrorMessageModel set_order_finish(@RequestParam(value ="str_id",required = true) String str_id,
						@RequestParam(value ="status",required = true) String status,
						HttpServletRequest req) throws ClassNotFoundException, InstantiationException, IOException, SQLException{
			
			ErrorMessageModel e=new ErrorMessageModel();
			int f=OrderImp.set_order_status(str_id,status);
			e=ErrorMesage.get_json_mes_error(f);
			System.out.println(status+"_"+f);
			
			//send mail to cus if status = delivered
			if(status.equals("1") && f==0){
				String[] ids = str_id.split(",");
				for (String str : ids) {
					str= str.substring(1, str.length()-1);
					System.out.println(str);
					int rs = OrderImp.send_mail_delivered(str, req);
				}
			}
			return e;
			
			
		}
	 @RequestMapping(value="/get_order_by_id",
				method=RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

		public Order_Entity get_order_by_id(	
				@RequestParam(value ="id",required = true) String id){
			
			
			return OrderImp.get_order_by_id(id);
			
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
			JsonDataGripModel<Order_Entity> GripModel =new JsonDataGripModel<Order_Entity>();
			GripModel.setRows(OrderImp.get_list_search_pagg(searchmodel));
			GripModel.setTotal(OrderImp.count_get_list_search_pagg(searchmodel));
			return GripModel;
		}
		
		@RequestMapping(value="/get_json_detail_order.json",
				method=RequestMethod.POST,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

		public JsonDataGripModel get_json_detail_order(
				@RequestParam(value ="page",required = false,defaultValue="1") int page,
				@RequestParam(value ="rows",required = false,defaultValue="10") int rows,
				@RequestParam(value ="col",required = false,defaultValue="") String col,
				@RequestParam(value ="val",required = false,defaultValue="") String val,
				@RequestParam(value ="order_id",required = true) String order_id){
			SearchPaggModel searchmodel=new SearchPaggModel();
			searchmodel.setPage(page);
			searchmodel.setRows(rows);
			searchmodel.setCol(col);
			searchmodel.setVal(val);
			searchmodel.setCustom_value(order_id);
			JsonDataGripModel<Order_Entity> GripModel =new JsonDataGripModel<Order_Entity>();
			GripModel.setRows(OrderImp.get_order_detail(searchmodel));
			GripModel.setTotal(OrderImp.count_get_order_detail(searchmodel));
			return GripModel;
		}
		
		@RequestMapping(value="/add_update_Order",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel add_update_Order(
				@RequestParam(value ="ptype",required = true) String ptype,
				@RequestParam(value ="product_type_vmall",required = false,defaultValue="0") String product_type_vmall,
				@RequestParam(value ="product_type_name",required = true) String product_type_name,
				@RequestParam(value ="category_img",required = false,defaultValue="") String category_img,
				@RequestParam(value ="title_img",required = false,defaultValue="") String title_img){
			Order_Entity d=new Order_Entity();
			//d.setProduct_type_vmall(product_type_vmall);
			//d.setProduct_type_name(product_type_name);
			//d.setTitle_img(title_img);
			//d.setCategory_img(category_img);
			//System.out.print(id);
		//	System.out.print(ptype+"-"+product_type_vmall+"-"+product_type_name+"-"+category_img+"-"+title_img);
			ErrorMessageModel e=new ErrorMessageModel();
			int f= OrderImp.add_update_Order(ptype, d);
			e=ErrorMesage.get_json_mes_error(f);
			return e;
			//return (ptype+"-"+product_type_vmall+"-"+product_type_name+"-"+category_img+"-"+title_img);
			
			
		}
		@RequestMapping(value="/detele_Order",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel detele_Order(@RequestParam(value ="str_id",required = true) String str_id){
			
			ErrorMessageModel e=new ErrorMessageModel();
			int f=OrderImp.delete_Order(str_id);
			e=ErrorMesage.get_json_mes_error(f);
			return e;
			
			
		}
		@RequestMapping(value="/visivled_Order",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel visivled_Order(
				@RequestParam(value ="str_id",required = true) String str_id,
				@RequestParam(value ="visible",required = true) String visible){
			
			ErrorMessageModel e=new ErrorMessageModel();
			int f=OrderImp.visivled_Order(str_id,visible);
			e=ErrorMesage.get_json_mes_error(f);
			return e;
			
			
		}

}
