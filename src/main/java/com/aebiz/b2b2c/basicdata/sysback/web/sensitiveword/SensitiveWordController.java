package com.aebiz.b2b2c.basicdata.sysback.web.sensitiveword;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.vo.DataTablesPageParam;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.basicdata.sensitiveword.service.SensitiveWordService;
import com.aebiz.b2b2c.basicdata.sensitiveword.vo.SensitiveWordEnum;
import com.aebiz.b2b2c.basicdata.sensitiveword.vo.SensitiveWordModel;
import com.aebiz.b2b2c.basicdata.sensitiveword.vo.SensitiveWordQueryModel;

@Controller
@RequestMapping("/sysback/sensitiveword")
public class SensitiveWordController extends
		BaseController<SensitiveWordModel, SensitiveWordQueryModel> {
	private SensitiveWordService myService;

	@Autowired
	public void setMyService(SensitiveWordService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public SensitiveWordController() {
		super("basicdata/sysback/sensitiveword", "SensitiveWord",
				SensitiveWordController.class);
	}

	protected void preparedUpdateData(Model model, HttpServletRequest request) {
		sendOrderTypeToPage(model);
	}

	protected void preparedAddData(Model model, HttpServletRequest request) {
		sendOrderTypeToPage(model);
	}

	/**
	 * 组织订单状态列表数据发送到页面，供筛选试用
	 * 
	 * @param model
	 */
	private void sendOrderTypeToPage(Model model) {
		List<DataTablesPageParam> sensitiveLevelList = new ArrayList<DataTablesPageParam>();

		for (SensitiveWordEnum spe : SensitiveWordEnum.values()) {
			DataTablesPageParam dpp = new DataTablesPageParam();

			dpp.setName(spe.getName());
			dpp.setValue(spe.getValue());

			sensitiveLevelList.add(dpp);
		}

		model.addAttribute("sensitiveLevelList", sensitiveLevelList);
	}
}