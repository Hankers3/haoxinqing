package com.aebiz.b2b2c.order.sysback.web.orderrevisit;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.order.orderdetail.service.OrderDetailService;
import com.aebiz.b2b2c.order.orderdetail.vo.OrderDetailModel;
import com.aebiz.b2b2c.order.ordermain.service.OrderMainService;
import com.aebiz.b2b2c.order.ordermain.vo.OrderConst;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderStatusEnum;
import com.aebiz.b2b2c.order.ordermainaddress.service.OrderMainAddressService;
import com.aebiz.b2b2c.order.ordermainlog.service.OrderMainLogService;
import com.aebiz.b2b2c.order.orderrevisit.service.OrderRevisitService;
import com.aebiz.b2b2c.order.orderrevisit.vo.OrderRevisitModel;
import com.aebiz.b2b2c.order.orderrevisit.vo.OrderRevisitQueryModel;

@Controller
@RequestMapping("/sysback/orderrevisit")
public class OrderRevisitController extends BaseController<OrderRevisitModel,OrderRevisitQueryModel>{
	private OrderRevisitService myService;
	@Autowired
	public void  setMyService(OrderRevisitService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public OrderRevisitController(){
		super("order/sysback/orderrevisit","OrderRevisit",OrderRevisitController.class);
	}
	
	
	@Autowired
	private OrderMainService orderMainService;
	
	@Autowired
	OrderMainAddressService oraService;
	
	@Autowired
	OrderDetailService orderDetailService;

	@Autowired
	private OrderMainLogService orderLogService;
	
	/**
	 *今日成功订单---回访
	 * 
	 * @param uuid
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/revisitcl" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String viewCl(@RequestParam("uuid") String uuid,
			Model model,
			HttpServletRequest request) {
		//通过订单编号获得这个订单对应的收货地址对象
	/*	OrderMainAddressModel m=	oraService.getOrderMainAddressModelByUuid(uuid);
		model.addAttribute("m", m);
		*/
		 //通过订单编号查询订单明细列表
		List<OrderDetailModel> t=orderDetailService.getOrderDetailModelByOrderId(uuid);
		model.addAttribute("t", t);
		
		 OrderMainModel w=orderMainService.getByUuid(uuid);
		 model.addAttribute("w", w);
		
		return "order/sysback/orderrevisit/OrderRevisitAdd";
	}
	
	/**
	 * 
	 * @Description: (添加订单回访内容)    
	 * @author XP  
	 * @param model
	 * @param m
	 * @param request
	 * @return
	 * @date 2016-1-12 下午12:51:34
	 */
	@Override
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(Model model, @ModelAttribute("m") OrderRevisitModel m,
			HttpServletRequest request) {
	    
	    if(null!=m){
	    m.setCommentTime(DateFormatHelper.getNowTimeStr());
	    m.setManagerUuid(LoginUserHelper.getLoginUserUuid());
	    OrderMainModel om = orderMainService.getByUuid(m.getOrderMainUuid());
	        if(null!=om){
	            om.setState(OrderStatusEnum.DROPINCOMPLETE.getValue());
	            orderMainService.update(om);
	        }
	    myService.create(m);
	    }
	    return"redirect:/sysback/order/list/5/1/20";
	}
	
	
	/**
	 * 
	 * @Description: (将订单任务变成动态订单任务)    
	 * @author XP  
	 * @param orderId
	 * @param state
	 * @param note
	 * @param request
	 * @return
	 * @date 2016-1-12 下午2:55:12
	 */
	@RequestMapping(value = "/checkOrder", method = RequestMethod.POST)
	@ResponseBody
        public String checkOrder(@RequestParam("orderId") String orderId,
                        @RequestParam("state") String state,
                        @RequestParam("note") String note, HttpServletRequest request) {
                OrderMainModel om = orderMainService.getByUuid(orderId);
                /*将订单转为动态订单*/
                om.setState(state);
                om.setNote(note);
                orderMainService.update(om);
                return "success";

        }
}