package com.aebiz.b2b2c.websiteoperation.sysback.web.websiteoperate;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.basecrud.vo.DataTablesPageParam;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.websiteoperation.websiteoperate.service.WebsiteOperateService;
import com.aebiz.b2b2c.websiteoperation.websiteoperate.vo.ModuleEnum;
import com.aebiz.b2b2c.websiteoperation.websiteoperate.vo.ParamTypeEnum;
import com.aebiz.b2b2c.websiteoperation.websiteoperate.vo.WebsiteOperateModel;
import com.aebiz.b2b2c.websiteoperation.websiteoperate.vo.WebsiteOperateQueryModel;

@Controller
@RequestMapping("/sysback/websiteoperate")
public class WebsiteOperateController extends
		BaseController<WebsiteOperateModel, WebsiteOperateQueryModel> {
	private WebsiteOperateService myService;

	@Autowired
	public void setMyService(WebsiteOperateService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public WebsiteOperateController() {
		super("websiteoperation/sysback/websiteoperate", "WebsiteOperate",
				WebsiteOperateController.class);
	}

	@Override
	protected void preparedAddData(Model model, HttpServletRequest request) {
		sendModuleShowName(model);
		sendParamTypeShowName(model);
	}

	@Override
	protected void preparedUpdateData(Model model, HttpServletRequest request) {
		sendModuleShowName(model);
		sendParamTypeShowName(model);
	}

	@Override
	protected void preparedListData(Model model, HttpServletRequest request) {
		sendModuleShowName(model);
		sendParamTypeShowName(model);
	}

	/**
	 * 将模块展示数据发送到页面
	 * 
	 * @param model
	 */
	public void sendModuleShowName(Model model) {
		List<DataTablesPageParam> moduleList = new ArrayList<DataTablesPageParam>();

		for (ModuleEnum me : ModuleEnum.values()) {
			DataTablesPageParam dpp = new DataTablesPageParam();
			dpp.setName(me.getKey());
			dpp.setValue(me.getValue());
			moduleList.add(dpp);
		}
		model.addAttribute("moduleList", moduleList);
	}

	/**
	 * 将模块展示数据发送到页面
	 * 
	 * @param model
	 */
	public void sendParamTypeShowName(Model model) {
		List<DataTablesPageParam> paramTypeList = new ArrayList<DataTablesPageParam>();

		for (ParamTypeEnum pt : ParamTypeEnum.values()) {
			DataTablesPageParam dpp = new DataTablesPageParam();
			dpp.setName(pt.getKey());
			dpp.setValue(pt.getValue());
			paramTypeList.add(dpp);
		}
		model.addAttribute("paramTypeList", paramTypeList);
	}

	/**
	 * 校验编号是否重复
	 * 
	 * @param resourceKey
	 *            编号
	 * @return
	 */
	@RequestMapping("/checkKeyExist")
	@ResponseBody
	public String checkKeyExist(@RequestParam("resourceKey") String resourceKey) {
		if (this.myService.checkKeyExist(resourceKey)) {
			return "true";
		}
		return "false";
	}
}