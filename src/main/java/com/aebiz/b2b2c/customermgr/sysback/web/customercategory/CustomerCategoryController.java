package com.aebiz.b2b2c.customermgr.sysback.web.customercategory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.customermgr.customercategory.service.CustomerCategoryService;
import com.aebiz.b2b2c.customermgr.customercategory.vo.CustomerCategoryModel;
import com.aebiz.b2b2c.customermgr.customercategory.vo.CustomerCategoryQueryModel;

@Controller
@RequestMapping("/sysback/customerCategory")
public class CustomerCategoryController extends BaseController<CustomerCategoryModel,CustomerCategoryQueryModel>{
	private CustomerCategoryService myService;
	@Autowired
	public void  setMyService(CustomerCategoryService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public CustomerCategoryController(){
		super("/customermgr/sysback/customercategory","CustomerCategory",CustomerCategoryController.class);
	}
	
	
	/**
	 * 根据传入的患者分类信息判断患者分类是否已经存在
	 * 
	 * @param categoryName
	 *            患者分类名
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