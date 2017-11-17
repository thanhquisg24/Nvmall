
$(function(){
	exe_load_header(function(){
		append_loading("#content_slide_product_sellbest");
		append_loading("#content_slide_membership_seller");
		append_loading("#content_category");
		append_loading("#content_product_qt");
		append_loading("#content_slide_brand");		
		load_product_sellbest();
		load_product_membership();
		load_category();
		load_product_care();
		load_brand();
	});
	function load_product_sellbest(){		
		extras_GET_json('ProductController','get_product_sell_best','',function(data){
			if(data!=null){
				get_box_sellbest(function(str){
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
						strproduct+='	<a 	class="linkproduct" target="_blank" href="'+base_url_detail+'"><img class="img_product_recommend" src="'+data[i].productImage+'" style="height:200px" alt=""></a>';
						strproduct+='</div>';
						strproduct+='<div class="caption text-center">';
						strproduct+='<p>'+data[i].productName+'</p>';
						strproduct+='</div></div></li>';							
						if(item_b==6){
							iscom = 0;
							if(isbreak<1){							
								strbrk+='<ul class="thumbnails items_list">'+strproduct+'<ul/>';
								isbreak+=1;
								strproduct='';	
								item_b = 0;	
							}
							if(isbreak==1){
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
						if(isbreak<1){							
							strbrk+='<ul class="thumbnails items_list">'+strproduct+'<ul/>';
							isbreak+=1;
							item_b = 0;	
						}
						if(isbreak==1){
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
							strproduct='';							
							isbreak = 0;
						}	
					}
					var strhtml = str;
					strhtml = strhtml.replace('@content_slide',strproduct_i);
					var time_menu = setTimeout(function(){
						clearTimeout(time_menu);						
						$("#content_slide_product_sellbest").html(strhtml);
					},1000); 	
				});							
			}
		});
	}	
	function load_product_membership(){		
		extras_GET_json('ProductController','get_product_memberhip','',function(data){
			if(data!=null){
				get_box_membership(function(str){
					var strproduct = '';
					var strproduct_i = '';
					var item_b = 1;
					var isactive = 0;	
					var iscom = 1;
					var isbreak = 0;
					var strbrk = '';
					for(var i=0;i<data.length;i++){	
						var base_url_detail =url_detail_membership;
						base_url_detail = base_url_detail.replace('@id',data[i].productId);
						strproduct+='<li class="col-sm-2" style="padding: 0px;">';
						strproduct+='<div class="fff_qt">';
						strproduct+='<div class="thumbnail_qt">';
						strproduct+='	<a 	class="linkproduct" target="_blank" href="'+base_url_detail+'"><img class="img_product_recommend" src="'+ftp_server()+'product/'+data[i].productImage+'" style="height:200px" alt=""></a>';
						strproduct+='</div>';
						strproduct+='<div class="caption text-center">';
						strproduct+='<p>'+data[i].productName+'</p>';
						strproduct+='</div></div></li>';							
						if(item_b==6){
							iscom = 0;
							if(isbreak<1){							
								strbrk+='<ul class="thumbnails items_list">'+strproduct+'<ul/>';
								isbreak+=1;
								strproduct='';	
								item_b = 0;	
							}
							if(isbreak==1){
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
						if(isbreak<1){							
							strbrk+='<ul class="thumbnails items_list">'+strproduct+'<ul/>';
							isbreak+=1;
							item_b = 0;	
						}
						if(isbreak==1){
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
							strproduct='';							
							isbreak = 0;
						}	
					}
					var strhtml = str;
					strhtml = strhtml.replace('@content_slide',strproduct_i);
					var time_menu = setTimeout(function(){
						clearTimeout(time_menu);						
						$("#content_slide_membership_seller").html(strhtml);
					},1000); 	
				});							
			}
		});
	}	
	function load_category(){
		extras_GET_json('CategoryController','get_category_index','',function(data){
			if(data!==null){
				get_box_product(function(str){
					var strhtml_a = '';
					var strhtml_s = '';
					for(var i=0;i<data.length;i++){				
						
						var strhtml = str;						
						strhtml = strhtml.replace('@id',i)
						strhtml = strhtml.replace('@category',data[i].product_type_name)
						strhtml = strhtml.replace('@idmain',data[i].product_type_vmall)
						//slide
						if(data[i].list_slide.length > 0){
							var strhtml_slide_i = '';							
							var strhtml_slide = '';							
							var item_b = 1;
							var isactive = 0;	
							var iscom = 0;
							var slide_num = 0;							
							for(var j=0;j<data[i].list_slide.length;j++){
								var url_img_l = ReturnHosing_tomcat_upload()+'upload/category/'+data[i].list_slide[j].category_img;
								strhtml_slide += '<li class="col-xs-6" style="padding: 0px;">';
								strhtml_slide+=	'<a class="viewcategory" target="_blank" href="category_second_product.html?id$@id&id2$@id2&id3$@id3"><div class="viewcard">';
								strhtml_slide+=	'<img src="'+url_img_l+'" alt="">';
								strhtml_slide+=	'	<div class="caption text-center">';
								strhtml_slide+=	'			<p class="caption_slide">'+data[i].list_slide[j].product_type_name+'</p>';													
								strhtml_slide+=	'	</div>';
								strhtml_slide+=	'</div></a>';
								strhtml_slide+=	'</li>';
								strhtml_slide = strhtml_slide.replace('@id',data[i].product_type_vmall);
								strhtml_slide = strhtml_slide.replace('@id2',data[i].list_slide[j].parent);
								strhtml_slide = strhtml_slide.replace('@id3',data[i].list_slide[j].product_type_id);
									if(item_b==4){										
										iscom = 0;
										if(isactive==0){
											var strhtml_slide_ii = '<div class="item active">';
											strhtml_slide_ii+=			'<ul class="thumbnails items_list">'+strhtml_slide;
											strhtml_slide_ii+='</ul></div>';
											strhtml_slide_i+=strhtml_slide_ii;
											isactive = 1;
										}
										else{										
											var strhtml_slide_ii = '<div class="item">';
											strhtml_slide_ii+=			'<ul class="thumbnails items_list">'+strhtml_slide;
											strhtml_slide_ii+='</ul></div>';
											strhtml_slide_i+=strhtml_slide_ii;
										}
										strhtml_slide='';
										item_b = 0;
										slide_num+=1;
									}
									else{
										iscom = 1;
									}
									item_b+=1;
							}
							if(strhtml_slide.length>0){
								if(isactive==0){
									var strhtml_slide_ii = '<div class="item active">';
									strhtml_slide_ii+=			'<ul class="thumbnails items_list">'+strhtml_slide;
									strhtml_slide_ii+='</ul></div>';
									strhtml_slide_i+=strhtml_slide_ii;
									isactive = 1;
								}
								else{										
									var strhtml_slide_ii = '<div class="item">';
									strhtml_slide_ii+=			'<ul class="thumbnails items_list">'+strhtml_slide;
									strhtml_slide_ii+='</ul></div>';
									strhtml_slide_i+=strhtml_slide_ii;
								}
								slide_num+=1;
							}
							//
							if(strhtml_slide_i.length>0){
								var str_slide_num = '';
								for(j=0;j<slide_num;j++){
									var str_slide='';
									if(j==0){
										str_slide = '<li data-target="#slidecategory'+i+'" data-slide-to="'+j+'" class="active"></li>';
									}
									else{
										str_slide = '<li data-target="#slidecategory'+i+'" data-slide-to="'+j+'" ></li>';
									}
									str_slide_num+=str_slide;
								}
								var str_slide_all = ' <div class="col-xs-12" style="height:20px;position:relative">';
									str_slide_all+='<ol class="carousel-indicators " >';
									str_slide_all+=str_slide_num;
									str_slide_all+='</ol></div>'
								var strbranch_a='<div class="carousel slide" id="slidecategory'+i+'">';
									strbranch_a+=	'<div class="carousel-inner">';
									strbranch_a+=strhtml_slide_i;
									strbranch_a+=str_slide_all;		
									strbranch_a+='	</div></div>';
									
									strhtml = strhtml.replace('@display',"block");
									strhtml = strhtml.replace('@content_slide',strbranch_a);
							}
							else{
								strhtml = strhtml.replace('@content_slide','');
								strhtml = strhtml.replace('@display',"none");
								
							}					
						}
						else{
							strhtml = strhtml.replace('@content_slide','');
						//	strhtml = strhtml.replace('@display',"none");
						}
						// end slide
						// big category
						if(data[i].list_item_big.length > 0){
							var obj = data[i].list_item_big[0];
							var url_img_l = ReturnHosing_tomcat_upload()+'upload/category/'+obj.category_img;
							strhtml = strhtml.replace('@product_type_image_big',url_img_l);
							strhtml = strhtml.replace('@product_type_name',obj.product_type_name);
							strhtml = strhtml.replace('@id',data[i].product_type_vmall);
							strhtml = strhtml.replace('@id2',obj.parent);
							strhtml = strhtml.replace('@id3',obj.product_type_id);
						}
						else{
							strhtml = strhtml.replace('@product_type_image_big','');
							strhtml = strhtml.replace('@product_type_name','');
							strhtml = strhtml.replace('@display',"none");
						} //end big category
						//  category sub
						if(data[i].list_p.length>0){
							var strhtml_p = '';
							for(var j=0;j<data[i].list_p.length;j++){
								var url_img_l = ReturnHosing_tomcat_upload()+'upload/category/'+data[i].list_p[j].category_img;
								strhtml_p+='<div class="col-xs-4 viewcard" style="padding: 0px;">';
								strhtml_p+='<a  target="_blank" href="category_second_product.html?id$@id&id2$@id2&id3$@id3">	<img';
								strhtml_p+='	src="'+url_img_l+'"';
								strhtml_p+='alt="'+data[i].list_p[j].product_type_name+'">'
								strhtml_p+='<div class="caption text-center">'
								strhtml_p+='<p>'+data[i].list_p[j].product_type_name+'</p>';
								strhtml_p+='</div><a></div>';
								strhtml_p = strhtml_p.replace('@id',data[i].product_type_vmall);
								strhtml_p = strhtml_p.replace('@id2',data[i].list_p[j].parent);
								strhtml_p = strhtml_p.replace('@id3',data[i].list_p[j].product_type_id);
							}
							strhtml = strhtml.replace('@content_category_sub',strhtml_p);
						}
						else{
							strhtml = strhtml.replace('@content_category_sub','');
						}
						//end category sub
						//branch
						if(data[i].list_branch.length > 0){
							var strhtml_b ='';
							for(var j=0;j<data[i].list_branch.length;j++){
								var url_img_l = ReturnHosing_tomcat_upload()+'upload/branch/'+data[i].list_branch[j].image;
								strhtml_b +='<a href="brand.html?id$'+data[i].list_branch[j].id+'" title="'+data[i].list_branch[j].name+'">';
								strhtml_b +='<img src="'+url_img_l+'" alt="" height="50" width="100">';
								strhtml_b +='</a>';
							}								
							strhtml = strhtml.replace('@branch',strhtml_b);
						}
						else{
							strhtml = strhtml.replace('@branch','');
						}
						//category top
						if(data[i].list_top.length > 0){
							var strhtml_t ='';
							for(var j=0;j<data[i].list_top.length;j++){
								var url_img_l = ReturnHosing_tomcat_upload()+'upload/branch/'+data[i].list_top[j].category_img;
								strhtml_t +='<a  target="_blank" href="category_second_product.html?id$@id&id2$@id2&id3$@id3" title="'+data[i].list_top[j].product_type_name+'">';
								strhtml_t +=data[i].list_top[j].product_type_name
								strhtml_t +='</a>';
								strhtml_t = strhtml_t.replace('@id',data[i].product_type_vmall);
								strhtml_t = strhtml_t.replace('@id2',data[i].list_top[j].parent);
								strhtml_t = strhtml_t.replace('@id3',data[i].list_top[j].product_type_id);
							}								
							strhtml = strhtml.replace('@category_top',strhtml_t);
						}
						else{
							strhtml = strhtml.replace('@category_top','');
						}
						//
						var url_icon = ReturnHosing_tomcat_upload()+'upload/icon/'+data[i].icon;
						if(i==0){
							var strscroll = '<li class="active"><a href="#cate'+i+'"><img src="'+url_icon+'" class="icon_cate" /></a></li>';
							strhtml_s+=strscroll;
								
						}
						else{
							var strscroll = '<li><a href="#cate'+i+'"><img src="'+url_icon+'" class="icon_cate" /></a></li>';
							strhtml_s+=strscroll;
						}
						
						
						//
						strhtml_a+=strhtml;
					}
					var time_menu = setTimeout(function(){
						clearTimeout(time_menu);
						$("#contain_scroll_cate").append(strhtml_s);
						$("#content_category").html(strhtml_a);
						load_scroll();
					},1000); 
				});
			}
		});
	}
	function load_scroll(){
		$("#contain_scroll_cate a").on('click', function(event) {
		    // Make sure this.hash has a value before overriding default behavior
		    if (this.hash !== "") {
		      // Prevent default anchor click behavior
		      event.preventDefault();

		      // Store hash
		      var hash = this.hash;

		      // Using jQuery's animate() method to add smooth page scroll
		      // The optional number (800) specifies the number of milliseconds it takes to scroll to the specified area
		      var _roll1 = $(hash).offset().top - 30;
				$('html, body').animate({
				   scrollTop: _roll1
				}, 800, function(){		   
		      });
		    }  // End if
		  });
		 $(document).scroll(function () 
		{
					
			var position = $(this).scrollTop();
			if (position > 800) 
			{
				$("#myScrollspy").css('display','block');
			} 
			else 
			{
				$("#myScrollspy").css('display','none');
			}							
		});
	}
	function load_product_care(){
		
		extras_GET_json('ProductController','get_product_care_index','',function(data){
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
						strproduct+='	<a 	class="linkproduct" target="_blank" href="'+base_url_detail+'"><img class="img_product_recommend" src="'+data[i].productImage+'" style="height:200px" alt=""></a>';
						strproduct+='</div>';
						strproduct+='<div class="caption text-center">';
						strproduct+='<p class="caption_slide_qt">'+data[i].productName+'</p>';
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
							strproduct='';							
							isbreak = 0;
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
	function load_brand(){
		
		extras_GET_json('CategoryController','get_brand','',function(data){			
			if(data!=null){
				get_box_brand(function(str){
					var strproduct = '';
					var strproduct_i = '';
					var item_b = 1;
					var isactive = 0;	
					var iscom = 1;
					var isbreak = 0;
					var strbrk = '';
					
					for(var i=0;i<data.length;i++){	
						var url_img_l = ReturnHosing_tomcat_upload()+'upload/branch/'+data[i].image;
						
						strproduct+='<li class="col-sm-4" style="padding: 0px;">';
						strproduct+='<div class="fff_qt">';
						strproduct+='<div class="thumbnail_qt">';
						strproduct+='	<a title="'+data[i].name+'" 	class="linkproduct" href="brand.html?id$'+data[i].id+'"><img class="img_product_recommend" src="'+url_img_l+'" style="height:100px;width:80%;" alt=""></a>';
						strproduct+='</div>';
						strproduct+='<div class="caption text-center">';
						strproduct+='<p class="caption_slide_qt">'+data[i].name+'</p>';
						strproduct+='</div></div></li>';							
						if(item_b<3){
							iscom = 0;
							if(isbreak<3){							
								strbrk+='<ul class="thumbnails items_list">'+strproduct+'<ul/>';
								isbreak+=1;
								strproduct='';	
								item_b = 0;	
							}
							if(isbreak==3){
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
						if(isbreak<3){							
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
					strhtml = strhtml.replace('@contain_brand',strproduct_i);
					var time_menu = setTimeout(function(){
						clearTimeout(time_menu);
						$("#content_slide_brand").html(strhtml);
					},1000); 	
				});							
			}
		});
	}
	function get_box_sellbest(callback){
		var url = ReturnHosing_apache()+'txt/item_box_category_sellbest.txt';
		$.get(url,function(data){
			callback(data);
		});
	}
	function get_box_membership(callback){
		var url = ReturnHosing_apache()+'txt/item_box_category_membership.txt';
		$.get(url,function(data){
			callback(data);
		});
	}
	function get_box_product(callback){
		var url = ReturnHosing_apache()+'txt/box_product_index.txt';
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
	function get_box_brand(callback){
		var url = ReturnHosing_apache()+'txt/item_box_brand.txt';
		$.get(url,function(data){
			callback(data);
		});
	}
});

