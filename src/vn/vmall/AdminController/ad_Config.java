package vn.vmall.AdminController;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import vn.vmall.Entity.Config_Entity;
import vn.vmall.Entity.Fee_Entity;
import vn.vmall.Helper.AllcodeModel;
import vn.vmall.Helper.ErrorMesage;
import vn.vmall.Helper.ErrorMessageModel;
import vn.vmall.Interface.ConfigInterface;
import vn.vmall.SessionModel.UserSessionModel;



@RestController
@RequestMapping(value="ad/ConfigController")
//@SessionAttributes(value = "user", types = {UserSessionModel.class})
public class ad_Config {

	@Autowired
	@Qualifier("ConfigImp")
	private ConfigInterface configimp;
	
	@RequestMapping(value="/get_config_url_nganluong",
			method=RequestMethod.GET,
			produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<AllcodeModel> get_config_url_nganluong(){
		return configimp.get_url_nganluong();
	}
	
	@RequestMapping(value="/update_munti_config_nganluong",
			method=RequestMethod.POST,
			produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ErrorMessageModel do_append_brand(@RequestParam(value ="json_nganluong_update",required = true) String json_nganluong_update) 
			throws InstantiationException, ClassNotFoundException, SQLException{
		ErrorMessageModel e=new ErrorMessageModel();
		int f= configimp.update_munti_config_nganluong(json_nganluong_update);
		e=ErrorMesage.get_json_mes_error(f);
		return e;
	}
	
	
	@RequestMapping(value="/get_all_fee",
			method=RequestMethod.GET,
			produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Fee_Entity> get_all_fee(){
		return configimp.get_all_fee();
	}
	
	@RequestMapping(value="/update_munti_fee",
			method=RequestMethod.POST,
			produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ErrorMessageModel update_munti_fee(@RequestParam(value ="json_fee",required = true) String json_fee) 
			throws InstantiationException, ClassNotFoundException, SQLException{
		ErrorMessageModel e=new ErrorMessageModel();
		int f= configimp.update_munti_fee(json_fee);
		e=ErrorMesage.get_json_mes_error(f);
		return e;
	}
	
	@RequestMapping(value="/get_all_config",
			method=RequestMethod.GET,
			produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Config_Entity> get_all_config(){
		return configimp.get_all_config();
	}
	
	@RequestMapping(value="/update_munti_config",
			method=RequestMethod.POST,
			produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ErrorMessageModel update_munti_config(@RequestParam(value ="json_config",required = true) String json_config) 
			throws InstantiationException, ClassNotFoundException, SQLException{
		ErrorMessageModel e=new ErrorMessageModel();
		int f= configimp.update_munti_config(json_config);
		e=ErrorMesage.get_json_mes_error(f);
		return e;
	}
}
