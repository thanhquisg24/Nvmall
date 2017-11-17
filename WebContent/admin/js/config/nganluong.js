$(function(){
	var title_="Config Ngan luong"
		$('title').text(title_);
	$("#tt").tabs({
		onSelect:function(title,index){
			//$("#idcurrent").val(title);
		},
		onClose:function(title,index){
			//$("#subtypeid_"+title).remove();
		}
	});
	var editIndex = undefined;
	$('#dg_nganluong').datagrid({
		toolbar: '#tbar',
	    url: extras_Hosting["tomcatSpring_context"]+'ad/ConfigController/get_config_url_nganluong',
	    method:'GET',
	    height:650,
	    rownumbers:true,
	    autoRowHeight:true,
	    idField:'id_a',
	    multiSort:true,
	    remoteSort:false,
	    striped:true,
	    singleSelect:true,
	    columns:[[
	        {field:'id_a',title:'ID#',width:80,halign:'center'},
	        {field:'cdtype',title:'Type',width:80,align:'left',halign:'center'},
	        {field:'cdname',title:'Name',width:80,align:'left',halign:'center'},
	        {field:'cdval',title:'Value',width:180,align:'left',halign:'center'},
	        {field:'cdcontent',title:'Content',width:'auto',align:'left',halign:'center',editor:'textbox'}
	    ]],
	    onClickCell: onClickCell,
	    onEndEdit: onEndEdit
 
	});
	 function onClickCell(index, field){
         if (editIndex != index){
             if (endEditing()){
            	 $(this).datagrid('selectRow', index)
                         .datagrid('beginEdit', index);
                 var ed = $(this).datagrid('getEditor', {index:index,field:field});
                 if (ed){
                     ($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
                 }
                 editIndex = index;
             } else {
                 setTimeout(function(){
                	 $(this).datagrid('selectRow', editIndex);
                 },0);
             }
         }
     }
	  function onEndEdit(index, row){
         /* var ed = $(this).datagrid('getEditor', {
              index: index,
              field: 'id_a'
          });
          row.productname = $(ed.target).combobox('getText');*/
      }
	  function endEditing(){
          if (editIndex == undefined){return true}
          if ($('#dg_nganluong').datagrid('validateRow', editIndex)){
              $('#dg_nganluong').datagrid('endEdit', editIndex);
              editIndex = undefined;
              return true;
          } else {
              return false;
          }
      }
	
	  
	  $("#idGetChanges").click(function(){
		  getChanges();
	  });
			  function getChanges(){
				  $('#dg_nganluong').datagrid('endEdit', editIndex);
		          var rows = $('#dg_nganluong').datagrid('getChanges');
		          alert(rows.length+' rows are changed!');
		          //alert(rows[0].id_a);
		      }
	  /////////////////////////////////
	  $("#idreject").click(function(){
		  reject();
	  });
			  function reject(){
		          $('#dg_nganluong').datagrid('rejectChanges');
		          editIndex = undefined;
		      }
	  //////////////////////////
	  $("#idaccept").click(function(){
		  accept();
	  });
			  function accept(){
		          if (endEditing()){
		        	  var rows = $('#dg_nganluong').datagrid('getChanges');
		        	  if(rows.length>0){
			        	  $.messager.confirm('Confirm','Are you sure you want to apply changes?',function(r){
			      		    if (r){
			      		    	var pdata={"json_nganluong_update":JSON.stringify(rows)};
			      		    	//var url= extras_Hosting["tomcatSpring_context"]+"ad/locationController/delete_multi_location";
			      		    	 extras_POST_json(true,"ad/ConfigController","update_munti_config_nganluong",pdata,function(data){
			      			        	$.messager.alert('Result',data.message,'info');
			      			        	if(data.f=="0"){
			      			    			$('#dg_nganluong').datagrid('acceptChanges');
			      			        	}
			      			        });     
			      		    	}
			      		});//end message config
		        	  }//if row
		          }//end if endeditting
		      }
	  //////////////////////////////////			  
});
