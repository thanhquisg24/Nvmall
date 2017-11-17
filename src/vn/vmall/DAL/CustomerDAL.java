package vn.vmall.DAL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import vn.vmall.Entity.Customer_Entity;
import vn.vmall.Helper.ResultSetMapper;
import vn.vmall.Helper.SearchPaggModel;


@Component
public class CustomerDAL {


	public List<Customer_Entity> get_list_search_pagg(
			SearchPaggModel searchmodel) {
		List<Customer_Entity> list=null;
		//String query="select * from tb_democrud limit "+searchmodel.getOffset()+","+searchmodel.getRows();
		try{
			ResultSet rs = null;
			String spname = "search_customer";
			String[] pfield = { "p_offset", "p_rows", "p_col", "p_val" };
			Object[] pvalues = {searchmodel.getOffset(),searchmodel.getRows(),searchmodel.getCol(),searchmodel.getVal() };
			rs =ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			//rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<Customer_Entity> resultSetMapper = new ResultSetMapper<Customer_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,Customer_Entity.class);
			
		}catch(Exception ex){
			//System.out.println("customerDAL error:"+ex);
		}
		if(list==null){
			list=new ArrayList<Customer_Entity>();
		}
		return list;
		
		
	}
	public int count_get_list_search_pagg(SearchPaggModel searchmodel){
		int count=0;
		try{
			ResultSet rs = null;
			String spname = "search_customer_count_total";
			String[] pfield = { "p_col", "p_val" };
			Object[] pvalues = {searchmodel.getCol(),searchmodel.getVal()};
			rs =ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			rs.first();
			count =rs.getInt("count");
			
		}catch(Exception ex){
			//System.out.println("customerDAL error:"+ex);
		}
		return count;
	}
	public int add_update_customer(String type, Customer_Entity d){
		int _result=0;
		try{
			String spname="sp_customer_insert_update";
			String[] pfield={"f","p_type","p_product_type_vmall","p_product_type_name","p_category_img","p_title_img"};
			String[] ptype={"INT","VARCHAR","VARCHAR","VARCHAR","VARCHAR","VARCHAR"};
			//Object[] pvalues={"",type,d.getProduct_type_vmall(),d.getProduct_type_name(),d.getCategory_img(),d.getTitle_img()};
			Object[] pvalues={};
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
	public Customer_Entity get_customer_by_id(String id){
		Customer_Entity m= null;
		String query ="SELECT product_type_vmall, product_type_name, category_img, title_img, isvisible, isdelete, isstate, issyn "
				+ " FROM tb_product_type_vmall where product_type_vmall='"+id+"'";
		try{
			ResultSet rs = null;
			ResultSetMapper<Customer_Entity> resultSetMapper = new ResultSetMapper<Customer_Entity>();
			rs = ConnectDB.GetDataReturnResultSet(query);
			m = resultSetMapper.mapRersultSetToObject_singlerow(rs, Customer_Entity.class);
			
		}catch(Exception ex){
			
		}
		
		//System.out.print(mem.toString());
		return m;
		
	}
	public int delete_customer(String str_id){
		int _result=0;
		try{
			String spname="sp_customer_delete";
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
	public int visivled_customer(String str_id,String visible) {
		int _result=0;
		try{
			String spname="sp_customer_visibled";
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
	public List<Customer_Entity> get_allcustomer() {
		List<Customer_Entity> list=null;
		String query ="SELECT id, email, shop_name, domain, project_name "
				+ " FROM tb_customer";
		try{
			ResultSet rs = null;
		
			rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<Customer_Entity> resultSetMapper = new ResultSetMapper<Customer_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,Customer_Entity.class);
			
		}catch(Exception ex){
			//System.out.println("customerDAL error:"+ex);
		}
		
		if(list==null){
			list=new ArrayList<Customer_Entity>();
		}
		//System.out.println(list.size());
		return list;
	}
	public int do_approve(String str_id, String approve) {
		int _result=0;
		try{
			String spname="sp_customer_approve";
			String[] pfield={"f","p_str_id","p_approve"};
			String[] ptype={"INT","VARCHAR","INT"};
			Object[] pvalues={"",str_id,approve};
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
