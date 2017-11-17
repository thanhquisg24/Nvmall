package vn.vmall.Service;

import java.net.URI;
import java.util.ArrayList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import vn.vmall.Entity.Product_Entity;


public class ProductTypeService {
	public static ArrayList<Product_Entity> get_product_memberhip(){
		ArrayList<Product_Entity> list = new ArrayList<Product_Entity>();
		try{
			ClientConfig config = new ClientConfig();
			Client client = ClientBuilder.newClient(config);
			WebTarget target = client.target(getBaseURI());
			//read service
			String data = target.path("get_product_seller")
					.request()
					.accept(MediaType.APPLICATION_JSON).get(String.class);
			
			if(data.length()>0){
				Gson gson = new Gson();
				list = gson.fromJson(data, new TypeToken<ArrayList<Product_Entity>>() {}.getType());				
				return list;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}		
		return list;
	}
	private static URI getBaseURI() {
		return UriBuilder.fromUri(ModelService.get_url_product_membership()+"SrvConnect").build();
	}
	public static void main(String[] args) {
		ArrayList<Product_Entity> list = new ArrayList<Product_Entity>();
		 list =get_product_memberhip(); 
	}
}
