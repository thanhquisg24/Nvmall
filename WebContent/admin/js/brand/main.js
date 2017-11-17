$(function(){
	var title_="Brand Manager"
		$('title').text(title_);
	$("#tt").tabs({
		onSelect:function(title,index){
			//$("#idcurrent").val(title);
		},
		onClose:function(title,index){
			//$("#subtypeid_"+title).remove();
		}
	});
	$("#btnview").click(function(){
		var id=get_single_row_select();
		if(id==""){
			$.messager.alert('Warning','Select row please!','warning');
			return;
		}
	  var tabtitle =  $('#iddatagrip').datagrid('getSelected').product_type_name;
	  var stab=$('#tt').tabs('getTab',tabtitle);
		  if(stab==null){
				create_panel(id,tabtitle);
		  }else{
			  $('#tt').tabs('select',tabtitle);
		  }
	  $('#iddatagrip').datagrid('clearChecked');
	});
	function create_panel(id,tabtitle){
		$.get(extras_Hosting["apache"] +"admin/views/Catgory/viewbrand_inner.html",function(data){
			if(data!=null){
				data=data.replace(/@id/g,id);
				$('#tt').tabs('add',{
						id:'tab_'+id,
			            title:tabtitle,
			            content: '<div style="padding:5px">'+data+'</div>',
			            closable: true, 
			    });
			}
		});
	}
	$('#iddatagrip').datagrid({
		toolbar: '#tbar',
	    url: extras_Hosting["tomcatSpring_context"]+'ad/brandController/get_json_append_to_datagrip.json',
	    idField:'id',
	    multiSort:true,
	    remoteSort:false,
	    columns:[[
	        {field:'ck',checkbox:true},
	        {field:'id',title:'Code',width:150,halign:'center',sortable:true},
	        {field:'name',title:'Name',width:180,align:'left',halign:'center',sortable:true},
	        {field:'image',title:'Image',width:180,align:'left',halign:'center',sortable:true,formatter:extras_formatImg_datagrid_branch},
	        {field:'country',title:'Country',width:180,align:'left',halign:'center',sortable:true},
	        {field:'create_date',title:'Date Create',width:100,align:'center',halign:'center',sortable:true},
	        {field:'modify_date',title:'Date modify',width:100,align:'center',halign:'center',sortable:true},
	        {field:'create_user',title:'User create',width:100,align:'center',halign:'center',sortable:true},
	        {field:'modify_user',title:'User modify',width:100,align:'center',halign:'center',sortable:true},
	        {field:'isvisible',title:'Is visibled',width:100,align:'center',halign:'center',sortable:true,formatter:extras_formatstatus_datagrid}
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
		var pdata={'pcdtype':'BRANCH','pcdname':'SEARCH'};
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
		$('#dialog_brand').dialog('open').dialog('center').dialog('setTitle','New Brand');
		$('#fm_brand').form('clear');
		$('#frm_uploadimg').form('clear');
		$('#idfm_ptype').val('A');
		$('#idfm_brandid').val('0');
		$('#dotxtimage').filebox('setText','');
	     $("#img_brand").attr('src',extras_Hosting["tomcat"]+'upload/no_image.jpg');
	});
	
	$("#idedit").click(function(){
		var ids=get_single_row_select();
		if(ids==""){
			$.messager.alert('Warning','Select single row please!','warning');
			return;
		}
		 extras_GET_json(true,"ad/brandController","get_branch_by_id",{'id':ids},function(data){
				$('#dialog_brand').dialog('open').dialog('center').dialog('setTitle','Edit Brand '+data.id);
				$('#fm_brand').form('clear');
				$('#idfm_ptype').val('E');
				$('#idfm_brandid').val(data.id);
				$('#idfm_brandname').textbox('setValue',data.name);
				$('#idfm_brandcontry').textbox('setValue',data.country);
				$('#idfm_brandimg').val(data.image);
				$('#dotxtimage').filebox('setText','');
				 $("#img_brand").attr('src',extras_Hosting["tomcat"]+'upload/branch/'+data.image);
	        });
	});
	$("#idremove").click(function(){
		//extras_viahref("add_demo_crud");
		var ids=get_multi_row_select();
		if(ids==""){
			$.messager.alert('Warning','Select row please!','warning');
			return;
		}
		$.messager.confirm('Confirm','Are you sure you want to delete record?',function(r){
		    if (r){
		        //alert('ok');
		        extras_POST_json(true,"ad/brandController","delete_multi_brand",{'str_id':ids},function(data){
		        	$.messager.alert('Result',data.message);
		        	if(data.f=="0"){
		        		$('#iddatagrip').datagrid('clearChecked');
		        		$('#iddatagrip').datagrid('reload');
		        	}
		        });
		        	
		        
		    }
		});
		
	});
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
		var ids=get_multi_row_select();
		if(ids==""){
				$.messager.alert('Warning','Select row please!','warning');
				return;
		}
		 $.messager.confirm('Confirm',alert_,function(r){
			    if (r){
			        extras_POST_json(true,"ad/brandController","visivled_brand",{'str_id':ids,'visible':status_},function(data){
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
	function close_collapse(current_index){
		var allrow=$('#iddatagrip').datagrid('getRows');
		for(var i=0;i<allrow.length;i++){
			var index = $('#iddatagrip').datagrid('getRowIndex',allrow[i]);
			if(index==current_index){
				$('#iddatagrip').datagrid('expandRow', current_index);
				$('#iddatagrip').datagrid('selectRow', current_index);
			}else{
				$('#iddatagrip').datagrid('collapseRow',index);
			}
		}
	}
});
