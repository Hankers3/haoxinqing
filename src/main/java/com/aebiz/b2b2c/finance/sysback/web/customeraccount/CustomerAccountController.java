package com.aebiz.b2b2c.finance.sysback.web.customeraccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.finance.customeraccount.service.CustomerAccountService;
import com.aebiz.b2b2c.finance.customeraccount.vo.CustomerAccountModel;
import com.aebiz.b2b2c.finance.customeraccount.vo.CustomerAccountQueryModel;

@Controller
@RequestMapping("/sysback/customeraccount")
public class CustomerAccountController extends BaseController<CustomerAccountModel,CustomerAccountQueryModel>{
	private CustomerAccountService myService;
	@Autowired
	public void  setMyService(CustomerAccountService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public CustomerAccountController(){
		super("finance/sysback/customeraccount","CustomerAccount",CustomerAccountController.class);
	}
}