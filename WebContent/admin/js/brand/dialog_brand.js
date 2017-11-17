$(function(){
	$('#dialog_brand').dialog({
	    title: 'New Brand',
	    width: 400,
	    closed: true,
	    cache: false,
	    modal: true,
	    buttons: [{
            text: 'Save',
            iconCls: 'icon-save',
            handler: function() {
            	var url = extras_Hosting["tomcatSpring_context"]
				+'ad/brandController/add_update_branch';
            	$('#fm_brand').form('submit',{
            		url: url,
            		onSubmit: function(){
            			return $(this).form('validate');
            		},
            		success: function(result){
            			var data=JSON.parse(result);
            	        $.messager.alert('Info', data.message, 'info');
            	        if(data.f=="0"){
            	        	 $("#dialog_brand").dialog('close');
            	        	 $('#iddatagrip').datagrid('clearChecked');
     		        		$('#iddatagrip').datagrid('reload');
            	        }
            		}
            	});
            }
        }, {
            text: 'Cancel',
            iconCls: 'icon-cancel',
            handler: function() {
                $("#dialog_brand").dialog('close');
            }
        }]
    });
	
	$('input[name="dotxtimage"]').ajaxfileupload({
	    'action':  extras_Hosting["tomcatSpring_context"]+"ad/UploadController/upload_image_normal_withparam?folder=save_imgbranch",
	    'onComplete': function(response) {	
	        if (response.status==false) {
	        	$.messager.alert('Error',response.message,'error');
	        }
	        if (response.status==true) {
	        	//$.messager.alert('Info',response.message,'info');
	            var pram = response.pram;
	            $("#idfm_brandimg").val(pram);
	            $("#img_brand").attr('src',extras_Hosting["tomcat"]+'upload/branch/'+pram);
	        }

	    },
	    'onStart': function() {
	    	var fileSize = this.get(0).files[0].size;
	     	if(fileSize>1024*1000){
	     		$.messager.alert('Warning','File này có kích cỡ lớn hơn cho phép. Xin chọn hình ảnh khác!!','warning');
	     		 $("#idfm_brandimg").val(pram);
	     		return false;
	     	}
	    }
	});
});