package vn.vmall.Interface;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import vn.vmall.Entity.Member_Entity;
import vn.vmall.Entity.Product_Entity;



public interface MemberInterface {
	 List<Member_Entity> getAllMember();
	 Member_Entity getMemberByid(String id);
	 Member_Entity get_info_member(String email);
	 int update_info_member(String email,String fullname,String phone,String address);
	 int update_account(String str);
	 ArrayList<Product_Entity> get_product_save_later(String email);
	 ArrayList<Product_Entity> get_product_rating(String email);
	 String testgetstring();
	 int insert_customer(String email,String fullname);
}
