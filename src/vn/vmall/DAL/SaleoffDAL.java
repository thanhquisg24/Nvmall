package vn.vmall.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import vn.vmall.Entity.Catgory_Entity;
import vn.vmall.Entity.Catgory_vmall_sub_temp;

@Component
public class SaleoffDAL {

	public ArrayList<Catgory_Entity> get_sale_list_catgory() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Catgory_vmall_sub_temp> get_temp_catgory_databysale() {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		List<Catgory_vmall_sub_temp> list=new ArrayList<Catgory_vmall_sub_temp>();;
		try{
			String query="select A.product_type_id ,A.customer_id,A.group_category_id,A.category_id as produc_type_sub_id,"
				+" tbsub.product_type_name as product_type_sub_name,tbvmall.product_type_vmall,"
                +" tbvmall.product_type_name as product_type_vmall_name"
				+" from"
				+" (SELECT tb1.product_type_id,tb1.customer_id,tb2.group_category_id,tb2.category_id,tb2.product_type_name FROM" 
				+"	tb_product tb1,tb_product_type tb2"
				+"	where tb1.ispromo =true "
				+"	and tb1.product_type_id=tb2.product_type_id"
				+"	and tb1.customer_id=tb2.customer_id"
				+"	and tb1.isvisible=true and tb2.isvisible=true"
				+"	group by tb1.customer_id,tb1.product_type_id)"
			+"	A ,tb_product_type_sub tbsub,tb_product_type_vmall tbvmall"
			+"	where A.category_id=tbsub.product_type_id"
            +"	and tbvmall.product_type_vmall=tbsub.product_type_vmall"
            +"	and tbsub.isvisible=true";
			con = ConnectDB.getconnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				Catgory_vmall_sub_temp item=new Catgory_vmall_sub_temp();
				item.setCustomer_id(rs.getString("customer_id"));
				item.setGroup_category_id(rs.getString("group_category_id"));
				item.setProduc_type_sub_id(rs.getString("produc_type_sub_id"));
				item.setProduct_type_id(rs.getString("product_type_id"));
				item.setProduct_type_sub_name(rs.getString("product_type_sub_name"));
				item.setProduct_type_vmall(rs.getString("product_type_vmall"));
				item.setProduct_type_vmall_name(rs.getString("product_type_vmall_name"));
				list.add(item);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(rs!=null){
					rs.close();
				}
				if(ps!=null){
					ps.close();
				}
				if(con!=null){
					con.close();
				}		
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		
		return list;
	}

}
