package com.aebiz.b2b2c.api.conrest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConfAlarmClock {
	private static String AccountSid="8a48b551506fd26f01507f16d7a52533";											//云通讯平台主帐号，在云通讯平台注册帐号获取
	private static String AccountToken="246ed57917ca4e608afa126af0411ebb";										//云通讯平台主帐号token，在云通讯平台注册帐号获取
	private static String AppId="aaf98f8951858ab8015185ae44a6002b";														//云通讯平台应用id，在云通讯官网登录后创建应用获取，demo应用和未上线应用只能在沙盒测试环境使用
	
	//云通讯平台应用id，在云通讯官网登录后创建应用获取，demo应用和未上线应用只能在沙盒测试环境使用
	private static String ServerIP="sandboxapp.cloopen.com";	
	
	
	 /**
	 * 会议计时闹钟
	 * @param confid			会议Id，由云通讯平台产生，是一路通话的唯一标识
	 * @param time				闹钟计时时长，正整数，单位为秒。 
	 * @param action			设置会议计时闹钟结果通知的的回调url。自定义的相对地址，默认值为空
	 * @param timeraction 闹钟到时后的动作类型：0发送通知请求；1播放提示音；2解散会议。默认值为0 
	 * @param clockurl		会议闹钟到时后通知的请求url地址。自定义的相对地址，无自定义则为默认值alarmclock。
	 * @param promptfile	提示音文件名，wav格式
	 * @param autohangup	会议自动解散后是否自动挂断用户。true表示挂断，false不挂断，默认false。 
	 * @param quiturl			会议自动解散但不挂断用户时退出会议通知的回调url。若自动挂断则不发送。自定义的相对地址，默认值为空  
	 * @param recordurl		录音媒体控制结果通知的回调url，有录音才会回调。自定义的相对地址，默认值为空
	 * @param delurl			会议被删除通知的回调url。自定义的相对地址，默认值为空
	 */
	public static void main(String[] args) {
		RestSDK re=new RestSDK();
		re.init(AccountSid, AccountToken, AppId, ServerIP);
		//参数的填写顺序按照上面参数注释的顺序填写
		String response=re.ConfAlarmClock("60008165", "20", "", "2", "", "", "false", "", "", "elurl");
		
	}
	
	public static String delurl(){
		System.out.println("123444444444444444444444444445555555555566666666666666666667777777777777777777777");
		
		return null;
		
	}
}
