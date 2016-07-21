package com.aebiz.b2b2c.thirdinterface.sysback.web.thirdInterfaceTemplate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.thirdinterface.thirdInterfaceTemplate.service.ThirdInterfaceTemplateService;
import com.aebiz.b2b2c.thirdinterface.thirdInterfaceTemplate.vo.ThirdInterfaceTemplateModel;
import com.aebiz.b2b2c.thirdinterface.thirdInterfaceTemplate.vo.ThirdInterfaceTemplateQueryModel;

@Controller
@RequestMapping("/sysback/thirdInterfaceTemplate")
public class ThirdInterfaceTemplateController
		extends
		BaseController<ThirdInterfaceTemplateModel, ThirdInterfaceTemplateQueryModel> {
	private ThirdInterfaceTemplateService myService;

	@Autowired
	public void setMyService(ThirdInterfaceTemplateService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public ThirdInterfaceTemplateController() {
		super("thirdinterface/sysback/thirdInterfaceTemplate",
				"ThirdInterfaceTemplate",
				ThirdInterfaceTemplateController.class);
	}

	@Override
	@RequestMapping(value = "/toList", method = RequestMethod.GET)
	public String toList(Model model, HttpServletRequest request) {
		String templateType = request.getParameter("templateType");
		model.addAttribute("templateType", templateType);
		return super.toList(model, request);
	}

	@Override
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(Model model, HttpServletRequest request) {
		String templateType = request.getParameter("templateType");
		model.addAttribute("templateType", templateType);
		return super.toAdd(model, request);
	}

	@Override
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(Model model,
			@ModelAttribute("m") ThirdInterfaceTemplateModel m,
			HttpServletRequest request) {
		model.addAttribute("templateType", m.getTemplateType());
		return super.add(model, m, request);
	}

	@RequestMapping(value = { "/toUpdate/{uuid}" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String toUpdate(@PathVariable("uuid") String uuid, Model model,
			HttpServletRequest request) {
		ThirdInterfaceTemplateModel m = myService.getByUuid(uuid);

		model.addAttribute("m", m);
		model.addAttribute("templateType", m.getTemplateType());

		preparedUpdateData(model, request);

		return "thirdinterface/sysback/thirdInterfaceTemplate" + "/" + "ThirdInterfaceTemplateUpdate";
	}

	@Override
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Model model,
			@ModelAttribute("m") ThirdInterfaceTemplateModel m,
			HttpServletRequest request) {
		model.addAttribute("templateType", m.getTemplateType());
		return super.update(model, m, request);
	}

}