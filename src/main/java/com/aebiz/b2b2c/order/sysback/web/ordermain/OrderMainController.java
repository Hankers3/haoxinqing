package com.aebiz.b2b2c.order.sysback.web.ordermain;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.api.conrest.RestSDK;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.service.SysManagerService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.ConditionOpTypeEnum;
import com.aebiz.b2b2c.baseframework.basecrud.vo.DataTablesPageParam;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.basecrud.web.vo.BaseWebModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.basicdata.city.service.CityService;
import com.aebiz.b2b2c.basicdata.region.service.RegionService;
import com.aebiz.b2b2c.cms.innermessage.service.InnerMessageService;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageModel;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.finance.customeraccount.service.CustomerAccountInteractive;
import com.aebiz.b2b2c.order.ordercomplain.service.OrderComplainService;
import com.aebiz.b2b2c.order.orderdetail.service.OrderDetailService;
import com.aebiz.b2b2c.order.orderdetail.vo.OrderDetailModel;
import com.aebiz.b2b2c.order.ordermain.service.OrderMainService;
import com.aebiz.b2b2c.order.ordermain.vo.IncomeModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderConst;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainQueryModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderStatusEnum;
import com.aebiz.b2b2c.order.ordermainfrom.service.OrderMainFromService;
import com.aebiz.b2b2c.order.ordermainfrom.vo.OrderMainFromModel;
import com.aebiz.b2b2c.order.ordermainlog.service.OrderMainLogService;
import com.aebiz.b2b2c.order.ordermainlog.vo.OrderMainLogModel;
import com.aebiz.b2b2c.order.orderrouting.service.OrderRoutingService;
import com.aebiz.b2b2c.servicestaff.packagemanagement.service.PackageManagementService;
import com.aebiz.b2b2c.servicestaff.packagemanagement.vo.PackageManagementModel;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;
import com.aebiz.b2b2c.servicestaff.servicestaffinfo.service.ServicestaffinfoService;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.service.VipclubIntegralStatService;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomerlog.service.VirtualAccountCustomerLogService;
import com.aebiz.b2b2c.websiteoperation.customerservice.service.CustomerServiceService;
import com.aebiz.b2b2c.websiteoperation.customerservice.vo.CustomerServiceModel;
import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/sysback/order")
public class OrderMainController extends
		BaseController<OrderMainModel, OrderMainQueryModel> {
	private static String AccountSid="8a48b551506fd26f01507f16d7a52533";											//云通讯平台主帐号，在云通讯平台注册帐号获取
	private static String AccountToken="246ed57917ca4e608afa126af0411ebb";										//云通讯平台主帐号token，在云通讯平台注册帐号获取
	private static String AppId="aaf98f8951858ab8015185ae44a6002b";	
	//云通讯平台应用id，在云通讯官网登录后创建应用获取，demo应用和未上线应用只能在沙盒测试环境使用
	private static String ServerIP="sandboxapp.cloopen.com";	
	
	private OrderMainService myService;
	@Autowired
	public void setMyService(OrderMainService bs) {
		this.myService = bs;
		super.setBs(bs);
	}
	
	/* 注入城市service */
	@Autowired
	private CityService cityService;

	@Autowired
	private RegionService regionService;

	@Autowired
	private OrderMainLogService orderLogService;

	@Autowired
	private OrderMainFromService orderMainFromService;

	@Autowired
	private OrderDetailService orderDetailService;

	/* 订单投诉service */
	@Autowired
	private OrderComplainService complainService;

	/* 订单投诉service */
	@Autowired
	private ServicestaffinfoService infoService;


	@Autowired
	private ServicestaffService servicestaffService;

	// 积分
	@Autowired
	private VipclubIntegralStatService vipclubIntegralStatService;

	/* 虚拟账户日志 */
	@Autowired
	private VirtualAccountCustomerLogService virtualAccountCustomerLogService;

	@Autowired
	private CustomerAccountInteractive customerAccountInteractive;

	/* 注入站内信service */
	@Autowired
	private InnerMessageService messageService;

	/**
	 * 订单分账
	 */
	@Autowired
	private OrderRoutingService orderRoutingService;

	/* 用户service */
	@Autowired
	private CustomerService customerService;

	/* 管理员service */
	@Autowired
	private SysManagerService sysService;
	
	/* 客服的service */
	@Autowired
	private CustomerServiceService customerServiceService;
	/*注入医生私人套餐信息*/
	@Autowired
	PackageManagementService packageService ;
	
	public OrderMainController() {
		super("/order/sysback/ordermain", "OrderMain",
				OrderMainController.class);
	}

	/**
	 * 订单列表准备数据到页面
	 * 
	 * 1.订单状态筛选列表
	 * 
	 * @param model
	 * @param request
	 */
	@Override
	protected void preparedListData(Model model, HttpServletRequest request) {

		// 订单查询中 已关联的城市
		//sendCitysToPage(model);

		sendOrderStatusToPage(model);
		
	}

	/**
	 * 跳转到电话咨询订单详情页面 
	 * @param uuid
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/view/{uuid}" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String view(@PathVariable("uuid") String uuid, Model model,
			HttpServletRequest request) {
		OrderMainModel m = myService.getByUuid(uuid);

		//获取医生编号
		String doctorUuid = m.getDoctorUuid();
		ServicestaffModel staff = servicestaffService.getByUuid(doctorUuid);
		
		
		//获取患者编号
		String customerUuid = m.getCustomerUuid();
		CustomerModel customer = customerService.getByUuid(customerUuid);
		System.out.println("111111111111111111111111111111");
		System.out.println(customer.getRealName());
		System.out.println(customer.getSex());
		System.out.println(customer.getBirthday());
		System.out.println("222222222222222222222222222");
		System.out.println(staff.getHospitalName());
		System.out.println("33333333333333333333");
		model.addAttribute("m", m);
		model.addAttribute("staff", staff);
		model.addAttribute("customer",customer);
		
		//preparedViewData(model, m);

		return "order/sysback/ordermain/OrderMainView";
	}

	/**
	 * 跳转到私人套餐订单详情页面 
	 * @param uuid
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/viewPackage/{uuid}" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String viewPackage(@PathVariable("uuid") String uuid, Model model,
			HttpServletRequest request) {
		OrderMainModel m = myService.getByUuid(uuid);
		model.addAttribute("m", m);
		//获取医生编号
		String doctorUuid = m.getDoctorUuid();
		ServicestaffModel staff = servicestaffService.getByUuid(doctorUuid);
		
		//获取患者编号
		String customerUuid = m.getCustomerUuid();
		CustomerModel customer = customerService.getByUuid(customerUuid);

		model.addAttribute("staff", staff);
		model.addAttribute("customer",customer);
		
		//获取私人医生套餐信息
		PackageManagementModel pm = new PackageManagementModel();
		String packAgeUuid = m.getPackageUuid();
		if(!StringUtil.isEmpty(packAgeUuid)){
			 pm = packageService.getByUuid(packAgeUuid);
		}
		model.addAttribute("pm",pm);
		
		//preparedViewData(model, m);

		return "order/sysback/ordermain/OrderMainPackageView";
	}

	/**
	 * 准备数据发送到订单查看页查看
	 * 
	 * 3.订单的日志信息<br />
	 * 5.订单的来源信息<br />
	 * 
	 * @param model
	 */
	private void preparedViewData(Model model, OrderMainModel m) {

		// 3.订单的日志信息
		sendOrderLogToPage(model, m);

		// 5.订单的来源信息
		sendOrderFromToPage(model, m);

	}

	/**
	 * 订单超时未支付，关闭订单
	 * 
	 * @param uuid
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/closeorder/{uuid}" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public String closeOrderForOvertime(@PathVariable("uuid") String uuid,
			Model model, HttpServletRequest request) {
		OrderMainModel m = myService.getByUuid(uuid);
		// myService.closeOrderForOvertime(m);
		return "success";
	}

	/**
	 * 订单超时未确认收货，自动确认收货
	 * 
	 * @param uuid
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/autoreceive/{uuid}" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public String autoConfirmReceiveForOvertime(
			@PathVariable("uuid") String uuid, Model model,
			HttpServletRequest request) {
		OrderMainModel m = myService.getByUuid(uuid);

		// myService.autoReceiveOrderForOverTime(m);
		return "success";
	}

	/**
	 * 添加订单的记录备注信息到订单日志表中
	 * 
	 * @param orderMainUuid
	 * @param note
	 * @param operateType
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addLog")
	@ResponseBody
	public String addLog(@RequestParam("uuid") String orderMainUuid,
			@RequestParam("note") String note,
			@RequestParam("operateType") String operateType,
			HttpServletRequest request) {

		// 订单的操作日志
		orderLogService.createOrderLog(orderMainUuid, note, "1",
				LoginUserHelper.getLoginUserUuid(), "1", "13", "", "");

		return "success";

	}

	/**
	 * 
	 * **************************************************************
	 * 
	 * 
	 */

	/**
	 * 构造订单明细数据
	 * 
	 * @param model
	 * @param m
	 */
	private void sendDetailListToPage(Model model, OrderMainModel m) {
		List<OrderDetailModel> detailList = orderDetailService
				.getOrderDetailAndConsumerProtectionOrderId(m.getUuid());
		model.addAttribute("detailList", detailList);
	}

	/**
	 * 构造订单的日志信息
	 * 
	 * @param model
	 * @param m
	 */
	private void sendOrderLogToPage(Model model, OrderMainModel m) {
		List<OrderMainLogModel> orderLogList = orderLogService
				.getOrderMainLogModelListByOrderId(m.getUuid(), "13");
		model.addAttribute("orderLogList", orderLogList);
	}

	/**
	 * 构造订单的日志记录信息 订单记录
	 * 
	 * @param model
	 * @param m
	 */
	private void sendOrderLogToPage1(Model model, OrderMainModel m) {
		List<OrderMainLogModel> orderLogList = orderLogService
				.getOrderMainLogModelListByOrderId(m.getUuid());
		model.addAttribute("LogList", orderLogList);
	}

	/**
	 * 构造订单的来源信息
	 * 
	 * @param model
	 * @param m
	 */
	private void sendOrderFromToPage(Model model, OrderMainModel m) {
		OrderMainFromModel omfm = orderMainFromService
				.getOrderMainFromModelByOrderId(m.getUuid());
		model.addAttribute("omfm", omfm);
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
	 * 转向电话咨询订单 列表界面
	 * 
	 * @param wm
	 *            界面和控制器之间交互数据的webmodel
	 * @param model
	 *            Spring web mvc的 数据模型，用来带数据到前台页面，用的前缀是 “listModel”
	 * @param request
	 *            Http请求对象
	 * @return 要返回的列表页面
	 */
	@RequestMapping(value = "/list/{nowPage}/{pageShow}", method = { RequestMethod.GET })
	public String list(@ModelAttribute("wm") BaseWebModel wm, Model model,
			HttpServletRequest request) {

		OrderMainQueryModel qm = this.getQueryModel();

		// 判断是否需要初始化查询
		String init = request.getParameter("init");
		if (!"true".equalsIgnoreCase(init)) {
			Object obj = SecurityUtils.getSubject().getSession()
					.getAttribute("OrderMainIsQuery");
			if ((obj != null) && (obj.toString().equals("true"))) {
				qm = (OrderMainQueryModel) SecurityUtils.getSubject()
						.getSession().getAttribute("OrderMainQueryModel");
			}
		}
		
		//根据订单类型搜素 1：电话咨询订单
		qm.setSerachtype("1");
		qm = preparedQMFixValue(qm);

		wm.setTotalNum(myService.getOrderCount(qm));
		List<OrderMainModel> list = myService.getByCondition(qm,wm.getFromNum(), wm.getPageShow());
		wm.setRows(list);
		request.setAttribute("qm", qm);

		this.preparedListData(model, request);
		return "order/sysback/ordermain/OrderMainList";
		
	}

	/**
	 * 执行查询操作，查询后转向list界面
	 * 
	 * @param qm
	 *            查询的model
	 * @param request
	 *            Http请求对象
	 * @return 转向list的路径
	 */
	@RequestMapping(value = "/query", method = { RequestMethod.POST })
	public String query(OrderMainQueryModel qm,
			@RequestParam("pageShow") String pageShow,
			HttpServletRequest request) {

		// 辅助设置要查询的条件的比较方式
		Map<String, String[]> map = request.getParameterMap();
		for (String key : map.keySet()) {
			if (key.endsWith("_q")) {
				String v = map.get(key)[0];
				((OrderMainQueryModel) qm).getMapCondition().put(
						key.substring(0, key.length() - 2),
						ConditionOpTypeEnum.valueOf(v).getCode());
			}
		}

		SecurityUtils.getSubject().getSession()
				.setAttribute("OrderMainIsQuery", "true");
		SecurityUtils.getSubject().getSession()
				.setAttribute("OrderMainQueryModel", qm);
		
		//订单状态
		String orderState = request.getParameter("orderState");
		SecurityUtils.getSubject().getSession()
				.setAttribute("orderState", orderState);

		if (!StringUtil.isEmpty(orderState)) {
			return "redirect:/sysback/order/list/" + orderState + "/1/"
					+ pageShow;
		} else {
			return "redirect:/sysback/order/list/1/" + pageShow;
		}
	}

	/**
	 * 查询订单 待支付订单、待审核订单、今日订单任务
	 * 
	 * @param
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list/{orderState}/{nowPage}/{pageShow}", method = { RequestMethod.GET })
	public String listtoday(@PathVariable("orderState") String orderState,
			@ModelAttribute("wm") BaseWebModel wm, Model model,
			HttpServletRequest request) {

		/* 将及需要搜索的类型放入主订单的QueryModel */
		OrderMainQueryModel qm = this.getQueryModel();

		// 判断是否需要初始化查询
		String init = request.getParameter("init");
		if (!"true".equalsIgnoreCase(init)) {
			Object obj = SecurityUtils.getSubject().getSession()
					.getAttribute("OrderMainIsQuery");
			if ((obj != null) && (obj.toString().equals("true"))) {
				qm = (OrderMainQueryModel) SecurityUtils.getSubject()
						.getSession().getAttribute("OrderMainQueryModel");
			}
		}
		String orderTime = request.getParameter("orderTime");
		//订单状态
		if(orderState.equals("4")){
		    qm.setOrderTime(DateFormatHelper.getNowTimeStr("yyyy-MM-dd"));
		}
		
		qm.setSerachtype("1");
		qm.setOrderState(orderState);
		qm = preparedQMFixValue(qm);
		wm.setTotalNum(myService.getOrderCount(qm));
		List<OrderMainModel> list = myService.getByCondition(qm,
				wm.getFromNum(), wm.getPageShow());
		wm.setRows(list);
		
		request.setAttribute("qm", qm);
		model.addAttribute("returnType", "list");
		this.preparedListData(model, request);
		//待支付
		if(orderState.equals("1")){
			return "order/sysback/ordermain/OrderMainPayList";
		}else if(orderState.equals("4")){
			//今日订单详情
			return "order/sysback/ordermain/TodayMainList";
		}else if(orderState.equals("7")){
	         //动态任务
	         return "order/sysback/ordermain/TodayMainList";
		}else if(orderState.equals("5")){
            //电话回访管理
            return "order/sysback/ordermain/telephoneManageList";
		}else{
		  //待审核
		    return "order/sysback/ordermain/OrderMainCheckList";
		}
		
	}
	
	/**
     * 转向电话咨询订单 列表界面
     * 
     * @param wm
     *            界面和控制器之间交互数据的webmodel
     * @param model
     *            Spring web mvc的 数据模型，用来带数据到前台页面，用的前缀是 “listModel”
     * @param request
     *            Http请求对象
     * @return 要返回的列表页面
     */
    @RequestMapping(value = "/telist/{nowPage}/{pageShow}", method = { RequestMethod.GET })
    public String telist(@ModelAttribute("wm") BaseWebModel wm, Model model,
                    HttpServletRequest request) {
            OrderMainQueryModel qm = this.getQueryModel();
            // 判断是否需要初始化查询
            String init = request.getParameter("init");
            if (!"true".equalsIgnoreCase(init)) {
                    Object obj = SecurityUtils.getSubject().getSession()
                                    .getAttribute("OrderMainIsQuery");
                    if ((obj != null) && (obj.toString().equals("true"))) {
                            qm = (OrderMainQueryModel) SecurityUtils.getSubject()
                                            .getSession().getAttribute("OrderMainQueryModel");
                    }
            }
            //根据订单类型搜素 1：电话咨询订单
            qm = preparedQMFixValue(qm);
            qm.setSerachtype("1");
            wm.setTotalNum(myService.getOrderCount(qm));
            List<OrderMainModel> list = myService.getByCondition(qm,wm.getFromNum(), wm.getPageShow());
            wm.setRows(list);
            request.setAttribute("qm", qm);

            this.preparedListData(model, request);
            return "order/sysback/ordermain/TelOrderMainPayList";
            
    }
	
	
	/**
	 * 转向套餐列表界面
	 * 
	 * @param wm
	 *            界面和控制器之间交互数据的webmodel
	 * @param model
	 *            Spring web mvc的 数据模型，用来带数据到前台页面，用的前缀是
	 * @param request
	 *            Http请求对象
	 * @return 要返回的列表页面
	 */
	@RequestMapping(value = "/packagelist/{nowPage}/{pageShow}", method = { RequestMethod.GET })
	public String packagelist(@ModelAttribute("wm") BaseWebModel wm, 
			Model model,HttpServletRequest request) {

		OrderMainQueryModel qm = this.getQueryModel();

		// 判断是否需要初始化查询
		String init = request.getParameter("init");
		if (!"true".equalsIgnoreCase(init)) {
			Object obj = SecurityUtils.getSubject().getSession()
					.getAttribute("OrderMainIsQuery");
			if ((obj != null) && (obj.toString().equals("true"))) {
				qm = (OrderMainQueryModel) SecurityUtils.getSubject()
						.getSession().getAttribute("OrderMainQueryModel");
			}
		}
		//获取订单类型 1：电话 2：私人套餐
		qm.setSerachtype("2");
		qm = preparedQMFixValue(qm);

		wm.setTotalNum(myService.getOrderCount(qm));
		List<OrderMainModel> list = myService.getByCondition(qm,wm.getFromNum(), wm.getPageShow());
		wm.setRows(list);
		request.setAttribute("qm", qm);
		this.preparedListData(model, request);
		
		return "order/sysback/ordermain/OrderMainPackageList";
		
	}
	
	/**
	 * 执行套餐查询操作，查询后转向list界面
	 * 
	 * @param qm
	 *            查询的model
	 * @param request
	 *            Http请求对象
	 * @return 转向list的路径
	 */
	@RequestMapping(value = "/packagequery", method = { RequestMethod.POST })
	public String packagequery(OrderMainQueryModel qm,
			@RequestParam("pageShow") String pageShow,
			HttpServletRequest request) {

		// 辅助设置要查询的条件的比较方式
		Map<String, String[]> map = request.getParameterMap();
		for (String key : map.keySet()) {
			if (key.endsWith("_q")) {
				String v = map.get(key)[0];
				((OrderMainQueryModel) qm).getMapCondition().put(
						key.substring(0, key.length() - 2),
						ConditionOpTypeEnum.valueOf(v).getCode());
			}
		}
		SecurityUtils.getSubject().getSession()
				.setAttribute("OrderMainIsQuery", "true");
		SecurityUtils.getSubject().getSession()
				.setAttribute("OrderMainQueryModel", qm);
		
		//订单状态
		String orderState = request.getParameter("orderState");
		SecurityUtils.getSubject().getSession()
				.setAttribute("orderState", orderState);

		if (!StringUtil.isEmpty(orderState)) {
			return "redirect:/sysback/order/packagelist/" + orderState + "/1/"
					+ pageShow;
		} else {
			return "redirect:/sysback/order/packagelist/1/" + pageShow;
		}
	}
	
	
	/**
	 * 查询订单 待支付订单、待审核订单
	 * 
	 * @param
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/packagelist/{orderState}/{nowPage}/{pageShow}", method = { RequestMethod.GET })
	public String packagelist(@PathVariable("orderState") String orderState,
			@ModelAttribute("wm") BaseWebModel wm, Model model,
			HttpServletRequest request) {

		/* 将及需要搜索的类型放入主订单的QueryModel */
		OrderMainQueryModel qm = this.getQueryModel();

		// 判断是否需要初始化查询
		String init = request.getParameter("init");
		if (!"true".equalsIgnoreCase(init)) {
			Object obj = SecurityUtils.getSubject().getSession()
					.getAttribute("OrderMainIsQuery");
			if ((obj != null) && (obj.toString().equals("true"))) {
				qm = (OrderMainQueryModel) SecurityUtils.getSubject()
						.getSession().getAttribute("OrderMainQueryModel");
			}
		}
		
		qm.setSerachtype("2");
		//订单状态
		qm.setOrderState(orderState);
		qm = preparedQMFixValue(qm);
		wm.setTotalNum(myService.getOrderCount(qm));
		List<OrderMainModel> list = myService.getByCondition(qm,
				wm.getFromNum(), wm.getPageShow());
		wm.setRows(list);
		
		request.setAttribute("qm", qm);
		this.preparedListData(model, request);
		//待支付
		return "order/sysback/ordermain/OrderMainPackagePayList";
		
	}
	
	/**
	 * 后台审核订单
	 * 
	 * @param orderId
	 * @param request
	 * 
	 *            xl add 20150309
	 * @return
	 */
	@RequestMapping(value = "/checkOrder", method = RequestMethod.GET)
	@ResponseBody
	public String checkOrder(@RequestParam("orderId") String orderId,
			@RequestParam("state") String state,
			@RequestParam("note") String note, HttpServletRequest request) {
		OrderMainModel om = myService.getByUuid(orderId);
		/*审核状态 通过（订单状态为待执行） ，审核状态 不通过(无效订单)*/
		om.setState(state);
		om.setNote(note);
		myService.update(om);
		// 添加订单的日志信息
		orderLogService.createOrderLog(orderId, note, "1",
				LoginUserHelper.getLoginUserUuid(), "2", "2",
				OrderConst.ORDERSTATUS_QX, om.getPayType());

		return "success";

	}

	/**
	 * 后台客服取消订单
	 * 
	 * @param orderId
	 * @param request
	 * 
	 * @return
	 */
	@RequestMapping(value = "/orderCancel", method = RequestMethod.GET)
	@ResponseBody
	public String orderCancel(@RequestParam("orderId") String orderId, HttpServletRequest request) {
		OrderMainModel om = myService.getByUuid(orderId);

		// 线下支付直接取消订单
		om.setState("2");
		/* 新添加部分 2015/05/20 10:58 */
		om.setOperateTime(DateFormatHelper.getNowTimeStr());
		om.setCancelOrderTime(DateFormatHelper.getNowTimeStr());
		
		// 取消订单 
		myService.update(om);

		// 根据订单编号获取该订单档期表
		
		/*List<ServiceStaffScheduleModel> list = serviceStaffScheduleService
				.getServiceStaffScheduleListByOrderId(orderId);
		if (list != null && list.size() > 0) {
			String time = "";
			List<String> uuids = new ArrayList<String>();
			for (int i = 0; i < list.size(); i++) {
				ServiceStaffScheduleModel sssm = list.get(i);
				sssm.setType("2");// 在取消该订单时，将档期状态更新为2
				serviceStaffScheduleService.update(sssm);
				time = sssm.getDate() + "日" + sssm.getStartTime() + "-"
						+ sssm.getEndTime();
				String serviceStaffUuid = sssm.getServiceStatffUuid();
				uuids.add(serviceStaffUuid);
				// 保存消息
				saveInnerMessage(orderId, time, serviceStaffUuid);
			}
			// 发送取消订单消息给家政员
			sendInnerMessage(orderId, time, uuids);
		}*/

		// 添加订单的日志信息
		orderLogService.createOrderLog(orderId, " 平台取消订单", "1",
				LoginUserHelper.getLoginUserUuid(), "2", "2",
				OrderConst.ORDERSTATUS_QX, om.getPayType());

		return "success";

	}

	
	/**
	 * 财务管理-收入管理
	 * 
	 * @param wm
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/incomeList/{nowPage}/{pageShow}", method = { RequestMethod.GET })
	public String incomeList(@ModelAttribute("wm") BaseWebModel wm,
			Model model, HttpServletRequest request) {
		/* 将及需要搜索的退款订单类型放入主订单的QueryModel */
		OrderMainQueryModel qm = this.getQueryModel();

		// 判断是否需要初始化查询
		String init = request.getParameter("init");
		if (!"true".equalsIgnoreCase(init)) {
			Object obj = SecurityUtils.getSubject().getSession()
					.getAttribute("OrderMainIsQueryQs");
			if ((obj != null) && (obj.toString().equals("true"))) {
				qm = (OrderMainQueryModel) SecurityUtils.getSubject()
						.getSession().getAttribute("OrderMainQueryModel");
			}
		}
		qm = preparedQMFixValue(qm);
		
		String receiveTime = DateFormatHelper.getNowTimeStr(DateFormatHelper.FORMAT_DATE_STR).substring(0, 7);

		wm.setTotalNum(myService.getCountOrderListByReceiveTime(receiveTime));
		List<Object> list = myService.getOrderListByReceiveTime(receiveTime,
				wm.getFromNum(), wm.getPageShow());
		List<IncomeModel> incomeList = new ArrayList();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				IncomeModel im = new IncomeModel();
				// 时间
				im.setDate((String) obj[0]);
				// 订单总金额
				im.setOrderTotalMoney((Double) obj[1]);
				// 积分使用金额
				im.setIntegralTotalMoney((Double) obj[2]);
				// 优惠券金额
				im.setCouponTotalMoney((Double) obj[3]);
				// 其他折扣金额
				im.setOtherTotalMoney((Double) obj[6] + (Double) obj[7]);
				// 实际收入金额
				im.setPaidTotalMoney((Double) obj[5]);
				// 平台分账,1为平台分账
				double sysrouting = orderRoutingService
						.getTotalRoutingMoneyByTime((String) obj[0], "1");
				im.setSysRoutingMoney(sysrouting);
				incomeList.add(im);
			}
		}
		wm.setRows(incomeList);
		double totalPaid = myService.getPaidMoneyByReceiveTime(receiveTime);
		model.addAttribute("totalPaid", totalPaid);
		request.setAttribute("qm", qm);
		return "order/sysback/ordermain/OrderIncomeList";
	}

	/**
	 *获取订单展示数量
	 * 
	 * @param menuId
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/getOrderNum" }, method = { RequestMethod.POST })
	@ResponseBody
	public String getOrderNum(@RequestParam("menuId") String menuId,
			Model model, HttpServletRequest request) {
		String serachType = "1";
		if (menuId.equals("OrdeServiceLIst")) {
			serachType = "1";
		} else if (menuId.equals("todayOrderTask1")) {
			serachType = "2";
		} else if (menuId.equals("dynamicTask1")) {
			serachType = "3";
		}
		int orderNum = myService.getOrderCount(serachType);
		String num = orderNum + "";

		return num;

	}

	/**
	 * 跳转到执行订单详情
	 * @param uuid
	 * @param model
	 * @param request
	 * @return
	 */
   @RequestMapping(value = { "/viewTask/{uuid}" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
    public String viewTask(@PathVariable("uuid") String uuid, Model model,
                    HttpServletRequest request) {
            OrderMainModel m = myService.getByUuid(uuid);

            //获取医生编号
            String doctorUuid = m.getDoctorUuid();
            ServicestaffModel staff = servicestaffService.getByUuid(doctorUuid);
            
            //获取患者编号
            String customerUuid = m.getCustomerUuid();
            CustomerModel customer = customerService.getByUuid(customerUuid);
            
            List<CustomerServiceModel> CustomerServiceModellist = customerServiceService.getAll();

            model.addAttribute("m", m);
            model.addAttribute("csmList", CustomerServiceModellist);

            model.addAttribute("staff", staff);
            model.addAttribute("customer",customer);
            
            preparedViewData(model, m);

            return "order/sysback/ordermain/OrderMainViewTask";
    }
    
    /**
     * 跳转到电话订单的回访详情
     * @param uuid
     * @param model
     * @param request
     * @return
     */
   @RequestMapping(value = { "/viewTelTask/{uuid}" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
    public String viewTelTask(@PathVariable("uuid") String uuid, Model model,
                    HttpServletRequest request) {
            OrderMainModel m = myService.getByUuid(uuid);

            //获取医生编号
            String doctorUuid = m.getDoctorUuid();
            ServicestaffModel staff = servicestaffService.getByUuid(doctorUuid);
            List<CustomerServiceModel> CustomerServiceModellist = customerServiceService.getAll();

            //获取患者编号
            String customerUuid = m.getCustomerUuid();
            CustomerModel customer = customerService.getByUuid(customerUuid);
            model.addAttribute("m", m);
            model.addAttribute("staff", staff);
            model.addAttribute("customer",customer);
            
            model.addAttribute("csmList", CustomerServiceModellist);
            
            preparedViewData(model, m);

            return "order/sysback/ordermain/OrderMainViewTelTask";
   }

}