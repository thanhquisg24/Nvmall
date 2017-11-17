package vn.vmall.DAL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import vn.vmall.Entity.Brand_Entity;
import vn.vmall.Entity.CategoryNew_Entity;
import vn.vmall.Entity.CatgorySub_Entity;
import vn.vmall.Entity.Catgory_Entity;
import vn.vmall.Entity.Color_Entity;
import vn.vmall.Entity.ItemCategoryProperty;
import vn.vmall.Entity.Level_price_vmall_Entity;
import vn.vmall.Entity.Property_Entity;
import vn.vmall.Helper.EncrypterDecrypter;
import vn.vmall.Helper.ResultSetMapper;
import vn.vmall.Helper.SearchPaggModel;

@Component
public class CatgoryDAL {

	public List<Catgory_Entity> get_list_search_pagg(SearchPaggModel searchmodel) {
		List<Catgory_Entity> list = null;
		// String query="select * from tb_democrud limit
		// "+searchmodel.getOffset()+","+searchmodel.getRows();
		try {
			ResultSet rs = null;
			String spname = "search_catgory";
			String[] pfield = { "p_offset", "p_rows", "p_col", "p_val" };
			Object[] pvalues = { searchmodel.getOffset(), searchmodel.getRows(), searchmodel.getCol(),
					searchmodel.getVal() };
			rs = ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			// rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<Catgory_Entity> resultSetMapper = new ResultSetMapper<Catgory_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs, Catgory_Entity.class);

		} catch (Exception ex) {
			// System.out.println("CatgoryDAL error:"+ex);
		}
		if (list == null) {
			list = new ArrayList<Catgory_Entity>();
		}
		return list;

	}

	public int count_get_list_search_pagg(SearchPaggModel searchmodel) {
		int count = 0;
		try {
			ResultSet rs = null;
			String spname = "search_catgory_count_total";
			String[] pfield = { "p_col", "p_val" };
			Object[] pvalues = { searchmodel.getCol(), searchmodel.getVal() };
			rs = ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			// rs = ConnectDB.GetDataReturnResultSet(query);
			rs.first();
			count = rs.getInt("count");

		} catch (Exception ex) {
			// System.out.println("CatgoryDAL error:"+ex);
		}
		return count;
	}

	public int add_update_catgory(String type, Catgory_Entity d) {
		int _result = 0;
		try {
			String spname = "sp_catgory_insert_update";
			String[] pfield = { "f", "p_type", "p_product_type_vmall", "p_product_type_name", "p_category_img",
					"p_title_img" };
			String[] ptype = { "INT", "VARCHAR", "VARCHAR", "VARCHAR", "VARCHAR", "VARCHAR" };
			Object[] pvalues = { "", type, d.getProduct_type_vmall(), d.getProduct_type_name(), d.getCategory_img(),
					d.getTitle_img() };
			int[] pdirec = { 1, 0, 0, 0, 0, 0 };
			Map<String, Object> mapOfObjects = new HashMap<String, Object>();
			mapOfObjects = ConnectDB.ExecBoFunctionReturnList(spname, pfield, ptype, pvalues, pdirec);
			_result = Integer.parseInt(mapOfObjects.get("f").toString());
		} catch (Exception e) {
			e.printStackTrace();
			_result = -1;
		}
		return _result;

	}

	public Catgory_Entity get_catgory_by_id(String id) {
		Catgory_Entity m = null;
		String query = "SELECT product_type_vmall, product_type_name, category_img, title_img, isvisible, isdelete, isstate, issyn "
				+ " FROM tb_product_type_vmall where product_type_vmall='" + id + "'";
		try {
			ResultSet rs = null;
			ResultSetMapper<Catgory_Entity> resultSetMapper = new ResultSetMapper<Catgory_Entity>();
			rs = ConnectDB.GetDataReturnResultSet(query);
			m = resultSetMapper.mapRersultSetToObject_singlerow(rs, Catgory_Entity.class);

		} catch (Exception ex) {

		}

		// System.out.print(mem.toString());
		return m;

	}

	public int delete_catgory(String str_id) {
		int _result = 0;
		try {
			String spname = "sp_catgory_delete";
			String[] pfield = { "f", "p_str_id" };
			String[] ptype = { "INT", "VARCHAR" };
			Object[] pvalues = { "", str_id };
			int[] pdirec = { 1, 0 };
			Map<String, Object> mapOfObjects = new HashMap<String, Object>();
			mapOfObjects = ConnectDB.ExecBoFunctionReturnList(spname, pfield, ptype, pvalues, pdirec);
			_result = Integer.parseInt(mapOfObjects.get("f").toString());
		} catch (Exception e) {
			e.printStackTrace();
			_result = -1;
		}
		return _result;
	}

	public int visivled_catgory(String str_id, String visible) {
		int _result = 0;
		try {
			String spname = "sp_catgory_visibled";
			String[] pfield = { "f", "p_str_id", "p_visible" };
			String[] ptype = { "INT", "VARCHAR", "VARCHAR" };
			Object[] pvalues = { "", str_id, visible };
			int[] pdirec = { 1, 0, 0 };
			Map<String, Object> mapOfObjects = new HashMap<String, Object>();
			mapOfObjects = ConnectDB.ExecBoFunctionReturnList(spname, pfield, ptype, pvalues, pdirec);
			_result = Integer.parseInt(mapOfObjects.get("f").toString());
		} catch (Exception e) {
			e.printStackTrace();
			_result = -1;
		}
		return _result;
	}

	public List<Catgory_Entity> get_allcatgory() {
		List<Catgory_Entity> list = null;
		String query = "SELECT product_type_vmall, product_type_name, category_img, title_img, isvisible, isdelete, isstate, issyn "
				+ " FROM tb_product_type_vmall" + " WHERE isvisible= 1";
		try {
			ResultSet rs = null;

			rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<Catgory_Entity> resultSetMapper = new ResultSetMapper<Catgory_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs, Catgory_Entity.class);

		} catch (Exception ex) {
			// System.out.println("CatgoryDAL error:"+ex);
		}

		if (list == null) {
			list = new ArrayList<Catgory_Entity>();
		}
		return list;
	}
	public static void main(String[] args) {
		ArrayList<CatgorySub_Entity> list_sub = new ArrayList<CatgorySub_Entity>();
		list_sub = get_sub_category("002");
		System.out.println(new Gson().toJson(list_sub));
	}
	public static List<Catgory_Entity> get_category() {
		List<Catgory_Entity> list = new ArrayList<Catgory_Entity>();
		String query = "SELECT product_type_vmall, product_type_name,icon,isnew, category_img, title_img, isvisible, isdelete, isstate, issyn "
				+ " FROM tb_product_type_vmall" + " WHERE isvisible = true and isdelete = false";
		try {
			ResultSet rs = null;
			rs = ConnectDB.GetDataReturnResultSet(query);
			while (rs.next()) {
				Catgory_Entity item = new Catgory_Entity();
				item.setProduct_type_vmall(
						EncrypterDecrypter.encodeCategory(rs.getString("product_type_vmall").toString()));
				item.setProduct_type_name(rs.getString("product_type_name").toString());
				item.setIcon(rs.getString("icon"));
				item.setIsnew(rs.getString("isnew"));
				ArrayList<CatgorySub_Entity> list_sub = new ArrayList<CatgorySub_Entity>();
				list_sub = get_sub_category(rs.getString("product_type_vmall").toString());
				item.setList_cate_sub(list_sub);
				ArrayList<Brand_Entity> listBrand = new ArrayList<Brand_Entity>();
				listBrand = BrandDAL.get_brand_by_vmall(EncrypterDecrypter.encodeCategory(rs.getString("product_type_vmall").toString()));
				item.setList_brand(listBrand);
				list.add(item);
			}
		} catch (Exception ex) {
			System.out.println("CatgoryDAL error:" + ex);
		}

		return list;
	}

	public static List<Catgory_Entity> get_category_index() {
		List<Catgory_Entity> list = new ArrayList<Catgory_Entity>();
		String query = "SELECT product_type_vmall, product_type_name,icon, category_img, title_img, isvisible, isdelete, isstate, issyn "
				+ " FROM tb_product_type_vmall" + " WHERE isvisible = true and isdelete = false";
		try {
			ResultSet rs = null;
			rs = ConnectDB.GetDataReturnResultSet(query);
			while (rs.next()) {
				Catgory_Entity item = new Catgory_Entity();
				item.setProduct_type_vmall(
						EncrypterDecrypter.encodeCategory(rs.getString("product_type_vmall").toString()));
				item.setIcon(rs.getString("icon"));
				item.setProduct_type_name(rs.getString("product_type_name").toString());
				item.setList_item_big(get_sub_category_top_8(rs.getString("product_type_vmall").toString(), 0, 1));
				item.setList_slide(get_sub_category_top_8(rs.getString("product_type_vmall").toString(), 1, 8));
				item.setList_top(get_sub_category_top_8(rs.getString("product_type_vmall").toString(), 9, 5));
				item.setList_p(get_sub_category_top_8(rs.getString("product_type_vmall").toString(), 14, 6));
				item.setList_branch(get_branch_by_product_type(rs.getString("product_type_vmall").toString()));
				list.add(item);
			}
		} catch (Exception ex) {
			System.out.println("CatgoryDAL error:" + ex);
		}

		return list;
	}

	public static List<Catgory_Entity> get_category_by_id(String id) {
		List<Catgory_Entity> list = new ArrayList<Catgory_Entity>();
		String query = "SELECT product_type_vmall, product_type_name, category_img, title_img, isvisible, isdelete, isstate, issyn "
				+ " FROM tb_product_type_vmall"
				+ " WHERE isvisible = true and isdelete = false and product_type_vmall = '" + id + "'";
		try {
			ResultSet rs = null;
			rs = ConnectDB.GetDataReturnResultSet(query);
			while (rs.next()) {
				Catgory_Entity item = new Catgory_Entity();
				item.setProduct_type_vmall(
						EncrypterDecrypter.encodeCategory(rs.getString("product_type_vmall").toString()));
				item.setProduct_type_name(rs.getString("product_type_name").toString());
				ArrayList<CatgorySub_Entity> list_sub = new ArrayList<CatgorySub_Entity>();
				list_sub = get_sub_category(rs.getString("product_type_vmall").toString());
				item.setList_cate_sub(list_sub);
				list.add(item);
			}
		} catch (Exception ex) {
			System.out.println("CatgoryDAL error:" + ex);
		}
		return list;
	}

	public static ArrayList<CatgorySub_Entity> get_sub_category_top_8(String category, int from, int row) {
		ArrayList<CatgorySub_Entity> list = new ArrayList<CatgorySub_Entity>();
		;
		String query = "select B.product_type_id, B.product_type_name,B.category_img,A.product_type_id as parent ";
		query += "from (select product_type_id,product_type_vmall from tb_product_type_sub where parent ='0' and isvisible = true and isdelete = false)A, ";
		query += "(select product_type_id,product_type_name,parent,category_img from tb_product_type_sub where parent !='0'  and isvisible = true and isdelete = false)B ";
		query += "where A.product_type_id = B.parent ";
		query += "and A.product_type_vmall = '" + category + "' limit " + from + "," + row + "";
		try {
			ResultSet rs = null;
			rs = ConnectDB.GetDataReturnResultSet(query);
			while (rs.next()) {
				CatgorySub_Entity item = new CatgorySub_Entity();
				item.setProduct_type_id(EncrypterDecrypter.encodeCategory(rs.getString("product_type_id").toString()));
				item.setProduct_type_name(rs.getString("product_type_name").toString());
				item.setCategory_img(rs.getString("category_img"));
				ArrayList<CatgorySub_Entity> list_sub = new ArrayList<CatgorySub_Entity>();
				list_sub = get_sub_category(category, rs.getString("product_type_id").toString());
				item.setList_cate_sub(list_sub);
				item.setParent(EncrypterDecrypter.encodeCategory(rs.getString("parent")));
				list.add(item);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public static ArrayList<CatgorySub_Entity> get_sub_category(String category) {
		ArrayList<CatgorySub_Entity> list = new ArrayList<CatgorySub_Entity>();
		;
		String query = "SELECT product_type_id, product_type_name,category_img " 
				+ " FROM tb_product_type_sub "
				+ " WHERE product_type_vmall = '" + category
				+ "' and isvisible = true and isdelete = false and parent = '0'  ";
		try {
			ResultSet rs = null;
			rs = ConnectDB.GetDataReturnResultSet(query);
			while (rs.next()) {
				CatgorySub_Entity item = new CatgorySub_Entity();
				item.setProduct_type_id(EncrypterDecrypter.encodeCategory(rs.getString("product_type_id").toString()));
				item.setProduct_type_name(rs.getString("product_type_name").toString());
				item.setCategory_img(rs.getString("category_img"));
				ArrayList<CatgorySub_Entity> list_sub = new ArrayList<CatgorySub_Entity>();
				list_sub = get_sub_category(category, rs.getString("product_type_id").toString());
				item.setList_cate_sub(list_sub);			
				list.add(item);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public static ArrayList<CatgorySub_Entity> get_sub_category(String category, String type) {
		ArrayList<CatgorySub_Entity> list = new ArrayList<CatgorySub_Entity>();
		String query = "SELECT product_type_id, product_type_name,category_img,parent "
				+ " FROM tb_product_type_sub"
				+ " WHERE product_type_vmall = '" + category
				+ "' and isvisible = true and isdelete = false and parent = '" + type + "' ";
		try {
			ResultSet rs = null;
			rs = ConnectDB.GetDataReturnResultSet(query);
			while (rs.next()) {
				CatgorySub_Entity item = new CatgorySub_Entity();
				item.setProduct_type_id(EncrypterDecrypter.encodeCategory(rs.getString("product_type_id").toString()));
				item.setCategory_img(rs.getString("category_img"));
				item.setProduct_type_name(rs.getString("product_type_name").toString());
				item.setList_branch(get_branch_top_1(rs.getString("product_type_id").toString()));
				item.setParent(EncrypterDecrypter.encodeCategory(rs.getString("parent")));
				list.add(item);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public static ArrayList<Brand_Entity> get_branch_top_1(String product_type_id) {
		ArrayList<Brand_Entity> list = new ArrayList<Brand_Entity>();
		String query_name = "002";
		String[] pf = { "p_product_type_id" };
		String[] pv = { product_type_id };
		try {
			ResultSet rs = null;
			rs = ConnectDB.GetDataDirecReturnResultSet(query_name, pf, pv);
			while (rs.next()) {
				Brand_Entity item = new Brand_Entity();
				item.setId(rs.getString("id"));
				item.setName(rs.getString("name"));
				item.setImage(rs.getString("image"));
				list.add(item);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public static ArrayList<Brand_Entity> get_branch_by_product_type(String product_type_id) {

		ArrayList<Brand_Entity> list = new ArrayList<Brand_Entity>();
		String query_name = "001";
		String[] pf = { "p_product_type_vmall" };
		String[] pv = { product_type_id };
		try {
			ResultSet rs = null;
			rs = ConnectDB.GetDataDirecReturnResultSet(query_name, pf, pv);
			while (rs.next()) {
				Brand_Entity item = new Brand_Entity();
				item.setId(EncrypterDecrypter.encodeCategory(rs.getString("id")));
				item.setName(rs.getString("name"));
				item.setImage(rs.getString("image"));
				list.add(item);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public static List<Catgory_Entity> get_category_summary() {
		List<Catgory_Entity> list = new ArrayList<Catgory_Entity>();
		String query = "SELECT product_type_vmall, product_type_name, category_img, title_img, isvisible, isdelete, isstate, issyn "
				+ " FROM tb_product_type_vmall" + " WHERE isvisible = true and isdelete = false";
		try {
			ResultSet rs = null;
			rs = ConnectDB.GetDataReturnResultSet(query);
			while (rs.next()) {
				Catgory_Entity item = new Catgory_Entity();
				item.setProduct_type_vmall(
						EncrypterDecrypter.encodeCategory(rs.getString("product_type_vmall").toString()));
				item.setProduct_type_name(rs.getString("product_type_name").toString());
				item.setList_slide(get_sub_category_top_8(rs.getString("product_type_vmall").toString(), 0, 5));
				list.add(item);
			}
		} catch (Exception ex) {
			System.out.println("CatgoryDAL error:" + ex);
		}

		return list;
	}

	public static List<Brand_Entity> get_brand() {
		List<Brand_Entity> list = new ArrayList<Brand_Entity>();
		String query = "SELECT id, name, image FROM brand where isvisible = true and isdelete = false";
		try {
			ResultSet rs = null;
			rs = ConnectDB.GetDataReturnResultSet(query);
			while(rs.next()){
				Brand_Entity item = new Brand_Entity();
				item.setId(EncrypterDecrypter.encodeCategory(rs.getString("id")));
				item.setName(rs.getString("name"));
				item.setImage(rs.getString("image"));
				list.add(item);
			}
			
		} catch (Exception ex) {
			// System.out.println("BranchDAL error:"+ex);
		}
		return list;
	}

	public static List<Catgory_Entity> get_category_product_by_id(String product_type_vmall, String product_type_id) {
		List<Catgory_Entity> list = new ArrayList<Catgory_Entity>();
		String query = "SELECT product_type_vmall, product_type_name, category_img, title_img, isvisible, isdelete, isstate, issyn "
				+ " FROM tb_product_type_vmall"
				+ " WHERE isvisible = true and isdelete = false and product_type_vmall = '" + product_type_vmall + "'";
		try {
			ResultSet rs = null;
			rs = ConnectDB.GetDataReturnResultSet(query);
			while (rs.next()) {
				Catgory_Entity item = new Catgory_Entity();
				item.setProduct_type_vmall(
						EncrypterDecrypter.encodeCategory(rs.getString("product_type_vmall").toString()));
				item.setProduct_type_name(rs.getString("product_type_name").toString());
				ArrayList<CatgorySub_Entity> list_sub = new ArrayList<CatgorySub_Entity>();
				list_sub = get_sub_category(rs.getString("product_type_vmall").toString());
				item.setList_cate_sub(list_sub);
				item.setList_p(get_product_type_id_by_parent(product_type_vmall, product_type_id));
				item.setItem_sub(get_product_type_id(product_type_id));
				list.add(item);
			}
		} catch (Exception ex) {
			System.out.println("CatgoryDAL error:" + ex);
		}
		return list;
	}

	public static CatgorySub_Entity get_product_type_id(String product_type_id) {
		CatgorySub_Entity item = new CatgorySub_Entity();
		String query = "SELECT product_type_id, product_type_name,category_img " + " FROM tb_product_type_sub "
				+ " WHERE product_type_id = '" + product_type_id
				+ "' and isvisible = true and isdelete = false ";
		try {
			ResultSet rs = null;
			rs = ConnectDB.GetDataReturnResultSet(query);
			while (rs.next()) {

				item.setProduct_type_id(EncrypterDecrypter.encodeCategory(rs.getString("product_type_id").toString()));
				item.setProduct_type_name(rs.getString("product_type_name").toString());
				item.setCategory_img(rs.getString("category_img"));
				break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return item;
	}

	public static ArrayList<CatgorySub_Entity> get_product_type_id_by_parent(String product_type_vmall,
			String product_type_id) {
		ArrayList<CatgorySub_Entity> list = new ArrayList<CatgorySub_Entity>();
		;
		String query = "SELECT product_type_id, product_type_name,category_img " + " FROM tb_product_type_sub "
				+ " WHERE parent = '" + product_type_id
				+ "' and isvisible = true and isdelete = false ";
		try {
			ResultSet rs = null;
			rs = ConnectDB.GetDataReturnResultSet(query);
			while (rs.next()) {
				CatgorySub_Entity item = new CatgorySub_Entity();
				item.setProduct_type_id(EncrypterDecrypter.encodeCategory(rs.getString("product_type_id").toString()));
				item.setProduct_type_name(rs.getString("product_type_name").toString());
				item.setCategory_img(rs.getString("category_img"));
				ArrayList<CatgorySub_Entity> list_sub = new ArrayList<CatgorySub_Entity>();
				list_sub = get_sub_category(product_type_vmall, product_type_id);
				item.setList_cate_sub(list_sub);
				list.add(item);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public static ArrayList<Brand_Entity> get_brand_by_product_type_id(String product_type_id) {

		ArrayList<Brand_Entity> list = new ArrayList<Brand_Entity>();
		String query_name = "004";
		String[] pf = { "p_product_type_id" };
		String[] pv = { product_type_id };
		try {
			ResultSet rs = null;
			rs = ConnectDB.GetDataDirecReturnResultSet(query_name, pf, pv);
			while (rs.next()) {
				Brand_Entity item = new Brand_Entity();
				item.setId(rs.getString("id"));
				item.setName(rs.getString("name"));
				item.setImage(rs.getString("image"));
				list.add(item);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public static ArrayList<CatgorySub_Entity> get_category(String product_type_id) {

		ArrayList<CatgorySub_Entity> list = new ArrayList<CatgorySub_Entity>();
		String query_name = "005";
		String[] pf = { "p_product_type_id" };
		String[] pv = { product_type_id };
		try {
			ResultSet rs = null;
			rs = ConnectDB.GetDataDirecReturnResultSet(query_name, pf, pv);
			while (rs.next()) {
				CatgorySub_Entity item = new CatgorySub_Entity();
				item.setProduct_type_id(EncrypterDecrypter.encodeCategory(rs.getString("product_type_id").toString()));
				item.setProduct_type_name(rs.getString("product_type_name").toString());
				list.add(item);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	public static ArrayList<Level_price_vmall_Entity> get_leve_price(String product_type_id) {

		ArrayList<Level_price_vmall_Entity> list = new ArrayList<Level_price_vmall_Entity>();
		String query_name = "006";
		String[] pf = { "p_product_type_id" };
		String[] pv = { product_type_id };
		try {
			ResultSet rs = null;
			rs = ConnectDB.GetDataDirecReturnResultSet(query_name, pf, pv);
			while (rs.next()) {
				Level_price_vmall_Entity item = new Level_price_vmall_Entity();
				item.setLevel_price_id(rs.getString("id"));
				item.setTb_level_price_0_name(rs.getString("name"));
				item.setTb_level_price_0_pquery(rs.getString("pquery"));
				list.add(item);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	public static ArrayList<Color_Entity> get_color_by_product_type_id(String product_type_id) {

		ArrayList<Color_Entity> list = new ArrayList<Color_Entity>();
		String query_name = "007";
		String[] pf = { "p_product_type_id" };
		String[] pv = { product_type_id };
		try {
			ResultSet rs = null;
			rs = ConnectDB.GetDataDirecReturnResultSet(query_name, pf, pv);
			while (rs.next()) {
				Color_Entity item = new Color_Entity();
				item.setId(rs.getString("id").toString());
				item.setColor(rs.getString("color").toString());
				item.setImg(rs.getString("color").toString());
				item.setType(rs.getString("type"));
				list.add(item);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	public static ArrayList<Color_Entity> get_color_by_product_type_id(String product_type_id,String product_type_second_id) {

		ArrayList<Color_Entity> list = new ArrayList<Color_Entity>();
		String query_name = "011";
		String[] pf = { "p_parent","p_product_type_id" };
		String[] pv = { product_type_id,product_type_second_id};
		try {
			ResultSet rs = null;
			rs = ConnectDB.GetDataDirecReturnResultSet(query_name, pf, pv);
			while (rs.next()) {
				Color_Entity item = new Color_Entity();
				item.setId(rs.getString("id").toString());
				item.setColor(rs.getString("color").toString());
				item.setImg(rs.getString("color").toString());
				item.setType(rs.getString("type"));
				list.add(item);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	public static ArrayList<Property_Entity> get_property_by_product_type_id(String product_type_id) {

		ArrayList<Property_Entity> list = new ArrayList<Property_Entity>();
		String query_name = "008";
		String[] pf = { "p_product_type_id" };
		String[] pv = { product_type_id };
		try {
			ResultSet rs = null;
			rs = ConnectDB.GetDataDirecReturnResultSet(query_name, pf, pv);
			while (rs.next()) {
				Property_Entity item = new Property_Entity();
				item.setId(rs.getString("ID"));
				item.setProperty_name(rs.getString("property_name"));
				item.setList_property(get_property_id_id(rs.getString("ID")));
				list.add(item);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	public static ArrayList<Property_Entity> get_property_by_product_type_id(String product_type_id,String product_type_second_id) {

		ArrayList<Property_Entity> list = new ArrayList<Property_Entity>();
		String query_name = "012";
		String[] pf = {"p_parent", "p_product_type_id" };
		String[] pv = { product_type_id,product_type_second_id };
		try {
			ResultSet rs = null;
			rs = ConnectDB.GetDataDirecReturnResultSet(query_name, pf, pv);
			while (rs.next()) {
				Property_Entity item = new Property_Entity();
				item.setId(rs.getString("ID"));
				item.setProperty_name(rs.getString("property_name"));
				item.setList_property(get_property_id_id(rs.getString("ID")));
				list.add(item);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	public static ArrayList<Property_Entity> get_property_id_id(String p_property) {		
		ArrayList<Property_Entity> list = new ArrayList<Property_Entity>();
		String query_name = "009";
		String[] pf = { "p_property" };
		String[] pv = { p_property };
		try {
			ResultSet rs = null;
			rs = ConnectDB.GetDataDirecReturnResultSet(query_name, pf, pv);
			while (rs.next()) {
				Property_Entity item = new Property_Entity();
				item.setId(rs.getString("ID"));
				item.setProperty_name(rs.getString("property_name"));
				list.add(item);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	public static ItemCategoryProperty get_property_by_product_type(String product_type_id) {
		ItemCategoryProperty item = new ItemCategoryProperty();
		try {
			item.setList_brand(get_brand_by_product_type_id(product_type_id));
			item.setList_category(get_category(product_type_id));
			item.setList_price(get_leve_price(product_type_id));
			item.setList_color(get_color_by_product_type_id(product_type_id));
			item.setList_property(get_property_by_product_type_id(product_type_id));
		} catch (Exception ex) {

		}
		return item;
	}
	public static List<Catgory_Entity> get_category_second_product_by_id(String product_type_vmall, String product_type_id,String second_type_id) {
		List<Catgory_Entity> list = new ArrayList<Catgory_Entity>();
		String query = "SELECT product_type_vmall, product_type_name, category_img, title_img, isvisible, isdelete, isstate, issyn "
				+ " FROM tb_product_type_vmall"
				+ " WHERE isvisible = true and isdelete = false and product_type_vmall = '" + product_type_vmall + "'";
		try {
			ResultSet rs = null;
			rs = ConnectDB.GetDataReturnResultSet(query);
			while (rs.next()) {
				Catgory_Entity item = new Catgory_Entity();
				item.setProduct_type_vmall(	EncrypterDecrypter.encodeCategory(rs.getString("product_type_vmall").toString()));
				item.setProduct_type_name(rs.getString("product_type_name").toString());
				ArrayList<CatgorySub_Entity> list_sub = new ArrayList<CatgorySub_Entity>();
				list_sub = get_sub_category(rs.getString("product_type_vmall").toString());
				item.setList_cate_sub(list_sub);
				item.setList_p(get_product_type_id_by_parent(product_type_vmall, product_type_id));
				item.setItem_sub(get_product_type_id(product_type_id));
				item.setItem_se_sub(get_product_type_id(second_type_id));
				list.add(item);
			}
		} catch (Exception ex) {
			System.out.println("CatgoryDAL error:" + ex);
		}
		return list;
	}
	public static ItemCategoryProperty get_property_by_product_type_second(String product_type_id,String product_type_second_id) {
		ItemCategoryProperty item = new ItemCategoryProperty();
		try {
			item.setList_brand(get_brand_by_product_type_id(product_type_id));
			//item.setList_category(get_category(product_type_id));
			item.setList_price(get_leve_price(product_type_id));
			item.setList_color(get_color_by_product_type_id(product_type_id,product_type_second_id));
			item.setList_property(get_property_by_product_type_id(product_type_id,product_type_second_id));
		} catch (Exception ex) {
		}
		return item;
	}
	public static List<Catgory_Entity> get_category_mobile() {
		List<Catgory_Entity> list = new ArrayList<Catgory_Entity>();
		String query = "SELECT product_type_vmall, product_type_name,icon,isnew, category_img, title_img, isvisible, isdelete, isstate, issyn "
				+ " FROM tb_product_type_vmall" + " WHERE isvisible = true and isdelete = false";
		try {
			ResultSet rs = null;
			rs = ConnectDB.GetDataReturnResultSet(query);
			while (rs.next()) {
				Catgory_Entity item = new Catgory_Entity();
				item.setProduct_type_vmall(rs.getString("product_type_vmall").toString());
				item.setProduct_type_name(rs.getString("product_type_name").toString());
				item.setIcon(rs.getString("icon"));
				item.setIsnew(rs.getString("isnew"));				
				list.add(item);
			}
		} catch (Exception ex) {
			System.out.println("CatgoryDAL error:" + ex);
		}

		return list;
	}
}
