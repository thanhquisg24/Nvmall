package vn.vmall.AdminController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import vn.vmall.Entity.Slide_Entity;
import vn.vmall.Helper.ErrorMesage;
import vn.vmall.Helper.ErrorMessageModel;
import vn.vmall.Helper.JsonDataGripModel;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.Interface.SlideInterface;
import vn.vmall.SessionModel.UserSessionModel;



@RestController
@RequestMapping(value="ad/SlideController")
@SessionAttributes(value = "user", types = {UserSessionModel.class})
public class ad_SlideController {
		
	

	 @Autowired
	 @Qualifier("SlideImp")
	 private SlideInterface Slide;
	 
	 
	 	@RequestMapping(value="/get_slide_by_id",
				method=RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

		public Slide_Entity get_slide_by_id(	
				@RequestParam(value ="id",required = true) String id){
			return Slide.get_slide_by_id(id);
			
		}
		
		@RequestMapping(value="/get_json_append_to_datagrip.json",
				method=RequestMethod.POST,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

		public JsonDataGripModel get_json_append_to_datagrip(
				@RequestParam(value ="page",required = false,defaultValue="1") int page,
				@RequestParam(value ="rows",required = false,defaultValue="10") int rows,
				@RequestParam(value ="col",required = false,defaultValue="") String col,
				@RequestParam(value ="val",required = false,defaultValue="") String val){
			SearchPaggModel searchmodel=new SearchPaggModel();
			searchmodel.setPage(page);
			searchmodel.setRows(rows);
			searchmodel.setCol(col);
			searchmodel.setVal(val);
			JsonDataGripModel<Slide_Entity> GripModel =new JsonDataGripModel<Slide_Entity>();
			GripModel.setRows(Slide.get_list_search_pagg(searchmodel));
			GripModel.setTotal(Slide.count_get_list_search_pagg(searchmodel));
			return GripModel;
			//return "{'aa':'ồ ố ô ộ'}";
			
		}
		
		@RequestMapping(value="/add_update_slide",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel add_update_slide(
				@RequestParam(value ="ptype",required = true) String ptype,
				@RequestParam(value ="name",required = true) String name,
				@RequestParam(value ="id",required = false,defaultValue="") String id,
				HttpServletRequest request){
			HttpSession session = request.getSession(false);
			UserSessionModel usrm=(UserSessionModel)session.getAttribute(ad_loginController.KEY_SESSION_USER);
			Slide_Entity d=new Slide_Entity();
			d.setId(id);
			d.setName(name);
			d.setCreator(usrm.getId());
			d.setModifyer(usrm.getId());
			ErrorMessageModel e=new ErrorMessageModel();
			int f= Slide.add_update_slide(ptype, d);
			e=ErrorMesage.get_json_mes_error(f);
			return e;
		
			
			
		}
		@RequestMapping(value="/detele_slide",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel detele_slide(@RequestParam(value ="str_id",required = true) String str_id){
			
			ErrorMessageModel e=new ErrorMessageModel();
			int f=Slide.delete_slide(str_id);
			e=ErrorMesage.get_json_mes_error(f);
			return e;
			
			
		}
		@RequestMapping(value="/visivled_slide",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel visivled_slide(
				@RequestParam(value ="str_id",required = true) String str_id,
				@RequestParam(value ="visible",required = true) String visible){
			
			ErrorMessageModel e=new ErrorMessageModel();
			int f=Slide.visivled_slide(str_id,visible);
			e=ErrorMesage.get_json_mes_error(f);
			return e;
			
			
		}
	
}
