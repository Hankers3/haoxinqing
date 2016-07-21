package com.aebiz.b2b2c.order.sysback.web.ordermain;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.api.conrest.RestSDK;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.order.ordermain.service.OrderMainService;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainQueryModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderStatusEnum;
import com.aebiz.b2b2c.order.orderstamp.service.OrderStampService;
import com.aebiz.b2b2c.order.orderstamp.vo.OrderStampModel;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.service.VipclubIntegralLogService;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.VipclubIntegralLogModel;
import com.alibaba.fastjson.JSON;
/**
 * 容联云通讯 三方电话会议 操作类
 * @author xueli 
 * @version 20151229
 *
 */
@Controller
@RequestMapping("/conrest")
public class ConrestController extends
		BaseController<OrderMainModel, OrderMainQueryModel> {
	
	//云通讯平台主帐号，在云通讯平台注册帐号获取
	private static String AccountSid="8a48b551506fd26f01507f16d7a52533";
	//云通讯平台主帐号token，在云通讯平台注册帐号获取
	private static String AccountToken="246ed57917ca4e608afa126af0411ebb";		
	//云通讯平台应用id，在云通讯官网登录后创建应用获取，demo应用和未上线应用只能在沙盒测试环境使用
	private static String AppId="aaf98f8951858ab8015185ae44a6002b";														
	//云通讯平台应用id，在云通讯官网登录后创建应用获取，demo应用和未上线应用只能在沙盒测试环境使用
	private static String ServerIP="app.cloopen.com";
	
	private OrderMainService myService;
	@Autowired
	public void setMyService(OrderMainService bs) {
		this.myService = bs;
		super.setBs(bs);
	}
	
	/*注解录音信息service*/
	@Autowired
	private OrderStampService orderStampService;
	/*积分日志的service*/
	@Autowired
	private VipclubIntegralLogService vipclubIntegralLogService;
	
	/*注解医生service*/
	@Autowired
	private ServicestaffService staffService;
	
    //log4j记录日志
    private static Logger log = Logger.getLogger("conrest_logger");
    
	public ConrestController() {
		super("/order/sysback/ordermain", "OrderMain",
				ConrestController.class);
	}
        
    /**
     * 创建电话三方会议 便邀请客服加入
     * @param kfMobile
     * @param request
     * @return
     */
    @RequestMapping(value = "/createConf", method = RequestMethod.POST)
    public String createConf(@RequestParam("kfMobile") String kfMobile,
    		@RequestParam("orderMainUuid") String orderMainUuid,
            HttpServletRequest request, HttpServletResponse response){
    	
		log.info("创建电话三方会议 便邀请客服加入======createConf");

    	//返回结果
    	Map<String, Object> jsonMap = new HashMap();
    	String result="fail";
    	
    	RestSDK re=new RestSDK();
		re.init(AccountSid, AccountToken, AppId, ServerIP);
		//创建会议
		String ret=re.CreateConf("", "3", "", "", "", "delurl", "0", "false", "", "true", "", "mediaopturl", "false", "");
		log.info("======ret======"+ret);
		try {
			//解析返回参数
			Document doc = DocumentHelper.parseText(ret);
			Element rootElt = doc.getRootElement();
			//会议Id
			String confid = rootElt.elementTextTrim("confid");
			//返回状态码 取值000000（成功）
			String statusCode = rootElt.elementTextTrim("statusCode");
			//直呼加入的会议Id，用于VoIP直呼加入会议时呼叫的Id。
			String voiptoconfid = rootElt.elementTextTrim("voiptoconfid");
			log.info("======rootElt======"+JSON.toJSONString(rootElt));

			//创建会议成功 立即邀请客服
			if("000000".equals(statusCode)){
				//保存订单会议Id
				OrderMainModel order = myService.getByUuid(orderMainUuid);
				order.setOrgId(confid);
				myService.update(order);
				log.info("======order======"+JSON.toJSONString(order));

				//邀请客服加入
				String res = re.InviteJoinConf(confid, kfMobile, "", "1", "1", "");
				doc = DocumentHelper.parseText(res);
				rootElt = doc.getRootElement();
				//呼叫Id
				String callSid = rootElt.elementTextTrim("callSid");
				//返回状态码 取值000000（成功）
				String statusCodeI = rootElt.elementTextTrim("statusCode");
				if("000000".equals(statusCodeI)){
					result = "success";
				}
				 jsonMap.put("callSid", callSid);
			}
			 jsonMap.put("result", result);
			 jsonMap.put("confid", confid);
			
			 PrintWriter out = response.getWriter();
        	 out.print(JSON.toJSONString(jsonMap));
			
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return null;
    	
    }
    
    /**
     * 邀请患者和医生加入会议
     * @param kfMobile
     * @param request
     * @return
     */
    @RequestMapping(value = "/inviteJoinConf", method = RequestMethod.POST)
    public String inviteJoinConf(@RequestParam("callMobile") String callMobile,
    		@RequestParam("confid") String confid,
    		@RequestParam("callid") String callid,
    		@RequestParam("userType") String userType,
            HttpServletRequest request, HttpServletResponse response){
    	//返回结果
    	Map<String, Object> jsonMap = new HashMap();
    	String result="fail";
    	
    	RestSDK re=new RestSDK();
		re.init(AccountSid, AccountToken, AppId, ServerIP);
	
		try {
			//邀请客服加入
			String res = re.InviteJoinConf(confid, callMobile, "", "1", "1", "");
			Document doc = DocumentHelper.parseText(res);
			Element rootElt = doc.getRootElement();
			//会议Id
			String callSid = rootElt.elementTextTrim("callSid");
			//返回状态码 取值000000（成功）
			String statusCodeI = rootElt.elementTextTrim("statusCode");
			if("000000".equals(statusCodeI)){
				result = "success";
				if("2".equals(userType)){
					//邀请医生成功后 给患者禁听
					String resp =re.ConfMemberPause( confid,callid, "");
					Document docp = DocumentHelper.parseText(resp);
					Element rootEltp = docp.getRootElement();
					String statusCode = rootEltp.elementTextTrim("statusCode");
					jsonMap.put("statusCode", statusCode);
				}
			}
			jsonMap.put("result", result);
			jsonMap.put("callSid", callSid);
			jsonMap.put("confid", confid);
			PrintWriter out = response.getWriter();
			out.print(JSON.toJSONString(jsonMap));
			
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    	return null;
    }
    
    /**
     * 取消患者禁听
     * @param callSid
     * @param confid
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/confMemberResume", method = RequestMethod.POST)
    @ResponseBody
    public String confMemberResume(@RequestParam("callSid") String callSid,
    		@RequestParam("confid") String confid,
            HttpServletRequest request){
    	
    	//返回结果
    	String result="fail";
    	RestSDK re=new RestSDK();
		re.init(AccountSid, AccountToken, AppId, ServerIP);
	
		try {
			//取消患者禁听
			String res = re.ConfMemberResume(confid, callSid, "","1");
			Document doc = DocumentHelper.parseText(res);
			Element rootElt = doc.getRootElement();
			//返回状态码 取值000000（成功）
			String statusCode = rootElt.elementTextTrim("statusCode");
			if("000000".equals(statusCode)){
				result = "success";
			}
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
    	return result;
    }

    /**
     * 客服退出会议  并且调用会议闹钟
     * @param callSid
     * @param confid
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/quitConf", method = RequestMethod.POST)
    @ResponseBody
    public String quitConf(@RequestParam("callSid") String callSid,
    		@RequestParam("confid") String confid,
    		@RequestParam("consultDuration") String consultDuration,
            HttpServletRequest request){
    	
    	//返回结果
    	String result="fail";
    	RestSDK re=new RestSDK();
		re.init(AccountSid, AccountToken, AppId, ServerIP);
	
		try {
			//客服退出
			String res = re.QuitConf(callSid, confid, "");
			Document doc = DocumentHelper.parseText(res);
			Element rootElt = doc.getRootElement();
			//返回状态码 取值000000（成功）
			String statusCodeI = rootElt.elementTextTrim("statusCode");
			if("000000".equals(statusCodeI)){
				//获取通话时长
				int cusultTime = Integer.parseInt(consultDuration);
				int realTime = cusultTime*60;
				//客服退出成功 调用会议闹钟
				String response=re.ConfAlarmClock(confid, realTime+"", "", "2", "alarmclock", "", "false", "", "", "");
				
				Document docX = DocumentHelper.parseText(response);
				Element rootXElt = docX.getRootElement();
				String statusCodeX = rootXElt.elementTextTrim("statusCode");
				if("000000".equals(statusCodeX)){
					result = "success";
				}
			}
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
    	return result;
    }
    
    /**
     * 会议解散通知
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/delurl", method = RequestMethod.POST)
    public String delurl(HttpServletRequest request, HttpServletResponse response){
		log.info("======会议解散通知 ====delurl======");
    	//会议Id
		String confid =request.getParameter("confid");
		System.out.println("=======confid======"+confid);
		//录音信息保存地址
		//通过会议id获取订单信息
		OrderMainModel order = myService.getOrderMainByConfId(confid);
		
		log.info("======会议解散通知 ====order======"+JSON.toJSONString(order));
		System.out.println("======会议解散通知 ====order======"+JSON.toJSONString(order));

		//会议结束之后订单 改成待回访订单 清空已有的会议ID
		if(order !=null ){
			order.setState(OrderStatusEnum.DROPIN.getValue());
			myService.update(order);
		}
		
		return "";
    	
    }
    
    /**
     * 会议媒体控制结果通知
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/mediaopturl", method = RequestMethod.POST)
    public String  mediaopturl(HttpServletRequest request, HttpServletResponse response){
    	
		log.info("======会议媒体控制结果通知 ====mediaopturl======");

    	//会议ID
		String confid =request.getParameter("confid");
		System.out.println("=======confid======"+confid);
		log.info("======confid======"+confid);

		
		//操作类型 1放音、2录音、3调大音量、4静音5禁听6收键；11停止放音、12停止录音、13调小音量、14取消静音、15取消禁听。
    	String notifytype =request.getParameter("notifytype");
		log.info("======notifytype======"+notifytype);
    	// 操作结果。　0成功 ，其它值为失败。
    	String result =request.getParameter("result");
		log.info("======result======"+result);
    	//会议 1放音中、2录音中、11放音完成、12录音完成。
    	String state =request.getParameter("state");
		log.info("======state======"+state);
    	//录音时长。
		String recordduration =request.getParameter("recordduration");
		//录音文件下载地址当notifytype=12、result=0、state=12时，此参数才有内容。
		String recordurl = request.getParameter("recordurl");
		System.out.println("=======recordurl======"+recordurl);
		log.info("======recordurl======"+recordurl);
		
		//通过会议id获取订单信息
		OrderMainModel order = myService.getOrderMainByConfId(confid);
		log.info("=======会议媒体控制结果通知====order======"+JSON.toJSONString(order));
		
		System.out.println("=======会议媒体控制结果通知====order======"+JSON.toJSONString(order));
		//录音信息保存地址
		if(order !=null ){
			OrderStampModel os = orderStampService.getOrderStampByConfId(confid);
			if(os !=null){
				os.setMedurl(recordurl);
				os.setRecordduration(recordduration);
				orderStampService.update(os);
			}else{
				os = new OrderStampModel();
				os.setOrderUuid(order.getUuid());
				os.setConfId(confid);
				os.setCutomerUuid(order.getCustomerUuid());
				os.setDoctocUuid(order.getDoctorUuid());
				os.setMedurl(recordurl);
				os.setRecordduration(recordduration);
				os.setCreateTime(DateFormatHelper.getNowTimeStr());
				orderStampService.create(os);
			}
		}
		//会议结束之后订单 改成待回访订单 清空已有的会议ID
		if(order !=null ){
			//增加医生的服务次数
			ServicestaffModel  service = staffService.getByUuid(order.getDoctorUuid());
			if(service !=null){
				service.setServiceTimes(service.getServiceTimes()+1);
				staffService.update(service);
			}
			
			order.setState(OrderStatusEnum.DROPIN.getValue());
			myService.update(order);
			 VipclubIntegralLogModel vig = vipclubIntegralLogService
			 .getVipclubIntegralLogByConditions(order.getDoctorUuid(), VipclubIntegralLogModel.VIPCLUB_USERTYPE_DOC, "5",order.getUuid());
			 if(null==vig){
			     vipclubIntegralLogService.saveVipIntegralLog(order.getDoctorUuid(), "add", 20, "5",  VipclubIntegralLogModel.VIPCLUB_USERTYPE_DOC,"完成电话咨询订单",order.getUuid());
			 }
		}
    	return "";
    	
    }

}