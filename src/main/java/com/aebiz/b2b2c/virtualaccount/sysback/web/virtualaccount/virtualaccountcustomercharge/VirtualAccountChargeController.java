package com.aebiz.b2b2c.virtualaccount.sysback.web.virtualaccount.virtualaccountcustomercharge;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aebiz.b2b2c.baseframework.basecrud.vo.ConditionOpTypeEnum;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.basecrud.web.vo.BaseWebModel;
import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerInteractive;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.customermgr.customerinfo.service.CustomerInfoService;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoModel;
import com.aebiz.b2b2c.finance.customeraccount.service.CustomerAccountInteractive;
import com.aebiz.b2b2c.finance.customeraccount.vo.CustomerAccountModel;
import com.aebiz.b2b2c.finance.usercenter.service.usercenterwithdrawapply.UserCenterWithdrawApplyInteractive;
import com.aebiz.b2b2c.finance.withdrawapply.vo.WithdrawApplyModel;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomercharge.service.VirtualAccountCustomerChargeService;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomercharge.vo.VirtualAccountCustomerChargeModel;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomercharge.vo.VirtualAccountCustomerChargeQueryModel;

@Controller
@RequestMapping("/sysback/virtualaccountcharge")
public class VirtualAccountChargeController
		extends
		BaseController<VirtualAccountCustomerChargeModel, VirtualAccountCustomerChargeQueryModel> {
	private VirtualAccountCustomerChargeService myService;

	@Autowired
	public void setMyService(VirtualAccountCustomerChargeService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	// 注入财务系统的会员账户对外接口
	@Autowired
	private CustomerAccountInteractive accountInteractive;

	// 会员帐户财务信息
	@Autowired
	private CustomerAccountInteractive customerAccountInteractive;

	// 注入财务系统 体现申请的对外接口
	@Autowired
	private UserCenterWithdrawApplyInteractive centerWithdrawApplyInteractive;

	// 会员信息
	@Autowired
	private CustomerInteractive custtomerInteractive;
	// 患者信息
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CustomerInfoService customerInfoService;

	public VirtualAccountChargeController() {
		super("virtualaccount/sysback/virtualaccountcustomercharge",
				"VirtualAccountCustomerCharge",
				VirtualAccountChargeController.class);
	}

	/**
	 * 跳转到我的账户信息页面,将当前登陆的会员的账户信息发送到面
	 */
	@RequestMapping(value = { "/toMyAccount" }, method = { RequestMethod.GET })
	public String toMyAccount(Model model, HttpServletRequest request) {
		// 获取当前登陆的会员的uuid
		String customerUuid = LoginUserHelper.getCustomerLoginUserUuid();
		CustomerAccountModel accountModel = accountInteractive
				.getCustomerAccountModelByCustomerUuid(customerUuid);
		if (accountModel != null) {
			model.addAttribute("account", accountModel);
		}

		return "virtualaccount/usercenter/virtualaccountcustomercharge/MyVirtualAccount";
	}

	/**
	 * 跳转至提现申请页面，输入具体的数据，进行提现申请
	 * 
	 * @param m
	 * @return
	 */
	@RequestMapping("/toWithdrawApply")
	public String toWithdrawApply(Model m) {
		// String userCenterUuid = LoginUserHelper.getStoreLoginUserUuid();

		// 获取当前登陆的会员的uuid
		String customerUuid = LoginUserHelper.getCustomerLoginUserUuid();
		// 会员信息对象
		CustomerModel cm = custtomerInteractive
				.getCustomerModelByCondition(customerUuid);
		CustomerAccountModel caModel = null;
		if (cm != null) {

			// 商户财务信息对象
			caModel = customerAccountInteractive
					.getCustomerAccountModelByCustomerUuid(cm.getCustomerId());
		}

		// 发送会员的财务信息到页面，展示会员的冻结金额、可提现金额、虚拟帐户余额等帐户信息
		m.addAttribute("m", caModel);
		return "virtualaccount/usercenter/withdrawapply/toWithdrawApply";
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
	@RequestMapping("/savewithdrawapply")
	public String savewithdrawapply(Model m,
			@RequestParam("payPasswd") String payPasswd,
			@RequestParam("applyMoney") String applyMoney,
			@RequestParam("note") String note, HttpServletRequest request) {

		// 获取当前登陆的会员的uuid
		String customerUuid = LoginUserHelper.getCustomerLoginUserUuid();
		CustomerModel cm = custtomerInteractive
				.getCustomerModelByCondition(customerUuid);
		CustomerAccountModel caModel = null;
		if (cm != null) {
			caModel = customerAccountInteractive
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
		centerWithdrawApplyInteractive.saveExtractionMoney(caModel, paramM, cm,
				customerUuid, applyMoney, note);

		return "virtualaccount/usercenter/withdrawapply/WithdrawApplySuccess";
	}

	/**
	 * 查询会员的充值记录
	 * 
	 * @param model
	 * @param wm
	 * @param state
	 *            充值状态 0:未处理 1:成功 2:失败
	 * @param nowPage
	 * @param showPage
	 * @return String
	 */
	@RequestMapping("/searchChargeDetails")
	public String searchChargeDetails(Model model,
			@ModelAttribute("wm") BaseWebModel wm,
			@RequestParam("state") String state,
			@RequestParam("nowPage") String nowPage,
			@RequestParam("showPage") String showPage) {

		// 获取当前登陆的会员的uuid
		String customerUuid = LoginUserHelper.getCustomerLoginUserUuid();

		wm.setNowPage(Integer.parseInt(nowPage));
		wm.setPageShow(Integer.parseInt(showPage));
		int count = myService.getChargeCount(customerUuid, state);
		wm.setTotalNum(count);

		List<VirtualAccountCustomerChargeModel> list = myService.searchCharge(
				customerUuid, state, wm.getFromNum(), wm.getPageShow());

		if (list != null && list.size() > 0) {
			wm.setRows(list);
			model.addAttribute("list", list);
		}
		model.addAttribute("state", state);

		return "virtualaccount/usercenter/virtualaccountcustomercharge/VirtualAccountCustomerChargeList";

	}

	/**
	 * 查询会员的提现记录
	 * 
	 * @param model
	 * @param wm
	 * @param state
	 * @param nowPage
	 * @param showPage
	 * @return String
	 */
	@RequestMapping("/searchWithapplyDetail")
	public String searchWithapplyDetail(Model model,
			@ModelAttribute("wm") BaseWebModel wm,
			@RequestParam("state") String state,
			@RequestParam("timeType") String timeType,
			@RequestParam("nowPage") String nowPage,
			@RequestParam("showPage") String showPage) {

		// 获取当前登陆的会员的uuid
		String customerUuid = LoginUserHelper.getCustomerLoginUserUuid();

		wm.setNowPage(Integer.parseInt(nowPage));
		wm.setPageShow(Integer.parseInt(showPage));
		int count = centerWithdrawApplyInteractive
				.getCustomerWithdrawAppllyCount(customerUuid, state, timeType);
		wm.setTotalNum(count);

		List<WithdrawApplyModel> list = centerWithdrawApplyInteractive
				.getCustomerWithdrawAppllyList(customerUuid, state, timeType,
						wm.getFromNum() + "", wm.getPageShow() + "");

		if (list != null && list.size() > 0) {
			wm.setRows(list);
			model.addAttribute("list", list);
		}
		model.addAttribute("timeType", timeType);
		model.addAttribute("state", state);

		return "virtualaccount/usercenter/withdrawapply/WithdrawApplyList";

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
								"applyMoney",
								MessageHelper
										.getMessage("withdrawapply.apply.notSufficient"));
					}
				}
			}
		}
		if (this.getMapErrorMsg() != null && this.getMapErrorMsg().size() > 0) {
			m.addAttribute("m", caModel);
			request.setAttribute("ERROR_BACK_URL",
					"virtualaccount/usercenter/withdrawapply/toWithdrawApply");
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
					"virtualaccount/usercenter/withdrawapply/toWithdrawApply");
			return false;
		}
		return true;
	}

	/**
	 * 查询所有会员的充值记录
	 * 
	 * @param wm
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/accountchargelist/{nowPage}/{pageShow}", method = { RequestMethod.GET })
	public String servicemanagelist(@ModelAttribute("wm") BaseWebModel wm,
			Model model, HttpServletRequest request) {

		VirtualAccountCustomerChargeQueryModel qm = this.getQueryModel();

		// 判断是否需要初始化查询
		String init = request.getParameter("init");
		if (!"true".equalsIgnoreCase(init)) {
			Object obj = SecurityUtils.getSubject().getSession()
					.getAttribute("VirtualAccountIsQueryQs");
			if ((obj != null) && (obj.toString().equals("true"))) {
				qm = (VirtualAccountCustomerChargeQueryModel) SecurityUtils
						.getSubject().getSession()
						.getAttribute("VirtualAccountCustomerChargeQueryModel");
			}
		}
		qm = preparedQMFixValue(qm);

		wm.setTotalNum(myService.getAllChargeCount(qm));
		List<VirtualAccountCustomerChargeModel> list = myService
				.getByCondition(qm, wm.getFromNum(), wm.getPageShow());

		// 新增此条以获取会员充值次数
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {

				List<VirtualAccountCustomerChargeModel> chargeList = myService
						.searchAllCharge(list.get(i).getCustomerUuid());
				list.get(i).setChargeTimes(chargeList.size());
			}
		}

		wm.setRows(list);
		request.setAttribute("qm", qm);
		this.preparedListData(model, request);
		return "virtualaccount/sysback/virtualaccountcustomercharge/VirtualAccountCustomerChargeList";
	}

	/**
	 * 查询所有会员的充值记录
	 * 
	 * @param qm
	 * @param pageShow
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/query", method = { RequestMethod.POST })
	public String query(VirtualAccountCustomerChargeQueryModel qm,
			@RequestParam("pageShow") String pageShow,
			HttpServletRequest request) {

		// 辅助设置要查询的条件的比较方式
		Map<String, String[]> map = request.getParameterMap();
		for (String key : map.keySet()) {
			if (key.endsWith("_q")) {
				String v = map.get(key)[0];
				((VirtualAccountCustomerChargeQueryModel) qm).getMapCondition()
						.put(key.substring(0, key.length() - 2),
								ConditionOpTypeEnum.valueOf(v).getCode());
			}
		}

		SecurityUtils.getSubject().getSession()
				.setAttribute("VirtualAccountIsQueryQs", "true");
		SecurityUtils.getSubject().getSession()
				.setAttribute("VirtualAccountCustomerChargeQueryModel", qm);

		return "redirect:/sysback/virtualaccountcharge/accountchargelist/1/"
				+ pageShow;
	}

	/**
	 * 根据会员id获取该会员所有的充值记录
	 * 
	 * @param customerUuid
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/searchcusstomercharges/{customerUuid}")
	public String searchcusstomercharges(
			@ModelAttribute("customerUuid") String customerUuid, Model model,
			HttpServletRequest request) {

		List<VirtualAccountCustomerChargeModel> chargeList = myService
				.searchAllCharge(customerUuid);
		model.addAttribute("charges", chargeList);
		return "virtualaccount/sysback/virtualaccountcustomercharge/CustomerChargeList";
	}

	/**
	 * 查看患者充值记录详情 by szr
	 * 
	 */
	@RequestMapping(value = "/toView/{uuid}", method = { RequestMethod.GET })
	public String getDoctorIncomeView(@PathVariable("uuid") String uuid,
			Model m, HttpServletRequest request) {
		// 根据uuid查出充值记录表
		VirtualAccountCustomerChargeModel virtualAccountCustomerChargeModel = myService
				.getByUuid(uuid);
		String customerUuid = "";
		if (virtualAccountCustomerChargeModel != null) {
			customerUuid = virtualAccountCustomerChargeModel.getCustomerUuid();
		}

		// 根据患者id查出患者信息，存入model对象中
		CustomerInfoModel customerInfoModel = customerInfoService
				.getCustomerInfoModelByCustomerUuid(customerUuid);
		CustomerModel customerModel = customerService.getByUuid(customerUuid);

		if (customerInfoModel != null) {
			m.addAttribute("customerInfoModel", customerInfoModel);
		}
		if (customerModel != null) {
			m.addAttribute("customerModel", customerModel);
		}

		// 根据患者uuid查出充值记录list
		List<VirtualAccountCustomerChargeModel> virtualAccountCustomerChargeModelList = myService
				.searchAllCharge(customerUuid);

		if (virtualAccountCustomerChargeModelList != null
				&& virtualAccountCustomerChargeModelList.size() > 0) {
			m.addAttribute("virtualAccountCustomerChargeModelList",
					virtualAccountCustomerChargeModelList);
		}
		return "virtualaccount/sysback/virtualaccountcustomercharge/CustomerChargeView";

	}

}