$(function(){
	
	var path = ReturnHosing_tomcat_upload() + "upload/slide/";	
	 get_list_slider();
	 function get_list_slider() {
		 extras_GET_json("SlideController", "get_list_slide", '', function(data) {
	        	 $("#carousel-indicators").html('');
	        	 $("#content_slide").html('');
	            if (data != null) {                
	            	//alert(data[0].name);
	                for(var i=0;i<data.length;i++){
	                	var active="";
	                	if(i==0){
	                		active="active";
	                	}
	                	var li='<li data-target="#slide_main" data-slide-to="'+i+'" class="'+active+'"></li>';
	                	var devv='<div class="item '+active+'" style="position: relative;">';
	                	devv+='<img src="'+path+''+data[i].name+'" style="height:406px;width:100%">';
	                	if(data[i].list_sub.length>0){
	                		var strsub='<div style="position: absolute;top:10px;right:50px">';                			
	                		for(var j=0;j<data[i].list_sub.length;j++){	                			
	                			var itemsub = '<div class="box-image-slide"><img src="'+path+''+data[i].list_sub[j].name+'" style="height:170px;width:170px" /></div>';
	                			strsub+=itemsub;
	                		}
	                		strsub+='</div>';
	                		devv+=strsub;
	                	}	                		
	                	devv+='</div>';
	                	 $("#carousel-indicators").append(li);
	                	 $("#content_slide").append(devv);
	                }

	            }
	        });
	   }
});