package com.aebiz.b2b2c.customermgr.usercenter.web.customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Transient;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.basecrud.vo.DataTablesPageParam;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.basecrud.web.vo.BaseWebModel;
import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.basicdata.city.service.CityService;
import com.aebiz.b2b2c.cms.innermessage.service.InnerMessageService;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageModel;
import com.aebiz.b2b2c.cms.innermessagenotice.service.InnerMessageNoticeService;
import com.aebiz.b2b2c.cms.innermessagenotice.vo.InnerMessageNoticeModel;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerQueryModel;
import com.aebiz.b2b2c.customermgr.customeraddress.service.CustomerAddressService;
import com.aebiz.b2b2c.customermgr.customerinfo.service.CustomerInfoService;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoModel;
import com.aebiz.b2b2c.customermgr.customersource.service.CustomerSourceService;
import com.aebiz.b2b2c.customermgr.customersource.vo.CustomerSourceModel;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.MobileUtils;
import com.aebiz.b2b2c.customermgr.mobile.web.service.user.AppUserService;
import com.aebiz.b2b2c.finance.customeraccount.service.CustomerAccountInteractive;
import com.aebiz.b2b2c.finance.customeraccount.service.CustomerAccountService;
import com.aebiz.b2b2c.finance.customeraccount.vo.CustomerAccountModel;
import com.aebiz.b2b2c.giftcard.giftcards.service.GiftCardsService;
import com.aebiz.b2b2c.order.ordermain.service.OrderMainService;
import com.aebiz.b2b2c.order.ordermain.vo.OrderConst;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainQueryModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderStatusEnum;
import com.aebiz.b2b2c.order.ordermainaddress.service.OrderMainAddressService;
import com.aebiz.b2b2c.order.ordermaincomment.service.OrderMainCommentService;
import com.aebiz.b2b2c.order.ordermaincomment.vo.OrderMainCommentModel;
import com.aebiz.b2b2c.order.ordermainlog.service.OrderMainLogService;
import com.aebiz.b2b2c.promotion.coupondetails.service.CouponDetailsService;
import com.aebiz.b2b2c.promotion.frontshow.service.PromotionWebInteractive;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.service.VipclubIntegralLogService;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.VipclubIntegralLogModel;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.VipclubIntegralLogQueryModel;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.service.VipclubIntegralStatService;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.vo.StatIntegralType;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.vo.VipclubIntegralStatModel;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomercharge.service.VirtualAccountCustomerChargeService;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomerlog.service.VirtualAccountCustomerLogService;
import com.aebiz.b2b2c.websiteoperation.websiteoperate.service.WebsiteOperateInteractive;
import com.alibaba.fastjson.JSON;

/**
 * 平台后台会员系统控制器
 * 
 * @author zdx
 * 
 */
@Controller("UCCustomerController")
@RequestMapping("/usercenter/customer")
public class CustomerController extends
		BaseController<CustomerModel, CustomerQueryModel> {
	// 会员登录失败次数
	public static final String CUSTOMERLOGINERRORTIME = "CUSTOMERLOGINERRORTIME";// 放到cookies中的key
	// 登录时的验证码
	public static final String CUSTOMELOGINCODEKEY = "CUSTOMELOGINCODEKEY";// 放到cookies中的key
	// 城市id
	public static final String CITYIDCODEKEY = "CITYIDCODEKEY";// 放到session中的key
	// 城市对象key
	public static final String CITYCODEKEY = "CITYCODEKEY";// 放到cookies中的当前城市对象key
	// 注入运营系统接口
	@Autowired
	public WebsiteOperateInteractive websiteOperateInteractive;
	@Autowired
	private org.apache.shiro.mgt.SecurityManager sm = null;
	@Autowired
	public CustomerAddressService customerAddressService;
	@Autowired
	public VirtualAccountCustomerLogService virtualAccountCustomerLogService;
	@Autowired
	public CouponDetailsService couponDetailsService;
	@Autowired
	public VirtualAccountCustomerChargeService virtualAccountCustomerChargeService;
	@Autowired
	public GiftCardsService giftCardsService;
	@Autowired
	private PromotionWebInteractive promotionWebInteractive;
	@Autowired
	private CustomerAccountInteractive customerAccountInteractive;

	@Autowired
	private InnerMessageService messageService;
	@Autowired
	private OrderMainLogService orderLogService;
	private CustomerService myService;

	/**
	 * 积分日志
	 */
	@Transient
	private static VipclubIntegralLogService vipclubIntegralLogService;

	@Autowired
	public void setVipclubIntegralLogService(
			VipclubIntegralLogService integralLogService) {
		this.vipclubIntegralLogService = integralLogService;
	}

	/**
	 * 积分
	 */
	@Transient
	private static VipclubIntegralStatService vipclubIntegralStatService;

	@Autowired
	public void setVipclubIntegralStatService(
			VipclubIntegralStatService integralStatService) {
		this.vipclubIntegralStatService = integralStatService;
	}

	/**
	 * 订单
	 */
	@Transient
	private static OrderMainService orderMainService;

	@Autowired
	public void setOrderMainService(OrderMainService orderService) {
		this.orderMainService = orderService;
	}

	/**
	 * 订单评价
	 */
	@Transient
	private static OrderMainCommentService orderMainCommentService;

	@Autowired
	public void setOrderMainCommentService(
			OrderMainCommentService commentService) {
		this.orderMainCommentService = commentService;
	}

	@Autowired
	private OrderMainAddressService orderMainAddressService;
	/**
	 * 消息
	 */
	@Transient
	private static InnerMessageNoticeService innerMessageNoticeService;

	@Autowired
	public void setInnerMessageNoticeService(
			InnerMessageNoticeService messageNoticeService) {
		this.innerMessageNoticeService = messageNoticeService;
	}

	/**
	 * 城市
	 */
	@Autowired
	private CityService cityService;

	@Autowired
	public void setMyService(CustomerService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public CustomerController() {
		super("customermgr/usercenter/customer", "Customer",
				CustomerController.class);
	}

	@Autowired
	private CustomerSourceService customerSourceService;

	@Autowired
	private CustomerAccountService customerAccountService;

	@Autowired
	private CustomerInfoService customerInfoService;

	/**
	 * 会员中心-我的积分
	 */

	@RequestMapping(value = "/toMyIntegration/{nowPage}/{pageShow}", method = { RequestMethod.GET })
	public String toMyIntegration(@ModelAttribute("wm") BaseWebModel wm,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {

		// 获取当前登陆的会员的uuid "017adec0c892459186dcb0db478849b4"
		String customerUuid = LoginUserHelper.getCustomerLoginUserUuid();
		if (StringUtil.isEmpty(customerUuid)) {
			return this.toLogin(request, response);
		}
		List<VipclubIntegralLogModel> vilList = new ArrayList<VipclubIntegralLogModel>();
		// 总积分
		int totalIntegers = 0;
		// 可用积分
		int canUseIntegers = 0;
		// 分页-总量
		int totalNum = 0;
		if (!StringUtil.isEmpty(customerUuid)) {
			VipclubIntegralLogQueryModel qm = new VipclubIntegralLogQueryModel();
			qm.setCustomerUuid(customerUuid);
			totalNum = vipclubIntegralLogService
					.getVipclubIntegralLogModelListCountByConditon(qm);
			wm.setTotalNum(totalNum);
			vilList = vipclubIntegralLogService.getByCustomerUuid(customerUuid,
					wm.getPageShow(), wm.getNowPage());
			totalIntegers = vipclubIntegralStatService.getVipclubIntegralCount(
					customerUuid, StatIntegralType.TOTAL.getValue());
			canUseIntegers = vipclubIntegralStatService
					.getVipclubIntegralCount(customerUuid,
							StatIntegralType.USABLE.getValue());

		}

		wm.setRows(vilList);
		model.addAttribute("totalIntegers", totalIntegers);
		model.addAttribute("canUseIntegers", canUseIntegers);
		// 我的积分-样式
		model.addAttribute("integers", "true");
		return "customermgr/usercenter/customer/ucMyVirtualIntegration";
	}

	/**
	 * 会员中心-我的订单
	 * 
	 * @param wm
	 * @param orderState
	 *            订单状态
	 * @param model
	 * @param request
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/toMyOrders/{orderState}/{nowPage}/{pageShow}",
	 * method = { RequestMethod.GET }) public String
	 * toMyOrders(@ModelAttribute("wm") BaseWebModel
	 * wm,@PathVariable("orderState") String orderState, Model
	 * model,HttpServletRequest request,HttpServletResponse response) {
	 * 
	 * // 获取当前登陆的会员的uuid String customerUuid =
	 * LoginUserHelper.getCustomerLoginUserUuid();
	 * if(StringUtil.isEmpty(customerUuid)){ return this.toLogin(request,
	 * response); } List<OrderMainModel> orderList = new
	 * ArrayList<OrderMainModel>(); //订单状态 String state = orderState ;
	 * //当orderState为0时默认查询所有 if("0".equals(orderState)){ state=""; } //分页-总量
	 * int totalNum = 0 ; //待付款 int toPay = 0; //待确认 int toConfirm = 0 ; //待评价
	 * int toAppl = 0 ; //总订单数 int totalOrder = 0; // 判断是否需要初始化查询
	 * CustomerQueryModel qm = this.getQueryModel(); OrderMainQueryModel oqm =
	 * new OrderMainQueryModel(); oqm.setCustomerUuid(customerUuid); String init
	 * = request.getParameter("init"); if (!"true".equalsIgnoreCase(init)) {
	 * Object obj =
	 * SecurityUtils.getSubject().getSession().getAttribute("CustomerIsQueryQs"
	 * ); if ((obj != null) && (obj.toString().equals("true"))) { qm =
	 * (CustomerQueryModel)
	 * SecurityUtils.getSubject().getSession().getAttribute(
	 * "CustomerQueryModel"); //开始时间
	 * oqm.setOrderTime(qm.getCreateStartTime()+" 00:00:00"); //结束时间
	 * oqm.setOrderTime2(qm.getCreateEndTime() + " 23:59:59"); //评价状态
	 * oqm.setCommentState(qm.getCommentState()); //订单状态
	 * if(!StringUtil.isEmpty(qm.getState())){ state = qm.getState(); } } }
	 * 
	 * if(!StringUtil.isEmpty(customerUuid)){ totalOrder =
	 * orderMainService.getOrderCountByCustomerId(null,customerUuid,null);
	 * totalNum =
	 * orderMainService.getOrderCountByCustomerId(oqm,customerUuid,state);
	 * wm.setTotalNum(totalNum); orderList =
	 * orderMainService.getOrderListByCustomerId(oqm,customerUuid,state,
	 * wm.getPageShow(), wm.getNowPage()); toPay =
	 * orderMainService.getOrderCountByCustomerId(null,customerUuid,
	 * OrderConst.ORDERSTATUS_DFK); toConfirm =
	 * orderMainService.getOrderCountByCustomerId(null,customerUuid,
	 * OrderConst.ORDERSTATUS_YZF); toAppl =
	 * orderMainService.getOrderCountByCustomerId(null,customerUuid,
	 * OrderConst.ORDERSTATUS_QRWC); } //订单类型 this.sendOrderStatusToPage(model);
	 * wm.setRows(orderList); model.addAttribute("toPay", toPay);
	 * model.addAttribute("toConfirm", toConfirm); model.addAttribute("toAppl",
	 * toAppl); model.addAttribute("totalOrder", totalOrder);
	 * if(StringUtil.isEmpty(orderState)){ orderState="0"; }
	 * model.addAttribute("orderState", orderState); model.addAttribute("qm",
	 * qm); //我的订单-样式 model.addAttribute("myorder", "true"); return
	 * "order/usercenter/ordermain/ucMyOrders"; }
	 */

	/**
	 * 会员中心-我的订单（搜索功能）
	 * 
	 * @param orderId
	 * @param payType
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/searchOrders", method = { RequestMethod.POST })
	public String searchOrders(CustomerQueryModel qm,
			@RequestParam("pageShow") String pageShow,
			@RequestParam("orderState") String orderState,
			HttpServletRequest request) {
		SecurityUtils.getSubject().getSession()
				.setAttribute("CustomerIsQueryQs", "true");
		SecurityUtils.getSubject().getSession()
				.setAttribute("CustomerQueryModel", qm);
		return "redirect:/usercenter/customer/toMyOrders/" + orderState + "/1/"
				+ pageShow;
	}

	/**
	 * 会员中心-我的好心情
	 * 
	 * @param wm
	 * @param model
	 * @param request
	 * @author zdx
	 * @return
	 */
	@RequestMapping(value = "/toMyUserCenter", method = { RequestMethod.GET })
	public String toMyUserCenter(@ModelAttribute("wm") BaseWebModel wm,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {

		// 获取当前登陆的会员的uuid
		String customerUuid = LoginUserHelper.getCustomerLoginUserUuid();
		if (StringUtil.isEmpty(customerUuid)) {
			return this.toLogin(request, response);
		}
		// 订单列表
		List<OrderMainModel> orderList = new ArrayList<OrderMainModel>();
		// 消息
		List<InnerMessageNoticeModel> msgList = new ArrayList<InnerMessageNoticeModel>();
		// 最新评价的订单
		List<OrderMainModel> msgOrderList = new ArrayList<OrderMainModel>();
		// 待付款
		int toPay = 0;
		// 待确认
		int toConfirm = 0;
		// 待评价
		int toAppl = 0;
		// 已退款
		int refounded = 0;
		if (!StringUtil.isEmpty(customerUuid)) {
			List<OrderMainModel> list = new ArrayList<OrderMainModel>();
			toPay = orderMainService.getOrderCountByCustomerId(null,
					customerUuid, OrderConst.ORDERSTATUS_DFK);
			toConfirm = orderMainService.getOrderCountByCustomerId(null,
					customerUuid, OrderConst.ORDERSTATUS_YZF);
			toAppl = orderMainService.getOrderCountByCustomerId(null,
					customerUuid, OrderConst.ORDERSTATUS_QRWC);
			OrderMainQueryModel qm = new OrderMainQueryModel();
			OrderMainQueryModel qm1 = new OrderMainQueryModel();
			// 查询已退款订单
			qm.setRefoundState("1");
			refounded = orderMainService.getOrderCountByCustomerId(qm,
					customerUuid, null);
			orderList = orderMainService.getOrderListByCustomerId(null,
					customerUuid, null, 2, 1);
			qm1.setCommentState("1");
			list = orderMainService.getOrderListByCustomerId(qm1, customerUuid,
					null, 1, 1);
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					OrderMainModel order = list.get(i);
					if (order != null) {
						List<OrderMainCommentModel> commentList = orderMainCommentService
								.getCommentByOrderId(order.getUuid());
						order.setCommentList(commentList);
						msgOrderList.add(order);
					}
				}

			}
			msgList = innerMessageNoticeService.getByUserId(customerUuid, 3, 1);
		}
		CustomerInfoModel cim = customerInfoService
				.getCustomerInfoModelByCustomerUuid(customerUuid);
		String imgUrl = "";
		if (cim != null) {
			imgUrl = cim.getImgUrl();
		}
		wm.setRows(orderList);
		model.addAttribute("imgUrl", imgUrl);
		model.addAttribute("toPay", toPay);
		model.addAttribute("toConfirm", toConfirm);
		model.addAttribute("toAppl", toAppl);
		model.addAttribute("refounded", refounded);
		model.addAttribute("msgList", msgList);
		model.addAttribute("msgOrderList", msgOrderList);
		model.addAttribute("ucenter", "true");
		return "customermgr/usercenter/customer/ucMyUserCenter";
	}

	/**
	 * 跳转到会员登录页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toLogin", method = RequestMethod.GET)
	public String toLogin(HttpServletRequest request,
			HttpServletResponse response) {

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("customerLoginNameAndPassword")) {
					String customerLoginNameAndPassword = cookie.getValue();
					// 获取用户名和密码
					if (!StringUtil.isEmpty(customerLoginNameAndPassword)) {
						String[] v = customerLoginNameAndPassword.split(",");
						request.setAttribute("loginName", v[0]);
						request.setAttribute("pwd", v[1]);
						request.setAttribute("remember", "on");
					}
				}
				if (CustomerController.CUSTOMERLOGINERRORTIME.equals(cookie
						.getName())) {
					int cookiesErrorTime = 0;
					// 如果已经存在当前登录的用户的cookies
					String value = cookie.getValue();
					String[] v = value.split(",");
					// 第四个为错误登录次数
					cookiesErrorTime = Integer.parseInt(v[3]);

					// 如果失败次数大于3次,则再次登陆时需要输入验证码,如果小于3则还需要再去数据库里查看错误登陆次数
					if (cookiesErrorTime >= 3) {
						// 则再次登陆时需要输入验证码,
						request.setAttribute("hasVilidateCode", "true");
					} else {
						// 如果数据的错误次数小于3,等有可能是恶意更改了cookies,这个时候要以数据库的为准
						if (!StringUtil.isEmpty(v[0])) {
							// 根据会员输入的用户名来查找会员
							CustomerModel customer = myService
									.getCustomerModelByLoginNameOrMobileOrEmail(v[0]);
							if (customer != null) {
								int dbWrongTime = customer.getLoginErrorTimes();
								if (dbWrongTime < 3) {
									request.setAttribute("hasVilidateCode",
											"false");
								}
							}
						}
					}
				}
			}
		}

		return "customermgr/usercenter/customer/ucLogin";
	}

	/**
	 * 会员中心登录
	 * 
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("loginName") String loginName,
			@RequestParam("pwd") String pwd,
			@RequestParam("hasVilidateCode") String hasVilidateCode,
			HttpServletRequest request, HttpServletResponse response) {
		if (StringUtil.isEmpty(loginName)) {
			request.setAttribute("Error_Msg", MessageHelper
					.getMessage("customer.login.customerNameIsEmpty"));
			request.setAttribute("loginName", loginName);
			return "customermgr/usercenter/customer/ucLogin";
		}

		if (StringUtil.isEmpty(pwd)) {
			request.setAttribute("Error_Msg",
					MessageHelper.getMessage("customer.login.passwordIsEmpty"));
			request.setAttribute("loginName", loginName);
			return "customermgr/usercenter/customer/ucLogin";
		}

		// 如果是需要验证码的,需要验证验证码
		if (hasVilidateCode.equals("true")) {
			String validateCode = request.getParameter("validateCode");
			Cookie[] cookies = request.getCookies();
			String code = "";
			for (Cookie cookie : cookies) {
				if (CustomerController.CUSTOMELOGINCODEKEY.equals(cookie
						.getName())) {
					code = cookie.getValue();
				}
			}
			// 验证码不能为空
			if (StringUtil.isEmpty(validateCode)) {
				request.setAttribute(
						"Error_Msg",
						MessageHelper
								.getMessage("customerregister.verificationCode.isEmpty"));
				request.setAttribute("loginName", loginName);
				request.setAttribute("hasVilidateCode", "true");
				return "customermgr/usercenter/customer/ucLogin";
				// 验证码不正确和cooikes里的不一致
			} else if (!validateCode.equalsIgnoreCase(code)) {
				request.setAttribute("Error_Msg", MessageHelper
						.getMessage("customerregister.verificationCodeError"));
				request.setAttribute("loginName", loginName);
				request.setAttribute("validateCode", validateCode);
				request.setAttribute("hasVilidateCode", "true");
				return "customermgr/usercenter/customer/ucLogin";
			}
		}

		SecurityUtils.setSecurityManager(sm);

		String type = request.getParameter("type");
		UsernamePasswordToken token = new UsernamePasswordToken(type + ","
				+ loginName, pwd);
		token.setRememberMe(true);

		Subject currentCustomer = SecurityUtils.getSubject();

		// 根据会员输入的用户名来查找会员
		CustomerModel customer = myService
				.getCustomerModelByLoginNameOrMobileOrEmail(loginName);
		if (customer == null) {
			request.setAttribute("Error_Msg",
					MessageHelper.getMessage("login.failed.error"));
			request.setAttribute("loginName", loginName);
			return "customermgr/usercenter/customer/ucLogin";
		}
		// 最大输入的错误密码数
		String times = websiteOperateInteractive
				.getValueByKey(LoginMaxErrorTimeConst.CUSTOMER_MAX_LOGIN_ERROR);
		int errortime = Integer.parseInt(times);
		// 会员登录错误次数
		int loginErrorTimes = customer.getLoginErrorTimes();
		// 如果登录的次数超过限定次数次了,即使帐号密码是正确的,也会提示超过限定次数或者找回密码
		/*
		 * if (customer != null && loginErrorTimes >= errortime) { //最后错误登录时间
		 * Date lastLoginErrorTime =
		 * DateFormatHelper.toDate(customer.getLastWrongLoginTime()); //20分钟后
		 * Date after20Time = DateUtil.addMinute(lastLoginErrorTime, 20);
		 * 
		 * Long minutes =
		 * DateUtil.timesBetween(DateUtil.toSqlTimestamp(DateFormatHelper
		 * .getTimeStr(after20Time),
		 * DateUtil.DATE_FORMAT_DATETIME),DateUtil.toSqlTimestamp(
		 * DateFormatHelper.getNowTimeStr(), DateUtil.DATE_FORMAT_DATETIME),
		 * DateUtil.MINUTE_MILLI); if(minutes<0){ String minu=
		 * String.valueOf(Math.abs(minutes)) ; request.setAttribute("loginName",
		 * loginName); request.setAttribute("hasVilidateCode", "true"); //
		 * 找回密码的url String[] p = new String[] { times,minu };
		 * request.setAttribute("Error_Msg","");
		 * request.setAttribute("More_Error_Msg"
		 * ,MessageHelper.getMessage("login.moreTimes", p)); return
		 * "customermgr/usercenter/customer/ucLogin"; }else{ //登录错误次数设置重置为0
		 * customer.setLoginErrorTimes(0); myService.update(customer); }
		 * 
		 * }
		 */

		try {
			currentCustomer.login(token);
			// 验证冻结状态
			if (customer != null) {
				if (CustomerModel.CUSTOMER_FROZENSTATE_FROZEN.equals(customer
						.getFrozenState())) {
					String errorMsg = MessageHelper
							.getMessage("login.failed.frozen.one")
							+ customer.getFrozenTypeShowName()
							+ MessageHelper
									.getMessage("login.failed.frozen.two");
					request.setAttribute("Error_Msg", errorMsg);
					request.setAttribute("loginName", loginName);
					return "customermgr/usercenter/customer/ucLogin";
				}
				// 验证激活状态
				if (CustomerModel.CUSTOMER_ACTIVESTATE_UNACTIVATE
						.equals(customer.getActiveState())) {
					request.setAttribute("Error_Msg",
							MessageHelper.getMessage("login.failed.unactivate"));
					request.setAttribute("loginName", loginName);
					return "customermgr/usercenter/customer/ucLogin";
				}
			}
			// 登录错误次数设置重置为0
			customer.setLoginErrorTimes(0);
			myService.update(customer);
			// 把cookies失败的次数置为0
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				if (CustomerController.CUSTOMERLOGINERRORTIME.equals(cookie
						.getName())) {
					String value = cookie.getValue();
					String[] v = value.split(",");
					cookie.setValue(v[0] + "," + v[1] + "," + v[2] + ",0");
					// 有效时间为30天
					cookie.setMaxAge(30 * 24 * 60 * 60);
					cookie.setPath("/");
					response.addCookie(cookie);
				}
			}

			System.out.println("currentCustomer========="
					+ currentCustomer.isAuthenticated());

		} catch (Exception err) {
			err.printStackTrace();
			request.setAttribute("Error_Msg",
					MessageHelper.getMessage("login.failed.error"));

			// 登录失败时,做的cookies失败次数和数据库登录失败次数的操作,来判定是否需要输入验证码
			this.doError(customer, loginName, request, response);
			request.setAttribute("loginName", loginName);

			return "customermgr/usercenter/customer/ucLogin";
		}

		if (customer != null) {
			currentCustomer.getSession().setAttribute(
					LoginUserHelper.LOGIN_CUSTOMER, customer);
			// 如果记住我，则加cookies
			String remember = request.getParameter("remember");
			if ("on".equals(remember)) {
				// 把用户名和密码
				Cookie customerLoginNameCookies = new Cookie(
						"customerLoginNameAndPassword", loginName + "," + pwd);

				// 有效时间为30天
				customerLoginNameCookies.setMaxAge(30 * 24 * 60 * 60);
				customerLoginNameCookies.setPath("/");
				response.addCookie(customerLoginNameCookies);
			} else {
				// 把cookies里的用户名密码清空
				// 把用户名和密码
				Cookie customerLoginNameCookies = new Cookie(
						"customerLoginNameAndPassword", "");

				// 立即删除cookies
				customerLoginNameCookies.setMaxAge(0);
				customerLoginNameCookies.setPath("/");
				response.addCookie(customerLoginNameCookies);
			}
		}
		// 重定向到根目录
		return "redirect:toMyUserCenter";
	}

	/**
	 * 当登录失败时做的操作
	 * 
	 * @param customer
	 * @param loginName
	 * @param request
	 * @param response
	 *            void
	 */
	private boolean doError(CustomerModel customer, String loginName,
			HttpServletRequest request, HttpServletResponse response) {
		// 最大输入的错误密码数
		String times = websiteOperateInteractive
				.getValueByKey(LoginMaxErrorTimeConst.CUSTOMER_MAX_LOGIN_ERROR);
		int errortime = Integer.parseInt(times);
		// 找回密码的url
		// 数据库的登录失败次数+1
		if (customer != null) {
			int dbErrorTime = customer.getLoginErrorTimes();
			dbErrorTime = dbErrorTime + 1;
			customer.setLoginErrorTimes(dbErrorTime);
			customer.setLastWrongLoginTime(DateFormatHelper.getNowTimeStr());
			myService.updateLoginErrorTimes(customer);
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				int i = 0;
				for (Cookie cookie : cookies) {
					if (CustomerController.CUSTOMERLOGINERRORTIME.equals(cookie
							.getName())) {
						i = 1;
					}
				}
				if (i == 0) {
					String value = loginName + "," + loginName + ","
							+ loginName + ",1";
					// 有效时间为30天
					Cookie cookie = new Cookie(
							CustomerController.CUSTOMERLOGINERRORTIME, value);
					cookie.setMaxAge(30 * 24 * 60 * 60);
					cookie.setPath("/");
					response.addCookie(cookie);
				}
				for (Cookie cookie : cookies) {
					// 获取登录失败次数,如果还没有cookies 则去数据库进行比较
					if (CustomerController.CUSTOMERLOGINERRORTIME.equals(cookie
							.getName())) {
						int cookiesErrorTime = 0;
						// 如果已经存在当前登录的用户的cookies
						if (cookie.getValue().indexOf(loginName) > -1) {
							String value = cookie.getValue();
							String[] v = value.split(",");
							// 第四个为错误登录次数
							cookiesErrorTime = Integer.parseInt(v[3]);
							// 登录失败次数+1
							cookiesErrorTime = cookiesErrorTime + 1;
							cookie.setValue(customer.getCustomerName() + ","
									+ customer.getMobile() + ","
									+ customer.getEmail() + ","
									+ cookiesErrorTime + "");
							// 有效时间为30天
							cookie.setMaxAge(30 * 24 * 60 * 60);
							cookie.setPath("/");
							response.addCookie(cookie);

							// 如果失败次数大于3次,则再次登陆时需要输入验证码,如果小于3则还需要再去数据库里查看错误登陆次数
							if (cookiesErrorTime >= 3
									&& cookiesErrorTime < errortime) {
								// 则再次登陆时需要输入验证码,
								request.setAttribute("hasVilidateCode", "true");
								return true;
							} else if (cookiesErrorTime >= errortime) {
								// 最大输入的错误密码数
								String[] p = new String[] { times };
								request.setAttribute("More_Error_Msg",
										MessageHelper.getMessage(
												"login.moreTimes", p));
							} else {
								// 如果数据的错误次数小于3,等有可能是恶意更改了cookies,这个时候要以数据库的为准
								int dbWrongTime = Integer.valueOf(customer
										.getLoginErrorTimes());
								if (dbWrongTime < 3) {
									request.setAttribute("hasVilidateCode",
											"false");
									request.setAttribute("More_Error_Msg", "");
								}
							}
						}
					} else {
						int dbWrongTime = Integer.valueOf(customer
								.getLoginErrorTimes());
						if (dbWrongTime >= 3 && dbWrongTime < errortime) {
							request.setAttribute("hasVilidateCode", "true");
						} else if (dbWrongTime >= errortime) {
							request.setAttribute("hasVilidateCode", "true");
							String[] p = new String[] { times };
							request.setAttribute("More_Error_Msg",
									MessageHelper.getMessage("login.moreTimes",
											p));
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * 退出登录
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		return "redirect:toLogin";
	}

	/**
	 * 组织订单状态列表数据发送到页面，供筛选试用
	 * 
	 * @param model
	 */
	private void sendOrderStatusToPage(Model model) {
		List<DataTablesPageParam> stateList = new ArrayList<DataTablesPageParam>();

		for (OrderStatusEnum spe : OrderStatusEnum.values()) {
			DataTablesPageParam dpp = new DataTablesPageParam();

			dpp.setName(spe.getName());
			dpp.setValue(spe.getValue());

			stateList.add(dpp);
		}

		model.addAttribute("orderStateList", stateList);
	}

	/**
	 * 
	 * 判断用户是否登录
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("isLogin")
	@ResponseBody
	public String isLogin(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		if (StringUtil.isEmpty(LoginUserHelper.getCustomerLoginUserUuid())) {
			return "0";
		} else {
			return "1";
		}
	}

	/**
	 * 快速登录<br>
	 * 
	 * 每次登录错误时都需要把cookies里的登录错误次数+1,<br>
	 * 
	 * 取cookies里的值的时候取出的是存有登录名,手机号,邮箱和登录错无次数字符串,粗腰去判断是否是当前正在登录的cookies<br>
	 * 
	 * 把登陆的用户名,手机号,邮箱和登陆错误次数发那个发哦cookies,同时数据库的错误登录次数也需要+1,<br>
	 * 
	 * 如果cookies里的错误次数>=3,则传给页面hasVilidateCode为true,需要输入验证码,<br>
	 * 
	 * 反之,还需要去数据库里取出错误次数进行比较,为了避免恶意修改cookies的出现
	 * 
	 * @param loginName
	 * @param pwd
	 * @param hasVilidateCode
	 *            是否需要验证码
	 * @param request
	 * @param response
	 * @return String
	 */
	@RequestMapping(value = "/quickLogin", method = RequestMethod.POST)
	public String quickLogin(@RequestParam("loginName") String loginName,
			@RequestParam("pwd") String pwd,
			@RequestParam("hasVilidateCode") String hasVilidateCode,
			HttpServletRequest request, HttpServletResponse response) {
		if (StringUtil.isEmpty(loginName)) {
			request.setAttribute("Error_Msg", MessageHelper
					.getMessage("customer.login.customerNameIsEmpty"));
			request.setAttribute("loginName", loginName);
			return "customermgr/usercenter/customer/quickLoginCenter";
		}

		if (StringUtil.isEmpty(pwd)) {
			request.setAttribute("Error_Msg",
					MessageHelper.getMessage("customer.login.passwordIsEmpty"));
			request.setAttribute("loginName", loginName);
			return "customermgr/usercenter/customer/quickLoginCenter";
		}

		// 如果是需要验证码的,需要验证验证码
		if (hasVilidateCode.equals("true")) {
			String validateCode = request.getParameter("validateCode");
			Cookie[] cookies = request.getCookies();
			String code = "";
			for (Cookie cookie : cookies) {
				if (CustomerController.CUSTOMELOGINCODEKEY.equals(cookie
						.getName())) {
					code = cookie.getValue();
				}
			}
			// 验证码不能为空
			if (StringUtil.isEmpty(validateCode)) {
				request.setAttribute(
						"Error_Msg",
						MessageHelper
								.getMessage("customerregister.verificationCode.isEmpty"));
				request.setAttribute("loginName", loginName);
				request.setAttribute("hasVilidateCode", "true");
				return "customermgr/usercenter/customer/quickLoginCenter";
				// 验证码不正确和cooikes里的不一致
			} else if (!validateCode.equalsIgnoreCase(code)) {
				request.setAttribute("Error_Msg", MessageHelper
						.getMessage("customerregister.verificationCodeError"));
				request.setAttribute("loginName", loginName);
				request.setAttribute("validateCode", validateCode);
				request.setAttribute("hasVilidateCode", "true");
				return "customermgr/usercenter/customer/quickLoginCenter";
			}
		}

		SecurityUtils.setSecurityManager(sm);

		String type = request.getParameter("type");
		UsernamePasswordToken token = new UsernamePasswordToken(type + ","
				+ loginName, pwd);
		token.setRememberMe(true);

		Subject currentCustomer = SecurityUtils.getSubject();

		// 根据会员输入的用户名来查找会员
		CustomerModel customer = myService
				.getCustomerModelByLoginNameOrMobileOrEmail(loginName);
		if (customer == null) {
			request.setAttribute("Error_Msg",
					MessageHelper.getMessage("login.failed.error"));
			request.setAttribute("loginName", loginName);
			return "customermgr/usercenter/customer/quickLoginCenter";
		}
		// 最大输入的错误密码数
		String times = websiteOperateInteractive
				.getValueByKey(LoginMaxErrorTimeConst.CUSTOMER_MAX_LOGIN_ERROR);
		int errortime = Integer.parseInt(times);
		// 会员登录错误次数
		int loginErrorTimes = customer.getLoginErrorTimes();
		// 如果登录的次数超过限定次数次了,即使帐号密码是正确的,也会提示超过限定次数或者找回密码
		/*
		 * if (customer != null && loginErrorTimes >= errortime) { //最后错误登录时间
		 * Date lastLoginErrorTime =
		 * DateFormatHelper.toDate(customer.getLastWrongLoginTime()); //20分钟后
		 * Date after20Time = DateUtil.addMinute(lastLoginErrorTime, 20);
		 * 
		 * Long minutes =
		 * DateUtil.timesBetween(DateUtil.toSqlTimestamp(DateFormatHelper
		 * .getTimeStr(after20Time),
		 * DateUtil.DATE_FORMAT_DATETIME),DateUtil.toSqlTimestamp(
		 * DateFormatHelper.getNowTimeStr(), DateUtil.DATE_FORMAT_DATETIME),
		 * DateUtil.MINUTE_MILLI); if(minutes<0){ String minu=
		 * String.valueOf(Math.abs(minutes)) ; request.setAttribute("loginName",
		 * loginName); request.setAttribute("hasVilidateCode", "true"); //
		 * 找回密码的url String[] p = new String[] { times,minu };
		 * request.setAttribute("Error_Msg","");
		 * request.setAttribute("More_Error_Msg"
		 * ,MessageHelper.getMessage("login.moreTimes", p)); return
		 * "customermgr/usercenter/customer/quickLoginCenter"; }else{
		 * //登录错误次数设置重置为0 customer.setLoginErrorTimes(0);
		 * myService.update(customer); }
		 * 
		 * }
		 */

		try {
			currentCustomer.login(token);

			// 把cookies失败的次数置为0
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				if (CustomerController.CUSTOMERLOGINERRORTIME.equals(cookie
						.getName())) {
					String value = cookie.getValue();
					String[] v = value.split(",");
					cookie.setValue(v[0] + "," + v[1] + "," + v[2] + ",0");
					// 有效时间为30天
					cookie.setMaxAge(30 * 24 * 60 * 60);
					cookie.setPath("/");
					response.addCookie(cookie);
				}
			}

			System.out.println("currentCustomer========="
					+ currentCustomer.isAuthenticated());

		} catch (Exception err) {
			err.printStackTrace();
			request.setAttribute("Error_Msg",
					MessageHelper.getMessage("login.failed.error"));

			// 登录失败时,做的cookies失败次数和数据库登录失败次数的操作,来判定是否需要输入验证码
			this.doError(customer, loginName, request, response);
			request.setAttribute("loginName", loginName);

			return "customermgr/usercenter/customer/quickLoginCenter";
		}

		if (customer != null) {
			currentCustomer.getSession().setAttribute(
					LoginUserHelper.LOGIN_CUSTOMER, customer);
			// 如果记住我，则加cookies
			String remember = request.getParameter("remember");
			if ("on".equals(remember)) {
				// 把用户名和密码
				Cookie customerLoginNameCookies = new Cookie(
						"customerLoginNameAndPassword", loginName + "," + pwd);

				// 有效时间为30天
				customerLoginNameCookies.setMaxAge(30 * 24 * 60 * 60);
				customerLoginNameCookies.setPath("/");
				response.addCookie(customerLoginNameCookies);
			} else {
				// 把cookies里的用户名密码清空
				// 把用户名和密码
				Cookie customerLoginNameCookies = new Cookie(
						"customerLoginNameAndPassword", "");

				// 立即删除cookies
				customerLoginNameCookies.setMaxAge(0);
				customerLoginNameCookies.setPath("/");
				response.addCookie(customerLoginNameCookies);
			}
		}

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("success", "success");
		response.setContentType("charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(JSON.toJSONString(jsonMap));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 会员--注册页面 hedongfei
	 * 
	 * @param model
	 * @param request
	 */
	@RequestMapping(value = { "/toCustomerRegister" }, method = { RequestMethod.GET })
	public String toCustomerRegister(Model model, HttpServletRequest request) {

		// preparedUpdateData(model, request);

		return "customermgr/usercenter/customer/CustomerRegister";
	}

	/**
	 * 根据传入的手机号判断手机号是否已经存在
	 * 
	 * @param mobile
	 *            手机号 hedongfei
	 */
	@RequestMapping(value = { "/checkMobile" }, method = { RequestMethod.GET })
	@ResponseBody
	public String checkMobile(@RequestParam("mobile") String mobile,
			HttpServletRequest request, HttpServletResponse response) {

		if (myService.checkMobileExist(mobile)) {
			return "true";
		}

		return "false";
	}

	/**
	 * 点击注册
	 * 
	 * @param mobile
	 *            手机号 hedongfei
	 */
	@Override
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(Model model, @ModelAttribute("m") CustomerModel m,
			HttpServletRequest request) {
		return super.add(model, m, request);
	}

	/**
	 * 手机获取验证码接口
	 * 
	 * @param request
	 * @param response
	 * @param mobile
	 *            hedongfei
	 */

	@RequestMapping(value = "/getCode", method = RequestMethod.POST)
	@ResponseBody
	public String getCode(Model model, HttpServletRequest request,
			HttpServletResponse response, @RequestParam("mobile") String mobile) {

		// 设置返回信息
		// Map<Object, Object> jsonMap = new HashMap();

		// 生成6位手机验证码
		String code = AppUserService.getActivatingKey(6);
		boolean resendsuccess = false;
		try {
			// 发验证码到手机
			resendsuccess = AppUserService.sendRegCodeMobile(mobile, code);
		} catch (ServiceException e) {
		}

		request.getSession().setAttribute("smsCode", code);

		// 验证码
		// Map codeMap = new HashMap();
		// codeMap.put("code",code);

		// codeMap.put("resendsuccess",resendsuccess +"");
		/*
		 * jsonMap.put("code",code); jsonMap.put("resendsuccess",resendsuccess);
		 * request.getSession().setAttribute("smsCode", code);
		 */
		// HttpServletUtils.outJson(response, jsonMap);

		/*
		 * model.addAttribute("code", code); model.addAttribute("resendsuccess",
		 * resendsuccess);
		 * 
		 * if(resendsuccess){
		 * 
		 * return code;
		 * 
		 * }else{
		 * 
		 * return null; }
		 */

		if (resendsuccess) {

			return "true";

		} else {

			return "false";
		}

	}

	/**
	 * 注册
	 * 
	 * @param request
	 * @param response
	 * @param mobile
	 * @param verificationCode
	 *            hedongfei
	 */
	@RequestMapping(value = "/gotoLogin", method = RequestMethod.POST)
	public String gotoLogin(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("mobile") String mobile,
			@RequestParam("password") String password,
			@RequestParam("mobileAuthCode") String verificationCode) {

		String smsCode = (String) request.getSession().getAttribute("smsCode");
		// 定义此变量，用来存放会员对象
		Subject currentCustomer = SecurityUtils.getSubject();

		if (smsCode.equals(verificationCode)) {

			CustomerModel customerModel = myService.getCustomerByMobile(mobile);

			if (customerModel == null) {
				// customerModel = customerService.getCustomerByMobile(mobile);
				customerModel = new CustomerModel();
				customerModel.setMobile(mobile);
				customerModel.setCustomerName(mobile);
				// customerModel.setPassword(mobile.substring(5, 11));
				customerModel.setPassword(password);
				customerModel.setFrozenState("0");
				customerModel.setCreateTime(DateFormatHelper.getNowTimeStr());
				myService.create(customerModel);

				request.getSession().setAttribute("customerModel",
						customerModel);

				// 将会员对象存在定义的变量中
				currentCustomer.getSession().setAttribute(
						LoginUserHelper.LOGIN_CUSTOMER, customerModel);

				// 生成新用户时，自动生成一个用户的申请时获得的邀请码，并存储。
				CustomerSourceModel customerSourceModel = new CustomerSourceModel();
				customerSourceModel.setCustomerUuid(customerModel.getUuid());
				MobileUtils mobileUtils = new MobileUtils();
				customerSourceModel.setMyInviteCode(myService
						.createInviteCode(5));
				customerSourceService.create(customerSourceModel);

				// 创建记录会员的账户信息
				CustomerAccountModel cam = new CustomerAccountModel();
				cam.setCustomerUuid(customerModel.getUuid());
				cam.setCustomerName(mobile);
				cam.setAccountBalance(0);
				customerAccountService.create(cam);

				// 创建会员积分信息
				VipclubIntegralStatModel vipclubIntegralStatModel = new VipclubIntegralStatModel();
				vipclubIntegralStatModel.setCustomerUuid(customerModel
						.getUuid());
				vipclubIntegralStatModel.setIntergralCount(0);
				vipclubIntegralStatModel.setIntergralType("1");
				vipclubIntegralStatService.create(vipclubIntegralStatModel);

				// 创建会员积分信息
				VipclubIntegralStatModel vism = new VipclubIntegralStatModel();
				vism.setCustomerUuid(customerModel.getUuid());
				vism.setIntergralCount(0);
				vism.setIntergralType("3");
				vipclubIntegralStatService.create(vism);

			}
			// 通过会员编号或者手机号或者用户名获取会员信息
			String customerUuid = customerModel.getUuid();
			// 会员基础信息
			CustomerInfoModel customerInfoModel = customerInfoService
					.getCustomerInfoModelByCustomerUuid(customerUuid);
			if (customerInfoModel == null) {
				customerInfoModel = new CustomerInfoModel();
				customerInfoModel.setCustomerUuid(customerModel.getUuid());
				// customerInfoModel.setNickName(customerModel.getMobile());
				customerInfoService.create(customerInfoModel);
			}
			return "customermgr/usercenter/customer/RegisterSuccess";

		} else {
			return null;
		}
	}

	/**
	 * 会员注册成功--点击帐户名---进会员账户中心
	 * 
	 * @param wm
	 * @param qm
	 * @param model
	 * @param request
	 * @param response
	 *            hedongfei
	 */
	@RequestMapping(value = "/toCustomercenter", method = { RequestMethod.POST })
	public String toCustomercenter(Model model, HttpServletRequest request,
			HttpServletResponse response) {

		return "redirect:toMycharges/1/4";
	}

	/**
	 * 保存系统发送的消息
	 * 
	 * @param orderUuid
	 */
	public void saveInnerMessage(String orderUuid, String time, String userId) {
		OrderMainModel om = orderMainService.getByUuid(orderUuid);

		InnerMessageModel im = new InnerMessageModel();
		// 收件人类别
		im.setAccountType(InnerMessageModel.ACCOUNT_TYPE_STORE);
		im.setContent("服务时间为" + time + "订单已取消！");
		im.setOrderUuid(orderUuid);
		im.setMessageType(InnerMessageModel.MESSAGE_TYPE_ORDER);
		im.setReceiveUser(userId);
		im.setSendUser("system");
		im.setTitle("订单已取消");
		im.setReadStatus("0");
		// 已发送
		im.setSendStatus("1");
		im.setSendTime(DateFormatHelper.getNowTimeStr());

		messageService.create(im);
	}

	/**
	 * 点击注册时校验手机短信验证码
	 * 
	 * @param model
	 * @param request
	 * @param response
	 *            hedongfei
	 */
	@RequestMapping(value = "/checkMobileAuthCode", method = { RequestMethod.POST })
	@ResponseBody
	public String checkMobileAuthCode(
			@RequestParam("mobileAuthCode") String mobileAuthCode, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		String smsCode = (String) request.getSession().getAttribute("smsCode");
		if (mobileAuthCode.equals(smsCode)) {
			return "true";
		} else {
			return "false";
		}

	}

}