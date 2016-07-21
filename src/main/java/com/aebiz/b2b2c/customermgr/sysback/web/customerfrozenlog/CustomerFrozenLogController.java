package com.aebiz.b2b2c.customermgr.sysback.web.customerfrozenlog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.customermgr.customerfrozenlog.service.CustomerFrozenLogService;
import com.aebiz.b2b2c.customermgr.customerfrozenlog.vo.CustomerFrozenLogModel;
import com.aebiz.b2b2c.customermgr.customerfrozenlog.vo.CustomerFrozenLogQueryModel;

@Controller
@RequestMapping("/customerfrozenlog")
public class CustomerFrozenLogController extends
		BaseController<CustomerFrozenLogModel, CustomerFrozenLogQueryModel> {
	private CustomerFrozenLogService myService;

	@Autowired
	public void setMyService(CustomerFrozenLogService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public CustomerFrozenLogController() {
		super("customermgr/sysback/customerfrozenlog", "CustomerFrozenLog",
				CustomerFrozenLogController.class);
	}
}