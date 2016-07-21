package com.aebiz.b2b2c.customermgr.mobile.web.customer.user;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aebiz.b2b2c.basebusiness.utils.DateUtil;
import com.aebiz.b2b2c.baseframework.filemgr.helper.FileUploadHelper;
import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.filemgr.vo.FileModel;
import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.content.service.ContentService;
import com.aebiz.b2b2c.cms.content.vo.ContentModel;
import com.aebiz.b2b2c.cms.contentlist.service.ContentListService;
import com.aebiz.b2b2c.cms.innermessage.service.InnerMessageService;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageModel;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageTypeEnum;
import com.aebiz.b2b2c.cms.platforminfo.service.PlatFormInfoService;
import com.aebiz.b2b2c.cms.platforminfo.vo.PlatFormInfoModel;
import com.aebiz.b2b2c.cms.tags.service.TagsService;
import com.aebiz.b2b2c.cms.tags.vo.TagsModel;
import com.aebiz.b2b2c.customermgr.consultrecord.service.ConsultRecordService;
import com.aebiz.b2b2c.customermgr.consultrecord.vo.ConsultRecordModel;
import com.aebiz.b2b2c.customermgr.consultrecordreply.service.ConsultRecordReplyService;
import com.aebiz.b2b2c.customermgr.consultrecordreply.vo.ConsultRecordReplyModel;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.customermgr.customerinfo.service.CustomerInfoService;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoModel;
import com.aebiz.b2b2c.customermgr.drugnotice.service.DrugNoticeService;
import com.aebiz.b2b2c.customermgr.drugnotice.vo.DrugNoticeModel;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.AppBaseController;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.HttpServletUtils;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.MobileUtils;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.SysConst;
import com.aebiz.b2b2c.customermgr.mobile.web.push.PushConst;
import com.aebiz.b2b2c.order.ordermain.service.OrderMainService;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;
import com.aebiz.b2b2c.product.utils.ImageUtils;
import com.aebiz.b2b2c.servicestaff.doctornotice.service.DoctorNoticeService;
import com.aebiz.b2b2c.servicestaff.doctornotice.vo.DoctorNoticeModel;
import com.aebiz.b2b2c.servicestaff.doctorright.service.DoctorRightService;
import com.aebiz.b2b2c.servicestaff.doctorright.vo.DoctorRightModel;
import com.aebiz.b2b2c.servicestaff.homevisitset.service.HomeVisitSetService;
import com.aebiz.b2b2c.servicestaff.homevisitset.vo.HomeVisitSetModel;
import com.aebiz.b2b2c.servicestaff.packagedoctor.service.PackageDoctorService;
import com.aebiz.b2b2c.servicestaff.packagemanagement.service.PackageManagementService;
import com.aebiz.b2b2c.servicestaff.packagemanagement.vo.PackageManagementModel;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;
import com.aebiz.b2b2c.servicestaff.servicestaffinfo.service.ServicestaffinfoService;
import com.aebiz.b2b2c.servicestaff.servicestaffinfo.vo.ServicestaffinfoModel;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.service.VipclubIntegralLogService;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.IntegralType;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.VipclubIntegralLogModel;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomercharge.service.VirtualAccountCustomerChargeService;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomerlog.service.VirtualAccountCustomerLogService;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomerlog.vo.VirtualAccountCustomerLogModel;
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
import com.aebiz.b2b2c.visitprecept.illnessrecord.service.IllnessRecordService;
import com.aebiz.b2b2c.visitprecept.illnessrecord.vo.IllnessRecordModel;
import com.aebiz.b2b2c.visitprecept.medicalrecord.service.MedicalRecordService;
import com.aebiz.b2b2c.visitprecept.medicalrecord.vo.MedicalRecordModel;
import com.aebiz.b2b2c.visitprecept.visitrecord.service.VisitRecordService;
import com.aebiz.b2b2c.visitprecept.visitrecord.vo.VisitRecordModel;
import com.aebiz.b2b2c.visitprecept.visitrecordextend.service.VisitRecordExtendService;
import com.aebiz.b2b2c.visitprecept.visitrecordextend.vo.VisitRecordExtendModel;
import com.aebiz.b2b2c.websiteoperation.concern.service.ConcernService;
import com.aebiz.b2b2c.websiteoperation.concern.vo.ConcernModel;
import com.aebiz.b2b2c.websiteoperation.doctorshare.service.DoctorShareService;
import com.aebiz.b2b2c.websiteoperation.doctorshare.vo.DoctorShareModel;
import com.aebiz.b2b2c.websiteoperation.favorite.service.FavoriteService;
import com.aebiz.b2b2c.websiteoperation.favorite.vo.FavoriteModel;
import com.hxq.mobile.common.service.BasicDataService;
import com.hxq.mobile.common.service.ServiceStaffInfoService;
import com.hxq.mobile.common.service.ServiceStaffService;
import com.hxq.mobile.doctor.visit.service.DoctorCustomerService;
import com.hxq.mobile.entity.common.DepartmentInfo;
import com.hxq.mobile.entity.common.HospitalInfo;
import com.hxq.mobile.entity.common.ServiceStaff;
import com.hxq.mobile.entity.common.ServiceStaffInfo;
import com.hxq.mobile.support.service.ImgUploadService;
import com.hxq.mobile.util.CompatibleTools;
import com.hxq.mobile.util.Image4App;
import com.wxcommon.util.DateUtils;
import com.wxcommon.util.MathUtils;
import com.wxcommon.util.ObjectUtils;

/**
 * 患者端服务接口控制类
 * 
 * @author szr
 * 
 */
@Controller
@RequestMapping("/app/customer/patient")
public class AppPatientController extends AppBaseController {
	/**
	 * 构造方法
	 */
	public AppPatientController() {
		super("", "", AppPatientController.class);
	}


	@Autowired
	private DoctorRightService doctorRightService;
	@Autowired
	private ServicestaffService servicestaffService;
	@Autowired
	private ServicestaffinfoService servicestaffinfoService;
	@Autowired
	private OrderMainService orderMainService;
	
	/*医生基础信息service2.0*/
	@Autowired
	@Resource(name = "com.hxq.mobile.common.service.ServiceStaffService")
	private ServiceStaffService serviceStaffService;
	
	@Autowired
	@Resource(name = "com.hxq.mobile.common.service.ServiceStaffInfoService")
	private ServiceStaffInfoService serviceStaffInfoService;

	/* 消息service */
	@Autowired
	private InnerMessageService innerMessageService;
	@Autowired
	private ConsultRecordService consultRecordService;

	@Autowired
	private VipclubIntegralLogService vipclubIntegralLogService;

	// 患者关注医生service
	@Autowired
	private ConcernService concernService;
	// 标签service
	@Autowired
	private TagsService tagsService;

	@Resource(name = "com.hxq.mobile.support.service.ImgUploadService")
	private ImgUploadService imgUploadService;

    @Resource(name = "com.hxq.mobile.common.service.BasicDataService")
    private BasicDataService basicDataService;
    
    @Resource(name = "com.hxq.mobile.doctor.visit.service.DoctorCustomerService")
    private DoctorCustomerService doctorCustomerService;

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
	

	@Autowired
	private DoctorShareService doctorShareService;
	@Autowired
	private FileUploadHelper fileUpload;

	@Autowired
	PlatFormInfoService platFormInfoService;

	/*
	 * 注入导入医生信息表
	 * 
	 * @Autowired private DoctorImportService doctorImportService;
	 */

	/* 注入患者医生关联关系表 */
	@Autowired
	private CustomerDoctorReleService customerDoctorReleService;

	/* 随访记录信息表 */
	@Autowired
	private VisitRecordService visitRecordService;
	/*
	 * 注入私人套餐的Service
	 */
	@Autowired
	private PackageManagementService packageManagementService;

	/*
	 * 注入医嘱的Service
	 */
	@Autowired
	private DoctorAdviceService doctorAdviceService;

	/*
	 * 注入患者病例的Service
	 */
	@Autowired
	private MedicalRecordService medicalRecordService;
	/*
	 * 注入健康指导的Service
	 */
	@Autowired
	private HealthGuideService healthGuideService;

	/*
	 * 消费记录的Service
	 */
	@Autowired
	private VirtualAccountCustomerLogService virtualAccountCustomerLogService;

	@Autowired
	private VirtualAccountCustomerChargeService virtualAccountCustomerChargeService;
	/*
	 * 病情记录的Service
	 */
	@Autowired
	private IllnessRecordService illnessRecordService;
	/*
	 * 药物不良反应的Service
	 */
	@Autowired
	private DrugReactionService drugReactionService;

	/*
	 * 其他的记录的Service
	 */
	@Autowired
	private VisitRecordExtendService VisitRecordExtendService;

	/*
	 * 收藏的的Service
	 */
	@Autowired
	private FavoriteService favoriteService;

	/*
	 * 资讯的Service
	 */
	@Autowired
	private ContentService contentService;

	/*
	 * 会员建议的Service
	 */
	@Autowired
	private CustomerAdviceService customerAdviceService;

	/*
	 * 今日推荐service
	 */
	@Autowired
	private DoctorNoticeService doctorNoticeService;

	/*
	 * 医生基本service
	 */
	@Autowired
	private ServicestaffinfoService servicestaffInfoService;
	/*
	 * 服药提醒的service
	 */
	@Autowired
	private DrugNoticeService drugNoticeService;
	/*
	 * 医生关联套餐的service
	 */
	@Autowired
	private PackageDoctorService packageDoctorService;
	/*
	 * 获取文章的service
	 */
	@Autowired
	private ContentListService contentListService;

	/* 咨询回复 service */
	@Autowired
	private ConsultRecordReplyService crrService;

	/* 加号设置的 service */
	@Autowired
	private HomeVisitSetService homeVisitSetService;

	@Autowired
	private FileService fileService;

	/**
	 * 患者端首页获取名医风采
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getFamousDoctors", method = RequestMethod.GET)
	public String getFamousDoctors(HttpServletRequest request, HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		Map<String, String> map = getParam(request, response, false, new String[] { "callback,false" });

		Object callback = map.get("callback");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获得名医List
		List<ServicestaffModel> servicestaffList = servicestaffService.getFamousDoctors();
		if (servicestaffList == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "无名医信息"), callback);
			return null;
		}

		// 返回list
		List<Map<String, Object>> reList = new ArrayList<>();

		for (ServicestaffModel servicestaffModel : servicestaffList) {
			String uuid = servicestaffModel.getUuid();
			Map<String, Object> reMap = new HashMap<>();
			// 医生名
			reMap.put("doctorName", servicestaffModel.getRealName());
			// 医生头像
			reMap.put("img", servicestaffModel.getImgUrl());
			// 执业医院
			reMap.put("hospitalName", servicestaffModel.getHospitalName());
			// 执业科室
			reMap.put("departmentName", servicestaffModel.getDepartmentName());
			// 医生擅长
			reMap.put("territory", servicestaffModel.getTerritory());
			// 医生uuid
			reMap.put("doctorUuid", uuid);
			// 医生职称
			reMap.put("professional", servicestaffModel.getProfessional());
			reList.add(reMap);
		}

		jsonMap.put("reList", reList);

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;
	}

	/**
	 * 患者端电话咨询查询医生 搜索条件暂时为医生姓名
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getDoctors", method = RequestMethod.POST)
	public String getDoctors(HttpServletRequest request, HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut, new String[] { "callback,false" });

		Object callback = map.get("callback");
		// 条件，可能是医生姓名，可能是科室
		String doctorConditon = request.getParameter("doctorConditon");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 通过查询条件，得到医生
		long a1 = System.currentTimeMillis();
		List<ServicestaffModel> servicestaffList = servicestaffService.getDoctors(doctorConditon);
		
	//	serviceStaffService.get
		long a2 = System.currentTimeMillis();
		// 返回list
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		if (servicestaffList != null && servicestaffList.size() > 0) {
			for (ServicestaffModel servicestaffModel : servicestaffList) {
				// 医生uuid
				String uuid = servicestaffModel.getUuid();
				Map<String, Object> reMap = new HashMap<String, Object>();
				
				

			

			
				
				// 医生性别
				reMap.put("sex", servicestaffModel.getSex());
				
				// 医生名
				reMap.put("doctorName", servicestaffModel.getRealName());
				
				
				// 执业医院
		        HospitalInfo hospitalModel;
				try {
					hospitalModel = (HospitalInfo) basicDataService.select(new HospitalInfo(servicestaffModel.getHospital()));
					
					if(hospitalModel != null){
			        	reMap.put("hospital", hospitalModel.getUuid());//所在医院uuid
			        	reMap.put("hospitalName", hospitalModel.getHospitalName());//所在医院
			        }
					
					
				     // 执业科室
			        DepartmentInfo departmentModel = (DepartmentInfo) basicDataService.select(new DepartmentInfo(servicestaffModel.getDepartment()));
			        if(departmentModel != null){
			        	reMap.put("department", departmentModel.getUuid());//所在科室uuid
			        	reMap.put("departmentName", departmentModel.getDepartmentName());//所在科室
			        }
			
			        int customerNumModel = doctorCustomerService.selectCustomerNumModel(uuid);
			        reMap.put("customerNum", customerNumModel);// 获取患者数量
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	
		
				// 是否开通电话咨询 0：未开通 1：开通
				reMap.put("telState", servicestaffModel.getTelState());
				
		
				// 是否开通图文资讯 0：未开通 1：开通
				reMap.put("teletext", servicestaffModel.getTeletext());
				// 是否开通预约加号 0：未开通 1：开通
				reMap.put("plus", servicestaffModel.getPlus());
				// 是否开通私人医生 0：未开通 1：开通
				reMap.put("personal", servicestaffModel.getPersonal());
	

				// 医生uuid
				reMap.put("doctorUuid", uuid);
				// 医生服务次数
				reMap.put("serviceCount", servicestaffModel.getServiceTimes());
		        
		
	
				//ServiceStaffInfo ssinfo = servicestaffinfoService.selectByServiceStaffUuid(uuid);
				
				ServicestaffinfoModel ssinfo = servicestaffinfoService.getServicestaffinfoModelByServicestaffUuid(uuid);
				
				
				
				if (ssinfo != null) {
					reMap.put("territory", ssinfo.getTerritory());// 擅长领域
					reMap.put("professional", ssinfo.getProfessional());// 职称

					
					
					
					reMap.put("synopsis", ssinfo.getSynopsis());// 简介
					reMap.put("sex", ssinfo.getSex());// 性别
					
					
		
			
					
					
		
					reMap.put("imageId", ssinfo.getImage());// 头像id
					reMap.put("certImageId", ssinfo.getCertImage());// 证件照id
		
					
					Image4App[] url = CompatibleTools.getImages(imgUploadService, fileService, (String)ssinfo.getImage());
					//医生图像
					String imgUrl = "";
					if (!ObjectUtils.isEmpty(url)){
						imgUrl = url[0].getLarge();
					}
					
					
					// 医生头像
					reMap.put("img", imgUrl);
				}
				
				


				reList.add(reMap);

			}
		}
		jsonMap.put("reList", reList);
		long a8 = System.currentTimeMillis();
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		long a9 = System.currentTimeMillis();

		System.out.println("=======================================getDoctors==" + (a2 - a1) + " ,loop total=="
				+ (a8 - a2) + ", outJsonCallBack==" + (a9 - a8));
		return null;
	}

	// TODO:找医生
	/**
	 * 找医生筛选 通过城市id，医院id，医生专长(医生标签id) 2016/01/07
	 * 
	 * @param request
	 *            request
	 * @param response
	 *            response
	 * @return string
	 */
	@RequestMapping(value = "/getDoctorsBySelect", method = RequestMethod.GET)
	public String getDoctorsBySelect(HttpServletRequest request, HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		Map<String, String> map = getParam(request, response, false, new String[] { "callback,false" });

		Object callback = map.get("callback");
		String city = request.getParameter("city");

		// String territorys = "";
		String[] hospitalUuids = null;
		String strTerritorys = request.getParameter("territorys");
		// if (!StringUtil.isEmpty(strTerritorys)) {
		// territorys = strTerritorys.replace(",", ";");
		// }
		String strHospitalUuids = request.getParameter("hospitalUuids");
		if (!StringUtil.isEmpty(strHospitalUuids)) {
			hospitalUuids = strHospitalUuids.split(",");
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 通过查询条件，得到医生
		List<ServicestaffModel> servicestaffList = servicestaffService.getDoctorsBySelect(city, hospitalUuids,
				strTerritorys);
		// 返回list
		List<Map<String, Object>> reList = new ArrayList<>();
		if (servicestaffList != null) {
			for (ServicestaffModel servicestaffModel : servicestaffList) {
				// 医生uuid
				String uuid = servicestaffModel.getUuid();
				Map<String, Object> reMap = new HashMap<>();

				// 医生名
				reMap.put("doctorName", servicestaffModel.getRealName());
				// 医生uuid
				reMap.put("doctorUuid", uuid);
				// 医生头像
				reMap.put("img", servicestaffModel.getImgUrl());
				// 执业医院
				reMap.put("hospitalName", servicestaffModel.getHospitalName());
				// 执业科室
				reMap.put("departmentName", servicestaffModel.getDepartmentName());
				// 是否开通电话咨询 0：未开通 1：开通
				DoctorRightModel doctorRightModel = doctorRightService.getByDoctorUuid(uuid);
				if (doctorRightModel != null) {
					// 电话咨询
					reMap.put("telState", doctorRightModel.getPhone());
					// 加号预约
					reMap.put("plus", doctorRightModel.getPlus());
					// 图文咨询
					reMap.put("teletext", doctorRightModel.getTeletext());
					// 私人医生
					reMap.put("personal", doctorRightModel.getPersonal());
					// 通过医生主键查询医生电话咨询已处理的数量
				} else {
					reMap.put("telState", "0");
				}
				// 医生性别
				reMap.put("sex", servicestaffModel.getSex());
				// 医生服务次数
				reMap.put("serviceCount", servicestaffModel.getServiceTimes());
				// 医生职称
				reMap.put("professional", servicestaffModel.getProfessional());
				reList.add(reMap);
			}
		}

		jsonMap.put("reList", reList);

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;
	}

	/**
	 * 患者端获取所有标签
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getTags", method = RequestMethod.GET)
	public String getTags(HttpServletRequest request, HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut, new String[] { "callback,true" });

		Object callback = map.get("callback");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获得名医List
		List<TagsModel> tagsList = tagsService.getAll();
		if (tagsList == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "无标签"), callback);
			return null;
		}

		// 返回list
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();

		for (TagsModel tagsModel : tagsList) {
			Map<String, Object> reMap = new HashMap<String, Object>();
			// 标签名
			reMap.put("tagName", tagsModel.getTagName());
			// 标签uuid
			reMap.put("tagUuid", tagsModel.getUuid());
			reList.add(reMap);

		}

		jsonMap.put("reList", reList);

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;
	}

	/**
	 * 电话咨询生成未支付订单接口
	 * 
	 * @author xp
	 */
	@RequestMapping(value = "/saveTelOrder", method = RequestMethod.POST)
	public String saveTelOrder(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("customerUuid") String customerUuid, @RequestParam("doctorUuid") String doctorUuid,
			@RequestParam("bookTime") String bookTime, @RequestParam("receiveTime") String receiveTime,
			@RequestParam("consultDuration") String consultDuration,
			@RequestParam("consultContent") String consultContent, @RequestParam("totalMoney") String totalMoney) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "customerUuid,true", "doctorUuid,true", "bookTime,true",
						"receiveTime,true", "consultDuration,true", "consultContent,true", "totalMoney,true" }); // 标志
																													// 以及必需要传的参数
		Object callback = map.get("callback");

		// 判断adUuid是否为空
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "用户未登录"), callback);
			return null;
		}
		// 判断adUuid是否为空
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "该医生不存在"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 生成电话咨询未支付订单
		String customerName = customerService.getCustomerNameByCustomerUuid(customerUuid);
		OrderMainModel om = new OrderMainModel();
		om.setOrderType("1");
		om.setDoctorUuid(doctorUuid);
		om.setCustomerUuid(customerUuid);
		om.setCustomerName(customerName);
		om.setBookTime(bookTime);
		om.setReceiveTime(receiveTime);
		om.setConsultDuration(consultDuration);
		om.setTotalMoney(Double.parseDouble(totalMoney));
		om.setConsultContent(consultContent);
		orderMainService.create(om);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 
	 * @Description: (跳转到预约加号页面)
	 * @author XP
	 * @param request
	 * @param response
	 * @param doctorUuid
	 * @param customerUuid
	 * @return
	 * @date 2016-1-24 下午2:06:44
	 */
	@RequestMapping(value = "/toPlusPage", method = RequestMethod.GET)
	public String toPlusPage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("doctorUuid") String doctorUuid, @RequestParam("customerUuid") String customerUuid) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "doctorUuid,true", "customerUuid,true" }); // 标志
																							// 以及必需要传的参数
		Object callback = map.get("callback");

		// 判断doctorUuid是否为空
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生id不能为空"), callback);
			return null;
		}
		// 判断customerUuid是否为空
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "用户未登录，请先登录"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 获取医生的model
		ServicestaffModel sfm = servicestaffService.getByUuid(doctorUuid);
		ServicestaffinfoModel sim = servicestaffinfoService.getServicestaffinfoModelByServicestaffUuid(doctorUuid);
		// 获取患者的model
		CustomerModel cm = customerService.getByUuid(customerUuid);
		List<HomeVisitSetModel> hvs = homeVisitSetService.getByDoctorUuid(doctorUuid, "1");
		// 定义返回List
		List<Map<String, Object>> relist = new ArrayList<>();
		List<Map<String, Object>> hvsList = new ArrayList<>();
		if (sfm != null && sim != null && cm != null) {
			Map<String, Object> save = new HashMap<>();
			// 医生的真实姓名
			save.put("doctorRealName", sfm.getRealName());
			// 医生的头像
			save.put("doctorIcon", sim.getImgUrl());
			// 医生的职称
			save.put("professional", sim.getProfessional());
			// 医生所在医院
			save.put("hospitalName", sfm.getHospitalName());
			// 医生所在科室
			save.put("departmentName", sfm.getDepartmentName());
			// 患者真实姓名
			save.put("customerRealName", cm.getRealName());
			// 患者的性别
			save.put("customerSex", cm.getSex());// 1代表男 ；2代表女
			// 患者的出生日期
			save.put("customerBirthday", cm.getBirthday());
			relist.add(save);

		}
		if (hvs != null && hvs.size() > 0) {
			for (HomeVisitSetModel vs : hvs) {
				Map<String, Object> save = new HashMap<>();
				// 医生的真实姓名
				save.put("weekDate", vs.getWeekDate());
				// 医生的头像
				save.put("timeType", vs.getTimeType());
				save.put("plusNum", vs.getPlusNum());
				hvsList.add(save);
				jsonMap.put("hvsList", hvsList);// 消息字段
			}

		}
		jsonMap.put("relist", relist);// 消息字段

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 
	 * @Description: (保存预约加号的接口)
	 * @author XP
	 * @param request
	 * @param response
	 * @param customerUuid
	 * @param doctorUuid
	 * @param seeDoctorTime
	 * @param orderIllness
	 * @param orderReason
	 * @param illnessDescription
	 * @return
	 * @date 2016-1-24 下午2:07:29
	 */
	@RequestMapping(value = "/savePlus", method = RequestMethod.POST)
	public String savePlus(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("customerUuid") String customerUuid, @RequestParam("doctorUuid") String doctorUuid,
			@RequestParam("seeDoctorTime") String seeDoctorTime, @RequestParam("orderIllness") String orderIllness,
			@RequestParam("orderReason") String orderReason,
			@RequestParam("illnessDescription") String illnessDescription) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "customerUuid,true", "doctorUuid,true", "seeDoctorTime,true",
						"orderIllness,true", "orderReason,true", "illnessDescription,true" }); // 标志
																								// 以及必需要传的参数
		Object callback = map.get("callback");

		String plusNote = request.getParameter("plusNote");
		// 判断adUuid是否为空
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "用户未登录"), callback);
			return null;
		}
		// 判断adUuid是否为空
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "该医生不存在"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取设置的时间类型1：全天 ,2：上午,3：下午
		String timeType = request.getParameter("timeType");

		String weekDay = "";
		// 获取周几
		if (!StringUtil.isEmpty(seeDoctorTime)) {
			weekDay = DateUtil.getWeekDay(seeDoctorTime);
			int day = Integer.parseInt(weekDay);
			if (day == 1) {
				day = 7;
			} else {
				day = day - 1;
			}
			weekDay = day + "";
		}
		// 获取医生设置的加号的人数
		int plusNum = homeVisitSetService.getPlusNumByDoctorUuidAndWeekDayAndTimeType(doctorUuid, weekDay, timeType);
		if (plusNum <= 0) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "该医生这个时间段 还未开通预约加号"),
					callback);
			return null;
		}

		System.out.println("===============plusNum============" + plusNum);
		// 获取已经预约的患者的人数
		int patientNum = consultRecordService.getPatientNumByDoctorUuidAndCustomerUuidAndTimeType(doctorUuid, timeType,
				seeDoctorTime);
		System.out.println("===============patientNum============" + patientNum);

		// 预约加号人数未满生成加号预约订单
		if (patientNum < plusNum) {
			ConsultRecordModel om = new ConsultRecordModel();
			om.setDoctorUuid(doctorUuid);
			om.setCustomerUuid(customerUuid);
			om.setPlusNote(plusNote);
			om.setSeeDoctorTime(seeDoctorTime);
			om.setOrderIllness(orderIllness);
			om.setOrderReason(orderReason);
			om.setIllnessDescription(illnessDescription);
			om.setConsultType(ConsultRecordModel.TYPE_ORDER);
			om.setCreateTime(DateFormatHelper.getNowTimeStr());
			om.setState("0");
			om.setExam("0");
			om.setDealState("0");
			om.setTimeType(timeType);
			consultRecordService.create(om);
			String customerName = "";
			customerName = customerService.getCustomerNameByCustomerUuid(customerUuid);
			// szr 推送消息 提交预约加号信息 弹出消息显示XX患者预约时间等信息
			String content = customerName + MessageHelper.getMessage("message.savePlusPushDoctor") + "时间为:"
					+ om.getCreateTime();
			innerMessageService.saveInnerMessageAndPush(customerUuid, doctorUuid, content,
					InnerMessageModel.ACCOUNT_TYPE_CUSTOMER, InnerMessageModel.ACCOUNT_TYPE_STORE,
					PushConst.push_client_service, "consultRecordUuid", om.getUuid(),
					InnerMessageTypeEnum.SUBSCRIBE.getValue());
		} else {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "该时间的加号人数已满，请预约其他时间。"),
					callback);
			return null;
		}
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 跳转到私人医生的页面
	 * 
	 * @author xp
	 */
	@RequestMapping(value = "/toPersonnalPage", method = RequestMethod.GET)
	public String toPersonnalPage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("doctorUuid") String doctorUuid,
			@RequestParam("packageManagementUuid") String packageManagementUuid) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "doctorUuid,true", "packageManagementUuid,true" }); // 标志
																									// 以及必需要传的参数
		Object callback = map.get("callback");

		// 判断doctorUuid是否为空
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生id不能为空"), callback);
			return null;
		}
		// 判断customerUuid是否为空
		if (StringUtil.isEmpty(packageManagementUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "请选择具体的套餐"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 获取医生的model
		ServicestaffModel sfm = servicestaffService.getByUuid(doctorUuid);
		ServicestaffinfoModel sim = servicestaffinfoService.getServicestaffinfoModelByServicestaffUuid(doctorUuid);
		// 获取套餐管理的model
		PackageManagementModel cm = packageManagementService.getByUuid(packageManagementUuid);
		// 定义返回List
		List relist = new ArrayList();
		if (sfm != null && sim != null && cm != null) {
			Map<String, Object> save = new HashMap<String, Object>();
			// 医生的真实姓名
			save.put("doctorRealName", sfm.getRealName());
			// 医生的性别
			save.put("sex", sim.getSex());
			// 医生的职称
			save.put("professional", sim.getProfessional());
			// 医生所在医院
			save.put("hospitalName", sfm.getHospitalName());
			// 医生所在科室
			save.put("departmentName", sfm.getDepartmentName());
			// 套餐名称
			save.put("packageName", cm.getPackageName());
			// 套餐价格
			save.put("packageMoney", cm.getMoney());
			// 图文咨询次数
			save.put("netTimes", "1");// 1代表无限次
			// 电话咨询次数
			save.put("phoneTimes", cm.getPhoneTimes());
			// 加号咨询次数
			save.put("plusTimes", cm.getPlusTimes());
			// 私人医生的套餐的id
			save.put("packageManagementUuid", packageManagementUuid);
			relist.add(save);

		}

		jsonMap.put("relist", relist);// 消息字段

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 获取所有的医生重要的医嘱
	 * 
	 * @author xp
	 */
	@RequestMapping(value = "/getAllDoctorAdvice", method = RequestMethod.GET)
	public String getAllDoctorAdvice(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("customerUuid") String customerUuid) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		// 标志以及必需要传的参数
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "customerUuid,true" });
		Object callback = map.get("callback");

		// 判断customerUuid是否为空
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "请登录"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		List relist = new ArrayList();
		// 查处所有的给该患者医嘱的医生的ID
		List<String> doctorIds = doctorAdviceService.getAllDoctorUuidByCustomerUuid(customerUuid);
		if (doctorIds != null && doctorIds.size() > 0) {
			for (String doctorId : doctorIds) {
				if (!StringUtil.isEmpty(doctorId)) {
					Map<String, Object> save = new HashMap<String, Object>();
					DoctorAdviceModel dam = doctorAdviceService.getModelByDoctorIdAndCustomerUuid(customerUuid,
							doctorId);
					if (dam != null) {
						// save.put("doctorName", dam.getDoctorName());
						save.put("productName", dam.getMedicineUuid());
						// save.put("doctorId", dam.getServiceStaffUuid());
						save.put("dosage", dam.getDosage());
						save.put("frequency", dam.getFrequency());
						save.put("directions", dam.getDirections());
						save.put("adivceType", dam.getState());
						save.put("unit", dam.getUnit());
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
	 * 获取所有的医生重要的医嘱
	 * 
	 * @author xp
	 */
	@RequestMapping(value = "/getDoctorAdvicesByDoctorUuid", method = RequestMethod.GET)
	public String getDoctorAdvices(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("doctorUuid") String doctorUuid, @RequestParam("customerUuid") String customerUuid) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		// 标志以及必需要传的参数
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "customerUuid,true" });
		Object callback = map.get("callback");

		// 判断customerUuid是否为空
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "请登录"), callback);
			return null;
		}
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生编号不能为空"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 查处所有的给该患者医嘱的医生的ID
		List relist = new ArrayList();
		// 根据医生和患者的id获取该患者的所有的医嘱
		List<DoctorAdviceModel> doctorAdviceModelList = doctorAdviceService
				.getDoctorAdivceListByCustomerUuidAndDoctorUuid(doctorUuid, customerUuid);
		if (doctorAdviceModelList != null && doctorAdviceModelList.size() > 0) {
			for (DoctorAdviceModel dam : doctorAdviceModelList) {
				Map<String, Object> save = new HashMap<String, Object>();
				if (dam != null) {
					save.put("doctorAdviceUuid", dam.getUuid());
					save.put("productName", dam.getMedicineUuid());
					// save.put("doctorName", dam.getDoctorName());
					save.put("dosage", dam.getDosage());
					save.put("frequency", dam.getFrequency());
					save.put("directions", dam.getDirections());
					save.put("unit", dam.getUnit());
					save.put("cureNote", dam.getCureNote());
					save.put("createTime", dam.getCreateTime());
					save.put("adivceType", dam.getState());
					// 药物不良反应处理(数据库中没有找到)
					// save.put("drugReaction",dam.getDrugReaction());
					relist.add(save);
				}
			}
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 患者端我的医生服务记录 12/20</br>
	 * 获得以下 1:电话咨询订单 2：私人套餐 0:在线咨询即图文咨询 3预约加号
	 * 
	 * 
	 * @author szr
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "getServicesByCustomerUuid",
			"1.0/getServicesByCustomerUuid" }, method = RequestMethod.GET)
	public String getServicesByCustomerUuid(HttpServletRequest request, HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,false", "customerUuid,true" });

		Object callback = map.get("callback");
		// 患者uuid
		String customerUuid = map.get("customerUuid");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 判断患者id是否为空
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "患者主键为空"), callback);
			return null;
		}
		// 返回list 0:在线咨询即图文咨询
		List<Map<String, Object>> picList = new ArrayList<Map<String, Object>>();
		// 返回list 1:电话咨询List
		List<Map<String, Object>> telList = new ArrayList<Map<String, Object>>();
		// 返回list 2：私人套餐List
		List<Map<String, Object>> privateList = new ArrayList<Map<String, Object>>();
		// 返回list 3预约加号
		List<Map<String, Object>> plusList = new ArrayList<Map<String, Object>>();
		// 通过查询条件，得到患者的1:电话咨询订单
		List<OrderMainModel> orderMainList = orderMainService.getOrderList("1", customerUuid, 0, 0);
		if (orderMainList != null && orderMainList.size() > 0) {
			for (OrderMainModel orderMainModel : orderMainList) {
				String doctorUuid = orderMainModel.getDoctorUuid();
				Map<String, Object> reMap = this.getDoctor(doctorUuid);
				// 订单类型 1:电话咨询订单 2：私人套餐
				reMap.put("type", orderMainModel.getOrderType());
				// 订单Uuid
				reMap.put("orderUuid", orderMainModel.getUuid());
				reMap.put("opeTime", orderMainModel.getOrderTime());

				telList.add(reMap);
			}
		}
		jsonMap.put("telList", telList);
		// 通过查询条件，得到患者的2：私人套餐
		List<OrderMainModel> orderMainList1 = orderMainService.getOrderList("2", customerUuid, 0, 0);
		if (orderMainList1 != null && orderMainList1.size() > 0) {
			for (OrderMainModel orderMainModel : orderMainList1) {
				String doctorUuid = orderMainModel.getDoctorUuid();
				Map<String, Object> reMap = this.getDoctor(doctorUuid);
				// 订单类型 2：私人套餐
				reMap.put("type", orderMainModel.getOrderType());
				// 订单Uuid
				reMap.put("orderUuid", orderMainModel.getUuid());
				reMap.put("opeTime", orderMainModel.getOrderTime());
				// 套餐信息
				PackageManagementModel packageManagementModel = packageManagementService
						.getByUuid(orderMainModel.getPackageUuid());
				// 套餐名称
				reMap.put("packageName", packageManagementModel.getPackageName());
				// 价格
				reMap.put("money", packageManagementModel.getMoney());
				// 电话咨询次数
				reMap.put("phoneTimes", packageManagementModel.getPhoneTimes());
				// 预约加号次数
				reMap.put("plusTimes", packageManagementModel.getPlusTimes());
				reMap.put("createTime", packageManagementModel.getCreateTime());
				reMap.put("endTime", packageManagementModel.getEndTime());

				privateList.add(reMap);
			}
		}
		jsonMap.put("privateList", privateList);

		// 咨询类型 0在线咨询即图文咨询
		List<ConsultRecordModel> imageTextRecordList = consultRecordService.getByCustomerUuid(customerUuid, "0");
		// 2预约加号
		List<ConsultRecordModel> plusRecordList = consultRecordService.getByCustomerUuid(customerUuid, "2");
		// 如果imageTextRecordList不为空，放返回值
		if (imageTextRecordList != null) {
			for (ConsultRecordModel consultRecordModel : imageTextRecordList) {
				String doctorUuid = consultRecordModel.getDoctorUuid();
				Map<String, Object> ImageTextMap = this.getDoctor(doctorUuid);
				// 图文资讯的主键
				ImageTextMap.put("consultRecordUuid", consultRecordModel.getUuid());
				// 类型咨询类型 0在线咨询即图文咨询
				ImageTextMap.put("type", "0");
				ImageTextMap.put("opeTime", consultRecordModel.getCreateTime());
				picList.add(ImageTextMap);
			}
		}
		jsonMap.put("picList", picList);
		// 如果plusRecordList不为空，放返回值
		if (plusRecordList != null) {
			for (ConsultRecordModel consultRecordModel : plusRecordList) {
				String doctorUuid = consultRecordModel.getDoctorUuid();
				Map<String, Object> ImageTextMap = this.getDoctor(doctorUuid);
				// 加号资讯的主键
				ImageTextMap.put("consultRecordUuid", consultRecordModel.getUuid());
				// 加号就诊时间
				ImageTextMap.put("seeDoctorTime", consultRecordModel.getSeeDoctorTime());
				// 类型咨询类型 3预约加号
				ImageTextMap.put("type", "3");
				ImageTextMap.put("opeTime", consultRecordModel.getCreateTime());

				plusList.add(ImageTextMap);
			}
		}
		jsonMap.put("plusList", plusList);

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/* ——————————私有方法——————————— */
	/**
	 * 根据医生uuid返回一个通用的map，以便返回到页面
	 * 
	 * @param doctorUuid
	 * @return
	 */
	private Map<String, Object> getDoctor(String doctorUuid) {
		Map<String, Object> reMap = new HashMap<String, Object>();
		if (!StringUtil.isEmpty(doctorUuid)) {
			ServicestaffModel servicestaffModel = servicestaffService.getByUuid(doctorUuid);
			ServicestaffinfoModel servicestaffinfoModel = servicestaffinfoService
					.getServicestaffinfoModelByServicestaffUuid(doctorUuid);

			if (servicestaffModel == null || servicestaffinfoModel == null) {
				reMap.put("error", "该医生Uuid下无医生信息");
				return reMap;
			}
			// 医生名
			reMap.put("doctorName", servicestaffModel.getRealName());
			// 医生性别
			reMap.put("sex", servicestaffinfoModel.getSex());
			// 医生头像
			reMap.put("img", servicestaffinfoModel.getImgUrl());
			// 执业医院
			reMap.put("hospitalName", servicestaffModel.getHospitalName());
			// 执业科室
			reMap.put("departmentName", servicestaffModel.getDepartmentName());
			// 医生擅长
			reMap.put("territory", servicestaffinfoModel.getTerritory());
			// 医生uuid
			reMap.put("doctorUuid", doctorUuid);
			// 医生职称
			reMap.put("professional", servicestaffinfoModel.getProfessional());
		}

		return reMap;
	}

	/* ——————————私有方法——————————— */

	/**
	 * 患者端关注的医生
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "getAttentionDoctors", "1.0/getAttentionDoctors" }, method = RequestMethod.GET)
	public String getAttentionDoctors(HttpServletRequest request, HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,false", "customerUuid,true" });

		Object callback = map.get("callback");
		//
		String customerUuid = map.get("customerUuid");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 判断参数是否为空
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "患者id为空"), callback);
			return null;
		}
		// 通过查询条件，得到医生
		List<ConcernModel> concernList = concernService.getByCustomerUuid(customerUuid);

		// 返回list
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		if (concernList != null && concernList.size() > 0) {
			for (ConcernModel concernModel : concernList) {
				// 医生id
				String doctorUuid = concernModel.getDoctorUuid();
				String createTime = concernModel.getCreateTime();
				Map<String, Object> reMap = this.getDoctor(doctorUuid);
				reMap.put("createTime", createTime);

				// 获取该医生的咨询总量(图文资讯的加电话咨询的数量的和)
				int consultNum = consultRecordService.getConsultNumBydoctorId(doctorUuid);
				int totalTelNum = orderMainService.getTotalTelNumByDoctorId(doctorUuid);
				int totalNum = consultNum + totalTelNum;
				reMap.put("totalNum", totalNum);

				// 获取医生的接诊量
				int allOrderNum = consultRecordService.getAllOrderNumByDoctorId(doctorUuid);

				int visitRecordNum = visitRecordService.getVisitRecordNumByDoctorId(doctorUuid);

				int receTotalNum = consultNum + allOrderNum + totalTelNum + visitRecordNum;
				reMap.put("receTotalNum", receTotalNum);

				reList.add(reMap);
			}
		}

		jsonMap.put("reList", reList);

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;
	}

	/**
	 * 患者端取消关注的医生
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/cancleAttentionDoctors", method = RequestMethod.POST)
	public String cancleAttentionDoctors(HttpServletRequest request, HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "customerUuid,true", "doctorUuid,true" });

		Object callback = map.get("callback");
		//
		String customerUuid = map.get("customerUuid");
		String doctorUuid = map.get("doctorUuid");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 判断参数是否为空
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "患者id为空"), callback);
			return null;
		}
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生id为空"), callback);
			return null;
		}
		// 患者取消关注医生
		// 通过医生id和患者id查询出患者关注医生model
		ConcernModel concernModel = concernService.getByCustomerAndDoctorUuid(customerUuid, doctorUuid);

		if (concernModel != null) {
			concernService.delete(concernModel);
		}

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;
	}

	/**
	 * 获取该医生的所有的医嘱
	 * 
	 * @author xp
	 */
	@RequestMapping(value = "/getDoctorAdviceList", method = RequestMethod.GET)
	public String getDoctorAdviceList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("visitRecordUuid") String visitRecordUuid) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut, new String[] { "callback,true" }); // 标志
																											// 以及必需要传的参数
		Object callback = map.get("callback");
		// 判断customerUuid是否为空
		// 医生id不能为空
		if (StringUtil.isEmpty(visitRecordUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "随访编号不能为空"), callback);
			return null;
		}
		VisitRecordModel vr = visitRecordService.getByUuid(visitRecordUuid);
		if (vr == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "随访对象为空"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		List relist = new ArrayList();
		// 根据医生和患者的id获取该患者的所有的医嘱
		List<DoctorAdviceModel> doctorAdviceModelList = doctorAdviceService.getByVisitRecordUuid(visitRecordUuid);
		if (doctorAdviceModelList != null && doctorAdviceModelList.size() > 0) {
			for (DoctorAdviceModel dam : doctorAdviceModelList) {
				Map<String, Object> save = new HashMap<String, Object>();
				if (dam != null) {
					save.put("doctorAdviceUuid", dam.getUuid());
					save.put("productName", dam.getMedicineUuid());
					// save.put("doctorName", dam.getDoctorName());
					save.put("dosage", dam.getDosage());
					save.put("frequency", dam.getFrequency());
					save.put("directions", dam.getDirections());
					save.put("unit", dam.getUnit());
					save.put("createTime", dam.getCreateTime());
					// 0代表已过期 ；1代表未过期
					save.put("adivceType", dam.getState());
					save.put("cureNote", dam.getCureNote());
					relist.add(save);
				}
			}
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 获取总病例
	 * 
	 * @author xp
	 */
	@RequestMapping(value = "/getAllMedicalRecord", method = RequestMethod.GET)
	public String getAllMedicalRecord(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("customerUuid") String customerUuid) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "customerUuid,true" }); // 标志
																		// 以及必需要传的参数
		Object callback = map.get("callback");

		// 判断customerUuid是否为空
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "请登录"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		List relist = new ArrayList();
		// 查处所有的给该患者医嘱的医生的ID
		List<String> doctorIds = medicalRecordService.getAllDoctorUuidByCustomerUuid(customerUuid);
		if (doctorIds != null && doctorIds.size() > 0) {
			for (String doctorId : doctorIds) {
				if (!StringUtil.isEmpty(doctorId)) {
					Map<String, Object> save = new HashMap<String, Object>();
					String doctorName = servicestaffService.getRealNameByUuid(doctorId);
					save.put("doctorUuid", doctorId);
					save.put("doctorName", doctorName);
					save.put("medicalRecordName", "我的病例");
					relist.add(save);
				}
			}
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 
	 * 获取病例列表
	 * 
	 * @author xp
	 */
	@RequestMapping(value = "/getMedicalRecordList", method = RequestMethod.GET)
	public String getMedicalRecordList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("doctorUuid") String doctorUuid, @RequestParam("customerUuid") String customerUuid) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,false", "doctorUuid,true", "customerUuid,true" }); // 标志
																							// 以及必需要传的参数
		Object callback = map.get("callback");

		// 判断customerUuid是否为空
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "请登录"), callback);
			return null;
		}

		// 判断customerUuid是否为空
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "该医生不存在"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		List relist = new ArrayList();
		// 根据医生和患者的id获取该患者的所有的病例
		List<MedicalRecordModel> list = medicalRecordService
				.getMedicalRecordListByCustomerUuidAndDoctorUuid(customerUuid, doctorUuid);
		if (list != null && list.size() > 0) {
			for (MedicalRecordModel mrm : list) {
				if (mrm != null) {
					Map<String, Object> save = new HashMap<String, Object>();
					save.put("medicalRecordUuid", mrm.getUuid());
					// 病例类型 0住院病例 1门诊病例
					save.put("caseCategoryType", mrm.getCaseCategoryType());
					save.put("createTime", mrm.getCreateTime());
					relist.add(save);
				}
			}
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 
	 * 获取或者的病情描述（诊断）
	 * 
	 * @author xl
	 */
	@RequestMapping(value = "/getIllnessDescription", method = RequestMethod.GET)
	public String getIllnessDescription(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("customerUuid") String customerUuid) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut, new String[] { "customerUuid,true" });

		Object callback = request.getParameter("callback");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		CustomerInfoModel customerInfo = customerInfoService.getCustomerInfoModelByCustomerUuid(customerUuid);
		String illnessDescription = "";
		if (customerInfo != null) {
			illnessDescription = customerInfo.getIllnessDescription();
		}
		jsonMap.put("illnessDescription", illnessDescription);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 
	 * 保存病情描述（诊断）
	 * 
	 * @author xl
	 */
	@RequestMapping(value = "/saveIllnessDescription", method = RequestMethod.GET)
	public String saveIllnessDescription(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("customerUuid") String customerUuid) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut, new String[] { "customerUuid,true" });

		Object callback = request.getParameter("callback");
		String illnessDescription = request.getParameter("illnessDescription");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		CustomerInfoModel customerInfo = customerInfoService.getCustomerInfoModelByCustomerUuid(customerUuid);
		if (customerInfo != null) {
			customerInfo.setIllnessDescription(illnessDescription);
			customerInfoService.update(customerInfo);
		}
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 
	 * 获取病例详情
	 * 
	 * @author xp
	 */
	@RequestMapping(value = "/getMedicalRecordDetail", method = RequestMethod.GET)
	public String getMedicalRecordDetail(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("medicalRecordUuid") String medicalRecordUuid) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "medicalRecordUuid,true" });

		Object callback = map.get("callback");

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		List relist = new ArrayList();
		// 根据病例id获取病例的model
		MedicalRecordModel mrm = medicalRecordService.getByUuid(medicalRecordUuid);
		if (mrm != null) {
			Map<String, Object> save = new HashMap<String, Object>();
			String caseType = mrm.getCaseCategoryType();
			// 就诊医院
			save.put("hospitalName", mrm.getHospitalName());
			// 就诊医生
			save.put("doctorName", mrm.getDoctorName());
			save.put("doctorUuid", mrm.getDoctorUuid());
			// 病情记录
			// 图片1
			save.put("image1", mrm.getImg1Url());
			// 图片2
			save.put("image2", mrm.getImg2Url());
			// 图片3
			save.put("image3", mrm.getImg3Url());
			// 图片4
			save.put("image4", mrm.getImg4Url());
			// 图片5
			save.put("image5", mrm.getImg5Url());

			// 图片1
			save.put("img1", mrm.getImage1());
			// 图片2
			save.put("img2", mrm.getImage2());
			// 图片3
			save.put("img3", mrm.getImage3());
			// 图片4
			save.put("img4", mrm.getImage4());
			// 图片5
			save.put("img5", mrm.getImage5());
			// 主诉
			save.put("mainsuit", mrm.getMainsuit());
			// 既往史
			save.put("previous", mrm.getPrevious());
			// 个人史
			save.put("personal", mrm.getPersonal());
			// 躯体状况
			save.put("somastate", mrm.getSomastate());
			// 家族史
			save.put("family", mrm.getFamily());
			// 现病史
			save.put("illness", mrm.getIllness());
			// 精神检查
			save.put("spiritCheck", mrm.getSpiritCheck());
			// 辅助检查
			save.put("assistCheck", mrm.getAssistCheck());
			save.put("assistCheckImgUrl", mrm.getAssistCheckImgUrl());
			save.put("assistCheckImage", mrm.getAssistCheckImage());
			save.put("diagnosis", mrm.getDiagnosis());
			save.put("diagnosisImage", mrm.getDiagnosisImage());
			save.put("diagnosisImgUrl", mrm.getDiagnosisImgUrl());

			// 病程
			save.put("process", mrm.getProcess());
			// 发作次数
			save.put("attackNum", mrm.getAttackNum());
			if (!StringUtil.isEmpty(caseType) && caseType.equals("1")) {
				// 就诊类型
				save.put("caseCategoryType", "1");
				// 就诊时间
				save.put("seeDoctorTime", mrm.getSeeDoctorTime());
			}
			if (!StringUtil.isEmpty(caseType) && caseType.equals("0")) {
				// 就诊类型
				save.put("caseCategoryType", "0");
				// 住院时间
				save.put("startTime", mrm.getStartTime());
				// 出院时间
				save.put("endTime", mrm.getEndTime());
				// 循环药物治疗方案 type 0 是药物治疗 1是其他治疗
				List<DoctorAdviceModel> list = doctorAdviceService.getAllDoctorAdivceByUuidAndType(medicalRecordUuid,
						"1");
				if (list != null && list.size() > 0) {
					for (DoctorAdviceModel dam : list) {
						if (dam != null) {
							// 药物名称
							// save.put("productName", dam.getProductName());
							// 单量
							save.put("dosage", dam.getDosage());
							// 频率
							save.put("frequency", dam.getFrequency());
							// 指导
							save.put("directions", dam.getDirections());
						}
					}
				}
				// 循环药物治疗方案 type 0 是药物治疗 1是其他治疗
				List<DoctorAdviceModel> list1 = doctorAdviceService.getAllDoctorAdivceByUuidAndType(medicalRecordUuid,
						"0");
				if (list1 != null && list1.size() > 0) {
					for (DoctorAdviceModel dam : list1) {
						if (dam != null) {
							// 其他治疗
							save.put("cureNote", dam.getCureNote());
						}
					}
				}
			}
			relist.add(save);
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 获取所有的健康指导
	 * 
	 * @author xp
	 */
	@RequestMapping(value = "/getAllHealthGuide", method = RequestMethod.GET)
	public String getAllHealthGuide(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("customerUuid") String customerUuid) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "customerUuid,true" }); // 标志
																		// 以及必需要传的参数
		Object callback = map.get("callback");

		// 判断customerUuid是否为空
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "请登录"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		List relist = new ArrayList();
		// 查处所有的给该患者医嘱的医生的ID
		List<String> doctorIds = healthGuideService.getAllDoctorUuidByCustomerUuid(customerUuid);
		if (doctorIds != null && doctorIds.size() > 0) {
			for (String doctorId : doctorIds) {
				if (!StringUtil.isEmpty(doctorId)) {
					Map<String, Object> save = new HashMap<String, Object>();
					HealthGuideModel dam = healthGuideService.getHealthGuideIdAndCustomerUuid(customerUuid, doctorId);
					if (dam != null) {
						save.put("doctorName", dam.getDoctorName());
						save.put("diet", dam.getDiet());
						save.put("doctorUuid", dam.getServiceStaffUuid());
						save.put("state", dam.getState());
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
	 * 获取该医生的健康指导
	 * 
	 * @author xp
	 */
	@RequestMapping(value = "/getHealthGuideList", method = RequestMethod.GET)
	public String getHealthGuideList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("doctorUuid") String doctorUuid, @RequestParam("customerUuid") String customerUuid) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "doctorUuid,true", "customerUuid,true" }); // 标志
																							// 以及必需要传的参数
		Object callback = map.get("callback");

		// 判断customerUuid是否为空
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "请登录"), callback);
			return null;
		}

		// 判断customerUuid是否为空
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "该医生不存在"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		List relist = new ArrayList();
		// 根据医生和患者的id获取该患者的所有的健康指导
		List<HealthGuideModel> healthGuideModelList = healthGuideService
				.getHealthGuideListByCustomerUuidAndDoctorUuid(doctorUuid, customerUuid);
		if (healthGuideModelList != null && healthGuideModelList.size() > 0) {
			for (HealthGuideModel dam : healthGuideModelList) {
				Map<String, Object> save = new HashMap<String, Object>();
				if (dam != null) {
					int guideType = 0;// 0代表已过期 ；1代表未过期
					String nowTime = DateFormatHelper.getNowTimeStr();// 获取系统当前时间
					save.put("diet", dam.getDiet());
					save.put("sports", dam.getSports());
					save.put("rest", dam.getRest());
					save.put("createTime", dam.getCreateTime());
					save.put("note", dam.getNote());
					save.put("state", dam.getState());
					if (nowTime != null && dam.getCreateTime() != null) {
						if (nowTime.compareTo(dam.getCreateTime()) < 0) {
							guideType = 1;
						}
					}
					save.put("guideType", guideType);
					relist.add(save);
				}
			}
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 获取该医生的健康指导
	 * 
	 * @author xp
	 */
	@RequestMapping(value = "/getHealthGuides", method = RequestMethod.GET)
	public String getHealthGuides(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("visitRecordUuid") String visitRecordUuid) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut, new String[] { "callback,true" }); // 标志
																											// 以及必需要传的参数
		Object callback = map.get("callback");

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		List<Map<String, Object>> relist = new ArrayList<>();
		// 根据医生和患者的id获取该患者的所有的健康指导
		List<HealthGuideModel> healthGuideModelList = healthGuideService
				.getHealthGuideListByVisitRecordUuid(visitRecordUuid);

		if (healthGuideModelList != null && healthGuideModelList.size() > 0) {
			for (HealthGuideModel dam : healthGuideModelList) {
				Map<String, Object> save = new HashMap<String, Object>();
				if (dam != null) {
					int guideType = 0;// 0代表已过期 ；1代表未过期
					String nowTime = DateFormatHelper.getNowTimeStr();// 获取系统当前时间
					save.put("diet", dam.getDiet());
					save.put("sports", dam.getSports());
					save.put("rest", dam.getRest());
					save.put("createTime", dam.getCreateTime());
					save.put("note", dam.getNote());
					save.put("state", dam.getState());
					if (dam.getCreateTime() != null) {
						if (nowTime.compareTo(dam.getCreateTime()) < 0) {
							guideType = 1;
						}
					}
					save.put("guideType", guideType);
					relist.add(save);
				}
			}
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 余额支付接口
	 * 
	 * @author xp
	 */
	@RequestMapping(value = "/toPayment", method = RequestMethod.GET)
	public String toPayment(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("customerUuid") String customerUuid, @RequestParam("orderMainUuid") String orderMainUuid) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "orderMainUuid,true", "customerUuid,true" }); // 标志
																								// 以及必需要传的参数
		Object callback = map.get("callback");

		// 判断orderMainUuid是否为空
		if (StringUtil.isEmpty(orderMainUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "没有订单"), callback);
			return null;
		}
		// 判断customerUuid是否为空
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "用户未登录，请先登录"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 获取患者的余额
		double accountAmount = customerService.getAccountAmountByUuid(customerUuid);
		double totalMoney = 0;
		// 支付状态 0代表是余额不足 ，1代表是支付成功
		int payType = 0;
		OrderMainModel omm = orderMainService.getByUuid(orderMainUuid);
		if (omm != null) {
			// 获取订单的金额
			totalMoney = omm.getTotalMoney();
			if (accountAmount < totalMoney) {
				List relist = new ArrayList();
				Map<String, Object> save = new HashMap<String, Object>();
				save.put("accountAmount", accountAmount);
				save.put("payType", payType);
				relist.add(save);
				jsonMap.put("relist", relist);
				HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "对不起，余额不足"), callback);
				return null;
			} else {
				List relist = new ArrayList();
				Map<String, Object> save = new HashMap<String, Object>();
				// 修改患者账户中的余额
				CustomerModel cm = customerService.getByUuid(customerUuid);
				if (cm != null) {
					cm.setAccountAmount(accountAmount - totalMoney);
					customerService.update(cm);
				}
				// 生成消费记录
				VirtualAccountCustomerLogModel vam = new VirtualAccountCustomerLogModel();
				vam.setCustomerUuid(customerUuid);
				vam.setOperType(VirtualAccountCustomerLogModel.OUT);
				vam.setOperAmount((float) totalMoney);
				vam.setNowBalance((float) (accountAmount - totalMoney));
				vam.setOrderMainUuid(orderMainUuid);
				vam.setPayType("3");
				vam.setOrderType(omm.getOrderType());
				vam.setOrderTime(omm.getOrderTime());
				virtualAccountCustomerLogService.create(vam);
				omm.setState("3");
				orderMainService.update(omm);
				payType = 1;
				save.put("payType", payType);
				relist.add(save);
				jsonMap.put("relist", relist);

				/*------------------------------------------消息推送  余额支付------------------------------------------*/
				String orderType = omm.getOrderType();
				String packageUuid = omm.getPackageUuid();
				CustomerInfoModel user = customerInfoService.getCustomerInfoModelByCustomerUuid(customerUuid);
				String doctorUuid = omm.getDoctorUuid();
				double orderAmount = omm.getTotalMoney();
				String consultDuration = omm.getConsultDuration();
				String bookTime = omm.getBookTime();
				String receiveTime = omm.getReceiveTime();
				String endTime = omm.getEndTime();
				String packName = "";
				String message = "";
				if (!StringUtil.isEmpty(orderType) && "2".equals(orderType)) {
					PackageManagementModel packageManagement = null;
					if (!StringUtil.isEmpty(packageUuid)) {
						packageManagement = packageManagementService.getByUuid(packageUuid);
						packName = packageManagement.getPackageName();
					}
				}
				// 私人套餐
				if (!StringUtil.isEmpty(orderType) && "2".equals(orderType)) {
					message = user.getRealName() + "已成功购买了您的" + packName + "私人医生套餐服务";
				} else {
					message = user.getRealName() + "已成功购买了您的" + consultDuration + "分钟-" + orderAmount
							+ "元 的电话咨询服务，预约通话时间";
					message = message + bookTime + " " + receiveTime + " 至 " + endTime;
				}
				System.out.println("==========message=====" + message);
				// 保存到innerMessage
				innerMessageService.saveInnerMessageAndPush(customerUuid, doctorUuid, message,
						InnerMessageModel.ACCOUNT_TYPE_CUSTOMER, InnerMessageModel.ACCOUNT_TYPE_STORE,
						PushConst.push_client_service, "orderUuid", omm.getUuid(),
						InnerMessageTypeEnum.ORDER.getValue());

			}
		} else {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "订单不存在"), callback);
			return null;
		}
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 创建随访记录的接口并返回随访记录的uuid
	 * 
	 * @param request
	 * @param response
	 * @author xp
	 * @return
	 */
	@RequestMapping(value = "/toVisitRecord", method = RequestMethod.GET)
	public String toVisitRecord(HttpServletRequest request, @RequestParam("customerUuid") String customerUuid,
			@RequestParam("doctorUuid") String doctorUuid, HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut, new String[] { "callback,true" });

		Object callback = map.get("callback");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 判断参数是否为空
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "患者id为空"), callback);
			return null;
		}
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生id为空"), callback);
			return null;
		}

		// 创建随访记录表
		VisitRecordModel vrm = new VisitRecordModel();
		vrm.setCustomerUuid(customerUuid);
		vrm.setServiceStaffUuid(doctorUuid);

		vrm.setCreateTime(DateFormatHelper.getNowTimeStr());
		vrm.setApplyState("0");
		visitRecordService.create(vrm);

		jsonMap.put("visitRecordUuid", vrm.getUuid());

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 获取是否已存在随访表单
	 * 
	 * @param request
	 * @param response
	 * @author xp
	 * @return
	 */
	@RequestMapping(value = "/getVisitRecord", method = RequestMethod.GET)
	public String getVisitRecord(HttpServletRequest request, @RequestParam("customerUuid") String customerUuid,
			@RequestParam("doctorUuid") String doctorUuid, HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut, new String[] { "callback,true" });

		Object callback = map.get("callback");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 判断参数是否为空
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "患者id为空"), callback);
			return null;
		}
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生id为空"), callback);
			return null;
		}

		// 创建随访记录表
		VisitRecordModel vrm = visitRecordService.getVisitRecord(customerUuid, doctorUuid);
		// 申请状态
		String applyState = "0";
		String visitRecordUuid = "";
		if (vrm != null) {
			applyState = vrm.getApplyState();
			visitRecordUuid = vrm.getUuid();
		}
		//
		if (!StringUtil.isEmpty(applyState)) {
			if ("1".equals(applyState)) {
				jsonMap.put("applyState", applyState);
				jsonMap.put("visitRecordUuid", visitRecordUuid);
			} else if ("0".equals(applyState)) {
				jsonMap.put("applyState", applyState);
				jsonMap.put("visitRecordUuid", visitRecordUuid);
			}
		}
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 修改随访可用状态
	 * 
	 * @param request
	 * @param response
	 * @author xp
	 * @return
	 */
	@RequestMapping(value = "/toUpdateVisit", method = RequestMethod.GET)
	public String toUpdateVisit(HttpServletRequest request, @RequestParam("visitRecordUuid") String visitRecordUuid,
			HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut, new String[] { "callback,true" });

		Object callback = request.getParameter("callback");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 判断参数是否为空
		if (StringUtil.isEmpty(visitRecordUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "随访编号不能为空"), callback);
			return null;
		}

		VisitRecordModel vrm = visitRecordService.getByUuid(visitRecordUuid);
		// 修改为可用状态
		String customerUuid = "";
		String doctorUuid = "";
		String customerName = "";
		if (vrm != null) {
			customerUuid = vrm.getCustomerUuid();
			customerName = vrm.getCustomerName();
			doctorUuid = vrm.getServiceStaffUuid();

			vrm.setVisitState("1");
			visitRecordService.update(vrm);
		}

		String content = customerName + MessageHelper.getMessage("toVisitRecord.showmessage.newAdd");
		if (!StringUtil.isEmpty(customerUuid) && !StringUtil.isEmpty(doctorUuid)) {
			// 只针对申请随访的消息推送
			System.out.println("=========content=========" + content);
			innerMessageService.saveInnerMessageAndPushForVisitRecord(customerUuid, doctorUuid, content,
					InnerMessageModel.ACCOUNT_TYPE_CUSTOMER, InnerMessageModel.ACCOUNT_TYPE_STORE,
					PushConst.push_client_service, "visitRecordUuid", vrm.getUuid(),
					InnerMessageTypeEnum.VISITDETAIL.getValue());

			System.out.println("=========content=========" + content);

		}

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 患者端随访记录添加病情变化接口
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addIllnessRecord", method = RequestMethod.POST)
	public String addIllnessRecord(HttpServletRequest request, @RequestParam("previons") String previons,
			@RequestParam("visitRecordUuid") String visitRecordUuid, HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "previons,true", "visitRecordUuid,true" });

		Object callback = map.get("callback");
		// 获取页面传过来的请求参数
		String note = request.getParameter("note");// 其他症状
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 判断参数是否为空
		if (StringUtil.isEmpty(visitRecordUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "随访记录的id为空"), callback);
			return null;
		}

		// 新增病情变化的记录
		IllnessRecordModel irm = new IllnessRecordModel();
		irm.setVisitRecordUuid(visitRecordUuid);
		irm.setPrevions(previons);
		irm.setNote(note);
		irm.setCreateTime(DateFormatHelper.getNowTimeStr());
		illnessRecordService.create(irm);
		/*
		 * VisitRecordModel vrm = visitRecordService.getByUuid(visitRecordUuid);
		 * if (null != vrm) { vrm.setVisitState("1");
		 * visitRecordService.update(vrm); }
		 */

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;
	}

	/**
	 * 患者端随访记录添加药物不良反应的接口
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "addDrugReaction", method = RequestMethod.POST)
	public String addDrugReaction(@RequestParam("visitRecordUuid") String visitRecordUuid, HttpServletRequest request,
			HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "visitRecordUuid,true" });

		Object callback = map.get("callback");
		// 获取页面传过来的请求参数
		String impact = request.getParameter("impact");// 影响
		String frequency = request.getParameter("frequency");// 症状描述
		String dosageTime = request.getParameter("dosageTime");// 持续时间
		String occurrenceTime = request.getParameter("occurrenceTime");// 发生时间
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 判断参数是否为空
		if (StringUtil.isEmpty(visitRecordUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "随访记录的id为空"), callback);
			return null;
		}

		// 新增不良反应的记录
		DrugReactionModel drm = new DrugReactionModel();
		drm.setVisitRecordUuid(visitRecordUuid);
		drm.setImpact(impact);
		drm.setFrequency(frequency);
		drm.setDosageTime(dosageTime);
		drm.setOccurrenceTime(occurrenceTime);
		drugReactionService.create(drm);
		/*
		 * VisitRecordModel vrm = visitRecordService.getByUuid(visitRecordUuid);
		 * if (null != vrm) { vrm.setVisitState("1");
		 * visitRecordService.update(vrm); }
		 */
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;
	}

	/**
	 * 患者端随访的医生 12/21
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getVisitDoctors", method = RequestMethod.GET)
	public String getVisitDoctors(HttpServletRequest request, HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "customerUuid,true" });

		Object callback = map.get("callback");
		//
		String customerUuid = map.get("customerUuid");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 判断参数是否为空
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "患者id为空"), callback);
			return null;
		}
		// 通过查询条件，得到随访List
		List<VisitRecordModel> visitRecordList = visitRecordService.getByCustomerUuid(customerUuid);

		// 返回list
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		if (visitRecordList != null && visitRecordList.size() > 0) {
			for (VisitRecordModel visitRecordModel : visitRecordList) {
				// 医生id
				String doctorUuid = visitRecordModel.getServiceStaffUuid();
				String createTime = visitRecordModel.getCreateTime();
				Map<String, Object> reMap = this.getDoctor(doctorUuid);
				// 医生id 根据医生id调用接口得到医生信息
				if (!StringUtil.isEmpty(doctorUuid)) {
					reMap.put("doctorUuid", doctorUuid);
					reMap.put("createTime", createTime);
				} else {
					reMap.put("doctorUuid", "");
				}
				reList.add(reMap);
			}
		}

		jsonMap.put("reList", reList);

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;
	}

	/**
	 * 患者端添加关注的医生 12/21
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addAttentionDoctors", method = RequestMethod.POST)
	public String addAttentionDoctors(HttpServletRequest request, HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "customerUuid,true", "doctorUuid,true" });

		Object callback = map.get("callback");
		//
		String customerUuid = map.get("customerUuid");
		String doctorUuid = map.get("doctorUuid");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 判断参数是否为空
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "患者id为空"), callback);
			return null;
		}
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生id为空"), callback);
			return null;
		}
		// 患者添加关注医生
		// 通过医生id和患者id查询出患者关注医生model
		ConcernModel concernModel = concernService.getByCustomerAndDoctorUuid(customerUuid, doctorUuid);
		if (concernModel == null) {
			concernModel = new ConcernModel();
			// 患者id
			concernModel.setCustomerUuid(customerUuid);
			// 医生id
			concernModel.setDoctorUuid(doctorUuid);
			// 关注时间
			concernModel.setCreateTime(DateFormatHelper.getNowTimeStr());
			// 关注状态？
			concernModel.setState("1");
			concernService.create(concernModel);
		}

		String customerName = "";
		customerName = customerService.getCustomerNameByCustomerUuid(customerUuid);
		// szr 推送消息 扫医生二维码，患者关注医生成功
		String content = customerName + MessageHelper.getMessage("message.customerAttentionDoctor");
		// 关注我的人专用消息推送接口
		innerMessageService.saveInnerMessageAndPushForConcernDoctor(customerUuid, doctorUuid, content,
				InnerMessageModel.ACCOUNT_TYPE_CUSTOMER, InnerMessageModel.ACCOUNT_TYPE_STORE,
				PushConst.push_client_service, "customerUuid", customerUuid, "");
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;
	}

	/**
	 * 患者端随访记录睡眠情况、进食情况、其他情况 、其他检查结果的接口
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addVisitRecordExtend", method = RequestMethod.POST)
	public String addVisitRecordExtend(HttpServletRequest request,
			@RequestParam("visitRecordUuid") String visitRecordUuid, @RequestParam("type") String type,
			@RequestParam(value = "image", required = false) String image, HttpServletResponse response) {

		System.out.println("interface go into ....");
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "type,true", "visitRecordUuid,true" });

		Object callback = map.get("callback");
		// 获取页面传过来的请求参数
		String result = request.getParameter("result");// 描述
		// String period = request.getParameter("period");// 周期
		// String state = request.getParameter("state");// 状态1代表良好 2一般 3异常 4 其他
		String checkName = request.getParameter("checkName");// 检查名称

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 判断参数是否为空
		if (StringUtil.isEmpty(visitRecordUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "随访记录的id为空"), callback);
			return null;
		}
		Map<String, Object> imagerMap = new HashMap<>();
		if (!StringUtil.isEmpty(image)) {
			// 将String型通过Base64解码并返回byte[],然后将byte[]流转成InputStream流
			InputStream in = new ByteArrayInputStream(MobileUtils.GenerateImage(image));
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
		jsonMap.put("image", imgName);
		jsonMap.put("smallImage", smallImage);

		// 新增随访记录扩展的记录
		VisitRecordExtendModel drm = new VisitRecordExtendModel();
		if (type.equals("1") && !StringUtil.isEmpty(result)) {
			drm.setResult(result);
			drm.setName("睡眠情况");
			// drm.setPeriod(period);
			// drm.setState(state);
			drm.setType("1");
			drm.setVisitRecordUuid(visitRecordUuid);
			VisitRecordExtendService.create(drm);
			VisitRecordModel vrm = visitRecordService.getByUuid(visitRecordUuid);
			/*
			 * if (null != vrm) { vrm.setVisitState("1");
			 * visitRecordService.update(vrm); }
			 */

		} else if (type.equals("2") && !StringUtil.isEmpty(result)) {
			drm.setResult(result);
			// drm.setPeriod(period);
			drm.setName("进食情况 ");
			// drm.setState(state);
			drm.setType("2");
			drm.setVisitRecordUuid(visitRecordUuid);
			VisitRecordExtendService.create(drm);
			VisitRecordModel vrm = visitRecordService.getByUuid(visitRecordUuid);
			/*
			 * if (null != vrm) { vrm.setVisitState("1");
			 * visitRecordService.update(vrm); }
			 */

		} else if (type.equals("3") && !StringUtil.isEmpty(result)) {
			drm.setResult(result);
			// drm.setPeriod(period);
			drm.setName("其他情况 ");
			// drm.setState(state);
			drm.setType("3");
			drm.setVisitRecordUuid(visitRecordUuid);
			VisitRecordExtendService.create(drm);
			VisitRecordModel vrm = visitRecordService.getByUuid(visitRecordUuid);

		} else if (type.equals("4") && !StringUtil.isEmpty(result)) {
			drm.setResult(result);
			// drm.setPeriod(period);
			drm.setName("其他的检查结果");
			// drm.setState(state);
			drm.setType("4");
			drm.setVisitRecordUuid(visitRecordUuid);
			VisitRecordExtendService.create(drm);
			VisitRecordModel vrm = visitRecordService.getByUuid(visitRecordUuid);
			/*
			 * if (null != vrm) { vrm.setVisitState("1");
			 * visitRecordService.update(vrm); }
			 */
		} else if (type.equals("5") && !StringUtils.isEmpty(result)) {
			drm.setResult(result);
			// drm.setPeriod(period);
			drm.setName("体重");
			// drm.setState(state);
			drm.setType("4");
			drm.setVisitRecordUuid(visitRecordUuid);
			VisitRecordExtendService.create(drm);
			VisitRecordModel vrm = visitRecordService.getByUuid(visitRecordUuid);

		}
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;
	}

	/**
	 * 上传图片到moglif
	 * 
	 * @param pathName
	 *            图片路径包括名称
	 * @param name
	 *            图片
	 */
	private void uploadImage(String pathName, String name) {
		File file = new File(pathName);
		InputStream in = null;
		try {
			in = new FileInputStream(file);
			fileUpload.uploadFiles(in, name);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

	/**
	 * 上传检查报告图片
	 * 
	 * @param in
	 *            InputStream图片流
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

	/**
	 * 提交快速提问的接口
	 * 
	 * @param request
	 * @param response
	 * @author xp
	 * @return
	 */
	@RequestMapping(value = "/saveQuickQuestion", method = RequestMethod.POST)
	public String saveQuickQuestion(HttpServletRequest request, @RequestParam("customerUuid") String customerUuid,
			HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "customerUuid,true" });

		Object callback = map.get("callback");

		// 获取页面传过来的请求参数
		String content = request.getParameter("content");// 病情描述
		String realName = request.getParameter("realName");// 患者真实姓名
		String sex = request.getParameter("sex");// 患者性别
		String birthday = request.getParameter("birthday");// 患者出生年月
		String picture = request.getParameter("imageUrl");// 图片地址
		String picture1 = request.getParameter("imageUrl1");// 图片地址
		String picture2 = request.getParameter("imageUrl2");// 图片地址
		String picture3 = request.getParameter("imageUrl3");// 图片地址
		String picture4 = request.getParameter("imageUrl4");// 图片地址

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 判断参数是否为空
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "请登录"), callback);
			return null;
		}
		// 保存患者信息
		CustomerModel cm = customerService.getByUuid(customerUuid);
		if (null != cm) {
			cm.setRealName(realName);
			cm.setBirthday(birthday);
			cm.setSex(sex);
			customerService.update(cm);
		}
		// 保存快速提问信息
		ConsultRecordModel crm = new ConsultRecordModel();
		crm.setCustomerUuid(customerUuid);
		crm.setCreateTime(DateFormatHelper.getNowTimeStr());
		crm.setPicture(picture);
		crm.setPicture1(picture1);
		crm.setPicture2(picture2);
		crm.setPicture3(picture3);
		crm.setPicture4(picture4);
		crm.setContent(content);
		crm.setConsultType(ConsultRecordModel.TYPE_ONLINE);
		crm.setReply("0");
		crm.setState("0");
		consultRecordService.create(crm);

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;
	}

	/**
	 * IM 聊天保存数据
	 * 
	 * @param request
	 * @param response
	 * @author xp
	 * @return
	 */
	@RequestMapping(value = "/saveConsultRecord", method = RequestMethod.POST)
	public String saveConsultRecord(HttpServletRequest request, @RequestParam("customerUuid") String customerUuid,
			@RequestParam("doctorUuid") String doctorUuid, @RequestParam("userType") String userType,
			HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "customerUuid,true" });

		Object callback = map.get("callback");
		// 获取页面传过来的请求参数
		String content = request.getParameter("content");// 病情描述
		String picture = request.getParameter("imageUrl");// 图片地址

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		System.out.println("====userType======" + userType);
		// 判断参数是否为空
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "请登录"), callback);
			return null;
		}

		ConsultRecordModel replyM = consultRecordService.getConsultRecordReply(customerUuid, doctorUuid,
				ConsultRecordModel.TYPE_ONLINE);
		int num = 0;
		// 获取是否存在关系 没有就新增
		CustomerDoctorReleModel cd = customerDoctorReleService.getByCustomerUuidAndDoctorUuid(customerUuid, doctorUuid);
		if (cd == null) {
			cd = new CustomerDoctorReleModel();
			cd.setCreateTime(DateFormatHelper.getNowTimeStr());
			cd.setCustomerUuid(customerUuid);
			cd.setGroupUuid("");
			cd.setDoctorUuid(doctorUuid);
			customerDoctorReleService.create(cd);
		}

		if (!StringUtil.isEmpty(userType) && "1".equals(userType)) {
			// 医生端 推送消息给患者
			innerMessageService.saveInnerMessageAndPush(doctorUuid, customerUuid, content,
					InnerMessageModel.ACCOUNT_TYPE_STORE, InnerMessageModel.ACCOUNT_TYPE_CUSTOMER,
					PushConst.push_client_customer, "customerUuid", customerUuid, "4");
			System.out.println("患者的id=========================" + customerUuid + "===============================");
		} else {
			// 患者端 推送消息医生
			innerMessageService.saveInnerMessageAndPush(customerUuid, doctorUuid, content,
					InnerMessageModel.ACCOUNT_TYPE_CUSTOMER, InnerMessageModel.ACCOUNT_TYPE_STORE,
					PushConst.push_client_service, "doctorUuid", doctorUuid, "4");
			System.out.println("医生的id=========================" + doctorUuid + "===============================");
		}

		// 患者发送IM聊天信息记录 每次新增未回复消息
		//医生发送IM聊天信息记录,也要新增未回复消息
		
		if (!StringUtil.isEmpty(userType) && ("2".equals(userType)  || "1".equals(userType))) {
			ConsultRecordModel crm = new ConsultRecordModel();
			crm.setCustomerUuid(customerUuid);
			crm.setDoctorUuid(doctorUuid);
			crm.setPicture(picture);
			crm.setContent(content);
			crm.setCreateTime(DateFormatHelper.getNowTimeStr());
			//类型  (现在改为1是医生发送的消息,2是患者发送的消息)
			crm.setConsultType(userType);
			crm.setReply("0");
			crm.setState("0");
			crm.setDealState("0");
			crm.setIfread("0");
			crm.setIquestion(0);   //1问题,0消息
			consultRecordService.create(crm);
		}

		// 获取如果以回复 在新增咨询次数
		ConsultRecordReplyModel rcrm = new ConsultRecordReplyModel();
		rcrm.setCreateTime(DateFormatHelper.getNowTimeStr());
		if (replyM != null) {
			rcrm.setConsultRecordUuid(replyM.getUuid());
		} else {
			rcrm.setConsultRecordUuid("");
		}
		rcrm.setContent(content);
		rcrm.setPicture(picture);
		rcrm.setNum(num);
		// 患者信息
		if (!StringUtil.isEmpty(userType) && "2".equals(userType)) {
			rcrm.setCustomerUuid(customerUuid);
		} else {
			// 获取所有未回复的在线咨询
			List<ConsultRecordModel> consultRecords = consultRecordService.getConsultRecords(customerUuid, doctorUuid,
					ConsultRecordModel.TYPE_ONLINE);
			if (consultRecords != null && consultRecords.size() > 0) {
				for (ConsultRecordModel consultRecord : consultRecords) {
					consultRecord.setReply("1");
					consultRecordService.update(consultRecord);
				}
			}
			// 医生发送（回复消息）
			vipclubIntegralLogService.saveVipIntegralLog(doctorUuid, "add", 20, IntegralType.PIC_REPLY.getValue(),
					VipclubIntegralLogModel.VIPCLUB_USERTYPE_DOC, content, "");
			System.out.println("========vipclubIntegralLogService=========");
			rcrm.setDoctorUuid(doctorUuid);
		}
		crrService.create(rcrm);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;
	}

	/**
	 * IM 聊天信息结束
	 * 
	 * @param request
	 * @param response
	 * @author xp
	 * @return
	 */
	@RequestMapping(value = "/overConsultRecord", method = RequestMethod.POST)
	public String overConsultRecord(HttpServletRequest request, @RequestParam("customerUuid") String customerUuid,
			@RequestParam("doctorUuid") String doctorUuid, HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "customerUuid,true" });

		Object callback = map.get("callback");

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 判断参数是否为空
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "请登录"), callback);
			return null;
		}
		ConsultRecordModel replyM = consultRecordService.getConsultRecordReply(customerUuid, doctorUuid,
				ConsultRecordModel.TYPE_ONLINE);

		// 判断是否存在IM聊天信息记录 没有就新增
		if (replyM != null) {
			replyM.setReply("1");
			replyM.setDealState("1");
			replyM.setEndTime(DateFormatHelper.getNowTimeStr());
			consultRecordService.update(replyM);
		}

		// 保存消息到数据库，并推送到手机终端
		// String content = "是否同意结束本次咨询";
		String content = MessageHelper.getMessage("overConsultRecord.showmessage.newAdd");
		innerMessageService.saveInnerMessageAndPush(doctorUuid, customerUuid, content,
				InnerMessageModel.ACCOUNT_TYPE_STORE, InnerMessageModel.ACCOUNT_TYPE_CUSTOMER,
				PushConst.push_client_service, "consultRecord", replyM.getUuid(),
				InnerMessageTypeEnum.SERVICE.getValue());

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 
	 * 获取患者积分列表
	 * 
	 * @author xp
	 */
	@RequestMapping(value = "/getVipclubIntegralLogList", method = RequestMethod.GET)
	public String getVipclubIntegralLogList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("customerUuid") String customerUuid) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "customerUuid,true" }); // 标志
																		// 以及必需要传的参数
		Object callback = map.get("callback");

		// 判断customerUuid是否为空
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "请登录"), callback);
			return null;
		}

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		List relist = new ArrayList();

		// 根据患者的id获取患者的积分列表
		List<VipclubIntegralLogModel> list = vipclubIntegralLogService
				.getVipclubIntegralLogModelListByUuid(customerUuid);
		if (list != null && list.size() > 0) {
			for (VipclubIntegralLogModel mrm : list) {
				if (mrm != null) {
					Map<String, Object> save = new HashMap<String, Object>();
					// 当前积分余额
					save.put("nowIntegral", mrm.getNowIntegral());
					// 每次积分获取的具体的积分数
					save.put("intergralCount", mrm.getIntergralCount());
					// 积分日志的uuid
					save.put("VirtualAccountCustomerLogUuid", mrm.getUuid());
					// 每笔积分的创建时间
					save.put("createTime", mrm.getCreateTime());
					// 积分类型名称
					if (!StringUtil.isEmpty(mrm.getTypeName())) {
						save.put("intergralType", mrm.getTypeName());
					} else {
						save.put("intergralType", mrm.getIntergralType());
					}

					relist.add(save);
				}
			}
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 患者端随访记录添加服药记录接口
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "addMedicationRecord", method = RequestMethod.POST)
	public String addMedicationRecord(@RequestParam("visitRecordUuid") String visitRecordUuid,
			HttpServletRequest request, HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "visitRecordUuid,true" });

		Object callback = map.get("callback");
		/* 获取页面传过来的请求参数 */
		// 药物id
		String medicineUuid = request.getParameter("medicineUuid");
		// 用量(按天)：40，30，20
		String dosage = request.getParameter("dosage");
		// 用量(天)：1，2，3
		String frequency = request.getParameter("frequency");
		// 用法：早，中，晚
		String directions = request.getParameter("directions");
		// 单位 1:粒 2:袋 3：mg 4:ml
		String unit = request.getParameter("unit");

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 判断参数是否为空
		if (StringUtil.isEmpty(visitRecordUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "随访记录的id为空"), callback);
			return null;
		}
		// 获取随访表单信息
		VisitRecordModel visitRecord = visitRecordService.getByUuid(visitRecordUuid);

		if (visitRecord == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "随访记录信息不存在"), callback);
			return null;
		}

		// 新增服药记录
		DoctorAdviceModel dam = new DoctorAdviceModel();
		dam.setMedicineUuid(medicineUuid);
		dam.setDosage(dosage);
		dam.setFrequency(frequency);
		dam.setDirections(directions);
		dam.setCustomerUuid(visitRecord.getCustomerUuid());
		dam.setVisitRecordUuid(visitRecordUuid);
		if (!StringUtil.isEmpty(visitRecord.getServiceStaffUuid())) {
			dam.setServiceStaffUuid(visitRecord.getServiceStaffUuid());
		}
		dam.setCreateTime(DateFormatHelper.getNowTimeStr());
		dam.setUnit(unit);
		dam.setType("0");
		dam.setMedicalDateBegin(DateUtils.parseDate(request.getParameter("medicalDateBegin")));
		dam.setMedicalDateEnd(DateUtils.parseDate(request.getParameter("medicalDateEnd")));

		doctorAdviceService.create(dam);
		/*
		 * VisitRecordModel vrm = visitRecordService.getByUuid(visitRecordUuid);
		 * if (null != vrm) { vrm.setVisitState("1");
		 * visitRecordService.update(vrm); }
		 */
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 跳转到快速提问的接口
	 * 
	 * @param request
	 * @param response
	 * @author xp
	 * @return
	 */
	@RequestMapping(value = "/toQuickQuestion", method = RequestMethod.GET)
	public String toQuickQuestion(HttpServletRequest request, @RequestParam("customerUuid") String customerUuid,
			HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "customerUuid,true" });

		Object callback = map.get("callback");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 判断参数是否为空
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "请登录"), callback);
			return null;
		}

		// 获取患者的信息
		CustomerInfoModel info = customerInfoService.getCustomerInfoModelByCustomerUuid(customerUuid);
		if (info == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "请登录"), callback);
			return null;
		}

		String sex = info.getSex();
		String birthday = info.getBirthday();
		String realName = info.getRealName();

		jsonMap.put("sex", sex);
		jsonMap.put("birthday", birthday);
		jsonMap.put("realName", realName);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;
	}

	/**
	 * 根据患者的id获取患者的收藏的列表
	 * 
	 * @author xp
	 * 
	 */
	@RequestMapping(value = "/getAllCustomerFavoriteList", method = RequestMethod.GET)
	public String getAllCustomerFavoriteList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("customerUuid") String customerUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,false", "customerUuid,true" });
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 获取随访方案的集合列表
		List relist = new ArrayList();
		// 根据患者的uuid和收藏状态获取
		List<FavoriteModel> list = favoriteService.getFavoriteModelListByCustomerUuidAndState(customerUuid,
				FavoriteModel.NOTCANCEL);

		if (list != null && list.size() > 0) {
			for (FavoriteModel fm : list) {
				Map<Object, Object> save = new HashMap<Object, Object>();
				if (fm != null) {
					String contentUuid = fm.getContentUuid();
					String videoUuid = fm.getVideoUuid();
					if (!StringUtil.isEmpty(contentUuid)) {
						// 获取关注人数
						int foucsNum = favoriteService.getNumByContentUuid(contentUuid, "1");
						ContentModel cm = contentService.getByUuid(contentUuid);
						if (cm != null) {
							save.put("contentTitle", cm.getContentTitle());
							save.put("introduction", cm.getContentNote());
							save.put("url", cm.getUrl());
							save.put("imageUrl", cm.getImgUrl());
							save.put("foucsNum", foucsNum);
							save.put("contentUuid", contentUuid);
							save.put("createTime", cm.getCreateTime());
							/* 类型 1：文章 2：视频 */
							save.put("favoriteType", "1");

						}
					}
					if (!StringUtil.isEmpty(videoUuid)) {
						// 获取关注人数
						int foucsNum = favoriteService.getNumByContentUuid(videoUuid, "2");
						PlatFormInfoModel pim = platFormInfoService.getByUuid(videoUuid);
						// int shareType =
						// doctorShareService.getShareTypeByDoctorUuidAndContenUuid(
						// customerUuid, videoUuid);
						// save.put("shareType", shareType);// 0代表未分享 1代表已分享
						if (pim != null) {
							save.put("contentTitle", pim.getVideoModel());
							save.put("introduction", pim.getVideoIntroduction());
							save.put("url", pim.getVideoAddress());
							save.put("imageUrl", pim.getImageUrl());
							save.put("foucsNum", foucsNum);
							save.put("contentUuid", videoUuid);
							save.put("createTime", pim.getCreateTime());
							/* 类型 1：文章 2：视频 */
							save.put("favoriteType", "2");
						}
					}
					save.put("favoriteUuid", fm.getUuid());
					relist.add(save);
				}
			}
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 添加意见反馈信息
	 * 
	 * @author xp
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addCustomerAdvice", method = RequestMethod.POST)
	public String addCustomerAdvice(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("customerUuid") String customerUuid) {
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
		// 判断参数是否为空
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "请登录"), callback);
			return null;
		}
		// 获取意见内容
		String adviceContent = request.getParameter("adviceContent");
		String customerMobile = request.getParameter("customerMobile");
		String customerEmail = request.getParameter("customerEmail");
		String customerQQ = request.getParameter("customerQQ");

		/************************ 添加意见建议信息 *************************/
		CustomerAdviceModel fm = new CustomerAdviceModel();
		fm.setCustomerUuid(customerUuid);
		fm.setCustomerEmail(customerEmail);
		fm.setCustomerMobile(customerMobile);
		fm.setAdviceContent(adviceContent);
		fm.setCreateTime(DateFormatHelper.getNowTimeStr());
		fm.setType("2");// 2代表是患者
		fm.setStatus("0");// 0代表是未处理
		customerAdviceService.create(fm);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 跳转到消息页面的接口
	 * 
	 * @param request
	 * @param response
	 * @author xp
	 * @return
	 */
	@RequestMapping(value = "/toInnerMessage", method = RequestMethod.GET)
	public String toInnerMessage(HttpServletRequest request, @RequestParam("customerUuid") String customerUuid,
			HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "customerUuid,true" });

		Object callback = map.get("callback");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 判断参数是否为空
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "请登录"), callback);
			return null;
		}

		// 获取系统和随访消息的数量
		int systemMsgNum = innerMessageService.getMessageCenterCount(InnerMessageModel.MESSAGE_TYPE_SYSTEM,
				customerUuid);
		int visitpreceptMsgNum = innerMessageService.getMessageCenterCount(InnerMessageModel.MESSAGE_TYPE_ORDER,
				customerUuid);

		jsonMap.put("systemMSG", "系统消息");
		jsonMap.put("systemMsgNum", systemMsgNum);
		jsonMap.put("visitpreceptMsg", "随访消息");
		jsonMap.put("visitpreceptMsgNum", visitpreceptMsgNum);

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;
	}

	/**
	 * 根据患者的id和消息类型获取消息
	 * 
	 * @author xp
	 */
	@RequestMapping(value = "/getAllInnerMessageList", method = RequestMethod.GET)
	public String getAllInnerMessageList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("messageType") String messageType, @RequestParam("customerUuid") String customerUuid) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		// 标志以及必需要传的参数
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "customerUuid,true" });
		Object callback = map.get("callback");

		// 判断messageType消息类型不能为空0代表是系统消息 1代表是随访消息
		if (StringUtil.isEmpty(messageType)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "消息类型不能为空"), callback);
			return null;
		}
		// 判断customerUuid是否为空
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "请登录"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		List relist = new ArrayList();

		// 根据会员的id获取系统信息id列表messageType 2是系统消息 1是随访消息
		if (messageType.equals(InnerMessageModel.MESSAGE_TYPE_SYSTEM)) {
			List<InnerMessageModel> list = innerMessageService.getCollectMeListByReceiveUser(customerUuid,
					InnerMessageModel.MESSAGE_TYPE_SYSTEM);
			if (list != null && list.size() > 0) {
				for (InnerMessageModel imm : list) {
					if (null != imm && imm.getReadStatus().equals(InnerMessageModel.READ_STATUS_UNREAD)) {
						Map<String, Object> save = new HashMap<String, Object>();
						save.put("title", imm.getTitle());
						save.put("content", imm.getContent());
						save.put("sendUser", imm.getSendUser());
						save.put("sendTime", imm.getSendTime());
						save.put("InnerMessageUuid", imm.getUuid());
						imm.setReadStatus(InnerMessageModel.READ_STATUS_READ);
						innerMessageService.update(imm);
						relist.add(save);
					}
				}
			}
		} else if (messageType.equals(InnerMessageModel.MESSAGE_TYPE_ORDER)) {
			List<InnerMessageModel> list = innerMessageService.getCollectMeListByReceiveUser(customerUuid,
					InnerMessageModel.MESSAGE_TYPE_ORDER);
			if (list != null && list.size() > 0) {
				for (InnerMessageModel imm : list) {
					if (null != imm && imm.getReadStatus().equals(InnerMessageModel.READ_STATUS_UNREAD)) {
						Map<String, Object> save = new HashMap<String, Object>();
						save.put("title", imm.getTitle());
						save.put("content", imm.getContent());
						save.put("sendUser", imm.getSendUser());
						save.put("sendTime", imm.getSendTime());
						save.put("InnerMessageUuid", imm.getUuid());
						imm.setReadStatus(InnerMessageModel.READ_STATUS_READ);
						innerMessageService.update(imm);
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
	 * 跳转到个人详情页面
	 * 
	 * @param request
	 * @param response
	 * @author xp
	 * @return
	 */
	@RequestMapping(value = "/toCustomerInfo", method = RequestMethod.GET)
	public String toCustomerInfo(HttpServletRequest request, @RequestParam("customerUuid") String customerUuid,
			HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut, new String[] { "callback,true" });

		Object callback = map.get("callback");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 判断参数是否为空
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "请登录"), callback);
			return null;
		}

		// 获取患者的信息
		CustomerInfoModel cim = customerInfoService.getCustomerInfoModelByCustomerUuid(customerUuid);
		CustomerModel cm = customerService.getByUuid(customerUuid);
		if (null != cim && null != cm) {
			jsonMap.put("imgUrl", cim.getImgUrl());// 头像
			jsonMap.put("nickName", cm.getCustomerName());// 昵称
			jsonMap.put("realName", cim.getRealName());
			jsonMap.put("sex", cim.getSex());
			jsonMap.put("birthday", cim.getBirthday());
			jsonMap.put("customerUuid", customerUuid);
			jsonMap.put("accountAmount", cm.getAccountAmount());// 账户剩余金额
			jsonMap.put("availableIntegral", cm.getAvailableIntegral());
			jsonMap.put("illnessDescription", cim.getIllnessDescription());

			jsonMap.put("certCode", cim.getCertCode());
			jsonMap.put("marryState", cim.getMarryState());
			jsonMap.put("industry", cim.getIndustry());
			jsonMap.put("zipCode", cim.getZipCode());
			jsonMap.put("address", cim.getAddress());

			jsonMap.put("diseaseTime", cim.getDiseaseTime());
			jsonMap.put("firstDiagnosis", cim.getFirstDiagnosis());
			jsonMap.put("ifStart", cim.getIfStart());
			jsonMap.put("seizureTimes", cim.getSeizureTimes());
			jsonMap.put("height", cim.getHeight());
			jsonMap.put("weight", cim.getWeight());
			jsonMap.put("nearlyDrugs", cim.getNearlyDrugs());
		}
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;
	}

	/**
	 * 保存个人信息
	 * 
	 * @param request
	 * @param response
	 * @author xp
	 * @return
	 */
	@RequestMapping(value = { "/editCustomerInfo", "/1.0/editCustomerInfo" }, method = RequestMethod.POST)
	public String editCustomerInfo(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("customerUuid") String customerUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,false", "customerUuid,true" });
		// 客户上传的参数
		String image = request.getParameter("image");
		String customerName = request.getParameter("customerName");
		String realName = request.getParameter("realName");
		String sex = request.getParameter("sex");
		String birthday = request.getParameter("birthday");
		String illnessDescription = request.getParameter("illnessDescription");
		String diseaseTime = request.getParameter("diseaseTime");
		Date firstDiagnosis = DateUtils.parseDate(request.getParameter("firstDiagnosis"));
		String ifStart = request.getParameter("ifStart");
		Integer seizureTimes = MathUtils.toInt(request.getParameter("seizureTimes"), 1);
		Double height = MathUtils.toDouble(request.getParameter("height"), 0.0);
		Double weight = MathUtils.toDouble(request.getParameter("weight"), 0.0);
		String nearlyDrugs = request.getParameter("nearlyDrugs");
		Object callback = map.get("callback");
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
			customerService.update(cm);
			if (!StringUtil.isEmpty(image)) {
				cim.setImage(image);
			}
			cim.setSex(sex);
			cim.setRealName(realName);
			cim.setBirthday(birthday);
			cim.setIllnessDescription(illnessDescription);
			cim.setDiseaseTime(diseaseTime);
			cim.setIllnessDescription(illnessDescription);
			cim.setFirstDiagnosis(firstDiagnosis);
			cim.setIfStart(ifStart);
			cim.setSeizureTimes(seizureTimes);
			cim.setHeight(height < 1 ? null : height.floatValue());
			cim.setWeight(weight < 1 ? null : weight.floatValue());
			cim.setNearlyDrugs(nearlyDrugs);
			customerInfoService.update(cim);
		}
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;
	}

	/**
	 * 跳转到个人账户页面列表页面
	 * 
	 * @param request
	 * @param response
	 * @author xp
	 * @return
	 */
	@RequestMapping(value = "/toPersonAccountList", method = RequestMethod.GET)
	public String toPersonAccountList(HttpServletRequest request, @RequestParam("customerUuid") String customerUuid,
			HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "customerUuid,true" });

		Object callback = map.get("callback");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 判断参数是否为空
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "请登录"), callback);
			return null;
		}
		// 获取患者的账户余额
		List<Map<String, Object>> relist = new ArrayList<>();
		List<VirtualAccountCustomerLogModel> list = virtualAccountCustomerLogService
				.getVirtualAccountCustomerLogListByCustomerUuid(customerUuid);
		if (list != null && list.size() > 0) {
			for (VirtualAccountCustomerLogModel vam : list) {
				Map<String, Object> save = new HashMap<>();
				if (null != vam) {
					// 当前余额
					save.put("nowBalance", vam.getNowBalance());
					// 操作类型 0代表收入 1代表支出
					save.put("operType", vam.getOperType());
					// 操作金额
					save.put("operAmount", vam.getOperAmount());
					// 操作时间
					save.put("orderTime", vam.getOpeTime());
					// 消费类型 患者消费类型 1 电话咨询 ;2私人医生 3:充值
					save.put("orderType", vam.getOrderType());
					// 消费记录的ID
					save.put("VirtualAccountCustomerLogUuid", vam.getUuid());
					relist.add(save);
				}
			}
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 跳转到个人消费的详情页面
	 * 
	 * @param request
	 * @param response
	 * @author xp
	 * @return
	 */
	@RequestMapping(value = "/toPersonAccountDetail", method = RequestMethod.GET)
	public String toPersonAccountDetail(HttpServletRequest request,
			@RequestParam("VirtualAccountCustomerLogUuid") String VirtualAccountCustomerLogUuid,
			HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		Map<String, String> map = getParam(request, response, false,
				new String[] { "callback,false", "VirtualAccountCustomerLogUuid,true" });

		Object callback = map.get("callback");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 判断参数是否为空
		if (StringUtil.isEmpty(VirtualAccountCustomerLogUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "该消费记录不存在"), callback);
			return null;
		}
		// 获取患者的账户余额
		List<Map<String, Object>> relist = new ArrayList<>();
		VirtualAccountCustomerLogModel vam = virtualAccountCustomerLogService.getByUuid(VirtualAccountCustomerLogUuid);
		if (null != vam) {
			Map<String, Object> save = new HashMap<>();
			// 操作类型 0代表收入 1代表支出
			save.put("operType", vam.getOperType());
			// 操作金额
			save.put("operAmount", vam.getOperAmount());
			// 操作时间
			save.put("orderTime", vam.getOpeTime());
			// 消费类型 患者消费类型 1 电话咨询 ;2私人医生
			save.put("orderType", vam.getOrderType());
			// 支付方式1：微信支付 2：支付支付 3：虚拟账户
			save.put("payType", vam.getPayType());
			relist.add(save);
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	// TODO:医生详情
	/**
	 * 获取医生详情页接口
	 */
	@RequestMapping(value = "getDoctorDetail", method = RequestMethod.GET)
	public String getDoctorDetail(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("doctorUuid") String doctorUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Map<String, String> map = getParam(request, response, false,
				new String[] { "callback,false", "doctorUuid,true" }); // 标志
		// 获取患者id
		String customerUuid = request.getParameter("customerUuid"); // 以及必需要传的参数

		Object callback = map.get("callback");

		// 判断用户是否登陆
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "该医生不存在"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 获取该医生的model和infomedol
		ServiceStaff sf = null;
		ServiceStaffInfo si  = null;

		
		try {
			sf = serviceStaffService.getServiceStaffByUuid(doctorUuid);
			si =  serviceStaffInfoService.selectByServiceStaffUuid(doctorUuid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
/*		ServicestaffModel sfm = servicestaffService.getByUuid(doctorUuid);
		ServicestaffinfoModel sim = servicestaffInfoService.getServicestaffinfoModelByServicestaffUuid(doctorUuid);*/
		// 获取医生公告
		DoctorNoticeModel dnm = doctorNoticeService.getDoctorNoticeModelByDoctorUuid(doctorUuid);
		// 获该患者是否收藏该医生 0代表没有关注 ，1代表已经关注
		int concernType = 0;
		if (!StringUtil.isEmpty(doctorUuid) && !StringUtil.isEmpty(customerUuid)) {
			int num = concernService.getConcernType(doctorUuid, customerUuid);
			if (num != 0) {
				concernType = 1;
			}
		}
		// 获取关注该医生的粉丝数
		int concernNum = concernService.getConcernNumByDoctorId(doctorUuid);
		// 获取该医生的咨询总量(图文资讯的加电话咨询的数量的和)
		int consultNum = consultRecordService.getConsultNumBydoctorId(doctorUuid);
		int totalTelNum = orderMainService.getTotalTelNumByDoctorId(doctorUuid);
		int totalNum = consultNum + totalTelNum;

		// 获取医生的接诊量
		int allOrderNum = consultRecordService.getAllOrderNumByDoctorId(doctorUuid);

		int visitRecordNum = visitRecordService.getVisitRecordNumByDoctorId(doctorUuid);

		int receTotalNum = consultNum + allOrderNum + totalTelNum + visitRecordNum;

		// 定义返回List
		List<Map<String, Object>> relist = new ArrayList<>();
		Map<String, Object> save = new HashMap<>();
		if (si != null) {
			// 医生的擅长
			save.put("territory", si.getTerritory());
			// 医生的职称
			save.put("professional", si.getProfessional());
			// 医生的个人介绍
			save.put("synopsis", si.getSynopsis());
			// 医生的头像地址
			Image4App[] url = CompatibleTools.getImages(imgUploadService, fileService, (String)si.getImage());
			String imgUrl = "";
			if(url != null && url.length>0){
				imgUrl = url[0].getLarge();
			}
			save.put("imgUrl", imgUrl);
		}
		if (sf != null) {
			// 医生姓名
			save.put("realName", sf.getRealName());
			// 医生所在医院
			save.put("hospitalName", sf.getHospitalName());
			// 医生所在科室
			save.put("departmentName", sf.getDepartmentName());
			// 医生的认证状态
			save.put("certificationTyp", sf.getSate());// 0:未认证
			// 1：认证通过
			// 2：认证不通过
		}
		if (dnm != null) {
			// 医生公告内容
			save.put("content", dnm.getContent());
		}

		DoctorRightModel doctorRightModel = doctorRightService.getByDoctorUuid(doctorUuid);
		if (doctorRightModel != null) {
			save.put("telState", doctorRightModel.getPhone());
			save.put("personal", doctorRightModel.getPersonal());
			save.put("plus", doctorRightModel.getPlus());
			save.put("teletext", doctorRightModel.getTeletext());
		} else {
			save.put("telState", "0");
			save.put("personal", "0");
			save.put("plus", "0");
			save.put("teletext", "0");
		}

		// 获取关注状态 1代表已关注，0代表未关注
		save.put("concernType", concernType);
		// 获取关注该医生的粉丝数
		save.put("concernNum", concernNum);
		// 获取咨询量的数量
		save.put("totalNum", totalNum);
		// 获取接诊的数量
		save.put("receTotalNum", receTotalNum);

		relist.add(save);
		jsonMap.put("relist", relist);// 消息字段

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	// TODO:获取今日推荐
	/**
	 * 获取今日推荐接口
	 */
	@RequestMapping(value = "/getTodayContent", method = RequestMethod.GET)
	public String getTodayContent(HttpServletRequest request, HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		Map<String, String> map = getParam(request, response, false, new String[] { "callback,false" });
		Object callback = map.get("callback");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 获取今日推荐第一条并将今日推荐返回给患者
		List<ContentModel> list = contentService.getAllContentModelList();
		// 定义返回List
		List<Map<String, Object>> relist = new ArrayList<>();
		if (list != null && list.size() > 0) {
			ContentModel cm = list.get(0);
			if (cm != null) {
				Map<String, Object> save = new HashMap<>();
				// 今日推荐的Uuid
				save.put("contentUuid", cm.getUuid());
				save.put("contentType", "0");
				// 今日推荐的标题
				save.put("contentTitle", cm.getContentTitle());
				// 描述
				save.put("contentNote", cm.getContentNote());
				// 今日推荐图片
				save.put("imageUrl", cm.getImgUrl());
				// 今日推荐的超级链接
				save.put("url", cm.getUrl());
				relist.add(save);
			}
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 根据患者id和医生id获得随访记录 2016/1/13
	 * 
	 * @author szr
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getVisitRecordByCusAndDoc", method = RequestMethod.GET)
	public String getVisitRecordByCusAndDoc(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("doctorUuid") String doctorUuid, @RequestParam("customerUuid") String customerUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "doctorUuid,true", "customerUuid,true" });
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 判断传入参数
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生编号不能为空"), callback);
			return null;
		}
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "患者编号不能为空"), callback);
			return null;
		}
		// 返回List
		List<Object> relist = new ArrayList<>();
		// 通过查询条件，得到随访List
		List<VisitRecordModel> visitRecordList = visitRecordService.getVisitRecordByCusAndDoc(customerUuid, doctorUuid);
		// 获取随访的集合列表
		if (visitRecordList != null && visitRecordList.size() > 0) {
			for (VisitRecordModel vp : visitRecordList) {
				Map<Object, Object> reMap = new HashMap<Object, Object>();
				if (vp != null) {
					// 创建时间
					reMap.put("createTime", vp.getCreateTime());
					// 随访记录主键
					reMap.put("visitRecordUuid", vp.getUuid());
				}
				relist.add(reMap);
			}
		}
		// 根据医生和患者的id获取该患者的所有的病例
		List<MedicalRecordModel> list = medicalRecordService
				.getMedicalRecordListByCustomerUuidAndDoctorUuid(customerUuid, doctorUuid);
		if (list != null && list.size() > 0) {
			for (MedicalRecordModel mrm : list) {
				if (mrm != null) {
					Map<String, Object> save = new HashMap<String, Object>();
					// 病例时间
					save.put("medicalRecordUuid", mrm.getUuid());
					// 病例类型 0住院病例 1门诊病例
					save.put("caseCategoryType", mrm.getCaseCategoryType());
					// 创建时间
					save.put("createTime", mrm.getCreateTime());
					relist.add(save);
				}
			}
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 
	 * @Description: (添加服药提醒的接口)
	 * @author XP
	 * @param request
	 * @param response
	 * @return
	 * @date 2016-1-13 下午2:15:06
	 */
	@RequestMapping(value = "/addDrugNotice", method = RequestMethod.POST)
	public String addDrugNotice(HttpServletRequest request, @RequestParam("customerUuid") String customerUuid,
			HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "customerUuid,true" });

		Object callback = map.get("callback");
		// 获取页面传过来的请求参数
		String medicineName = request.getParameter("medicineName");// 药品名称
		String dosage = request.getParameter("dosage");// 服药计量
		String frequency = request.getParameter("frequency");// 服药次数
		String directions = request.getParameter("directions");// 用药指导
		String noticeTime = request.getParameter("noticeTime");// 提醒时间
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 判断参数是否为空
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "请登录"), callback);
			return null;
		}

		// 新增服药提醒
		DrugNoticeModel drm = new DrugNoticeModel();
		drm.setCustomerUuid(customerUuid);
		drm.setMedicineName(medicineName);
		drm.setDosage(dosage);
		drm.setFrequency(frequency);
		drm.setDirections(directions);
		drm.setNoticeTime(noticeTime);
		drm.setCreateTime(DateFormatHelper.getNowTimeStr());
		drugNoticeService.create(drm);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;
	}

	/**
	 * 
	 * @Description: (获取服药提醒的列表的接口)
	 * @author XP
	 * @param request
	 * @param response
	 * @param customerUuid
	 * @return
	 * @date 2016-1-13 下午2:56:50
	 */
	@RequestMapping(value = "/getDrugNoticeList", method = RequestMethod.GET)
	public String getDrugNoticeList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("customerUuid") String customerUuid) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "customerUuid,true" }); // 标志
																		// 以及必需要传的参数
		Object callback = map.get("callback");
		// 判断customerUuid是否为空
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "请登录"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		List relist = new ArrayList();
		// 返回的是服药提醒的list
		List<DrugNoticeModel> drugNoticeList = drugNoticeService.getDrugNoticeListByCustomerUuid(customerUuid);
		if (drugNoticeList != null && drugNoticeList.size() > 0) {
			for (DrugNoticeModel dnm : drugNoticeList) {
				Map<String, Object> save = new HashMap<String, Object>();
				if (dnm != null) {
					save.put("medicineName", dnm.getMedicineName());
					save.put("dosage", dnm.getDosage());
					save.put("frequency", dnm.getFrequency());
					save.put("directions", dnm.getDirections());
					String noticeTimes = dnm.getNoticeTime();
					if (!StringUtil.isEmpty(noticeTimes)) {
						String[] noticeTimesList = StringUtils.split(noticeTimes, ",");
						save.put("noticeTime", noticeTimesList);
					}
					relist.add(save);
				}
			}
			// 返回的是服药提醒的最新的一个

			/*
			 * List newlist = new ArrayList(); DrugNoticeModel drugNoticeModel =
			 * drugNoticeList.get(0); if(null != drugNoticeModel) { Map<String,
			 * Object> newSave = new HashMap<String, Object>();
			 * newSave.put("medicineName", drugNoticeModel.getMedicineName());
			 * newSave.put("dosage", drugNoticeModel.getDosage());
			 * newSave.put("frequency", drugNoticeModel.getFrequency());
			 * newSave.put("directions", drugNoticeModel.getDirections());
			 * String noticeTimes = dnm.getNoticeTime();
			 * if(!StringUtil.isEmpty(noticeTimes)){ String[] noticeTimesList =
			 * StringUtils.split(noticeTimes, ","); save.put("noticeTime",
			 * noticeTimesList); } newlist.add(newSave); }
			 */

		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 
	 * @Description: (患者端根据医生的id，获取医生开通的私人医生)
	 * @author XP
	 * @param request
	 * @param response
	 * @return
	 * @date 2016-1-18 下午12:58:55
	 */
	@RequestMapping(value = "/getPackageList", method = RequestMethod.GET)
	public String getPackageList(HttpServletRequest request, HttpServletResponse response,
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
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "没有该医生"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 根据医生的id查询医生开通的所有的私人套餐的uuids
		List<String> uuids = packageDoctorService.getPackageUuidsByDoctorUuid(doctorUuid);
		// 获取所有的私人套餐
		List<PackageManagementModel> list = packageManagementService.getPackageListByDoctorUuid(uuids);
		// 定义返回List
		List relist = new ArrayList();
		if (list != null && list.size() > 0) {
			for (PackageManagementModel cm : list) {
				if (cm != null) {
					Map<String, Object> save = new HashMap<String, Object>();
					// 获得套餐的Uuid
					save.put("packageUuid", cm.getUuid());
					// 套餐名称
					save.put("packageName", cm.getPackageName());
					// 套餐钱数
					save.put("money", cm.getMoney());
					// 电话咨询次数
					save.put("phoneTimes", cm.getPhoneTimes());
					// 支持加号次数
					save.put("plusTimes", cm.getPlusTimes());
					// 图文资讯次数
					save.put("netTimes", 1);// 图文咨询次数的1代表是无限次
					relist.add(save);
				}
			}
		}
		jsonMap.put("relist", relist);// 消息字段

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 患者端分享接口 2016/01/19
	 * 
	 * @author szr
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addShare", method = RequestMethod.GET)
	public String addShare(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("customerUuid") String customerUuid, @RequestParam("shareUuid") String shareUuid,
			@RequestParam("shareType") String shareType) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "customerUuid,true", "shareUuid,true", "shareType,true", "callback,true" });
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");

		/************************ 患者分享 *************************/

		DoctorShareModel dsm = new DoctorShareModel();
		// 分享类型
		String integralType = "";
		if (!StringUtil.isEmpty(customerUuid) && !StringUtil.isEmpty(shareUuid) && !StringUtil.isEmpty(shareType)) {
			dsm.setUserType(DoctorShareModel.SHARE_USERTYPE_CUS);
			dsm.setUserUuid(customerUuid);
			// shareType 分享类型 1：文章 2：视频
			if ("1".equals(shareType)) {
				integralType = IntegralType.ADD_SHARE_BY_ARTICLE.getValue();
				dsm.setContentUuid(shareUuid);
			} else if ("2".equals(shareType)) {
				integralType = IntegralType.ADD_SHARE_BY_VIDEO.getValue();
				dsm.setVideoUuid(shareUuid);
			}
			doctorShareService.create(dsm);
		}

		// 增加积分
		// 判断该文章或视频是否被分享过
		VipclubIntegralLogModel vig = vipclubIntegralLogService.getVipclubIntegralLogByConditions(customerUuid,
				VipclubIntegralLogModel.VIPCLUB_USERTYPE_CUS, integralType, shareUuid);
		if (null == vig) {
			// 没被分享过，添加积分
			vipclubIntegralLogService.saveVipIntegralLog(customerUuid, "add", 1, integralType,
					VipclubIntegralLogModel.VIPCLUB_USERTYPE_CUS, "首次分享" + shareType == "1" ? "文章 " : "视频" + "获得积分",
					shareUuid);
		}

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	public static void main(String[] args) {
		String seeDoctorTime = "2016-2-23";
		String weekDay = DateUtil.getWeekDay(seeDoctorTime);
		System.out.println(weekDay);

	}
}
