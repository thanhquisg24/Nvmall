$(function(){
	//alert("#33");
	var title_="";
	var ptype=getUrlParameter_VIA("type");
	if(ptype!="E"){//A insert, E update
		title_="ADD Demo CRUD";
		ptype="A";
	}else{
		title_="Edit Demo CRUD";
		$("#rowid").show();
		$("#id_id").show();
		var id=getUrlParameter_VIA("id");
		load_edit_demo(id);
	}
	$('title').text(title_);
	function load_edit_demo(){
		
		extras_GET_json(true,"ad/democrudController","get_democrud_by_id",{'id':id},
			function(data){ 
			$("#id_id").val(data.id);
			$("#idname").textbox('setValue',data.name);
			$("#idemail").textbox('setValue',data.email);
			$("#idaddress").textbox('setValue',data.address);
			$("#idphone").textbox('setValue',data.phone);
		});
			
	}
	
	$('#p').panel({
	    title:title_
	   
	}); 
	$("#idsubmit").click(function(){
		   $('#ff').submit();  
	});
	
	$('#ff').form({
	    url:extras_Hosting["tomcatSpring_context"]+'ad/democrudController/add_update_demo',
	    onSubmit:function(){
	    	$("#idptype").val(ptype);
	        return $(this).form('validate');
	    },
	    success:function(data){
	        $.messager.alert('Info', data, 'info');
	    }
	});
});