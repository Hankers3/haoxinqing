package com.aebiz.b2b2c.websiteoperation.sysback.web.quizcategory;

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
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.websiteoperation.quizcategory.service.QuizCategoryService;
import com.aebiz.b2b2c.websiteoperation.quizcategory.vo.QuizCategoryModel;
import com.aebiz.b2b2c.websiteoperation.quizcategory.vo.QuizCategoryQueryModel;

@Controller
@RequestMapping("/sysback/quizcategory")
public class QuizCategoryController extends BaseController<QuizCategoryModel,QuizCategoryQueryModel>{
	private QuizCategoryService myService;
	@Autowired
	public void  setMyService(QuizCategoryService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public QuizCategoryController(){
		super("/websiteoperation/sysback/quizcategory","QuizCategory",QuizCategoryController.class);
	}
	/**
	 * 根据传入的测试分类信息判断测试分类是否已经存在
	 * 
	 * @param categoryName
	 *            测试分类名
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
	
	
	/**
	 * 保存内容信息
	 * 
	 * @param m
	 * @param request
	 * @param files
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/doAdd", method = RequestMethod.POST)
	public String save(@ModelAttribute("m") QuizCategoryModel m,
			HttpServletRequest request,
			@RequestParam("files") MultipartFile[] files, Model model) {
		myService.uploadImage(m, files);
		m.setCreateTime(DateFormatHelper.getNowTimeStr());

		return super.add(model, m, request);
	}

}