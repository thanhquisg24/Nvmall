package vn.vmall.Imp;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import vn.vmall.DAL.AllcodeDAL;
import vn.vmall.DAL.ConfigDAL;
import vn.vmall.DAL.FeeDAL;
import vn.vmall.Entity.Config_Entity;
import vn.vmall.Entity.Fee_Entity;
import vn.vmall.Helper.AllcodeModel;
import vn.vmall.Interface.ConfigInterface;


@Component(value="ConfigImp")
public class ConfigImp implements ConfigInterface{
	@Autowired
	private AllcodeDAL allcode;
	
	@Autowired
	private FeeDAL fee;

	@Autowired
	private ConfigDAL config;
	@Override
	public List<AllcodeModel> get_url_nganluong() {
		// TODO Auto-generated method stub
		return allcode.get_url_nganluong();
	}

	@Override
	public int update_munti_config_nganluong(String json_nganluong_update)
			throws InstantiationException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Gson gson= new Gson();
		List<AllcodeModel> list = gson.fromJson(json_nganluong_update, new TypeToken<List<AllcodeModel>>(){}.getType());
		return allcode.update_munti_config_nganluong(list);
	}

	@Override
	public List<Fee_Entity> get_all_fee() {
		// TODO Auto-generated method stub
		return fee.get_all_fee();
	}

	@Override
	public int update_munti_fee(String json_fee) throws InstantiationException,
			ClassNotFoundException, SQLException {
		Gson gson= new Gson();
		List<Fee_Entity> list = gson.fromJson(json_fee, new TypeToken<List<Fee_Entity>>(){}.getType());
		return fee.update_munti_fee(list);
	}

	@Override
	public List<Config_Entity> get_all_config() {
		// TODO Auto-generated method stub
		return config.get_all_config();
	}

	@Override
	public int update_munti_config(String json_config)
			throws InstantiationException, ClassNotFoundException, SQLException {
		Gson gson= new Gson();
		List<Config_Entity> list = gson.fromJson(json_config, new TypeToken<List<Config_Entity>>(){}.getType());
		return config.update_munti_config(list);
	}
	
}
