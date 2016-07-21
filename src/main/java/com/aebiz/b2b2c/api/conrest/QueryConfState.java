package com.aebiz.b2b2c.api.conrest;

public class QueryConfState {
	private static String AccountSid="8a48b551506fd26f01507f16d7a52533";											//云通讯平台主帐号，在云通讯平台注册帐号获取
	private static String AccountToken="246ed57917ca4e608afa126af0411ebb";										//云通讯平台主帐号token，在云通讯平台注册帐号获取
	private static String AppId="aaf98f8951858ab8015185ae44a6002b";	
	private static String ServerIP="sandboxapp.cloopen.com";//请求的URL
	
	/**
	* IVR外呼
	* @param number   待呼叫号码，为Dial节点的属性
	* @param userdata 用户数据，在<startservice>通知中返回，只允许填写数字字符，为Dial节点的属性
	* @param record   是否录音，可填项为true和false，默认值为false不录音，为Dial节点的属性
	*/
	public static void main(String[] args) {
		RestSDK re=new RestSDK();
		re.init(AccountSid, AccountToken, AppId, ServerIP);
		String response=re.QueryConfState("60008142", "");
		System.out.println(response);
	}
}
