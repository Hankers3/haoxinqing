package com.aebiz.b2b2c.visitprecept.sysback.web.visitcustomercommon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.visitprecept.visitcustomercommon.service.VisitCustomerCommonService;
import com.aebiz.b2b2c.visitprecept.visitcustomercommon.vo.VisitCustomerCommonModel;
import com.aebiz.b2b2c.visitprecept.visitcustomercommon.vo.VisitCustomerCommonQueryModel;

@Controller
@RequestMapping("/visitcustomercommon")
public class VisitCustomerCommonController extends BaseController<VisitCustomerCommonModel,VisitCustomerCommonQueryModel>{
	private VisitCustomerCommonService myService;
	@Autowired
	public void  setMyService(VisitCustomerCommonService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public VisitCustomerCommonController(){
		super("/visitprecept/sysback/visitpreceptextend","VisitCustomerCommon",VisitCustomerCommonController.class);
	}
}