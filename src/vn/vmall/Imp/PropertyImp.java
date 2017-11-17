package vn.vmall.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.vmall.DAL.PropertyDAL;
import vn.vmall.Entity.Property_Entity;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.Interface.PropertyInterface;


@Component(value="PropertyImp")
public class  PropertyImp implements PropertyInterface {

	@Autowired
	private PropertyDAL DAL;
	
	@Override
	public List<Property_Entity> get_list_search_pagg(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.get_list_search_pagg(searchmodel);
	}

	@Override
	public int count_get_list_search_pagg(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.count_get_list_search_pagg(searchmodel);
	}

	@Override
	public int add_update_property(String ptype, Property_Entity d) {
		// TODO Auto-generated method stub
		return DAL.add_update_property(ptype, d);

	}

	@Override
	public Property_Entity get_property_by_id(String id) {
		// TODO Auto-generated method stub
		return DAL.get_property_by_id(id);
	}



	@Override
	public int visivled_property(String strproductype,String strparentid,String strpropertyids,String visible) {
		// TODO Auto-generated method stub
		return DAL.visivled_property( strproductype, strparentid, strpropertyids, visible);
	}

	@Override
	public List<Property_Entity> get_allproperty() {
		// TODO Auto-generated method stub
		return DAL.get_allproperty();
	}

	@Override
	public List<Property_Entity> get_datagrip_byparentid(
			SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.get_datagrip_byparentid(searchmodel);
	}

	@Override
	public int count_get_datagrip_byparentid(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.count_get_datagrip_byparentid(searchmodel);
	}

	@Override
	public int detele_single_property(String str_id) {
		// TODO Auto-generated method stub
		 return DAL.detele_single_property( str_id);
	}

	@Override
	public int delete_multi_property(String strproductype,String strparentid,String  strpropertyids) {
		// TODO Auto-generated method stub
		 return DAL.delete_multi_property(  strproductype, strparentid,  strpropertyids);
	}

	@Override
	public List<Property_Entity> get_property_parent_byproducttypeid(
			String product_type_id) {
		// TODO Auto-generated method stub
		return DAL.get_property_parent_byproducttypeid(product_type_id);
	}
	
	
	
}