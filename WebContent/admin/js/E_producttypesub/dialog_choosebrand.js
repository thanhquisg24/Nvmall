$(function(){
	
	$("#id_d").click(function(){
		 //$("#dialog_level").data('sgrid','#dg_@id');
		 //$("#dialog_level").data('svmall','#hidden_vnall_@id');
		 $("#dialog_choosebrand").dialog('open').dialog('center');
	});
	
	$('#dialog_choosebrand').dialog({
	    title: 'Choose Brand',
	    width: 800,
	    closed: true,
	    cache: false,
	    modal: true,
	    buttons: [{
            text: 'Save',
            iconCls: 'icon-save',
            handler: function() {
            	  var grid_selector=	$("#dialog_choosebrand").data('sgrid');
                  var hidden_selector=	$("#dialog_choosebrand").data('sproductypesub');
                  var productypeid=$(hidden_selector).val();
      	            var checkedItems = $('#dg_brand').datagrid('getChecked');
      	            if(checkedItems.length>0){
      	            	var allitem_ofgrid_selector=$(grid_selector).datagrid('getData');
      	            	//alert(JSON.stringify(allitem_ofgrid_selector));
      	            	do_append_andcompare(checkedItems,allitem_ofgrid_selector.rows,productypeid,grid_selector);
      	            }
      	            else{
      	            	  $.messager.alert('Warning','Please select row!!','warning');
      	            	  return;
      	            }
      	            $("#dialog_choosebrand").dialog('close');
            }
        }, {
            text: 'Cancel',
            iconCls: 'icon-cancel',
            handler: function() {
                $("#dialog_choosebrand").dialog('close');
            }
        }]
    });
	function do_append_andcompare(checkedItems,rows,product_type_id,grid_selector){
		var arr_=[];
		if(rows.length>0){
			for(var i=0;i<checkedItems.length;i++){
				if(bool_compare(checkedItems[i],rows)){
					arr_.push(checkedItems[i]);
				}
			}
			if(arr_.length==0){
				 $.messager.alert('Info','Append success!!','info');
				 $('#dg_brand').datagrid('clearChecked');
				 return;
			}
		}else{
			arr_=checkedItems;
		}
		//alert(product_type_id);
		//alert(JSON.stringify(arr_));
		var pdata={'product_type_id':product_type_id,'json_brand':JSON.stringify(arr_)};	
		 extras_POST_json(true,"ad/branddetailproductypeController","do_append_brand",pdata,function(data){
		        	$.messager.alert('Result',data.message);
		        	if(data.f=="0"){
		        		$('#dg_brand').datagrid('clearChecked');
		        		$(grid_selector).datagrid('clearChecked');
		        		$(grid_selector).datagrid('reload');
		        	}
		        });
	}
	function bool_compare(item,rows){
		for(var i=0;i<rows.length;i++){
			if(item.id==rows[i].brand_id){
				return false;
			}
		}
		return true;
	}
	
	
	$('#dg_brand').datagrid({
		toolbar: '#tbar_brand',
	    url: extras_Hosting["tomcatSpring_context"]+'ad/brandController/get_json_append_to_datagrip.json',
	    idField:'id',
	    multiSort:true,
	    remoteSort:false,
	    height:600,
	    columns:[[
	        {field:'ck',checkbox:true},
	        {field:'id',title:'Code',width:150,halign:'center',sortable:true},
	        {field:'name',title:'Name',width:110,align:'left',halign:'center',sortable:true},
	        {field:'image',title:'Image',width:150,align:'left',halign:'center',sortable:true,formatter:extras_formatImg_datagrid_branch},
	        {field:'country',title:'Country',width:110,align:'left',halign:'center',sortable:true},
	        {field:'isvisible',title:'Is visibled',width:90,align:'center',halign:'center',sortable:true,formatter:extras_formatstatus_datagrid}
	    ]],
	    pagination:true
	  
	});
	load_mm_selector(function(out){
		if(out==true){
			$('#ss_brand').searchbox({
			    searcher:function(value,name){
			       // alert(value + "," + name);
			    	  $('#dg_brand').datagrid('load',{
			    		  	col:name,
			    		  	val:value
			    	    });
			    },
			    menu:'#mm_brand',
			    prompt:'Please Input Value'
			});

		}
	});
	function load_mm_selector(callback){
		$("#mm_brand").html('');   	
		var pdata={'pcdtype':'BRANCH','pcdname':'SEARCH'};
    	extras_GET_json(true,"ad/AllcodeController","get_allcode_searchbox.json",pdata,function(data){
    		if (data != null) {
	    		var s1 = '';
	            $.map(data, function(item) {
					s1 += '<div data-options="name:' + item.cdval + '">'
					+ item.cdcontent + '</div>';
				});
	        	$('#mm_brand').html(s1);
	        	callback(true);
	       
    		}
    	});
	}
	
	
	
});