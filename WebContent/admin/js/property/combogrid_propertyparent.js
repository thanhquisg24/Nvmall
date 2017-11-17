$(function(){
	
	$('#cc_propertyparent').combogrid({
        panelWidth: 600,				      
        idField: 'id',
        textField: 'property_name',
        editable:false,
        columns: [[
                {field:'id',title:'ID#',width:100,halign:'center',sortable:true},
                {field:'property_name',title:'Property Name',width:200,halign:'center',sortable:true},
       	        {field:'product_type_sub_id',title:'product_type_sub_id',width:150,halign:'center',sortable:true},
       	        {field:'isvisible',title:'Is Visibled',width:100,halign:'center',align:'right',sortable:true,formatter:extras_formatstatus_datagrid}
         ]],
         /*onSelect:function(index,row){
        	 var parentid=row.id;
        	 var url_property_sub_context= extras_Hosting["tomcatSpring_context"]+'ad/propertyController/get_datagrip_byparentid.json?parentid='+parentid;
        	 $('#dg').datagrid('clearChecked');
        	 $('#dg').datagrid({url:url_property_sub_context});
         }*/
         onChange: function(newValue,oldValue){
        	 var parentid = $("#cc_propertyparent").combogrid('getValue');
        	 if(parentid!=""&&parentid!=null){
        	 var url_property_sub_context= extras_Hosting["tomcatSpring_context"]+'ad/propertyController/get_datagrip_byparentid.json?parentid='+parentid;
        	
        	 $('#dg').datagrid('clearChecked');
        	 $('#dg').datagrid({url:url_property_sub_context});
        	 }else{
   
        		  $('#dg').datagrid('clearChecked');
        		  $('#dg').datagrid("loadData",[]);
        	 }
         }
    });
	
});