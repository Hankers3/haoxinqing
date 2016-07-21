package com.aebiz.b2b2c.cms.sysback.web.protectionproductrel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;


import com.aebiz.b2b2c.cms.protectionproductrel.service.ProtectionProductRelService;
import com.aebiz.b2b2c.cms.protectionproductrel.vo.ProtectionProductRelModel;
import com.aebiz.b2b2c.cms.protectionproductrel.vo.ProtectionProductRelQueryModel;

@Controller
@RequestMapping("/sysback/protectionproductrel")
public class ProtectionProductRelController extends BaseController<ProtectionProductRelModel,ProtectionProductRelQueryModel>{
	private ProtectionProductRelService myService;
	@Autowired
	public void  setMyService(ProtectionProductRelService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public ProtectionProductRelController(){
		super("cms/sysback/protectionproductrel","ProtectionProductRel",ProtectionProductRelController.class);
	}
}