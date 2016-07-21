package com.aebiz.b2b2c.api.conrest;

public class ConfMemberPause {
	private static String AccountSid="8a48b551506fd26f01507f16d7a52533";											//云通讯平台主帐号，在云通讯平台注册帐号获取
	private static String AccountToken="246ed57917ca4e608afa126af0411ebb";										//云通讯平台主帐号token，在云通讯平台注册帐号获取
	private static String AppId="aaf98f8951858ab8015185ae44a6002b";														//云通讯平台应用id，在云通讯官网登录后创建应用获取，demo应用和未上线应用只能在沙盒测试环境使用
	
	//云通讯平台应用id，在云通讯官网登录后创建应用获取，demo应用和未上线应用只能在沙盒测试环境使用
	private static String ServerIP="sandboxapp.cloopen.com";
	
	
	public static void main(String[] args) {
		RestSDK re=new RestSDK();
		re.init(AccountSid, AccountToken, AppId, ServerIP);
		String response=re.ConfMemberPause("60008148", "1512231439405644000600080006e932", "");
		System.out.println(response);
	}
}
