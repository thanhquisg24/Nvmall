package vn.vmall.Interface;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import vn.vmall.Entity.Customer_Entity;
import vn.vmall.Entity.proRecommended_Entity;
import vn.vmall.Helper.SearchPaggModel;



public interface proRecommendedInterface {
	List<proRecommended_Entity> get_list_search_pagg(SearchPaggModel searchmodel);
	 int count_get_list_search_pagg(SearchPaggModel searchmodel);
	 int add_update_proRecommended(String custome_value) throws InstantiationException, ClassNotFoundException, SQLException;
	 proRecommended_Entity get_proRecommended_by_id(String id);
	 int visivled_proRecommended(String str_id,String visible)  
			 throws JsonParseException, JsonMappingException, IOException,SQLException, InstantiationException, ClassNotFoundException;
	 List<proRecommended_Entity> get_allbranh();
	List<proRecommended_Entity> get_datagrip_byproducttypeid(SearchPaggModel searchmodel);
	int count_get_datagrip_byproducttypeid(SearchPaggModel searchmodel);
	int detele_single_proRecommended(String str_id);
	int delete_multi_proRecommended(String str_id)  throws JsonParseException, JsonMappingException, IOException,
	SQLException, InstantiationException, ClassNotFoundException;
	List<proRecommended_Entity> get_datagrip_customer_product(SearchPaggModel searchmodel);
	int count_get_datagrip_customer_product(SearchPaggModel searchmodel);
	List<Customer_Entity> get_customer_of_type(String type_id);
}
