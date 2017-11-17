package vn.vmall.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.vmall.DAL.CatgorySubDAL;
import vn.vmall.Entity.CatgorySub_Entity;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.Interface.CatgorySubInterface;


@Component(value="CatgorySubImp")
public class CatgorySubImp implements CatgorySubInterface {

	@Autowired
	private CatgorySubDAL DAL;
	
	@Override
	public List<CatgorySub_Entity> get_list_search_pagg(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.get_list_search_pagg(searchmodel);
	}

	@Override
	public int count_get_list_search_pagg(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.count_get_list_search_pagg(searchmodel);
	}
	@Override
	public List<CatgorySub_Entity> get_list_search_pagg_sub(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.get_list_search_pagg_sub(searchmodel);
	}
	@Override
	public int count_get_list_search_pagg_sub(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.count_get_list_search_pagg_sub(searchmodel);
	}

	@Override
	public int add_update_catgory(String ptype, CatgorySub_Entity d) {
		// TODO Auto-generated method stub
		return DAL.add_update_catgory(ptype, d);

	}

	@Override
	public CatgorySub_Entity get_catgory_by_id(String id) {
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
	public List<CatgorySub_Entity> get_allcatgory() {
		// TODO Auto-generated method stub
		return DAL.get_allcatgory();
	}
	@Override
	public List<CatgorySub_Entity> get_subcatgory_byparent(String parentid) {
		// TODO Auto-generated method stub
		return DAL.get_subcatgory_byparent(parentid);
	}
	public List<CatgorySub_Entity> get_subcatgory_byvmall(String vmallid) {
		// TODO Auto-generated method stub
		return DAL.get_subcatgory_byvmall(vmallid);
	}
	
	@Override
	public int remove_sub_catgory(String str_id) {
		// TODO Auto-generated method stub
		return DAL.remove_sub_catgory(str_id);
	}

	@Override
	public CatgorySub_Entity get_prodcutypesubfull_by_id(String id) {
		// TODO Auto-generated method stub
		return DAL.get_prodcutypesubfull_by_id(id);
	}

	@Override
	public int save_function_name(CatgorySub_Entity d) {
		// TODO Auto-generated method stub
		return DAL.save_function_name(d);
	}

	@Override
	public List<CatgorySub_Entity> E_get_list_search_pagg(
			SearchPaggModel searchmodel) {
		return DAL.E_get_list_search_pagg(searchmodel);
	}

	@Override
	public int E_count_get_list_search_pagg(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.E_count_get_list_search_pagg(searchmodel);
	}
	@Override
	public List<CatgorySub_Entity> E_get_list_search_pagg_sub(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.E_get_list_search_pagg_sub(searchmodel);
	}
	@Override
	public int E_count_get_list_search_pagg_sub(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.E_count_get_list_search_pagg_sub(searchmodel);
	}
}
