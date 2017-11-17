package vn.vmall.Interface;

import java.util.List;

import vn.vmall.Entity.ProductTypeEntity;

public interface ProductTypeInterface {
	
	List<ProductTypeEntity> get_catgory_bycus(String customerid);
	
	
}
