package vn.vmall.Entity;

import java.util.ArrayList;
import java.util.List;

public class ItemCategoryProperty {
	ArrayList<Brand_Entity> list_brand;
	List<CatgorySub_Entity> list_category;

	public List<CatgorySub_Entity> getList_category() {
		return list_category;
	}

	public void setList_category(List<CatgorySub_Entity> list_category) {
		this.list_category = list_category;
	}

	public ArrayList<Brand_Entity> getList_brand() {
		return list_brand;
	}

	public void setList_brand(ArrayList<Brand_Entity> list_brand) {
		this.list_brand = list_brand;
	}
	ArrayList<Level_price_vmall_Entity> list_price;

	public ArrayList<Level_price_vmall_Entity> getList_price() {
		return list_price;
	}

	public void setList_price(ArrayList<Level_price_vmall_Entity> list_price) {
		this.list_price = list_price;
	}
	ArrayList<Color_Entity> list_color;

	public ArrayList<Color_Entity> getList_color() {
		return list_color;
	}

	public void setList_color(ArrayList<Color_Entity> list_color) {
		this.list_color = list_color;
	}
	ArrayList<Property_Entity> list_property;

	public ArrayList<Property_Entity> getList_property() {
		return list_property;
	}

	public void setList_property(ArrayList<Property_Entity> list_property) {
		this.list_property = list_property;
	}
	
}
