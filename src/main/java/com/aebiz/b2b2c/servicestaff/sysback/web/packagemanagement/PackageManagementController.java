package com.aebiz.b2b2c.servicestaff.sysback.web.packagemanagement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.aebiz.b2b2c.servicestaff.packagemanagement.service.PackageManagementService;
import com.aebiz.b2b2c.servicestaff.packagemanagement.vo.PackageManagementModel;
import com.aebiz.b2b2c.servicestaff.packagemanagement.vo.PackageManagementQueryModel;

@Controller
@RequestMapping("/sysback/packagemanagement")
public class PackageManagementController extends
		BaseController<PackageManagementModel, PackageManagementQueryModel> {
	private PackageManagementService myService;

	@Autowired
	public void setMyService(PackageManagementService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public PackageManagementController() {
		super("/servicestaff/sysback/packagemanagement", "PackageManagement",
				PackageManagementController.class);
	}

	/**
	 * 根据传入的套餐信息名判断套餐名是否已经存在
	 * 
	 * @param packageName
	 *            套餐名
	 * @return
	 */
	@RequestMapping(value = { "/checkName" }, method = { RequestMethod.GET })
	@ResponseBody
	public String checkName(@RequestParam("packageName") String packageName,
			HttpServletRequest request, HttpServletResponse response) {
		if (myService.checkPackageName(packageName)) {
			return "true";
		}
		return "false";
	}

	/**
	 * 添加之前创建时间
	 */
	@Override
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(Model model,
			@ModelAttribute("m") PackageManagementModel m,
			HttpServletRequest request) {
		m.setCreateTime(DateFormatHelper.getNowTimeStr());
		return super.add(model, m, request);
	}

	/**
	 * 返回详细信息
	 */
	@RequestMapping("/toDetail/{uuid}")
	public String toDetail(@PathVariable("uuid") String uuid, Model model,
			HttpServletRequest request) {
		// 通过id获得model
		PackageManagementModel packageManagementModel = myService.getByUuid(uuid);

		preparedUpdateData(model, request);

		model.addAttribute("m", packageManagementModel);
		return "servicestaff/sysback/packagemanagement/PackageManagementDetails";
	}

}