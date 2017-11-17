package vn.vmall.AdminController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.vmall.Entity.CatgorySub_Entity;
import vn.vmall.Helper.ErrorMesage;
import vn.vmall.Helper.ErrorMessageModel;
import vn.vmall.Helper.JsonDataGripModel;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.Interface.CatgorySubInterface;



@RestController
@RequestMapping(value="ad/ad_Eproductypesub")
public class ad_Eproductypesub {

	 @Autowired
	 @Qualifier("CatgorySubImp")
	 private CatgorySubInterface CatgorySubImp;
	 
	 	@RequestMapping(value="/get_prodcutypesubfull_by_id",
				method=RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public CatgorySub_Entity get_prodcutypesubfull_by_id(	
				@RequestParam(value ="id",required = true) String id){
			return CatgorySubImp.get_prodcutypesubfull_by_id(id);
		}
		@RequestMapping(value="/save_function_name",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel save_function_name(
				@RequestParam(value ="id",required = true) String id,
				@RequestParam(value ="store_name",required = true) String store_name)
				{
			CatgorySub_Entity d=new CatgorySub_Entity();
			d.setId(id);
			d.setStore_name(store_name);
			ErrorMessageModel e=new ErrorMessageModel();
			int f= CatgorySubImp.save_function_name(d);
			e=ErrorMesage.get_json_mes_error(f);
			return e;
		}
		
		@RequestMapping(value="/get_json_append_to_datagrip.json",
				method=RequestMethod.POST,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public JsonDataGripModel get_json_append_to_datagrip(
				@RequestParam(value ="parentid",required = true) String parentid,
				@RequestParam(value ="page",required = false,defaultValue="1") int page,
				@RequestParam(value ="rows",required = false,defaultValue="10") int rows,
				@RequestParam(value ="col",required = false,defaultValue="") String col,
				@RequestParam(value ="val",required = false,defaultValue="") String val){
			SearchPaggModel searchmodel=new SearchPaggModel();
			searchmodel.setPage(page);
			searchmodel.setRows(rows);
			searchmodel.setCol(col);
			searchmodel.setVal(val);
			searchmodel.setParent(parentid);
			//System.out.println(parentid);
			JsonDataGripModel<CatgorySub_Entity> GripModel =new JsonDataGripModel<CatgorySub_Entity>();
			GripModel.setRows(CatgorySubImp.E_get_list_search_pagg(searchmodel));
			GripModel.setTotal(CatgorySubImp.E_count_get_list_search_pagg(searchmodel));
			return GripModel;
			//return "{'aa':'ồ ố ô ộ'}";
		}
		
		
		@RequestMapping(value="/get_json_sub_append_to_datagrip.json",
				method=RequestMethod.POST,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public JsonDataGripModel get_json_sub_append_to_datagrip(
				@RequestParam(value ="parentid",required = true) String parentid,
				@RequestParam(value ="page",required = false,defaultValue="1") int page,
				@RequestParam(value ="rows",required = false,defaultValue="10") int rows,
				@RequestParam(value ="col",required = false,defaultValue="") String col,
				@RequestParam(value ="val",required = false,defaultValue="") String val){
			SearchPaggModel searchmodel=new SearchPaggModel();
			searchmodel.setPage(page);
			searchmodel.setRows(rows);
			searchmodel.setCol(col);
			searchmodel.setVal(val);
			searchmodel.setParent(parentid);
			//System.out.println(parentid);
			JsonDataGripModel<CatgorySub_Entity> GripModel =new JsonDataGripModel<CatgorySub_Entity>();
			GripModel.setRows(CatgorySubImp.E_get_list_search_pagg_sub(searchmodel));
			GripModel.setTotal(CatgorySubImp.E_count_get_list_search_pagg_sub(searchmodel));
			return GripModel;
			//return "{'aa':'ồ ố ô ộ'}";
		}
		
}
