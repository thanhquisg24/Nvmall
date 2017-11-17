$(function(){
	var title_="Color Manager"
		$('title').text(title_);
	
	$("#tt").tabs({
		onSelect:function(title,index){
			//$("#idcurrent").val(title);
		},
		onClose:function(title,index){
			//$("#subtypeid_"+title).remove();
		}
	});
	
	$('#iddatagrip').datagrid({
		toolbar: '#tbar',
	    url: extras_Hosting["tomcatSpring_context"]+'ad/colorController/get_json_append_to_datagrip.json',
	    //width:600,
	   // height:300,
	   // fitColumns:true,
	    view:groupview,
        groupField:'product_type_name',
        groupFormatter:function(value,rows){
            return value + ' - ' + rows.length + ' Item(s)';
        },
	    idField:'id',
	    multiSort:true,
	    remoteSort:false,
	    columns:[[
	        {field:'ck',checkbox:true},
	        {field:'id',title:'Code',width:170,halign:'center',sortable:true},
	        {field:'product_type_sub_id',title:'Type ID',width:80,align:'left',halign:'center',sortable:true},
	        {field:'product_type_name',title:'Productype name',width:170,align:'left',halign:'center',sortable:true},
	        {field:'color_name',title:'Color name',width:120,align:'left',halign:'center',sortable:true},
	        {field:'color',title:'Color code',width:120,align:'left',halign:'center',sortable:true,formatter:extras_format_color_datagrid},
	        {field:'img',title:'Image',width:180,align:'left',halign:'center',sortable:true,formatter:extras_formatImg_datagrid_color},
	        {field:'isvisible',title:'Is visibled',width:80,align:'center',halign:'center',sortable:true,formatter:extras_formatstatus_datagrid},
	        {field:'type',title:'Type set',width:80,align:'left',halign:'center',sortable:true,formatter:extras_format_type_color}
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
		var pdata={'pcdtype':'COLOR','pcdname':'SEARCH'};
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
		  $('#idchooseproductype').textbox('setValue','');
		  $('#tt').tabs('select',1);
	});
	$("#idedit").click(function(){
		//alert(get_single_row_select(0));
		var id=get_single_row_select();
		if(id==""){
			$.messager.alert('Warning','Select row please!');
			return;
		}
		 var checkedItems = $('#iddatagrip').datagrid('getChecked');
		 var productypeid=checkedItems[0].product_type_sub_id;
		  $('#idchooseproductype').textbox('setValue',productypeid);
		  $('#tt').tabs('select',1);
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
		        extras_POST_json(true,"ad/colorController","delete_multi_color",{'str_id':ids},function(data){
		        	alert(ids);
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
				$.messager.alert('Warning','Select customer please!');
				return;
		}
		 $.messager.confirm('Confirm',alert_,function(r){
			    if (r){
			        extras_POST_json(true,"ad/colorController","visivled_color",{'str_id':ids,'visible':status_},function(data){
			        	$.messager.alert('Result',data.message);
			        	if(data.f=="0"){
							$('#iddatagrip').datagrid('clearChecked');
							$('#iddatagrip').datagrid('reload');
			        	}
			        });

			    }
			});
	}
	

	function create_panel(tabtitle){
		var stab=$('#tt').tabs('getTab',tabtitle);
		  if(stab==null){
			  $.get(extras_Hosting["apache"] +"admin/views/Color/add_edittabs.html",function(data){
					if(data!=null){
						//data=data.replace(/@id/g,id);
						$('#tt').tabs('add',{
					            title:tabtitle,
					            content: '<div style="padding:10px">'+data+'</div>',
					            closable: true, 
					    });
					}
				});
		  }else{
			  $('#tt').tabs('select',tabtitle);
		  }
	};
	
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
