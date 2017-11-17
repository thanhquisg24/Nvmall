package vn.vmall.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import vn.vmall.DAL.ProductDAL;
import vn.vmall.Entity.Catgory_Entity;
import vn.vmall.Entity.Product_Entity;
import vn.vmall.Helper.EncrypterDecrypter;
import vn.vmall.Interface.CatgoryInterface;
import vn.vmall.Interface.ProductInterface;
import vn.vmall.Service.ProductTypeService;

@RestController
@RequestMapping(value = "ProductController")
public class ProductController {
	@Autowired
	@Qualifier("ProductImp")
	private ProductInterface ProductImp;
	
	@RequestMapping(value = "/get_product_recommend", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Product_Entity> get_category(@RequestParam("id") String id) {
		String product_type = EncrypterDecrypter.decodeCategory(id);
		return ProductImp.get_product_recommend(product_type);
	}
	@RequestMapping(value = "/get_product_care", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Product_Entity> get_product_care(@RequestParam("id") String id) {
		String product_type = EncrypterDecrypter.decodeCategory(id);
		return ProductImp.get_product_care(product_type);
	}
	@RequestMapping(value = "/get_product_sell_best", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Product_Entity> get_product_sell_best() {		
		return ProductImp.get_product_sell_best();
	}
	@RequestMapping(value = "/get_product_care_index", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Product_Entity> get_product_care_index() {
		return ProductImp.get_product_care_index();
	}
	@RequestMapping(value = "/search_product_by_category", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Product_Entity> search_product_by_category(
			@RequestParam("id") String id,
			@RequestParam("brand") String brand,
			@RequestParam("category") String category,
			@RequestParam("price") String price,
			@RequestParam("color") String color,
			@RequestParam("property") String property,
			@RequestParam("row") String row
			) {
		int i_row = 0;
		if(row.length()>0){
			i_row = Integer.parseInt(row);
		}
		System.out.println("11232132132");
		System.out.println(id);
		String product_type = EncrypterDecrypter.decodeCategory(id);
		System.out.println(product_type);
		return ProductImp.search_product_by_category(product_type,brand, category, price, color, property,i_row);
	}
	@RequestMapping(value = "/get_product_recommend_product_type", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Product_Entity> get_product_recommend_product_type(@RequestParam("id") String id) {
		String product_type = EncrypterDecrypter.decodeCategory(id);
		return ProductImp.get_product_recomment_product_type(product_type);
	}
	@RequestMapping(value = "/get_product_qt_product_type", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Product_Entity> get_product_qt_product_type(@RequestParam("id") String id) {
		String product_type = EncrypterDecrypter.decodeCategory(id);
		return ProductImp.get_product_qt_product_type(product_type);
	}
	@RequestMapping(value = "/remove_product_later", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public int remove_product_later(@RequestParam("email") String email,
			@RequestParam("customer_id") String customer_id,
			@RequestParam("product_id") String product_id,
			@RequestParam("property") String property,
			@RequestParam("color") String color) {
		
		return ProductImp.remove_product_later(email,customer_id,product_id,property,color);
	}
	@RequestMapping(value = "/search_product_by_brand", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Product_Entity> search_product_by_brand(
			@RequestParam("brand") String brand,
			@RequestParam("row") String row
			) {
		int i_row = 0;
		if(row.length()>0){
			i_row = Integer.parseInt(row);
		}
		if(brand.trim().length() !=0){
			brand = EncrypterDecrypter.decodeCategory(brand);	
		}
		
		
		
		return ProductImp.search_product_by_brand(brand,i_row);
	}
	@RequestMapping(value="like_product",method=RequestMethod.GET)	
	public int like_product(
			@RequestParam("session_id") String session_id,
			@RequestParam("str") String str,
			HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		int rs = 0;
		//default get top 10
		try {		
			//
			if(session_id.length()>0){
				int rs1 = ProductDAL.save_session_product(str,session_id,"LIKE");
			}
		} catch (Exception ex){
			rs = -1;
		}
		return rs;
	}
	@RequestMapping(value = "/get_product_memberhip", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Product_Entity> get_product_memberhip() {		
		return ProductTypeService.get_product_memberhip();
	}
	@RequestMapping(value = "/get_product_by_category", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Product_Entity> get_product_by_category(
			@RequestParam("produt_type_vmall") String produt_type_vmall
			) {
		
		return ProductImp.get_product_by_category(produt_type_vmall);
	}
}
