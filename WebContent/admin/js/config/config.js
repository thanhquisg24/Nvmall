$(function(){
	var title_="Config - publish"
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
	$('#dg_publish').datagrid({
		toolbar: '#tbar',
	    url: extras_Hosting["tomcatSpring_context"]+'ad/ConfigController/get_all_config',
	    method:'GET',
	    height:300,
	    rownumbers:true,
	    autoRowHeight:true,
	    idField:'id',
	    multiSort:true,
	    remoteSort:false,
	    striped:true,
	    singleSelect:true,
	    columns:[[
	        {field:'id',title:'ID#',width:80,halign:'center'},
	        {field:'name',title:'Name ',width:180,align:'left',halign:'center',editor:'textbox'},
	        {field:'isvisible',title:'Visibled ',width:80,align:'left',halign:'center',editor:{type:'checkbox',options:{on:'1',off:'0'}}}
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
          if ($('#dg_publish').datagrid('validateRow', editIndex)){
              $('#dg_publish').datagrid('endEdit', editIndex);
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
				  $('#dg_publish').datagrid('endEdit', editIndex);
		          var rows = $('#dg_publish').datagrid('getChanges');
		          alert(rows.length+' rows are changed!');
		          //alert(rows[0].id_a);
		      }
	  /////////////////////////////////
	  $("#idreject").click(function(){
		  reject();
	  });
			  function reject(){
		          $('#dg_publish').datagrid('rejectChanges');
		          editIndex = undefined;
		      }
	  //////////////////////////
	  $("#idaccept").click(function(){
		  accept();
	  });
			  function accept(){
		          if (endEditing()){
		        	  var rows = $('#dg_publish').datagrid('getChanges');
		        	  if(rows.length>0){
			        	  $.messager.confirm('Confirm','Are you sure you want to apply changes?',function(r){
			      		    if (r){
			      		    	var pdata={"json_config":JSON.stringify(rows)};
			      		    	//var url= extras_Hosting["tomcatSpring_context"]+"ad/locationController/delete_multi_location";
			      		    	 extras_POST_json(true,"ad/ConfigController","update_munti_config",pdata,function(data){
			      			        	$.messager.alert('Result',data.message,'info');
			      			        	if(data.f=="0"){
			      			    			$('#dg_publish').datagrid('acceptChanges');
			      			        	}
			      			        });     
			      		    	}
			      		});//end message config
		        	  }//if row
		          }//end if endeditting
		      }
	  //////////////////////////////////			  
});
