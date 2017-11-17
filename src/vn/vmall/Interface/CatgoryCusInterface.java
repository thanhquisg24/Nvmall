package vn.vmall.Interface;

import java.util.List;

import vn.vmall.Entity.CatgoryCus_Entity;
import vn.vmall.Helper.SearchPaggModel;




public interface CatgoryCusInterface {
	
	List<CatgoryCus_Entity> get_list_search_pagg(SearchPaggModel searchmodel);
	 int count_get_list_search_pagg(SearchPaggModel searchmodel);
	 int add_update_catgory(String ptype,CatgoryCus_Entity d);
	 CatgoryCus_Entity get_catgory_by_id(String id);	
	 int delete_catgory(String str_id);
	int visivled_catgory(String str_id,String visible);
	List<CatgoryCus_Entity> get_allcatgory();
	 int approve_catgory(String str_id);

}
