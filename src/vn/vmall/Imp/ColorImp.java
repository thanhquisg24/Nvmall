package vn.vmall.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.vmall.DAL.ColorDAL;
import vn.vmall.Entity.Color_Entity;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.Interface.ColorInterface;


@Component(value=" ColorImp")
public class  ColorImp implements ColorInterface {

	@Autowired
	private ColorDAL DAL;
	
	@Override
	public List<Color_Entity> get_list_search_pagg(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.get_list_search_pagg(searchmodel);
	}

	@Override
	public int count_get_list_search_pagg(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.count_get_list_search_pagg(searchmodel);
	}

	@Override
	public int add_update_color(String ptype, Color_Entity d) {
		// TODO Auto-generated method stub
		return DAL.add_update_color(ptype, d);

	}

	@Override
	public Color_Entity get_color_by_id(String id) {
		// TODO Auto-generated method stub
		return DAL.get_color_by_id(id);
	}



	@Override
	public int visivled_color(String str_id,String visible) {
		// TODO Auto-generated method stub
		return DAL.visivled_color(str_id,visible);
	}

	@Override
	public List<Color_Entity> get_allbranh() {
		// TODO Auto-generated method stub
		return DAL.get_allbranh();
	}

	@Override
	public List<Color_Entity> get_datagrip_byproducttypeid(
			SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.get_datagrip_byproducttypeid(searchmodel);
	}

	@Override
	public int count_get_datagrip_byproducttypeid(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.count_get_datagrip_byproducttypeid(searchmodel);
	}

	@Override
	public int detele_single_color(String str_id) {
		// TODO Auto-generated method stub
		 return DAL.detele_single_color( str_id);
	}

	@Override
	public int delete_multi_color(String str_id) {
		// TODO Auto-generated method stub
		 return DAL.delete_multi_color( str_id);
	}
	
	

}