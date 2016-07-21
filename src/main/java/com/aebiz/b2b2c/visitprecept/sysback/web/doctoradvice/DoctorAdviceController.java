package com.aebiz.b2b2c.visitprecept.sysback.web.doctoradvice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.visitprecept.doctoradvice.service.DoctorAdviceService;
import com.aebiz.b2b2c.visitprecept.doctoradvice.vo.DoctorAdviceModel;
import com.aebiz.b2b2c.visitprecept.doctoradvice.vo.DoctorAdviceQueryModel;

@Controller
@RequestMapping("/sysback/doctoradvice")
public class DoctorAdviceController extends BaseController<DoctorAdviceModel,DoctorAdviceQueryModel>{
	private DoctorAdviceService myService;
	@Autowired
	public void  setMyService(DoctorAdviceService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public DoctorAdviceController(){
		super("visitprecept/sysback/doctoradvice","DoctorAdvice",DoctorAdviceController.class);
	}
}