package com.aebiz.b2b2c.api.conrest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RestSDK {
	private static String AccountSid="8a48b551506fd26f01507f16d7a52533";											//云通讯平台主帐号，在云通讯平台注册帐号获取
	private static String AccountToken="246ed57917ca4e608afa126af0411ebb";										//云通讯平台主帐号token，在云通讯平台注册帐号获取
	private static String AppId="aaf98f8951858ab8015185ae44a6002b";	
	//云通讯平台应用id，在云通讯官网登录后创建应用获取，demo应用和未上线应用只能在沙盒测试环境使用
	//REST请求地址，sandboxapp.cloopen.com为沙盒测试地址，app.cloopen.com为上线生产地址
	//private static String ServerIP="sandboxapp.cloopen.com";
	private static String ServerIP="app.cloopen.com";
	private static String ServerPort="8883";									//REST请求端口
	private static String SoftVersion="2013-12-26";						//REST版本号
	private static String timestamp="";

	//SDK初始化
	public void init(String accountsSid, String accountToken,String appId, String serverIP) {
		AccountSid=accountsSid;
		AccountToken=accountToken;
		AppId=appId;
		ServerIP=serverIP;
	}
	
    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {

            //生成auth
    		String src = AccountSid + ":" + timestamp;
    		String auth = new sun.misc.BASE64Encoder().encode( src.getBytes() ); 
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();

            // 设置通用的请求属性
            conn.setRequestProperty("accept", "application/xml");
            conn.setRequestProperty("connection", "close");
            conn.setRequestProperty("content-type","application/xml;charset=utf-8");
            conn.setRequestProperty("authorization",auth);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        //System.out.println(result);
        return result;
    }   
    
	/**
	 * 创建会议 
	 * @param action					会议创建通知的回调url地址。自定义的相对地址，默认值为空
	 * @param maxmember 			最大会议人数，不能大于300。默认值为3
	 * @param passwd 					加入会议密码。默认为空，暂时设置无法使用
	 * @param passwderrorurl	密码输入三次错误的回调url地址。自定义的相对地址，默认为空，暂时设置无法使用
	 * @param dtmfreporturl 	会议中DTMF上报通知的回调url地址。自定义的相对地址，默认值为空
	 * @param delreporturl 		会议被删除通知的回调url地址。自定义的相对地址，默认值为空
   * @param confduration 		此次会议时长单位是秒，小于等于0时则不限时，到时后会议自动结束。第一个成员加入后开始计时，默认值为0
   * @param autohangup 			会议自动结束后是否自动挂断用户电话，默认值为false 
   * @param confendprompt 	会议自动结束前的提示音，为空则不播放。默认值为空 
   * @param autorecord 			是否自动录音，true为录音，false为不录音。默认值为false 
   * @param quiturl 				退出会议通知的回调url地址。自定义的相对地址，默认值为空
   * @param mediaopturl 		会议媒体控制结果通知的回调url地址。自定义的相对地址，默认值为空 
   * @param autojoin 				是否自动加入会议。通过IVR响应命令调用时有效。默认为false 
   * @param joinurl 				加入会议通知的回调url地址。自定义的相对地址，默认值为空 
	 */
    public static String CreateConf(String action,String  maxmember,String passwd,String passwderrorurl,String  dtmfreporturl,String delreporturl,String confduration,String  autohangup,String confendprompt,String autorecord,String  quiturl,String mediaopturl,String  autojoin,String joinurl){
    	//获取系统时间
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        timestamp=df.format(new Date());
		//生成sig
        String signature="";
		String sig = AccountSid + AccountToken + timestamp;
		try {
			signature = md5Digest(sig);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//拼接请求包体
		String requsetbody = "";

		requsetbody="<?xml version='1.0' encoding='utf-8'?><Request><Appid>"+AppId+"</Appid><CreateConf action='"+action+"' maxmember='"+maxmember+"' passwd='"+passwd+"' passwderrorurl='"+passwderrorurl+"' dtmfreporturl='"+dtmfreporturl+"' delreporturl='"+delreporturl+"' confduration='"+confduration+"' autohangup='"+autohangup+"' confendprompt='"+confendprompt+"' autorecord='"+autorecord+"' quiturl='"+quiturl+"' mediaopturl='"+mediaopturl+"' autojoin='"+autojoin+"' joinurl='"+joinurl+"'/></Request>";
		String url= "https://"+ServerIP+":"+ServerPort+"/"+SoftVersion+"/Accounts/"+AccountSid+"/ivr/createconf?sig="+signature+"&maxmember="+maxmember;
		return sendPost(url,requsetbody);
    	
    }
    
	/**
	 * 邀请加入会议
	 * @param confid	会议Id，由云通讯平台产生，通过创建会议获取 
	 * @param number	被邀请者的手机号、座机号或voip帐号
	 * @param action	会议邀请结果通知的回调url地址。自定义的相对地址，默认值为空 
	 * @param role		加入会议的角色。会议中只有一个主持人，以最后一个为准。0：普通成员，1：主持人，默认值为0 
   * @param speak		是否可讲，0：不可讲，1：可讲，默认值为1 
   * @param joinurl	成功加入会议通知的回调url地址。自定义的相对地址，默认值为空
	 */
    public static String InviteJoinConf(String confid,String  number,String action,String role,String  speak,String joinurl){
    	//获取系统时间
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        timestamp=df.format(new Date());
		//生成sig
        String signature="";
		String sig = AccountSid + AccountToken + timestamp;
		try {
			signature = md5Digest(sig);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//拼接请求包体
		String requsetbody = "";

		requsetbody="<?xml version='1.0' encoding='utf-8'?><Request><Appid>"+AppId+"</Appid><InviteJoinConf confid='"+confid+"' number='"+number+"' action='"+action+"' role='"+role+"' speak='"+speak+"' joinurl='"+joinurl+"'/></Request>";
		System.out.println(requsetbody);
		String url= "https://"+ServerIP+":"+ServerPort+"/"+SoftVersion+"/Accounts/"+AccountSid+"/ivr/conf?sig="+signature+"&confid="+confid;
		return sendPost(url,requsetbody);
    	
    }
    
    /**
     * 静音
     * @param callid 呼叫Id，由云通讯平台产生，是一路通话的唯一标识 
     * @param confid 会议Id，由云通讯平台产生，通过创建会议获取
     * @param action 会议媒体控制结果通知的回调url地址。自定义的相对地址，默认值为空  
     */
    public static String ConfMute(String callid,String  confid,String action){
    	//获取系统时间
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        timestamp=df.format(new Date());
		//生成sig
        String signature="";
		String sig = AccountSid + AccountToken + timestamp;
		try {
			signature = md5Digest(sig);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//拼接请求包体
		String requsetbody = "";

		requsetbody="<?xml version='1.0' encoding='utf-8'?><Request><Appid>"+AppId+"</Appid><ConfMute callid='"+callid+"' confid='"+confid+"' action='"+action+"'/></Request>";
		System.out.println(requsetbody);
		String url= "https://"+ServerIP+":"+ServerPort+"/"+SoftVersion+"/Accounts/"+AccountSid+"/ivr/conf?sig="+signature+"&confid="+confid;
		return sendPost(url,requsetbody);
    	
    }
    
    /**
     * 取消静音
     * @param callid 呼叫Id，由云通讯平台产生，是一路通话的唯一标识
     * @param confid 会议Id，由云通讯平台产生，通过创建会议获取 
     * @param action 会议媒体控制结果通知的回调url地址。自定义的相对地址，默认值为空 
     */
    public static String ConfUnMute(String callid,String  confid,String action){
    	//获取系统时间
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        timestamp=df.format(new Date());
		//生成sig
        String signature="";
		String sig = AccountSid + AccountToken + timestamp;
		try {
			signature = md5Digest(sig);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//拼接请求包体
		String requsetbody = "";

		requsetbody="<?xml version='1.0' encoding='utf-8'?><Request><Appid>"+AppId+"</Appid><ConfUnMute callid='"+callid+"' confid='"+confid+"' action='"+action+"'/></Request>";
		System.out.println(requsetbody);
		String url= "https://"+ServerIP+":"+ServerPort+"/"+SoftVersion+"/Accounts/"+AccountSid+"/ivr/conf?sig="+signature+"&confid="+confid;
		return sendPost(url,requsetbody);
    	
    }
    
	/**
    * IVR外呼
    * @param number   待呼叫号码，为Dial节点的属性
    * @param userdata 用户数据，在<startservice>通知中返回，只允许填写数字字符，为Dial节点的属性
    * @param record   是否录音，可填项为true和false，默认值为false不录音，为Dial节点的属性
    */
    public static String Dial(String number,String  userdata,String record){
    	//获取系统时间
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        timestamp=df.format(new Date());
		//生成sig
        String signature="";
		String sig = AccountSid + AccountToken + timestamp;
		try {
			signature = md5Digest(sig);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//拼接请求包体
		String requsetbody = "";

		requsetbody="<?xml version='1.0' encoding='utf-8'?><Request><Appid>"+AppId+"</Appid><Dial number='"+number+"' userdata='"+userdata+"' record='"+record+"'/></Request>";
		String url= "https://"+ServerIP+":"+ServerPort+"/"+SoftVersion+"/Accounts/"+AccountSid+"/ivr/dial?sig="+signature;
		return sendPost(url,requsetbody);
    	
    }
    
	 /**
     * 解散会议 
     * @param action			解散会议操作结果通知回调url。自定义的相对地址，默认值为空
     * @param confid			会议Id，由云通讯平台产生，通过创建会议获取 
     * @param delurl			会议被删除通知的相对url地址。自定义的相对地址，默认值为空
		 * @param autohangup	会议自动解散后是否自动挂断用户，true表示挂断，false不挂断。默认值为false 
		 * @param quiturl			解散时参会者退出会议通知的回调url地址。若自动挂断则不发送。默认值为空 
		 * @param recordurl		录音控制结果通知的回调url地址。自定义的相对地址，默认值为空
     */
    public static String DismissConf(String action,String confid,String delurl,String autohangup,String quiturl,String recordurl){
    	//获取系统时间
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        timestamp=df.format(new Date());
		//生成sig
        String signature="";
		String sig = AccountSid + AccountToken + timestamp;
		try {
			signature = md5Digest(sig);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//拼接请求包体
		String requsetbody = "";

		requsetbody="<?xml version='1.0' encoding='utf-8'?><Request><Appid>"+AppId+"</Appid><DismissConf action='"+action+"' confid='"+confid+"' delurl='"+delurl+"' autohangup='"+autohangup+"' quiturl='"+quiturl+"' recordurl='"+recordurl+"'/></Request>";
		System.out.println(requsetbody);
		String url= "https://"+ServerIP+":"+ServerPort+"/"+SoftVersion+"/Accounts/"+AccountSid+"/ivr/conf?sig="+signature+"&confid="+confid;
		return sendPost(url,requsetbody);	
    }
    
	 /**
     * 退出会议
     * @param callid 呼叫Id，由云通讯平台产生，是一路通话的唯一标识 
     * @param confid 会议Id，由云通讯平台产生，通过创建会议获取 
     * @param action 退出会议通知的回调url地址。自定义的相对地址，默认值为空
     */
    public static String QuitConf(String callid,String confid,String action){
    	//获取系统时间
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        timestamp=df.format(new Date());
		//生成sig
        String signature="";
		String sig = AccountSid + AccountToken + timestamp;
		try {
			signature = md5Digest(sig);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//拼接请求包体
		String requsetbody = "";

		requsetbody="<?xml version='1.0' encoding='utf-8'?><Request><Appid>"+AppId+"</Appid><QuitConf callid='"+callid+"' confid='"+confid+"' action='"+action+"'/></Request>";
		System.out.println(requsetbody);
		String url= "https://"+ServerIP+":"+ServerPort+"/"+SoftVersion+"/Accounts/"+AccountSid+"/ivr/conf?sig="+signature+"&confid="+confid;
		return sendPost(url,requsetbody);	
    }
    
	/**
     * 会议放音
     * @param confid					会议Id，由云通讯平台产生，是一路通话的唯一标识
     * @param filename				会议放音文件名，wav格式
     * @param loop						循环次数，正整数值，0不播放　-1无限循环，默认值为1 
     * @param action					会议媒体控制结果通知的回调url地址，不论成功与失败只要不为空则发送请求。自定义的相对地址，默认值为空
     * @param playcompleteurl	放音完成通知的回调url地址。自定义的相对地址，默认值为空 
     */
    public static String ConfPlay(String confid,String filename,String loop,String action,String playcompleteurl){
    	//获取系统时间
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        timestamp=df.format(new Date());
		//生成sig
        String signature="";
		String sig = AccountSid + AccountToken + timestamp;
		try {
			signature = md5Digest(sig);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//拼接请求包体
		String requsetbody = "";

		requsetbody="<?xml version='1.0' encoding='utf-8'?><Request><Appid>"+AppId+"</Appid><ConfPlay confid='"+confid+"' filename='"+filename+"' loop='"+loop+"' action='"+action+"' playcompleteurl='"+playcompleteurl+"'/></Request>";
		System.out.println(requsetbody);
		String url= "https://"+ServerIP+":"+ServerPort+"/"+SoftVersion+"/Accounts/"+AccountSid+"/ivr/conf?sig="+signature+"&confid="+confid;
		return sendPost(url,requsetbody);	
    }
    
	 /**
     * 停止会议放音
     * @param confid 会议Id，由云通讯平台产生，是一路通话的唯一标识
     * @param action 会议媒体控制结果通知的回调url地址，不论成功与失败只要不为空则发送请求。自定义的相对地址，默认值为空
     */
    public static String ConfStopPlay(String confid,String action){
    	//获取系统时间
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        timestamp=df.format(new Date());
		//生成sig
        String signature="";
		String sig = AccountSid + AccountToken + timestamp;
		try {
			signature = md5Digest(sig);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//拼接请求包体
		String requsetbody = "";

		requsetbody="<?xml version='1.0' encoding='utf-8'?><Request><Appid>"+AppId+"</Appid><ConfStopPlay confid='"+confid+"' action='"+action+"'/></Request>";
		System.out.println(requsetbody);
		String url= "https://"+ServerIP+":"+ServerPort+"/"+SoftVersion+"/Accounts/"+AccountSid+"/ivr/conf?sig="+signature+"&confid="+confid;
		return sendPost(url,requsetbody);	
    }
    
	 /**
     * 会议录音
     * @param confid						会议Id，由云通讯平台产生，是一路通话的唯一标识
     * @param action						会议媒体控制结果通知的回调url地址，不论成功与失败只要不为空则发送请求。自定义的相对地址，默认值为空
     * @param time							录音时长，单位为秒。默认值为0，不限时。 
     * @param timeoutrecordurl	录音时长到时的录音结果通知的回调url地址。自定义的相对地址，默认值为空 
     */
    public static String ConfRecord(String confid,String action,String time,String timeoutrecordurl){
    	//获取系统时间
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        timestamp=df.format(new Date());
		//生成sig
        String signature="";
		String sig = AccountSid + AccountToken + timestamp;
		try {
			signature = md5Digest(sig);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//拼接请求包体
		String requsetbody = "";

		requsetbody="<?xml version='1.0' encoding='utf-8'?><Request><Appid>"+AppId+"</Appid><ConfRecord confid='"+confid+"' action='"+action+"' time='"+time+"' timeoutrecordurl='"+timeoutrecordurl+"'/></Request>";
		System.out.println(requsetbody);
		String url= "https://"+ServerIP+":"+ServerPort+"/"+SoftVersion+"/Accounts/"+AccountSid+"/ivr/conf?sig="+signature+"&confid="+confid;
		return sendPost(url,requsetbody);	
    }
    
	 /**
     * 停止会议录音
     * @param confid 会议Id，由云通讯平台产生，是一路通话的唯一标识
     * @param action 会议媒体控制结果通知的回调url地址。不论成功与失败只要不为空则发送请求。自定义的相对地址，默认值为空 
     */
    public static String ConfStopRecord(String confid,String action){
    	//获取系统时间
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        timestamp=df.format(new Date());
		//生成sig
        String signature="";
		String sig = AccountSid + AccountToken + timestamp;
		try {
			signature = md5Digest(sig);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//拼接请求包体
		String requsetbody = "";

		requsetbody="<?xml version='1.0' encoding='utf-8'?><Request><Appid>"+AppId+"</Appid><ConfStopRecord confid='"+confid+"' action='"+action+"'/></Request>";
		System.out.println(requsetbody);
		String url= "https://"+ServerIP+":"+ServerPort+"/"+SoftVersion+"/Accounts/"+AccountSid+"/ivr/conf?sig="+signature+"&confid="+confid;
		return sendPost(url,requsetbody);	
    }
    
	 /**
     * 调节会议音量
     * @param confid			会议Id，由云通讯平台产生，是一路通话的唯一标识
     * @param adjusttype	增加或减小会议音量。True增加音量　false减小音量 
     * @param action			会议媒体控制结果通知的回调url地址，不论成功与失败只要不为空则发送请求。自定义的相对地址，默认值为空   
     */
    public static String ConfVolumeAdjust(String confid,String adjusttype,String action){
    	//获取系统时间
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        timestamp=df.format(new Date());
		//生成sig
        String signature="";
		String sig = AccountSid + AccountToken + timestamp;
		try {
			signature = md5Digest(sig);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//拼接请求包体
		String requsetbody = "";

		requsetbody="<?xml version='1.0' encoding='utf-8'?><Request><Appid>"+AppId+"</Appid><ConfVolumeAdjust confid='"+confid+"' adjusttype='"+adjusttype+"' action='"+action+"'/></Request>";
		System.out.println(requsetbody);
		String url= "https://"+ServerIP+":"+ServerPort+"/"+SoftVersion+"/Accounts/"+AccountSid+"/ivr/conf?sig="+signature+"&confid="+confid;
		return sendPost(url,requsetbody);	
    }
    
	 /**
     * 会议成员禁听
     * @param confid	会议Id，由云通讯平台产生，是一路通话的唯一标识
     * @param callid	呼叫Id，由云通讯平台产生，是一路通话的唯一标识 
     * @param action	会议媒体控制结果通知的回调url地址。自定义的相对地址，默认值为空 
     */
    public static String ConfMemberPause(String confid,String callid,String action){
    	//获取系统时间
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        timestamp=df.format(new Date());
		//生成sig
        String signature="";
		String sig = AccountSid + AccountToken + timestamp;
		try {
			signature = md5Digest(sig);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//拼接请求包体
		String requsetbody = "";

		requsetbody="<?xml version='1.0' encoding='utf-8'?><Request><Appid>"+AppId+"</Appid><ConfMemberPause confid='"+confid+"' callid='"+callid+"' action='"+action+"'/></Request>";
		System.out.println(requsetbody);
		String url= "https://"+ServerIP+":"+ServerPort+"/"+SoftVersion+"/Accounts/"+AccountSid+"/ivr/conf?sig="+signature+"&confid="+confid;
		System.out.println(url);
		return sendPost(url,requsetbody);	
    }
    
	 /**
     * 取消会议成员禁听
     * @param confid	会议Id，由云通讯平台产生，是一路通话的唯一标识
     * @param callid	呼叫Id，由云通讯平台产生，是一路通话的唯一标识 
     * @param action	会议媒体控制结果通知的回调url地址。自定义的相对地址，默认值为空 
     * @param speak		是否可讲：0 不可讲；1 可讲。默认为0 
     */
    public static String ConfMemberResume(String confid,String callid,String action,String speak){
    	//获取系统时间
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        timestamp=df.format(new Date());
		//生成sig
        String signature="";
		String sig = AccountSid + AccountToken + timestamp;
		try {
			signature = md5Digest(sig);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//拼接请求包体
		String requsetbody = "";

		requsetbody="<?xml version='1.0' encoding='utf-8'?><Request><Appid>"+AppId+"</Appid><ConfMemberResume confid='"+confid+"' callid='"+callid+"' action='"+action+"' speak='"+speak+"'/></Request>";
		System.out.println(requsetbody);
		String url= "https://"+ServerIP+":"+ServerPort+"/"+SoftVersion+"/Accounts/"+AccountSid+"/ivr/conf?sig="+signature+"&confid="+confid;
		return sendPost(url,requsetbody);	
    }
    
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
    public static String ConfAlarmClock(String confid,String time,String action,String timeraction,String clockurl,String promptfile,String autohangup,String quiturl,String recordurl,String delurl){
    	//获取系统时间
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        timestamp=df.format(new Date());
		//生成sig
        String signature="";
		String sig = AccountSid + AccountToken + timestamp;
		try {
			signature = md5Digest(sig);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//拼接请求包体
		String requsetbody = "";

		requsetbody="<?xml version='1.0' encoding='utf-8'?><Request><Appid>"+AppId+"</Appid><ConfAlarmClock confid='"+confid+"' time='"+time+"' action='"+action+"' timeraction='"+timeraction+"' clockurl='"+clockurl+"' promptfile='"+promptfile+"' autohangup='"+autohangup+"' quiturl='"+quiturl+"' recordurl='"+recordurl+"' delurl='"+delurl+"'/></Request>";
		System.out.println(requsetbody);
		String url= "https://"+ServerIP+":"+ServerPort+"/"+SoftVersion+"/Accounts/"+AccountSid+"/ivr/conf?sig="+signature+"&confid="+confid;
		return sendPost(url,requsetbody);	
    }
    
	 /**
     * 会议状态查询
     * @param confid	会议Id，由云通讯平台产生，是一路通话的唯一标识
     * @param action	会议媒体控制结果通知的回调url地址。自定义的相对地址，默认值为空   
     */
    public static String QueryConfState(String confid,String action){
    	//获取系统时间
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        timestamp=df.format(new Date());
		//生成sig
        String signature="";
		String sig = AccountSid + AccountToken + timestamp;
		try {
			signature = md5Digest(sig);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//拼接请求包体
		String requsetbody = "";

		requsetbody="<?xml version='1.0' encoding='utf-8'?><Request><Appid>"+AppId+"</Appid><QueryConfState confid='"+confid+"' action='"+action+"'/></Request>";
		System.out.println(requsetbody);
		String url= "https://"+ServerIP+":"+ServerPort+"/"+SoftVersion+"/Accounts/"+AccountSid+"/ivr/conf?sig="+signature+"&confid="+confid;
		return sendPost(url,requsetbody);	
    }
    
    //MD5加密
    public static String md5Digest(String src) throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] b = md.digest(src.getBytes("utf-8"));
      return byte2HexStr(b);
    }
    private static String byte2HexStr(byte[] b)
    {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < b.length; ++i) {
        String s = Integer.toHexString(b[i] & 0xFF);
        if (s.length() == 1)
          sb.append("0");

        sb.append(s.toUpperCase());
      }
      return sb.toString();
    }
    
}
