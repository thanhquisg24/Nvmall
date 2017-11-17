package vn.vmall.DAL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import vn.vmall.Entity.Color_Entity;
import vn.vmall.Helper.ResultSetMapper;
import vn.vmall.Helper.SearchPaggModel;


@Component
public class ColorDAL {


	public List<Color_Entity> get_list_search_pagg(
			SearchPaggModel searchmodel) {
		List<Color_Entity> list=null;
		//String query="select * from tb_democrud limit "+searchmodel.getOffset()+","+searchmodel.getRows();
		try{
			ResultSet rs = null;
			String spname = "search_color";
			String[] pfield = { "p_offset", "p_rows", "p_col", "p_val" };
			Object[] pvalues = {searchmodel.getOffset(),searchmodel.getRows(),searchmodel.getCol(),searchmodel.getVal() };
			rs =ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			//rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<Color_Entity> resultSetMapper = new ResultSetMapper<Color_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,Color_Entity.class);
			
		}catch(Exception ex){
			//System.out.println("colorDAL error:"+ex);
		}
		if(list==null){
			list=new ArrayList<Color_Entity>();
		}
		return list;
		
		
	}
	public int count_get_list_search_pagg(SearchPaggModel searchmodel){
		int count=0;
		try{
			ResultSet rs = null;
			String spname = "search_color_count_total";
			String[] pfield = { "p_col", "p_val" };
			Object[] pvalues = {searchmodel.getCol(),searchmodel.getVal()};
			rs =ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			//rs = ConnectDB.GetDataReturnResultSet(query);
			rs.first();
			count =rs.getInt("count");
			
		}catch(Exception ex){
			//System.out.println("colorDAL error:"+ex);
		}
		return count;
	}
	public int add_update_color(String type, Color_Entity d){
		int _result=0;
		try{
			String spname="sp_color_insert_update";
			String[] pfield={"f","p_id","p_type","p_sel_type","p_colorid","p_colorname","p_image","p_product_type_id"};
			String[] ptype={"INT","VARCHAR","VARCHAR","VARCHAR","VARCHAR","VARCHAR","VARCHAR","VARCHAR"};
			Object[] pvalues={"",d.getId(),type,d.getType(),d.getColor(),d.getColor_name(),d.getImg(),d.getProduct_type_sub_id()};
			int[] pdirec={1,0,0,0,0,0,0,0};
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
	public Color_Entity get_color_by_id(String id){
		Color_Entity m= null;
		String query ="SELECT product_type_vmall, product_type_name, category_img, title_img, isvisible, isdelete, isstate, issyn "
				+ " FROM tb_product_type_vmall where product_type_vmall='"+id+"'";
		try{
			ResultSet rs = null;
			ResultSetMapper<Color_Entity> resultSetMapper = new ResultSetMapper<Color_Entity>();
			rs = ConnectDB.GetDataReturnResultSet(query);
			m = resultSetMapper.mapRersultSetToObject_singlerow(rs, Color_Entity.class);
			
		}catch(Exception ex){
			
		}
		
		//System.out.print(mem.toString());
		return m;
		
	}

	public int visivled_color(String str_id,String visible) {
		int _result=0;
		try{
			String spname="sp_color_visibled";
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
	public List<Color_Entity> get_allbranh() {
		List<Color_Entity> list=null;
		String query ="SELECT product_type_vmall, product_type_name, category_img, title_img, isvisible, isdelete, isstate, issyn "
				+ " FROM tb_product_type_vmall"
				+ " WHERE isvisible= 1";
		try{
			ResultSet rs = null;
		
			rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<Color_Entity> resultSetMapper = new ResultSetMapper<Color_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,Color_Entity.class);
			
		}catch(Exception ex){
			//System.out.println("colorDAL error:"+ex);
		}
		
		if(list==null){
			list=new ArrayList<Color_Entity>();
		}
		return list;
	}
	public List<Color_Entity> get_datagrip_byproducttypeid(
			SearchPaggModel searchmodel) {
		List<Color_Entity> list=null;
		String query ="SELECT tb1.id, tb1.product_type_sub_id,tb2.product_type_name,"
				+ " tb1.color_name,tb1.color,tb1.type,tb1.img, "
				+ " tb1.isvisible, tb1.isdelete"
				+" FROM tb_color tb1, tb_product_type_sub tb2"
				+ " where product_type_id='"+searchmodel.getCustom_value()+"' "
				+ " AND tb1.product_type_sub_id = tb2.product_type_id"
				+ " AND tb1.isdelete = 0 "
				+ " limit "+searchmodel.getOffset()+","+searchmodel.getRows();
		try{
			ResultSet rs = null;
			rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<Color_Entity> resultSetMapper = new ResultSetMapper<Color_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,Color_Entity.class);
			
		}catch(Exception ex){
			//System.out.println("colorDAL error:"+ex);
		}
		if(list==null){
			list=new ArrayList<Color_Entity>();
		}
		return list;
	}
	public int count_get_datagrip_byproducttypeid(SearchPaggModel searchmodel) {
		int count=0;
		String query ="select count(*) as count  from (SELECT tb1.id, tb1.product_type_sub_id,tb2.product_type_name,"
				+ " tb1.color_name,tb1.color,tb1.type,tb1.img, "
				+ " tb1.isvisible, tb1.isdelete"
				+" FROM tb_color tb1, tb_product_type_sub tb2"
				+ " where product_type_id='"+searchmodel.getCustom_value()+"' "
				+ " AND tb1.product_type_sub_id = tb2.product_type_id)A";
				
		try{
			ResultSet rs = null;
			rs = ConnectDB.GetDataReturnResultSet(query);
			rs.first();
			count =rs.getInt("count");
		}catch(Exception ex){
			//System.out.println("colorDAL error:"+ex);
		}
		return count;
	}
	public int detele_single_color(String str_id) {
		int _result=0;
		try{
			String spname="sp_color_delete_single";
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
	public int delete_multi_color(String str_id) {
		int _result=0;
		try{
			String spname="sp_color_delete_multi";
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
