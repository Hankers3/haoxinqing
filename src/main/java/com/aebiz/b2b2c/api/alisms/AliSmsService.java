package com.aebiz.b2b2c.api.alisms;

import java.util.HashMap;
import java.util.Map;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.JsonUtils;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class AliSmsService {
	
	
	 public static void main(String[] args) throws Exception {
        // TOP服务地址，正式环境需要设置为http://gw.api.taobao.com/router/rest
        String serverUrl = "http://gw.api.taobao.com/router/rest";//MessageHelper.getMessage("ali_sms_url");
        String appKey = "23313071";//MessageHelper.getMessage("ali_sms_appkey"); // 可替换为您的沙箱环境应用的AppKey
        String appSecret = "04e0020db36c4e3a6b47d0b532b258dc";//MessageHelper.getMessage("ali_sms_appsecret"); // 可替换为您的沙箱环境应用的AppSecret
        //String sessionKey = "test"; // 必须替换为沙箱账号授权得到的真实有效SessionKey
 
        TaobaoClient client = new DefaultTaobaoClient(serverUrl, appKey, appSecret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        //【可选】，公共回传参数，在“消息返回”中会透传回该参数；举例：用户可以传入自己下级的会员ID，在消息返回时，该会员ID会包含在内，用户可以根据该会员ID识别是哪位会员使用了你的应用
        req.setExtend("");
        //【必须】短信类型，传入值请填写normal
        req.setSmsType("normal");
        //【必须】短信签名，传入的短信签名必须是在阿里大鱼“管理中心-短信签名管理”中的可用签名。如“阿里大鱼”已在短信签名管理中通过审核，则可传入”阿里大鱼“（传参时去掉引号）作为短信签名。短信效果示例：【阿里大鱼】欢迎使用阿里大鱼服务。
        req.setSmsFreeSignName("注册验证");
        //【可选】短信模板变量，传参规则{"key":"value"}，key的名字须和申请模板中的变量名一致，多个变量之间以逗号隔开。示例：针对模板“验证码${code}，您正在进行${product}身份验证，打死不要告诉别人哦！”，传参时需传入{"code":"1234","product":"alidayu"}
        req.setSmsParamString("{\"code\":\"1234\",\"product\":\"阿里大鱼\",\"item\":\"阿里大鱼\"}");
        //【必须】短信接收号码。支持单个或多个手机号码，传入号码为11位手机号码，不能加0或+86。群发短信需传入多个号码，以英文逗号分隔，一次调用最多传入200个号码。示例：18600000000,13911111111,13322222222
        req.setRecNum("18210320961");
        //【必须】短信模板ID，传入的模板必须是在阿里大鱼“管理中心-短信模板管理”中的可用模板。示例：SMS_585014
        req.setSmsTemplateCode("SMS_3900459");
        AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }
//	public static void main(String[] args) {
//		Map<String,String> jsonMap = new HashMap<String,String>();
//		jsonMap.put("code", "123456");
//		jsonMap.put("product", "好心情医疗平台验证码");
//		jsonMap.put("item", "阿里大鱼");
//		String jsonString = JsonUtils.getJSONString(jsonMap);
//		System.out.println(jsonString);
//	}
	 
	/**
	 * 阿里大鱼短信通道
	 * @param mobile
	 * @param smsTempMessage
	 * @param smsTempId
	 */
	 public static void sendSms(String mobile,String smsTempMessage,String smsTempId){
		System.out.println("--------------阿里大鱼sendSms----begin-------");
		String serverUrl = MessageHelper.getMessage("ali_sms_url");
        String appKey = MessageHelper.getMessage("ali_sms_appkey");
        String appSecret = MessageHelper.getMessage("ali_sms_appsecret");
        String smsFreeSignName = MessageHelper.getMessage("ali_sms_freeSignName");
        TaobaoClient client = new DefaultTaobaoClient(serverUrl, appKey, appSecret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        //【可选】，公共回传参数，在“消息返回”中会透传回该参数；举例：用户可以传入自己下级的会员ID，在消息返回时，该会员ID会包含在内，用户可以根据该会员ID识别是哪位会员使用了你的应用
        req.setExtend("");
        //【必须】短信类型，传入值请填写normal
        req.setSmsType("normal");
        //【必须】短信签名，传入的短信签名必须是在阿里大鱼“管理中心-短信签名管理”中的可用签名。如“阿里大鱼”已在短信签名管理中通过审核，则可传入”阿里大鱼“（传参时去掉引号）作为短信签名。短信效果示例：【阿里大鱼】欢迎使用阿里大鱼服务。
        req.setSmsFreeSignName("好心情");
        //【可选】短信模板变量，传参规则{"key":"value"}，key的名字须和申请模板中的变量名一致，多个变量之间以逗号隔开。示例：针对模板“验证码${code}，您正在进行${product}身份验证，打死不要告诉别人哦！”，传参时需传入{"code":"1234","product":"alidayu"}
        //req.setSmsParamString("{\"code\":\"1234\",\"product\":\"您在好心情的\",\"item\":\"阿里大鱼\"}");
        //{"product":"好心情医疗平台验证码","item":"阿里大鱼","code":"123456"}
        req.setSmsParamString(smsTempMessage);
        //【必须】短信接收号码。支持单个或多个手机号码，传入号码为11位手机号码，不能加0或+86。群发短信需传入多个号码，以英文逗号分隔，一次调用最多传入200个号码。示例：18600000000,13911111111,13322222222
        req.setRecNum(mobile);
        //【必须】短信模板ID，传入的模板必须是在阿里大鱼“管理中心-短信模板管理”中的可用模板。示例：SMS_585014
        req.setSmsTemplateCode(smsTempId);
        AlibabaAliqinFcSmsNumSendResponse rsp;
		try {
			rsp = client.execute(req);
			System.out.println("--------------阿里大鱼sendSms----end-------");
			System.out.println(rsp.getBody());
		} catch (ApiException e) {
			e.printStackTrace();
		}
	 }
	 
	 
	 
}
