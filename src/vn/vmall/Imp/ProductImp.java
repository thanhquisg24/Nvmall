package vn.vmall.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.vmall.DAL.ProductDAL;
import vn.vmall.Entity.Product_Entity;
import vn.vmall.Interface.ProductInterface;

@Component(value="ProductImp")
public class ProductImp implements ProductInterface {
	@Autowired
	private ProductDAL DAL;

	@Override
	public List<Product_Entity> get_product_recommend(String product_type) {
		return DAL.get_product_recommend(product_type);
	}

	@Override
	public List<Product_Entity> get_product_care(String product_type) {
		return DAL.get_product_care(product_type);
	}

	@Override
	public List<Product_Entity> get_product_sell_best() {
		return DAL.get_product_sell_best();
	}

	@Override
	public List<Product_Entity> get_product_care_index() {
		return DAL.get_product_care_index();
	}

	@Override
	public List<Product_Entity> search_product_by_category(String product_type,String brand, String category, String price, String color,
			String property,int row) {
		return DAL.search_product_by_category(product_type,brand, category, price, color, property,row);
	}

	@Override
	public List<Product_Entity> get_product_recomment_product_type(String product_type) {
		return DAL.get_product_recomment_product_type(product_type);
	}

	@Override
	public List<Product_Entity> get_product_qt_product_type(String product_type) {
		return DAL.get_product_qt_product_type(product_type);
	}
	public int remove_product_later(String email,String customer_id,String product_id,String property,String color){
		return DAL.remove_product_later(email,customer_id,product_id,property,color);
	}

	@Override
	public List<Product_Entity> search_product_by_brand(String brand, int row) {
		// TODO Auto-generated method stub
		return DAL.search_product_by_brand(brand, row);
	}

	@Override
	public List<Product_Entity> get_product_by_category(String produt_type_vmall) {
		// TODO Auto-generated method stub
		return ProductDAL.get_product_by_category(produt_type_vmall);
	};
}
