package vn.vmall.AdminController;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.vmall.Helper.ErrorMessageModel;
import vn.vmall.SessionModel.UserSessionModel;




@RestController
@RequestMapping(value="ad/loginController")
public class ad_loginController {
	public final static String KEY_SESSION_USER="user";
	
	 @RequestMapping(value="/do_login",
				method=RequestMethod.POST,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel do_login(HttpSession session){
		 UserSessionModel u=new UserSessionModel();
		 u.setId("u1");
		 u.setName("name of u1");
		 u.setRole("ROLE_ADMIN");
		 u.setDate_login( new Date());
		 session.setAttribute(KEY_SESSION_USER, u);
		 ErrorMessageModel m=new ErrorMessageModel();
		 m.setF(0);
		 m.setMessage("login success!");
		return m;
		 
	 }
	 
	 @RequestMapping(value="/do_logout",
				method=RequestMethod.POST,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel do_logout(HttpSession session){
		 session.invalidate();
		 ErrorMessageModel m=new ErrorMessageModel();
		 m.setF(0);
		 m.setMessage("logout success!");
		 return m;
		 
	 }
	 
	 @RequestMapping(value="/get_login",
				method=RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public UserSessionModel get_login(HttpSession session){
		 //
		 UserSessionModel u1=new UserSessionModel();
		 u1.setId("u1");
		 u1.setName("name of u1");
		 u1.setRole("ROLE_ADMIN");
		 u1.setDate_login( new Date());
		 session.setAttribute(KEY_SESSION_USER,u1);
		 //
		 UserSessionModel u=new UserSessionModel();
		 if(session.getAttribute(KEY_SESSION_USER)!=null){
			 u=(UserSessionModel)session.getAttribute(KEY_SESSION_USER);
		 }
		return u;	 
	 }
	 
}
