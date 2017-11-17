package vn.vmall.Interface;

import vn.vmall.Entity.FAQ_Entity;

public interface FAQInterface {

	FAQ_Entity get_FAQbyid(String faq_id);

	int SaveFAQ(FAQ_Entity d);

}
