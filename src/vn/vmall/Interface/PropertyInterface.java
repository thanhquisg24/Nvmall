package vn.vmall.Interface;

import java.util.List;

import vn.vmall.Entity.Property_Entity;
import vn.vmall.Helper.SearchPaggModel;



public interface PropertyInterface {
	List<Property_Entity> get_list_search_pagg(SearchPaggModel searchmodel);
	 int count_get_list_search_pagg(SearchPaggModel searchmodel);
	 int add_update_property(String ptype, Property_Entity d);
	 Property_Entity get_property_by_id(String id);
	 int visivled_property(String strproductype,String strparentid,String strpropertyids,String visible);
	 List<Property_Entity> get_allproperty();
	List<Property_Entity> get_datagrip_byparentid(SearchPaggModel searchmodel);
	int count_get_datagrip_byparentid(SearchPaggModel searchmodel);
	int detele_single_property(String str_id);
	int delete_multi_property(String strproductype,String strparentid,String  strpropertyids);
	List<Property_Entity> get_property_parent_byproducttypeid(
			String product_type_id);
}
