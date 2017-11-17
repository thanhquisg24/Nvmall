package vn.vmall.AdminController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import vn.vmall.Entity.CategoryNew_Entity;
import vn.vmall.Entity.CatgoryNews_Entitty;
import vn.vmall.Entity.News_Entity;
import vn.vmall.Helper.ErrorMesage;
import vn.vmall.Helper.ErrorMessageModel;
import vn.vmall.Helper.JsonDataGripModel;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.Interface.NewsInterface;
import vn.vmall.SessionModel.UserSessionModel;



@RestController
@RequestMapping(value="ad/NewsController")
@SessionAttributes(value = "user", types = {UserSessionModel.class})
public class ad_NewsController {
	@Autowired
	@Qualifier("NewsImp")
	private NewsInterface NewsImp;
	
	
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
		JsonDataGripModel<News_Entity> GripModel =new JsonDataGripModel<News_Entity>();
		GripModel.setRows( NewsImp.get_list_search_pagg(searchmodel));
		GripModel.setTotal( NewsImp.count_get_list_search_pagg(searchmodel));
		return GripModel;
	}
	@RequestMapping(value="/visivled_news",
			method=RequestMethod.POST,
			produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ErrorMessageModel visivled_news(
			@RequestParam(value ="str_id",required = true) String str_id,
			@RequestParam(value ="visible",required = true) String visible){
		ErrorMessageModel e=new ErrorMessageModel();
		int f= NewsImp.visivled_news(str_id,visible);
		e=ErrorMesage.get_json_mes_error(f);
		return e;
	}
	@RequestMapping(value="/delete_multi_news",
			method=RequestMethod.POST,
			produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ErrorMessageModel delete_multi_news(@RequestParam(value ="str_id",required = true) String str_id){	
		ErrorMessageModel e=new ErrorMessageModel();
		int f= NewsImp.delete_multi_news(str_id);
		e=ErrorMesage.get_json_mes_error(f);
		return e;
	}
	@RequestMapping(value="/get_allCatgoryNews",
			method=RequestMethod.GET,
			produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<CategoryNew_Entity> get_allCatgoryNews(){
	return  NewsImp.get_allCatgoryNews();
	}
	@RequestMapping(value="/add_update_catgorynews",
			method=RequestMethod.POST,
			produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ErrorMessageModel add_update_catgorynews(
			@RequestParam(value ="ptype",required = true) String ptype,
			@RequestParam(value ="idcatgorynews",required = true) int idcatgorynews,
			@RequestParam(value ="namecatgorynews",required = true) String namecatgorynews,
			@RequestParam(value ="imgcatgorynews",required = false,defaultValue="") String imgcatgorynews,
			@ModelAttribute("user") UserSessionModel userm){
		CatgoryNews_Entitty d=new CatgoryNews_Entitty();
		d.setId(idcatgorynews);
		d.setName(namecatgorynews);
		d.setImg(imgcatgorynews);
		d.setCreator(userm.getId());
		d.setModifyer(userm.getId());
		//System.out.println(userm.getId() + " "+ userm.getName());
		//System.out.print(ptype+"-"+product_type_id+"-"+parentlocation+"-"+locationid+"-"+locationname);
		ErrorMessageModel e=new ErrorMessageModel();
		int f=  NewsImp.add_update_catgorynews(ptype, d);
		e=ErrorMesage.get_json_mes_error(f);
		return e;
	}
	
	@RequestMapping(value="/SaveNews",
			method=RequestMethod.POST,
			produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ErrorMessageModel SaveNews(
			@RequestParam(value ="ptype",required = true) String ptype,
			@RequestParam(value ="newsid",required = true) String newsid,
			@RequestParam(value ="catgory_news",required = true) String catgory_news,
			@RequestParam(value ="news_title",required = true) String news_title,
			@RequestParam(value ="short_description",required = true) String short_description,
			@RequestParam(value ="content",required = false,defaultValue="") String content,
			@RequestParam(value ="img",required = false,defaultValue="") String img,
			@ModelAttribute("user") UserSessionModel userm){
		News_Entity d=new News_Entity();
		d.setId(newsid);
		d.setNews_catgory(catgory_news);
		d.setTitle(news_title);
		d.setShort_description(short_description);
		d.setContent(content);
		d.setImg(img);
		d.setCreator(userm.getId());
		d.setModifyer(userm.getId());
		//System.out.println(userm.getId() + " "+ userm.getName());
		//System.out.print(ptype+"-"+product_type_id+"-"+parentlocation+"-"+locationid+"-"+locationname);
		Map<String,Object> mapOfObjects = new HashMap<String,Object>();
		mapOfObjects =  NewsImp.SaveNews(ptype, d);
		ErrorMessageModel e=new ErrorMessageModel();
			e=ErrorMesage.get_json_mes_error(Integer.parseInt(mapOfObjects.get("f").toString()));
			e.setOut_id(mapOfObjects.get("out_id").toString());
		return e;
	}
	
	@RequestMapping(value="/get_Newsbyid",
			method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public News_Entity get_Newsbyid(@RequestParam(value ="news_id",required = true) String news_id){		
		return  NewsImp.get_Newsbyid(news_id);
	}
	@RequestMapping(value="/get_json_new_cate.json",
			method=RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

	public JsonDataGripModel get_json_new_cate(
			@RequestParam(value ="page",required = false,defaultValue="1") int page,
			@RequestParam(value ="rows",required = false,defaultValue="10") int rows,
			@RequestParam(value ="col",required = false,defaultValue="") String col,
			@RequestParam(value ="val",required = false,defaultValue="") String val){
		SearchPaggModel searchmodel=new SearchPaggModel();
		searchmodel.setPage(page);
		searchmodel.setRows(rows);
		searchmodel.setCol(col);
		searchmodel.setVal(val);
		JsonDataGripModel<CategoryNew_Entity> GripModel =new JsonDataGripModel<CategoryNew_Entity>();
		GripModel.setRows(NewsImp.get_list_new_cate(searchmodel));
		GripModel.setTotal(NewsImp.count_get_list_new_cate(searchmodel));
		return GripModel;
	}

	@RequestMapping(value="/add_update_catgory_new",
			method=RequestMethod.POST,
			produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ErrorMessageModel add_update_catgory_new(
			@RequestParam(value ="ptype",required = true) String ptype,
			@RequestParam(value ="id_new_cate",required = false) String category_id,
			@RequestParam(value ="name_new_cate",required = true) String category_name,
			@RequestParam(value ="category_img",required = false,defaultValue="") String category_img,
			@RequestParam(value ="title_img",required = false,defaultValue="") String title_img){
		CategoryNew_Entity d=new CategoryNew_Entity();
		d.setCategory_id(category_id);
		d.setCategory_img(category_img);
		d.setCategory_name(category_name);
		d.setTitle_img(title_img);
		ErrorMessageModel e=new ErrorMessageModel();
		int f= NewsImp.add_update_catgory_new(ptype, d);
		e=ErrorMesage.get_json_mes_error(f);
		return e;
		
	}
	 
	 @RequestMapping(value="/get_newcat_by_id",
				method=RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

		public CategoryNew_Entity get_newcat_by_id(	
				@RequestParam(value ="id",required = true) String id){
			
			
			return NewsImp.get_newcat_by_id(id);
			
		}
	 @RequestMapping(value="/remove_new_catgory",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel remove_new_catgory(@RequestParam(value ="str_id",required = true) String str_id){
			
			ErrorMessageModel e=new ErrorMessageModel();
			int f=NewsImp.remove_new_catgory(str_id);
			e=ErrorMesage.get_json_mes_error(f);
			return e;
			
			
		}
	 @RequestMapping(value="/visibled_new_catgory",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel visibled_new_catgory(
				@RequestParam(value ="str_id",required = true) String str_id,
				@RequestParam(value ="visible",required = true) String visible){
			
			ErrorMessageModel e=new ErrorMessageModel();
			int f=NewsImp.visibled_new_catgory(str_id,visible);
			e=ErrorMesage.get_json_mes_error(f);
			return e;
			
			
		}
	
}
