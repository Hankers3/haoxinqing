package com.aebiz.b2b2c.customermgr.sysback.web.customer;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.basecrud.web.vo.BaseWebModel;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerQueryModel;
import com.aebiz.b2b2c.customermgr.customerfrozenlog.service.CustomerFrozenLogService;
import com.aebiz.b2b2c.customermgr.mobile.web.service.user.AppUserService;

/**
 * 平台后台会员系统控制器
 * 
 * @author likun
 * 
 */
@Controller
@RequestMapping("/sysback/customer")
public class CustomerController extends
		BaseController<CustomerModel, CustomerQueryModel> {

	private CustomerService myService;
	/* 会员冻结日志service */
	@Autowired
	private CustomerFrozenLogService customerFrozenLogService;

	@Autowired
	public void setMyService(CustomerService bs) {
		this.myService = bs;
		super.setBs(bs);
	}
	
	public CustomerController() {
		super("customermgr/sysback/customer", "Customer",
				CustomerController.class);
	}

	/**
	 * 判断该用户名是否已经存在
	 * 
	 * @param customerName
	 */
	@RequestMapping(value = { "/checkCustomerName" }, method = { RequestMethod.GET })
	@ResponseBody
	public String checkCustomerNameExist(
			@RequestParam("customerName") String customerName,
			HttpServletRequest request, HttpServletResponse response) {
		if (this.myService.checkCustomerNameExist(customerName)) {
			return "true";
		}

		return "false";
	}

	
	/**
	 * 判断手机号是否已经存在
	 * 
	 * @param mobile
	 */
	@RequestMapping(value = { "/checkMobile" }, method = { RequestMethod.GET })
	@ResponseBody
	public String checkMobileExist(@RequestParam("mobile") String mobile) {
		if (this.myService.checkMobileExist(mobile)) {
			return "true";
		}

		return "false";
	}

	/**
	 * 判断该邮箱是否已经存在
	 * 
	 * @param email
	 */
	@RequestMapping(value = { "/checkEmail" }, method = { RequestMethod.GET })
	@ResponseBody
	public String checkEmailExist(@RequestParam("email") String email) {
		if (this.myService.checkEmailExist(email)) {
			return "true";
		}

		return "false";
	}

	/**
	 * 会员冻结，需要更新会员登录信息中的冻结状态<br />
	 * 1表示冻结，0表示未冻结
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/frozen")
	@ResponseBody
	public String frozen(@RequestParam("customerUuid") String customerUuid,
			@RequestParam("frozenNote") String frozenNote,
			@RequestParam("frozenType") String frozenType,
			HttpServletResponse response) throws IOException {
		// 冻结操作人
		String oper = LoginUserHelper.getLoginUserUuid();

		this.myService.frozen(customerUuid, frozenType, oper);
		this.customerFrozenLogService.addFrozenLog(customerUuid, frozenType,
				frozenNote, oper);

		return "true";
	}
	
	
	/**
	 * 患者审核，需要更新患者登录信息中的审核状态<br />
	 * 1表示通过，0表示未审核，2表示未通过
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/state")
	@ResponseBody
	public String state(@RequestParam("customerUuid") String customerUuid,
			@RequestParam("state") String state,
			HttpServletResponse response) throws IOException {

		this.myService.state(customerUuid,state);	
		return "true";
	}
	

	/**
	 * 会员解冻,需要更新会员登录信息中的冻结状态<br />
	 * 1表示冻结，0表示未冻结
	 * 
	 * @param uuids
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/unfrozen")
	@ResponseBody
	public String unfrozen(@RequestParam("customerUuid") String customerUuid,
			@RequestParam("unFrozenNote") String unFrozenNote,
			HttpServletResponse response) throws IOException {
		// 解冻操作人
		String oper = LoginUserHelper.getLoginUserUuid();
	
		this.myService.unfrozen(customerUuid, oper);
		this.customerFrozenLogService.addUnFrozenLog(customerUuid,
				unFrozenNote, oper);

		return "true";
	}

	/**
	 * 会员重置密码:<br />
	 * 1.需要知道会员的手机号，手机号可以是会员注册的手机号或者新手机号<br />
	 * 2.系统生成随机密码，系统根据会员编号将随机密码更新到数据库<br />
	 * 3.然后调用短信接口给用户发送随机密码。<br />
	 * 
	 * @param customerUuid
	 * @param customerMobile
	 * @return
	 */
	@RequestMapping("/resetPassword")
	@ResponseBody
	public String resetPassword(
			@RequestParam("customerUuid") String customerUuid,
			@RequestParam("confirmMobile") String mobile) {
		// 生成6位长的随机密码
		String randomPwd = this.genRandomNum(6);

		if (!StringUtil.isEmpty(customerUuid) && !StringUtil.isEmpty(randomPwd)) {
			this.myService.resetPwd(customerUuid, randomPwd);
			// 调用短信接口将随机密码发送给用户
			AppUserService.sendPassword(mobile, randomPwd);

			return "true";
		}
		return "false";
	}

	/**
	 * 生成随机密码的长度为6
	 * 
	 * @param pwd_len
	 * @return
	 */
	public String genRandomNum(int pwd_len) {
		// 10个数字
		final int maxNum = 10;
		int i; // 生成的随机数
		int count = 0; // 生成的密码的长度
		char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		StringBuffer pwd = new StringBuffer("");
		Random random = new Random();
		while (count < pwd_len) {
			// 生成随机数，取绝对值，防止生成负数，

			i = Math.abs(random.nextInt(maxNum)); // 生成的数最大为10

			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}

		return pwd.toString();
	}

	/**
	 * 根据传入的患者分类信息判断患者分类中是否已经存在患者
	 * 
	 * @param checkIds
	 *            患者分类uuid
	 * @return
	 */
	@RequestMapping(value = { "/checkName" }, method = { RequestMethod.GET })
	@ResponseBody
	public String checkName(@RequestParam("checkIds") List<String> checkIds,
			HttpServletRequest request, HttpServletResponse response) {
		if (myService.checkCustomerCategory(checkIds)) {
			return "true";
		}
		return "false";
	}
	
	
	
	
	

}