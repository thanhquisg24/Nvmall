package vn.vmall.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import vn.vmall.Entity.Config_Entity;


@Component
public class ConfigDAL {

	public List<Config_Entity> get_all_config(){
		List<Config_Entity>  list= new ArrayList<Config_Entity>();
		String query = "SELECT id,name,isvisible "
				+ " FROM tb_config ";
		try{
			ResultSet rs = null;
			rs = ConnectDB.GetDataReturnResultSet(query);
			while(rs.next()){
			
				Config_Entity item=new Config_Entity();
				item.setId(rs.getString("id"));
				item.setName(rs.getString("name"));
				item.setIsvisible(rs.getInt("isvisible"));
				list.add(item);
			}
		
		}catch(Exception ex){}
		return list;
	}
	public int update_munti_config(List<Config_Entity> list)
			throws InstantiationException, ClassNotFoundException, SQLException{
		Connection dbConnection = null;
		PreparedStatement preparedStatementUpdate = null;
		String sql = "UPDATE `tb_config` SET "
				+ " `name` = ? ,`isvisible` = ?  "
				+ " WHERE `id` = ? ";
		try {
		dbConnection=ConnectDB.getconnection();
		dbConnection.setAutoCommit(false);
		preparedStatementUpdate = dbConnection.prepareStatement(sql);
			for(int i=0;i<list.size();i++){
				//System.out.println(listLevel.get(i).getId());
				preparedStatementUpdate.setString(1,list.get(i).getName());
				preparedStatementUpdate.setInt(2,list.get(i).getIsvisible());
				preparedStatementUpdate.setString(3,list.get(i).getId());
				preparedStatementUpdate.addBatch();
			}
			preparedStatementUpdate.executeBatch();
			dbConnection.commit();
			System.out.println("update_munti_config  Done!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			dbConnection.rollback();
			return -1;
		} finally {
			if (preparedStatementUpdate != null) {
				preparedStatementUpdate.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return 0;
	}
	
}
