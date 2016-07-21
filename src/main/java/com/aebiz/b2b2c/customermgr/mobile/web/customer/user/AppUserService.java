package com.aebiz.b2b2c.customermgr.mobile.web.customer.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.hibernate.service.spi.ServiceException;

import com.aebiz.b2b2c.api.alisms.AliSmsService;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.AppMessageManager;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.DataConst;
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
	 * @param email
	 * @param activeCode
	 * @return
	 * @throws ServiceException
	 */
	public static boolean sendRegCodeMobile(String mobile, String activeCode)
			throws ServiceException {
		// 发送验证码
//		String message = new StringBuffer("好欣情验证码为：").append(activeCode).append("，请及时使用此验证码。如非本人操作，请忽略此短信。【好欣情】").toString();
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
	 * @param email
	 * @param activeCode
	 * @return
	 * @throws ServiceException
	 */
	public static boolean sendPassword(String mobile, String activeCode)
			throws ServiceException {
		// 发送验证码
//		String message = new StringBuffer("好欣情会员登录密码为：").append(activeCode).append("，请使用此密码网页端登录，请注意保管。如非本人操作，请忽略此短信。【好欣情】").toString();
		Map<String,String> jsonMap = new HashMap<String,String>();
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
	 * 生成手机校验码
	 * 
	 * @param length
	 * @return
	 */
	public static String getActivatingKey(int length) {
		// String[] str = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
		// ,"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		String[] str = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		StringBuffer buffer = new StringBuffer("");
		Random r = new Random();
		for (int i = 0; i < length; i++) {
			buffer.append(str[r.nextInt(str.length)]);
		}

		return buffer.toString();

	}
	/**
	 * MD5加密找回密码时，生成随机密码。
	 * 获取手机验证码
	 * @param user
	 * @param request
	 * @return
	 * @throws Exception
	 */
//	public boolean getBoundedCode(String telephoneNo, HttpServletRequest request)
//			throws Exception {
//		Boolean isSuccess = false;
//		if (StringUtil.isEmpty(telephoneNo)) {
//			return false;
//		}
//		try {
//			// 发送激活短信
//			String activeCode = new StringBuffer(UserHelper.getRandomCodes(5)).append("8").toString();
//			isSuccess = sendRegCodeMobile(telephoneNo, activeCode);
//			if (isSuccess) {
//				request.getSession().setAttribute("activeCode", activeCode);//放入session
//			}
//		} catch (Exception ex) {
//			StringBuffer exceptionString = new StringBuffer();
//			exceptionString.append("当前类：").append(this.getClass()).append(
//					"。获取短信激活码，发短信异常！");
//			ShopLogs.userLog.error(exceptionString, ex);
//		}
//		return isSuccess;
//	}
	
	
	
	/**
	 * 根据四周的经纬度来判断查询点是否在其中
	 * @param zuolongitude左侧经度
	 * @param youlongitude右侧经度
	 * @param shanglatitude上方纬度
	 * @param xialatitude下方纬度
	 * @param longitude查询点的经度 
	 * @param latitude查询点纬度
	 * @return
	 */
    public static String GetDistances(String cityId,double longitude,double latitude)
    {
    	/*北京*/
    	if ("110100".equals(cityId)) {
    		//左侧经度减去查询点的经度 
        	double a = DataConst.BJzuolongitude-longitude;
        	//右侧经度减去查询点的经度
        	double b = DataConst.BJyoulongitude-longitude;
        	//上方纬度减去查询点纬度
        	double c = DataConst.BJshanglatitude-latitude;
        	//下方纬度减去查询点纬度
        	double d = DataConst.BJxialatitude-latitude;
        	
        	//如果查询点在左侧经度右边，在右侧经度左边，在上方纬度下面，在下方纬度上面，则此查询点可以注册。
        	if (a<0 && b>0 && c>0 && d<0) {
    			return "true";
    		}else {
    			return "false";
    		}
		}else if ("310100".equals(cityId)) {/*上海*/
			//左侧经度减去查询点的经度 
	    	double a = DataConst.SHzuolongitude-longitude;
	    	//右侧经度减去查询点的经度
	    	double b = DataConst.SHyoulongitude-longitude;
	    	//上方纬度减去查询点纬度
	    	double c = DataConst.SHshanglatitude-latitude;
	    	//下方纬度减去查询点纬度
	    	double d = DataConst.SHxialatitude-latitude;
	    	
	    	//如果查询点在左侧经度右边，在右侧经度左边，在上方纬度下面，在下方纬度上面，则此查询点可以注册。
	    	if (a<0 && b>0 && c>0 && d<0) {
				return "true";
			}else {
				return "false";
			}
		}else if ("120100".equals(cityId)) {/*天津*/
			//左侧经度减去查询点的经度 
	    	double a = DataConst.TJzuolongitude-longitude;
	    	//右侧经度减去查询点的经度
	    	double b = DataConst.TJyoulongitude-longitude;
	    	//上方纬度减去查询点纬度
	    	double c = DataConst.TJshanglatitude-latitude;
	    	//下方纬度减去查询点纬度
	    	double d = DataConst.TJxialatitude-latitude;
	    	
	    	//如果查询点在左侧经度右边，在右侧经度左边，在上方纬度下面，在下方纬度上面，则此查询点可以注册。
	    	if (a<0 && b>0 && c>0 && d<0) {
				return "true";
			}else {
				return "false";
			}
		}
    	return "false";
    	
    	
    }
    
    
}
