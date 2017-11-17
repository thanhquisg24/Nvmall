package vn.vmall.DAL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.vmall.Entity.ItemRegulation;
import vn.vmall.Helper.AllcodeModel;

public class RegulationDAL {
	public static ItemRegulation get_schema(){
		ItemRegulation item = new ItemRegulation();
		String query = "SELECT content_page FROM tb_rule where id = 'SCHEME'";
		try{
			ResultSet rs = null;
			rs = ConnectDB.GetDataReturnResultSet(query);
			while(rs.next()){
				item.setContent_page(rs.getString("content_page"));
				item.setContent_page(replace_origin_html(item.getContent_page()));
				break;
			}
		}catch(Exception ex){
			
		}
		return item;
	}
	public static ItemRegulation get_regulation(){
		ItemRegulation item = new ItemRegulation();
		String query = "SELECT content_page FROM tb_rule where id = 'REGULATION'";
		try{
			ResultSet rs = null;
			rs = ConnectDB.GetDataReturnResultSet(query);
			while(rs.next()){
				item.setContent_page(rs.getString("content_page"));
				item.setContent_page(replace_origin_html(item.getContent_page()));
				break;
			}
		}catch(Exception ex){
			
		}
		return item;
	}
	public static String replace_origin_html(String str_html){
		  return str_html.replace( "&amp;","&").replace("&lt;","<").replace("&gt;",">").replace( "&quot;","\"").replace("&blink;","'");
		 }
	public static String replace_html_origin(String str_html){
		  return str_html.replace( "&","&amp;").replace("<","&lt;").replace(">","&gt;").replace( "\"","&quot;").replace("'","&blink;");
		 }
}
