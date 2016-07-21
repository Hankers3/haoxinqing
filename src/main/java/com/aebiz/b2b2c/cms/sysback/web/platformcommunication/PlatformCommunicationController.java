package com.aebiz.b2b2c.cms.sysback.web.platformcommunication;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;

import com.aebiz.b2b2c.cms.platformcommunication.service.PlatformCommunicationService;
import com.aebiz.b2b2c.cms.platformcommunication.vo.PlatformCommunicationModel;
import com.aebiz.b2b2c.cms.platformcommunication.vo.PlatformCommunicationQueryModel;

@Controller
@RequestMapping("/sysback/platformcommunication")
public class PlatformCommunicationController
		extends
		BaseController<PlatformCommunicationModel, PlatformCommunicationQueryModel> {
	private PlatformCommunicationService myService;

	@Autowired
	public void setMyService(PlatformCommunicationService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public PlatformCommunicationController() {
		super("cms/sysback/platformcommunication", "PlatformCommunication",
				PlatformCommunicationController.class);
	}
	
	/**
	 * 修改审核状态
	 * @param uuid
	 * @param model
	 * @param state
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateState/{uuid}", method = RequestMethod.GET)
	@ResponseBody
	public String updateState(@PathVariable("uuid") String uuid, Model model,	
			@RequestParam("state") String state,
			@RequestParam("remark") String remark,
			HttpServletRequest request) {
		
		PlatformCommunicationModel m=myService.getByUuid(uuid);
		m.setConftimeState(state);
		m.setAdmin(LoginUserHelper.getLoginUserUuid());
		m.setRemark(remark);
		m.setConftime(DateFormatHelper.getNowTimeStr());
		
		myService.update(m);
		
		return "success";
	}
	
	
	
	/**
	 *  跳转到回复信息页面
	 * @param uuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toView/{uuid}", method = RequestMethod.GET)
	public String toView(@PathVariable("uuid") String uuid, Model model){
		PlatformCommunicationModel m=myService.getByUuid(uuid);
		model.addAttribute("m", m);
		
		return "cms/sysback/platformcommunication/PlatformCommunicationView";
	}
	
	
	/**
	 * 对信息进行回复
	 * @param uuid
	 * @param model
	 * @param state
	 * @param remark
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateReplyState/{uuid}", method = RequestMethod.GET)
	@ResponseBody
	public String updateReplyState(@PathVariable("uuid") String uuid, Model model,	
			@RequestParam("state") String state,
			@RequestParam("remark") String remark,
			HttpServletRequest request) {
		
		PlatformCommunicationModel m=myService.getByUuid(uuid);
		m.setReplyState(state);
		m.setReplyMessage(remark);
		m.setReplyTime(DateFormatHelper.getNowTimeStr());
		
		myService.update(m);
		return "success";
	}
	
	
	
	
	
}