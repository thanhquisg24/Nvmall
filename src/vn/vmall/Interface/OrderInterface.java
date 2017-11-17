package vn.vmall.Interface;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import vn.vmall.Entity.Brand_Entity;
import vn.vmall.Entity.OrderDetail_Entity;
import vn.vmall.Entity.Order_Entity;
import vn.vmall.Entity.Product_Entity;
import vn.vmall.Helper.SearchPaggModel;
public interface OrderInterface {

	List<Order_Entity> get_list_search_pagg(SearchPaggModel searchmodel);

	int count_get_list_search_pagg(SearchPaggModel searchmodel);

	int add_update_Order(String ptype, Order_Entity d);

	Order_Entity get_order_by_id(String id);

	int delete_Order(String str_id);

	int visivled_Order(String str_id, String visible);
	
	int set_order_status(String str_id,String status);
	
	List<Order_Entity> get_order_detail(SearchPaggModel searchmodel);
	
	int count_get_order_detail(SearchPaggModel searchmodel);
	
	int send_mail_delivered(String order_id,HttpServletRequest req) throws IOException, ClassNotFoundException, InstantiationException, SQLException;
	
	List<OrderDetail_Entity> get_order_detail_byid(String order_id) throws ClassNotFoundException, InstantiationException, SQLException;
	
}
