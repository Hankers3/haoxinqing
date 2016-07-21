package com.aebiz.b2b2c.vipclub.sysback.web.giftpackagecustomerrel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.vipclub.giftpackagecustomerrel.service.GiftPackageCustomerRelService;
import com.aebiz.b2b2c.vipclub.giftpackagecustomerrel.vo.GiftPackageCustomerRelModel;
import com.aebiz.b2b2c.vipclub.giftpackagecustomerrel.vo.GiftPackageCustomerRelQueryModel;

@Controller
@RequestMapping("/sysback/giftpackagecustomerrel")
public class GiftPackageCustomerRelController
		extends
		BaseController<GiftPackageCustomerRelModel, GiftPackageCustomerRelQueryModel> {
	private GiftPackageCustomerRelService myService;

	@Autowired
	public void setMyService(GiftPackageCustomerRelService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public GiftPackageCustomerRelController() {
		super("vipclub/sysback/giftpackagecustomerrel",
				"GiftPackageCustomerRel",
				GiftPackageCustomerRelController.class);
	}
}