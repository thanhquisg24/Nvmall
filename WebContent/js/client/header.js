var check_timer_cart = false;
var check_timer_like = false;
$(function() {	
		check_cookie(function(out){
			get_main_category();
			load_slide();
			get_service_info_login_server();
			count_product_shopping_cart();
			count_product_like();
		});
		$("#btnshowmenu").click(function(){
			var data =$("#menu_main").attr('data');
			if(data=='hidden'){
				$("#menu_main").fadeIn('slow');
				$("#menu_main").attr('data','visible');
			}
			else{
				$("#menu_main").fadeOut('slow');
				$("#menu_main").attr('data','hidden');
			}
			
		});		
		function check_cookie(callback){
			if(Cookies.set('sessionid')==null || Cookies.set('sessionid')=='' || Cookies.set('sessionid') == undefined){
				extras_GET_json('SessionController','get_session','',function(data){
					if(data!=null)
					{
						if(publish==true){
							Cookies.set('sessionid',data.sessionid,{ domain: '.vivmall.vn'});
						}
						else{
							Cookies.set('sessionid',data.sessionid); // localhost
						}
						callback(true);
					}
				});
			}
			else{
				var ss_id = get_cookie_session_id(); // set cookie for all sub domain				
				if(publish==true){
					Cookies.set('sessionid',ss_id,{ domain: location.host});
				}
				else{
					Cookies.set('sessionid',ss_id); // localhost
				}
				callback(true);
			}
		}
		function get_main_category(){
			extras_GET_json('CategoryController','get_category','',function(data){
				if(data != null){
					if(data.length > 0){
						get_item_menu(function(str){
							get_item_menu_2(function(str1){
								// level 0
								var strhtml_a = '';
								for(var i=0;i<data.length;i++){
									var strhtml = '';
									var url_icon = ReturnHosing_tomcat_upload()+'upload/icon/'+data[i].icon;
									if(i<10){
										strhtml = str;
										strhtml = strhtml.replace('@id',data[i].product_type_vmall);
										strhtml = strhtml.replace('@srcicon',url_icon);
										strhtml = strhtml.replace('@category_name',data[i].product_type_name);		
										if(data[i].isnew=='0'){
											strhtml = strhtml.replace('@isnew','<img src="'+ReturnHosing_apache_vivmall()+'image/New_icons_10.gif" class="icon_new" />');	
										}
										else{
											strhtml = strhtml.replace('@isnew','');
										}
									}
									else{
										strhtml = str1;
										strhtml = strhtml.replace('@id',data[i].product_type_vmall);
										strhtml = strhtml.replace('@srcicon',url_icon);
										strhtml = strhtml.replace('@category_name',data[i].product_type_name);
										if(data[i].isnew=='0'){
											strhtml = strhtml.replace('@isnew','<img src="'+ReturnHosing_apache_vivmall()+'image/New_icons_10.gif" class="icon_new" />');	
										}
										else{
											strhtml = strhtml.replace('@isnew','');
										}
									}															
									//Menu sub
									if(data[i].list_cate_sub.length > 0){
										// level 1
										var strhtmls = '';
										for(var j=0;j<data[i].list_cate_sub.length;j++){
											var strhtmlsub = '<ul class="cataloge_list_lever2_list_group">@menu_sub</ul>';
											var strhtmlsub_1 = '<li class="title"><a target="_blank" href="'+ReturnHosing_apache_vivmall()+'category_product.html?id$@id&id2$@id2">@name</a></li>';
											strhtmlsub_1 = strhtmlsub_1.replace('@name',data[i].list_cate_sub[j].product_type_name);
											strhtmlsub_1 = strhtmlsub_1.replace('@id',data[i].product_type_vmall);
											strhtmlsub_1 = strhtmlsub_1.replace('@id2',data[i].list_cate_sub[j].product_type_id);
											if(data[i].list_cate_sub[j].list_cate_sub.length>0){
												// level 2											
												for(var k=0;k<data[i].list_cate_sub[j].list_cate_sub.length;k++){
													var strhtmlsub_2 = '<li><a target="_blank" href="'+ReturnHosing_apache_vivmall()+'category_second_product.html?id$@id&id2$@id2&id3$@id3">@name</a></li>';
													strhtmlsub_2 = strhtmlsub_2.replace('@name',data[i].list_cate_sub[j].list_cate_sub[k].product_type_name);
													strhtmlsub_2 = strhtmlsub_2.replace('@id',data[i].product_type_vmall);
													strhtmlsub_2 = strhtmlsub_2.replace('@id2',data[i].list_cate_sub[j].product_type_id);
													strhtmlsub_2 = strhtmlsub_2.replace('@id3',data[i].list_cate_sub[j].list_cate_sub[k].product_type_id);
													strhtmlsub_1+=strhtmlsub_2;
												}
												strhtmlsub = strhtmlsub.replace('@menu_sub',strhtmlsub_1);
											}
											else{
												strhtmlsub = strhtmlsub.replace('@menu_sub',strhtmlsub_1);
											}	
											strhtmls+=strhtmlsub;
										}
										strhtml = strhtml.replace('@menu_sub',strhtmls);	
									}
									else{
										strhtml = strhtml.replace('@menu_sub','');
									}
									
									// Menu brand
									if(data[i].list_brand.length>0){
										var str_brand='<ul style="margin-top:10px;">@menu_brand</ul>';
										var str_brand_tit='<li><a target="_blank" href="brand.html" style="font-weight:bold;color:black">Thương hiệu nổi bật</a></li>';
										for(var k =0;k<data[i].list_brand.length;k++){
											var str_brand_item='<li style="margin-top:10px;"><a target="_blank" href="brand.html?id$@id">'
												+'<img src="@src" alt=@name/ width="80px" height="40px"></a></li>';
											str_brand_item = str_brand_item.replace('@id',data[i].list_brand[k].id);
											str_brand_item = str_brand_item.replace('@name',data[i].list_brand[k].name);
											str_brand_item = str_brand_item.replace('@src',
													ReturnHosing_apache()+'upload/branch/'+data[i].list_brand[k].image);
											str_brand_tit+=str_brand_item;
										}
										str_brand = str_brand.replace('@menu_brand',str_brand_tit);
										strhtml = strhtml.replace('@content_brand',str_brand);
										
									}else{
										strhtml = strhtml.replace('@content_brand','');
									}
									//end brand
									strhtml_a+=strhtml;									
								}
								var time_menu = setTimeout(function(){
									clearTimeout(time_menu);								
									$("#ul_menu_main").html(strhtml_a);							
									fn_exec();
								},1000); 
							});							
						});
					}
				}
			});
		}
		$("#menu_main" ).hover(function() {
			$(".item_menu_more").css('display','block');
		},function(){
			$(".item_menu_more").css('display','none');
		});
		function fn_exec(){
			$(".menu_item_box").hover(function() {
				var img = $(this).find('.icon-menu').attr('src');
				img = img.replace('gif','png');
				$(this).find('.icon-menu').attr('src',img);
			},function(){
				var img = $(this).find('.icon-menu').attr('src');
				img = img.replace('png','gif');
				$(this).find('.icon-menu').attr('src',img);
			});
		}
		function load_slide(){
			var _ismobile  = ismobile();
			if(_ismobile==false){
				
				$("#content-slide").load(ReturnUrlSlide(), function (responseText, textStatus, XMLHttpRequest) {
					var time_slide = setTimeout(function(){
						clearTimeout(time_slide);
						$("#content-slide").css('display','block');
						$("#content-slide_loading").css('display','none');
					},1000); 
				});		
			}
		}	
		
		function get_item_menu(callback){
			var url = ReturnHosing_apache()+'txt/menu_main.txt';
			$.get(url,function(data){
				callback(data);
			});
		}
		function get_item_menu_2(callback){
			var url = ReturnHosing_apache()+'txt/menu_main2.txt';
			$.get(url,function(data){
				callback(data);
			});
		}
	    $("#linklogin").click(function(){
	    	
	    	var url = ReturnHosing_apache_account() + 'signin.html';
	    	var baseurl = window.location.href;
	    	url+="?baseurl$"+baseurl;
	    	window.location.href=url;
	    });
	    
	    $("#linkregister").click(function(){
	    	var url = ReturnHosing_apache_account() + 'register.html';	    	
	    	window.location.href=url;
	    });
	    
	    $("#linklogout").click(function(){
	    	
	    	blockbg();
	    	set_service_info_login_server();
	    });
	    $("#linkscheme").click(function(){
	    	window.location.href=ReturnHosing_apache_vivmall()+'scheme.html';
	    })
	     $("#linkrule").click(function(){
	    	window.location.href=ReturnHosing_apache_vivmall()+'rule.html';
	    })
	    $("#linkshoppingcart").click(function(){
	    	window.location.href=ReturnHosing_apache_shoppingcart()+'shoppingCart.html';
	    });
	    $("#linkshoppingcart2").click(function(){
	    	window.location.href=ReturnHosing_apache_shoppingcart()+'shoppingCart.html';
	    });
	    $("#linklikeproduct").click(function(){
	    	window.location.href=ReturnHosing_apache_dvmall()+'like_product.html';
	    });
	    $("#linklikeproduct2").click(function(){
	    	window.location.href=ReturnHosing_apache_dvmall()+'like_product.html';
	    });
	    $("#linklikeproduct3").click(function(){
	    	window.location.href=ReturnHosing_apache_dvmall()+'like_product.html';
	    });
	    $("#linkviewproduct").click(function(){
	    	window.location.href=ReturnHosing_apache_dvmall()+'review_product.html';
	    });
	    $("#linkviewproduct2").click(function(){
	    	window.location.href=ReturnHosing_apache_dvmall()+'review_product.html';
	    });
	    $("#linkratingproduct").click(function(){
	    	window.location.href=ReturnHosing_apache_dvmall()+'rating_product.html';
	    });
	   
	   function count_product_shopping_cart(){
		   if(publish==true){
			   get_count_product_shopping_cart();
			   return;
			   setInterval(function(){
				   if(check_timer_cart==false){
						check_timer_cart = true;
						get_count_product_shopping_cart();
				   }
				   
			   }, 1000);
		   }
		   else{

				get_count_product_shopping_cart();
		   }
		  

	   }
	   function count_product_like(){	
		   if(publish==true){
			   get_count_product_like();
			   return;
			   setInterval(function(){
				   if(check_timer_like==false){
						check_timer_like = true;
						get_count_product_like();
				   }
				   
			}, 1000);
		   }
		   else{
			   get_count_product_like();
		   }
		   

	   }
	   $("#linkhome").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'index.html';
		   window.location.href = url;
	   });
	   $("#linkhome1").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'index.html';
		   window.location.href = url;
	   });
	   $("#linkbuyintro").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'introduce.html';
		   window.location.href = url;
	   });
	   $("#linkshopping").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'introduce.html';
		   window.location.href = url;
	   });
	   $("#linkquestion").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'introduce.html';
		   window.location.href = url;
	   });
	   $("#linkbuyproduct").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'introduce.html';
		   window.location.href = url;
	   });
	   $("#linkpaymentway").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'introduce.html';
		   window.location.href = url;
	   });
	   $("#linkdilivery").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'introduce.html';
		   window.location.href = url;
	   });
	   $("#linkaboutvivmall").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'introduce.html';
		   window.location.href = url;
	   });
	   $("#linkcheckorder").click(function(){
		   
		   var url = ReturnHosing_apache_shoppingcart()+'check-order.html';		   
		   window.location.href = url;
	   });
	   $("#linkgeneralaccount").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'general_Account.html';
		   window.location.href = url;
	   });
	   $("#linkinfoaccount").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'edit_account.html';
		   window.location.href = url;
	   });
	   $("#linkorderhistory").click(function(){
		   var url = ReturnHosing_apache_shoppingcart()+'check-order.html';
		   window.location.href = url;
	   });
	   $("#linksaveproduct").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'save_product.html';
		   window.location.href = url;
	   });
	   $("#linkcommendproduct").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'comment_product.html';
		   window.location.href = url;
	   });
	   $("#linknewsproduct").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'new_product.html';
		   window.location.href = url;
	   });
	   $("#linknewproduct").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'product_new.html';
		   window.location.href = url;
	   });
	   $("#linkbuybest").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'product_mostbuy.html';
		   window.location.href = url;
	   });
	   $("#linkfindbest").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'key_product.html';
		   window.location.href = url;
	   });
	   $("#linktopproduct").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'day_product.html';
		   window.location.href = url;
	   });
	   $("#linkproductrecommend").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'product_recommend.html';
		   window.location.href = url;
	   });
	   $("#linkproductchoose").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'product_choose.html';
		   window.location.href = url;
	   });
	   $("#linkhightrating").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'product_highrating.html';
		   window.location.href = url;
	   });
	   $("#linkmostview").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'product_mostview.html';
		   window.location.href = url;
	   });
	   $("#linkfeedback").click(function(){
		   var url = ReturnHosing_apache_vivmall()+'feedback.html';
		   window.location.href = url;
	   });
	   $("#btnsearchbasic").click(function(){		
			var key = $("#search-form").val();
			if(key==null || key=='' || key==undefined){
				showdialog('dialogmanual',0,'Vui lòng nhập từ khóa tìm kiếm','','');
				return;
			}
			else{
				window.location.href=ReturnHosing_apache_vivmall()+'search_product.html?skey='+key;
			}
			
			
		});
	}); //end document
function get_count_product_like(){
	var pdata = {
			'session_id':Cookies.get('sessionid')
	}
	extras_GET_json('SessionController','get_session_like',pdata,function(data){
		check_timer_like = false;
		if(data!=null){
			$("#count_product_love").text(data);	
		}
	});
	
}


function get_count_product_shopping_cart(){
	var pdata = {
			'session_id':Cookies.get('sessionid')
	}
	extras_GET_json('SessionController','get_session_cart',pdata,function(data){
		check_timer_cart = false;
		if(data!=null){
			$("#count_product_shopping_cart").text(data);	
		}
	});
	
}

function get_service_info_login_server(){
	var pdata = {
			'session_id':Cookies.get('sessionid')
	}
	extras_GET_json('SessionController','get_user_login',pdata,function(data){
		myCallbackLogin(data);
	});
	check_user_loading();
}
function check_user_loading(){
	var time_menu = setInterval(function(){
		if(check_load_email==true){
			clearTimeout(time_menu);				
			$("#loading_user").html('');			
		}				
	},100);
}

function get_li_singin(callback){
	var url = ReturnHosing_apache()+'menu_signin.html';
	$.get(url,function(data){
		callback(data);
	});
}
function set_service_info_login_server(){
	var pdata = {
			'session_id':Cookies.get('sessionid')
	}
	extras_GET_json('SessionController','remove_user_login',pdata,function(data){
		if(data!=null)
		{			
			location.reload();
		}
	});
	
}
function myCallbackLogin(data){
	if(data!=null && data.email!='-1'){
		var icon='<img  src="'+ReturnHosing_apache_vivmall()+'image/user.png">';
		var str = data.email;
		if(str.length > 0){

			email_login =str;
			if(str.length>10){
				str=str.substring(0, 10);
				str+="...";
			}			
			$("#userlogin").html(icon+' xin chào: '+str);
			$(".infobelogin").css('display','none');
			$(".infoaflogin").css('display','block');
		}
		else{
			$(".infobelogin").css('display','block');	
		}
	}
	else{
		$(".infobelogin").css('display','block');
	}
	check_load_email= true;
}
