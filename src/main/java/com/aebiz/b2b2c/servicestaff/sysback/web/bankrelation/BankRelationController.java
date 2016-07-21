package com.aebiz.b2b2c.servicestaff.sysback.web.bankrelation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;


import com.aebiz.b2b2c.servicestaff.bankrelation.service.BankRelationService;
import com.aebiz.b2b2c.servicestaff.bankrelation.vo.BankRelationModel;
import com.aebiz.b2b2c.servicestaff.bankrelation.vo.BankRelationQueryModel;

@Controller
@RequestMapping("/sysback/bankrelation")
public class BankRelationController extends BaseController<BankRelationModel,BankRelationQueryModel>{
	private BankRelationService myService;
	@Autowired
	public void  setMyService(BankRelationService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public BankRelationController(){
		super("servicestaff/sysback/bankrelation","BankRelation",BankRelationController.class);
	}
}