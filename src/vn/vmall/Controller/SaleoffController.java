package vn.vmall.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.vmall.Entity.Catgory_Entity;
import vn.vmall.Interface.SaleoffInterface;


@RestController
@RequestMapping(value = "Saleoff")
public class SaleoffController {
	
	@Autowired
	@Qualifier("SaleoffImp")
	private SaleoffInterface SaleoffImp;
	 @RequestMapping(value="/get_sale_list_catgory",
				method=RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ArrayList<Catgory_Entity> get_sale_list_catgory(){		
			return  SaleoffImp.get_sale_list_catgory();
	 }
}
