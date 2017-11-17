package vn.vmall.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.vmall.Entity.Slide_Entity;
import vn.vmall.Interface.SlideInterface;

@RestController
@RequestMapping(value = "SlideController")
public class SlideController {
	 @Autowired
	 @Qualifier("SlideImp")
	 private SlideInterface Slide;
	 @RequestMapping(value="/get_list_slide",
				method=RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

		public List<Slide_Entity> get_list_slide(){
			return Slide.get_list_slide();
			
		}
}
