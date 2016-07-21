package com.aebiz.b2b2c.cms.sysback.web.innermessagenotice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.cms.innermessagenotice.service.InnerMessageNoticeService;
import com.aebiz.b2b2c.cms.innermessagenotice.vo.InnerMessageNoticeModel;
import com.aebiz.b2b2c.cms.innermessagenotice.vo.InnerMessageNoticeQueryModel;



@Controller
@RequestMapping("/sysback/innermessagenotice")
public class InnerMessageNoticeController extends BaseController<InnerMessageNoticeModel,InnerMessageNoticeQueryModel>{
	private InnerMessageNoticeService myService;
	@Autowired
	public void  setMyService(InnerMessageNoticeService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public InnerMessageNoticeController(){
		super("basebusiness/systembackmgr/innermessagenotice","InnerMessageNotice",InnerMessageNoticeController.class);
	}
}