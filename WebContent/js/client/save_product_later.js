$(function(){
	exe_load_header_history(function(out){	
		if(out==true){
			if(email_login==null || email_login==''){
				showdialogfunctionok("dialogmanual", "Vui lòng đăng nhập",function(){
					window.location.href=ReturnHosing_apache_account()+"signin.html";
				})
			}else{
				load_product_later(email_login);
			}
		}
	});	
	
});
function load_product_later(email){		
	var pdata={'email':email};
	extras_GET_json('MemberController','get_product_save_later',pdata,function(data){
		if(data!=null){
			$("#content-save-product").text('');
			load_text(function(str){
				for(var i=0;i<data.length;i++){
					var html = str;
					var base_url_detail = url_detail;
					base_url_detail = base_url_detail.replace('@id',data[i].productId);
					base_url_detail = base_url_detail.replace('@url',window.location.href);		
					html = html.replace('@imgurl',data[i].productImage);
					html = html.replace('@property',data[i].property_value);
					html = html.replace('@color',data[i].color_value);
					html = html.replace('@productid',data[i].productId);
					html = html.replace('@href',base_url_detail);
					html = html.replace('@nameproduct',data[i].productName);
					html = html.replace('@priceproduct',formatcurrency(data[i].productPrice));
					html = html.replace('@description',data[i].productDes);
					html = html.replace('@idremove',data[i].customer_id+"&"+data[i].productId+"&"+data[i].property+"&"+data[i].color);
					$("#content-save-product").append(html);
				}	
				exec_event();
			});
		}
	});
}
function load_text(callback){
	var url = ReturnHosing_apache()+'txt/item_product_later.txt';
	$.get(url,function(data){
		callback(data);
	});
}
function exec_event(){
	/*$(".savebtn").click(function(){
		var id = $(this).attr('id');
		var arr = id.split('_');
		id = arr[1];
		var pdata =  {
				'product_id':id,
				'product_quantity':1
		};
		blockbg();
		Return_get("ShoppingCartController","add_shopping_cart",pdata,"GET",function(data){
			unblockbg();
			if (data != null) {
				showdialog('dialogmanual',0,'Thêm giỏ hàng thành công','','');	
			}
		});
	});*/
	$(".btncancel").click(function(){
		var id = $(this).attr('id');
		var arr = id.split('&');
		var pdata =  {
				'email':email_login,
				'customer_id':arr[0],
				'product_id':arr[1],
				'property':arr[2],
				'color':arr[3]
		};
		blockbg();
		extras_GET_json('ProductController','remove_product_later',pdata,function(data){
			unblockbg();
			if (data != null) {					
				if(data=='0'){
					showdialog('dialogmanual',0,'Hủy bỏ thành công','','');		
					location.reload();
				}
				else if(data=='-2'){
					showdialog('dialogmanual',0,'Sản phẩm không tồn tại!','','');		
				}
				else{
					showdialog('dialogmanual',0,'Hủy bỏ không thành công','','');		
				}
			}
		});
		
	});
}