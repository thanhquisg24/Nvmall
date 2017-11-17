$(function(){
	tinymce.remove();
	tinymce.init({
	    //selector: '#my_editor_question,#my_editor_guide_buy,#my_editor_smart_buy,#my_editor_safe_buy,#my_editor_pay_method',
		//mode : "exact",
		selector:'#my_editor_news',
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
		      		$("#newstitle").data('ptype','A');
		      		$("#newstitle").data('newsid','');
		      		$('#cc_catgorynews').combogrid('setValue',''); 
		      		$("#newstitle").textbox('setValue','');
		      		$("#newsshort_description").textbox('setValue','');
		      		$('#dialog_news_img').find('#idnewsimg').val('');
		      		$('#dialog_news_img').find('img').attr('src',extras_Hosting["tomcat"]+'upload/no_image.jpg');
		          }
		        }); 
	    	 
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
	    plugins: ["image","link","textcolor","code","charmap","searchreplace","visualblocks","preview","fullscreen","table","advlist"],
	    removed_menuitems: 'newdocument',
	    file_browser_callback_types: 'image',
	    file_browser_callback: function(field_name, url, type, win) {
	        if(type=='image'){
	        	$('#my_form input').click();
	        }
	    }
	});
	function load_content_and_set_it_to_MCE(MCEeditor){
		var text_aria_selector=MCEeditor.getElement();
		var ptype=$("#newstitle").data('ptype');
		var newsid=$("#newstitle").data('newsid');
		if(ptype=="A"){
			return;
		}
		if(newsid==""){
			return;
		}
		extras_GET_json(true,"ad/NewsController","get_Newsbyid",{'news_id':newsid},function(data){
			if(data !== null && typeof data === "object"){
				$(text_aria_selector).val(data.content);
				MCEeditor.setContent(data.content);
				$('#cc_catgorynews').combogrid('setValue',data.news_catgory); 
				$("#newstitle").textbox('setValue',data.title);
				$("#newsshort_description").textbox('setValue',data.short_description);
				$('#dialog_news_img').find('#idnewsimg').val(data.img);
				 $('#dialog_news_img').find('img').attr('src',extras_Hosting["tomcat"]+'upload/news/'+data.img);
			}
		});
	}
	function save_content_toDB_MCE(MCEeditor){
		if(check_befor_save()){
						var _mes_confirm="Save this News ,Are you sure?";
						var ptype=$("#newstitle").data('ptype');
						var newsid=$("#newstitle").data('newsid');
						if(ptype=="E"){
							_mes_confirm="Modify this '"+ newsid +"',Are you sure?"
						}
			 $.messager.confirm('Confirm',_mes_confirm,function(r){
				    if (r){
				    	var catgory_item=$('#cc_catgorynews').combogrid('getValue'); 
						var title_item=$("#newstitle").textbox('getValue');
						var shortdescription_item=$("#newsshort_description").textbox('getValue');
						var img_item=$('#dialog_news_img').find('#idnewsimg').val();
						var content_item=MCEeditor.getContent();
						var pdata={'ptype':ptype,'newsid':newsid,'catgory_news':catgory_item,'news_title':title_item,'short_description':shortdescription_item,'img':img_item,'content':content_item};
						extras_POST_json(true,"ad/NewsController","SaveNews",pdata,function(data){
							if(data !== null && typeof data === "object"){
								if(data.f=="0"){
									$("#newstitle").data('ptype','E');
									$("#newstitle").data('newsid',data.out_id);
									$.messager.alert('Result',"Save successfull!",'info',function(){
										$('#tt').tabs('select',0);
										window.location.reload();
									});
					        	}else{	
					        		$.messager.alert('Result',data.message,'info');
					        	}
							}
						});
				    }
				});//end $.messager
		}//end check
		
	}
	function check_befor_save(){
		var catgory_item=$('#cc_catgorynews').combogrid('getValue');
		if(catgory_item==null || catgory_item==""){
			$.messager.alert('Warning','Please choose News Catgory','warning');
			return false;
		}
		if(!$("#newstitle").textbox('isValid')){
			$("#newstitle").next().find('input').focus();

			return false;
		}
		if(!$("#newsshort_description").textbox('isValid')){
			$("#newsshort_description").next().find('input').focus();
			return false;
		}
		return true;
		//var title_item=$("#newstitle").textbox('getValue');
		//var shortdescription_item=$("#newsshort_description").textbox('getValue');
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