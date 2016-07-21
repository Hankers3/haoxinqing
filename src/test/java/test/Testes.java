package test;

import java.util.HashMap;
import java.util.Map;

import com.aebiz.b2b2c.api.conrest.Leancloud;
import com.aebiz.b2b2c.api.conrest.RestSDK;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.JsonUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/** 
* @author  作者 E-mail: liuyang
* @date 创建时间：2016年7月1日 下午8:30:52 
* @version 2.0 
* @parameter  
* @since  
* @return  
*/
public class Testes {

	public static void main(String[] args) {
	
		String url = "https://api.leancloud.cn/1.1/classes/_Conversation";
/*		Map<String, Object> map = new HashMap<String, Object>();
		map.put("m", new String[]{"BillGatesaacc","SteveJobsbbddd"});*/
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "leancloud聊天组");
		map.put("m", new String[]{"BillGatesaacc","SteveJobsbbddd"});
	
		
		String jsonString = JsonUtils.getJSONString(map);
	
		String param = Leancloud.sendPost(jsonString);
		
		System.out.println("param is :" + param);
		JSONObject  jasonObject = JSONObject.parseObject(param);
		Map jsonMap = (Map)jasonObject; 
		
		System.out.println("param is " + jsonMap.get("objectId"));
	
	}

}
