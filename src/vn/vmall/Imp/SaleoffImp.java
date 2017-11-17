package vn.vmall.Imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.vmall.DAL.SaleoffDAL;
import vn.vmall.Entity.CatgorySub_Entity;
import vn.vmall.Entity.Catgory_Entity;
import vn.vmall.Entity.Catgory_vmall_sub_temp;
import vn.vmall.Interface.SaleoffInterface;

@Component("SaleoffImp")
public class SaleoffImp implements SaleoffInterface{

	@Autowired
	private SaleoffDAL dal;

	@Override
	public ArrayList<Catgory_Entity> get_sale_list_catgory() {
		// TODO Auto-generated method stub
		List<Catgory_vmall_sub_temp> temp_catgory=null;
		temp_catgory=dal.get_temp_catgory_databysale();
		//removeDuplicates_byproduc_type_sub_id
		    temp_catgory=this.removeDuplicates_byproduc_type_sub_id(temp_catgory);
		  ArrayList<Catgory_Entity> list=this.get_list(temp_catgory);
		return list;
	}
	
	
	private List<Catgory_vmall_sub_temp> removeDuplicates_byproduc_type_sub_id(List<Catgory_vmall_sub_temp> listWithDuplicates) {
	    /* Set of all attributes seen so far */
	    Set<String> attributes = new HashSet<String>();
	    /* All confirmed duplicates go in here */
	    List<Catgory_vmall_sub_temp> duplicates = new ArrayList<Catgory_vmall_sub_temp>();

	    for(Catgory_vmall_sub_temp x : listWithDuplicates) {
	        if(attributes.contains(x.getProduc_type_sub_id())) {
	            duplicates.add(x);
	        }
	        attributes.add(x.getProduc_type_sub_id());
	    }
	    /* Clean list without any dups */
	    if(duplicates.size()>0){
	        listWithDuplicates.removeAll(duplicates);
	    }
	     return listWithDuplicates;
	}
	private ArrayList<Catgory_Entity> get_list(List<Catgory_vmall_sub_temp> listin){
		 /* Set of all attributes seen so far */
	    Set<String> attributes = new HashSet<String>();
	    ArrayList<Catgory_Entity> list_result=new ArrayList<Catgory_Entity>();
	    Map<String,ArrayList<CatgorySub_Entity>> map=new HashMap<String,ArrayList<CatgorySub_Entity>>();
	    for(Catgory_vmall_sub_temp x : listin) {
	    	 String key =x.getProduct_type_vmall();
	    	 if (map.get(key) == null) {
	    		  attributes.add(x.getProduct_type_vmall());
	    	      map.put(key, new ArrayList<CatgorySub_Entity>());
	    	   }
	    	 CatgorySub_Entity en1=new CatgorySub_Entity();
	    	 en1.setProduct_type_id(x.getProduc_type_sub_id());
	    	 en1.setProduct_type_name(x.getProduct_type_sub_name());
	    	 en1.setProduct_type_vmall(x.getProduct_type_vmall_name());
	    	 map.get(key).add(en1);
	    	}//end for
	    if(attributes.size()>0){
	    	for(String key :attributes){
	    		Catgory_Entity catgory=new Catgory_Entity();
	    		catgory.setProduct_type_vmall(key);
	    		catgory.setProduct_type_name(map.get(key).get(0).getProduct_type_vmall());
	    		catgory.setList_cate_sub(map.get(key));
	    		list_result.add(catgory);
	    	}
	    }
	    attributes=null;
	    map=null;
	    return list_result;
	}
	
}
