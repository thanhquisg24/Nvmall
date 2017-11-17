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

import vn.vmall.Entity.Color_Entity;
import vn.vmall.Helper.ErrorMesage;
import vn.vmall.Helper.ErrorMessageModel;
import vn.vmall.Helper.JsonDataGripModel;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.Interface.ColorInterface;
import vn.vmall.SessionModel.UserSessionModel;


@RestController
@RequestMapping(value="ad/colorController")
@SessionAttributes(value = "user", types = {UserSessionModel.class})
public class ad_colorController {
		
	 @Autowired
	 @Qualifier(" ColorImp")
	 private ColorInterface  ColorImp;
	 
	 
	 @RequestMapping(value="/get_color_by_id",
				method=RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

		public Color_Entity get_color_by_id(	
				@RequestParam(value ="id",required = true) String id){
			
			
			return  ColorImp.get_color_by_id(id);
			
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
			JsonDataGripModel<Color_Entity> GripModel =new JsonDataGripModel<Color_Entity>();
			GripModel.setRows( ColorImp.get_list_search_pagg(searchmodel));
			GripModel.setTotal( ColorImp.count_get_list_search_pagg(searchmodel));
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
			JsonDataGripModel<Color_Entity> GripModel =new JsonDataGripModel<Color_Entity>();
			GripModel.setRows( ColorImp.get_datagrip_byproducttypeid(searchmodel));
			GripModel.setTotal( ColorImp.count_get_datagrip_byproducttypeid(searchmodel));
			return GripModel;
		}
		
		
		@RequestMapping(value="/add_update_color",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel add_update_color(
				@ModelAttribute("user") UserSessionModel user,
				@RequestParam(value ="idvalue",required = false,defaultValue="") String id,
				@RequestParam(value ="ptype",required = true) String ptype,
				@RequestParam(value ="seltype",required = true) String seltype,
				@RequestParam(value ="product_type_id",required = true) String product_type_id,
				@RequestParam(value ="colorid",required = false,defaultValue="") String colorid,
				@RequestParam(value ="colorname",required = false,defaultValue="") String colorname,
				@RequestParam(value ="colorimage",required = false,defaultValue="") String colorimage){
			Color_Entity d=new Color_Entity();
			d.setId(id);
			d.setProduct_type_sub_id(product_type_id);
			d.setType(seltype);
			d.setColor(colorid);
			d.setColor_name(colorname);
			d.setImg(colorimage);
			//d.setCreate_user(user.getId());
		//	System.out.print(ptype+"-"+product_type_vmall+"-"+product_type_name+"-"+category_img+"-"+title_img);
			ErrorMessageModel e=new ErrorMessageModel();
			int f=  ColorImp.add_update_color(ptype, d);
			e=ErrorMesage.get_json_mes_error(f);
			return e;
			//return (ptype+"-"+product_type_vmall+"-"+product_type_name+"-"+category_img+"-"+title_img);
			
			
		}
		@RequestMapping(value="/delete_multi_color",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel detele_color(@RequestParam(value ="str_id",required = true) String str_id){
			
			ErrorMessageModel e=new ErrorMessageModel();
			int f= ColorImp.delete_multi_color(str_id);
			e=ErrorMesage.get_json_mes_error(f);
			return e;
			
			
		}
		
		@RequestMapping(value="/detele_single_color",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel detele_single_color(@RequestParam(value ="str_id",required = true) String str_id){
			
			ErrorMessageModel e=new ErrorMessageModel();
			int f= ColorImp.detele_single_color(str_id);
			e=ErrorMesage.get_json_mes_error(f);
			return e;
			
			
		}
		
		@RequestMapping(value="/visivled_color",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel visivled_color(
				@RequestParam(value ="str_id",required = true) String str_id,
				@RequestParam(value ="visible",required = true) String visible){
			
			ErrorMessageModel e=new ErrorMessageModel();
			int f= ColorImp.visivled_color(str_id,visible);
			e=ErrorMesage.get_json_mes_error(f);
			return e;
			
			
		}
	@RequestMapping(value="/get_allbranh",
				method=RequestMethod.GET,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public List<Color_Entity> get_allbranh(){
		return  ColorImp.get_allbranh();
		
	}
}