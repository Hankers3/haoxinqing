package com.aebiz.b2b2c.cms.usercenter.web.remindsub;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.cms.remindsub.service.RemindSubService;
import com.aebiz.b2b2c.cms.remindsub.vo.RemindSubModel;
import com.aebiz.b2b2c.cms.remindsub.vo.RemindSubQueryModel;

@Controller
@RequestMapping("/usercenter/remindsub")
public class UCRemindSubController extends BaseController<RemindSubModel,RemindSubQueryModel>{
	
	private RemindSubService myService;
	@Autowired
	public void  setMyService(RemindSubService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public UCRemindSubController(){
		super("cms/usercenter/remindsub","RemindSub",UCRemindSubController.class);
	}
	
	/**
	 * 保存会员消息设置
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveSet", method = RequestMethod.POST)
	public String saveSet(Model model, HttpServletRequest request){
		
		String[] messageUuids=request.getParameterValues("messageUuid");
		
		String customerUuid="1";
		
		for (int i = 0; i < messageUuids.length; i++) {
			String messageUuid=messageUuids[i].toString();
			String remindSubUuid=request.getParameter("redmindSubUuid"+messageUuid);
			String mobileSend=request.getParameter("mobileSend"+messageUuid);
			String emailSend=request.getParameter("emailSend"+messageUuid);
			String innerSend=request.getParameter("innerSend"+messageUuid);
			System.out.println(remindSubUuid);
			System.out.println(mobileSend);
			myService.saveRemindSubModel(customerUuid, messageUuid, remindSubUuid, mobileSend, emailSend, innerSend);
		}
		
		return "cms/usercenter/messageremind/CustomerMessageRemindList";
	}
}