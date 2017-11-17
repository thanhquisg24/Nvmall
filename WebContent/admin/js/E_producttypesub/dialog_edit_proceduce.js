$(function(){
	$('#dialog_function').dialog({
	    title: 'Function',
	    width: 400,
	    closed: true,
	    cache: false,
	    modal: true,
	    buttons: [{
            text: 'Save',
            iconCls: 'icon-save',
            handler: function() {
            	var url = extras_Hosting["tomcatSpring_context"]
				+'ad/ad_Eproductypesub/save_function_name';
            	$('#fm_function').form('submit',{
            		url: url,
            		onSubmit: function(){
            			return $(this).form('validate');
            		},
            		success: function(result){
            			var data=JSON.parse(result);
            	        $.messager.alert('Info', data.message, 'info');
            	        if(data.f=="0"){
            	        	 var sgrid=$('#dialog_function').data('sgrid');
            	        	  $("#dialog_function").dialog('close');
            	        	  $(sgrid).datagrid('clearChecked');
     		        		  $(sgrid).datagrid('reload');
            	        }
            		}
            	});
            }
        }, {
            text: 'Cancel',
            iconCls: 'icon-cancel',
            handler: function() {
                $("#dialog_function").dialog('close');
            }
        }]
    });
	
});