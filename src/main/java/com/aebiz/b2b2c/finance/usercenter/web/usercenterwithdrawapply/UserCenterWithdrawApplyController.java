package com.aebiz.b2b2c.finance.usercenter.web.usercenterwithdrawapply;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.basecrud.vo.ConditionOpTypeEnum;
import com.aebiz.b2b2c.baseframework.basecrud.vo.DataTablesPageParam;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerInteractive;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.finance.customeraccount.service.CustomerAccountService;
import com.aebiz.b2b2c.finance.customeraccount.vo.CustomerAccountModel;
import com.aebiz.b2b2c.finance.usercenter.service.usercenterwithdrawapply.UserCenterWithdrawApplyService;
import com.aebiz.b2b2c.finance.usercenter.web.usercenterwithdrawapply.vo.UserCenterSelectTime;
import com.aebiz.b2b2c.finance.usercenter.web.usercenterwithdrawapply.vo.UserCenterWithdrawApplyWebModel;
import com.aebiz.b2b2c.finance.withdrawapply.vo.WithdrawApplyModel;
import com.aebiz.b2b2c.finance.withdrawapply.vo.WithdrawApplyQueryModel;

@Controller
@RequestMapping("/usercenter/withdrawapply")
public class UserCenterWithdrawApplyController extends
		BaseController<WithdrawApplyModel, WithdrawApplyQueryModel> {

	String userCenterUuid = LoginUserHelper.getCustomerLoginUserUuid();

	private UserCenterWithdrawApplyService myService;

	@Autowired
	public void setMyService(UserCenterWithdrawApplyService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	// 会员帐户财务信息
	@Autowired
	private CustomerAccountService customerAccountService;

	// 会员信息
	@Autowired
	private CustomerInteractive custtomerInteractive;

	public UserCenterWithdrawApplyController() {
		super("finance/usercenter/withdrawapply", "WithdrawApply",
				UserCenterWithdrawApplyController.class);
	}

	/**
	 * 跳转至提现申请页面，输入具体的数据，进行提现申请
	 * 
	 * @param m
	 * @return
	 */
	@RequestMapping("/extractionMoney")
	public String extractionMoney(Model m) {
		// String userCenterUuid = LoginUserHelper.getStoreLoginUserUuid();
		// 会员信息对象
		CustomerModel cm = custtomerInteractive
				.getCustomerModelByCondition(userCenterUuid);
		CustomerAccountModel caModel = null;
		if (cm != null) {

			// 商户财务信息对象
			caModel = customerAccountService
					.getCustomerAccountModelByCustomerUuid(cm.getCustomerId());
		}

		// 发送会员的财务信息到页面，展示会员的冻结金额、可提现金额、虚拟帐户余额等帐户信息
		m.addAttribute("m", caModel);
		return "finance/usercenter/withdrawapply/extractionMoney";
	}

	/**
	 * 会员余额:检查数据是否为空、提现是否大于余额、字符串是否为字数
	 * 
	 * @param value
	 *            要检查的字符串
	 * @param sfam
	 *            商户信息model
	 * @param request
	 * @return
	 */
	public boolean checkValue(String value, CustomerAccountModel caModel,
			Model m, HttpServletRequest request) {
		if (StringUtil.isEmpty(value)) {
			this.putErrorMsg("applyMoney",
					MessageHelper.getMessage("withdrawapply.apply.noEmpty"));
		} else {
			if (!checkPriceValid(value)) {
				this.putErrorMsg("applyMoney",
						MessageHelper.getMessage("withdrawapply.apply.number"));
			} else {
				// 提现金额
				float v = Float.valueOf(value);
				// 提现金额小于0
				if (v < 0) {
					this.putErrorMsg("applyMoney", MessageHelper
							.getMessage("withdrawapply.apply.gtZero"));
				}

				// 余额等于0
				if (caModel.getAccountBalance() == 0) {
					this.putErrorMsg("applyMoney", MessageHelper
							.getMessage("withdrawapply.apply.notSufficient"));
				}

				// 余额大于0
				if (caModel.getAccountBalance() > 0) {
					// 提现金额大于余额
					if (caModel.getAccountBalance() < v) {
						this.putErrorMsg(
								"apply",
								MessageHelper
										.getMessage("withdrawapply.apply.notSufficient"));
					}
				}
			}
		}
		if (this.getMapErrorMsg() != null && this.getMapErrorMsg().size() > 0) {
			m.addAttribute("m", caModel);
			request.setAttribute("ERROR_BACK_URL",
					"finance/usercenter/withdrawapply/extractionMoney");
			return false;
		}
		return true;
	}

	/**
	 * 校验申请提现金额 是否有效<br />
	 * 
	 * 判断字符串中是否全是数字(包含正负数、小数)<br />
	 * 
	 * @return
	 */
	private boolean checkPriceValid(String value) {
		if (value.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$")) {
			return true;
		}
		return false;
	}

	/**
	 * 会员提现:检查密码是否为空、是否正确
	 * 
	 * @param value
	 *            要检查的字符串
	 * @param sfam
	 *            商户信息model
	 * @param request
	 * @return
	 */
	public boolean checkValuePass(String value, CustomerAccountModel caModel,
			Model m, HttpServletRequest request) {
		if (StringUtil.isEmpty(value)) {
			this.putErrorMsg("payPasswd",
					MessageHelper.getMessage("withdrawapply.apply.empPassword"));
		} else {
			// 输入的密码不正确
			if (!value.equals(caModel.getPayPasswd())) {
				this.putErrorMsg("payPasswd", MessageHelper
						.getMessage("withdrawapply.apply.passwrod"));
			}
		}

		if (this.getMapErrorMsg() != null && this.getMapErrorMsg().size() > 0) {
			m.addAttribute("m", caModel);
			request.setAttribute("ERROR_BACK_URL",
					"finance/usercenter/withdrawapply/extractionMoney");
			return false;
		}
		return true;
	}

	/**
	 * 保存提现记录，更改会员帐户冻结金额，将其减去提现金额。
	 * 
	 * @param m
	 * @param payPasswd
	 *            支付密码
	 * @param applyMoney
	 *            提现金额
	 * @param note
	 *            备注
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveExtractionMoney")
	public String saveExtractionMoney(Model m,
			@RequestParam("payPasswd") String payPasswd,
			@RequestParam("applyMoney") String applyMoney,
			@RequestParam("note") String note, HttpServletRequest request) {
		CustomerModel cm = custtomerInteractive
				.getCustomerModelByCondition(userCenterUuid);
		CustomerAccountModel caModel = null;
		if (cm != null) {
			caModel = customerAccountService
					.getCustomerAccountModelByCustomerUuid(cm.getCustomerId());
		}

		this.getMapErrorMsg().clear();
		Map<String, List<String>> mapErrorMsg = this.getMapErrorMsg();

		if (!checkValue(applyMoney, caModel, m, request)) {
			request.setAttribute("ShowMsgs", mapErrorMsg);
			return (String) request.getAttribute("ERROR_BACK_URL");
		}

		if (!checkValuePass(payPasswd, caModel, m, request)) {
			request.setAttribute("ShowMsgs", mapErrorMsg);
			return (String) request.getAttribute("ERROR_BACK_URL");
		}

		cleanQuerySession(request);

		WithdrawApplyModel paramM = null;

		// 保存提现记录，更改会员帐户数据
		myService.saveExtractionMoney(caModel, paramM, cm, userCenterUuid,
				applyMoney, note);

		return "finance/usercenter/withdrawapply/extractionMoneySuccess";
	}

	/**
	 * 进入我的虚拟账户
	 * 
	 * @return
	 */
	@RequestMapping("/userCenterDummy")
	public String userCenterDummy() {
		return "finance/usercenter/withdrawapply/userCenterDummy";
	}

	/**
	 * 会员财务信息列表
	 * 
	 * @param wm
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/userCenterWithdrawList/{nowPage}/{pageShow}" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String toList(
			@ModelAttribute("wm") UserCenterWithdrawApplyWebModel wm,
			Model model, HttpServletRequest request) {

		WithdrawApplyQueryModel qm = getQueryModel();

		qm.setUserUuid(userCenterUuid);
		String time = request.getParameter("time");
		if (!StringUtil.isEmpty(time)) {
			qm.setTime(time);
		}
		qm.getMapCondition().put("userUuid", ConditionOpTypeEnum.EQ.getCode());
		String init = request.getParameter("init");
		if (!"true".equalsIgnoreCase(init)) {
			Object obj = SecurityUtils.getSubject().getSession()
					.getAttribute("WithdrawApplyQueryModelIsQuery");
			if ((obj != null) && (obj.toString().equals("true"))) {
				qm = (WithdrawApplyQueryModel) SecurityUtils.getSubject()
						.getSession().getAttribute("WithdrawApplyQueryModel");
			}
		}

		qm.setDelFlag(BaseModel.DEL_FLAG_VALID);
		qm.getMapCondition().put("delFlag",
				Integer.valueOf(ConditionOpTypeEnum.EQ.getCode()));

		qm = preparedQMFixValue(qm);

		wm.setTotalNum(myService.getCount(qm));
		List<WithdrawApplyModel> list = myService.getByCondition(qm,
				wm.getFromNum(), wm.getPageShow());

		wm.setRows(list);
		model.addAttribute("list1", list);

		request.setAttribute("qm", qm);
		wm.setTime(qm.getTime());
		model.addAttribute("wm", wm);

		this.preparedListData(model, request);
		return "finance/usercenter/withdrawapply/userCenterWithdrawList";
	}

	@Override
	protected void preparedListData(Model model, HttpServletRequest request) {
		this.sendWithdrawwApplyTypesToPage(model, request);
	}

	/**
	 * 发送提现单类型到页面供选择
	 * 
	 * @param model
	 * @param request
	 */
	private void sendWithdrawwApplyTypesToPage(Model model,
			HttpServletRequest request) {
		List<DataTablesPageParam> timeList = new ArrayList<DataTablesPageParam>();
		for (UserCenterSelectTime spe : UserCenterSelectTime.values()) {
			DataTablesPageParam dpp = new DataTablesPageParam();

			dpp.setName(spe.getName());
			dpp.setValue(spe.getValue());

			timeList.add(dpp);
		}
		model.addAttribute("timeList", timeList);
	}

	@RequestMapping(value = { "/queryWithdrawApply" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public String queryWithdrawApply(
			@ModelAttribute("qm") WithdrawApplyQueryModel qm,
			@RequestParam("pageShow") String pageShow,
			HttpServletRequest request) {

		Map<String, String[]> map = request.getParameterMap();
		for (String key : map.keySet()) {
			if (key.endsWith("_q")) {
				String v = ((String[]) map.get(key))[0];
				qm.getMapCondition().put(
						key.substring(0, key.length() - 2),
						Integer.valueOf(ConditionOpTypeEnum.valueOf(v)
								.getCode()));
			}
		}

		SecurityUtils.getSubject().getSession()
				.setAttribute("WithdrawApplyQueryModelIsQuery", "true");
		SecurityUtils.getSubject().getSession()
				.setAttribute("WithdrawApplyQueryModel", qm);

		qm.setUserUuid(userCenterUuid);
		String time = request.getParameter("time");
		if (!StringUtil.isEmpty(time)) {
			qm.setTime(time);
		}
		qm.getMapCondition().put("userUuid", ConditionOpTypeEnum.EQ.getCode());

		return "redirect:userCenterWithdrawList/1/" + pageShow;
	}
}