package vn.vmall.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;







import vn.vmall.DAL.DemoCRUDDAL;
import vn.vmall.Entity.DemoCRUD_Entity;
import vn.vmall.Helper.ErrorMesage;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.Interface.DemoCRUDInterface;

@Component(value="DemoCRUDImp")
public class DemoCRUDImp implements DemoCRUDInterface {

	@Autowired
	private DemoCRUDDAL DAL;
	
	@Override
	public List<DemoCRUD_Entity> get_list_search_pagg(
			SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.get_list_search_pagg(searchmodel);
	}

	@Override
	public int count_get_list_search_pagg(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.count_get_list_search_pagg(searchmodel);
	}

	@Override
	public String add_update_demo(String ptype, DemoCRUD_Entity d) {
		// TODO Auto-generated method stub
		int f=DAL.add_update_demo(ptype, d);
		return ErrorMesage.getMesageError(f);
	}

	@Override
	public DemoCRUD_Entity get_democrud_by_id(String id) {
		// TODO Auto-generated method stub
		return DAL.get_democrud_by_id( id);
	}

	@Override
	public int delete_demo(String str_id) {
		// TODO Auto-generated method stub
		return DAL.delete_demo(str_id);
	}
	
	
	
}
