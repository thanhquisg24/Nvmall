$(function(){
	exe_load_header(function(){
			$(".catalogies,.banner").hover(function() {
				$(".banner").show();
			// $( this ).append( $( "<span> ***</span>" ) );
			}, function() {
				$(".banner").hide();
				// $( this ).find( "span:last" ).remove();
			});
	});
	
	$.get("container/saleoff/col-left.html",function(data){
			$("#idsaleoff_container").find(".col-left").html(data);
	});
	$.get("container/saleoff/col-right.html",function(data){
		  $("#idsaleoff_container").find(".col-right").html(data);
	});
});