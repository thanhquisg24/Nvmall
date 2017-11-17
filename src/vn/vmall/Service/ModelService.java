package vn.vmall.Service;


public class ModelService {
	public static String master_server = "http://10.0.10.21:8080/";
	
	public static String get_url_product_membership(){
		String url = master_server+"SrvMembershipB/RestSrv/";
		return url;
	}
}
