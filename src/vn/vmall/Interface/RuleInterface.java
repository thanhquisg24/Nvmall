package vn.vmall.Interface;

import vn.vmall.Entity.RuleModel;



public interface RuleInterface {

	public RuleModel get_regulartion();
	public RuleModel get_scheme();
	public int saverule(String id, String content_page);
	
}
