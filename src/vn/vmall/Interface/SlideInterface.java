package vn.vmall.Interface;

import java.util.List;

import vn.vmall.Entity.Slide_Entity;
import vn.vmall.Helper.SearchPaggModel;

public interface SlideInterface {

	List<Slide_Entity> get_list_search_pagg(SearchPaggModel searchmodel);

	int count_get_list_search_pagg(SearchPaggModel searchmodel);

	int add_update_slide(String ptype, Slide_Entity d);

	Slide_Entity get_slide_by_id(String id);

	int delete_slide(String str_id);

	int visivled_slide(String str_id, String visible);
	List<Slide_Entity> get_list_slide();
}
