package com.aebiz.b2b2c.customermgr.mobile.web.wxpay;

import java.io.BufferedOutputStream;
import java.io.StringReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.InputSource;

import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.innermessage.service.InnerMessageService;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageModel;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageTypeEnum;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoModel;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.HttpServletUtils;
import com.aebiz.b2b2c.customermgr.mobile.web.push.PushConst;
import com.aebiz.b2b2c.finance.customeraccount.service.CustomerAccountInteractive;
import com.aebiz.b2b2c.finance.customeraccount.service.CustomerAccountService;
import com.aebiz.b2b2c.order.orderdetail.service.OrderDetailService;
import com.aebiz.b2b2c.order.ordermain.service.OrderMainService;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderStatusEnum;
import com.aebiz.b2b2c.order.ordermainlog.service.OrderMainLogService;
import com.aebiz.b2b2c.order.ordermainpay.service.OrderMainPayService;
import com.aebiz.b2b2c.servicestaff.packagemanagement.service.PackageManagementService;
import com.aebiz.b2b2c.servicestaff.packagemanagement.vo.PackageManagementModel;
import com.aebiz.b2b2c.thirdinterface.weixinPay.config.WeixinConfig;
import com.aebiz.b2b2c.thirdinterface.weixinPay.util.GetWxOrderno;
import com.aebiz.b2b2c.thirdinterface.weixinPay.util.RequestHandler;
import com.aebiz.b2b2c.thirdinterface.weixinPay.util.TenpayUtil;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomercharge.service.VirtualAccountCustomerChargeService;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomercharge.vo.VirtualAccountCustomerChargeModel;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomerlog.service.VirtualAccountCustomerLogService;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomerlog.vo.VirtualAccountCustomerLogModel;
import com.aebiz.b2b2c.websiteoperation.websiteoperate.service.WebsiteOperateInteractive;
import com.alibaba.fastjson.JSON;

/**
 * 微信支付
 * 
 * @author xl
 * @date 2016-01-20
 */
@Controller
@RequestMapping("/app/weixinpay")
public class WeiXinPayController {

	@Autowired
	private OrderMainService orderMainService;
	
	@Autowired
	private WebsiteOperateInteractive webOperateInterative;
	
	@Autowired
	private CustomerAccountInteractive customerAccountInteractive;
	
	@Autowired
	private InnerMessageService innerMsgService;
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	@Autowired
	private CustomerAccountService customerAccountService;
	
	@Autowired
	private OrderMainLogService orderMainLogService;

	@Autowired
	private OrderMainPayService orderMainPayService;
	
	@Autowired
	private VirtualAccountCustomerLogService virtualAccountCustomerLogService;
	
	@Autowired
	private VirtualAccountCustomerChargeService virtualAccountCustomerChargeService;
	@Autowired
	private VirtualAccountCustomerLogService accountCustomerLogService;
	
	/* 患者信息service */
	@Autowired
	private CustomerService customerService;
	/* 医生私人套餐 */
	@Autowired
	private PackageManagementService packageManagementService;
	
    //log4j记录日志
    private static Logger log = Logger.getLogger("weixinPayBack_logger");
	/*消息service*/
	@Autowired
	private InnerMessageService inmessageService;
	
	/**
	 * 微信统一订单
	 * 
	 * @author xl
	 * @date 2016-01-20
	 * @param uuid
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toPayOrder", method = { RequestMethod.POST })
	@ResponseBody
	public String toPayOrder(@RequestParam("orderId") String orderId,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {

		log.info("====微信统一下单接口toPayOrder====");
		log.info("====微信统一下单接口orderId===="+orderId);
		boolean orderFlag = orderId.contains("order");
		boolean chargeFlag= orderId.contains("charge");
		
		log.info("====微信统一下单接口orderFlag===="+orderFlag);
		log.info("====微信统一下单接口chargeFlag===="+chargeFlag);

		String jsonStr = "";
		// 订单应付总金额
		double orderMoney = 0;
		// 订单支付
		if(orderFlag){
			OrderMainModel order = orderMainService.getByUuid(orderId);
			if (order != null) {
				orderMoney = order.getPayMoney();
			}
			log.info("====微信统一下单接口====order====="+JSON.toJSONString(order));
		}
		
		//充值支付
		if(chargeFlag){
			VirtualAccountCustomerChargeModel virtualAccountCustomerChargeModel = virtualAccountCustomerChargeService.getVirtualAccountCustomerChargeModelByOrderUuid(orderId);
			if (virtualAccountCustomerChargeModel != null) {
				orderMoney = virtualAccountCustomerChargeModel.getOperAmount();
			}
			log.info("====微信统一下单接口====virtualAccountCustomerChargeModel====="+JSON.toJSONString(virtualAccountCustomerChargeModel));
		}
		// 付款金额
		DecimalFormat df1 = new DecimalFormat("###0.00");
		String appid = WeixinConfig.APP_ID;
		String partnerkey = WeixinConfig.APP_KEY;
		String mchId = WeixinConfig.APP_MCHID;
		String appsecret = WeixinConfig.APP_SECRET;
		// 总金额以分为单位，不带小数点
		// 格式转换
		DecimalFormat dformat = new DecimalFormat("0");
		// 交易金额 转换为以 分为单位
		String totalFee = dformat.format(Double.parseDouble(df1.format(orderMoney)) * 100);

		// 订单生成的机器 IP
		String spbill_create_ip = this.localIp();
		// 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
		//String notify_url = MessageHelper.getMessage("weixinpay.notify.url");
		String notify_url = "http://admin.hxqydyl.com/app/weixinpay/notifyUrl";
		log.info("====微信统一下单接口====notify_url====="+notify_url);
		
		String trade_type = "APP"; 
		// 随机字符串
		String nonce_str = getNonceStr();
		// 商品描述根据情况修改
		String body = "好心情-请核实是否为本人订单，谨防被骗";
		// 商户订单号
		String out_trade_no = orderId;

		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mchId);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", body);
		packageParams.put("out_trade_no", out_trade_no);
		// 这里写的金额为1 分到时修改
		String total_fee="1";
		if(!StringUtil.isEmpty(totalFee)&&!"0".equals(totalFee)){		
			total_fee=totalFee+"";
			packageParams.put("total_fee",totalFee);
		}else{
			//做测试使用  正式上线需要屏蔽
			packageParams.put("total_fee",total_fee);
		}
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("notify_url", notify_url);
		packageParams.put("trade_type", trade_type);

		RequestHandler reqHandler = new RequestHandler(null, null);
		reqHandler.init(appid, appsecret, partnerkey);
		String sign = reqHandler.createSign(packageParams);
		String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>"
				+ mchId + "</mch_id>" + "<nonce_str>" + nonce_str
				+ "</nonce_str>" + "<sign>" + sign + "</sign>"
				+ "<body><![CDATA[" + body + "]]></body>"
				+ "<out_trade_no>" + out_trade_no + "</out_trade_no>"
				+ "<total_fee>"+ total_fee + "</total_fee>" 
				+ "<spbill_create_ip>"+ spbill_create_ip + "</spbill_create_ip>" 
				+ "<notify_url>"+ notify_url + "</notify_url>" 
				+ "<trade_type>"+ trade_type + "</trade_type>" 
				+ "</xml>";
		
		log.info("====微信统一下单接口====xml====="+xml);
		
		System.out.println("====微信统一下单接口====xml====="+xml);
		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		jsonStr = new GetWxOrderno().getCodeUrl(createOrderURL, xml);
		System.out.println("=========结束========" + jsonStr);
		log.info("====微信统一下单接口====jsonStr====="+jsonStr);
		Map<Object, Object> jsonMap  = new HashMap<Object,Object>();;
		Map m = parseXmlToList2(jsonStr);
		String return_code =m.get("return_code").toString();
		if(!StringUtil.isEmpty(return_code)&&"SUCCESS".equals(return_code)){
			String  prepay_id = m.get("prepay_id").toString();
			nonce_str = m.get("nonce_str").toString();
			String timestamp = getTimeStamp();
			SortedMap<String, String> pacarams = new TreeMap<String, String>();
			pacarams.put("appid", appid);
			pacarams.put("noncestr", nonce_str);
			pacarams.put("package", "Sign=WXPay");
			pacarams.put("partnerid", mchId);
			pacarams.put("prepayid", prepay_id);
			pacarams.put("timestamp", timestamp);
			
			RequestHandler reqHandler1 = new RequestHandler(null, null);
			reqHandler1.init(appid, appsecret, partnerkey);
			String resign = reqHandler1.createSign(pacarams);
			System.out.println("resign==============="+resign);
			
			jsonMap.put("sign", resign);
			jsonMap.put("prepay_id", prepay_id);
			jsonMap.put("nonce_str", nonce_str);
			jsonMap.put("timestamp", timestamp);
		}
		log.info("====微信统一下单接口====Map====="+JSON.toJSONString(m));
		System.out.println("====微信统一下单接口====Map====="+JSON.toJSONString(m));
		// 返回数据
		HttpServletUtils.outJson(response, jsonMap);
		return "";
	}
	/**
	 * 获取随机字符串
	 * 
	 * @return
	 */
	public static String getNonceStr() {
		// 随机数
		String currTime = TenpayUtil.getCurrTime();
		// 8位日期
		String strTime = currTime.substring(8, currTime.length());
		// 四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		// 10位序列号,可以自行调整。
		return strTime + strRandom;
	}
	
	/**
	 * 获取时间戳
	 * 
	 * @return
	 */
	public static String getTimeStamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}
	
	/**
	 * 微信支付回调
	 * 
	 * @author xl
	 * @date 2016-01-20
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/notifyUrl", method = { RequestMethod.POST })
	public String notifyUrl(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		log.info("====微信支付回调====notifyUrl====");
		String inputLine;
		String notityXml = "";
		String resXml = "";
		try {
			while ((inputLine = request.getReader().readLine()) != null) {
				notityXml += inputLine;
			}
			request.getReader().close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		System.out.println("====微信支付回调====notityXml===="+ notityXml);
		log.info("====微信支付回调====notityXml===="+notityXml);
		Map m = parseXmlToList2(notityXml);
		WxPayResult wpr = new WxPayResult();
		wpr.setOutTradeNo(m.get("out_trade_no").toString());
		wpr.setResultCode(m.get("result_code").toString());
		wpr.setReturnCode(m.get("return_code").toString());
		String orderId= m.get("out_trade_no").toString();
		
		System.out.println("====orderId===="+ orderId);
		boolean orderFlag = orderId.contains("Order");
		boolean chargeFlag= orderId.contains("charge");
		System.out.println("====chargeFlag===="+ chargeFlag);

		if ("SUCCESS".equals(wpr.getResultCode())) {
			// 支付成功
			resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
					+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
			// 订单支付
			if(orderFlag){
				OrderMainModel order = this.orderMainService.getByUuid(wpr.getOutTradeNo());
				if(order !=null){
					order.setState(OrderStatusEnum.PAID.getValue());
					orderMainService.update(order);
				}
				
				String orderType = order.getOrderType();
				String packageUuid = order.getPackageUuid();
				String customerUuid=order.getCustomerUuid();
				CustomerModel user = customerService.getByUuid(customerUuid);
				String doctorUuid=order.getDoctorUuid();
				double orderAmount = order.getTotalMoney();
				String consultDuration = order.getConsultDuration();
				String bookTime = order.getBookTime();
				String receiveTime = order.getReceiveTime();
				String endTime = order.getEndTime();
				String packName="";
				String message="";
				
				if(!StringUtil.isEmpty(orderType)&&"2".equals(orderType)){
					PackageManagementModel packageManagement = null;
					if (!StringUtil.isEmpty(packageUuid)) {
						packageManagement = packageManagementService.getByUuid(packageUuid);
						packName = packageManagement.getPackageName();
					}
				}
				
				//私人套餐
				if(!StringUtil.isEmpty(orderType)&&"2".equals(orderType)){
					message=user.getRealName()+ "已成功购买了您的"+packName +"私人医生套餐服务" ;
				}else{
					message=user.getRealName()+ "已成功购买了您的"+ consultDuration+"分钟-"+orderAmount+"元 的电话咨询服务，预约通话时间" ;
					message=message+bookTime+" "+ receiveTime+" 至 "+endTime;
				}
				//保存到innerMessage 
				inmessageService.saveInnerMessageAndPush(customerUuid, doctorUuid, message,
						InnerMessageModel.ACCOUNT_TYPE_CUSTOMER,InnerMessageModel.ACCOUNT_TYPE_STORE,
						PushConst.push_client_service, "orderUuid", order.getUuid(),InnerMessageTypeEnum.ORDER.getValue());
				
			}
			// 充值支付
			if(chargeFlag){
				// 获取订单
				VirtualAccountCustomerChargeModel virtualAccountCustomerChargeModel = virtualAccountCustomerChargeService.getVirtualAccountCustomerChargeModelByOrderUuid(wpr.getOutTradeNo());
				if(virtualAccountCustomerChargeModel !=null){
					virtualAccountCustomerChargeModel.setChargeState(VirtualAccountCustomerChargeModel.PAY_SUCCESS);
					virtualAccountCustomerChargeService.update(virtualAccountCustomerChargeModel);
					//获取充值金额
					double balance =virtualAccountCustomerChargeModel.getOperAmount();
					//获取充值会员账户名
					String customerUuid = virtualAccountCustomerChargeModel.getCustomerUuid();
					//获取用户对象 更改用户对象的余额信息
					CustomerModel cm = customerService.getByUuid(customerUuid);
					System.out.println("========================cm======>"+JSON.toJSONString(cm));

					double account = 0.0;
					if(cm !=null){
						account = cm.getAccountAmount();
						//保存现在的余额
						cm.setAccountAmount(account+balance);
						customerService.update(cm);
					}
					//根据充值订单号判断此次充值操作的日志是否已经存在
					VirtualAccountCustomerLogModel vacl=accountCustomerLogService.getVirtualAccountCustomerLogModelByOrderUuid(wpr.getOutTradeNo());
					if(vacl==null){
						//充值操作日志
						VirtualAccountCustomerLogModel accountCustomerLogModel= new VirtualAccountCustomerLogModel();
						accountCustomerLogModel.setCustomerUuid(customerUuid);
						//操作类型
						accountCustomerLogModel.setOperType(VirtualAccountCustomerLogModel.IN);
						//操作金额
						accountCustomerLogModel.setOperAmount((float)balance);
						//操作描述--------------已修改，去掉操作人信息。
						accountCustomerLogModel.setDescription("充值时间："+DateFormatHelper.getNowTimeStr() +"充值金额："+balance+"元");
						accountCustomerLogModel.setNowBalance(account+balance);
						accountCustomerLogModel.setOrderMainUuid(wpr.getOutTradeNo());
						accountCustomerLogService.create(accountCustomerLogModel);
						System.out.println("===============================>"+customerUuid+"进行账号充值操作。充值时间："+DateFormatHelper.getNowTimeStr() +"充值金额："+balance+"元");
					}
				}
			}
			
		} else {
			System.out.println("*********zdxj weixinhuidiao***8**pay fail***");
			resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
					+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
		}
		log.info("====微信支付回调====resXml===="+resXml);

		System.out.println("========resXml=========="+resXml);
		try {
			BufferedOutputStream out = new BufferedOutputStream(
					response.getOutputStream());
			out.write(resXml.getBytes());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	/**
	 * 查询订单支付信息
	 * 
	 * @author xl
	 * @date 2016-01-20
	 * @param uuid
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/orderquery", method = { RequestMethod.POST })
	@ResponseBody
	public String orderquery(@RequestParam("orderId") String orderId,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		log.info("====查询微信支付订单====orderquery====");

		System.out.println("========orderquery开始========");
		System.out.println("=========开始========");
		// 订单组
		String appid = WeixinConfig.APP_ID;
		String partnerkey = WeixinConfig.APP_KEY;
		String mchId = WeixinConfig.APP_MCHID;
		String appsecret = WeixinConfig.APP_SECRET;

		// 随机字符串
		String nonce_str = getNonceStr();
		// 商户订单号
		String out_trade_no = orderId;

		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mchId);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("out_trade_no", out_trade_no);

		RequestHandler reqHandler = new RequestHandler(null, null);
		reqHandler.init(appid, appsecret, partnerkey);
		String sign = reqHandler.createSign(packageParams);
		
		StringBuffer xmlRe =new StringBuffer("");
		xmlRe.append("<xml>");
		xmlRe.append("<appid>").append(appid).append("</appid>");
		xmlRe.append("<mch_id>").append(mchId).append("</mch_id>");
		xmlRe.append("<nonce_str>").append(nonce_str).append("</nonce_str>");
		xmlRe.append("<sign>").append(sign).append("</sign>");
		xmlRe.append("<out_trade_no>").append(out_trade_no).append("</out_trade_no>");
		xmlRe.append("</xml>");
		
		log.info("====查询微信支付订单====xmlRe===="+xmlRe.toString());
		
	/*	String xml = "<xml>" + "<appid>" + appid + "</appid>" 
				+ "<mch_id>"+ mchId + "</mch_id>" 
				+ "<nonce_str>" + nonce_str+ "</nonce_str>" 
				+ "<sign>" + sign + "</sign>"
				+ "<out_trade_no>" + out_trade_no + "</out_trade_no>"
				+ "</xml>";*/

		String createOrderURL = "https://api.mch.weixin.qq.com/pay/orderquery";
		String jsonStr  = new GetWxOrderno().getCodeUrl(createOrderURL, xmlRe.toString());
		Map m = parseXmlToList2(jsonStr);
		Map<Object, Object> jsonMap  = new HashMap<Object,Object>();;
		String return_code =m.get("return_code").toString();
		if(!StringUtil.isEmpty(return_code)&&"SUCCESS".equals(return_code)){
			String  resign = m.get("sign").toString();
			String  result_code = m.get("result_code").toString();
			nonce_str = m.get("nonce_str").toString();
			out_trade_no = m.get("out_trade_no").toString();
			String  trade_state = m.get("trade_state").toString();
			String  trade_state_desc = m.get("trade_state_desc").toString();
			jsonMap.put("sign", resign);
			jsonMap.put("result_code", result_code);
			jsonMap.put("nonce_str", nonce_str);
			jsonMap.put("out_trade_no", out_trade_no);
			jsonMap.put("trade_state", trade_state);
			jsonMap.put("trade_state_desc", trade_state_desc);
		}
		// 返回数据
		HttpServletUtils.outJson(response, jsonMap);
		log.info("====查询微信支付订单====jsonStr===="+JSON.toJSONString(m));

		
		System.out.println("=========orderquery结束========" + jsonStr);
		
		return "";

	}
	
	/**
	 * description: 解析微信通知xml
	 * 
	 * @param xml
	 * @return
	 * @author
	 * @see
	 */
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	private static Map parseXmlToList2(String xml) {
		Map retMap = new HashMap();
		try {
			StringReader read = new StringReader(xml);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			SAXBuilder sb = new SAXBuilder();
			// 通过输入源构造一个Document
			Document doc = (Document) sb.build(source);
			Element root = doc.getRootElement();// 指向根节点
			List<Element> es = root.getChildren();
			if (es != null && es.size() != 0) {
				for (Element element : es) {
					retMap.put(element.getName(), element.getValue());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retMap;
	}

	/**
	 * 获取本地ip
	 * 
	 * @author xl
	 * @date 2016-01-20
	 * @return
	 */
	private String localIp() {
		String ip = "";
		Enumeration allNetInterfaces;
		try {
			allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			while (allNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces
						.nextElement();
				List<InterfaceAddress> InterfaceAddress = netInterface
						.getInterfaceAddresses();
				for (InterfaceAddress add : InterfaceAddress) {
					InetAddress Ip = add.getAddress();
					if (Ip != null && Ip instanceof Inet4Address) {
						ip = Ip.getHostAddress();
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return ip;
	}
	
	public static void main(String[] args) {
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", "wx5ceaf6c529acb913");
		packageParams.put("nonce_str", "N8EvujTKMczwtFnW");
		packageParams.put("prepay_id", "wx201601251113135b8f29023b0881149620");
		packageParams.put("package", "=Sign=WXPay");
		packageParams.put("timestamp", "1453691593");
		
		String appid = WeixinConfig.APP_ID;
		String partnerkey = WeixinConfig.APP_KEY;
		String mchId = WeixinConfig.APP_MCHID;
		String appsecret = WeixinConfig.APP_SECRET;
		
		RequestHandler reqHandler = new RequestHandler(null, null);
		reqHandler.init(appid, appsecret, partnerkey);
		String sign = reqHandler.createSign(packageParams);
		
		System.out.println(sign);
	}
}
