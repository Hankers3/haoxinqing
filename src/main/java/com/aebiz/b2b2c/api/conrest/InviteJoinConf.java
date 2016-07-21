package com.aebiz.b2b2c.api.conrest;

public class InviteJoinConf {
	private static String AccountSid="8a48b551506fd26f01507f16d7a52533";											//云通讯平台主帐号，在云通讯平台注册帐号获取
	private static String AccountToken="246ed57917ca4e608afa126af0411ebb";										//云通讯平台主帐号token，在云通讯平台注册帐号获取
	private static String AppId="aaf98f8951858ab8015185ae44a6002b";									//云通讯平台应用id，在云通讯官网登录后创建应用获取，demo应用和未上线应用只能在沙盒测试环境使用
	private static String ServerIP="sandboxapp.cloopen.com";		//REST请求地址，sandboxapp.cloopen.com为沙盒测试地址，app.cloopen.com为上线生产地址
	
	/**
	 * 邀请加入会议
	 * @param confid	会议Id，由云通讯平台产生，通过创建会议获取 
	 * @param number	被邀请者的手机号、座机号或voip帐号
	 * @param action	会议邀请结果通知的回调url地址。自定义的相对地址，默认值为空 
	 * @param role		加入会议的角色。会议中只有一个主持人，以最后一个为准。0：普通成员，1：主持人，默认值为0 
   * @param speak		是否可讲，0：不可讲，1：可讲，默认值为1 
   * @param joinurl	成功加入会议通知的回调url地址。自定义的相对地址，默认值为空
	 */
	public static void main(String[] args) {
		RestSDK re=new RestSDK();
		re.init(AccountSid, AccountToken, AppId, ServerIP);
		//参数的填写顺序按照上面参数注释的顺序填写
		String response=re.InviteJoinConf("60008165", "13241301602", "", "0", "1", "");
		System.out.println(response);
	}
}
