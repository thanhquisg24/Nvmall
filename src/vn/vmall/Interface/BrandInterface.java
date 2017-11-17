package vn.vmall.Interface;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.vmall.Entity.Brand_Entity;
import vn.vmall.Entity.Brand_productype_detail_Entity;
import vn.vmall.Entity.Catgory_Entity;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.SessionModel.UserSessionModel;

public interface BrandInterface {
	List<Brand_Entity> get_list_search_pagg(SearchPaggModel searchmodel);

	int count_get_list_search_pagg(SearchPaggModel searchmodel);

	int add_update_branch(String ptype, Brand_Entity d);

	Brand_Entity get_branch_by_id(String id);

	int visivled_brand(String str_id, String visible);

	List<Brand_Entity> get_allbranh();

	List<Brand_Entity> get_datagrip_byproducttypeid(SearchPaggModel searchmodel);

	int count_get_datagrip_byproducttypeid(SearchPaggModel searchmodel);

	int detele_single_branch(String str_id);

	int delete_multi_branch(String str_id);

	List<Brand_productype_detail_Entity> get_branddetail_byproduct_type_id(String product_type_id);

	int do_append_brand(String product_type_id, String json_brand, UserSessionModel user)
			throws InstantiationException, ClassNotFoundException, SQLException;

	int delete_multi_branddetail(String str_id, String product_type_id);

	ArrayList<Catgory_Entity> get_list_brand();
	
	ArrayList<Brand_Entity> get_brand_by_vmall(String type_vmall);

}
