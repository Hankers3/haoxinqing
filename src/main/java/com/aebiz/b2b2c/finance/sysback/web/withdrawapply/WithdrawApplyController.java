package com.aebiz.b2b2c.finance.sysback.web.withdrawapply;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.basecrud.vo.DataTablesPageParam;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.finance.withdrawapply.service.WithdrawApplyService;
import com.aebiz.b2b2c.finance.withdrawapply.vo.UserType;
import com.aebiz.b2b2c.finance.withdrawapply.vo.WithdraApplyType;
import com.aebiz.b2b2c.finance.withdrawapply.vo.WithdrawApplyModel;
import com.aebiz.b2b2c.finance.withdrawapply.vo.WithdrawApplyQueryModel;
import com.aebiz.b2b2c.servicestaff.bankrelation.service.BankRelationService;
import com.aebiz.b2b2c.servicestaff.bankrelation.vo.BankRelationModel;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;

/**
 * 平台后台对体现申请在处理
 * 
 * 包含： <br />
 * 1.列表查询<br />
 * 2.驳回用户的提现申请<br />
 * 3.同意用户的提现申请<br />
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/sysback/withdrawapply")
public class WithdrawApplyController extends
		BaseController<WithdrawApplyModel, WithdrawApplyQueryModel> {

	private WithdrawApplyService myService;

	@Autowired
	public void setMyService(WithdrawApplyService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	// 医生信息
	@Autowired
	private ServicestaffService servicestaffService;

	// 银行卡信息
	@Autowired
	private BankRelationService bankRelationService;

	public WithdrawApplyController() {
		super("finance/sysback/withdrawapply", "WithdrawApply",
				WithdrawApplyController.class);
	}

	// 提现成功列表
	@RequestMapping("/WithdrawApplySuccess")
	public String withdrawApplySuccess(Model model, HttpServletRequest request) {
		this.preparedListData(model, request);
		return "finance/sysback/withdrawapply/WithdrawApplySuccessList";
	}

	// 提现失败列表
	@RequestMapping("/WithdrawApplyFail")
	public String withdrawApplyFail(Model model, HttpServletRequest request) {
		this.preparedListData(model, request);
		return "finance/sysback/withdrawapply/WithdrawApplyFailList";
	}

	/**
	 * 提现详细页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/toApplyView/{uuid}")
	public String toApplyView(@PathVariable("uuid") String uuid, Model model,
			HttpServletRequest request) {
		// 根据uuid查出model
		WithdrawApplyModel withdrawApplyModel = myService.getByUuid(uuid);
		// 查出医生信息
		ServicestaffModel servicestaffModel = servicestaffService
				.getByUuid(withdrawApplyModel.getUserUuid());
		// 银行卡
		
							if (StringUtil.isEmpty(withdrawApplyModel.getBankRelationUuid())) {
								return "该用户下没有银行卡，不能转账？？";
							}
		
		BankRelationModel bankRelationModel = bankRelationService
				.getByUuid(withdrawApplyModel.getBankRelationUuid());

		// 存入
		model.addAttribute("withdrawApplyModel", withdrawApplyModel);
		model.addAttribute("servicestaffModel", servicestaffModel);
		model.addAttribute("bankRelationModel", bankRelationModel);

		return "finance/sysback/withdrawapply/WithdrawApplyView";
	}

	// 未处理的列表
	@RequestMapping("/WithdrawApplyUntreated")
	public String withdrawApplyUntreated(Model model, HttpServletRequest request) {
		this.preparedListData(model, request);
		return "finance/sysback/withdrawapply/WithdrawApplyList";
	}

	protected void preparedListData(Model model, HttpServletRequest request) {
		// 发送提现单类型到页面供选择
		this.sendWithdrawwApplyTypesToPage(model, request);
		// 发送用户类型
		this.sendUserTypesToPage(model, request);
	}

	/**
	 * 发送提现类型到页面供选择
	 * 
	 * @param model
	 * @param request
	 */
	private void sendWithdrawwApplyTypesToPage(Model model,
			HttpServletRequest request) {
		List<DataTablesPageParam> applyTypesList = new ArrayList<DataTablesPageParam>();
		for (WithdraApplyType spe : WithdraApplyType.values()) {
			DataTablesPageParam dpp = new DataTablesPageParam();

			dpp.setName(spe.getName());
			dpp.setValue(spe.getValue());

			applyTypesList.add(dpp);
		}

		model.addAttribute("applyTypesList", applyTypesList);
	}

	/**
	 * 发送用户类型到页面供选择
	 * 
	 * @param model
	 * @param request
	 */
	private void sendUserTypesToPage(Model model, HttpServletRequest request) {
		List<DataTablesPageParam> userList = new ArrayList<DataTablesPageParam>();
		for (UserType spe : UserType.values()) {
			DataTablesPageParam dpp = new DataTablesPageParam();

			dpp.setName(spe.getName());
			dpp.setValue(spe.getValue());

			userList.add(dpp);
		}
		model.addAttribute("userList", userList);
	}

	/**
	 * 同意用户的提现申请
	 * 
	 * 已转账：状态为1<br/>
	 * 修改提现信息的状态为已转账<br/>
	 * 
	 * @param uuid
	 *            提现申请的id
	 * @param qm
	 * @return
	 */
	@RequestMapping(value = "/transfer", method = { RequestMethod.POST })
	@ResponseBody
	public String transfer(@RequestParam("uuid") String uuid) {
		try {
			myService.transfer(uuid);
		} catch (Exception e) {
			String message = e.getMessage();

			return message;
		}
		return "success";
	}

	/**
	 * 驳回请求：状态为2 <br />
	 * 修改提现信息的状态为驳回<br/>
	 * 
	 * @param uuid
	 *            提现申请的id
	 * @param failReason
	 * @return
	 */
	@RequestMapping("/reject")
	@ResponseBody
	public String reject(@RequestParam("uuid") String uuid,
			@RequestParam("failReason") String failReason) {

		myService.reject(uuid, failReason);
		return "success";
	}

}