package vn.vmall.DAL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import vn.vmall.Entity.*;
import vn.vmall.Helper.EncrypterDecrypter;

@Component
public class ProductDAL {
	public static List<Product_Entity> get_product_recommend(String product_type) {
		List<Product_Entity> list = new ArrayList<Product_Entity>();
		try {
			String spname = "sp_get_product_recommend";
			String[] p_field = { "p_product_type_vmall" };
			Object[] p_values = { product_type };
			ResultSet rs = null;
			rs = ConnectDB.ExecBoFunctionReturnResutlSet(spname, p_field, p_values);
			while (rs.next()) {
				Product_Entity item = new Product_Entity();
				item.setProductId(EncrypterDecrypter
						.encodeString(rs.getString("customer_id") + "-" + rs.getString("product_id")));
				item.setProductName(rs.getString("product_name"));
				item.setCustomer_id(rs.getString("customer_id").toString());
				item.setProductImage(rs.getString("domain") + rs.getString("project_name").toString()
						+ "/upload/product/" + rs.getString("product_image").toString());
				item.setProductPrice(
						rs.getString("product_price_3") == "" ? 0 : Float.parseFloat(rs.getString("product_price_3")));
				list.add(item);
			}

		} catch (Exception ex) {

		}
		return list;
	}

	public static List<Product_Entity> get_product_care(String product_type) {
		List<Product_Entity> list = new ArrayList<Product_Entity>();
		try {
			String spname = "sp_get_product_qt";
			String[] p_field = { "p_product_type_vmall" };
			Object[] p_values = { product_type };
			ResultSet rs = null;
			rs = ConnectDB.ExecBoFunctionReturnResutlSet(spname, p_field, p_values);
			while (rs.next()) {
				Product_Entity item = new Product_Entity();
				item.setProductId(EncrypterDecrypter
						.encodeString(rs.getString("customer_id") + "-" + rs.getString("product_id")));
				item.setProductName(rs.getString("product_name"));
				item.setCustomer_id(rs.getString("customer_id").toString());
				item.setProductImage(rs.getString("domain") + rs.getString("project_name").toString()
						+ "/upload/product/" + rs.getString("product_image").toString());
				item.setProductPrice(
						rs.getString("product_price_3") == "" ? 0 : Float.parseFloat(rs.getString("product_price_3")));
				list.add(item);
			}

		} catch (Exception ex) {

		}
		return list;
	}

	public static List<Product_Entity> get_product_care_index() {
		List<Product_Entity> list = new ArrayList<Product_Entity>();
		try {
			String spname = "sp_get_product_qt_customer";
			String[] p_field = { "p_customer" };
			Object[] p_values = { "" };
			ResultSet rs = null;
			rs = ConnectDB.ExecBoFunctionReturnResutlSet(spname, p_field, p_values);
			while (rs.next()) {
				Product_Entity item = new Product_Entity();
				item.setProductId(EncrypterDecrypter
						.encodeString(rs.getString("customer_id") + "-" + rs.getString("product_id")));
				item.setProductName(rs.getString("product_name"));
				item.setCustomer_id(rs.getString("customer_id").toString());
				item.setProductImage(rs.getString("domain") + rs.getString("project_name").toString()
						+ "/upload/product/" + rs.getString("product_image").toString());
				item.setProductPrice(
						rs.getString("product_price_3") == "" ? 0 : Float.parseFloat(rs.getString("product_price_3")));
				list.add(item);
			}

		} catch (Exception ex) {

		}
		return list;
	}

	public static List<Product_Entity> get_product_sell_best() {
		List<Product_Entity> list = new ArrayList<Product_Entity>();
		try {
			String query = "select * from vw_product_sell_best";
			ResultSet rs = null;
			rs = ConnectDB.GetDataReturnResultSet(query);
			while (rs.next()) {
				Product_Entity item = new Product_Entity();
				item.setProductId(EncrypterDecrypter
						.encodeString(rs.getString("customer_id") + "-" + rs.getString("product_id")));
				item.setProductName(rs.getString("product_name"));
				item.setCustomer_id(rs.getString("customer_id").toString());
				item.setProductImage(rs.getString("domain") + rs.getString("project_name").toString()
						+ "/upload/product/" + rs.getString("product_image").toString());
				item.setProductPrice(
						rs.getString("product_price_3") == "" ? 0 : Float.parseFloat(rs.getString("product_price_3")));
				list.add(item);
			}

		} catch (Exception ex) {

		}
		return list;
	}

	public static List<Product_Entity> search_product_by_category(String product_type, String brand, String category,
			String price, String color, String property, int row) {
		List<Product_Entity> list = new ArrayList<Product_Entity>();
		try {
			//
			if (category.length() > 0) {
				String strcate = "";
				String[] arr = category.split(",");
				if (arr.length > 0) {
					for (int i = 0; i < arr.length; i++) {
						strcate += "'" + EncrypterDecrypter.decodeCategory(arr[i]) + "',";
					}
					if (strcate.length() > 0) {
						strcate = strcate.substring(0, strcate.length() - 1);
					}
					category = strcate;
				}
			}
			//
			String spname = "sp_search_product";
			String[] pf = { "p_parent", "p_brand", "p_category", "p_price", "p_color", "p_property", "p_row" };
			Object[] pv = { product_type, brand, category, price, color, property, row };
			System.out.println(product_type +"\t" +brand +"\t"  +category +"\t"  +price +"\t"  +color +"\t"  +property +"\t" +row);
			ResultSet rs = null;
			rs = ConnectDB.ExecBoFunctionReturnResutlSet(spname, pf, pv);
			while (rs.next()) {
				Product_Entity item = new Product_Entity();
				item.setProductId(EncrypterDecrypter
						.encodeString(rs.getString("customer_id") + "-" + rs.getString("product_id")));
				item.setProductName(rs.getString("product_name"));
				item.setCustomer_id(rs.getString("customer_id").toString());
				item.setProductImage(rs.getString("domain") + rs.getString("project_name").toString()
						+ "/upload/product/" + rs.getString("product_image").toString());
				item.setProductPrice(
						rs.getString("product_price_3") == "" ? 0 : Float.parseFloat(rs.getString("product_price_3")));
				item.setNewPrice(
						rs.getString("product_price") == "" ? 0 : Float.parseFloat(rs.getString("product_price")));
				item.setIsPromo(Integer.parseInt(rs.getString("ispromo")));
				item.setShop_name(rs.getString("shop_name"));
				String url_shop = "";
				if (ExtraDAL.is_publish()) {
					url_shop = rs.getString("domain") + "index.html";
				} else {
					url_shop = rs.getString("domain") + rs.getString("project_name").toString() + "/index.html";
				}
				item.setUrl_shop(url_shop);
				item.setNum_view(rs.getString("num_view") == null ? 0 : Float.parseFloat(rs.getString("num_view")));
				item.setNum_buy(rs.getString("num_buy") == null ? 0 : Float.parseFloat(rs.getString("num_buy")));
				list.add(item);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public static List<Product_Entity> get_product_recomment_product_type(String product_type) {

		List<Product_Entity> list = new ArrayList<Product_Entity>();
		try {
			String spname = "sp_get_product_recomment_product_type";
			String[] p_field = { "p_type" };
			Object[] p_values = { product_type };
			ResultSet rs = null;
			rs = ConnectDB.ExecBoFunctionReturnResutlSet(spname, p_field, p_values);
			while (rs.next()) {
				Product_Entity item = new Product_Entity();
				item.setProductId(EncrypterDecrypter
						.encodeString(rs.getString("customer_id") + "-" + rs.getString("product_id")));
				item.setProductName(rs.getString("product_name"));
				item.setCustomer_id(rs.getString("customer_id").toString());
				item.setProductImage(rs.getString("domain") + rs.getString("project_name").toString()
						+ "/upload/product/" + rs.getString("product_image").toString());
				item.setProductPrice(
						rs.getString("product_price_3") == "" ? 0 : Float.parseFloat(rs.getString("product_price_3")));
				list.add(item);
			}

		} catch (Exception ex) {

		}
		return list;
	}

	public static List<Product_Entity> get_product_qt_product_type(String product_type) {
		List<Product_Entity> list = new ArrayList<Product_Entity>();
		try {
			String spname = "sp_get_product_qt_product_type";
			String[] p_field = { "p_product_type" };
			Object[] p_values = { product_type };
			ResultSet rs = null;
			rs = ConnectDB.ExecBoFunctionReturnResutlSet(spname, p_field, p_values);
			while (rs.next()) {
				Product_Entity item = new Product_Entity();
				item.setProductId(EncrypterDecrypter
						.encodeString(rs.getString("customer_id") + "-" + rs.getString("product_id")));
				item.setProductName(rs.getString("product_name"));
				item.setCustomer_id(rs.getString("customer_id").toString());
				item.setProductImage(rs.getString("domain") + rs.getString("project_name").toString()
						+ "/upload/product/" + rs.getString("product_image").toString());
				item.setProductPrice(
						rs.getString("product_price_3") == "" ? 0 : Float.parseFloat(rs.getString("product_price_3")));
				list.add(item);
			}

		} catch (Exception ex) {

		}
		return list;
	}

	public static Product_Entity get_info_product(String customer_id, String product_id) {
		Product_Entity item = new Product_Entity();
		String query = "SELECT tb1.customer_id, tb1.product_id, tb1.product_name,tb1.product_price,tb1.product_des,tb1.product_image,"
				+ " tb2.domain,tb2.project_name" + " FROM tb_product tb1,tb_customer tb2"
				+ " WHERE tb1.customer_id = tb2.id" + " AND tb1.product_id = '" + product_id + "'"
				+ " AND tb1.customer_id= '" + customer_id + "'";
		ResultSet rs = null;
		try {

			rs = ConnectDB.GetDataReturnResultSet(query);
			while (rs.next()) {
				String urlimg = rs.getString("domain") + rs.getString("project_name") + "/upload/product/"
						+ rs.getString("product_image");
				item.setCustomer_id(rs.getString("customer_id"));
				item.setProductId(EncrypterDecrypter.encodeString(customer_id + "-" + product_id));
				item.setProductName(rs.getString("product_name"));
				item.setProductPrice(rs.getFloat("product_price"));
				item.setProductDes(rs.getString("product_des"));
				item.setProductImage(urlimg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return item;
	}

	public static String get_value_option(String type, String id) {
		String result = "";
		if (type.equals("property")) {
			String[] arr_id = id.split("-");
			for (String str_id : arr_id) {
				String temp = get_property_value(str_id);
				result += temp;
				result = result + "<br>";
			}
		}
		if (type.equals("color")) {
			result = get_color_value(id);
		}

		return result;
	}

	public static String get_property_value(String id) {
		String result = "";
		String query = "SELECT * FROM (SELECT tb2.ID,tb2.property_name,tb1.ID parent,tb1.property_name parent_name "
				+ " FROM tb_property tb1,(SELECT ID,property_name,parent FROM tb_property " + " WHERE ID='" + id
				+ "' ) tb2" + " WHERE tb2.parent = tb1.ID) A";
		ResultSet rs = null;
		try {
			ConnectDB conn = new ConnectDB();
			rs = conn.GetDataReturnResultSet(query);
			while (rs.next()) {
				result = rs.getString("parent_name") + " : " + rs.getString("property_name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String get_color_value(String id) {
		String result = "";
		String query = "SELECT ID,product_type_sub_id,color_name,color" + " FROM tb_color WHERE ID='" + id + "'";
		ResultSet rs = null;
		try {
			ConnectDB conn = new ConnectDB();
			rs = conn.GetDataReturnResultSet(query);
			while (rs.next()) {
				result = rs.getString("color");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int remove_product_later(String email, String customer_id, String product_id, String property,
			String color) {
		int result = -2;

		try {
			String query = "";
			query = "SELECT * FROM tb_product_later " + " WHERE email ='" + email + "' AND customer_id='" + customer_id
					+ "'" + " AND product_id='" + product_id + "' AND property='" + property + "' AND color='" + color
					+ "'";
			ResultSet rs = null;
			rs = ConnectDB.GetDataReturnResultSet(query);
			while (rs.next()) {
				query = "DELETE FROM tb_product_later " + " WHERE email ='" + email + "' AND customer_id='"
						+ customer_id + "'" + " AND product_id='" + product_id + "' AND property='" + property
						+ "' AND color='" + color + "'";
				result = ConnectDB.ExecUpdate(query);
			}

		} catch (Exception ex) {
			result = -1;
		}
		return result;
	}

	public static List<Product_Entity> search_product_by_brand(String brand, int row) {
		List<Product_Entity> list = new ArrayList<Product_Entity>();
		try {

			//
			String spname = "sp_search_product_by_brand";
			String[] pf = { "p_brand", "p_row" };
			Object[] pv = { brand, row };
			ResultSet rs = null;
			rs = ConnectDB.ExecBoFunctionReturnResutlSet(spname, pf, pv);
			while (rs.next()) {
				Product_Entity item = new Product_Entity();
				item.setProductId(EncrypterDecrypter
						.encodeString(rs.getString("customer_id") + "-" + rs.getString("product_id")));
				item.setProductName(rs.getString("product_name"));
				item.setCustomer_id(rs.getString("customer_id").toString());
				item.setProductImage(rs.getString("domain") + rs.getString("project_name").toString()
						+ "/upload/product/" + rs.getString("product_image").toString());
				item.setProductPrice(
						rs.getString("product_price_3") == "" ? 0 : Float.parseFloat(rs.getString("product_price_3")));
				item.setNewPrice(
						rs.getString("product_price") == "" ? 0 : Float.parseFloat(rs.getString("product_price")));
				item.setIsPromo(Integer.parseInt(rs.getString("ispromo")));
				item.setShop_name(rs.getString("shop_name"));
				String url_shop = "";
				if (ExtraDAL.is_publish()) {
					url_shop = rs.getString("domain") + "index.html";
				} else {
					url_shop = rs.getString("domain") + rs.getString("project_name").toString() + "/index.html";
				}
				item.setUrl_shop(url_shop);
				item.setNum_view(rs.getString("num_view") == null ? 0 : Float.parseFloat(rs.getString("num_view")));
				item.setNum_buy(rs.getString("num_buy") == null ? 0 : Float.parseFloat(rs.getString("num_buy")));
				list.add(item);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public static int save_session_product(String product, String sessionid, String type) {
		int _result = 0;
		try {
			String spname = "sp_save_session_product";
			String[] pfield = { "f", "p_product", "p_sessionid", "p_type" };

			Object[] pvalues = { "", product, sessionid, type };
			String[] ptype = { "INT", "VARCHAR", "VARCHAR", "VARCHAR" };
			int[] pdirec = { 1, 0, 0, 0 };
			Map<String, Object> mapOfObjects = new HashMap<String, Object>();
			mapOfObjects = ConnectDB.ExecBoFunctionReturnList(spname, pfield, ptype, pvalues, pdirec);
			_result = Integer.parseInt(mapOfObjects.get("f").toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			_result = -1;
		}
		return _result;
	}

	public static List<Product_Entity> get_product_by_category(String produt_type_vmall) {
		List<Product_Entity> list = new ArrayList<Product_Entity>();
		try {
			String spname = "sp_get_product_by_category";
			String[] p_field = { "p_produt_type_vmall" };
			Object[] p_values = { produt_type_vmall };
			ResultSet rs = null;
			rs = ConnectDB.ExecBoFunctionReturnResutlSet(spname, p_field, p_values);
			while (rs.next()) {
				Product_Entity item = new Product_Entity();
				item.setProductId(EncrypterDecrypter
						.encodeString(rs.getString("customer_id") + "-" + rs.getString("product_id")));
				item.setProductName(rs.getString("product_name"));
				item.setCustomer_id(rs.getString("customer_id").toString());
				item.setProductImage(rs.getString("domain") + rs.getString("project_name").toString()
						+ "/upload/product/" + rs.getString("product_image").toString());
				item.setProductPrice(
						rs.getString("product_price") == "" ? 0 : Float.parseFloat(rs.getString("product_price")));
				list.add(item);
			}

		} catch (Exception ex) {

		}
		return list;
	}

	public static void main(String[] args) {
		List<Product_Entity> list = new ArrayList<Product_Entity>();
		list = get_product_by_category("001");
		System.out.println(new Gson().toJson(list));
	}
}
