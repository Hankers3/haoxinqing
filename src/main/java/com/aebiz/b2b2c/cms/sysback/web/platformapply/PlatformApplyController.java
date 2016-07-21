package com.aebiz.b2b2c.cms.sysback.web.platformapply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.cms.platformapply.service.PlatformApplyService;
import com.aebiz.b2b2c.cms.platformapply.vo.PlatformApplyModel;
import com.aebiz.b2b2c.cms.platformapply.vo.PlatformApplyQueryModel;

@Controller
@RequestMapping("/sysback/platformapply")
public class PlatformApplyController extends BaseController<PlatformApplyModel,PlatformApplyQueryModel>{
	private PlatformApplyService myService;
	@Autowired
	public void  setMyService(PlatformApplyService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	
	public PlatformApplyController(){
		super("cms/sysback/platformapply","PlatformApply",PlatformApplyController.class);
	}
	
	
}