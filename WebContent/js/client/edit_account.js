$(function(){
	$("#idbirthday").datepicker();
	 $("#idbirthday").datepicker("option", "dateFormat", "yy-mm-dd");
	 
	exe_load_header_history(function(out){		
		if(out==true){
			if(email_login==null || email_login==''){
				showdialogfunctionok("dialogmanual", "Vui lòng đăng nhập",function(){
					window.location.href=ReturnHosing_apache_account()+"signin.html";
				})
			}else{
				load_info_user();	
			}
		}
	$("#chkchangepass").prop('checked',false);
	$("#boxchange").css('display','none');
	});	
	function load_info_user(){
		extras_GET_json("MemberController", "get_info_member", {'email':email_login}, function(data){
			if(data!=null){
				$("#name").val(data.fullname);
				$("#Email").val(data.email);
				$("#idbirthday").val(data.birthday=="null"? "1/1/1900" : data.birthday);
				$("#address").val(data.address);
				var gender = data.gender;
				
				if(gender=='Nam'){ //nam
					$("#rdomale").prop('checked',true);
				}
				else if(gender=='Nu'){ //nu
					$("#rdofemale").prop('checked',true);
				}
			}
		});
	}
	$("#btnchangeinfo").click(function(){
		var fullname = $("#name").val();
		var email = $("#Email").val();
		var birthday = $("#idbirthday").val();
		var address = $("#address").val();
		var gender = $("input:radio[name ='rdogender']:checked").val();
		var oldpassword = $("#oldpassword").val();
		var newpassword = $("#newpassword").val();
		var retypepassword= $("#retypepassword").val();	
		var chkchangepass = $("#chkchangepass").prop('checked');
		if(chkchangepass==true){
			if(oldpassword==null || oldpassword==''){
				showdialog('dialogmanual',0,'Vui lòng nhập thông tin mật khẩu cũ','','');
				return;
			}
			if(newpassword==null || newpassword==''){
				showdialog('dialogmanual',0,'Vui lòng nhập thông tin mật khẩu mới','','');
				return;				
			}
			if(retypepassword==null || retypepassword==''){
				showdialog('dialogmanual',0,'Vui lòng nhập xác nhận mật khẩu mới','','');
				return;				
			}
			if(newpassword!=retypepassword){
				showdialog('dialogmanual',0,'Mật khẩu không khớp','','');
				return;
			}
		}
		var obj ={
			'email':email,
			'fullname':fullname,
			'birthday':birthday,
			'address':address,
			'gender':gender,
			'pass':oldpassword,
			'passnew':newpassword
		};
		 var str= JSON.stringify(obj);
		 var pdata={'str':str}; 
		 blockbg();
		 extras_GET_json("MemberController", "update_account", pdata, function(data){
			if(data!=null){
				unblockbg();			
				if(data == 0 ){					
					load_info_user();
					showdialogfunctionok('dialogmanual','Save Success',function(){
						location.reload();
					});	
				}					
				else if(data==3){
					showdialogfunctionok('dialogmanual','Wrong password',function(){
						location.reload();
					});					
				}else if(data=-1){
					showdialog('dialogmanual',0,'Failed, please try again','','');	
				}
			}
			else{
				showdialog('dialogmanual',0,'Save Not Success','','');
			}
		});
		
	});
	$("#chkchangepass").change(function() {
	    if(this.checked) {
	        $("#boxchange").css('display','block');
	    }
	    else{
	    	$("#boxchange").css('display','none');
	    }
	    	
	});
});