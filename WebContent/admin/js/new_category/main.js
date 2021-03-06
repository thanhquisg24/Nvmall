$(function(){
	var title_="Category New"
		$('title').text(title_);
	$('#p').panel({
	    title:title_   
	}); 
	$("#tt").tabs({
		onSelect:function(title,index){
			//$("#idcurrent").val(title);
		},
		onClose:function(title,index){
			//$("#subtypeid_"+title).remove();
		}
	});
	function create_panel(id,tabtitle){
		$.get(extras_Hosting["apache"] +"admin/views/Catgory/viewlevelprice_inner.html",function(data){
			if(data!=null){
				data=data.replace(/@id/g,id);
				$('#tt').tabs('add',{
						id:'tab_'+id,
			            title:tabtitle,
			            content: '<div style="padding:5px">'+data+'</div>',
			            closable: true, 
			    });
			}
		});
	}
	
	$('#iddatagrip').datagrid({
		toolbar: '#tbar',
	    url: extras_Hosting["tomcatSpring_context"]+'ad/NewsController/get_json_new_cate.json',
	    idField:'category_id',
	    remoteSort:false,
	    //singleSelect:true,
	    columns:[[
	        {field:'ck',checkbox:true},
	        {field:'category_id',title:'ID#',width:250,halign:'center',sortable:true},
	        {field:'category_name',title:'Name',width:200,halign:'center',sortable:true},
	        {field:'category_img',title:'Image',width:250,halign:'center',align:'center',sortable:true,formatter:extras_formatImg_datagrid},
	        {field:'isvisible',title:'Is Visibled',width:150,halign:'center',align:'right',sortable:true,formatter:extras_formatstatus_datagrid}
	    ]],
	    pagination:true
	});
	load_mm_selector(function(out){
		if(out==true){
			$('#ss').searchbox({
			    searcher:function(value,name){
			       // alert(value + "," + name);
			    	$('#iddatagrip').datagrid('clearChecked');
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
		var pdata={'pcdtype':'CATNEW','pcdname':'SEARCH'};
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
	
	

	$('#ff').form({
	    url:extras_Hosting["tomcatSpring_context"]+'ad/NewsController/add_update_catgory_new',
	    onSubmit:function(){
	        return $(this).form('validate');
	    },
	    success:function(result){
	    	//alert(result);
	    	//var data = eval('(' + data + ')');
	    	var data=JSON.parse(result);
	        $.messager.alert('Info', data.message, 'info');
	        if(data.f=="0"){
	        	$("#dlgcreate").dialog('close');
	        	    clear_input();
	        		$('#iddatagrip').datagrid('clearChecked');
	    	    	$('#iddatagrip').datagrid('reload');
	        }
	        
	    }
	});
	 function clear_input(){
		 	$("#idproduct_type_vmall").textbox({readonly:false,value:''});
		 	$('#ff').form('reset');
		 	$('#upload_immg').form('reset');
	    	$("#strongmessage").text("");
	    	
	    }
	
	$("#idadd").click(function(){
			clear_input();
		   var type = 'A';
	        $("#dlgcreate").css('display', 'block');
	        $('#dlgcreate').dialog({
	            autoOpen: false,
	            title: 'New Category',
	            width: 350,
	            height: 300,
	            buttons: [{
	                text: 'OK',
	                iconCls: 'icon-ok',
	                handler: function() {
	                	$("#idptype").val(type);
	                	$('#ff').submit();
	                }
	            }, {
	                text: 'Cancel',
	                iconCls: 'icon-cancel',
	                handler: function() {
	                    $("#dlgcreate").dialog('close');
	                }
	            }]
	        });
	        $(dlgcreate).dialog('open');
	});
	$("#idedit").click(function(){
		//alert(get_single_row_select(0));
		var id=get_single_row_select();
		if(id==""){
			$.messager.alert('Warning','Select row please!','warning');
			return;
		}
		extras_GET_json(true,"ad/NewsController","get_newcat_by_id",{'id':id},function(data){
    		if (data != null) {
    				$("#id_uploadsuccess").val(data.category_img);
    				$("#id_new_cate").textbox('setValue',data.category_id);
    				$("#name_new_cate").textbox('setValue',data.category_name);
    			    $("#strongmessage").html("<font color='green'>" + data.category_img + " </font>");	
    		}
    		var type = 'E';
            $("#dlgcreate").css('display', 'block');
            $('#dlgcreate').dialog({
                autoOpen: false,
                title: 'Edit New Product',
                width: 350,
                height: 300,
                buttons: [{
                    text: 'OK',
                    iconCls: 'icon-ok',
                    handler: function() {
                    	$("#idptype").val(type);
                    	$('#ff').submit();
                    }
                }, {
                    text: 'Cancel',
                    iconCls: 'icon-cancel',
                    handler: function() {
                        $("#dlgcreate").dialog('close');
                    }
                }]
            });
            $(dlgcreate).dialog('open');
    	});
	});
	$("#idremove").click(function(){
		var ids=get_multi_row_select();
		if(ids==""){
			$.messager.alert('Warning','Select row please!');
			return;
		}
		$.messager.confirm('Confirm','Are you sure you want to delete ?',function(r){
		    if (r){
		        extras_POST_json(true,"ad/NewsController","remove_new_catgory",{'str_id':ids},function(data){
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
		 visibled_catgory(true);
	 });
	 $("#btnunvisible").click(function() {
		 visibled_catgory(false);
	 });
	 
	 function visibled_catgory(isvible){
		 var status_visible=0;
		 var alert_="";
		 if(isvible){
			 status_visible=1;
			 alert_="Are you sure you want to visibled record?"
			 
		 }else{
			 status_visible=0;
			 alert_="Are you sure you want to unvisibled record?"
		 }
		var ids=get_multi_row_select();
		if(ids==""){
				$.messager.alert('Warning','Select row please!');
				return;
		}
		 
		 $.messager.confirm('Confirm',alert_,function(r){
			    if (r){
			        extras_POST_json(true,"ad/NewsController","visibled_new_catgory",{'str_id':ids,'visible':status_visible},function(data){
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
			 return checkedItems[0].category_id;
		 }
		return "";
	}
	function get_multi_row_select(){
		 var checkedItems = $('#iddatagrip').datagrid('getChecked');
		 if(checkedItems.length>0){
			 var ids = [];
		        $.each(checkedItems, function(index, item){
		        	ids.push("'"+item.category_id+"'");
		        });                
		   return ids.join(",");
		 }
		 return ""; 
	}
	
	
	
	$('input[name="txtimage"]').ajaxfileupload({
	    'action':  extras_Hosting["tomcatSpring_context"]+"ad/UploadController/upload_image_normal_withparam?folder=save_image_cate",
	    'onComplete': function(response) {	
	        if (response.status==false) {
	        	$.messager.alert('Error',response.message,'error');
	        	 $("#strongmessage").html("<font color='red'>" + JSON.stringify(response.message) + " </font>");
	        }
	        if (response.status==true) {
	        	//$.messager.alert('Info',response.message,'info');
	            var pram = response.pram;
	            $("#id_uploadsuccess").val(pram);
	            //$("#branchimg_@index").attr('src','upload/branch/'+pram);
	            $("#strongmessage").html("<font color='green'>" + JSON.stringify(response.message) + " </font>");
	            
	        }

	    },
	    'onStart': function() {
	    	var fileSize = this.get(0).files[0].size;
	     	if(fileSize>1024*1000){
	     		$.messager.alert('Warning','File này có kích cỡ lớn hơn cho phép. Xin chọn hình ảnh khác!!','warning');
	     		$("#id_uploadsuccess").val('');
	     		return false;
	     	}
	    }
	});
	
});
