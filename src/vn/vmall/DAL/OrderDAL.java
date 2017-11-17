package vn.vmall.DAL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import vn.vmall.Entity.OrderDetail_Entity;
import vn.vmall.Entity.Order_Entity;
import vn.vmall.Helper.EncrypterDecrypter;
import vn.vmall.Helper.ResultSetMapper;
import vn.vmall.Helper.SearchPaggModel;


@Component
public class OrderDAL {


	public List<Order_Entity> get_list_search_pagg(
			SearchPaggModel searchmodel) {
		List<Order_Entity> list=null;
		//String query="select * from tb_democrud limit "+searchmodel.getOffset()+","+searchmodel.getRows();
		try{
			ResultSet rs = null;
			String spname = "search_order";
			String[] pfield = { "p_offset", "p_rows", "p_col", "p_val" };
			Object[] pvalues = {searchmodel.getOffset(),searchmodel.getRows(),searchmodel.getCol(),searchmodel.getVal() };
			rs =ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			//rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<Order_Entity> resultSetMapper = new ResultSetMapper<Order_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,Order_Entity.class);
			
		}catch(Exception ex){
			//System.out.println("OrderDAL error:"+ex);
		}
		if(list==null){
			list=new ArrayList<Order_Entity>();
		}
		return list;
		
		
	}
	public int count_get_list_search_pagg(SearchPaggModel searchmodel){
		int count=0;
		try{
			ResultSet rs = null;
			String spname = "search_order_count_total";
			String[] pfield = { "p_col", "p_val" };
			Object[] pvalues = {searchmodel.getCol(),searchmodel.getVal()};
			rs =ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			//rs = ConnectDB.GetDataReturnResultSet(query);
			rs.first();
			count =rs.getInt("count");
			
		}catch(Exception ex){
			//System.out.println("OrderDAL error:"+ex);
		}
		return count;
	}
	public int add_update_Order(String type, Order_Entity d){
		int _result=0;
		try{
			String spname="sp_Order_insert_update";
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
	public static Order_Entity get_order_by_id(String id){
		Order_Entity m= new Order_Entity();
		String query ="SELECT * FROM(SELECT tb1.order_id, tb1.email, tb1.payment_method_id,tb1.order_date,tb2.fullname customer_name,tb1.address_delivery,"
				+ " tb2.phone "
				+ " FROM tb_order tb1, tb_member tb2 "
				+ "where order_id='"+id+"' "
				+ "AND tb1.email= tb2.email )A";
		try{
			ResultSet rs = null;
			ResultSetMapper<Order_Entity> resultSetMapper = new ResultSetMapper<Order_Entity>();
			rs = ConnectDB.GetDataReturnResultSet(query);
			m = resultSetMapper.mapRersultSetToObject_singlerow(rs, Order_Entity.class);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return m;
		
	}
	public static void main(String[] args) {
		Order_Entity m = get_order_by_id("OD1609050000000026");
		System.out.println(m.getAddress_delivery()+"_"+m.getPhone()+"_"+m.getEmail());
	}
	public int delete_Order(String str_id){
		int _result=0;
		try{
			String spname="sp_Order_delete";
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
	public int visivled_Order(String str_id,String visible) {
		int _result=0;
		try{
			String spname="sp_Order_visibled";
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
	public List<Order_Entity> get_allOrder() {
		List<Order_Entity> list=null;
		String query ="SELECT product_type_vmall, product_type_name, category_img, title_img, isvisible, isdelete, isstate, issyn "
				+ " FROM tb_product_type_vmall"
				+ " WHERE isvisible= 1";
		try{
			ResultSet rs = null;
		
			rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<Order_Entity> resultSetMapper = new ResultSetMapper<Order_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,Order_Entity.class);
			
		}catch(Exception ex){
			//System.out.println("OrderDAL error:"+ex);
		}
		
		if(list==null){
			list=new ArrayList<Order_Entity>();
		}
		return list;
	}

	public int set_order_status(String str_id,String status) {
		int _result=0;
		try{
			String spname="sp_set_order_status";
			String[] pfield={"f","p_str_id","p_status"};
			String[] ptype={"INT","VARCHAR","VARCHAR"};
			Object[] pvalues={"",str_id,status};
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
	
	public List<Order_Entity> get_order_detail(
			SearchPaggModel searchmodel) {
		List<Order_Entity> list=null;
		//String query="select * from tb_democrud limit "+searchmodel.getOffset()+","+searchmodel.getRows();
		try{
			ResultSet rs = null;
			String spname = "get_order_detail";
			String[] pfield = { "p_offset", "p_rows", "p_col", "p_val","p_order_id" };
			Object[] pvalues = {searchmodel.getOffset(),searchmodel.getRows(),searchmodel.getCol(),searchmodel.getVal(),searchmodel.getCustom_value() };
			rs =ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			ResultSetMapper<Order_Entity> resultSetMapper = new ResultSetMapper<Order_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,Order_Entity.class);
			
		}catch(Exception ex){

		}
		if(list==null){
			list=new ArrayList<Order_Entity>();
		}
		return list;
		
		
	}
	public int count_get_order_detail(SearchPaggModel searchmodel){
		int count=0;
		try{
			ResultSet rs = null;
			String spname = "get_order_detail_count_total";
			String[] pfield = { "p_col", "p_val","p_order_id" };
			Object[] pvalues = {searchmodel.getCol(),searchmodel.getVal(),searchmodel.getCustom_value()};
			rs =ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			//rs = ConnectDB.GetDataReturnResultSet(query);
			rs.first();
			count =rs.getInt("count");
			
		}catch(Exception ex){
			//System.out.println("OrderDAL error:"+ex);
		}
		return count;
	}
	public List<OrderDetail_Entity> get_order_detail_byid(String order_id) throws ClassNotFoundException, InstantiationException, SQLException {
		List<OrderDetail_Entity> list = new ArrayList<OrderDetail_Entity>();
		String query = "SELECT tb1.order_id, tb1.customer_id, tb1.product_id,tb1.quantity,tb1.price,tb1.amount,tb1.old_price,tb2.product_name ";
		query+=	" FROM tb_order_detail tb1,tb_product tb2 ";
		query+=" WHERE order_id='"+order_id+"' ";
		query+=" AND tb1.product_id= tb2.product_id";	
		ResultSet rs = null;
		rs= ConnectDB.GetDataReturnResultSet(query);
		ResultSetMapper<OrderDetail_Entity> resultSetMapper = new ResultSetMapper<OrderDetail_Entity>();
		list = resultSetMapper.mapRersultSetToObject(rs,OrderDetail_Entity.class);
		return list;
	}
	
}
