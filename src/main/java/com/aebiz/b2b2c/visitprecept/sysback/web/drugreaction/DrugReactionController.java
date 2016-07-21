package com.aebiz.b2b2c.visitprecept.sysback.web.drugreaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;


import com.aebiz.b2b2c.visitprecept.drugreaction.service.DrugReactionService;
import com.aebiz.b2b2c.visitprecept.drugreaction.vo.DrugReactionModel;
import com.aebiz.b2b2c.visitprecept.drugreaction.vo.DrugReactionQueryModel;

@Controller
@RequestMapping("/sysback/drugreaction")
public class DrugReactionController extends BaseController<DrugReactionModel,DrugReactionQueryModel>{
	private DrugReactionService myService;
	@Autowired
	public void  setMyService(DrugReactionService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public DrugReactionController(){
		super("visitprecept/sysback/drugreaction","DrugReaction",DrugReactionController.class);
	}
}