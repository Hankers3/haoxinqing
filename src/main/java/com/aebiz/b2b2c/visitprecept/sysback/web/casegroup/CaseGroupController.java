package com.aebiz.b2b2c.visitprecept.sysback.web.casegroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.visitprecept.casegroup.service.CaseGroupService;
import com.aebiz.b2b2c.visitprecept.casegroup.vo.CaseGroupModel;
import com.aebiz.b2b2c.visitprecept.casegroup.vo.CaseGroupQueryModel;

@Controller
@RequestMapping("/casegroup")
public class CaseGroupController extends BaseController<CaseGroupModel,CaseGroupQueryModel>{
	private CaseGroupService myService;
	@Autowired
	public void  setMyService(CaseGroupService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public CaseGroupController(){
		super("/visitprecept/sysback/casegroup","CaseGroup",CaseGroupController.class);
	}
}