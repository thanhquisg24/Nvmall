$(function(){
	exe_load_header(function(){	
		var key_search = getUrlParameter('key');		
		if(key_search==null || key_search=="" || key_search == undefined){
			//window.location.href=ReturnHosing_apache() + 'page_not_found.html';
			alert(key_search);
		}else{
			render_breadcrumb(key_search);
			render_search_filter(key_search);
		}
	
	});	
	
	
	function render_breadcrumb(key_search){
		var breadcrumb=$.get(ReturnHosing_apache()+'container/search_product/breadcrumb.html',{}, null,'text');
		$.when(breadcrumb).done(function(text){
			var t=text.replace("@key",key_search);
			$("#contain_menu").html(t);
		});
	}
	function render_search_filter(key_search){
		
	}
});