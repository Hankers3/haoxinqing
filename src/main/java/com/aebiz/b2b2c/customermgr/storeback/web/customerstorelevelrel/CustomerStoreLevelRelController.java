package com.aebiz.b2b2c.customermgr.storeback.web.customerstorelevelrel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.customermgr.customerstorelevelrel.vo.CustomerStoreLevelRelModel;
import com.aebiz.b2b2c.customermgr.customerstorelevelrel.vo.CustomerStoreLevelRelQueryModel;
import com.aebiz.b2b2c.customermgr.storeback.service.customerstorelevelrel.CustomerStoreLevelRelService;

@Controller
@RequestMapping("/customerstorelevelrel")
public class CustomerStoreLevelRelController
		extends
		BaseController<CustomerStoreLevelRelModel, CustomerStoreLevelRelQueryModel> {
	private CustomerStoreLevelRelService myService;

	@Autowired
	public void setMyService(CustomerStoreLevelRelService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public CustomerStoreLevelRelController() {
		super("customermgr/storeback/customerstorelevelrel",
				"CustomerStoreLevelRel", CustomerStoreLevelRelController.class);
	}
}