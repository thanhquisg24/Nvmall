$(function(){
	var title_="Config Fee"
		$('title').text(title_);
	$("#tt").tabs({
		onSelect:function(title,index){
			//$("#idcurrent").val(title);
		},
		onClose:function(title,index){
			//$("#subtypeid_"+title).remove();
		}
	});
	var check_required=true;
	var editIndex = undefined;
	$('#dg_fee').datagrid({
		toolbar: '#tbar',
	    url: extras_Hosting["tomcatSpring_context"]+'ad/ConfigController/get_all_fee',
	    method:'GET',
	    height:650,
	    rownumbers:true,
	    autoRowHeight:true,
	    idField:'id_fee',
	    multiSort:true,
	    remoteSort:false,
	    striped:true,
	    singleSelect:true,
	    columns:[[
	        {field:'id_fee',title:'ID#',width:80,halign:'center'},
	        {field:'fee_name',title:'Fee Name',width:160,align:'left',halign:'center',/*editor:'textbox',*/
	        	editor:{type:'textbox',options:{required:true}}},
	        {field:'fee',title:'Fee',width:120,align:'right',halign:'center',
	        		editor:{type:'numberbox',options:{ min:0,value:0}}},
	        {field:'moption',title:'Moption',width:120,align:'right',halign:'center',
	        			editor:{type:'numberbox',options:{ min:0}}}
	    ]],
	    onBeforeSave:function(index){
	    	
	    },
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
	    /*	var fee_nameEditor = $(this).datagrid('getEditor',{index:index,field:'fee_name'});
	    	var feeEditor = $(this).datagrid('getEditor',{index:index,field:'fee'});
	    	var moptionEdittor= $(this).datagrid('getEditor',{index:index,field:'moption'});
	    	//var nameValue = $(nameEditor.target).val(); // get name field value
	    	//var countryValue = $(countryEditor.target).combobox('getValue'); // get country field value, notice that the editor type is combobox
	    	// do validation about name and country field
	    	var feenameval=$(fee_nameEditor.target).val();
	    	var feeval=$(feeEditor.target).val();
	    	var moptionval=$(moptionEdittor.target).val();
	    	//alert(feenameval +" "+ feeval +" " + moptionval);
	    	if(feenameval==""||feeval==""||moptionval==""){
	    		check_required= false; 
	    	}
	    	// valiate failure*/
      }
	  function endEditing(){
          if (editIndex == undefined){return true}
          if ($('#dg_fee').datagrid('validateRow', editIndex)){
              $('#dg_fee').datagrid('endEdit', editIndex);
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
				  $('#dg_fee').datagrid('endEdit', editIndex);
		          var rows = $('#dg_fee').datagrid('getChanges');
		          alert(rows.length+' rows are changed!');
		          //alert(rows[0].id_fee);
		      }
	  /////////////////////////////////
	  $("#idreject").click(function(){
		  reject();
	  });
			  function reject(){
		          $('#dg_fee').datagrid('rejectChanges');
		          editIndex = undefined;
		      }
	  //////////////////////////
	  $("#idaccept").click(function(){
		  accept();
	  });
			  function accept(){
		          if (endEditing()){
		        	  var rows = $('#dg_fee').datagrid('getChanges');
		          	  if(rows.length>0){
		          		  if(check_valib(rows)){
		          			  $.messager.confirm('Confirm','Are you sure you want to apply changes?',function(r){
		          				  if (r){
		          					  var pdata={"json_fee":JSON.stringify(rows)};
		          					  //var url= extras_Hosting["tomcatSpring_context"]+"ad/locationController/delete_multi_location";
		          					  extras_POST_json(true,"ad/ConfigController","update_munti_fee",pdata,function(data){
		          						  $.messager.alert('Result',data.message,'info');
		          						  if(data.f=="0"){
		          							  $('#dg_fee').datagrid('acceptChanges');
		          						  }
		          					  });     
		          				  }
		          			  });//end message config
		          		  }//end if valid
		          		  else{
		          			  $.messager.alert('Warning'," 'Fee name' or 'Fee' or 'Moption' null!!! ",'warning');
		          		  } 
		          	  }//end if rows
		          }//end if endeditting
		      }
			  function check_valib(rows){
				  for(var i=0;i<rows.length;i++){
					  var row=rows[i];
					  if(row.fee_name==""||row.fee==""|| row.moption==""){
						  return false;
					  }
				  }
				  return true;
			  }
	  //////////////////////////////////			  
});
