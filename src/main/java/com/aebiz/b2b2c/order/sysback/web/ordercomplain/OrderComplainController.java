package com.aebiz.b2b2c.order.sysback.web.ordercomplain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.order.ordercomplain.service.OrderComplainService;
import com.aebiz.b2b2c.order.ordercomplain.vo.OrderComplainModel;
import com.aebiz.b2b2c.order.ordercomplain.vo.OrderComplainQueryModel;

@Controller
@RequestMapping("/ordercomplain")
public class OrderComplainController extends BaseController<OrderComplainModel,OrderComplainQueryModel>{
	private OrderComplainService myService;
	@Autowired
	public void  setMyService(OrderComplainService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public OrderComplainController(){
		super("order/ordercomplain","OrderComplain",OrderComplainController.class);
	}
}