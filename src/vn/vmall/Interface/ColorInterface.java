package vn.vmall.Interface;

import java.util.List;

import vn.vmall.Entity.Color_Entity;
import vn.vmall.Helper.SearchPaggModel;



public interface ColorInterface {
	List<Color_Entity> get_list_search_pagg(SearchPaggModel searchmodel);
	 int count_get_list_search_pagg(SearchPaggModel searchmodel);
	 int add_update_color(String ptype, Color_Entity d);
	 Color_Entity get_color_by_id(String id);
	 int visivled_color(String str_id,String visible);
	 List<Color_Entity> get_allbranh();
	List<Color_Entity> get_datagrip_byproducttypeid(SearchPaggModel searchmodel);
	int count_get_datagrip_byproducttypeid(SearchPaggModel searchmodel);
	int detele_single_color(String str_id);
	int delete_multi_color(String str_id);
}
