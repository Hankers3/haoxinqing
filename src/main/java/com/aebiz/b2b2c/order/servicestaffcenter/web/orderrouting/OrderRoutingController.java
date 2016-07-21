package com.aebiz.b2b2c.order.servicestaffcenter.web.orderrouting;

import java.util.List;
import java.util.Map;

import javax.persistence.Transient;
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
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.basecrud.web.vo.BaseWebModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.order.ordermain.service.OrderMainService;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;
import com.aebiz.b2b2c.order.orderrouting.service.OrderRoutingService;
import com.aebiz.b2b2c.order.orderrouting.vo.OrderRoutingModel;
import com.aebiz.b2b2c.order.orderrouting.vo.OrderRoutingQueryModel;
import com.aebiz.b2b2c.order.servicestaffcenter.service.ordermain.OrderMainUcenterService;
import com.aebiz.b2b2c.order.servicestaffcenter.web.ordermain.OrderMainController;
import com.aebiz.b2b2c.order.sysback.web.orderrouting.vo.OrderRoutingWebModel;

@Controller("SsOrderRoutingController")
@RequestMapping("/servicestaff/orderrouting")
public class OrderRoutingController extends BaseController<OrderRoutingModel,OrderRoutingQueryModel>{
	@Autowired
	private OrderMainUcenterService orderMainUcenterService;
	private OrderRoutingService myService;
	@Autowired
	public void  setMyService(OrderRoutingService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public OrderRoutingController(){
		super("order/servicestaff/orderrouting","orderrouting",OrderRoutingController.class);
	}
	
	/**
	 * 我的收入
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @2015-4-20
	 * @author :SZH
	 * URL:/servicestaff/orderrouting/getServicestaffIncome/1/2
	 */
	@RequestMapping("/getServicestaffIncome/{nowPage}/{pageShow}")
	public String getServicestaffIncome(
			HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("wm") OrderRoutingWebModel wm,
			Model model){
		String serviceStaffUuid = LoginUserHelper.getStoreLoginUserUuid(); 
		if(StringUtil.isEmpty(serviceStaffUuid)){
			return "redirect:/usercenter/servicestaffcomb/toLogin";
		}
		wm.setTotalNum(myService.getOrderRoutingsByServiceStaffId(serviceStaffUuid,0,0).size());
		int pageCount =wm.getPageShow();
		int pageNo = wm.getNowPage();
		
		List<OrderRoutingModel> orderRoutingModels = myService.getOrderRoutingsByServiceStaffId(serviceStaffUuid,pageCount,pageNo);
		model.addAttribute("myIncome",orderMainUcenterService.getServiceIncome(serviceStaffUuid));
		model.addAttribute("orderRoutingModels",orderRoutingModels);
		//用于家政员中心选中使用 
		model.addAttribute("orderrout", "true");
		
		return "order/servicestaff/orderrouting/serviceStaffIncome";
	}
	
}