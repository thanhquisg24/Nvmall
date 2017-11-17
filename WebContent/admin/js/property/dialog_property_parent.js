$(function(){
	
	$('#dialog_property').dialog({
	    title: 'New Parent Property',
	    width: 400,
	    closed: true,
	    cache: false,
	    modal: true,
	    buttons: [{
            text: 'Save',
            iconCls: 'icon-save',
            handler: function() {
            	var  product_type_id=$("#idchooseproductype").textbox('getValue');
            	var url = extras_Hosting["tomcatSpring_context"]
				+'ad/propertyController/add_update_property?'
				+'product_type_id='+product_type_id;
            	$('#fm_property').form('submit',{
            		url: url,
            		onSubmit: function(){
            			return $(this).form('validate');
            		},
            		success: function(result){
            			var data=JSON.parse(result);
            	        $.messager.alert('Info', data.message, 'info');
            	        if(data.f=="0"){
            	        	 $("#dialog_property").dialog('close');
            	        		$('#fm_property').form('clear');
            	        		$('#cc_propertyparent').combogrid('grid').datagrid('reload');
            	        }
            		}
            	});
            }
        }, {
            text: 'Cancel',
            iconCls: 'icon-cancel',
            handler: function() {
                $("#dialog_property").dialog('close');
            }
        }]
    });
	
	$('#id_btn1_addnew_1').click(function(){
		var  product_type_id=$("#idchooseproductype").textbox('getValue');
		if(product_type_id!=""){
			$('#dialog_property').dialog('open').dialog('center').dialog('setTitle','New Parent Property');
			$('#fm_property').form('clear');
			$('#idfm_ptype').val('A');
			$('#idfm_parentproperty').val('0');
			$('#idfm_propertyid').textbox({readonly:false});
		}else{
			$.messager.alert('Warning','Choose product type please!','warning');
		}
		
	});
	
	$('#id_btn1_edit_1').click(function(){
		var row=$('#cc_propertyparent').combogrid('grid').datagrid('getSelected');
		if(row){
			//alert(row.id+"_"+row.property_name+"_"+row.product_type_sub_id);
			$('#dialog_property').dialog('open').dialog('center').dialog('setTitle','Edit Parent Property');
			$('#fm_property').form('clear');
			$('#idfm_ptype').val('E');
			$('#idfm_parentproperty').val('0');
			$('#idfm_propertyid').textbox('setValue',row.id);
			$('#idfm_propertyname').textbox('setValue',row.property_name);
			$('#idfm_propertyid').textbox({readonly:true});
		}else{
			$.messager.alert('Warning','Select Parent property please!','warning');
		}
		
		
		
	});
	
});