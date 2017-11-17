package vn.vmall.DAL;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import vn.vmall.Entity.DemoCRUD_Entity;

import vn.vmall.Helper.ResultSetMapper;
import vn.vmall.Helper.SearchPaggModel;

@Component
public class DemoCRUDDAL {
	
	public List<DemoCRUD_Entity> get_list_search_pagg(
			SearchPaggModel searchmodel) {
		List<DemoCRUD_Entity> list=null;
		//String query="select * from tb_democrud limit "+searchmodel.getOffset()+","+searchmodel.getRows();
		try{
			ResultSet rs = null;
			String spname = "search_DemoCRUD";
			String[] pfield = { "p_offset", "p_rows", "p_col", "p_val" };
			Object[] pvalues = {searchmodel.getOffset(),searchmodel.getRows(),searchmodel.getCol(),searchmodel.getVal() };
			rs =ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			//rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<DemoCRUD_Entity> resultSetMapper = new ResultSetMapper<DemoCRUD_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,DemoCRUD_Entity.class);
			
		}catch(Exception ex){
			//System.out.println("DemoCRUDDAL error:"+ex);
		}
		
		return list;
		
		
	}
	public int count_get_list_search_pagg(SearchPaggModel searchmodel){
		int count=0;
		try{
			ResultSet rs = null;
			String spname = "search_DemoCRUD_count_total";
			String[] pfield = { "p_col", "p_val" };
			Object[] pvalues = {searchmodel.getCol(),searchmodel.getVal()};
			rs =ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			//rs = ConnectDB.GetDataReturnResultSet(query);
			rs.first();
			count =rs.getInt("count");
			
		}catch(Exception ex){
			//System.out.println("DemoCRUDDAL error:"+ex);
		}
		return count;
	}
	public int add_update_demo(String type, DemoCRUD_Entity d){
		int _result=0;
		try{
			String spname="sp_DemoCRUD_insert_update";
			String[] pfield={"f","p_type","p_id","p_name","p_email","p_address","p_phone"};
			String[] ptype={"INT","VARCHAR","VARCHAR","VARCHAR","VARCHAR","VARCHAR","VARCHAR"};
			Object[] pvalues={"",type,d.getId(),d.getName(),d.getEmail(),d.getAddress(),d.getPhone()};
			int[] pdirec={1,0,0,0,0,0,0};
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
	public DemoCRUD_Entity get_democrud_by_id(String id){
		DemoCRUD_Entity m= null;
		String query ="SELECT id, name, email, address, phone, create_date, creator, is_enabled "
				+ " FROM tb_democrud where id='"+id+"'";
		try{
			ResultSet rs = null;
			ResultSetMapper<DemoCRUD_Entity> resultSetMapper = new ResultSetMapper<DemoCRUD_Entity>();
			rs = ConnectDB.GetDataReturnResultSet(query);
			m = resultSetMapper.mapRersultSetToObject_singlerow(rs, DemoCRUD_Entity.class);
			
		}catch(Exception ex){
			
		}
		//System.out.print(mem.toString());
		return m;
		
	}
	public int delete_demo(String str_id){
		int _result=0;
		try{
			String spname="sp_DemoCRUD_delete";
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

}
