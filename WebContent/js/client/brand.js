
$(function(){	
	exe_load_header(function(){
			append_loading("#content_menu_left");
			get_info_category();
			exec_search_product();		

	});	
	function get_info_category(){
		extras_GET_json('BrandController','get_list_brand','',function(data){
			if(data!=null)
			{
				get_box_menu_brand(function(str){
					if(data.length>0){
						var strhtml_a = '';
						for(var i=0;i<data.length;i++){
							if(data[i].list_branch.length > 0){
								var strhtml = str;
								strhtml = strhtml.replace('@name',data[i].product_type_name);
								var strcontent = '';
								for(var j=0;j<data[i].list_branch.length;j++){
									strcontent+='<li class="list-group-item"><a href="brand.html?id$'+data[i].list_branch[j].id+'">'+data[i].list_branch[j].name+'</a></li>'
								}
								strhtml = strhtml.replace('@content_brand',strcontent);
								strhtml = strhtml.replace(/\@id/g,i);
								strhtml_a+=strhtml;
							}
						}
						var time_menu = setTimeout(function(){
							clearTimeout(time_menu);
							$("#content_menu_left").text('');
							$("#content_menu_left").html(strhtml_a);
							
						},900); 
					}					
				});
			}
		});
	}
	function search_product(){	
		
		var brand = getUrlParameter('id');
		if(brand==null || brand=="" || brand == undefined){
			brand = '';
		}
		var pdata = {		
				'brand':brand,
				'row':p_row
		} 
		
		extras_GET_json('ProductController','search_product_by_brand',pdata,function(data){
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
						$("#content_search_product").append(strhtml_a);
						$("#loading_product").html('');
					},900);
				});
			}
		});		
		
	}
	var lazy_load= false;
	var check_end = false;
	$(document).scroll(function() {
		if (lazy_load == true) {
			var position = $(this).scrollTop();
			if (position > 500) {
				if (check_exists == true) {
					lazy_load = false;
					search_product();
				} else {
					if (check_end == false) {
						check_end = true;
						$("#content_search_product").append('');
					}
				}
			}
		}
	});
	function exec_search_product() {
		lazy_load = false;
		p_row = 0;
		check_exists = true;
		$("#content_search_product").html('');
		append_loading("#loading_product");
		search_product();
	}	
	function get_box_menu_brand(callback){
		var url = ReturnHosing_apache()+'txt/box_menu_brand.txt';
		$.get(url,function(data){
			callback(data);
		});
	}
	function get_box_product(callback){
		var url = ReturnHosing_apache()+'txt/box_product_brand.txt';
		$.get(url,function(data){
			callback(data);
		});
	}
});
