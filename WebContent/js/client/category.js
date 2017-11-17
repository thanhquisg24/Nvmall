
$(function(){
	blockbg();
	exe_load_header(function(){			
			get_info_category();
			append_loading("#content_product_recommend");
			append_loading("#content_product_qt");
			load_product_care();
			load_product_recommend();
	});	
	function get_info_category(){
		var id = getUrlParameter('id');		
		if(id==null || id=="" || id == undefined){
			window.location.href=ReturnHosing_apache() + 'page_not_found.html';
		}		
		var pdata = {
				'id':id
		};
		extras_GET_json('CategoryController','get_category_sub',pdata,function(data){
			if(data!=null)
			{
				get_item_menu(function(str){
					if(data.length>0){
						var list = data[0].list_cate_sub;
						var strhtml = str;
						strhtml = strhtml.replace('@name',data[0].product_type_name);
						var strsub = '';
						for(var i=0;i<list.length;i++){
							var str2 = '<li><a target="_blank" href="category_product.html?id$@id&id2$@id2" style="text-decoration: none;cursor: pointer;">'+list[i].product_type_name+'</a></li>';
							str2 = str2.replace('@id',data[0].product_type_vmall);
							str2 = str2.replace('@id2',list[i].product_type_id);
							strsub+=str2;
						}
						strhtml = strhtml.replace('@menusub',strsub);
						$("#contain_menu").append(strhtml);
						append_loading("#contain_search");
						load_category_search(data);
					}					
				});
			}
			unblockbg();
		});
	}
	function load_category_search(data){
		get_box(function(str){
			if(data.length>0){			
				var strhtml_a = '';
				var list = data[0].list_cate_sub;
				for(var i=0;i<list.length;i++){
					var strhtml = str;
					strhtml = strhtml.replace('@name',list[i].product_type_name);					
					if(list[i].list_cate_sub.length>1){
						/// box larg
						var url_img_l = ReturnHosing_tomcat_upload()+'upload/category/'+list[i].list_cate_sub[0].category_img;
						var stritem_l = '<a class="item_slide" target="_blank" href="category_second_product.html?id$@id&id2$@id2&id3$@id3"><img src="'+url_img_l+'" /><div class="ttl-menu">'+list[i].list_cate_sub[0].product_type_name+'</div></a>';
						stritem_l = stritem_l.replace('@id',data[0].product_type_vmall);
						stritem_l = stritem_l.replace('@id2',list[i].list_cate_sub[0].parent);
						stritem_l = stritem_l.replace('@id3',list[i].list_cate_sub[0].product_type_id);
						strhtml = strhtml.replace('@boxlag',stritem_l);
						
						//box small
						var content_row = '';
						var stritem ='';
						var num_item_init = 6;
						var num = 0;
						if(list[i].list_cate_sub.length - 1  >= num_item_init){
							num = num_item_init;
						}
						else{
							num =list[i].list_cate_sub.length - 1;						
						}
						for(var j=1;j<num+1;j++){								
							var url_img = ReturnHosing_tomcat_upload()+'upload/category/'+list[i].list_cate_sub[j].category_img;
							var stritem_1 = '<div class="col-md-4">';
							stritem_1+=	'	<div class="box-item-menu-category">';
							stritem_1+='<a  target="_blank" href="category_second_product.html?id$@id&id2$@id2&id3$@id3">';
							stritem_1+=	'		<img src="'+url_img+'" />';
							stritem_1+=	'		<div class="ttl-menu">'+list[i].list_cate_sub[j].product_type_name+'</div>';
							stritem_1+=	'	</div>';
							stritem_1+='</a>';
							stritem_1+=	' </div>';	
							stritem_1 = stritem_1.replace('@id',data[0].product_type_vmall);
							stritem_1 = stritem_1.replace('@id2',list[i].list_cate_sub[j].parent);
							stritem_1 = stritem_1.replace('@id3',list[i].list_cate_sub[j].product_type_id);
							stritem+=stritem_1;				
							
						}
						content_row ='<div class="row row-box">'+stritem+'</div>';
						strhtml = strhtml.replace('@rowbox',content_row);
						//
						// branch
						var strbranch = '';
						var strbranch_i = '';
						var item_b = 1;
						var isactive = 0;	
						var iscom = 0;
						for(var j=0;j<list[i].list_cate_sub.length;j++){	
							if(list[i].list_cate_sub[j].list_branch.length > 0){
								var url_img = ReturnHosing_tomcat_upload()+'upload/branch/'+list[i].list_cate_sub[j].list_branch[0].image;
								strbranch+='<li class="col-sm-12" style="padding: 0px;">';
								strbranch+='<div class="fff">';
								strbranch+='<div class="thumbnail">';
								strbranch+='	<a href="#"><img style="height:100px;width:70%;margin-left:15%" src="'+url_img+'"';
								strbranch+='		alt=""></a>';
								strbranch+='</div></div></li>';							
								if(item_b==3){
									iscom = 0;
									if(isactive==0){
										var strbranch_ii = '<div class="item active">';
										strbranch_ii+=			'<ul class="thumbnails items_list">'+strbranch;
										strbranch_ii+='</ul></div>';
										strbranch_i+=strbranch_ii;
										isactive = 1;
									}
									else{										
										var strbranch_ii = '<div class="item">';
										strbranch_ii+=			'<ul class="thumbnails items_list">'+strbranch;
										strbranch_ii+='</ul></div>';
										strbranch_i+=strbranch_ii;
									}
									strbranch='';
									item_b = 0;
								}
								else{
									iscom = 1;
								}
								item_b+=1;
							}							
						}	
						// trong truong hop so hinh bi  le
						if(strbranch.length>0){
							if(isactive==0){
								var strbranch_ii = '<div class="item active">';
								strbranch_ii+=			'<ul class="thumbnails items_list">'+strbranch;
								strbranch_ii+='</ul></div>';
								strbranch_i+=strbranch_ii;								
							}
							else{										
								var strbranch_ii = '<div class="item">';
								strbranch_ii+=			'<ul class="thumbnails items_list">'+strbranch;
								strbranch_ii+='</ul></div>';
								strbranch_i+=strbranch_ii;
							}
						}
												
						if(strbranch_i.length>0){
							
							var strbranch_a='';										
							strbranch_a+= '<div class="carousel slide" id="myCarousel'+list[i].product_type_id+'">';
							strbranch_a+='<div class="control-box pager" style="float:left"> ';
							strbranch_a+='<div style="width: 30px; height: 50px; position: absolute; top: 0; text-align: center; padding: 17px 0px;">';
							strbranch_a+='<a data-slide="prev" href="#myCarousel'+list[i].product_type_id+'" class=""><i';
							strbranch_a+='	class="glyphicon glyphicon-chevron-left"></i></a>'
							strbranch_a+='</div>';
							strbranch_a+='<div style="width: 30px; height: 50px; position: absolute; top: 0;left:50px; text-align: center; padding: 17px 0px;;">';
							strbranch_a+='<a data-slide="next" href="#myCarousel'+list[i].product_type_id+'" class=""><i'
							strbranch_a+='	class="glyphicon glyphicon-chevron-right"></i>';
							strbranch_a+='</div></div>';
							strbranch_a+='<div class="carousel-inner">'+strbranch_i+'</div></div>';							
							strhtml = strhtml.replace('@branch',strbranch_a);
							strhtml = strhtml.replace('@display',"block");	
						}
						else{
							strhtml = strhtml.replace('@branch','');
							strhtml = strhtml.replace('@display',"none");
						}
						//											
					}
					else{
						strhtml = strhtml.replace('@display',"none");
					}									
					//
					
					strhtml_a += strhtml;					
				}				
				var time_menu = setTimeout(function(){
					clearTimeout(time_menu);
					$("#contain_search").text('');
					$("#contain_search").append(strhtml_a);
				},1000); 
			}					
		});
	}
	function load_product_recommend(){
		var id = getUrlParameter('id');		
		if(id==null || id=="" || id == undefined){
			window.location.href=ReturnHosing_apache() + 'page_not_found.html';
		}		
		var pdata = {
				'id':id
		};
		extras_GET_json('ProductController','get_product_recommend',pdata,function(data){
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
	function load_product_care(){
		var id = getUrlParameter('id');		
		if(id==null || id=="" || id == undefined){
			window.location.href=ReturnHosing_apache() + 'page_not_found.html';
		}		
		var pdata = {
				'id':id
		};
		extras_GET_json('ProductController','get_product_care',pdata,function(data){
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
						strproduct+='<li class="col-sm-3" style="padding: 0px;">';
						strproduct+='<div class="fff_qt">';
						strproduct+='<div class="thumbnail_qt">';
						strproduct+='	<a 	class="linkproduct" target="_blank" href="'+base_url_detail+'"><img class="img_product_recommend" src="'+data[i].productImage+'" style="height:200px;width:100%;" alt=""></a>';
						strproduct+='</div>';
						strproduct+='<div class="caption text-center">';
						strproduct+='<p>'+data[i].productName+'</p>';
						strproduct+='</div></div></li>';							
						if(item_b==4){
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
	function exec_link(){
		$(".linkproduct").click(function(){
			var link = $(this).attr('opso_link');
			window.open(link);			
		});
	}
	function get_item_menu(callback){
		var url = ReturnHosing_apache()+'txt/menu_search_category.txt';
		$.get(url,function(data){
			callback(data);
		});
	}
	function get_box(callback){
		var url = ReturnHosing_apache()+'txt/item_box_category.txt';
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
