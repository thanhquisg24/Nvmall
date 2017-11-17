package vn.vmall.Imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.vmall.DAL.CatgoryDAL;
import vn.vmall.Entity.Brand_Entity;
import vn.vmall.Entity.CategoryNew_Entity;
import vn.vmall.Entity.CatgorySub_Entity;
import vn.vmall.Entity.Catgory_Entity;
import vn.vmall.Entity.ItemCategoryProperty;
import vn.vmall.Helper.EncrypterDecrypter;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.Interface.CatgoryInterface;


@Component(value="CatgoryImp")
public class CatgoryImp implements CatgoryInterface {

	@Autowired
	private CatgoryDAL DAL;
	
	@Override
	public List<Catgory_Entity> get_list_search_pagg(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.get_list_search_pagg(searchmodel);
	}

	@Override
	public int count_get_list_search_pagg(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.count_get_list_search_pagg(searchmodel);
	}

	@Override
	public int add_update_catgory(String ptype, Catgory_Entity d) {
		// TODO Auto-generated method stub
		return DAL.add_update_catgory(ptype, d);

	}

	@Override
	public Catgory_Entity get_catgory_by_id(String id) {
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
	public List<Catgory_Entity> get_allcatgory() {
		// TODO Auto-generated method stub
		return DAL.get_allcatgory();
	}

	@Override
	public List<Catgory_Entity> get_category() {
		return DAL.get_category();
	}

	@Override
	public List<Catgory_Entity> get_category_by_id(String id) {
		if(id.length()>0){
			String cate = EncrypterDecrypter.decodeCategory(id);
			return DAL.get_category_by_id(cate);			
		}
		return null;
	}

	@Override
	public List<Catgory_Entity> get_category_index() {
		// TODO Auto-generated method stub
		return DAL.get_category_index();
	}

	@Override
	public List<Catgory_Entity> get_category_summary() {
		// TODO Auto-generated method stub
		return DAL.get_category_summary();
	}

	@Override
	public List<Brand_Entity> get_brand() {
		// TODO Auto-generated method stub
		return DAL.get_brand();
	}

	@Override
	public List<Catgory_Entity> get_category_product_by_id(String product_type_vmall, String product_type_id) {
		// TODO Auto-generated method stub
		if(product_type_vmall.length()>0){
			String type_vmall = EncrypterDecrypter.decodeCategory(product_type_vmall);
			String type_id = EncrypterDecrypter.decodeCategory(product_type_id);
			return DAL.get_category_product_by_id(type_vmall,type_id);			
		}
		return null;
	}

	@Override
	public ItemCategoryProperty get_property_by_product_type(String product_type_id) {
		if(product_type_id.length()>0){			
			String type_id = EncrypterDecrypter.decodeCategory(product_type_id);
			return DAL.get_property_by_product_type(type_id);			
		}
		return null;
	}

	@Override
	public List<Catgory_Entity> get_category_second_product_by_id(String product_type_vmall, String product_type_id,
			String second_type_id) {
		if(product_type_vmall.length()>0){
			String type_vmall = EncrypterDecrypter.decodeCategory(product_type_vmall);
			String type_id = EncrypterDecrypter.decodeCategory(product_type_id);
			second_type_id= EncrypterDecrypter.decodeCategory(second_type_id);			
			return DAL.get_category_second_product_by_id(type_vmall, type_id, second_type_id);						
		}
		return null;
		
	}

	@Override
	public ItemCategoryProperty get_property_by_product_type_second(String product_type_id,
			String product_type_second_id) {
		if(product_type_id.length()>0){			
			String type_id = EncrypterDecrypter.decodeCategory(product_type_id);
			String type_second_id = EncrypterDecrypter.decodeCategory(product_type_second_id);
			return DAL.get_property_by_product_type_second(type_id,type_second_id);			
		}
		return null;
	}

	@Override
	public List<Catgory_Entity> get_category_mobile() {
		// TODO Auto-generated method stub
		return CatgoryDAL.get_category_mobile();
	}

	
	
}
