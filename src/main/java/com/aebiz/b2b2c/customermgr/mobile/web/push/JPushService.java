package com.aebiz.b2b2c.customermgr.mobile.web.push;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.PushPayload.Builder;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

/**
 * 激光推送消息实现类
 * @author xueli
 *
 */
public class JPushService {
	//用户类型 1：医生
    public static final String USER_DOCTOR = "1";
    //用户类型 2：患者
    public static final String USER_CUSTOMER = "2";
	
	//注入医生service
	@Autowired
	private ServicestaffService servicestaffService;
	
	//推送消息类型：系统通知：1、消息：2
	/**
	 * 推送系统消息
	 * @param userIds别名
	 * @param map推送信息
	 * @return
	 * @throws Exception
	 */
	public static String pushSystemMessage(List<String> userIds ,Map<String,String> map) {
		//推送平台
		String pushClient = map.get("pushClient");
		//客户端(ios)
		JPushClient jpushClient = null;
		//客户端(安卓)
		JPushClient jpushClienta = null;
		if (USER_CUSTOMER.equals(pushClient)) {
			jpushClient = new JPushClient(PushConst.MASTERSECRET_CUSTOMER_IOS, PushConst.APPKEY_CUSTOMER_IOS, 3);
			jpushClienta = new JPushClient(PushConst.MASTERSECRET_CUSTOMER, PushConst.APPKEY_CUSTOMER, 3);
		}else if (USER_DOCTOR.equals(pushClient)) {
			//医生端
			jpushClient = new JPushClient(PushConst.MASTERSECRET_SERVICE_IOS, PushConst.APPKEY_SERVICE_IOS, 3);
			jpushClienta = new JPushClient(PushConst.MASTERSECRET_SERVICE, PushConst.APPKEY_SERVICE, 3);
		}
		
		PushPayload payload_Android = null;
		PushPayload payload_IOS = null;
		
		//推送系统通知(客户端)
		if (USER_CUSTOMER.equals(pushClient)) {
			payload_Android = buildPushSystemCustomerAndroid(userIds,map);
			payload_IOS = buildPushSystemCustomerIOS(userIds,map);
		}
		//推送系统通知(医生端)
		if (USER_DOCTOR.equals(pushClient)) {
			payload_Android = buildPushSystemServiceAndroid(userIds, map);
			payload_IOS = buildPushSystemServiceIOS(userIds, map);
		}
		PushResult result = null;
		
		try {
			//推送Android
			result = jpushClienta.sendPush(payload_Android);
			System.out.println("消息推送安卓 成功");
		} catch (APIConnectionException e) {
			//e.printStackTrace();
			System.out.println("消息推送安卓fail");
		} catch (APIRequestException e) {
			///e.printStackTrace();
			System.out.println("消息推送安卓fail");
		}
		
		try {
			//推送IOS
			result = jpushClient.sendPush(payload_IOS);
			System.out.println("消息推送IOS 成功");
		} catch (APIConnectionException e) {
			//e.printStackTrace();
			System.out.println("消息推送IOS fail");
		} catch (APIRequestException e) {
			System.out.println("消息推送IOS fail");
			//e.printStackTrace();
		}
		
		if(result!=null){
			return "push_success";
		}else{
			return "push_fail";
		}
		
		
		
	}
    
    /**
     * 推送系统通知(通知到客户端【Android】)
     * @param message_title
     * @return
     */
    public static PushPayload buildPushSystemCustomerAndroid(List<String> userIds,Map<String, String> map) {
        return new Builder()
            .setPlatform(Platform.android())
             .setAudience(Audience.alias(userIds))
            .setNotification(Notification.android(map.get("message"),"好心情系统通知", map)).build();
          
            
    }
    
    /**
     * 推送系统通知(通知到客户端【IOS】)
     * @param message_title
     * @return
     */
    public static PushPayload buildPushSystemCustomerIOS(List<String> userIds,Map<String, String> map) {
        return new Builder()
            .setPlatform(Platform.ios())
            .setAudience(Audience.alias(userIds))
            .setNotification(Notification.ios(map.get("message"), map)).build();
            
    }
    /**
     * 推送系统通知(通知到医生端【Android】)
     * @param message_title
     * @return
     */
    public static PushPayload buildPushSystemServiceAndroid(List<String> userIds,Map<String, String> map) {
        return new Builder()
            .setPlatform(Platform.android())
            .setAudience(Audience.alias(userIds))
            .setNotification(Notification.android(map.get("message"),"好心情系统通知", map)).build();
    }
    
    /**
     * 推送系统通知(通知到医生端【IOS】)
     * @param message_title
     * @return
     */
    public static PushPayload buildPushSystemServiceIOS(List<String> userIds,Map<String, String> map) {
        return new Builder()
            .setPlatform(Platform.ios())
            .setAudience(Audience.alias(userIds))
            .setNotification(Notification.ios(map.get("message"), map)).build();
        //setOptions(new Options(false)) 切换正式环境与测试环境
    }
    
    /**
     * 使用广播测试
     * @param message_title
     * @return
     * @throws Exception 
     */
    public static PushPayload testAll(List<String> userIds,Map<String, String> map) {
    	//医生端
    	JPushClient jpushClient = null;
    	//JPushClient jpushClienta = null;
    	//患者端
    	jpushClient = new JPushClient(PushConst.MASTERSECRET_SERVICE_IOS, PushConst.APPKEY_SERVICE_IOS, 3);
    	//jpushClienta = new JPushClient(PushConst.MASTERSECRET_CUSTOMER, PushConst.APPKEY_CUSTOMER, 3);
    	//PushPayload payload_Android = null;
		PushPayload payload_IOS = null;
		//payload_Android = buildPushSystemCustomerAndroid(userIds, map);
		map.put("url", "http://docs.jpush.cn/display/dev/Push-API-v3");
		payload_IOS = buildPushSystemServiceIOS(userIds, map);

    	try {
    	        //jpushClienta.sendPush(payload_Android);
    	        jpushClient.sendPush(payload_IOS);
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		} catch (Exception e) {
                e.printStackTrace();
        }
		return payload_IOS;
            
    }
    
    
    public static void main(String[] args) {
    
		// 消息发送
		Map<String, String> map = new HashMap<String, String>();
		// 消息体
		map.put("message", "11111111111");
		map.put("sendTime", DateFormatHelper.getNowTimeStr());
		map.put("pushType", PushConst.push_type_system);
		
    	new Builder().setAudience(Audience.all())
         .setNotification(Notification.android("好心情系统通知", "好心情系统通知", map))
         .setNotification(Notification.ios("好心情系统通知", map)).build();
    	
	}

}
