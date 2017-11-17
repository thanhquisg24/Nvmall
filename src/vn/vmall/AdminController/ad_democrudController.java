package vn.vmall.AdminController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.vmall.Entity.DemoCRUD_Entity;
import vn.vmall.Helper.ErrorMesage;
import vn.vmall.Helper.ErrorMessageModel;
import vn.vmall.Helper.JsonDataGripModel;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.Interface.DemoCRUDInterface;

@RestController
@RequestMapping(value="ad/democrudController")
public class ad_democrudController {
	
	 @Autowired
	 @Qualifier("DemoCRUDImp")
	private DemoCRUDInterface DemoCRUDImp;
	
	
	
	@RequestMapping(value="/get_democrud_by_id",
			method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)

	public DemoCRUD_Entity get_democrud_by_id(	
			@RequestParam(value ="id",required = true) String id){
		
		
		return DemoCRUDImp.get_democrud_by_id(id);
		
	}
	
	@RequestMapping(value="/get_json_append_to_datagrip.json",
			method=RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)

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
		JsonDataGripModel<DemoCRUD_Entity> GripModel =new JsonDataGripModel<DemoCRUD_Entity>();
		GripModel.setRows(DemoCRUDImp.get_list_search_pagg(searchmodel));
		GripModel.setTotal(DemoCRUDImp.count_get_list_search_pagg(searchmodel));
		return GripModel;
		//return "{'aa':'ồ ố ô ộ'}";
		
	}
	
	@RequestMapping(value="/add_update_demo",
			method=RequestMethod.POST,
			produces =MediaType.TEXT_PLAIN_VALUE+";charset=UTF-8")
	public String add_update_demo(
			@RequestParam(value ="ptype",required = true) String ptype,
			@RequestParam(value ="id",required = false,defaultValue="0") String id,
			@RequestParam(value ="name",required = true) String name,
			@RequestParam(value ="email",required = true) String email,
			@RequestParam(value ="address",required = true) String address,
			@RequestParam(value ="phone",required = true) String phone){
		DemoCRUD_Entity d=new DemoCRUD_Entity();
		d.setId(id);
		d.setAddress(address);
		d.setEmail(email);
		d.setPhone(phone);	
		d.setName(name);
		//System.out.print(id);
		return DemoCRUDImp.add_update_demo(ptype, d);
		//return name+"-"+email+"-"+address+"-"+phone+"-"+ptype;
		
	}
	@RequestMapping(value="/detele_demo",
			method=RequestMethod.POST,
			produces =MediaType.APPLICATION_JSON_VALUE)
	public ErrorMessageModel detele_demo(@RequestParam(value ="str_id",required = true) String str_id){
		
		ErrorMessageModel e=new ErrorMessageModel();
		int f=DemoCRUDImp.delete_demo(str_id);
		e=ErrorMesage.get_json_mes_error(f);
		return e;
		
		
	}
	
	@RequestMapping(value="/get_map",method=RequestMethod.GET,produces = "application/json")

	public SearchPaggModel get_map(){
		SearchPaggModel m=new SearchPaggModel();
		m.setToDate("ồ ố ô ộ");
		m.setFromDate("2016");
		return m;
		
	}
	@RequestMapping(value="/get_s",
			method=RequestMethod.GET)
	public  String get_s(){
	
		return "SSSSSSS";
		
	}
	
}
