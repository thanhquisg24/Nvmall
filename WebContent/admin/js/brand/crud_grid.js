
$(function(){	
	$('#dg').datagrid({
	  		//url: extras_Hosting["tomcatSpring_context"]+'ad/branchController/get_datagrip_byproducttypeid.json?product_type_id=0401',

			title:'List branch',
			toolbar: '#tbar_tab2',
		 	singleSelect:true,
		    idField:'product_type_id',
		    remoteSort:false,
		    height:600,
		    columns:[[
				        {field:'id',title:'Branch ID#',width:200,halign:'left',sortable:true},
				        {field:'name',title:'Branch Name',width:100,halign:'center',sortable:true},
				        {field:'country',title:'Branch contry',width:300,halign:'center',sortable:true},
				        {field:'image',title:'Branch Image',width:250,halign:'center',align:'center',sortable:true,formatter:extras_formatImg_datagrid_branch},
				        {field:'isvisible',title:'Is Visibled',width:150,halign:'center',align:'right',sortable:true,formatter:extras_formatstatus_datagrid}
				    ]],
		pagination:true,
	    view: detailview,
	    detailFormatter:function(index,row){
	        return '<div class="ddv"></div>';
	    },
	    onExpandRow: function(index,row){
	    	    close_collapse(index);
	    		var branchid=row.id;
	    	  	var ddv = $(this).datagrid('getRowDetail',index).find('div.ddv');
	        	$.get(extras_Hosting["apache"] +"admin/views/Branch/detail_edit_grid.html",function(data){
	    				//data=data.replace(/@id/g,id);
	        		   if(row.isNewRecord){
	        			   data=data.replace(/@branchidvalue/g,'');
	        			   data=data.replace(/@branchimgvalue/g,'');
	        			   data=data.replace(/@branchnamevalue/g,'');
	        			   data=data.replace(/@branchcountryvalue/g,'');
	        			   data=data.replace(/@branchimg_display/g,'upload/no_image.jpg');
	        			   data=data.replace(/@index/g,index);
	        		   }else{
	        			   data=data.replace(/@branchidvalue/g,branchid);
	        			   data=data.replace(/@branchimgvalue/g,row.image);
	        			   data=data.replace(/@branchnamevalue/g,row.name);
	        			   data=data.replace(/@branchcountryvalue/g,row.country);
	        			   if(row.image==null||row.image==""){
	        				   data=data.replace(/@branchimg_display/g,'upload/no_image.jpg');
	        			   }else{
	        				   data=data.replace(/@branchimg_display/g,'upload/branch/'+row.image);
	        			   }
	        			  
	        			   data=data.replace(/@index/g,index);
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
	$("#idappend_branch_tab2").click(function(){
		var  product_type_id=$("#idchooseproductype").textbox('getValue');
		if(product_type_id==null||product_type_id==""){
			  $.messager.alert('Warning', 'Please choose product type first!!!', 'warning');
			  return false;
		}
		$('#dg').datagrid('appendRow',{isNewRecord:true});
		var index = $('#dg').datagrid('getRows').length - 1;
		$('#dg').datagrid('expandRow', index);
		$('#dg').datagrid('selectRow', index);
	});
	$("#idremove_branch_tab2").click(function(){
		var  product_type_id=$("#idchooseproductype").textbox('getValue');
		if(product_type_id==null||product_type_id==""){
			  $.messager.alert('Warning', 'Please choose product type first!!!', 'warning');
			  return false;
		}
		var row = $('#dg').datagrid('getSelected');
		var index = $('#dg').datagrid('getRowIndex',row);
		if(row.isNewRecord){
			$('#dg').datagrid('clearChecked');
			$('#dg').datagrid('deleteRow',index);
		}else{
			var brandid=row.id;
			do_delete_grid_tab2(brandid,index);
			
		}
	});
	function do_delete_grid_tab2(brandid,index_row){
		if(brandid==""){
			$.messager.alert('Warning','Select Branch please!');
			return;
		}
		$.messager.confirm('Confirm','Are you sure you want to delete record?',function(r){
		    if (r){
		        extras_POST_json(true,"ad/branchController","detele_single_branch",{'str_id':brandid},function(data){
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