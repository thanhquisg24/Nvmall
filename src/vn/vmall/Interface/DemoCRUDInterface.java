package vn.vmall.Interface;

import java.util.List;

import vn.vmall.Entity.DemoCRUD_Entity;
import vn.vmall.Helper.SearchPaggModel;



public interface DemoCRUDInterface {
	List<DemoCRUD_Entity> get_list_search_pagg(SearchPaggModel searchmodel);
	 int count_get_list_search_pagg(SearchPaggModel searchmodel);
	 String add_update_demo(String ptype,DemoCRUD_Entity d);
	 DemoCRUD_Entity get_democrud_by_id(String id);	
	 int delete_demo(String str_id);
	
}
