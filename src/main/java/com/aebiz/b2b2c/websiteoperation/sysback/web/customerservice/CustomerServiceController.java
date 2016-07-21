package com.aebiz.b2b2c.websiteoperation.sysback.web.customerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.websiteoperation.customerservice.service.CustomerServiceService;
import com.aebiz.b2b2c.websiteoperation.customerservice.vo.CustomerServiceModel;
import com.aebiz.b2b2c.websiteoperation.customerservice.vo.CustomerServiceQueryModel;

@Controller
@RequestMapping("/sysback/customerservice")
public class CustomerServiceController extends BaseController<CustomerServiceModel,CustomerServiceQueryModel>{
	private CustomerServiceService myService;
	@Autowired
	public void  setMyService(CustomerServiceService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public CustomerServiceController(){
		super("websiteoperation/sysback/customerservice","CustomerService",CustomerServiceController.class);
	}
}