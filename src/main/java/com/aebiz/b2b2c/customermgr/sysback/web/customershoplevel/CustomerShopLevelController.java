package com.aebiz.b2b2c.customermgr.sysback.web.customershoplevel;

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
import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.customermgr.customershoplevel.service.CustomerShopLevelService;
import com.aebiz.b2b2c.customermgr.customershoplevel.vo.CustomerShopLevelModel;
import com.aebiz.b2b2c.customermgr.customershoplevel.vo.CustomerShopLevelQueryModel;

@Controller
@RequestMapping("/sysback/customershoplevel")
public class CustomerShopLevelController extends
		BaseController<CustomerShopLevelModel, CustomerShopLevelQueryModel> {
	private CustomerShopLevelService myService;

	@Autowired
	private UuidService us;

	@Autowired
	public void setMyService(CustomerShopLevelService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public CustomerShopLevelController() {
		super("customermgr/sysback/customershoplevel", "CustomerShopLevel",
				CustomerShopLevelController.class);
	}

	/**
	 * 根据传入的用户名判断该用户名是否已经存在
	 * 
	 * @param levelName
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/checkLevelName" }, method = { RequestMethod.GET })
	@ResponseBody
	public String checkLevelNameExist(
			@RequestParam("levelName") String levelName,
			@RequestParam("uuid") String uuid, HttpServletRequest request,
			HttpServletResponse response) {
		if (myService.checkLevelNameExist(levelName, uuid)) {
			return "true";
		}
		return "false";
	}

	/**
	 * 校验添加会员时候，输入的值是否合法
	 */
	@Override
	protected boolean checkAdd(Model model, CustomerShopLevelModel m,
			HttpServletRequest request) {
		/* 校验等级名称 */
		checkLevelName(m.getLevelName(), 2, 20);
		/* 校验排序 */
		checkPosition(m.getPosition(), 1, 3);
		/* 校验积分开始范围 */
		checkMinIntegral(m.getMinIntegral(), 1, 10);
		/* 校验积分结束范围 */
		checkMaxIntegral(m.getMaxIntegral(), 1, 10);
		// 跳转到页面，并将错误返回
		if (this.getMapErrorMsg() != null && this.getMapErrorMsg().size() > 0) {
			request.setAttribute(ERROR_BACK_URL,
					"customermgr/sysback/customershoplevel/CustomerShopLevelAdd");
			return false;
		}
		return true;
	}

	/**
	 * 校验等级名称是否为空和长度
	 * 
	 * @param levelNameValue
	 * @param minLength
	 * @param maxLength
	 */
	public void checkLevelName(String levelNameValue, int minLength,
			int maxLength) {
		if (StringUtil.isEmpty(levelNameValue)) {
			this.putErrorMsg("levelName", MessageHelper
					.getMessage("customershoplevel.levelName.empty"));
		} else {
			if (maxLength == 0 && minLength == 0) {
				return;
			}
			if (levelNameValue.length() > maxLength) {
				this.putErrorMsg(
						"levelName",
						MessageHelper
								.getMessage("customershoplevel.levelName.maxlength")
								+ maxLength);
			}
			if (levelNameValue.length() < minLength) {
				this.putErrorMsg(
						"levelName",
						MessageHelper
								.getMessage("customershoplevel.levelName.minlength")
								+ minLength);
			}
		}
	}

	/**
	 * 校验排序是否为空和长度
	 * 
	 * @param positionValue
	 * @param minLength
	 * @param maxLength
	 */
	public void checkPosition(String positionValue, int minLength, int maxLength) {
		if (StringUtil.isEmpty(positionValue)) {
			this.putErrorMsg("position", MessageHelper
					.getMessage("customershoplevel.position.empty"));
		} else {
			if (maxLength == 0 && minLength == 0) {
				return;
			}
			if (positionValue.length() > maxLength) {
				this.putErrorMsg(
						"position",
						MessageHelper
								.getMessage("customershoplevel.position.maxlength")
								+ maxLength);
			}
			if (positionValue.length() < minLength) {
				this.putErrorMsg(
						"position",
						MessageHelper
								.getMessage("customershoplevel.position.minlength")
								+ minLength);
			}
		}
	}

	/**
	 * 校验积分初始值是否为空和长度
	 * 
	 * @param minIntegralValue
	 * @param minLength
	 * @param maxLength
	 */
	public void checkMinIntegral(int minIntegral, int minLength,
			int maxLength) {
		
		String minIntegralValue =minIntegral+"";
		if (StringUtil.isEmpty(minIntegralValue)) {
			this.putErrorMsg("minIntegral", MessageHelper
					.getMessage("customershoplevel.minIntegral.empty"));
		} else {
			if (maxLength == 0 && minLength == 0) {
				return;
			}
			if (minIntegralValue.length() > maxLength) {
				this.putErrorMsg(
						"minIntegral",
						MessageHelper
								.getMessage("customershoplevel.minIntegral.maxlength")
								+ maxLength);
			}
			if (minIntegralValue.length() < minLength) {
				this.putErrorMsg(
						"minIntegral",
						MessageHelper
								.getMessage("customershoplevel.minIntegral.minlength")
								+ minLength);
			}
		}
	}

	/**
	 * 校验积分结束值是否为空和长度
	 * 
	 * @param maxIntegralValue
	 * @param minLength
	 * @param maxLength
	 */
	public void checkMaxIntegral(int maxIntegral, int minLength,
			int maxLength) {
		String maxIntegralValue= maxIntegral+"";
		if (StringUtil.isEmpty(maxIntegralValue)) {
			this.putErrorMsg("maxIntegral", MessageHelper
					.getMessage("customershoplevel.maxIntegral.empty"));
		} else {
			if (maxLength == 0 && minLength == 0) {
				return;
			}
			if (maxIntegralValue.length() > maxLength) {
				this.putErrorMsg(
						"maxIntegral",
						MessageHelper
								.getMessage("customershoplevel.maxIntegral.maxlength")
								+ maxLength);
			}
			if (maxIntegralValue.length() < minLength) {
				this.putErrorMsg(
						"maxIntegral",
						MessageHelper
								.getMessage("customershoplevel.maxIntegral.minlength")
								+ minLength);
			}
		}
	}

	@Override
	protected boolean checkUpdate(Model model, CustomerShopLevelModel m,
			HttpServletRequest request) {
		/* 校验等级名称 */
		checkLevelName(m.getLevelName(), 2, 20);
		/* 校验排序 */
		checkPosition(m.getPosition(), 1, 3);
		/* 校验积分开始范围 */
		checkMinIntegral(m.getMinIntegral(), 1, 10);
		/* 校验积分结束范围 */
		checkMaxIntegral(m.getMaxIntegral(), 1, 10);
		// 跳转到页面，并将错误返回
		if (this.getMapErrorMsg() != null && this.getMapErrorMsg().size() > 0) {
			request.setAttribute(ERROR_BACK_URL,
					"customermgr/sysback/customershoplevel/CustomerShopLevelUpdate");
			return false;
		}
		return true;
	}

	/**
	 * 保存会员平台等级
	 * 
	 * @param m
	 * @param request
	 * @param files
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/doAdd", method = RequestMethod.POST)
	public String save(
			@ModelAttribute("m") CustomerShopLevelModel m,
			HttpServletRequest request,
			@RequestParam(value = "files", required = false) MultipartFile[] files,
			Model model) {
		// 校验前台数据
		this.getMapErrorMsg().clear();
		if (!checkAdd(model, m, request)) {
			request.setAttribute("ShowMsgs", getMapErrorMsg());
			return (String) request.getAttribute("ERROR_BACK_URL");
		}
		myService.uploadImage(m, files);
		myService.create(m);
		return "customermgr/sysback/customershoplevel/CustomerShopLevelSuccess";
	}
@Override
@RequestMapping(value = "/add", method = RequestMethod.POST)
public String add(Model model,
		@ModelAttribute("m") CustomerShopLevelModel m,
		HttpServletRequest request) {
	
	return super.add(model, m, request);
}
	/**
	 * 更新
	 * 
	 * @param m
	 * @param request
	 * @param files
	 * @return
	 */
	@RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
	public String update(
			@ModelAttribute("m") CustomerShopLevelModel m,
			HttpServletRequest request,
			@RequestParam(value = "files", required = false) MultipartFile[] files,
			Model model) {

		// 校验前台数据
		this.getMapErrorMsg().clear();
		if (!checkAdd(model, m, request)) {
			request.setAttribute("ShowMsgs", getMapErrorMsg());
			return (String) request.getAttribute("ERROR_BACK_URL");
		}
		// 上传图片
		this.myService.uploadImage(m, files);
		// 更新数据
		myService.update(m);
		// 跳转页面
		return "customermgr/sysback/customershoplevel/CustomerShopLevelSuccess";
	}

}