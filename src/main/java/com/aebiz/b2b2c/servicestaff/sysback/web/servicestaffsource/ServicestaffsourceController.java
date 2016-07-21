package com.aebiz.b2b2c.servicestaff.sysback.web.servicestaffsource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.servicestaff.servicestaffsource.service.ServicestaffsourceService;
import com.aebiz.b2b2c.servicestaff.servicestaffsource.vo.ServicestaffsourceModel;
import com.aebiz.b2b2c.servicestaff.servicestaffsource.vo.ServicestaffsourceQueryModel;

@Controller
@RequestMapping("/sysback/servicestaffsource")
public class ServicestaffsourceController extends BaseController<ServicestaffsourceModel,ServicestaffsourceQueryModel>{
	private ServicestaffsourceService myService;
	@Autowired
	public void  setMyService(ServicestaffsourceService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public ServicestaffsourceController(){
		super("/servicestaff/sysback/servicestaffsource","Servicestaffsource",ServicestaffsourceController.class);
	}
}