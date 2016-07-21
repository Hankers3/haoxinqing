package com.aebiz.b2b2c.thirdinterface.sysback.web.emailmessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.thirdinterface.emailmessage.service.EmailMessageService;
import com.aebiz.b2b2c.thirdinterface.emailmessage.vo.EmailMessageModel;
import com.aebiz.b2b2c.thirdinterface.emailmessage.vo.EmailMessageModelQueryModel;

@Controller
@RequestMapping("/sysback/emailmessage")
public class EmailMessageController extends
		BaseController<EmailMessageModel, EmailMessageModelQueryModel> {
	private EmailMessageService myService = null;

	@Autowired
	public void setMyService(EmailMessageService myService) {
		this.myService = myService;
		super.setBs(myService);
	}

	public EmailMessageController() {
		super("messagesend/sysback/emailmessage", "EmailMessage",
				EmailMessageController.class);
	}

	/**
	 * 更新邮件设置
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/toView")
	public String toView(Model model) {
		EmailMessageModel m = this.myService.getModel();
		if(m != null){
			model.addAttribute("m", m);
			return "messagesend/sysback/emailmessage/EmailMessageUpdate";
		}
		return "messagesend/sysback/emailmessage/EmailMessageAdd";
	}
}
