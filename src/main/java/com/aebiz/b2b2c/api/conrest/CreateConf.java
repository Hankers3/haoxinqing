package com.aebiz.b2b2c.api.conrest;


public class CreateConf {
	private static String AccountSid="8a48b551506fd26f01507f16d7a52533";											//云通讯平台主帐号，在云通讯平台注册帐号获取
	private static String AccountToken="246ed57917ca4e608afa126af0411ebb";										//云通讯平台主帐号token，在云通讯平台注册帐号获取
	private static String AppId="aaf98f8951858ab8015185ae44a6002b";														//云通讯平台应用id，在云通讯官网登录后创建应用获取，demo应用和未上线应用只能在沙盒测试环境使用
	
	//云通讯平台应用id，在云通讯官网登录后创建应用获取，demo应用和未上线应用只能在沙盒测试环境使用
	private static String ServerIP="sandboxapp.cloopen.com";		//REST请求地址，sandboxapp.cloopen.com为沙盒测试地址，app.cloopen.com为上线生产地址
	
	/**
	 * 创建会议 
	 * @param action			会议创建通知的回调url地址。自定义的相对地址，默认值为空
	 * @param maxmember 		最大会议人数，不能大于300。默认值为3
	 * @param passwd 			加入会议密码。默认为空，暂时设置无法使用
	 * @param passwderrorurl	密码输入三次错误的回调url地址。自定义的相对地址，默认为空，暂时设置无法使用
	 * @param dtmfreporturl 	会议中DTMF上报通知的回调url地址。自定义的相对地址，默认值为空
	 * @param delreporturl 		会议被删除通知的回调url地址。自定义的相对地址，默认值为空
	 * @param confduration 		此次会议时长单位是秒，小于等于0时则不限时，到时后会议自动结束。第一个成员加入后开始计时，默认值为0
	 * @param autohangup 		会议自动结束后是否自动挂断用户电话，默认值为false 
	 * @param confendprompt 	会议自动结束前的提示音，为空则不播放。默认值为空 
	 * @param autorecord 		是否自动录音，true为录音，false为不录音。默认值为false 
	 * @param quiturl 			退出会议通知的回调url地址。自定义的相对地址，默认值为空
	 * @param mediaopturl 		会议媒体控制结果通知的回调url地址。自定义的相对地址，默认值为空 
	 * @param autojoin 			是否自动加入会议。通过IVR响应命令调用时有效。默认为false 
	 * @param joinurl 			加入会议通知的回调url地址。自定义的相对地址，默认值为空 
	 */
	public static void main(String[] args) {
		RestSDK re=new RestSDK();
		re.init(AccountSid, AccountToken, AppId, ServerIP);
		//参数的填写顺序按照上面参数注释的顺序填写
		String response=re.CreateConf("", "3", "", "", "", "", "0", "false", "", "true", "", "", "false", "");
		System.out.println(response);
	}
}
