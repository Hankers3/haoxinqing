package com.aebiz.b2b2c.customermgr.storeback.web.customerstorelevel;

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
import com.aebiz.b2b2c.customermgr.customerstorelevel.vo.CustomerStoreLevelModel;
import com.aebiz.b2b2c.customermgr.customerstorelevel.vo.CustomerStoreLevelQueryModel;
import com.aebiz.b2b2c.customermgr.storeback.service.customerstorelevel.CustomerStoreLevelService;

@Controller
@RequestMapping("/store/customerstorelevel")
public class CustomerStoreLevelController extends
		BaseController<CustomerStoreLevelModel, CustomerStoreLevelQueryModel> {
	private CustomerStoreLevelService myService;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyService(CustomerStoreLevelService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public CustomerStoreLevelController() {
		super("customermgr/storeback/customerstorelevel", "CustomerStoreLevel",
				CustomerStoreLevelController.class);
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
					.getMessage("customerstorelevel.levelName.empty"));
		} else {
			if (minLength == 0 && maxLength == 0) {
				return;
			}
			if (levelNameValue.length() > maxLength) {
				this.putErrorMsg(
						"levelName",
						MessageHelper
								.getMessage("customerstorelevel.levelName.maxlength")
								+ maxLength);
			}
			if (levelNameValue.length() < minLength) {
				this.putErrorMsg(
						"levelName",
						MessageHelper
								.getMessage("customerstorelevel.levelName.minlength")
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
					.getMessage("customerstorelevel.position.empty"));
		} else {
			if (maxLength == 0 && minLength == 0) {
				return;
			}
			if (positionValue.length() > maxLength) {
				this.putErrorMsg(
						"position",
						MessageHelper
								.getMessage("customerstorelevel.position.maxlength")
								+ maxLength);
			}
			if (positionValue.length() < minLength) {
				this.putErrorMsg(
						"position",
						MessageHelper
								.getMessage("customerstorelevel.position.minlength")
								+ minLength);
			}
		}
	}

	/**
	 * 校验购买金额和购买笔数之一不能为为空
	 * 
	 * @param count
	 * @param money
	 */
	public void checkBuyCountAndBuyMoney(String count, String money) {
		if (StringUtil.isEmpty(count) && StringUtil.isEmpty(money)) {
			this.putErrorMsg("buyTotalCount", MessageHelper
					.getMessage("customerstorelevel.countAndMoney.empty"));
		}
	}

	@Override
	protected boolean checkAdd(Model model, CustomerStoreLevelModel m,
			HttpServletRequest request) {
		/* 校验等级名称 */
		checkLevelName(m.getLevelName(), 3, 20);
		checkPosition(m.getPosition(), 1, 3);
		checkBuyCountAndBuyMoney(m.getBuyTotalCount(), m.getBuyTotalMoney());

		// 跳转到页面，并将错误返回
		if (this.getMapErrorMsg() != null && this.getMapErrorMsg().size() > 0) {
			request.setAttribute(ERROR_BACK_URL,
					"customermgr/storeback/customerstorelevel/CustomerStoreLevelAdd");
			return false;
		}
		return true;
	}

	@Override
	protected boolean checkUpdate(Model model, CustomerStoreLevelModel m,
			HttpServletRequest request) {
		/* 校验等级名称 */
		checkLevelName(m.getLevelName(), 3, 20);
		checkPosition(m.getPosition(), 1, 3);
		checkBuyCountAndBuyMoney(m.getBuyTotalCount(), m.getBuyTotalMoney());

		// 跳转到页面，并将错误返回
		if (this.getMapErrorMsg() != null && this.getMapErrorMsg().size() > 0) {
			request.setAttribute(ERROR_BACK_URL,
					"customermgr/storeback/customerstorelevel/CustomerStoreLevelSuccess");
			return false;
		}
		return true;
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

	@RequestMapping(value = "/doAdd", method = RequestMethod.POST)
	public String save(
			@ModelAttribute("m") CustomerStoreLevelModel m,
			HttpServletRequest request,
			@RequestParam(value = "files", required = false) MultipartFile[] files,
			Model model) {
		// 校验前台数据
		this.getMapErrorMsg().clear();
		if (!checkAdd(model, m, request)) {
			request.setAttribute("ShowMsgs", getMapErrorMsg());
			return (String) request.getAttribute(ERROR_BACK_URL);
		}
		// 上传图片
		myService.uploadImage(m, files);

		// 保存数据
		myService.create(m);

		/* 跳转到成功页面 */
		return "customermgr/storeback/customerstorelevel/CustomerStoreLevelSuccess";
	}

	/**
	 * 更新商户会员等级
	 * 
	 * @param m
	 * @param request
	 * @param files
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
	public String update(
			@ModelAttribute("m") CustomerStoreLevelModel m,
			HttpServletRequest request,
			@RequestParam(value = "files", required = false) MultipartFile[] files,
			Model model) {
		// 校验前台数据
		this.getMapErrorMsg().clear();
		if (!checkAdd(model, m, request)) {
			request.setAttribute("ShowMsgs", getMapErrorMsg());
			return (String) request.getAttribute(ERROR_BACK_URL);
		}
		// 上传图片
		myService.uploadImage(m, files);
		// 更新数据
		myService.update(m);
		// 跳转页面
		return "customermgr/storeback/customerstorelevel/CustomerStoreLevelSuccess";
	}

}