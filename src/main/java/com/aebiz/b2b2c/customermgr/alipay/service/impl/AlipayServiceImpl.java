package com.aebiz.b2b2c.customermgr.alipay.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.exception.AppException;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.customermgr.alipay.dao.AlipayDAO;
import com.aebiz.b2b2c.customermgr.alipay.service.AlipayService;
import com.aebiz.b2b2c.customermgr.alipay.vo.AlipayModel;
import com.aebiz.b2b2c.customermgr.alipay.vo.AlipayQueryModel;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.order.ordermain.service.OrderMainService;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;
import com.aebiz.b2b2c.order.ordermainlog.service.OrderMainLogService;
import com.aebiz.b2b2c.order.ordermainpay.service.OrderMainPayService;
import com.aebiz.b2b2c.order.ordermainpay.vo.OrderMainPayModel;
import com.aebiz.b2b2c.product.productmain.service.ProductMainService;
import com.aebiz.b2b2c.product.productpub.service.ProductService;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;

@Service
@Transactional
public class AlipayServiceImpl extends BaseServiceImpl<AlipayModel,AlipayQueryModel> implements AlipayService {
	private AlipayDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(AlipayDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}
    //log4j记录日志
    private static Logger log = Logger.getLogger("alipayBack_logger");
    
	@Autowired
	private OrderMainLogService orderLogService;
	//订单
	@Autowired
	private OrderMainService orderMainService;
	//订单支付信息
	@Autowired
	private OrderMainPayService orderMainPayService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductMainService productMainService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ServicestaffService servicestaffService;


	@Override
	public String create(AlipayModel m) {
		m.setUuid(us.getNextUuid("Alipay",10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	
	@Override
	public void update(AlipayModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	
	@Override
	public void delete(AlipayModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 订单支付后修改订单状态
	 */
	@Override
	public String payNotify(HttpServletRequest request, String orderId,
			double price, String paystatus) {
		System.out.println("=================payNotify======================================");
		// 记录日志
		log.info("支付宝支付修改订单状态=====》");
		// 根据订单ID获取用户ID
		System.out.println("===========orderId==========="+orderId+"==================");
		final OrderMainModel order = orderMainService.getByUuid(orderId);
		
		if (order == null) {
			return "false";
		}
		System.out.println("=================根据订单ID获取用户ID======================================");
		System.out.println("=========order.getPayMoney()============="+order.getPayMoney()+"=================================");
		System.out.println("========= price=========================="+price+"============");
		
		// 设置订单支付信息
		OrderMainPayModel orderMainPayModel  = new OrderMainPayModel();
		orderId = order.getUuid();
		//订单号
		orderMainPayModel.setOrderMainUuid(orderId);
		//支付为1 未支付为0
		orderMainPayModel.setPayState("1");
		
		//支付金额
		double payMoney = order.getPayMoney()-price;
		
		orderMainPayModel.setPayMoney(payMoney);
		//支付时间
		orderMainPayModel.setPayTime(DateFormatHelper.getNowTimeStr());
		//支付类型
		orderMainPayModel.setPayType("1");
		if (order.getPayMoney() < 0) {
			order.setPayMoney(0);
			orderMainPayModel.setPayMoney(0);
		}
		
		System.out.println("=================setState_update_start======================================");
		orderMainService.update(order);
		System.out.println("=================setState_update_end======================================");

		String temp= orderMainPayService.create(orderMainPayModel);
		System.out.println("==========temp======="+temp+"======================================");
		// 增加订单日志
		StringBuffer note = new StringBuffer();
		note.append(" 用户").append(order.getCustomerUuid());
		note.append("使用").append("支付宝方式付款").append(price).append("元");
		log.info("订单号："+orderId+note);
		
		return "true";

	}
}