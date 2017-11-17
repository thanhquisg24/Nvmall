$(function(){
	var title_="Catgory Customer"
		$('title').text(title_);
	$('#p').panel({
	    title:title_   
	}); 
	$('#cc').combogrid({
        panelWidth: 400,				      
        idField: 'product_type_vmall',
        textField: 'product_type_name',
        columns: [[
                {field:'product_type_vmall',title:'ID#',width:100,halign:'center',sortable:true},
       	        {field:'product_type_name',title:'Name',width:150,halign:'center',sortable:true},
       	        {field:'isvisible',title:'Is Visibled',width:100,halign:'center',align:'right',sortable:true,formatter:extras_formatstatus_datagrid}
         ]],
         onSelect:function(index,row){
        	 //$('#ss').searchbox('setValue', '');
        	
        	var vmallid = $("#cc").combogrid('getValue');
        	load_combogrid_catgory_parent(function(s){},vmallid);
         }
    });
	$('#cc2').combogrid({
        panelWidth: 350,				      
        idField: 'product_type_id',
        textField: 'product_type_name',
        columns: [[
				{field:'product_type_id',title:'ID#',width:100,halign:'center',sortable:true},
				{field:'product_type_name',title:'Name',width:150,halign:'center',sortable:true},
				{field:'isvisible',title:'Is Visibled',width:100,halign:'center',align:'right',sortable:true,formatter:extras_formatstatus_datagrid}
         ]],
         onSelect:function(index,row){
        	 //$('#ss').searchbox('setValue', '');
        	
        	var parentid = $("#cc2").combogrid('getValue');
        	load_combogrid_catgorysub(function(s){},parentid);
         }
    });
	$('#cc5').combogrid({
        panelWidth: 350,				      
        idField: 'product_type_id',
        textField: 'product_type_name',
        columns: [[
				{field:'product_type_id',title:'ID#',width:100,halign:'center',sortable:true},
				{field:'product_type_name',title:'Name',width:150,halign:'center',sortable:true},
				{field:'isvisible',title:'Is Visibled',width:100,halign:'center',align:'right',sortable:true,formatter:extras_formatstatus_datagrid}
         ]]
    });
	$('#cc3').combogrid({
        panelWidth: 500,				      
        idField: 'id',
        textField: 'shop_name',
        columns: [[
                {field:'id',title:'ID#',width:100,halign:'center',sortable:true},
                {field:'email',title:'Email',width:150,halign:'center',sortable:true},
       	        {field:'shop_name',title:'Name',width:150,halign:'center',sortable:true},
       	        {field:'project_name',title:'Project name',width:100,halign:'center',align:'right',sortable:true},    
         ]],	
         	onSelect:function(index,row){
	        	
			 }
    });
	
	/*$('#cc4').combogrid({
        panelWidth: 350,				      
        idField: 'product_type_id',
        textField: 'product_type_name',
        columns: [[
				{field:'product_type_id',title:'ID#',width:100,halign:'center',sortable:true},
				{field:'product_type_name',title:'Name',width:150,halign:'center',sortable:true},
				{field:'isvisible',title:'Is Visibled',width:100,halign:'center',align:'right',sortable:true,formatter:extras_formatstatus_datagrid}
         ]]
    });*/
	function load_combogrid_catgoryvmall(callback,isADD){
		extras_GET_json(true,"ad/product_type_vmallController","get_allcatgory","",function(data){
			if(data.length>0){
				$("#cc").combogrid("grid").datagrid("loadData",data);
				if(isADD){
					$("#cc").combogrid('setValue',data[0].product_type_vmall);
				}
				callback(true);
			}else{
				
				callback(false);
			}
		});
	};
	function load_combogrid_catgory_parent(callback,vmallid){
		var pdata = {'vmallid':vmallid}
		extras_GET_json(true,"ad/product_type_subController","get_subcatgory_byvmall",pdata,function(data){
			if(data.length>0){
				$("#cc2").combogrid("grid").datagrid("loadData",data);
				callback(true);
			}else{
				$("#cc2").combogrid('clear');
				$("#cc2").combogrid("grid").datagrid("loadData",[]);
				callback(false);
			}
		});
	};
	function load_combogrid_catgorysub(callback,parrentid){
		var pdata = {'parentid':parrentid}
		extras_GET_json(true,"ad/product_type_subController","get_subcatgory_byparent",pdata,function(data){
			if(data.length>0){
				$("#cc5").combogrid("grid").datagrid("loadData",data);
				callback(true);
			}else{
				$("#cc5").combogrid('clear');
				$("#cc5").combogrid("grid").datagrid("loadData",[]);
				callback(false);
			}
		});
	};
	function load_combogrid_customer(callback,isADD){
		extras_GET_json(true,"ad/CustomerController","get_allcustomer","",function(data){
			if(data.length>0){
				$("#cc3").combogrid("grid").datagrid("loadData",data);
				if(isADD){
					$("#cc3").combogrid('setValue',data[0].id);
					$("#cc3").combogrid('readonly',false);
					//$("#cc4").combogrid('readonly',false);
				}
				callback(true);
			}else{
				$('#cc4').html('');
				callback(false);
			}
		});
	};
	function load_combogrid_cat_cus(callback,customerid){
		var pdata = {'customerid':customerid}
		extras_GET_json(true,"ad/ad_product_type_Controller","get_catgory_bycus",pdata,function(data){
			if(data.length>0){
				$("#cc4").combogrid("grid").datagrid("loadData",data);
				callback(true);
			}else{
				$("#cc4").combogrid('clear');
				$("#cc4").combogrid("grid").datagrid("loadData",[]);
				callback(false);
			}
		});
	};
	
	$('#iddatagrip').datagrid({
		toolbar: '#tbar',
	    url: extras_Hosting["tomcatSpring_context"]+'ad/product_type_customerController/get_json_append_to_datagrip.json',
	    //width:600,
	   // height:300,
	   // fitColumns:true,
	    idField:'customer_id',
	    remoteSort:false,
	    //singleSelect:true,
	    columns:[[
	        {field:'ck',checkbox:true},
	        {field:'customer_id',title:'Customer ID',width:110,halign:'center',sortable:true},
	        {field:'customer_email',title:'Name',width:100,halign:'center',sortable:true},
	        {field:'product_type_id',title:'Type ID of customer',width:100,halign:'center',sortable:true},
	        {field:'product_type_name',title:'Type name of customer',width:180,halign:'center',sortable:true},
	        {field:'group_category_name',title:'Group category',width:180,halign:'center',sortable:true},
	        {field:'category_name',title:'Category',width:180,halign:'center',sortable:true},
	        {field:'isvisible',title:'Is Visibled',width:80,halign:'center',align:'right',sortable:true,formatter:extras_formatstatus_datagrid},
	        {field:'isapprove',title:'Is Approve',width:80,halign:'center',align:'right',sortable:true,formatter:extras_formatstatus_datagrid}
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
		var pdata={'pcdtype':'CATGORYCUS','pcdname':'SEARCH'};
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
		clear_input();
		$("#cc,#cc2,#cc3,#cc5").combogrid('clear');
		load_combogrid_catgoryvmall(function(out){
			load_combogrid_catgory_parent(function(out){
				
				
				},$("#cc").combogrid('getValue'));
			},true);
		
		load_combogrid_customer(function(out){
			
			},true);
		
	   var type = 'A';
        $("#dlgcreate").css('display', 'block');
        $('#dlgcreate').dialog({
            autoOpen: false,
            title: 'New Customer Category',
            width: 675,
            height: 480,
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
	
	
	
	
	$('#ff').form({
	    url:extras_Hosting["tomcatSpring_context"]+'ad/product_type_customerController/add_update_catgory',
	    onSubmit:function(){
	    	return custom_validate_form();
	        //return $(this).form('validate');
	    },
	    success:function(result){
	    	//alert(result);
	    	//var data = eval('(' + data + ')');
	    	var data=JSON.parse(result);
	        $.messager.alert('Info', data.message, 'info');
	        if(data.f=="0"){
	        	$("#dlgcreate").dialog('close');
	        	  	//clear_input();
	        		$('#iddatagrip').datagrid('clearChecked');
	    	    	$('#iddatagrip').datagrid('reload');
	        }
	        
	    }
	});
	
	function custom_validate_form(){
		var cc = $("#cc").combogrid('getValue');
		var cc2= $("#cc2").combogrid('getValue');
		var cc3=$("#cc3").combogrid('getValue');
		var cc4=$("#cc4").val();
		var cc5=$("#cc5").combogrid('getValue');
		var cc6=$("#cc6").val();
		if(cc==null||cc==""){
			$.messager.alert('Warning','Please Select Product type vmall!!! ');
			return false;
		}
		if(cc2==null||cc2==""){
			$.messager.alert('Warning','Please Select Group category!!! ');
			return false;
		}
		if(cc3==null||cc3==""){
			$.messager.alert('Warning','Please Select Customer!!! ');
			return false;
		}
		if(cc4==null||cc4==""){
			$.messager.alert('Warning','Please input category ID !!! ');
			return false;
		}
		if(cc5==null||cc5==""){
			$.messager.alert('Warning','Please Select Category!!! ');
			return false;
		}
		if(cc6==null||cc6==""){
			$.messager.alert('Warning','Please input category name !!! ');
			return false;
		}
		return true;
	}

	
	 function clear_input(){
		 	$("#idproduct_type_vmall").textbox({readonly:false,value:''});
		 	$('#ff').form('reset');
		 	$('#upload_immg').form('reset');
	    	$("#strongmessage").text("");
	    	
	    }
	
	
	$("#idedit").click(function(){
		var id=get_selected_custype();
		if(id==""){
			$.messager.alert('Warning','Select row please!');
			return;
		}
		extras_GET_json(true,"ad/product_type_customerController","get_catgory_by_id",{'id':id},function(data){
    		if (data!=null && data!="") {
    			$("#cc,#cc2,#cc3,#cc5").combogrid('clear');
    			$("#cc4,#cc6").val('');
    			$("#id_ID").val(id);
    			load_combogrid_catgoryvmall(function(out){
    				if(out){
    					$("#cc").combogrid('setValue',data.product_id_vmall);
    					
    					load_combogrid_catgory_parent(function(out){
    						if(out){
    							$("#cc2").combogrid('setValue',data.group_category_id);
    							load_combogrid_catgorysub(function(out){
    								if(out){
    									$("#cc5").combogrid('setValue',data.category_id);
    								}
    							}, $("#cc2").combogrid('getValue'))
    						}
    					},$("#cc").combogrid('getValue'));
    				}
    			},false);
    			load_combogrid_customer(function(out){
    				if(out){
    					$("#cc3").combogrid('setValue',data.customer_id);
    					$("#cc3").combogrid('readonly',true);
    					$("#cc4").val(data.product_type_id);
    					$("#cc4").attr('readonly',true);
    					$("#cc6").val(data.product_type_name);
    					$("#cc6").attr('readonly',true);
    				}
    			},false);
    			$("#id_uploadsuccess").val(data.category_img);
    			$("#strongmessage").html("<font color='green'>" + data.category_img + " </font>");	
    			
    			var type = 'E';
                $("#dlgcreate").css('display', 'block');
                $('#dlgcreate').dialog({
                    autoOpen: false,
                    title: 'Edit  Category Customer',
                    width: 675,
                    height: 480,
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
    		}//end if data respone null
    		else{
    			$.messager.alert('Error',"Can't handle reponse!");
    			return;
    		}
    	});//end ajax call
	});//end edit click
	$("#idremove").click(function(){
		var ids=get_selected_custype();
		if(ids==""){
			$.messager.alert('Warning','Select row please!');
			return;
		}
		$.messager.confirm('Confirm','Are you sure you want to delete record?',function(r){
		    if (r){
		        extras_POST_json(true,"ad/product_type_customerController","detele_catgory",{'str_id':ids},function(data){
		        	$.messager.alert('Result',data.message);
		        	if(data.f=="0"){
		        		$('#iddatagrip').datagrid('clearChecked');
		        		$('#iddatagrip').datagrid('reload');
		        	}
		        });
		        	
		        
		    }
		});
		
	});
	
	$("#btnapprove").click(function(){
		var ids=get_selected_custype();
		if(ids==""){
			$.messager.alert('Warning','Select row please!');
			return;
		}
		
		$.messager.confirm('Confirm','Do you want to approve this category?',function(r){
			if(r){
				extras_POST_json(true,"ad/product_type_customerController","approve_catgory",{'str_id':ids},function(data){
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
		var ids=get_selected_custype();
		if(ids==""){
				$.messager.alert('Warning','Select row please!');
				return;
		}
		 
		 $.messager.confirm('Confirm',alert_,function(r){
			    if (r){
			        extras_POST_json(true,"ad/product_type_customerController","visivled_catgory",{'str_id':ids,'visible':status_visible},function(data){
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
	function get_selected_custype(){
		 var checkedItems = $('#iddatagrip').datagrid('getChecked');
		 if(checkedItems.length>0){
			 return checkedItems[0].customer_id+"_"+checkedItems[0].product_type_id;
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
