$(function() {
	load_catetory_summary();
	function load_catetory_summary(){
		append_loading("#content_category_summary");		
		extras_GET_json('CategoryController','get_category_summary','',function(data){
			if(data!==null){
				get_box_summary(function(str){					
					var strhtml_a = '';
					for(var i=0;i<data.length;i++){
						var strhtml = str;
						strhtml = strhtml.replace('@category_name',data[i].product_type_name);
						strhtml = strhtml.replace('@href','category.html?id$'+data[i].product_type_vmall);
						if(data[i].list_slide.length>0){
							var strhtml_i ='';
							for(var j=0;j<data[i].list_slide.length;j++){
								strhtml_i+='<li><a href="category_second_product.html?id$'+data[i].product_type_vmall
										+'&id2$'+data[i].list_slide[j].parent+'&id3$'+data[i].list_slide[j].product_type_id+'">';
								strhtml_i+=data[i].list_slide[j].product_type_name+'</a> </li>';								
							}
							strhtml = strhtml.replace('@content_cateogory',strhtml_i);
						}
						else{
							strhtml = strhtml.replace('@content_cateogory','');
						}		
						strhtml_a+=strhtml;
					}
					var time_menu = setTimeout(function(){
						clearTimeout(time_menu);
						$("#content_category_summary").html(strhtml_a);
					},1000); 
				});
			}
		});
	}
	function get_box_summary(callback){
		var url = ReturnHosing_apache()+'txt/item_category_summary.txt';
		$.get(url,function(data){
			callback(data);
		});
	}
	function clear_content(){
		$("#txtemail_footer").val("");
		$("#txtfullname_footer").val("");
	}
	
	function checkform(){
		
		var check=true;
		var email = $("#txtemail_footer").val();
		var name = $("#txtfullname_footer").val();
		if(email==null || email==""){
			showdialog('dialogmanual',0,'Xin nhập email','','');
			check = false;
			return check;
		}
		if(name==null || name==""){
			showdialog('dialogmanual',0,'Xin nhập họ tên','','');
			check = false;
			return check;
		}
		return check;
	}
	$("#btnsend_footer").click(function(){
		
		
		
		if(checkform()){	
			var email = $("#txtemail_footer").val();
			var name = $("#txtfullname_footer").val();
			var pdata={
					'email':email,
					'fullname':name
			};
			extras_GET_json("MemberController", "insert_customer", pdata,function(data) {
				 if(data!=null){
					 var error= data;
					 if(error==0){
						 showdialog('dialogmanual', 0, 'Đăng ký nhận tin nhắn qua email thành công', '', '');
						 clear_content();
					 }
					 else if(error==2){
						 showdialog('dialogmanual', 0, 'Email này đã tồn tại', '', '');
						 clear_content();
					 }
					 else {
						 showdialog('dialogmanual', 0, 'Đăng ký không thành công', '', '');
						 clear_content();
					 }
				 }
		})
		}
	})
	$("#linkabout1").click(function(){
		var url = ReturnHosing_apache_vivmall()+'about.html';
		window.location.href=url;
	});
	$("#linkregulation").click(function(){
		var url = ReturnHosing_apache_vivmall()+'regulation.html';
		window.location.href=url;
	});
	$("#linkcontact").click(function(){
		var url = ReturnHosing_apache_vivmall()+'contact.html';
		window.location.href=url;
	});
	$("#linksaleoff").click(function(){
		var url = ReturnHosing_apache_vivmall()+'saleoff.html';
		window.location.href=url;
	});
	$("#linknewstech").click(function(){
		var url = ReturnHosing_apache_newsvmall()+'index.html';
		window.location.href=url;
	});
	$("#linkjob").click(function(){
		var url = ReturnHosing_apache_jobvmall()+'index.html';
		window.location.href=url;
	});
	$("#linkscheme1").click(function(){
		window.location.href=ReturnHosing_apache_vivmall()+'scheme.html';
	});
	$("#linkrule1").click(function(){
		window.location.href=ReturnHosing_apache_vivmall()+'rule.html';
	});
});


