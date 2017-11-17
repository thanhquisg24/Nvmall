package vn.vmall.Entity;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class CategoryNew_Entity {

	
		@Column(name="category_id")
		private String category_id;
		
		@Column(name="category_name")
		private String category_name;
		
		@Column(name="category_img")
		private String category_img;
		
		@Column(name="title_img")
		private String title_img;
		
		@Column(name="isvisible")
		private int isvisible;
		
		@Column(name="isdelete")
		private int isdelete;

		
		public String getCategory_id() {
			return category_id;
		}

		public void setCategory_id(String category_id) {
			this.category_id = category_id;
		}

		public String getCategory_name() {
			return category_name;
		}

		public void setCategory_name(String category_name) {
			this.category_name = category_name;
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
		
		
		
}
