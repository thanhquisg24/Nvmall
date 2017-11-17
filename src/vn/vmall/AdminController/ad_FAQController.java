package vn.vmall.AdminController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.vmall.Entity.FAQ_Entity;
import vn.vmall.Helper.ErrorMesage;
import vn.vmall.Helper.ErrorMessageModel;
import vn.vmall.Interface.FAQInterface;

@RestController
@RequestMapping(value="ad/FAQController")
public class ad_FAQController {

	 @Autowired
	 @Qualifier("FAQImp")
	 private FAQInterface FAQImp;
	
	@RequestMapping(value="/get_FAQbyid",
			method=RequestMethod.GET,
			produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
	public FAQ_Entity get_FAQbyid(@RequestParam(value ="faq_id",required = true) String faq_id){
		return FAQImp.get_FAQbyid(faq_id);
	}
	
	@RequestMapping(value="/SaveFAQ",
			method=RequestMethod.POST,
			produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ErrorMessageModel SaveFAQ(
			@RequestParam(value ="faq_id",required = true,defaultValue="0") String faq_id,
			@RequestParam(value ="faq_content",required = false,defaultValue="") String faq_content){
		FAQ_Entity d=new FAQ_Entity();
		d.setId(faq_id);
		d.setContent(faq_content);
	//	System.out.print(ptype+"-"+product_type_vmall+"-"+product_type_name+"-"+category_img+"-"+title_img);
		ErrorMessageModel e=new ErrorMessageModel();
		int f=  FAQImp.SaveFAQ(d);
		e=ErrorMesage.get_json_mes_error(f);
		return e;
		//return (ptype+"-"+product_type_vmall+"-"+product_type_name+"-"+category_img+"-"+title_img);
	}
}
