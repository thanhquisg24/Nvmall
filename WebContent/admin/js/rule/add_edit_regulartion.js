$(function(){
	extras_GET_json(true,"ad/RuleController","get_regulartion",{},function(data){
		if(data !== null && typeof data === "object"){
			$("#my_editor_regulartion").val(data.content_page)
			tinymce.remove();
			tinymce.init({
				selector:'#my_editor_regulartion',
			    height : 350,
			    menubar: 'file edit insert view format table tools',
			    setup: function(editor) {
			    	 var text_aria_selector=editor.getElement();
			      editor.addMenuItem('menu_new_document', {
				          text: 'New document',
				          context: 'file',
				          onclick: function() {
				           var MCEeditor=editor;
				      		var text_aria_selector=MCEeditor.getElement();
				      		$(text_aria_selector).val("");
				      		MCEeditor.setContent("");
				          }
				        }); 
			      },
			    toolbar: 'undo redo | bold italic | fontselect fontsizeselect | forecolor backcolor |alignleft aligncenter alignright  alignjustify | bullist numlist indent outdent | link unlink image',
			    plugins: ["image","link","textcolor","code","charmap","searchreplace","visualblocks","preview","fullscreen","table","advlist"],
			    removed_menuitems: 'newdocument',
			    file_browser_callback_types: 'image',
			    file_browser_callback: function(field_name, url, type, win) {
			        if(type=='image'){
			        	$('#my_form input').click();
			        }
			    }
			});
		
		}
	});
	//save button
	$("#id_save_rule").click(function(){
		var MCEeditor=tinymce.get('my_editor_regulartion');
		var content=MCEeditor.getContent();
		var pdata = {"id":"REGULATION",
					"content_page":content};
		var _mes_confirm="Save this! ,Are you sure?";
		 $.messager.confirm('Confirm',_mes_confirm,function(r){
				extras_POST_json(true,"ad/RuleController","Saverule",pdata,function(data){
					if(data !== null && typeof data === "object"){
						if(data.f=="0"){
							$.messager.alert('Result',"Save successfull!",'info',function(){
								$('#tt').tabs('select',0);
								window.location.reload();
							});
			        	}else{	
			        		$.messager.alert('Result',data.message,'info');
			        	}
					}
				});
		});
	});
	
	
	$('input[name="txtimage"]').ajaxfileupload({
		'valid_extensions' : ['gif','png','jpg','jpeg'],
	    'action':  extras_Hosting["tomcatSpring_context"]+"ad/UploadController/upload_image_normal_withparam?folder=saveeditor",
	    'onComplete': function(response) {	
	        if (response.status==false) {
	        	$.messager.alert('Error',response.message,'error');
	        	 //$("#strongmessage").html("<font color='red'>" + JSON.stringify(response.message) + " </font>");
	        }
	        if (response.status==true) {
	        	//$.messager.alert('Info',response.message,'info');
	            var pram = response.pram;
	            var url_img=  extras_Hosting["path_img_editor"]+pram;
	            top.$('.mce-btn.mce-open').parent().find('.mce-textbox').val(url_img);
	           // $("#id_uploadsuccess").val(pram);
	            //$("#branchimg_@index").attr('src','upload/branch/'+pram);
	           // $("#strongmessage").html("<font color='green'>" + JSON.stringify(response.message) + " </font>");
	            
	        }
	    },
	    'onStart': function() {
	    	var fileSize = this.get(0).files[0].size;
	     	if(fileSize>1024*1000){
	     		$.messager.alert('Warning','File này có kích cỡ lớn hơn cho phép. Xin chọn hình ảnh khác!!','warning');
	     		//$("#id_uploadsuccess").val('');
	     		return false;
	     	}
	    }
	});
	
});