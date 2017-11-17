$(function(){
	var tabindex=0;
	var title_="Catgory Sub"
		$('title').text(title_);
	$("#tt").tabs({
		onSelect:function(title,index){
			$("#idcurrent").val(title);
			//alert(index);
		},
		onClose:function(title,index){
			//$("#subtypeid_"+index).remove();
			/*var nameinput="subtypename_"+index;
			alert(nameinput);
			$("input[name='"+nameinput+"']").remove();*/
		}
	});
	
	function load_combogrid_catgoryvmall(callback){
		extras_GET_json(true,"ad/product_type_vmallController","get_allcatgory","",function(data){
			if(data.length>0){
				$("#cc,#cc2").val(data[0].product_type_vmall);
				$('#cc,#cc2').combogrid({
			        panelWidth: 400,				      
			        idField: 'product_type_vmall',
			        textField: 'product_type_name',
			        columns: [[
			                 {field:'product_type_vmall',title:'ID#',width:100,halign:'center',sortable:true},
			       	        {field:'product_type_name',title:'Name',width:150,halign:'center',sortable:true},
			       	        {field:'isvisible',title:'Is Visibled',width:100,halign:'center',align:'right',sortable:true,formatter:extras_formatstatus_datagrid}
			         ]]
			    });
				$('#cc').combogrid({ 
					onSelect:function(index,row){
		        	 //$('#ss').searchbox('setValue', '');
		        	 $('#iddatagrip').datagrid('clearChecked');
		        	 load_datagrid_sub(row.product_type_vmall);
				 }
				});
				$("#cc").combogrid("grid").datagrid("loadData",data);
				$("#cc2").combogrid("grid").datagrid("loadData",data);
				callback(true);
			}else{
				callback(false);
			}
		});
	}
	
	load_combogrid_catgoryvmall(function(fl){
		if(fl){
			var parent_id=$("#cc").combogrid('getValue');
			load_datagrid_sub(parent_id);
		}
	});
	
	$("#islink").change(function(){
		if(this.checked){
			this.value="1";
		}else{
			this.value="0";
		}
	});
	
	function load_datagrid_sub(parrentid){
		$('#iddatagrip').datagrid({
			toolbar: '#tbar',
		    url: extras_Hosting["tomcatSpring_context"]+'ad/product_type_subController/get_json_append_to_datagrip.json?parentid='+parrentid,
		    //width:600,
		   // height:300,
		   // fitColumns:true,
		    idField:'id',
		    remoteSort:false,
		    //singleSelect:true,
		    columns:[[
		        {field:'ck',checkbox:true},
		        {field:'id',title:'ID#',width:200,halign:'left',sortable:true},
		        {field:'product_type_id',title:'Type sub id',width:100,halign:'center',sortable:true},
		        {field:'product_type_name',title:'Name',width:250,halign:'center',sortable:true},
		        {field:'category_img',title:'Image',width:250,halign:'center',align:'center',sortable:true,formatter:extras_formatImg_datagrid},
		        {field:'isvisible',title:'Is Visibled',width:100,halign:'center',align:'right',sortable:true,formatter:extras_formatstatus_datagrid},
		        {field:'islink',title:'Is Link',width:100,halign:'center',align:'right',sortable:true,formatter:extras_formatstatus_datagrid}
		    ]],
		    pagination:true
		});
	}
	
	
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
		var pdata={'pcdtype':'CATGORYSUB','pcdname':'SEARCH'};
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
	    url:extras_Hosting["tomcatSpring_context"]+'ad/product_type_subController/add_update_catgory',
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
		 	$("#idproduct_type_id").textbox({readonly:false,value:''});
		 	$('#ff').form('reset');
		 	$('#upload_immg').form('reset');
	    	$("#strongmessage").text("");
	    	
	    }
	$("#btneditsub").click(function(){
		var id=get_single_row_select();
		if(id==""){
			$.messager.alert('Warning','Select row please!','warning');
			return;
		}
	  var tabtitle =  $('#iddatagrip').datagrid('getSelected').product_type_name;
	  var stab=$('#tt').tabs('getTab',tabtitle);
	  if(stab==null){
		  // tabindex++;
		  //extras_append_input_hidden(id,tabindex);
			create_panel(id,tabtitle);
	  }else{
		  $('#tt').tabs('select',tabtitle);
	  }
	  $('#iddatagrip').datagrid('clearChecked');
	});
	function create_panel(id,tabtitle){
		$.get(extras_Hosting["apache"] +"admin/views/Catgory/producttypesub_edit.html",function(data){
			if(data!=null){
				var vmallid=$("#cc").combogrid('getValue');
				data=data.replace(/@id/g,id);
				data=data.replace(/@vmallid/g,vmallid);
				$('#tt').tabs('add',{
						id:'tab_'+id,
			            title:tabtitle,
			            content: '<div style="padding:5px">'+data+'</div>',
			            closable: true, 
			    });
				$(".barcontrol").css('display','block');
				$(".barsearch").css('display','block');
				$("#ff_"+id).css('display','block');
				$("#upload_immg_"+id).css('display','block');
			}
		});
	};
	
	$("#idadd").click(function(){
			clear_input();
			//var parent_id=$('#cc').combogrid('getValue');
			//var parent_name=$('#cc').combogrid('grid').datagrid('getSelected').product_type_name;
		   var type = 'A';
	        $("#dlgcreate").css('display', 'block');
	        $('#dlgcreate').dialog({
	            autoOpen: false,
	            title: 'New  Category Sub :',
	            width: 480,
	            height: 350,
	            buttons: [{
	                text: 'OK',
	                iconCls: 'icon-ok',
	                handler: function() {
	                	//$("#id_parentid").val(parent_id);
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
			$.messager.alert('Warning','Select row please!');
			return;
		}
		extras_GET_json(true,"ad/product_type_subController","get_catgory_by_id",{'id':id},function(data){
    		if (data != null) {
    				$("#id_uploadsuccess").val(data.category_img);
    				$("#islink").value= data.islink;
    				if(data.islink==1){
    					$("#islink").prop('checked',true);
    				}else{
    					$("#islink").prop('checked',false);
    				}
    				$('#cc2').combogrid('setValue',data.product_type_vmall);
    				$("#idproduct_type_id").textbox({readonly:true,value:data.product_type_id});
    				$("#idproduct_type_name").textbox('setValue',data.product_type_name);
    			    $("#strongmessage").html("<font color='green'>" + data.category_img + " </font>");	
    		}
    		var type = 'E';
            $("#dlgcreate").css('display', 'block');
            $('#dlgcreate').dialog({
                autoOpen: false,
                title: 'Edit  Category Sub:',
                width: 480,
                height: 350,
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
		$.messager.confirm('Confirm','Are you sure you want to delete record?',function(r){
		    if (r){
		        extras_POST_json(true,"ad/product_type_subController","detele_catgory",{'str_id':ids},function(data){
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
			        extras_POST_json(true,"ad/product_type_subController","visivled_catgory",{'str_id':ids,'visible':status_visible},function(data){
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
			 return checkedItems[0].product_type_id;
		 }
		return "";
	}
	function get_multi_row_select(){
		 var checkedItems = $('#iddatagrip').datagrid('getChecked');
		 if(checkedItems.length>0){
			 var ids = [];
		        $.each(checkedItems, function(index, item){
		        	ids.push("'"+item.product_type_id+"'");
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
