package vn.vmall.Entity;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class Catgory_Entity {

	
		@Column(name="product_type_vmall")
		private String product_type_vmall;
		
		@Column(name="product_type_name")
		private String product_type_name;
		
		@Column(name="category_img")
		private String category_img;
		
		@Column(name="title_img")
		private String title_img;
		
		@Column(name="isvisible")
		private int isvisible;
		
		@Column(name="isdelete")
		private int isdelete;
		
		@Column(name="isstate")
		private int isstate;
		
		@Column(name="issyn")
		private int issyn;

		public String getProduct_type_vmall() {
			return product_type_vmall;
		}

		public void setProduct_type_vmall(String product_type_vmall) {
			this.product_type_vmall = product_type_vmall;
		}

		public String getProduct_type_name() {
			return product_type_name;
		}

		public void setProduct_type_name(String product_type_name) {
			this.product_type_name = product_type_name;
		}

		public String getCategory_img() {
			return category_img;
		}

		public void setCategory_img(String category_img) {
			this.category_img = category_img;
		}

		public String getTitle_img() {
			return title_img;
		}

		public void setTitle_img(String title_img) {
			this.title_img = title_img;
		}

		public int getIsvisible() {
			return isvisible;
		}

		public void setIsvisible(int isvisible) {
			this.isvisible = isvisible;
		}

		public int getIsdelete() {
			return isdelete;
		}

		public void setIsdelete(int isdelete) {
			this.isdelete = isdelete;
		}

		public int getIsstate() {
			return isstate;
		}

		public void setIsstate(int isstate) {
			this.isstate = isstate;
		}

		public int getIssyn() {
			return issyn;
		}

		public void setIssyn(int issyn) {
			this.issyn = issyn;
		}		
		ArrayList<CatgorySub_Entity> list_cate_sub;

		public ArrayList<CatgorySub_Entity> getList_cate_sub() {
			return list_cate_sub;
		}

		public void setList_cate_sub(ArrayList<CatgorySub_Entity> list_cate_sub) {
			this.list_cate_sub = list_cate_sub;
		}
		
		ArrayList<CatgorySub_Entity> list_slide;
		public ArrayList<CatgorySub_Entity> getList_slide() {
			return list_slide;
		}

		public void setList_slide(ArrayList<CatgorySub_Entity> list_slide) {
			this.list_slide = list_slide;
		}

		public ArrayList<CatgorySub_Entity> getList_p() {
			return list_p;
		}

		public void setList_p(ArrayList<CatgorySub_Entity> list_p) {
			this.list_p = list_p;
		}

		public ArrayList<CatgorySub_Entity> getList_top() {
			return list_top;
		}

		public void setList_top(ArrayList<CatgorySub_Entity> list_top) {
			this.list_top = list_top;
		}

		public ArrayList<Brand_Entity> getList_branch() {
			return list_branch;
		}

		public void setList_branch(ArrayList<Brand_Entity> list_branch) {
			this.list_branch = list_branch;
		}
		ArrayList<CatgorySub_Entity> list_p;
		ArrayList<CatgorySub_Entity> list_top;
		ArrayList<Brand_Entity> list_branch;
		ArrayList<CatgorySub_Entity> list_item_big;
		String icon;
		String isnew;
		CatgorySub_Entity item_sub;
		CatgorySub_Entity item_se_sub;
		public CatgorySub_Entity getItem_se_sub() {
			return item_se_sub;
		}

		public void setItem_se_sub(CatgorySub_Entity item_se_sub) {
			this.item_se_sub = item_se_sub;
		}

		public CatgorySub_Entity getItem_sub() {
			return item_sub;
		}

		public void setItem_sub(CatgorySub_Entity item_sub) {
			this.item_sub = item_sub;
		}

		public String getIsnew() {
			return isnew;
		}

		public void setIsnew(String isnew) {
			this.isnew = isnew;
		}

		public String getIcon() {
			return icon;
		}

		public void setIcon(String icon) {
			this.icon = icon;
		}

		public ArrayList<CatgorySub_Entity> getList_item_big() {
			return list_item_big;
		}

		public void setList_item_big(ArrayList<CatgorySub_Entity> list_item_big) {
			this.list_item_big = list_item_big;
		}
		ArrayList<Brand_Entity> list_brand;

		public ArrayList<Brand_Entity> getList_brand() {
			return list_brand;
		}

		public void setList_brand(ArrayList<Brand_Entity> list_brand) {
			this.list_brand = list_brand;
		}

		
}
