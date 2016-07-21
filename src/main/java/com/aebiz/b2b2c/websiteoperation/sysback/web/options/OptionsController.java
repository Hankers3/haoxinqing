package com.aebiz.b2b2c.websiteoperation.sysback.web.options;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;

import com.aebiz.b2b2c.websiteoperation.options.service.OptionsService;
import com.aebiz.b2b2c.websiteoperation.options.vo.OptionsModel;
import com.aebiz.b2b2c.websiteoperation.options.vo.OptionsQueryModel;

@Controller
@RequestMapping("/sysback/options")
public class OptionsController extends
		BaseController<OptionsModel, OptionsQueryModel> {
	private OptionsService myService;

	@Autowired
	public void setMyService(OptionsService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public OptionsController() {
		super("websiteoperation/sysback/options", "Options",
				OptionsController.class);
	}
}