package vn.vmall.DAL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import vn.vmall.Entity.Nation_Entity;
import vn.vmall.Helper.ResultSetMapper;

@Component
public class NationDAL {

	public List<Nation_Entity> get_allnation() {
		List<Nation_Entity> list=null;
		String query ="SELECT nation_id, nation_name, isvisible, isdelete"
				+ " FROM tb_nation";
		try{
			ResultSet rs = null;
			rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<Nation_Entity> resultSetMapper = new ResultSetMapper<Nation_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,Nation_Entity.class);
		}catch(Exception ex){
			System.out.println("NationDAL error:"+ex);
		}
		if(list==null){
			list=new ArrayList<Nation_Entity>();
		}
		return list;
		}
	
	public Nation_Entity get_nationbyid(String id){
		Nation_Entity e=new Nation_Entity();
		String query ="SELECT nation_id, nation_name, isvisible, isdelete"
				+ " FROM tb_nation Where nation_id='"+id+"'";
		try{
			ResultSet rs = null;
			rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<Nation_Entity> resultSetMapper = new ResultSetMapper<Nation_Entity>();
			e = resultSetMapper.mapRersultSetToObject_singlerow(rs, Nation_Entity.class);
		}catch(Exception ex){
			System.out.println("NationDAL error:"+ex);
		}
		return e;
	}
	
	
	public int add_update_nation(String type, Nation_Entity d) {
		int _result=0;
		try{
			String spname="sp_nation_insert_update";
			String[] pfield={"f","p_type","p_nation_id","p_nation_name"};
			String[] ptype={"INT","VARCHAR","VARCHAR","VARCHAR"};
			Object[] pvalues={"",type,d.getNation_id(),d.getNation_name()};
			int[] pdirec={1,0,0,0};
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
	public int detele_single_nation(String str_id) {
		int _result=0;
		try{
			String spname="sp_nation_delete_single";
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
