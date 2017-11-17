$(function(){
	//alert("#33");
	
	$('#p').panel({
	    title:'My Panel',
	    tools:[{
	        iconCls:'icon-add',
	        handler:function(){alert('new')}
	    },{
	        iconCls:'icon-save',
	        handler:function(){alert('save')}
	    }]
	}); 
	
	/*$('#dg').datagrid({
	    url:'admin/datagrid_data.json',
	    columns:[[
	        {field:'code',title:'Code',width:100},
	        {field:'name',title:'Name',width:100},
	        {field:'price',title:'Price',width:100,align:'right'}
	    ]]
	});
	*/
});
