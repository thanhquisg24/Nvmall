
$(function(){	
	$('#dg').datagrid({
	  		//url: extras_Hosting["tomcatSpring_context"]+'ad/colorController/get_datagrip_byproducttypeid.json?product_type_id=0401',

			title:'List Product Recommended',
			toolbar: '#tbar_tab2',
		 	singleSelect:true,
		    idField:'product_type_sub_id',
		    remoteSort:false,
		    height:600,
		    columns:[[
						{field:'ck',checkbox:true},
						{field:'product_type_id',title:'Code',width:120,halign:'center',sortable:true},
						{field:'customer_id',title:'Customer ID',width:100,align:'left',halign:'center',sortable:true},
						{field:'email',title:'Customer name',width:100,align:'left',halign:'center',sortable:true},
						{field:'product_id',title:'Product ID',width:120,align:'left',halign:'center',sortable:true},
						{field:'product_name',title:'Product name',width:250,align:'left',halign:'center',sortable:true},
						{field:'isvisible',title:'Is visibled',width:80,align:'center',halign:'center',sortable:true,formatter:extras_formatstatus_datagrid},
				    ]],
		pagination:true,
	  
	});
	$("#idappend_product_tab2").bind('click',function(){
		var  product_type_id=$("#idchooseproductype").textbox('getValue');
		if(product_type_id==null||product_type_id==""){
			  $.messager.alert('Warning', 'Please choose product type first!!!', 'warning');
			  return false;
		}
		 $("#dialog_productcustomer").dialog('open').dialog('center');
	});
	
	$("#idremove_product_tab2").click(function(){
		var  product_type_id=$("#idchooseproductype").textbox('getValue');
		if(product_type_id==null||product_type_id==""){
			  $.messager.alert('Warning', 'Please choose product type first!!!', 'warning');
			  return false;
		}
		
			var ids=get_selected_type();
			if(ids==""){
				$.messager.alert('Warning','Select row please!');
				return;
			}
			do_delete_grid_tab2(ids);

	});
	function do_delete_grid_tab2(ids){
		if(ids==""){
			$.messager.alert('Warning','Select Product please!');
			return;
		}
		$.messager.confirm('Confirm','Are you sure you want to delete record?',function(r){
		    if (r){
		    	 extras_POST_json(true,"ad/proRecommendedController","delete_multi_proRecommended",{'str_id':ids},function(data){
			        	$.messager.alert('Result',data.message);
			        	if(data.f=="0"){
			        		$('#dg').datagrid('clearChecked');
			        		$('#dg').datagrid('reload');
			        	}
			        });      
		    }
		});
	}
	function get_selected_type(){
		 var checkedItems = $('#iddatagrip').datagrid('getChecked');
		 if(checkedItems.length>0){
			 return checkedItems[0].customer_id+"_"+checkedItems[0].product_id;
		 }
		return "";
	}
});