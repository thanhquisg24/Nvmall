package vn.vmall.DAL;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import vn.vmall.Entity.FAQ_Entity;
import vn.vmall.Helper.ResultSetMapper;


@Component
public class FAQDAL {

	public FAQ_Entity get_FAQbyid(String faq_id) {
		FAQ_Entity e=new FAQ_Entity();
		String query ="SELECT id, content, isvisible"
				+ " FROM tb_faq Where id='"+faq_id+"'";
		try{
			ResultSet rs = null;
			rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<FAQ_Entity> resultSetMapper = new ResultSetMapper<FAQ_Entity>();
			e = resultSetMapper.mapRersultSetToObject_singlerow(rs, FAQ_Entity.class);
		}catch(Exception ex){
			System.out.println("FAQDAL error:"+ex);
		}
		return e;
	}

	public int SaveFAQ(FAQ_Entity d) {
		int _result=0;
		try{
			String spname="sp_faq_update";
			String[] pfield={"f","p_faq_id","p_faq_content"};
			String[] ptype={"INT","VARCHAR","TEXT"};
			Object[] pvalues={"-1",d.getId(),d.getContent()};
			int[] pdirec={1,0,0};
			Map<String,Object> mapOfObjects = new HashMap<String,Object>();
			mapOfObjects = ConnectDB.ExecBoFunctionReturnList(spname, pfield, ptype, pvalues, pdirec);
			_result = Integer.parseInt(mapOfObjects.get("f").toString());
		}catch(Exception e)
		{
			e.printStackTrace();
			_result=-1;
		}
		return _result;
	}
	
}
