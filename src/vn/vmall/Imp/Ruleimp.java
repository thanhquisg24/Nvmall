package vn.vmall.Imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import vn.vmall.DAL.ConnectDB;
import vn.vmall.Entity.RuleModel;
import vn.vmall.Helper.ResultSetMapper;
import vn.vmall.Interface.RuleInterface;

@Repository(value="Ruleimp")
public class Ruleimp implements RuleInterface {

	@Override
	public RuleModel get_regulartion() {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		RuleModel a=new RuleModel();
		try{
			String query="SELECT  id, content_page"
						+ " FROM tb_rule "
						+ " Where id='REGULATION'";
			con = ConnectDB.getconnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			ResultSetMapper<RuleModel> resultSetMapper = new ResultSetMapper<RuleModel>();
			a = resultSetMapper.mapRersultSetToObject_singlerow(rs, RuleModel.class);
			a.setContent_page(replace_origin_html(a.getContent_page()));
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
		
		return a;
	}

	@Override
	public RuleModel get_scheme() {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		RuleModel a=new RuleModel();
		try{
			String query="SELECT  id, content_page"
						+ " FROM tb_rule "
						+ " Where id='SCHEME'";
			con = ConnectDB.getconnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			ResultSetMapper<RuleModel> resultSetMapper = new ResultSetMapper<RuleModel>();
			a = resultSetMapper.mapRersultSetToObject_singlerow(rs, RuleModel.class);
			a.setContent_page(replace_origin_html(a.getContent_page()));
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
		
		return a;
	}

	@Override
	public int saverule(String id, String content_page) {
		int result=-1;
		String newcontent=replace_html_origin(content_page);
		try{
			String query="UPDATE tb_rule  "
					+ " set  content_page='"+newcontent+"'"
					+ " Where id='"+id+"'";
			
			System.out.println(query);
				result = ConnectDB.ExecUpdate(query);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}

	public static String replace_origin_html(String str_html){
		  return str_html.replace( "&amp;","&").replace("&lt;","<").replace("&gt;",">").replace( "&quot;","\"").replace("&blink;","'");
		 }
	public static String replace_html_origin(String str_html){
		  return str_html.replace( "&","&amp;").replace("<","&lt;").replace(">","&gt;").replace( "\"","&quot;").replace("'","&blink;");
		 }
}
