
$(function(){
	$('#cc_city').combogrid({
		 url: extras_Hosting["tomcatSpring_context"]+'ad/locationController/get_allCity',
		  method: 'get',
        panelWidth: 600,				      
        idField: 'location_id',
        textField: 'location_name',
        editable:false,
        columns: [[
                  {field:'location_id',title:'ID#',width:150,halign:'center',sortable:true},
       	        {field:'location_name',title:' City Name',width:180,align:'left',halign:'center',sortable:true},
       	        {field:'isvisible',title:'Is visibled',width:100,align:'center',halign:'center',sortable:true,formatter:extras_formatstatus_datagrid},
       	        {field:'action',title:'Action',width:80,align:'center',
					formatter:function(value,row,index){
						if (row.editing){
							var s = '<a href="javascript:void(0);" onclick="saverow_city('+index+')" class="easyui-linkbutton" iconCls="icon-save" plain="true">Save</a> ';
							var c = '<a href="javascript:void(0);" onclick="cancelrow_city('+index+')" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">Cancel</a>';
							return s+c;
						} else {
							var e = '<a href="javascript:void(0);" onclick="editrow_city('+index+')" class="easyui-linkbutton" iconCls="icon-edit" plain="true">Edit</a> ';
							var d = '<a href="javascript:void(0);" onclick="deleterow_city('+index+')" class="easyui-linkbutton" iconCls="icon-remove" plain="true">Delete</a>';
							return e+d;
						}
					}
				}
         ]],
        /* onSelect:function(index,row){
        	/* var parentid=row.id;
        	 var url_property_sub_context= extras_Hosting["tomcatSpring_context"]+'ad/propertyController/get_datagrip_byparentid.json?parentid='+parentid;
        	 $('#dg').datagrid('clearChecked');
        	 $('#dg').datagrid({url:url_property_sub_context});
        	 alert("onselect");
        
        	 $(this).combogrid('setValue', '');  
         },*/
         onHidePanel: function () {  
             var t = $(this).combogrid('getValue');  
          if(check_action_click_cc_city){
        	  $(this).combogrid('setValue', '');  
        	  $("#cc_district").combogrid("grid").datagrid("loadData",[]);		
          }               
          check_action_click_cc_city=false;
         },  
         onChange: function(newValue,oldValue){
        	 var cityid = $("#cc_city").combogrid('getValue');
        	 $("#cc_district").combogrid('clear');
        	 if(cityid!=""&&cityid!=null){
        	 var url_get_district= extras_Hosting["tomcatSpring_context"]+'ad/locationController/get_district_bycity?city='+cityid;
        	  $("#cc_district").combogrid({url:url_get_district});
   
        	 }else{
        		  $("#cc_district").combogrid("grid").datagrid("loadData",[]);		
        	 }
 
         }
    });
	
	
	$('#cc_district').combogrid({
		// url: extras_Hosting["tomcatSpring_context"]+'ad/locationController/get_allCity',
		  //method: 'get',
       panelWidth: 600,				      
       idField: 'location_id',
       textField: 'location_name',
       editable:false,
       columns: [[
                 {field:'location_id',title:'ID#',width:150,halign:'center',sortable:true},
      	        {field:'location_name',title:' District Name',width:180,align:'left',halign:'center',sortable:true},
      	        {field:'isvisible',title:'Is visibled',width:100,align:'center',halign:'center',sortable:true,formatter:extras_formatstatus_datagrid},
      	      {field:'action',title:'Action',width:80,align:'center',
					formatter:function(value,row,index){
						if (row.editing){
							var s = '<a href="javascript:void(0);" onclick="saverow_district('+index+')" class="easyui-linkbutton" iconCls="icon-save" plain="true">Save</a> ';
							var c = '<a href="javascript:void(0);" onclick="cancelrow_district('+index+')" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">Cancel</a>';
							return s+c;
						} else {
							var e = '<a href="javascript:void(0);" onclick="editrow_district('+index+')" class="easyui-linkbutton" iconCls="icon-edit" plain="true">Edit</a> ';
							var d = '<a href="javascript:void(0);" onclick="deleterow_district('+index+')" class="easyui-linkbutton" iconCls="icon-remove" plain="true">Delete</a>';
							return e+d;
						}
					}
				}
        ]],
        onHidePanel: function () {  
            var t = $(this).combogrid('getValue');  
         if(check_action_click_cc_district){
       	  $(this).combogrid('setValue', '');  
       	  $('#dg').datagrid('clearSelections');
       	  $('#dg').datagrid("loadData",[]);
         }               
         check_action_click_cc_district=false;
        },  
        /*onSelect:function(index,row){
       	 var parentid=row.id;
       	 var url_property_sub_context= extras_Hosting["tomcatSpring_context"]+'ad/propertyController/get_datagrip_byparentid.json?parentid='+parentid;
       	 $('#dg').datagrid('clearChecked');
       	 $('#dg').datagrid({url:url_property_sub_context});
        }*/
        onChange: function(newValue,oldValue){
       	 var district = $("#cc_district").combogrid('getValue');
       	 if(district!=""&&district!=null){
       	 var url_get_ward= extras_Hosting["tomcatSpring_context"]+'ad/locationController/get_ward_bydistrict?district='+district;
       	 $('#dg').datagrid('clearSelections');
       	 $('#dg').datagrid({url:url_get_ward});
       	 }else{
  
       		  $('#dg').datagrid('clearSelections');
       		  $('#dg').datagrid("loadData",[]);
       	 }
        }
   });
});