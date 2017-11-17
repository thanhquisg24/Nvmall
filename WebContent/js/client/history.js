$(function(){	
	 $("#linktocheckorder").attr("href",ReturnHosing_apache_shoppingcart()+'check-order.html');
	 get_name_user();
	 function get_name_user(){
			extras_GET_json("MemberController", "get_info_member", {'email':email_login}, function(data){		
			if(data!=null){		
					$("#txtuser_fullname").text(data.fullname);										
			}
		});
    }
});