package com.aebiz.b2b2c.customermgr.sysback.web.customeraudit;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.basecrud.vo.DataTablesPageParam;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.customermgr.customeraudit.service.CustomerAuditService;
import com.aebiz.b2b2c.customermgr.customeraudit.vo.CustomerAuthModel;
import com.aebiz.b2b2c.customermgr.customeraudit.vo.CustomerAuthQueryModel;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CertTypeEnum;

@Controller
@RequestMapping("/sysback/customeraudit")
public class CustomerAuditController extends
		BaseController<CustomerAuthModel, CustomerAuthQueryModel> {
	private CustomerAuditService myService;

	/* 会员账户信息service */
	@Autowired
	private CustomerService customerService;

	@Autowired
	public void setMyService(CustomerAuditService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public CustomerAuditController() {
		super("customermgr/sysback/customeraudit", "CustomerAudit",
				CustomerAuditController.class);
	}

	@Override
	protected void preparedListData(Model model, HttpServletRequest request) {
		// 将会员的证件类型发送到页面
		sendCertTypeListToPage(model);
	}

	// 会员实名认证审核通过，需要将认证状态更新到customer表中
	@RequestMapping("/doAuditPass")
	@ResponseBody
	public String doAuditPass(
			@RequestParam("customerUuid") String customerUuid,
			@RequestParam("auditReason") String auditReason) {
		// 会员实名认证
		myService.doAuditPass(customerUuid, auditReason);
		// 需要将会员的实名认证状态更新到会员的账户信息表中
		customerService.updateCustomerAuthState(customerUuid,
				CustomerAuthModel.CUSTOMERAUTH_AUTHSTATE_AUTHED);

		return "ture";
	}

	// 会员实名认证审核,需要将认证状态更新到customer表中
	@RequestMapping("/doAuditUnPass")
	@ResponseBody
	public String doAuditUnPass(
			@RequestParam("customerUuid") String customerUuid,
			@RequestParam("auditReason") String auditReason) {
		// 会员实名认证
		myService.doAuditUnPass(customerUuid, auditReason);
		// 需要将会员的实名认证状态更新到会员的账户信息表中
		customerService.updateCustomerAuthState(customerUuid,
				CustomerAuthModel.CUSTOMERAUTH_AUTHSTATE_UNAUTHED);

		return "ture";
	}

	/**
	 * 将证件类型数据发送到页面去展示
	 */
	private void sendCertTypeListToPage(Model model) {
		List<DataTablesPageParam> certTypeList = new ArrayList<DataTablesPageParam>();
		for (CertTypeEnum ct : CertTypeEnum.values()) {
			DataTablesPageParam dpp = new DataTablesPageParam();
			dpp.setName(ct.getKey());
			dpp.setValue(ct.getValue());
			certTypeList.add(dpp);
		}
		model.addAttribute("certTypeList", certTypeList);
	}
}