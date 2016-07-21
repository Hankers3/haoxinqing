package com.aebiz.b2b2c.servicestaff.sysback.web.doctorcategory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.servicestaff.doctorcategory.service.DoctorCategoryService;
import com.aebiz.b2b2c.servicestaff.doctorcategory.vo.DoctorCategoryModel;
import com.aebiz.b2b2c.servicestaff.doctorcategory.vo.DoctorCategoryQueryModel;

@Controller
@RequestMapping("/sysback/doctorcategory")
public class DoctorCategoryController extends
		BaseController<DoctorCategoryModel, DoctorCategoryQueryModel> {
	private DoctorCategoryService myService;

	@Autowired
	public void setMyService(DoctorCategoryService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public DoctorCategoryController() {
		super("/servicestaff/sysback/doctorcategory", "DoctorCategory",
				DoctorCategoryController.class);
	}

	/**
	 * 添加之前创建时间和状态
	 */
	@Override
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(Model model, @ModelAttribute("m") DoctorCategoryModel m,
			HttpServletRequest request) {
		m.setCreateTime(DateFormatHelper.getNowTimeStr());
		m.setState("1");
		return super.add(model, m, request);
	}

	/**
	 * 根据传入的医生分类信息判断医生分类是否已经存在
	 * 
	 * @param categoryName
	 *            医生分类名
	 * @return
	 */
	@RequestMapping(value = { "/checkName" }, method = { RequestMethod.GET })
	@ResponseBody
	public String checkName(@RequestParam("categoryName") String categoryName,
			HttpServletRequest request, HttpServletResponse response) {
		if (myService.checkCateGoryName(categoryName)) {
			return "true";
		}
		return "false";
	}
}