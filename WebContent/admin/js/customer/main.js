$(function(){
	var title_="Customer Manager"
		$('title').text(title_);
	
	$('#p').panel({
	    title:title_
	}); 
	$('#iddatagrip').datagrid({
		toolbar: '#tbar',
	    url: extras_Hosting["tomcatSpring_context"]+'ad/CustomerController/get_json_append_to_datagrip.json',
	    //width:600,
	   // height:300,
	   // fitColumns:true,
	    idField:'id',
	    remoteSort:false,
	    singleSelect:true,
	    columns:[[
	        {field:'ck',checkbox:true},
	        {field:'id',title:'Code',width:150,halign:'center',sortable:true},
	        {field:'email',title:'Email',width:180,align:'left',halign:'center',sortable:true},
	        {field:'shop_name',title:'Shop Name',width:180,align:'left',halign:'center',sortable:true},
	        {field:'shop_url',title:'Shop URL',width:180,align:'left',halign:'center',sortable:true},
	        {field:'db_name',title:'Database Name',width:180,align:'left',halign:'center',sortable:true},
	        {field:'server_id',title:'Server ID#',width:100,align:'right',halign:'center',sortable:true},
	        {field:'domain',title:'Domain',width:180,align:'left',halign:'center',sortable:true},
	        {field:'url_admin',title:'Admin URL',width:180,align:'left',halign:'center',sortable:true},
	        {field:'fn_signin',title:'Funtion signin',width:410,align:'left',halign:'center',sortable:true},
	        {field:'fn_signin_c',title:'Funtion signin C',width:180,align:'left',halign:'center',sortable:true},
	        {field:'project_name',title:'Project Name',width:180,align:'left',halign:'center',sortable:true},
	        {field:'filepdf',title:'File PDF',width:410,align:'left',halign:'center',sortable:true},
	        {field:'pass_forget',title:'pass_forget',width:100,align:'left',halign:'center',sortable:true},
	        {field:'isvisible',title:'Is visibled',width:100,align:'center',halign:'center',sortable:true,formatter:extras_formatstatus_datagrid},
	        {field:'isconfirm',title:'Is Comfirm',width:100,align:'center',halign:'center',sortable:true,formatter:extras_formatstatus_datagrid},
	        {field:'isapprove',title:'Is Approve',width:100,align:'center',halign:'center',sortable:true,formatter:extras_formatstatus_datagrid},
	        {field:'confirm_forget',title:'confirm_forget',width:100,align:'center',halign:'center',sortable:true,formatter:extras_formatstatus_datagrid}
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
		var pdata={'pcdtype':'CUSTOMER','pcdname':'SEARCH'};
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
	$("#idapprove").click(function(){
		 var checkedItems = $('#iddatagrip').datagrid('getChecked');
		 var email=checkedItems[0].email;
		 if(checkedItems.length>0){
			 var id=checkedItems[0].id;
			 var pdffile=checkedItems[0].filepdf;
			 if(pdffile!=null){
				  $('#dlg').dialog({
			            autoOpen: false,
			            title: 'Cofirm',
			            width: 800,
			            height: 600,
			            buttons: [{
			                text: 'OK',
			                iconCls: 'icon-ok',
			                handler: function() {
			                	 approve_and_unapprove(function(o,data){
			 						if(o){
			 						 
			 							$('#iddatagrip').datagrid('clearChecked');
			 							$('#iddatagrip').datagrid('reload');
			 							$("#dlg").dialog('close');
			 						 	$.messager.alert('Result',data.message);
			 						}
			 					},true,email);
			                }
			            }, {
			                text: 'Cancel',
			                iconCls: 'icon-cancel',
			                handler: function() {
			                    $("#dlg").dialog('close');
			                }
			            }]
			        });
				  PDFObject.embed(extras_Hosting["pdfcustomer"]+""+pdffile, "#vpdf");
				  $('#dlg').dialog('open');
			 }else{
				 approve_and_unapprove(function(o,data){
						if(o){
						  
							$('#iddatagrip').datagrid('clearChecked');
							$('#iddatagrip').datagrid('reload');
							$.messager.alert('Result',data.message);
						}
					},true,email);
			 }
			
		 }//end if checkedItems length	
 
	
	});
	$("#idunapprove").click(function(){
		 var checkedItems = $('#iddatagrip').datagrid('getChecked');
		 var email=checkedItems[0].email;
		approve_and_unapprove(function(o,data){
			if(o){
			  	$.messager.alert('Result',data.message);
				$('#iddatagrip').datagrid('clearChecked');
				$('#iddatagrip').datagrid('reload');
			}
		},false,email);
	});
	
	function approve_and_unapprove(callback,isapprove,email){
		 var status_=0;
		 var alert_="";
		 if(isapprove){
			 status_=1;
			 alert_="Are you sure you want to approve this customer?"
			 
		 }else{
			 status_=0;
			 alert_="Are you sure you want to unapprove this custome?"
		 }
		var ids=get_single_row_select();
		if(ids==""){
				$.messager.alert('Warning','Select customer please!');
				return;
		}
		 
		 $.messager.confirm('Confirm',alert_,function(r){
			    if (r){
			        extras_POST_json(true,"ad/CustomerController","do_approve",{'str_id':ids,'approve':status_,'email':email},function(data){
			        	//$.messager.alert('Result',data.message);
			        	if(data.f=="0"){
			        		callback(true,data);
			        	}
			        });

			    }
			});
	}
	
	 $("#btnvisible").click(function() {
		 visibled_and_unvisibled(true);
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
		var ids=get_single_row_select();
		if(ids==""){
				$.messager.alert('Warning','Select customer please!');
				return;
		}
		 $.messager.confirm('Confirm',alert_,function(r){
			    if (r){
			        extras_POST_json(true,"ad/CustomerController","do_visible",{'str_id':ids,'visible':status_},function(data){
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
