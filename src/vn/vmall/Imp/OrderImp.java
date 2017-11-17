package vn.vmall.Imp;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.vmall.Helper.Extra;
import vn.vmall.DAL.OrderDAL;
import vn.vmall.Entity.Brand_Entity;
import vn.vmall.Entity.OrderDetail_Entity;
import vn.vmall.Entity.Order_Entity;
import vn.vmall.Helper.EncrypterDecrypter;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.Interface.OrderInterface;
import vn.vmall.Service.ReadServiceMail;


@Component(value="OrderImp")
public class OrderImp implements OrderInterface {

	@Autowired
	private OrderDAL DAL;
	
	@Override
	public List<Order_Entity> get_list_search_pagg(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.get_list_search_pagg(searchmodel);
	}

	@Override
	public int count_get_list_search_pagg(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.count_get_list_search_pagg(searchmodel);
	}

	@Override
	public int add_update_Order(String ptype, Order_Entity d) {
		// TODO Auto-generated method stub
		return DAL.add_update_Order(ptype, d);

	}

	@Override
	public Order_Entity get_order_by_id(String id) {
		// TODO Auto-generated method stub
		return DAL.get_order_by_id(id);
	}

	@Override
	public int delete_Order(String str_id) {
		// TODO Auto-generated method stub
		return DAL.delete_Order(str_id);
	}

	@Override
	public int visivled_Order(String str_id,String visible) {
		// TODO Auto-generated method stub
		return DAL.visivled_Order(str_id,visible);
	}
	
	@Override
	public int set_order_status(String str_id,String status) {
		// TODO Auto-generated method stub
		return DAL.set_order_status(str_id,status);
	}
	
	@Override
	public List<Order_Entity> get_order_detail(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.get_order_detail(searchmodel);
	}
	
	@Override
	public int count_get_order_detail(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.count_get_order_detail(searchmodel);
	}
	@Override
	public int send_mail_delivered(String order_id,HttpServletRequest req) throws IOException, ClassNotFoundException, InstantiationException, SQLException{
		// TODO Auto-generated method stub
		int _result=1;
		
		Order_Entity item = new Order_Entity();
		item = get_order_by_id(order_id);
		String email = item.getEmail();
		System.out.println(email);
		// get content txt
		String urltext = req.getRealPath("/upload/txt");
		urltext += "/order_delivered.txt";
		String content_text = Extra.readFile(urltext);
		System.out.println(content_text);
		//get order date
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date order_date = null;
		try {
			order_date = dt.parse(item.getOrder_date().toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//set content
		content_text = content_text.replaceAll("@orderid", order_id);
		content_text = content_text.replaceAll("@customer_name", item.getCustomer_name());
		content_text = content_text.replace("@date_order", dt.format(order_date));
		content_text = content_text.replace("@customer_email",
				order_id.equals(item.getEmail()) ? "NO" : item.getEmail());
		content_text = content_text.replaceAll("@phone", item.getPhone());
		content_text = content_text.replace("@customer_address", item.getAddress_delivery());
		content_text = content_text.replace(" @customer_payment", (item.getPayment_method_id().equals("01") ? "Trực tiếp" : "Ngân lượng"));
		//set order detail
		List<OrderDetail_Entity> list = new ArrayList<OrderDetail_Entity>();
		list = get_order_detail_byid(order_id);
		String order_content = "";
		float total_amount = 0;
		for (OrderDetail_Entity detail : list) {
			String row = "<tr>";
			row += "<td>" + detail.getProduct_name() + "</td><td>"
					+ Extra.format_currency(String.valueOf(detail.getPrice())) + " VND </td><td>" + detail.getQuantity()
					+ "</td><td>0</td><td>" + Extra.format_currency(String.valueOf(detail.getAmount())) + " VND</td>";
			row += "</tr>";
			order_content += row;
			total_amount += detail.getAmount();
		}
		content_text = content_text.replace("@content_order", order_content);
		content_text = content_text.replace("@customer_amount",
				Extra.format_currency(String.valueOf(total_amount)) + " VND");
		
		//send mail
		String title = "Đơn hàng đã sẵn sàng để giao tới quý khách " + item.getCustomer_name() + " !";
		if(!email.equals(order_id)){
			int rs = ReadServiceMail.SendingFromgmail(email, title, content_text, req);
			return rs;
		}
		return 1;
	}

	@Override
	public List<OrderDetail_Entity> get_order_detail_byid(String order_id) throws ClassNotFoundException, InstantiationException, SQLException {
		// TODO Auto-generated method stub
		return DAL.get_order_detail_byid(order_id);
	}
	
	
}
