package com.aebiz.b2b2c.customermgr.sysback.web.customertemp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.customermgr.customertemp.service.CustomerTempService;
import com.aebiz.b2b2c.customermgr.customertemp.vo.CustomerTempModel;
import com.aebiz.b2b2c.customermgr.customertemp.vo.CustomerTempQueryModel;

@Controller
@RequestMapping("/sysback/customertemp")
public class CustomerTempController extends
		BaseController<CustomerTempModel, CustomerTempQueryModel> {
	private CustomerTempService myService;

	@Autowired
	public void setMyService(CustomerTempService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public CustomerTempController() {
		super("customermgr/sysback/customertemp", "CustomerTemp",
				CustomerTempController.class);
	}
}