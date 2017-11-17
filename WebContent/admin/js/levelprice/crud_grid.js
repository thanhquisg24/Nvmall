
$(function(){	
	$('#dg').datagrid({
	  		url: extras_Hosting["tomcatSpring_context"]+'ad/LevelPriceController/get_alllevel',
			toolbar: '#tbar',
		    idField:'id',
		 	singleSelect:true,
		    remoteSort:false,
		    method:'get',
		    columns:[[
		              {field:'ck',checkbox:true},
				        {field:'id',title:'ID#',width:100,halign:'center',align:'left',sortable:true},
				        {field:'name',title:'Name',width:150,halign:'center',align:'right',sortable:true},
				        {field:'pquery',title:'Query',width:300,halign:'center',align:'right',sortable:true}
				    ]],
	    view: detailview,
	    detailFormatter:function(index,row){
	        return '<div class="ddv"></div>';
	    },
	    onExpandRow: function(index,row){
	    	    close_collapse(index);
	    	  	var ddv = $(this).datagrid('getRowDetail',index).find('div.ddv');
	        	$.get(extras_Hosting["apache"] +"admin/views/LevelPrice/detail_edit_grid.html",function(data){
	    				//data=data.replace(/@id/g,id);
	        		   if(row.isNewRecord){
	        			   data=data.replace(/@levelpriceid_value/g,'');
	        			   data=data.replace(/@levelpricename_value/g,'');
	        			   data=data.replace(/@levelpricepquery_value/g,'');

	        		   }else{
	        			   data=data.replace(/@levelpriceid_value/g,row.id);
	        			   data=data.replace(/@levelpricename_value/g,row.name);
	        			   data=data.replace(/@levelpricepquery_value/g,row.pquery);
	        		   }
	        		   ddv.panel({
	       	            border:false,
	       	            cache:true,
	       	            content:data,
	       	            onLoad:function(){
	       	                $('#dg').datagrid('fixDetailRowHeight',index);
	       	                $('#dg').datagrid('fixRowHeight',index);
	       	                $('#dg').datagrid('selectRow',index);
	       	                //$('#dg').datagrid('getRowDetail',index).find('form').form('load',row);
	       	            }
	       	        });
	       	        $('#dg').datagrid('fixDetailRowHeight',index);
	       	        $('#dg').datagrid('fixRowHeight',index);
	        	});
	    }
	});
	
	
	$("#idappend").click(function(){
		$('#dg').datagrid('appendRow',{isNewRecord:true});
		var index = $('#dg').datagrid('getRows').length - 1;
		$('#dg').datagrid('expandRow', index);
		$('#dg').datagrid('selectRow', index);
	});
	$("#idremove").click(function(){
		var row = $('#dg').datagrid('getSelected');
		if(row){
			var index = $('#dg').datagrid('getRowIndex',row);
			if(row.isNewRecord){
				$('#dg').datagrid('clearChecked');
				$('#dg').datagrid('deleteRow',index);
			}else{
				do_delete_grid_tab2(row.id,index);
				
			}
		}else{
			$.messager.alert('Warning','Select Level please!','warning');
		}
	
	});
	function do_delete_grid_tab2(levelid,index_row){
		if(levelid==""){
			$.messager.alert('Warning','Select Level please!','warning');
			return;
		}
		$.messager.confirm('Confirm','Are you sure you want to delete record?',function(r){
		    if (r){
		        extras_POST_json(true,"ad/LevelPriceController","detele_single_level",{'str_id':levelid},function(data){
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