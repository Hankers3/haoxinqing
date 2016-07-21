package com.aebiz.b2b2c.visitprecept.sysback.web.visitrecordextend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;


import com.aebiz.b2b2c.visitprecept.visitrecordextend.service.VisitRecordExtendService;
import com.aebiz.b2b2c.visitprecept.visitrecordextend.vo.VisitRecordExtendModel;
import com.aebiz.b2b2c.visitprecept.visitrecordextend.vo.VisitRecordExtendQueryModel;

@Controller
@RequestMapping("/sysback/visitrecordextend")
public class VisitRecordExtendController extends BaseController<VisitRecordExtendModel,VisitRecordExtendQueryModel>{
	private VisitRecordExtendService myService;
	@Autowired
	public void  setMyService(VisitRecordExtendService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public VisitRecordExtendController(){
		super("visitprecept/sysback/visitrecordextend","VisitRecordExtend",VisitRecordExtendController.class);
	}
}