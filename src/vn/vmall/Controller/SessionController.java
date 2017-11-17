package vn.vmall.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import vn.vmall.DAL.ConnectDB;
import vn.vmall.Entity.ItemMember;
import vn.vmall.Entity.ItemSession;


@RestController
@RequestMapping(value = "SessionController")
public class SessionController {
	@RequestMapping(value = "/get_session", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ItemSession get_session(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		ItemSession item = new ItemSession();
		HttpSession session = request.getSession();
		item.setSessionid(session.getId());
		return item;
	}

	@RequestMapping(value = "set_session", method = RequestMethod.GET)
	@ResponseBody
	public void set_session(@RequestParam("session_id") String session_id, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ParseException {
		HttpSession session = request.getSession();
		session.setAttribute("sessionid", session_id);
	}
	@RequestMapping(value = "set_session_cart", method = RequestMethod.GET)
	@ResponseBody
	public void set_session_cart(@RequestParam("session_id") String session_id, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ParseException {
		HttpSession session = request.getSession();
		session.setAttribute("sessionid_cart", session_id);
	}
	@RequestMapping(value = "get_session_cart", method = RequestMethod.GET)
	@ResponseBody
	public String get_session_cart(
			@RequestParam("session_id") String session_id,
			HttpServletRequest request,			
			HttpServletResponse response) throws IOException, ParseException {
		HttpSession session = request.getSession();		
		String data =get_product_cart(session_id);
		return data;
	}
	@RequestMapping(value = "get_session_like", method = RequestMethod.GET)
	@ResponseBody
	public String get_session_like(HttpServletRequest request,
			@RequestParam("session_id") String session_id,
			HttpServletResponse response) throws IOException, ParseException {
		HttpSession session = request.getSession();
		String data =get_product_like(session_id,"LIKE");
		return data;
	}
	@RequestMapping(value = "get_user_login", method = RequestMethod.GET)
	@ResponseBody
	public ItemMember get_user_login(HttpServletRequest request,
			@RequestParam("session_id") String session_id,
			HttpServletResponse response) throws IOException, ParseException {
		
		String data ="-1";
		if(session_id.length()>0){
			data = get_user_login(session_id);
		}
		ItemMember item = new ItemMember();
		item.setEmail(data);
		return item;
	}
	@RequestMapping(value = "remove_user_login", method = RequestMethod.GET)
	@ResponseBody
	public ItemMember remove_user_login(HttpServletRequest request,
			@RequestParam("session_id") String session_id,
			HttpServletResponse response) throws IOException, ParseException {
		
		int rs =-1;
		if(session_id.length()>0){
			rs = remove_user_login(session_id);
		}
		ItemMember item = new ItemMember();
		item.setEmail(String.valueOf(rs));
		return item;
	}
	public static String get_session_id(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		String sessionid = "";
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("sessionid")) {
					sessionid = cookie.getValue();
				}
			}
		}
		return sessionid;
	}
	
	
	public static String get_user_login(String sessionid){
		String str="";
		try{
			String spname ="sp_get_session_user";
			String[]pf = {"p_session_id"};
			String[]pv = {sessionid};
			ResultSet rs = ConnectDB.ExecBoFunctionReturnResutlSet(spname, pf, pv);
			while(rs.next()){
				str =rs.getString("user");
			}
			if(str.length()==0){
				str = "-1";
			}
		}catch(Exception ex){
			
		}
		return str;
		
	}
	public static int remove_user_login(String sessionid){
		int rs=-1;
		try{
			String query ="delete from tb_session_user   where sessionid = '"+sessionid+"';";
			rs = ConnectDB.ExecUpdate(query);
			
		}catch(Exception ex){
			
		}
		return rs;
		
	}
	public static String get_product_like(String sessionid,String type){
		String str="";
		try{
			String spname ="sp_get_session_product";
			String[]pf = {"p_session_id","p_type"};
			String[]pv = {sessionid,type};
			ResultSet rs = ConnectDB.ExecBoFunctionReturnResutlSet(spname, pf, pv);
			while(rs.next()){
				str =rs.getString("num");
			}
			if(str.length()==0){
				str = "-1";
			}
		}catch(Exception ex){
			
		}
		return str;		
	}
	public static String get_product_cart(String sessionid){
		String str="";
		try{
			String spname ="sp_get_session_cart";
			String[]pf = {"p_session_id"};
			String[]pv = {sessionid};
			ResultSet rs = ConnectDB.ExecBoFunctionReturnResutlSet(spname, pf, pv);
			while(rs.next()){
				str =rs.getString("num");
			}
			if(str.length()==0){
				str = "-1";
			}
		}catch(Exception ex){
			
		}
		return str;		
	}
	public static void main(String[] args) {
		System.out.println(get_user_login("75F78C2DC8F577AE14CE286CBCA79DCD"));
	}
}
