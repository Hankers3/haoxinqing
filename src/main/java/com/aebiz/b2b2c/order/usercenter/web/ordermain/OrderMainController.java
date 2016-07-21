package com.aebiz.b2b2c.order.usercenter.web.ordermain;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.basecrud.web.vo.BaseWebModel;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.order.ordermain.service.OrderMainService;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainQueryModel;
import com.aebiz.b2b2c.order.ordermaincomment.service.OrderMainCommentService;
import com.aebiz.b2b2c.order.ordermaincomment.vo.OrderMainCommentModel;




@Controller("UCOrderMainController")
@RequestMapping("/usercenter/order")
public class OrderMainController extends
		BaseController<OrderMainModel, OrderMainQueryModel> {
	private OrderMainService myService;
	/*
	 * 评价service
	 */
	@Autowired
	private OrderMainCommentService orderMainCommentService;
	@Autowired
	public void setMyService(OrderMainService bs) {
		this.myService = bs;
		super.setBs(bs);
	}
	
	public OrderMainController() {
		super("/order/usercenter/ordermain", "OrderMain",
				OrderMainController.class);
	}
	/**
	 * 查询订单信息和评论
	 * @param model
	 * @return
	 * @2015-3-30
	 * @author :SZH
	 * URL:/usercenter/order/toOrdermainCommentList/1/2
	 */
	@RequestMapping("/toOrdermainCommentList/{nowPage}/{pageShow}")
	public String toOrdermainCommentList(
			@ModelAttribute("wm") BaseWebModel wm,
			Model model)
	{
		CustomerModel customer =  LoginUserHelper.getCustomerLoginUserModel();
 		String customerUuid = customer.getUuid();
		OrderMainQueryModel qm = new OrderMainQueryModel();
		qm.setCustomerUuid(customerUuid);
		wm.setTotalNum(myService.getOrderList(customerUuid,0,0).size());
		int pageCount =wm.getPageShow();
		int pageNo = wm.getNowPage();
		

		//返回orderMainModel
		List<Map<String,Object>> maps= orderMainCommentService.getOrderMainCommentAndOthers(customerUuid,pageCount,pageNo);
		//wm.setRows(maps);
		model.addAttribute("maps",maps);
		model.addAttribute("comment","true");
		return "order/usercenter/ordermain/OrderMainCommentList";
	}
	/**
	 * 提交评价 
	 * @param orderMainUuid
	 * @param content
	 * @param request
	 * @param response
	 * @return
	 * @2015-3-27
	 * @author : SZH
	 * URL:/usercenter/order/addOrdermainComment
	 */
	@RequestMapping("/addOrdermainComment")
	public String addOrdermainComment(
		@RequestParam("orderMainUuid") String orderMainUuid,
		@RequestParam("serviceScore") String serviceScore,
		@RequestParam("content") String content,
		Model model,
		HttpServletRequest request,
		HttpServletResponse response){
		//new一个对象，并把字段放进去
		OrderMainCommentModel orderMainCommentModel = new OrderMainCommentModel();
		orderMainCommentModel.setOrderMainUuid(orderMainUuid);
		orderMainCommentModel.setServiceScore(serviceScore);
		orderMainCommentModel.setContent(content);
		//用service将对象入库
		orderMainCommentService.create(orderMainCommentModel);
		model.addAttribute("om",orderMainCommentModel);
		return "order/usercenter/ordermain/ShowComment";
	}
}