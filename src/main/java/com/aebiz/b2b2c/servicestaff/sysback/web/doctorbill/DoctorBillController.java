package com.aebiz.b2b2c.servicestaff.sysback.web.doctorbill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;


import com.aebiz.b2b2c.servicestaff.doctorbill.service.DoctorBillService;
import com.aebiz.b2b2c.servicestaff.doctorbill.vo.DoctorBillModel;
import com.aebiz.b2b2c.servicestaff.doctorbill.vo.DoctorBillQueryModel;

@Controller
@RequestMapping("/sysback/doctorbill")
public class DoctorBillController extends BaseController<DoctorBillModel,DoctorBillQueryModel>{
	private DoctorBillService myService;
	@Autowired
	public void  setMyService(DoctorBillService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public DoctorBillController(){
		super("servicestaff/sysback/doctorbill","DoctorBill",DoctorBillController.class);
	}
}