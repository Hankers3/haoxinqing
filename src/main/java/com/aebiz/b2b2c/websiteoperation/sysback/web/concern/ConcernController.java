package com.aebiz.b2b2c.websiteoperation.sysback.web.concern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;


import com.aebiz.b2b2c.websiteoperation.concern.service.ConcernService;
import com.aebiz.b2b2c.websiteoperation.concern.vo.ConcernModel;
import com.aebiz.b2b2c.websiteoperation.concern.vo.ConcernQueryModel;

@Controller
@RequestMapping("/concern")
public class ConcernController extends BaseController<ConcernModel,ConcernQueryModel>{
	private ConcernService myService;
	@Autowired
	public void  setMyService(ConcernService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public ConcernController(){
		super("websiteoperation/sysback/concern","Concern",ConcernController.class);
	}
}