package com.aebiz.b2b2c.visitprecept.sysback.web.illnessrecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;


import com.aebiz.b2b2c.visitprecept.illnessrecord.service.IllnessRecordService;
import com.aebiz.b2b2c.visitprecept.illnessrecord.vo.IllnessRecordModel;
import com.aebiz.b2b2c.visitprecept.illnessrecord.vo.IllnessRecordQueryModel;

@Controller
@RequestMapping("/sysback/illnessrecord")
public class IllnessRecordController extends BaseController<IllnessRecordModel,IllnessRecordQueryModel>{
	private IllnessRecordService myService;
	@Autowired
	public void  setMyService(IllnessRecordService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public IllnessRecordController(){
		super("visitprecept/sysback/illnessrecord","IllnessRecord",IllnessRecordController.class);
	}
}