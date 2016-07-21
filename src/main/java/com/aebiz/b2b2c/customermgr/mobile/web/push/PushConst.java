package com.aebiz.b2b2c.customermgr.mobile.web.push;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;

/**
 * 定义几个常量，比如说appKey与masterSecret
 * @author dgh 
 *
 */
public class PushConst {

	//开发者标识(DevKey)客户端

	//public static final String APPKEY_CUSTOMER = "a7af25e53a814b679fad092a";
	public static  String APPKEY_CUSTOMER = "";
	public static  String APPKEY_CUSTOMER_IOS = "";
	//API DevSecret客户端
	//public static final String MASTERSECRET_CUSTOMER = "dd435877ea14f581d96fdce6";
	public static  String MASTERSECRET_CUSTOMER = "";
	public static  String MASTERSECRET_CUSTOMER_IOS = "";

	
	//开发者标识(DevKey)医生端
	//public static final String APPKEY_SERVICE = "cf7273a86c53515f5f031000";
	public static  String APPKEY_SERVICE = "";
	public static  String APPKEY_SERVICE_IOS = "";
	//API DevSecret医生端
	//public static final String MASTERSECRET_SERVICE = "af6a953b9ee1d42a67e3e798";
	public static  String MASTERSECRET_SERVICE = "";
	public static  String MASTERSECRET_SERVICE_IOS = "";
	//推送后跳车到html5的url地址
	public static String PUSH_URL ="";
	
	public static Boolean isFormal;
	static{
		//是否是正式环境
		isFormal =Boolean.valueOf(MessageHelper.getMessage("is_formal"));
		if(isFormal){
			APPKEY_CUSTOMER = MessageHelper.getMessage("appkey_customer");
			APPKEY_CUSTOMER_IOS = MessageHelper.getMessage("appkey_customer_ios");
			MASTERSECRET_CUSTOMER = MessageHelper.getMessage("mastersecret_customer");
			MASTERSECRET_CUSTOMER_IOS = MessageHelper.getMessage("mastersecret_customer_ios");
			APPKEY_SERVICE = MessageHelper.getMessage("appkey_service");
			APPKEY_SERVICE_IOS = MessageHelper.getMessage("appkey_service_ios");
			MASTERSECRET_SERVICE = MessageHelper.getMessage("mastersecret_service");
			MASTERSECRET_SERVICE_IOS = MessageHelper.getMessage("mastersecret_service_ios");
			PUSH_URL = MessageHelper.getMessage("push_url");
		}else{
			APPKEY_CUSTOMER = MessageHelper.getMessage("appkey_customer_test");
			APPKEY_CUSTOMER_IOS = MessageHelper.getMessage("appkey_customer_ios_test");
			MASTERSECRET_CUSTOMER = MessageHelper.getMessage("mastersecret_customer_test");
			MASTERSECRET_CUSTOMER_IOS = MessageHelper.getMessage("mastersecret_customer_ios_test");
			APPKEY_SERVICE = MessageHelper.getMessage("appkey_service_test");
			APPKEY_SERVICE_IOS = MessageHelper.getMessage("appkey_service_ios_test");
			MASTERSECRET_SERVICE = MessageHelper.getMessage("mastersecret_service_test");
			MASTERSECRET_SERVICE_IOS = MessageHelper.getMessage("mastersecret_service_ios_test");
			PUSH_URL = MessageHelper.getMessage("push_url_test");
		}
	}


	//平台：医生端为1，患者端为2
	public static final String push_client_service = "1";
	public static final String  push_client_customer= "2";
	
	//推送消息类型：系统通知：1、消息：2
	public static final String push_type_system = "1";
	public static final String push_type_customer = "2";
	
	//消息类型：推送订单:1、取消订单:2、确认订单:3、上门服务:4
	public static final String push_message_order = "1";
	public static final String push_message_cancel = "2";
	public static final String push_message_confirm = "3";
	public static final String push_message_door = "4";
	
	
}

