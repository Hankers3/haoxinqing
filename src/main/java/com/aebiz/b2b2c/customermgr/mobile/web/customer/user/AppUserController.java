package com.aebiz.b2b2c.customermgr.mobile.web.customer.user;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aebiz.b2b2c.baseframework.filemgr.helper.FileUploadHelper;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoModel;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.AppBaseController;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.HttpServletUtils;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.SysConst;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.service.VipclubIntegralLogService;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.IntegralType;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.VipclubIntegralLogModel;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomercharge.service.VirtualAccountCustomerChargeService;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomercharge.vo.VirtualAccountCustomerChargeModel;
import com.alibaba.fastjson.JSON;

/**
 * APP用户控制类
 * 
 * @author dgh
 * 
 */
@Controller
@RequestMapping("/app/customer/user")
public class AppUserController extends AppBaseController {

	public AppUserController() {
		super("", "", AppUserController.class);
	}

	/* 会员账户信息service */
	@Autowired
	private CustomerService customerService;
	@Autowired
	private VipclubIntegralLogService vipclubIntegralLogService;
	@Autowired
	private FileUploadHelper fileUpload;
	/* 记录会员虚拟账户Service */
	@Autowired
	private VirtualAccountCustomerChargeService virtualAccountCustomerChargeService;

	/************************************************************************************************/
	/**
	 * 患者端修改支付密码 16/01/14
	 * 
	 * @author szr
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/updatePayPassword", method = RequestMethod.POST)
	public String updatePayPassword(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("mobile") String mobile, @RequestParam("payPassword") String payPassword,
			@RequestParam("captcha") String captcha) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "payPassword,true", "captcha,true", "callback,true" });
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");
		// 调用service获取患者
		CustomerModel customerModel = customerService.getCustomerByMobile(mobile);
		if (customerModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "用户不存在"), callback);
			return null;
		}
		if (StringUtil.isEmpty(payPassword)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "新密码为空"), callback);
			return null;
		}
		// 获取修改密码时发送的验证码，存在session中
		String smsCode = (String) request.getSession().getAttribute("customerSmsCode");
		System.out.println("=================smsCode===========" + smsCode);
		if (StringUtil.isEmpty(smsCode)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "验证码为空"), callback);
			return null;
		} else if (!captcha.trim().equals(smsCode.trim())) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "验证码错误"), callback);
			return null;
		}

		/************************ 修改患者信息表 *************************/
		// 修改密码
		customerModel.setPayPassword(payPassword);

		// 调用service中修改方法
		customerService.update(customerModel);
		// 设置返回信息
		// jsonMap.put("", "");
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 检查支付密码
	 * 
	 * 
	 * @param request
	 * @param response
	 * @param mobile
	 * @param password
	 * @param captcha
	 * @return
	 */
	@RequestMapping(value = "/checkPayPassWord", method = RequestMethod.POST)
	public String checkPayPassWord(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("customerUuid") String customerUuid, @RequestParam("password") String password) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "password,true", "callback,true" });
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");
		// 调用service获取患者
		CustomerModel customerModel = customerService.getByUuid(customerUuid);
		if (customerModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "用户不存在"), callback);
			return null;
		}
		String payPassword = customerModel.getPayPassword();
		
		if (!password.equals(payPassword)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "支付密码不对"), callback);
			return null;
		}

		// 设置返回信息
		// jsonMap.put("", "");
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}
	
	/**
	 * 检查支付密码
	 * 
	 * 
	 * @param request
	 * @param response
	 * @param mobile
	 * @param password
	 * @param captcha
	 * @return
	 */
	@RequestMapping(value = "/getPayPassWord", method = RequestMethod.GET)
	public String getPayPassWord(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("customerUuid") String customerUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true" });
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");
		// 调用service获取患者
		CustomerModel customerModel = customerService.getByUuid(customerUuid);
		if (customerModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "用户不存在"), callback);
			return null;
		}
		String payPassword = customerModel.getPayPassword();
		if (StringUtil.isEmpty(payPassword)) {
			jsonMap.put("hasPassword", "0")	;
		}else{
			jsonMap.put("hasPassword", "1")	;
		}
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}
	

	/**
	 * 患者端设置支付密码 16/01/14
	 * 
	 * @author szr
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/setPayPassword", method = RequestMethod.POST)
	public String setPayPassword(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("customerUuid") String customerUuid, @RequestParam("payPassword") String payPassword) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "customerUuid,true", "payPassword,true", "callback,true" });
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");
		// 调用service获取患者
		CustomerModel customerModel = customerService.getByUuid(customerUuid);
		if (customerModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "用户不存在"), callback);
			return null;
		}
		if (StringUtil.isEmpty(payPassword)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "新密码为空"), callback);
			return null;
		}

		/************************ 修改患者信息表 *************************/
		// 修改密码
		customerModel.setPayPassword(payPassword);

		// 调用service中修改方法
		customerService.update(customerModel);
		// 设置返回信息
		// jsonMap.put("", "");
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 患者端登录后每天启动软件后或得积分 2016/01/19
	 * 
	 * @author szr
	 * @param request
	 * @param response
	 * @param type
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/getVipclubByLogin", method = RequestMethod.GET)
	public String getVipclubByLogin(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("customerUuid") String customerUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "customerUuid,true", "callback,true" });
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		// 定义返回字段，1表示积分增加成功，0表示今天已经获取过积分
		String message = "0";
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, "患者id不能为空！"));
			return null;
		}
		// 判断今天是否已经增加积分，已经增加就不在增加
		VipclubIntegralLogModel vipclubIntegralLogModel = vipclubIntegralLogService.getVipclubIntegralLogByConditions(
				customerUuid, VipclubIntegralLogModel.VIPCLUB_USERTYPE_CUS, IntegralType.GET_BY_ORDER.getValue(), "");
		if (vipclubIntegralLogModel == null) {
			// 添加积分日志
			vipclubIntegralLogService.saveVipIntegralLog(customerUuid, "add", 1, IntegralType.GET_BY_ORDER.getValue(),
					VipclubIntegralLogModel.VIPCLUB_USERTYPE_CUS, "每天启动软件获得积分", "");
			message = "1";
		}
		// 1表示积分增加成功，0表示今天已经获取过积分
		jsonMap.put("prompt", message);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 充值账户生成订单号
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/balance", method = RequestMethod.POST)
	public String balance(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("customerUuid") String customerUuid, @RequestParam("balancePrice") String balancePrice) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "customerUuid,true", "callback,true" });
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		// 设置返回信息
		Object callback = map.get("callback");
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 创建生成充值的订单号
		String chargeNo = "charge" + virtualAccountCustomerChargeService.createChargeNo();
		// 账户
		CustomerModel cm = customerService.getByUuid(customerUuid);
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, "患者id不能为空！"));
			return null;
		}
		if (cm == null) {
			HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, "患者为空！"));
			return null;
		}
		// 充值金额
		double operAmount = 0;
		if (!StringUtil.isEmpty(balancePrice)) {
			operAmount = Double.parseDouble(balancePrice);
		}

		/* 增加会员虚拟账余额 日志 */
		VirtualAccountCustomerChargeModel virtualAccountCustomerChargeModel = new VirtualAccountCustomerChargeModel();
		virtualAccountCustomerChargeModel.setChargeNo(chargeNo);
		// 保存订单号
		virtualAccountCustomerChargeModel.setOrderId(chargeNo);
		// 用户名
		virtualAccountCustomerChargeModel.setCustomerUuid(cm.getUuid());
		// 充值金额
		virtualAccountCustomerChargeModel.setOperAmount((float) operAmount);
		virtualAccountCustomerChargeModel.setCreateTime(DateFormatHelper.getNowTimeStr());
		// 支付状态
		virtualAccountCustomerChargeModel.setChargeState(VirtualAccountCustomerChargeModel.UNDER_PAY);
		System.out.println(
				"======virtualAccountCustomerChargeModel===" + JSON.toJSONString(virtualAccountCustomerChargeModel));
		// 保存充值信息
		virtualAccountCustomerChargeService.create(virtualAccountCustomerChargeModel);

		jsonMap.put("operAmount", operAmount);
		jsonMap.put("orderId", chargeNo);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 通过手机号删除患者信息 线上环境屏蔽该方法
	 * 
	 * @author szr
	 * @date 2016/02/15
	 */
	/*@RequestMapping(value = "/deleteByMobile", method = RequestMethod.GET)
	public String deleteByMobile(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("mobile") String mobile) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "mobile,true" }); // 标志
																	// 以及必需要传的参数
		Object callback = map.get("callback");

		// 判断doctorUuid是否为空
		if (StringUtil.isEmpty(mobile)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "手机号为空"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 根据手机号得到医生id
		CustomerModel customerModel = customerService.getCustomerByMobile(mobile);
		if (customerModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "手机号内无账号信息"), callback);
			return null;
		}
		String uuid = customerModel.getUuid();
		// 删除用户信息
		customerService.delete(customerModel);
		CustomerInfoModel customerInfoModel = customerInfoService.getCustomerInfoModelByCustomerUuid(uuid);
		if (customerInfoModel != null) {
			customerInfoService.delete(customerInfoModel);
		}
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}*/

	/**
	 * 保存会员图像信息
	 * 
	 * @param m
	 *            会员基础信息表
	 * @param in
	 *            InputStream图片流
	 * @return
	 */
	public CustomerInfoModel uploadImage(CustomerInfoModel m, InputStream in) {
		try {
			if (in != null) {
				// 生成图片名称前缀
				String filePrefix = "customerInfo" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
				// 生成图片名称
				String newName = filePrefix + ".jpg";
				m.setImage(newName);
				// 通过InputStream流 和图片名称上传图片
				fileUpload.uploadFiles(in, newName);
			} else {
				m.setImage("");
			}
		} catch (Exception ex) {
			m.setImage("");
		}

		return m;
	}

	/**
	 * 关于雇我吧
	 * 
	 * @return
	 */
	@RequestMapping("/guanyuguwoba")
	public String guanyuguwoba() {
		return "appmore/guanyuguwoba";
	}

	/**
	 * 服务承诺
	 * 
	 * @return
	 */
	@RequestMapping("/fuwuchengnuo")
	public String fuwuchengnuo() {
		return "appmore/fuwuchengnuo";
	}

	/**
	 * 使用帮助
	 * 
	 * @return
	 */
	@RequestMapping("/shiyongbangzhu")
	public String shiyongbangzhu() {
		return "appmore/shiyongbangzhu";
	}
}
