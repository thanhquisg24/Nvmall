package vn.vmall.Imp;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import vn.vmall.DAL.MemberDAL;
import vn.vmall.Entity.Member_Entity;
import vn.vmall.Entity.Product_Entity;
import vn.vmall.Interface.MemberInterface;

@Component(value="MemberImp")
public class MemberImp implements MemberInterface {

	@Autowired
	private MemberDAL memDAL;
	@Override
	public List<Member_Entity> getAllMember() {
		//memDAL=new MemberDAL();
		return memDAL.getAllMember();
	
	}
	@Override
	public String testgetstring() {
		// TODO Auto-generated method stub
		return memDAL.testgetstring();
	}
	@Override
	public Member_Entity getMemberByid(String id) {
		// TODO Auto-generated method stub
		return memDAL.getMemberbyid(id);
	}
	@Override
	public Member_Entity get_info_member(String email) {
		
		return memDAL.get_info_member(email);
	}
	
	public int update_info_member(String email,String fullname,String phone,String address) {
		
		return memDAL.update_info_member(email,fullname,phone,address);
	}
	public int update_account(String str){
		int result=0;
	
		Member_Entity item = new Member_Entity();
		item = new Gson().fromJson(str, Member_Entity.class);
		result= memDAL.update_account(item);
		
		return result;
	}
	public ArrayList<Product_Entity> get_product_save_later(String email){
		 
		 return memDAL.get_product_save_later(email);
	 }
	public ArrayList<Product_Entity> get_product_rating(String email){
		 
		 return memDAL.get_product_rating(email);
	 }
	@Override
	public int insert_customer(String email, String fullname) {
		// TODO Auto-generated method stub
		return memDAL.insert_customer(email,fullname);
	}
	
}
