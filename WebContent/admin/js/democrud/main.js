$(function(){
	$('#p').panel({
	    title:'Demo CRUD'   
	}); 
	$('#iddatagrip').datagrid({
		toolbar: '#tbar',
	    url: extras_Hosting["tomcatSpring_context"]+'ad/democrudController/get_json_append_to_datagrip.json',
	    //width:600,
	   // height:300,
	   // fitColumns:true,
	    idField:'id',
	    //singleSelect:true,
	    columns:[[
	        {field:'ck',checkbox:true},
	        {field:'id',title:'Code',width:150},
	        {field:'name',title:'Name',width:100},
	        {field:'email',title:'Email',width:180,align:'right'},
	        {field:'address',title:'Adress',width:100},
	        {field:'phone',title:'Phone',width:100},
	        {field:'create_date',title:'Create date',width:100},
	        {field:'creator',title:'creator',width:100},
	        {field:'is_enabled',title:'is_enabled',width:100}
	    ]],
	    pagination:true
	});
	load_mm_selector(function(out){
		if(out==true){
			$('#ss').searchbox({
			    searcher:function(value,name){
			       // alert(value + "," + name);
			    	  $('#iddatagrip').datagrid('load',{
			    		  	col:name,
			    		  	val:value
			    	    });
			    },
			    menu:'#mm',
			    prompt:'Please Input Value'
			});

		}
	});
	function load_mm_selector(callback){
		$("#mm").html('');   	
		var pdata={'pcdtype':'DEMOCRUD','pcdname':'SEARCH'};
    	extras_GET_json(true,"ad/AllcodeController","get_allcode_searchbox.json",pdata,function(data){
    		if (data != null) {
	    		var s1 = '';
	            $.map(data, function(item) {
					s1 += '<div data-options="name:' + item.cdval + '">'
					+ item.cdcontent + '</div>';
				});
	        	$('#mm').html(s1);
	        	callback(true);
	       
    		}
    	});
	}
	
	$("#idadd").click(function(){
		extras_viahref("add_demo_crud");
	});
	$("#idedit").click(function(){
		//alert(get_single_row_select(0));
		var id=get_single_row_select();
		if(id==""){
			$.messager.alert('Warning','Select row please!');
			return;
		}
		extras_viahref("add_demo_crud?type=E&id="+id);
	});
	$("#idremove").click(function(){
		//extras_viahref("add_demo_crud");
		var ids=get_multi_row_select();
		if(ids==""){
			$.messager.alert('Warning','Select row please!');
			return;
		}
		$.messager.confirm('Confirm','Are you sure you want to delete record?',function(r){
		    if (r){
		        //alert('ok');
		        extras_POST_json(true,"ad/democrudController","detele_demo",{'str_id':ids},function(data){
		        	$.messager.alert('Result',data.message);
		        	if(data.f=="0"){
		        		$('#iddatagrip').datagrid('clearChecked');
		        		$('#iddatagrip').datagrid('reload');
		        	}
		        });
		        	
		        
		    }
		});
		
	});
	function get_single_row_select(){
		 var checkedItems = $('#iddatagrip').datagrid('getChecked');
		 if(checkedItems.length>0){
			 return checkedItems[0].id;
		 }
		return "";
	}
	function get_multi_row_select(){
		 var checkedItems = $('#iddatagrip').datagrid('getChecked');
		 if(checkedItems.length>0){
			 var ids = [];
		        $.each(checkedItems, function(index, item){
		        	ids.push("'"+item.id+"'");
		        });                
		   return ids.join(",");
		 }
		 return ""; 
	}
});
