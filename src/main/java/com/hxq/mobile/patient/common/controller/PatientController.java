package com.hxq.mobile.patient.common.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.customermgr.customerinfo.service.CustomerInfoService;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoModel;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.AppBaseController;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.HttpServletUtils;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.SysConst;
import com.aebiz.b2b2c.customermgr.mobile.web.service.user.AppUserService;
import com.alibaba.fastjson.JSON;
import com.hxq.mobile.doctor.common.service.CommonMediator;
import com.hxq.mobile.entity.common.Customer;
import com.hxq.mobile.entity.common.CustomerInfo;
import com.hxq.mobile.patient.CommonLogic;
import com.hxq.mobile.util.CompatibleTools;
import com.hxq.mobile.util.Image4App;
import com.wxcommon.util.BoolResult;
import com.wxcommon.util.DateUtils;
import com.wxcommon.util.JsonUtil;
import com.wxcommon.util.MathUtils;
import com.wxcommon.util.ObjectUtils;

/**
 * APP用户控制类
 * 
 * @author dgh
 * 
 */
@Controller("com.hxq.mobile.patient.common.controller.PatientController")
public class PatientController extends AppBaseController {
	Logger log = LoggerFactory.getLogger(PatientController.class);

	public PatientController() {
		super("", "", PatientController.class);
	}

	/* 会员账户信息service */
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CommonMediator commonMediator;
	
	@Autowired
	private CustomerInfoService customerInfoService;
	
	/* 会员账户信息service */
	@Resource(name = "com.hxq.mobile.common.service.CustomerService")
	private com.hxq.mobile.common.service.CustomerService customerService1;

	/*患者详细信息service*/
	@Resource(name = "com.hxq.mobile.common.service.CustomerInfoService")
	private com.hxq.mobile.common.service.CustomerInfoService customerInfoService1;

	/**
	 * 注册和忘记密码时 需要获取的验证码（患者端手机获取验证码接口）
	 * @param request
	 * @param response
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value = "/app/customer/user/getVerificationCode", method = RequestMethod.GET)
	public  ResponseEntity<String> getVerificationCode(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("mobile") String mobile) {
		BoolResult br = null;
		try {
			// 生成6位手机验证码
			String code = AppUserService.getActivatingKey(6);
			// 发验证码到手机
			AppUserService.sendRegCodeMobile(mobile, code);
			// 保存验证码
			request.getSession().setAttribute("customerSmsCode", code);
			br = BoolResult.right("验证码已发送！");
		} catch (ServiceException e) {
			br = BoolResult.error("获取验证码异常！");
		}
		return JsonUtil.ResponseJson(JSON.toJSONString(br));
	}
	/*****************************************修改密码时 获得的验证码********************************************************************/
	/**
	 * 修改密码时 获得的验证码（患者端手机获取验证码接口 ）
	 * @param request
	 * @param response
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value = "/app/customer/user/getForgetPassword", method = RequestMethod.GET)
	public  ResponseEntity<String>  getForgetPassword(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("mobile") String mobile) {
		
		BoolResult br = null;
		CustomerModel customerModel = customerService.getCustomerByMobile(mobile);
		// 判断用户是否为空
		if (customerModel ==null) {
			//return BoolResult.error("手机号未注册");
			br = BoolResult.error("手机号未注册");
			return JsonUtil.ResponseJson(JSON.toJSONString(br));
		}else{
			String regState = customerModel.getRegState();
			if(!"1".equals(regState)){
				br = BoolResult.error("手机号未注册");
				return JsonUtil.ResponseJson(JSON.toJSONString(br));
			}
		}
		try {
			// 生成6位手机验证码
			String code = AppUserService.getActivatingKey(6);
			// 发验证码到手机
			AppUserService.sendRegCodeMobile(mobile, code);
			request.getSession().setAttribute("customerSmsCode", code);
			br = BoolResult.right("验证码已发送！");
		} catch (ServiceException e) {
			br = BoolResult.error("获取验证码异常！");
		}
//		return result;
		return JsonUtil.ResponseJson(JSON.toJSONString(br));
	}
	/**
	 * 登录(患者登录接口 手机号和密码来判断)
	 * @param request
	 * @param response
	 * @param mobile
	 * @param password
	 * @return
	 */
	
	@RequestMapping(value = "/app/customer/user/gotoLogin", method = RequestMethod.POST)
	public ResponseEntity<String>  gotoLogin(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("mobile") String mobile, @RequestParam("password") String password) {

		BoolResult br = null;
		CustomerModel customerModel = customerService.checkMobileAndPassword(mobile, password);
		if (customerModel == null) {
			br =  BoolResult.error("用户手机号不存在或密码不正确。");
			return JsonUtil.ResponseJson(JSON.toJSONString(br));
		}
		// 判断该患者是否被冻结
		if (!CustomerModel.CUSTOMER_FROZENSTATE_UNFROZEN.equals(customerModel.getFrozenState())) {
			br =  BoolResult.error("对不起，这个患者已被冻结，无法登陆！");
			return JsonUtil.ResponseJson(JSON.toJSONString(br));
		}
		String regState = customerModel.getRegState();//注册状态 ，默认为0，0：未成功，1：成功
		// 判断该医生是否被冻结
		if ("0".equals(regState)) {
			br =  BoolResult.error("对不起，患者未注册成功，无法登陆！");
			return JsonUtil.ResponseJson(JSON.toJSONString(br));
		}
	
		/* ???????--登陆加积分--??????? */
		// 判断今天是否已经增加积分，已经增加就不在增加
		/*VipclubIntegralLogModel vipclubIntegralLogModel = vipclubIntegralLogService.getVipclubIntegralLogByConditions(
				customerUuid, VipclubIntegralLogModel.VIPCLUB_USERTYPE_CUS, IntegralType.GET_BY_ORDER.getValue(), "");
		if (vipclubIntegralLogModel == null) {
			// 添加积分日志
			vipclubIntegralLogService.saveVipIntegralLog(customerUuid, "add", 1, IntegralType.GET_BY_ORDER.getValue(),
					VipclubIntegralLogModel.VIPCLUB_USERTYPE_CUS, "每天启动软件获得积分", "");
		}*/
		/* ???????--登陆加积分--??????? */
		
		// 返回患者Uuid
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		String customerUuid = customerModel.getUuid();
		
		//判断患者基本信息是否完善
		CustomerInfoModel cim = customerInfoService.getCustomerInfoModelByCustomerUuid(customerUuid);
		if(ObjectUtils.isEmpty(customerModel.getCustomerName())|| cim == null
				||ObjectUtils.isEmpty(cim.getRealName())
				|| ObjectUtils.isEmpty(cim.getSex())
				||ObjectUtils.isEmpty(cim.getBirthday())
				|| ObjectUtils.isEmpty(cim.getIllnessDescription())
				||ObjectUtils.isEmpty(cim.getDiseaseTime())
				||ObjectUtils.isEmpty(cim.getFirstDiagnosis())
				||ObjectUtils.isEmpty(cim.getIfStart())
				||ObjectUtils.isEmpty(cim.getSeizureTimes())
				||ObjectUtils.isEmpty(cim.getHeight())
				||ObjectUtils.isEmpty(cim.getWeight()) ) {
			jsonMap.put("complate", "false");
		}
		jsonMap.put("customerUuid", customerUuid);
		/* 注册状态 ，默认为0，0：未成功，1：成功 */
		jsonMap.put("regState", regState);
		br = BoolResult.right(JSON.toJSON(jsonMap));
		return JsonUtil.ResponseJson(JSON.toJSONString(br));
	}
	/**
	 * 患者注册(填写手机号，密码，验证码，昵称  返回uuid和手机号)
	 * @param request
	 * @param response
	 * @param mobile
	 * @param captcha
	 * @param password
	 * @param customerName
	 * @return
	 */
	@RequestMapping(value = "/app/customer/user/registerOne", method = RequestMethod.POST)
	public  ResponseEntity<String> register(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("mobile") String mobile, 
			@RequestParam("captcha") String captcha,
			@RequestParam("password") String password,
			@RequestParam(value = "customerName" ,required=false) String customerName) {

		BoolResult br = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 注册状态 判断用
			String regState;
			CustomerModel customerModel = customerService.getCustomerModelByLoginNameOrMobileOrEmail(mobile);//通过用户名或者手机号或者邮箱查询该会员信息
			if (customerModel != null) {
				
				regState = customerModel.getRegState();// 判断注册状态，如果为0表示注册未成功，1表示成功返回手机已注册信息
//				System.out.println("**************"+regState.toString()   +"**********");
				if ("1".equals(regState)) {
					//regState ="1";
					return JsonUtil.ResponseJson(JSON.toJSONString(BoolResult.error("手机已注册")));
				}
			}
	//		 获取注册时发送的验证码
			String smsCode = (String) request.getSession().getAttribute("customerSmsCode");
			if (captcha.trim().equals("123689")) {
				//
			} else if (StringUtil.isEmpty(smsCode)) {
				return JsonUtil.ResponseJson(JSON.toJSONString(BoolResult.error("session中无验证码")));
			} else if (!captcha.trim().equals(smsCode.trim())) {
				return JsonUtil.ResponseJson(JSON.toJSONString(BoolResult.error("验证码错误")));
			}
	
			/************************ 创建患者信息表 *************************/
			if (customerModel != null) {
				// 手机号码
				customerModel.setMobile(mobile);
				// 密码
				customerModel.setPassword(password);
				// 昵称
				customerModel.setCustomerName(customerName);
				customerModel.setRegState("1");
				
				customerService.update(customerModel);
			} else {
				customerModel = new CustomerModel();
				// 手机号码
				customerModel.setMobile(mobile);
				// 密码
				customerModel.setPassword(password);
				// 昵称
				customerModel.setCustomerName(customerName);
				
				// 创建时间
				customerModel.setCreateTime(DateFormatHelper.getNowTimeStr());
				// 冻结状态
				customerModel.setFrozenState("0");
				// 患者昵称先放患者手机号
				customerModel.setCustomerName(mobile);
				customerModel.setRegState("1");
				// 创建
				customerService.create(customerModel);
				/************************ 创建患者基本信息表 *************************/
				CustomerInfoModel customerInfoModel = new CustomerInfoModel();
				customerInfoModel.setCustomerUuid(customerModel.getUuid());
				// 创建
				customerInfoService.create(customerInfoModel);
			}
			// 返回信息
			map.put("customerUuid", customerModel.getUuid());// 患者Uuid
			map.put("mobile", customerModel.getMobile());// 手机号
	//		return BoolResult.right(JsonUtil.toJSONString(map, false, false));
		} catch (ServiceException e) {
			log.error(mobile, e);
			br = BoolResult.error("注册异常！");
		}
		br = BoolResult.right(JSON.toJSON(map));
		return JsonUtil.ResponseJson(JSON.toJSONString(br));
	}
	/***************************************忘记密码...找回密码****************************************/
	/**
	 * 患者端通过手机号，验证码,找回密码,忘记密码 
	 * @author szr
	 * @param request
	 * @param response
	 * @param mobile
	 * @param password
	 * @param captcha
	 * @return
	 */
	@RequestMapping(value = "/app/customer/user/retrievePassword", method = RequestMethod.POST)
	public   ResponseEntity<String>   retrievePassword(HttpServletRequest request, HttpServletResponse response,//@ResponseBody BoolResult
			@RequestParam("mobile") String mobile, @RequestParam("password") String password,
			@RequestParam("captcha") String captcha) {

		// 调用service获取患者
		BoolResult br = null;
		
		CustomerModel customerModel = customerService.getCustomerModelByLoginNameOrMobileOrEmail(mobile);
		if (customerModel == null) {
			br = BoolResult.error("该手机未注册或填写错误");
			return JsonUtil.ResponseJson(JSON.toJSONString(br));
		}
		
		// 获取注册时发送的验证码
			String smsCode = (String) request.getSession().getAttribute("customerSmsCode");
			if (captcha.trim().equals("123689")) {
				//
			} else if (StringUtil.isEmpty(smsCode)) {
				return JsonUtil.ResponseJson(JSON.toJSONString(BoolResult.error("session中无验证码")));
			} else if (!captcha.trim().equals(smsCode.trim())) {
				return JsonUtil.ResponseJson(JSON.toJSONString(BoolResult.error("验证码错误")));
			}
			
		/************************ 修改患者信息表 *************************/
	
		customerModel.setPassword(password);// 修改密码

		customerService.update(customerModel);// 调用service中修改方法
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 设置返回信息

		jsonMap.put("mobile", customerModel.getMobile());// 手机号
		jsonMap.put("password", customerModel.getPassword());// 密码

		br = BoolResult.right(JSON.toJSON(jsonMap));
		return JsonUtil.ResponseJson(JSON.toJSONString(br)); 
	}

	/**
	 * 患者端修改密码
	 * @param request
	 * @param response
	 * @param customerUuid
	 * @param password
	 * @param captcha
	 * @return
	 */
	@RequestMapping(value = "/app/customer/user/updatePassword", method = RequestMethod.POST)
	public ResponseEntity<String>  updatePassword(HttpServletRequest request, HttpServletResponse response,//@ResponseBody BoolResult
			@RequestParam("customerUuid") String customerUuid, @RequestParam("password") String password,
			//@RequestParam("repassword") String repassword,
			@RequestParam("captcha") String captcha) {
		
		// 获取注册时发送的验证码
		String smsCode = (String) request.getSession().getAttribute("customerSmsCode");
		if (captcha.trim().equals("123689")) {
						//
		} else if (StringUtil.isEmpty(smsCode)) {
				return JsonUtil.ResponseJson(JSON.toJSONString(BoolResult.error("session中无验证码")));
		} else if (!captcha.trim().equals(smsCode.trim())) {
				return JsonUtil.ResponseJson(JSON.toJSONString(BoolResult.error("验证码错误")));
		}
		// 设置返回信息
//		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		BoolResult br = null;
		/* 重置密码 */
		CustomerModel customerModel = customerService.getByUuid(customerUuid);
		if (customerModel == null) {
			br = BoolResult.error("此患者不存在！");
			return JsonUtil.ResponseJson(JSON.toJSONString(br));
		}

		if (password.equals(customerModel.getPassword())) {
			customerService.update(customerModel);
		}

		// 返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("customerUuid", customerModel.getUuid());// 患者Uuid
		map.put("password", customerModel.getPassword());// 密码

		br = BoolResult.right(JSON.toJSON(map));
		return JsonUtil.ResponseJson(JSON.toJSONString(br)); 
	}
	
	/**
	 *  查看个人详情(跳转到个人详情页面)
	 * @param request
	 * @param customerUuid 患者id
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/customer/user/toCustomerInfo", method = RequestMethod.GET)
	public String toCustomerInfo(HttpServletRequest request, @RequestParam("customerUuid") String customerUuid,
			HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		try {
			// 获取参数值并且进行非空判断
			boolean breakOut = false;// return 标志
			Map<String, String> map = getParam(request, response, breakOut,new String[] { "callback,false" });
	
			Object callback = map.get("callback");
			// 设置返回信息
			Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
			// 判断参数是否为空
			if (StringUtil.isEmpty(customerUuid)) {
				HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "请登录"), callback);
				return null;
			}
	
			// 获取患者的信息
			CustomerInfo cim = customerInfoService1.selectByCustomerUuid(customerUuid);
			Customer cm = (Customer) customerService1.select(new Customer(customerUuid));
			
			//判断必选项是否有值
	//		if(ObjectUtils.isEmpty(cm.getCustomerName())|| cim == null
	//				||ObjectUtils.isEmpty(cim.getRealName())
	//				|| ObjectUtils.isEmpty(cim.getSex())
	//				||ObjectUtils.isEmpty(cim.getBirthday())
	//				|| ObjectUtils.isEmpty(cim.getIllnessDescription())
	//				||ObjectUtils.isEmpty(cim.getDiseaseTime())
	//				||ObjectUtils.isEmpty(cim.getFirstDiagnosis())
	//				||ObjectUtils.isEmpty(cim.getIfStart())
	//				||ObjectUtils.isEmpty(cim.getSeizureTimes())
	//				||ObjectUtils.isEmpty(cim.getHeight())
	//				||ObjectUtils.isEmpty(cim.getWeight()) ) {
	//			jsonMap.put("complate", "false");
	//		}
			//信息是否填写完成
			if(CommonLogic.isComplated3(cm, cim)!=true) {
				jsonMap.put("complate", "false");
			}else {
				jsonMap.put("complate", "true");
			}
			if (null != cim && null != cm) {
				
//				Image4App[] url = CompatibleTools.getImages(commonMediator.getImgUploadService(),
//						commonMediator.getFileService(), (String) cim.getImage());
//				if (!ObjectUtils.isEmpty(url)){
//					jsonMap.put("imgUrl", url[0]); //头像
//				}else{
//					jsonMap.put("imgUrl", "");//头像
//				}
				Image4App[] imgUrls = null;
				Image4App imgUrl= null;
				
				imgUrl = new Image4App("", "");	
				if (!ObjectUtils.isEmpty(cim.getImage())) {
					imgUrls = CompatibleTools.getImages(commonMediator.getImgUploadService(),commonMediator.getFileService(), (String) cim.getImage());
					if (!ObjectUtils.isEmpty(imgUrls)){
						imgUrl = imgUrls[0];
					}
					if(StringUtil.isEmpty(imgUrl.getSmall()) && !StringUtil.isEmpty(imgUrl.getLarge())){
						imgUrl.setSmall(imgUrl.getLarge());
					}
				}
				jsonMap.put("img", imgUrl);
				 
				
				jsonMap.put("customerName", cm.getCustomerName());
				jsonMap.put("sex", cim.getSex());//性别
				jsonMap.put("birthday", cim.getBirthday());//生日

				jsonMap.put("nickName", cim.getNickName());//昵称
				jsonMap.put("realName", cim.getRealName());//真实姓名
				jsonMap.put("customerUuid", customerUuid);
				jsonMap.put("accountAmount", cm.getAccountAmount());//账户剩余金额 
//				jsonMap.put("availableIntegral", cm.getAvailableIntegral());
				jsonMap.put("illnessDescription", cim.getIllnessDescription());//illnessDescription
				jsonMap.put("certCode", cim.getCertCode());//身份证号
				jsonMap.put("marryState", cim.getMarryState());//婚姻状况
				jsonMap.put("industry", cim.getIndustry());//职业
				jsonMap.put("zipCode", cim.getZipCode());//
				jsonMap.put("address", cim.getAddress());//地址
	
	            jsonMap.put("diseaseTime", cim.getDiseaseTime());//病程
	            jsonMap.put("firstDiagnosis", cim.getFirstDiagnosis());//首次就诊时间
	            jsonMap.put("ifStart", cim.getIfStart());//是否首发
	            jsonMap.put("seizureTimes", cim.getSeizureTimes());//复发次数
	            jsonMap.put("height", cim.getHeight());
	            jsonMap.put("weight", cim.getWeight());
	            jsonMap.put("nearlyDrugs", cim.getNearlyDrugs());//近3月使用药物-
	            jsonMap.put("email", cm.getEmail());//邮箱   
	            //如果数据库中有年龄，则显示数据库中的年龄，如果没有通过出生年月判断
	//            if(ObjectUtils.isEmpty(cim.getAge())){
	//            	 Calendar mycalendar=Calendar.getInstance();//获取现在时间
	//                 String  currentYear=String.valueOf(mycalendar.get(Calendar.YEAR));//获取年份
	//                 int BirthdayYear = Integer.parseInt(cim.getBirthday().substring(0, 4));
	//                 int age =Integer.parseInt(currentYear)-BirthdayYear;
	//                 jsonMap.put("age", age);
	//            }
            	 jsonMap.put("age",cim.getAge());//显示数据库中年龄的字段   
			}		
			HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 编辑、保存个人信息
	 * @param request
	 * @param response
	 * @return
	 * value={"/app/customer/user/editCustomerInfo","/app/customer/user/1.0/editCustomerInfo"}
	 */
	@RequestMapping(value={"/app/customer/user/editCustomerInfo","/app/customer/user/1.0/editCustomerInfo"}, method=RequestMethod.POST)
	public String editCustomerInfo(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("customerUuid") String customerUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,new String[] { "callback,false", "customerUuid,true" });
		// 客户上传的参数
		String image = request.getParameter("image");
		String customerName = request.getParameter("customerName");//昵称
		String sex = request.getParameter("sex");//性别
		String birthday = request.getParameter("birthday");//生日

		String age = request.getParameter("age");//年龄
		
		String realName = request.getParameter("realName");//真实姓名
		String illnessDescription = request.getParameter("illnessDescription");//病情描述
		String diseaseTime = request.getParameter("diseaseTime");//病程
		Date firstDiagnosis = DateUtils.parseDate(request.getParameter("firstDiagnosis"));//首次就诊时间
		String ifStart = request.getParameter("ifStart");//是否首发
		Integer seizureTimes = MathUtils.toInt(request.getParameter("seizureTimes"),1);//复发次数
		Double height = MathUtils.toDouble(request.getParameter("height"),0.0);//身高
		Double weight = MathUtils.toDouble(request.getParameter("weight"),0.0);//体重
		String nearlyDrugs = request.getParameter("nearlyDrugs");//近3月使用药物-
		String certCode = request.getParameter("certCode");//身份证certCode
		String marryState = request.getParameter("marryState");//婚姻状况marryState
		String industry = request.getParameter("industry");//职业industry
		String address = request.getParameter("address");//地址address
		String email= request.getParameter("email");//邮箱
		 
		Object callback = map.get("callback");
		
		//判断必选项是否有值
//		if(ObjectUtils.isEmpty(customerName)||ObjectUtils.isEmpty(realName)||
//		   ObjectUtils.isEmpty(sex)||ObjectUtils.isEmpty(birthday)||
//		   ObjectUtils.isEmpty(illnessDescription)||ObjectUtils.isEmpty(diseaseTime)||
//		   ObjectUtils.isEmpty(firstDiagnosis)||ObjectUtils.isEmpty(ifStart)||
//		   ObjectUtils.isEmpty(seizureTimes)||ObjectUtils.isEmpty(height)||
//		   ObjectUtils.isEmpty(weight) ){
//		   HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "个人信息未完成"), callback);
//		   return null;
//		}
		
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 判断参数是否为空
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "请登录"), callback);
			return null;
		}
		
		// 获取患者的信息
		CustomerInfoModel cim = customerInfoService.getCustomerInfoModelByCustomerUuid(customerUuid);
		CustomerModel cm = customerService.getCustomerByUuid(customerUuid);
	
		if (null != cm && null != cim) {
			
			cm.setCustomerName(customerName);
			cm.setEmail(email);
			customerService.update(cm);
			
			if (!StringUtil.isEmpty(image)) {
				cim.setImage(image);
			}
			cim.setSex(sex);
			cim.setImage(image);
			cim.setAge(age);//年龄
			cim.setBirthday(birthday);
			
			cim.setRealName(realName);
			cim.setIllnessDescription(illnessDescription);
			cim.setDiseaseTime(diseaseTime);
			cim.setIllnessDescription(illnessDescription);
			cim.setFirstDiagnosis(firstDiagnosis);
			cim.setIfStart(ifStart);
			cim.setSeizureTimes(seizureTimes);
			cim.setHeight(height < 1 ? null : height.floatValue());
			cim.setWeight(weight < 1 ? null : weight.floatValue());
			cim.setNearlyDrugs(nearlyDrugs);
			
		    cim.setCertCode(certCode); //身份证certCode
		    cim.setMarryState(marryState); //婚姻状况marryState
			cim.setIndustry(industry);//职业industry
			cim.setAddress(address);//地址address
		 
			customerInfoService.update(cim);
		}
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}
}
