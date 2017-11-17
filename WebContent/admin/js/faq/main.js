$(function(){
	var title_="FAQ"
		$('title').text(title_);
	
	$("#tt").tabs({
		onSelect:function(title,index){
			//$("#idcurrent").val(title);
		},
		onClose:function(title,index){
			//$("#subtypeid_"+title).remove();
		}
	});
	
	tinymce.remove();
	jQuery.each( $('textarea'), function( i, val ) {
		var e=$(this);
		var key_item=e.data('save');
		//tinymce.createEditor("#"+ e.attr('id'),{});
		extras_GET_json(true,"ad/FAQController","get_FAQbyid",{'faq_id':key_item},function(data){
			if(data !== null && typeof data === "object"){			
						e.val(data.content);
						init_single_editor("#"+ e.attr('id'));
						//alert("#"+ e.attr('id'));
						
			}
		});
	});
	
	function init_single_editor(textare_id){
		tinymce.init({
		    //selector: '#my_editor_question,#my_editor_guide_buy,#my_editor_smart_buy,#my_editor_safe_buy,#my_editor_pay_method',
			//mode : "exact",
			selector:textare_id,
		    height : 450,
		    menubar: 'file edit insert view format table tools',
		    setup: function(editor) {
		    	 var text_aria_selector=editor.getElement();
		    	editor.addMenuItem('menu_reload', {
			          text: 'Reload',
			          context: 'file',
			          onclick: function() {
			            //editor.insertContent('&nbsp;Here\'s some content!&nbsp;');
			        	  load_content_and_set_it_to_MCE(editor);
			            //editor.setContent('&nbsp;Here\'s some content!&nbsp;');
			          }
			        });
		        editor.addMenuItem('menu_save', {
		          text: 'Save',
		          context: 'file',
		          onclick: function() {
		        	  save_content_toDB_MCE(editor);
		          }
		        });
		      //load_content_and_set_it_to_MCE(editor);
		      },
		  /*  menu: {
		        file: {title: 'File', items: 'newdocument'},
		        edit: {title: 'Edit', items: 'undo redo | cut copy paste pastetext | selectall'},
		        insert: {title: 'Insert', items: 'link media | template hr'},
		        view: {title: 'View', items: 'visualaid'},
		        format: {title: 'Format', items: 'bold italic underline strikethrough superscript subscript | formats | removeformat'},
		        table: {title: 'Table', items: 'inserttable tableprops deletetable | cell row column'},
		        tools: {title: 'Tools', items: 'spellchecker code'}
		      },*/
		    toolbar: 'undo redo | bold italic | fontselect fontsizeselect | forecolor backcolor |alignleft aligncenter alignright  alignjustify | bullist numlist indent outdent | link unlink image',
		    plugins: ["image","link","textcolor","code","charmap","searchreplace","visualblocks","preview","fullscreen","table","lists"],
		   
		    file_browser_callback_types: 'image',
		    file_browser_callback: function(field_name, url, type, win) {
		        if(type=='image'){
		        	$('#my_form input').click();
		        }
		    }

		});
	}
	

	function load_content_and_set_it_to_MCE(MCEeditor){
		var text_aria_selector=MCEeditor.getElement();
		var key_item=$(text_aria_selector).data('save');
		//alert(key_item);
		extras_GET_json(true,"ad/FAQController","get_FAQbyid",{'faq_id':key_item},function(data){
			if(data !== null && typeof data === "object"){
				$(text_aria_selector).val(data.content);
				MCEeditor.setContent(data.content);
			}
		});
	}
	function save_content_toDB_MCE(MCEeditor){
		var text_aria_selector=MCEeditor.getElement();
		var key_item=$(text_aria_selector).data('save');
		var content_item=MCEeditor.getContent();
		extras_POST_json(true,"ad/FAQController","SaveFAQ",{'faq_id':key_item,'faq_content':content_item},function(data){
			if(data !== null && typeof data === "object"){
				$.messager.alert('Result',data.message,'info');
				/*if(data.f=="0"){
	        		$('#dg').datagrid('clearChecked');
	    			$('#dg').datagrid('deleteRow',index_row);
	        	}*/
			}
		});
	}
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
