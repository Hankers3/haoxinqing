package com.hxq.mobile.patient.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.customermgr.mobile.web.service.user.AppUserService;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.IntegralType;
import com.alibaba.fastjson.JSON;
import com.hxq.mobile.common.service.CustomerInfoService;
import com.hxq.mobile.common.service.CustomerService;
import com.hxq.mobile.common.service.ServiceStaffInfoService;
import com.hxq.mobile.common.service.ServiceStaffService;
import com.hxq.mobile.doctor.common.service.CustomerAdviceService;
import com.hxq.mobile.entity.common.Customer;
import com.hxq.mobile.entity.common.CustomerAdvice;
import com.hxq.mobile.entity.common.CustomerInfo;
import com.hxq.mobile.entity.common.DepartmentInfo;
import com.hxq.mobile.entity.common.HospitalInfo;
import com.hxq.mobile.entity.common.VipclubIntegralLog;
import com.hxq.mobile.patient.CommonLogic;
import com.hxq.mobile.patient.content.service.ContentService;
import com.hxq.mobile.support.service.ImgUploadService;
import com.hxq.mobile.util.CompatibleTools;
import com.hxq.mobile.util.Image4App;
import com.hxq.mobile.util.api.ApiCode;
import com.hxq.mobile.util.api.ApiResult;
import com.hxq.mobile.vipclub.service.VipclubIntegralLogService;
import com.wxcommon.util.IdentityHelper;
import com.wxcommon.util.ObjectUtils;


/**
 * 患者登录，注册， 验证码，今日推荐， 名医指导，修改密码... 
 *
 */
@Controller("com.hxq.mobile.patient.common.controller.PatientController2")
public class PatientController2 {
	Logger log = LoggerFactory.getLogger(PatientController2.class);

	@Autowired
	private FileService fileService;
	/* 会员账户信息service */
	@Resource(name = "com.hxq.mobile.common.service.CustomerService")
	private CustomerService customerService;

	/*患者详细信息service*/
	@Resource(name = "com.hxq.mobile.common.service.CustomerInfoService")
	private CustomerInfoService customerInfoService;

	/* 医生信息service */
	@Resource(name = "com.hxq.mobile.common.service.ServiceStaffService")
	private ServiceStaffService servicestaffService;

	/* 医生基础信息service */
	@Resource(name = "com.hxq.mobile.common.service.ServiceStaffInfoService")
	private ServiceStaffInfoService servicestaffInfoService;
	
	/* 资讯的Service*/
	@Resource(name = "com.hxq.mobile.patient.content.service.ContentService")
	private ContentService contentService;

	/*图片service*/
	@Resource(name = "com.hxq.mobile.support.service.ImgUploadService")
	private ImgUploadService imgService;
	/*用户积分日志表service*/
	@Resource(name = "com.hxq.mobile.vipclub.service.VipclubIntegralLogService")
	VipclubIntegralLogService vipclubIntegralLogService;
	
	@Resource(name = "com.hxq.mobile.doctor.common.service.CustomerAdviceService")
	private CustomerAdviceService customerAdviceService;
	/**
	 * 注册和忘记密码时需要获取的验证码1.0
	 */
	@RequestMapping(value = "/app/customer/user/1.0/getVerificationCode", method = RequestMethod.GET)
	public  @ResponseBody ApiResult getVerificationCode1(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("mobile") String mobile) {
		try {
			// 生成6位手机验证码
			String code = AppUserService.getActivatingKey(6);
			// 发验证码到手机
			AppUserService.sendRegCodeMobile(mobile, code);
			// 保存验证码
			request.getSession().setAttribute("customerSmsCode", code);
			return ApiResult.right();
		} catch (ServiceException e) {
			log.error("", e);
			return ApiResult.error(ApiCode.SERVER_ERROR,"获取验证码异常！");
		}
	}
	
	/**
	 * 修改密码时获得的验证码1.0
	 * 必传mobile
	 */
	@RequestMapping(value = "/app/customer/user/1.0/getForgetPassword", method = RequestMethod.GET)
	public  @ResponseBody ApiResult  getForgetPassword1(HttpServletRequest request,
			@RequestParam("mobile") String mobile) {
		try {
			Customer customerModel = customerService.selectCustomerByMobile(mobile);
			
			if (customerModel ==null) { 
				return ApiResult.error(ApiCode.BAD_REQUEST,"手机号未注册");
			}else{
				String regState = customerModel.getRegState();
				if(!"1".equals(regState)){
					return ApiResult.error(ApiCode.BAD_REQUEST,"手机号未注册");
				}
			}
			String code = AppUserService.getActivatingKey(6);// 生成6位手机验证码
			// 发验证码到手机
			AppUserService.sendRegCodeMobile(mobile, code);
			request.getSession().setAttribute("customerSmsCode", code);
			return ApiResult.right();
		} catch (Exception e) {
			log.error("", e);
			return ApiResult.error(ApiCode.SERVER_ERROR,"获取验证码异常！");
		}
	}

    /**
     * 患者登录1.0
     * 必传：手机号 密码 
     */
	@RequestMapping(value = "/app/customer/user/1.0/gotoLogin", method = RequestMethod.POST)
	public @ResponseBody ApiResult  gotoLogin1(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("mobile") String mobile, @RequestParam("password") String password) {
		String customerUuid;
		Customer  customer;
		try {
			customer = customerService.select4CheckMobileAndPassword(mobile, password);//通过手机号 密码查询患者信息
			//判断用户名和密码
			if (customer == null) {
				return ApiResult.error(ApiCode.BAD_REQUEST,"用户手机号不存在或密码不正确。");
			}
			// 判断该患者是否被冻结
			if (!CustomerModel.CUSTOMER_FROZENSTATE_UNFROZEN.equals(customer.getFrozenState())) {
				return ApiResult.error(ApiCode.BAD_REQUEST,"对不起，这个患者已被冻结，无法登陆！");
			}
			String regState = customer.getRegState(); //注册状态 ，默认为0，0：未成功，1：成功 
			// 判断该医生是否被冻结
			if ("0".equals(regState)) {
				return ApiResult.error(ApiCode.BAD_REQUEST,"对不起，患者未注册成功，无法登陆！");
			}
			/* ???????--登陆加积分--??????? */ 
			customerUuid= customer.getUuid();//患者uuid
			// 判断今天是否已经增加积分，已经增加就不在增加          //1 代表 每天登陆操作:GET_BY_ORDER    患者类型:VIPCLUB_USERTYPE_CUS
			 VipclubIntegralLog vipclubIntegralLog = vipclubIntegralLogService.selectVipclubIntegralLogByConditions(
					customerUuid, VipclubIntegralLog.VIPCLUB_USERTYPE_CUS, IntegralType.GET_BY_ORDER.getValue(), "");
			 if (vipclubIntegralLog == null) {
					// 添加积分日志
					try {
						vipclubIntegralLogService.insertVipIntegralLog(customerUuid, "add", 1, IntegralType.GET_BY_ORDER.getValue(),
								VipclubIntegralLog.VIPCLUB_USERTYPE_CUS, "每天启动软件获得积分", "");
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			customerUuid = customer.getUuid();// 返回患者Uuid
	
			//判断患者基本信息是否完善
		    customer = (Customer) customerService.select(new Customer(customerUuid));
			//查看个人信息是否添加完成
			CustomerInfo cim = customerInfoService.selectByCustomerUuid(customerUuid);
			if(CommonLogic.isComplated3(customer, cim)!=true) {
				jsonMap.put("complate", "false");
			}else {
				jsonMap.put("complate", "true");
			}
			
			jsonMap.put("customerUuid", customerUuid);
			jsonMap.put("regState", regState);// 注册状态 ，默认为0，0：未成功，1：成功 
			return ApiResult.right(JSON.toJSON(jsonMap));
		} catch (Exception e) {
			log.error("", e);
			return ApiResult.error(ApiCode.SERVER_ERROR,"网络异常，请重新登录");
		}
	}
	
	/**
	 * 注册1.0
	 * 患者注册，填写手机号，密码，验证码，昵称（可不填）  返回uuid和手机号
	 * 后门码：123689
	 */
	@RequestMapping(value = "/app/customer/user/1.0/registerOne", method = RequestMethod.POST)
	public @ResponseBody ApiResult register1(HttpServletRequest request, HttpServletResponse response,
		   @RequestParam("mobile") String mobile, 
		   @RequestParam("captcha") String captcha,
		   @RequestParam("password") String password,
		   @RequestParam(value = "customerName",required=false) String customerName) {
		try {
			
			String regState;// 注册状态 判断用
			
			Customer customer = customerService.selectCustomerByMobile(mobile);//通过用户名或者手机号或者邮箱查询该会员信息
			if (customer != null) {
				// 判断注册状态，如果为0表示注册未成功，1表示成功返回手机已注册信息
				regState = customer.getRegState();
				if ("1".equalsIgnoreCase(regState)) {
					return  ApiResult.error(ApiCode.BAD_REQUEST, "手机已注册");
				}
			}
			// 获取注册时发送的验证码
			String smsCode = (String) request.getSession().getAttribute("customerSmsCode");
			if (captcha.trim().equals("123689")) {//后门码
				
			} else if (StringUtil.isEmpty(smsCode)) {
				return ApiResult.error(ApiCode.BAD_REQUEST,"session中无验证码");
			} else if (!captcha.trim().equals(smsCode.trim())) {
				return ApiResult.error(ApiCode.BAD_REQUEST,"验证码错误");
			}
	
			/************************ 创建患者信息表 *************************/
			if (customer != null) {
				customer.setMobile(mobile);  //手机号码
				customer.setPassword(password);  //密码
				customer.setCustomerName(customerName);  //昵称
				customer.setRegState("1");  //注册状态
				customerService.update(customer);
			} else {
				customer = new Customer(IdentityHelper.uuid2());
				customer.setMobile(mobile);  //手机号码
				customer.setPassword(password);  //密码
				customer.setCustomerName(ObjectUtils.isEmpty(customerName) ? mobile : customerName);  //昵称
				customer.setCreateTime(DateFormatHelper.getNowTimeStr());  //创建时间
				customer.setFrozenState("0");  //冻结状态
				customer.setRegState("1");  //注册状态
				customerService.insert(customer);  //创建

				/************************ 创建患者基本信息表 *************************/
				CustomerInfo customerInfo = new CustomerInfo();
				customerInfo.setCustomerUuid(customer.getUuid());
				customerInfoService.insert(customerInfo);// 创建
/*				String username = "patient"+customer.getUuid();//环信ID
				JsonTool.write(talkDataService.userSave(username,customer.getUuid(),customer.getCustomerName()));*/
			}
			// 返回信息
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("customerUuid", customer.getUuid());// 患者Uuid
			return ApiResult.right(map);
		} catch (Exception e) {
			log.error(mobile, e);
			return ApiResult.error(ApiCode.SERVER_ERROR,"注册异常！");
		}
	}
	
	/**
	 * 忘记密码1.0
	 */
	@RequestMapping(value = "/app/customer/user/1.0/retrievePassword", method = RequestMethod.POST)
	public   @ResponseBody ApiResult retrievePassword1(HttpServletRequest request, HttpServletResponse response,
			 @RequestParam("mobile") String mobile, 
			 @RequestParam("password") String password,
			 @RequestParam("captcha") String captcha) {
		

		try {
			//通过手机号查询患者信息
			Customer customerModel = customerService.selectCustomerByMobile(mobile);
			if (customerModel == null) {
				return ApiResult.error(ApiCode.BAD_REQUEST,"该手机未注册或填写错误");
			}
			// 获取注册时发送的验证码
			String smsCode = (String) request.getSession().getAttribute("customerSmsCode");
			if (captcha.trim().equals("123689")) {
				//
			} else if (StringUtil.isEmpty(smsCode)) {
				return ApiResult.error(ApiCode.BAD_REQUEST,"session中无验证码");
			} else if (!captcha.trim().equals(smsCode.trim())) {
				return ApiResult.error(ApiCode.BAD_REQUEST,"验证码错误");
			}

			/************************ 修改患者信息表 *************************/
			// 修改密码
			customerModel.setPassword(password);
			// 调用service中修改方法
			customerService.update(customerModel);
			
			Map<String, Object> jsonMap = new HashMap<String, Object>();// 设置返回信息
			
			String customerUuid = customerModel.getUuid();

//			jsonMap.put("mobile", customerModel.getMobile());// 手机号
//			jsonMap.put("password", customerModel.getPassword());// 密码
			jsonMap.put("customerUuid", customerUuid);//返回患者id
			jsonMap.put("regState", customerModel.getRegState());// 注册状态 ，默认为0，0：未成功，1：成功 
//			jsonMap.put("customerName", customerModel.getCustomerName());//昵称

			return ApiResult.right(JSON.toJSON(jsonMap)); 
			} catch (Exception e) {	
				return  ApiResult.error(ApiCode.SERVER_ERROR, "请稍后再试！");
			}	
	}
	/**
	 *判断账号是否冻结 
	 */
	@RequestMapping(value = "/app/customer/user/1.0/FrozenType", method = RequestMethod.GET)
	public   @ResponseBody ApiResult frozenType(HttpServletRequest request, @RequestParam("mobile") String mobile) {
		if (ObjectUtils.isEmpty(mobile)) {
			return ApiResult.error(ApiCode.BAD_REQUEST, "手机号不能为空！");
		}
		try {
			//通过手机号查询患者信息
			Customer customerModel = customerService.selectCustomerByMobile(mobile);
			String frozenState = customerModel.getFrozenState();
			return ApiResult.right(frozenState);//0未冻结，1 已冻结
		} catch (Exception e) {
			return ApiResult.error(ApiCode.SERVER_ERROR,"请稍后再试！");
		}
	}
	
	
	/**
	 * 患者端修改密码1.0
	 */
	@RequestMapping(value = "/app/customer/user/1.0/updatePassword", method = RequestMethod.POST)
	public @ResponseBody ApiResult  updatePassword1(HttpServletRequest request, HttpServletResponse response,
		   @RequestParam("customerUuid") String customerUuid, @RequestParam("password") String password,
			//@RequestParam("repassword") String repassword,(不需要)
		   @RequestParam("captcha") String captcha) {
		try {
			// 获取注册时发送的验证码
			String smsCode = (String) request.getSession().getAttribute("customerSmsCode");
			if (captcha.trim().equals("123689")) {
			//
			} else if (StringUtil.isEmpty(smsCode)) {
					return ApiResult.error(ApiCode.BAD_REQUEST,"session中无验证码");
			} else if (!captcha.trim().equals(smsCode.trim())) {
					return ApiResult.error(ApiCode.BAD_REQUEST,"验证码错误");
			}

			//通过患者id 查询信息
			Customer customerModel = (Customer) customerService.select(new Customer(customerUuid));
			if (customerModel == null) {	
				return  ApiResult.error(ApiCode.BAD_REQUEST,"此患者不存在！");
			}
			if (!password.equals(customerModel.getPassword())) {
				customerModel.setPassword(password);
				customerService.update(customerModel);
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("customerUuid", customerModel.getUuid());// 患者Uuid
			map.put("password", customerModel.getPassword());// 密码
			
			return ApiResult.right(JSON.toJSON(map));
			} catch (Exception e) {
				return ApiResult.error(ApiCode.SERVER_ERROR, "请稍后再试！"); 
			}	
	}

	/**
	 * 获取今日推荐1.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/customer/patient/1.0/getTodayContent", method = RequestMethod.GET)
	public @ResponseBody ApiResult getTodayContent1(HttpServletRequest request, HttpServletResponse response) {
		
		Image4App[] imgUrls = null;
		// 获取今日推荐第一条并将今日推荐返回给患者
		List<Map<String,Object>> list = contentService.selectAllContentList("");
		List<Map<String, Object>> relist = new ArrayList<>();
		if (list != null && list.size() > 0) {
			Map<String,Object> cm =  list.get(0);//获得list中第一条数据
			if (cm != null) {
				Map<String, Object> save = new HashMap<>();
				
				save.put("contentUuid", cm.get("uuid"));// 今日推荐的Uuid
				save.put("contentType", "0");
				save.put("contentTitle", cm.get("contentTitle"));// 今日推荐的标题
				save.put("contentNote", cm.get("contentNote"));// 描述
				// 今日推荐图片
				if (ObjectUtils.isEmpty(cm.get("image"))!=true) {
					imgUrls = CompatibleTools.getImages(imgService, fileService, (String) cm.get("image"));
					if(!ObjectUtils.isEmpty(imgUrls)) save.put("img",  imgUrls[0]);
				}
				save.put("url", cm.get("url"));// 今日推荐的超级链接
				relist.add(save);
			}
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("relist", relist);// 消息字段
		return  ApiResult.right(JSON.toJSON(jsonMap));
	}
	
	/**
	 * 名医风采(患者端首页获取名医风采 1.0)
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/app/customer/patient/1.0/getFamousDoctors", method = RequestMethod.GET)
	public @ResponseBody ApiResult getFamousDoctors1() {
		// 获得名医List
		List<Map<String,Object>> rows = servicestaffService.selectFamousDoctors();
		if (ObjectUtils.isEmpty(rows)) {
			return ApiResult.right();
		}
		// 返回list
		Image4App[] imgUrls = null;
		Map<String, Object> reMap = null;
		List<Map<String, Object>> reList = new ArrayList<>(rows.size());
		for (Map<String, Object> map : rows) {
			reMap = new HashMap<>();
			reMap.put("doctorName", map.get("realName"));// 医生名
			reMap.put("doctorUuid", map.get("uuid"));// 医生uuid
			
			if(!ObjectUtils.isEmpty(map.get("image"))) {
				imgUrls = CompatibleTools.getImages(imgService, fileService, (String) map.get("image"));
				if(!ObjectUtils.isEmpty(imgUrls)) {
					reMap.put("img",  imgUrls[0]);
				}
			}

			try {// 执业医院
				HospitalInfo hi = (HospitalInfo) servicestaffService.select(new HospitalInfo((String) map.get("hospital")));
				reMap.put("hospitalName", hi != null ? hi.getHospitalName() : map.get("hospital"));
			} catch (Exception e) {
				log.error("", e);
				reMap.put("hospitalName", map.get("hospital"));
			}
			try {// 执业科室
				DepartmentInfo di = (DepartmentInfo) servicestaffService.select(new DepartmentInfo((String) map.get("department")));
				reMap.put("departmentName", di != null ? di.getDepartmentName() : map.get("department"));
			} catch (Exception e) {
				log.error("", e);
				reMap.put("departmentName", map.get("department"));
			}

			reMap.put("territory", map.get("territory"));// 医生擅长
			reMap.put("professional", map.get("professional"));// 医生职称
			reList.add(reMap);
		}
		return ApiResult.right(reList);		
	}
	/**
	 * 编辑保存个人信息接口（没有callback）
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/app/customer/user/2.0/editCustomerInfo", method=RequestMethod.POST)
	public @ResponseBody ApiResult editCustomerInfo(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("customerUuid") String customerUuid) {
		
		// 客户上传的参数
		String image = request.getParameter("image");
		String customerName = request.getParameter("customerName");//昵称
		String sex = request.getParameter("sex");//性别
		String birthday = request.getParameter("birthday");//出生年月
		try {
			
			if (StringUtil.isEmpty(customerUuid)) {
				return ApiResult.error(ApiCode.BAD_REQUEST, "请登录");
			}
			
			// 获取患者的信息
			CustomerInfo cim = customerInfoService.selectByCustomerUuid(customerUuid);
			Customer cm = (Customer)customerService.select(new Customer(customerUuid));//根据主键id查询患者信息
		
			if (null != cm && null != cim) {
				cm.setCustomerName(customerName);
				customerService.update(cm);
				
				if (!StringUtil.isEmpty(image)) {
					cim.setImage(image);
				}
				cim.setSex(sex);
				cim.setImage(image);
				cim.setBirthday(birthday);
				customerInfoService.update(cim);
			}
			return ApiResult.right();
		} catch (Exception e) {
			return ApiResult.error(ApiCode.SERVER_ERROR, "请稍后再试!"); 
		}
	}
	

	//编辑保存个人信息接口（没有callback）

	/**
	 *  查看个人详情(跳转到个人详情页面)(没有callback)
	 * @param request
	 * @param customerUuid 患者id
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/customer/user/2.0/toCustomerInfo", method = RequestMethod.GET)
	public @ResponseBody ApiResult toCustomerInfo(HttpServletRequest request, HttpServletResponse response,
		   @RequestParam("customerUuid") String customerUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		try {
			Image4App[] imgUrls = null;
			Image4App imgUrl = null;
			// 判断参数是否为空
			if (StringUtil.isEmpty(customerUuid)) {
				return ApiResult.error(ApiCode.BAD_REQUEST, "请登录"); 
			}
			
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			
			// 获取患者的信息
			CustomerInfo cim = customerInfoService.selectByCustomerUuid(customerUuid);
			Customer cm = (Customer) customerService.select(new Customer(customerUuid));
			
			//信息是否填写完成
			if(CommonLogic.isComplated3(cm, cim)!=true) {
				jsonMap.put("complate", "false");
			}else {
				jsonMap.put("complate", "true");
			}
			if (null != cim && null != cm) {
				//jsonMap.put("imgUrl", cim.getImage());//头像
				imgUrl = new Image4App("", "");	
				if (!ObjectUtils.isEmpty(cim.getImage())) {
					imgUrls = CompatibleTools.getImages(imgService, fileService, (String) cim.getImage());
					if (!ObjectUtils.isEmpty(imgUrls)){
						imgUrl = imgUrls[0];
					}
					
					if(StringUtil.isEmpty(imgUrl.getSmall()) && !StringUtil.isEmpty(imgUrl.getLarge())){
						imgUrl.setSmall(imgUrl.getLarge());
					}
				}
				jsonMap.put("imgUrl", imgUrl);
				
				jsonMap.put("customerName", cm.getCustomerName());//昵称
				jsonMap.put("sex", cim.getSex());//性别
				jsonMap.put("customerUuid", customerUuid);
				jsonMap.put("birthday", cim.getBirthday());//生日
				
	            //如果数据库中有年龄，则显示数据库中的年龄，如果没有通过出生年月判断
	//            if(ObjectUtils.isEmpty(cim.getAge())){
	//            	 Calendar mycalendar=Calendar.getInstance();//获取现在时间
	//                 String  currentYear=String.valueOf(mycalendar.get(Calendar.YEAR));//获取年份
	//                 int BirthdayYear = Integer.parseInt(cim.getBirthday().substring(0, 4));
	//                 int age =Integer.parseInt(currentYear)-BirthdayYear;
	//                 jsonMap.put("age", age);
	//            }
			}		
			return ApiResult.right(jsonMap);
		}catch (Exception e) {
			e.printStackTrace();
			return ApiResult.error(ApiCode.SERVER_ERROR, "请稍后再试！");
		}
	}
	
	//意见反馈
	/**
	 * 
	 * @param request
	 * @param response
	 * @param customerUuid患者id
	 * @param adviceContent意见内容
	 * @return
	 */
	@RequestMapping(value = "/app/customer/patient/1.0/addCustomerAdvice", method = RequestMethod.POST)
	public @ResponseBody ApiResult addCustomerAdvice(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("customerUuid") String customerUuid,
			@RequestParam("adviceContent") String adviceContent) {
		try {
			
			// 判断参数是否为空
			if (StringUtil.isEmpty(customerUuid)) {
				return ApiResult.error(ApiCode.BAD_REQUEST, "请登录");
			}
	
			/************************ 添加意见建议信息 *************************/
			CustomerAdvice fm = new CustomerAdvice();
			fm.setCustomerUuid(customerUuid);
			fm.setAdviceContent(adviceContent);
			fm.setCreateTime(DateFormatHelper.getNowTimeStr());
			fm.setType("2");// 2代表是患者
			fm.setStatus("0");// 0代表是未处理
			customerAdviceService.insert(fm);
			return ApiResult.right();
			
		} catch (Exception e) {
			return ApiResult.error(ApiCode.SERVER_ERROR, "请稍后再试！");
		}
	}
}
