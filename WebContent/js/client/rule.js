
$(function(){
	exe_load_header(function(){
		blockbg();
		extras_GET_json('RegulationController','get_regulation','',function(data){			
			$("#contain_page").html(data.content_page);
			unblockbg();
		});
	});	
});

