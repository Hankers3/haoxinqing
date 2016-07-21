package com.aebiz.b2b2c.websiteoperation.sysback.web.customerdiseasere;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;


import com.aebiz.b2b2c.websiteoperation.customerdiseasere.service.CustomerDiseaseReService;
import com.aebiz.b2b2c.websiteoperation.customerdiseasere.vo.CustomerDiseaseReModel;
import com.aebiz.b2b2c.websiteoperation.customerdiseasere.vo.CustomerDiseaseReQueryModel;

@Controller
@RequestMapping("/customerdiseasere")
public class CustomerDiseaseReController extends BaseController<CustomerDiseaseReModel,CustomerDiseaseReQueryModel>{
	private CustomerDiseaseReService myService;
	@Autowired
	public void  setMyService(CustomerDiseaseReService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public CustomerDiseaseReController(){
		super("websiteoperation/sysback/customerdiseasere","CustomerDiseaseRe",CustomerDiseaseReController.class);
	}
}