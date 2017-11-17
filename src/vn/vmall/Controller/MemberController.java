package vn.vmall.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.vmall.Entity.Member_Entity;
import vn.vmall.Entity.Product_Entity;
import vn.vmall.Entity.Slide_Entity;
import vn.vmall.Interface.MemberInterface;
import vn.vmall.Interface.SlideInterface;

@RestController
@RequestMapping(value = "MemberController")
public class MemberController {
	@Autowired
	@Qualifier("MemberImp")
	private MemberInterface Member;

	@RequestMapping(value = "/get_info_member", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

	public Member_Entity get_info_member(@RequestParam("email") String email) {

		return Member.get_info_member(email);
	}

	@RequestMapping(value = "/update_info_member", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

	public int update_info_member(@RequestParam("email") String email, @RequestParam("fullname") String fullname,
			@RequestParam("phone") String phone, @RequestParam("address") String address) {

		return Member.update_info_member(email, fullname, phone, address);
	}

	@RequestMapping(value = "/update_account", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

	public int update_account(@RequestParam("str") String str) {

		return Member.update_account(str);
	}

	@RequestMapping(value = "/get_product_save_later", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

	public ArrayList<Product_Entity> get_product_save_later(@RequestParam("email") String email) {

		return Member.get_product_save_later(email);
	}

	@RequestMapping(value = "/get_product_rating", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

	public ArrayList<Product_Entity> get_product_rating(@RequestParam("email") String email) {

		return Member.get_product_rating(email);
	}

	@RequestMapping(value = "/insert_customer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

	public int insert_customer(@RequestParam("email") String email, @RequestParam("fullname") String fullname) {

		return Member.insert_customer(email, fullname);
	}

	@RequestMapping(value = "/set_info_login", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

	public void set_info_login(@RequestParam("email") String email,
			@RequestParam("branch") String branch,
			@RequestParam("fullname") String fullname,
			@RequestParam("id") String id,
			@RequestParam("cookies") String cookies, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.setAttribute("branch", branch);
		session.setAttribute("email", email);
		session.setAttribute("username", fullname);
		session.setAttribute("fullname", fullname);
		if (cookies.equals("Y")) {

			Cookie setEmailCookies = new Cookie("email", email);
			setEmailCookies.setMaxAge(30 * 24 * 60 * 60);
			response.addCookie(setEmailCookies);
			response.setContentType("text/html");
		}
	}
}
