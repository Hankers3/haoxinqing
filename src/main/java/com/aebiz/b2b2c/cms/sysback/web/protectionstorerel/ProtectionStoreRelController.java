package com.aebiz.b2b2c.cms.sysback.web.protectionstorerel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;


import com.aebiz.b2b2c.cms.protectionstorerel.service.ProtectionStoreRelService;
import com.aebiz.b2b2c.cms.protectionstorerel.vo.ProtectionStoreRelModel;
import com.aebiz.b2b2c.cms.protectionstorerel.vo.ProtectionStoreRelQueryModel;

@Controller
@RequestMapping("/sysback/protectionstorerel")
public class ProtectionStoreRelController extends BaseController<ProtectionStoreRelModel,ProtectionStoreRelQueryModel>{
	private ProtectionStoreRelService myService;
	@Autowired
	public void  setMyService(ProtectionStoreRelService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public ProtectionStoreRelController(){
		super("cms/sysback/protectionstorerel","ProtectionStoreRel",ProtectionStoreRelController.class);
	}
}