package com.aebiz.b2b2c.customermgr.sysback.web.customerloginlog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.customermgr.customerloginlog.service.CustomerLoginLogService;
import com.aebiz.b2b2c.customermgr.customerloginlog.vo.CustomerLoginLogModel;
import com.aebiz.b2b2c.customermgr.customerloginlog.vo.CustomerLoginLogQueryModel;

@Controller
@RequestMapping("/sysback/customerloginlog")
public class CustomerLoginLogController extends
		BaseController<CustomerLoginLogModel, CustomerLoginLogQueryModel> {
	private CustomerLoginLogService myService;

	@Autowired
	public void setMyService(CustomerLoginLogService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public CustomerLoginLogController() {
		super("customermgr/sysback/customerloginlog", "CustomerLoginLog",
				CustomerLoginLogController.class);
	}
}