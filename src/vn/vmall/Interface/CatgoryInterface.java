package vn.vmall.Interface;

import java.util.ArrayList;
import java.util.List;

import vn.vmall.Entity.Brand_Entity;
import vn.vmall.Entity.CategoryNew_Entity;
import vn.vmall.Entity.Catgory_Entity;
import vn.vmall.Entity.ItemCategoryProperty;
import vn.vmall.Entity.Product_Entity;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.Entity.CatgorySub_Entity;
public interface CatgoryInterface {

	List<Catgory_Entity> get_list_search_pagg(SearchPaggModel searchmodel);

	int count_get_list_search_pagg(SearchPaggModel searchmodel);

	int add_update_catgory(String ptype, Catgory_Entity d);

	Catgory_Entity get_catgory_by_id(String id);

	int delete_catgory(String str_id);

	int visivled_catgory(String str_id, String visible);

	List<Catgory_Entity> get_allcatgory();
	List<Catgory_Entity> get_category();
	List<Catgory_Entity> get_category_by_id(String id);

	List<Catgory_Entity> get_category_index();
	List<Catgory_Entity> get_category_summary();
	List<Brand_Entity>  get_brand();
	List<Catgory_Entity> get_category_product_by_id(String product_type_vmall,String product_type_id);
	ItemCategoryProperty get_property_by_product_type(String product_type_id);
	List<Catgory_Entity> get_category_second_product_by_id(String product_type_vmall,String product_type_id,String second_type_id);
	ItemCategoryProperty get_property_by_product_type_second(String product_type_id,String product_type_second_id);
	List<Catgory_Entity> get_category_mobile();
	
	
}
