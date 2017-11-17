$(function(){
	var title_="Level Price Manager"
		$('title').text(title_);
	
	$("#tt").tabs({
		onSelect:function(title,index){
			//$("#idcurrent").val(title);
		},
		onClose:function(title,index){
			//$("#subtypeid_"+title).remove();
		}
	});
	
});
