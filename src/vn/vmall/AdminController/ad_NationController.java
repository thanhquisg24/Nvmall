package vn.vmall.AdminController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import vn.vmall.Entity.Nation_Entity;
import vn.vmall.Helper.ErrorMesage;
import vn.vmall.Helper.ErrorMessageModel;
import vn.vmall.Interface.NationInterface;




@RestController
@RequestMapping(value="ad/NationController")
public class ad_NationController {
	 @Autowired
	 @Qualifier("NationImp")
	 private NationInterface NationImp;
	
	@RequestMapping(value="/get_allnation",
			method=RequestMethod.GET,
			produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Nation_Entity> get_allnation(){
		return NationImp.get_allnation();
	}
	
	@RequestMapping(value="/get_nationbyid",
			method=RequestMethod.GET,
			produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Nation_Entity get_nationbyid(@RequestParam(value ="nation_id",required = true) String nation_id){
		return NationImp.get_nationbyid(nation_id);
	}
	@RequestMapping(value="/add_update_nation",
			method=RequestMethod.POST,
			produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ErrorMessageModel add_update_nation(
			@RequestParam(value ="ptype",required = true) String ptype,
			@RequestParam(value ="nation_id",required = true,defaultValue="0") String nation_id,
			@RequestParam(value ="nation_name",required = false,defaultValue="") String nation_name){
		Nation_Entity d=new Nation_Entity();
		d.setNation_id(nation_id);
		d.setNation_name(nation_name);
	//	System.out.print(ptype+"-"+product_type_vmall+"-"+product_type_name+"-"+category_img+"-"+title_img);
		ErrorMessageModel e=new ErrorMessageModel();
		int f=  NationImp.add_update_nation(ptype, d);
		e=ErrorMesage.get_json_mes_error(f);
		return e;
		//return (ptype+"-"+product_type_vmall+"-"+product_type_name+"-"+category_img+"-"+title_img);
	}
	@RequestMapping(value="/detele_single_nation",
			method=RequestMethod.POST,
			produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ErrorMessageModel detele_single_nation(@RequestParam(value ="str_id",required = true) String str_id){
		ErrorMessageModel e=new ErrorMessageModel();
		int f= NationImp.detele_single_nation(str_id);
		e=ErrorMesage.get_json_mes_error(f);
		return e;
	}
}
