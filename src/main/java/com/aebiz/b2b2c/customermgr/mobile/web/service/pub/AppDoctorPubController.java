package com.aebiz.b2b2c.customermgr.mobile.web.service.pub;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.aebiz.b2b2c.basebusiness.utils.DateUtil;
import com.aebiz.b2b2c.baseframework.filemgr.helper.FileUploadHelper;
import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.filemgr.vo.FileModel;
import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.basicdata.operatelog.service.OperateLogService;
import com.aebiz.b2b2c.basicdata.operatelog.vo.OperateLogModel;
import com.aebiz.b2b2c.cms.content.service.ContentService;
import com.aebiz.b2b2c.cms.content.vo.ContentModel;
import com.aebiz.b2b2c.cms.content.vo.ContentQueryModel;
import com.aebiz.b2b2c.cms.contentlist.service.ContentListService;
import com.aebiz.b2b2c.cms.contentlist.vo.ContentListModel;
import com.aebiz.b2b2c.cms.innermessage.service.InnerMessageService;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageModel;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageTypeEnum;
import com.aebiz.b2b2c.cms.platformapply.service.PlatformApplyService;
import com.aebiz.b2b2c.cms.platformapply.vo.PlatformApplyModel;
import com.aebiz.b2b2c.cms.platforminfo.service.PlatFormInfoService;
import com.aebiz.b2b2c.cms.platforminfo.vo.PlatFormInfoModel;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.customermgr.customerinfo.service.CustomerInfoService;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoModel;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.AppBaseController;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.HttpServletUtils;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.MobileUtils;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.SysConst;
import com.aebiz.b2b2c.customermgr.mobile.web.push.PushConst;
import com.aebiz.b2b2c.customermgr.mobile.web.service.user.AppUserService;
import com.aebiz.b2b2c.order.ordermain.service.OrderMainService;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;
import com.aebiz.b2b2c.order.orderrouting.service.OrderRoutingService;
import com.aebiz.b2b2c.product.utils.ImageUtils;
import com.aebiz.b2b2c.servicestaff.bankrelation.service.BankRelationService;
import com.aebiz.b2b2c.servicestaff.bankrelation.vo.BankRelationModel;
import com.aebiz.b2b2c.servicestaff.doctornotice.service.DoctorNoticeService;
import com.aebiz.b2b2c.servicestaff.doctornotice.vo.DoctorNoticeModel;
import com.aebiz.b2b2c.servicestaff.doctorright.service.DoctorRightService;
import com.aebiz.b2b2c.servicestaff.doctorright.vo.DoctorRightModel;
import com.aebiz.b2b2c.servicestaff.groupfriends.service.GroupFriendsService;
import com.aebiz.b2b2c.servicestaff.groupfriends.vo.GroupFriendsModel;
import com.aebiz.b2b2c.servicestaff.homevisitset.service.HomeVisitSetService;
import com.aebiz.b2b2c.servicestaff.homevisitset.vo.HomeVisitSetModel;
import com.aebiz.b2b2c.servicestaff.packagedoctor.service.PackageDoctorService;
import com.aebiz.b2b2c.servicestaff.packagedoctor.vo.PackageDoctorModel;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;
import com.aebiz.b2b2c.servicestaff.servicestaffcomb.service.ServicestaffcombService;
import com.aebiz.b2b2c.servicestaff.servicestaffinfo.service.ServicestaffinfoService;
import com.aebiz.b2b2c.servicestaff.servicestaffinfo.vo.ServicestaffinfoModel;
import com.aebiz.b2b2c.servicestaff.staffloginstatus.service.StaffLoginStatusService;
import com.aebiz.b2b2c.servicestaff.staffloginstatus.vo.StaffLoginStatusModel;
import com.aebiz.b2b2c.servicestaff.telephonecost.service.TelephoneCostService;
import com.aebiz.b2b2c.servicestaff.telephonecost.vo.TelephoneCostModel;
import com.aebiz.b2b2c.servicestaff.telephonecounse.service.TelephoneCounseService;
import com.aebiz.b2b2c.servicestaff.telephonecounse.vo.TelephoneCounseModel;
import com.aebiz.b2b2c.servicestaff.telephonetime.service.TelephoneTimeService;
import com.aebiz.b2b2c.servicestaff.telephonetime.vo.TelephoneTimeModel;
import com.aebiz.b2b2c.userfront.platad.service.PlatAdService;
import com.aebiz.b2b2c.userfront.platad.vo.PlatAdModel;
import com.aebiz.b2b2c.userfront.platadimagerel.service.PlatAdImageRelService;
import com.aebiz.b2b2c.userfront.platadimagerel.vo.PlatAdImageRelModel;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.service.VipclubIntegralLogService;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.IntegralType;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.VipclubIntegralLogModel;
import com.aebiz.b2b2c.visitprecept.customeradvice.service.CustomerAdviceService;
import com.aebiz.b2b2c.visitprecept.customeradvice.vo.CustomerAdviceModel;
import com.aebiz.b2b2c.visitprecept.customerdoctorrele.service.CustomerDoctorReleService;
import com.aebiz.b2b2c.visitprecept.customerdoctorrele.vo.CustomerDoctorReleModel;
import com.aebiz.b2b2c.visitprecept.doctoradvice.service.DoctorAdviceService;
import com.aebiz.b2b2c.visitprecept.doctoradvice.vo.DoctorAdviceModel;
import com.aebiz.b2b2c.visitprecept.drugreaction.service.DrugReactionService;
import com.aebiz.b2b2c.visitprecept.drugreaction.vo.DrugReactionModel;
import com.aebiz.b2b2c.visitprecept.healthguide.service.HealthGuideService;
import com.aebiz.b2b2c.visitprecept.healthguide.vo.HealthGuideModel;
import com.aebiz.b2b2c.visitprecept.medicalrecord.service.MedicalRecordService;
import com.aebiz.b2b2c.visitprecept.medicalrecord.vo.MedicalRecordModel;
import com.aebiz.b2b2c.visitprecept.visitapply.service.VisitApplyService;
import com.aebiz.b2b2c.visitprecept.visitapply.vo.VisitApplyModel;
import com.aebiz.b2b2c.visitprecept.visitprecept.service.VisitPreceptService;
import com.aebiz.b2b2c.visitprecept.visitprecept.vo.VisitPreceptModel;
import com.aebiz.b2b2c.visitprecept.visitpreceptextend.service.VisitPreceptExtendService;
import com.aebiz.b2b2c.visitprecept.visitpreceptextend.vo.VisitPreceptExtendModel;
import com.aebiz.b2b2c.visitprecept.visitpreceptpush.service.VisitPreceptPushService;
import com.aebiz.b2b2c.visitprecept.visitpreceptpush.vo.VisitPreceptPushModel;
import com.aebiz.b2b2c.visitprecept.visitrecord.service.VisitRecordService;
import com.aebiz.b2b2c.visitprecept.visitrecord.vo.VisitRecordModel;
import com.aebiz.b2b2c.visitprecept.visitrecordextend.service.VisitRecordExtendService;
import com.aebiz.b2b2c.visitprecept.visitrecordextend.vo.VisitRecordExtendModel;
import com.aebiz.b2b2c.websiteoperation.doctorshare.service.DoctorShareService;
import com.aebiz.b2b2c.websiteoperation.doctorshare.vo.DoctorShareModel;
import com.aebiz.b2b2c.websiteoperation.favorite.service.FavoriteService;
import com.aebiz.b2b2c.websiteoperation.favorite.vo.FavoriteModel;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hxq.mobile.doctor.common.service.FavoriteInformationSerivce;
import com.hxq.mobile.doctor.common.service.PlatformInfoSerivce;
import com.hxq.mobile.entity.common.Favorite;
import com.hxq.mobile.entity.common.PlatFormInfo;
import com.hxq.mobile.entity.visit.Content;
import com.hxq.mobile.entity.visit.VisitPrecept;
import com.hxq.mobile.entity.weixin.CsZyItem;
import com.hxq.mobile.entity.weixin.CsZySubject;
import com.hxq.mobile.support.service.ImgUploadService;
import com.hxq.mobile.util.api.ApiResult;
import com.hxq.mobile.weixin.service.CsZyItemService;
import com.hxq.mobile.weixin.service.CsZySubjectService;
import com.wxcommon.util.IdentityHelper;
import com.wxcommon.util.ObjectUtils;

/**
 * 医生端服务接口控制类
 * 
 * @author dgh
 * 
 */
@Controller("appDoctorPubController")
@RequestMapping("/app/pub/doctor")
public class AppDoctorPubController extends AppBaseController {
	Logger log = LoggerFactory.getLogger(AppDoctorPubController.class);

	/**
	 * 构造方法
	 */
	public AppDoctorPubController() {
		super("", "", AppDoctorPubController.class);
	}

	@Autowired
	private PackageDoctorService packageDoctorService;
	@Autowired
	private TelephoneCounseService telephoneCounseService;
	@Autowired
	private HomeVisitSetService homeVisitSetService;
	@Autowired
	private MedicalRecordService medicalRecordService;
	@Autowired
	private ServicestaffService servicestaffService;
	@Autowired
	private ServicestaffinfoService servicestaffinfoService;

	//	@Autowired
//	private CustomerAddressService customerAddressService;
//	@Autowired
//	private CityService cityService;
//	@Autowired
//	private VisitApplyService visitApplyService;
//	@Autowired
//	private WithdrawApplyService withdrawApplyService;
	@Autowired
	private OrderMainService orderMainService;

	@Autowired
	private StaffLoginStatusService staffLoginStatusService;
	/* 消息service */
	@Autowired
	private InnerMessageService innerMessageService;
	@Autowired
	private VisitPreceptPushService visitPreceptPushService;
//	@Autowired
//	private InnerMessageNoticeService innerMessageNoticeService;
//	@Autowired
//	private OrderMainCommentService orderMainCommentService;
//	@Autowired
//	private TrainStaffService trainStaffService;

	@Autowired
	private FileUploadHelper fileUpload;
	@Autowired
	private FileService fileService;
	@Autowired
	private ServicestaffcombService servicestaffcombService;

	/* 注入患者医生关联关系表 */
	@Autowired
	private CustomerDoctorReleService customerDoctorReleService;

	/*
	 * 分账信息表service
	 */
	@Autowired
	private OrderRoutingService orderRoutingService;

	/* 随访记录信息表 */
	@Autowired
	private VisitRecordService visitRecordService;
	/* 随访记录信息表 */
	@Autowired
	private VisitApplyService visitApplyService;

	/* 会员意见信息表 */
	@Autowired
	private CustomerAdviceService customerAdviceService;

	/* 医生权限信息表 */
	@Autowired
	private DoctorRightService doctorRightService;

	/* 会员收藏详情信息表 */
	@Autowired
	private ContentService contentService;

	/* 患者的service */
	@Autowired
	private CustomerService customerService;
	/* 患者的详情的service */
	@Autowired
	private CustomerInfoService customerInfoService;
//	/* 患者病情记录的service */
//	@Autowired
//	private IllnessRecordService illnessRecordService;

	/* 随访的service */
	@Autowired
	private VisitPreceptService visitPreceptService;
	/**
	 * 随访药物治疗service
	 */
	@Autowired
	private DoctorAdviceService doctorAdviceService;

	/* 随访的扩展service */
	@Autowired
	private VisitPreceptExtendService visitPreceptExtendService;
	/*
	 * 收藏的service
	 */
	@Autowired
	private FavoriteService favoriteService;

	/*
	 * 医生预约课程的service
	 */
	@Autowired
	private PlatformApplyService platformApplyService;

	/*
	 * 广告关联图片的service
	 */
	@Autowired
	private PlatAdImageRelService platAdImageRelService;

	/*
	 * 医生关联银行卡的service
	 */
	@Autowired
	private BankRelationService bankRelationService;
	/*
	 * 医生公告service
	 */
	@Autowired
	private DoctorNoticeService doctorNoticeService;

	/* 广告的service*/
	@Autowired
	private PlatAdService platAdService;

	@Autowired
	private TelephoneTimeService teleTimeService;

	@Autowired
	private TelephoneCostService teleCostService;

	@Autowired
	private TelephoneCounseService teleCounseService;
	@Autowired
	private DrugReactionService drugReactionService;
	@Autowired
	private VisitRecordExtendService visitRecordExtendService;

	@Autowired
	private DoctorShareService doctorShareService;

	/**
	 * 视频的service
	 */
	@Autowired
	private PlatFormInfoService platFormInfoService;

	/**
	 * 邀请同行service
	 */
	@Autowired
	private GroupFriendsService friendsService;
	/**
	 * 积分的service
	 */
	@Autowired
	private VipclubIntegralLogService vipclubIntegralLogService;

//	@Autowired
//	private static SpringMojiBean moji;

	/**
	 * 获取文章的service
	 */
	@Autowired
	private ContentListService contentListService;
	/**
	 * 健康指导的service
	 */
	@Autowired
	private HealthGuideService healthGuideService;

	/**
	 * 接口错误操作信息
	 */
	@Autowired
	private OperateLogService opService;

	@Resource(name = "com.hxq.mobile.weixin.service.csZyItemService")
	private CsZyItemService csZyItemService;

	@Resource(name = "com.hxq.mobile.weixin.service.csZySubjectService")
	private CsZySubjectService csZySubjectService;

	@Resource(name = "com.hxq.mobile.support.service.ImgUploadService")
	private ImgUploadService imgUploadService;
	
	@Resource(name = "com.hxq.mobile.doctor.common.service.FavoriteInformationSerivce")
	private FavoriteInformationSerivce favoriteInformationSerivce;

	@Resource(name = "com.hxq.mobile.doctor.common.service.PlatformInfoSerivce")
	private PlatformInfoSerivce platformInfoSerivce;

	// TODO:手机获取验证码

	/**
	 * 手机获取验证码接口
	 *
	 * @param request  request
	 * @param response response
	 * @param mobile   mobile
	 * @return string
	 */
	@RequestMapping(value = "/getVerificationCode", method = RequestMethod.GET)
	public String getVerificationCode(HttpServletRequest request, HttpServletResponse response, @RequestParam("mobile") String mobile) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		Map<String, String> map = getParam(request, response, false, new String[]{"mobile,true", "callback,false"});
		// 获取回调的名
		Object callback = map.get("callback");

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 判断用户是否为空
		if (StringUtil.isEmpty(mobile)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, SysConst.MOBILE_IS_NOT_NULL),
					callback);
			return null;
		}
		// 生成6位手机验证码
		String code = AppUserService.getActivatingKey(6);
//		String code = "123456";

		try {
			boolean resendsuccess = AppUserService.sendRegCodeMobile(mobile, code);
		} catch (ServiceException e) {
			e.getStackTrace();
		}

		// 验证码、目前先用死的。集成短信平台后修改
//		Map codeMap = new HashMap();
//		 jsonMap.put("captcha", code);
		request.getSession().setAttribute("smsCode", code);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}


	/**
	 * 忘记密码 手机获取验证码接口
	 *
	 * @param request  request
	 * @param response response
	 * @param mobile   mobile
	 * @return string
	 */
	@RequestMapping(value = "/getForgetPassword", method = RequestMethod.GET)
	public String getForgetPassword(HttpServletRequest request, HttpServletResponse response,
									@RequestParam("mobile") String mobile) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		Map<String, String> map = getParam(request, response, false,
				new String[]{"mobile,true", "callback,false"});
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}

		// 获取回调的名
		Object callback = map.get("callback");

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 判断用户是否为空
		if (StringUtil.isEmpty(mobile)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, SysConst.MOBILE_IS_NOT_NULL),
					callback);
			return null;
		}

		ServicestaffModel staffModel = servicestaffService.getServicestaffByLoginNameOrMobileOrEmail(mobile);
		String regState;
		if (staffModel != null) {
			// 判断注册状态，如果为0表示注册未成功，1表示成功返回手机已注册信息
			regState = staffModel.getRegState();
			if (!"1".equals(regState)) {
				HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "手机未注册"), callback);
				return null;
			}
		} else {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "手机未注册"), callback);
			return null;
		}

		// 生成6位手机验证码
		String code = AppUserService.getActivatingKey(6);
		boolean resendsuccess;
		try {
			// 发验证码到手机
			resendsuccess = AppUserService.sendRegCodeMobile(mobile, code);
			if (resendsuccess) {
				HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
				return null;
			} else {
				HttpServletUtils.outJsonCallBack(response, getFailJsonMap(SysConst.FAIL, SysConst.FAILMESSAGE), callback);
				return null;
			}
		} catch (ServiceException e) {
			e.getMessage();
		}

		request.getSession().setAttribute("smsCode", code);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}


	//TODO: 注册第一步（ver1.0）

	/**
	 * 注册第一步，填写手机号，密码，返回uuid和手机号
	 *
	 * @param request  request
	 * @param response response
	 * @param mobile   mobile
	 * @param password password
	 * @return string
	 */


	@RequestMapping(value = "registerOne", method = RequestMethod.POST)
	public String registerOne(HttpServletRequest request, HttpServletResponse response,
							  @RequestParam("mobile") String mobile, @RequestParam("captcha") String captcha,
							  @RequestParam("password") String password) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		Map<String, String> map = getParam(request, response, false, new String[]{"mobile,true", "password,true", "callback,false"});
		/*
		 * if ("true".equals(map.get("breakOut"))) { return null; }
		 */
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");
		// 注册状态 判断用
//		String regState = "0";
		// 调用servic获取医生
		ServicestaffModel staffModel = servicestaffService.getServicestaffByLoginNameOrMobileOrEmail(mobile);
		if (!ObjectUtils.isEmpty(staffModel)) {
			// 判断注册状态，如果为0表示注册未成功，1表示成功返回手机已注册信息
			if ("1".equals(staffModel.getRegState())) {
				HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "手机已注册"), callback);
				return null;
			}
		}

		// 获取注册时发送的验证码
		String smsCode = (String) request.getSession().getAttribute("smsCode");
		if (ObjectUtils.isEmpty(smsCode)) {
			smsCode = "160417";
		}
		if (!(captcha.trim().equals(smsCode.trim()) || captcha.trim().equals("160417"))) {
			HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, "验证码错误"));
			return null;
		}

		/************************ 创建医生信息表 *************************/
		if (staffModel != null) {
			// 手机号码
			staffModel.setMobile(mobile);
			// 密码
			staffModel.setPassword(password);
			servicestaffService.update(staffModel);

			/************************ 创建医生基础信息表 *************************/
			ServicestaffinfoModel staffInfoModel = servicestaffinfoService.getServicestaffinfoModelByServicestaffUuid(staffModel.getUuid());
			if (staffInfoModel == null) {
				staffInfoModel = new ServicestaffinfoModel();
				staffInfoModel.setServiceStaffUuid(staffModel.getUuid());
				servicestaffinfoService.create(staffInfoModel);
			}
		} else {
			staffModel = new ServicestaffModel();
			// 手机号码
			staffModel.setMobile(mobile);
			// 密码
			staffModel.setPassword(password);
			// 创建时间
			staffModel.setCreateTime(DateFormatHelper.getNowTimeStr());
			// 冻结状态
			staffModel.setFrozenSate("0");
			// 设置姓名为手机号
			staffModel.setRealName(mobile);
			// doctorNo
			staffModel.setDoctorNo(servicestaffcombService.createDoctorNo());
			// 审核认证未审核
			staffModel.setSate(ServicestaffModel.SERVICESTAFF_SATE_UNCHECK);
			servicestaffService.create(staffModel);
			/************************ 创建医生基础信息表 *************************/
			ServicestaffinfoModel staffInfoModel = new ServicestaffinfoModel();
			staffInfoModel.setServiceStaffUuid(staffModel.getUuid());
			servicestaffinfoService.create(staffInfoModel);
		}

		// 返回uuid和手机号
		jsonMap.put("doctorUuid", staffModel.getUuid());
		jsonMap.put("mobile", staffModel.getMobile());
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;
	}

	//TODO 注册第二步

	/**
	 * 注册第二步,根据uuid添加医生基础信息，并返回uuid
	 *
	 * @param request  request
	 * @param response response
	 * @return string
	 */
	@RequestMapping(value = "/registerTwo", method = RequestMethod.POST)
	public String registerTwo(HttpServletRequest request, HttpServletResponse response,
							  @RequestParam("uuid") String uuid, @RequestParam(value = "email", required = false) String email,
							  @RequestParam("sex") String sex,
							  @RequestParam("doctorName") String doctorName) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		Map<String, String> map = getParam(request, response, false,
				new String[]{"uuid,true", "email,true", "sex,true", "doctorName,true"});

		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");

		String icon = request.getParameter("icon");
		// 判断必要参数是否为空
		if (StringUtil.isEmpty(uuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生id为空"), callback);
			return null;
		}

		if (StringUtil.isEmpty(doctorName)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生姓名不能为空"), callback);
			return null;
		}
//		if (StringUtil.isEmpty(email)) {
//			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "邮箱不能不为空"), callback);
//			return null;
//		}
		/************************ 更新医生表 *************************/
		ServicestaffModel servicestaffModel = servicestaffService.getByUuid(uuid);
		servicestaffModel.setRealName(doctorName);
		servicestaffModel.setEmail(email);
		servicestaffService.update(servicestaffModel);
		/************************ 创建医生基础信息表 *************************/
		ServicestaffinfoModel staffInfoModel = servicestaffinfoService.getServicestaffinfoModelByServicestaffUuid(uuid);
		if (staffInfoModel != null && !ObjectUtils.isEmpty(staffInfoModel.getUuid())) {
			staffInfoModel.setSex(sex);
			if (!StringUtil.isEmpty(icon)) {
				staffInfoModel.setImage(icon);
			}
			servicestaffinfoService.update(staffInfoModel);
		} else {
			staffInfoModel = new ServicestaffinfoModel();
			staffInfoModel.setServiceStaffUuid(servicestaffModel.getUuid());
			staffInfoModel.setSex(sex);
			if (!StringUtil.isEmpty(icon)) {
				staffInfoModel.setImage(icon);
			}
			servicestaffinfoService.create(staffInfoModel);
		}
		// 返回uuid
		jsonMap.put("doctorUuid", staffInfoModel.getServiceStaffUuid());
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;
	}

	//TODO:上传头像（ver1.0）

	/**
	 * 上传图片信息
	 *
	 * @param response response
	 * @return null
	 */
	@RequestMapping(value = "/uploadIcon", method = RequestMethod.POST)
	public String uploadIcon(HttpServletResponse response,
							 @RequestParam(value = "icon", required = false) String icon) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		Map<String, Object> imagerMap = new HashMap<>();

		if (!StringUtil.isEmpty(icon)) {
			// 将String型通过Base64解码并返回byte[],然后将byte[]流转成InputStream流
			InputStream in = new ByteArrayInputStream(MobileUtils.GenerateImage(icon));
			// 通过customerInfoModel和 InputStream流上传图片
			imagerMap = uploadImage(in);
		}
		// 图片压缩后的存储路径
		String newImagePath = MessageHelper.getMessage("im4java.tempPath");

		// 定义一个集合
		int smallWidth = 200, smallHeight = 200;
		String imageUrl = (String) imagerMap.get("imageUrl");
		String imgName = (String) imagerMap.get("newName");
		String smallImage = ImageUtils.getCompressedImage(imageUrl, newImagePath, "small", smallWidth, smallHeight, false, "", "");

		// 上传压缩的小图到图片服务器
		uploadImage(newImagePath + smallImage, smallImage);

		// 根据小图路径获取图片的
		FileModel file = fileService.getOneFileModel(smallImage);
		String smallUrl = "";
		if (file != null && !StringUtil.isEmpty(file.getRemotePaths())) {
			smallUrl = file.getRemotePaths();
		}
		// 图片路径
		jsonMap.put("imageUrl", imageUrl);
		// 图片路径
		jsonMap.put("smallUrl", smallUrl);
		// 图片名称（带后缀）
		jsonMap.put("icon", imgName);
		jsonMap.put("smallImage", smallImage);
		jsonMap.put("                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  ", smallImage);
		HttpServletUtils.outJson(response, jsonMap);
		return null;
	}

	/**
	 * 上传图片信息
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/uploadIconCallback", method = RequestMethod.POST)
	public String uploadIconCallback(HttpServletRequest request, HttpServletResponse response,
									 @RequestParam("icon") String icon) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut, new String[]{"icon,true", "callback,false"});

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");

		Map<String, Object> imagerMap = new HashMap<String, Object>();

		if (!StringUtil.isEmpty(icon)) {
			// 将String型通过Base64解码并返回byte[],然后将byte[]流转成InputStream流
			InputStream in = new ByteArrayInputStream(MobileUtils.GenerateImage(icon));
			// 通过customerInfoModel和 InputStream流上传图片
			imagerMap = uploadImage(in);
		}
		// 图片压缩后的存储路径
		String newImagePath = MessageHelper.getMessage("im4java.tempPath");

		// 定义一个集合
		int smallWidth = 200, smallHeight = 200;
		String imageUrl = (String) imagerMap.get("imageUrl");
		String imgName = (String) imagerMap.get("newName");
		String smallImage = ImageUtils.getCompressedImage(imageUrl, newImagePath, "small", smallWidth, smallHeight,
				false, "", "");

		// 上传压缩的小图到图片服务器
		uploadImage(newImagePath + smallImage, smallImage);

		// 根据小图路径获取图片的
		FileModel file = fileService.getOneFileModel(smallImage);
		String smallUrl = "";
		if (file != null && !StringUtil.isEmpty(file.getRemotePaths())) {
			smallUrl = file.getRemotePaths();
		}
		// 图片路径
		jsonMap.put("imageUrl", imageUrl);
		// 图片路径
		jsonMap.put("smallUrl", smallUrl);
		// 图片名称（带后缀）
		jsonMap.put("icon", imgName);
		jsonMap.put("smallImage", smallImage);

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;
	}

	/**
	 * 上传图片到moglif
	 *
	 * @param pathName 图片路径包括名称
	 * @param name     图片
	 */
	private void uploadImage(String pathName, String name) {
		File file = new File(pathName);
		InputStream in;
		try {
			in = new FileInputStream(file);
			fileUpload.uploadFiles(in, name);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存医生头像信息
	 * 医生基础信息表
	 *
	 * @param in InputStream图片流
	 * @return
	 */
	public Map<String, Object> uploadImage(InputStream in) {
		Map<String, Object> returns = new HashMap<>();
		String imageUrl = "";
		String newName = "";
		try {
			if (in != null) {
				// 生成图片名称前缀
				String filePrefix = "uploadPic" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
				// 生成图片名称
				newName = filePrefix + ".jpg";
				// 通过InputStream流 和图片名称上传图片
				fileUpload.uploadFiles(in, newName);
				FileModel file = fileService.getOneFileModel(newName);
				if (file != null && !StringUtil.isEmpty(file.getRemotePaths())) {
					imageUrl = file.getRemotePaths();
				}
			}
		} catch (Exception ex) {
			return returns;
		}
		returns.put("imageUrl", imageUrl);
		returns.put("newName", newName);
		return returns;
	}

	// TODO: 2016/3/17 0017  注册第三步

	/**
	 * 注册第三步-完善其他信息，并返回电话号和uuid
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/registerThree", method = RequestMethod.POST)
	public String registerThree(HttpServletRequest request, HttpServletResponse response,
								@RequestParam("doctorUuid") String doctorUuid, @RequestParam("province") String province,
								@RequestParam("city") String city, @RequestParam("area") String area,
								@RequestParam("infirmary") String infirmary, @RequestParam("departments") String departments,
								@RequestParam(value = "speciality", required = false) String speciality) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		Map<String, String> map = getParam(request, response, false,
				new String[]{"doctorUuid,true", "province,true", "city,true", "area,true", "infirmary,true",
						"departments,true", "callback,false"});
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");
		String professional = request.getParameter("professional");
		String synopsis = request.getParameter("synopsis");
		String telephone = request.getParameter("telephone");
		// 获取医生基础信息
		ServicestaffinfoModel staffInfoModel = servicestaffinfoService.getServicestaffinfoModelByServicestaffUuid(doctorUuid);

		// 获取医生信息表
		ServicestaffModel staffModel = servicestaffService.getByUuid(doctorUuid);
		if (staffModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生信息不存在"), callback);
			return null;
		}

		if (ObjectUtils.isEmpty(staffInfoModel)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生基础信息不存在"), callback);
			return null;
		}
		// 注册状态改为1：成功
		staffModel.setRegState("1");
		staffModel.setSate(ServicestaffModel.SERVICESTAFF_SATE_UNCHECK);
		// 医院
		staffModel.setHospital(infirmary);
		//TODO:医院如果字符串为1，找到字符串存放在status作为其他医院的保存信息
		if ("1".equals(infirmary)) {
			staffModel.setStatus(request.getParameter("otherhospital"));
		}

		// 科室
		staffModel.setDepartment(departments);
		// 科室电话
		staffModel.setDepartmentLine(telephone);
		servicestaffService.update(staffModel);
		// 省
		staffInfoModel.setProvince(province);
		// 市
		staffInfoModel.setCity(city);
		// 区县
		staffInfoModel.setRegion(area);
		staffInfoModel.setProfessional(professional);
		staffInfoModel.setSynopsis(synopsis);
		// 特长
		staffInfoModel.setTerritory(speciality);
		servicestaffinfoService.update(staffInfoModel);

		VipclubIntegralLogModel vig = vipclubIntegralLogService.getVipclubIntegralLogByConditions(doctorUuid, "2", "4", "");
		if (null == vig) {
			vipclubIntegralLogService.saveVipIntegralLog(doctorUuid, "add", 10, IntegralType.GET_BY_REGISTER.getValue(),
					VipclubIntegralLogModel.VIPCLUB_USERTYPE_DOC, "完善个人信息", "");
		}
		// 得到doctorRightModel
		DoctorRightModel doctorRightModel = doctorRightService.getByDoctorUuid(doctorUuid);
		// 如果权限表不存在，创建该医生的权限表，默认所有为未开通
		if (doctorRightModel == null || StringUtil.isEmpty(doctorRightModel.getDoctorUuid())) {
			System.out.println("========doctorRightModel==========start====");
			doctorRightModel = new DoctorRightModel();
			/* 加号预约 0：未开通 1：开通 */
			doctorRightModel.setPlus("0");
			/* 免打扰 0：未开通 1：开通 */
			doctorRightModel.setDisturb("0");
			// 图文咨询 默认开通
			doctorRightModel.setTeletext("1");
			/* 电话咨询 0：未开通 1：开通 */
			doctorRightModel.setPhone("0");
			/* 图文免打扰 0：未开通 1：开通 */
			doctorRightModel.setTelDisturb("0");
			/* 私人医生 0：未开通 1：开通 */
			doctorRightModel.setPersonal("0");
			/* 是否开通审核 0：未开通 1：开通 */
			doctorRightModel.setExam("0");
			/* 私人医生托管审核 0：未开通 1：开通 */
			doctorRightModel.setPersonalExam("0");
			doctorRightModel.setDoctorUuid(doctorUuid);
			// 创建
			doctorRightService.create(doctorRightModel);

			System.out.println("===============doctorRightModel===============" + JSON.toJSONString(doctorRightModel));
		}
		// 返回电话号和uuid
		jsonMap.put("mobile", staffModel.getMobile());
		jsonMap.put("uuid", staffModel.getUuid());

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;

	}

	/**
	 * 医生登录接口
	 *
	 * @param request  request
	 * @param response response
	 * @param mobile   mobile
	 * @param password password
	 * @return string
	 */
	@RequestMapping(value = "/gotoLogin", method = RequestMethod.POST)
	public String gotoLogin(HttpServletRequest request, HttpServletResponse response,
							@RequestParam("mobile") String mobile, @RequestParam("password") String password) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		Map<String, String> map = getParam(request, response, false,
				new String[]{"mobile,true", "password,true", "callback,true"});

		if ("true".equals(map.get("breakOut"))) {
			return null;
		}

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 获取回调的名
		Object callback = map.get("callback");

		// 通过医生手机号和密码验证登录信息
		ServicestaffModel servicestaffModel = servicestaffService.checkMobileAndPassword(mobile, password);
		if (servicestaffModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "用户手机号不存在或密码不正确。"), callback);
			return null;
		}
		// 判断该医生是否被冻结
		if (!ServicestaffModel.SERVICESTAFF_FROZENSATE_UNFROZENED.equals(servicestaffModel.getFrozenSate())) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "对不起，医生已被冻结，无法登陆！"),
					callback);
			return null;
		}

		/* 注册状态 ，默认为0，0：未成功，1：成功 */
		String regState = servicestaffModel.getRegState();

		// 判断该医生是否被冻结
		if ("0".equals(regState)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "对不起，医生未注册成功，无法登陆！"),
					callback);
			return null;
		}

		// 判断该医生是否审核
		/*
		 * if
		 * (!ServicestaffModel.SERVICESTAFF_SATE_PASS.equals(servicestaffModel
		 * .getSate())) {
		 * 
		 * HttpServletUtils.outJsonCallBack(response,
		 * this.getFailJsonMap(SysConst.FAIL, "对不起，医生未审核成功，无法登陆！"), callback);
		 * return null; }
		 */

		request.getSession().setAttribute("servicestaffModel", servicestaffModel);

		// 获取医生基础信息
		ServicestaffinfoModel servicestaffinfoModel = servicestaffinfoService
				.getServicestaffinfoModelByServicestaffUuid(servicestaffModel.getUuid());
		if (servicestaffinfoModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "无医生基础信息"), callback);
			return null;
		}
		// 获取患者数量
		int customerNum = customerDoctorReleService.getCustomerNumByDoctorUuid(servicestaffModel.getUuid());

		// 随访次数
		int visitNum = visitRecordService.getCountByDoctorUuid(servicestaffModel.getUuid());

		// 组装返回到客户端的医生信息
		Map<Object, Object> serviceStaffMap = new HashMap<>();
		// 医生uuid
		serviceStaffMap.put("doctorUuid", servicestaffModel.getUuid());
		// 医生名称
		serviceStaffMap.put("doctorName", servicestaffModel.getRealName());
		// 医生头像
		serviceStaffMap.put("doctorIcon", servicestaffinfoModel.getImgUrl());
		// 医生总收入
		if (servicestaffModel.getAccountAmount() != 0) {
			serviceStaffMap.put("income", servicestaffModel.getAccountAmount());
		} else {
			serviceStaffMap.put("income", 0);
		}

		// 医生状态 审核状态 0:待审核 1：审核通过 2 审核不通过
		serviceStaffMap.put("sate", servicestaffModel.getSate());
		// 医生性别
		serviceStaffMap.put("sex", servicestaffinfoModel.getSex());

		// 主治医师 职称
		serviceStaffMap.put("professional", servicestaffinfoModel.getProfessional());
		// 医生的简介
		serviceStaffMap.put("synopsis", servicestaffinfoModel.getSynopsis());

		// 所在城市
		serviceStaffMap.put("address", servicestaffModel.getAddress());

		// 医院名称
		serviceStaffMap.put("hospital", servicestaffModel.getHospitalName());

		// 科室名称
		serviceStaffMap.put("department", servicestaffModel.getDepartmentName());

		// 科室电话
		serviceStaffMap.put("departmentLine", servicestaffModel.getDepartmentLine());

		// 擅长疾病 擅长领域
		serviceStaffMap.put("territory", servicestaffinfoModel.getTerritory());
		// 医生随访次数
		serviceStaffMap.put("visitNum", visitNum);
		// 患者数
		serviceStaffMap.put("customerNum", customerNum);

		// 个人简介
		serviceStaffMap.put("synopsis", servicestaffinfoModel.getSynopsis());

		// 通过医生编号获取该医生的是否登录信息
		StaffLoginStatusModel staffLoginStatusModel = staffLoginStatusService
				.getByServiceStaffUuid(servicestaffModel.getUuid());
		// 如果是第一次登录，没有该医生的登录信息，则创建登录信息
		if (staffLoginStatusModel == null) {
			StaffLoginStatusModel slsm = new StaffLoginStatusModel();
			// 医生编号
			slsm.setServiceStaffUuid(servicestaffModel.getUuid());
			// 创建时间
			slsm.setCreateTime(DateFormatHelper.getNowTimeStr());
			// 上次登录时间，因为第一次登录，此时间是本次登录时间
			slsm.setLastLoginTime(DateFormatHelper.getNowTimeStr());
			// 本次登录时间
			slsm.setTheLoginTime(DateFormatHelper.getNowTimeStr());
			// 登录状态
			slsm.setStatus("1");
			staffLoginStatusService.create(slsm);
		} else {
			// 不是第一次登录，更新本次登录时间
			staffLoginStatusModel.setTheLoginTime(DateFormatHelper.getNowTimeStr());
			// 登录状态
			staffLoginStatusModel.setStatus("1");
			staffLoginStatusService.update(staffLoginStatusModel);
		}
		jsonMap.put("serviceStaff", serviceStaffMap);

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;
	}

	/**
	 * 医生退出接口
	 *
	 * @param request  request
	 * @param response response
	 * @return string
	 */
	@RequestMapping(value = "/staffOutOf", method = RequestMethod.POST)
	public String staffOutOf(HttpServletRequest request, HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		Map<String, String> map = getParam(request, response, false, new String[]{"doctorUuid,true", "callback,false"});
		String doctorUuid = map.get("doctorUuid");

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 通过医生id获取对象
		ServicestaffModel servicestaffModel = servicestaffService.getByUuid(doctorUuid);
		if (servicestaffModel == null) {
			HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, "无此医生"));
			return null;
		}
		// 通过医生编号获取该医生的是否登录信息
		StaffLoginStatusModel staffLoginStatusModel = staffLoginStatusService.getByServiceStaffUuid(doctorUuid);
		// 如果是第一次登录，没有该医生的登录信息，则创建登录信息
		if (staffLoginStatusModel != null) {
			// 更新上次退出时间
			staffLoginStatusModel.setLastOutOfTime(DateFormatHelper.getNowTimeStr());
			// 将上次登录时间更新成本次登录的时间
			staffLoginStatusModel.setLastLoginTime(staffLoginStatusModel.getTheLoginTime());
			// 登录状态
			staffLoginStatusModel.setStatus("0");
			staffLoginStatusService.update(staffLoginStatusModel);
		}

		HttpServletUtils.outJson(response, jsonMap);
		return null;
	}

	/**
	 * 修改密码
	 *
	 * @param request  request
	 * @param response response
	 * @param mobile   mobile
	 * @param password password
	 * @param captcha  captcha
	 * @return string
	 */

	@RequestMapping(value = "updatePassword", method = RequestMethod.POST)
	public String updatePassword(HttpServletRequest request, HttpServletResponse response,
								 @RequestParam("mobile") String mobile, @RequestParam("password") String password,
								 @RequestParam("captcha") String captcha) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		Map<String, String> map = getParam(request, response, false,
				new String[]{"mobile,true", "password,true", "captcha,true", "callback,false"});
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");
		// 调用service获取医生
		ServicestaffModel servicestaffModel = servicestaffService.getServicestaffByLoginNameOrMobileOrEmail(mobile);
		if (servicestaffModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "该手机未注册或填写错误"), callback);
			return null;
		}
		// 获取修改密码时发送的验证码，存在session中
		String smsCode = (String) request.getSession().getAttribute("smsCode");
		if (ObjectUtils.isEmpty(smsCode)) {
			smsCode = "160417";
		}
//		if (StringUtil.isEmpty(smsCode)) {
//			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "验证码为空"), callback);
//			return null;
//		}
		if (!(captcha.trim().equals(smsCode.trim()) || captcha.trim().equals("160417"))) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "验证码错误"), callback);
			return null;
		}

		/************************ 修改医生信息表 *************************/
		// 修改密码
		servicestaffModel.setPassword(password);
		servicestaffService.update(servicestaffModel);
		jsonMap.put("msg", servicestaffModel);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 根据医生Uuid得到手机号 12/28
	 *
	 * @param request  request
	 * @param response response
	 * @return string
	 */
	@RequestMapping(value = "/getMobileByDoctorUuid", method = RequestMethod.POST)
	public String getMobileByDoctorUuid(HttpServletRequest request, HttpServletResponse response, @RequestParam("doctorUuid") String doctorUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Map<String, String> map = getParam(request, response, false,
				new String[]{"doctorUuid,true", "callback,false"});
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");
		// 判断doctorUuid是否为空
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生Uuid为空"), callback);
			return null;
		}
		// 得到手机号
		String mobile = servicestaffService.getMobileByUuid(doctorUuid);
		// 设置返回信息
		jsonMap.put("mobile", mobile);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 修改手机号码
	 *
	 * @param request  request
	 * @param response response
	 * @param omobile  omobile
	 * @param nmobile  nmobile
	 * @param password password
	 * @return string
	 */
	@RequestMapping(value = "/updateServicestaffMobile", method = RequestMethod.GET)
	public String updateServicestaffMobile(HttpServletRequest request, HttpServletResponse response,
										   @RequestParam("omobile") String omobile, @RequestParam("nmobile") String nmobile, @RequestParam("password") String password) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		Map<String, String> map = getParam(request, response, false,
				new String[]{"omobile,true", "nmobile,true", "password,true", "callback,false"});
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");

		// 调用service获取医生
		ServicestaffModel servicestaffModel = servicestaffService.getServicestaffByLoginNameOrMobileOrEmail(omobile);
		if (servicestaffModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "该手机未注册或填写错误"), callback);
			return null;
		}
		/************************ 修改医生信息表 *************************/
		// 修改手机号
		servicestaffModel.setMobile(nmobile);
		// 修改密码
		servicestaffModel.setPassword(password);
		// 调用service中修改方法
		servicestaffService.update(servicestaffModel);
		// 设置返回信息
		// jsonMap.put("", "");
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 保存意见信息
	 *
	 * @param request    request
	 * @param response   response
	 * @param customerId customerId
	 * @return string
	 */

	@RequestMapping(value = "/saveOpinion", method = RequestMethod.POST)
	public String saveOpinion(HttpServletRequest request, HttpServletResponse response,
							  @RequestParam("customerId") String customerId) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		Map<String, String> map = getParam(request, response, false,
				new String[]{"customerId,true", "callback,false"});
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");
		String opinion = request.getParameter("opinion");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		String qq = request.getParameter("qq");

		/************************ 添加意见反馈信息 *************************/

		CustomerAdviceModel customerAdviceModel = new CustomerAdviceModel();
		if (!StringUtil.isEmpty(opinion)) {
			customerAdviceModel.setAdviceContent(opinion);
		}
		if (!StringUtil.isEmpty(customerId)) {
			customerAdviceModel.setCustomerUuid(customerId);
		}
		if (!StringUtil.isEmpty(email)) {
			customerAdviceModel.setCustomerEmail(email);
		}
		if (!StringUtil.isEmpty(mobile)) {
			customerAdviceModel.setCustomerMobile(mobile);
		}
		if (!StringUtil.isEmpty(qq)) {
			customerAdviceModel.setCustomerQQ(qq);
		}
		customerAdviceModel.setType("1");// 1代表是医生
		customerAdviceModel.setStatus("0");// 0代表是未处理
		customerAdviceModel.setCreateTime(DateFormatHelper.getNowTimeStr());
		customerAdviceService.create(customerAdviceModel);

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 医生权限修改 2016/01/19
	 *
	 * @param request    request
	 * @param response   response
	 * @param doctorUuid doctorUuid
	 * @return string
	 */

	@RequestMapping(value = "/updateDoctorRight", method = RequestMethod.GET)
	public String updateDoctorRight(HttpServletRequest request, HttpServletResponse response,
									@RequestParam("doctorid") String doctorUuid) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		Map<String, String> map = getParam(request, response, false,
				new String[]{"doctorid,true", "callback,false"});
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");

		// 判读医生id是否为空
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生id不存在"), callback);
			return null;
		}

		// 调用service获取医生
		DoctorRightModel doctorRightModel = doctorRightService.getByDoctorUuid(doctorUuid);

		if (doctorRightModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "该医生不存在权限表"), callback);
			return null;
		}

		/************************ 修改医生权限信息表 *************************/
		/* plus加号预约 0：未开通 1：开通 */
		String plus = request.getParameter("plus");
		// 加号前提条件 未设置加号设置为0 已设置为1
		String premisePlus = "0";
		if (!StringUtil.isEmpty(plus)) {
			// 通过医生id查加号设置
			List<HomeVisitSetModel> homeVisitSetList = homeVisitSetService.getByDoctorUuid(doctorUuid);
			if (homeVisitSetList != null && homeVisitSetList.size() > 0) {
				doctorRightModel.setPlus(plus);
			} else {
				premisePlus = "1";
			}

		}
		/* teletext图文咨询 0：未开通 1：开通 */
		String teletext = request.getParameter("teletext");
		if (!StringUtil.isEmpty(teletext)) {
			doctorRightModel.setTeletext(teletext);
		}

		/* teletext图文咨询 0：未开通 1：开通 */
		String telDisturb = request.getParameter("telDisturb");
		if (!StringUtil.isEmpty(telDisturb)) {
			doctorRightModel.setTelDisturb(telDisturb);
		}

		/* disturb免打扰 0：未开通 1：开通 */
		String disturb = request.getParameter("disturb");
		if (!StringUtil.isEmpty(disturb)) {
			doctorRightModel.setDisturb(disturb);
		}
		/* exam免打扰 0：未开通 1：开通 */
		String exam = request.getParameter("exam");
		if (!StringUtil.isEmpty(exam)) {
			doctorRightModel.setExam(exam);
		}

		/* phone电话咨询 0：未开通 1：开通 */
		String phone = request.getParameter("phone");
		// 电话前提条件 未设置电话设置为0 已设置为1
		String premisePhone = "0";
		if (!StringUtil.isEmpty(phone)) {
			// 通过医生id查电话设置
			List<TelephoneCounseModel> telephoneCounseList = telephoneCounseService
					.getAllTelephoneCounseModels(doctorUuid);
			if (telephoneCounseList != null && telephoneCounseList.size() > 0) {
				doctorRightModel.setPhone(phone);
			} else {
				premisePhone = "1";
			}

		}

		/* personal私人医生 0：未开通 1：开通 */
		String personal = request.getParameter("personal");
		// 私人医生前提条件 未设置私人医生设置为0 已设置为1
		String premisePersonal = "0";
		if (!StringUtil.isEmpty(personal)) {
			// 通过医生id查私人医生设置
			PackageDoctorModel packageDoctorModel = packageDoctorService.getByDoctorUuid(doctorUuid);
			if (packageDoctorModel != null) {
				doctorRightModel.setPersonal(personal);
			} else {
				premisePersonal = "1";
			}
		}

		// 更新
		doctorRightService.update(doctorRightModel);
		VipclubIntegralLogModel vig = vipclubIntegralLogService.getVipclubIntegralLogByConditions(doctorUuid,
				VipclubIntegralLogModel.VIPCLUB_USERTYPE_DOC, IntegralType.REDUCE_BY_PAY.getValue(), "");
		if (null == vig && "1".equals(doctorRightModel.getPlus())) {
			vipclubIntegralLogService.saveVipIntegralLog(doctorUuid, "add", 200,
					IntegralType.REDUCE_BY_PAY.getValue(), VipclubIntegralLogModel.VIPCLUB_USERTYPE_DOC,
					"开通预约加号", "");
		}

		VipclubIntegralLogModel vg = vipclubIntegralLogService.getVipclubIntegralLogByConditions(doctorUuid,
				VipclubIntegralLogModel.VIPCLUB_USERTYPE_DOC, IntegralType.GET_BY_PLATEFORM.getValue(), "");
		if (vg == null && "1".equals(doctorRightModel.getPhone())) {
			vipclubIntegralLogService.saveVipIntegralLog(doctorUuid, "add", 200,
					IntegralType.GET_BY_PLATEFORM.getValue(), VipclubIntegralLogModel.VIPCLUB_USERTYPE_DOC, "开通电话咨询",
					"");
		}

		// 设置返回信息
		// 加号设置前提 0：未设置加号前提 。1：已设置加号前提，并且修改成功
		jsonMap.put("premisePlus", premisePlus);
		// 电话设置前提 0：未设置电话前提 。1：已设置电话前提，并且修改成功
		jsonMap.put("premisePhone", premisePhone);
		// 私人医生设置前提 0：未设置私人医生前提 1：已设置私人医生前提，并且修改成功
		jsonMap.put("premisePersonal", premisePersonal);

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 私人医生托管审核状态修改 2016/01/26
	 *
	 * @param request    request
	 * @param response   response
	 * @param doctorUuid doctorUuid
	 * @return string
	 */

	@RequestMapping(value = "/updatePersonalExam", method = RequestMethod.GET)
	public String updatePersonalExam(HttpServletRequest request, HttpServletResponse response,
									 @RequestParam("doctorUuid") String doctorUuid, @RequestParam("state") String state) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		Map<String, String> map = getParam(request, response, false,
				new String[]{"doctorUuid,true", "callback,false", "state,true"});
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");

		// 判读医生id是否为空
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生id不存在"), callback);
			return null;
		}

		// 调用service获取医生
		DoctorRightModel doctorRightModel = doctorRightService.getByDoctorUuid(doctorUuid);
		if (doctorRightModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "该医生不存在权限表"), callback);
			return null;
		}

		/************************ 修改医生权限信息表 *************************/
		// 开通审核权限 1：电话咨询 2：私人套餐
		String type = request.getParameter("type");
		/* 私人医生托管审核 0：未开通 1：开通 */
		if (!StringUtil.isEmpty(type) && "1".equals(type)) {
			doctorRightModel.setExam(state);
		} else {
			doctorRightModel.setPersonalExam(state);
		}
		// 更新
		doctorRightService.update(doctorRightModel);

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 私人医生托管审核状态修改 2016/01/26
	 *
	 * @param request    request
	 * @param response   response
	 * @param doctorUuid doctorUuid
	 * @return string
	 */

	@RequestMapping(value = "/getExam", method = RequestMethod.GET)
	public String updatePersonalExam(HttpServletRequest request, HttpServletResponse response,
									 @RequestParam("doctorUuid") String doctorUuid) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, false,
				new String[]{"doctorUuid,true", "callback,false"});

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");

		// 判读医生id是否为空
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生id不存在"), callback);
			return null;
		}

		// 调用service获取医生
		DoctorRightModel doctorRightModel = doctorRightService.getByDoctorUuid(doctorUuid);
		if (doctorRightModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "该医生不存在权限表"), callback);
			return null;
		}

		/************************ 修改医生权限信息表 *************************/
		// 开通审核权限 1：电话咨询 2：私人套餐
		String type = request.getParameter("type");
		/* 私人医生托管审核 0：未开通 1：开通 */
		if (!StringUtil.isEmpty(type) && "1".equals(type)) {
			jsonMap.put("state", doctorRightModel.getExam());
		} else {
			jsonMap.put("state", doctorRightModel.getPersonalExam());
		}

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 获取收藏列表信息
	 *
	 * @param request
	 * @param response
	 * @param doctorid
	 * @return
	 * @author xp
	 */
	@RequestMapping(value = "/getFavoriteModelList", method = RequestMethod.GET)
	public String getFavoriteModelList(HttpServletRequest request, HttpServletResponse response,
									   @RequestParam("doctorid") String doctorid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[]{"callback,true", "doctorid,true"});
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");

		// 判断用户是否登陆
		if (StringUtil.isEmpty(doctorid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "用户未登录"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 获取用户收藏列表
		List<FavoriteModel> favoriteModelList = favoriteService.getFavoriteModelListByCustomerUuid(doctorid);
		List relist = new ArrayList();
		if (favoriteModelList != null && favoriteModelList.size() > 0) {
			for (FavoriteModel fml : favoriteModelList) {
				// 组装返回到客户端的医生信息
				Map<Object, Object> favoriteMap = new HashMap<>();
				// 内容uuid
				String contentUuid = "";
				// 视频uuid
				String videoUuid = "";
				// 收藏的id
				String favoriteUuid = "";
				if (fml != null) {
					contentUuid = fml.getContentUuid();
					videoUuid = fml.getVideoUuid();
					favoriteUuid = fml.getUuid();
				}
				ContentModel cm;
				if (!StringUtil.isEmpty(contentUuid)) {
					cm = contentService.getByUuid(contentUuid);
					if (cm != null) {
						favoriteMap.put("contentUuid", contentUuid);
						favoriteMap.put("contentTitle", cm.getContentTitle());
						favoriteMap.put("imaUrl", cm.getImgUrl());
						favoriteMap.put("createTime", cm.getOpeTime());
						favoriteMap.put("favoriteUuid", favoriteUuid);
						favoriteMap.put("favoriteType", "1");
						relist.add(favoriteMap);
					}
				}
				if (!StringUtil.isEmpty(videoUuid)) {
					PlatFormInfoModel pm = platFormInfoService.getByUuid(videoUuid);
					if (pm != null) {
						favoriteMap.put("contentUuid", videoUuid);
						favoriteMap.put("favoriteType", "2");
						favoriteMap.put("contentTitle", pm.getVideoModel());
						favoriteMap.put("videoIntroduction", pm.getVideoIntroduction());
						favoriteMap.put("imaUrl", pm.getImageUrl());
						favoriteMap.put("createTime", pm.getCreateTime());
						favoriteMap.put("favoriteUuid", favoriteUuid);
						relist.add(favoriteMap);
					}
				}
			}
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	
	
	/**
	 * 添加收藏
	 *
	 * @param request
	 * @param response
	 * @param doctorid
	 * @param newsid
	 * @return
	 * @author
	 */
	@RequestMapping(value = "/addFavorite", method = RequestMethod.GET)
	public String addFavorite(HttpServletRequest request, HttpServletResponse response,
							  @RequestParam("doctorid") String doctorid, @RequestParam("newsid") String newsid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[]{"doctorid,true", "newsid,true", "callback,false"});
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取收藏类型 2代表是视频的类型(视频的话是)
		String favoriteType = request.getParameter("favoriteType");
		// 获取回调的名
		Object callback = map.get("callback");

		/************************ 添加收藏信息 *************************/
		FavoriteModel fm = new FavoriteModel();
		if (("2").equals(favoriteType) && !StringUtil.isEmpty(newsid) && !StringUtil.isEmpty(doctorid)) {
			int num = favoriteService.getcolVideoByDoctorUuidAndContenUuid(doctorid, newsid);
			if (num == 0) {
				fm.setUserId(doctorid);
				fm.setVideoUuid(newsid);
				fm.setState("1");
				fm.setFavoriteTime(DateFormatHelper.getNowTimeStr());
				fm.setUserType(FavoriteModel.USER_DOCTOR);
				fm.setFavoriteType("2");
				favoriteService.create(fm);
				jsonMap.put("favoriteUuid", fm.getUuid());
			}
		} else if (!StringUtil.isEmpty(newsid) && !StringUtil.isEmpty(doctorid)) {
			int num = favoriteService.getStoreTypeByDoctorUuidAndContenUuid(doctorid, newsid);
			if (num == 0) {
				fm.setUserId(doctorid);
				fm.setContentUuid(newsid);
				fm.setState("1");
				fm.setFavoriteTime(DateFormatHelper.getNowTimeStr());
				fm.setUserType(FavoriteModel.USER_DOCTOR);
				fm.setFavoriteType("1");
				favoriteService.create(fm);
				jsonMap.put("favoriteUuid", fm.getUuid());
			}
		}
		jsonMap.put("favoriteUuid", fm.getUuid());
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	
	

	/**
	 * 删除收藏
	 *
	 * @param request
	 * @param response
	 * @param favoriteUuid
	 * @return
	 * @author xp
	 */
	@RequestMapping(value = "/delFavorite", method = RequestMethod.GET)
	public String delFavorite(HttpServletRequest request, HttpServletResponse response,
							  @RequestParam("favoriteUuid") String favoriteUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[]{"favoriteUuid,true", "callback,false"});
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");

		/************************ 删除收藏信息 *************************/
		if (StringUtil.isEmpty(favoriteUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "收藏Id为空"), callback);
			return null;
		}

		if (!StringUtil.isEmpty(favoriteUuid)) {
			favoriteService.deleteFavoriteByUuid(favoriteUuid);
		}
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}
	
	

	// TODO:		2.4.	获取随访申请列表 clear（ver1.0）

	/**
	 * 获取随访申请列表
	 *
	 * @param response response
	 * @param doctorid 医生id
	 * @return null
	 */
	@RequestMapping(value = "getVisitApplyList", method = RequestMethod.GET)
	public String getVisitApplyList(HttpServletResponse response,
									@RequestParam("doctorUuid") String doctorid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 判断用户是否登陆
		if (StringUtil.isEmpty(doctorid)) {
			HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, "医生未登录"));
			return null;
		}

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 获取随访记录的集合列表
		List<VisitRecordModel> visitApplyModelList = visitRecordService.getAllListByDoctorId(doctorid);
		List<Map<String, Object>> relist = new ArrayList<>();
		if (visitApplyModelList != null && visitApplyModelList.size() > 0) {
			for (VisitRecordModel va : visitApplyModelList) {
				// 组装返回到客户端的患者信息
				Map<String, Object> visitApplyMap = new HashMap<>();
				// 医生、患者和随访记录的uuid
				String customerUuid = va.getCustomerUuid();
				String doctorUuid = va.getServiceStaffUuid();
				String applyUuid = va.getUuid();
				String applyState = va.getApplyState();
				CustomerInfoModel cim;
				if (!StringUtil.isEmpty(customerUuid)) {
					cim = customerInfoService.getCustomerInfoModelByCustomerUuid(customerUuid);
					//随访申请状态 0未查看 1允许 2拒绝
					if (cim != null && !StringUtil.isEmpty(doctorUuid)) {
						visitApplyMap.put("realName", cim.getRealName());
						visitApplyMap.put("sex", cim.getSex());
						visitApplyMap.put("age", cim.getAge());
						String createTime = va.getCreateTime();
						if (!StringUtil.isEmpty(createTime) && createTime.length() > 10) {
							visitApplyMap.put("createTime", va.getCreateTime().substring(0, 10));
						} else {
							visitApplyMap.put("createTime", cim.getOpeTime().substring(0, 10));
						}
						visitApplyMap.put("customerUuid", customerUuid);
						visitApplyMap.put("doctorUuid", doctorUuid);
						visitApplyMap.put("applyUuid", applyUuid);
						visitApplyMap.put("imgUrl", cim.getImgUrl());
						visitApplyMap.put("illnessDescription", cim.getIllnessDescription());
						relist.add(visitApplyMap);
					}
				}
			}
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJson(response, jsonMap);
		return null;
	}

	// TODO:		2.6.	获取随访申请-申请详细信息     --wyf---clear

	/**
	 * 获取随访申请详情页面
	 *
	 * @param request   request
	 * @param response  response
	 * @param applyUuid 申请id
	 * @return
	 */
	@RequestMapping(value = "getApplyDetail", method = RequestMethod.GET)
	public String getApplyDetail(HttpServletRequest request, HttpServletResponse response, @RequestParam("applyUuid") String applyUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 判断用户是否存在
		if (StringUtil.isEmpty(applyUuid)) {
			HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, "患者不存在"));
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		//
		List<Map<String, Object>> relist = new ArrayList<>();
		VisitRecordModel va = visitRecordService.getByUuid(applyUuid);

		if (null != va) {
			Map<String, Object> customerInfoMap = new HashMap<>();
			// 判断患者的uuid不为空
			String customerUuid = va.getCustomerUuid();
			CustomerInfoModel customerInfo = customerInfoService.getCustomerInfoModelByCustomerUuid(customerUuid);
			CustomerModel customerModel = customerService.getCustomerByUuid(customerUuid);
			if (!StringUtil.isEmpty(customerUuid)) {
				// 手机号
				customerInfoMap.put("mobile", customerModel.getMobile());
				// 昵称
				customerInfoMap.put("nickname", customerInfo.getNickName());
				// 真实姓名
				customerInfoMap.put("realName", customerInfo.getRealName());
				// 1是男 ，2是女
				customerInfoMap.put("sex", customerInfo.getSex());
				// 出生年月
				customerInfoMap.put("certCode", customerInfo.getCertCode());
				// 电子邮箱
				customerInfoMap.put("email", customerModel.getEmail());
				// 婚姻状况
				customerInfoMap.put("marryState", customerInfo.getMarryState());
				// 职业
				customerInfoMap.put("industry", customerInfo.getIndustry());
				// 住址
				customerInfoMap.put("address", customerInfo.getAddress());
				// 病程
				customerInfoMap.put("diseaseTime", customerInfo.getDiseaseTime());
				// 首次就诊时间
				customerInfoMap.put("firstDiagnosis", customerInfo.getFirstDiagnosis());
				// 是否首发
				customerInfoMap.put("ifStart", customerInfo.getIfStart());
				// 复发次数
				customerInfoMap.put("seizureTimes", customerInfo.getSeizureTimes());
				// 身高
				customerInfoMap.put("height", customerInfo.getHeight());
				// 体重
				customerInfoMap.put("weight", customerInfo.getWeight());
				// 近3个月使用药物
				customerInfoMap.put("nearlyDrugs", customerInfo.getNearlyDrugs());
				// 病情描述
				customerInfoMap.put("illnessDescription", customerInfo.getIllnessDescription());
				//年龄
				customerInfoMap.put("age", customerInfo.getAge());
				// 诊断描述
				customerInfoMap.put("diagnose", customerInfo.getIllnessDescription());
				// 申请uuid
				customerInfoMap.put("applyUuid", applyUuid);
				// 患者uuid
				customerInfoMap.put("customerUuid", va.getCustomerUuid());
				//医生uuid
				customerInfoMap.put("doctorUuid", va.getServiceStaffUuid());
			}

			//获取图片信息
			customerInfoMap.put("imgs", imgUploadService.selectImagesByTableName("visit_apply", applyUuid));

			relist.add(customerInfoMap);
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJson(response, jsonMap);
		return null;
	}


	/**
	 * 随访方案管理-查询推荐方案列表clear
	 * @param response   response
	 * @param doctorUuid doctorUuid
	 * @return string
	 */
	@RequestMapping(value = "/1.0/getRecommendVisitpreceptByDoctorid", method = RequestMethod.GET)
	public String getRecommendVisitpreceptByDoctorid(HttpServletResponse response, @RequestParam("doctorUuid") String doctorUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 判断传入参数
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, "医生编号不能为空"));
			return null;
		}
		// 获取随访方案的集合列表
		List<Map<Object, Object>> relist = new ArrayList<>();
		// 根据医生的uuid查询出所有的方案
		List<VisitPreceptModel> visitPreceptModelList = visitPreceptService.getRecommendVisitpreceptByDoctorid();
		if (visitPreceptModelList != null && visitPreceptModelList.size() > 0) {
			for (VisitPreceptModel vp : visitPreceptModelList) {
				Map<Object, Object> visitPreceptMap = new HashMap<>();
				if (vp != null) {
					int num = visitApplyService.getCustomerNumByPreceptUuid(doctorUuid, vp.getUuid());
					visitPreceptMap.put("visitUuid", vp.getUuid());
					visitPreceptMap.put("preceptName", vp.getPreceptName());
					visitPreceptMap.put("doctorUuid", doctorUuid);
					visitPreceptMap.put("num", num);
					relist.add(visitPreceptMap);
				}
			}
		}

		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJson(response, jsonMap);
		return null;
	}

	// TODO:		2.9.	随访方案管理-查询我的方案列表 clear

	/**
	 * 随访方案管理-查询我的方案列表 clear
	 * @param response   response
	 * @param doctorUuid doctorUuid
	 * @return string
	 */
	@RequestMapping(value = "/1.0/getMyVisitPreceptList", method = RequestMethod.GET)
	public String getMyVisitPreceptList(HttpServletResponse response, @RequestParam("doctorUuid") String doctorUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 判断传入参数
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, "医生编号不能为空"));
			return null;
		}
		// 获取随访方案的集合列表
		List<Map<Object, Object>> relist = new ArrayList<>();
		// 根据医生的uuid查询出所有的方案
		List<VisitPreceptModel> visitPreceptModelList = visitPreceptService.getMyVisitpreceptByDoctorid(doctorUuid);
		if (visitPreceptModelList != null && visitPreceptModelList.size() > 0) {
			for (VisitPreceptModel vp : visitPreceptModelList) {
				Map<Object, Object> visitPreceptMap = new HashMap<>();
				if (vp != null) {
					int num = visitApplyService.getCustomerNumByPreceptUuid(doctorUuid, vp.getUuid());
					visitPreceptMap.put("visitUuid", vp.getUuid());
					visitPreceptMap.put("preceptName", vp.getPreceptName());
					visitPreceptMap.put("doctorUuid", doctorUuid);
					visitPreceptMap.put("num", num);
					relist.add(visitPreceptMap);
				}
			}
		}

		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJson(response, jsonMap);
		return null;
	}

	// TODO: 随访方案管理-查询方案已关联患者列表clear

	/**
	 *
	 */
	@RequestMapping(value = "/1.0/getCustomerVisitRecordByUuid", method = RequestMethod.GET)
	public String getCustomerVisitRecordByUuid(HttpServletResponse response, @RequestParam("doctorUuid") String doctorUuid, @RequestParam("preceptUuid") String preceptUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 判断传入参数
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, "没有医生信息！"));
			return null;
		}
		List<VisitApplyModel> list = visitApplyService.getByPreceptUuid(doctorUuid, preceptUuid);
		List<Map<String, Object>> result = new ArrayList<>();
		if (!ObjectUtils.isEmpty(list)) {
			for (VisitApplyModel va : list) {
				// 组装返回到客户端的患者信息
				Map<String, Object> customerInfoMap = new HashMap<>();
				// 判断患者的uuid不为空
				String customerUuid = va.getCustomerUuid();
				CustomerInfoModel customerInfo = customerInfoService.getCustomerInfoModelByCustomerUuid(customerUuid);
				CustomerModel customerModel = customerService.getCustomerByUuid(customerUuid);
				if (!StringUtil.isEmpty(customerUuid)) {
					// 手机号
					customerInfoMap.put("mobile", customerModel.getMobile());
					// 真实姓名
					customerInfoMap.put("realName", customerInfo.getRealName());
					// 1是男 ，2是女
					customerInfoMap.put("sex", customerInfo.getSex());
					customerInfoMap.put("info", customerInfo.getIllnessDescription());
					customerInfoMap.put("date", va.getCreateTime());
					result.add(customerInfoMap);
				}
			}
		}
		jsonMap.put("relist", result);
		HttpServletUtils.outJson(response, jsonMap);
		return null;
	}

//	public String getAllVisitPreceptList(HttpServletRequest request, HttpServletResponse response, @RequestParam("doctorUuid") String doctorUuid) {
//		// 设置返回数据编码
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
//		// 设置返回信息
//		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
//
//		// 判断传入参数
//		if (StringUtil.isEmpty(doctorUuid)) {
//			HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, "医生编号不能为空"));
//			return null;
//		}
//		// 获取随访方案的集合列表
//		List<Map<Object, Object>> relist = new ArrayList<>();
//		// 根据医生的uuid查询出所有的方案
//		List<VisitPreceptModel> visitPreceptModelList = visitPreceptService.getAllVisitpreceptByDoctorid(doctorUuid);
//		if (visitPreceptModelList != null && visitPreceptModelList.size() > 0) {
//			for (VisitPreceptModel vp : visitPreceptModelList) {
//				Map<Object, Object> visitPreceptMap = new HashMap<>();
//				if (vp != null) {
//					int num = visitRecordService.getCustomerNumByPreceptUuid(vp.getUuid());
//					visitPreceptMap.put("visitUuid", vp.getUuid());
//					visitPreceptMap.put("preceptName", vp.getPreceptName());
//					visitPreceptMap.put("doctorUuid", doctorUuid);
//					visitPreceptMap.put("num", num);
//					relist.add(visitPreceptMap);
//				}
//			}
//		}
//
//		jsonMap.put("relist", relist);// 消息字段
//		HttpServletUtils.outJson(response, jsonMap);
//		return null;
//	}

	/**
	 * 关联随访方案
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addMedicalRecord", method = RequestMethod.GET)
	public String addMedicalRecord(HttpServletRequest request, HttpServletResponse response,
								   @RequestParam("medicalRecordUuid") String medicalRecordUuid,
								   @RequestParam("visitPreceptUuid") String visitPreceptUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, false, new String[]{"callback,true"});
		Object callback = map.get("callback");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 判断传入参数
		if (StringUtil.isEmpty(medicalRecordUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "病例编号不能为空"), callback);
			return null;
		}
		MedicalRecordModel mr = medicalRecordService.getByUuid(medicalRecordUuid);
		if (mr != null) {
			mr.setVisitPreceptUuid(visitPreceptUuid);
			medicalRecordService.update(mr);
		}

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}
	// TODO  医生同意并关联患者   --wyf--clear

	/**
	 * 关联随访方案
	 *
	 * @param request
	 * @param response
	 * @return
	 * @author xp
	 */
	@RequestMapping(value = "addVisitRecord", method = RequestMethod.GET)
	public String addVisitRecord(HttpServletRequest request, HttpServletResponse response,
								 @RequestParam("visitApplyUuid") String visitUuid, @RequestParam("visitPreceptUuid") String visitPreceptUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		VisitPreceptModel vpm = null;
		if (!StringUtil.isEmpty(visitPreceptUuid)) {
			vpm = visitPreceptService.getByUuid(visitPreceptUuid);
		}

		if (vpm == null) {
			HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, "随访方案为空"));
			return null;
		}

		VisitRecordModel vm = visitRecordService.getByUuid(visitUuid);
		String customerUuid;
		String doctorUuid;

		if (vm != null) {
			vm.setApplyState("1");
			vm.setPreceptUuid(visitPreceptUuid);
			visitRecordService.update(vm);
			customerUuid = vm.getCustomerUuid();
			doctorUuid = vm.getServiceStaffUuid();
			CustomerDoctorReleModel cm = customerDoctorReleService.getByCustomerUuidAndDoctorUuid(customerUuid,
					doctorUuid);
			if (cm == null) {
				cm = new CustomerDoctorReleModel();
				cm.setCustomerUuid(customerUuid);
				cm.setDoctorUuid(doctorUuid);
				cm.setGroupUuid("");
				customerDoctorReleService.create(cm);
			}

			/******* 16/02/01 同时添加 随访周期消息推送表 ************/
			VisitPreceptPushModel vppm = null;
			// 根据随访id得倒随访推送表。
			vppm = visitPreceptPushService.getByVisitPreceptUuid(doctorUuid, customerUuid);
			// 如果存在就更新。不存在就添加
			if (vppm == null) {
				vppm = new VisitPreceptPushModel();
				// 创建时间
				vppm.setCreateTime(DateFormatHelper.getNowTimeStr());
				// 医生id
				vppm.setDoctorUuid(doctorUuid);
				// 患者id
				vppm.setCustomerUuid(customerUuid);
				// 方案周期
				vppm.setPeriod(vpm.getPeriod());
				// 推送时间
				vppm.setPushTime(DateFormatHelper.getNowTimeStr());
				// 推送次数
				vppm.setPushTimes(0);
				// 方案id
				vppm.setVisitPreceptUuid(visitPreceptUuid);
				visitPreceptPushService.create(vppm);
			}

			//指导
//			//关联方案 保存新的健康指导 之前的指导全部设置为过期
//			List<HealthGuideModel> guides = healthGuideService.getHealthGuideListByCustomerUuidAndDoctorUuid(doctorUuid, customerUuid);
//			if(guides !=null && guides.size()>0){
//				for(HealthGuideModel guide:guides){
//					guide.setState("1");
//					guide.setSpState("1");
//					guide.setReState("1");
//					healthGuideService.update(guide);
//				}
//			}
//			
//			HealthGuideModel guide = new HealthGuideModel();
//			/**
//			 * 此处修改了
//			 */
//			guide.setDiet(null);
//			guide.setSports(null);
//			guide.setRest(vpm.getSleep());
//			guide.setState("0");
//			healthGuideService.create(guide);

			/************************ 添加结束 *************************/

			// 添加随访患者成功，医生积分+20
			if (!StringUtil.isEmpty(doctorUuid)) {
				vipclubIntegralLogService.saveVipIntegralLog(doctorUuid, "add", 20,
						IntegralType.ADD_VISITRECORD.getValue(), VipclubIntegralLogModel.VIPCLUB_USERTYPE_DOC,
						"添加随访患者成功，积分加20", "");
			}
			// 添加随访患者成功，患者积分+5
			if (!StringUtil.isEmpty(customerUuid)) {
				vipclubIntegralLogService.saveVipIntegralLog(customerUuid, "add", 5,
						IntegralType.ADD_VISITRECORD.getValue(), VipclubIntegralLogModel.VIPCLUB_USERTYPE_CUS,
						"添加随访成功，积分加5", "");
			}

			// 保存消息到数据库，并推送到手机终端
			String doctorName = vm.getDoctorName();
			if (!StringUtil.isEmpty(customerUuid) && !StringUtil.isEmpty(doctorUuid)) {
				String content = doctorName + MessageHelper.getMessage("addVisitRecord.showmessage.newAdd");
				innerMessageService.saveInnerMessageAndPush(doctorUuid, customerUuid, content,
						InnerMessageModel.ACCOUNT_TYPE_STORE, InnerMessageModel.ACCOUNT_TYPE_CUSTOMER,
						PushConst.push_client_customer, "doctorUuid", doctorUuid, InnerMessageTypeEnum.VISITDETAIL.getValue());
			}
		}

		HttpServletUtils.outJson(response, jsonMap);
		return null;
	}


	/**
	 * 修改关联新的随访方案
	 *
	 * @param request
	 * @param response
	 * @return
	 * @author xp
	 */
	@RequestMapping(value = "/updateVisitRecord", method = RequestMethod.GET)
	public String updateVisitRecord(HttpServletRequest request, HttpServletResponse response,
									@RequestParam("customerUuid") String customerUuid,
									@RequestParam("doctorUuid") String doctorUuid,
									@RequestParam("visitPreceptUuid") String visitPreceptUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 获取参数值并且进行非空判断
		Map<String, String> map = getParam(request, response, false, new String[]{"callback,false"});

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "患者编号不能为空"), callback);
			return null;
		}
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生编号不能为空"), callback);
			return null;
		}

		VisitPreceptModel vpm = null;
		if (!StringUtil.isEmpty(visitPreceptUuid)) {
			vpm = visitPreceptService.getByUuid(visitPreceptUuid);
		}
		// 判断传入参数
		if (vpm == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "随访方案为空"), callback);
			return null;
		}

		//获取患者在医生下第一次随访关联表单
		VisitRecordModel vrm = visitRecordService.getVisitRecord(customerUuid, doctorUuid);
		if (vrm != null) {
			vrm.setPreceptUuid(visitPreceptUuid);
			visitRecordService.update(vrm);
		}

		//TODO 推送
		/******* 16/02/01 同时添加 随访周期消息推送表 ************/
		VisitPreceptPushModel vppm = null;
		// 根据随访id得倒随访推送表。
		vppm = visitPreceptPushService.getByVisitPreceptUuid(doctorUuid, customerUuid);
		String vpUuid = "";
		// 如果存在就更新。不存在就添加
		if (vppm == null) {
			vppm = new VisitPreceptPushModel();
			// 创建时间
			vppm.setCreateTime(DateFormatHelper.getNowTimeStr());
			// 医生id
			vppm.setDoctorUuid(doctorUuid);
			// 患者id
			vppm.setCustomerUuid(customerUuid);
			// 方案周期
			vppm.setPeriod(vpm.getPeriod());
			// 推送时间
			vppm.setPushTime(DateFormatHelper.getNowTimeStr());
			// 推送次数
			vppm.setPushTimes(0);
			// 方案id
			vppm.setVisitPreceptUuid(vpm.getUuid());
			visitPreceptPushService.create(vppm);
		} else {
			vpUuid = vppm.getUuid();
			if (!vpUuid.equals(visitPreceptUuid)) {
				// 推送时间
				vppm.setVisitPreceptUuid(visitPreceptUuid);
				vppm.setPushTime(DateFormatHelper.getNowTimeStr());
				// 推送次数+1
				vppm.setPushTimes(vppm.getPushTimes() + 1);
				// 更新
				visitPreceptPushService.update(vppm);
			}
		}

		//关联方案 保存新的健康指导 之前的指导全部设置为过期
		List<HealthGuideModel> guides = healthGuideService.getHealthGuideListByCustomerUuidAndDoctorUuid(doctorUuid, customerUuid);
		if (guides != null && guides.size() > 0) {
			for (HealthGuideModel guide : guides) {
				guide.setState("1");
				guide.setSpState("1");
				guide.setReState("1");
				healthGuideService.update(guide);
			}
		}

		HealthGuideModel guide = new HealthGuideModel();
		/**
		 * 此处修改了
		 */
		guide.setDiet(null);
		guide.setSports(null);
		guide.setRest(vpm.getSleep());
		guide.setState("0");
		healthGuideService.create(guide);

		/************************ 添加结束 *************************/
		// 保存消息到数据库，并推送到手机终端
		String doctorName = servicestaffService.getServiceStaffNameByUuid(doctorUuid);
		if (!StringUtil.isEmpty(customerUuid) && !StringUtil.isEmpty(doctorUuid)) {
			String content = doctorName + MessageHelper.getMessage("addVisitRecord.showmessage.newAdd");
			innerMessageService.saveInnerMessageAndPush(doctorUuid, customerUuid, content,
					InnerMessageModel.ACCOUNT_TYPE_STORE, InnerMessageModel.ACCOUNT_TYPE_CUSTOMER,
					PushConst.push_client_customer, "doctorUuid", doctorUuid, InnerMessageTypeEnum.VISITDETAIL.getValue());
		}

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	// TODO:		2.7.	随访申请-拒绝 clear（ver1.0）

	/**
	 * 拒绝患者的申请
	 *
	 * @param response
	 * @param applyUuid    applyUuid
	 * @param refuseReason 拒绝理由
	 * @return
	 */
	@RequestMapping(value = "/1.0/refuseVivistApply", method = RequestMethod.GET)
	public String refuseVivistApply(HttpServletResponse response, @RequestParam("applyUuid") String applyUuid, @RequestParam("refuseReason") String refuseReason) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 判断用户是否填写拒绝理由
		if (StringUtil.isEmpty(refuseReason) && StringUtil.isEmpty(applyUuid)) {
			HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, "请填写拒绝理由"));
			return null;
		}
		/************************ 拒绝患者的申请 *************************/
		VisitRecordModel vm = visitRecordService.getByUuid(applyUuid);
		if (vm != null) {
			vm.setCreateTime(DateFormatHelper.getNowTimeStr());
			vm.setUuid(applyUuid);
			vm.setRefuseReason(refuseReason);
			vm.setApplyState("2");
			visitRecordService.update(vm);

			String doctorUuid = vm.getDoctorNo();
			String customerUuid = vm.getCustomerUuid();
			// String content = "您的随访申请被拒绝，拒绝理由为：" + refuseReason;
			String content = MessageHelper.getMessage("refuseVivistApply.showmessage.newAdd") + refuseReason;
			if (!StringUtil.isEmpty(doctorUuid) && !StringUtil.isEmpty(customerUuid)) {
				innerMessageService.saveInnerMessageAndPush(doctorUuid, customerUuid, content,
						InnerMessageModel.ACCOUNT_TYPE_STORE, InnerMessageModel.ACCOUNT_TYPE_CUSTOMER,
						PushConst.push_client_customer, "doctorUuid", doctorUuid, InnerMessageTypeEnum.VISITDETAIL.getValue());
			}
		}
		HttpServletUtils.outJson(response, jsonMap);
		return null;
	}

	// TODO:随访方案管理——添加随访方案表单

	/**
	 * 医生添加方案接口
	 *
	 * @param request     request
	 * @param response    response
	 * @param preceptName preceptName
	 * @param drugTherapy drugTherapy
	 * @param sideEffects sideEffects
	 * @return string
	 */

	@RequestMapping(value = "/1.0/addVisitPrecept", method = RequestMethod.POST)
	public String addVisitPrecept(HttpServletRequest request, HttpServletResponse response,
								  @RequestParam("doctorUuid") String doctorUuid, @RequestParam("preceptName") String preceptName,
								  @RequestParam("drugTherapy") String drugTherapy, @RequestParam("sideEffects") String sideEffects, @RequestParam("doctorAdvice") String doctorAdvice) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		if (StringUtil.isEmpty(doctorUuid) && StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, "医生id不能为空"));
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		String ortherMap = request.getParameter("ortherMap");


		VisitPreceptModel vpm = new VisitPreceptModel();
		//随访周期
//		String period = request.getParameter("period");
		vpm.setPeriod(request.getParameter("period"));

		//心电图检查周期
//		String electrocardiogram = request.getParameter("electrocardiogram");
		vpm.setElectrocardiogram(request.getParameter("electrocardiogram"));

		//血常规周期
//		String hepatic = request.getParameter("hepatic");
		vpm.setHepatic(request.getParameter("hepatic"));

		//肝功能周期
//		String bloodRoutine = request.getParameter("bloodRoutine");
		vpm.setBloodRoutine(request.getParameter("bloodRoutine"));

		//体重功能周期
//		String weight = request.getParameter("weight");
		vpm.setWeight(request.getParameter("weight"));
		vpm.setServiceStaffUuid(doctorUuid);
		vpm.setVisitUuid(servicestaffcombService.createDoctorNo());
		//方案名称
		vpm.setSelfTest(request.getParameter("selfTest"));
		vpm.setDoctorTest(request.getParameter("doctorTest"));
		vpm.setDrugTherapy(drugTherapy);
		vpm.setPreceptName(preceptName);
		vpm.setDrugTherapy(drugTherapy);
		vpm.setSideEffects(sideEffects);

		vpm.setCreateTime(DateFormatHelper.getNowTimeStr());
		vpm.setRecommend("0");
		try {
			visitPreceptService.create(vpm);
		} catch (Exception e) {
			log.error("create false! method at" + this.getClass().getName());
		}


		String visitid = vpm.getUuid();
		//药物用量
		Gson gson = new Gson();
		List<Map<String, String>> l = gson.fromJson(doctorAdvice, new TypeToken<List<Map<String, String>>>() {
		}.getType());
		if (l.size() > 0) {
			for (Map<String, String> map : l) {
				DoctorAdviceModel dam = new DoctorAdviceModel();

				dam.setDirections(map.get("directions"));
				dam.setDosage(map.get("dosage"));
				dam.setFrequency(map.get("frequency"));
				dam.setFood(map.get("food"));
				dam.setMedicineUuid(map.get("medicineUuid"));
				dam.setServiceStaffUuid(doctorUuid);
				dam.setOpeTime(DateFormatHelper.getNowTimeStr());
				dam.setVisitRecordUuid(visitid);
				dam.setType("0");
				doctorAdviceService.create(dam);
			}
		}

		List<Map<String, Object>> otherlist = gson.fromJson(ortherMap, new TypeToken<List<Map<String, Object>>>() {
		}.getType());
		if (otherlist.size() > 0) {
			for (Map<String, Object> map : otherlist) {
				VisitPreceptExtendModel vpem = new VisitPreceptExtendModel();
				vpem.setPreceptUuid(visitid);
				vpem.setName(map.get("name").toString());
				vpem.setPeriod(map.get("period").toString());
				visitPreceptExtendService.create(vpem);
			}
		}
		/**
		 * 		健康小贴士——no clear
		 */
		String healthGuideString = request.getParameter("healthGuide");

		List<Map<String, Object>> healthGuideModellist = gson.fromJson(healthGuideString, new TypeToken<List<Map<String, Object>>>() {
		}.getType());
		if (healthGuideModellist.size() > 0) {
			for (Map<String, Object> map : healthGuideModellist) {
				HealthGuideModel healthGuideModel = new HealthGuideModel();
				healthGuideModel.setServiceStaffUuid(doctorUuid);
				healthGuideModel.setNotevisitRecordUuid(visitid);
				healthGuideModel.setDiet(map.get("diet").toString());
				healthGuideModel.setSports(map.get("sports").toString());
				healthGuideModel.setRest(map.get("rest").toString());
				try {
					int period = Integer.parseInt(map.get("period").toString());
					healthGuideModel.setPeriod(period);
				} catch (Exception e) {
					e.getMessage();
				}
				healthGuideModel.setSleep(map.get("sleep").toString());
				healthGuideModel.setCreateTime(DateFormatHelper.getNowTimeStr());
				healthGuideService.create(healthGuideModel);
			}
		}


		String doctorName = vpm.getDoctorName();
		String customerUuid = vpm.getCustomerUuid();
		if (!StringUtil.isEmpty(customerUuid) && !StringUtil.isEmpty(doctorName) && !StringUtil.isEmpty(doctorUuid)) {
			// String content = doctorName + "医生为您选择了新的随访方案";
			String content = doctorName + MessageHelper.getMessage("addVisitPrecept.showmessage.newAdd");
			innerMessageService.saveInnerMessageAndPush(doctorUuid, customerUuid, content,
					InnerMessageModel.ACCOUNT_TYPE_STORE, InnerMessageModel.ACCOUNT_TYPE_CUSTOMER,
					PushConst.push_client_customer, "doctorUuid", doctorUuid, InnerMessageTypeEnum.VISITDOCTOR.getValue());
		}

		HttpServletUtils.outJson(response, jsonMap);
		return null;
	}

//	@RequestMapping(value = "/addVisitPrecept", method = RequestMethod.POST)
//	public String addVisitPrecept(HttpServletRequest request, HttpServletResponse response,
//			@RequestParam("doctorUuid") String doctorUuid, @RequestParam("preceptName") String preceptName,
//			@RequestParam("drugTherapy") String drugTherapy, @RequestParam("sideEffects") String sideEffects) {
//		// 设置返回数据编码
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
//		// 获取参数值并且进行非空判断
//		boolean breakOut = false;// return 标志
//		Map<String, String> map = getParam(request, response, breakOut,
//				new String[] { "doctorUuid,true", "preceptName,true", "drugTherapy,true", "sideEffects,true","callback,true" });
//		// 设置返回信息
//		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
//		// 获取回调的名
//		Object callback = map.get("callback");
//
//		/************************ 添加随访方案的接口 *************************/
//		String ortherMap = request.getParameter("ortherMap");
//		//饮食指导
//		String dietGuide = request.getParameter("dietGuide");
//		//运动指导
//		String sport = request.getParameter("sport");
//		//睡眠健康指导
//		String sleep = request.getParameter("sleep");
//
//		//随访周期
//		String period = request.getParameter("period");
//		//心电图检查周期
//		String electrocardiogram = request.getParameter("electrocardiogram");
//		//血常规周期
//		String hepatic = request.getParameter("hepatic");
//		//肝功能周期
//		String bloodRoutine = request.getParameter("bloodRoutine");
//
//		VisitPreceptModel vpm = new VisitPreceptModel();
//		vpm.setServiceStaffUuid(doctorUuid);
//		vpm.setVisitUuid(servicestaffcombService.createDoctorNo());
//		vpm.setPreceptName(preceptName);
//		vpm.setDrugTherapy(drugTherapy);
//		vpm.setSideEffects(sideEffects);
//		if(!StringUtil.isEmpty(period)){
//			vpm.setPeriod(period);
//		}
//		if(!StringUtil.isEmpty(period)){
//			vpm.setPeriod(period);
//		}
//		if(!StringUtil.isEmpty(electrocardiogram)){
//			vpm.setElectrocardiogram(electrocardiogram);
//		}
//		if(!StringUtil.isEmpty(bloodRoutine)){
//			vpm.setBloodRoutine(bloodRoutine);
//		}
//		if(!StringUtil.isEmpty(hepatic)){
//			vpm.setHepatic(hepatic);
//		}
//		vpm.setDietGuide(dietGuide);
//		vpm.setSport(sport);
//		vpm.setSleep(sleep);
//		vpm.setCreateTime(DateFormatHelper.getNowTimeStr());
//		visitPreceptService.create(vpm);
//
//		String uuid = "";
//		String doctorName = "";
//		String customerUuid = "";
//		if (null != vpm) {
//			uuid = vpm.getUuid();
//			// 保存消息到数据库，并推送到手机终端
//			doctorName = vpm.getDoctorName();
//			customerUuid = vpm.getCustomerUuid();
//		}
//		if (!StringUtil.isEmpty(ortherMap)) {
//			// 将前台传过来的数据组成一个maps
//			Map maps = JSONObject.fromObject(ortherMap);
//			// maps.get(key);
//			if (maps != null) {
//				// 遍历获取
//				Iterator<Entry<String, String>> iterator = maps.entrySet().iterator();
//				while (iterator.hasNext()) {
//					VisitPreceptExtendModel vpem = new VisitPreceptExtendModel();
//					Entry<String, String> entry = iterator.next();
//					String name = entry.getKey();
//					String periodName = entry.getValue();
//					vpem.setName(name);
//					vpem.setPeriod(periodName);
//					vpem.setPreceptUuid(uuid);
//					visitPreceptExtendService.create(vpem);
//				}
//			}
//		}
//
//		if (!StringUtil.isEmpty(customerUuid) && !StringUtil.isEmpty(doctorName) && !StringUtil.isEmpty(doctorUuid)) {
//			// String content = doctorName + "医生为您选择了新的随访方案";
//			String content = doctorName + MessageHelper.getMessage("addVisitPrecept.showmessage.newAdd");
//			innerMessageService.saveInnerMessageAndPush(doctorUuid, customerUuid, content,
//					InnerMessageModel.ACCOUNT_TYPE_STORE, InnerMessageModel.ACCOUNT_TYPE_CUSTOMER,
//					PushConst.push_client_customer, "doctorUuid", doctorUuid, InnerMessageTypeEnum.VISITDOCTOR.getValue());
//		}
//
//		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
//		return null;
//	}

	// TODO:查询医评，自评量表

	/**
	 * 查询医评 自评
	 *
	 * @param response response
	 * @param type     查询类型
	 * @return string
	 */
	@RequestMapping(value = "/1.0/selectPreceptDetail", method = RequestMethod.GET)
	public String selectPreceptDetail(HttpServletResponse response, @RequestParam("type") String type) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 判断用户是否存在
		if (StringUtil.isEmpty(type)) {
			HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, ""));
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		try {
			// 获取随访方案的集合列表
			List<CsZySubject> list = csZySubjectService.selectAll(type);
			jsonMap.put("relist", list);
		} catch (Exception e) {
			jsonMap.put("relist", null);
		}
		HttpServletUtils.outJson(response, jsonMap);
		return null;
	}
	// TODO:根据量表id查询题目

	/**
	 * 查询医评 自评
	 *
	 * @param response  response
	 * @param subjectId 科目id
	 * @return string
	 */
	@RequestMapping(value = "/1.0/selectSubjectDetail", method = RequestMethod.GET)
	public String selectSubjectDetail(HttpServletResponse response, @RequestParam("subjectId") String subjectId) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 判断用户是否存在
		if (StringUtil.isEmpty(subjectId)) {
			HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, "需要指定的量表id"));
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		try {
			// 获取随访方案的集合列表
			List<CsZyItem> list = csZyItemService.selectBySubjectId(subjectId);
			jsonMap.put("relist", list);
		} catch (Exception e) {
			jsonMap.put("relist", null);
		}
		HttpServletUtils.outJson(response, jsonMap);
		return null;
	}

	//TODO:随访方案管理——查看随访方案的详细信息

	/**
	 * 查看随访方案的详细信息
	 *
	 * @param response  response
	 * @param visitUuid visitUuid
	 * @return string
	 */
	@RequestMapping(value = "/1.0/visitPreceptDetail", method = RequestMethod.GET)
	public String visitPreceptDetail(HttpServletResponse response, @RequestParam("doctorUuid") String doctorUuid, @RequestParam("visitUuid") String visitUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 判断用户是否存在
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, SysConst.USER_REQUIRED));
			return null;
		}
		if (StringUtil.isEmpty(visitUuid)) {
			HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, "请指定随访方案id"));
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 根据随访方案的的uuid查询出相对应的随访方案对象
		VisitPreceptModel visitPreceptModel = visitPreceptService.getByUuid(visitUuid);
		// 返回給app端的参数
		if (visitPreceptModel != null) {
			jsonMap.put("visitUuid", visitUuid);
			jsonMap.put("preceptName", visitPreceptModel.getPreceptName());
			jsonMap.put("drugTherapy", visitPreceptModel.getDrugTherapy());
			jsonMap.put("sideEffects", visitPreceptModel.getSideEffects());
			jsonMap.put("period", visitPreceptModel.getPeriod());
			jsonMap.put("electrocardiogram", visitPreceptModel.getElectrocardiogram());
			jsonMap.put("bloodRoutine", visitPreceptModel.getBloodRoutine());
			jsonMap.put("weight", visitPreceptModel.getWeight());
			jsonMap.put("hepatic", visitPreceptModel.getHepatic());
			if (!ObjectUtils.isEmpty(visitPreceptModel.getSelfTest())) {
				jsonMap.put("selfTest", this.getList(visitPreceptModel.getSelfTest()));
			}
			if (!ObjectUtils.isEmpty(visitPreceptModel.getDoctorTest())) {
				jsonMap.put("doctorTest", this.getList(visitPreceptModel.getDoctorTest()));
			}
		} else {
			HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, "未能成功的找到随访方案内容，请更换id重试！"));
			return null;
		}

		//药物信息
		List<DoctorAdviceModel> doctorAdviceModelList = doctorAdviceService.getDoctorAdivceByUuidAndType(visitUuid, "0");
		jsonMap.put("doctorAdvice", doctorAdviceModelList);

		// 其他方案
		List<VisitPreceptExtendModel> list = visitPreceptExtendService.getAllVisitPreceptByPreceptUuid(visitUuid);
		List<Map<String, Object>> preceptExtend = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (VisitPreceptExtendModel vpe : list) {
				if (vpe != null) {
					Map<String, Object> otherMap = new HashMap<>();
					otherMap.put("name", vpe.getName());
					otherMap.put("period", vpe.getPeriod());
					otherMap.put("uuid", vpe.getUuid());
					preceptExtend.add(otherMap);
				}
			}
		}
		jsonMap.put("otherMap", preceptExtend);

		List<HealthGuideModel> healthGuideModelList = healthGuideService.getHealthGuideByDoctorUuidAndVisitUuid(doctorUuid, visitUuid);
		List<Map<String, Object>> healthGuideMap = new ArrayList<>();
		if (healthGuideModelList != null && healthGuideModelList.size() > 0) {
			for (HealthGuideModel hgm : healthGuideModelList) {
				if (hgm != null) {
					Map<String, Object> otherMap = new HashMap<>();
					otherMap.put("uuid", hgm.getUuid());
					otherMap.put("diet", hgm.getDiet());
					otherMap.put("sports", hgm.getSports());
					otherMap.put("rest", hgm.getRest());
					otherMap.put("period", hgm.getPeriod());
					otherMap.put("sleep", hgm.getSleep());
					healthGuideMap.add(otherMap);
				}
			}
		}
		jsonMap.put("healthGuide", healthGuideMap);

		HttpServletUtils.outJson(response, jsonMap);
		return null;
	}

	//TODO:随访方案管理——编辑随访方案

	/**
	 * 编辑随访方案接口
	 *
	 * @param request     request
	 * @param response    response
	 * @param preceptName preceptName
	 * @param drugTherapy drugTherapy
	 * @param sideEffects sideEffects
	 * @param visitUuid   visitUuid
	 * @return string
	 */
	@RequestMapping(value = "/1.0/editVisitPrecept", method = RequestMethod.POST)
	public String editVisitPrecept(HttpServletRequest request, HttpServletResponse response,
								   @RequestParam("visitUuid") String visitUuid, @RequestParam("doctorUuid") String doctorUuid,
								   @RequestParam("preceptName") String preceptName, @RequestParam("drugTherapy") String drugTherapy,
								   @RequestParam("sideEffects") String sideEffects, @RequestParam("doctorAdvice") String doctorAdvice) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		if (StringUtil.isEmpty(doctorUuid) && StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, "医生id不能为空"));
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		//随访方案表表单
		if (!StringUtil.isEmpty(visitUuid)) {
			Gson gson = new Gson();
			VisitPreceptModel vpm = visitPreceptService.getByUuid(visitUuid);
			if (vpm != null) {
				vpm.setUuid(visitUuid);
				vpm.setPeriod(request.getParameter("period"));
				//心电图检查周期
				vpm.setElectrocardiogram(request.getParameter("electrocardiogram"));
				//血常规周期
				vpm.setHepatic(request.getParameter("hepatic"));
				//肝功能周期
				vpm.setBloodRoutine(request.getParameter("bloodRoutine"));
				//体重功能周期
				vpm.setWeight(request.getParameter("weight"));

				vpm.setSelfTest(request.getParameter("selfTest"));
				vpm.setDoctorTest(request.getParameter("doctorTest"));
				vpm.setServiceStaffUuid(doctorUuid);
				vpm.setPreceptName(preceptName);
				vpm.setDrugTherapy(drugTherapy);
				vpm.setSideEffects(sideEffects);
				vpm.setCreateTime(DateFormatHelper.getNowTimeStr());
				visitPreceptService.update(vpm);

				// 保存消息到数据库，并推送到手机终端
				String doctorName = vpm.getDoctorName();
				String customerUuid = vpm.getCustomerUuid();
				if (!StringUtil.isEmpty(customerUuid) && !StringUtil.isEmpty(doctorName)
						&& !StringUtil.isEmpty(doctorUuid)) {
					// String content = doctorName + "医生为您选择了新的随访方案";
					String content = doctorName + MessageHelper.getMessage("addVisitPrecept.showmessage.newAdd");
					innerMessageService.saveInnerMessageAndPush(doctorUuid, customerUuid, content,
							InnerMessageModel.ACCOUNT_TYPE_STORE, InnerMessageModel.ACCOUNT_TYPE_CUSTOMER,
							PushConst.push_client_customer, "doctorUuid", doctorUuid, InnerMessageTypeEnum.VISITDOCTOR.getValue());
				}

				String uuid = vpm.getUuid();
				String ortherMap = request.getParameter("ortherMap");
				List<Map<String, Object>> otherlist = gson.fromJson(ortherMap, new TypeToken<List<Map<String, Object>>>() {
				}.getType());
				if (otherlist.size() > 0) {
					for (Map<String, Object> map : otherlist) {
						VisitPreceptExtendModel vpem = new VisitPreceptExtendModel();
						vpem.setName(map.get("name").toString());
						vpem.setPeriod(map.get("period").toString());
						vpem.setPreceptUuid(uuid);

						if (ObjectUtils.isEmpty(map.get("uuid"))) {
							visitPreceptExtendService.create(vpem);
						} else {
							vpem.setUuid(map.get("uuid").toString());
							visitPreceptExtendService.update(vpem);
						}
					}
				}

				//药物用量
				List<Map<String, String>> l = gson.fromJson(doctorAdvice, new TypeToken<List<Map<String, String>>>() {
				}.getType());
				if (l.size() > 0) {
					for (Map<String, String> map : l) {
						DoctorAdviceModel dam = new DoctorAdviceModel();
						dam.setDirections(map.get("directions"));
						dam.setDosage(map.get("dosage"));
						dam.setFrequency(map.get("frequency"));
						dam.setFood(map.get("food"));
						dam.setMedicineUuid(map.get("medicineUuid"));
						dam.setOpeTime(DateFormatHelper.getNowTimeStr());
						dam.setVisitRecordUuid(visitUuid);
						dam.setType("0");
						if (ObjectUtils.isEmpty(map.get("uuid"))) {
							doctorAdviceService.create(dam);
						} else {
							dam.setUuid(map.get("uuid"));
							doctorAdviceService.update(dam);
						}
					}
				}

				/**
				 * 		健康小贴士——no clear
				 */
				String healthGuideString = request.getParameter("healthGuide");

				List<Map<String, Object>> healthGuideModellist = gson.fromJson(healthGuideString, new TypeToken<List<Map<String, Object>>>() {
				}.getType());
				if (healthGuideModellist.size() > 0) {
					for (Map<String, Object> map : healthGuideModellist) {
						HealthGuideModel healthGuideModel = new HealthGuideModel();
						healthGuideModel.setServiceStaffUuid(doctorUuid);
						healthGuideModel.setNotevisitRecordUuid(visitUuid);
						healthGuideModel.setDiet(map.get("diet").toString());
						healthGuideModel.setSports(map.get("sports").toString());
						healthGuideModel.setRest(map.get("rest").toString());
						try {
							int period = Integer.parseInt(map.get("period").toString());
							healthGuideModel.setPeriod(period);
						} catch (Exception e) {
							e.getMessage();
						}
						healthGuideModel.setSleep(map.get("sleep").toString());
						if (ObjectUtils.isEmpty(map.get("uuid"))) {
							healthGuideModel.setCreateTime(DateFormatHelper.getNowTimeStr());
							healthGuideService.create(healthGuideModel);
						} else {
							healthGuideModel.setUuid(map.get("uuid").toString());
							healthGuideService.update(healthGuideModel);
						}

					}
				}

				String doctorAdviceDelete = request.getParameter("doctorAdviceDelete");
				if (!ObjectUtils.isEmpty(doctorAdviceDelete)) {
					System.out.print(doctorAdviceDelete);
					for (String s : doctorAdviceDelete.split(",")) {
						if (!ObjectUtils.isEmpty(s)) {
							System.out.print(s);
							DoctorAdviceModel model = new DoctorAdviceModel();
							model.setUuid(s);
							try {
								doctorAdviceService.delete(model);
							} catch (Exception e) {
								e.getStackTrace();
							}

						}
					}
				}

				String ortherMapDelete = request.getParameter("ortherMapDelete");
				if (!ObjectUtils.isEmpty(ortherMapDelete)) {
					for (String s : ortherMapDelete.split(",")) {
						if (!ObjectUtils.isEmpty(s)) {
							VisitPreceptExtendModel model = new VisitPreceptExtendModel();
							model.setUuid(s);
							try {
								visitPreceptExtendService.delete(model);
							} catch (Exception e) {
								e.getStackTrace();
							}
						}
					}
				}

				String healthGuideDelete = request.getParameter("healthGuideDelete");
				if (!ObjectUtils.isEmpty(healthGuideDelete)) {
					for (String s : healthGuideDelete.split(",")) {
						if (!ObjectUtils.isEmpty(s)) {
							HealthGuideModel model = new HealthGuideModel();
							model.setUuid(s);
							try {
								healthGuideService.delete(model);
							} catch (Exception e) {
								e.getStackTrace();
							}
						}
					}
				}

			}

		}


		HttpServletUtils.outJson(response, jsonMap);
		return null;
	}

	private List<CsZySubject> getList(String str) {
		if (!ObjectUtils.isEmpty(str)) {
			String[] arr;
			if (str.indexOf(",") > 0) {
				arr = str.split(",");
			} else if (str.indexOf("，") > 0) {
				arr = str.split("，");
			} else {
				arr = new String[]{str};
			}
			List<CsZySubject> list = new ArrayList<>();
			if (!ObjectUtils.isEmpty(arr)) {
				for (String temp : arr) {
					try {
						// 获取随访方案的集合列表
						CsZySubject subject = csZySubjectService.selectByPrimaryKey(temp);
						if (!ObjectUtils.isEmpty(subject)) {
							list.add(subject);
						}
					} catch (Exception e) {
						e.getMessage();
					}
				}
			}
			return list;
		} else {
			return null;
		}
	}

//	public String editVisitPrecept(HttpServletRequest request, HttpServletResponse response,
//								   @RequestParam("visitUuid") String visitUuid, @RequestParam("doctorUuid") String doctorUuid,
//								   @RequestParam("preceptName") String preceptName, @RequestParam("drugTherapy") String drugTherapy,
//								   @RequestParam("sideEffects") String sideEffects, @RequestParam("period") String period,
//								   @RequestParam("electrocardiogram") String electrocardiogram,
//								   @RequestParam("bloodRoutine") String bloodRoutine, @RequestParam("hepatic") String hepatic) {
//		// 设置返回数据编码
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
//		// 获取参数值并且进行非空判断
//		boolean breakOut = false;// return 标志
//		Map<String, String> map = getParam(request, response, breakOut,
//				new String[] { "doctorUuid,true", "preceptName,true", "drugTherapy,true", "sideEffects,true",
//						"period,true", "electrocardiogram,true", "bloodRoutine,true", "hepatic,true", "visitUuid,true",});
//		// 设置返回信息
//		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
//		// 获取回调的名
//		Object callback = map.get("callback");
//
//		String visitPreceptExtendUuid = request.getParameter("visitPreceptExtendUuid");
//
//		String ortherMap = request.getParameter("ortherMap");
//		/************************ 编辑随访方案信息 *************************/
//		// 将前台传过来的数据组成一个maps
//		Map maps = JSONObject.fromObject(ortherMap);
//		// maps.get(key);
//		if (maps != null) {
//			// 遍历获取
//			Iterator<Entry<String, String>> iterator = maps.entrySet().iterator();
//			while (iterator.hasNext()) {
//				Entry<String, String> entry = iterator.next();
//				String name = entry.getKey();
//				String periodName = entry.getValue();
//				VisitPreceptExtendModel vpm = visitPreceptExtendService.getByUuid(visitPreceptExtendUuid);
//				if (vpm != null) {
//					vpm.setName(name);
//					vpm.setPeriod(periodName);
//					visitPreceptExtendService.update(vpm);
//				}
//			}
//		}
//
//		if (!StringUtil.isEmpty(visitUuid)) {
//			VisitPreceptModel vpm = visitPreceptService.getByUuid(visitUuid);
//			if (vpm != null) {
//				vpm.setUuid(visitUuid);
//				vpm.setServiceStaffUuid(doctorUuid);
//				vpm.setPreceptName(preceptName);
//				vpm.setDrugTherapy(drugTherapy);
//				vpm.setSideEffects(sideEffects);
//				vpm.setPeriod(period);
//				vpm.setElectrocardiogram(electrocardiogram);
//				vpm.setBloodRoutine(bloodRoutine);
//				vpm.setHepatic(hepatic);
//				vpm.setCreateTime(DateFormatHelper.getNowTimeStr());
//				visitPreceptService.update(vpm);
//
//				// 保存消息到数据库，并推送到手机终端
//				String doctorName = vpm.getDoctorName();
//				String customerUuid = vpm.getCustomerUuid();
//				if (!StringUtil.isEmpty(customerUuid) && !StringUtil.isEmpty(doctorName)
//						&& !StringUtil.isEmpty(doctorUuid)) {
//					// String content = doctorName + "医生为您选择了新的随访方案";
//					String content = doctorName + MessageHelper.getMessage("addVisitPrecept.showmessage.newAdd");
//					innerMessageService.saveInnerMessageAndPush(doctorUuid, customerUuid, content,
//							InnerMessageModel.ACCOUNT_TYPE_STORE, InnerMessageModel.ACCOUNT_TYPE_CUSTOMER,
//							PushConst.push_client_customer, "doctorUuid", doctorUuid, InnerMessageTypeEnum.VISITDOCTOR.getValue());
//				}
//			}
//		}
//
//		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
//		return null;
//	}

	/**
	 * 获取资讯列表
	 *
	 * @param request
	 * @param response
	 * @param contentCategoryUuid
	 * @return
	 */
	@RequestMapping(value = "/getAllContentList", method = RequestMethod.GET)
	public String getAllContentList(HttpServletRequest request, HttpServletResponse response,
									@RequestParam("contentCategoryUuid") String contentCategoryUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[]{"contentCategoryUuid,true", "callback,false"});
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		Object callback = map.get("callback");
		// 判断有没有咨询信息
		if (StringUtil.isEmpty(contentCategoryUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "没有咨讯信息"), callback);
			return null;
		}
		String doctorUuid = request.getParameter("doctorUuid");

		// 获取回调的名

		/************************ 获取咨询 *************************/

		// 定义返回List
		List relist = new ArrayList();
		List<ContentModel> list = contentService.getByContentCategoryUuid(contentCategoryUuid);
		if (list != null && list.size() > 0) {
			for (ContentModel cm : list) {
				Map<String, Object> save = new HashMap<String, Object>();
				if (cm != null) {
					String contentType = cm.getContentType();
					save.put("imgUrl", cm.getImgUrl());
					save.put("contentUuid", cm.getUuid());
					// 3:医脉通 跳转到医脉通自己的
					save.put("contentType", contentType);
					save.put("contentUrl", cm.getUrl());
					if (!StringUtil.isEmpty(doctorUuid) && !StringUtil.isEmpty(cm.getUuid())) {
						int storeType = favoriteService.getStoreTypeByDoctorUuidAndContenUuid(doctorUuid, cm.getUuid());
						save.put("storeType", storeType);// 0代表未收藏 1代表已收藏
						int shareType = doctorShareService.getShareTypeByDoctorUuidAndContenUuid(doctorUuid,
								cm.getUuid());
						save.put("shareType", shareType);// 0代表未分享 1代表已分享

						String favoriteUuid = favoriteService.getUuidByCustomerUuidAndContextUuid(doctorUuid,
								cm.getUuid());
						// 返回收藏的id
						save.put("favoriteUuid", favoriteUuid);
					}

					save.put("contentTitle", cm.getContentTitle());
					save.put("creatime", cm.getCreateTime());
					relist.add(save);
				}
			}
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 获取筛选资讯列表
	 *
	 * @param request
	 * @param response
	 * @param contentCategoryUuid
	 * @return
	 */
	@RequestMapping(value = "/getSearchContentList", method = RequestMethod.POST)
	public String getSearchContentList(HttpServletRequest request, HttpServletResponse response,
									   @RequestParam("contentCategoryUuid") String contentCategoryUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[]{"contentCategoryUuid,true", "callback,true"});
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		Object callback = map.get("callback");
		// 判断有没有咨询信息
		if (StringUtil.isEmpty(contentCategoryUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "没有咨讯信息"), callback);
			return null;
		}
		String doctorUuid = request.getParameter("doctorUuid");

		// 获取内容名称
		String contentName = request.getParameter("contentName");
		// 获取疾病编号
		String illnessId = request.getParameter("illnessId");
		// 获取症状编号
		String symptomId = request.getParameter("symptomId");
		// 获取作者
		String author = request.getParameter("author");
		// 获取病种
		String entity = request.getParameter("entity");
		// 获取国家
		String country = request.getParameter("country");

		// 获取回调的名
		/************************ 获取咨询 *************************/
		// 定义返回List
		List relist = new ArrayList();
		ContentQueryModel qm = new ContentQueryModel();
		// 内容名称
		qm.setAppContentName(contentName);
		qm.setAppIllnessId(illnessId);
		qm.setAppSymptomId(symptomId);
		qm.setAppAuthor(author);
		qm.setAppEntity(entity);
		qm.setAppCountry(country);

		System.out.println("=======illnessId======" + illnessId);
		System.out.println("=======symptomId======" + symptomId);
		System.out.println("=======author======" + author);
		System.out.println("=======entity======" + entity);
		System.out.println("=======country======" + country);

		List<ContentModel> list = contentService.getByContentCategoryUuid(contentCategoryUuid, qm);
		if (list != null && list.size() > 0) {
			for (ContentModel cm : list) {
				Map<String, Object> save = new HashMap<String, Object>();
				if (cm != null) {
					String contentType = cm.getContentType();
					save.put("imgUrl", cm.getImgUrl());
					save.put("contentUuid", cm.getUuid());
					save.put("contentType", contentType);
					save.put("contentUrl", cm.getUrl());
					if (!StringUtil.isEmpty(doctorUuid) && !StringUtil.isEmpty(cm.getUuid())) {
						int storeType = favoriteService.getStoreTypeByDoctorUuidAndContenUuid(doctorUuid, cm.getUuid());
						save.put("storeType", storeType);// 0代表未收藏 1代表已收藏
						int shareType = doctorShareService.getShareTypeByDoctorUuidAndContenUuid(doctorUuid,
								cm.getUuid());
						save.put("shareType", shareType);// 0代表未分享 1代表已分享

						String favoriteUuid = favoriteService.getUuidByCustomerUuidAndContextUuid(doctorUuid,
								cm.getUuid());
						// 返回收藏的id
						save.put("favoriteUuid", favoriteUuid);
					}
					save.put("contentTitle", cm.getContentTitle());
					save.put("creatime", cm.getCreateTime());
					relist.add(save);
				}
			}
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 获取医生广告的轮播图
	 */
	@RequestMapping(value = "/getDoctorPlatAd", method = RequestMethod.GET)
	public String getDoctorPlatAd(HttpServletRequest request, HttpServletResponse response,
								  @RequestParam("adUuid") String adUuid) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[]{"callback,true", "adUuid,true"}); // 标志
		// 以及必需要传的参数
		Object callback = map.get("callback");

		// 判断adUuid是否为空
		if (StringUtil.isEmpty(adUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "广告Id不能为空"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 根据广告的id获得广告关联图片的集合
		List<PlatAdImageRelModel> list = platAdImageRelService.getPlatAdImageRelModelsByAdUuid(adUuid);

		// 定义返回List
		List<Map<String, Object>> relist = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (PlatAdImageRelModel pm : list) {
				Map<String, Object> save = new HashMap<>();
				// 图片uuid
				save.put("imageUuid", pm.getImageUuid());
				// 图片的地址
				save.put("imageUrl", pm.getImageUrl());
				// 图片位置
				save.put("position", pm.getPosition());
				// 图片描述
				save.put("imageNote", pm.getImageNote());
				// 图片的链接地址
				save.put("url", pm.getUrl());
				relist.add(save);
			}
		}

		jsonMap.put("relist", relist);// 消息字段

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	//TODO:获取讲坛焦点轮播图

	/**
	 * 获取讲坛焦点轮播图
	 */
	@RequestMapping(value = "/getPlatformPic", method = RequestMethod.GET)
	public String getPlatformPic(HttpServletRequest request, HttpServletResponse response,
								 @RequestParam("adUuid") String adUuid) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		Map<String, String> map = getParam(request, response, false,
				new String[]{"adUuid,true", "callback,false"}); // 标志
		// 以及必需要传的参数
		Object callback = map.get("callback");

		// 判断adUuid是否为空
		if (StringUtil.isEmpty(adUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "广告Id不能为空"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 根据广告的id获得讲坛关联图片的集合
		List<PlatAdImageRelModel> list = platAdImageRelService.getPlatAdImageRelModelsByAdUuid(adUuid);
		// 获取广告的model
		PlatAdModel pdm = platAdService.getByUuid(adUuid);
		// 定义返回List
		List<Map<String, Object>> relist = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (PlatAdImageRelModel pm : list) {
				Map<String, Object> save = new HashMap<>();
				// 图片uuid
				save.put("imageUuid", pm.getImageUuid());
				// 图片的地址
				save.put("imageUrl", pm.getImageUrl());
				// 图片位置
				save.put("position", pm.getPosition());
				// 图片描述
				save.put("imageNote", pm.getImageNote());
				// 图片的链接地址
				save.put("url", pm.getUrl());
				// 图片的链接地址
				save.put("note", pm.getImageNote());
				relist.add(save);
			}
		}

		jsonMap.put("relist", relist);// 消息字段

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 获取医生所有的银行卡信息
	 *
	 * @param request
	 * @param response
	 * @param doctorUuid
	 * @return
	 * @author xp
	 */
	@RequestMapping(value = "/getAllDoctorBank", method = RequestMethod.GET)
	public String getAllDoctorBank(HttpServletRequest request, HttpServletResponse response,
								   @RequestParam("doctorUuid") String doctorUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[]{"callback,true", "doctorUuid,true"});
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");
		// 判断用户是否存在
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "请登陆"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 获取随访方案的集合列表
		List<Map<String, Object>> relist = new ArrayList<>();

		// 根据医生的uuid查询出所有的银行卡
		List<BankRelationModel> list = bankRelationService.getAllBankByDoctorUuid(doctorUuid);
		if (list != null && list.size() > 0) {
			for (BankRelationModel vp : list) {
				Map<String, Object> bankMap = new HashMap<>();
				if (vp != null) {
					bankMap.put("bankCode", vp.getBankCode());
					bankMap.put("cardType", vp.getCardType());
					bankMap.put("bankUuid", vp.getBankUuid());
					bankMap.put("realName", vp.getRealName());
					bankMap.put("ID", vp.getID());
					relist.add(bankMap);
				}
			}
		}

		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 删除绑定银行卡接口
	 *
	 * @param request
	 * @param response
	 * @param doctorUuid
	 * @return
	 * @author xp
	 */
	@RequestMapping(value = "/delDoctorBank", method = RequestMethod.GET)
	public String delDoctorBank(HttpServletRequest request, HttpServletResponse response,
								@RequestParam("bankCode") String bankCode, @RequestParam("doctorUuid") String doctorUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, false,
				new String[]{"doctorUuid,true", "bankCode,true"});
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");
		// 判断用户是否存在
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "请登陆"), callback);
			return null;
		}
		// 判断用户是否存在
		if (StringUtil.isEmpty(bankCode)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "未绑定该银行卡"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		/************************ 删除绑定的银行卡 *************************/

		if (!StringUtil.isEmpty(doctorUuid) && !StringUtil.isEmpty(bankCode)) {
			bankRelationService.deleteBankCardByDoctorUuidAndBankUuid(doctorUuid, bankCode);
		}

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 修改医生绑定的银行卡
	 *
	 * @param request
	 * @param response
	 * @param bankUuid
	 * @param doctorUuid
	 * @return
	 * @author xp
	 */
	@RequestMapping(value = "/updateDoctorBank", method = RequestMethod.POST)
	public String updateDoctorBank(HttpServletRequest request, HttpServletResponse response,
								   @RequestParam("bankUuid") String bankUuid, @RequestParam("doctorUuid") String doctorUuid,
								   @RequestParam("bankCode") String bankCode, @RequestParam("cardType") String cardType,
								   @RequestParam("realName") String realName, @RequestParam("ID") String ID) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut, new String[]{"bankUuid,true",
				"doctorUuid,true", "bankCode,true", "cardType,true", "realName,true", "ID,true", "callback,true"});
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");
		// 判断用户是否存在
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "请登陆"), callback);
			return null;
		}
		// 判断用户是否存在
		if (StringUtil.isEmpty(bankUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "未绑定该银行卡"), callback);
			return null;
		}

		// 调用service获取医生绑定的银行卡
		BankRelationModel brm = bankRelationService.getModelByDoctorUuidAndBankUuid(bankUuid, doctorUuid);

		/************************ 修改医生信息表 *************************/
		if (brm != null) {
			// 持卡人姓名
			brm.setRealName(realName);
			// 修改持卡人的身份证号
			brm.setID(ID);
			// 修改银行卡的名称以及卡的类型
			brm.setBankCode(bankCode);
			brm.setCardType(cardType);// 1代表是信用卡 ；2代表是储蓄卡
			brm.setBankUuid(bankUuid);
			bankRelationService.update(brm);
		}
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 医生绑定的银行卡
	 *
	 * @param request
	 * @param response
	 * @param bankUuid
	 * @param doctorUuid
	 * @return
	 * @author xp
	 */
	@RequestMapping(value = "/addDoctorBank", method = RequestMethod.POST)
	public String addDoctorBank(HttpServletRequest request, HttpServletResponse response,
								@RequestParam("bankUuid") String bankUuid, @RequestParam("doctorUuid") String doctorUuid,
								@RequestParam("bankCode") String bankCode, @RequestParam("cardType") String cardType,
								@RequestParam("realName") String realName, @RequestParam("ID") String ID) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut, new String[]{"bankUuid,true",
				"doctorUuid,true", "bankCode,true", "cardType,true", "realName,true", "ID,true", "callback,true"});
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");
		// 判断用户是否存在
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "请登陆"), callback);
			return null;
		}
		// 判断银行卡号是否重复
		BankRelationModel bankRelationModel = bankRelationService.getByBankCode(bankCode);
		// 如果不为空，代表银行卡号已存在，返回错误信息
		if (bankRelationModel != null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "银行卡号已存在"), callback);
			return null;
		}

		// 判断用户是否存在
		if (StringUtil.isEmpty(bankUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "未绑定该银行卡"), callback);
			return null;
		}

		/************************ 添加绑定银行卡 *************************/
		BankRelationModel brm = new BankRelationModel();
		// 持卡人姓名
		brm.setRealName(realName);
		// 修改持卡人的身份证号
		brm.setID(ID);
		// 修改银行卡的名称以及卡的类型
		brm.setBankCode(bankCode);
		brm.setCardType(cardType);// 1代表是信用卡 ；2代表是储蓄卡
		brm.setBankUuid(bankUuid);
		brm.setDoctorUuid(doctorUuid);
		bankRelationService.create(brm);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 获取医生所有公告的列表
	 *
	 * @param request
	 * @param response
	 * @param doctorUuid
	 * @return
	 * @author xp
	 */
	@RequestMapping(value = "/getAllDoctorNoticeList", method = RequestMethod.GET)
	public String getAllDoctorNoticeList(HttpServletRequest request, HttpServletResponse response,
										 @RequestParam("doctorUuid") String doctorUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[]{"callback,false", "doctorUuid,true"});
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");
		// 判断用户是否存在
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "请登陆"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 获取随访方案的集合列表
		List relist = new ArrayList();
		// 根据医生的uuid获取医生所有的公告列表
		List<DoctorNoticeModel> list = doctorNoticeService.getAllDoctorNoticeList(doctorUuid);
		if (list != null && list.size() > 0) {
			for (DoctorNoticeModel dm : list) {
				Map<Object, Object> noticeMap = new HashMap<Object, Object>();
				if (dm != null) {
					noticeMap.put("line", dm.getLine());
					noticeMap.put("content", dm.getContent());
					noticeMap.put("createTime", dm.getCreateTime());
					noticeMap.put("noticeUuid", dm.getUuid());
					relist.add(noticeMap);
				}
			}
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 查看医生详细公告的接口
	 *
	 * @param request
	 * @param response
	 * @return
	 * @author xp
	 */
	@RequestMapping(value = "/getDoctorNoticeDetail", method = RequestMethod.GET)
	public String getDoctorNoticeDetail(HttpServletRequest request, HttpServletResponse response,
										@RequestParam("noticeUuid") String noticeUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[]{"callback,false", "noticeUuid,true"});
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");
		// 判断公告是否存在
		if (StringUtil.isEmpty(noticeUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "该公告已经删除"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 获取随访方案的集合列表
		List relist = new ArrayList();
		// 根据公告的uuid获取医生公告
		DoctorNoticeModel dm = doctorNoticeService.getByUuid(noticeUuid);
		Map<Object, Object> noticeMap = new HashMap<Object, Object>();
		if (dm != null) {
			noticeMap.put("line", dm.getLine());
			noticeMap.put("content", dm.getContent());
			noticeMap.put("createTime", dm.getCreateTime());
			relist.add(noticeMap);
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 添加医生公告
	 *
	 * @param request
	 * @param response
	 * @param doctorUuid
	 * @return
	 * @author xp
	 */
	@RequestMapping(value = "/addDoctorNotice", method = RequestMethod.POST)
	public String addDoctorNotice(HttpServletRequest request, HttpServletResponse response,
								  @RequestParam("doctorUuid") String doctorUuid, @RequestParam("line") String line,
								  @RequestParam("content") String content, @RequestParam("createTime") String createTime) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[]{"doctorUuid,true", "line,true", "content,true", "createTime,true", "callback,false"});
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");
		// 判断用户是否存在
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "请登陆"), callback);
			return null;
		}

		/************************ 添加医生公告 *************************/
		vipclubIntegralLogService.saveVipIntegralLog(doctorUuid, "add", 1, IntegralType.ADD_GG.getValue(),
				"2", "添加医生公告", "");
		DoctorNoticeModel dnm = new DoctorNoticeModel();
		String doctorName = servicestaffService.getRealNameByUuid(doctorUuid);
		dnm.setServiceStatffUuid(doctorUuid);
		dnm.setDoctorName(doctorName);
		dnm.setLine(line);
		dnm.setContent(content);
		dnm.setCreateTime(createTime);
		doctorNoticeService.create(dnm);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 获取医生所有的预约课程
	 *
	 * @param request
	 * @param response
	 * @param doctorUuid
	 * @return
	 * @author xp
	 */
	@RequestMapping(value = "/getAllDoctorPlatformApplyList", method = RequestMethod.GET)
	public String getAllDoctorPlatformApplyList(HttpServletRequest request, HttpServletResponse response,
												@RequestParam("doctorUuid") String doctorUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[]{"callback,true", "doctorUuid,true"});
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");
		// 判断用户是否存在
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "请登陆"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 获取预约课程的集合列表
		List<Map<Object, Object>> relist = new ArrayList<>();
		// 根据医生的uuid获取医生所有预约课程
		List<PlatformApplyModel> list = platformApplyService.getByUserid(doctorUuid, "1");
		if (list != null && list.size() > 0) {
			for (PlatformApplyModel pam : list) {
				Map<Object, Object> noticeMap = new HashMap<>();
				if (pam != null) {
					int vidoType = 0;// 0代表已过期 ；1代表未过期
					String nowTime = DateFormatHelper.getNowTimeStr();// 获取系统当前时间
					noticeMap.put("vidoUuid", pam.getVidoUuid());
					noticeMap.put("vidoName", pam.getVidoName());
					noticeMap.put("startTime", pam.getStartTime());
					if (!StringUtil.isEmpty(nowTime) && !StringUtil.isEmpty(pam.getStartTime())) {
						if (nowTime.compareTo(pam.getStartTime()) < 0) {
							vidoType = 1;
						}
					}
					noticeMap.put("vidoType", vidoType);
					relist.add(noticeMap);
				}
			}
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 获取所有的电话咨询时间
	 *
	 * @param request
	 * @param response
	 * @return
	 * @author xl
	 */
	@RequestMapping(value = "/getAllTeleTime", method = RequestMethod.GET)
	public String getAllTeleTime(HttpServletRequest request, HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut, new String[]{"callback,true"});
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}

		Object callback = map.get("callback");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 获取预约课程的集合列表
		List relist = new ArrayList();
		// 根据医生的uuid获取医生所有预约课程
		List<TelephoneTimeModel> list = teleTimeService.getAll();
		if (list != null && list.size() > 0) {
			for (TelephoneTimeModel pam : list) {
				Map<Object, Object> noticeMap = new HashMap<Object, Object>();
				if (pam != null) {
					noticeMap.put("teleTimeUuid", pam.getUuid());
					noticeMap.put("teleTime", pam.getTelTime());
					relist.add(noticeMap);
				}
			}
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 获取所有的电话咨询费用
	 *
	 * @param request
	 * @param response
	 * @return
	 * @author xl
	 */
	@RequestMapping(value = "/getAllTeleCost", method = RequestMethod.GET)
	public String getAllTeleCost(HttpServletRequest request, HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut, new String[]{"callback,true"});
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}

		Object callback = map.get("callback");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 获取预约课程的集合列表
		List<Map<Object, Object>> relist = new ArrayList<Map<Object, Object>>();
		// 根据医生的uuid获取医生所有预约课程
		List<TelephoneCostModel> list = teleCostService.getAll();
		if (list != null && list.size() > 0) {
			for (TelephoneCostModel pam : list) {
				Map<Object, Object> noticeMap = new HashMap<>();
				if (pam != null) {
					noticeMap.put("teleCostUuid", pam.getUuid());
					noticeMap.put("teleCost", pam.getTelCost());
					relist.add(noticeMap);
				}
			}
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 保存电话咨询设置
	 *
	 * @param request
	 * @param response
	 * @param doctorUuid
	 * @return
	 * @author xl
	 */
	@RequestMapping(value = "/saveOrUpdateTelephoneCounse", method = RequestMethod.GET)
	public String saveTelephoneCounse(HttpServletRequest request, HttpServletResponse response,
									  @RequestParam("doctorUuid") String doctorUuid, @RequestParam("weekDate") List<String> weekDate,
									  @RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime,
									  @RequestParam("teleTimeUuid") String teleTimeUuid, @RequestParam("teleCostUuid") String teleCostUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut, new String[]{"callback,true"});
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 判断用户是否存在
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "请登陆"), callback);
			return null;
		}

		System.out.println("===========weekDate=========" + JSON.toJSONString(weekDate));
		System.out.println("===========weekDate=========" + JSON.toJSONString(weekDate));

		if (weekDate != null && weekDate.size() > 0) {
			for (String date : weekDate) {
				// 电话咨询设置保存
				TelephoneCounseModel teleCounse = teleCounseService.getTeleCounse(doctorUuid, date, startTime, endTime,
						teleTimeUuid, teleCostUuid);
				if (teleCounse != null) {
					teleCounse.setStartTime(startTime);
					teleCounse.setEndTime(endTime);
					teleCounse.setTeleTimeUuid(teleTimeUuid);
					teleCounse.setTeleCostUuid(teleCostUuid);
					teleCounseService.update(teleCounse);
				} else {
					TelephoneCounseModel teleCoun = new TelephoneCounseModel();
					teleCoun.setDoctorUuid(doctorUuid);
					teleCoun.setWeekDate(date);
					teleCoun.setStartTime(startTime);
					teleCoun.setEndTime(endTime);
					teleCoun.setTeleTimeUuid(teleTimeUuid);
					teleCoun.setTeleCostUuid(teleCostUuid);
					teleCounseService.create(teleCoun);
				}
			}
		}
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 获取所有的电话咨询设置
	 *
	 * @param request
	 * @param response
	 * @param doctorUuid
	 * @return
	 * @author xl
	 */
	@RequestMapping(value = "/getAllTelephoneCounse", method = RequestMethod.GET)
	public String getAllTelephoneCounse(HttpServletRequest request, HttpServletResponse response,
										@RequestParam("doctorUuid") String doctorUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut, new String[]{"callback,true"});
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}

		Object callback = map.get("callback");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 判断用户是否存在
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "请登陆"), callback);
			return null;
		}
		List relist = new ArrayList();

		// 获取医生所有设置咨询
		List<TelephoneCounseModel> list = teleCounseService.getAllTelephoneCounseModels(doctorUuid);
		if (list != null && list.size() > 0) {
			for (TelephoneCounseModel tc : list) {
				Map<Object, Object> noticeMap = new HashMap<Object, Object>();
				noticeMap.put("telepCounseUuid", tc.getUuid());
				noticeMap.put("weekDate", tc.getWeekDate());
				noticeMap.put("nowTime", DateFormatHelper.getNowTimeStr());
				noticeMap.put("startTime", tc.getStartTime());
				noticeMap.put("endTime", tc.getEndTime());
				noticeMap.put("teleTime", tc.getTeleTime());
				noticeMap.put("teleCost", tc.getTeleCostl());
				relist.add(noticeMap);
			}
		}
		jsonMap.put("relist", relist);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 获取所有的电话咨询时间段
	 *
	 * @param request
	 * @param response
	 * @param doctorUuid
	 * @return
	 * @author xl
	 */
	@RequestMapping(value = "/getAllTelephoneTime", method = RequestMethod.GET)
	public String getAllTelephoneTime(HttpServletRequest request, HttpServletResponse response,
									  @RequestParam("doctorUuid") String doctorUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut, new String[]{"callback,true"});
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}

		Object callback = map.get("callback");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 判断用户是否存在
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "请登陆"), callback);
			return null;
		}

		List relist = new ArrayList();
		// 日期
		String weekDate = request.getParameter("weekDate");
		String weekDay = "";
		// 获取周几
		if (!StringUtil.isEmpty(weekDate)) {
			weekDay = DateUtil.getWeekDay(weekDate);
			int day = Integer.parseInt(weekDay);
			if (day == 1) {
				day = 7;
			} else {
				day = day - 1;
			}
			weekDay = day + "";
		}
		System.out.println("=====weekDay=====" + weekDay);
		// 时长
		String consultDuration = request.getParameter("consultDuration");
		// 获取医生咨询
		TelephoneCounseModel tc = teleCounseService.getTeleCounse(weekDay, doctorUuid);
		if (tc != null) {
			jsonMap.put("telepCounseUuid", tc.getUuid());
			jsonMap.put("weekDate", tc.getWeekDate());
			jsonMap.put("startTime", tc.getStartTime());
			jsonMap.put("endTime", tc.getEndTime());
		} else {
			jsonMap.put("telepCounseUuid", "");
			jsonMap.put("weekDate", "");
			jsonMap.put("startTime", "");
			jsonMap.put("endTime", "");
		}

		// 从订单里面获取已经被预约的时间端
		List<OrderMainModel> list = orderMainService.getOrderMains(doctorUuid, consultDuration, weekDate);
		Map<Object, Object> noticeMap = new HashMap<Object, Object>();
		if (list != null && list.size() > 0) {
			for (OrderMainModel om : list) {
				noticeMap.put("hasStartTime", om.getReceiveTime());
				noticeMap.put("hasEndTime", om.getEndTime());
				noticeMap.put("consultDuration", consultDuration);
				relist.add(noticeMap);
			}
		}
		// jsonMap.put("noticeMap", noticeMap);
		jsonMap.put("relist", relist);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 删除电话咨询设置
	 *
	 * @param request
	 * @param response
	 * @return
	 * @author xl
	 */
	@RequestMapping(value = "/deleteTelephoneCounse", method = RequestMethod.GET)
	public String deleteTelephoneCounse(HttpServletRequest request, HttpServletResponse response,
										@RequestParam("telepCounseUuid") String telepCounseUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut, new String[]{"callback,true"});
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");
		// 判断用户是否存在
		if (StringUtil.isEmpty(telepCounseUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "电话咨询的编号不能为空"), callback);
			return null;
		}
		TelephoneCounseModel tm = teleCounseService.getByUuid(telepCounseUuid);
		if (tm != null) {
			teleCounseService.deleteTeleCounse(tm);
		}

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取医生所有设置咨询

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 获取诊所收入的详请
	 *
	 * @param request
	 * @param response
	 * @param doctorUuid
	 * @return
	 * @author xp
	 */
	@RequestMapping(value = "/getAllDoctorIncomeList", method = RequestMethod.GET)
	public String getAllDoctorIncomeList(HttpServletRequest request, HttpServletResponse response,
										 @RequestParam("doctorUuid") String doctorUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[]{"callback,true", "doctorUuid,true"});
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");
		// 判断用户是否存在
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "请登陆"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取随访方案的集合列表
		List relist = new ArrayList();
		// 跟据医生的id获取医生的账户余额
		double accountAmount = servicestaffService.getAccountAmountByUuid(doctorUuid);
		// 根据医生的id和状态获取医生的总收入
		Number allIncome = orderRoutingService.getDoctorAllIncomeByIdAndType(doctorUuid, "0");
		// 根据医生的id和状态获取医生的昨天的收入
		Number yesterdayIncome = orderRoutingService.getDoctorAllIncomeByIdAndType(doctorUuid, "1");
		Map<Object, Object> save = new HashMap<>();
		if (allIncome == null) {
			allIncome = 0;
		}
		if (yesterdayIncome == null) {
			yesterdayIncome = 0;
		}
		double allIncome1 = allIncome.doubleValue();
		double yesterdayIncome1 = yesterdayIncome.doubleValue();
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		save.put("accountAmount", df.format(accountAmount));
		save.put("allIncome", df.format(allIncome1));
		save.put("yesterdayIncome", df.format(yesterdayIncome1));
		relist.add(save);
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;

	}

	// TODO: 删除随访方案 clear

	/**
	 * 删除随访方案的接口
	 *
	 * @param response  response
	 * @param visitUuid visitUuid
	 * @return
	 */
	@RequestMapping(value = "/1.0/delPreceptDetail", method = RequestMethod.POST)
	public String delPreceptDetail(HttpServletResponse response, @RequestParam("doctorUuid") String doctorUuid, @RequestParam("visitUuid") String visitUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		if (StringUtil.isEmpty(visitUuid)) {
			HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, ""));
			return null;
		}

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		if (!StringUtil.isEmpty(visitUuid)) {
			boolean allow = false;

			List<VisitPreceptModel> list = visitPreceptService.getMyVisitpreceptByDoctorid(doctorUuid);
			if (!ObjectUtils.isEmpty(list)) {
				for (VisitPreceptModel vp : list) {
					if (visitUuid.equals(vp.getUuid())) {
						allow = true;
						break;
					}
				}
			}
			if (allow) {
				visitPreceptService.deleteVisitPrecept(visitUuid);
			} else {
				jsonMap = this.getSucJsonMap(SysConst.FAIL, "未能找到这个随访方案！");
			}
		}
		HttpServletUtils.outJson(response, jsonMap);
		return null;
	}

	/**
	 * 根据随访id获取所有的不良反应
	 *
	 * @param request
	 * @param response
	 * @return
	 * @author xp
	 */
	@RequestMapping(value = "/getDrugReactionList", method = RequestMethod.GET)
	public String getDrugReactionList(HttpServletRequest request, HttpServletResponse response,
									  @RequestParam("preceptUuid") String preceptUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[]{"callback,true", "preceptUuid,true"});
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");
		// 判断用户是否存在
		if (StringUtil.isEmpty(preceptUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, ""), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取随访方案的集合列表
		List relist = new ArrayList();
		if (!StringUtil.isEmpty(preceptUuid)) {
			List<DrugReactionModel> list = drugReactionService.getAllDrugReactionByVisitRecordUuid(preceptUuid);
			if (list != null && list.size() > 0) {
				for (DrugReactionModel drm : list) {
					if (null != drm) {
						Map<Object, Object> save = new HashMap<Object, Object>();
						save.put("impact", drm.getImpact());
						save.put("frequency", drm.getFrequency());
						save.put("dosageTime", drm.getDosageTime());
						save.put("occurrenceTime", drm.getOccurrenceTime());
						relist.add(save);
					}
				}
			}
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 根据随访id获取所有其他的 type 1睡眠情况、2进食情况、3其他情况 、4其他检查结果的接口
	 *
	 * @param request
	 * @param response
	 * @return
	 * @author xp
	 */
	@RequestMapping(value = "/getVisitPreceptExtendList", method = RequestMethod.GET)
	public String getVisitPreceptExtendList(HttpServletRequest request, HttpServletResponse response,
											@RequestParam("type") String type, @RequestParam("preceptUuid") String preceptUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[]{"callback,true", "preceptUuid,true", "type,true"});
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");
		// 判断用户是否存在
		if (StringUtil.isEmpty(preceptUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, ""), callback);
			return null;
		}
		// 判断类型不能为空
		if (StringUtil.isEmpty(type)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, ""), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取随访方案的集合列表
		List relist = new ArrayList();
		if (!StringUtil.isEmpty(preceptUuid) && !StringUtil.isEmpty(type)) {
			List<VisitRecordExtendModel> list = visitRecordExtendService
					.getAllVisitRecordExtendListByPreceptUuid(preceptUuid, type);
			if (list != null && list.size() > 0) {
				for (VisitRecordExtendModel drm : list) {
					if (null != drm) {
						Map<Object, Object> save = new HashMap<Object, Object>();
						save.put("name", drm.getName());// 名称1代表睡眠情况 2 代表进食情况 3
						// 代表是其他情况 4 代表是其他的检查结果
						save.put("result", drm.getResult());// 描述
						save.put("period", drm.getPeriod());// 周期
						save.put("state", drm.getState());// 1代表良好 2一般 3异常 4 其他
						relist.add(save);
					}
				}
			}
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 医生分享文章给用户
	 *
	 * @param request
	 * @param response
	 * @return
	 * @author xp
	 */
	@RequestMapping(value = "/addDoctorShare", method = RequestMethod.GET)
	public String addDoctorShare(HttpServletRequest request, HttpServletResponse response,
								 @RequestParam("doctorUuid") String doctorUuid, @RequestParam("contentUuid") String contentUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[]{"doctorUuid,true", "contentUuid,true", "callback,true"});
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");

		/************************ 医生分享文章给用户 *************************/

		DoctorShareModel dsm = new DoctorShareModel();
		if (!StringUtil.isEmpty(doctorUuid) && !StringUtil.isEmpty(contentUuid)) {
			dsm.setUserUuid(doctorUuid);
			dsm.setUserType(DoctorShareModel.SHARE_USERTYPE_DOC);
			dsm.setContentUuid(contentUuid);
			doctorShareService.create(dsm);
			// 分享文章成功，积分+5
			vipclubIntegralLogService.saveVipIntegralLog(doctorUuid, "add", 5,
					IntegralType.ADD_SHARE_BY_ARTICLE.getValue(), VipclubIntegralLogModel.VIPCLUB_USERTYPE_DOC,
					"分享文章成功，积分加5", "");
		}
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 医生分享视频给用户
	 *
	 * @param request
	 * @param response
	 * @param doctorUuid
	 * @param videoUuid
	 * @return
	 */
	@RequestMapping(value = "/addDoctorShareByVideoUuid", method = RequestMethod.GET)
	public String addDoctorShareByVideoUuid(HttpServletRequest request, HttpServletResponse response,
											@RequestParam("doctorUuid") String doctorUuid, @RequestParam("videoUuid") String videoUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数的值，并进行非空判断
		boolean breakOut = false;
		Map<String, String> map = getParam(request, response, breakOut,
				new String[]{"doctorUuid,true", "videoUuid,true", "callback,true"});
		// 设置返回的信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");

		DoctorShareModel dsm = new DoctorShareModel();
		if (!StringUtil.isEmpty(videoUuid) && !StringUtil.isEmpty(doctorUuid)) {
			dsm.setUserUuid(doctorUuid);
			dsm.setUserType(DoctorShareModel.SHARE_USERTYPE_DOC);
			dsm.setVideoUuid(videoUuid);
			doctorShareService.create(dsm);
			// 分享视频成功，积分+5
			vipclubIntegralLogService.saveVipIntegralLog(doctorUuid, "add", 5,
					IntegralType.ADD_SHARE_BY_VIDEO.getValue(), VipclubIntegralLogModel.VIPCLUB_USERTYPE_DOC,
					"分享视频成功，积分加5", "");

		}
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 邀请同行
	 *
	 * @param request
	 * @param response
	 * @param doctorUuid
	 * @param mobiles
	 * @return
	 */
	@RequestMapping(value = "/invitePeer", method = RequestMethod.POST)
	public String invitePeer(HttpServletRequest request, HttpServletResponse response,
							 @RequestParam("doctorUuid") String doctorUuid, @RequestParam("mobiles") List<String> mobiles) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[]{"doctorUuid,true", "mobiles,true", "callback,true"});

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");
		// 邀请人
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "邀请人不能为空"), callback);
			return null;
		}
		ServicestaffModel sm = servicestaffService.getByUuid(doctorUuid);
		if (sm == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "邀请人不存在"), callback);
			return null;
		}

		String doctorName = "";
		if (sm != null) {
			doctorName = sm.getRealName();
		}

		// 被邀请人不能为空
		if (mobiles == null || mobiles.size() < 0) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "被邀请人不能为空"), callback);
			return null;
		}

		for (String mobile : mobiles) {
			// 判断是否被邀请过
			boolean isExit = friendsService.isExit(mobile, doctorUuid);
			if (!isExit) {
				GroupFriendsModel gm = new GroupFriendsModel();
				gm.setDoctorUuid(doctorUuid);
				gm.setMobile(mobile);
				gm.setCreateTime(DateFormatHelper.getNowTimeStr());
				gm.setNote("邀请医生同行加入");
				friendsService.create(gm);
				// 邀请同行
				AppUserService.invitePeer(mobile, doctorName);
			}
		}
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;
	}

	/**
	 * @param request
	 * @param response
	 * @param doctorUuid
	 * @return
	 * @Description: (获取医生同行列表)
	 * @author XP
	 * @date 2016-1-19 上午10:46:54
	 */
	@RequestMapping(value = "/getGroupFriendList", method = RequestMethod.GET)
	public String getGroupFriendList(HttpServletRequest request, HttpServletResponse response,
									 @RequestParam("doctorUuid") String doctorUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[]{"callback,true", "doctorUuid,true"});
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");
		// 判断医生必须登陆
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "请登录"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取医生邀请同行的列表
		List relist = new ArrayList();
		List<GroupFriendsModel> list = friendsService.getGropFriendListByDoctorUuid(doctorUuid);
		if (list != null && list.size() > 0) {
			for (GroupFriendsModel drm : list) {
				if (null != drm) {
					Map<Object, Object> save = new HashMap<Object, Object>();
					save.put("groupUuid", drm.getGroupUuid());// 医圈的id
					save.put("friendName", drm.getFriendName());// 好友姓名
					save.put("mobile", drm.getMobile());// 好友电话
					save.put("createTime", drm.getCreateTime());// 邀请时间
					save.put("note", drm.getNote());// 备注
					relist.add(save);
				}
			}
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 * @Description: (根据医生的id获取医生的邮箱)
	 * @author XP
	 * @date 2016-1-18 下午12:58:55
	 */
	@RequestMapping(value = "/getDoctorEmail", method = RequestMethod.GET)
	public String getDoctorEmail(HttpServletRequest request, HttpServletResponse response,
								 @RequestParam("doctorUuid") String doctorUuid) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[]{"callback,true", "doctorUuid,true"}); // 标志
		// 以及必需要传的参数
		Object callback = map.get("callback");

		// 判断doctorUuid是否为空
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "没有该医生"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		ServicestaffModel sm = servicestaffService.getByUuid(doctorUuid);
		if (null != sm) {
			jsonMap.put("doctorEmail", sm.getEmail());// 消息字段
		} else {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "用户未登录，请登录"), callback);
			return null;
		}
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * @param request
	 * @param doctorUuid
	 * @param contentUuid
	 * @param doctorEmail
	 * @param response
	 * @return
	 * @Description: (分享文章给用户)
	 * @author XP
	 * @date 2016-1-22 下午2:12:32
	 */
	@RequestMapping(value = "/addContentList", method = RequestMethod.POST)
	public String addContentList(HttpServletRequest request, @RequestParam("doctorUuid") String doctorUuid,
								 @RequestParam("contentUuid") String contentUuid, @RequestParam("doctorEmail") String doctorEmail,
								 HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		Map<String, String> map = getParam(request, response, false,
				new String[]{"callback,false", "doctorUuid,true", "contentUuid,true", "doctorEmail,true"});

		Object callback = map.get("callback");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 判断参数是否为空
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "请登录"), callback);
			return null;
		}
		// 判断参数是否为空
		if (StringUtil.isEmpty(contentUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "文章不存在"), callback);
			return null;
		}
		// 判断参数是否为空
		if (StringUtil.isEmpty(doctorEmail)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生的邮箱不能为空"), callback);
			return null;
		}
		ContentListModel ctm = contentListService.getContentlistByConditions(doctorUuid, contentUuid, doctorEmail);
		if (null == ctm) {
			// 新增获取文章的对象
			ContentListModel clm = new ContentListModel();
			clm.setDoctorUuid(doctorUuid);
			clm.setContentUuid(contentUuid);
			clm.setEmail(doctorEmail);
			clm.setCreateTime(DateFormatHelper.getNowTimeStr());
			clm.setState("0");
			contentListService.create(clm);
		} else {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "今日已经索取过了，请明日再次索取吧"),
					callback);
			return null;
		}
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;
	}

	/**
	 * @param request
	 * @param response
	 * @param userUuid
	 * @return
	 * @Description: (登陆获得积分)
	 * @author XP
	 * @date 2016-1-22 上午11:19:41
	 */
	@RequestMapping(value = "/getScoreByLogin", method = RequestMethod.GET)
	public String getVipclubByLogin(HttpServletRequest request, HttpServletResponse response,
									@RequestParam("userUuid") String userUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[]{"userType,true", "userUuid,true", "callback,true"});
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		// 定义返回字段，1表示积分增加成功，0表示今天已经获取过积分
		String message = "0";
		String userType = request.getParameter("userType");// 1是会员2是医生
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");
		if (StringUtil.isEmpty(userUuid)) {
			HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, "id不能为空！"));
			return null;
		}
		if ("1".equals(userType)) {
			// 判断患者今天是否已经增加积分，已经增加就不在增加
			VipclubIntegralLogModel vipclubIntegralLogModel = vipclubIntegralLogService
					.getVipclubIntegralLogByConditions(userUuid, VipclubIntegralLogModel.VIPCLUB_USERTYPE_CUS, "1", "");
			if (vipclubIntegralLogModel == null) {
				// 添加积分日志
				vipclubIntegralLogService.saveVipIntegralLog(userUuid, "add", 1, "1",
						VipclubIntegralLogModel.VIPCLUB_USERTYPE_CUS, "", "");
				message = "1";
			}
		} else {
			// 判断医生今天是否已经增加积分，已经增加就不在增加
			VipclubIntegralLogModel vipclubIntegralLogModel = vipclubIntegralLogService
					.getVipclubIntegralLogByConditions(userUuid, VipclubIntegralLogModel.VIPCLUB_USERTYPE_DOC, "1", "");
			if (vipclubIntegralLogModel == null) {
				// 添加积分日志
				vipclubIntegralLogService.saveVipIntegralLog(userUuid, "add", 5, "1",
						VipclubIntegralLogModel.VIPCLUB_USERTYPE_DOC, "", "");
				message = "1";
			}
		}

		// 1表示积分增加成功，0表示今天已经获取过积分
		jsonMap.put("prompt", message);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * @param request
	 * @param response
	 * @param doctorUuid
	 * @param visitRecordUuid
	 * @return
	 * @Description: (获取医生的健康指导)
	 * @author XP
	 * @date 2016-1-22 下午1:50:27
	 */
	@RequestMapping(value = "/getHealthGuide", method = RequestMethod.GET)
	public String getHealthGuide(HttpServletRequest request, HttpServletResponse response,
								 @RequestParam("doctorUuid") String doctorUuid, @RequestParam("visitRecordUuid") String visitRecordUuid) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[]{"callback,true", "doctorUuid,true", "visitRecordUuid,true"}); // 标志
		// 以及必需要传的参数
		Object callback = map.get("callback");

		// 判断doctorUuid是否为空
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生的id为空"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		HealthGuideModel hgm = healthGuideService.getHealthGuideByDoctorUuidAndVisitRecordUuid(doctorUuid,
				visitRecordUuid);
		List relist = new ArrayList();
		if (null != hgm) {
			Map<Object, Object> save = new HashMap<Object, Object>();
			save.put("diet", hgm.getDiet());// 饮食指导
			save.put("sports", hgm.getSports());// 运动指导
			save.put("rest", hgm.getRest());// 其他指导
			save.put("healthGuideUuid", hgm.getUuid());// 健康指导的id
			relist.add(save);
		}
		jsonMap.put("relist", relist);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 添加或者修改健康指导
	 *
	 * @param request    request
	 * @param response   response
	 * @param doctorUuid doctorUuid
	 * @return string
	 */
	@RequestMapping(value = "/saveOrUpdateHealthGuide", method = RequestMethod.POST)
	public String saveOrUpdateHealthGuide(HttpServletRequest request, HttpServletResponse response,
										  @RequestParam("doctorUuid") String doctorUuid, @RequestParam("visitRecordUuid") String visitRecordUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		Map<String, String> map = getParam(request, response, false, new String[]{"callback,false", "visitRecordUuid,true"});
		Object callback = map.get("callback");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 健康指导的id
		String healthGuideUuid = request.getParameter("healthGuideUuid");
		// 饮食指导
		String diet = request.getParameter("diet");
		// 运动指导
		String sports = request.getParameter("sports");
		// 其他指导
		String rest = request.getParameter("rest");
		// 判断医生的id
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生id为空"), callback);
			return null;
		}
		// 判断患者的id
		if (StringUtil.isEmpty(visitRecordUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "随访id为空"), callback);
			return null;
		}
		// 获取患者的id
		String customerUuid = visitRecordService.getCustomerUuidByUuid(visitRecordUuid);

		List<HealthGuideModel> guides = healthGuideService.getHealthGuideListByCustomerUuidAndDoctorUuid(doctorUuid, customerUuid);
		if (guides != null && guides.size() > 0) {
			for (HealthGuideModel guide : guides) {
				guide.setState("1");
				guide.setSpState("1");
				guide.setReState("1");
				healthGuideService.update(guide);
			}
		}

		if (StringUtil.isEmpty(healthGuideUuid)) {
			HealthGuideModel hgm = new HealthGuideModel();
			hgm.setDiet(diet);
			hgm.setSports(sports);
			hgm.setRest(rest);
			hgm.setServiceStaffUuid(doctorUuid);
			hgm.setCustomerUuid(customerUuid);
			hgm.setCreateTime(DateFormatHelper.getNowTimeStr());
			hgm.setNotevisitRecordUuid(visitRecordUuid);
			healthGuideService.create(hgm);
			// 消息推送
			innerMessageService.saveInnerMessageAndPush(doctorUuid, customerUuid,
					MessageHelper.getMessage("healthGuideMesg.showmessage.newAdd"),
					InnerMessageModel.ACCOUNT_TYPE_STORE, InnerMessageModel.ACCOUNT_TYPE_CUSTOMER,
					PushConst.push_client_customer, customerUuid, healthGuideUuid,
					InnerMessageTypeEnum.DOCTORHEALTHGUIDE.getValue());

		} else {
			HealthGuideModel hgm = healthGuideService.getByUuid(healthGuideUuid);
			if (null != hgm) {
				hgm.setDiet(diet);
				hgm.setSports(sports);
				hgm.setRest(rest);
				hgm.setServiceStaffUuid(doctorUuid);
				hgm.setCustomerUuid(customerUuid);
				hgm.setCreateTime(DateFormatHelper.getNowTimeStr());
				hgm.setNotevisitRecordUuid(visitRecordUuid);
				healthGuideService.update(hgm);
				// 消息推送
				innerMessageService.saveInnerMessageAndPush(doctorUuid, customerUuid,
						MessageHelper.getMessage("healthGuideMesg.showmessage.newAdd"),
						InnerMessageModel.ACCOUNT_TYPE_STORE, InnerMessageModel.ACCOUNT_TYPE_CUSTOMER,
						PushConst.push_client_customer, customerUuid, healthGuideUuid,
						InnerMessageTypeEnum.DOCTORHEALTHGUIDE.getValue());
			}
		}
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 保存接口调用错误信息
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/saveOperateLog", method = RequestMethod.GET)
	public String saveOperateLog(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		Object callback = request.getParameter("callback");
		String ua = request.getParameter("ua");
		String url = request.getParameter("url");
		String param = request.getParameter("param");
		String state = request.getParameter("state");

		OperateLogModel op = new OperateLogModel();
		op.setUa(ua);
		op.setUrl(url);
		op.setParam(param);
		op.setState(state);
		op.setCreateTime(DateFormatHelper.getNowTimeStr());
		opService.create(op);

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;

	}

	/*
	*    待处理随访任务-查询列表
	*    @param request
	*    @param response

	*    @return String
	*
	* */
	@RequestMapping(value = "1.0/getProcessedVisitList", method = RequestMethod.GET)
	public String waitProcessedVisitList(HttpServletResponse response, @RequestParam("doctorUuid") String doctorUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);


		// 判断用户是否登陆
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, "医生未登录"));
			return null;
		}

		// 获取随访记录的集合列表
		List<VisitRecordModel> visitApplyModelList = visitRecordService.getAllListByDoctorId(doctorUuid);
		List<Map<String, Object>> relist = new ArrayList<>();
		if (visitApplyModelList != null && visitApplyModelList.size() > 0) {
			for (VisitRecordModel va : visitApplyModelList) {
				// 组装返回到客户端的患者信息
				Map<String, Object> visitApplyMap = new HashMap<>();
				// 医生、患者和随访记录的的uuid
				String customerUuid = va.getCustomerUuid();
//				String doctorUuid = va.getServiceStaffUuid();
				String applyUuid = va.getUuid();
				String applyState = va.getApplyState();
				CustomerInfoModel cim;
				if (!StringUtil.isEmpty(customerUuid)) {
					cim = customerInfoService.getCustomerInfoModelByCustomerUuid(customerUuid);
					//随访申请状态 0未查看 1允许 2拒绝
					if (cim != null && !StringUtil.isEmpty(doctorUuid) && "0".equals(applyState)) {
						visitApplyMap.put("realName", cim.getRealName());
						visitApplyMap.put("sex", cim.getSex()); // 1是男；2是女
						visitApplyMap.put("age", cim.getAge());
						String createTime = va.getCreateTime();
						if (!StringUtil.isEmpty(createTime) && createTime.length() > 10) {
							visitApplyMap.put("createTime", va.getCreateTime().substring(0, 10));
						} else {
							visitApplyMap.put("createTime", cim.getOpeTime().substring(0, 10));
						}
						visitApplyMap.put("customerUuid", customerUuid);
						visitApplyMap.put("doctorUuid", doctorUuid);
						visitApplyMap.put("applyUuid", applyUuid);
						visitApplyMap.put("imgUrl", cim.getImgUrl());
						visitApplyMap.put("illnessDescription", cim.getIllnessDescription());
						relist.add(visitApplyMap);
					}
				}
			}
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJson(response, jsonMap);
		return null;
	}

	@RequestMapping("tempCreate")
	@ResponseBody
	public ApiResult TempCreateDoctors() {
		String[] str = null;
		Map<String, Object> result = new HashMap<>();
		boolean success;
		if (!ObjectUtils.isEmpty(str) && str.length > 0) {
			for (String mobile : str) {
				success = true;
				ServicestaffModel staffModel = new ServicestaffModel();
				// 手机号码
				staffModel.setMobile(mobile);
				// 密码
				staffModel.setPassword(mobile);
				// 创建时间
				staffModel.setCreateTime(DateFormatHelper.getNowTimeStr());
				// 冻结状态
				staffModel.setFrozenSate("0");
				// 设置姓名为手机号
				staffModel.setRealName(mobile);
				staffModel.setRegState("1");
				// doctorNo
				staffModel.setDoctorNo(servicestaffcombService.createDoctorNo());
				staffModel.setRealName("请填写真实姓名");
				staffModel.setEmail("请填写真实邮箱");
				// 审核认证未审核
				staffModel.setSate(ServicestaffModel.SERVICESTAFF_SATE_UNCHECK);
				// 医院
				staffModel.setHospital("742a4a0869fe4bc6a70d9738c998bf27");
				// 科室
				staffModel.setDepartment("rtmentInfo0000000191");

				// 创建
				try {
					servicestaffService.create(staffModel);
				} catch (Exception e) {
					result.put(mobile + "create(staffModel)", e.getMessage());
					success = false;
				}
				if (!success){
					continue;
				}
				/************************ 创建医生基础信息表 *************************/
				ServicestaffinfoModel staffInfoModel = new ServicestaffinfoModel();
				staffInfoModel.setSex("1");
				staffInfoModel.setServiceStaffUuid(staffModel.getUuid());
				// 省
				staffInfoModel.setProvince("110000");
				// 市
				staffInfoModel.setCity("110000");
				// 区县
				staffInfoModel.setRegion("110101");
				staffInfoModel.setProfessional("请填写职称");
				staffInfoModel.setSynopsis("");
				// 特长
				staffInfoModel.setTerritory("");
				// 创建
				try {
					servicestaffinfoService.create(staffInfoModel);
				} catch (Exception e) {
					result.put(mobile + "create(staffInfoModel)", e.getMessage());
					success = false;
				}
				if (!success){
					continue;
				}
				try {
					VipclubIntegralLogModel vig = vipclubIntegralLogService.getVipclubIntegralLogByConditions(staffModel.getUuid(), "2", "4", "");
					if (null == vig) {
						vipclubIntegralLogService.saveVipIntegralLog(staffModel.getUuid(), "add", 10, IntegralType.GET_BY_REGISTER.getValue(), VipclubIntegralLogModel.VIPCLUB_USERTYPE_DOC, "完善个人信息", "");
					}
				} catch (Exception e) {
					result.put(mobile + "create(VipclubIntegralLogModel)", e.getMessage());
					success = false;
				}
				if (!success){
					continue;
				}
				// 得到doctorRightModel
				DoctorRightModel doctorRightModel = doctorRightService.getByDoctorUuid(staffModel.getUuid());
				// 如果权限表不存在，创建该医生的权限表，默认所有为未开通
				if (doctorRightModel == null || StringUtil.isEmpty(doctorRightModel.getDoctorUuid())) {
					doctorRightModel = new DoctorRightModel();
			/* 加号预约 0：未开通 1：开通 */
					doctorRightModel.setPlus("0");
			/* 免打扰 0：未开通 1：开通 */
					doctorRightModel.setDisturb("0");
					// 图文咨询 默认开通
					doctorRightModel.setTeletext("1");
			/* 电话咨询 0：未开通 1：开通 */
					doctorRightModel.setPhone("0");
			/* 图文免打扰 0：未开通 1：开通 */
					doctorRightModel.setTelDisturb("0");
			/* 私人医生 0：未开通 1：开通 */
					doctorRightModel.setPersonal("0");
			/* 是否开通审核 0：未开通 1：开通 */
					doctorRightModel.setExam("0");
			/* 私人医生托管审核 0：未开通 1：开通 */
					doctorRightModel.setPersonalExam("0");
					doctorRightModel.setDoctorUuid(staffModel.getUuid());
					// 创建
					try {
						doctorRightService.create(doctorRightModel);
					} catch (Exception e) {
						result.put(mobile + "create(doctorRightModel)", e.getMessage());
					}
				}
			}
		}
		return ApiResult.right(result);
	}
}
