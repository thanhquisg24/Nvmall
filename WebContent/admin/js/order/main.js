$(function(){
	var title_="Order Manager"
		$('title').text(title_);
	
	$('#iddatagrip').datagrid({
		toolbar: '#tbar',
	    url: extras_Hosting["tomcatSpring_context"]+'ad/orderController/get_json_append_to_datagrip.json',
	    idField:'order_id',
	    multiSort:true,
	    remoteSort:false,
	    columns:[[
	        {field:'ck',checkbox:true},
	        {field:'order_id',title:'Order ID',width:180,halign:'center',sortable:true},
	        {field:'email',title:'Member email',width:150,align:'left',halign:'center',sortable:true},
	        {field:'payment_method_id',title:'Payment type',width:110,align:'left',halign:'center',sortable:true},
	        {field:'order_date',title:'Order date',width:150,align:'left',halign:'center',sortable:true},
	        {field:'order_status',title:'Status',width:80,align:'center',halign:'center',sortable:true,formatter:extras_format_order_status},
	        {field:'address_delivery',title:'Address',width:180,align:'center',halign:'center',sortable:true},
	        {field:'note',title:'Note',width:180,align:'center',halign:'center',sortable:true}
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
		var pdata={'pcdtype':'ORDER','pcdname':'SEARCH'};
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
	
	$("#btnfinish").click(function(){
		  var ids=get_multi_row_select();
		  var status = 3;
		  if(ids==""){
				$.messager.alert('Warning','Select order please!');
				return;
		  }
		  $.messager.confirm('Confirm','Are you sure to set to finish?',function(r){
			    if (r){
			        extras_POST_json(true,"ad/orderController","set_order_status",{'str_id':ids,'status':status},function(data){
			        	$.messager.alert('Result',data.message);
			        	if(data.f=="0"){
			        		$('#iddatagrip').datagrid('clearChecked');
			        		$('#iddatagrip').datagrid('reload');
			        	}
			        });  
			    }
			});
	});
	$("#btndelivered").click(function(){
		 var ids=get_multi_row_select();
		  var status = 4;
		  if(ids==""){
				$.messager.alert('Warning','Select order please!');
				return;
		  }
		  $.messager.confirm('Confirm','Are you sure to set to delivered ?',function(r){
			    if (r){
			        extras_POST_json(true,"ad/orderController","set_order_status",{'str_id':ids,'status':status},function(data){
			        	$.messager.alert('Result',data.message);
			        	if(data.f=="0"){
			        		$('#iddatagrip').datagrid('clearChecked');
			        		$('#iddatagrip').datagrid('reload');
			        	}
			        }); 			        
			    }
			});
	});
	$("#btnprocessing").click(function(){
		var ids=get_multi_row_select();
		  var status = 1;
		  if(ids==""){
				$.messager.alert('Warning','Select order please!');
				return;
		  }
		  $.messager.confirm('Confirm','Set to processing will send an confirm email to customer ?',function(r){
			    if (r){
			        extras_POST_json(true,"ad/orderController","set_order_status",{'str_id':ids,'status':status},function(data){
			        	$.messager.alert('Result',data.message);
			        	if(data.f=="0"){
			        		$('#iddatagrip').datagrid('clearChecked');
			        		$('#iddatagrip').datagrid('reload');
			        	}
			        }); 
			    }
		});
	});
	 $("#btncancel").click(function() {
		 var ids=get_multi_row_select();
		  var status = -1;
		  if(ids==""){
				$.messager.alert('Warning','Select order please!');
				return;
		  }
		  $.messager.confirm('Confirm','Are you sure to set to cancel ?',function(r){
			    if (r){
			        extras_POST_json(true,"ad/orderController","set_order_status",{'str_id':ids,'status':status},function(data){
			        	$.messager.alert('Result',data.message);
			        	if(data.f=="0"){
			        		$('#iddatagrip').datagrid('clearChecked');
			        		$('#iddatagrip').datagrid('reload');
			        	}
			        }); 
			    }
		});
	 });
	 $("#btnunvisible").click(function() {
		 visibled_and_unvisibled(false);
	 });
	
	function visibled_and_unvisibled(visibled){
		 var status_=0;
		 var alert_="";
		 if(visibled){
			 status_=1;
			 alert_="Are you sure you want to visibled this customer?"
			 
		 }else{
			 status_=0;
			 alert_="Are you sure you want to unvisibled this custome?"
		 }
		var ids=get_selected_type();
		if(ids==""){
				$.messager.alert('Warning','Select customer please!');
				return;
		}
		 $.messager.confirm('Confirm',alert_,function(r){
			    if (r){
			        extras_POST_json(true,"ad/proRecommendedController","visivled_proRecommended",{'str_id':ids,'visible':status_},function(data){
			        	$.messager.alert('Result',data.message);
			        	if(data.f=="0"){
							$('#iddatagrip').datagrid('clearChecked');
							$('#iddatagrip').datagrid('reload');
			        	}
			        });

			    }
			});
	}
	
	function get_single_row_select(){
		 var checkedItems = $('#iddatagrip').datagrid('getChecked');
		 if(checkedItems.length>0){
			 return checkedItems[0].product_type_id;
		 }
		return "";
	}
	function get_multi_row_select(){
		 var checkedItems = $('#iddatagrip').datagrid('getChecked');
		 if(checkedItems.length>0){
			 var ids = [];
		        $.each(checkedItems, function(index, item){
		        	ids.push("'"+item.order_id+"'");
		        });                
		   return ids.join(",");
		 }
		 return ""; 
	}
	function get_selected_type(){
		 var checkedItems = $('#iddatagrip').datagrid('getChecked');
		 if(checkedItems.length>0){
			 return checkedItems[0].customer_id+"_"+checkedItems[0].product_id;
		 }
		return "";
	}
});
