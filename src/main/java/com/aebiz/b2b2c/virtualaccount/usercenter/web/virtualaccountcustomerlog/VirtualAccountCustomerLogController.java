package com.aebiz.b2b2c.virtualaccount.usercenter.web.virtualaccountcustomerlog;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.basecrud.web.vo.BaseWebModel;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.finance.customeraccount.service.CustomerAccountInteractive;
import com.aebiz.b2b2c.finance.customeraccount.vo.CustomerAccountModel;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomerlog.service.VirtualAccountCustomerLogService;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomerlog.vo.VirtualAccountCustomerLogModel;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomerlog.vo.VirtualAccountCustomerLogQueryModel;

@Controller("CustomerVirtualAccountCustomerLogController")
@RequestMapping("/usercenter/virtualaccountcustomerlog")
public class VirtualAccountCustomerLogController extends BaseController<VirtualAccountCustomerLogModel,VirtualAccountCustomerLogQueryModel>{
	private VirtualAccountCustomerLogService myService;
	@Autowired
	public void  setMyService(VirtualAccountCustomerLogService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public VirtualAccountCustomerLogController(){
		super("virtualaccount/usercenter/virtualaccountcustomerlog","VirtualAccountCustomerLog",VirtualAccountCustomerLogController.class);
	}
	
	//注入财务系统的会员账户对外接口
	@Autowired
	private CustomerAccountInteractive accountInteractive;
	
	/**
	 * 跳转到我的账户信息页面,将当前登陆的会员的账户信息发送到面
	 */
	@RequestMapping(value = {"/toMyAccount"}, method = {RequestMethod.GET})
	public String toMyAccount(Model model, HttpServletRequest request) {
		// 获取当前登陆的会员的uuid
		String customerUuid = LoginUserHelper.getCustomerLoginUserUuid();
		CustomerAccountModel accountModel = accountInteractive.getCustomerAccountModelByCustomerUuid(customerUuid);
		if(accountModel != null){
			model.addAttribute("account", accountModel);
		}
		
		return "virtualaccount/usercenter/virtualaccountcustomerlog/MyVirtualAccount";
	}
	
	
}