$(function(){
	
	function urlgrid(parentid){
		 return  extras_Hosting["tomcatSpring_context"]+'ad/product_type_subController/get_json_sub_append_to_datagrip.json?parentid='+parentid;
	}
	function clear_iddatagrip_dialog_s(){
		$("#iddatagrip_dialog_s").datagrid("clearChecked");
		$("#iddatagrip_dialog_s").datagrid("loadData",[]);
	}
	function set_value_toselector(selector,svalue){
		$(selector).textbox('setValue',svalue);
	}
	
	 $('#id_btn1_choose_1').bind('click', function(){
		  $("#dialog_s").data('selector','#idchooseproductype');
		  $("#dialog_s").dialog('open');
	    });
	$('#idchooseproductype').textbox({
		prompt:"Click button open list ...",
		//iconWidth: 30,
		readonly:true,
		/*icons: [{
			iconCls:'icon-search',
			handler: function(e){
				  $("#dialog_s").data('selector','#idchooseproductype');
				  $("#dialog_s").dialog('open');
				//$(e.data.target).textbox('setValue', 'Something added!');
			}
		}],*/
		 onChange: function(newValue,oldValue){
			 	console.log('The old value ' + oldValue);
			    console.log('The value has been changed to ' + newValue);
			    if(newValue==null||newValue==""){
			    	  $('#dg').datagrid('clearChecked');
					  $('#dg').datagrid("loadData",[]);
			    }else{
			    	  var url_context_grid_color_of=extras_Hosting["tomcatSpring_context"]+'ad/colorController/get_datagrip_byproducttypeid.json?product_type_id='+newValue;
					  $('#dg').datagrid('clearChecked');
					  $('#dg').datagrid({url:url_context_grid_color_of});
			    }
			
		}
	});
	
	$('#dialog_s').dialog({
	    title: 'Choose product type',
	    width: 800,
	    height: 450,
	    closed: true,
	    cache: false,
	    modal: true,
	    buttons: [{
            text: 'OK',
            iconCls: 'icon-ok',
            handler: function() {
            var data_selector=	$("#dialog_s").data('selector');
            var svalue=get_single_row_select();
	            if(svalue==""){
	            	  $.messager.alert('Warning','Please select row!!','warning');
	            	  return
	            }
	            //set_value_toselector(data_selector,svalue) ;
	            $('#idchooseproductype').textbox('setValue',svalue);
	            $("#dialog_s").dialog('close');
            }
        }, {
            text: 'Cancel',
            iconCls: 'icon-cancel',
            handler: function() {
                $("#dialog_s").dialog('close');
            }
        }]
    });
	
	$('#iddatagrip_dialog_s').datagrid({
		toolbar: '#tbar_dialog_s',
	    //url: extras_Hosting["tomcatSpring_context"]+'ad/democrudController/get_json_append_to_datagrip.json',
	    height:350,
	    singleSelect:true,
	    idField:'product_type_id',
	    remoteSort:false,
	    columns:[[
			        {field:'ck',checkbox:true},
			        {field:'id',title:'ID#',width:200,halign:'left',sortable:true},
			        {field:'product_type_id',title:'Type sub id',width:100,halign:'center',sortable:true},
			        {field:'product_type_name',title:'Name',width:300,halign:'center',sortable:true},
			        {field:'category_img',title:'Image',width:250,halign:'center',align:'center',sortable:true,formatter:extras_formatImg_datagrid},
			        {field:'isvisible',title:'Is Visibled',width:150,halign:'center',align:'right',sortable:true,formatter:extras_formatstatus_datagrid}
			    ]],
	    pagination:true
	});
	load_mm_selector(function(out){
		if(out==true){
			$('#ss_dialog_s').searchbox({
			    searcher:function(value,name){
			    	  clear_iddatagrip_dialog_s();
			    	  var vmall = $("#productype_vmall_dialog_s").combogrid('getValue');
			    	  var vsub= $("#productype_sub_dialog_s").combogrid('getValue');
			    	
			    	  if(vmall!='' &&vsub!='')
					   {	$('#iddatagrip_dialog_s').datagrid('load',{
					    		  	col:name,
					    		  	val:value
					    	    });
			    	  }
			    	  else{
			    		  $.messager.alert('Warning','Please select Productype Vmall and Productype Sub','warning');
			    	  }
			    },
			    menu:'#mm_dialog_s',
			    prompt:'Please Input Value'
			});

		}
	});
	function load_mm_selector(callback){
		$("#mm_dialog_s").html('');   	
		var pdata={'pcdtype':'CATGORYSUB','pcdname':'SEARCH'};
    	extras_GET_json(true,"ad/AllcodeController","get_allcode_searchbox.json",pdata,function(data){
    		if (data != null) {
	    		var s1 = '';
	            $.map(data, function(item) {
					s1 += '<div data-options="name:' + item.cdval + '">'
					+ item.cdcontent + '</div>';
				});
	        	$('#mm_dialog_s').html(s1);
	        	callback(true);
	       
    		}
    	});
	}
	
	$('#productype_vmall_dialog_s').combogrid({
        panelWidth: 400,				      
        idField: 'product_type_vmall',
        textField: 'product_type_name',
        columns: [[
                {field:'product_type_vmall',title:'ID#',width:100,halign:'center',sortable:true},
       	        {field:'product_type_name',title:'Name',width:150,halign:'center',sortable:true},
       	        {field:'isvisible',title:'Is Visibled',width:100,halign:'center',align:'right',sortable:true,formatter:extras_formatstatus_datagrid}
         ]],
         onSelect:function(index,row){
        	var vmall = $("#productype_vmall_dialog_s").combogrid('getValue');
        	clear_iddatagrip_dialog_s();
        	load_combogrid_catgorysub_ofvmall_parent0(function(s){},vmall,false);
        	
         }
    });
	$('#productype_sub_dialog_s').combogrid({
        panelWidth: 400,				      
        idField: 'product_type_id',
        textField: 'product_type_name',
        columns: [[
				{field:'product_type_id',title:'ID#',width:100,halign:'center',sortable:true},
				{field:'product_type_name',title:'Name',width:150,halign:'center',sortable:true},
				{field:'isvisible',title:'Is Visibled',width:100,halign:'center',align:'right',sortable:true,formatter:extras_formatstatus_datagrid}
         ]],
         onSelect:function(index,row){
        	 var product_type_id= row.product_type_id;
        	 $('#iddatagrip_dialog_s').datagrid({
        		 url: urlgrid(product_type_id)
        	 });
          }
    });
	load_combogrid_catgoryvmall(function(s){
		if(s){
	     	var vmall = $("#productype_vmall_dialog_s").combogrid('getValue');
			load_combogrid_catgorysub_ofvmall_parent0(function(ss){},vmall,false);
		}
	},true);
	function load_combogrid_catgoryvmall(callback,isADD){
		extras_GET_json(true,"ad/product_type_vmallController","get_allcatgory","",function(data){
			if(data.length>0){
				$("#productype_vmall_dialog_s").combogrid("grid").datagrid("loadData",data);
				if(isADD){
					$("#productype_vmall_dialog_s").combogrid('setValue',data[0].product_type_vmall);
				}
				callback(true);
			}else{
				$("#productype_vmall_dialog_s").combogrid('clear');
				$("#productype_vmall_dialog_s").combogrid("grid").datagrid("loadData",[]);
				callback(false);
			}
		});
	}
	function load_combogrid_catgorysub_ofvmall_parent0(callback,vmall,isADD){
		var pdata = {'vmallid':vmall}
			extras_GET_json(true,"ad/product_type_subController","get_subcatgory_byvmall",pdata,function(data){
				if(data.length>0){
					$("#productype_sub_dialog_s").combogrid("grid").datagrid("loadData",data);
					if(isADD){
						$("#productype_sub_dialog_s").combogrid('setValue',data[0].product_type_id);
					}
					callback(true);
				}else{
					//$("#liuser").text("null");
					$("#productype_sub_dialog_s").combogrid('clear');
					$("#productype_sub_dialog_s").combogrid("grid").datagrid("loadData",[]);
					callback(false);
				}
			});
	}
	
	function get_single_row_select(){
		 var checkedItems = $('#iddatagrip_dialog_s').datagrid('getChecked');
		 if(checkedItems.length>0){
			 return checkedItems[0].product_type_id;
		 }
		return "";
	}
	function get_multi_row_select(){
		 var checkedItems = $('#iddatagrip_dialog_s').datagrid('getChecked');
		 if(checkedItems.length>0){
			 var ids = [];
		        $.each(checkedItems, function(index, item){
		        	ids.push("'"+item.product_type_id+"'");
		        });                
		   return ids.join(",");
		 }
		 return ""; 
	}
	
	
});