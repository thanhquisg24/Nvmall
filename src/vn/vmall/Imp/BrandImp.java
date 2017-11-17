package vn.vmall.Imp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import vn.vmall.DAL.BrandDAL;
import vn.vmall.Entity.Brand_Entity;
import vn.vmall.Entity.Brand_productype_detail_Entity;
import vn.vmall.Entity.Catgory_Entity;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.Interface.BrandInterface;
import vn.vmall.SessionModel.UserSessionModel;


@Component(value="BrandImp")
public class  BrandImp implements BrandInterface {

	@Autowired
	private BrandDAL DAL;
	
	@Override
	public List<Brand_Entity> get_list_search_pagg(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.get_list_search_pagg(searchmodel);
	}
	@Override
	public int count_get_list_search_pagg(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.count_get_list_search_pagg(searchmodel);
	}

	@Override
	public int add_update_branch(String ptype, Brand_Entity d) {
		// TODO Auto-generated method stub
		return DAL.add_update_branch(ptype, d);

	}

	@Override
	public Brand_Entity get_branch_by_id(String id) {
		// TODO Auto-generated method stub
		return DAL.get_branch_by_id(id);
	}



	@Override
	public int visivled_brand(String str_id,String visible) {
		// TODO Auto-generated method stub
		return DAL.visivled_brand(str_id,visible);
	}

	@Override
	public List<Brand_Entity> get_allbranh() {
		// TODO Auto-generated method stub
		return DAL.get_allbranh();
	}

	@Override
	public List<Brand_Entity> get_datagrip_byproducttypeid(
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
	public int detele_single_branch(String str_id) {
		// TODO Auto-generated method stub
		 return DAL.detele_single_branch( str_id);
	}

	@Override
	public int delete_multi_branch(String str_id) {
		// TODO Auto-generated method stub
		 return DAL.delete_multi_branch( str_id);
	}
	@Override
	public List<Brand_productype_detail_Entity> get_branddetail_byproduct_type_id(
			String product_type_id) {
		// TODO Auto-generated method stub
		return DAL.get_branddetail_byproduct_type_id(product_type_id);
	}
	@Override
	public int do_append_brand(String product_type_id, String json_brand,UserSessionModel user) throws InstantiationException, ClassNotFoundException, SQLException {
		Gson gson= new Gson();
		List<Brand_Entity> listLevel = gson.fromJson(json_brand, new TypeToken<List<Brand_Entity>>(){}.getType());
		return DAL.do_append_brand(product_type_id,listLevel,user) ;
	}
	@Override
	public int delete_multi_branddetail(String str_id, String product_type_id) {
		// TODO Auto-generated method stub
		return DAL.delete_multi_branddetail(str_id,product_type_id);
	}
	@Override
	public ArrayList<Catgory_Entity> get_list_brand() {
		// TODO Auto-generated method stub
		return DAL.get_list_brand();
	}
	@Override
	public ArrayList<Brand_Entity> get_brand_by_vmall(String type_vmall){
		return DAL.get_brand_by_vmall(type_vmall);
	}
}