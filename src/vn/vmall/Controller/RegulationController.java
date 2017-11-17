package vn.vmall.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.vmall.DAL.RegulationDAL;
import vn.vmall.Entity.Catgory_Entity;
import vn.vmall.Entity.ItemRegulation;
import vn.vmall.Interface.BrandInterface;

@RestController
@RequestMapping(value="RegulationController")
public class RegulationController {
	@RequestMapping(value = "/get_schema", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ItemRegulation get_schema() {
		return RegulationDAL.get_schema();
	}
	@RequestMapping(value = "/get_regulation", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ItemRegulation get_regulation() {
		return RegulationDAL.get_regulation();
	}
}
