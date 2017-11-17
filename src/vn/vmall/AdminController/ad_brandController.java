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

import vn.vmall.Entity.Brand_Entity;
import vn.vmall.Helper.ErrorMesage;
import vn.vmall.Helper.ErrorMessageModel;
import vn.vmall.Helper.JsonDataGripModel;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.Interface.BrandInterface;
import vn.vmall.SessionModel.UserSessionModel;


@RestController
@RequestMapping(value="ad/brandController")
@SessionAttributes(value = "user", types = {UserSessionModel.class})
public class ad_brandController {
		
	 @Autowired
	 @Qualifier("BrandImp")
	 private BrandInterface  BrandImp;
	 
	 
	 @RequestMapping(value="/get_branch_by_id",
				method=RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public Brand_Entity get_branch_by_id(	
				@RequestParam(value ="id",required = true) String id){		
			return  BrandImp.get_branch_by_id(id);
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
			JsonDataGripModel<Brand_Entity> GripModel =new JsonDataGripModel<Brand_Entity>();
			GripModel.setRows( BrandImp.get_list_search_pagg(searchmodel));
			GripModel.setTotal( BrandImp.count_get_list_search_pagg(searchmodel));
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
			JsonDataGripModel<Brand_Entity> GripModel =new JsonDataGripModel<Brand_Entity>();
			GripModel.setRows( BrandImp.get_datagrip_byproducttypeid(searchmodel));
			GripModel.setTotal( BrandImp.count_get_datagrip_byproducttypeid(searchmodel));
			return GripModel;
		}
		
		
		@RequestMapping(value="/add_update_branch",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel add_update_branch(
				@ModelAttribute("user") UserSessionModel user,
				@RequestParam(value ="ptype",required = true) String ptype,
				@RequestParam(value ="brandid",required = false,defaultValue="") String branchid,
				@RequestParam(value ="brandname",required = false,defaultValue="") String brandname,
				@RequestParam(value ="brandcontry",required = false,defaultValue="") String brandcontry,
				@RequestParam(value ="brandimg",required = false,defaultValue="") String brandimg){
			Brand_Entity d=new Brand_Entity();
			d.setId(branchid);
			d.setName(brandname);
			d.setCountry(brandcontry);
			d.setImage(brandimg);
			d.setCreate_user(user.getId());
			//System.out.print(id);
		//	System.out.print(ptype+"-"+product_type_vmall+"-"+product_type_name+"-"+category_img+"-"+title_img);
			ErrorMessageModel e=new ErrorMessageModel();
			int f=  BrandImp.add_update_branch(ptype, d);
			e=ErrorMesage.get_json_mes_error(f);
			return e;
			//return (ptype+"-"+product_type_vmall+"-"+product_type_name+"-"+category_img+"-"+title_img);
		}
		@RequestMapping(value="/delete_multi_brand",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel detele_branch(@RequestParam(value ="str_id",required = true) String str_id){
			
			ErrorMessageModel e=new ErrorMessageModel();
			int f= BrandImp.delete_multi_branch(str_id);
			e=ErrorMesage.get_json_mes_error(f);
			return e;
		}
		
		@RequestMapping(value="/detele_single_branch",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel detele_single_branch(@RequestParam(value ="str_id",required = true) String str_id){
			
			ErrorMessageModel e=new ErrorMessageModel();
			int f= BrandImp.detele_single_branch(str_id);
			e=ErrorMesage.get_json_mes_error(f);
			return e;
		}
		
		@RequestMapping(value="/visivled_brand",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel visivled_brand(
				@RequestParam(value ="str_id",required = true) String str_id,
				@RequestParam(value ="visible",required = true) String visible){
			ErrorMessageModel e=new ErrorMessageModel();
			int f= BrandImp.visivled_brand(str_id,visible);
			e=ErrorMesage.get_json_mes_error(f);
			return e;
		}
	@RequestMapping(value="/get_allbranh",
				method=RequestMethod.GET,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public List<Brand_Entity> get_allbranh(){
		return  BrandImp.get_allbranh();
		
	}
}