package vn.vmall.Interface;

import java.util.List;

import vn.vmall.Entity.CatgorySub_Entity;
import vn.vmall.Helper.SearchPaggModel;

public interface CatgorySubInterface {

	List<CatgorySub_Entity> get_list_search_pagg(SearchPaggModel searchmodel);

	List<CatgorySub_Entity> get_list_search_pagg_sub(SearchPaggModel searchmodel);

	int count_get_list_search_pagg(SearchPaggModel searchmodel);

	int count_get_list_search_pagg_sub(SearchPaggModel searchmodel);

	int add_update_catgory(String ptype, CatgorySub_Entity d);

	CatgorySub_Entity get_catgory_by_id(String id);

	int delete_catgory(String str_id);

	int visivled_catgory(String str_id, String visible);

	List<CatgorySub_Entity> get_allcatgory();

	List<CatgorySub_Entity> get_subcatgory_byparent(String parentid);

	List<CatgorySub_Entity> get_subcatgory_byvmall(String vmallid);

	int remove_sub_catgory(String str_id);

	CatgorySub_Entity get_prodcutypesubfull_by_id(String id);

	int save_function_name(CatgorySub_Entity d);

	List<CatgorySub_Entity> E_get_list_search_pagg(SearchPaggModel searchmodel);

	int E_count_get_list_search_pagg(SearchPaggModel searchmodel);

	List<CatgorySub_Entity> E_get_list_search_pagg_sub(SearchPaggModel searchmodel);

	int E_count_get_list_search_pagg_sub(SearchPaggModel searchmodel);
}
