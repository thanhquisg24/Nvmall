package vn.vmall.AdminController;

import java.util.List;

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
@RequestMapping(value="ad/product_type_subController")
public class ad_product_type_subController {
		
	

	 @Autowired
	 @Qualifier("CatgorySubImp")
	 private CatgorySubInterface CatgorySubImp;
	 	@RequestMapping(value="/get_catgory_by_id",
				method=RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public CatgorySub_Entity get_catgory_by_id(	
				@RequestParam(value ="id",required = true) String id){
			return CatgorySubImp.get_catgory_by_id(id);
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
			GripModel.setRows(CatgorySubImp.get_list_search_pagg(searchmodel));
			GripModel.setTotal(CatgorySubImp.count_get_list_search_pagg(searchmodel));
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
			GripModel.setRows(CatgorySubImp.get_list_search_pagg_sub(searchmodel));
			GripModel.setTotal(CatgorySubImp.count_get_list_search_pagg_sub(searchmodel));
			return GripModel;
			//return "{'aa':'ồ ố ô ộ'}";
		}
		@RequestMapping(value="/add_update_catgory",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel add_update_catgory(
				@RequestParam(value ="ptype",required = true) String ptype,
				@RequestParam(value ="product_type_id",required = false,defaultValue="0") String product_type_id,
				@RequestParam(value ="product_type_name",required = true) String product_type_name,
				@RequestParam(value ="parentid",required = true) String parentid_vmall,
				@RequestParam(value ="category_img",required = false,defaultValue="") String category_img,
				@RequestParam(value ="title_img",required = false,defaultValue="") String title_img,
				@RequestParam(value ="islink",required = false,defaultValue="0") String islink,
				@RequestParam(value ="parent_ofsub",required = false,defaultValue="0") String parent_ofsub){
			CatgorySub_Entity d=new CatgorySub_Entity();
			d.setProduct_type_id(product_type_id);
			d.setProduct_type_name(product_type_name);
			d.setTitle_img(title_img);
			d.setCategory_img(category_img);
			d.setProduct_type_vmall(parentid_vmall);
			d.setIslink(Integer.parseInt(islink));
			d.setParent(parent_ofsub);
			//System.out.print(id);
			//System.out.println(ptype+"-"+parentid_vmall+"-"+product_type_id+"-"+product_type_name+"-"+category_img+"-"+title_img);
			ErrorMessageModel e=new ErrorMessageModel();
			int f= CatgorySubImp.add_update_catgory(ptype, d);
			e=ErrorMesage.get_json_mes_error(f);
			return e;
		}
		@RequestMapping(value="/detele_catgory",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel detele_catgory(@RequestParam(value ="str_id",required = true) String str_id){
			
			ErrorMessageModel e=new ErrorMessageModel();
			int f=CatgorySubImp.delete_catgory(str_id);
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
			int f=CatgorySubImp.visivled_catgory(str_id,visible);
			e=ErrorMesage.get_json_mes_error(f);
			return e;
		}
	@RequestMapping(value="/get_allcatgory",
				method=RequestMethod.GET,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<CatgorySub_Entity> get_allcatgory(){
		return CatgorySubImp.get_allcatgory();
		
	}
	@RequestMapping(value="/get_subcatgory_byparent",
			method=RequestMethod.GET,
			produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<CatgorySub_Entity> get_subcatgory_byparent(@RequestParam(value="parentid",required=true) String parentid){
	return CatgorySubImp.get_subcatgory_byparent(parentid);
	
	}
	@RequestMapping(value="/get_subcatgory_byvmall",
			method=RequestMethod.GET,
			produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<CatgorySub_Entity> get_subcatgory_byvmall(@RequestParam(value="vmallid",required=true) String vmallid){
	return CatgorySubImp.get_subcatgory_byvmall(vmallid);
	
	}
	@RequestMapping(value="/remove_sub_catgory",
			method=RequestMethod.POST,
			produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ErrorMessageModel remove_sub_catgory(@RequestParam(value ="str_id",required = true) String str_id){
		
		ErrorMessageModel e=new ErrorMessageModel();
		int f=CatgorySubImp.remove_sub_catgory(str_id);
		e=ErrorMesage.get_json_mes_error(f);
		return e;
		
		
	}
}
