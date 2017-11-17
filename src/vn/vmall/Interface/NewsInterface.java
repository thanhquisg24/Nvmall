package vn.vmall.Interface;

import java.util.List;
import java.util.Map;

import vn.vmall.Entity.CategoryNew_Entity;
import vn.vmall.Entity.CatgoryNews_Entitty;
import vn.vmall.Entity.News_Entity;
import vn.vmall.Helper.SearchPaggModel;

public interface NewsInterface {

	List<News_Entity> get_list_search_pagg(SearchPaggModel searchmodel);

	int count_get_list_search_pagg(SearchPaggModel searchmodel);

	int visivled_news(String str_id, String visible);

	int delete_multi_news(String str_id);

	List<CategoryNew_Entity> get_allCatgoryNews();

	int add_update_catgorynews(String ptype, CatgoryNews_Entitty d);

	 Map<String,Object> SaveNews(String ptype, News_Entity d);

	 News_Entity get_Newsbyid(String news_id);
	 
	 List<CategoryNew_Entity> get_list_new_cate(SearchPaggModel searchmodel);
		int count_get_list_new_cate(SearchPaggModel searchmodel);
		int add_update_catgory_new(String ptype, CategoryNew_Entity d);
		CategoryNew_Entity get_newcat_by_id(String id);
		int remove_new_catgory(String str_id);
		int visibled_new_catgory(String str_id, String visible);
}
