package vn.vmall.AdminController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;







import vn.vmall.Entity.RuleModel;
import vn.vmall.Helper.ErrorMesage;
import vn.vmall.Helper.ErrorMessageModel;
import vn.vmall.Interface.RuleInterface;




@RestController
@RequestMapping(value="ad/RuleController")
public class ad_rule {
	 @Autowired
	 @Qualifier("Ruleimp")
	 private RuleInterface Ruleimp;
	 
		@RequestMapping(value="/get_regulartion",
				method=RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public RuleModel get_regulartion(){		
			return  Ruleimp.get_regulartion();
		}
		
		@RequestMapping(value="/get_scheme",
				method=RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public RuleModel get_scheme(){		
			return  Ruleimp.get_scheme();
		}
		@RequestMapping(value="/Saverule",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel saverule(@RequestParam(value ="id",required = true) String id,
				@RequestParam(value ="content_page",required = true) String content_page){
						
			ErrorMessageModel e=new ErrorMessageModel();
			int f=Ruleimp.saverule(id,content_page);
			e=ErrorMesage.get_json_mes_error(f);
			return e;
			
		}
}
