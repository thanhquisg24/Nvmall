$(function(){
	$('#dialog_productcustomer').dialog({
	    title: 'Choose product of customer',
	    width: 850,
	    height: 450,
	    closed: true,
	    cache: false,
	    modal: true,
	    buttons: [{
            text: 'Save',
            iconCls: 'icon-save',
            handler: function() {
            var ids=get_multi_product_add();
	            if(ids==""){
	            	  $.messager.alert('Warning','Please select row!!','warning');
	            	  return
	            }
	            var valuetab =$("#idchooseproductype").textbox('getValue');
	            custom_value="";
	            custom_value += $('#customer_cc').combogrid('getValue')+"_";
	            custom_value += $('#productype_parent_dialog_s').combogrid('getValue')+"_";
	            custom_value += valuetab+"_";
	            custom_value += ids ;
	            
	            var pdata={'custom_value': custom_value}
	            extras_POST_json(true,"ad/proRecommendedController","add_update_proRecommended",pdata,function(data){
	            	$.messager.alert('Result',data.message);
		        	if(data.f=="0"){
		        		$('#dg_productcustomer').datagrid('clearChecked');
		        		 $('#dg_productcustomer').datagrid('reload');
		        		$('#dg').datagrid('clearChecked');
		  	            $('#dg').datagrid('reload');
		        	}
	            });
	            $("#dialog_productcustomer").dialog('close');
            }
        }, {
            text: 'Cancel',
            iconCls: 'icon-cancel',
            handler: function() {
                $("#dialog_productcustomer").dialog('close');
            }
        }]
    });
	$('#dg_productcustomer').datagrid({
		toolbar: '#tbar_productcustomer',
	    //url: extras_Hosting["tomcatSpring_context"]+'ad/democrudController/get_json_append_to_datagrip.json',
	    height:350,
	    singleSelect:false,
	    idField:'product_id',
	    remoteSort:false,
	    columns:[[
			        {field:'ck',checkbox:true},
			        {field:'product_type_id',title:'ID#',width:100,halign:'left',sortable:true},
			        {field:'customer_type_name',title:'Customer type',width:150,halign:'center',sortable:true},
			        {field:'product_id',title:'Product ID',width:150,halign:'center',sortable:true},
			        {field:'product_name',title:'Product Name',width:250,halign:'center',sortable:true},
			    ]],
	    pagination:true
	});
	
	
	$('#customer_cc').combogrid({
    panelWidth: 400,				      
    idField: 'id',
    textField: 'shop_name',
    editable:false,
    columns: [[
            {field:'id',title:'ID#',width:100,halign:'center',sortable:true},
            {field:'email',title:'Email',width:150,halign:'center',sortable:true},
   	        {field:'shop_name',title:'Name',width:150,halign:'center',sortable:true},
   	        {field:'project_name',title:'Project name',width:100,halign:'center',align:'right',sortable:true},    
     ]],	
     onChange: function(newValue,oldValue){
    		$("#dg_productcustomer").datagrid("clearChecked");
    		$("#dg_productcustomer").datagrid("loadData",[]);
     		var cusid=  $("#customer_cc").combogrid('getValue');
     		if(cusid!=""&&cusid!=null){
     			var typeid = $("#idchooseproductype").textbox('getValue');
         		value_custom = cusid+"_"+typeid;
         		$('#dg_productcustomer').datagrid({
         			url: urlgrid(value_custom)
         		});
	       	 }
		 }
	});
	function urlgrid(values){
		 return  extras_Hosting["tomcatSpring_context"]+'ad/proRecommendedController/get_datagrip_customer_product.json?customvalue='+values;
	}
	function get_multi_product_add(){
		var checkedItems = $('#dg_productcustomer').datagrid('getChecked');
		 if(checkedItems.length>0){
			 var ids = [];
		        $.each(checkedItems, function(index, item){
		        	ids.push(item.product_id);
		        });                
		   return ids.join(",");
		 }
		 return "";
	}
});