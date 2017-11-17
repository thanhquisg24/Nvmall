package vn.vmall.Imp;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import vn.vmall.DAL.LevelPriceDAL;
import vn.vmall.Entity.LevelPrice_Entity;
import vn.vmall.Interface.LevelPriceInterface;


@Component(value="LevelPriceImp")
public class LevelPriceImp implements LevelPriceInterface {

	@Autowired
	private LevelPriceDAL DAL;
	
	@Override
	public List<LevelPrice_Entity> get_alllevel() {
		// TODO Auto-generated method stub
		return DAL.get_alllevel();
	}

	@Override
	public int add_update_level(String ptype, LevelPrice_Entity d) {
		// TODO Auto-generated method stub
		return DAL.add_update_level( ptype,  d) ;
	}

	@Override
	public int detele_single_level(String str_id) {
		// TODO Auto-generated method stub
		return DAL.detele_single_level( str_id) ;
	}

	@Override
	public List<LevelPrice_Entity> get_leveldetail_byvmall(
			String productype_vmallid) {
		// TODO Auto-generated method stub
		return DAL.get_leveldetail_byvmall( productype_vmallid) ;
	}

	@Override
	public int do_append_level(String product_type_vmall, String json_level) throws InstantiationException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Gson gson= new Gson();
		List<LevelPrice_Entity> listLevel = gson.fromJson(json_level, new TypeToken<List<LevelPrice_Entity>>(){}.getType());
		
		return DAL.do_append_level(product_type_vmall,listLevel) ;
	}

	@Override
	public int delete_multi_leveldetail(String str_id, String product_type_vmall) {
		// TODO Auto-generated method stub
		return DAL.delete_multi_leveldetail( str_id,product_type_vmall) ;
	}
	
	

}
