package com.aebiz.b2b2c.order.alipcpay;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aebiz.b2b2c.baseframework.exception.AppException;
import com.aebiz.b2b2c.customermgr.alipay.service.AlipayService;
import com.aebiz.b2b2c.order.alipcpay.config.AlipayConfig;
import com.aebiz.b2b2c.order.alipcpay.util.AlipayNotify;
import com.aebiz.b2b2c.order.alipcpay.util.AlipaySubmit;
import com.aebiz.b2b2c.order.ordermain.service.OrderMainService;
import com.aebiz.b2b2c.order.ordermain.vo.OrderConst;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;
import com.aebiz.b2b2c.order.ordermainlog.service.OrderMainLogService;

/**
 * pc端订单支付宝支付
 * @author zdx
 *
 */
@Controller
@RequestMapping("/alipcpay")
public class AlipcpayController {
	private static Logger log = LoggerFactory.getLogger(AppException.class);
	@Autowired
	private OrderMainService orderMainService;
	@Autowired
	private OrderMainLogService orderLogService;
	@Autowired
	private AlipayService alipayService ;
	
	/**
	 * 支付第一步-去支付
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toPay", method = { RequestMethod.POST })
	public String toPay(@RequestParam("uuid") String uuid , HttpServletRequest request,HttpServletResponse response, Model model) {
		OrderMainModel order = orderMainService.getByUuid(uuid);
		if(order == null){
			return "redirect:orderfront/order/"+uuid;
		}
		//支付类型
		String payment_type = "1";
		//必填，不能修改
		//服务器异步通知页面路径
		String notify_url ="" ;
		//页面跳转同步通知页面路径
		String return_url = "" ;
		//防钓鱼时间戳
		String anti_phishing_key = "" ;
		//String exter_invoke_ip = "" ;
		try{
			//客户端的IP地址
		//	exter_invoke_ip =InetAddress.getLocalHost().getHostAddress()+":"+request.getLocalPort();
			//非局域网的外网IP地址，如：221.0.0.1
			anti_phishing_key = AlipaySubmit.query_timestamp();
			notify_url = "http://www.guwoba.cn/alipcpay/notify_url";
			return_url = "http://www.guwoba.cn/alipcpay/return_url";
		}catch(Exception e){
			
		}
		//卖家支付宝帐户
		String seller_email = AlipayConfig.SELLER;
		//商户订单号
		String out_trade_no = order.getUuid();
		//商户网站订单系统中唯一订单号，必填
		//订单名称
		String subject = order.getOrderId();
		//必填

		//付款金额
		String total_fee = String.valueOf(order.getPayMoney());
		//订单描述
		String body = order.getNote();
		//商品展示地址
		String show_url = "";
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("anti_phishing_key", anti_phishing_key);
		sParaTemp.put("body", body);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("partner", AlipayConfig.partner);
		sParaTemp.put("payment_type", payment_type);
		sParaTemp.put("return_url", return_url);
		sParaTemp.put("seller_email", seller_email);
		sParaTemp.put("service", "create_direct_pay_by_user");
		sParaTemp.put("show_url", show_url);
		sParaTemp.put("subject", subject);
		sParaTemp.put("notify_url", notify_url);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		request.setAttribute("sParaTemp",sParaTemp);
		//记录订单请求支付日志
		StringBuffer note = new StringBuffer();
		note.append(" 用户<<").append(order.getCustomerUuid()).append(">>");
		note.append("使用PC端支付宝方式向订单号为<<").append(out_trade_no).append(">>支付<<").append(total_fee).append(">>元,");
		note.append("请求参数为<<").append(sParaTemp).append(">>");
		log.info(note.toString());
		return "alipcpay/alipayapi";
		}
	
	/**
	 * 服务器异步通知页面路径
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = "/notify_url", method = { RequestMethod.POST })
	public String notify_url( HttpServletRequest request,HttpServletResponse response, Model model) {
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		//支付宝回调参数日志--未解析前
		StringBuffer info = new StringBuffer();
		info.append("服务器异步通知：");
		info.append("PC端支付宝支付后回调未解析之前的参数为：<<").append(requestParams).append(">>");
		log.info(info.toString());
		//支付金额
		String total_fee = "0";
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			if("total_fee".equals(name)){
				total_fee = values[0];
			}
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号
		String orderId ="" ;
		//支付宝交易号
		String trade_no = "" ;
		//交易状态
		String trade_status = "" ;
		try{
			orderId = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
			trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
		}catch(Exception e){
			e.printStackTrace();
		}
		//pc端支付宝支付后回调参数日志
		StringBuffer note = new StringBuffer();
		note.append("服务器异步通知：");
		note.append(" 订单<<").append(orderId).append(">>");
		note.append("PC端支付宝回调相关参数为：支付宝交易号<<").append(trade_no).append(">>，交易状态<<").append(trade_status).append(">>,");
		note.append("详细参数为<<").append(params).append(">>");
		log.info(note.toString());
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		OrderMainModel orderMM = orderMainService.getByUuid(orderId);
		if(AlipayNotify.verify(params)){//验证成功
			if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
				return "redirect:/orderfront/order/toPaySuccess/"+orderMM.getOrderGroupUuid()+"/"+OrderMainModel.ORDER_PAY_SUCCESS;
			}else{
				return "redirect:/orderfront/order/toPaySuccess/"+orderMM.getOrderGroupUuid()+"/"+OrderMainModel.ORDER_PAY_FAIL;
			}
		}else{//验证失败
			return "redirect:/orderfront/order/toPaySuccess/"+orderMM.getOrderGroupUuid()+"/"+OrderMainModel.ORDER_PAY_VALIDATA_FAIL;
		}
	}
	
	
	/**
	 * 支付宝回调
	 * 页面跳转同步通知页面路径
	 * @param request
	 * @param response
	 * @param model
	 */
	
	@RequestMapping(value = "/return_url", method = { RequestMethod.GET })
	public String return_url( HttpServletRequest request,HttpServletResponse response, Model model) {
		//获取支付宝GET过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		//支付宝回调参数日志--未解析前
		StringBuffer info = new StringBuffer();
		info.append("服务器同步通知：");
		info.append("PC端支付宝支付后回调未解析之前的参数为：<<").append(requestParams).append(">>");
		log.info(info.toString());
		//支付金额
		String total_fee = "0";
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			if("total_fee".equals(name)){
				total_fee = values[0];
			}
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		//商户订单号
		String orderId = "" ;
		//支付宝交易号
		String trade_no ="";
		//交易状态
		String trade_status = "" ;
		try{
			orderId = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			 trade_no= new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
			 trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
		}catch(Exception e){
			e.printStackTrace();
		}
		//pc端支付宝支付后回调参数日志
		StringBuffer note = new StringBuffer();
		note.append("服务器同步通知：");
		note.append(" 订单<<").append(orderId).append(">>");
		note.append("PC端支付宝回调相关参数为：支付宝交易号<<").append(trade_no).append(">>，交易状态<<").append(trade_status).append(">>,");
		note.append("详细参数为<<").append(params).append(">>");
		log.info(note.toString());
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		
		//计算得出通知验证结果
		boolean verify_result = AlipayNotify.verify(params);
		String status = "false" ;
		//验证成功
		final OrderMainModel orderMM = orderMainService.getByUuid(orderId);
		if(verify_result){
			if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
				//更改订单状态
				status = alipayService.payNotify(request, orderMM.getOrderId(), Double.parseDouble(total_fee), trade_status);
			}
			if("true".equals(status)){
					
				return "redirect:/orderfront/order/toPaySuccess/"+orderMM.getOrderGroupUuid()+"/"+OrderMainModel.ORDER_PAY_SUCCESS;
			}else{
				return "redirect:/orderfront/order/toPaySuccess/"+orderMM.getOrderGroupUuid()+"/"+OrderMainModel.ORDER_PAY_FAIL;
			}
		}else{
			return "redirect:/orderfront/order/toPaySuccess/"+orderMM.getOrderGroupUuid()+"/"+OrderMainModel.ORDER_PAY_VALIDATA_FAIL;
		}
	}
	
}
