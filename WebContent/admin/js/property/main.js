$(function(){
	var title_="Property Manager"
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
	    url: extras_Hosting["tomcatSpring_context"]+'ad/propertyController/get_json_append_to_datagrip.json',
	    height:700,
	    rownumbers:true,
	    autoRowHeight:true,
	    view: detailview,
        detailFormatter:function(index,row){
            return '<div style="padding:2px"><table class="ddv"></table></div>';
        },
        rowStyler: function(index,row){
                return 'background-color:#6293BB;color:#fff;font-weight:bold;cursor:pointer';
        },
	    idField:'product_type_sub_id',
	    multiSort:true,
	    remoteSort:false,
	    columns:[[
	        {field:'ck',checkbox:true},
	        {field:'product_type_sub_id',title:'Product type sub#',width:250,halign:'center',sortable:true},
	        {field:'tb_product_type_sub_0_product_type_name',title:'Product type Name',width:360,align:'left',halign:'center',sortable:true}
	    ]],
	    pagination:true,
	    onExpandRow: function(index,row){
	        var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
	        subgrid_1(ddv,row.product_type_sub_id,index);
            $('#iddatagrip').datagrid('fixDetailRowHeight',index);
	    },
	    onDblClickRow:	 function(index,row){
	    	var productype=row.product_type_sub_id;
	     	$('#idchooseproductype').textbox('setValue',productype);
  	    	$('#cc_propertyparent').combogrid('setValue','');
  	    	$('#tt').tabs('select',1);
	    }
	});
	function subgrid_1(ddv,product_type_sub_id,indexdatagrid){
		  ddv.datagrid({
              url:extras_Hosting["tomcatSpring_context"]+'ad/propertyController/get_property_parent_byproducttypeid.json?product_type_id='+product_type_sub_id,
              fitColumns:true,
              autoRowHeight:true,
              multiSort:true,
              remoteSort:false,
              idField:'id',
              loadMsg:'',
              height:'auto',
              view: detailview,
              detailFormatter:function(index,row){
                  return '<div style="padding:2px"><table class="ddv2"></table></div>';
              }, 
              rowStyler: function(index,row){
                  return 'background-color:#98b2e6;color:#fff;font-weight:bold;cursor:pointer';
              },
              columns:[[
                   {field:'ck',checkbox:true},
                  {field:'id',title:'Parent property#',width:150,align:'left',halign:'center',sortable:true},
                  {field:'property_name',title:'Parent property name',width:250,align:'left',halign:'center',sortable:true},
                  {field:'isvisible',title:'Is visibled',width:100,align:'center',halign:'center',sortable:true,formatter:extras_formatstatus_datagrid}
              ]],
              onResize:function(){
                 $('#iddatagrip').datagrid('fixDetailRowHeight',indexdatagrid);
                
              },
              onLoadSuccess:function(){
                  setTimeout(function(){
                      $('#iddatagrip').datagrid('fixDetailRowHeight',indexdatagrid);
                  },0);
              },
              onCollapseRow:function(index,row){
            
            	  //$('#iddatagrip').datagrid('fixRowHeight',indexdatagrid);
            	 // $('#iddatagrip').datagrid('fixDetailRowHeight',indexdatagrid);
            	  var rowdetail = $('#iddatagrip').datagrid('getRowDetail',indexdatagrid).closest('tr');
            	  $(rowdetail).css('vertical-align','top');
            	 /* alert("on");
            	  $('#iddatagrip').datagrid('fixDetailRowHeight',indexdatagrid);
            	  $('#iddatagrip').datagrid('fixRowHeight',indexdatagrid);*/
            	  /*var rowcollapse=$(ddv).datagrid('getRowDetail',index).closest('tr');
            	  var rowcollapse_height=$(rowcollapse).height();
            	  var rowdetail = $('#iddatagrip').datagrid('getRowDetail',indexdatagrid).closest('tr');
            	  var rowdetail_height=$(rowdetail).height();
            	  var sub_heght=rowdetail_height-rowcollapse_height;
            	  alert(sub_heght);
            	  $(rowdetail).height(sub_heght);
            	  $('#iddatagrip').datagrid('fixDetailRowHeight',indexdatagrid);*/
              },
              onExpandRow: function(index,row){
      	        var ddv2 = $(ddv).datagrid('getRowDetail',index).find('table.ddv2');
      	        	subgrid_2(ddv,ddv2,row.id,index,indexdatagrid);
                  $(ddv).datagrid('fixDetailRowHeight',indexdatagrid);
      	    	},
      	    onDblClickRow:	 function(index,row){
      	    	var rows = $('#iddatagrip').datagrid('getRows'); 
      	    	var row_iddatagrip = rows[indexdatagrid]; 
      	    	var productype=row_iddatagrip.product_type_sub_id;
      	    	var parentproperty=row.id;
      	    	$('#idchooseproductype').textbox('setValue',productype);
      	    	$('#cc_propertyparent').combogrid('setValue',parentproperty);
      	    	$('#tt').tabs('select',1);
      	    }
          });
	}
	function subgrid_2(ddv,ddv2,parentid,index_subgrid1,indexdatagrid){
		 var url_property_sub_context= extras_Hosting["tomcatSpring_context"]+'ad/propertyController/get_datagrip_byparentid.json?parentid='+parentid;
		  ddv2.datagrid({
              url:url_property_sub_context,
              pagination:true,
              multiSort:true,
              remoteSort:false,
              idField:'id',
              autoRowHeight:true,
              loadMsg:'',
              height:'auto',
              columns:[[
                   {field:'ck',checkbox:true},
                  {field:'id',title:'Property#',width:150,align:'left',halign:'center',sortable:true},
                  {field:'property_name',title:'Property name',width:250,align:'left',halign:'center',sortable:true},
                  {field:'isvisible',title:'Is visibled',width:100,align:'center',halign:'center',sortable:true,formatter:extras_formatstatus_datagrid}
              ]],
              onResize:function(){
                  $(ddv).datagrid('fixDetailRowHeight',index_subgrid1);
               
              },
              onLoadSuccess:function(){
            	  
                  setTimeout(function(){
                	  $('#iddatagrip').datagrid('fixDetailRowHeight',indexdatagrid);
                      $(ddv).datagrid('fixDetailRowHeight',index_subgrid1);
                  },0);
              }
          
          });
	}
	
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
		var pdata={'pcdtype':'PROPERTY','pcdname':'SEARCH'};
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
		  $('#cc_propertyparent').combogrid('setValue','');
		  $('#tt').tabs('select',1);
	});
	$("#idedit").click(function(){
		$.messager.alert('Warning','Double click Row Product type or Row Parent Property to edit!','warning');
		return;
		//alert(get_single_row_select(0));
		/*var id=get_single_row_select();
		if(id==""){
			$.messager.alert('Warning','Select row Product type or row Parent Property please!');
			return;
		}
		 var checkedItems = $('#iddatagrip').datagrid('getChecked');
		 var productypeid=checkedItems[0].product_type_sub_id;
		  //$('#idchooseproductype').textbox('setValue',productypeid);
		 alert(JSON.stringify(checkedItems[0]));*/
		  
		  //$('#tt').tabs('select',1);
	});
	$("#idremove").click(function(){
		//extras_viahref("add_demo_crud");
		var ids=get_multi_row_select();
		if(ids.length==0){
				$.messager.alert('Warning','Select row please!');
				return;
		}
		$.messager.confirm('Confirm','Are you sure you want to delete records?',function(r){
		    if (r){
		     	var productype=ids.productype;
		    	var parentid=ids.parentid;
		    	var propertyids=ids.propertyids;
		    	var pdata={'strproductype':productype,'strparentid':parentid,'strpropertyids':propertyids}
		        extras_POST_json(true,"ad/propertyController","delete_multi_property",pdata,function(data){
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
			 alert_="Are you sure you want to visibled ?"
			 
		 }else{
			 status_=0;
			 alert_="Are you sure you want to unvisibled ?"
		 }
		var ids=get_multi_row_select();
		if(ids.length==0){
				$.messager.alert('Warning','Select row please!');
				return;
		}
		//alert(JSON.stringify(ids));
	
		 $.messager.confirm('Confirm',alert_,function(r){
			    if (r){
			    	var productype=ids.productype;
			    	var parentid=ids.parentid;
			    	var propertyids=ids.propertyids;
			    	var pdata={'strproductype':productype,'strparentid':parentid,'strpropertyids':propertyids,'visible':status_}
			        extras_POST_json(true,"ad/propertyController","visivled_property_3option",pdata,function(data){
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
	function _get_multi_row_select(){
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
	
	function get_multi_row_select(){
		var selected={};	
			selected.productype='';	
			selected.parentid='';	
			selected.propertyids='';	
		 var checkedItems = $('#iddatagrip').datagrid('getChecked');
		 if(checkedItems.length>0){
			 var productype_s = [];
			 var parentid_s=[];
			 var propertyids_s=[];
		        $.each(checkedItems, function(index, item){
		        	productype_s.push("'"+item.product_type_sub_id+"'");
		        	var index=$('#iddatagrip').datagrid('getRowIndex',item);
		        	var expander = $('#iddatagrip').datagrid('getExpander', index);
		        	if(expander.hasClass('datagrid-row-collapse')){
		        		 var grid_parent_property = $('#iddatagrip').datagrid('getRowDetail',index).find('table.ddv');
		        		 var parent_propertycheckedItems = $(grid_parent_property).datagrid('getChecked');
		        		 if(parent_propertycheckedItems.length>0){
		        			 for(var i=0;i<parent_propertycheckedItems.length;i++){
		        				 parentid_s.push("'"+parent_propertycheckedItems[i].id+"'");
		        				 var index_grid_parent=$(grid_parent_property).datagrid('getRowIndex',parent_propertycheckedItems[i]);
		     		        	 var expander_grid_parent = $(grid_parent_property).datagrid('getExpander', index_grid_parent);
		     		        	if(expander_grid_parent.hasClass('datagrid-row-collapse')){
		     		        		 var grid_property = $(grid_parent_property).datagrid('getRowDetail',index_grid_parent).find('table.ddv2');
		    		        		 var propertycheckedItems = $(grid_property).datagrid('getChecked');
		    		        		 if(propertycheckedItems.length>0){
		    		        			 for(var j=0;j<propertycheckedItems.length;j++){
		    		        				 propertyids_s.push("'"+propertycheckedItems[j].id+"'");
		    		        			 }		    		        			 
		    		        		 }//end if propertycheckedItems.length
		     		        	}//end if expant grid_parent
		        			 }//end for parent_propertycheckedItems
		        		 }//end if parent_propertycheckedItems.length>0
		        	}//end if expant iddatagrip
		        });                
		        selected.productype= productype_s.join(",");
		       
		        if(parentid_s.length>0){
		        	selected.parentid=parentid_s.join(",");
		        }
		        if(propertyids_s.length>0){
		        	selected.propertyids=propertyids_s.join(",");
		        }
		        return selected;
		 }else{
			 return []; 
		 }
	}
	function get_checked_0(selectordatagrid){
		 var checkedItems = $(selectordatagrid).datagrid('getChecked');
		 if(checkedItems.length>0){
			 var ids=[];
			    $.each(checkedItems, function(index, item){
			    	ids.push("'"+item.id+"'");
			    });
			    return ids.join(",");
		 }
		 return ""; 
	}
});
