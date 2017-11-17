package vn.vmall.AdminController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import vn.vmall.Entity.Customer_Entity;
import vn.vmall.Entity.proRecommended_Entity;
import vn.vmall.Helper.ErrorMesage;
import vn.vmall.Helper.ErrorMessageModel;
import vn.vmall.Helper.JsonDataGripModel;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.Interface.proRecommendedInterface;
import vn.vmall.SessionModel.UserSessionModel;


@RestController
@RequestMapping(value="ad/proRecommendedController")
@SessionAttributes(value = "user", types = {UserSessionModel.class})
public class ad_proRecommendedController {
		
	 @Autowired
	 @Qualifier(" proRecommendedImp")
	 private proRecommendedInterface  proRecommendedImp;
	 
	 
	 @RequestMapping(value="/get_proRecommended_by_id",
				method=RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

		public proRecommended_Entity get_proRecommended_by_id(	
				@RequestParam(value ="id",required = true) String id){
			
			
			return  proRecommendedImp.get_proRecommended_by_id(id);
			
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
			JsonDataGripModel<proRecommended_Entity> GripModel =new JsonDataGripModel<proRecommended_Entity>();
			GripModel.setRows( proRecommendedImp.get_list_search_pagg(searchmodel));
			GripModel.setTotal( proRecommendedImp.count_get_list_search_pagg(searchmodel));
			return GripModel;
		}
		@RequestMapping(value="/get_datagrip_byproducttypeid.json",
				method=RequestMethod.POST,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

		public JsonDataGripModel get_datagrip_byproducttypeid(
				@RequestParam(value ="page",required = false,defaultValue="1") int page,
				@RequestParam(value ="rows",required = false,defaultValue="10") int rows,
				@RequestParam(value ="product_type_id",required = false,defaultValue="") String custom_value){
			SearchPaggModel searchmodel=new SearchPaggModel();
			searchmodel.setPage(page);
			searchmodel.setRows(rows);
			searchmodel.setCustom_value(custom_value);
			JsonDataGripModel<proRecommended_Entity> GripModel =new JsonDataGripModel<proRecommended_Entity>();
			GripModel.setRows( proRecommendedImp.get_datagrip_byproducttypeid(searchmodel));
			GripModel.setTotal( proRecommendedImp.count_get_datagrip_byproducttypeid(searchmodel));
			return GripModel;
		}
		
		
		@RequestMapping(value="/add_update_proRecommended",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel add_update_proRecommended(
				@ModelAttribute("user") UserSessionModel user,
				@RequestParam(value ="custom_value",required = true) String custom_value) throws InstantiationException, ClassNotFoundException, SQLException{
			
			ErrorMessageModel e=new ErrorMessageModel();
			int f=  proRecommendedImp.add_update_proRecommended(custom_value);
			e=ErrorMesage.get_json_mes_error(f);
			return e;

		}
		@RequestMapping(value="/delete_multi_proRecommended",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel detele_proRecommended(@RequestParam(value ="jsons",required = true) String jsons)
				throws JsonParseException, JsonMappingException, IOException,
				SQLException, InstantiationException, ClassNotFoundException{
			ErrorMessageModel e=new ErrorMessageModel();
			int f= proRecommendedImp.delete_multi_proRecommended(jsons);
			e=ErrorMesage.get_json_mes_error(f);
			return e;
		}
		
		@RequestMapping(value="/detele_single_proRecommended",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel detele_single_proRecommended(@RequestParam(value ="str_id",required = true) String str_id){
			
			ErrorMessageModel e=new ErrorMessageModel();
			int f= proRecommendedImp.detele_single_proRecommended(str_id);
			e=ErrorMesage.get_json_mes_error(f);
			return e;
			
			
		}
		
		@RequestMapping(value="/visivled_proRecommended",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel visivled_proRecommended(
				@RequestParam(value ="jsons",required = true) String jsons,
				@RequestParam(value ="visible",required = true) String visible) 
						throws JsonParseException, JsonMappingException, IOException,
						SQLException, InstantiationException, ClassNotFoundException{
			ErrorMessageModel e=new ErrorMessageModel();
			int f= proRecommendedImp.visivled_proRecommended(jsons,visible);
			e=ErrorMesage.get_json_mes_error(f);
			return e;
		}
	@RequestMapping(value="/get_allbranh",
				method=RequestMethod.GET,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public List<proRecommended_Entity> get_allbranh(){
		return  proRecommendedImp.get_allbranh();
		
	}
	
	@RequestMapping(value="/get_datagrip_customer_product.json",
				method=RequestMethod.POST,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

		public JsonDataGripModel get_datagrip_customer_product(
				@RequestParam(value ="page",required = false,defaultValue="1") int page,
				@RequestParam(value ="rows",required = false,defaultValue="10") int rows,
				@RequestParam(value ="customvalue",required = false,defaultValue="") String custom_value){
			SearchPaggModel searchmodel=new SearchPaggModel();
			searchmodel.setPage(page);
			searchmodel.setRows(rows);
			searchmodel.setCustom_value(custom_value);
			JsonDataGripModel<proRecommended_Entity> GripModel =new JsonDataGripModel<proRecommended_Entity>();
			GripModel.setRows( proRecommendedImp.get_datagrip_customer_product(searchmodel));
			GripModel.setTotal( proRecommendedImp.count_get_datagrip_customer_product(searchmodel));
			return GripModel;
		}
	 @RequestMapping(value="/get_customer_of_type",
				method=RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

		public List<Customer_Entity> get_customer_of_type(	
				@RequestParam(value ="type_id",required = true) String type_id){
			return  proRecommendedImp.get_customer_of_type(type_id);
			
		}
}