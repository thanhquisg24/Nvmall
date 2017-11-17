package vn.vmall.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.vmall.DAL.SlideDAL;
import vn.vmall.Entity.Slide_Entity;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.Interface.CatgoryInterface;
import vn.vmall.Interface.SlideInterface;



@Component(value="SlideImp")
public class SlideImp implements SlideInterface {

	@Autowired
	private SlideDAL DAL;
	
	@Override
	public List<Slide_Entity> get_list_search_pagg(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.get_list_search_pagg(searchmodel);
	}

	@Override
	public int count_get_list_search_pagg(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.count_get_list_search_pagg(searchmodel);
	}

	@Override
	public int add_update_slide(String ptype, Slide_Entity d) {
		// TODO Auto-generated method stub
		return DAL.add_update_slide(ptype, d);

	}

	@Override
	public Slide_Entity get_slide_by_id(String id) {
		// TODO Auto-generated method stub
		return DAL.get_slide_by_id(id);
	}

	@Override
	public int delete_slide(String str_id) {
		// TODO Auto-generated method stub
		return DAL.delete_slide(str_id);
	}

	@Override
	public int visivled_slide(String str_id,String visible) {
		// TODO Auto-generated method stub
		return DAL.visivled_slide(str_id,visible);
	}

	@Override
	public List<Slide_Entity> get_list_slide() {
		// TODO Auto-generated method stub
		return DAL.get_list_slide();
	}
	
}