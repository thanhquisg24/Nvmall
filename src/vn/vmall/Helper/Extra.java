package vn.vmall.Helper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;


public class Extra {
	private final static String default_lang="VN";
	
	public static String get_current_lang(HttpServletRequest request){
		HttpSession session = request.getSession();	
		String lang=session.getAttribute("language")==null?default_lang:session.getAttribute("language").toString();
		return lang;
	}
	public static String replace_origin_html(String str_html){
		return str_html.replace( "&amp;","&").replace("&lt;","<").replace("&gt;",">").replace( "&quot;","\"").replace("&blink;","'");
	}
	public static String readFile(String filename) throws IOException
	{
		String content="";
		
		
		BufferedReader in = new BufferedReader(
		   new InputStreamReader(
                      new FileInputStream(filename), "UTF8"));
		        
		String str;		      
		while ((str = in.readLine()) != null) {
		    content+=str;
		}		        
        in.close();
        return content;	   
	}
	public static String format_currency(String price) {
		NumberFormat formatter = new DecimalFormat("###,###");
		String resp = formatter.format(Double.parseDouble(price));
		resp = resp.replaceAll(",", ".");
		return resp;
	}
}
