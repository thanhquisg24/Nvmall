$(function(){
	exe_load_header_history(function(out){	
			if(out==true){
				if(email_login==null || email_login==''){
					showdialogfunctionok("dialogmanual", "Vui lòng đăng nhập",function(){
						window.location.href=ReturnHosing_apache_account()+"signin.html";
					})
				}else{
					load_layout_data_general(email_login);
				}
			}
	});	
	
	
	
	function load_layout_data_general(email_login){
		extras_GET_json("MemberController", "get_info_member", {'email':email_login}, function(data){
			if(data!=null){		
				$("#sfullname").val(data.fullname);
				$("#semail").text(data.email);
				$("#sphone").val(data.phone);
				$("#saddress").val(data.address);
			}				
		});			
		
	}

	$("#btnsubmit").click(function(){
		
		var pdata={
				'email':$("#semail").text(),
				'fullname':$("#sfullname").val(),
				'phone':$("#sphone").val(),
				'address':$("#saddress").val()
			};
		
		extras_GET_json("MemberController","update_info_member",pdata,function(data){
			if(data!=null && data!="null"){			
				if(data=="0"){
					showdialogfunctionok('dialogmanual','Cập nhật thông tin thành công',function(){
						location.reload();
					});
				}else{
					showdialogfunctionok('dialogmanual','Cập nhật thông tin lỗi',function(){
						location.reload();
					});
				}
			}				
		});		
		
	});
	
   

});
