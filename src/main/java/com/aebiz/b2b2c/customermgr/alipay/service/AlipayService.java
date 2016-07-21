package com.aebiz.b2b2c.customermgr.alipay.service;

import javax.servlet.http.HttpServletRequest;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.customermgr.alipay.vo.AlipayModel;
import com.aebiz.b2b2c.customermgr.alipay.vo.AlipayQueryModel;

public interface AlipayService extends BaseService<AlipayModel,AlipayQueryModel>{
	
	/**
	 * 订单支付后修改状态
	 * @param request
	 * @param orderId
	 * @param price
	 * @param paystatus
	 * @return
	 */
	public String payNotify(HttpServletRequest request,String orderId,double price,String paystatus);
	
}
