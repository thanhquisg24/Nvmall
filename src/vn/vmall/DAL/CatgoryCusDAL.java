package vn.vmall.DAL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import vn.vmall.Entity.CatgoryCus_Entity;
import vn.vmall.Helper.ResultSetMapper;
import vn.vmall.Helper.SearchPaggModel;


@Component
public class CatgoryCusDAL {


	public List<CatgoryCus_Entity> get_list_search_pagg(
			SearchPaggModel searchmodel) {
		List<CatgoryCus_Entity> list=null;
		//String query="select * from tb_democrud limit "+searchmodel.getOffset()+","+searchmodel.getRows();
		try{
			ResultSet rs = null;
			String spname = "search_catgory_customer";
			String[] pfield = { "p_offset", "p_rows", "p_col", "p_val" };
			Object[] pvalues = {searchmodel.getOffset(),searchmodel.getRows(),searchmodel.getCol(),searchmodel.getVal() };
			rs =ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			//rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<CatgoryCus_Entity> resultSetMapper = new ResultSetMapper<CatgoryCus_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,CatgoryCus_Entity.class);
	
		}catch(Exception ex){
			//System.out.println("CatgoryCusDAL error:"+ex);
		}
		if(list==null){
			list=new ArrayList<CatgoryCus_Entity>();
		}
		return list;
		
		
	}
	public int count_get_list_search_pagg(SearchPaggModel searchmodel){
		int count=0;
		try{
			ResultSet rs = null;
			String spname = "search_catgory_customer_count_total";
			String[] pfield = { "p_col", "p_val" };
			Object[] pvalues = {searchmodel.getCol(),searchmodel.getVal()};
			rs =ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			//rs = ConnectDB.GetDataReturnResultSet(query);
			rs.first();
			count =rs.getInt("count");
			
		}catch(Exception ex){
			//System.out.println("CatgoryCusDAL error:"+ex);
		}
		return count;
	}
	public int add_update_catgory(String type, CatgoryCus_Entity d){
		int _result=0;
		try{
			String spname="sp_catgory_customer_insert_update";
			String[] pfield={"f","p_type","p_cus_id","p_product_type_id","p_product_type_name",
					"p_group_cate","p_cate","p_cate_img","p_img_title"};
			String[] ptype={"INT","VARCHAR","VARCHAR","VARCHAR","VARCHAR","VARCHAR","VARCHAR","VARCHAR"};
			Object[] pvalues={"",type,d.getCustomer_id(),d.getProduct_type_id(),d.getProduct_type_name(),
					d.getGroup_category_id(),d.getCategory_id(),d.getCategory_img(),d.getTitle_img()};
			int[] pdirec={1,0,0,0,0,
					0,0,0,0};
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
	public CatgoryCus_Entity get_catgory_by_id(String id){
		CatgoryCus_Entity m= null;
		String[] arr_id = id.split("_");
		String cus_id=arr_id[0];
		String type_id=arr_id[1];
		String query ="SELECT tb2.product_type_vmall as product_id_vmall,tb1.customer_id, tb1.product_type_id,tb1.product_type_name,"
		+ "tb1.group_category_id, tb1.category_id,tb1.category_img,tb1.title_img,tb1.isvisible, tb1.isdelete  "
		+ " FROM tb_product_type tb1,tb_product_type_sub tb2 "
		+ " WHERE tb1.customer_id='"+cus_id+"' And tb1.product_type_id = '"+type_id+"'"
		+ " And tb1.category_id=tb2.product_type_id";
		try{
			ResultSet rs = null;
			ResultSetMapper<CatgoryCus_Entity> resultSetMapper = new ResultSetMapper<CatgoryCus_Entity>();
			rs = ConnectDB.GetDataReturnResultSet(query);
			m = resultSetMapper.mapRersultSetToObject_singlerow(rs, CatgoryCus_Entity.class);
			m.setProduct_id_vmall(rs.getString("product_id_vmall"));
			System.out.println(m.getProduct_id_vmall());
		}catch(Exception ex){
			
		}
		
		//System.out.print(mem.toString());
		return m;
		
	}
	public int delete_catgory(String str_id){
		int _result=0;
		String[] arr_id = str_id.split("_");
		String cus_id=arr_id[0];
		String type_id=arr_id[1];
		try{
			String spname="sp_catgory_cus_delete";
			String[] pfield={"f","p_cus_id","p_type_id"};
			String[] ptype={"INT","VARCHAR","VARCHAR"};
			Object[] pvalues={"",cus_id,type_id};
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
	public int visivled_catgory(String str_id,String visible) {
		int _result=0;
		String[] arr_id = str_id.split("_");
		String cus_id=arr_id[0];
		String type_id=arr_id[1];
		try{
			String spname="sp_catgory_cus_visibled";
			String[] pfield={"f","p_cus_id","p_type_id","p_visible"};
			String[] ptype={"INT","VARCHAR","VARCHAR","INT"};
			Object[] pvalues={"",cus_id,type_id,Integer.parseInt(visible)};
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
	public List<CatgoryCus_Entity> get_allcatgory() {
		List<CatgoryCus_Entity> list=null;
		String query ="SELECT product_type_vmall, product_type_name, category_img, title_img, isvisible, isdelete, isstate, issyn "
				+ " FROM tb_product_type_vmall";
		try{
			ResultSet rs = null;
		
			rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<CatgoryCus_Entity> resultSetMapper = new ResultSetMapper<CatgoryCus_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,CatgoryCus_Entity.class);
			
		}catch(Exception ex){
			//System.out.println("CatgoryDAL error:"+ex);
		}
		
		if(list==null){
			list=new ArrayList<CatgoryCus_Entity>();
		}
		return list;
	}
	public int approve_catgory(String str_id){
		int _result=0;
		String[] arr_id = str_id.split("_");
		String cus_id=arr_id[0];
		String type_id=arr_id[1];
		try{
			String spname="sp_catgory_cus_approve";
			String[] pfield={"f","p_cus_id","p_type_id"};
			String[] ptype={"INT","VARCHAR","VARCHAR"};
			Object[] pvalues={"",cus_id,type_id};
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
}
