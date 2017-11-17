$(function(){
	var imgurl_1=extras_Hosting["tomcat"] +'upload/slide';
	var title_="Slide Manager"
		$('title').text(title_);
	$('#p').panel({
	    title:title_   
	}); 
	$('#iddatagrip').datagrid({
		toolbar: '#tbar',
	    url: extras_Hosting["tomcatSpring_context"]+'ad/SlideController/get_json_append_to_datagrip.json',
	    //width:600,
	   // height:300,
	   // fitColumns:true,
	    idField:'product_type_vmall',
	    remoteSort:false,
	    //singleSelect:true,
	    columns:[[
	        {field:'ck',checkbox:true},
	        {field:'id',title:'ID#',width:100,halign:'center',sortable:true},
	        {field:'name',title:'Image',width:300,halign:'center',sortable:true,formatter:extras_formatImg_datagrid_slide},
	        {field:'create_date',title:'Create Date',width:250,halign:'center',align:'center'},
	        {field:'creator',title:'Creator',width:100,halign:'center',align:'right',sortable:true},
	        {field:'isvisible',title:'Is Visibled',width:100,halign:'center',align:'center',formatter:extras_formatstatus_datagrid},
	        {field:'modifyer',title:'Modifyer',width:100,halign:'center',align:'right',sortable:true},
	        {field:'modify_date',title:'Modify Date',width:100,halign:'center',align:'center'}
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
		var pdata={'pcdtype':'SLIDE','pcdname':'SEARCH'};
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
	    url:extras_Hosting["tomcatSpring_context"]+'ad/SlideController/add_update_slide',
	    onSubmit:function(){
	    	
	        return $(this).form('validate');
	    },
	    success:function(result){
	    	var data=JSON.parse(result);
	        $.messager.alert('Info', data.message, 'info');
	        if(data.f=="0"){
	        	$("#dlgcreate_slide").dialog('close');
	        	  clear_input();
	        		$('#iddatagrip').datagrid('clearChecked');
	    	    	$('#iddatagrip').datagrid('reload');
	        }
	        
	    }
	});
	 function clear_input(){
		 	//$("#idproduct_type_vmall").textbox({readonly:false,value:''});
		 	$('#ff').form('reset');
		 	$('#upload_immg').form('reset');
	    	$("#strongmessage").text("");
	    	
	    }
	function check_img_up(){
		var img=$('#id_uploadsuccess').val();
		if(img==""||img==null){
			   $.messager.alert('Info', 'Please upload your image!!', 'info');
			return false;
		}
		return true;
	}
	$("#idadd").click(function(){
			clear_input();
		   var type = 'A';
	        $("#dlgcreate_slide").css('display', 'block');
	        $('#dlgcreate_slide').dialog({
	            autoOpen: false,
	            title: 'New Slide ',
	            width: 470,
	            height:450,
	            buttons: [{
	                text: 'OK',
	                iconCls: 'icon-ok',
	                handler: function() {
	                	if(check_img_up()){
	                		$("#idptype").val(type);
	                		$('#ff').submit();
	                	}
	                }
	            }, {
	                text: 'Cancel',
	                iconCls: 'icon-cancel',
	                handler: function() {
	                	//alert(1);
	                    $("#dlgcreate_slide").dialog('close');
	                }
	            }]
	        });
	        $(dlgcreate_slide).dialog('open');
	});
	$("#idedit").click(function(){
		//alert(get_single_row_select(0));
		var id=get_single_row_select();
		if(id==""){
			$.messager.alert('Warning','Select row please!');
			return;
		}
		extras_GET_json(true,"ad/SlideController","get_slide_by_id",{'id':id},function(data){
    		if (data != null) {
    				$("#id_uploadsuccess").val(data.name);
    				//$("#idproduct_type_vmall").textbox('setValue',data.product_type_vmall);
    				
    				$("#id_slide").val(data.id);
    			    $("#strongmessage").html("<font color='green'>" + data.name + " </font>");	
    			    $("#ff img").attr('src',imgurl_1+'/'+data.name);
    			    
    		}
    		var type = 'E';
            $("#dlgcreate_slide").css('display', 'block');
            $('#dlgcreate_slide').dialog({
                autoOpen: false,
                title: 'Edit Slide',
                width: 470,
	            height:450,
                buttons: [{
                    text: 'OK',
                    iconCls: 'icon-ok',
                    handler: function() {
                    	if(check_img_up()){
	                		$("#idptype").val(type);
	                		$('#ff').submit();
	                	}
                    }
                }, {
                    text: 'Cancel',
                    iconCls: 'icon-cancel',
                    handler: function() {
                        $("#dlgcreate_slide").dialog('close');
                    }
                }]
            });
            $('#dlgcreate_slide').dialog('open');
    	});
	});
	$("#idremove").click(function(){
		var ids=get_multi_row_select();
		if(ids==""){
			$.messager.alert('Warning','Select row please!');
			return;
		}
		$.messager.confirm('Confirm','Are you sure you want to delete record?',function(r){
		    if (r){
		        extras_POST_json(true,"ad/SlideController","detele_slide",{'str_id':ids},function(data){
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
			        extras_POST_json(true,"ad/SlideController","visivled_slide",{'str_id':ids,'visible':status_visible},function(data){
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
	
	
	
	var url = extras_Hosting["tomcatSpring_context"]+"ad/UploadController/upload_image_normal_withparam?folder=saveslideimage";
    $('input[name="txtimage"]').ajaxfileupload({
        'action': url,
        
        'onComplete': function(response) {	
	        if (response.status==false) {
	        	$.messager.alert('Error',response.message,'error');
	        }
	        if (response.status==true) {
	        	//$.messager.alert('Info',response.message,'info');
	            var pram = response.pram;
	            $('#id_uploadsuccess').val(pram);
	            $("#ff img").attr('src',imgurl_1+'/'+pram);
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
