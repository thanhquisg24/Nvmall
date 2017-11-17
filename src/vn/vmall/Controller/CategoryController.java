package vn.vmall.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.vmall.Entity.Brand_Entity;
import vn.vmall.Entity.CatgorySub_Entity;
import vn.vmall.Entity.Catgory_Entity;
import vn.vmall.Entity.ItemCategoryProperty;
import vn.vmall.Interface.CatgoryInterface;

@RestController
@RequestMapping(value = "CategoryController")
public class CategoryController {
	@Autowired
	@Qualifier("CatgoryImp")
	private CatgoryInterface CatgoryImp;

	@RequestMapping(value = "/get_category", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Catgory_Entity> get_category() {
		return CatgoryImp.get_category();

	}
	
	@RequestMapping(value = "/get_category_sub", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Catgory_Entity> get_category_sub(@RequestParam("id") String id) {
		return CatgoryImp.get_category_by_id(id);

	}
	@RequestMapping(value = "/get_category_index", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Catgory_Entity> get_category_index() {
		return CatgoryImp.get_category_index();

	}
	@RequestMapping(value = "/get_category_summary", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Catgory_Entity> get_category_summary() {
		return CatgoryImp.get_category_summary();

	}
	@RequestMapping(value = "/get_brand", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Brand_Entity> get_brand() {
		return CatgoryImp.get_brand();

	}
	@RequestMapping(value = "/get_category_product_by_id", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Catgory_Entity> get_category_product_by_id(@RequestParam("type_vmall") String type_vmall,@RequestParam("type_id") String type_id) {
		return CatgoryImp.get_category_product_by_id(type_vmall,type_id);

	}
	@RequestMapping(value = "/get_property_by_product_type", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ItemCategoryProperty get_property_by_product_type(@RequestParam("type_id") String type_id) {
		return CatgoryImp.get_property_by_product_type(type_id);

	}
	@RequestMapping(value = "/get_category_second_product_by_id", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Catgory_Entity> get_category_second_product_by_id(
			@RequestParam("type_vmall") String type_vmall,
			@RequestParam("type_id") String type_id,
			@RequestParam("second_type_id") String second_type_id) {
		return CatgoryImp.get_category_second_product_by_id(type_vmall,type_id,second_type_id);

	}
	@RequestMapping(value = "/get_property_by_product_type_second", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ItemCategoryProperty get_property_by_product_type_second(
			@RequestParam("type_id") String type_id,
			@RequestParam("second_type_id") String second_type_id)	
	{
		return CatgoryImp.get_property_by_product_type_second(type_id, second_type_id);

	}
	@RequestMapping(value = "/get_category_mobile", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Catgory_Entity> get_category_mobile() {
		return CatgoryImp.get_category_mobile();

	}
}
