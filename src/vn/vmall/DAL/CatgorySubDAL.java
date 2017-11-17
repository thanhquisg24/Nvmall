package vn.vmall.DAL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import vn.vmall.Entity.CatgorySub_Entity;
import vn.vmall.Helper.ResultSetMapper;
import vn.vmall.Helper.SearchPaggModel;


@Component
public class CatgorySubDAL {


	public List<CatgorySub_Entity> get_list_search_pagg(
			SearchPaggModel searchmodel) {
		List<CatgorySub_Entity> list=null;
		//String query="select * from tb_democrud limit "+searchmodel.getOffset()+","+searchmodel.getRows();
		try{
			ResultSet rs = null;
			String spname = "search_catgory_sub";
			String[] pfield = { "p_offset", "p_rows", "p_col", "p_val","p_parent" };
			Object[] pvalues = {searchmodel.getOffset(),searchmodel.getRows(),searchmodel.getCol(),searchmodel.getVal(),searchmodel.getParent() };
			rs =ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			ResultSetMapper<CatgorySub_Entity> resultSetMapper = new ResultSetMapper<CatgorySub_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,CatgorySub_Entity.class);
			
		}catch(Exception ex){
			//System.out.println("CatgorySubDAL error:"+ex);
		}
		if(list==null){
			list=new ArrayList<CatgorySub_Entity>();
		}
		return list;

	}
	public static void main(String[] args) {
		
		CatgorySubDAL a = new CatgorySubDAL();
		SearchPaggModel searchmodel=new SearchPaggModel();
		searchmodel.setPage(1);
		searchmodel.setRows(10);
		searchmodel.setCol("");
		searchmodel.setVal("");
		searchmodel.setParent("001");
		List<CatgorySub_Entity> list = new ArrayList<CatgorySub_Entity>();
		list = a.get_list_search_pagg(searchmodel);
		System.out.println(new Gson().toJson(list));
		
	}
	public int count_get_list_search_pagg(SearchPaggModel searchmodel){
		int count=0;
		try{
			ResultSet rs = null;
			String spname = "search_catgorysub_count_total";
			String[] pfield = { "p_col", "p_val","p_parent" };
			Object[] pvalues = {searchmodel.getCol(),searchmodel.getVal(),searchmodel.getParent()};
			rs =ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			//rs = ConnectDB.GetDataReturnResultSet(query);
			rs.first();
			count =rs.getInt("count");
			
		}catch(Exception ex){
			//System.out.println("CatgorySubDAL error:"+ex);
		}
		return count;
	}
	
	public List<CatgorySub_Entity> get_list_search_pagg_sub(
			SearchPaggModel searchmodel) {
		List<CatgorySub_Entity> list=null;
		try{
			ResultSet rs = null;
			String spname = "search_catgory_sub_2";
			String[] pfield = { "p_offset", "p_rows", "p_col", "p_val","p_parent" };
			Object[] pvalues = {searchmodel.getOffset(),searchmodel.getRows(),searchmodel.getCol(),searchmodel.getVal(),searchmodel.getParent() };
			rs =ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			
			ResultSetMapper<CatgorySub_Entity> resultSetMapper = new ResultSetMapper<CatgorySub_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,CatgorySub_Entity.class);
			
		}catch(Exception ex){
		}
		if(list==null){
			list=new ArrayList<CatgorySub_Entity>();
		}
		return list;

	}
	public int count_get_list_search_pagg_sub(SearchPaggModel searchmodel){
		int count=0;
		try{
			ResultSet rs = null;
			String spname = "search_catgorysub_count_total_2";
			String[] pfield = { "p_col", "p_val","p_parent" };
			Object[] pvalues = {searchmodel.getCol(),searchmodel.getVal(),searchmodel.getParent()};
			rs =ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			rs.first();
			count =rs.getInt("count");
			
		}catch(Exception ex){
			//System.out.println("CatgorySubDAL error:"+ex);
		}
		return count;
	}
	
	public int add_update_catgory(String type, CatgorySub_Entity d){
		int _result=0;
		try{
			String spname="sp_catgory_sub_insert_update";
			String[] pfield={"f","p_type","p_product_type_id","p_product_type_name","p_category_img","p_title_img","p_parent","p_parent_ofsub","p_islink"};
			String[] ptype={"INT","VARCHAR","VARCHAR","VARCHAR","VARCHAR","VARCHAR","VARCHAR","VARCHAR","INT"};
			Object[] pvalues={"",type,d.getProduct_type_id(),d.getProduct_type_name(),d.getCategory_img(),d.getTitle_img(),d.getProduct_type_vmall(),d.getParent(),d.getIslink()};
			int[] pdirec={1,0,0,0,0,0,0,0,0};
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
	public CatgorySub_Entity get_catgory_by_id(String id){
		CatgorySub_Entity m= null;
		String query ="SELECT product_type_id,product_type_vmall, product_type_name, category_img, title_img, isvisible, isdelete, isstate, issyn,islink "
				+ " FROM tb_product_type_sub where product_type_id='"+id+"'";
		try{
			ResultSet rs = null;
			ResultSetMapper<CatgorySub_Entity> resultSetMapper = new ResultSetMapper<CatgorySub_Entity>();
			rs = ConnectDB.GetDataReturnResultSet(query);
			m = resultSetMapper.mapRersultSetToObject_singlerow(rs, CatgorySub_Entity.class);
			
		}catch(Exception ex){
		}
		return m;
	}
	public CatgorySub_Entity get_prodcutypesubfull_by_id(String id){
		CatgorySub_Entity m= null;
		String query ="SELECT ID,product_type_id,product_type_vmall, product_type_name, category_img, title_img, isvisible, isdelete, isstate, issyn, islink, store_name "
				+ " FROM tb_product_type_sub where ID='"+id+"'";
		try{
			ResultSet rs = null;
			ResultSetMapper<CatgorySub_Entity> resultSetMapper = new ResultSetMapper<CatgorySub_Entity>();
			rs = ConnectDB.GetDataReturnResultSet(query);
			m = resultSetMapper.mapRersultSetToObject_singlerow(rs, CatgorySub_Entity.class);
			
		}catch(Exception ex){
			
		}
		
		//System.out.print(mem.toString());
		return m;
		
	}
	
	
	public int delete_catgory(String str_id){
		int _result=0;
		try{
			String spname="sp_catgory_sub_delete";
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
	public int remove_sub_catgory(String str_id){
		int _result=0;
		try{
			String spname="sp_remove_sub_catgory";
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
	public int visivled_catgory(String str_id,String visible) {
		int _result=0;
		try{
			String spname="sp_catgory_sub_visibled";
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
	public List<CatgorySub_Entity> get_allcatgory() {
		List<CatgorySub_Entity> list=null;
		String query ="SELECT product_type_vmall, product_type_name, category_img, title_img, isvisible, isdelete, isstate, issyn "
				+ " FROM tb_product_type_vmall";
		try{
			ResultSet rs = null;
		
			rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<CatgorySub_Entity> resultSetMapper = new ResultSetMapper<CatgorySub_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,CatgorySub_Entity.class);
			
		}catch(Exception ex){
			//System.out.println("CatgorySubDAL error:"+ex);
		}
		
		if(list==null){
			list=new ArrayList<CatgorySub_Entity>();
		}
		return list;
	}
	public List<CatgorySub_Entity> get_subcatgory_byparent(String parentid) {
		List<CatgorySub_Entity> list=null;
		String query ="SELECT product_type_id, product_type_name, category_img, title_img, isvisible, isdelete, parent "
				+ " FROM tb_product_type_sub"
				+ " WHERE parent='"+parentid+"' "
				+ " AND isvisible=1;";
		try{
			ResultSet rs = null;
		
			rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<CatgorySub_Entity> resultSetMapper = new ResultSetMapper<CatgorySub_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,CatgorySub_Entity.class);
			
		}catch(Exception ex){
			//System.out.println("CatgorySubDAL error:"+ex);
		}
		
		if(list==null){
			list=new ArrayList<CatgorySub_Entity>();
		}
		return list;
	}
	public List<CatgorySub_Entity> get_subcatgory_byvmall(String vmallid) {
		List<CatgorySub_Entity> list=null;
		String query ="SELECT product_type_vmall,product_type_id, product_type_name, category_img, title_img, isvisible, isdelete, parent "
				+ " FROM tb_product_type_sub"
				+ " WHERE product_type_vmall='"+vmallid+"' "
				+ " AND parent='0' "
				+ " AND isvisible=1;";
		try{
			ResultSet rs = null;
		
			rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<CatgorySub_Entity> resultSetMapper = new ResultSetMapper<CatgorySub_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,CatgorySub_Entity.class);
			
		}catch(Exception ex){
			//System.out.println("CatgorySubDAL error:"+ex);
		}
		
		if(list==null){
			list=new ArrayList<CatgorySub_Entity>();
		}
		return list;
	}
	public int save_function_name(CatgorySub_Entity d) {
		int _result=0;
		try{
			String spname="sp_catgory_sub_save_function_name";
			String[] pfield={"f","p_id","p_store_name"};
			String[] ptype={"INT","VARCHAR","VARCHAR"};
			Object[] pvalues={"",d.getId(),d.getStore_name()};
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
	public List<CatgorySub_Entity> E_get_list_search_pagg(
			SearchPaggModel searchmodel) {
		List<CatgorySub_Entity> list=null;
		//String query="select * from tb_democrud limit "+searchmodel.getOffset()+","+searchmodel.getRows();
		try{
			ResultSet rs = null;
			String spname = "search_catgory_sub_E";
			String[] pfield = { "p_offset", "p_rows", "p_col", "p_val","p_parent" };
			Object[] pvalues = {searchmodel.getOffset(),searchmodel.getRows(),searchmodel.getCol(),searchmodel.getVal(),searchmodel.getParent() };
			rs =ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			ResultSetMapper<CatgorySub_Entity> resultSetMapper = new ResultSetMapper<CatgorySub_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,CatgorySub_Entity.class);
			
		}catch(Exception ex){
			//System.out.println("CatgorySubDAL error:"+ex);
		}
		if(list==null){
			list=new ArrayList<CatgorySub_Entity>();
		}
		return list;
	}
	public int E_count_get_list_search_pagg(SearchPaggModel searchmodel) {
		int count=0;
		try{
			ResultSet rs = null;
			String spname = "search_catgorysub_count_total_E";
			String[] pfield = { "p_col", "p_val","p_parent" };
			Object[] pvalues = {searchmodel.getCol(),searchmodel.getVal(),searchmodel.getParent()};
			rs =ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			rs.first();
			count =rs.getInt("count");
			
		}catch(Exception ex){
			//System.out.println("CatgorySubDAL error:"+ex);
		}
		return count;
	}
	public List<CatgorySub_Entity> E_get_list_search_pagg_sub(
			SearchPaggModel searchmodel) {
		List<CatgorySub_Entity> list=null;
		try{
			ResultSet rs = null;
			String spname = "search_catgory_sub_2_E";
			String[] pfield = { "p_offset", "p_rows", "p_col", "p_val","p_parent" };
			Object[] pvalues = {searchmodel.getOffset(),searchmodel.getRows(),searchmodel.getCol(),searchmodel.getVal(),searchmodel.getParent() };
			rs =ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			
			ResultSetMapper<CatgorySub_Entity> resultSetMapper = new ResultSetMapper<CatgorySub_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,CatgorySub_Entity.class);
			
		}catch(Exception ex){
		}
		if(list==null){
			list=new ArrayList<CatgorySub_Entity>();
		}
		return list;

	}
	public int E_count_get_list_search_pagg_sub(SearchPaggModel searchmodel){
		int count=0;
		try{
			ResultSet rs = null;
			String spname = "search_catgorysub_count_total_2_E";
			String[] pfield = { "p_col", "p_val","p_parent" };
			Object[] pvalues = {searchmodel.getCol(),searchmodel.getVal(),searchmodel.getParent()};
			rs =ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			rs.first();
			count =rs.getInt("count");
			
		}catch(Exception ex){
			//System.out.println("CatgorySubDAL error:"+ex);
		}
		return count;
	}
}
