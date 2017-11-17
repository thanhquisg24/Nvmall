package vn.vmall.Interface;

import java.util.List;

import vn.vmall.Entity.Product_Entity;

public interface ProductInterface {
	List<Product_Entity> get_product_recommend(String product_type);
	List<Product_Entity> get_product_care(String product_type);
	List<Product_Entity> get_product_care_index();
	List<Product_Entity> get_product_sell_best();
	List<Product_Entity> search_product_by_category(String product_type,String brand,String category,String price,String color,String property,int row);
	List<Product_Entity> get_product_recomment_product_type(String product_type);
	List<Product_Entity> get_product_qt_product_type(String product_type);
	int remove_product_later(String email,String customer_id,String product_id,String property,String color);
	List<Product_Entity> search_product_by_brand(String brand,int row);
	List<Product_Entity> get_product_by_category(String produt_type_vmall);
}


