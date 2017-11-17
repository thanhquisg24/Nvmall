package vn.vmall.Imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.vmall.DAL.FAQDAL;
import vn.vmall.Entity.FAQ_Entity;
import vn.vmall.Interface.FAQInterface;


@Component("FAQImp")
public class FAQImp implements FAQInterface {
	@Autowired
	private FAQDAL dal;
	@Override
	public FAQ_Entity get_FAQbyid(String faq_id) {
		// TODO Auto-generated method stub
		return dal.get_FAQbyid(faq_id);
	}
	@Override
	public int SaveFAQ(FAQ_Entity d) {
		// TODO Auto-generated method stub
		return dal.SaveFAQ(d);
	}
	

}
