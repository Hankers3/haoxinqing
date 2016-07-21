package com.aebiz.b2b2c.customermgr.webservice.server.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.customermgr.customerinfo.service.CustomerInfoService;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoModel;
import com.aebiz.b2b2c.customermgr.webservice.server.service.UserCenterWebService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 用户中心外部接口
 * 
 * @author tangyunkai
 * 
 */
@WebService(endpointInterface = "com.aebiz.b2b2c.customermgr.webservice.server.service.UserCenterWebService")
public class UserCenterWebServiceImpl implements UserCenterWebService {
	
	//注入c端会员service
	@Autowired
	private CustomerService customerService;
	
	//注入c端会员Infoservice
        @Autowired
        private CustomerInfoService customerInfoService;
       //注入文件的service
        @Autowired
        private   FileService fileService;

	/**
	 * 获取患者用户的接口
	 * @param jsonString
	 * @return
	 */
	public String getUserInfo(@WebParam(name = "jsonString") String jsonString) {
		//定义返回数据
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		//返回的jsonString
		JSONObject jsonObj =  null;
		//判断请求json参数是否为空
	    if(StringUtil.isEmpty(jsonString)){
	    	 jsonMap.put("code", "2");
			 jsonMap.put("message", "参数为空");
			 jsonObj = (JSONObject) JSON.toJSON(jsonMap);
			 return jsonObj.toString();
	    }
	    try{
	    	 //解密后的json串
	    	//String rejsonString = des.get3DESDecrypt(jsonString, UtilHelper.token);
	    	 //把请求jsonString转化成json对象
	 		 JSONObject jsonObject1 = JSON.parseObject(jsonString);
	 		//根据签名sign获取到签名值
	 	     String sign= jsonObject1.getString("sign");
	 	    //判断jsonString参数值是否为空
	 	     if(StringUtil.isEmpty(sign)){
	 	    	  jsonMap.put("code", "1");
	 			  jsonMap.put("message", "无效签名");
	 			  jsonObj = (JSONObject) JSON.toJSON(jsonMap);
	 			  return jsonObj.toString();
	 	     }
	 	     //判断签名是否正确
//	  	     if(!sign.equals(MD5Util.md5(UtilHelper.token))){
//	  	    	  jsonMap.put("code", "1");
//	  			  jsonMap.put("message", "签名不正确！");
//	  			  jsonObj = (JSONObject) JSON.toJSON(jsonMap);
//	  			  return des.get3DESEncrypt(jsonObj.toString(), UtilHelper.token);
//	  	     }
	 	     //获取到B端客户登录名密码
	 	     String loginName= (String) jsonObject1.getString("loginName");
	 	     String pwd= (String) jsonObject1.getString("pwd");
	 	     //判断用户名是否为空
	  	     if(StringUtil.isEmpty(loginName)){
	  	    	  jsonMap.put("code", "2");
	  			  jsonMap.put("message", "用户名为空！");
	  			  jsonObj = (JSONObject) JSON.toJSON(jsonMap);
	  			  return jsonObj.toString();
	  	     }
	  	   //判断密码是否为空
                     if(StringUtil.isEmpty(pwd)){
                          jsonMap.put("code", "2");
                                  jsonMap.put("message", "密码为空！");
                                  jsonObj = (JSONObject) JSON.toJSON(jsonMap);
                                  return jsonObj.toString();
                     }
	  	     //调用service实现业务逻辑
	  	     CustomerModel customer =customerService.getCustomerInfoModelByLoginNameAndPassword(loginName,pwd);
	  	    		 
	  	     if(customer == null ){
				jsonMap.put("code", "2");
				jsonMap.put("message", "用户名或密码不正确!");
				jsonObj = (JSONObject) JSON.toJSON(jsonMap);
				return jsonObj.toString();
	  	     }
	  	   List user = new ArrayList();
	  	     
	  	     
        	  	    //根据用户的Uuid获取用户的详细信息
        	  	     String customerUuid = customer.getUuid();
        	  	     String imageUrl ="";
        	  	     if(!StringUtil.isEmpty(customerUuid)){
        	  	       CustomerInfoModel  customerInfoModel =customerInfoService.getCustomerInfoModelByCustomerUuid(customerUuid);
        	  	       if(customerInfoModel!=null){
        	  	         imageUrl = customerInfoModel.getImgUrl();
        	  	        }}
        	  	     
        	  	   Map<String, Object> map = new HashMap<String, Object>();
        	  	        map.put("customerName", customer.getCustomerName());
                	  	map.put("mobile", customer.getMobile());
                	  	map.put("email", customer.getEmail());
                	  	map.put("integral", customer.getAvailableIntegral());
                	  	map.put("icon", imageUrl);
                	  	user.add(map);
            	  	      // jsonMap.put("score", customer);
            	  	      //成功参数
        		      jsonMap.put("code", "0");
        		      jsonMap.put("message", "成功");
        		      jsonMap.put("user", user);
        		      jsonObj = (JSONObject) JSON.toJSON(jsonMap);
	 		 
	        }catch(Exception e){
	    	e.printStackTrace();
    	    	               //报错捕获
	    	              jsonMap.put("code", "1");
	    	              jsonMap.put("message", "系统出错");
	    	              jsonObj = (JSONObject) JSON.toJSON(jsonMap);
	    	
	        }
	        System.out.println("return:jsonString =======================" + jsonObj.toString());
		 return jsonObj.toString();
	        }
	
	


}


