package com.aebiz.b2b2c.customermgr.sysback.web.alipay;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.innermessage.service.InnerMessageService;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageModel;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageTypeEnum;
import com.aebiz.b2b2c.customermgr.alipay.config.AlipayConfig;
import com.aebiz.b2b2c.customermgr.alipay.service.AlipayService;
import com.aebiz.b2b2c.customermgr.alipay.util.AlipayNotify;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.customermgr.customerinfo.service.CustomerInfoService;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoModel;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.AppBaseController;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.SysConst;
import com.aebiz.b2b2c.customermgr.mobile.web.push.PushConst;
import com.aebiz.b2b2c.finance.customeraccount.service.CustomerAccountInteractive;
import com.aebiz.b2b2c.order.ordermain.service.OrderMainService;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderStatusEnum;
import com.aebiz.b2b2c.order.ordermainlog.service.OrderMainLogService;
import com.aebiz.b2b2c.servicestaff.packagemanagement.service.PackageManagementService;
import com.aebiz.b2b2c.servicestaff.packagemanagement.vo.PackageManagementModel;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomercharge.service.VirtualAccountCustomerChargeService;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomercharge.vo.VirtualAccountCustomerChargeModel;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomerlog.service.VirtualAccountCustomerLogService;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomerlog.vo.VirtualAccountCustomerLogModel;
import com.alibaba.fastjson.JSON;
/**
 * 支付宝支付代码
 * @author xueli
 *
 */
@Controller
@RequestMapping("/alipay")
public class AlipayController extends AppBaseController{
	
	@Autowired
	private AlipayService myService;
	
	public AlipayController(){
		super("alipay","notify_url",AlipayController.class);
	}
	
	@Autowired
	private OrderMainService orderMainService;
	@Autowired
	private OrderMainLogService orderLogService;
	
	/* 消息service */
	@Autowired
	private InnerMessageService innerMessageService;
	
	/* 患者信息service */
	@Autowired
	private CustomerService customerService;
	
	/* 患者基础信息service */
	@Autowired
	private CustomerInfoService customerInfoService;
	
	/* 医生私人套餐 */
	@Autowired
	private PackageManagementService packageManagementService;
	
	@Autowired
	private CustomerAccountInteractive customerAccountInteractive;
	
	@Autowired
	private VirtualAccountCustomerChargeService virtualAccountCustomerChargeService;
	@Autowired
	private VirtualAccountCustomerLogService accountCustomerLogService;
	
    //log4j记录日志
    private static Logger log = Logger.getLogger("alipay_logger");
    
	/**
	 * 支付回调
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/order_notify", method = RequestMethod.POST)
	public String order_notify(
			HttpServletRequest request,
			HttpServletResponse response){
		log.info("支付宝支付成功回调页面======》");

		String  netpayname = "";
		String  netPayAccount = "";
		String  netPayKey = "";
		String  netPayPartners = "";
		
		netpayname = "alipay";
		netPayKey = AlipayConfig.netPayKey;
		netPayAccount = AlipayConfig.SELLER;
		netPayPartners = AlipayConfig.partner;
		
		log.info(request.getRequestURL() + "?" + request.getQueryString());
		
		String partner = netPayPartners; //支付宝合作伙伴id (账户内提取)
		String privateKey = netPayKey; //支付宝安全校验码(账户内提取)
		//**********************************************************************************
		//如果您服务器不支持https交互，可以使用http的验证查询地址
		/*注意下面的注释，如果在测试的时候导致response等于空值的情况，请将下面一个注释，打开上面一个验证连接，另外检查本地端口，
		  请挡开80或者443端口*/
		//String alipayNotifyURL = "https://www.alipay.com/cooperate/gateway.do?service=notify_verify"
		String alipayNotifyURL = "http://notify.alipay.com/trade/notify_query.do?"
				+ "partner="
				+ partner
				+ "&notify_id="
				+ request.getParameter("notify_id");
		//**********************************************************************************

		//获取支付宝ATN返回结果，true是正确的订单信息，false 是无效的
		//String responseTxt = CheckURL.check(alipayNotifyURL);
		String responseTxt = "true"; 
		System.out.println("get param");
		Map params = new HashMap();
		//获得POST 过来参数设置到新的params中
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			
			log.info(name + "=="  +valueStr);
			/*乱码解决，这段代码在出现乱码时使用,但是不一定能解决所有的问题，所以建议写过滤器实现编码控制。
			如果mysign和sign不相等也可以使用这段代码转化*/
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "GBK"); //乱码解决
			params.put(name, valueStr);
		}
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号
		String orderId = "";
		orderId = request.getParameter("out_trade_no");
		log.info("======支付订单编号======orderId======"+orderId);
		//支付宝交易号
		try {
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//status为支付宝返回状态交易状态
		String trade_status = "";
		try {
			trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		log.info("======支付返回状态======trade_status======"+trade_status);

		//付款金额
		String get_total_fee = request.getParameter("total_fee");
		double balance = Double.parseDouble(get_total_fee);
		String status = "";
		boolean orderFlag = orderId.contains("Order");
		boolean chargeFlag= orderId.contains("charge");
		System.out.println("=================orderFlag======================================"+orderFlag);
		System.out.println("=================chargeFlag======================================"+chargeFlag);

		//订单支付
		if(orderFlag){
			if(AlipayNotify.verify(params)){//验证成功
				//////////////////////////////////////////////////////////////////////////////////////////
				//请在这里加上商户的业务逻辑程序代码
				//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
				OrderMainModel orderMM = orderMainService.getByUuid(orderId);
				if(trade_status.equals("TRADE_FINISHED")){
					//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
					//注意：
					//该种交易状态只在两种情况下出现
					//1、开通了普通即时到账，买家付款成功后。
					//2、开通了高级即时到账，从该笔交易成功时间算起，过了签约时的可退款时限（如：三个月以内可退款、一年以内可退款等）后。
					//在这里可以写入数据处理,
					System.out.println("=================TRADE_FINISHED======================================");
					//更改订单状态
					status = myService.payNotify(request, orderId, Double.parseDouble(get_total_fee), trade_status);
					System.out.println("=================payNotify_SUCCESS__1======================================");
					//交易成功   注意一定要返回给支付宝一个成功的信息
					try {
						response.getWriter().print("SUCCESS");
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				} else if (trade_status.equals("TRADE_SUCCESS")){
					//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
					System.out.println("=================TRADE_SUCCESS======================================");
					//更改订单状态
					status = myService.payNotify(request, orderId, Double.parseDouble(get_total_fee), trade_status);
					System.out.println("=================payNotify_SUCCESS__2======================================");
					
					//交易成功
					//out.println("success"); //注意一定要返回给支付宝一个成功的信息
					if ("true".equals(status)) {
						System.out.println("=================payNotify_SUCCESS__3======================================");
						orderMM.setState(OrderStatusEnum.PAID.getValue());
						orderMainService.update(orderMM);
						
					
						//交易成功   注意一定要返回给支付宝一个成功的信息
						if ("true".equals(status)) {
							try {
								response.getWriter().print("SUCCESS");
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						//订单操作日志
						orderLogService.createOrderLog(orderId, "会员支付完成",
								"3",orderMM.getCustomerUuid(),"3", "3",OrderStatusEnum.PAID.getValue(),orderMM.getPayType());
						
						/*------------------------------------------消息推送------------------------------------------*/
						String orderType = orderMM.getOrderType();
						String packageUuid = orderMM.getPackageUuid();
						String customerUuid=orderMM.getCustomerUuid();
						CustomerInfoModel user = customerInfoService.getCustomerInfoModelByCustomerUuid(customerUuid);
						String doctorUuid=orderMM.getDoctorUuid();
						double orderAmount = orderMM.getTotalMoney();
						String consultDuration = orderMM.getConsultDuration();
						String bookTime = orderMM.getBookTime();
						String receiveTime = orderMM.getReceiveTime();
						String endTime = orderMM.getEndTime();
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
						
						// 保存到innerMessage
						innerMessageService.saveInnerMessageAndPush(customerUuid, doctorUuid, message,
								InnerMessageModel.ACCOUNT_TYPE_CUSTOMER,InnerMessageModel.ACCOUNT_TYPE_STORE,
								PushConst.push_client_service, "orderUuid", orderMM.getUuid(),InnerMessageTypeEnum.ORDER.getValue());
						System.out.println("=================orderMM======================================"+JSON.toJSONString(orderMM));
						return "";
					}
					System.out.println("=================payNotify_SUCCESS__4======================================");
					//注意：
					//该种交易状态只在一种情况下出现——开通了高级即时到账，买家付款成功后。
				}
				
			} else {
				try {
					response.getWriter().print("FAIL");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		/******************************************上面订单支付回调     下面充值回调***********************************************/
		//充值支付回调
		if(chargeFlag){
			if(AlipayNotify.verify(params)){//验证成功
				//////////////////////////////////////////////////////////////////////////////////////////
				//请在这里加上商户的业务逻辑程序代码
				//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
				if(trade_status.equals("TRADE_FINISHED")){
					//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
					//注意：
					//该种交易状态只在两种情况下出现
					//1、开通了普通即时到账，买家付款成功后。
					//2、开通了高级即时到账，从该笔交易成功时间算起，过了签约时的可退款时限（如：三个月以内可退款、一年以内可退款等）后。
					//在这里可以写入数据处理,
					VirtualAccountCustomerChargeModel virtualAccountCustomerChargeModel = virtualAccountCustomerChargeService.getVirtualAccountCustomerChargeModelByOrderUuid(orderId);
					//获取充值会员账户名
					String customerUuid = virtualAccountCustomerChargeModel.getCustomerUuid();
					//充值状态
					String chargeStatus = virtualAccountCustomerChargeModel.getChargeState();
					
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
					/* 增加会员虚拟账余额 */
					if (!VirtualAccountCustomerChargeModel.PAY_SUCCESS.equals(chargeStatus)) {
						customerAccountInteractive.addAccountBalance(customerUuid, balance);
					}
					/* 增加更新会员虚拟账余额 日志 */
					//金额
					//virtualAccountCustomerChargeModel.setAccountBalance(balance+account);
					virtualAccountCustomerChargeModel.setPayType("2");
					virtualAccountCustomerChargeModel.setOperAmount((float)balance);
					//备注
					virtualAccountCustomerChargeModel.setNote(customerUuid+"进行账号充值操作。充值时间："+DateFormatHelper.getNowTimeStr() +"充值金额："+balance+"元");
					virtualAccountCustomerChargeModel.setChargeState(VirtualAccountCustomerChargeModel.PAY_SUCCESS);
					virtualAccountCustomerChargeService.update(virtualAccountCustomerChargeModel);
					System.out.println("===============================>"+customerUuid+"进行账号充值操作。充值时间："+DateFormatHelper.getNowTimeStr() +"充值金额："+balance+"元");
					//交易成功  注意一定要返回给支付宝一个成功的信息
					try {
						response.getWriter().print("SUCCESS");
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (trade_status.equals("TRADE_SUCCESS")){
					//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序

					VirtualAccountCustomerChargeModel virtualAccountCustomerChargeModel = virtualAccountCustomerChargeService.getVirtualAccountCustomerChargeModelByOrderUuid(orderId);
					//获取充值会员账户名
					String customerUuid = virtualAccountCustomerChargeModel.getCustomerUuid();
					//充值状态
					String chargeStatus = virtualAccountCustomerChargeModel.getChargeState();
					
					//获取用户对象 更改用户对象的余额信息
					CustomerModel cm = customerService.getByUuid(customerUuid);
					double account = 0.0;
					/* 增加更新会员虚拟账余额 日志 */
					if(VirtualAccountCustomerChargeModel.PAY_SUCCESS.equals(chargeStatus)){
						if(cm !=null){
							account = cm.getAccountAmount();
							//保存现在的余额
							cm.setAccountAmount(account+balance);
							customerService.update(cm);
						}
					}
					
					//金额
					virtualAccountCustomerChargeModel.setPayType("2");
					virtualAccountCustomerChargeModel.setOperAmount((float)balance);
					//备注
					virtualAccountCustomerChargeModel.setNote(customerUuid+"进行账号充值操作。充值时间："+DateFormatHelper.getNowTimeStr() +"充值金额："+balance+"元");
					virtualAccountCustomerChargeModel.setChargeState(VirtualAccountCustomerChargeModel.PAY_SUCCESS);
					virtualAccountCustomerChargeService.update(virtualAccountCustomerChargeModel);
					
					//根据充值订单号判断此次充值操作的日志是否已经存在---by hdf
					VirtualAccountCustomerLogModel vacl=accountCustomerLogService.getVirtualAccountCustomerLogModelByOrderUuid(orderId);
					
					log.info("======充值日志======vacl======"+JSON.toJSONString(vacl));

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
						accountCustomerLogModel.setNowBalance( (float) virtualAccountCustomerChargeService.getVirtualAccountCustomerChargeModelByOrderUuid(orderId).getAccountBalance());
						accountCustomerLogModel.setOrderMainUuid(orderId);
						accountCustomerLogService.create(accountCustomerLogModel);
						System.out.println("===============================>"+customerUuid+"进行账号充值操作。充值时间："+DateFormatHelper.getNowTimeStr() +"充值金额："+balance+"元");
					}
					
					try {
						response.getWriter().print("SUCCESS");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
			} else {
				try {
					response.getWriter().print("FAIL");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return "";
	}
}