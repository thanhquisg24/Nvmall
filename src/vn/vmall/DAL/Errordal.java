package vn.vmall.DAL;

import java.sql.ResultSet;
public class Errordal {

	public static String getMesageError(int error){
		String query = "";
		String mes = "";
		ResultSet rs = null;
		try{
			query = "select ID,ErrorMsg from tb_error where Id='"+error+"'";
			rs = ConnectDB.GetDataReturnResultSet(query);
			while(rs.next()){
				mes = rs.getString("ErrorMsg");
				break;
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return mes;
	}
}
