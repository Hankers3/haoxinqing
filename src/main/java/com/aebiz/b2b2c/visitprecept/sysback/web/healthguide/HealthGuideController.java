package com.aebiz.b2b2c.visitprecept.sysback.web.healthguide;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.visitprecept.healthguide.service.HealthGuideService;
import com.aebiz.b2b2c.visitprecept.healthguide.vo.HealthGuideModel;
import com.aebiz.b2b2c.visitprecept.healthguide.vo.HealthGuideQueryModel;

@Controller
@RequestMapping("/sysback/healthguide")
public class HealthGuideController extends BaseController<HealthGuideModel,HealthGuideQueryModel>{
	private HealthGuideService myService;
	@Autowired
	public void  setMyService(HealthGuideService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public HealthGuideController(){
		super("/visitprecept/sysback/healthguide","HealthGuide",HealthGuideController.class);
	}
	
}