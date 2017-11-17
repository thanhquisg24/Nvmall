package vn.vmall.AdminController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.vmall.Entity.CatgoryCus_Entity;
import vn.vmall.Entity.CatgorySub_Entity;
import vn.vmall.Helper.ErrorMesage;
import vn.vmall.Helper.ErrorMessageModel;
import vn.vmall.Helper.JsonDataGripModel;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.Interface.CatgoryCusInterface;






@RestController
@RequestMapping(value="ad/product_type_customerController")
public class ad_product_type_customerController {
		
	

	 @Autowired
	 @Qualifier("CatgoryCusImp")
	 private CatgoryCusInterface CatgoryCusImp;
	 
	 
	 @RequestMapping(value="/get_catgory_by_id",
				method=RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

		public CatgoryCus_Entity get_catgory_by_id(	
				@RequestParam(value ="id",required = true) String id){
			return CatgoryCusImp.get_catgory_by_id(id);
			
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
			JsonDataGripModel<CatgoryCus_Entity> GripModel =new JsonDataGripModel<CatgoryCus_Entity>();
			GripModel.setRows(CatgoryCusImp.get_list_search_pagg(searchmodel));
			GripModel.setTotal(CatgoryCusImp.count_get_list_search_pagg(searchmodel));
			return GripModel;
			//return "{'aa':'ồ ố ô ộ'}";
			
		}
		
		
		@RequestMapping(value="/add_update_catgory",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel add_update_catgory(
				@RequestParam(value ="ptype",required = true) String ptype,
				@RequestParam(value ="typeparent",required = true) String typeparent,
				@RequestParam(value ="typesub",required = true) String typesub,
				@RequestParam(value ="customerid",required = true) String customerid,
				@RequestParam(value ="customercate",required = true) String customercate,
				@RequestParam(value ="typenamecus",required = true) String typenamecus,
				@RequestParam(value ="category_img",required = false,defaultValue="") String category_img,
				@RequestParam(value ="title_img",required = false,defaultValue="") String title_img)
		{
			CatgoryCus_Entity d=new CatgoryCus_Entity();

			d.setGroup_category_id(typeparent);;
			d.setCategory_id(typesub);
			d.setCustomer_id(customerid);
			d.setProduct_type_id(customercate);
			d.setProduct_type_name(typenamecus);	
			d.setCategory_img(category_img);
			d.setTitle_img(title_img);
			//System.out.print(ptype+"-"+typeparent+"-"+typesub+"-"+customerid+"-"+customercate+"-"+typenamecus);
			ErrorMessageModel e=new ErrorMessageModel();
			int f= CatgoryCusImp.add_update_catgory(ptype, d);
			e=ErrorMesage.get_json_mes_error(f);
			return e;
			//return (ptype+"-"+product_type_vmall+"-"+product_type_name+"-"+category_img+"-"+title_img);
		
		}
		@RequestMapping(value="/detele_catgory",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel detele_catgory(@RequestParam(value ="str_id",required = true) String str_id){
			
			ErrorMessageModel e=new ErrorMessageModel();
			int f=CatgoryCusImp.delete_catgory(str_id);
			e=ErrorMesage.get_json_mes_error(f);
			return e;	
		}
		
		@RequestMapping(value="/visivled_catgory",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel visivled_catgory(
				@RequestParam(value ="str_id",required = true) String str_id,
				@RequestParam(value ="visible",required = true) String visible){
			
			ErrorMessageModel e=new ErrorMessageModel();
			int f=CatgoryCusImp.visivled_catgory(str_id,visible);
			e=ErrorMesage.get_json_mes_error(f);
			return e;
			
			
		}
	@RequestMapping(value="/get_allcatgory",
				method=RequestMethod.GET,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<CatgoryCus_Entity> get_allcatgory(){
		return CatgoryCusImp.get_allcatgory();
		
	}
	@RequestMapping(value="/approve_catgory",
			method=RequestMethod.POST,
			produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ErrorMessageModel approve_catgory(@RequestParam(value ="str_id",required = true) String str_id){
		
		ErrorMessageModel e=new ErrorMessageModel();
		int f=CatgoryCusImp.approve_catgory(str_id);
		e=ErrorMesage.get_json_mes_error(f);
		return e;	
	}

}
