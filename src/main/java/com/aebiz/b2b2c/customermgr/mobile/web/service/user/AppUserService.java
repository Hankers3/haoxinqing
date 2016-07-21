package com.aebiz.b2b2c.customermgr.mobile.web.service.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.hibernate.service.spi.ServiceException;

import com.aebiz.b2b2c.api.alisms.AliSmsService;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.JsonUtils;


/**
 * 类描述：
 * @author: Aebiz
 * @date： 日期：2013-11-27 时间：上午10:11:16
 * @version 1.0
 */
public class AppUserService {
	/**
	 * 发送验证码短信
	 *
	 * @param activeCode
	 * @return
	 * @throws ServiceException
	 */
	public static boolean sendRegCodeMobile(String mobile, String activeCode)
			throws ServiceException {
		// 发送验证码
		//String message = new StringBuffer("好心情医疗平台验证码为：").append(activeCode).append("，请及时使用此验证码。如非本人操作，请忽略此短信。【好心情医疗平台】").toString();
		Map<String,String> jsonMap = new HashMap<String,String>();
		jsonMap.put("activeCode", activeCode);
		String jsonString = JsonUtils.getJSONString(jsonMap);
		boolean result = false;
		try {
			//使用阿里大鱼短信通道发信息
			AliSmsService.sendSms(mobile, jsonString, "SMS_5038609");
			result = true;
			//result = AppMessageManager.sendMobileMessage(mobile, message);
		} catch (Exception e) {
			result = false;
		}
		return result;
	}
	/**
	 * 发送验证码短信
	 *
	 * @param activeCode
	 * @return
	 * @throws ServiceException
	 */
	public static boolean sendPassword(String mobile, String activeCode)
			throws ServiceException {
		// 发送验证码
//		String message = new StringBuffer("好心情会员登录密码为：").append(activeCode).append("，请使用此密码网页端登录，请注意保管。如非本人操作，请忽略此短信。【好心情】").toString();
		Map<String,String> jsonMap = new HashMap<>();
		jsonMap.put("activeCode", activeCode);
		String jsonString = JsonUtils.getJSONString(jsonMap);
		boolean result = false;
		try {
			//使用阿里大鱼短信通道发信息
			AliSmsService.sendSms(mobile, jsonString, "SMS_5069175");
			result = true;
			//result = AppMessageManager.sendMobileMessage(mobile, message);
		} catch (Exception e) {
			result = false;
		}
		return result;
	}
	
	
	/**
	 * 发送验证码短信
	 * @return
	 * @throws ServiceException
	 */
	public static boolean invitePeer(String mobile, String doctorName)
			throws ServiceException {
		// 发送验证码
//		String message = new StringBuffer("您的朋友").append(doctorName).append("邀请您加入全国第一个专注于精神、心理领域的医疗平台。了解好心情请点击一下链接 http://www.hxqydyl.com/page/doctorMain.html【好心情医疗平台】").toString();
		Map<String,String> jsonMap = new HashMap<String,String>();
		jsonMap.put("doctorName", doctorName);
		String jsonString = JsonUtils.getJSONString(jsonMap);
		boolean result = false;
		try {
			//使用阿里大鱼短信通道发信息
			AliSmsService.sendSms(mobile, jsonString, "SMS_5028934");
			result = true;
			//result = AppMessageManager.sendMobileMessage(mobile, message);
		} catch (Exception e) {
			result = false;
		}
		return result;
	}


	/**
	 * 生成指定长度位数验证码
	 *
	 * @param length 长度
	 * @return string
	 */
	public static String getActivatingKey(int length) {
		String[] str = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		StringBuilder buffer = new StringBuilder("");
		Random r = new Random();
		for (int i = 0; i < length; i++) {
			buffer.append(str[r.nextInt(str.length)]);
		}

		return buffer.toString();

	}
	
    
}
