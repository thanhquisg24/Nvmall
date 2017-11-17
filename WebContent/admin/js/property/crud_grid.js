
$(function(){	
	$('#dg').datagrid({
	  		//url: extras_Hosting["tomcatSpring_context"]+'ad/branchController/get_datagrip_byproducttypeid.json?product_type_id=0401',
			title:'List property sub ',
			toolbar: '#tbar_tab2',
		 	singleSelect:true,
		    idField:'id',
		    remoteSort:false,
		    height:600,
		    columns:[[
		              {field:'id',title:'ID#',width:100,halign:'center',sortable:true},
		              {field:'property_name',title:'Property Name',width:200,halign:'center',sortable:true},
		              {field:'product_type_sub_id',title:'product_type_sub_id',width:150,halign:'center',sortable:true},
		              {field:'isvisible',title:'Is Visibled',width:100,halign:'center',align:'right',sortable:true,formatter:extras_formatstatus_datagrid}
				    ]],
		pagination:true,
	    view: detailview,
	    detailFormatter:function(index,row){
	        return '<div class="ddv"></div>';
	    },
	    onExpandRow: function(index,row){
	    	    close_collapse(index);
	    	  	var ddv = $(this).datagrid('getRowDetail',index).find('div.ddv');
	        	$.get(extras_Hosting["apache"] +"admin/views/Property/detail_edit_grid.html",function(data){
	    				//data=data.replace(/@id/g,id);
	        		   if(row.isNewRecord){
	        			   data=data.replace(/@propertyid_value/g,'');
	        			   data=data.replace(/@propertyname_value/g,'');
	        			
	        		   }else{
	        			   data=data.replace(/@propertyid_value/g,row.id);
	        			   data=data.replace(/@propertyname_value/g,row.property_name);
	        		   }
	        		   ddv.panel({
	       	            border:false,
	       	            cache:true,
	       	            content:data,
	       	            onLoad:function(){
	       	                $('#dg').datagrid('fixDetailRowHeight',index);
	       	                $('#dg').datagrid('selectRow',index);
	       	                //$('#dg').datagrid('getRowDetail',index).find('form').form('load',row);
	       	            }
	       	        });
	       	        $('#dg').datagrid('fixDetailRowHeight',index);
	        	});
	    }
	});
	$("#idappend_tab2").click(function(){
		var  product_type_id=$("#idchooseproductype").textbox('getValue');
		var parentproperty= $("#cc_propertyparent").combogrid('getValue');
		if(product_type_id==""||parentproperty==""){
			  $.messager.alert('Warning', 'Please choose product type and Parent property first!!!', 'warning');
			  return false;
		}
		$('#dg').datagrid('appendRow',{isNewRecord:true});
		var index = $('#dg').datagrid('getRows').length - 1;
		$('#dg').datagrid('expandRow', index);
		$('#dg').datagrid('selectRow', index);
	});
	$("#idremove_tab2").click(function(){
		var  product_type_id=$("#idchooseproductype").textbox('getValue');
		var parentproperty= $("#cc_propertyparent").combogrid('getValue');
		if(product_type_id==""||parentproperty==""){
			  $.messager.alert('Warning', 'Please choose product type and Parent property first!!!', 'warning');
			  return false;
		}
		var row = $('#dg').datagrid('getSelected');
		var index = $('#dg').datagrid('getRowIndex',row);
		if(row.isNewRecord){
			$('#dg').datagrid('clearChecked');
			$('#dg').datagrid('deleteRow',index);
		}else{
			var propertyid=row.id;
			do_delete_grid_tab2(propertyid,index);
			
		}
	});
	function do_delete_grid_tab2(propertyid,index_row){
		if(propertyid==""){
			$.messager.alert('Warning','Select Branch please!');
			return;
		}
		$.messager.confirm('Confirm','Are you sure you want to delete record?',function(r){
		    if (r){
		        extras_POST_json(true,"ad/propertyController","detele_single_property",{'str_id':propertyid},function(data){
		        	$.messager.alert('Result',data.message);
		        	if(data.f=="0"){
		        		$('#dg').datagrid('clearChecked');
		    			$('#dg').datagrid('deleteRow',index_row);
		        	}
		        });        
		    }
		});
	}
	function close_collapse(current_index){
		var allrow=$('#dg').datagrid('getRows');
		for(var i=0;i<allrow.length;i++){
			var index = $('#dg').datagrid('getRowIndex',allrow[i]);
			if(index==current_index){
				$('#dg').datagrid('expandRow', current_index);
				$('#dg').datagrid('selectRow', current_index);
			}else{
				$('#dg').datagrid('collapseRow',index);
			}
		}
	}
});