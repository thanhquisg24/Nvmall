package vn.vmall.Service;

import java.rmi.RemoteException;


import javax.servlet.http.HttpServletRequest;


import org.tempuri.WebServiceSoapProxy;

public class ReadServiceMail {
	public static int  SendingFromgmail(String tomail,String title,String message,HttpServletRequest request){		
		WebServiceSoapProxy call = new WebServiceSoapProxy();
		try {
			Boolean data = call.sendMailToCustomer(tomail,message, title);
			if(data==true){
				return 0;
			}
			else{
				return 1;
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}
	public static void main(String[] args) throws RemoteException {
		WebServiceSoapProxy call = new WebServiceSoapProxy();
		call.sendMailToCustomer("tranthang0704@gmail.com","123","123");
	}
}
