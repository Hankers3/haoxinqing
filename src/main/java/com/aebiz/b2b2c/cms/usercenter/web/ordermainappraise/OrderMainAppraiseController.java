package com.aebiz.b2b2c.cms.usercenter.web.ordermainappraise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;


import com.aebiz.b2b2c.cms.ordermainappraise.service.OrderMainAppraiseService;
import com.aebiz.b2b2c.cms.ordermainappraise.vo.OrderMainAppraiseModel;
import com.aebiz.b2b2c.cms.ordermainappraise.vo.OrderMainAppraiseQueryModel;

@Controller
@RequestMapping("/ordermainappraise")
public class OrderMainAppraiseController extends BaseController<OrderMainAppraiseModel,OrderMainAppraiseQueryModel>{
	private OrderMainAppraiseService myService;
	@Autowired
	public void  setMyService(OrderMainAppraiseService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public OrderMainAppraiseController(){
		super("cms/ordermainappraise","OrderMainAppraise",OrderMainAppraiseController.class);
	}
}