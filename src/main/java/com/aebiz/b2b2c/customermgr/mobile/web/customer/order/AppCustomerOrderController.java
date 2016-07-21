package com.aebiz.b2b2c.customermgr.mobile.web.customer.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aebiz.b2b2c.basebusiness.utils.DateUtil;
import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.innermessage.service.InnerMessageService;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageModel;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageTypeEnum;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.customermgr.customerinfo.service.CustomerInfoService;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoModel;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.AppBaseController;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.HttpServletUtils;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.SysConst;
import com.aebiz.b2b2c.customermgr.mobile.web.push.JPushService;
import com.aebiz.b2b2c.customermgr.mobile.web.push.PushConst;
import com.aebiz.b2b2c.finance.customeraccount.service.CustomerAccountInteractive;
import com.aebiz.b2b2c.finance.customeraccount.service.CustomerAccountService;
import com.aebiz.b2b2c.order.orderdetail.service.OrderDetailService;
import com.aebiz.b2b2c.order.orderdetail.vo.OrderDetailModel;
import com.aebiz.b2b2c.order.ordermain.service.OrderMainService;
import com.aebiz.b2b2c.order.ordermain.vo.OrderConst;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderStatusEnum;
import com.aebiz.b2b2c.order.ordermainlog.service.OrderMainLogService;
import com.aebiz.b2b2c.servicestaff.doctorright.service.DoctorRightService;
import com.aebiz.b2b2c.servicestaff.doctorright.vo.DoctorRightModel;
import com.aebiz.b2b2c.servicestaff.packagemanagement.service.PackageManagementService;
import com.aebiz.b2b2c.servicestaff.packagemanagement.vo.PackageManagementModel;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;
import com.aebiz.b2b2c.servicestaff.servicestaffinfo.service.ServicestaffinfoService;
import com.aebiz.b2b2c.servicestaff.servicestaffinfo.vo.ServicestaffinfoModel;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomerlog.service.VirtualAccountCustomerLogService;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomerlog.vo.VirtualAccountCustomerLogModel;
import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/app/customer/order")
public class AppCustomerOrderController extends AppBaseController {

	public AppCustomerOrderController() {
		super("", "", AppCustomerOrderController.class);
	}

	@Autowired
	private OrderMainService orderMainService;
	@Autowired
	private ServicestaffService servicestaffService;
	@Autowired
	private ServicestaffinfoService servicestaffinfoService;

	/* 患者service */
	@Autowired
	private CustomerService customerService;
	/* 患者基础信息service */
	@Autowired
	private CustomerInfoService customerInfoService;

	@Autowired
	private OrderMainLogService orderLogService;
	@Autowired
	private CustomerAccountService customerAccountService;

	/* 虚拟账户日志 */
	@Autowired
	private VirtualAccountCustomerLogService virtualAccountCustomerLogService;

	@Autowired
	private CustomerAccountInteractive customerAccountInteractive;

	/* 医生私人套餐 */
	@Autowired
	private PackageManagementService packageManagementService;

	/* 套餐订单详情 */
	@Autowired
	private OrderDetailService orderDetailService;

	/* 医生权限 */
	@Autowired
	private DoctorRightService doctorRightService;

	/* 消息service */
	@Autowired
	private InnerMessageService innerMessageService;

	// log4j记录日志
	private static Logger log = Logger.getLogger("orderCenter_logger");

	/**
	 * 获取所有患者的订单的接口
	 * 
	 * @author xp
	 */
	@RequestMapping(value = "/getCustomerOrderList", method = RequestMethod.GET)
	public String getCustomerOrderList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("stateType") String stateType, @RequestParam("customerUuid") String customerUuid) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		// 标志以及必需要传的参数
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "customerUuid,true" });
		Object callback = map.get("callback");

		// 判断customerUuid是否为空
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "请登录"), callback);
			return null;
		}

		// 判断stateType是否为空
		if (StringUtil.isEmpty(stateType)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "未输入订单状态"), callback);
			return null;
		}

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		List relist = new ArrayList();

		// 查询所有患者的订单
		List<OrderMainModel> orderList = orderMainService.getCustomerOrderList(stateType, customerUuid, 0, 0, "");
		if (orderList != null && orderList.size() > 0) {
			for (OrderMainModel orm : orderList) {
				Map<String, Object> save = new HashMap<String, Object>();
				if (null != orm) {
					// 订单类型1:电话咨询订单 2：私人套餐
					String orderType = orm.getOrderType();
					save.put("orderType", orderType);
					// 订单状态 1是未支付的 3是已支付的
					save.put("state", orm.getState());
					// 订单价格
					save.put("totalMoney", orm.getTotalMoney());
					// 预约时长
					save.put("consultDuration", orm.getConsultDuration());
					if ("1".equals(orderType)) {
						save.put("bookTime", orm.getBookTime());
						save.put("startTime", orm.getReceiveTime());
					} else {
						save.put("bookTime", "");
						save.put("startTime", "");
					}
					// order的id
					save.put("orderUuid", orm.getUuid());
					String doctorUuid = orm.getDoctorUuid();
					// 医生的id
					save.put("doctorUuid", doctorUuid);
					ServicestaffModel sm = servicestaffService.getByUuid(doctorUuid);
					ServicestaffinfoModel sim = servicestaffinfoService
							.getServicestaffinfoModelByServicestaffUuid(doctorUuid);
					// 医生的姓名
					if (sm != null) {
						save.put("doctorName", sm.getRealName());
					} else {
						save.put("doctorName", "");
					}
					if (sim != null) {
						save.put("doctorSex", sim.getSex());
						save.put("doctorImg", sim.getImgUrl());
					} else {
						save.put("doctorSex", "");
						save.put("doctorImg", "");
					}
					// 订单的id
					save.put("orderMainUuid", orm.getUuid());
				}
				CustomerInfoModel cim = customerInfoService.getCustomerInfoModelByCustomerUuid(customerUuid);
				if (null != cim) {
					// 获取患者的头像
					save.put("imgUrl", cim.getImgUrl());
					save.put("sex", cim.getSex());
					save.put("age", cim.getAge());
					save.put("realName", cim.getRealName());
				}

				relist.add(save);
			}
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 通过订单主键查看订单详情 12/24
	 * 
	 * @param request
	 * @param response
	 * @author szr
	 * @return
	 */
	@RequestMapping(value = "/toOrderMainDetail", method = RequestMethod.GET)
	public String toOrderMainDetail(HttpServletRequest request, @RequestParam("orderMainUuid") String orderMainUuid,
			HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "orderMainUuid,true" });

		Object callback = map.get("callback");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 判断参数是否为空
		if (StringUtil.isEmpty(orderMainUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "订单Uuid不存在"), callback);
			return null;
		}
		// 通过订单id得到订单model
		OrderMainModel orderMainModel = orderMainService.getByUuid(orderMainUuid);
		if (orderMainModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "订单不存在"), callback);
			return null;
		}
		String packageUuid = orderMainModel.getPackageUuid();

		Map<String, Object> orderInfo = new HashMap<String, Object>();
		// 订单类型
		String orderType = orderMainModel.getOrderType();
		orderInfo.put("orderType", orderType);
		if (!StringUtil.isEmpty(orderType) && "1".equals(orderType)) {
			// 预约时间
			orderInfo.put("bookTime", orderMainModel.getBookTime());
			// 预约时长
			orderInfo.put("consultDuration", orderMainModel.getConsultDuration());
		} else {
			if (!StringUtil.isEmpty(packageUuid)) {
				PackageManagementModel pm = packageManagementService.getByUuid(packageUuid);
				if (pm != null) {
					orderInfo.put("packageName", pm.getPackageName());
					orderInfo.put("plusTimes", pm.getPlusTimes());
					orderInfo.put("phoneTimes", pm.getPhoneTimes());
					orderInfo.put("picTimes", "不限");
				} else {
					orderInfo.put("packageName", "");
					orderInfo.put("plusTimes", "");
					orderInfo.put("phoneTimes", "");
					orderInfo.put("picTimes", "");
				}
			}
		}

		// 价格
		orderInfo.put("orderPrice", orderMainModel.getTotalMoney());
		// 医生回复情况(订单状态)
		orderInfo.put("orderState", orderMainModel.getState());
		// 拒绝理由
		orderInfo.put("note", orderMainModel.getNote());
		// 订单描述
		orderInfo.put("orderDescription", orderMainModel.getOrderDescription());
		orderInfo.put("receiveTime", orderMainModel.getReceiveTime());
		// 结束时间
		orderInfo.put("endTime", orderMainModel.getEndTime());
		// 支付方式 0:线上支付 1：线下支付
		orderInfo.put("customerUuid", orderMainModel.getCustomerUuid());
		orderInfo.put("doctorUuid", orderMainModel.getDoctorUuid());

		orderInfo.put("payType", orderMainModel.getPayType());

		jsonMap.put("orderInfo", orderInfo);// 消息字段

		// 通过订单id得到医生id
		String doctorUuid = orderMainService.getDoctorUuidByUuid(orderMainUuid);
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生Uuid不存在"), callback);
			return null;
		}
		ServicestaffModel servicestaffModel = servicestaffService.getByUuid(doctorUuid);
		if (servicestaffModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生信息不存在"), callback);
			return null;
		}
		ServicestaffinfoModel servicestaffinfoModel = servicestaffinfoService
				.getServicestaffinfoModelByServicestaffUuid(doctorUuid);
		if (servicestaffinfoModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生基本信息不存在"), callback);
			return null;
		}
		Map<String, Object> doctorInfo = new HashMap<String, Object>();
		// 医生姓名
		doctorInfo.put("doctorName", servicestaffModel.getRealName());
		// 医生性别
		doctorInfo.put("doctorSex", servicestaffinfoModel.getSex());
		// 医生职称
		doctorInfo.put("professional", servicestaffinfoModel.getProfessional());
		// 医生医院
		doctorInfo.put("hospital", servicestaffModel.getHospitalName());
		// 医生科室
		doctorInfo.put("department", servicestaffModel.getDepartmentName());
		// 医生头像
		doctorInfo.put("doctorImg", servicestaffinfoModel.getImgUrl());
		// 医生uuid
		doctorInfo.put("doctorUuid", doctorUuid);
		jsonMap.put("doctorInfo", doctorInfo);// 消息字段

		// 通过订单id得到患者id
		String customerUuid = orderMainService.getCustomerUuidByUuid(orderMainUuid);
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "患者Uuid不存在"), callback);
			return null;
		}
		CustomerModel customerModel = customerService.getByUuid(customerUuid);
		if (customerModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "患者信息不存在"), callback);
			return null;
		}
		CustomerInfoModel customerInfoModel = customerInfoService.getCustomerInfoModelByCustomerUuid(customerUuid);
		if (customerInfoModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "患者基本信息不存在"), callback);
			return null;
		}
		Map<String, Object> customerInfo = new HashMap<String, Object>();
		// 患者姓名
		customerInfo.put("customerName", customerModel.getRealName());
		// 患者性别
		customerInfo.put("customerSex", customerModel.getSex());
		// 患者头像
		customerInfo.put("customerImg", customerInfoModel.getImgUrl());
		// 患者年龄
		customerInfo.put("customerAge", customerInfoModel.getAge());
		customerInfo.put("birthday", customerInfoModel.getBirthday());

		// 订单价格
		customerInfo.put("orderPrice", orderMainModel.getTotalMoney());
		// 问题描述(取的是订单中的备注)
		customerInfo.put("illnessDescription", customerInfoModel.getIllnessDescription());
		jsonMap.put("customerInfo", customerInfo);// 消息字段

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	
	
	
	/**
	 * 通过订单主键查看订单详情 12/24
	 * 
	 * @param request
	 * @param response
	 * @author szr
	 * @return
	 */
	@RequestMapping(value = "/getOrderMainDetail", method = RequestMethod.GET)
	public String getOrderMainDetail(HttpServletRequest request, @RequestParam("orderMainUuid") String orderMainUuid,
			HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "orderMainUuid,true" });

		Object callback = map.get("callback");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 判断参数是否为空
		if (StringUtil.isEmpty(orderMainUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "订单Uuid不存在"), callback);
			return null;
		}
		// 通过订单id得到订单model
		OrderMainModel orderMainModel = orderMainService.getByUuid(orderMainUuid);
		if (orderMainModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "订单不存在"), callback);
			return null;
		}
		String packageUuid = orderMainModel.getPackageUuid();

		Map<String, Object> orderInfo = new HashMap<String, Object>();
		// 订单类型
		String orderType = orderMainModel.getOrderType();
		orderInfo.put("orderType", orderType);
		if (!StringUtil.isEmpty(orderType) && "1".equals(orderType)) {
			// 预约时间
			orderInfo.put("bookTime", orderMainModel.getBookTime());
			// 预约时长
			orderInfo.put("consultDuration", orderMainModel.getConsultDuration());
		} else {
			if (!StringUtil.isEmpty(packageUuid)) {
				PackageManagementModel pm = packageManagementService.getByUuid(packageUuid);
				if (pm != null) {
					orderInfo.put("packageName", pm.getPackageName());
					orderInfo.put("plusTimes", pm.getPlusTimes());
					orderInfo.put("phoneTimes", pm.getPhoneTimes());
					orderInfo.put("picTimes", "不限");
				} else {
					orderInfo.put("packageName", "");
					orderInfo.put("plusTimes", "");
					orderInfo.put("phoneTimes", "");
					orderInfo.put("picTimes", "");
				}
			}
		}

		// 价格
		orderInfo.put("orderPrice", orderMainModel.getTotalMoney());
		// 医生回复情况(订单状态)
		orderInfo.put("orderState", orderMainModel.getState());
		// 拒绝理由
		orderInfo.put("note", orderMainModel.getNote());
		// 订单描述
		orderInfo.put("orderDescription", orderMainModel.getOrderDescription());
		//开始时间
		orderInfo.put("receiveTime", orderMainModel.getReceiveTime());
		// 结束时间
		orderInfo.put("endTime", orderMainModel.getEndTime());
		// 支付方式 0:线上支付 1：线下支付
		orderInfo.put("customerUuid", orderMainModel.getCustomerUuid());
		orderInfo.put("doctorUuid", orderMainModel.getDoctorUuid());

		orderInfo.put("payType", orderMainModel.getPayType());

		jsonMap.put("orderInfo", orderInfo);// 消息字段

		// 通过订单id得到医生id
		String doctorUuid = orderMainService.getDoctorUuidByUuid(orderMainUuid);
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生Uuid不存在"), callback);
			return null;
		}

		// 通过订单id得到患者id
		String customerUuid = orderMainService.getCustomerUuidByUuid(orderMainUuid);
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "患者Uuid不存在"), callback);
			return null;
		}
		CustomerModel customerModel = customerService.getByUuid(customerUuid);
		if (customerModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "患者信息不存在"), callback);
			return null;
		}
		CustomerInfoModel customerInfoModel = customerInfoService.getCustomerInfoModelByCustomerUuid(customerUuid);
		if (customerInfoModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "患者基本信息不存在"), callback);
			return null;
		}
		Map<String, Object> customerInfo = new HashMap<String, Object>();
		// 患者姓名
		customerInfo.put("customerName", customerModel.getRealName());
		// 患者性别
		customerInfo.put("customerSex", customerModel.getSex());
		// 患者头像
		customerInfo.put("customerImg", customerInfoModel.getImgUrl());
		// 患者年龄
		customerInfo.put("customerAge", customerInfoModel.getAge());
		//出生日期
		customerInfo.put("birthday", customerInfoModel.getBirthday());
		// 订单价格
		customerInfo.put("orderPrice", orderMainModel.getTotalMoney());
		// 问题描述(取的是订单中的备注)
		customerInfo.put("illnessDescription", customerInfoModel.getIllnessDescription());
		
		jsonMap.put("customerInfo", customerInfo);// 消息字段

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	
	
	
	/**
	 * 取消订单(将订单状态改为2.订单取消) 12/24
	 * 
	 * @param request
	 * @param response
	 * @author szr
	 * @return
	 */
	@RequestMapping(value = "/cancelOrder", method = RequestMethod.GET)
	public String cancelOrder(HttpServletRequest request, @RequestParam("orderMainUuid") String orderMainUuid,
			HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "orderMainUuid,true" });

		Object callback = map.get("callback");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 判断参数是否为空
		if (StringUtil.isEmpty(orderMainUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "订单id为空"), callback);
			return null;
		}
		// 通过订单id得到订单model
		OrderMainModel omm = orderMainService.getByUuid(orderMainUuid);
		if (omm == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "订单不存在"), callback);
			return null;
		}
		String state = omm.getState();
		//XP取消订单后将金额退回到患者的虚拟账户里面
		String customerUuid = omm.getCustomerUuid();
		double nowBalance = 0;
		if(!StringUtil.isEmpty(customerUuid)){
		nowBalance = customerService.getAccountAmountByUuid(customerUuid);
		}
		String orderType = omm.getOrderType();
		
		double payMoney = omm.getTotalMoney();
		
		String orderTime = omm.getOrderTime();
		//已支付订单取消 支付金额去虚拟账户
		if(OrderStatusEnum.PAID.getValue().endsWith(state)){
			//XP 创建消费日志
			VirtualAccountCustomerLogModel  vcm = new VirtualAccountCustomerLogModel();
			vcm.setCustomerUuid(customerUuid);
			vcm.setOrderMainUuid(orderMainUuid);
			vcm.setOperType(VirtualAccountCustomerLogModel.IN);
			vcm.setOperAmount(payMoney);
			vcm.setNowBalance(nowBalance+payMoney);
			vcm.setOrderType(orderType);
			vcm.setOrderTime(orderTime);
			vcm.setPayType("3");
			virtualAccountCustomerLogService.create(vcm);
			//XP 修改患者账户的余额
			CustomerModel cm =customerService.getByUuid(customerUuid);
			if ( null == cm) {
	                    HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "请登录"), callback);
	                    return null;
			}
			cm.setAccountAmount(nowBalance+payMoney);
			customerService.update(cm);
		}
		// 将订单设置为取消状态 详见订单枚举
		omm.setState(OrderStatusEnum.ORDERCANCEL.getValue());
		// 更新
		orderMainService.update(omm);
		
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 审核订单(将订单状态改为 4.待执行（审核通过），8.已拒绝) 16/01/26
	 * 
	 * @param request
	 * @param response
	 * @param state
	 *            0:拒绝 1：待执行
	 * @author szr
	 * @return
	 */
	@RequestMapping(value = "/checkOrder", method = RequestMethod.GET)
	public String checkOrder(HttpServletRequest request, @RequestParam("orderMainUuid") String orderMainUuid,
			@RequestParam("state") String state, HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "orderMainUuid,true", "state,true" });

		Object callback = map.get("callback");
		// 拒绝理由
		String refuse = request.getParameter("refuse");

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 判断参数是否为空
		if (StringUtil.isEmpty(orderMainUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "订单id为空"), callback);
			return null;
		}
		// 通过订单id得到订单model
		OrderMainModel orderMainModel = orderMainService.getByUuid(orderMainUuid);
		if (orderMainModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "订单不存在"), callback);
			return null;
		}
		String customerUuid = orderMainModel.getCustomerUuid();
		String doctorUuid = orderMainModel.getDoctorUuid();
		String doctorName = orderMainModel.getDoctorName();
		// 判断订单状态
		if ("0".equals(state)) {
			// 将订单设置为8.已拒绝
			orderMainModel.setState(OrderStatusEnum.REFUSE.getValue());
			// 拒绝理由
			orderMainModel.setNote(refuse);
			// 更新
			orderMainService.update(orderMainModel);
			
			if (!StringUtil.isEmpty(customerUuid) && !StringUtil.isEmpty(doctorUuid)) {
				String content = doctorName + "已经拒绝了您的电话咨询";
				innerMessageService.saveInnerMessageAndPush(doctorUuid, customerUuid, content,
						InnerMessageModel.ACCOUNT_TYPE_STORE, InnerMessageModel.ACCOUNT_TYPE_CUSTOMER,
						PushConst.push_client_customer, "orderUuid", orderMainUuid, InnerMessageTypeEnum.ORDER.getValue());
			}
			
		} else if ("1".equals(state)) {
			// 将订单设置为4.待执行（审核通过）
			orderMainModel.setState(OrderStatusEnum.DROPIN.getValue());
			// 更新
			orderMainService.update(orderMainModel);
			if (!StringUtil.isEmpty(customerUuid) && !StringUtil.isEmpty(doctorUuid)) {
				String content = doctorName + "已经接受了您的电话咨询";
				innerMessageService.saveInnerMessageAndPush(doctorUuid, customerUuid, content,
						InnerMessageModel.ACCOUNT_TYPE_STORE, InnerMessageModel.ACCOUNT_TYPE_CUSTOMER,
						PushConst.push_client_customer, "orderUuid", orderMainUuid, InnerMessageTypeEnum.ORDER.getValue());
			}
			
		} else {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "state状态有误，只能为0:拒绝 1：待执行"),
					callback);
			return null;
		}
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 保存订单接口 xl
	 * 
	 * @param request
	 * @param response
	 * @param city
	 * @return
	 */
	@RequestMapping(value = "/orderSave", method = RequestMethod.POST)
	public String orderSave(HttpServletRequest request, HttpServletResponse response) {

		log.info("==============orderSave==============start====");
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut, new String[] { "callback,true",
				"customerUuid,true", "doctorUuid,true", "orderAmount,true", "orderType,true" });
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}

		log.info("==============map==================" + map);

		Object callback = map.get("callback");
		// 患者编号
		String customerUuid = map.get("customerUuid");
		// 医生编号
		String doctorUuid = map.get("doctorUuid");
		// 订单金额
		double orderAmount = Double.parseDouble(map.get("orderAmount"));
		// 订单类型
		String orderType = map.get("orderType");
		// 支付方式
		String payType = request.getParameter("payType");

		// 订单备注
		String orderNote = request.getParameter("orderNote");
		// 电话咨询预约开始时间
		String bookTime = request.getParameter("bookTime");
		// 通话执行时间点
		String receiveTime = request.getParameter("receiveTime");
		// 订单描述
		String orderDescription = request.getParameter("orderDescription");
		// 通话结束时间点
		String endTime = "";
		// 电话时长
		String consultDuration = request.getParameter("consultDuration");
		if (!StringUtil.isEmpty(receiveTime) && !StringUtil.isEmpty(consultDuration)) {
			// 获取结束时间
			endTime = DateUtil.getMinTime(receiveTime, Integer.parseInt(consultDuration));
		}

		// 套餐编号
		String packageUuid = request.getParameter("packageUuid");

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 校验客户是否为空
		CustomerModel user = null;
		if (!StringUtil.isEmpty(customerUuid)) {
			user = customerService.getByUuid(customerUuid);
		}
		if (user == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "此会员不存在"), callback);
			return null;
		}

		// 校验医生信息是否为空
		ServicestaffModel serviceStaff = null;
		if (!StringUtil.isEmpty(doctorUuid)) {
			serviceStaff = servicestaffService.getByUuid(doctorUuid);
		}
		if (serviceStaff == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生不存在"), callback);
			return null;
		}

		OrderMainModel order = new OrderMainModel();
		String orderNo = orderMainService.createOrderNo();
		order.setOrderId(orderNo);
		//分账状态 未分账
		order.setAccountState(OrderMainModel.ACCOUNTSTATE_UNFZ);
		// 设置用户id
		order.setCustomerUuid(customerUuid);
		// 设置用户名
		order.setCustomerName(customerService.getCustomerNameByCustomerUuid(customerUuid));
		// 医生编号
		order.setDoctorUuid(doctorUuid);
		// 订单描述
		order.setOrderDescription(orderDescription);

		// 购买电话咨询 先查询是否套餐里面满足 如果套餐电话咨询满足 直接减去套餐里面次数
		if ("1".equals(orderType)) {
			OrderDetailModel om = orderDetailService.getOrderDetailModel(customerUuid, doctorUuid, consultDuration);
			if (om != null) {
				om.setPhoneTimes(om.getPhoneTimes() - 1);
				order.setTotalMoney(0);
				order.setPayMoney(0);
				orderDetailService.update(om);
			}
		}

		String ordertime = DateFormatHelper.getNowTimeStr();
		// 设置订单时间
		order.setOrderTime(ordertime);
		// 设置订单金额
		order.setTotalMoney(orderAmount);
		order.setPayMoney(orderAmount);
		// 设置订单备注
		if (!StringUtil.isEmpty(orderNote)) {
			order.setNote(orderNote);
		} else {
			order.setNote("");
		}
		// 订单类型 1：电话咨询 2：私人套餐
		order.setOrderType(orderType);

		// 获取医生是否开通审核
		// String check = doctorRightService.getExamByDoctorUuid(doctorUuid);
		DoctorRightModel dr = doctorRightService.getByDoctorUuid(doctorUuid);
		String check = "";
		String personalExam = "";
		if (dr != null) {
			check = dr.getExam();
			personalExam = dr.getPersonalExam();
		}
		// 电话咨询 根据电话咨询设置
		if ("1".equals(orderType)) {
			if (!"1".equals(check)) {
				order.setCheckType("2");
			} else {
				order.setCheckType("1");
			}
		} else {
			// 私人套餐 根据私人套餐审核设置
			if (!"1".equals(personalExam)) {
				order.setCheckType("2");
			} else {
				order.setCheckType("1");
			}
		}
		// 私人医生到期时间
		String dueTime = "";
		if (!StringUtil.isEmpty(orderType) && "2".equals(orderType)) {
			// 私人医生套餐信息
			PackageManagementModel packageManagement = null;
			if (!StringUtil.isEmpty(packageUuid)) {
				packageManagement = packageManagementService.getByUuid(packageUuid);
				if (!StringUtil.isEmpty(packageManagement.getPeriod())) {
					if ("0".equals(packageManagement.getPeriod())) {
						dueTime = DateUtil.dueTime(1);
					} else if ("1".equals(packageManagement.getPeriod())) {
						dueTime = DateUtil.dueTime(3);
					} else if ("2".equals(packageManagement.getPeriod())) {
						dueTime = DateUtil.dueTime(12);
					} else {
						dueTime = DateUtil.dueTime(1);
					}
				}
				order.setSendTime(dueTime);
			}
			if (packageManagement == null) {
				HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生套餐信息不存在"), callback);
				return null;
			}
			order.setPackageUuid(packageUuid);
		} else {
			order.setBookTime(bookTime);
			order.setReceiveTime(receiveTime);
			order.setEndTime(endTime);
			order.setConsultDuration(consultDuration);
		}
		// 设置订单支付方式
		order.setPayType(payType);
		// 订单状态
		if (order.getPayMoney() <= 0) {
			order.setState(OrderStatusEnum.PAID.getValue());
		} else {
			order.setState(OrderStatusEnum.WAITPAY.getValue());
		}
		// 订单来源类型 0:手机订单，1：pc端订单
		order.setOrderFromType("0");
		// 生成订单
		orderMainService.create(order);

		log.info("==============order==================" + JSON.toJSONString(order));

		// 订单类型 1：电话咨询 2：私人套餐 私人套餐创建订单的详情
		String packName = "";
		if (!StringUtil.isEmpty(orderType) && "2".equals(orderType)) {
			OrderDetailModel orderDetail = new OrderDetailModel();
			orderDetail.setOrderMainUuid(order.getUuid());
			PackageManagementModel packageManagement = null;
			if (!StringUtil.isEmpty(packageUuid)) {
				packageManagement = packageManagementService.getByUuid(packageUuid);
				packName = packageManagement.getPackageName();
			}

			orderDetail.setCustomerUuid(customerUuid);
			orderDetail.setDoctorUuid(doctorUuid);
			orderDetail.setProductUuid(packageUuid);
			orderDetail.setProductName(packName);
			orderDetail.setBasePrice(packageManagement.getMoney());
			orderDetail.setFinalPrice(orderAmount);
			orderDetail.setMoney(orderAmount);
			orderDetail.setPhoneTimes(packageManagement.getPhoneTimes());
			orderDetail.setPlusTimes(packageManagement.getPlusTimes());
			orderDetail.setDuration(packageManagement.getDuration());
			orderDetail.setDueTime(dueTime);
			orderDetailService.create(orderDetail);
			log.info("==============orderDetail==================" + JSON.toJSONString(orderDetail));

		}

		Map<String, Object> orderMap = new HashMap<String, Object>();
		orderMap.put("orderUuid", order.getUuid());
		orderMap.put("orderId", order.getOrderId());
		orderMap.put("orderTime", order.getOrderTime());
		orderMap.put("orderType", order.getOrderType());
		if ("2".equals(orderType)) {
			orderMap.put("packageUuid", order.getPackageUuid());
		}

		orderMap.put("orderAmount", order.getTotalMoney());// 订单金额
		orderMap.put("orderNote", order.getNote());
		orderMap.put("orderStatus", order.getState());
		orderMap.put("payType", order.getPayType());// 订单可操作状态

		// 生成订单日志
		orderLogService.createOrderLog(order.getUuid(), "会员提交订单", "3", customerUuid, "3", "1",
				OrderConst.ORDERSTATUS_YZF, order.getPayType());

		jsonMap.put("order", orderMap);

		// 返回数据
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;
	}

	/**
	 * 订单支付接口(根据传来的参数将订单状态 3.已支付(待审核)16/01/14
	 * 
	 * @param request
	 * @param response
	 * @author szr
	 * @return
	 */
	@RequestMapping(value = "/updateOrderState", method = RequestMethod.GET)
	public String updateOrderState(HttpServletRequest request, HttpServletResponse response) {

		// 设置返回数据编码XP
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String userId = request.getParameter("userId");
		List<String> userIds = new ArrayList<String>();

		userIds.add(userId);
		// 消息体
		// 消息发送
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", "息发送给test123456");
		map.put("sendTime", DateFormatHelper.getNowTimeStr());
		//map.put("pushType", PushConst.push_type_system);
		JPushService.testAll(userIds, map);
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		HttpServletUtils.outJson(response, jsonMap);
		return "";

	}

}
