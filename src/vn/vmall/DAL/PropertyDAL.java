package vn.vmall.DAL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import vn.vmall.Entity.Property_Entity;
import vn.vmall.Helper.ResultSetMapper;
import vn.vmall.Helper.SearchPaggModel;


@Component
public class PropertyDAL {


	public List<Property_Entity> get_list_search_pagg(
			SearchPaggModel searchmodel) {
		List<Property_Entity> list=null;
		//String query="select * from tb_democrud limit "+searchmodel.getOffset()+","+searchmodel.getRows();
		try{
			ResultSet rs = null;
			String spname = "search_property";
			String[] pfield = { "p_offset", "p_rows", "p_col", "p_val" };
			Object[] pvalues = {searchmodel.getOffset(),searchmodel.getRows(),searchmodel.getCol(),searchmodel.getVal() };
			rs =ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			//rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<Property_Entity> resultSetMapper = new ResultSetMapper<Property_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,Property_Entity.class);
			
		}catch(Exception ex){
			//System.out.println("PropertyDAL error:"+ex);
		}
		if(list==null){
			list=new ArrayList<Property_Entity>();
		}
		return list;
		
		
	}
	public int count_get_list_search_pagg(SearchPaggModel searchmodel){
		int count=0;
		try{
			ResultSet rs = null;
			String spname = "search_property_count_total";
			String[] pfield = { "p_col", "p_val" };
			Object[] pvalues = {searchmodel.getCol(),searchmodel.getVal()};
			rs =ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			//rs = ConnectDB.GetDataReturnResultSet(query);
			rs.first();
			count =rs.getInt("count");
			
		}catch(Exception ex){
			//System.out.println("PropertyDAL error:"+ex);
		}
		return count;
	}
	public int add_update_property(String type, Property_Entity d){
		int _result=0;
		try{
			String spname="sp_property_insert_update";
			String[] pfield={"f","p_type","p_id","p_property_name","p_product_type_sub_id","p_parent"};
			String[] ptype={"INT","VARCHAR","VARCHAR","VARCHAR","VARCHAR","VARCHAR"};
			Object[] pvalues={"",type,d.getId(),d.getProperty_name(),d.getProduct_type_sub_id(),d.getParent()};
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
	public Property_Entity get_property_by_id(String id){
		Property_Entity m= null;
		String query ="SELECT product_type_vmall, product_type_name, category_img, title_img, isvisible, isdelete, isstate, issyn "
				+ " FROM tb_product_type_vmall where product_type_vmall='"+id+"'";
		try{
			ResultSet rs = null;
			ResultSetMapper<Property_Entity> resultSetMapper = new ResultSetMapper<Property_Entity>();
			rs = ConnectDB.GetDataReturnResultSet(query);
			m = resultSetMapper.mapRersultSetToObject_singlerow(rs, Property_Entity.class);
			
		}catch(Exception ex){
			
		}
		
		//System.out.print(mem.toString());
		return m;
		
	}

	public int visivled_property(String strproductype,String strparentid,String strpropertyids,String visible) {
		int _result=0;
		try{
			String spname="sp_property_visibled_3option";
			String[] pfield={"f","strproductype","strparentid","strpropertyids","p_visible"};
			String[] ptype={"INT","VARCHAR","VARCHAR","VARCHAR","VARCHAR"};
			Object[] pvalues={"",strproductype,strparentid,strpropertyids,visible};
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
	public List<Property_Entity> get_allproperty() {
		List<Property_Entity> list=null;
		String query ="SELECT product_type_vmall, product_type_name, category_img, title_img, isvisible, isdelete, isstate, issyn "
				+ " FROM tb_product_type_vmall"
				+ " WHERE isvisible= 1";
		try{
			ResultSet rs = null;
		
			rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<Property_Entity> resultSetMapper = new ResultSetMapper<Property_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,Property_Entity.class);
			
		}catch(Exception ex){
			//System.out.println("PropertyDAL error:"+ex);
		}
		
		if(list==null){
			list=new ArrayList<Property_Entity>();
		}
		return list;
	}
	public List<Property_Entity> get_datagrip_byparentid(
			SearchPaggModel searchmodel) {
		List<Property_Entity> list=null;
		String query ="SELECT tb1.ID as id, tb1.product_type_sub_id, tb1.property_name,"
				+ " tb1.isvisible, tb1.isdelete, tb1.parent "
				+" FROM tb_property tb1 where tb1.parent='"+searchmodel.getCustom_value()+"' "
				+ " limit "+searchmodel.getOffset()+","+searchmodel.getRows();
		try{
			ResultSet rs = null;
			rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<Property_Entity> resultSetMapper = new ResultSetMapper<Property_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,Property_Entity.class);
			
		}catch(Exception ex){
			//System.out.println("PropertyDAL error:"+ex);
		}
		if(list==null){
			list=new ArrayList<Property_Entity>();
		}
		return list;
	}
	public int count_get_datagrip_byparentid(SearchPaggModel searchmodel) {
		int count=0;
		String query ="select count(*) as count  from (SELECT tb1.ID as id, tb1.product_type_sub_id, tb1.property_name, "
				+ " tb1.isvisible, tb1.isdelete, tb1.parent "
				+" FROM tb_property tb1  where tb1.parent='"+searchmodel.getCustom_value()+"')A";
				
		try{
			ResultSet rs = null;
			rs = ConnectDB.GetDataReturnResultSet(query);
			rs.first();
			count =rs.getInt("count");
		}catch(Exception ex){
			//System.out.println("PropertyDAL error:"+ex);
		}
		return count;
	}
	public int detele_single_property(String str_id) {
		int _result=0;
		try{
			String spname="sp_property_delete_single";
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
	public int delete_multi_property(String strproductype,String strparentid,String  strpropertyids) {
		int _result=0;
		try{
			String spname="sp_property_delete_multi_3option";
			String[] pfield={"f","strproductype","strparentid","strpropertyids"};
			String[] ptype={"INT","VARCHAR","VARCHAR","VARCHAR"};
			Object[] pvalues={"",strproductype,strparentid,strpropertyids};
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
	public List<Property_Entity> get_property_parent_byproducttypeid(
			String product_type_id) {
		List<Property_Entity> list=null;
		String query ="SELECT tb1.ID as id, tb1.product_type_sub_id, tb1.property_name, tb1.isvisible, tb1.isdelete, tb1.parent"
						+" FROM dbvmall.tb_property tb1 "
						+" where tb1.parent='0' and tb1.product_type_sub_id='"+product_type_id+"'";
		try{
			ResultSet rs = null;
		
			rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<Property_Entity> resultSetMapper = new ResultSetMapper<Property_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,Property_Entity.class);
			
		}catch(Exception ex){
			//System.out.println("PropertyDAL error:"+ex);
		}
		
		if(list==null){
			list=new ArrayList<Property_Entity>();
		}
		return list;
	}
	
}