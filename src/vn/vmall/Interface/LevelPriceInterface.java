package vn.vmall.Interface;

import java.sql.SQLException;
import java.util.List;

import vn.vmall.Entity.LevelPrice_Entity;


public interface LevelPriceInterface {

	 List<LevelPrice_Entity> get_alllevel();

	int add_update_level(String ptype, LevelPrice_Entity d);

	int detele_single_level(String str_id);

	List<LevelPrice_Entity> get_leveldetail_byvmall(String productype_vmallid);

	int do_append_level(String product_type_vmall, String json_level) throws InstantiationException, ClassNotFoundException, SQLException;

	int delete_multi_leveldetail(String str_id, String product_type_vmall);
	
}
