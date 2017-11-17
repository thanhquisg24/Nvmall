
$(function(){	
	$('#dg').datagrid({
	  		//url: extras_Hosting["tomcatSpring_context"]+'ad/colorController/get_datagrip_byproducttypeid.json?product_type_id=0401',

			title:'List Color',
			toolbar: '#tbar_tab2',
		 	singleSelect:true,
		    idField:'product_type_sub_id',
		    remoteSort:false,
		    height:600,
		    columns:[[
					{field:'id',title:'Code',width:170,halign:'center',sortable:true},
					{field:'product_type_sub_id',title:'Type ID',width:80,align:'left',halign:'center',sortable:true},
					{field:'product_type_name',title:'Productype name',width:170,align:'left',halign:'center',sortable:true},
			        {field:'color_name',title:'Color name',width:120,align:'left',halign:'center',sortable:true},
			        {field:'color',title:'Color code',width:120,align:'left',halign:'center',sortable:true,formatter:extras_format_color_datagrid},
			        {field:'img',title:'Image',width:180,align:'left',halign:'center',sortable:true,formatter:extras_formatImg_datagrid_color},
			        {field:'isvisible',title:'Is visibled',width:80,align:'center',halign:'center',sortable:true,formatter:extras_formatstatus_datagrid},
			        {field:'type',title:'Type set',width:80,align:'left',halign:'center',sortable:true,formatter:extras_format_type_color}
				    ]],
		pagination:true,
	    view: detailview,
	    detailFormatter:function(index,row){
	        return '<div class="ddv"></div>';
	    },
	    onExpandRow: function(index,row){
	    	    close_collapse(index);
	    	  	var ddv = $(this).datagrid('getRowDetail',index).find('div.ddv');
	        	$.get(extras_Hosting["apache"] +"admin/views/Color/detail_edit_grid.html",function(data){
	    				//data=data.replace(/@id/g,id);
	        		   if(row.isNewRecord){
	        			   data=data.replace(/@coloridvalue/g,'');
	        			   data=data.replace(/@colorimgvalue/g,'');
	        			   data=data.replace(/@colornamevalue/g,'');
	        			   data=data.replace(/@colorcountryvalue/g,'');
	        			   data=data.replace(/@colorimg_display/g,'upload/no_image.jpg');
	        			   data=data.replace(/@index/g,index);
	        		   }else{
	        			   data=data.replace(/@idvalue/g,row.id);
	        			   data=data.replace(/@coloridvalue/g,row.color);
	        			   data=data.replace(/@colorimgvalue/g,row.img);
	        			   data=data.replace(/@colornamevalue/g,row.color_name);
	        			   if(row.img==null||row.img==""){
	        				   data=data.replace(/@colorimg_display/g,'upload/no_image.jpg');
	        			   }else{
	        				   data=data.replace(/@colorimg_display/g,'upload/color/'+row.img);
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
	$("#idappend_color_tab2").click(function(){
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
	$("#idremove_color_tab2").click(function(){
		var  product_type_id=$("#idchooseproductype").textbox('getValue');
		if(product_type_id==null||product_type_id==""){
			  $.messager.alert('Warning', 'Please choose product type first!!!', 'warning');
			  return false;
		}
		var row = $('#dg').datagrid('getSelected');
		if(row==null||row==""){
			  $.messager.alert('Warning', 'Please choose a color type !!!', 'warning');
			  return false;
		}
		var index = $('#dg').datagrid('getRowIndex',row);
		if(row.isNewRecord){
			$('#dg').datagrid('clearChecked');
			$('#dg').datagrid('deleteRow',index);
		}else{
			var valueid="'"+row.id+"'";
			do_delete_grid_tab2(valueid,index);
			
		}
	});
	function do_delete_grid_tab2(valueid,index_row){
		if(valueid==""){
			$.messager.alert('Warning','Select Color type please!');
			return;
		}
		$.messager.confirm('Confirm','Are you sure you want to delete record?',function(r){
		    if (r){
		        extras_POST_json(true,"ad/colorController","delete_multi_color",{'str_id':valueid},function(data){
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