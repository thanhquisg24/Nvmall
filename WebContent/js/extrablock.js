function blockbg(){
	
	$.blockUI({
		   message: '<img src="image/loading.gif" style="width:50px;height:50px;" />',
		   css: { 
                border: 'none', 
                background: 'none' 
    			} 
	});
}
function unblockbg(){
	$.unblockUI();	
}