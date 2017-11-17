package vn.vmall.Imp;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vn.vmall.DAL.proRecommendedDAL;
import vn.vmall.Entity.Customer_Entity;
import vn.vmall.Entity.proRecommended_Entity;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.Interface.proRecommendedInterface;


@Component(value=" proRecommendedImp")
public class  proRecommendedImp implements proRecommendedInterface {

	@Autowired
	private proRecommendedDAL DAL;
	
	@Override
	public List<proRecommended_Entity> get_list_search_pagg(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.get_list_search_pagg(searchmodel);
	}

	@Override
	public int count_get_list_search_pagg(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.count_get_list_search_pagg(searchmodel);
	}

	@Override
	public int add_update_proRecommended(String custom_value) throws InstantiationException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return DAL.add_update_proRecommended(custom_value);

	}

	@Override
	public proRecommended_Entity get_proRecommended_by_id(String id) {
		// TODO Auto-generated method stub
		return DAL.get_proRecommended_by_id(id);
	}



	@Override
	public int visivled_proRecommended(String str_id,String visible) throws JsonParseException, JsonMappingException, IOException,
	SQLException, InstantiationException, ClassNotFoundException{
		ObjectMapper mapper = new ObjectMapper();
		List<proRecommended_Entity> myObjects = mapper.readValue(str_id, new TypeReference<List<proRecommended_Entity>>(){});
		
		return DAL.visivled_proRecommended(myObjects,visible);
	}

	@Override
	public List<proRecommended_Entity> get_allbranh() {
		// TODO Auto-generated method stub
		return DAL.get_allbranh();
	}

	@Override
	public List<proRecommended_Entity> get_datagrip_byproducttypeid(
			SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.get_datagrip_byproducttypeid(searchmodel);
	}

	@Override
	public int count_get_datagrip_byproducttypeid(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.count_get_datagrip_byproducttypeid(searchmodel);
	}

	@Override
	public int detele_single_proRecommended(String str_id) {
		// TODO Auto-generated method stub
		 return DAL.detele_single_proRecommended( str_id);
	}

	@Override
	public int delete_multi_proRecommended(String str_id)  throws JsonParseException, JsonMappingException, IOException,
	SQLException, InstantiationException, ClassNotFoundException{
		ObjectMapper mapper = new ObjectMapper();
		List<proRecommended_Entity> myObjects = mapper.readValue(str_id, new TypeReference<List<proRecommended_Entity>>(){});
		 return DAL.delete_multi_proRecommended( myObjects);
	}
	
	@Override
	public List<proRecommended_Entity> get_datagrip_customer_product(
			SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.get_datagrip_customer_product(searchmodel);
	}

	@Override
	public int count_get_datagrip_customer_product(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.count_get_datagrip_customer_product(searchmodel);
	}
	
	@Override
	public List<Customer_Entity> get_customer_of_type(String type_id){
		// TODO Auto-generated method stub
		return DAL.get_customer_of_type(type_id);
	}
}