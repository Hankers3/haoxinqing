package com.aebiz.b2b2c.visitprecept.sysback.web.casecategory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.visitprecept.casecategory.service.CaseCategoryService;
import com.aebiz.b2b2c.visitprecept.casecategory.vo.CaseCategoryModel;
import com.aebiz.b2b2c.visitprecept.casecategory.vo.CaseCategoryQueryModel;

@Controller
@RequestMapping("/casecategory")
public class CaseCategoryController extends BaseController<CaseCategoryModel,CaseCategoryQueryModel>{
	private CaseCategoryService myService;
	@Autowired
	public void  setMyService(CaseCategoryService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public CaseCategoryController(){
		super("/visitprecept/sysback/casecategory","CaseCategory",CaseCategoryController.class);
	}
	
	@Override
	@RequestMapping(value = "/toInfo/{uuid}", method = RequestMethod.GET)
	public String toInfo(@PathVariable("uuid") String uuid, Model model,
			HttpServletRequest request) {
		return super.toInfo(uuid, model, request);
	}
}