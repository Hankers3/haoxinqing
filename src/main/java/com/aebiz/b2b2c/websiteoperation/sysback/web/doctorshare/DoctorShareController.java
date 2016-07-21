package com.aebiz.b2b2c.websiteoperation.sysback.web.doctorshare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.websiteoperation.doctorshare.service.DoctorShareService;
import com.aebiz.b2b2c.websiteoperation.doctorshare.vo.DoctorShareModel;
import com.aebiz.b2b2c.websiteoperation.doctorshare.vo.DoctorShareQueryModel;

@Controller
@RequestMapping("/doctorshare")
public class DoctorShareController extends BaseController<DoctorShareModel,DoctorShareQueryModel>{
	private DoctorShareService myService;
	@Autowired
	public void  setMyService(DoctorShareService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public DoctorShareController(){
		super("websiteoperation/sysback/doctorshare","DoctorShare",DoctorShareController.class);
	}
}