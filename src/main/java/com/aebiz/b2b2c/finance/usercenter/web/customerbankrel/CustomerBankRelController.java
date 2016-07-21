package com.aebiz.b2b2c.finance.usercenter.web.customerbankrel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerInteractive;
import com.aebiz.b2b2c.finance.CodeManager;
import com.aebiz.b2b2c.finance.customerbankrel.service.CustomerBankRelService;
import com.aebiz.b2b2c.finance.customerbankrel.vo.CustomerBankRelModel;
import com.aebiz.b2b2c.finance.customerbankrel.vo.CustomerBankRelQueryModel;

/**
 * 会员提出绑定申请,申请完,会员是不能立即去验证的,由平台给申请记录中的银行卡打一定的金额,<br>
 * 
 * 然后后台更新这个金额,并且把验证状态修改为待验证状态,<br>
 * 
 * 这时会员可以根据收到的金额去和会员中心验证,如果错误3次那么就不能再次验证了,需要联系平台
 *
 * @author tangyunkai
 *
 * @date 2014年12月20日 下午12:26:57 
 *
 */
@Controller("UsercenterCustomerBankRelController")
@RequestMapping("/usercenter/customerbankrel")
public class CustomerBankRelController extends BaseController<CustomerBankRelModel,CustomerBankRelQueryModel>{
	
	public static final String CUSTOMERBONDCODEKEY = "CUSTOMERBONDCODEKEY";// 放到cookies中的key
	
	private CustomerBankRelService myService;
	@Autowired
	public void  setMyService(CustomerBankRelService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public CustomerBankRelController(){
		super("finance/usercenter/customerbankrel","CustomerBankRel",CustomerBankRelController.class);
	}
	
	//注入会员的接口
	@Autowired
	private CustomerInteractive customerInteractive;
	
	/**
	 * 如果数据库已经存在当前会员的申请记录,并且验证状态是已成功,则跳转到验证页面<br>
	 * 
	 * 反之,则跳转到申请页面
	 */
	@RequestMapping(value = {"/toBondBank"}, method = {RequestMethod.GET})
	public String toBondBank(Model model,HttpServletRequest request) {
		// 获取当前登陆的会员的uuid
		String uuid = LoginUserHelper.getCustomerLoginUserUuid();
		
		//获取会员绑定银行卡记录
		CustomerBankRelModel bankRelModel =  myService.getCustomerBankRelModelByCustomerUuid(uuid);
		if(bankRelModel == null ){
	
			model.addAttribute("uuid", uuid);
			return "finance/usercenter/customerbankrel/CustomerBankRelAdd";
		}else{
			model.addAttribute("uuid", uuid);
			model.addAttribute("m", bankRelModel);
			return "finance/usercenter/customerbankrel/CustomerBankRelInfo";
		}
		
	}
	
	/**
	 * 增加会员银行卡绑定<br>
	 * 
	 * 添加成功后,数据库中已经存在该会员的申请记录,<br>
	 * 
	 * 这时跳转到CustomerBankRelInfo页面,会员可以修改信息
	 */
	@RequestMapping(value = {"/add"}, method = {RequestMethod.POST})
	public String add(Model model, @ModelAttribute("m") CustomerBankRelModel m,
			HttpServletRequest request) {
		//TODO 获取当前登陆的会员的uuid
		String customerUuid = LoginUserHelper.getCustomerLoginUserUuid();
		
		Map<String, List<String>> mapErrorMsg = this.getMapErrorMsg();
		mapErrorMsg.clear();
		if (!checkAdd(model, m, request)) {
			request.setAttribute("ShowMsgs", mapErrorMsg);
		
			return (String) request.getAttribute("ERROR_BACK_URL");
		}
		m.setCustomerUuid(customerUuid);
		myService.create(m);
		model.addAttribute("m", m);
		//添加成功后,再次请求去绑定方法,这时,数据库肯定存在该会员的申请记录,从而跳转到CustomerBankRelInfo页面
		return "redirect:toBondBank";
	}
	
	/**
	 * 修改绑定信息,修改成功后,同样需要跳转到CustomerBankRelInfo页面
	 */
	@RequestMapping(value = {"/update"}, method = {RequestMethod.POST})
	public String update(Model model,
			@ModelAttribute("m") CustomerBankRelModel m,
			HttpServletRequest request) {
		// TODO 获取当前登陆的会员的uuid
		String customerUuid = LoginUserHelper.getCustomerLoginUserUuid();

		Map<String, List<String>> mapErrorMsg = this.getMapErrorMsg();
		mapErrorMsg.clear();
		if (!checkUpdate(model, m, request)) {
			request.setAttribute("ShowMsgs", mapErrorMsg);

	/*		// 如果验证存在错误,则,需要返回到原页面,同时需要把实名认证的证件图片信息发送到页面
			CustomerAuthModel customerAuthModel = customerInteractive
					.getCustomerAuthModelByCustomerUuid(customerUuid);
			if (customerAuthModel != null) {
				model.addAttribute("customerAuthModel", customerAuthModel);
			}*/
			return (String) request.getAttribute("ERROR_BACK_URL");
		}
		m.setCustomerUuid(customerUuid);
		myService.updateBankRel(m);
		model.addAttribute("m", m);
		// 修改成功后,再次请求去绑定方法,这时,数据库肯定存在该会员的申请记录,从而跳转到CustomerBankRelInfo页面
		return "redirect:toBondBank";
	}
	
	/**
	 * 添加绑定信息时验证验证码
	 */
	protected boolean checkAdd(Model model, CustomerBankRelModel m, HttpServletRequest request) {
		if(!StringUtil.isEmpty(m.getVilidateCode())){
			Cookie[] cookies = request.getCookies();
			String code = "";
			for (Cookie cookie : cookies) {
				if (this.CUSTOMERBONDCODEKEY.equals(cookie.getName())) {
					code = cookie.getValue();
				}
			}
			
			if(!m.getVilidateCode().equalsIgnoreCase(code)){
				this.putErrorMsg("vilidateCode", MessageHelper
						.getMessage("bankBoundEdit.validateCode.Error"));
			}
			
		}
		if (this.getMapErrorMsg() != null && this.getMapErrorMsg().size() > 0) {
			model.addAttribute("m", m);
			request.setAttribute("ERROR_BACK_URL",
					"finance/usercenter/customerbankrel/CustomerBankRelAdd");
			return false;
		}
		return true;
	}
	
	/**
	 * 在修改绑定信息时校验验证码
	 */
	protected boolean checkUpdate(Model model, CustomerBankRelModel m, HttpServletRequest request) {
		if(!StringUtil.isEmpty(m.getVilidateCode())){
			Cookie[] cookies = request.getCookies();
			String code = "";
			for (Cookie cookie : cookies) {
				if (this.CUSTOMERBONDCODEKEY.equals(cookie.getName())) {
					code = cookie.getValue();
				}
			}
			
			if(!m.getVilidateCode().equalsIgnoreCase(code)){
				this.putErrorMsg("vilidateCode", MessageHelper
						.getMessage("bankBoundEdit.validateCode.Error"));
			}
			
		}
		if (this.getMapErrorMsg() != null && this.getMapErrorMsg().size() > 0) {
			model.addAttribute("m", m);
			request.setAttribute("ERROR_BACK_URL",
					"finance/usercenter/customerbankrel/CustomerBankRelUpdate");
			return false;
		}
		return true;
	}
	
	/**
	 * 在平台未打款,修改申请状态为待审核前,会员可以修改绑定信息,也需要把认证信息发送到页面
	 */
	@RequestMapping(value = {"/toUpdate/{uuid}"}, method = {RequestMethod.GET})
	public String toUpdate(@PathVariable("uuid") String uuid, Model model,
			HttpServletRequest request) {
		// TODO 获取当前登陆的会员的uuid
		String customerUuid = LoginUserHelper.getCustomerLoginUserUuid();
		CustomerBankRelModel m = myService.getByUuid(uuid);

	/*	// 修改绑定信息,那么该会员肯定是已经实名认证了,那么直接获取实名认证的证件图片显示到页面
		CustomerAuthModel customerAuthModel = customerInteractive
				.getCustomerAuthModelByCustomerUuid(customerUuid);
		if (customerAuthModel != null) {
			model.addAttribute("customerAuthModel", customerAuthModel);
		}*/
		model.addAttribute("m", m);

		return "finance/usercenter/customerbankrel/CustomerBankRelUpdate";
	}

	/**
	 * 验证会员输入的金额<br>
	 * 
	 * 把输入的金额和数据库中的金额进行比较,如果一致,则验证成功,将会员账户的银行卡信息替换成新的,<br>
	 * 
	 * 反之,则记录失败的次数,查过三次,需要联系客服来解决
	 * 
	 * @param model
	 * @param uuid 绑定记录的额uuid
	 * @param amount 会员输入的金额
	 * @param request
	 * @return 
	 * String
	 */
	@RequestMapping(value = {"/validateAmount"}, method = {RequestMethod.POST})
	public String validateAmount(Model model,
			@RequestParam("uuid") String uuid,@RequestParam("amount") String amount,
			HttpServletRequest request) {
		//验证输入的金额,成功则更改会员账户银行卡信息,不成功则增加失败次数
		CustomerBankRelModel bankRelModel = myService.checkValidateAmount(uuid, amount);
		
		if(bankRelModel != null){
			model.addAttribute("m", bankRelModel);
		}
		
		return "finance/usercenter/customerbankrel/CustomerBankRelSuccessOrFail";
	}
	
	/**
	 * 获取手机验证码,把验证码放到cooikes
	 * @param mobile
	 * @param request
	 * @param response
	 * @return 
	 * String
	 */
	@RequestMapping("/getCode")
	@ResponseBody
	public String getCode(
			@RequestParam("mobile") String mobile,
			 HttpServletRequest request,
			HttpServletResponse response) {

		// 获取验证码
		String code = CodeManager.getRandomNum(6);

		boolean flag = false;
			// 手机发送验证码
			//TODO  调用短信接口

			flag = true;

		if (flag) {
			//将随机生成的验证码放入Cookie中
			Cookie cookie = new Cookie(this.CUSTOMERBONDCODEKEY,code);
			response.addCookie(cookie);
			System.out.println(code);
			
			return "success";
		} else {
			return "fail";
		}
	}
	
}