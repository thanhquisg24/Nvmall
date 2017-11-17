package vn.vmall.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.vmall.DAL.ProductTypeDAL;
import vn.vmall.Entity.ProductTypeEntity;
import vn.vmall.Interface.ProductTypeInterface;


@Component(value="ProductTypeImp")
public class ProductTypeImp implements ProductTypeInterface{
	
	@Autowired
	private ProductTypeDAL DAL;
	@Override
	public List<ProductTypeEntity> get_catgory_bycus(String customerid) {
		// TODO Auto-generated method stub
		return DAL.get_catgory_bycus(customerid);
	}

	
}
