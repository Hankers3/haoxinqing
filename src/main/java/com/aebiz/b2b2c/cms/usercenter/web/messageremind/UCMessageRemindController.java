package com.aebiz.b2b2c.cms.usercenter.web.messageremind;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.cms.messageremind.service.MessageRemindService;
import com.aebiz.b2b2c.cms.messageremind.vo.MessageRemindModel;
import com.aebiz.b2b2c.cms.messageremind.vo.MessageRemindQueryModel;
import com.aebiz.b2b2c.cms.usercenter.web.messageremind.vo.CustomerMessageRemind;
import com.aebiz.b2b2c.cms.usercenter.web.messageremind.vo.MessageRemindWebModel;

@Controller
@RequestMapping("usercenter/messageremind")
public class UCMessageRemindController extends BaseController<MessageRemindModel,MessageRemindQueryModel>{
	private MessageRemindService myService;
	@Autowired
	public void  setMyService(MessageRemindService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public UCMessageRemindController(){
		super("cms/sysback/messageremind","MessageRemind",UCMessageRemindController.class);
	}
	
	/**
	 * 页面展示会员所有消息和该会员对消息的设置
	 * @param webModel
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/showMessageRemindList", method = RequestMethod.GET)
	public String showMessageRemindList(@ModelAttribute("wm") MessageRemindWebModel webModel, Model model, HttpServletRequest request) {
		List<CustomerMessageRemind> customerMessageReminds=myService.getCoustomerMessageWebModelList("1");
		webModel.setCustomerMessageReminds(customerMessageReminds);
		return "cms/usercenter/messageremind/CustomerMessageRemindList";
	}
}