package vn.vmall.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import vn.vmall.Entity.Fee_Entity;

@Component
public class FeeDAL {

	public List<Fee_Entity> get_all_fee(){
		List<Fee_Entity>  list= new ArrayList<Fee_Entity>();
		String query = "SELECT id_fee, fee_name, fee, Moption "
				+ " FROM tb_fee ";
		try{
			ResultSet rs = null;
			rs = ConnectDB.GetDataReturnResultSet(query);
			while(rs.next()){
			
				Fee_Entity item=new Fee_Entity();
				item.setId_fee(rs.getString("id_fee"));
				item.setFee_name(rs.getString("fee_name"));
				item.setFee(rs.getFloat("fee"));
				item.setMoption(rs.getFloat("Moption"));
				list.add(item);
			}
		
		}catch(Exception ex){}
		return list;
	}
	public int update_munti_fee(List<Fee_Entity> list)
			throws InstantiationException, ClassNotFoundException, SQLException{
		Connection dbConnection = null;
		PreparedStatement preparedStatementUpdate = null;
		String sql = "UPDATE `tb_fee` SET "
				+ " `fee_name` = ? ,`fee` = ? ,`Moption` = ? "
				+ " WHERE `id_fee` = ? ";
		try {
		dbConnection=ConnectDB.getconnection();
		dbConnection.setAutoCommit(false);
		preparedStatementUpdate = dbConnection.prepareStatement(sql);
			for(int i=0;i<list.size();i++){
				//System.out.println(listLevel.get(i).getId());
				preparedStatementUpdate.setString(1,list.get(i).getFee_name());
				preparedStatementUpdate.setFloat(2,list.get(i).getFee());
				preparedStatementUpdate.setFloat(3,list.get(i).getMoption());
				preparedStatementUpdate.setString(4,list.get(i).getId_fee());
				preparedStatementUpdate.addBatch();
			}
			preparedStatementUpdate.executeBatch();
			dbConnection.commit();
			System.out.println("update_munti_fee  Done!");
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
