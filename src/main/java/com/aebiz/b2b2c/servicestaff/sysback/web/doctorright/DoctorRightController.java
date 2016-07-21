package com.aebiz.b2b2c.servicestaff.sysback.web.doctorright;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.servicestaff.doctorright.service.DoctorRightService;
import com.aebiz.b2b2c.servicestaff.doctorright.vo.DoctorRightModel;
import com.aebiz.b2b2c.servicestaff.doctorright.vo.DoctorRightQueryModel;

@Controller
@RequestMapping("/sysback/doctorright")
public class DoctorRightController extends BaseController<DoctorRightModel,DoctorRightQueryModel>{
	private DoctorRightService myService;
	@Autowired
	public void  setMyService(DoctorRightService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public DoctorRightController(){
		super("servicestaff/sysback/doctorright","DoctorRight",DoctorRightController.class);
	}
	
}