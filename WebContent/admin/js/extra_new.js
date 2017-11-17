/**
 * http://usejsdoc.org/
 */

var extras_Hosting = [];
extras_Hosting["tomcat"] = 'http://'+window.location.hostname+':8080/Nvmall/';
extras_Hosting["tomcatSpring_context"] = 'http://'+window.location.hostname+':8080/Nvmall/spring/';
extras_Hosting["apache"] = 'http://'+window.location.hostname+':8080/Nvmall/';
extras_Hosting["service"] = 'http://'+window.location.hostname+':8080/Nvmall/';
extras_Hosting["pdfcustomer"]="http://localhost:8080/Svmall/upload/customer/pdf/";
extras_Hosting["path_img_editor"]='http://'+window.location.hostname+':8080/Nvmall/upload/editor/';
extras_Hosting["other_hosting"]='';
var extra_views= {
	    index: [{
            selector: "#main_body_content",
            templateUrl: 'admin/views/index_inner.html'
        }
    ],
    nganluong: [{
        selector: "#main_body_content",
        templateUrl: 'admin/views/Config/nganluong_inner.html'
    	}
    ],
    
    fee: [{
        selector: "#main_body_content",
        templateUrl: 'admin/views/Config/fee_inner.html'
    	}
    ],
    publish: [{
        selector: "#main_body_content",
        templateUrl: 'admin/views/Config/config_inner.html'
    	}
    ],
    scheme: [{
        selector: "#main_body_content",
        templateUrl: 'admin/views/Rule/scheme_inner.html'
    	}
    ],
    regulartion: [{
        selector: "#main_body_content",
        templateUrl: 'admin/views/Rule/regulartion_inner.html'
    	}
    ],
    
    Customermanager: [{
        selector: "#main_body_content",
        templateUrl: 'admin/views/Customer/manager_inner.html'
    	}
    ],
    Branch: [{
        selector: "#main_body_content",
        templateUrl: 'admin/views/Brand/manager_inner.html'
    	}
    ],
    Color: [{
        selector: "#main_body_content",
        templateUrl: 'admin/views/Color/manager_inner.html'
    	}
    ],
    
    producttypevmall: [{
        selector: "#main_body_content",
        templateUrl: 'admin/views/Catgory/producttypevmall_inner.html'
    	}
    ], 
    producttypesub: [{
        selector: "#main_body_content",
        templateUrl: 'admin/views/Catgory/producttypesub_inner.html'
    	}
    ],
    producttypesub_edit: [{
        selector: "#main_body_content",
        templateUrl: 'admin/views/Catgory/producttypesub_edit.html'
    	}
    ],
    producttypecustomer: [{
        selector: "#main_body_content",
        templateUrl: 'admin/views/Catgory/producttypecustomer_inner.html'
    	}
    ],
    newcategory: [{
        selector: "#main_body_content",
        templateUrl: 'admin/views/Catgory/new_category.html'
    	}
    ], 
    property: [{
        selector: "#main_body_content",
        templateUrl: 'admin/views/Property/manager_inner.html'
    	}
    ],

    productrecommended: [{
        selector: "#main_body_content",
        templateUrl: 'admin/views/ProductRecommended/manager_inner.html'
    	}
    ],
    checkorder: [{
        selector: "#main_body_content",
        templateUrl: 'admin/views/Order/manager_inner.html'
    	}
    ],

    levelprice: [{
        selector: "#main_body_content",
        templateUrl: 'admin/views/LevelPrice/manager_inner.html'
    	}
    ],
    
    E_producttypesub: [{
        selector: "#main_body_content",
        templateUrl: 'admin/views/E_producttypesub/producttypesub_inner.html'
    	}
    ],
    nation: [{
        selector: "#main_body_content",
        templateUrl: 'admin/views/Nation/manager_inner.html'
    	}
    ],
    location: [{
        selector: "#main_body_content",
        templateUrl: 'admin/views/Location/manager_inner.html'
    	}
    ],
    faq: [{
        selector: "#main_body_content",
        templateUrl: 'admin/views/FAQ/manager_inner.html'
    	}
    ],
    news: [{
        selector: "#main_body_content",
        templateUrl: 'admin/views/News/manager_inner.html'
    	}
    ],
    slidemanager: [{
        selector: "#main_body_content",
        templateUrl: 'admin/views/slide_inner.html'
    	}
    ],
    member: [{
        selector: "#main_body_content",
        templateUrl: 'admin/views/member_inner.html'
    	}
    ],  
    democrud: [{
        selector: "#main_body_content",
        templateUrl: 'admin/views/democrud_inner.html'
    	}
    ],
    add_demo_crud: [{
        selector: "#main_body_content",
        templateUrl: 'admin/views/add_demo_crud.html'
    	}
    ],
    about: [{
            selector: "#main_body_content",
            templateUrl: 'admin/views/about_inner.html'
        }
    ],
    contact: [{
            selector: "#main_body_content",
            templateUrl: 'admin/views/contact_inner.html'
        // }, {
        //     selector: "#content",
        //     templateUrl: 'views/contact-content.php'
        },
    ],
    testtab: [{
        selector: "#main_body_content",
        templateUrl: 'admin/views/Catgory/viewlevelprice_inner.html'
    // }, {
    //     selector: "#content",
    //     templateUrl: 'views/contact-content.php'
    },
    ],
    defaultView: {
        view: 'index'
    }
};



function extras_GET_json(isloading,controller,action,pdata,callback){
	var purl=extras_Hosting["tomcatSpring_context"]+""+controller+"/"+action;
	jQuery.ajax({
			global: isloading,
		    url: purl,
		    type: "GET",
		    dataType: "json",
		    data:pdata,
		    success: function(data) {
		        callback(data);
		     },
		    error: function(x, t, m) {
		    	console.log("GET "+purl +"error:" +m);
		    	callback("");
		    }
		});
}

function extras_POST_json(isloading,controller,action,pdata,callback){
	var purl=extras_Hosting["tomcatSpring_context"]+""+controller+"/"+action;
	jQuery.ajax({
			global: isloading,
		    url: purl,
		    type: "POST",
		    dataType: "json",
		    data:pdata,
		    success: function(data) {
		        callback(data);
		     },
		    error: function(x, t, m) {
		    	console.log("POST "+purl +"error:" +m);
		    	callback("");
		    }
		});
}

function extras_viahref(viahref){
	
	var atvihref=document.createAttribute("via-href");
	atvihref.value = viahref;  
	
	var  a = document.createElement("a");
		a.setAttributeNode(atvihref);
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);
}
function extras_rename_url_without_redirect(url){
	history.pushState({}, null, extras_Hosting["tomcat"]+"admin/index.html#"+url);
}
function extras_append_input_hidden(id,tabindex){
	 var attname=document.createAttribute("name");
	 attname.value = "subtypename_"+tabindex;  
	 var attid=document.createAttribute("id");
	  attid.value = "subtypeid_"+id;  
	  var attvalue=document.createAttribute("value");
	  attvalue.value = id;  
	  var input=document.createElement("input");
	  input.setAttributeNode(attid);	
	  input.setAttributeNode(attvalue);	
	  input.setAttributeNode(attname);	
	  input.type="hidden";
	  document.body.appendChild(input);

}

function load_view_teamplate_html(callback){
	var _view_location="views/";
	$.ajaxSetup({ 
		  global: false		
		});
	 $("[data-load]").each(function(){
	        $(this).load(_view_location+''+$(this).data("load"), function(){
	        	
	        }); 
	    });
	 /*$.ajaxSetup({ 
		  global: true		
		});*/
	 callback(true);
}


function formatcurrency(num){	
    var str = num.toString().replace("$", ""), parts = false, output = [], i = 1, formatted = null;
    if(str.indexOf(".") > 0) {
        parts = str.split(".");
        str = parts[0];
    }
    str = str.split("").reverse();
    for(var j = 0, len = str.length; j < len; j++) {
        if(str[j] != ",") {
            output.push(str[j]);
            if(i%3 == 0 && j < (len - 1)) {
                output.push(",");
            }
            i++;
        }
    }
    formatted = output.reverse().join("");
    return(formatted + ((parts) ? "." + parts[1].substr(0, 2) : ""));
};
function getUrlParameter(sParam)
{
	
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++) 
    {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam) 
        {
            return sParameterName[1];
        }
    }
}
function getUrlParameter_VIA(sParam)
{
	
    var sPageURL = window.location.hash.substring(1);   
    var sURLVariables = sPageURL.split('?');
    if(sURLVariables.length<2){
    	return "";
    }
    
    
    sURLVariables = sURLVariables[1].split('&');
    for (var i = 0; i < sURLVariables.length; i++) 
    {
    	
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam) 
        {
            return sParameterName[1];
        }
    }
}

function formatdate(dateObject) {
    var d = new Date(dateObject);
    var day = d.getDate();
    var month = d.getMonth() + 1;
    var year = d.getFullYear();
    if (day < 10) {
        day = "0" + day;
    }
    if (month < 10) {
        month = "0" + month;
    }
    var date = day + "/" + month + "/" + year;

    return date;
};


function extras_formatImg_datagrid(val,row,index){
	var urlimg=extras_Hosting["tomcat"]+'upload/category';
	var noimg=extras_Hosting["tomcat"]+'upload/no_image.jpg';
    if (val !=null && val!=""){
    	var img=urlimg+'/'+val;
        return '<img src="'+img+'" alt="'+img+'" style="  width: 100%;height:80px;" />';
    } else {
        return '<img src="'+noimg+'" alt="'+noimg+'" style="  width: 100%;height:80px;" />';
    }
}
function extras_formatstatus_datagrid(val,row,index){
	if(val=="0" || val=="false"){
		   return '<b style="color:red;">False</b>';
	}else if(val=="1" || val=="true"){
		 return '<b style="color:green;">True</b>';
	}else{
		 return '<b style="color:gray;">Unknow</b>';
	}
   
}
function extras_format_color_datagrid(val,row,index){
	
	return '<div style="width:100px;height:50px;background-color:'+val+'"></div>';
	
   
}
function extras_format_type_color(val,row,index){
	if(val=="1" || val=="false"){
		   return '<b style="color:red;">Color</b>';
	}else if(val=="2" || val=="true"){
		 return '<b style="color:green;">Image</b>';
	}else{
		 return '<b style="color:gray;">Unknow</b>';
	}
   
}
function extras_formatImg_datagrid_slide(val,row,index){
	var urlimg=extras_Hosting["tomcat"]+'upload/slide';
	var noimg=extras_Hosting["tomcat"]+'upload/no_image.jpg';
    if (val !=null && val!=""){
    	var img=urlimg+'/'+val;
        return '<img src="'+img+'" alt="'+img+'" style="  width: 100%;height:80px;" />';
    } else {
    	 return '<img src="'+noimg+'" style=" width: 100%;height:80px;" />';
    }
}
function extras_format_order_status(val,row,index){
	if(val=='1'){
		return '<b style="color:blue;">Processing</b>';
	}else if(val=='2'){
		return '<b style="color:green;">Logistic</b>';
	}else if(val=='3'){
		return '<b style="color:green;">Finish</b>';
	}else if(val=='0'){
		return '<b style="color:brown;">New</b>';
	}else if(val=='4'){
		return '<b style="color:green;">Delivered</b>';
	}else if(val=='-1'){
		return '<b style="color:red;">Cancel</b>';
	}
}

function extras_formatImg_datagrid_branch(val,row,index){
	var urlimg=extras_Hosting["tomcat"]+'upload/branch';
	var noimg=extras_Hosting["tomcat"]+'upload/no_image.jpg';
    if (val !=null && val!=""){
    	var img=urlimg+'/'+val;
        return '<img src="'+img+'" alt="'+img+'" style="  width: 100%;height:80px;" />';
    } else {
    	 return '<img src="'+noimg+'" style=" width: 100%;height:80px;" />';
    }
}
function extras_formatImg_datagrid_color(val,row,index){
	var urlimg=extras_Hosting["tomcat"]+'upload/color';
	var noimg=extras_Hosting["tomcat"]+'upload/no_image.jpg';
    if (val !=null && val!=""){
    	var img=urlimg+'/'+val;
        return '<img src="'+img+'" alt="'+img+'" style="  width: 100%;height:80px;" />';
    } else {
    	 return '<img src="'+noimg+'" style=" width: 100%;height:80px;" />';
    }
}
function extras_getnowdate_mysql(){
	var nowdate=new Date();
	return nowdate.getFullYear()+"-"+(nowdate.getMonth()+1)+"-"+nowdate.getDate();
}