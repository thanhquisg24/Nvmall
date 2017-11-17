$(function(){
	var title_="News Manager"
		$('title').text(title_);
	$("#tt").tabs({
		onSelect:function(title,index){
			//$("#idcurrent").val(title);
		},
		onClose:function(title,index){
			//$("#subtypeid_"+title).remove();
		}
	});
	
	$('#iddatagrip').datagrid({
		toolbar: '#tbar',
	    url: extras_Hosting["tomcatSpring_context"]+'ad/NewsController/get_json_append_to_datagrip.json',
	    idField:'id',
	    multiSort:true,
	    remoteSort:false,
	    columns:[[
	        {field:'ck',checkbox:true},
	        {field:'news_catgory_name',title:'News catgory name',width:150,halign:'center',sortable:true},
	        {field:'id',title:'News #',width:200,halign:'center',sortable:true},
	        {field:'title',title:'News title',width:250,align:'left',halign:'center',sortable:true},
	        {field:'create_date',title:'Date Create',align:'center',halign:'center',sortable:true},
	        {field:'modify_date',title:'Date modify',align:'center',halign:'center',sortable:true},
	        {field:'creator',title:'User create',align:'center',halign:'center',sortable:true},
	        {field:'modifyer',title:'User modify',align:'center',halign:'center',sortable:true},
	        {field:'isvisible',title:'Is visibled',align:'center',halign:'center',sortable:true,formatter:extras_formatstatus_datagrid}
	    ]],
	    pagination:true
	  
	});
	load_mm_selector(function(out){
		if(out==true){
			$('#ss').searchbox({
			    searcher:function(value,name){
			       // alert(value + "," + name);
			    	  $('#iddatagrip').datagrid('load',{
			    		  	col:name,
			    		  	val:value
			    	    });
			    },
			    menu:'#mm',
			    prompt:'Please Input Value'
			});

		}
	});
	function load_mm_selector(callback){
		$("#mm").html('');   	
		var pdata={'pcdtype':'NEWS','pcdname':'SEARCH'};
    	extras_GET_json(true,"ad/AllcodeController","get_allcode_searchbox.json",pdata,function(data){
    		if (data != null) {
	    		var s1 = '';
	            $.map(data, function(item) {
					s1 += '<div data-options="name:' + item.cdval + '">'
					+ item.cdcontent + '</div>';
				});
	        	$('#mm').html(s1);
	        	callback(true);
	       
    		}
    	});
	}
	
	$("#idadd").click(function(){
		var MCEeditor=tinymce.get('my_editor_news');
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
		 $('#tt').tabs('select',1);
	});
	
	$("#idedit").click(function(){
		var ids=get_single_row_select();
		if(ids==""){
			$.messager.alert('Warning','Select single row please!','warning');
			return;
		}
		extras_GET_json(true,"ad/NewsController","get_Newsbyid",{'news_id':ids},function(data){
			if(data !== null && typeof data === "object"){
				var MCEeditor=tinymce.get('my_editor_news');
				var text_aria_selector=MCEeditor.getElement();
				$(text_aria_selector).val(data.content);
				MCEeditor.setContent(data.content);
				$("#newstitle").data('ptype','E');
				$("#newstitle").data('newsid',data.id);
				$('#cc_catgorynews').combogrid('setValue',data.news_catgory); 
				$("#newstitle").textbox('setValue',data.title);
				$("#newsshort_description").textbox('setValue',data.short_description);
				$('#dialog_news_img').find('#idnewsimg').val(data.img);
				$('#dialog_news_img').find('img').attr('src',extras_Hosting["tomcat"]+'upload/news/'+data.img);
				$('#tt').tabs('select',1);
			}
		});
	});
	$("#idremove").click(function(){
		//extras_viahref("add_demo_crud");
		var ids=get_multi_row_select();
		if(ids==""){
			$.messager.alert('Warning','Select row please!','warning');
			return;
		}
		$.messager.confirm('Confirm','Are you sure you want to delete rows?',function(r){
		    if (r){
		        //alert('ok');
		        extras_POST_json(true,"ad/NewsController","delete_multi_news",{'str_id':ids},function(data){
		        	$.messager.alert('Result',data.message);
		        	if(data.f=="0"){
		        		$('#iddatagrip').datagrid('clearChecked');
		        		$('#iddatagrip').datagrid('reload');
		        	}
		        });
		        	
		        
		    }
		});
		
	});
	 $("#btnvisible").click(function() {
		 visibled_and_unvisibled(true);
	 });
	 $("#btnunvisible").click(function() {
		 visibled_and_unvisibled(false);
	 });
	
	function visibled_and_unvisibled(visibled){
		 var status_=0;
		 var alert_="";
		 if(visibled){
			 status_=1;
			 alert_="Are you sure you want to visibled this rows?"
			 
		 }else{
			 status_=0;
			 alert_="Are you sure you want to unvisibled this rows?"
		 }
		var ids=get_multi_row_select();
		if(ids==""){
				$.messager.alert('Warning','Select row please!','warning');
				return;
		}
		 $.messager.confirm('Confirm',alert_,function(r){
			    if (r){
			        extras_POST_json(true,"ad/NewsController","visivled_news",{'str_id':ids,'visible':status_},function(data){
			        	$.messager.alert('Result',data.message);
			        	if(data.f=="0"){
							$('#iddatagrip').datagrid('clearChecked');
							$('#iddatagrip').datagrid('reload');
			        	}
			        });

			    }
			});
	}

	function get_single_row_select(){
		 var checkedItems = $('#iddatagrip').datagrid('getChecked');
		 if(checkedItems.length>0){
			 return checkedItems[0].id;
		 }
		return "";
	}
	function get_multi_row_select(){
		 var checkedItems = $('#iddatagrip').datagrid('getChecked');
		 if(checkedItems.length>0){
			 var ids = [];
		        $.each(checkedItems, function(index, item){
		        	ids.push("'"+item.id+"'");
		        });                
		   return ids.join(",");
		 }
		 return ""; 
	}
	function close_collapse(current_index){
		var allrow=$('#iddatagrip').datagrid('getRows');
		for(var i=0;i<allrow.length;i++){
			var index = $('#iddatagrip').datagrid('getRowIndex',allrow[i]);
			if(index==current_index){
				$('#iddatagrip').datagrid('expandRow', current_index);
				$('#iddatagrip').datagrid('selectRow', current_index);
			}else{
				$('#iddatagrip').datagrid('collapseRow',index);
			}
		}
	}
});
