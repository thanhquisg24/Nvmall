package vn.vmall.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import vn.vmall.Entity.Member_Entity;
import vn.vmall.Helper.responseUtf8;
import vn.vmall.Interface.MemberInterface;



@Controller
@RequestMapping(value="IndexController")
public class IndexController {
	 @Autowired
	 @Qualifier("MemberImp")
	 private MemberInterface Imember;
	 
	 
	 	@RequestMapping(value="/testget",method=RequestMethod.GET)
		@ResponseBody
		public void testget(HttpServletRequest request,HttpServletResponse response) throws IOException {
	 		Member_Entity list=Imember.getMemberByid("00");
	 		Gson gs=new Gson();
	 		responseUtf8.response_Utf8(response, gs.toJson(list));
		
			
		}

	 	@RequestMapping(value="/testpost",method=RequestMethod.POST)
		@ResponseBody
		public String testpost(HttpServletRequest request,HttpServletResponse response) {
	 		
			return null;
			
		}
		@RequestMapping(value="/testgetstring",method=RequestMethod.GET)
		@ResponseBody
		public String testgetstring(HttpServletRequest request,HttpServletResponse response) throws IOException {
	 		//List<Member_Entity> list=Imember.getAllMember();
	 		//Gson gs=new Gson();
	 		//.response_Utf8(response, gs.toJson(list));
			return Imember.testgetstring();
		
			
		}

}
