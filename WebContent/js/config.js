var arr_domain=[
                	{   
                		'order':'0',
                		'project':'Nvmall',
                		'host_apache_publish':'http://vivmall.vn/',                		
                		'host_tomcat_publish':'http://vivmall.vn/Nvmall/',
                		'host_apache_local':'http://localhost:8080/Nvmall/',
                		'host_tomcat_local':'http://localhost:8080/Nvmall/'               		                		
                	},
                	{ 
                		'order':'1',
                		'project':'Dvmall',
                		'host_apache_publish':'http://detail.vivmall.vn/',                		
                		'host_tomcat_publish':'http://detail.vivmall.vn/Dvmall/',
                		'host_apache_local':'http://localhost:8080/Dvmall/',
                		'host_tomcat_local':'http://localhost:8080/Dvmall/'
                	},
                	{ 
                		'order':'2',
                		'project':'Mvmall',
                		'host_apache_publish':'http://account.vivmall.vn/',
                		'host_tomcat_publish':'http://account.vivmall.vn/Mvmall/',
                		'host_apache_local':'http://localhost:8080/Mvmall/',
                		'host_tomcat_local':'http://localhost:8080/Mvmall/'           		                		
                	},
                	{         
                		'order':'3',
                		'project':'Cvmall',
                		'host_apache_publish':'http://cart.vivmall.vn/',
                		'host_tomcat_publish':'http://cart.vivmall.vn/Cvmall/',
                		'host_apache_local':'http://localhost:8080/Cvmall/',
                		'host_tomcat_local':'http://localhost:8080/Cvmall/'          		                		
                	},
                	{        
                		'order':'4',
                		'project':'NEWSvmall',
                		'host_apache_publish':'http://localhost:8080/NEWSvmall/',
                		'host_tomcat_publish':'http://localhost:8080/NEWSvmall/',
                		'host_apache_local':'http://localhost:8080/NEWSvmall/',
                		'host_tomcat_local':'http://localhost:8080/NEWSvmall/'           		                		
                	},
                	{      
                		'order':'5',
                		'project':'JOBvmall',
                		'host_apache_publish':'http://localhost:8080/JOBvmall/',
                		'host_tomcat_publish':'http://localhost:8080/JOBvmall/',
                		'host_apache_local':'http://localhost:8080/JOBvmall/',
                		'host_tomcat_local':'http://localhost:8080/JOBvmall/'                		                		
                	},
                	{     
                		'order':'6',
                		'project':'WvivmallT',
                		'host_apache_publish':'http://vinhsangvn.vivmall.vn/',
                		'host_tomcat_publish':'http://vinhsangvn.vivmall.vn/VinhSangCommerce/',
                		'host_apache_local':'http://localhost:8080/WvivmallT/',
                		'host_tomcat_local':'http://localhost:8080/WvivmallT/'                		                		
                	}
                	
                ];
var publish = false;
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
function get_cookie_session_id(){
	return Cookies.get('sessionid');
}
var check_load_email = false;
var email_login ='';

var url_detail = get_host(1,1)+'detailProduct.html?id$@id&baseurl$@url';
var url_detail_short = get_host(1,1)+'detailProduct.html?id$@id';
var url_detail_membership = 'http://buyer.vivmall.vn/spring/detail-product.html?p=@id';