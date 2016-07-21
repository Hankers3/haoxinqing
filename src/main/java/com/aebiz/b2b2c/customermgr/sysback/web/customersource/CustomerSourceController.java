package com.aebiz.b2b2c.customermgr.sysback.web.customersource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.customermgr.customersource.service.CustomerSourceService;
import com.aebiz.b2b2c.customermgr.customersource.vo.CustomerSourceModel;
import com.aebiz.b2b2c.customermgr.customersource.vo.CustomerSourceQueryModel;

@Controller
@RequestMapping("/sysback/customersource")
public class CustomerSourceController extends
		BaseController<CustomerSourceModel, CustomerSourceQueryModel> {
	private CustomerSourceService myService;

	@Autowired
	public void setMyService(CustomerSourceService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public CustomerSourceController() {
		super("customermgr/sysback/customersource", "CustomerSource",
				CustomerSourceController.class);
	}
}