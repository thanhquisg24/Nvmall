$(function(){
	var custom_value="";
	function clear_iddatagrip_dialog_s(){
		$("#iddatagrip_dialog_s").datagrid("clearChecked");
		$("#iddatagrip_dialog_s").datagrid("loadData",[]);
	}
	function set_value_toselector(selector,svalue){
		$(selector).textbox('setValue',svalue);
	}
	
	 $('#id_btn1_choose_1').bind('click', function(){
		  $("#dialog_s").data('selector','#idchooseproductype');
		  $("#dialog_s").dialog('open').dialog('center');
	    });
	$('#idchooseproductype').textbox({
		prompt:"Click button open list ...",
		readonly:true,
		 onChange: function(newValue,oldValue){
			 	console.log('The old value ' + oldValue);
			    console.log('The value has been changed to ' + newValue);
			    if(newValue==null||newValue==""){
			    	  $('#dg').datagrid('clearChecked');
					  $('#dg').datagrid("loadData",[]);
			    }else{
			    	  var url_context_grid_recommended=extras_Hosting["tomcatSpring_context"]+'ad/proRecommendedController/get_datagrip_byproducttypeid.json?product_type_id='+newValue;
					  $('#dg').datagrid('clearChecked');
					  $('#dg').datagrid({url:url_context_grid_recommended});
					  load_combogrid_customer(newValue);
			    }
		}
	});
	
	function load_combogrid_customer(type_id){
		var pdata={'type_id': type_id};
		extras_GET_json(true,"ad/proRecommendedController","get_customer_of_type",pdata,function(data){
			if(data.length>0 && data!=null && data!=""){
				$("#customer_cc").combogrid("readonly",false);
				$("#customer_cc").combogrid("grid").datagrid("loadData",data);
				$("#customer_cc").combogrid('setValue',data[0].id);
			}else{
				$("#customer_cc").combogrid('clear');
				$("#customer_cc").combogrid("setValue","Have no customer");
				$("#customer_cc").combogrid("readonly",true);
			}
		});
	}
	$('#dialog_s').dialog({
	    title: 'Choose product type',
	    width: 850,
	    height: 450,
	    closed: true,
	    cache: false,
	    modal: true,
	    buttons: [{
            text: 'Ok',
            iconCls: 'icon-ok',
            handler: function() {
            var data_selector=	$("#dialog_s").data('selector');
            var ids=get_single_row_select();
	            if(ids==""){
	            	  $.messager.alert('Warning','Please select row!!','warning');
	            	  return
	            }
	            $('#idchooseproductype').textbox('setValue',ids);
	            $("#dialog_s").dialog('close');
	          
            }
        }, {
            text: 'Cancel',
            iconCls: 'icon-cancel',
            handler: function() {
                $("#dialog_s").dialog('close');
            }
        }]
    });
	
	$('#iddatagrip_dialog_s').datagrid({
		toolbar: '#tbar_dialog_s',
	    //url: extras_Hosting["tomcatSpring_context"]+'ad/democrudController/get_json_append_to_datagrip.json',
	    height:350,
	    singleSelect:true,
	    idField:'product_type_id',
	    remoteSort:false,
	    columns:[[
			        {field:'ck',checkbox:true},
			        {field:'product_type_id',title:'ID#',width:100,halign:'center',sortable:true},
					{field:'product_type_name',title:'Name',width:150,halign:'center',sortable:true},
					{field:'isvisible',title:'Is Visibled',width:100,halign:'center',align:'right',sortable:true,formatter:extras_formatstatus_datagrid}
			    ]]
	});
	
	$('#productype_vmall_dialog_s').combogrid({
        panelWidth: 400,				      
        idField: 'product_type_vmall',
        textField: 'product_type_name',
        editable:false,
        columns: [[
                {field:'product_type_vmall',title:'ID#',width:100,halign:'center',sortable:true},
       	        {field:'product_type_name',title:'Name',width:150,halign:'center',sortable:true},
       	        {field:'isvisible',title:'Is Visibled',width:100,halign:'center',align:'right',sortable:true,formatter:extras_formatstatus_datagrid}
         ]],
         onChange: function(newValue,oldValue){
        	var vmall = $("#productype_vmall_dialog_s").combogrid('getValue');
		       	 if(vmall!=""&&vmall!=null){
		       		//clear_iddatagrip_dialog_s();
		        	load_combogrid_catgory_parent(function(s){},vmall);
		       	 }
		       	 else{
		       		$("#productype_parent_dialog_s").combogrid('clear');
					$("#productype_parent_dialog_s").combogrid("grid").datagrid("loadData",[]);
		       	 }
         }
    });
	$('#productype_parent_dialog_s').combogrid({
        panelWidth: 400,				      
        idField: 'product_type_id',
        textField: 'product_type_name',
        editable:false,
        columns: [[
				{field:'product_type_id',title:'ID#',width:100,halign:'center',sortable:true},
				{field:'product_type_name',title:'Name',width:150,halign:'center',sortable:true},
				{field:'isvisible',title:'Is Visibled',width:100,halign:'center',align:'right',sortable:true,formatter:extras_formatstatus_datagrid}
         ]],
         onChange: function(newValue,oldValue){
        	 var parentid= $("#productype_parent_dialog_s").combogrid('getValue');
        	 	clear_iddatagrip_dialog_s();
        	 	if(parentid!=""&&parentid!=null){
        	 		 var url_=extras_Hosting["tomcatSpring_context"]+'ad/product_type_subController/get_subcatgory_byparent?parentid='+parentid;
					  $('#iddatagrip_dialog_s').datagrid({method:'get',url:url_});
		       	 }
          }
    });
	
	
	load_combogrid_catgoryvmall(function(s){
		if(s){
	     	var vmall = $("#productype_vmall_dialog_s").combogrid('getValue');
	     	load_combogrid_catgory_parent(function(ss){
	     		if(ss){
	     			//var parentid = $("#productype_parent_dialog_s").combogrid('getValue');
	     			/*load_combogrid_catgory_sub(function(sss){
	     				if(sss){
	     				}
	     			},parentid);*/
	     		}
	     	},vmall);
		}
	});
	
	function load_combogrid_catgoryvmall(callback){
		extras_GET_json(true,"ad/product_type_vmallController","get_allcatgory","",function(data){
			if(data.length>0){
				$("#productype_vmall_dialog_s").combogrid("grid").datagrid("loadData",data);
				$("#productype_vmall_dialog_s").combogrid('setValue',data[0].product_type_vmall);
				callback(true);
			}else{
				$("#productype_vmall_dialog_s").combogrid('clear');
				$("#productype_vmall_dialog_s").combogrid("grid").datagrid("loadData",[]);
				callback(false);
			}
		});
	}
	function load_combogrid_catgory_parent(callback,vmall){
		var pdata = {'vmallid':vmall}
			extras_GET_json(true,"ad/product_type_subController","get_subcatgory_byvmall",pdata,function(data){
				if(data.length>0){
					$("#productype_parent_dialog_s").combogrid("grid").datagrid("loadData",data);
					$("#productype_parent_dialog_s").combogrid('setValue',data[0].product_type_id);
					
					callback(true);
				}else{
					
					$("#productype_parent_dialog_s").combogrid('clear');
					$("#productype_parent_dialog_s").combogrid("grid").datagrid("loadData",[]);
					callback(false);
				}
			});
	}
	
	
	function get_single_row_select(){
		 var checkedItems = $('#iddatagrip_dialog_s').datagrid('getChecked');
		 if(checkedItems.length>0){
			 return checkedItems[0].product_type_id;
		 }
		return "";
	}
	function get_multi_row_select(){
		 var checkedItems = $('#iddatagrip_dialog_s').datagrid('getChecked');
		 if(checkedItems.length>0){
			 var ids = [];
		        $.each(checkedItems, function(index, item){
		        	ids.push("'"+item.product_type_id+"'");
		        });                
		   return ids.join(",");
		 }
		 return ""; 
	}

});