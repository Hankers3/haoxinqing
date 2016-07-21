package com.aebiz.b2b2c.customermgr.sysback.web.customerinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.customermgr.customerinfo.service.CustomerInfoService;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoModel;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoQueryModel;

@Controller
@RequestMapping("/customerinfo")
public class CustomerInfoController extends
		BaseController<CustomerInfoModel, CustomerInfoQueryModel> {
	private CustomerInfoService myService;

	@Autowired
	public void setMyService(CustomerInfoService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public CustomerInfoController() {
		super("customermgr/sysback/customerinfo", "CustomerInfo",
				CustomerInfoController.class);
	}
}