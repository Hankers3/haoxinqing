package com.aebiz.b2b2c.customermgr.sysback.web.customerinfomodify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.customermgr.customerinfomodify.service.CustomerInfoModifyService;
import com.aebiz.b2b2c.customermgr.customerinfomodify.vo.CustomerInfoModifyModel;
import com.aebiz.b2b2c.customermgr.customerinfomodify.vo.CustomerInfoModifyQueryModel;

@Controller
@RequestMapping("/sysback/customerinfomodify")
public class CustomerInfoModifyController extends
		BaseController<CustomerInfoModifyModel, CustomerInfoModifyQueryModel> {
	private CustomerInfoModifyService myService;

	@Autowired
	public void setMyService(CustomerInfoModifyService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public CustomerInfoModifyController() {
		super("customermgr/sysback/customerinfomodify", "CustomerInfoModify",
				CustomerInfoModifyController.class);
	}
}