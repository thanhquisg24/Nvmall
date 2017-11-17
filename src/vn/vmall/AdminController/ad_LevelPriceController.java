package vn.vmall.AdminController;

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

import vn.vmall.Entity.LevelPrice_Entity;
import vn.vmall.Entity.Level_price_vmall_Entity;
import vn.vmall.Helper.ErrorMesage;
import vn.vmall.Helper.ErrorMessageModel;
import vn.vmall.Interface.LevelPriceInterface;


@RestController
@RequestMapping(value="ad/LevelPriceController")
public class ad_LevelPriceController {

	
	 @Autowired
	 @Qualifier("LevelPriceImp")
	 private LevelPriceInterface LevelPriceImp;
	
	@RequestMapping(value="/get_alllevel",
			method=RequestMethod.GET,
			produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<LevelPrice_Entity> get_alllevel(){
		return LevelPriceImp.get_alllevel();
	}
	
	
	@RequestMapping(value="/get_leveldetail_byvmall",
			method=RequestMethod.GET,
			produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<LevelPrice_Entity> get_leveldetail_byvmall(
			@RequestParam(value ="productype_vmallid",required = true) String productype_vmallid){
		return LevelPriceImp.get_leveldetail_byvmall(productype_vmallid);
	}
	
	@RequestMapping(value="/add_update_level",
			method=RequestMethod.POST,
			produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ErrorMessageModel add_update_level(
			@RequestParam(value ="ptype",required = true) String ptype,
			@RequestParam(value ="levelpriceid",required = false,defaultValue="") int levelpriceid,
			@RequestParam(value ="levelpricename",required = false,defaultValue="") String levelpricename,
			@RequestParam(value ="levelpricepquery",required = false,defaultValue="") String levelpricepquery){
		LevelPrice_Entity d=new LevelPrice_Entity();
		d.setId(levelpriceid);
		d.setName(levelpricename);
		d.setPquery(levelpricepquery);
	//	System.out.print(ptype+"-"+product_type_vmall+"-"+product_type_name+"-"+category_img+"-"+title_img);
		ErrorMessageModel e=new ErrorMessageModel();
		int f=  LevelPriceImp.add_update_level(ptype, d);
		e=ErrorMesage.get_json_mes_error(f);
		return e;
		//return (ptype+"-"+product_type_vmall+"-"+product_type_name+"-"+category_img+"-"+title_img);
	}
	@RequestMapping(value="/detele_single_level",
			method=RequestMethod.POST,
			produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ErrorMessageModel detele_single_level(@RequestParam(value ="str_id",required = true) String str_id){
		ErrorMessageModel e=new ErrorMessageModel();
		int f= LevelPriceImp.detele_single_level(str_id);
		e=ErrorMesage.get_json_mes_error(f);
		return e;
	}
	
	@RequestMapping(value="/delete_multi_leveldetail",
			method=RequestMethod.POST,
			produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ErrorMessageModel delete_multi_leveldetail(
			@RequestParam(value ="str_id",required = true) String str_id,
			@RequestParam(value ="product_type_vmall",required = true)  String product_type_vmall){
		ErrorMessageModel e=new ErrorMessageModel();
		int f= LevelPriceImp.delete_multi_leveldetail(str_id,product_type_vmall);
		e=ErrorMesage.get_json_mes_error(f);
		return e;
	}
	
	@RequestMapping(value="/do_append_level",
			method=RequestMethod.POST,
			produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ErrorMessageModel do_append_level(
			@RequestParam(value ="product_type_vmall",required = true) String product_type_vmall,
			@RequestParam(value ="json_level",required = true) String json_level) throws InstantiationException, ClassNotFoundException, SQLException{
		ErrorMessageModel e=new ErrorMessageModel();
		int f= LevelPriceImp.do_append_level(product_type_vmall,json_level);
		e=ErrorMesage.get_json_mes_error(f);
		return e;
	}
}
