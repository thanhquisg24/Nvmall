
$(function(){
	$('#cc_catgorynews').combogrid({
		 url: extras_Hosting["tomcatSpring_context"]+'ad/NewsController/get_allCatgoryNews',
		  method: 'get',
        panelWidth: 600,				      
        idField: 'id',
        textField: 'name',
        editable:false,
        columns: [[
                {field:'category_id',title:'ID#',width:250,halign:'center',align:'center',sortable:true},
       	        {field:'category_name',title:'Name',width:250,align:'left',halign:'center',sortable:true},
       	        {field:'isvisible',title:'Is visibled',width:100,align:'center',halign:'center',sortable:true,formatter:extras_formatstatus_datagrid}
       	        /*{field:'action',title:'Action',width:80,align:'center',
					formatter:function(value,row,index){
						if (row.editing){
							var s = '<a href="javascript:void(0);" onclick="saverow_catgorynews('+index+')" class="easyui-linkbutton" iconCls="icon-save" plain="true">Save</a> ';
							var c = '<a href="javascript:void(0);" onclick="cancelrow_catgorynews('+index+')" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">Cancel</a>';
							return s+c;
						} else {
							var e = '<a href="javascript:void(0);" onclick="editrow_catgorynews('+index+')" class="easyui-linkbutton" iconCls="icon-edit" plain="true">Edit</a> ';
							var d = '<a href="javascript:void(0);" onclick="deleterow_catgorynews('+index+')" class="easyui-linkbutton" iconCls="icon-remove" plain="true">Delete</a>';
							return e+d;
						}
					}
				}*/
         ]],
         onSelect:function(index,row){
        	 $('#cc_catgorynews').combogrid('setValue',row.category_id);
         },
         onHidePanel: function () {  
             var t = $(this).combogrid('getValue');  
          if(check_action_click_cc_catgorynews){
        	  $(this).combogrid('setValue', '');  
        	  //$("#cc_district").combogrid("grid").datagrid("loadData",[]);		
          }               
          check_action_click_cc_catgorynews=false;
         },  
         onChange: function(newValue,oldValue){
        	/* var cityid = $("#cc_catgorynews").combogrid('getValue');
        	 $("#cc_district").combogrid('clear');
        	 if(cityid!=""&&cityid!=null){
        	 var url_get_district= extras_Hosting["tomcatSpring_context"]+'ad/locationController/get_district_bycity?city='+cityid;
        	  $("#cc_district").combogrid({url:url_get_district});
   
        	 }else{
        		  $("#cc_district").combogrid("grid").datagrid("loadData",[]);		
        	 }*/
 
         }
    });
	
});