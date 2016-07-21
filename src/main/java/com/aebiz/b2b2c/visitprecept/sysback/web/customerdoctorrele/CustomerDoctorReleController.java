package com.aebiz.b2b2c.visitprecept.sysback.web.customerdoctorrele;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.visitprecept.customerdoctorrele.service.CustomerDoctorReleService;
import com.aebiz.b2b2c.visitprecept.customerdoctorrele.vo.CustomerDoctorReleModel;
import com.aebiz.b2b2c.visitprecept.customerdoctorrele.vo.CustomerDoctorReleQueryModel;

@Controller
@RequestMapping("/customerdoctorrele")
public class CustomerDoctorReleController extends BaseController<CustomerDoctorReleModel,CustomerDoctorReleQueryModel>{
	private CustomerDoctorReleService myService;
	@Autowired
	public void  setMyService(CustomerDoctorReleService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public CustomerDoctorReleController(){
		super("visitprecept/customerdoctorrele","CustomerDoctorRele",CustomerDoctorReleController.class);
	}
}