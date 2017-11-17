$(function(){
	$('#iddatagrip').datagrid({
		 onDblClickRow: function(index,row){
		    	$("#dialog_s").dialog('open');
		    	$('#iddatagrip_dialog_s').datagrid({
		    		url:extras_Hosting["tomcatSpring_context"]+'ad/orderController/get_json_detail_order.json?order_id='+row.order_id, 
		    	});
		    }
	});
	$('#dialog_s').dialog({
	    title: 'View Order detail',
	    width: 850,
	    height: 450,
	    closed: true,
	    cache: false,
	    modal: true,
	    buttons: [{
            text: 'OK',
            iconCls: 'icon-ok',
            handler: function() {
              alert("OK");
          	$('#iddatagrip').datagrid('clearChecked');
    		$('#iddatagrip').datagrid('reload');
            }
        }, {
            text: 'Cancel',
            iconCls: 'icon-cancel',
            handler: function() {
                $("#dialog_s").dialog('close');
            	$('#iddatagrip').datagrid('clearChecked');
        		$('#iddatagrip').datagrid('reload');
            }
        }]
    });
	$('#iddatagrip_dialog_s').datagrid({
		toolbar: '#tbar_dialog_s',
	    height:350,
	    idField:'product_id',
	    singleSelect:true,
	    remoteSort:false,
	    columns:[[
			   
			        {field:'order_id',title:'ID#',width:150,halign:'left',sortable:true},
			        {field:'customer_name',title:'Customer',width:150,align:'center',sortable:true},
			        {field:'product_name',title:'Product name',width:150,align:'center',sortable:true},
			        {field:'quantity',title:'Quantity',width:50,halign:'center',sortable:true},
			        {field:'price',title:'Price',width:100,halign:'center',sortable:true},
			        {field:'amount',title:'Total',width:150,halign:'center',sortable:true}
			    ]],
		view: detailview,
		detailFormatter: function(index, rows){
			        return '<div style="padding:10px;line-height:20px;'
			        +'background-color:#f0f0f5;"><span>Customer name : '+rows.customer_name+'</span><br>'
			        +'<span>Shop name : '+rows.shop_name+'</span><br>'
			        +'<span>Shop url : '+rows.shop_url+'</span><br>'
			        +'<span>Product ID : '+rows.product_id+'</span><br></div>';
			    },	    
	    pagination:true
	});
});
