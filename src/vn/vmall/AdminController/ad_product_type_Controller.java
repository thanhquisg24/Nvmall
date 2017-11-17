package vn.vmall.AdminController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.vmall.Entity.ProductTypeEntity;
import vn.vmall.Interface.ProductTypeInterface;



@RestController
@RequestMapping(value="ad/ad_product_type_Controller")
public class ad_product_type_Controller {

	 @Autowired
	 @Qualifier("ProductTypeImp")
	 private ProductTypeInterface ProductTypeImp;
	
	@RequestMapping(value="/get_catgory_bycus",
			method=RequestMethod.GET,
			produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<ProductTypeEntity> get_catgory_bycus(@RequestParam(value="customerid",required=true) String customerid){
	return ProductTypeImp.get_catgory_bycus(customerid);
	
	}
}
