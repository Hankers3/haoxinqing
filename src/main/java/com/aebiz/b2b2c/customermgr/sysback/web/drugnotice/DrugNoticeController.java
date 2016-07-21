package com.aebiz.b2b2c.customermgr.sysback.web.drugnotice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.customermgr.drugnotice.service.DrugNoticeService;
import com.aebiz.b2b2c.customermgr.drugnotice.vo.DrugNoticeModel;
import com.aebiz.b2b2c.customermgr.drugnotice.vo.DrugNoticeQueryModel;

@Controller
@RequestMapping("/drugnotice")
public class DrugNoticeController extends BaseController<DrugNoticeModel,DrugNoticeQueryModel>{
	private DrugNoticeService myService;
	@Autowired
	public void  setMyService(DrugNoticeService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public DrugNoticeController(){
		super("customermgr/sysback/drugnotice","DrugNotice",DrugNoticeController.class);
	}
}