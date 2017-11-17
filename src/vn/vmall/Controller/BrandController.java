package vn.vmall.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import vn.vmall.Entity.Brand_Entity;
import vn.vmall.Entity.Catgory_Entity;
import vn.vmall.Entity.Member_Entity;
import vn.vmall.Helper.responseUtf8;
import vn.vmall.Interface.BrandInterface;
import vn.vmall.Interface.MemberInterface;

@RestController
@RequestMapping(value = "BrandController")
public class BrandController {
	@Autowired
	@Qualifier("BrandImp")
	private BrandInterface BrandImp;

	@RequestMapping(value = "/get_list_brand", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ArrayList<Catgory_Entity> get_list_brand() {
		return BrandImp.get_list_brand();
	}

	@RequestMapping(value = "/get_brand_by_vmall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ArrayList<Brand_Entity> get_brand_by_vmall(@RequestParam("type_vmall") String type_vmall) {
		return BrandImp.get_brand_by_vmall(type_vmall);
	}
}
