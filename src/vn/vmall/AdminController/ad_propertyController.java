package vn.vmall.AdminController;

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

import vn.vmall.Entity.Property_Entity;
import vn.vmall.Helper.ErrorMesage;
import vn.vmall.Helper.ErrorMessageModel;
import vn.vmall.Helper.JsonDataGripModel;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.Interface.PropertyInterface;
import vn.vmall.SessionModel.UserSessionModel;


@RestController
@RequestMapping(value="ad/propertyController")
//@SessionAttributes(value = "user", types = {UserSessionModel.class})
public class ad_propertyController {
		
	 @Autowired
	 @Qualifier("PropertyImp")
	 private PropertyInterface  PropertyImp;
	 
	 
	 @RequestMapping(value="/get_property_by_id",
				method=RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

		public Property_Entity get_property_by_id(	
				@RequestParam(value ="id",required = true) String id){		
			return  PropertyImp.get_property_by_id(id);
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
			JsonDataGripModel<Property_Entity> GripModel =new JsonDataGripModel<Property_Entity>();
			GripModel.setRows( PropertyImp.get_list_search_pagg(searchmodel));
			GripModel.setTotal( PropertyImp.count_get_list_search_pagg(searchmodel));
			return GripModel;
		}
		@RequestMapping(value="/get_datagrip_byparentid.json",
				method=RequestMethod.POST,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

		public JsonDataGripModel get_datagrip_byproducttypeid(
				@RequestParam(value ="page",required = false,defaultValue="1") int page,
				@RequestParam(value ="rows",required = false,defaultValue="10") int rows,
				@RequestParam(value ="parentid",required = false,defaultValue="") String parentid){
			SearchPaggModel searchmodel=new SearchPaggModel();
			searchmodel.setPage(page);
			searchmodel.setRows(rows);
			searchmodel.setCustom_value(parentid);
			JsonDataGripModel<Property_Entity> GripModel =new JsonDataGripModel<Property_Entity>();
			GripModel.setRows(PropertyImp.get_datagrip_byparentid(searchmodel));
			GripModel.setTotal(PropertyImp.count_get_datagrip_byparentid(searchmodel));
			return GripModel;
		}
		
		
		@RequestMapping(value="/add_update_property",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel add_update_property(
				//@ModelAttribute("user") UserSessionModel user,
				@RequestParam(value ="ptype",required = true) String ptype,
				@RequestParam(value ="product_type_id",required = true) String product_type_id,
				@RequestParam(value ="parentproperty",required = true) String parentproperty,
				@RequestParam(value ="propertyid",required = false,defaultValue="") String propertyid,
				@RequestParam(value ="propertyname",required = false,defaultValue="") String propertyname){
			Property_Entity d=new Property_Entity();
			d.setId(propertyid);
			d.setParent(parentproperty);
			d.setProduct_type_sub_id(product_type_id);
			d.setProperty_name(propertyname);
			//System.out.print(ptype+"-"+product_type_id+"-"+parentproperty+"-"+propertyid+"-"+propertyname);
			ErrorMessageModel e=new ErrorMessageModel();
			int f=  PropertyImp.add_update_property(ptype, d);
			e=ErrorMesage.get_json_mes_error(f);
			return e;
			//return (ptype+"-"+product_type_vmall+"-"+product_type_name+"-"+category_img+"-"+title_img);
			
			
		}
		@RequestMapping(value="/delete_multi_property",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel detele_branch(
				@RequestParam(value ="strproductype",required = true) String strproductype,
				@RequestParam(value ="strparentid",required = true) String strparentid,
				@RequestParam(value ="strpropertyids",required = true) String strpropertyids){
			
			ErrorMessageModel e=new ErrorMessageModel();
			int f= PropertyImp.delete_multi_property(strproductype,strparentid,strpropertyids);
			e=ErrorMesage.get_json_mes_error(f);
			return e;
			
			
		}
		
		@RequestMapping(value="/detele_single_property",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel detele_single_property(@RequestParam(value ="str_id",required = true) String str_id){
			
			ErrorMessageModel e=new ErrorMessageModel();
			int f= PropertyImp.detele_single_property(str_id);
			e=ErrorMesage.get_json_mes_error(f);
			return e;
			
			
		}
		
		@RequestMapping(value="/visivled_property_3option",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel visivled_property(
				@RequestParam(value ="strproductype",required = true) String strproductype,
				@RequestParam(value ="strparentid",required = true) String strparentid,
				@RequestParam(value ="strpropertyids",required = true) String strpropertyids,
				@RequestParam(value ="visible",required = true) String visible){
			
			ErrorMessageModel e=new ErrorMessageModel();
			int f= PropertyImp.visivled_property(strproductype,strparentid,strpropertyids,visible);
			e=ErrorMesage.get_json_mes_error(f);
			return e;
			
			
		}
		
	@RequestMapping(value="/get_property_parent_byproducttypeid.json",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public List<Property_Entity> get_property_parent_byproducttypeid(
				@RequestParam(value ="product_type_id",required = true) String product_type_id){
		return  PropertyImp.get_property_parent_byproducttypeid(product_type_id);
		
	}	
		
		
	@RequestMapping(value="/get_allproperty",
				method=RequestMethod.GET,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public List<Property_Entity> get_allproperty(){
		return  PropertyImp.get_allproperty();
		
	}
}



