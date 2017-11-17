package vn.vmall.Interface;

import java.sql.SQLException;
import java.util.List;

import vn.vmall.Entity.Config_Entity;
import vn.vmall.Entity.Fee_Entity;
import vn.vmall.Helper.AllcodeModel;


public interface ConfigInterface {

	List<AllcodeModel> get_url_nganluong();

	int update_munti_config_nganluong(String json_nganluong_update)
	   	throws InstantiationException, ClassNotFoundException, SQLException;
	
	List<Fee_Entity> get_all_fee();

	int update_munti_fee(String json_fee)
	   	throws InstantiationException, ClassNotFoundException, SQLException;
	
	
	 List<Config_Entity> get_all_config();

	 int update_munti_config(String json_config)
				throws InstantiationException, ClassNotFoundException, SQLException;
	
}
