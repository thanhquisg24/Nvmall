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

import vn.vmall.Entity.LevelPrice_Entity;
import vn.vmall.Helper.ResultSetMapper;


@Component
public class LevelPriceDAL {

	public List<LevelPrice_Entity> get_alllevel() {
			List<LevelPrice_Entity> list=null;
			String query ="SELECT id, name, pquery"
					+ " FROM tb_level_price";
			try{
				ResultSet rs = null;
				rs = ConnectDB.GetDataReturnResultSet(query);
				ResultSetMapper<LevelPrice_Entity> resultSetMapper = new ResultSetMapper<LevelPrice_Entity>();
				list = resultSetMapper.mapRersultSetToObject(rs,LevelPrice_Entity.class);
				
			}catch(Exception ex){
				System.out.println("LevelPriceDAL error:"+ex);
			}
			if(list==null){
				list=new ArrayList<LevelPrice_Entity>();
			}
			return list;
	}
	

	public List<LevelPrice_Entity> get_leveldetail_byvmall(String vmallid) {
			List<LevelPrice_Entity> list=null;
			String query ="SELECT tb1.id,tb2.product_type_vmall_id,"
					+ " tb1.name ,tb1.pquery "
					+" FROM tb_level_price tb1,tb_levelprice_vmall_detail tb2 "
					+" where tb1.id=tb2.level_price_id and tb2.product_type_vmall_id='"+vmallid+"'";
			try{
			
				ResultSet rs = null;
				rs = ConnectDB.GetDataReturnResultSet(query);
				ResultSetMapper<LevelPrice_Entity> resultSetMapper = new ResultSetMapper<LevelPrice_Entity>();
				list = resultSetMapper.mapRersultSetToObject(rs,LevelPrice_Entity.class);
				
			}catch(Exception ex){
				System.out.println("LevelPriceDAL error:"+ex);
			}
			if(list==null){
				list=new ArrayList<LevelPrice_Entity>();
			}
			return list;
	}
	
	

	public int add_update_level(String type, LevelPrice_Entity d) {
		int _result=0;
		try{
			String spname="sp_level_insert_update";
			String[] pfield={"f","p_type","p_id","p_name","p_pquery",};
			String[] ptype={"INT","VARCHAR","VARCHAR","VARCHAR","VARCHAR"};
			Object[] pvalues={"",type,d.getId(),d.getName(),d.getPquery()};
			int[] pdirec={1,0,0,0,0};
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

	public int detele_single_level(String str_id) {
		int _result=0;
		try{
			String spname="sp_level_delete_single";
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
	public int delete_multi_leveldetail(String str_id, String product_type_vmall) {
		int _result=0;
		try{
			String spname="sp_leveldetail_delete_multi";
			String[] pfield={"f","p_str_id","p_product_type_vmall"};
			String[] ptype={"INT","VARCHAR","VARCHAR"};
			Object[] pvalues={"",str_id,product_type_vmall};
			int[] pdirec={1,0,0};
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

	public int do_append_level(String product_type_vmall,
			List<LevelPrice_Entity> listLevel) throws InstantiationException, ClassNotFoundException, SQLException  {
		Connection dbConnection = null;
		PreparedStatement preparedStatementInsert = null;
		String insertTableSQL = "INSERT INTO tb_levelprice_vmall_detail"
				+ "(level_price_id, product_type_vmall_id) VALUES"
				+ "(?,?)";
		try {
		dbConnection=ConnectDB.getconnection();
		dbConnection.setAutoCommit(false);
		preparedStatementInsert = dbConnection.prepareStatement(insertTableSQL);
			for(int i=0;i<listLevel.size();i++){
				//System.out.println(listLevel.get(i).getId());
				preparedStatementInsert.setInt(1,listLevel.get(i).getId());
				preparedStatementInsert.setString(2,product_type_vmall);
				preparedStatementInsert.addBatch();
			}
			preparedStatementInsert.executeBatch();
			dbConnection.commit();
			System.out.println("do_append_level preparedStatementInsert Done!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			dbConnection.rollback();
			return -1;
		} finally {
			if (preparedStatementInsert != null) {
				preparedStatementInsert.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return 0;
	}

}
