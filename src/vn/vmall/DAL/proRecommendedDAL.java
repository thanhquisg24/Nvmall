package vn.vmall.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import vn.vmall.Entity.Customer_Entity;
import vn.vmall.Entity.proRecommended_Entity;
import vn.vmall.Helper.ResultSetMapper;
import vn.vmall.Helper.SearchPaggModel;


@Component
public class proRecommendedDAL {


	public List<proRecommended_Entity> get_list_search_pagg(
			SearchPaggModel searchmodel) {
		List<proRecommended_Entity> list=null;
		//String query="select * from tb_democrud limit "+searchmodel.getOffset()+","+searchmodel.getRows();
		try{
			ResultSet rs = null;
			String spname = "search_proRecommended";
			String[] pfield = { "p_offset", "p_rows", "p_col", "p_val" };
			Object[] pvalues = {searchmodel.getOffset(),searchmodel.getRows(),searchmodel.getCol(),searchmodel.getVal() };
			rs =ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			//rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<proRecommended_Entity> resultSetMapper = new ResultSetMapper<proRecommended_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,proRecommended_Entity.class);
			
		}catch(Exception ex){
			//System.out.println("proRecommendedDAL error:"+ex);
		}
		if(list==null){
			list=new ArrayList<proRecommended_Entity>();
		}
		return list;
		
		
	}
	public int count_get_list_search_pagg(SearchPaggModel searchmodel){
		int count=0;
		try{
			ResultSet rs = null;
			String spname = "search_proRecommended_count_total";
			String[] pfield = { "p_col", "p_val" };
			Object[] pvalues = {"",""};
			rs =ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			//rs = ConnectDB.GetDataReturnResultSet(query);
			rs.first();
			count =rs.getInt("count");
		}catch(Exception ex){
			//System.out.println("proRecommendedDAL error:"+ex);
		}
		return count;
	}
	public int add_update_proRecommended(String custom_value) throws SQLException, InstantiationException, ClassNotFoundException{
		int _result=0;
		String[] arr_values = custom_value.split("_");
		String cus_id = arr_values[0];
		String type_id = arr_values[2];
		String[] arr_proid = arr_values[3].split(",");

		Connection dBConnection =null;
		PreparedStatement preparedStatementInsert = null;
		String insertTableSQL = "INSERT INTO tb_product_recommend "
				+ "(customer_id, product_id,product_type, isvisible,isdelete) VALUES"
				+ "(?,?,?,?,?)";
		try {
			dBConnection=ConnectDB.getconnection();
			dBConnection.setAutoCommit(false);
			preparedStatementInsert = dBConnection.prepareStatement(insertTableSQL);
				for(int i=0;i<arr_proid.length;i++){
					preparedStatementInsert.setString(1,cus_id);
					preparedStatementInsert.setString(2,arr_proid[i]);
					preparedStatementInsert.setString(3, type_id);
					preparedStatementInsert.setInt(4, 1);
					preparedStatementInsert.setInt(5, 0);
					preparedStatementInsert.addBatch();
				}
				preparedStatementInsert.executeBatch();
				dBConnection.commit();
				System.out.println("do_append_ preparedStatementInsert Done!");
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				dBConnection.rollback();
				return -1;
			} finally {
				if (preparedStatementInsert != null) {
					preparedStatementInsert.close();
				}
				if (dBConnection != null) {
					dBConnection.close();
				}
			}
		return _result;	
	}
	public proRecommended_Entity get_proRecommended_by_id(String id){
		proRecommended_Entity m= null;
		String query ="SELECT product_type_vmall, product_type_name, category_img, title_img, isvisible, isdelete, isstate, issyn "
				+ " FROM tb_product_type_vmall where product_type_vmall='"+id+"'";
		try{
			ResultSet rs = null;
			ResultSetMapper<proRecommended_Entity> resultSetMapper = new ResultSetMapper<proRecommended_Entity>();
			rs = ConnectDB.GetDataReturnResultSet(query);
			m = resultSetMapper.mapRersultSetToObject_singlerow(rs, proRecommended_Entity.class);
			
		}catch(Exception ex){
			
		}
		
		//System.out.print(mem.toString());
		return m;
		
	}

	public int visivled_proRecommended(List<proRecommended_Entity> list,String visible)  throws SQLException, InstantiationException, ClassNotFoundException {
		if(list.size()>0){
			Connection dBConnection =null;
			PreparedStatement preparedStatementUpdate = null;
			String insertTableSQL = "UPDATE `tb_product_recommend` "
					+ " SET `isvisible` = ? "
					+ " WHERE `customer_id` = ? AND `product_id` = ? ";
			try {
				dBConnection=ConnectDB.getconnection();
				dBConnection.setAutoCommit(false);
				preparedStatementUpdate = dBConnection.prepareStatement(insertTableSQL);
					for(int i=0;i<list.size();i++){
						preparedStatementUpdate.setInt(1, Integer.parseInt(visible));
						preparedStatementUpdate.setString(2,list.get(i).getCustomer_id());
						preparedStatementUpdate.setString(3,list.get(i).getProduct_id());
						preparedStatementUpdate.addBatch();
					}
					preparedStatementUpdate.executeBatch();
					dBConnection.commit();
					System.out.println("visibled preparedStatementUpdate Done!");
				} catch (SQLException e) {
					System.out.println(e.getMessage());
					dBConnection.rollback();
					return -1;
				} finally {
					if (preparedStatementUpdate != null) {
						preparedStatementUpdate.close();
					}
					if (dBConnection != null) {
						dBConnection.close();
					}
				}
			return 0;
		}else{
			return -1;
		}
	}
	public List<proRecommended_Entity> get_allbranh() {
		List<proRecommended_Entity> list=null;
		String query ="SELECT product_type_vmall, product_type_name, category_img, title_img, isvisible, isdelete, isstate, issyn "
				+ " FROM tb_product_type_vmall"
				+ " WHERE isvisible= 1";
		try{
			ResultSet rs = null;
		
			rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<proRecommended_Entity> resultSetMapper = new ResultSetMapper<proRecommended_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,proRecommended_Entity.class);
			
		}catch(Exception ex){
			//System.out.println("proRecommendedDAL error:"+ex);
		}
		
		if(list==null){
			list=new ArrayList<proRecommended_Entity>();
		}
		return list;
	}
	public List<proRecommended_Entity> get_datagrip_byproducttypeid(
			SearchPaggModel searchmodel) {
		List<proRecommended_Entity> list=null;
		String query ="SELECT tb1.product_type, tb2.product_type_name, tb1.customer_id, tb3.email, tb1.product_id, tb5.product_name,"
				+"tb1.isvisible,tb1.isdelete "
				+"FROM tb_product_recommend tb1, tb_product_type_sub tb2," 
				+"tb_customer tb3," 
				+"(select product_name,tb4.product_id from tb_product tb4, tb_product_recommend " 
				+"where tb4.customer_id = tb_product_recommend.customer_id "
				+"AND tb4.product_id = tb_product_recommend.product_id) tb5 "
				+"WHERE tb1.product_type = tb2.product_type_id "
				+"AND tb1.customer_id = tb3.id "
				+"AND tb5.product_id = tb1.product_id "
                +"AND tb1.product_type = '"+searchmodel.getCustom_value()+"' "
				+"AND tb1.isdelete = 0"
				+ " limit "+searchmodel.getOffset()+","+searchmodel.getRows();
		try{
			ResultSet rs = null;
			rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<proRecommended_Entity> resultSetMapper = new ResultSetMapper<proRecommended_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,proRecommended_Entity.class);
			
		}catch(Exception ex){
			//System.out.println("proRecommendedDAL error:"+ex);
		}
		if(list==null){
			list=new ArrayList<proRecommended_Entity>();
		}
		return list;
	}
	public int count_get_datagrip_byproducttypeid(SearchPaggModel searchmodel) {
		int count=0;
		String query ="select count(*) as count  from (SELECT tb1.product_type, tb2.product_type_name, tb1.customer_id, tb3.email, tb1.product_id, tb5.product_name,"
				+"tb1.isvisible,tb1.isdelete "
				+"FROM tb_product_recommend tb1, tb_product_type_sub tb2," 
				+"tb_customer tb3," 
				+"(select product_name,tb4.product_id from tb_product tb4, tb_product_recommend " 
				+"where tb4.customer_id = tb_product_recommend.customer_id "
				+"AND tb4.product_id = tb_product_recommend.product_id) tb5 "
				+"WHERE tb1.product_type = tb2.product_type_id "
				+"AND tb1.customer_id = tb3.id "
				+"AND tb5.product_id = tb1.product_id "
                +"AND tb1.product_type = '"+searchmodel.getCustom_value()+"' "
				+"AND tb1.isdelete = 0)A";
				
		try{
			ResultSet rs = null;
			rs = ConnectDB.GetDataReturnResultSet(query);
			rs.first();
			count =rs.getInt("count");
		}catch(Exception ex){
			//System.out.println("proRecommendedDAL error:"+ex);
		}
		return count;
	}
	public int detele_single_proRecommended(String str_id) {
		int _result=0;
		try{
			String spname="sp_proRecommended_delete_single";
			String[] pfield={"f","p_str_id"};
			String[] ptype={"INT","VARCHAR"};
			Object[] pvalues={"",str_id};
			int[] pdirec={1,0};
			Map<String,Object> mapOfObjects = new HashMap<String,Object>();
			mapOfObjects = ConnectDB.ExecBoFunctionReturnList(spname, pfield, ptype, pvalues, pdirec);
			_result = Integer.parseInt(mapOfObjects.get("f").toString());
		}catch(Exception e)
		{
			e.printStackTrace();
			_result=-1;
		}
		return _result;
	}
	public int delete_multi_proRecommended(List<proRecommended_Entity> list)  throws SQLException, InstantiationException, ClassNotFoundException{
		if(list.size()>0){
			Connection dBConnection =null;
			PreparedStatement preparedStatementUpdate = null;
			String insertTableSQL = "UPDATE `tb_product_recommend` "
					+ " SET `isdelete` = 1 "
					+ " WHERE `customer_id` = ? AND `product_id` = ? ";
			try {
				dBConnection=ConnectDB.getconnection();
				dBConnection.setAutoCommit(false);
				preparedStatementUpdate = dBConnection.prepareStatement(insertTableSQL);
					for(int i=0;i<list.size();i++){
						preparedStatementUpdate.setString(1,list.get(i).getCustomer_id());
						preparedStatementUpdate.setString(2,list.get(i).getProduct_id());
						preparedStatementUpdate.addBatch();
					}
					preparedStatementUpdate.executeBatch();
					dBConnection.commit();
					System.out.println("visibled preparedStatementUpdate Done!");
				} catch (SQLException e) {
					System.out.println(e.getMessage());
					dBConnection.rollback();
					return -1;
				} finally {
					if (preparedStatementUpdate != null) {
						preparedStatementUpdate.close();
					}
					if (dBConnection != null) {
						dBConnection.close();
					}
				}
			return 0;
		}else{
			return -1;
		}
	}
	public List<proRecommended_Entity> get_datagrip_customer_product(
			SearchPaggModel searchmodel) {
		String[] arr_values = searchmodel.getCustom_value().split("_");
		String customer_id = arr_values[0];
		String cate_id = arr_values[1];
		List<proRecommended_Entity> list=null;
		String query ="SELECT * FROM(SELECT tb2.group_category_id , tb2.category_id product_type,tb2.customer_id, tb2.product_type_id customer_type,tb1.product_id,"
						+"tb2.product_type_name customer_type_name, tb1.product_name " 
						+"FROM tb_product tb1, tb_product_type tb2 "
						+"WHERE tb2.category_id = '"+cate_id+"' "
						+"AND tb1.product_type_id = tb2.product_type_id "
						+"AND tb1.customer_id = tb2.customer_id "
						+"AND tb2.customer_id = '"+customer_id+"'"
						+" AND tb1.product_id not in (SELECT product_id FROM tb_product_recommend WHERE customer_id = tb2.customer_id)"
						+ " limit "+searchmodel.getOffset()+","+searchmodel.getRows()+")A";
		try{
			ResultSet rs = null;
			rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<proRecommended_Entity> resultSetMapper = new ResultSetMapper<proRecommended_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,proRecommended_Entity.class);
			System.out.println(rs.getString("customer_type_name").toString());
			
		}catch(Exception ex){
			//System.out.println("proRecommendedDAL error:"+ex);
		}
		if(list==null){
			list=new ArrayList<proRecommended_Entity>();
		}
		return list;
	}
	public int count_get_datagrip_customer_product(SearchPaggModel searchmodel) {
		int count=0;
		String[] arr_values = searchmodel.getCustom_value().split("_");
		String customer_id = arr_values[0];
		String cate_id = arr_values[1];
		String query ="select count(*) as count  from (SELECT tb2.group_category_id , tb2.category_id product_type,tb2.customer_id, tb2.product_type_id customer_type,tb1.product_id,"
						+"tb2.product_type_name customer_type_name, tb1.product_name " 
						+"FROM tb_product tb1, tb_product_type tb2 "
						+"WHERE tb2.category_id = '"+cate_id+"' "
						+"AND tb1.product_type_id = tb2.product_type_id "
						+"AND tb1.customer_id = tb2.customer_id "
						+"AND tb2.customer_id = '"+customer_id+"'"
						+ " AND tb1.product_id not in (SELECT product_id FROM tb_product_recommend WHERE customer_id = tb2.customer_id))A";
				
		try{
			ResultSet rs = null;
			rs = ConnectDB.GetDataReturnResultSet(query);
			rs.first();
			count =rs.getInt("count");
		}catch(Exception ex){
			//System.out.println("proRecommendedDAL error:"+ex);
		}
		return count;
	}
	
	public List<Customer_Entity> get_customer_of_type(String type_id){
		List<Customer_Entity> cus = null;
		String query = "SELECT tb2.id, tb2.email, tb2.shop_name "
						+"FROM tb_product_type tb1, tb_customer tb2 "
						+"WHERE tb1.customer_id = tb2.id "
						+"AND tb1.category_id = '"+type_id+"'"
						+"Group by tb2.id";
		try{
			ResultSet rs = null;
			rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<Customer_Entity> resultSetMapper = new ResultSetMapper<Customer_Entity>();
			cus = resultSetMapper.mapRersultSetToObject(rs,Customer_Entity.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		if(cus==null){
			cus = new ArrayList<Customer_Entity>();
		}
		return cus;
	}
}
