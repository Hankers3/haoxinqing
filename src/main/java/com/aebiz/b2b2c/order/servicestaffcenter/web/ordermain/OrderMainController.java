package com.aebiz.b2b2c.order.servicestaffcenter.web.ordermain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aebiz.b2b2c.baseframework.basecrud.vo.ConditionOpTypeEnum;
import com.aebiz.b2b2c.baseframework.basecrud.vo.DataTablesPageParam;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.basecrud.web.vo.BaseWebModel;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.order.ordermain.service.OrderMainService;
import com.aebiz.b2b2c.order.ordermain.vo.OrderConst;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainQueryModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderStatusEnum;
import com.aebiz.b2b2c.order.servicestaffcenter.service.ordermain.OrderMainUcenterService;



@Controller("SsOrderMainController")
@RequestMapping("/servicestaff/order")
public class OrderMainController extends
		BaseController<OrderMainModel, OrderMainQueryModel> {
	private OrderMainUcenterService myService;
	@Autowired
	public void setMyService(OrderMainUcenterService bs) {
		this.myService = bs;
		super.setBs(bs);
	}
	
	/*注入订单主service*/
	@Autowired
	private OrderMainService orderMainService;
	public OrderMainController() {
		super("/order/servicestaff/ordermain", "OrderMain",
				OrderMainController.class);
	}
	
	
	/**
	 * 转向列表界面
	 * 
	 * @param wm
	 *            界面和控制器之间交互数据的webmodel
	 * @param model
	 *            Spring web mvc的 数据模型，用来带数据到前台页面，用的前缀是 “listModel”
	 * @param request
	 *            Http请求对象
	 * @return 要返回的列表页面
	 */
	@RequestMapping(value = "/list/{orderState}/{nowPage}/{pageShow}", method = { RequestMethod.GET })
	public String list(@ModelAttribute("wm") BaseWebModel wm, @PathVariable("orderState") String orderState, Model model,
			HttpServletRequest request) {
		String serviceStaffUuid = LoginUserHelper.getStoreLoginUserUuid(); 
		if(StringUtil.isEmpty(serviceStaffUuid)){
			return "redirect:/usercenter/servicestaffcomb/toLogin";
		}
		
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
		String state = orderState ;
		if(!StringUtil.isEmpty(orderState)&&"0".equals(orderState)){
			state="";
		}
		qm.setOrderState(state);
		
		qm = preparedQMFixValue(qm);
		
		wm.setTotalNum(myService.getOrderCount(qm));
		List<OrderMainModel> list = myService.getOrderListByCondition(qm,
				wm.getFromNum(), wm.getPageShow());
		wm.setRows(list);

		// 判断查询条件是否关闭
		String showOrClose = (String) SecurityUtils.getSubject().getSession()
				.getAttribute("showOrClose");
		if (!StringUtil.isEmpty(showOrClose)) {
			request.setAttribute("showOrClose", showOrClose);
		}

		request.setAttribute("qm", qm);

		this.preparedListData(model, request);
		model.addAttribute("smyorder", "true");
		return "order/servicestaff/ordermain/OrderMainList";
	}
	
	/**
	 * 订单查询
	 */
	@Override
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public String query(@ModelAttribute("qm") OrderMainQueryModel qm,
			@RequestParam("pageShow") String pageShow, HttpServletRequest request) {
		String orderState = request.getParameter("orderState");
		
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

		String searchTpe = request.getParameter("searchTpe");
		SecurityUtils.getSubject().getSession()
				.setAttribute("searchTpe", searchTpe);
		
		if(StringUtil.isEmpty(orderState)){
			orderState="0";
		}
		
		return  "redirect:/servicestaff/order/list/"+orderState+"/1/" + pageShow;
	}
	
	
	@Override
	protected void preparedListData(Model model, HttpServletRequest request) {
		//订单类型
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
	 * 获取家政员绩效
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @2015-4-17
	 * @author :SZH
	 * URL：
	 *  GET:/servicestaff/order/getperformance?init=true
	 */
	@RequestMapping("/getperformance")
	public String getperformance(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,
			@RequestParam("init") String init
			){
		String serviceStaffUuid = LoginUserHelper.getStoreLoginUserUuid(); 
		if(StringUtil.isEmpty(serviceStaffUuid)){
			return "redirect:/usercenter/servicestaffcomb/toLogin";
		}
		String timeType ="0";
		//判断是否是第一次访问（URL）访问
		if(!"true".equals(init)){
			 timeType = request.getParameter("timeType");
		}
		
		 if(!"true".equals(init)){
			//ajax返回
			 return "order/servicestaff/ordermain/Showperformance";
		 }
		//get访问
		return "order/servicestaff/ordermain/Performance";
	}
	/**
	 * 订单列表查询的条件构造，查询正常状态的订单
	 * 
	 * @param qm
	 * @return
	 */
	/*protected OrderMainQueryModel preparedQMFixValue1(OrderMainQueryModel qm) {
		// 默认为只操作有效的数据
		qm.setDelFlag(qm.DEL_FLAG_VALID);
		qm.getMapCondition().put("delFlag", ConditionOpTypeEnum.EQ.getCode());

		// 会员中心只查询本会员的数据
		// qm.setCustomerUuid("CUSTOMER000000000001");
		//qm.setCustomerUuid(LoginUserHelper.getCustomerLoginUserUuid());
		qm.setServiceStaffUuid("rvicestaff0000000723");
		qm.getMapCondition().put("serviceStaffUuid",
				ConditionOpTypeEnum.EQ.getCode());
		
		//设置排序字段
		qm.setSortName("orderTime");

		return qm;
	}*/
}