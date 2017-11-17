package vn.vmall.DAL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import vn.vmall.Entity.Slide_Entity;
import vn.vmall.Helper.ResultSetMapper;
import vn.vmall.Helper.SearchPaggModel;


@Component
public class SlideDAL {


	public List<Slide_Entity> get_list_search_pagg(
			SearchPaggModel searchmodel) {
		List<Slide_Entity> list=null;
		//String query="select * from tb_democrud limit "+searchmodel.getOffset()+","+searchmodel.getRows();
		try{
			ResultSet rs = null;
			String spname = "search_slide";
			String[] pfield = { "p_offset", "p_rows", "p_col", "p_val" };
			Object[] pvalues = {searchmodel.getOffset(),searchmodel.getRows(),searchmodel.getCol(),searchmodel.getVal() };
			rs =ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			//rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<Slide_Entity> resultSetMapper = new ResultSetMapper<Slide_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,Slide_Entity.class);
			
		}catch(Exception ex){
			//System.out.println("SlideDAL error:"+ex);
		}
		if(list==null){
			list=new ArrayList<Slide_Entity>();
		}
		return list;
		
		
	}
	public int count_get_list_search_pagg(SearchPaggModel searchmodel){
		int count=0;
		try{
			ResultSet rs = null;
			String spname = "search_slide_count_total";
			String[] pfield = { "p_col", "p_val" };
			Object[] pvalues = {searchmodel.getCol(),searchmodel.getVal()};
			rs =ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			//rs = ConnectDB.GetDataReturnResultSet(query);
			rs.first();
			count =rs.getInt("count");
			
		}catch(Exception ex){
			//System.out.println("SlideDAL error:"+ex);
		}
		return count;
	}
	public int add_update_slide(String type, Slide_Entity d){
		int _result=0;
		try{
			String spname="sp_slide_insert_update";
			String[] pfield={"f","p_type","p_id","p_name","p_creator","p_modifyer"};
			String[] ptype={"INT","VARCHAR","VARCHAR","VARCHAR","VARCHAR","VARCHAR"};
			Object[] pvalues={"",type,d.getId(),d.getName(),d.getCreator(),d.getModifyer()};
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
	public Slide_Entity get_slide_by_id(String id){
		Slide_Entity m= null;
		String query ="SELECT id, name, create_date, creator, isvisible, modifyer, modify_date "
				+ " FROM tb_slider where id='"+id+"'";
		try{
			ResultSet rs = null;
			ResultSetMapper<Slide_Entity> resultSetMapper = new ResultSetMapper<Slide_Entity>();
			rs = ConnectDB.GetDataReturnResultSet(query);
			m = resultSetMapper.mapRersultSetToObject_singlerow(rs, Slide_Entity.class);
			
		}catch(Exception ex){
			
		}
		
		//System.out.print(mem.toString());
		return m;
		
	}
	public int delete_slide(String str_id){
		int _result=0;
		try{
			String spname="sp_slide_delete";
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
	public int visivled_slide(String str_id,String visible) {
		int _result=0;
		try{
			String spname="sp_slide_visibled";
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
	public  List<Slide_Entity> get_list_slide() {
			
		List<Slide_Entity> list=new ArrayList<Slide_Entity>();
		//String query="select * from tb_democrud limit "+searchmodel.getOffset()+","+searchmodel.getRows();
		try{
			ResultSet rs = null;
			String query = "select id,name from tb_slider where isvisible = true and parent='0'";
			rs = ConnectDB.GetDataReturnResultSet(query);			
			while(rs.next()){
				Slide_Entity item = new Slide_Entity();
				item.setName(rs.getString("name"));
				item.setList_sub(get_list_slide_sub(rs.getString("id")));
				list.add(item);
			}
			
		}catch(Exception ex){
			//System.out.println("SlideDAL error:"+ex);
		}
		return list;		
		
	}
	
	public List<Slide_Entity> get_list_slide_sub(String id) {
		
		List<Slide_Entity> list=null;
		//String query="select * from tb_democrud limit "+searchmodel.getOffset()+","+searchmodel.getRows();
		try{
			ResultSet rs = null;
			String query = "select name from tb_slider where isvisible = true and parent='"+id+"'";
			rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<Slide_Entity> resultSetMapper = new ResultSetMapper<Slide_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,Slide_Entity.class);
			
		}catch(Exception ex){
			//System.out.println("SlideDAL error:"+ex);
		}
		if(list==null){
			list=new ArrayList<Slide_Entity>();
		}
		return list;		
		
	}
}
