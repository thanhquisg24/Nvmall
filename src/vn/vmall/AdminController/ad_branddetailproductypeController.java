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
import org.springframework.web.bind.annotation.SessionAttributes;

import vn.vmall.Helper.ErrorMesage;
import vn.vmall.Helper.ErrorMessageModel;
import vn.vmall.Interface.BrandInterface;
import vn.vmall.SessionModel.UserSessionModel;
import vn.vmall.Entity.Brand_productype_detail_Entity;

@RestController
@RequestMapping(value="ad/branddetailproductypeController")
@SessionAttributes(value = "user", types = {UserSessionModel.class})
public class ad_branddetailproductypeController {
	 @Autowired
	 @Qualifier("BrandImp")
	 private BrandInterface  BrandImp;
	 
	 @RequestMapping(value="/get_branddetail_byproduct_type_id",
				method=RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public List<Brand_productype_detail_Entity> get_branddetail_byproduct_type_id(	
				@RequestParam(value ="product_type_id",required = true) String product_type_id){		
			return  BrandImp.get_branddetail_byproduct_type_id(product_type_id);
	 }
	 
		
		@RequestMapping(value="/do_append_brand",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel do_append_brand(
				@ModelAttribute("user") UserSessionModel user,
				@RequestParam(value ="product_type_id",required = true) String product_type_id,
				@RequestParam(value ="json_brand",required = true) String json_brand) throws InstantiationException, ClassNotFoundException, SQLException{
			ErrorMessageModel e=new ErrorMessageModel();
			int f= BrandImp.do_append_brand(product_type_id,json_brand,user);
			e=ErrorMesage.get_json_mes_error(f);
			return e;
		}
		
		@RequestMapping(value="/delete_multi_branddetail",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel delete_multi_branddetail(
				@RequestParam(value ="str_id",required = true) String str_id,
				@RequestParam(value ="product_type_id",required = true)  String product_type_id){
			ErrorMessageModel e=new ErrorMessageModel();
			int f= BrandImp.delete_multi_branddetail(str_id,product_type_id);
			e=ErrorMesage.get_json_mes_error(f);
			return e;
		}
}
