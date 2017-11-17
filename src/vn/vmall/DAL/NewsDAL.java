package vn.vmall.DAL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import vn.vmall.Entity.CategoryNew_Entity;
import vn.vmall.Entity.CatgoryNews_Entitty;
import vn.vmall.Entity.News_Entity;
import vn.vmall.Helper.ResultSetMapper;
import vn.vmall.Helper.SearchPaggModel;

@Component
public class NewsDAL {

	public int count_get_list_search_pagg(SearchPaggModel searchmodel) {
		int count=0;
		ResultSet rs = null;
		Connection con = null;
		CallableStatement call=null;
		try{
			String spname = "search_news_count_total";
			String[] pfield = { "p_col", "p_val" };
			Object[] pvalues = {searchmodel.getCol(),searchmodel.getVal()};
			con=ConnectDB.getconnection();
			call=ConnectDB.get_CallableStatement_procedure_normal(spname, pfield, pvalues, con);
			rs =call.executeQuery();
			rs.first();
			count =rs.getInt("count");
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(rs!=null){
					rs.close();
				}
				if(call!=null){
					call.close();
				}
				if(con!=null){
					con.close();
				}		
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		return count;
	}
	public List<News_Entity> get_list_search_pagg(SearchPaggModel searchmodel) {
		ResultSet rs = null;
		Connection con = null;
		CallableStatement call=null;
		List<News_Entity> list=null;
		//String query="select * from tb_democrud limit "+searchmodel.getOffset()+","+searchmodel.getRows();
		try{
			String spname = "search_news";
			String[] pfield = { "p_offset", "p_rows", "p_col", "p_val" };
			Object[] pvalues = {searchmodel.getOffset(),searchmodel.getRows(),searchmodel.getCol(),searchmodel.getVal() };
			con=ConnectDB.getconnection();
			call=ConnectDB.get_CallableStatement_procedure_normal(spname, pfield, pvalues, con);
			rs =call.executeQuery();
			ResultSetMapper<News_Entity> resultSetMapper = new ResultSetMapper<News_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,News_Entity.class);
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(rs!=null){
					rs.close();
				}
				if(call!=null){
					call.close();
				}
				if(con!=null){
					con.close();
				}		
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		if(list==null){
			list=new ArrayList<News_Entity>();
		}
		return list;
	}
	public int visivled_news(String str_id, String visible) {
		int _result=0;
		try{
			String spname="sp_news_visibled";
			String[] pfield={"f","p_str_id","p_visible"};
			String[] ptype={"INT","VARCHAR","VARCHAR"};
			Object[] pvalues={"",str_id,visible};
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
	public int delete_multi_news(String str_id) {
		int _result=0;
		try{
			String spname="sp_news_delete";
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
	public List<CategoryNew_Entity> get_allCatgoryNews() {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		List<CategoryNew_Entity> list=null;
		try{
			String query="SELECT category_id, category_name, isvisible, category_img"
						+"  FROM tb_news_category";
			con = ConnectDB.getconnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			ResultSetMapper<CategoryNew_Entity> resultSetMapper = new ResultSetMapper<CategoryNew_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,CategoryNew_Entity.class);
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
		if(list==null){
			list=new ArrayList<CategoryNew_Entity>();
		}
		return list;
	}
	public int add_update_catgorynews(String p_type, CatgoryNews_Entitty d) {
		int _result=0;
		try{
			String spname="sp_catgorynews_insert_update";
			String[] pfield={"f","p_type","p_id","p_name","p_img","p_editor"};
			String[] ptype={"INT","VARCHAR","VARCHAR","VARCHAR","VARCHAR","VARCHAR"};
			Object[] pvalues={"",p_type,d.getId(),d.getName(),d.getImg(),d.getCreator()};
			int[] pdirec={1,0,0,0,0,0};
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
	public Map<String,Object> SaveNews(String type, News_Entity d) {
		Map<String,Object> mapOfObjects = new HashMap<String,Object>();
		try{
			String spname="sp_news_insert_update";
			String[] pfield={"f","p_type","p_id","p_title","p_img",
								"p_short_description","p_content","p_news_catgory","p_editor","out_id"};
			String[] ptype={"INT","VARCHAR","VARCHAR","VARCHAR","VARCHAR",
								"VARCHAR","TEXT","VARCHAR","VARCHAR","VARCHAR"};
			Object[] pvalues={"",type,d.getId(),d.getTitle(),d.getImg(),
								d.getShort_description(),d.getContent(),d.getNews_catgory(),d.getCreator(),""};
			int[] pdirec={1,0,0,0,0,
							0,0,0,0,1};
			mapOfObjects = ConnectDB.ExecBoFunctionReturnList(spname, pfield, ptype, pvalues, pdirec);
			
		}catch(Exception e)
		{
			e.printStackTrace();
			mapOfObjects.put("f", "-1");
			mapOfObjects.put("out_id", "");
		}
		return mapOfObjects;
	}
	public News_Entity get_Newsbyid(String news_id) {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		News_Entity a=new News_Entity();
		try{
			String query="SELECT  id, title, short_description, content,"
						+" creator, modifyer, news_catgory, isvisible, img "
						+ " FROM tb_news "
						+ " Where id='"+news_id+"'";
			con = ConnectDB.getconnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			ResultSetMapper<News_Entity> resultSetMapper = new ResultSetMapper<News_Entity>();
			a = resultSetMapper.mapRersultSetToObject_singlerow(rs, News_Entity.class);
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
	public List<CategoryNew_Entity> get_list_new_cate(SearchPaggModel searchmodel) {
		List<CategoryNew_Entity> list = null;
		try {
			ResultSet rs = null;
			String spname = "search_catgory_news";
			String[] pfield = { "p_offset", "p_rows", "p_col", "p_val" };
			Object[] pvalues = { searchmodel.getOffset(), searchmodel.getRows(), searchmodel.getCol(),
					searchmodel.getVal() };
			rs = ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			// rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<CategoryNew_Entity> resultSetMapper = new ResultSetMapper<CategoryNew_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs, CategoryNew_Entity.class);

		} catch (Exception ex) {
			// System.out.println("CatgoryDAL error:"+ex);
		}
		if (list == null) {
			list = new ArrayList<CategoryNew_Entity>();
		}
		return list;
	}

	public int count_get_list_new_cate(SearchPaggModel searchmodel) {
		int count = 0;
		try {
			ResultSet rs = null;
			String spname = "search_catgory_news_count_total";
			String[] pfield = { "p_col", "p_val" };
			Object[] pvalues = { searchmodel.getCol(), searchmodel.getVal() };
			rs = ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			rs.first();
			count = rs.getInt("count");

		} catch (Exception ex) {
		
		}
		return count;
	}

	public int add_update_catgory_new(String type, CategoryNew_Entity d) {
		int _result = 0;
		try {
			String spname = "sp_catgory_news_insert_update";
			String[] pfield = { "f", "p_type","p_category_id","p_category_name", "p_category_img",
					"p_title_img" };
			String[] ptype = { "INT", "VARCHAR", "VARCHAR","VARCHAR", "VARCHAR", "VARCHAR" };
			Object[] pvalues = { "", type, d.getCategory_id(),d.getCategory_name(), d.getCategory_img(),
					d.getTitle_img() };
			int[] pdirec = { 1, 0, 0, 0, 0, 0 };
			Map<String, Object> mapOfObjects = new HashMap<String, Object>();
			mapOfObjects = ConnectDB.ExecBoFunctionReturnList(spname, pfield, ptype, pvalues, pdirec);
			_result = Integer.parseInt(mapOfObjects.get("f").toString());
		} catch (Exception e) {
			e.printStackTrace();
			_result = -1;
		}
		return _result;
	}

	public CategoryNew_Entity get_newcat_by_id(String id) {
		CategoryNew_Entity m = null;
		String query = "SELECT category_id, category_name, category_img, title_img, isvisible, isdelete "
				+ " FROM tb_news_category where category_id='" + id + "'";
		try {
			ResultSet rs = null;
			ResultSetMapper<CategoryNew_Entity> resultSetMapper = new ResultSetMapper<CategoryNew_Entity>();
			rs = ConnectDB.GetDataReturnResultSet(query);
			m = resultSetMapper.mapRersultSetToObject_singlerow(rs, CategoryNew_Entity.class);

		} catch (Exception ex) {

		}
		return m;

	}

	public int remove_new_catgory(String str_id) {
		int _result = 0;
		try {
			String spname = "sp_catgory_news_remove";
			String[] pfield = { "f", "p_str_id" };
			String[] ptype = { "INT", "VARCHAR" };
			Object[] pvalues = { "", str_id };
			int[] pdirec = { 1, 0 };
			Map<String, Object> mapOfObjects = new HashMap<String, Object>();
			mapOfObjects = ConnectDB.ExecBoFunctionReturnList(spname, pfield, ptype, pvalues, pdirec);
			_result = Integer.parseInt(mapOfObjects.get("f").toString());
		} catch (Exception e) {
			e.printStackTrace();
			_result = -1;
		}
		return _result;
	}

	public int visibled_new_catgory(String str_id, String visible) {
		int _result = 0;
		try {
			String spname = "sp_visibled_news_catgory";
			String[] pfield = { "f", "p_str_id", "p_visible" };
			String[] ptype = { "INT", "VARCHAR", "VARCHAR" };
			Object[] pvalues = { "", str_id, visible };
			int[] pdirec = { 1, 0, 0 };
			Map<String, Object> mapOfObjects = new HashMap<String, Object>();
			mapOfObjects = ConnectDB.ExecBoFunctionReturnList(spname, pfield, ptype, pvalues, pdirec);
			_result = Integer.parseInt(mapOfObjects.get("f").toString());
		} catch (Exception e) {
			e.printStackTrace();
			_result = -1;
		}
		return _result;
	}
}
