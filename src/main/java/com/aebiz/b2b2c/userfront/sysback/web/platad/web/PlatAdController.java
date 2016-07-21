package com.aebiz.b2b2c.userfront.sysback.web.platad.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;


import com.aebiz.b2b2c.userfront.platad.service.PlatAdService;
import com.aebiz.b2b2c.userfront.platad.vo.PlatAdModel;
import com.aebiz.b2b2c.userfront.platad.vo.PlatAdQueryModel;

@Controller
@RequestMapping("/sysback/platad")
public class PlatAdController extends BaseController<PlatAdModel,PlatAdQueryModel>{
	private PlatAdService myService;
	@Autowired
	public void  setMyService(PlatAdService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public PlatAdController(){
		super("userfront/sysback/platad","PlatAd",PlatAdController.class);
	}
}