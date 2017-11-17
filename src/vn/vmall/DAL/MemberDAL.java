package vn.vmall.DAL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;


import vn.vmall.Entity.Member_Entity;
import vn.vmall.Entity.Product_Entity;
import vn.vmall.Helper.ResultSetMapper;

@Component
public class MemberDAL {
	/*@Autowired
	private ConnectDB condb;*/
	
	public List<Member_Entity> getAllMember(){
		
		List<Member_Entity> list= null;
		String query = "select id, email, pass, fullname, birthday, address, phone, address_delivery, register_date, confirm, gender, pass_forget, confirm_forget, status, syn_status"
				+ " from tb_member";
		try{
			ResultSet rs = null;
			ResultSetMapper<Member_Entity> resultSetMapper = new ResultSetMapper<Member_Entity>();
			rs = ConnectDB.GetDataReturnResultSet(query);
			
			list = resultSetMapper.mapRersultSetToObject(rs, Member_Entity.class);
			
			/*int i=0;
			while(rs.next()){
				i++;
				//System.out.println(i);
				Member_Entity item=new Member_Entity();
				item.setId(rs.getString("id"));
				item.setEmail(rs.getString("email"));
				item.setPass(rs.getString("pass"));
				item.setFullname(rs.getString("fullname"));
				item.setBirthday(rs.getString("birthday"));
				item.setAddress(rs.getString("address"));
				item.setPhone(rs.getString("phone"));
				item.setAddress_delivery(rs.getString("address_delivery"));
				item.setRegister_date(rs.getString("register_date"));
				item.setConfirm(rs.getInt("confirm"));
				item.setConfirmForget(rs.getInt("confirmForget"));
				item.setStatus(rs.getString("status"));
				item.setSyn_status(rs.getString("syn_status"));
				list.add(item);
			}*/
		}catch(Exception ex){
			
		}
		System.out.print(list.size());
		return list;
	}
public Member_Entity getMemberbyid(String id){
		//id="OD1604250000000030";
		Member_Entity mem= null;
		String query = "select id, email, pass "
				+ " from tb_member where id='20160805152654'";
		try{
			ResultSet rs = null;
			ResultSetMapper<Member_Entity> resultSetMapper = new ResultSetMapper<Member_Entity>();
			rs = ConnectDB.GetDataReturnResultSet(query);
			/*while(rs.next()){
				//System.out.println(rs.getString("pass"));
			}*/
			mem = resultSetMapper.mapRersultSetToObject_singlerow(rs, Member_Entity.class);
			
		}catch(Exception ex){
			
		}
		//System.out.print(mem.toString());
		return mem;
	}
	

	public String testgetstring(){
		String s="";
		String query = "select 10 as aa";
		try{
			ResultSet rs = null;
			rs = ConnectDB.GetDataReturnResultSet(query);
			while(rs.next()){
				s=rs.getString("aa");
			}
		}catch(Exception ex){
			
		}
		System.out.print(s);
	
		return s;
		
	}
	
	
	public static void main(String agrs[]){
		MemberDAL a=new MemberDAL();
		Member_Entity b=a.getMemberbyid("20160805152654");
		Gson gs =new Gson();
		System.out.print(gs.toJson(b));
	}
	public static Member_Entity get_info_member(String email){
		Member_Entity item = new Member_Entity();
		ResultSet rs = null;
		String query= "SELECT id,email,fullname,phone,address,birthday,gender "
				+ " FROM tb_member "
				+ " WHERE email='"+email+"';";
		try{
			ResultSetMapper<Member_Entity> resultSetMapper = new ResultSetMapper<Member_Entity>();
			rs = ConnectDB.GetDataReturnResultSet(query);
			item = resultSetMapper.mapRersultSetToObject_singlerow(rs, Member_Entity.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		return item;
	}
	public int update_info_member(String email,String fullname,String phone,String address) {
		int result=0;
		try {
			String spname = "tb_member_update";
			String[] pfield = { "p_email", "p_fullname", "p_phone", "p_address" };
			Object[] pvalues = { email, fullname, phone, address };
			
			result = ConnectDB.ExecBoFunction(spname, pfield, pvalues);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			result = -1;
		}
		return result;
	}
	public int update_account(Member_Entity item) {
		int result=-1;
		Map<String,Object> mapobj = new HashMap<String,Object>();
		try {
			String spname = "sp_change_info_account";
			String[] pfield = { "f", "p_email", "p_fullname", "p_birthday", "p_address", "p_gender", "p_passold",
					"p_passnew"

			};
			String[] ptype = { "INT", "VARCHAR", "VARCHAR", "VARCHAR", "VARCHAR", "VARCHAR", "VARCHAR", "VARCHAR" };
			Object[] pvalue = { "", item.getEmail(), item.getFullname(), item.getBirthday(), item.getAddress(),
					item.getGender(), item.getPass(), item.getPassnew() };
			int[] pdirec = { 1, 0, 0, 0, 0, 0, 0, 0 };
			mapobj = ConnectDB.ExecBoFunctionReturnList(spname, pfield, ptype, pvalue, pdirec);
			result = Integer.parseInt(mapobj.get("f").toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	public ArrayList<Product_Entity> get_product_save_later(String email) {
		ArrayList<Product_Entity> list = new ArrayList<Product_Entity>();
		
		try {
			String query = "SELECT tb1.email,tb1.customer_id,tb1.product_id,tb1.property,tb1.color "
					+" FROM tb_product_later tb1,tb_product tb2"
					+" WHERE tb1.customer_id = tb2.customer_id"
					+" AND tb1.product_id = tb2.product_id"
					+" AND tb1.email='"+email+"'";		
			ResultSet rs = null;			
			rs = ConnectDB.GetDataReturnResultSet(query);
			while(rs.next()){
				Product_Entity item =new Product_Entity();
				String cus_id = rs.getString("customer_id");
				String pro_id = rs.getString("product_id");
				item= ProductDAL.get_info_product(cus_id,pro_id);
				item.setProperty(rs.getString("property"));
				item.setProperty_value(ProductDAL.get_value_option("property", rs.getString("property")));
				item.setColor(rs.getString("color"));
				item.setColor_value(ProductDAL.get_value_option("color", rs.getString("color")));;
				item.setEmail(rs.getString("email"));
				list.add(item);
			}
		} catch (Exception ex) {

		}
		return list;
	}
	public ArrayList<Product_Entity> get_product_rating(String email) {
		ArrayList<Product_Entity> list = new ArrayList<Product_Entity>();
		
		try {
			String query = "SELECT tb1.email,tb1.customer_id,tb1.product_id,tb1.property,tb1.color,"
					+" tb2.product_name,tb2.product_price,tb2.product_image,tb2.product_des"
					+" FROM tb_product_later tb1,tb_product tb2"
					+" WHERE tb1.customer_id = tb2.customer_id"
					+" AND tb1.product_id = tb2.product_id"
					+" AND tb1.email='"+email+"'";		
			ResultSet rs = null;			
			rs = ConnectDB.GetDataReturnResultSet(query);
			while(rs.next()){
				Product_Entity item =new Product_Entity();
				String cus_id = rs.getString("customer_id");
				String pro_id = rs.getString("product_id");
				item= ProductDAL.get_info_product(cus_id,pro_id);
				item.setProperty(rs.getString("property"));
				item.setColor(rs.getString("color"));
				item.setEmail(rs.getString("email"));
				list.add(item);
			}
		} catch (Exception ex) {

		}
		return list;
	}
	public static int insert_customer(String email, String fullname) {
		int _result = 0;
		try {
			String spname = "tb_customer_insert";
			String[] pfield = { "f", "p_email", "p_fullname" };
			Object[] pvalues = { "", email, fullname };
			String[] ptype = { "INT", "VARCHAR", "VARCHAR" };
			int[] pdirec = { 1, 0, 0 };
			
			Map<String,Object> mapOfObjects = new HashMap<String,Object>();
			mapOfObjects = ConnectDB.ExecBoFunctionReturnList(spname, pfield, ptype, pvalues, pdirec);
			_result = Integer.parseInt(mapOfObjects.get("f").toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			_result = -1;
		}
		// //System.out.println(_result);
		return _result;
	}
}
