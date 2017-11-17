$(function(){
	$('#dialog_ae_catgorynews').dialog({
	    title: 'New City ',
	    width: 400,
	    closed: true,
	    cache: false,
	    modal: true,
	    buttons: [{
            text: 'Save',
            iconCls: 'icon-save',
            handler: function() {
            	//var  product_type_id=$("#idchooseproductype").textbox('getValue');
            	var url = extras_Hosting["tomcatSpring_context"]
				+'ad/NewsController/add_update_catgorynews';
            	$('#fm_catgorynews').form('submit',{
            		url: url,
            		onSubmit: function(){
            			return $(this).form('validate');
            		},
            		success: function(result){
            			var data=JSON.parse(result);
            	        $.messager.alert('Info', data.message, 'info');
            	        if(data.f=="0"){
            	        	 $("#dialog_ae_catgorynews").dialog('close');
            	        		$('#fm_catgorynews').form('clear');
            	        		$('#cc_catgorynews').combogrid('grid').datagrid('reload');
            	        }
            		}
            	});
            }
        }, {
            text: 'Cancel',
            iconCls: 'icon-cancel',
            handler: function() {
                $("#dialog_ae_catgorynews").dialog('close');
            }
        }]
    });
	$('#id_btn1_addnew_catgorynews').click(function(){
		$('#dialog_ae_catgorynews').dialog('open').dialog('center').dialog('setTitle','Add Catgory News');
		$('#fm_catgorynews').form('clear');
		$('#fm_catgorynews').find('input[name=ptype]').val('A');
		$('#fm_catgorynews').find('input[name=idcatgorynews]').val('0');
		$('#dialog_ae_catgorynews').find('img').attr('src','upload/no_image.jpg');
		//$('#idfm_catgorynewsname').textbox({readonly:false});
	});
	
	$('input[name="dotxtimage"]').ajaxfileupload({
	    'action':  extras_Hosting["tomcatSpring_context"]+"ad/UploadController/upload_image_normal_withparam?folder=save_imgcatgorynews",
	    'onComplete': function(response) {	
	        if (response.status==false) {
	        	$.messager.alert('Error',response.message,'error');
	        }
	        if (response.status==true) {
	        	//$.messager.alert('Info',response.message,'info');
	            var pram = response.pram;
	            $('#fm_catgorynews').find('input[name=imgcatgorynews]').val(pram);
	            $('#dialog_ae_catgorynews').find('img').attr('src',extras_Hosting["tomcat"]+'upload/catgorynews/'+pram);
	        }

	    },
	    'onStart': function() {
	    	var fileSize = this.get(0).files[0].size;
	     	if(fileSize>1024*1000){
	     		$.messager.alert('Warning','File này có kích cỡ lớn hơn cho phép. Xin chọn hình ảnh khác!!','warning');
	     		 //$("#idfm_brandimg").val(pram);
	     		return false;
	     	}
	    }
	});
	
	$('#dialog_news_img').dialog({
	    title: 'News Image ',
	    width: 400,
	    closed: true,
	    cache: false,
	    modal: true,
	    buttons: [{
            text: 'Close',
            iconCls: 'icon-cancel',
            handler: function() {
                $("#dialog_news_img").dialog('close');
            }
        }]
    });
	$('#id_btn1_view_img').click(function(){
		$('#dialog_news_img').dialog('open').dialog('center');
		//$('#dialog_news_img').find('img').attr('src','upload/no_image.jpg');
		//$('#idfm_catgorynewsname').textbox({readonly:false});
	});
	
	$('input[name="donewsimg"]').ajaxfileupload({
	    'action':  extras_Hosting["tomcatSpring_context"]+"ad/UploadController/upload_image_normal_withparam?folder=save_imgnews",
	    'onComplete': function(response) {	
	        if (response.status==false) {
	        	$.messager.alert('Error',response.message,'error');
	        }
	        if (response.status==true) {
	        	//$.messager.alert('Info',response.message,'info');
	            var pram = response.pram;
	            $('#dialog_news_img').find('#idnewsimg').val(pram);
	            $('#dialog_news_img').find('img').attr('src',extras_Hosting["tomcat"]+'upload/news/'+pram);
	        }

	    },
	    'onStart': function() {
	    	var fileSize = this.get(0).files[0].size;
	     	if(fileSize>1024*1000){
	     		$.messager.alert('Warning','File này có kích cỡ lớn hơn cho phép. Xin chọn hình ảnh khác!!','warning');
	     		 //$("#idfm_brandimg").val(pram);
	     		return false;
	     	}
	    }
	});
	
	
});	
	