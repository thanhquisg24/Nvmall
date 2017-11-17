$(function(){

	$('#dialog_level').dialog({
	    title: 'Choose Level',
	    width: 800,
	    height: 450,
	    closed: true,
	    cache: false,
	    modal: true,
	    buttons: [{
            text: 'OK',
            iconCls: 'icon-ok',
            handler: function() {
            var grid_selector=	$("#dialog_level").data('sgrid');
            var hidden_vmall_selector=	$("#dialog_level").data('svmall');
            var vmallid=$(hidden_vmall_selector).val();
	            var checkedItems = $('#dg_level').datagrid('getChecked');
	            if(checkedItems.length>0){
	            	var allitem_ofgrid_selector=$(grid_selector).datagrid('getData');
	            	//alert(JSON.stringify(allitem_ofgrid_selector));
	            	do_append_andcompare(checkedItems,allitem_ofgrid_selector.rows,vmallid,grid_selector);
	            	
	            }
	            else{
	            	  $.messager.alert('Warning','Please select row!!','warning');
	            	  return;
	            }
	            $("#dialog_level").dialog('close');
            }
        }, {
            text: 'Cancel',
            iconCls: 'icon-cancel',
            handler: function() {
                $("#dialog_level").dialog('close');
            }
        }]
    });

	$('#dg_level').datagrid({
		url: extras_Hosting["tomcatSpring_context"]+'ad/LevelPriceController/get_alllevel',
		toolbar: '#tbar',
	    idField:'id',
	    remoteSort:false,
	    method:'get',
	    columns:[[
	              {field:'ck',checkbox:true},
			        {field:'id',title:'LevelPrice ID#',width:100,halign:'center',align:'left',sortable:true},
			        {field:'name',title:'LevelPrice Name',width:150,halign:'center',align:'right',sortable:true},
			        {field:'pquery',title:'Query',width:300,halign:'center',align:'right',sortable:true}
			    ]]
	});
	
	
	function do_append_andcompare(checkedItems,rows,vmallid,grid_selector){
		var arr_=[];
		if(rows.length>0){
			for(var i=0;i<checkedItems.length;i++){
				if(bool_compare(checkedItems[i],rows)){
					arr_.push(checkedItems[i]);
				}
			}
			if(arr_.length==0){
				 $.messager.alert('Info','Append success!!','info');
				 return;
			}
		}else{
			arr_=checkedItems;
		}
		var pdata={'product_type_vmall':vmallid,'json_level':JSON.stringify(arr_)};	
		 extras_POST_json(true,"ad/LevelPriceController","do_append_level",pdata,function(data){
		        	$.messager.alert('Result',data.message);
		        	if(data.f=="0"){
		        		$('#dg_level').datagrid('clearChecked');
		        		$(grid_selector).datagrid('clearChecked');
		        		$(grid_selector).datagrid('reload');
		        	}
		        });
	}
	function bool_compare(item,rows){
		for(var i=0;i<rows.length;i++){
			if(item.id==rows[i].id){
				return false;
			}
		}
		return true;
	}
	
});