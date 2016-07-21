package com.aebiz.b2b2c.cms.sysback.web.messageremind;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;


import com.aebiz.b2b2c.cms.messageremind.service.MessageRemindService;
import com.aebiz.b2b2c.cms.messageremind.vo.MessageRemindModel;
import com.aebiz.b2b2c.cms.messageremind.vo.MessageRemindQueryModel;

@Controller
@RequestMapping("/sysback/messageremind")
public class MessageRemindController extends BaseController<MessageRemindModel,MessageRemindQueryModel>{
	private MessageRemindService myService;
	@Autowired
	public void  setMyService(MessageRemindService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public MessageRemindController(){
		super("cms/sysback/messageremind","MessageRemind",MessageRemindController.class);
	}
}