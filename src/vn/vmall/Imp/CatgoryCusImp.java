package vn.vmall.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.vmall.DAL.CatgoryCusDAL;
import vn.vmall.Entity.CatgoryCus_Entity;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.Interface.CatgoryCusInterface;
import vn.vmall.Interface.CatgoryInterface;


@Component(value="CatgoryCusImp")
public class CatgoryCusImp implements CatgoryCusInterface {

	@Autowired
	private CatgoryCusDAL DAL;
	
	@Override
	public List<CatgoryCus_Entity> get_list_search_pagg(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.get_list_search_pagg(searchmodel);
	}

	@Override
	public int count_get_list_search_pagg(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.count_get_list_search_pagg(searchmodel);
	}

	@Override
	public int add_update_catgory(String ptype, CatgoryCus_Entity d) {
		// TODO Auto-generated method stub
		return DAL.add_update_catgory(ptype, d);

	}

	@Override
	public CatgoryCus_Entity get_catgory_by_id(String id) {
		// TODO Auto-generated method stub
		return DAL.get_catgory_by_id(id);
	}

	@Override
	public int delete_catgory(String str_id) {
		// TODO Auto-generated method stub
		return DAL.delete_catgory(str_id);
	}

	@Override
	public int visivled_catgory(String str_id,String visible) {
		// TODO Auto-generated method stub
		return DAL.visivled_catgory(str_id,visible);
	}

	@Override
	public List<CatgoryCus_Entity> get_allcatgory() {
		// TODO Auto-generated method stub
		return DAL.get_allcatgory();
	}
	@Override
	public int approve_catgory(String str_id) {
		// TODO Auto-generated method stub
		return DAL.approve_catgory(str_id);
	}
	
}
