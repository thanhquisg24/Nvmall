package vn.vmall.Imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.vmall.DAL.NewsDAL;
import vn.vmall.Entity.CategoryNew_Entity;
import vn.vmall.Entity.CatgoryNews_Entitty;
import vn.vmall.Entity.News_Entity;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.Interface.NewsInterface;

@Component("NewsImp")
public class NewsImp  implements NewsInterface{

	@Autowired
	private NewsDAL dal;

	@Override
	public List<News_Entity> get_list_search_pagg(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return dal.get_list_search_pagg(searchmodel);
	}

	@Override
	public int count_get_list_search_pagg(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return dal.count_get_list_search_pagg(searchmodel);
		
	}

	@Override
	public int visivled_news(String str_id, String visible) {
		// TODO Auto-generated method stub
		return dal.visivled_news(str_id,visible);
	}

	@Override
	public int delete_multi_news(String str_id) {
		// TODO Auto-generated method stub
		return dal.delete_multi_news(str_id);
	}

	@Override
	public List<CategoryNew_Entity> get_allCatgoryNews() {
		// TODO Auto-generated method stub
		return dal.get_allCatgoryNews();
	}

	@Override
	public int add_update_catgorynews(String ptype, CatgoryNews_Entitty d) {
		// TODO Auto-generated method stub
		return dal.add_update_catgorynews(ptype,d);
	}

	@Override
	public  Map<String,Object> SaveNews(String ptype, News_Entity d) {
		// TODO Auto-generated method stub
		return dal.SaveNews(ptype,d);
	}

	@Override
	public News_Entity get_Newsbyid(String news_id) {
		// TODO Auto-generated method stub
		return dal.get_Newsbyid( news_id);
	}
	@Override
	public List<CategoryNew_Entity> get_list_new_cate(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return dal.get_list_new_cate(searchmodel);
	}

	@Override
	public int count_get_list_new_cate(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return dal.count_get_list_new_cate(searchmodel);
	}

	@Override
	public int add_update_catgory_new(String ptype, CategoryNew_Entity d) {
		// TODO Auto-generated method stub
		return dal.add_update_catgory_new(ptype, d);
	}

	@Override
	public CategoryNew_Entity get_newcat_by_id(String id) {
		// TODO Auto-generated method stub
		return dal.get_newcat_by_id(id);
	}

	@Override
	public int remove_new_catgory(String str_id) {
		// TODO Auto-generated method stub
		return dal.remove_new_catgory(str_id);
	}

	@Override
	public int visibled_new_catgory(String str_id, String visible) {
		// TODO Auto-generated method stub
		return dal.visibled_new_catgory(str_id,visible);
	}
}
