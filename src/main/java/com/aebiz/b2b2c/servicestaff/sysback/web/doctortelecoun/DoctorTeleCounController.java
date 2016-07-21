package com.aebiz.b2b2c.servicestaff.sysback.web.doctortelecoun;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;


import com.aebiz.b2b2c.servicestaff.doctortelecoun.service.DoctorTeleCounService;
import com.aebiz.b2b2c.servicestaff.doctortelecoun.vo.DoctorTeleCounModel;
import com.aebiz.b2b2c.servicestaff.doctortelecoun.vo.DoctorTeleCounQueryModel;

@Controller
@RequestMapping("/sysback/doctortelecoun")
public class DoctorTeleCounController extends BaseController<DoctorTeleCounModel,DoctorTeleCounQueryModel>{
	private DoctorTeleCounService myService;
	@Autowired
	public void  setMyService(DoctorTeleCounService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public DoctorTeleCounController(){
		super("servicestaff/sysback/doctortelecoun","DoctorTeleCoun",DoctorTeleCounController.class);
	}
	

}