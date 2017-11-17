var list_brand_p = [];
var list_cate_p = [];
var list_price_p = [];
var list_color_p = [];
var list_property_p = [];
var p_row =0;
var check_exists = true;
var timer_search = null;
$(function(){
	
	blockbg();
	exe_load_header(function(){			
		get_info_category_product();
		exec_search_product();			
	});	
	function get_info_category_product(){
		var type_vmall = getUrlParameter('id');		
		if(type_vmall==null || type_vmall=="" || type_vmall == undefined){
			window.location.href=ReturnHosing_apache() + 'page_not_found.html';
		}
		var type_id = getUrlParameter('id2');		
		if(type_id==null || type_id=="" || type_id == undefined){
			window.location.href=ReturnHosing_apache() + 'page_not_found.html';
		}
		var second_type_id = getUrlParameter('id3');		
		if(second_type_id==null || second_type_id=="" || second_type_id == undefined){
			window.location.href=ReturnHosing_apache() + 'page_not_found.html';
		}
		var pdata = {
				'type_vmall':type_vmall,
				'type_id':type_id,
				'second_type_id':second_type_id
		};
		extras_GET_json('CategoryController','get_category_second_product_by_id',pdata,function(data){
			if(data!=null)
			{
				get_item_menu(function(str){
					if(data.length>0){
						// level 1
						var list = data[0].list_cate_sub;						
						var strhtml = str;
						strhtml = strhtml.replace('@name',data[0].product_type_name);
						var strsub = '';
						for(var i=0;i<list.length;i++){							
							var str2 = '<li><a  href="category_product.html?id$@id&id2$@id2" style="text-decoration: none;cursor: pointer;">'+list[i].product_type_name+'</a></li>';
							str2 = str2.replace('@id',data[0].product_type_vmall);
							str2 = str2.replace('@id2',list[i].product_type_id);
							strsub+=str2;
						}
						strhtml = strhtml.replace('@menusub',strsub);
						//level 2
						if(data[0].list_p.length > 0){
							var list_p = data[0].list_p;
							var list_c_p = list_p[0].list_cate_sub
							$("#name_category").text(data[0].item_sub.product_type_name);
							strhtml = strhtml.replace('@subname',data[0].item_sub.product_type_name);
							strsub = '';
							for(var i=0;i<list_c_p.length;i++){
								var str2 = '<li><a href="category_second_product.html?id$@id&id2$@id2&id3$@id3" style="text-decoration: none;cursor: pointer;">'+list_c_p[i].product_type_name+'</a></li>';
								str2 = str2.replace('@id',data[0].product_type_vmall);
								str2 = str2.replace('@id2',data[0].item_sub.product_type_id);
								str2 = str2.replace('@id3',list_c_p[i].product_type_id);
								strsub+=str2;
							}
						}
						else{
							strsub = '';
							strhtml = strhtml.replace('@subname',data[0].item_sub.product_type_name);
						}						
						strhtml = strhtml.replace('@sub2',strsub);
						// level 3
						strhtml = strhtml.replace('@sub3',data[0].item_se_sub.product_type_name);
						$("#contain_menu").append(strhtml);						
						unblockbg();
						append_loading("#content_product_recommend");
						append_loading("#content_property");
						append_loading("#content_product_qt");
						load_property(type_id,second_type_id);
						load_product_recommend(type_id);
						load_product_care(type_id);
					}	
					else{
						unblockbg();
					}
				});
			
			}
			else{
				unblockbg();
			}
		});
	}
	function load_property(type_id,second_type_id){
		var pdata = {
				'type_id':type_id,
				'second_type_id':second_type_id
		};
		extras_GET_json('CategoryController','get_property_by_product_type_second',pdata,function(data){
			if(data!=null)
			{
				var strhtml = '';
				//brand
				var list_brand = data.list_brand;	
				if(list_brand.length>0){
					var strhtml_a_b = '<div class="box_filter clear_fix col-xs-12">';
					strhtml_a_b+='<div class="col-xs-2 bf_left">Thương hiệu</div>';
					strhtml_a_b+='<div class="col-xs-10 bf_attr">';
					strhtml_a_b+='<div class="list_attr list_brand_logo">@content_brand</div>';
					strhtml_a_b+='</div></div>';
					
					var strhtml_b = '';
					for(var i=0;i<list_brand.length;i++){
						var item_brand = '<div class="item-property"><input type="checkbox" class="item_brand" name="filter_atrr" id="ft_logo_@id"/>';
						item_brand +='<label   for="ft_logo_@id"><span>';
						item_brand +='<img src="@imghref" alt="" >';
						item_brand +='</span></label></div>';
						item_brand = item_brand.replace(/\@id/g,list_brand[i].id);
						var imghref = ReturnHosing_tomcat_upload()+'upload/branch/'+list_brand[i].image;
						item_brand = item_brand.replace(/\@imghref/g,imghref);
						strhtml_b+=item_brand;
					}
					strhtml_a_b = strhtml_a_b.replace('@content_brand',strhtml_b);
					strhtml+=strhtml_a_b;
				}				
								
				//price
				var list_price = data.list_price;
				if(list_price.length > 0){
					var strhtml_a_price = '<div class="box_filter clear_fix col-xs-12">';
					strhtml_a_price+='<div class="col-xs-2 bf_left">Giá<span style="font-size:10.5px;">(K = 1000VNĐ)</span></div>';
					strhtml_a_price+='<div class="col-xs-10 bf_attr">';
					strhtml_a_price+='<div class="list_attr list_attr_price">@content_price</div>';
					strhtml_a_price+='</div></div>';
					
					var strhtml_price = '';
					for(var i=0;i<list_price.length;i++){
						var item_price = '<div class="item-property"><input type="checkbox" class="item_price" name="filter_atrr" id="ft_price_@id"/>';
						item_price +='<label   for="ft_price_@id"><span>@name';					
						item_price +='</span></label></div>';
						item_price = item_price.replace(/\@id/g,list_price[i].level_price_id);					
						item_price = item_price.replace(/\@name/g,list_price[i].tb_level_price_0_name);
						strhtml_price+=item_price;
					}
					strhtml_a_price = strhtml_a_price.replace('@content_price',strhtml_price);
					strhtml+=strhtml_a_price;
					
				}
				//color
				var list_color = data.list_color;
				if(list_color.length> 0){
					var strhtml_a_color = '<div class="box_filter clear_fix col-xs-12">';
					strhtml_a_color+='<div class="col-xs-2 bf_left">Màu sắc</span></div>';
					strhtml_a_color+='<div class="col-xs-10 bf_attr">';
					strhtml_a_color+='<div class="list_attr list_attr_color">@content_color</div>';
					strhtml_a_color+='</div></div>';
					
					var strhtml_color = '';
					for(var i=0;i<list_color.length;i++){
						var item_color = '<div class="item-property"><input type="checkbox" class="item_color" name="filter_atrr" id="ft_color_@id"/>';
						item_color +='<label   for="ft_color_@id">@type';
						item_color +='</label></div>';
						
						item_color = item_color.replace(/\@id/g,list_color[i].id);
						if(list_color[i].type=='1'){
							item_color = item_color.replace('@type','<span  style="background-color:'+list_color[i].color+'"></span>');			 
								  		
						}
						else{
							var imghref = ReturnHosing_tomcat_upload()+'upload/color/'+list_color[i].image;
							item_color = item_color.replace('@type','<span><img style="height:30px;width:30px;" src="'+imghref+'" /></span>');
						}
						
						strhtml_color+=item_color;
					}					
					strhtml_a_color = strhtml_a_color.replace('@content_color',strhtml_color);
					strhtml+=strhtml_a_color;
				}				
				//property
				var list_property = data.list_property;
				if(list_property.length > 0){
					var strhtml_pro = '';
					for(var j=0;j<list_property.length;j++){
						if(list_property[j].list_property.length>0){
							var strhtml_a_property = '<div class="box_filter clear_fix col-xs-12">';
							strhtml_a_property+='<div class="col-xs-2 bf_left">'+list_property[j].property_name+'</span></div>';
							strhtml_a_property+='<div class="col-xs-10 bf_attr">';
							strhtml_a_property+='<div class="list_attr list_attr_other">@content_other</div>';
							strhtml_a_property+='</div></div>';
							
							var strhtml_other = '';
							for(var i=0;i<list_property[j].list_property.length;i++){
								var item_other = '<div class="item-property"><input type="checkbox" class="item_property" name="filter_atrr" id="property-@id"/>';
								item_other +='<label   for="property-@id"><span>@name';					
								item_other +='</span></label></div>';
								item_other = item_other.replace(/\@id/g,list_property[j].list_property[i].id);					
								item_other = item_other.replace(/\@name/g,list_property[j].list_property[i].property_name);
								strhtml_other+=item_other;
							}
							strhtml_a_property = strhtml_a_property.replace('@content_other',strhtml_other);								
							strhtml_pro+=strhtml_a_property;
						}						
					}	
					strhtml+=strhtml_pro;
				}
				var time_menu = setTimeout(function(){
					clearTimeout(time_menu);
					$("#name_category").css('display','block');
					$("#content_property").html(strhtml);
					exec_select_option();
				},900); 	
			}
		});
	}
	function exec_link(){
		$(".linkproduct").click(function(){
			var link = $(this).attr('opso_link');
			window.open(link);			
		});
	}
	
	
	function exec_select_option(){
		
		$(".item_brand").click(function(){			
			var id = $(this).attr('id');			
			var arr_id = id.split('_');
			if(arr_id.length> 0){
				var brand = arr_id[2];
				add_remove_brand_select(brand);	
				if(timer_search==null){
					exec_timer_search();
				}
			}			
		});
		$(".item_category").click(function(){			
			var id = $(this).attr('id');			
			var arr_id = id.split('_');
			if(arr_id.length> 0){
				var cate = arr_id[2];
				add_remove_cate_select(cate);
				if(timer_search==null){
					exec_timer_search();
				}
			}
			
		});
		
		$(".item_price").click(function(){			
			var id = $(this).attr('id');			
			var arr_id = id.split('_');
			if(arr_id.length> 0){
				var price = arr_id[2];
				add_remove_price_select(price);		
				if(timer_search==null){
					exec_timer_search();
				}
			}
			
		});
		
		$(".item_color").click(function(){			
			var id = $(this).attr('id');			
			var arr_id = id.split('_');
			if(arr_id.length> 0){
				var color = arr_id[2];
				add_remove_color_select(color);		
				if(timer_search==null){
					exec_timer_search();
				}
			}
			
		});
		$(".item_property").click(function(){			
			var id = $(this).attr('id');			
			var arr_id = id.split('-');
			if(arr_id.length> 0){
				var property = arr_id[1];
				add_remove_property_select(property);
				if(timer_search==null){
					exec_timer_search();
				}			
			}
			
		});
		$(".click_like").click(function(){			
			var productid = $(this).attr('id');			
			var arr = productid.split('_');
			if(arr.length > 0){
				productid = arr[1];
				fn_like_product(productid,function(data){
					if(data!=null){
						if(data=='0'){
							showdialog('dialogmanual',0,'Sản phẩm đã được thêm vào sản phẩm yêu thích','','');			
						}
						else{
							showdialog('dialogmanual',0,'Lỗi trong quá trình đưa sản phẩm vào danh sách yêu thích','','');
						}
					}
					else{
						showdialog('dialogmanual',0,'Lỗi trong quá trình đưa sản phẩm vào danh sách yêu thích','','');
					}
				});	
			}
		});
	}
	function add_remove_brand_select(id){
		var i = list_brand_p.indexOf(id);
		if(i != -1) {
			list_brand_p.splice(i, 1);				
		}
		else{
			list_brand_p.push(id);
		}		
	}
	function add_remove_cate_select(id){
		
		var i = list_cate_p.indexOf(id);
		if(i != -1) {
			list_cate_p.splice(i, 1);				
		}
		else{
			list_cate_p.push(id);
		}
		
	}
	function add_remove_price_select(id){
		
		
		var i = list_price_p.indexOf(id);
		if(i != -1) {
			list_price_p.splice(i, 1);				
		}
		else{
			list_price_p.push(id);
		}
	}
	function add_remove_color_select(id){
		var i = list_color_p.indexOf(id);
		if(i != -1) {
			list_color_p.splice(i, 1);				
		}
		else{
			list_color_p.push(id);
		}
		
	}
	function add_remove_property_select(id){
		
		var i = list_property_p.indexOf(id);
		if(i != -1) {
			list_property_p.splice(i, 1);				
		}
		else{
			list_property_p.push(id);
		}
	}
	function search_product(){	
		
		var brand = '';
		for(var i=0;i<list_brand_p.length;i++){
			brand+="'"+list_brand_p[i]+"',";
		}
		if(brand.length > 0){
			brand = brand.substring(0,brand.length-1);			
		}
		
		//category
		var category = '';		
		for(var i=0;i<list_cate_p.length;i++){
			category+=""+list_cate_p[i]+",";
		}
		if(category.length > 0){
			category = category.substring(0,category.length-1);			
		}
		//price
		var price = '';
		for(var i=0;i<list_price_p.length;i++){
			price+=""+list_price_p[i]+",";
		}
		// color
		var color = '';		
		for(var i=0;i<list_color_p.length;i++){
			color+=""+list_color_p[i]+",";
		}
		
		// property
		var property = '';
		for(var i=0;i<list_property_p.length;i++){
			property+=""+list_property_p[i]+",";
		}
		//
		var type_id = getUrlParameter('id2');		
		if(type_id==null || type_id=="" || type_id == undefined){
			window.location.href=ReturnHosing_apache() + 'page_not_found.html';
		}
		var pdata = {		
				'id':type_id,
				'brand':brand,
				'category':category,
				'price':price,
				'color':color,
				'property':property,
				'row':p_row
		} 
		
		extras_GET_json('ProductController','search_product_by_category',pdata,function(data){
			if(data!=null){
				if(data.length==0){
					lazy_load = true;
					check_exists = false;
					//$("#content_search_product").append('');
					$("#loading_product").html('');
					return;
				}
				get_box_product(function(str){
					var strhtml_a = '';
					console.log("1 :" + str);
					for(var i=0;i<data.length;i++){
						var strhtml = str;
						var base_url_detail = url_detail;
						base_url_detail = base_url_detail.replace('@id',data[i].productId);
						base_url_detail = base_url_detail.replace('@url',window.location.href);		
						strhtml = strhtml.replace(/\@nameproduct/g,data[i].productName);
						strhtml = strhtml.replace(/\@nameproduct/g,data[i].productName);
						strhtml = strhtml.replace(/\@detail/g,base_url_detail);
						strhtml = strhtml.replace(/\@imghref/g,data[i].productImage);
						strhtml = strhtml.replace(/\@shopname/g,data[i].shop_name);
						strhtml = strhtml.replace(/\@hrefshop/g,data[i].url_shop);
						strhtml = strhtml.replace(/\@love/g,'Yêu thích');
						strhtml = strhtml.replace(/\@idproduct/g,data[i].productId);
						strhtml = strhtml.replace(/\@num_buy/g,data[i].num_buy);
						strhtml = strhtml.replace(/\@num_view/g,data[i].num_view);
						
						if(data[i].isPromo=='0'){ // false
							strhtml = strhtml.replace(/\@newprice/g,formatcurrency(data[i].newPrice) +'đ');
							strhtml = strhtml.replace(/\@oldprice/g,'');
							strhtml = strhtml.replace(/\@saleoff/g,'');
						}
						else{
							strhtml = strhtml.replace(/\@newprice/g,formatcurrency(data[i].newPrice) +'đ');
							strhtml = strhtml.replace(/\@oldprice/g,formatcurrency(data[i].productPrice)+ 'đ');							
							strhtml = strhtml.replace(/\@saleoff/g,str_sell_off);
						}
						strhtml_a+=strhtml;
					}
					
					var time_menu = setTimeout(function(){
						clearTimeout(time_menu);						
						p_row+=12;
						lazy_load = true;
						console.log("2 :" + strhtml_a);
						$("#content_search_product").append(strhtml_a);
						$("#loading_product").html('');
					},900);
				});
			}
		});
		
		
	}
	var lazy_load= false;
	var check_end = false;
	$(document).scroll(function () 
	{						
		if(lazy_load==true){
			var position = $(this).scrollTop();
			if(position>500){
				if(check_exists==true){
					lazy_load = false;
					search_product();
				}
				else{
					if(check_end==false){
						check_end = true;
						$("#content_search_product").append('');
					}			
				}			
			}
		}		
	});
	function exec_search_product(){
		lazy_load = false;
		p_row = 0;
		check_exists = true;
		$("#content_search_product").html('');
		append_loading("#loading_product");
		search_product();
	}
	function exec_timer_search(){
		if(lazy_load==true){
			timer_search = setTimeout(function(){
				clearTimeout(timer_search);
				timer_search = null;
				exec_search_product();
			},500);
		}		 
	}
	function load_product_recommend(type_id){				
		var pdata = {
				'id':type_id
		};
		extras_GET_json('ProductController','get_product_recommend_product_type',pdata,function(data){
			if(data!=null){
				get_box_recommend(function(str){
					var strproduct = '';
					var strproduct_i = '';
					var item_b = 1;
					var isactive = 0;	
					var iscom = 0;
					for(var i=0;i<data.length;i++){	
						var base_url_detail = url_detail;
						base_url_detail = base_url_detail.replace('@id',data[i].productId);
						base_url_detail = base_url_detail.replace('@url',window.location.href);					
						strproduct+='<li class="col-sm-12" style="padding: 0px;">';
						strproduct+='<div class="fff_recom">';
						strproduct+='<div class="thumbnail">';
						strproduct+='	<a 	class="linkproduct" opso_link="'+base_url_detail+'"><img class="img_product_recommend" src="'+data[i].productImage+'" style="height:200px" alt=""><div class="box_product_recommend">'+data[i].productName+'</div></a>';
						strproduct+='</div></div></li>';							
						if(item_b==4){
							iscom = 0;
							if(isactive==0){
								var strproduct_ii = '<div class="item active">';
								strproduct_ii+=			'<ul class="thumbnails items_list">'+strproduct;
								strproduct_ii+='</ul></div>';
								strproduct_i+=strproduct_ii;
								isactive = 1;
							}
							else{										
								var strproduct_ii = '<div class="item">';
								strproduct_ii+=			'<ul class="thumbnails items_list">'+strproduct;
								strproduct_ii+='</ul></div>';
								strproduct_i+=strproduct_ii;
							}
							strproduct='';
							item_b = 0;
						}
						else{
							iscom = 1;
						}
						item_b+=1;						
					}	
					// trong truong hop so hinh bi  le
					if(strproduct.length>0){
						if(isactive==0){
							var strproduct_ii = '<div class="item active">';
							strproduct_ii+=			'<ul class="thumbnails items_list">'+strproduct;
							strproduct_ii+='</ul></div>';
							strproduct_i+=strproduct_ii;
							isactive = 1;
						}
						else{										
							var strproduct_ii = '<div class="item">';
							strproduct_ii+=			'<ul class="thumbnails items_list">'+strproduct;
							strproduct_ii+='</ul></div>';
							strproduct_i+=strproduct_ii;
						}
					}
					var strhtml = str;
					strhtml = strhtml.replace('@content_slide',strproduct_i);
					
					var time_menu = setTimeout(function(){
						clearTimeout(time_menu);
						$("#content_product_recommend").html(strhtml);
						exec_link();
					},1000); 
					
				});				
			}
		});
	}
	function load_product_care(type_id){
				
		var pdata = {
				'id':type_id
		};
		extras_GET_json('ProductController','get_product_qt_product_type',pdata,function(data){
			if(data!=null){
				get_box_qt(function(str){
					var strproduct = '';
					var strproduct_i = '';
					var item_b = 1;
					var isactive = 0;	
					var iscom = 1;
					var isbreak = 0;
					var strbrk = '';
					for(var i=0;i<data.length;i++){	
						var base_url_detail = url_detail;
						base_url_detail = base_url_detail.replace('@id',data[i].productId);
						base_url_detail = base_url_detail.replace('@url',window.location.href);					
						strproduct+='<li class="col-sm-2" style="padding: 0px;">';
						strproduct+='<div class="fff_qt">';
						strproduct+='<div class="thumbnail_qt">';
						strproduct+='	<a 	class="linkproduct" opso_link="'+base_url_detail+'"><img class="img_product_recommend" src="'+data[i].productImage+'" style="height:200px" alt=""></a>';
						strproduct+='</div>';
						strproduct+='<div class="caption text-center">';
						strproduct+='<p>'+data[i].productName+'</p>';
						strproduct+='</div></div></li>';							
						if(item_b==6){
							iscom = 0;
							if(isbreak<2){							
								strbrk+='<ul class="thumbnails items_list">'+strproduct+'<ul/>';
								isbreak+=1;
								strproduct='';	
								item_b = 0;	
							}
							if(isbreak==2){
								if(isactive==0){
									var strproduct_ii = '<div class="item active">';
									strproduct_ii+=strbrk;
									strproduct_ii+='</div>';
									strproduct_i+=strproduct_ii;
									isactive = 1;
								}
								else{										
									var strproduct_ii = '<div class="item">';
									strproduct_ii+=strbrk;
									strproduct_ii+='</div>';
									strproduct_i+=strproduct_ii;
								}	
								strbrk='';
								isbreak = 0;
							}			
						}
						else{
							iscom = 1;
						}
						
						item_b+=1;						
					}	
					// trong truong hop so hinh bi  le
					if(strproduct.length>0){
						if(isbreak<2){							
							strbrk+='<ul class="thumbnails items_list">'+strproduct+'<ul/>';
							isbreak+=1;
							item_b = 0;	
						}
						if(isactive==0){
							var strproduct_ii = '<div class="item active">';
							strproduct_ii+=strbrk;
							strproduct_ii+='</div>';
							strproduct_i+=strproduct_ii;
							isactive = 1;
						}
						else{										
							var strproduct_ii = '<div class="item">';
							strproduct_ii+=strbrk;
							strproduct_ii+='</div>';
							strproduct_i+=strproduct_ii;
						}				
					}
					var strhtml = str;
					strhtml = strhtml.replace('@contain_product_care',strproduct_i);
					var time_menu = setTimeout(function(){
						clearTimeout(time_menu);
						$("#content_product_qt").html(strhtml);
					},1000); 	
				});							
			}
		});
	}
	function get_item_menu(callback){
		var url = ReturnHosing_apache()+'txt/menu_search_category_second_product.txt';
		$.get(url,function(data){
			callback(data);
		});
	}
	function get_box_product(callback){
		var url = ReturnHosing_apache()+'txt/box_product.txt';
		$.get(url,function(data){
			callback(data);
		});
	}
	function get_box_recommend(callback){
		var url = ReturnHosing_apache()+'txt/item_box_category_recommend.txt';
		$.get(url,function(data){
			callback(data);
		});
	}
	function get_box_qt(callback){
		var url = ReturnHosing_apache()+'txt/item_box_category_qt.txt';
		$.get(url,function(data){
			callback(data);
		});
	}
});
function CallbackLikeProduct(data){
	unblockbg();
	if(data!=null){
		if(data.result=='0'){
			showdialog('dialogmanual',0,'Sản phẩm đã được thêm vào sản phẩm yêu thích','','');			
		}
		else{
			showdialog('dialogmanual',0,'Lỗi trong quá trình đưa sản phẩm vào danh sách yêu thích','','');
		}
	}
	else{
		showdialog('dialogmanual',0,'Lỗi trong quá trình đưa sản phẩm vào danh sách yêu thích','','');
	}
}	