package com.aebiz.b2b2c.thirdinterface.sysback.web.mobilemessage;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.thirdinterface.mobilemessage.service.MobileMessageService;
import com.aebiz.b2b2c.thirdinterface.mobilemessage.vo.MobileMessageModel;
import com.aebiz.b2b2c.thirdinterface.mobilemessage.vo.MobileMessageQueryModel;
import com.aebiz.b2b2c.thirdinterface.mobilemessagesend.service.SmsService;

@Controller
@RequestMapping("/sysback/mobilemessage")
public class MobileMessageController extends
		BaseController<MobileMessageModel, MobileMessageQueryModel> {
	private MobileMessageService myService;
	@Autowired
	private SmsService sendMobile;
	@Autowired
	public void setMyService(MobileMessageService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public MobileMessageController() {
		super("messagesend/sysback/mobilemessage", "MobileMessage",
				MobileMessageController.class);
	}
	
	/**
	 * 更新短信设置
	 * 
	 * @param model 
	 * @return
	 */
	@RequestMapping("/toView")
	public String toView(Model model) {
		MobileMessageModel m = this.myService.getModel();
		if(m != null){
			model.addAttribute("m", m);
			return "messagesend/sysback/mobilemessage/MobileMessageUpdate";
		}
		return "messagesend/sysback/mobilemessage/MobileMessageAdd";
	}
	
	@RequestMapping("sendMobile")
	public String sendMobile() throws UnsupportedEncodingException {
		this.sendMobile.sendSms("15207164637",
						"订单通知：感谢您订购全网数商，订单号：***** ， 我们将尽快处理您的订单，任何疑问请联系400-6060-980【全网数商】");
		return "";
	}
}