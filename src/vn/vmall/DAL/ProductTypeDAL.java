package vn.vmall.DAL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



import org.springframework.stereotype.Component;

import vn.vmall.Entity.ProductTypeEntity;
import vn.vmall.Helper.ResultSetMapper;

@Component
public class ProductTypeDAL {

	public List<ProductTypeEntity> get_catgory_bycus(String customerid) {
		List<ProductTypeEntity> list=null;
		String query ="SELECT customer_id, product_type_id, product_type_name, category_img, title_img, isvisible, isdelete, isstate, issyn "
				+ " FROM tb_product_type"
				+ " WHERE customer_id='"+customerid+"'"
				+ " AND isvisible=1;";
		try{
			ResultSet rs = null;
		
			rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<ProductTypeEntity> resultSetMapper = new ResultSetMapper<ProductTypeEntity>();
			list = resultSetMapper.mapRersultSetToObject(rs,ProductTypeEntity.class);
			
		}catch(Exception ex){
			//System.out.println("ProductTypeDAL error:"+ex);
		}
		
		if(list==null){
			list=new ArrayList<ProductTypeEntity>();
		}
		return list;
	}

}
