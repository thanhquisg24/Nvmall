package vn.vmall.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import vn.vmall.Entity.Brand_Entity;
import vn.vmall.Entity.Brand_productype_detail_Entity;
import vn.vmall.Entity.Catgory_Entity;
import vn.vmall.Entity.Property_Entity;
import vn.vmall.Helper.EncrypterDecrypter;
import vn.vmall.Helper.ResultSetMapper;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.SessionModel.UserSessionModel;


@Component
public class BrandDAL {


	public List<Brand_Entity> get_list_search_pagg(
			SearchPaggModel searchmodel) {
		List<Brand_Entity> list=null;
		//String query="select * from tb_democrud limit "+searchmodel.getOffset()+","+searchmodel.getRows();
		try{
			ResultSet rs = null;
			String spname = "search_brand";
			String[] pfield = { "p_offset", "p_rows", "p_col", "p_val" };
			Object[] pvalues = {searchmodel.getOffset(),searchmodel.getRows(),searchmodel.getCol(),searchmodel.getVal() };
			rs =ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			ResultSetMapper<Brand_Entity> resultSetMapper = new ResultSetMapper<Brand_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,Brand_Entity.class);
			
		}catch(Exception ex){
			//System.out.println("BranchDAL error:"+ex);
		}
		if(list==null){
			list=new ArrayList<Brand_Entity>();
		}
		return list;
		
	}
	public int count_get_list_search_pagg(SearchPaggModel searchmodel){
		int count=0;
		try{
			ResultSet rs = null;
			String spname = "search_brand_count_total";
			String[] pfield = { "p_col", "p_val" };
			Object[] pvalues = {searchmodel.getCol(),searchmodel.getVal()};
			rs =ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			//rs = ConnectDB.GetDataReturnResultSet(query);
			rs.first();
			count =rs.getInt("count");
			
		}catch(Exception ex){
			//System.out.println("BranchDAL error:"+ex);
		}
		return count;
	}
	public int add_update_branch(String type, Brand_Entity d){
		int _result=0;
		try{
			String spname="sp_brand_insert_update";
			String[] pfield={"f","p_type","p_id","p_name","p_image","p_country","p_editor"};
			String[] ptype={"INT","VARCHAR","VARCHAR","VARCHAR","VARCHAR","VARCHAR","VARCHAR"};
			Object[] pvalues={"",type,d.getId(),d.getName(),d.getImage(),d.getCountry(),d.getCreate_user()};
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
	public Brand_Entity get_branch_by_id(String id){
		Brand_Entity m= null;
		String query ="SELECT id, name, image, country, create_date, modify_date, create_user, modify_user, isvisible, isdelete "
				+ " FROM brand where id='"+id+"'";
		try{
			ResultSet rs = null;
			ResultSetMapper<Brand_Entity> resultSetMapper = new ResultSetMapper<Brand_Entity>();
			rs = ConnectDB.GetDataReturnResultSet(query);
			m = resultSetMapper.mapRersultSetToObject_singlerow(rs, Brand_Entity.class);
		}catch(Exception ex){
			
		}
		
		//System.out.print(mem.toString());
		return m;
		
	}
	
	public int visivled_brand(String str_id,String visible) {
		int _result=0;
		try{
			String spname="sp_brand_visibled";
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
	public List<Brand_Entity> get_allbranh() {
		List<Brand_Entity> list=null;
		String query ="SELECT product_type_vmall, product_type_name, category_img, title_img, isvisible, isdelete, isstate, issyn "
				+ " FROM tb_product_type_vmall"
				+ " WHERE isvisible= 1";
		try{
			ResultSet rs = null;
		
			rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<Brand_Entity> resultSetMapper = new ResultSetMapper<Brand_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,Brand_Entity.class);
			
		}catch(Exception ex){
			//System.out.println("BranchDAL error:"+ex);
		}
		
		if(list==null){
			list=new ArrayList<Brand_Entity>();
		}
		return list;
	}
	public List<Brand_Entity> get_datagrip_byproducttypeid(
			SearchPaggModel searchmodel) {
		List<Brand_Entity> list=null;
		String query ="SELECT id, name, image, country, product_type_id,"
				+ " create_date, modify_date, create_user, modify_user, "
				+ " isvisible, isdelete"
				+" FROM branch where product_type_id='"+searchmodel.getCustom_value()+"' "
				+ " limit "+searchmodel.getOffset()+","+searchmodel.getRows();
		try{
			ResultSet rs = null;
			rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<Brand_Entity> resultSetMapper = new ResultSetMapper<Brand_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,Brand_Entity.class);
			
		}catch(Exception ex){
			//System.out.println("BranchDAL error:"+ex);
		}
		if(list==null){
			list=new ArrayList<Brand_Entity>();
		}
		return list;
	}
	public int count_get_datagrip_byproducttypeid(SearchPaggModel searchmodel) {
		int count=0;
		String query ="select count(*) as count  from (SELECT id, name, image, country, product_type_id,"
				+ " create_date, modify_date, create_user, modify_user, "
				+ " isvisible, isdelete"
				+" FROM branch where product_type_id='"+searchmodel.getCustom_value()+"')A";
				
		try{
			ResultSet rs = null;
			rs = ConnectDB.GetDataReturnResultSet(query);
			rs.first();
			count =rs.getInt("count");
		}catch(Exception ex){
			//System.out.println("BranchDAL error:"+ex);
		}
		return count;
	}
	public int detele_single_branch(String str_id) {
		int _result=0;
		try{
			String spname="sp_branch_delete_single";
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
	public int delete_multi_branch(String str_id) {
		int _result=0;
		try{
			String spname="sp_brand_delete_multi";
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
	public List<Brand_productype_detail_Entity> get_branddetail_byproduct_type_id(
			String product_type_id) {
		List<Brand_productype_detail_Entity> list=null;
		String query ="SELECT tb1.ID, tb1.product_type_id, tb1.brand_id,tb1.isvisible, tb1.isdelete,"
				+ " tb2.name ,tb2.country,tb2.image"
				+ " FROM tb_product_type_brand tb1,brand tb2 where tb1.brand_id=tb2.id and tb1.product_type_id='"+product_type_id+"'";
		try{
			ResultSet rs = null;
			rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<Brand_productype_detail_Entity> resultSetMapper = new ResultSetMapper<Brand_productype_detail_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,Brand_productype_detail_Entity.class);
		}catch(Exception ex){	
		}
		if(list==null){
			list=new ArrayList<Brand_productype_detail_Entity>();
		}
		return list;
	}
	public int do_append_brand(String product_type_id,
			List<Brand_Entity> listbrand,UserSessionModel user) throws InstantiationException, ClassNotFoundException, SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatementInsert = null;
		String insertTableSQL = "INSERT INTO tb_product_type_brand"
				+ "(`product_type_id`,`brand_id`,`create_user`,`modify_user`,`isvisible`,`isdelete`,`create_date`,`modify_date`) VALUES"
				+ "(?,?,?,?,1,0,now(),now())";
		try {
		dbConnection=ConnectDB.getconnection();
		dbConnection.setAutoCommit(false);
		preparedStatementInsert = dbConnection.prepareStatement(insertTableSQL);
			for(int i=0;i<listbrand.size();i++){
				//System.out.println(listLevel.get(i).getId());
				preparedStatementInsert.setString(1,product_type_id);
				preparedStatementInsert.setString(2,listbrand.get(i).getId());
				preparedStatementInsert.setString(3,user.getId());
				preparedStatementInsert.setString(4,user.getId());
				preparedStatementInsert.addBatch();
			}
			preparedStatementInsert.executeBatch();
			dbConnection.commit();
			System.out.println("do_append_brand preparedStatementInsert Done!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			dbConnection.rollback();
			return -1;
		} finally {
			if (preparedStatementInsert != null) {
				preparedStatementInsert.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return 0;
	}
	public int delete_multi_branddetail(String str_id, String product_type_id) {
		int _result=0;
		try{
			String spname="sp_branddetail_delete_multi";
			String[] pfield={"f","p_str_id","p_product_type_id"};
			String[] ptype={"INT","VARCHAR","VARCHAR"};
			Object[] pvalues={"",str_id,product_type_id};
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
	public static void main(String[] args) {
		System.out.println(new Gson().toJson(get_brand_by_vmall("002")));
	}
	public static ArrayList<Catgory_Entity> get_list_brand(){
		ArrayList<Catgory_Entity> list = new ArrayList<Catgory_Entity>();
		String query_name = "020";
		String[] pf = {  };
		String[] pv = {  };
		try {
			ResultSet rs = null;
			rs = ConnectDB.GetDataDirecReturnResultSet(query_name, pf, pv);
			while (rs.next()) {
				String product_type_vmall = rs.getString("product_type_vmall");
				String product_type_name = rs.getString("product_type_name");
				String brand_id = rs.getString("brand_id");
				String brand_name = rs.getString("brand_name");				
				if(!check_exists_brand(list,product_type_vmall)){
					ArrayList<Brand_Entity> list_brand = new ArrayList<Brand_Entity>();
					Catgory_Entity item_a = new Catgory_Entity();
					item_a.setList_branch(list_brand);
					item_a.setProduct_type_name(product_type_name);
					item_a.setProduct_type_vmall(product_type_vmall);
					list.add(item_a);
				}			
				
				add_exists_brand(list,product_type_vmall,brand_id,brand_name);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	public static boolean check_exists_brand(ArrayList<Catgory_Entity> list, String cate )
	{
		for(Catgory_Entity i : list){
			if(i.getProduct_type_vmall().equals(cate)){
				return true;
			}
		}
		return false;
	}
	public static void add_exists_brand(ArrayList<Catgory_Entity> list,String product_type_vmall, String brand_id,String brand_name )
	{
		for(Catgory_Entity i : list){
			if(i.getProduct_type_vmall().equals(product_type_vmall)){
				Brand_Entity item = new Brand_Entity();
				item.setId(EncrypterDecrypter.encodeCategory(brand_id));
				item.setName(brand_name);
				i.getList_branch().add(item);
				break;
			}
		}		
	}
	public static ArrayList<Brand_Entity> get_brand_by_vmall(String type_vmall){
		
		type_vmall = EncrypterDecrypter.decodeCategory(type_vmall);
		ArrayList<Brand_Entity> list_brand = new ArrayList<Brand_Entity>();
		
		String query_name = "022";
		String[] pf = {"p_type_vmall"};
		String[] pv = {type_vmall};
		try {
			ResultSet rs = null;
			rs = ConnectDB.GetDataDirecReturnResultSet(query_name, pf, pv);
			while (rs.next()) {
				Brand_Entity brand = new Brand_Entity();
				brand.setId(EncrypterDecrypter.encodeCategory(rs.getString("brand_id")));
				brand.setName(rs.getString("brand_name"));
				brand.setImage(rs.getString("image"));
				list_brand.add(brand);
				}
			
			}catch(Exception e){
				e.printStackTrace();
			}
		return list_brand;
	}
}
