function ftp_server(){
	return "http://103.3.249.101/ImageStore/upload/";
}
var str_sell_off = '<div style="position: absolute;right:0;z-index:10;margin-right:15px"><img style="height:40px;width:50px;" src="image/sale-icon.png"></div>'
function get_host(type,index){
	if(type==1){//apache
		if(publish==false){
			return arr_domain[index].host_apache_local;
		}
		else{
			return arr_domain[index].host_apache_publish;	
		}		
	}
	else{ //tomcat
		if(publish==false){
			return arr_domain[index].host_tomcat_local;
		}
		else{
			return arr_domain[index].host_tomcat_publish;	
		}
	}
}
function ReturnHosing_tomcat_vivmall(){
	var url =get_host(2,0);
	return url;
}
function ReturnHosing_apache_vivmall(){
	var url =get_host(1,0);
	return url;
}
function ReturnHosing_apache_dvmall(){
	var port = location.port;	
	var url =get_host(1,1);
	return url;
}
function ReturnHosing_tomcat_dvmall(){
	var port = location.port;	
	var url =get_host(2,1);
	return url;
}

function ReturnHosing_apache_account(){	
	var url =get_host(1,2);
	return url;
}

function ReturnHosing_service_account(){
	var port = location.port;	
	var url =get_host(2,2);
	return url;
}
function ReturnHosing_tomcat_shoppingcart(){	
	var url =get_host(2,3);
	return url;
}
function ReturnHosing_apache_shoppingcart(){	
	var url =get_host(1,3);
	return url;
}
function ReturnHosing_tomcat_newsvmall(){
	var port = location.port;	
	var url =get_host(2,4);
	return url;
}
function ReturnHosing_apache_newsvmall(){
	var port = location.port;	
	var url =get_host(1,4);
	return url;
}
function ReturnHosing_tomcat_jobvmall(){
	var port = location.port;	
	var url =get_host(2,5);
	return url;
}
function ReturnHosing_apache_jobvmall(){
	var port = location.port;	
	var url =get_host(1,5);
	return url;
}
function ReturnHosing_tomcat(){
	var port = location.port;
	var url =get_host(2,0)+'spring/';
	return url;
}
function ReturnHosing_apache(){
	var port = location.port;	
	var url =get_host(1,0);
	return url;
}
function ReturnHosing_tomcat_upload(){
	var port = location.port;
	var url =get_host(2,0);
	return url;
}
function extras_GET_json(pcontroller,pmethod,pdata,callback){	
	_get_ajax(pcontroller,pmethod,pdata,'GET',callback);
}
function extras_POST_json(pcontroller,pmethod,pdata,callback){
	_post_ajax(pcontroller,pmethod,pdata,'POST',callback);
}
function _get_ajax(pcontroller,pmethod,pdata,ptype,callback){
	var purl = ReturnHosing_tomcat()+pcontroller+"/"+pmethod;	
	$.ajax({
		url : purl,
		headers : {
			'Accept' : 'application/json',
			'Content-Type': 'application/json'				
		},
		type:ptype,
		dataType : 'json',
		data:pdata,			
		success : function(data) {
			callback(data);
		},
		 error:function(data,status,er) { 
			 callback('');
		 }
	});
}
function _post_ajax(pcontroller,pmethod,pdata,ptype,callback){
	var purl = ReturnHosing_tomcat()+pcontroller+"/"+pmethod;	
	$.ajax({
		url: purl, 
	    type: ptype, 
	    dataType: 'json', 
	    data: pdata, 
	    contentType: 'application/json',
	    mimeType: 'application/json',
	    success: function(data) { 
	       callback(data);
	    },
	    error:function(data,status,er) { 
	      //  alert("Exception:"+er);
	    }
	});
}
function _ajax_json(url,fn){
	$.ajax({
		 url: url,
		 type: 'GET',
		 dataType: 'jsonp',
		 jsonp: 'callback',
		 crossDomain: true,			 
		 jsonpCallback: fn,			  
		 error: function (XMLHttpRequest, textStatus, errorThrown) {
		      alert(errorThrown);
		 }
	});
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
        var sParameterName = sURLVariables[i].split('$');        
        if (sParameterName[0] == sParam) 
        {
            return sParameterName[1];
        }
    }
}
function ReturnUrlHeader(){
	var url =ReturnHosing_apache()+'header.html';	
	return url;
}
function ReturnUrlFooter(){
	var url =ReturnHosing_apache()+'footer.html';	
	return url;
}
function ReturnUrlLeftHistory(){
	var url =ReturnHosing_apache()+'history/history.html';	
	return url;
}
function ReturnUrlSlide(){
	var url =ReturnHosing_apache()+'slideshow.html';	
	return url;
}
function get_url_header(){
	var mobile = ismobile();
	var ipad = isipad();
	var url = '';
	url = ReturnUrlHeader();
	return url;
}
function exe_load_header(callback){	
	blockbg();
	$("#header").load(get_url_header(), function (responseText, textStatus, XMLHttpRequest) {	
		
		$("#footer").load(ReturnUrlFooter(), function (responseText, textStatus, XMLHttpRequest) {	
			unblockbg();
			callback(true);	
			
		});
		
	});	
}
function exe_load_header_history(callback){	
	blockbg();
	$("#header").load(get_url_header(), function (responseText, textStatus, XMLHttpRequest) {	
		
		$("#footer").load(ReturnUrlFooter(), function (responseText, textStatus, XMLHttpRequest) {
			
			$("#left-his").load(ReturnUrlLeftHistory(), function (responseText, textStatus, XMLHttpRequest) {
				var time_menu = setInterval(function(){
					console.log(check_load_email);
					if(check_load_email==true){
						unblockbg();
						clearTimeout(time_menu);		
						callback(true);	
					}				
				},100);
			});
		});
		
	});	
}
function get_lang_current(){
	try{
		var lang  =$.session.get('curlange');
		if(lang==''|| lang==null || lang==undefined){
			lang = 'EN';
		}
		return lang;	
	}catch(ex){
		return 'EN';
	}
	
}
function change_lang(lang){
	$.session.set('curlange',lang);
	var url = window.location.href;
	window.location.href = url;
}
function set_menu_active(menu){
	$.session.set('menuactive',menu);
}


function fn_see_product(src){	
	
	var href =$(src).attr('opso_link');
	var arr = href.split('_');
	var id = arr[1];
	var action = $(src).attr('opso_slide');
	if(action=='down'){
		$(src).attr('opso_slide','up');
		$("#bottomsp_"+id).slideDown("slow");	
	}	
	else{
		$(src).attr('opso_slide','down');
		$("#bottomsp_"+id).slideUp("slow");
	}
}
function ValidateDate(dtValue)
{
	var check = false;	
	var rxDatePattern =/^(\d{1,2})(\/|-)(\d{1,2})(\/|-)(\d{4})$/;
	try
	{
		var dtArray = dtValue.match(rxDatePattern); 
	    if (dtArray == null) 
	        return false;
	    dtMonth = dtArray[3];
	    dtDay= dtArray[1];
	    dtYear = dtArray[5];        
	    
	    if (dtMonth < 1 || dtMonth > 12) 
	        return false;
	    else if (dtDay < 1 || dtDay> 31) 
	        return false;
	    else if ((dtMonth==4 || dtMonth==6 || dtMonth==9 || dtMonth==11) && dtDay ==31) 
	        return false;
	    else if (dtMonth == 2) 
	    {
	        var isleap = (dtYear % 4 == 0 && (dtYear % 100 != 0 || dtYear % 400 == 0));
	        if (dtDay> 29 || (dtDay ==29 && !isleap)) 
	                return false;
	    }
	    check = true;
	}
	catch(err)
	{
		check = false;
	}	
	return check;
}
function htmlUnescape(value){
    return String(value)
        .replace(/&quot;/g, '"')
        .replace(/&#39;/g, "'")
        .replace(/&lt;/g, '<')
        .replace(/&gt;/g, '>')
        .replace(/&amp;/g, '&');
}
function loadScript(callback,url) {
	 
    var script = document.createElement("script");
    script.type = "text/javascript";

    if (script.readyState) { //IE
        script.onreadystatechange = function () {
            if (script.readyState == "loaded" || script.readyState == "complete") {
                script.onreadystatechange = null;
               callback(true);
            }
        };
    } else { //Others
        script.onload = function () {
        	callback(true);
        };
    }	 
    script.src = url;
    document.getElementsByTagName("body")[0].appendChild(script);
}
function Dom_Get(pcontroller,pmethod,pdata,ptype,callback){
	switch(ptype.toUpperCase()){
		case 'GET':
			_getDom(pcontroller,pmethod,pdata,'GET',callback);
			break;
	}
}
function _getDom(pcontroller,pmethod,pdata,ptype,callback){
	var purl = ReturnHosing_tomcat()+pcontroller+"/"+pmethod+".htm?";	
	var i=0;
	$.map(pdata, function(value, key) {
		if(i==0){
			purl +=key+"="+value;	
		}
		else{
			purl +="&"+value+"="+key;
		}
		
		i++;
	});
	var rawFile = new XMLHttpRequest();
	 rawFile.open("GET", purl, false);
	 rawFile.onreadystatechange = function ()
	 {
	     if(rawFile.readyState === 4)
	     {
	        if(rawFile.status === 200 || rawFile.status == 0)
	         {
	             var text = rawFile.responseText;
	             callback(text);
	         }
	      }
	 };	
	 rawFile.send(null);
}

function loadScriptadv(url,callback) {
		 
	        var script = document.createElement("script");
	        script.type = "text/javascript";
	 
	        if (script.readyState) { //IE
	            script.onreadystatechange = function () {
	                if (script.readyState == "loaded" || script.readyState == "complete") {
	                    script.onreadystatechange = null;
	                   callback(true);
	                }
	            };
	        } else { //Others
	            script.onload = function () {
	            	callback(true);
	            };
	        }	 
	        script.src = url;
	        document.getElementsByTagName("body")[0].appendChild(script);
}
function ismobile(){
	var windowWidth = window.screen.width < window.outerWidth ?
                  window.screen.width : window.outerWidth;
	var mobile = windowWidth < 500 ?true : false;
	return mobile;
}
function isipad(){
	var windowWidth = window.screen.width < window.outerWidth ?
                  window.screen.width : window.outerWidth;
	if(windowWidth >500 && windowWidth < 1024){
		return true;
	}
	else{
		return false;
	}
}
function isPc(){
	if(ismobile() || isipad()){
		return false;
	}
	else{
		return true;
	}
}
function get_skype(){
	Skype.ui({
	      "name": "chat",
	      "element": "SkypeButton_Call_hnphung.2002_1",
	      "participants": ["hnphung.2002"],
	      "imageSize": 24
	});
	Skype.ui({
	      "name": "chat",
	      "element": "SkypeButton_Call_hyundai2611_1",
	      "participants": ["hyundai2611"],
	      "imageSize": 24
	    });
}
function load_skype(){
	$("#contain-skype").load(ReturnUrlSkype(), function (responseText, textStatus, XMLHttpRequest) {
		
		
	});	
}
function load_social(){
	$("#contain-social").load(ReturnUrlSocial(), function (responseText, textStatus, XMLHttpRequest) {
		
		
	});	
}
function hidden_content_page(){
	
	$("#idloading").css('display','none');
	$("#prevpage").css('display','none');
	$("#nextpage").css('display','none');
}
function fix_monitor(){
	var windowWidth = window.screen.width;
	if(windowWidth<=500){
		$(".fix-container").addClass('container');
	}
	else { 
		$(".fix-container").addClass('container-fluid');
	}
}
function removeEclass(callback){
	var windowWidth = window.screen.width;
	if(windowWidth<=992){//man hinh pho bien hien nay
		$(".cssmobile").remove();
	}
	else { // man hinh lon 1922
		$(".csspc").remove();

	}
	callback(true);
}
function get_width_monitor(){
	var windowWidth = window.screen.width;
	return windowWidth;
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
function append_loading(div){
	var str = '<div class="row" style="text-align:center">';
		str	+='<div class="col-md-12">';
		str	+='<img alt="" src="image/loading.gif" style="height:150px;width:200px;">'
		str	+='</div>';
		str	+='</div>';
	$(div).append(str);	
}
function isValidEmailAddress(emailAddress) {
    var pattern = /^([a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+(\.[a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+)*|"((([ \t]*\r\n)?[ \t]+)?([\x01-\x08\x0b\x0c\x0e-\x1f\x7f\x21\x23-\x5b\x5d-\x7e\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|\\[\x01-\x09\x0b\x0c\x0d-\x7f\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))*(([ \t]*\r\n)?[ \t]+)?")@(([a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.)+([a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.?$/i;
    return pattern.test(emailAddress);
};
function PopupCenter(url, title, w, h) {
    // Fixes dual-screen position                         Most browsers      Firefox
    var dualScreenLeft = window.screenLeft != undefined ? window.screenLeft : screen.left;
    var dualScreenTop = window.screenTop != undefined ? window.screenTop : screen.top;

    width = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width;
    height = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height;

    var left = ((width / 2) - (w / 2)) + dualScreenLeft;
    var top = ((height / 2) - (h / 2)) + dualScreenTop;
    var newWindow = window.open(url, title, 'scrollbars=yes, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);

    // Puts focus on the newWindow
    if (window.focus) {
        newWindow.focus();
    }
}
function fn_like_product(product_id,callback){
	blockbg();
	var pdata = {
			'session_id':Cookies.get('sessionid'),
			'str':product_id
	}
	extras_GET_json('ProductController','like_product',pdata,function(data){
		if(data!=null)
		{			
			unblockbg();
			callback(data);
		}
	});
}