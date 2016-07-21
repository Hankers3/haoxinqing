package com.aebiz.b2b2c.customermgr.mobile.web.service.user;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aebiz.b2b2c.baseframework.filemgr.helper.FileUploadHelper;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.basicdata.city.service.CityService;
import com.aebiz.b2b2c.basicdata.city.vo.CityModel;
import com.aebiz.b2b2c.basicdata.province.service.ProvinceService;
import com.aebiz.b2b2c.basicdata.province.vo.ProvinceModel;
import com.aebiz.b2b2c.basicdata.region.service.RegionService;
import com.aebiz.b2b2c.basicdata.region.vo.RegionModel;
import com.aebiz.b2b2c.cms.content.service.ContentService;
import com.aebiz.b2b2c.cms.innermessage.service.InnerMessageService;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageModel;
import com.aebiz.b2b2c.cms.innermessagenotice.service.InnerMessageNoticeService;
import com.aebiz.b2b2c.cms.message.service.MessageService;
import com.aebiz.b2b2c.cms.message.vo.MessageModel;
import com.aebiz.b2b2c.cms.platformapply.service.PlatformApplyService;
import com.aebiz.b2b2c.cms.platformapply.vo.PlatformApplyModel;
import com.aebiz.b2b2c.cms.platformcommunication.service.PlatformCommunicationService;
import com.aebiz.b2b2c.cms.platformcommunication.vo.PlatformCommunicationModel;
import com.aebiz.b2b2c.cms.platforminfo.service.PlatFormInfoService;
import com.aebiz.b2b2c.cms.platforminfo.vo.PlatFormInfoModel;
import com.aebiz.b2b2c.cms.tags.service.TagsService;
import com.aebiz.b2b2c.cms.tags.vo.TagsModel;
import com.aebiz.b2b2c.customermgr.consultrecord.service.ConsultRecordService;
import com.aebiz.b2b2c.customermgr.consultrecord.vo.ConsultRecordModel;
import com.aebiz.b2b2c.customermgr.consultrecord.vo.CustomerAppModel;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.customermgr.customeraddress.service.CustomerAddressService;
import com.aebiz.b2b2c.customermgr.customerinfo.service.CustomerInfoService;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoModel;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.AppBaseController;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.HttpServletUtils;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.JsonUtils;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.SysConst;
import com.aebiz.b2b2c.finance.withdrawapply.service.WithdrawApplyService;
import com.aebiz.b2b2c.finance.withdrawapply.vo.WithdrawApplyModel;
import com.aebiz.b2b2c.order.ordermain.service.OrderMainService;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;
import com.aebiz.b2b2c.order.ordermainaddress.service.OrderMainAddressService;
import com.aebiz.b2b2c.order.ordermaincomment.service.OrderMainCommentService;
import com.aebiz.b2b2c.order.orderrouting.service.OrderRoutingService;
import com.aebiz.b2b2c.order.orderrouting.vo.OrderRoutingModel;
import com.aebiz.b2b2c.servicestaff.departmentinfo.service.DepartmentInfoService;
import com.aebiz.b2b2c.servicestaff.departmentinfo.vo.DepartmentInfoModel;
import com.aebiz.b2b2c.servicestaff.doctorimport.service.DoctorImportService;
import com.aebiz.b2b2c.servicestaff.doctorimport.vo.DoctorImportModel;
import com.aebiz.b2b2c.servicestaff.doctorright.service.DoctorRightService;
import com.aebiz.b2b2c.servicestaff.doctorright.vo.DoctorRightModel;
import com.aebiz.b2b2c.servicestaff.homevisitset.service.HomeVisitSetService;
import com.aebiz.b2b2c.servicestaff.homevisitset.vo.HomeVisitSetModel;
import com.aebiz.b2b2c.servicestaff.hospitalinfo.service.HospitalInfoService;
import com.aebiz.b2b2c.servicestaff.hospitalinfo.vo.HospitalInfoModel;
import com.aebiz.b2b2c.servicestaff.packagedoctor.service.PackageDoctorService;
import com.aebiz.b2b2c.servicestaff.packagedoctor.vo.PackageDoctorModel;
import com.aebiz.b2b2c.servicestaff.packagemanagement.service.PackageManagementService;
import com.aebiz.b2b2c.servicestaff.packagemanagement.vo.PackageManagementModel;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;
import com.aebiz.b2b2c.servicestaff.servicestaffinfo.service.ServicestaffinfoService;
import com.aebiz.b2b2c.servicestaff.servicestaffinfo.vo.ServicestaffinfoModel;
import com.aebiz.b2b2c.servicestaff.servicestafflevel.service.ServicestafflevelService;
import com.aebiz.b2b2c.servicestaff.staffloginstatus.service.StaffLoginStatusService;
import com.aebiz.b2b2c.train.trainstaff.service.TrainStaffService;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.service.VipclubIntegralLogService;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.IntegralType;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.VipclubIntegralLogModel;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.VipclubIntegralLogQueryModel;
import com.aebiz.b2b2c.visitprecept.casegroup.service.CaseGroupService;
import com.aebiz.b2b2c.visitprecept.casegroup.vo.CaseGroupModel;
import com.aebiz.b2b2c.visitprecept.customerdoctorrele.service.CustomerDoctorReleService;
import com.aebiz.b2b2c.visitprecept.customerdoctorrele.vo.CustomerDoctorReleModel;
import com.aebiz.b2b2c.visitprecept.medicalrecord.service.MedicalRecordService;
import com.aebiz.b2b2c.visitprecept.medicalrecord.vo.MedicalRecordModel;
import com.aebiz.b2b2c.visitprecept.visitprecept.service.VisitPreceptService;
import com.aebiz.b2b2c.visitprecept.visitrecord.service.VisitRecordService;
import com.aebiz.b2b2c.websiteoperation.favorite.service.FavoriteService;
import com.hxq.mobile.doctor.common.service.FavoriteInformationSerivce;
import com.hxq.mobile.doctor.common.service.PlatformInfoSerivce;
import com.wxcommon.util.ObjectUtils;

/**
 * 医生端服务接口控制类
 * 
 * @author dgh
 * 
 */
@RequestMapping("/app/service/doctor")
public class AppServiceUserController extends AppBaseController {
	/**
	 * 构造方法
	 */
	public AppServiceUserController() {
		super("", "", AppServiceUserController.class);
	}

	@Autowired
	private VisitPreceptService visitPreceptService;
	@Autowired
	private CaseGroupService caseGroupService;
	@Autowired
	private ServicestaffService servicestaffService;
	@Autowired
	private ServicestaffinfoService servicestaffinfoService;
	@Autowired
	private ServicestafflevelService servicestafflevelService;

	@Autowired
	private OrderMainAddressService orderMainAddressService;
	@Autowired
	private CustomerAddressService customerAddressService;
	@Autowired
	private OrderMainService orderMainService;
	@Autowired
	private WithdrawApplyService withdrawApplyService;
	@Autowired
	private StaffLoginStatusService staffLoginStatusService;
	/* 消息service */
	@Autowired
	private InnerMessageService innerMessageService;
	@Autowired
	private ConsultRecordService consultRecordService;

	@Autowired
	private InnerMessageNoticeService innerMessageNoticeService;

	@Autowired
	private OrderMainCommentService orderMainCommentService;
	@Autowired
	private TrainStaffService trainStaffService;
	@Autowired
	private VipclubIntegralLogService vipclubIntegralLogService;

	// 视频service
	@Autowired
	private PlatFormInfoService platFormInfoService;
	
	@Resource(name = "com.hxq.mobile.doctor.common.service.PlatformInfoSerivce")
	private PlatformInfoSerivce platformInfoSerivce;
	
	@Resource(name = "com.hxq.mobile.doctor.common.service.FavoriteInformationSerivce")
	private FavoriteInformationSerivce favoriteInformationSerivce;
	
	@Resource(name = "com.hxq.mobile.message.InnerMessage.service.InnerMessageService")
	public com.hxq.mobile.message.InnerMessage.service.InnerMessageService innerMessageServiceS;
	
	@Autowired
	private PlatformApplyService platformApplyService;
	// 视频评论？service
	@Autowired
	private PlatformCommunicationService platformCommunicationService;

	// 医生及患者的收藏表
	@Autowired
	private FavoriteService favoriteService;
	/*
	 * 市service
	 */
	@Autowired
	private CityService cityService;
	/*
	 * 患者service
	 */
	@Autowired
	private CustomerService customerService;
	/*
	 * 患者基础信息service
	 */
	@Autowired
	private CustomerInfoService customerInfoService;
	/*
	 * 分账信息表service
	 */
	@Autowired
	private OrderRoutingService orderRoutingService;

	/*
	 * 省service
	 */
	@Autowired
	private ProvinceService provinceService;
	/*
	 * 区service
	 */
	@Autowired
	private RegionService regionService;
	/*
	 * 医院service
	 */
	@Autowired
	private HospitalInfoService hospitalInfoService;
	/*
	 * 科室service
	 */
	@Autowired
	private DepartmentInfoService departmentInfoService;

	@Autowired
	private FileUploadHelper fileUpload;
	@Autowired
	private MessageService messageService;

	/* 注入导入医生信息表 */
	@Autowired
	private DoctorImportService doctorImportService;

	/* 注入患者医生关联关系表 */
	@Autowired
	private CustomerDoctorReleService customerDoctorReleService;

	/* 随访记录信息表 */
	@Autowired
	private VisitRecordService visitRecordService;

	/* 套餐医生关联关系 */
	@Autowired
	private PackageDoctorService packageDoctorService;
	/*
	 * 今日推荐service
	 */
	@Autowired
	private ContentService contentService;

	/*
	 * 注入私人套餐的Service
	 */
	@Autowired
	private PackageManagementService packageManagementService;
	/*
	 * 医生权限的Service
	 */
	@Autowired
	private DoctorRightService doctorRightService;
	/*
	 * 出诊时间的Service
	 */
	@Autowired
	private HomeVisitSetService homeVisitSetService;
	/*
	 * 病历的Service
	 */
	@Autowired
	private MedicalRecordService medicalRecordService;
	@Autowired
	private com.aebiz.b2b2c.servicestaff.doctortag.service.DoctorTagService doctorTagService;
	@Autowired
	private TagsService tagsService;

	/**
	 * 获取医生的基本信息接口
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getDoctorInfo", method = RequestMethod.GET)
	public String getServiceStaff(HttpServletRequest request, HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, false, new String[] { "doctorUuid,true" });
		/*
		 * if ("true".equals(map.get("breakOut"))) { return null; }
		 */
		String doctorUuid = map.get("doctorUuid");
		Object callback = request.getParameter("callback");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 通过医生id获取对象
		ServicestaffModel servicestaffModel = servicestaffService.getByUuid(doctorUuid);
		//医生信息不存在
		if (servicestaffModel == null) {
			jsonMap.put("noDoctor", "1");
			HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
			return null;
		}
		// 获取医生基础信息
		ServicestaffinfoModel servicestaffinfoModel = servicestaffinfoService
				.getServicestaffinfoModelByServicestaffUuid(doctorUuid);
		if (servicestaffinfoModel == null) {
			jsonMap.put("noDoctorInfo", "1");
			HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
			return null;
		}

		// 获取患者数量
		int customerNum = customerDoctorReleService.getCustomerNumByDoctorUuid(servicestaffModel.getUuid());

		// 随访次数
		int visitNum = visitRecordService.getCountByDoctorUuid(servicestaffModel.getUuid());

		// 该医生昨天的收入
		// Double yesterdayIncome = orderRoutingService.getTodayIncome(1,
		// doctorUuid);
		// 该医生本月的收入
		// Double monthIncome =
		// orderRoutingService.getCurrentMonthIncomes(doctorUuid);

		// 组装返回到客户端的医生信息
		Map<Object, Object> serviceStaffMap = new HashMap<Object, Object>();

		/** -------------------------------------- **/
		// 医生uuid
		serviceStaffMap.put("doctorUuid", servicestaffModel.getUuid());
		// 医生名称
		serviceStaffMap.put("doctorName", servicestaffModel.getRealName());
		// 头像
		serviceStaffMap.put("doctorIcon", servicestaffinfoModel.getImgUrl());
		serviceStaffMap.put("delFlag", servicestaffModel.getDelFlag());
		// 医生性别
		serviceStaffMap.put("sex", servicestaffinfoModel.getSex());

		// 医生总收入
		Number income = orderRoutingService.getDoctorAllIncomeByIdAndType(doctorUuid, "0");
		if (income == null) {
			income = 0;
		}
		double allIncome = income.doubleValue();
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);

		serviceStaffMap.put("income", df.format(allIncome));

		// 医生注册成功与否状态
		serviceStaffMap.put("regState", servicestaffModel.getRegState());

		// 主治医师 职称
		String professional = servicestaffinfoModel.getProfessional();

		serviceStaffMap.put("professional", professional);
		// 医生的简介
		serviceStaffMap.put("synopsis", servicestaffinfoModel.getSynopsis());
		// 医生认证 审核状态 0:待审核 1：审核通过 2 审核不通过
		serviceStaffMap.put("sate", servicestaffModel.getSate());

		// 所在城市
		serviceStaffMap.put("address", servicestaffModel.getAddress());
		serviceStaffMap.put("province", servicestaffinfoModel.getProvince());
		serviceStaffMap.put("provinceName", servicestaffinfoModel.getProvinceName());
		serviceStaffMap.put("city", servicestaffinfoModel.getCity());
		serviceStaffMap.put("cityName", servicestaffinfoModel.getCityName());
		serviceStaffMap.put("region", servicestaffinfoModel.getRegion());
		serviceStaffMap.put("regionName", servicestaffinfoModel.getRegionName());
		// 医院名称
		serviceStaffMap.put("hospital", servicestaffModel.getHospitalName());
		serviceStaffMap.put("hospitalUuid", servicestaffModel.getHospital());

		//FIXME 其他情况 医生可能所在医院在status临时存放
		if (!StringUtil.isEmpty(servicestaffModel.getStatus())){
			serviceStaffMap.put("hospital",servicestaffModel.getStatus());
			serviceStaffMap.put("hospitalUuid", 1);
		}


		// 科室名称
		serviceStaffMap.put("department", servicestaffModel.getDepartmentName());
		serviceStaffMap.put("departmentUuid", servicestaffModel.getDepartment());

		// 科室电话
		serviceStaffMap.put("departmentLine", servicestaffModel.getDepartmentLine());

		// 擅长疾病 擅长领域
		serviceStaffMap.put("territory", servicestaffinfoModel.getTerritory());
		// 医生随访次数
		serviceStaffMap.put("visitNum", visitNum);
		// 患者数
		serviceStaffMap.put("customerNum", customerNum);

		/** -------------------------------------- **/

		jsonMap.put("doctorInfo", serviceStaffMap);

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;
	}

	/**
	 * 修改医生基础信息 12/29
	 * 
	 * @author szr
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/updateDoctorInfo", method = RequestMethod.POST)
	public String updateDoctorInfo(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("doctorUuid") String doctorUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "doctorUuid,true", "callback,true" });
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String area = request.getParameter("area");
		String infirmary = request.getParameter("infirmary");
		String departments = request.getParameter("departments");
		String telephone = request.getParameter("telephone");
		String synopsis = request.getParameter("synopsis");
		String territory = request.getParameter("territory");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生id为空"), callback);
			return null;
		}
		// 得到医生基础信息
		ServicestaffinfoModel servicestaffinfoModel = servicestaffinfoService
				.getServicestaffinfoModelByServicestaffUuid(doctorUuid);
		if (servicestaffinfoModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生基础信息不存在"), callback);
			return null;
		}
		// 得到医生信息
		ServicestaffModel servicestaffModel = servicestaffService.getByUuid(doctorUuid);
		if (servicestaffModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生信息不存在"), callback);
			return null;
		}
		// 更新医生信息
		if (!StringUtil.isEmpty(infirmary)) {
			servicestaffModel.setHospital(infirmary);
		}
		// 更新医生信息
		if (!StringUtil.isEmpty(departments)) {
			servicestaffModel.setDepartment(departments);
		}

		// 将审核状态改为未审核
		servicestaffModel.setSate(ServicestaffModel.SERVICESTAFF_SATE_UNCHECK);

		// 更新医生信息
		if (!StringUtil.isEmpty(telephone)) {
			servicestaffModel.setDepartmentLine(telephone);
		}
		// 更新医生信息
		servicestaffService.update(servicestaffModel);
		// 省
		if (!StringUtil.isEmpty(province)) {
			servicestaffinfoModel.setProvince(province);
		}
		// 市
		if (!StringUtil.isEmpty(city)) {
			servicestaffinfoModel.setCity(city);
		}
		// 区
		if (!StringUtil.isEmpty(area)) {
			servicestaffinfoModel.setRegion(area);
		}
		// 简介
		if (!StringUtil.isEmpty(synopsis)) {
			servicestaffinfoModel.setSynopsis(synopsis);
		}
		// 擅长领域
		if (!StringUtil.isEmpty(territory)) {
			servicestaffinfoModel.setTerritory(territory);
		}
		// 更新医生基础信息
		servicestaffinfoService.update(servicestaffinfoModel);

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 修改医生头像信息 12/29
	 * 
	 * @author szr
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/updateImg", method = RequestMethod.POST)
	public String updateImg(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("doctorUuid") String doctorUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "doctorUuid,true", "callback,true" });
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生id为空"), callback);
			return null;
		}
		// 得到医生基础信息
		ServicestaffinfoModel servicestaffinfoModel = servicestaffinfoService
				.getServicestaffinfoModelByServicestaffUuid(doctorUuid);

		ServicestaffModel servicestaffModel = servicestaffService.getByUuid(doctorUuid);
		if (servicestaffinfoModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生基础信息不存在"), callback);
			return null;
		}

		String realName = request.getParameter("realName");
		String professional = request.getParameter("professional");
		String image = request.getParameter("image");

		if (!StringUtil.isEmpty(realName)) {
			servicestaffModel.setRealName(realName);
			servicestaffService.update(servicestaffModel);
		}
		// 将头像更新
		if (!StringUtil.isEmpty(professional)) {
			servicestaffinfoModel.setProfessional(professional);
		}
		if (!StringUtil.isEmpty(image)) {
			servicestaffinfoModel.setImage(image);
		}
		servicestaffinfoService.update(servicestaffinfoModel);

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 最近收藏我的人
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getcollectMe", method = RequestMethod.GET)
	public String getcollectMe(HttpServletRequest request, HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "userId,true", "callback,true" });
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		String userId = map.get("userId");
		Object callback = map.get("callback");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		/** 消息类型 0最近收藏我的人 1随访消息 2在线咨询消息 **/
		List<InnerMessageModel> messageList = innerMessageService.getCollectMeListByReceiveUser(userId, "0"); // 通过uuid得到发送给该医生的消息
																												// 收藏我的人

		if (messageList == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生无收藏信息"), callback);
			return null;
		}
		List relist = new ArrayList();
		if (messageList != null && messageList.size() > 0) {
			for (InnerMessageModel imm : messageList) {
				Map<String, Object> save = new HashMap<String, Object>();
				if (imm != null) {
					CustomerInfoModel cim = customerInfoService.getCustomerInfoModelByCustomerUuid(imm.getSendUser());
					if (null != cim) {
						// 名字
						save.put("sendName", cim.getRealName());
						// 年龄
						save.put("age", cim.getAge());
					} else {
						// 名字
						save.put("sendName", "");
						// 年龄
						save.put("age", "");
					}
					// 显示内容
					save.put("showContent", imm.getShowContent());
					// 显示头像
					save.put("image", imm.getImage());
					// 发送时间
					save.put("sendTime", imm.getSendTime());
					relist.add(save);
				}
			}
		}
		// 未读数
		jsonMap.put("collectMeNum",
				innerMessageService.getMessageCenterCount(InnerMessageModel.MESSAGE_TYPE_SYSTEM, userId));
		// 消息字段
		jsonMap.put("relist", relist);

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;
	}

	/**
	 * 随访消息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getVisitMessage", method = RequestMethod.GET)
	public String getVisitMessage(HttpServletRequest request, HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "userId,true", "callback,true" });
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		String userId = map.get("userId");
		Object callback = map.get("callback");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		/** 消息类型 0最近收藏我的人 1随访消息 2在线咨询消息 **/
		List<InnerMessageModel> messageList = innerMessageService.getCollectMeListByReceiveUser(userId, "1"); // 通过uuid得到发送给该医生的消息
																												// 随访消息
		if (messageList == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生无随访消息"), callback);
			return null;
		}
		List relist = new ArrayList();
		if (messageList != null && messageList.size() > 0) {
			for (InnerMessageModel imm : messageList) {
				Map<String, Object> save = new HashMap<String, Object>();
				CustomerInfoModel cim = customerInfoService.getCustomerInfoModelByCustomerUuid(imm.getSendUser());
				if (null != cim) {
					// 名字
					save.put("sendName", cim.getRealName());
					// 年龄
					save.put("age", cim.getAge());
				} else {
					// 名字
					save.put("sendName", "");
					// 年龄
					save.put("age", "");
				}
				// 显示内容
				save.put("showContent", imm.getShowContent());
				// 显示头像
				save.put("image", imm.getImage());
				// 发送时间
				save.put("sendTime", imm.getSendTime());
				relist.add(save);
			}
		}
		// 消息字段
		jsonMap.put("relist", relist);

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;
	}

	/**
	 * 我的预约课程
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getPlatformApply", method = RequestMethod.GET)
	public String getPlatformApply(HttpServletRequest request, HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "userId,true", "callback,true" });
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		String userId = map.get("userId");
		Object callback = map.get("callback");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 通过医生id获取预约课程List
		List<PlatformApplyModel> platformApplyList = platformApplyService.getByUserid(userId,
				"1"); /* 类型 1表示医生 2表示患者 */
		if (platformApplyList == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "无预约课程"), callback);
			return null;
		}
		List relist = new ArrayList();
		if (platformApplyList != null && platformApplyList.size() > 0) {
			for (PlatformApplyModel pfam : platformApplyList) {
				Map<String, Object> save = new HashMap<String, Object>();
				// 得到视频主键
				String videoid = pfam.getVidoUuid();
				// 根据视频id查出视频信息
				PlatFormInfoModel platFormInfoModel = platFormInfoService.getByUuid(videoid);
				// 存入视频标题
				save.put("title", platFormInfoModel.getVideoIntroduction());
				// 存入发布时间
				save.put("createTime", platFormInfoModel.getCreateTime());
				// 存入发布者
				save.put("userName", platFormInfoModel.getUserName());

				relist.add(save);
			}
		}
		// 消息字段
		jsonMap.put("relist", relist);

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;
	}

	/**
	 * 提现申请接口
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getWithdrawApply", method = RequestMethod.POST)
	public String getWithdrawApply(HttpServletRequest request, HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut, new String[] { "doctorUuid,true",
				"bankName,true", "bankNo,true", "userName,true", "applyMoney,true", "telphone,true", "callback,true" });
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");

		String doctorUuid = map.get("doctorUuid");
		String bankName = map.get("bankName");
		String bankNo = map.get("bankNo");
		String userName = map.get("userName");
		double applyMoney = Double.parseDouble(map.get("applyMoney"));
		String telphone = map.get("telphone");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 通过医生id获取对象
		ServicestaffModel servicestaffModel = servicestaffService.getByUuid(doctorUuid);
		if (servicestaffModel == null) {
			HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, "无此医生"));
			return null;
		}
		double accountAmount = servicestaffModel.getAccountAmount();

		accountAmount = accountAmount - applyMoney;

		servicestaffModel.setAccountAmount(accountAmount);

		servicestaffService.update(servicestaffModel);

		// 新建提现申请对象
		WithdrawApplyModel withdrawApplyModel = new WithdrawApplyModel();
		withdrawApplyModel.setUserUuid(doctorUuid);
		withdrawApplyModel.setUserName(userName);
		withdrawApplyModel.setApplyMoney(applyMoney);
		withdrawApplyModel.setState("0");
		withdrawApplyModel.setUserType("2");
		withdrawApplyModel.setMoneyType("1");
		withdrawApplyModel.setApplyTime(DateFormatHelper.getNowTimeStr());
		withdrawApplyService.create(withdrawApplyModel);

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 账单明细-分账列表查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getOrderRoutingList", method = RequestMethod.POST)
	public String getOrderRoutingList(HttpServletRequest request, HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "doctorUuid,true", "timeType,false", "pageCount,false", "pageNo,false" });
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		String doctorUuid = map.get("doctorUuid");
		String timeType = map.get("timeType");
		String pageCount = map.get("pageCount");
		String pageNo = map.get("pageNo");

		int pc = 10;
		int pn = 1;
		if (!StringUtil.isEmpty(pageNo)) {
			pn = Integer.parseInt(pageNo);
		}
		if (!StringUtil.isEmpty(pageCount)) {
			pc = Integer.parseInt(pageCount);
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		List<Object> routingList = orderRoutingService.getOrderRoutingList(doctorUuid, timeType, pn, pc);
		List wamList = new ArrayList();
		if (routingList != null && routingList.size() > 0) {
			for (int i = 0; i < routingList.size(); i++) {
				Map<String, Object> myIncome = new HashMap<String, Object>();
				Object[] obj = (Object[]) routingList.get(i);
				// 分账时间
				String routTime = (String) obj[0];
				myIncome.put("routTime", routTime.substring(0, 11));
				// 分账金额
				myIncome.put("routPrice", obj[1]);
				// *分账类型 0：医生 1：平台
				myIncome.put("routType", obj[2]);
				// 订单编号
				String orderMainUuid = (String) obj[3];
				myIncome.put("uuid", orderMainUuid);
				OrderMainModel order = orderMainService.getByUuid(orderMainUuid);
				myIncome.put("orderId", order.getOrderId());
				wamList.add(myIncome);
			}
		}
		jsonMap.put("orderRoutingList", wamList);
		HttpServletUtils.outJson(response, jsonMap);
		return null;
	}

	/**
	 * 获取医生的消息中心未读数量接口
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getServiceStaffMessage", method = RequestMethod.GET)
	public String getServiceStaffMessage(HttpServletRequest request, HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "doctorUuid,true" });
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");

		// 取到医生ID
		String doctorUuid = map.get("doctorUuid");
		// 判断是否为空
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "传入的医生id为空，不能查询"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 最近收藏我的人消息数量
		jsonMap.put("collectMeNum",
				innerMessageService.getMessageCenterCount(InnerMessageModel.MESSAGE_TYPE_PROMOTION, doctorUuid));
		// 随访消息消息数量
		jsonMap.put("visitpreceptNum",
				innerMessageService.getMessageCenterCount(InnerMessageModel.MESSAGE_TYPE_ORDER, doctorUuid));
		// 系统的消息?(在线咨询)
		jsonMap.put("onlineConsultNum",
				innerMessageService.getMessageCenterCount(InnerMessageModel.MESSAGE_TYPE_SYSTEM, doctorUuid));

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;
	}

	/**
	 * 根据参数返回消息列表
	 * 
	 * @param request
	 * @param response
	 * @param messageType
	 *            消息类型 0最近收藏我的人 1：随访消息 2：在线咨询消息
	 * 
	 */
	@RequestMapping(value = "/getcollectMeMSG", method = RequestMethod.GET)
	public String getcollectMeMSG(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("messageType") String messageType, @RequestParam("doctorUuid") String doctorUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "messageType,true", "doctorUuid,true" }); // 标志
		// 必需要传的参数
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");
		// 判断是否为空
		if (StringUtil.isEmpty(doctorUuid) || StringUtil.isEmpty(messageType)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "传入的医生id或类型为空，不能查询"),
					callback);
			return null;
		}
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 判断是否为空
		if (StringUtil.isEmpty(messageType)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "传入类型为空，不能查询"), callback);
			return null;
		}

		List<InnerMessageModel> messageList = innerMessageService.getInnerMessageListByMessageType(messageType,
				doctorUuid);
		List relist = new ArrayList();
		Map<String, Object> save = null;
		if (messageList != null && messageList.size() > 0) {
			for (InnerMessageModel imm : messageList) {
				// 发信人类型 1：医生 2：患者
				String sendType = imm.getSendType();
				// 编号
				String sendUser = imm.getSendUser();
				CustomerInfoModel cim = null;
				CustomerModel cm = null;
				//save.put("innerMessageId", imm.getUuid());
				// 获取患者信息
				if ("2".equals(sendType)) {
					save = new HashMap<String, Object>();
					cim = customerInfoService.getCustomerInfoModelByCustomerUuid(sendUser);
					cm = customerService.getByUuid(sendUser);
					if (cim != null) {
						// 患者名
					//	save.put("sendName", cim.getRealName());
						save.put("customerName", cm.getCustomerName());
						// 头像
						save.put("image", cim.getImgUrl());
						save.put("age", cim.getAge());
						save.put("sex", cim.getSex());
					}
					// 类型为1时加入随访id
					if ("1".equals(messageType)) {
						save.put("visitRecordUuid", imm.getVisitRecordUuid());
					}
					if (!ObjectUtils.isEmpty(imm.getContent())) {
						// 内容
						save.put("showContent", imm.getShowContent());
					}else {
						save.put("showContent", "");
					}
					
					// 时间
					save.put("sendTime", imm.getSendTime());
					save.put("customerUuid", imm.getSendUser());
					relist.add(save);
				} else if ("1".equals(sendType)) {
					HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "发信人类型应该为患者"),
							callback);
					return null;
				}
			}
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;
	}

	/**
	 * 获取医生的任务未读，未处理数量接口
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getServiceStaffMission", method = RequestMethod.GET)
	public String getServiceStaffMission(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("doctorUuid") String doctorUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "doctorUuid,true" });
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");
		// 判断是否为空
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "传入的医生id为空，不能查询"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		jsonMap.put("onlineNum", consultRecordService.getReplyCount(ConsultRecordModel.TYPE_ONLINE, doctorUuid));// 在线咨询未回复消息数量
		jsonMap.put("orderNum", consultRecordService.getMissionCenterCount(ConsultRecordModel.TYPE_ORDER, doctorUuid));// 预约加号未处理消息数量

		List<OrderMainModel> orderList = orderMainService.getOrderList("0", doctorUuid, 0, 0, "1");

		List<OrderMainModel> orderPers = orderMainService.getOrderList("0", doctorUuid, 0, 0, "2");

		if (orderList != null && orderList.size() > 0) {
			jsonMap.put("telNum", orderList.size());// 电话咨询未处理消息数量
		} else {
			jsonMap.put("telNum", 0);// 电话咨询未处理消息数量
		}

		if (orderPers != null && orderPers.size() > 0) {
			jsonMap.put("perNum", orderPers.size());// 电话咨询未处理消息数量
		} else {
			jsonMap.put("perNum", 0);// 电话咨询未处理消息数量
		}

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;
	}

	/**
	 * 获取所有患者的订单的接口
	 * 
	 * @author xp
	 */
	@RequestMapping(value = "/getOrderList", method = RequestMethod.GET)
	public String getOrderList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("stateType") String stateType, @RequestParam("doctorUuid") String doctorUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		// 标志以及必需要传的参数
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "doctorUuid,true" });
		Object callback = map.get("callback");

		// 判断stateType是否为空
		if (StringUtil.isEmpty(stateType)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "未输入订单状态"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		List relist = new ArrayList();
		String orderType = request.getParameter("orderType");
		// 查询所有患者的订单
		List<OrderMainModel> orderList = orderMainService.getOrderList(stateType, doctorUuid, 0, 0, orderType);
		if (orderList != null && orderList.size() > 0) {
			for (OrderMainModel orm : orderList) {
				Map<String, Object> save = new HashMap<String, Object>();
				if (null != orm) {
					// 订单类型1:电话咨询订单 2：私人套餐
					orderType = orm.getOrderType();
					save.put("orderType", orderType);
					// 订单状态 1是未支付的 3是已支付的
					save.put("state", orm.getState());
					// 订单价格
					save.put("totalMoney", orm.getTotalMoney());
					// 预约时长
					save.put("consultDuration", orm.getConsultDuration());
					if ("1".equals(orderType)) {
						save.put("bookTime", orm.getBookTime());
						save.put("startTime", orm.getReceiveTime());
					} else {
						save.put("bookTime", "");
						save.put("startTime", "");
					}
					// order的id
					save.put("orderUuid", orm.getUuid());
					doctorUuid = orm.getDoctorUuid();
					// 医生的id
					save.put("doctorUuid", doctorUuid);
					ServicestaffModel sm = servicestaffService.getByUuid(doctorUuid);
					ServicestaffinfoModel sim = servicestaffinfoService
							.getServicestaffinfoModelByServicestaffUuid(doctorUuid);
					// 医生的姓名
					if (sm != null) {
						save.put("doctorName", sm.getRealName());
					} else {
						save.put("doctorName", "");
					}
					if (sim != null) {
						save.put("doctorSex", sim.getSex());
						save.put("doctorImg", sim.getImgUrl());
					} else {
						save.put("doctorSex", "");
						save.put("doctorImg", "");
					}
					// 订单的id
					save.put("orderMainUuid", orm.getUuid());
				}
				CustomerInfoModel cim = customerInfoService.getCustomerInfoModelByCustomerUuid(orm.getCustomerUuid());
				if (null != cim) {
					// 获取患者的头像
					save.put("imgUrl", cim.getImgUrl());
					save.put("sex", cim.getSex());
					save.put("illnessDescription", cim.getIllnessDescription());
					save.put("age", cim.getAge());
					save.put("realName", cim.getRealName());
				}

				relist.add(save);
			}
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return "";
	}

	/**
	 * 根据参数返回相应列表
	 * 
	 * @param request
	 * @param response
	 * @param Type
	 *            咨训类型0在线咨询 1电话咨询 2预约加号
	 * @return MessageList
	 */
	@RequestMapping(value = "/getConsultRecordMSG", method = RequestMethod.GET)
	public String getConsultRecordMSG(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("type") String messageType, @RequestParam("doctorUuid") String doctorUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "type,true", "doctorUuid,true" });
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");
		
		//回复状态 0：未回复 1：已回复
		String reply=request.getParameter("reply");
		// 判断是否为空
		if (StringUtil.isEmpty(messageType)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "传入类型为空，不能查询"), callback);
			return null;
		}
		// 判断是否为空
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "传入的医生id为空，不能查询"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		List<String> consultRecords = consultRecordService.getConsultRecordUuids(messageType, doctorUuid, reply);
		List relist = new ArrayList();
		if (consultRecords != null && consultRecords.size() > 0) {
			for (String customerUuid : consultRecords) {
				CustomerAppModel cam = new CustomerAppModel();
				ConsultRecordModel crm = consultRecordService.getConsultRecord(messageType, doctorUuid, reply,customerUuid);
				//
				CustomerInfoModel cm = customerInfoService.getCustomerInfoModelByCustomerUuid(customerUuid);
				if (cm != null) {
					cam.setAge(cm.getAge());
					cam.setSex(cm.getSex());
					cam.setIconImage(cm.getImgUrl());
					cam.setRealName(cm.getRealName());
				}
				cam.setConsultRecordUuid(crm.getUuid());
				cam.setContent(crm.getContent());
				cam.setCustomerUuid(customerUuid);
				cam.setCreateTime(crm.getCreateTime());
				if ("2".equals(messageType)) {
					/* 加号管理：医生审核状态0: 未审核 1:审核 2:审核未通过 */
					cam.setReply(crm.getDealState());
				} else {
					// 是否回复 0: 未回复 1：回复
					cam.setReply(crm.getReply());
				}
				relist.add(cam);
			}
		}

		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 根据参数返回相应列表
	 * 
	 * @param request
	 * @param response
	 * @param Type
	 *            咨训类型0在线咨询 1电话咨询 2预约加号
	 * @return MessageList
	 */
	@RequestMapping(value = "/getConsultRecord", method = RequestMethod.GET)
	public String getConsultRecord(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("consultRecordUuid") String consultRecordUuid,
			@RequestParam("customerUuid") String customerUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut, new String[] { "callback,true", });
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");
		// 判断是否为空
		if (StringUtil.isEmpty(consultRecordUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "传入预约加号编号为空，不能查询"), callback);
			return null;
		}

		// 判断是否为空
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "传入患者编号为空，不能查询"), callback);
			return null;
		}

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		ConsultRecordModel crm = consultRecordService.getByUuid(consultRecordUuid);
		CustomerModel cm = customerService.getByUuid(customerUuid);
		CustomerInfoModel cim = customerInfoService.getCustomerInfoModelByCustomerUuid(customerUuid);
		jsonMap.put("orderIllness", crm.getOrderIllness());// 消息字段
		jsonMap.put("orderReason", crm.getOrderReason());// 消息字段
		jsonMap.put("seeDoctorTime", crm.getSeeDoctorTime());// 消息字段
		jsonMap.put("illnessDescription", crm.getIllnessDescription());// 消息字段
		if (cm != null) {
			jsonMap.put("customerName", cm.getRealName());// 消息字段
			jsonMap.put("sex", cm.getSex());// 消息字段
			if (cim != null) {
				jsonMap.put("age", cim.getAge());// 消息字段
			} else {
				jsonMap.put("age", cim.getAge());// 消息字段
			}
			jsonMap.put("birthday", cm.getBirthday());// 消息字段
		} else {
			jsonMap.put("customerName", "");// 消息字段
			jsonMap.put("sex", "");// 消息字段
			jsonMap.put("age", "");// 消息字段
			jsonMap.put("birthday", "");// 消息字段
		}

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	// TODO:获取省 
	/**
	 * 获得省列表接口
	 */
	@RequestMapping(value = "/getProvince", method = RequestMethod.GET)
	public String getProvince(HttpServletRequest request, HttpServletResponse response) {
		// 获得省列表接口
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		Map<String, String> map = getParam(request, response, false, new String[]{"callback,false"}); // 标志

		Object callback = map.get("callback");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 得到省Model的集合
		List<ProvinceModel> provinceModelList = provinceService.getAll();
		List<Map<String, Object>> relist = new ArrayList<>();
		if (provinceModelList != null && provinceModelList.size() > 0) {
			for (ProvinceModel pm : provinceModelList) {
				Map<String, Object> save = new HashMap<>();
				save.put("provinceName", pm.getProvinceName());
				save.put("code", pm.getUuid());
				relist.add(save);
			}
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 获得区列表接口
	 */
	@RequestMapping(value = "/getRegion", method = RequestMethod.GET)
	public String getRegion(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("cityUuid") String cityUuid) {
		// 获得区列表接口
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,false", "cityUuid,true" }); // 标志
																	// 以及必需要传的参数
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 判断是否为空
		if (StringUtil.isEmpty(cityUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "传入市编码错误"), callback);
			return null;
		}
		// 得到区Model的集合
		List<RegionModel> regionModelList = regionService.getRegionModelListByCityUuid(cityUuid);

		List relist = new ArrayList();
		if (regionModelList != null && regionModelList.size() > 0) {
			for (RegionModel rm : regionModelList) {
				Map<String, Object> save = new HashMap<>();
				save.put("regionName", rm.getRegionName());
				save.put("code", rm.getUuid());
				relist.add(save);
			}
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	// TODO:获取城市

	/**
	 * 获得市列表接口
	 */
	@RequestMapping(value = "/getCity", method = RequestMethod.GET)
	public String getCity(HttpServletRequest request, HttpServletResponse response,
						  @RequestParam("provinceUuid") String provinceUuid) {
		// 获得市列表接口
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		Map<String, String> map = getParam(request, response, false, new String[]{"provinceUuid,true","callback,false"}); // 标志 以及
		// 必需要传的参数
		Object callback = map.get("callback");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 得到市Model的集合
		List<CityModel> cityModelList = cityService.getCityModelListByProvinceUuid(provinceUuid);
		// 判断是否为空
		if (StringUtil.isEmpty(provinceUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "传入省编码有误"), callback);
			return null;
		}
		List<Map<String, Object>> relist = new ArrayList<>(); // 要返回的List
		if (cityModelList != null && cityModelList.size() > 0) {
			for (CityModel cm : cityModelList) {
				Map<String, Object> save = new HashMap<>();
				save.put("cityName", cm.getCityName());
				save.put("code", cm.getUuid());
				relist.add(save);
			}
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	// TODO:获取医院

	/**
	 * 获得执业医院列表接口
	 */
	@RequestMapping(value = "/getHospital", method = RequestMethod.GET)
	public String getHospital(HttpServletRequest request, HttpServletResponse response) {
		// 获得执业医院列表接口
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		// 标志以及必需要传的参数
		Map<String, String> map = getParam(request, response, false, new String[] {"callback,false"});
		Object callback = map.get("callback");
		// 市id
		String cityUuid = request.getParameter("cityUuid");
		// 省id
		String provinceUuid = request.getParameter("provinceUuid");
		// 区id
		String regionUuid = request.getParameter("regionUuid");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 拼装的List
		List<HospitalInfoModel> hospitalInfoModelList;
		// 通过条件id得到医院Model的集合
		hospitalInfoModelList = hospitalInfoService.getByProvinceUuid(provinceUuid, cityUuid, regionUuid);

		// 循环医院列表，组装返回参数
		List<Map<String, Object>> relist = new ArrayList<>();
		if (hospitalInfoModelList != null && hospitalInfoModelList.size() > 0) {
			for (HospitalInfoModel him : hospitalInfoModelList) {
				Map<String, Object> save = new HashMap<>();
				save.put("hospitalName", him.getHospitalName());
				save.put("id", him.getUuid());
				relist.add(save);
			}
		}
		// FIXME: 2016/3/4 0004 添加其他医院 临时字符串为1
		if (!StringUtil.isEmpty(cityUuid)) {
			Map<String, Object> save = new HashMap<>();
			save.put("hospitalName", "其他医院");
			save.put("id", 1);
			relist.add(save);
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 获得科室列表接口
	 */
	@RequestMapping(value = "/getDepartment", method = RequestMethod.GET)
	public String getDepartment(HttpServletRequest request, HttpServletResponse response) {
		// 获得科室列表接口
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut, new String[] { "callback,false" }); // 标志
																											// 以及
																											// 必需要传的参数

		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 得到省Model的集合
		List<DepartmentInfoModel> departmentInfoModelList = departmentInfoService.getAll();
		List relist = new ArrayList();
		if (departmentInfoModelList != null && departmentInfoModelList.size() > 0) {
			for (DepartmentInfoModel dim : departmentInfoModelList) {
				Map<String, Object> save = new HashMap<String, Object>();
				save.put("departmentName", dim.getDepartmentName());
				save.put("id", dim.getUuid());
				relist.add(save);
			}
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 通过真实姓名获得医生列表接口
	 */
	@RequestMapping(value = "/getDoctorByRealName", method = RequestMethod.GET)
	public String getDoctorByRealName(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("realName") String realName) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "realName,true" }); // 标志
																	// 以及必需要传的参数
		Object callback = map.get("callback");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 得到市Model的集合
		List<DoctorImportModel> doctorImpors = doctorImportService.getDoctorByRealName(realName);

		List relist = new ArrayList(); // 要返回的List

		if (doctorImpors != null && doctorImpors.size() > 0) {
			for (DoctorImportModel ssim : doctorImpors) {
				// 定义map对象临时存放对象
				Map<String, Object> save = new HashMap<String, Object>();
				// 放入值
				save.put("realName", ssim.getRealName()); // 名字
				save.put("hospital", ssim.getHospitalUuid()); // 医院
				save.put("department", ssim.getDepartmentUuid()); // 执业科室
				save.put("professional", ssim.getProfessional()); // 职称
				relist.add(save);
			}
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	// TODO:完善用户图片信息

	/**
	 * 完善用户图片信息
	 */
	@RequestMapping(value = "/saveUserIconList", method = RequestMethod.GET)
	public String saveUserIconList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("userIconList") List<String> userIconList,
			@RequestParam("doctorUuid") String doctorUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		Map<String, String> map = getParam(request, response, false,
				new String[]{"callback,false","userIconList,true", "doctorUuid,true"}); // 标志
		// 以及必需要传的参数
		Object callback = map.get("callback");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 得到医生Model
		ServicestaffinfoModel servicestaffinfoModel = servicestaffinfoService
				.getServicestaffinfoModelByServicestaffUuid(doctorUuid);

		if (servicestaffinfoModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "无此医生或医生编号不正确"), callback);
			return null;
		}

		// 循环将得到的图片上传
		for (int j = 0; j < userIconList.size(); j++) {
			// 将图片存入
			if (j == 0) {
				servicestaffinfoModel.setPhotoOne(userIconList.get(j));
			} else if (j == 1) {
				servicestaffinfoModel.setPhotoTwo(userIconList.get(j));
			} else if (j == 2) {
				servicestaffinfoModel.setPhotoThree(userIconList.get(j));
			} else if (j == 3) {
				servicestaffinfoModel.setPhotoFour(userIconList.get(j));
			} else if (j == 4) {
				servicestaffinfoModel.setPhotoFive(userIconList.get(j));
			}
		}
		// 更新操作
		servicestaffinfoService.update(servicestaffinfoModel);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 完善用户图片信息
	 */
	@RequestMapping(value = "/getIconList", method = RequestMethod.GET)
	public String getIconList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("doctorUuid") String doctorUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "doctorUuid,true" }); // 标志
		// 以及必需要传的参数
		Object callback = map.get("callback");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 得到医生Model
		ServicestaffinfoModel servicestaffinfoModel = servicestaffinfoService
				.getServicestaffinfoModelByServicestaffUuid(doctorUuid);

		if (servicestaffinfoModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "无此医生或医生编号不正确"), callback);
			return null;
		}

		List relist = new ArrayList(); // 要返回的List
		Map<String, Object> save1 = new HashMap<String, Object>();
		save1.put("icon1", servicestaffinfoModel.getPhotoOne());
		save1.put("image1", servicestaffinfoModel.getImgUrlOne());

		relist.add(save1);

		Map<String, Object> save2 = new HashMap<String, Object>();
		save2.put("icon2", servicestaffinfoModel.getPhotoTwo());
		save2.put("image2", servicestaffinfoModel.getImgUrlTwo());
		relist.add(save2);

		Map<String, Object> save3 = new HashMap<String, Object>();
		save3.put("icon3", servicestaffinfoModel.getPhotoThree());
		save3.put("image3", servicestaffinfoModel.getImgUrlThree());
		relist.add(save3);

		Map<String, Object> save4 = new HashMap<String, Object>();
		save4.put("icon4", servicestaffinfoModel.getPhotoFour());
		save4.put("image4", servicestaffinfoModel.getImgUrlFour());
		relist.add(save4);

		Map<String, Object> save5 = new HashMap<String, Object>();
		save5.put("icon5", servicestaffinfoModel.getPhotoFive());
		save5.put("image5", servicestaffinfoModel.getImgUrlFive());
		relist.add(save5);
		jsonMap.put("relist", relist);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 保存医生图像信息
	 * 
	 * @param s
	 *            医生基础信息表
	 * @param in
	 *            InputStream图片流
	 * @param i
	 *            判断上传到第几个Model的图片中
	 * @return
	 */
	public ServicestaffinfoModel uploadImage(ServicestaffinfoModel s, InputStream in, int i) {
		try {
			if (in != null) {
				// 生成图片名称前缀
				String filePrefix = "dorctorinfo" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
				// 生成图片名称
				String newName = filePrefix + ".jpg";
				if (i == 1) {
					s.setPhotoOne(newName);
				} else if (i == 2) {
					s.setPhotoTwo(newName);
				} else if (i == 3) {
					s.setPhotoThree(newName);
				} else if (i == 4) {
					s.setPhotoFour(newName);
				} else if (i == 5) {
					s.setPhotoFive(newName);
				}
				// 通过InputStream流 和图片名称上传图片
				fileUpload.uploadFiles(in, newName);
			} else {
				s.setImage("");
			}
		} catch (Exception ex) {
			s.setImage("");
		}
		return s;
	}

	/**
	 * 保存医生图像信息
	 * 
	 * @param s
	 *            医生基础信息表
	 * @param imaPath
	 *            图片路径
	 * 
	 * @return
	 */
	public ServicestaffinfoModel uploadImage(ServicestaffinfoModel s, String imaPath) {

		try {
			if (!StringUtil.isEmpty(imaPath)) {
				// 生成图片名称前缀
				String filePrefix = "dorctorinfo" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
				// 生成图片名称
				String newName = filePrefix + "jpg";
				s.setTwoCode(newName);

				fileUpload.uploadFile(newName, imaPath);
			} else {
				s.setTwoCode("");
			}
		} catch (Exception ex) {
			s.setTwoCode("");
		}

		return s;
	}

	/**
	 * 获取积分通过医生id
	 */
	@RequestMapping(value = "/getNowIntegral", method = RequestMethod.GET)
	public String getNowIntegral(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("doctorUuid") String doctorUuid) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,false", "doctorUuid,true" }); // 标志
																		// 以及必需要传的参数
		Object callback = map.get("callback");

		// 判断doctorUuid是否为空
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生id不能为空"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		VipclubIntegralLogQueryModel vipclubIntegralLogQueryModel = new VipclubIntegralLogQueryModel();
		vipclubIntegralLogQueryModel.setCustomerUuid(doctorUuid);

		// 积分和
		jsonMap.put("sumIntergralCount",
				vipclubIntegralLogService.getTypeIntegralSumByConditon(vipclubIntegralLogQueryModel));// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 获取积分明细通过医生id
	 */
	@RequestMapping(value = "/getNowIntegralDetail", method = RequestMethod.GET)
	public String getNowIntegralDetail(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("doctorUuid") String doctorUuid) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "doctorUuid,true" }); // 标志
																		// 以及必需要传的参数
		Object callback = map.get("callback");

		// 判断doctorUuid是否为空
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生id不能为空"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 根据医生id获得用户积分日志实体类
		List<VipclubIntegralLogModel> vipclubIntegralLogList = vipclubIntegralLogService.getbyDoctorUuid(doctorUuid);

		// 定义返回List
		List relist = new ArrayList();
		if (vipclubIntegralLogList != null && vipclubIntegralLogList.size() > 0) {
			for (VipclubIntegralLogModel cm : vipclubIntegralLogList) {
				Map<String, Object> save = new HashMap<String, Object>();
				// 积分类型名称
				if (!StringUtil.isEmpty(cm.getTypeName())) {
					save.put("typeName", cm.getTypeName());
				} else {
					save.put("typeName", cm.getIntergralType());
				}

				// 获得积分
				save.put("intergralCount", cm.getIntergralCount());
				// 获得时间
				save.put("createTime", cm.getCreateTime());
				// 描述
				save.put("description", cm.getDescription());
				relist.add(save);
			}
		}
		// 总分
		VipclubIntegralLogQueryModel vipclubIntegralLogQueryModel = new VipclubIntegralLogQueryModel();
		vipclubIntegralLogQueryModel.setCustomerUuid(doctorUuid);

		jsonMap.put("sumIntergralCount",
				vipclubIntegralLogService.getTypeIntegralSumByConditon(vipclubIntegralLogQueryModel));
		jsonMap.put("relist", relist);// 消息字段

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 通过手机号删除医生信息
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
		ServicestaffModel servicestaffModel = servicestaffService.getServicestaffByLoginNameOrMobileOrEmail(mobile);
		if (servicestaffModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "手机号内无账号信息"), callback);
			return null;
		}
		String uuid = servicestaffModel.getUuid();
		// 删除用户信息
		servicestaffService.deleteReal(servicestaffModel);
		ServicestaffinfoModel servicestaffinfoModel = servicestaffinfoService
				.getServicestaffinfoModelByServicestaffUuid(uuid);
		if (servicestaffinfoModel != null) {
			servicestaffinfoService.deleteReal(servicestaffinfoModel);
		}
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}*/

	/**
	 * 通过讲堂名称获得讲堂课程 这里name可以传空
	 */
	@RequestMapping(value = "/getVideosByName", method = RequestMethod.POST)
	public String getVideosByName(HttpServletRequest request, HttpServletResponse response) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut, new String[] { "callback,true" });
		Object callback = map.get("callback");
		/*医生编号 */
		 String doctorUuid = request.getParameter("doctorUuid");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 视频名字和主讲人名字
		String name = "";
		name = request.getParameter("name");

		// 根据名称得到视频列表
		List<PlatFormInfoModel> platFormInfoList = platFormInfoService.getByName(name, "1");
		if (platFormInfoList == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "无视频信息"), callback);
			return null;
		}
		List<Map<String, Object>> relist = new ArrayList<Map<String, Object>>();
		if (platFormInfoList != null && platFormInfoList.size() > 0) {
			for (PlatFormInfoModel platFormInfoModel : platFormInfoList) {
				Map<String, Object> save = new HashMap<String, Object>();
				// 得到视频主键
				String videoUuid = platFormInfoModel.getUuid();
				save.put("uuid", videoUuid);

				String favoriteUuid = "";
				if(!StringUtil.isEmpty(doctorUuid)){
					favoriteUuid = favoriteService.getUuidByCustomerUuidAndVideoUuid(doctorUuid, videoUuid);
				}
						
				// 存入收藏标题
				save.put("favoriteUuid", favoriteUuid);
				// 存入视频标题
				save.put("title", platFormInfoModel.getVideoModel());
				// 存入视频简介
				save.put("videoIntroduction", platFormInfoModel.getVideoIntroduction());
				// 存入发布时间
				save.put("createTime", platFormInfoModel.getCreateTime());
				// 存入发布者
				save.put("userName", platFormInfoModel.getUserName());
				// 收藏视频的人数
				save.put("collectNum", favoriteService.getNumByVedioUuid(videoUuid));
				// 简介图片地址
				save.put("img", platFormInfoModel.getImageUrl());
				// 是否为正在热播视频
				save.put("videoHot", platFormInfoModel.getVideoHot());

				// 该医生是否收藏该视频 sc 0:未收藏 1：收藏
				int sc = 0;
				if(!StringUtil.isEmpty(doctorUuid)){
					sc = favoriteService.getcolVideoByDoctorUuidAndVideoUuid(doctorUuid, videoUuid);
				}
				save.put("sc", sc);

				relist.add(save);
			}
		}
		// 消息字段
		jsonMap.put("relist", relist);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}
	

	/**
	 * 讲堂视频详情
	 */
	@RequestMapping(value = "/getVideoByUuid", method = RequestMethod.GET)
	public String getVideoByUuid(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("videoUuid") String videoUuid) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "videoUuid,true" });
		Object callback = map.get("callback");
		String doctorUuid = request.getParameter("doctorUuid");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		if (StringUtil.isEmpty(videoUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "无视频主键"), callback);
			return null;
		}
		// 根据uuid得到视频信息
		PlatFormInfoModel platFormInfoModel = platFormInfoService.getByUuid(videoUuid);
		if (platFormInfoModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "无视频信息"), callback);
			return null;
		}
		if (!StringUtil.isEmpty(doctorUuid)) {

			// 观看视频课程记录积分
			vipclubIntegralLogService.saveVipIntegralLog(doctorUuid, "add", 0,
					IntegralType.ADD_SHARE_BY_VIDEO.getValue(), VipclubIntegralLogModel.VIPCLUB_USERTYPE_DOC,
					"分享"+platFormInfoModel.getVideoModel()+"视频成功，积分加0", "");

		}

		Map<String, Object> save = new HashMap<String, Object>();
		// 视频主键
		save.put("uuid", videoUuid);
		// 存入视频标题
		save.put("title", platFormInfoModel.getVideoModel());
		// 存入视频简介
		save.put("videoIntroduction", platFormInfoModel.getVideoIntroduction());
		// 存入发布时间
		save.put("createTime", platFormInfoModel.getCreateTime());
		// 存入发布者
		save.put("userName", platFormInfoModel.getUserName());
		// 发布者简介
		save.put("userSynopsis", platFormInfoModel.getUserIntroduce());
		// 简介图片地址
		save.put("img", platFormInfoModel.getImageUrl());
		// 视频url
		save.put("videoUrl", platFormInfoModel.getVideoAddress());
		// 是否为正在热播视频
		save.put("videoHot", platFormInfoModel.getVideoHot());
		// 消息字段
		jsonMap.put("videoInfo", save);

		/* ————————视频评论信息———————— */
		// 通过视频主键得到评论信息
		List<PlatformCommunicationModel> platformCommunicationList = platformCommunicationService
				.getByVideoUuid(videoUuid);
		if (platformCommunicationList != null && platformCommunicationList.size() > 0) {
			List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
			for (PlatformCommunicationModel platformCommunicationModel : platformCommunicationList) {
				Map<String, Object> comment = new HashMap<String, Object>();
				// 评论者
				comment.put("questionerName", platformCommunicationModel.getQuestionerName());
				// 存入评论者类型 1:医生 2:患者
				save.put("userType", platformCommunicationModel.getUserType());
				// 评论时间
				comment.put("questionerTime", platformCommunicationModel.getCreateTime());
				// 评论内容
				comment.put("questionerContext", platformCommunicationModel.getProblemDescription());
				reList.add(comment);
			}
			// 视频评论信息
			jsonMap.put("videoComment", reList);
		} else {
			jsonMap.put("videoComment", new ArrayList<Map<String, Object>>());
		}
		/* ————————视频评论信息———————— */
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 添加视频评论
	 */
	@RequestMapping(value = "/addPlatformCommunication", method = RequestMethod.POST)
	public String addPlatformCommunication(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("doctorUuid") String doctorUuid, @RequestParam("platformUuid") String platformUuid,
			@RequestParam("context") String context, @RequestParam("userType") String userType) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		// 标志以及必需要传的参数
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "doctorUuid,true", "platformUuid,true", "userType,true" });
		Object callback = map.get("callback");

		// 判断是否为空
		if (StringUtil.isEmpty(context)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "评论内容不能为空"), callback);
			return null;
		}
		// 判断doctorUuid是否为空
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生id为空"), callback);
			return null;
		}
		// 判断platformUuid是否为空
		if (StringUtil.isEmpty(platformUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "视频id为空"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		String name = "";
		if ("1".equals(userType)) {
			// 根据医生id得到医生姓名
			name = servicestaffService.getServiceStaffNameByUuid(doctorUuid);
			if (StringUtil.isEmpty(name)) {
				HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生姓名为空，添加评论时没有医生姓名"),
						callback);
				return null;
			}
		} else if ("2".equals(userType)) {
			// 根据患者id得到患者姓名
			name = customerService.getCustomerNameByCustomerUuid(doctorUuid);
			if (StringUtil.isEmpty(name)) {
				HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "患者姓名为空，添加评论时没有患者姓名"),
						callback);
				return null;
			}
		}

		// 添加评论信息
		PlatformCommunicationModel platformCommunicationModel = new PlatformCommunicationModel();
		// 视频uuid
		platformCommunicationModel.setPlatformUuid(platformUuid);
		// 视频名称
		PlatFormInfoModel platFormInfoModel = platFormInfoService.getByUuid(platformUuid);
		if (platFormInfoModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "视频id有误"), callback);
			return null;
		}
		platformCommunicationModel.setPlatformrName(platFormInfoModel.getVideoModel());
		// 提问人名称
		platformCommunicationModel.setQuestionerName(name);
		// 用户类型 1:医生 2:患者
		platformCommunicationModel.setUserType(userType);
		// 审核通过
		platformCommunicationModel.setConftimeState("1");
		// 提问人编号
		platformCommunicationModel.setQuestionerUuid(doctorUuid);
		// 问题描述
		platformCommunicationModel.setProblemDescription(context);

		platformCommunicationService.create(platformCommunicationModel);

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 查看医生权限
	 * 
	 * @param request
	 * @param response
	 * @param doctorUuid
	 * @param open
	 * @param type
	 * @return
	 */

	@RequestMapping(value = "/getDoctorRight", method = RequestMethod.GET)
	public String getDoctorRight(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("doctorid") String doctorUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "doctorid,true", "callback,true" });
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");

		// 判读医生id是否为空
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生id不存在"), callback);
			return null;
		}
		// 得到doctorRightModel
		DoctorRightModel doctorRightModel = doctorRightService.getByDoctorUuid(doctorUuid);
		// 如果权限表不存在，创建该医生的权限表，默认所有为未开通
		if (doctorRightModel == null) {
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
		}

		// 返回医生的权限列表
		jsonMap.put("exam", doctorRightModel.getExam());
		jsonMap.put("disturb", doctorRightModel.getDisturb());
		jsonMap.put("plus", doctorRightModel.getPlus());
		jsonMap.put("teletext", doctorRightModel.getTeletext());
		jsonMap.put("telDisturb", doctorRightModel.getTelDisturb());
		jsonMap.put("phone", doctorRightModel.getPhone());
		jsonMap.put("personal", doctorRightModel.getPersonal());
		jsonMap.put("personalExam", doctorRightModel.getPersonalExam());
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 查看医生是否开通 私人医生（即套餐）权限
	 * 
	 * @param request
	 * @param response
	 * @param doctorid
	 * @param open
	 * @param type
	 * @return
	 */

	@RequestMapping(value = "/getPackageDoctor", method = RequestMethod.GET)
	public String getPackageDoctor(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("doctorUuid") String doctorUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "doctorUuid,true", "callback,true" });
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");

		// 判读医生id是否为空
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生id不存在"), callback);
			return null;
		}
		// 得到所有套餐
		List<PackageManagementModel> packageManagementlist = packageManagementService.getAll();

		if (packageManagementlist == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "不存在套餐"), callback);
			return null;
		}
		// 返回list
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		for (PackageManagementModel packageManagementModel : packageManagementlist) {
			Map<String, Object> reMap = new HashMap<String, Object>();
			String packageUuid = packageManagementModel.getUuid();

			// 得到套餐医生关联关系表
			PackageDoctorModel packageDoctorModel = packageDoctorService.getByDoctorUuidAndPackageUuid(doctorUuid,
					packageUuid);
			// 如果套餐关联关系表不存在，就表示未开通
			if (packageDoctorModel == null) {
				// 医生是否开通该套餐，N表示未开通，Y表示开通
				reMap.put("state", "N");
			} else {
				// 医生是否开通该套餐，N表示未开通，Y表示开通
				reMap.put("state", "Y");
				// 主键
				// reMap.put("packageDoctorUuid", packageDoctorModel.getUuid());
			}
			// 套餐主键
			reMap.put("packageUuid", packageManagementModel.getUuid());
			// 套餐名
			reMap.put("packageName", packageManagementModel.getPackageName());
			// 套餐时长
			reMap.put("duration", packageManagementModel.getDuration());
			// 预约加号次数
			reMap.put("plusTimes", packageManagementModel.getPlusTimes());
			// 电话咨询次数
			reMap.put("phoneTimes", packageManagementModel.getPhoneTimes());
			// 套餐价格
			reMap.put("money", packageManagementModel.getMoney());

			reList.add(reMap);
		}

		jsonMap.put("reList", reList);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 医生套餐开通未开通修改
	 * 
	 * @param request
	 * @param response
	 * @param doctorid
	 * @param packageUuid
	 * @param state
	 *            0表示开通(添加) 1表示取消开通（删除）
	 * @return
	 */

	@RequestMapping(value = "/updatePackageDoctor", method = RequestMethod.GET)
	public String updatePackageDoctor(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("doctorid") String doctorid, @RequestParam("packageUuid") String packageUuid,
			@RequestParam("state") String state) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "doctorid,true", "callback,true" });
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");

		// 判读医生id是否为空
		if (StringUtil.isEmpty(doctorid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生id不存在"), callback);
			return null;
		}
		// 判读套餐id是否为空
		if (StringUtil.isEmpty(packageUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "套餐id不存在"), callback);
			return null;
		}
		// 判读state是否为空
		if (StringUtil.isEmpty(state)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "状态不存在"), callback);
			return null;
		} else if (state.equals("0")) {
			// state为0时表示添加
			PackageDoctorModel packageDoctorModel = new PackageDoctorModel();
			packageDoctorModel.setDoctorUuid(doctorid);
			packageDoctorModel.setPackageUuid(packageUuid);
			packageDoctorService.create(packageDoctorModel);
		} else if (state.equals("1")) {
			// state为0时表示删除
			PackageDoctorModel packageDoctorModel = packageDoctorService.getByDoctorUuidAndPackageUuid(doctorid,
					packageUuid);
			packageDoctorService.delete(packageDoctorModel);
		} else {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "状态参数值错误，只能为1或0"), callback);
			return null;
		}

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 查看医生提取账单
	 * 
	 * @param request
	 * @param response
	 * @param doctorid
	 * @param open
	 * @param type
	 * @return
	 */

	@RequestMapping(value = "/getWithdraApply", method = RequestMethod.GET)
	public String getWithdraApply(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("doctorid") String doctorid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "doctorid,true", "callback,true" });
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");

		// 判读医生id是否为空
		if (StringUtil.isEmpty(doctorid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生id不存在"), callback);
			return null;
		}
		// 通过医生id得到提取账单
		List<WithdrawApplyModel> withdraApplyList = withdrawApplyService.getByDoctorUuid(doctorid);
		if (withdraApplyList == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "无提取账单信息"), callback);
			return null;
		}
		List<Map<String, Object>> relist = new ArrayList<Map<String, Object>>();
		if (withdraApplyList != null && withdraApplyList.size() > 0) {
			for (WithdrawApplyModel withdrawApplyModel : withdraApplyList) {
				Map<String, Object> save = new HashMap<String, Object>();
				// 得到主键
				String uuid = withdrawApplyModel.getUuid();
				save.put("uuid", uuid);
				// 提取金额
				save.put("applyMoney", withdrawApplyModel.getApplyMoney());
				// 提现时间
				save.put("createTime", withdrawApplyModel.getSuccessTime());
				// 处理状态 0：表示未处理 1：表示已转账（提现成功） 2：表示驳回（提现失败）
				save.put("state", withdrawApplyModel.getState());
				relist.add(save);
			}
		}
		// 消息字段
		jsonMap.put("relist", relist);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 查看医生收入账单
	 * 
	 * @param request
	 * @param response
	 * @param doctorid
	 * @return
	 */

	@RequestMapping(value = "/getOrderRouting", method = RequestMethod.GET)
	public String getOrderRouting(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("doctorid") String doctorid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "doctorid,true", "callback,true" });
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");

		// 判读医生id是否为空
		if (StringUtil.isEmpty(doctorid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生id不存在"), callback);
			return null;
		}
		// 通过医生id得到提取账单
		List<OrderRoutingModel> orderRoutingList = orderRoutingService.getByDoctorUuid(doctorid);

		if (orderRoutingList == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "无收入账单信息"), callback);
			return null;
		}
		List<Map<String, Object>> relist = new ArrayList<Map<String, Object>>();
		if (orderRoutingList != null && orderRoutingList.size() > 0) {
			for (OrderRoutingModel orderRoutingModel : orderRoutingList) {
				Map<String, Object> save = new HashMap<String, Object>();
				// 得到主键
				String uuid = orderRoutingModel.getUuid();
				save.put("uuid", uuid);
				// 收入类型 0：是电话咨询 ；1：是私人医生 2:加号 3:图文咨询
				save.put("incomeType", orderRoutingModel.getIncomeType());
				// 收入金额
				// 保留两位
				double rp = orderRoutingModel.getRoutPrice();
				DecimalFormat df = new DecimalFormat("0.00");
				// 四舍五入
				df.setRoundingMode(RoundingMode.HALF_UP);
				String routPrice = df.format(rp);

				save.put("routPrice", routPrice);
				// 收入时间
				save.put("routTime", orderRoutingModel.getRoutTime());

				relist.add(save);
			}
		}
		// 消息字段
		jsonMap.put("relist", relist);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 查看医生出诊时间 12/19
	 * 
	 * @author szr
	 * @param request
	 * @param response
	 * @param doctorid
	 * @return
	 */

	@RequestMapping(value = "/getHomeVisitSet", method = RequestMethod.GET)
	public String getHomeVisitSet(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("doctorUuid") String doctorid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "doctorUuid,true", "callback,true" });
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");

		// 判读医生id是否为空
		if (StringUtil.isEmpty(doctorid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生id不存在"), callback);
			return null;
		}
		// 通过医生id得到
		List<HomeVisitSetModel> homeVisitSetList = homeVisitSetService.getByDoctorUuid(doctorid);

		List<Map<String, Object>> relist = new ArrayList<Map<String, Object>>();
		if (homeVisitSetList != null && homeVisitSetList.size() > 0) {
			for (HomeVisitSetModel homeVisitSetModel : homeVisitSetList) {
				Map<String, Object> save = new HashMap<String, Object>();
				// 得到主键
				String uuid = homeVisitSetModel.getUuid();
				save.put("homeVisitSetuuid", uuid);
				// 日期 1:周一 2：周二 3：周三4：周四..7:周日
				save.put("weekDate", homeVisitSetModel.getWeekDate());
				// 时间类型 1：全天 ,2：上午,3：下午
				save.put("timeType", homeVisitSetModel.getTimeType());
				// 状态 1：开通 2：停诊
				save.put("state", homeVisitSetModel.getState());

				relist.add(save);
			}
		}
		// 消息字段
		jsonMap.put("relist", relist);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 删除医生出诊时间 12/19
	 * 
	 * @author szr
	 * @param request
	 * @param response
	 * @param homeVisitSetuuid
	 * @return
	 */

	@RequestMapping(value = "/deleteHomeVisitSet", method = RequestMethod.GET)
	public String deleteHomeVisitSet(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("homeVisitSetuuid") String homeVisitSetuuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "homeVisitSetuuid,true", "callback,true" });
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");

		// 判读医生id是否为空
		if (StringUtil.isEmpty(homeVisitSetuuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "出诊id不存在"), callback);
			return null;
		}
		// 通过出诊id得到对象，删除
		HomeVisitSetModel homeVisitSetModel = homeVisitSetService.getByUuid(homeVisitSetuuid);
		if (homeVisitSetModel != null) {
			homeVisitSetService.delete(homeVisitSetModel);
		}
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 更新医生出诊时间 12/19
	 * 
	 * @author szr
	 * @param request
	 * @param response
	 * @param homeVisitSetuuid
	 * @return
	 */
	@RequestMapping(value = "/updateHomeVisitSet", method = RequestMethod.GET)
	public String updateHomeVisitSet(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("homeVisitSetuuid") String homeVisitSetuuid, @RequestParam("state") String state) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "homeVisitSetuuid,true", "callback,true" });
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");

		// 判读医生id是否为空
		if (StringUtil.isEmpty(homeVisitSetuuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "出诊id不存在"), callback);
			return null;
		}
		// 通过出诊id得到对象，更新
		HomeVisitSetModel homeVisitSetModel = homeVisitSetService.getByUuid(homeVisitSetuuid);
		if (homeVisitSetModel != null) {
			homeVisitSetModel.setState(state);
			homeVisitSetService.update(homeVisitSetModel);
		} else {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "没有对象，更新失败"), callback);
			return null;
		}
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 添加医生出诊时间 12/19
	 * 
	 * @author szr
	 * @param request
	 * @param response
	 * @param homeVisitSetuuid
	 * @return
	 */
	@RequestMapping(value = "/addHomeVisitSet", method = RequestMethod.GET)
	public String addHomeVisitSet(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("doctorUuid") String doctorUuid, @RequestParam("weekDate") String weekDate,
			@RequestParam("timeType") String timeType, @RequestParam("state") String state) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "doctorUuid,true", "callback,true" });
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");

		// 判读医生id是否为空
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生id不存在"), callback);
			return null;
		}
		// 通过出诊id得到对象，更新
		HomeVisitSetModel homeVisitSetModel = homeVisitSetService.getHomeVisitSet(weekDate, doctorUuid);
		if (homeVisitSetModel != null) {
			homeVisitSetModel.setWeekDate(weekDate);
			homeVisitSetModel.setTimeType(timeType);
			// 添加
			homeVisitSetService.update(homeVisitSetModel);
		} else {
			homeVisitSetModel = new HomeVisitSetModel();
			homeVisitSetModel.setDoctorUuid(doctorUuid);
			homeVisitSetModel.setWeekDate(weekDate);
			homeVisitSetModel.setTimeType(timeType);
			homeVisitSetModel.setState(state);
			// 添加
			homeVisitSetService.create(homeVisitSetModel);
		}

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 根据患者id得到最新的患者病历 12/24
	 * 
	 * @author szr
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getNowMedicalRecordByCustomerUuid", method = RequestMethod.GET)
	public String getNowMedicalRecordByCustomerUuid(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("customerUuid") String customerUuid, @RequestParam("doctorUuid") String doctorUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "customerUuid,true", "callback,true" });
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");

		// 判读医生id是否为空
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "患者id不存在"), callback);
			return null;
		}
		// 通过id得到患者信息
		CustomerModel customerModel = customerService.getByUuid(customerUuid);
		if (customerModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "患者信息不存在"), callback);
			return null;
		}
		CustomerInfoModel customerInfoModel = customerInfoService.getCustomerInfoModelByCustomerUuid(customerUuid);
		if (customerInfoModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "患者基本信息不存在"), callback);
			return null;
		}
		/* ——————————患者信息———————— */
		Map<String, Object> info = new HashMap<String, Object>();
		// 患者姓名
		info.put("customerName", customerModel.getRealName());
		// 患者性别
		info.put("customerSex", customerModel.getSex());
		// 患者年龄
		info.put("customerAge", customerInfoModel.getAge());
		// 患者信息
		jsonMap.put("customerInfo", info);
		/* ——————————最新病历信息———————— */
		MedicalRecordModel medicalRecordModel = medicalRecordService.getNewestByCustomerUuidAndDoctorUuid(customerUuid,
				doctorUuid);
		if (medicalRecordModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "患者病历信息不存在"), callback);
			return null;
		}
		// 病历信息
		jsonMap.put("medicalRecord", medicalRecordModel);

		/* ——————————患者分组———————— */

		CustomerDoctorReleModel customerDoctorReleModel = customerDoctorReleService
				.getByCustomerUuidAndDoctorUuid(customerUuid, doctorUuid);
		String groupUuid = customerDoctorReleModel.getGroupUuid();
		if (StringUtil.isEmpty(groupUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "分组id不存在"), callback);
			return null;
		}
		CaseGroupModel caseGroupModel = caseGroupService.getByUuid(groupUuid);
		if (caseGroupModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "分组不存在"), callback);
			return null;
		}
		Map<String, Object> groupMap = new HashMap<String, Object>();
		// 所属分组id
		groupMap.put("groupUuid", groupUuid);
		// 所属分组名
		groupMap.put("groupName", caseGroupModel.getGroupName());
		jsonMap.put("groupMap", groupMap);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 查看预约加号12/23
	 * 
	 * @author szr
	 * @param request
	 * @param response
	 * @param doctorid
	 * @return
	 */
	@RequestMapping(value = "/getPlusConfs", method = RequestMethod.GET)
	public String getPlusConfs(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("doctorUuid") String doctorUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "doctorUuid,true", "callback,true" });
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");

		// 判读医生id是否为空
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生id不存在"), callback);
			return null;
		}
		// 通过医生id得到对象,
		List<HomeVisitSetModel> homeVisitSetList = homeVisitSetService.getByDoctorUuid(doctorUuid);
		if (homeVisitSetList == null || homeVisitSetList.size() < 0) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生不存在预约加号"), callback);
			return null;
		}
		List<Map<String, Object>> retlist = new ArrayList<Map<String, Object>>();
		String plusNum = "0";
		if (homeVisitSetList != null && homeVisitSetList.size() > 0) {
			for (HomeVisitSetModel homeVisitSetModel : homeVisitSetList) {
				Map<String, Object> save = new HashMap<String, Object>();
				if (!StringUtil.isEmpty(homeVisitSetModel.getPlusNum())) {
					plusNum = homeVisitSetModel.getPlusNum();
				}
				// 得到主键
				String homeVisitSetuuid = homeVisitSetModel.getUuid();
				save.put("homeVisitSetuuid", homeVisitSetuuid);
				// 日期 1:周一 2：周二 3：周三4：周四..7:周日
				save.put("weekDate", homeVisitSetModel.getWeekDate());
				// 时间类型 1：全天 ,2：上午,3：下午
				save.put("timeType", homeVisitSetModel.getTimeType());
				// 是否提供预约加号状态 1：提供 2：不提供
				if (!StringUtil.isEmpty(homeVisitSetModel.getPlusState())) {
					save.put("plusState", homeVisitSetModel.getPlusState());
				} else {
					save.put("plusState", "0");
				}
				retlist.add(save);
			}
		}
		jsonMap.put("plusNum", plusNum);
		List<Map<String, Object>> otherRelist = new ArrayList<Map<String, Object>>();
		// 通过 医生id 找到 DoctorTagList
		List<String> doctorTagList = doctorTagService.getByDoctorUuid(doctorUuid);
		if (doctorTagList != null && doctorTagList.size() > 0) {
			for (String tagUuid : doctorTagList) {
				Map<String, Object> save = new HashMap<String, Object>();
				String tagName = tagsService.getTagNameByUuid(tagUuid);
				// 标签名
				save.put("tagName", tagName);
				// 标签id
				save.put("tagUuid", tagUuid);
				otherRelist.add(save);
			}
		}
		jsonMap.put("retlist", retlist);
		jsonMap.put("otherRelist", otherRelist);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 修改预约加号里的加号权限,及人数12/23
	 * 
	 * @author szr
	 * @param request
	 * @param response
	 * @param doctorid
	 * @return
	 */
	@RequestMapping(value = "/updatePlusHome", method = RequestMethod.GET)
	public String updatePlusHome(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("homeVisitSetuuid") List<String> homeVisitSetuuids,
			@RequestParam("plusState") String plusState, @RequestParam("plusNum") String plusNum) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "homeVisitSetuuid,true", "callback,true", "plusState,true", "plusNum,true" });
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");

		// 判读医生id是否为空
		if (StringUtil.isEmpty(plusState)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "状态不存在"), callback);
			return null;
		}
		// 判读人数是否为空
		if (StringUtil.isEmpty(plusNum)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "人数不存在"), callback);
			return null;
		}
		// 通过出诊id得到对象，更新
		if (homeVisitSetuuids != null && homeVisitSetuuids.size() > 0) {
			for (String homeVisitSetuuid : homeVisitSetuuids) {
				HomeVisitSetModel homeVisitSetModel = homeVisitSetService.getByUuid(homeVisitSetuuid);
				if (homeVisitSetModel != null) {
					homeVisitSetModel.setPlusState(plusState);
					homeVisitSetModel.setPlusNum(plusNum);
					homeVisitSetService.update(homeVisitSetModel);
					jsonMap.put("plusState", plusState);
					jsonMap.put("homeVisitSetuuid", homeVisitSetuuid);
				} else {
					HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "没有对象，更新失败"),
							callback);
					return null;
				}
			}
		}
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 修改预约加号里的标签12/23
	 * 
	 * @author szr
	 * @param request
	 * @param response
	 * @param doctorid
	 * @return
	 */
	@RequestMapping(value = "/updateDoctorTag", method = RequestMethod.GET)
	public String updateDoctorTag(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("doctorUuid") String doctorUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "doctorUuid,true", "callback,true" });
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");
		String str = request.getParameter("tagList");
		String[] tagList = null;
		if (!StringUtil.isEmpty(str)) {
			tagList = str.split(",");
		}
		// List<String> tagList = (List<String>) obj;

		// 判读医生id是否为空
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生id不存在"), callback);
			return null;
		}

		// 通过医生id删除
		doctorTagService.deleteByDoctorUuid(doctorUuid);
		// 如果不为空添加
		if (tagList != null && tagList.length > 0) {
			for (String tagUuid : tagList) {
				com.aebiz.b2b2c.servicestaff.doctortag.vo.DoctorTagModel doctorTagModel = new com.aebiz.b2b2c.servicestaff.doctortag.vo.DoctorTagModel();
				doctorTagModel.setDoctorUuid(doctorUuid);
				doctorTagModel.setTagUuid(tagUuid);
				doctorTagService.create(doctorTagModel);
			}
		}

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	// TODO: 2016/3/18 0018 获取标签
	/**
	 * 获取标签 12/23
	 *
	 * @param request
	 * @param response
	 * @return
	 * @author szr
	 */
	@RequestMapping(value = "/getTags", method = RequestMethod.POST)
	public String getTags(HttpServletRequest request, HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		Map<String, String> map = getParam(request, response, false, new String[]{"callback,false"});

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");
		// 判读医生id是否为空
		List<TagsModel> tags = tagsService.getAll();
		// 标签名
		List<Map<String, Object>> reList = new ArrayList<>();
		if (tags != null && tags.size() > 0) {
			for (TagsModel tag : tags) {
				Map<String, Object> save = new HashMap<>();
					save.put("tagName", tag.getTagName());
				// 标签id
				save.put("tagUuid", tag.getUuid());
				reList.add(save);
			}
		}
		jsonMap.put("reList", reList);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 添加标签 12/23
	 * 
	 * @author szr
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addTag", method = RequestMethod.POST)
	public String addTag(HttpServletRequest request, HttpServletResponse response, @RequestParam("tag") String tag) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut, new String[] { "tag,true", "callback,false" });
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");

		// 判读医生id是否为空
		if (StringUtil.isEmpty(tag)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "标签名不存在"), callback);
			return null;
		}
		// 添加标签
		TagsModel tagsModel = new TagsModel();
		tagsModel.setTagName(tag);
		tagsService.create(tagsModel);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 
	 * 通过医生ID获得医生手机号 12/27
	 * 
	 * @author szr
	 * @param request
	 * @param response
	 * @param customerUuid
	 * @return
	 */
	@RequestMapping(value = "/getDoctorMobile", method = RequestMethod.GET)
	public String getMobileByDoctorUuid(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("doctorUuid") String doctorUuid) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "doctorUuid,true" }); // 标志
		// 以及必需要传的参数
		Object callback = map.get("callback");

		// 判断Uuid是否为空
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "customerUuid不能为空"),
					callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 根据患者uuid得到患者手机号
		String mobile = servicestaffService.getMobileByUuid(doctorUuid);
		// 判断手机号是否为空
		if (StringUtil.isEmpty(mobile)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "该医生无手机号码"), callback);
			return null;
		}

		jsonMap.put("mobile", mobile);// 消息字段

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 
	 * 通过在线咨询id得到消息列表 12/31
	 * 
	 * @author szr
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getMessageByConsultRecordUuid", method = RequestMethod.GET)
	public String getMessageByConsultRecordUuid(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("consultRecordUuid") String consultRecordUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "consultRecordUuid,true" }); // 标志
		// 以及必需要传的参数
		Object callback = map.get("callback");

		// 判断Uuid是否为空
		if (StringUtil.isEmpty(consultRecordUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "consultRecordUuid不能为空"),
					callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 根据consultRecordUuid得到消息列表
		List<MessageModel> messageList = messageService.getByConsultRecordUuid(consultRecordUuid);
		List<Map<String, Object>> relist = new ArrayList<Map<String, Object>>();
		if (messageList != null && messageList.size() > 0) {
			for (MessageModel messageModel : messageList) {
				Map<String, Object> save = new HashMap<String, Object>();
				// 主键
				save.put("uuid", messageModel.getUuid());
				// 消息内容
				save.put("sendContent", messageModel.getSendContent());
				// 消息来源 1：患者 2：医生
				save.put("sourceId", messageModel.getSourceId());
				// 发送状态 1：已发送 2：未发送
				save.put("status", messageModel.getStatus());
				// 发送时间
				save.put("sendTime", messageModel.getSendTime());
				// 图片
				save.put("image", messageModel.getImage());
				// 医生id
				save.put("doctorUuid", messageModel.getDoctorUuid());
				// 患者id
				save.put("customerUuid", messageModel.getCustomerUuid());
				// 在线咨询id
				save.put("consultRecordUuid", messageModel.getConsultRecordUuid());
				relist.add(save);
			}
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 
	 * 通过在医生id和患者id添加消息 12/31
	 * 
	 * @author szr
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addMessage", method = RequestMethod.GET)
	public String addMessage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("doctorUuid") String doctorUuid, @RequestParam("customerUuid") String customerUuid,
			@RequestParam("sourceId") String sourceId, @RequestParam("consultRecordUuid") String consultRecordUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut, new String[] { "callback,true",
				"doctorUuid,true", "customerUuid,true", "sourceId,true", "consultRecordUuid,true" }); // 标志
		// 以及必需要传的参数
		Object callback = map.get("callback");

		String sendContent = request.getParameter("sendContent");

		String image = request.getParameter("image");

		// 判断Uuid是否为空
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "doctorUuid不能为空"), callback);
			return null;
		}
		if (StringUtil.isEmpty(consultRecordUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "consultRecordUuid不能为空"),
					callback);
			return null;
		}
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "customerUuid不能为空"),
					callback);
			return null;
		}
		if (StringUtil.isEmpty(sourceId)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "sourceId不能为空"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		MessageModel messageModel = new MessageModel();
		messageModel.setConsultRecordUuid(consultRecordUuid);
		messageModel.setCustomerUuid(customerUuid);
		messageModel.setDoctorUuid(doctorUuid);
		messageModel.setSourceId(sourceId);
		if (StringUtil.isEmpty(sendContent)) {
			messageModel.setSendContent(sendContent);
		}
		if (StringUtil.isEmpty(image)) {
			messageModel.setImage(image);
		}
		messageModel.setSendTime(DateFormatHelper.getNowTimeStr());
		messageModel.setStatus("1");
		messageService.create(messageModel);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 
	 * 通过在messageUuid删除消息 12/31
	 * 
	 * @author szr
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/deleteMessage", method = RequestMethod.GET)
	public String deleteMessage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("messageUuid") String messageUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "messageUuid,true" }); // 标志
		// 以及必需要传的参数
		Object callback = map.get("callback");
		// 判断Uuid是否为空
		if (StringUtil.isEmpty(messageUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "messageUuid不能为空"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		MessageModel messageModel = messageService.getByUuid(messageUuid);
		// 判断Uuid是否为空
		if (messageModel != null) {
			// 删除
			messageService.delete(messageModel);
		}

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 
	 * 通过随访方案id得到costomerUuid集合 16/02/03
	 * 
	 * @author szr
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getCostomerUuidsByVisitPreceptUuid", method = RequestMethod.GET)
	public String getCostomerUuidsByVisitPreceptUuid(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("visitPreceptUuid") String visitPreceptUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "visitPreceptUuid,true" }); // 标志
		// 以及必需要传的参数
		Object callback = map.get("callback");
		// 判断Uuid是否为空
		if (StringUtil.isEmpty(visitPreceptUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "visitPreceptUuid不能为空"),
					callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		List<String> customerUuids = visitRecordService.getCostomerUuidsByVisitPreceptUuid(visitPreceptUuid);

		// 判断Uuid是否为空
		if (customerUuids != null && customerUuids.size() > 0) {
			// 患者Uuid集合
			jsonMap.put("customerUuids", customerUuids);
		}

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	public static void main(String[] args) {
		List relist = new ArrayList(); // 要返回的List
		Map<String, Object> save = new HashMap<String, Object>();
		save.put("icon1", "11111");
		save.put("image1", "11111");

		relist.add(save);
		Map<String, Object> save1 = new HashMap<String, Object>();

		save1.put("icon2", "11111");
		save1.put("image2", "11111");
		relist.add(save1);
		String jsonString = JsonUtils.getJSONString(relist);
		System.out.println(jsonString);
	}
}
