package com.aebiz.b2b2c.websiteoperation.sysback.web.paltcongfig;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.websiteoperation.paltcongfig.service.PaltCongfigService;
import com.aebiz.b2b2c.websiteoperation.paltcongfig.vo.PaltCongfigModel;
import com.aebiz.b2b2c.websiteoperation.paltcongfig.vo.PaltCongfigQueryModel;

@Controller
@RequestMapping("/sysback/paltcongfig")
public class PaltCongfigController extends BaseController<PaltCongfigModel,PaltCongfigQueryModel>{
	private PaltCongfigService myService;
	@Autowired
	public void  setMyService(PaltCongfigService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public PaltCongfigController(){
		super("websiteoperation/sysback/paltcongfig","PaltCongfig",PaltCongfigController.class);
	}
	
	
	/**
	 * 创建系统配置信息,数据库中只能存在一条记录,如果数据库有则更新,不存在则先添加再更新
	 * @param m
	 * @return 
	 * PaltCongfigModel
	 */
	@RequestMapping(value = {"/toSetting"}, method = {RequestMethod.GET})
	public String toSetting(Model model, HttpServletRequest request) {
		List<PaltCongfigModel> list = myService
				.getByCondition(new PaltCongfigQueryModel());
		PaltCongfigModel congfigModel = null;
		if (list != null && list.size() > 0) {
			congfigModel = list.get(0);
		}

		if (congfigModel == null) {
			congfigModel = myService
					.createPlatConfig(new PaltCongfigModel());
		}

		model.addAttribute("m", congfigModel);

		return "websiteoperation/sysback/paltcongfig/PaltCongfigUpdate";
	}
	
	/**
	 * 修改商城的配置信息
	 * @param model
	 * @param m
	 * @param request
	 * @param files
	 * @return 
	 * String
	 */
	@RequestMapping(value = {"/updatePlatConfig"}, method = {RequestMethod.POST})
	public String updatePlatConfig(Model model, @ModelAttribute("m") PaltCongfigModel m,
			HttpServletRequest request,@RequestParam(value = "myfiles", required = false) MultipartFile[] files) {

		myService.updatePlatConfig(m,files);
		return "websiteoperation/sysback/paltcongfig/PaltCongfigSuccess";
	}
}