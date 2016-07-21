package com.aebiz.b2b2c.customermgr.mobile.web.pub;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.hibernate.service.spi.ServiceException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

/**
 * 类描述：
 * @author: Aebiz
 * @date： 日期：2014-1-3 时间：上午09:54:36
 * @version 1.0
 */
public class AppMessageManager {
	
	public static String getUserId() {
		//return SystemManager.getValueByKey("order.sms.userid");
		return "";
	}

	public static String getPassword() {
		//return SystemManager.getValueByKey("order.sms.password");
		return "";
	}

	public static String getService() {
		//return SystemManager.getValueByKey("order.sms.service");
		return "";
	}
	
	/**
	 * 短信网关,调用
	 * 
	 * @param mobile手机号
	 * @param message要发送的信息
	 * @param msgId
	 *            暂时没用
	 */
	/*
	public static boolean sendMobileMessage(String mobile, String message, String msgId) throws Exception {
		SmsService smsService = new SmsService();
		try {
			String sendMessage = message+"【工美艺城网】";
			//String result = smsService.sendSms(mobile, message, msgId);
			String result = smsService.mt(mobile, sendMessage, "", "", "");
//			String result = MessageUtil.sendSms(mobile, message, msgId);
			if (StringUtil.isEmpty(result) || result.startsWith("-")) {
				return false;
			} else {
				return true;
			}
		} catch (Exception ex) {
			throw ex;
		}
	}
	*/
	
	/**
	 * 短信网关,调用
	 * 
	 * @param mobile手机号
	 * @param message要发送的信息
	 * @param msgId
	 *            暂时没用
	 */
	public static boolean sendMobileMessage(String mobile, String message) throws ServiceException  {
		// SmsService smsService = new SmsService();
			int result = sendSms(mobile, message, "");
			if (result == 0) {
				System.out.println("短信发送成功，请查收短信。");
				return true;
			}
			return false;
	}
	
	/**
	 * 发送验证码短信
	 * @param mobile手机号
	 * @param message要发送的信息
	 * @param msgId暂时没用
	 * @return
	 */
	public static int sendSms(String mobile, String message, String msgId) {

		URL url = null;
		InputStream in = null;
		BufferedReader br = null;
		String resultStr = null;
		try {
			String realUrl = genRealUrl(mobile, message, msgId);
			url = new URL(realUrl);
			URLConnection conn = url.openConnection();
			in = conn.getInputStream();
			SAXBuilder builder = new SAXBuilder(); 
			
			Document doc = builder.build(in);
			Element foo = doc.getRootElement();
			Namespace ns = foo.getNamespace();
			String State = foo.getChildText("State", ns);
			String MsgID = foo.getChildText("MsgID", ns);
			String MsgState = foo.getChildText("MsgState", ns);
			String Reserve = foo.getChildText("Reserve", ns);
			
			System.out.println("------------------------------SMS.result--------------------------------------"); 
			System.out.println("State:" + State); 
			System.out.println("MsgID:" + MsgID); 
			System.out.println("MsgState:" + MsgState);
			System.out.println("Reserve:" + Reserve);
			System.out.println("------------------------------SMS.result--------------------------------------");
			resultStr = State;
		} catch (MalformedURLException e) {
			// logger.info("URL格式错误!");
			e.printStackTrace();
			return -1;
		} catch (Exception e) {
			// logger.info("IO错误!");
			e.printStackTrace();
			return -1;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		// if (resultStr.equals("0")) {
		// logger.info("发送短信 手机号:" + mobile + " 内容:" + message + " 成功 "
		// + resultStr);
		// } else {
		// logger.info("发送短信 手机号:" + mobile + " 内容:" + message + " 失败 "
		// + resultStr);
		// }

		int result = -1;
		try {
			result = Integer.parseInt(resultStr);
		} catch (Exception e) {
		}
		return result;
	}
	
	/**
	 * 生成短信内容url
	 * @param mobile
	 * @param message
	 * @param msgId
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static String genRealUrl(String mobile, String message, String msgId)
			throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder("http://cf.lmobile.cn/submitdata/service.asmx/g_Submit");

		sb.append("?sname=").append("dlqwss00");
		sb.append("&spwd=").append("ud7KZHT8");
		sb.append("&scorpid=").append("");
		//产品类型
		sb.append("&sprdid=").append("1012818");
		sb.append("&sdst=").append(mobile);
		//sb.append("&service=").append("ff8080812653e7600126744e010d50ab");
		//sb.append("&msgid=").append(msgId);
		String msg = URLEncoder.encode(message, "UTF-8");
		sb.append("&smsg=").append(msg);
		
		System.out.println(  sb.toString());
		return sb.toString();
	}
	
	public static void main(String[] args) throws Exception {
		String at = "测试一下短信【好欣情】";
		String test = new String(at.getBytes("UTF-8"),"UTF-8");
		//System.out.println(test);
		int result = sendSms("18701481186", "测试一下短信【好欣情】", "123");
		//System.out.println(result);
	}
}
