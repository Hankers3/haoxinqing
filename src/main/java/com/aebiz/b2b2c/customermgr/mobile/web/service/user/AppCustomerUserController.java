package com.aebiz.b2b2c.customermgr.mobile.web.service.user;

import java.text.DateFormat;
import java.text.ParseException;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.innermessage.service.InnerMessageService;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageModel;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageTypeEnum;
import com.aebiz.b2b2c.customermgr.consultrecord.service.ConsultRecordService;
import com.aebiz.b2b2c.customermgr.consultrecord.vo.ConsultRecordModel;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.customermgr.customerinfo.service.CustomerInfoService;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoModel;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.AppBaseController;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.HttpServletUtils;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.SysConst;
import com.aebiz.b2b2c.customermgr.mobile.web.push.PushConst;
import com.aebiz.b2b2c.order.ordermain.service.OrderMainService;
import com.aebiz.b2b2c.servicestaff.packagemanagement.service.PackageManagementService;
import com.aebiz.b2b2c.servicestaff.packagemanagement.vo.PackageManagementModel;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.service.VipclubIntegralLogService;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.IntegralType;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.VipclubIntegralLogModel;
import com.aebiz.b2b2c.visitprecept.casegroup.service.CaseGroupService;
import com.aebiz.b2b2c.visitprecept.casegroup.vo.CaseGroupModel;
import com.aebiz.b2b2c.visitprecept.customerdoctorrele.service.CustomerDoctorReleService;
import com.aebiz.b2b2c.visitprecept.customerdoctorrele.vo.CustomerDoctorReleModel;
import com.aebiz.b2b2c.visitprecept.doctoradvice.service.DoctorAdviceService;
import com.aebiz.b2b2c.visitprecept.doctoradvice.vo.DoctorAdviceModel;
import com.aebiz.b2b2c.visitprecept.illnessrecord.service.IllnessRecordService;
import com.aebiz.b2b2c.visitprecept.illnessrecord.vo.IllnessRecordModel;
import com.aebiz.b2b2c.visitprecept.medicalrecord.service.MedicalRecordService;
import com.aebiz.b2b2c.visitprecept.medicalrecord.vo.MedicalRecordModel;
import com.aebiz.b2b2c.visitprecept.visitcustomercommon.service.VisitCustomerCommonService;
import com.aebiz.b2b2c.visitprecept.visitcustomercommon.vo.VisitCustomerCommonModel;
import com.aebiz.b2b2c.visitprecept.visitrecord.service.VisitRecordService;
import com.aebiz.b2b2c.visitprecept.visitrecord.vo.VisitRecordModel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hxq.mobile.doctor.visit.service.VisitPreceptService;
import com.hxq.mobile.entity.visit.VisitApply;
import com.hxq.mobile.patient.visit.service.VisitApplyService;
import com.wxcommon.util.IdentityHelper;
import com.wxcommon.util.ObjectUtils;

/**
 * 医生端获取患者信息服务接口控制类
 *
 * @author szr
 *
 */
@Controller
@RequestMapping("/app/service/customer")
public class AppCustomerUserController extends AppBaseController {
	/**
	 * 构造方法
	 */
	public AppCustomerUserController() {
		super("", "", AppCustomerUserController.class);
	}

	/*
	 * 患者service
	 */
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CustomerInfoService customerInfoService;
	@Autowired
	private IllnessRecordService illnessRecordService;
	/*
	 * 患者医生关系service
	 */
	@Autowired
	private CustomerDoctorReleService customerDoctorReleService;

	@Autowired
	private DoctorAdviceService doctorAdviceService;
	/*
	 * 患者分组service
	 */
	@Autowired
	private CaseGroupService caseGroupService;

	/*
	 * 患者病历service
	 */
	@Autowired
	private MedicalRecordService medicalRecordService;

	/* 医生常用 */
	@Autowired
	private VisitCustomerCommonService visitCustomerCommonService;

	/*
	 * 医生service
	 */
	@Autowired
	private ServicestaffService servicestaffService;
	/*
	 * 注入私人套餐的Service
	 */
	@Autowired
	private PackageManagementService packageManagementService;
	
	@Autowired
	@Resource(name = "com.hxq.mobile.doctor.visit.service.VisitPreceptService")
	private VisitPreceptService visitPrecptSerivce;

	/*
	 * 咨询的service
	 */
	@Autowired
	private ConsultRecordService consultRecordService;

	/*
	 * 订单的service
	 */
	@Autowired
	private OrderMainService orderMainService;

	/*
	 * 随访的的service
	 */
	@Autowired
	private VisitRecordService visitRecordService;

	@Autowired
	private InnerMessageService innerMessageService;
	@Autowired
	private VipclubIntegralLogService vipclubIntegralLogService;

	@Resource(name = "com.hxq.mobile.patient.visit.service.VisitApplyService")
	private VisitApplyService service;

	// TODO: 查询分组患者 根据医生id及分組id获取患者列表 clear（ver1.0）
	/**
	 * 根据医生id及分組id获取患者列表
	 */
	@RequestMapping(value = "/1.0/getCustomerListByDoctorUuidAndGroupId", method = RequestMethod.GET)
	public String getCustomerListByDoctorUuidAndGroupId(HttpServletRequest request, HttpServletResponse response,
														@RequestParam("doctorUuid") String doctorUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 判断医生id不能为空
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, "医生id不能为空"));
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 得到分组id
		String groupId = request.getParameter("groupId");
		// 根据医生id及分组id获得关系列表
		List<CustomerDoctorReleModel> customerDoctorReleList = new ArrayList<>();
		if (!StringUtil.isEmpty(groupId)) {
			customerDoctorReleList = customerDoctorReleService.getByDoctorUuidAndGroupId(doctorUuid, groupId);
		} else {

			//随访的默认分组  查询所有随访患者 然后把随访患者中 没有分组信息的 展示为默认分组
			List<String> uuids = visitRecordService.getCustomerUuidByDoctorUuid(doctorUuid);
			System.out.println("==========uuids======" + JSON.toJSONString(uuids));
			if (uuids != null && uuids.size() > 0) {
				for (String uuid : uuids) {
					CustomerDoctorReleModel cdr = customerDoctorReleService.getByCustomerUuidAndDoctorUuid(uuid, doctorUuid);
					if (cdr != null) {
						System.out.println("==========cdr======" + JSON.toJSONString(cdr));
						String grUuid = cdr.getGroupUuid();
						if (StringUtil.isEmpty(grUuid)||"0".equals(grUuid)) {
							customerDoctorReleList.add(cdr);
						}
					}
				}
			}
		}
		// 定义返回List
		List<Map<String, Object>> relist = new ArrayList<>();
		if (customerDoctorReleList != null && customerDoctorReleList.size() > 0) {
			for (CustomerDoctorReleModel cdrm : customerDoctorReleList) {
				Map<String, Object> save = new HashMap<>();
				String customerUuid = cdrm.getCustomerUuid();
				CustomerInfoModel customerInfoModel = customerInfoService.getCustomerInfoModelByCustomerUuid(customerUuid);
				if (customerInfoModel == null) {
					customerInfoModel = new CustomerInfoModel();
				}
				// 患者id
				save.put("customerUuid", customerUuid);
				// 患者姓名
				save.put("customerName", customerInfoModel.getRealName());
				// 患者性别
				save.put("sex", customerInfoModel.getSex());
				// 患者年龄
				save.put("age", customerInfoModel.getAge());
				// 患者时间
				save.put("time", cdrm.getCreateTime());
				// 患者头像
				save.put("customerImg", customerInfoModel.getImgUrl());
				// 患者信息？
				save.put("customerMessage", customerInfoModel.getIllnessDescription());
				relist.add(save);
			}
		}

		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJson(response, jsonMap);
		return null;
	}

	@Resource(name = "com.hxq.mobile.patient.visit.service.VisitApplyService")
	private VisitApplyService visitApplyService;

	//TODO:根据医生id获取患者分组与分组下的患者
	@RequestMapping(value = "/1.0/getGroupAndCustomer", method = RequestMethod.GET)
	public String getGroupAndCustomer(HttpServletRequest request, HttpServletResponse response, @RequestParam("doctorUuid") String doctorUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		List<Map<String, Object>> resultlist = new ArrayList<>();
		List<CustomerDoctorReleModel> customerDoctorReleList = new ArrayList<>();

		// 得到casegroupList对象
		List<CaseGroupModel> casegroupList = caseGroupService.getByDoctorUuid(doctorUuid);

		//第一步 查询默认分组
		List<Map<String, Object>>uuids = visitApplyService.selectCustomeruidByDoctorUuid(doctorUuid);
//		List<String> uuids = visitRecordService.getCustomerUuidByDoctorUuid(doctorUuid);
		if (!ObjectUtils.isEmpty(uuids)) {
			for (Map<String, Object> uuid : uuids) {
				CustomerDoctorReleModel cdr = customerDoctorReleService.getByCustomerUuidAndDoctorUuid(uuid.get("customerUuid").toString(), doctorUuid);
				if (cdr != null) {
					String grUuid = cdr.getGroupUuid();
					if (StringUtil.isEmpty(grUuid)||"0".equals(grUuid)) {
						customerDoctorReleList.add(cdr);
					}
				}
			}
		}
		// 定义返回List
		List<Map<String, Object>> list = new ArrayList<>();
		if (!ObjectUtils.isEmpty(customerDoctorReleList)) {
			for (CustomerDoctorReleModel cdrm : customerDoctorReleList) {
				Map<String, Object> save = new HashMap<>();
				String customerUuid = cdrm.getCustomerUuid();
				CustomerInfoModel customerInfoModel = customerInfoService.getCustomerInfoModelByCustomerUuid(customerUuid);
				// 患者id
				save.put("customerUuid", customerUuid);
				if (ObjectUtils.isEmpty(customerInfoModel)) {
					save.put("customerName", "");
					// 患者姓名
					save.put("customerName", "");
					// 患者头像
					save.put("customerImg", "");
					// 患者信息？
					save.put("customerMessage", "");
					// 患者性别
					save.put("sex", "");
					// 患者年
					save.put("age", "");
					save.put("applyTime",cdrm.getCreateTime());
				}else{
					// 患者姓名
					save.put("customerName", customerInfoModel.getRealName());
					// 患者头像
					save.put("customerImg", customerInfoModel.getImgUrl());
					// 患者信息？
					save.put("customerMessage", customerInfoModel.getIllnessDescription());
					// 患者性别
					save.put("sex", customerInfoModel.getSex());
					// 患者年
					save.put("age", customerInfoModel.getAge());
					save.put("applyTime",cdrm.getCreateTime());
				}
				list.add(save);
			}
		}
		Map<String,Object> normaltemp = new HashMap<>();
		normaltemp.put("groupId","0");
		normaltemp.put("groupName","默认分组");
		normaltemp.put("customers",list);
		resultlist.add(normaltemp);

		//第二步 查询医生所有的分组
		if (!ObjectUtils.isEmpty(casegroupList)){
			for (CaseGroupModel caseGroupModel:casegroupList){
				if (!StringUtil.isEmpty(caseGroupModel.getDoctorUuid())) {
					Map<String,Object> temp = new HashMap<>();
					customerDoctorReleList = customerDoctorReleService.getByDoctorUuidAndGroupId(doctorUuid, caseGroupModel.getUuid());
					// 定义返回List
					List<Map<String, Object>> relist = new ArrayList<>();
					if (customerDoctorReleList != null && customerDoctorReleList.size() > 0) {
						for (CustomerDoctorReleModel cdrm : customerDoctorReleList) {
							Map<String, Object> save = new HashMap<>();
							String customerUuid = cdrm.getCustomerUuid();
							CustomerInfoModel customerInfoModel = customerInfoService.getCustomerInfoModelByCustomerUuid(customerUuid);
							if (customerInfoModel == null) {
								customerInfoModel = new CustomerInfoModel();
							}
							// 患者id
							save.put("customerUuid", customerUuid);
							// 患者姓名
							save.put("customerName", customerInfoModel.getRealName());
							// 患者头像
							save.put("customerImg", customerInfoModel.getImgUrl());
							// 患者信息？
							save.put("customerMessage", customerInfoModel.getIllnessDescription());
							// 患者性别
							save.put("sex", customerInfoModel.getSex());
							// 患者年
							save.put("age", customerInfoModel.getAge());

							relist.add(save);
						}
					}
					temp.put("groupId",caseGroupModel.getUuid());
					temp.put("groupName",caseGroupModel.getGroupName());
					temp.put("customers",relist);
					resultlist.add(temp);
				}
			}
		}
		jsonMap.put("relist",resultlist);
		HttpServletUtils.outJson(response, jsonMap);
		return null;
	}

	/**
	 * 根据医生id，获取患者列表
	 */
	@RequestMapping(value = "/getCustomerListByDoctorUuid", method = RequestMethod.GET)
	public String getCustomerListByDoctorUuid(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("doctorUuid") String doctorUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		// 标志 以及必需要传的参数
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "doctorUuid,true" });

		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");

		// 判断医生id和分组id不能为空
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生id不能为空"), callback);
			return null;
		}

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 根据医生id获取关系列表
		List<CustomerDoctorReleModel> customerDoctorReleList = customerDoctorReleService.getByDoctorUuid(doctorUuid);
		// 定义返回List
		List relist = new ArrayList();
		if (customerDoctorReleList != null && customerDoctorReleList.size() > 0) {
			for (CustomerDoctorReleModel cdrm : customerDoctorReleList) {
				Map<String, Object> save = new HashMap<String, Object>();
				String customerUuid = cdrm.getCustomerUuid();
				CustomerInfoModel customerInfoModel = customerInfoService
						.getCustomerInfoModelByCustomerUuid(customerUuid);
				if (customerInfoModel == null) {
					// 患者姓名
					save.put("customerName", "");
					// 患者性别
					save.put("sex", "");
					// 患者年龄
					save.put("age", "");
					// 患者时间
					save.put("time", "");
					// 患者头像
					save.put("customerImg", "");
					// 患者信息？
					save.put("customerMessage", "");
					save.put("customerUuid", "");
					relist.add(save);
				} else {
					// 患者姓名
					save.put("customerName", customerInfoModel.getRealName());
					// 患者性别
					save.put("sex", customerInfoModel.getSex());
					// 患者年龄
					save.put("age", customerInfoModel.getAge());
					// 患者时间
					save.put("time", cdrm.getCreateTime());
					// 患者头像
					save.put("customerImg", customerInfoModel.getImgUrl());
					// 患者信息？
					save.put("customerMessage", customerInfoModel.getIllnessDescription());
					save.put("customerUuid", customerInfoModel.getCustomerUuid());

					relist.add(save);
				}
			}
		}

		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 当前患者修改患者分组
	 */
	@RequestMapping(value = "/1.0/updateCustomerGroup", method = RequestMethod.POST)
	public String updateCustomerGroup(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("groupId") String groupId, @RequestParam("customerUuid") String customerUuid,
			@RequestParam("doctorUuid") String doctorUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 判断患者id和分组id不能为空
		if (StringUtil.isEmpty(customerUuid) || StringUtil.isEmpty(groupId)) {
			HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, "患者id或分组id不能为空"));
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 根据hid得到患者信息
		CustomerDoctorReleModel customerDoctorReleModel = customerDoctorReleService.getByCustomerUuidAndDoctorUuid(customerUuid,doctorUuid);
		if (customerDoctorReleModel == null) {
			customerDoctorReleModel = new CustomerDoctorReleModel();
			customerDoctorReleModel.setDoctorUuid(doctorUuid);
			customerDoctorReleModel.setCustomerUuid(customerUuid);
			customerDoctorReleModel.setGroupUuid(groupId);
			customerDoctorReleModel.setCreateTime(DateFormatHelper.getNowTimeStr());
			customerDoctorReleService.create(customerDoctorReleModel);
		} else {
			// 设置分组id
			customerDoctorReleModel.setGroupUuid(groupId);
			// 修改患者分组
			customerDoctorReleService.update(customerDoctorReleModel);
		}

		HttpServletUtils.outJson(response, jsonMap);
		return null;
	}

	//TODO:删除患者信息
	/**
	 * 删除患者信息
	 */
	@RequestMapping(value = "/1.0/deleteCustomerByCostomerUuidAndGid", method = RequestMethod.POST)
	public String deleteCustomerByCostomerUuidAndGid(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("groupId") String gid, @RequestParam("customerUuid") String customerUuid,@RequestParam("doctorUuid") String doctorUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 标志 以及必需要传的参数
		Map<String, String> map = getParam(request, response, false,
				new String[] { "callback,false", "groupId,true", "customerUuid,true" });

		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");
		// 判断医生id和分组id不能为空
		if (StringUtil.isEmpty(customerUuid) || StringUtil.isEmpty(gid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "患者id或分组id不能为空"), callback);
			return null;
		}

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 删除
		if (!StringUtil.isEmpty(gid) && !"0".equals(gid)) {
			customerDoctorReleService.deleteByCustomerIdAndGroupId(customerUuid, gid);
		} else {
			customerDoctorReleService.deleteByCustomerIdAndGroupId(customerUuid, "");
		}
		System.out.println("==========customerUuid======"+customerUuid);
		System.out.println("==========doctorUuid======"+doctorUuid);

		//删除该患者的所有随访记录信息
		List<VisitRecordModel> visits = visitRecordService.getVisitRecordByCusAndDoc(customerUuid, doctorUuid);
		if(visits !=null && visits.size()>0){
			for(VisitRecordModel visit :visits){
				visitRecordService.delete(visit);
			}
		}
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	// TODO:		2.20.	患者分组-添加新的分组 clear（ver1.0）
	/**
	 * 添加患者分组信息 ,医生ID，组名称
	 */
	@RequestMapping(value = "/1.0/addCaseGroup", method = RequestMethod.POST)
	public String addCaseGroup(HttpServletResponse response,@RequestParam("doctorUuid") String doctorUuid,
			@RequestParam("groupName") String groupName) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 判断医生id不能为空
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, "医生id不能为空"));
			return null;
		}
		// 判断分组名不能为空
		if (StringUtil.isEmpty(groupName)) {
			HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, "分组名不能为空"));
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 创建casegroup对象
		CaseGroupModel caseGroupModel = new CaseGroupModel();
		caseGroupModel.setDoctorUuid(doctorUuid);
		caseGroupModel.setCreateTime(DateFormatHelper.getNowTimeStr());
		caseGroupModel.setGroupName(groupName);
		// 添加
		caseGroupService.create(caseGroupModel);
		HttpServletUtils.outJson(response, jsonMap);
		return null;
	}

	// TODO:		2.5.	获取随访申请-患者详细信息（ver1.0）

	/**
	 * 获取患者信息根据患者ID 12/24
	 *
	 * @param request  request
	 * @param response response
	 * @param hid      customerUuid
	 * @return
	 */
	@RequestMapping(value = "1.0/getCustomerByCostomerUuid", method = RequestMethod.GET)
	public String getCustomerByCostomerUuid(HttpServletRequest request, HttpServletResponse response,
											@RequestParam("customerUuid") String hid,@RequestParam("doctorUuid") String doctorUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 判断患者id不能为空
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, "医生编号不能为空"));
			return null;
		}
		// 判断患者id不能为空
		if (StringUtil.isEmpty(hid)) {
			HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, "患者id不能为空"));
			return null;
		}

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 通过患者id得到患者信息
		CustomerInfoModel customerInfoModel = customerInfoService.getCustomerInfoModelByCustomerUuid(hid);
		CustomerModel customerModel = customerService.getByUuid(hid);
		// 设置返回值
		Map<String, Object> save = new HashMap<>();

		if (null != customerInfoModel) {
			save.put("nickname", customerInfoModel.getNickName());
			save.put("realName", customerInfoModel.getRealName());
			save.put("certCode", customerInfoModel.getCertCode());
			save.put("marryState", customerInfoModel.getMarryState());
			save.put("industry", customerInfoModel.getIndustry());
			save.put("address", customerInfoModel.getAddress());
			save.put("customrUuid", customerInfoModel.getCustomerUuid());
			// 患者姓名
			save.put("customerName", customerInfoModel.getRealName());
			// 患者性别
			save.put("sex", customerInfoModel.getSex());
			// 患者年龄
			save.put("age", customerInfoModel.getAge());
			// 患者头像
			save.put("customerImg", customerInfoModel.getImgUrl());
			// 患者信息
			save.put("customerMessage", customerInfoModel.getIllnessDescription());
		}

		if (customerModel != null) {
			save.put("mobile", customerModel.getMobile());
			save.put("sex", customerModel.getSex());// 1是男 ，2是女
			save.put("email", customerModel.getEmail());
			// 患者创建时间
			save.put("time", customerModel.getCreateTime());
		}

		/* ————————— 患者的历史病历————————— */
		// 定义病历List
		List<Map<String, Object>> medicalRecordList = new ArrayList<>();
		List<MedicalRecordModel> mlist = medicalRecordService.getMedicalRecordListByCustomerUuidAndDoctorUuid(hid, doctorUuid);
		if (mlist != null && mlist.size() > 0) {
			for (int i = 0; i < mlist.size(); i++) {
				save.put("courseRecord", mlist.get(i).getCreateTime());
				Map<String, Object> medicalRecord = new HashMap<>();
				String createTime = mlist.get(i).getCreateTime();
				// 创建时间
				medicalRecord.put("createTime", createTime);
				String medicalRecordUuid = mlist.get(i).getUuid();
				String type = "";
				type = mlist.get(i).getCaseCategoryType();

				// 病历类型（门诊or住院）/* 病例类型 0住院号 1门诊号 2床位号 3病案号 4其他 */
				medicalRecord.put("type", type);

				// 病历主键
				medicalRecord.put("medicalRecordUuid", medicalRecordUuid);
				medicalRecordList.add(i, medicalRecord);
				/**
				 * 找出第一条数据，作为首次
				 */
				if (i == (mlist.size() - 1)) {
					try {
						//第一条数据的时间
						DateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
						Date d = formater.parse(mlist.get(i).getOpeTime());
						Date now = new Date();
						long day=(now.getTime()-d.getTime())/(24*60*60*1000);
						save.put("courseRecord", (day%365));
						save.put("firstRecord", d);
					} catch (ParseException e) {
						save.put("courseRecord", "");
						e.printStackTrace();
					}
					save.put("course_Record", "");
				}

			}
			// 病历List{"病历主键1，病历时间1","病历主键2，病历时间2"}
			jsonMap.put("medicalRecordList", medicalRecordList);
		} else {

		}

		/* ————————— 患者的历史病历————————— */

		/* ————————— 患者的历史随访表单————————— */
		// 患者的历史随访表单
		List<Map<String, Object>> visitList = new ArrayList<>();
		List<VisitRecordModel> visitRecordList = visitRecordService.getVisitRecordByCusAndDoc(hid, doctorUuid);
		if (visitRecordList != null && visitRecordList.size() > 0) {
			for (int i = 0; i < visitRecordList.size(); i++) {

				Map<String, Object> visitRecord = new HashMap<String, Object>();
				String createTime = visitRecordList.get(i).getCreateTime();
				// 创建时间
				visitRecord.put("createTime", createTime);
				String visitRecordUuid = visitRecordList.get(i).getUuid();
				// 随访记录Uuid
				visitRecord.put("visitRecordUuid", visitRecordUuid);
				visitList.add(i, visitRecord);
			}
			// 随访List{"随访记录Uuid1，创建时间1","随访记录Uuid2，创建时间2"}
			jsonMap.put("visitList", visitList);
		}
		/* ————————— 患者的历史随访表单————————— */
		// 整体存入
		jsonMap.put("info", save);
		HttpServletUtils.outJson(response, jsonMap);
		return null;
	}

	/**
	 * 
	 * 获取患者信息根据患者ID 12/24
	 * 
	 * @author szr
	 * @param request
	 * @param response
	 * @param hid
	 * @return
	 */
	@RequestMapping(value = "/getCustomerByCostomerUuid", method = RequestMethod.GET)
	public String getCustomerByCostomerUuid(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("hid") String hid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		// 标志 以及必需要传的参数
		Map<String, String> map = getParam(request, response, breakOut, new String[] { "callback,true", "hid,true" });
	
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");
		String doctorUuid = request.getParameter("doctorUuid");
		// 判断患者id不能为空
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生编号不能为空"), callback);
			return null;
		}
		// 判断患者id不能为空
		if (StringUtil.isEmpty(hid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "患者id不能为空"), callback);
			return null;
		}

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 通过患者id得到患者信息
		CustomerInfoModel customerInfoModel = customerInfoService.getCustomerInfoModelByCustomerUuid(hid);
		// 设置返回值
		Map<String, Object> save = new HashMap<String, Object>();
		// 患者姓名
		save.put("customerName", customerInfoModel.getRealName());
		if (customerInfoModel != null) {
			// 患者性别
			save.put("sex", customerInfoModel.getSex());
			// 患者年龄
			save.put("age", customerInfoModel.getAge());
			// 患者头像
			save.put("customerImg", customerInfoModel.getImgUrl());
			// 患者信息
			save.put("customerMessage", customerInfoModel.getIllnessDescription());
			save.put("customrUuid", customerInfoModel.getIllnessDescription());
		}
		CustomerModel customerModel = customerService.getByUuid(hid);
		if (customerModel != null) {
			// 患者创建时间
			save.put("time", customerModel.getCreateTime());
		}

		/* ————————— 患者的历史病历————————— */
		// 定义病历List
		List<Map<String, Object>> medicalRecordList = new ArrayList<Map<String, Object>>();
		List<MedicalRecordModel> mlist = medicalRecordService.getMedicalRecordListByCustomerUuidAndDoctorUuid(hid, doctorUuid);
		if (mlist != null && mlist.size() > 0) {
			for (int i = 0; i < mlist.size(); i++) {
				Map<String, Object> medicalRecord = new HashMap<String, Object>();
				String createTime = mlist.get(i).getCreateTime();
				// 创建时间
				medicalRecord.put("createTime", createTime);
				String medicalRecordUuid = mlist.get(i).getUuid();
				String type = "";
				type = mlist.get(i).getCaseCategoryType();

				// 病历类型（门诊or住院）/* 病例类型 0住院号 1门诊号 2床位号 3病案号 4其他 */
				medicalRecord.put("type", type);

				// 病历主键
				medicalRecord.put("medicalRecordUuid", medicalRecordUuid);
				medicalRecordList.add(i, medicalRecord);
			}
			// 病历List{"病历主键1，病历时间1","病历主键2，病历时间2"}
			jsonMap.put("medicalRecordList", medicalRecordList);
		}

		/* ————————— 患者的历史病历————————— */

		/* ————————— 患者的历史随访表单————————— */
		// 患者的历史随访表单
		List<Map<String, Object>> visitList = new ArrayList<Map<String, Object>>();
		List<VisitRecordModel> visitRecordList = visitRecordService.getVisitRecordByCusAndDoc(hid, doctorUuid);
		if (visitRecordList != null && visitRecordList.size() > 0) {
			for (int i = 0; i < visitRecordList.size(); i++) {

				Map<String, Object> visitRecord = new HashMap<String, Object>();
				String createTime = visitRecordList.get(i).getCreateTime();
				// 创建时间
				visitRecord.put("createTime", createTime);
				String visitRecordUuid = visitRecordList.get(i).getUuid();
				// 随访记录Uuid
				visitRecord.put("visitRecordUuid", visitRecordUuid);
				visitList.add(i, visitRecord);
			}
			// 随访List{"随访记录Uuid1，创建时间1","随访记录Uuid2，创建时间2"}
			jsonMap.put("visitList", visitList);
		}
		/* ————————— 患者的历史随访表单————————— */
		// 整体存入
		jsonMap.put("info", save);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 获取病历信息根据病历ID
	 */
	@RequestMapping(value = "/getMedicalRecordByMedicalRecordUuid", method = RequestMethod.GET)
	public String getMedicalRecordByMedicalRecordUuid(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("medicalRecordUuid") String medicalRecordUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		// 标志 以及必需要传的参数
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "medicalRecordUuid,true" });

		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");

		// 判断患者id不能为空
		if (StringUtil.isEmpty(medicalRecordUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "病历id不能为空"), callback);
			return null;
		}

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 通过病历id得到病历信息
		MedicalRecordModel medicalRecordModel = medicalRecordService.getByUuid(medicalRecordUuid);
		// 设置存入信息
		Map<String, Object> info = new HashMap<String, Object>();

		/*
		 * // 就诊时间 info.put("seeDoctorTime",
		 * medicalRecordModel.getCreateTime());
		 * 
		 * // 就诊医院 info.put("hospital", medicalRecordModel.getHospitalName());
		 * 
		 * // 就诊科室 info.put("division", medicalRecordModel.getDivisionName());
		 * 
		 * // 就诊医生 info.put("doctor", medicalRecordModel.getDoctorName());
		 * 
		 * // 病历记录 info.put("record", medicalRecordModel.getDoctorName());
		 * 
		 * // 图片1 info.put("image1", medicalRecordModel.getImg1Url());
		 * 
		 * // 图片2 info.put("image2", medicalRecordModel.getImg2Url());
		 * 
		 * // 图片3 info.put("image3", medicalRecordModel.getImg3Url());
		 * 
		 * // 图片4 info.put("image4", medicalRecordModel.getImg4Url());
		 * 
		 * // 图片5 info.put("image5", medicalRecordModel.getImg5Url());
		 */

		// 整体存入
		jsonMap.put("info", medicalRecordModel);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	// TODO:		2.2.	添加患者-获取所有的分组 clear（ver1.0）
	/**
	 * 患者分组列表获取
	 */
	@RequestMapping(value = "/1.0/getCaseGroupByDoctorId", method = RequestMethod.GET)
	public String getCaseGroupByDoctorId(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("doctorUuid") String doctorUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 判断医生id不能为空
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, "医生id不能为空"));
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 得到casegroupList对象
		List<CaseGroupModel> casegroupList = caseGroupService.getByDoctorUuid(doctorUuid);

		// 定义返回List
		List<Map<String,Object>> relist = new ArrayList<>();
		if (casegroupList != null && casegroupList.size() > 0) {
			for (CaseGroupModel cgm : casegroupList) {
				Map<String, Object> save = new HashMap<>();
				if (cgm != null) {
					// 分组名
					save.put("groupName", cgm.getGroupName());
					// 分组id
					save.put("groupId", cgm.getUuid());
				}
				relist.add(save);
			}
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJson(response, jsonMap);
		return null;
	}

	// TODO:		2.21.	修改分组的名称clear（ver1.0）

	/**
	 * 修改患者分组
	 */
	@RequestMapping(value = "/1.0/updateCaseGroup", method = RequestMethod.POST)
	public String updateCaseGroup(HttpServletResponse response, @RequestParam("groupId") String gid, @RequestParam("groupName") String groupName) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 分组id不能为空
		if (StringUtil.isEmpty(gid)) {
			HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, "分组id不能为空"));
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		CaseGroupModel caseGroupModel = caseGroupService.getByUuid(gid);

		// 分组id不能为空
		if (caseGroupModel != null) {
			caseGroupModel.setGroupName(groupName);
			caseGroupService.update(caseGroupModel);
		}
		HttpServletUtils.outJson(response, jsonMap);
		return null;
	}

	// TODO:		2.22.	患者分组-删除 clear （ver1.0）
	/**
	 * 删除患者分组
	 */
	@RequestMapping(value = "/1.0/deleteCaseGroup", method = RequestMethod.POST)
	public String deleteCaseGroup(HttpServletRequest request, HttpServletResponse response, @RequestParam("groupId") String gid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 分组id不能为空
		if (StringUtil.isEmpty(gid)) {
			HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, "分组id不能为空"));
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 设置提醒值 0表示该分组下无患者。 1表示该分组下有患者
		String flag;
		// 判断customerDoctorReleModel中是否有值
		List<CustomerDoctorReleModel> list = customerDoctorReleService.getByGroupUuid(gid);
		if (list != null) {
			flag = "1";
			//删除分组之后 到默认分组里面
			for (CustomerDoctorReleModel cdr : list) {
				cdr.setGroupUuid("0");
				customerDoctorReleService.update(cdr);
			}
		} else {
			flag = "0";
		}
		// 设置返回信息 提醒值 0表示该分组下无患者。 1表示该分组下有患者
		jsonMap.put("flag", flag);
		// 得到caseGroupModel
		CaseGroupModel caseGroupModel = caseGroupService.getByUuid(gid);
		// 分组id不能为空
		if (caseGroupModel != null) {

			// 删除
			caseGroupService.delete(caseGroupModel);
		}

		HttpServletUtils.outJson(response, jsonMap);
		return null;
	}

    // TODO:		2.1.添加患者-手机号查询 clear（ver1.0）

    /**
     * 通过手机号判断患者：
     1、手机号是未注册的患者：不变，需医生手动输入数据，并在患者注册时提示，并发送短信提醒患者下载
     2、手机号是已经注册的患者端用户：拉取数据并填充
     3、手机号已经是医生的随访患者：弹出消息提示框“此手机号已经是您的随访患者！确认”跳转至患者详情页面
     4、手机号已经有其他随访医生，弹出提示信息“该患者已有随访医生，暂不能添加该患者”
     */
    @RequestMapping(value = "/1.0/getCustomerByMobile", method = RequestMethod.GET)
    public String getCustomerByMobile(HttpServletRequest request, HttpServletResponse response,@RequestParam("doctorUuid") String uuid, @RequestParam("mobile") String mobile) {
        // 设置返回数据编码
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 判断手机号不能为空
        if (StringUtil.isEmpty(mobile)) {
            HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, "手机号为空，无法查询"));
            return null;
        }

        // 设置返回信息
        Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
        // 得到casegroupList对象
        CustomerModel customerModel = customerService.getCustomerByMobile(mobile);

        //未注册患者输入数据，客户端接受到未注册的提示，需要发送短信来进行提醒
        if (customerModel == null) {
            HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, "手机号是未注册的患者"));
            return null;
        }

        //已注册患者 查询基本信息和是否随访
        //没有基本信息
        CustomerInfoModel customerInfoModel = customerInfoService.getCustomerInfoModelByCustomerUuid(customerModel.getUuid());
        if (customerInfoModel == null) {
            HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, "该患者无基本信息"));
            return null;
        }
        //查询到基本信息，尝试获取患者是否有随访信息
        List<VisitRecordModel> list = visitRecordService.getByCustomerUuid(customerModel.getUuid());
        //查询随访信息，判断是否是该医生的随访患者，进行判断返回
        if (list != null && list.size() > 0) {
            if (uuid.equals(list.get(0).getServiceStaffUuid())){
                HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, "此手机号已经是您的随访患者！"));
                return null;
            }else {
                HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, "该患者已有随访医生，暂不能添加该患者！"));
                return null;
            }
        }{
            //未查询到随访信息，只放置患者基础信息
            // 患者姓名
            jsonMap.put("name", customerInfoModel.getRealName());

            HttpServletUtils.outJson(response, jsonMap);
            return null;
        }
    }


    /**
	 * 通过手机号，获得患者信息
	 */
//	@RequestMapping(value = "/getCustomerByMobile", method = RequestMethod.GET)
//	public String getCustomerByMobile(HttpServletRequest request, HttpServletResponse response,
//			@RequestParam("mobile") String mobile) {
//		// 设置返回数据编码
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
//		// 获取参数值并且进行非空判断
//
//		// 标志 以及必需要传的参数
//		Map<String, String> map = getParam(request, response, false,
//				new String[] { "callback,true", "mobile,true" });
//
//		if ("true".equals(map.get("breakOut"))) {
//			return null;
//		}
//		Object callback = map.get("callback");
//
//		// 判断医生id不能为空
//		if (StringUtil.isEmpty(mobile)) {
//			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "手机号为空，无法查询"), callback);
//			return null;
//		}
//
//		// 设置返回信息
//		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
//		// 得到casegroupList对象
//		CustomerModel customerModel = customerService.getCustomerByMobile(mobile);
//
//		if (customerModel == null) {
//			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "该手机号下无患者"), callback);
//			return null;
//		}
//
//		CustomerInfoModel customerInfoModel = customerInfoService
//				.getCustomerInfoModelByCustomerUuid(customerModel.getUuid());
//		if (customerInfoModel == null) {
//			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "该患者无基本信息"), callback);
//			return null;
//		}
//
//		//获取患者是否有随访信息 如果有 提示该患者已有随访信息 不用再单独添加患者信息
//		List<VisitRecordModel> lits =  visitRecordService.getByCustomerUuid(customerModel.getUuid());
//		if(lits !=null && lits.size()>0){
//			jsonMap.put("customerUuid", customerModel.getUuid());
//			jsonMap.put("visitHas", "1");
//			HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
//			return null;
//		}
//
//		/* 放值 */
//		// 患者姓名
//		jsonMap.put("name", customerInfoModel.getRealName());
//		// 患者性别
//		jsonMap.put("sex", customerInfoModel.getSex());
//		// 患者出生日期
//		jsonMap.put("birthday", customerInfoModel.getBirthday());
//		// 患者身份证号
//		jsonMap.put("IDNum", customerInfoModel.getCertCode());
//		// 患者手机号
//		jsonMap.put("mobile", customerModel.getMobile());
//		// 患者邮箱
//		jsonMap.put("email", customerModel.getEmail());
//		// 患者职业
//		jsonMap.put("industry", customerInfoModel.getIndustry());
//		// 患者住址
//		jsonMap.put("address", customerInfoModel.getAddress());
//		// 介绍人
//		jsonMap.put("introduceName", customerInfoModel.getIntroduceName());
//		// 紧急联系人
//		jsonMap.put("alternativeName", customerInfoModel.getAlternativeName());
//		// 紧急联系人电话
//		jsonMap.put("alternativePhone", customerInfoModel.getAlternativePhone());
//
//		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
//		return null;
//	}

    // TODO:		2.3.	添加患者-保存 clear（ver1.0）
    /**
     * 医生添加患者成功，患者将收到推送的消息“XXX医生申请成为您的随访医生/通过 ”
     *
     * @param response           response
     * @param name               患者姓名
     * @param mobile             手机号
     * @param groupId            分组id
     * @param illnessDescription 诊断
     * @return null
     */
    @RequestMapping(value = "/1.0/addCustomer", method = RequestMethod.POST)
    public String addCustomer(HttpServletResponse response,
                              @RequestParam("doctorUuid") String doctorUuid,
                              @RequestParam("name") String name,
                              @RequestParam("mobile") String mobile,
                              @RequestParam("groupId") String groupId,
                              @RequestParam(value = "illnessDescription",required = false) String illnessDescription) {
        // 设置返回数据编码
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        // 判断医生id不能为空
        if (StringUtil.isEmpty(doctorUuid)) {
            HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, "医生id不能为空"));
            return null;
        }

        /************************ 添加患者信息 *************************/

        CustomerModel customerModel = customerService.getCustomerByMobile(mobile);
        if (customerModel != null) {
            // 手机号
            customerModel.setMobile(mobile);
            customerService.update(customerModel);
        } else {
            customerModel = new CustomerModel();
            // 手机号
            customerModel.setMobile(mobile);
            // 创建患者model
            customerService.create(customerModel);

        }
        // 得到患者uuid
        String customerUuid = customerModel.getUuid();
        CustomerInfoModel customerInfoModel = customerInfoService.getCustomerInfoModelByCustomerUuid(customerUuid);
        /************************ 创建患者基本信息 *************************/
        if (customerInfoModel != null) {
            // 姓名
            customerInfoModel.setRealName(name);
            customerInfoModel.setIllnessDescription(illnessDescription);
            // 创建
            customerInfoService.update(customerInfoModel);
        } else {
            customerInfoModel = new CustomerInfoModel();
            // 患者id
            customerInfoModel.setCustomerUuid(customerUuid);
            // 姓名
            customerInfoModel.setRealName(name);
            customerInfoModel.setIllnessDescription(illnessDescription);
			customerInfoModel.setFirstDiagnosis(null);
            // 创建
            customerInfoService.create(customerInfoModel);

        }
        /************************ 创建医生患者关系信息 *************************/

        CustomerDoctorReleModel customerDoctorReleModel = customerDoctorReleService
                .getByCustomerUuidAndDoctorUuid(customerUuid, doctorUuid);
        if (customerDoctorReleModel == null) {
            customerDoctorReleModel = new CustomerDoctorReleModel();
            customerDoctorReleModel.setCustomerUuid(customerUuid);
            customerDoctorReleModel.setDoctorUuid(doctorUuid);
			customerDoctorReleModel.setGroupUuid(groupId);
            // 创建
            customerDoctorReleService.create(customerDoctorReleModel);

			//添加随访申请
			VisitApply bean = new VisitApply(IdentityHelper.uuid2());//随访申请表
			bean.setCustomerUuid(customerUuid);//患者id
			bean.setServiceStaffUuid(doctorUuid);//医生id
			bean.setCreateTime(DateFormatHelper.getNowTimeStr());//创建时间
			bean.setOpeTime(DateFormatHelper.getNowTimeStr());//操作时间
			bean.setApplyState("1");
			try {
				service.insert(bean);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

        /************************ 创建病历信息 *************************/
        MedicalRecordModel medicalRecordModel = new MedicalRecordModel();
        // 患者uuid
        medicalRecordModel.setCustomerUuid(customerUuid);
        // 医生id
        medicalRecordModel.setDoctorUuid(doctorUuid);
        // 得到医生信息
        ServicestaffModel servicestaffModel = servicestaffService.getByUuid(doctorUuid);
        // 医院id
        medicalRecordModel.setHospitalUuid(servicestaffModel.getHospital());
        // 科室id ??
        medicalRecordModel.setDivisionUuid(servicestaffModel.getDepartment());
        // 创建
        medicalRecordService.create(medicalRecordModel);
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
        HttpServletUtils.outJson(response, jsonMap);
        return null;
    }

//    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
//    public String addCustomer(HttpServletRequest request, HttpServletResponse response,
//                              @RequestParam("doctorUuid") String doctorUuid, @RequestParam("name") String name,
//                              @RequestParam("sex") String sex, @RequestParam("IDNum") String IDNum, @RequestParam("mobile") String mobile,
//                              @RequestParam("email") String email, @RequestParam("industry") String industry,
//                              @RequestParam("address") String address, @RequestParam("introduceName") String introduceName,
//                              @RequestParam("alternativeName") String alternativeName,
//                              @RequestParam("alternativePhone") String alternativePhone, @RequestParam("birthday") String birthday,
//                              @RequestParam("seeDoctorTime") String seeDoctorTime, @RequestParam("divisionUuid") String divisionUuid,
//                              @RequestParam("caseCategoryType") String caseCategoryType, @RequestParam("medicalNum") String medicalNum) {
//
//        // 设置返回数据编码
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html;charset=UTF-8");
//        // 获取参数值并且进行非空判断
//        Map<String, String> map = getParam(request, response, false,
//
//                new String[] { "callback,true", "doctorUuid,true", "name,true", "sex,true", "IDNum,true", "mobile,true",
//                        "email,true", "industry,true", "address,true", "introduceName,true", "alternativeName,true",
//                        "alternativePhone,true", "birthday,true", "seeDoctorTime,true", "divisionUuid,true",
//                        "caseCategoryType,true", "medicalNum,true" });
//
//        if ("true".equals(map.get("breakOut"))) {
//            return null;
//        }
//        Object callback = map.get("callback");
//
//        // 判断医生id不能为空
//        if (StringUtil.isEmpty(doctorUuid)) {
//            HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生id不能为空"), callback);
//            return null;
//        }
//
//        // 设置返回信息
//        Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
//
//        /************************ 添加患者信息 *************************/
//
//        CustomerModel customerModel = customerService.getCustomerByMobile(mobile);
//        if (customerModel != null) {
//            // 手机号
//            customerModel.setMobile(mobile);
//            // 邮箱
//            customerModel.setEmail(email);
//            customerService.update(customerModel);
//        } else {
//            customerModel = new CustomerModel();
//            // 手机号
//            customerModel.setMobile(mobile);
//            // 邮箱
//            customerModel.setEmail(email);
//            // 创建患者model
//            customerService.create(customerModel);
//
//        }
//        // 得到患者uuid
//        String customerUuid = customerModel.getUuid();
//        CustomerInfoModel customerInfoModel = customerInfoService.getCustomerInfoModelByCustomerUuid(customerUuid);
//        /************************ 创建患者基本信息 *************************/
//        if (customerInfoModel != null) {
//            // 姓名
//            customerInfoModel.setRealName(name);
//            // 性别
//            customerInfoModel.setSex(sex);
//            // 身份证
//            customerInfoModel.setCertCode(IDNum);
//            // 所在行业
//            customerInfoModel.setIndustry(industry);
//            // 住址
//            customerInfoModel.setAddress(address);
//            // 介绍人
//            customerInfoModel.setIntroduceName(introduceName);
//            // 紧急联系人
//            customerInfoModel.setAlternativeName(alternativeName);
//            // 紧急联系人电话
//            customerInfoModel.setAlternativePhone(alternativePhone);
//            // 生日
//            customerInfoModel.setBirthday(birthday);
//            // 创建
//            customerInfoService.update(customerInfoModel);
//        } else {
//            customerInfoModel = new CustomerInfoModel();
//            // 患者id
//            customerInfoModel.setCustomerUuid(customerUuid);
//            // 姓名
//            customerInfoModel.setRealName(name);
//            // 性别
//            customerInfoModel.setSex(sex);
//            // 身份证
//            customerInfoModel.setCertCode(IDNum);
//            // 所在行业
//            customerInfoModel.setIndustry(industry);
//            // 介绍人
//            customerInfoModel.setIntroduceName(introduceName);
//            // 紧急联系人
//            customerInfoModel.setAlternativeName(alternativeName);
//            // 紧急联系人电话
//            customerInfoModel.setAlternativePhone(alternativePhone);
//            // 生日
//            customerInfoModel.setBirthday(birthday);
//            // 住址
//            customerInfoModel.setAddress(address);
//            // 创建
//            customerInfoService.create(customerInfoModel);
//
//        }
//        /************************ 创建医生患者关系信息 *************************/
//
//        CustomerDoctorReleModel customerDoctorReleModel = customerDoctorReleService
//                .getByCustomerUuidAndDoctorUuid(customerUuid, doctorUuid);
//        if (customerDoctorReleModel == null) {
//            customerDoctorReleModel = new CustomerDoctorReleModel();
//            customerDoctorReleModel.setCustomerUuid(customerUuid);
//            customerDoctorReleModel.setDoctorUuid(doctorUuid);
//            // 创建
//            customerDoctorReleService.create(customerDoctorReleModel);
//        }
//
//        /************************ 创建病历信息 *************************/
//        MedicalRecordModel medicalRecordModel = new MedicalRecordModel();
//        // 患者uuid
//        medicalRecordModel.setCustomerUuid(customerUuid);
//
//        // 就诊时间
//        medicalRecordModel.setSeeDoctorTime(seeDoctorTime);
//        // 科室 ??
//        medicalRecordModel.setDivisionUuid(divisionUuid);
//        // 病理类型 （编号类型）0住院号 1门诊号 2床位号 3病案号 4其他
//        medicalRecordModel.setCaseCategoryType(caseCategoryType);
//        // 编号
//        medicalRecordModel.setMedicalNum(medicalNum);
//        // 医生id
//        medicalRecordModel.setDoctorUuid(doctorUuid);
//        // 得到医生信息
//        ServicestaffModel servicestaffModel = servicestaffService.getByUuid(doctorUuid);
//        // 医院id
//        medicalRecordModel.setHospitalUuid(servicestaffModel.getHospital());
//        // 科室id ??
//        medicalRecordModel.setDivisionUuid(servicestaffModel.getDepartment());
//        // 创建
//        medicalRecordService.create(medicalRecordModel);
//
//        // 返回病历id,患者id，及患者信息
//        jsonMap.put("medicalRecordUuid", medicalRecordModel.getUuid());
//        jsonMap.put("customerUuid", customerUuid);
//        jsonMap.put("customerName", name);
//        jsonMap.put("sex", sex);
//        jsonMap.put("age", customerInfoModel.getAge());
//        jsonMap.put("seeDoctorTime", seeDoctorTime);
//
//        HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
//
//        return null;
//    }
	/**
	 * 获取患者病例信息详情
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getCustomer", method = RequestMethod.POST)
	public String getCustomer(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("customerUuid") String customerUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut, new String[] { "callback,true" });

		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");
		String doctorUuid = request.getParameter("doctorUuid");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 判断医生id不能为空
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "患者编号不能为空"), callback);
			return null;
		}

		CustomerModel customerModel = customerService.getByUuid(customerUuid);
		if (customerModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "该手机号下无患者"), callback);
			return null;
		}

		CustomerInfoModel customerInfoModel = customerInfoService
				.getCustomerInfoModelByCustomerUuid(customerModel.getUuid());
		if (customerInfoModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "该患者无基本信息"), callback);
			return null;
		}

		// 患者姓名
		jsonMap.put("name", customerInfoModel.getRealName());
		// 患者性别
		jsonMap.put("sex", customerInfoModel.getSex());
		// 患者出生日期
		jsonMap.put("birthday", customerInfoModel.getBirthday());
		// 患者身份证号
		jsonMap.put("IDNum", customerInfoModel.getCertCode());
		// 患者手机号
		jsonMap.put("mobile", customerModel.getMobile());
		// 患者邮箱
		jsonMap.put("email", customerModel.getEmail());
		// 患者职业
		jsonMap.put("industry", customerInfoModel.getIndustry());
		// 患者住址
		jsonMap.put("address", customerInfoModel.getAddress());
		// 介绍人
		jsonMap.put("introduceName", customerInfoModel.getIntroduceName());
		// 紧急联系人
		jsonMap.put("alternativeName", customerInfoModel.getAlternativeName());
		// 紧急联系人电话
		jsonMap.put("alternativePhone", customerInfoModel.getAlternativePhone());
		/* ——————————最新病历信息———————— */
		MedicalRecordModel medicalRecordModel = medicalRecordService.getNewestByCustomerUuidAndDoctorUuid(customerUuid,
				doctorUuid);
		// 病历信息
		jsonMap.put("medicalRecord", medicalRecordModel);

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;

	}

	/**
	 * 添加主诉
	 *
	 * @param request
	 * @param response
	 * @param medicalRecordUuid
	 * @return
	 */
	@RequestMapping(value = "/addMainsuit", method = RequestMethod.POST)
	public String addMainsuit(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("medicalRecordUuid") String medicalRecordUuid, @RequestParam("mainsuit") String mainsuit) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("textml;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "medicalRecordUuid,true", "callback,true", "mainsuit,true" });
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");

		// 判断医生id不能为空
		if (StringUtil.isEmpty(medicalRecordUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "病历id为空"), callback);
			return null;
		}

		// 通过uuid得到病历信息
		MedicalRecordModel medicalRecordModel = medicalRecordService.getByUuid(medicalRecordUuid);
		if(medicalRecordModel==null){
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "病历信息为空"), callback);
			return null;
		}
		// 患者uuid
		medicalRecordModel.setMainsuit(mainsuit);

		// 更新
		medicalRecordService.update(medicalRecordModel);

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 添加患者诊断
	 *
	 * @param request
	 * @param response
	 * @param medicalRecordUuid
	 * @return
	 */
	@RequestMapping(value = "/addDiagnosis", method = RequestMethod.POST)
	public String addDiagnosis(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("medicalRecordUuid") String medicalRecordUuid, @RequestParam("diagnosis") String diagnosis) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("textml;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "medicalRecordUuid,true", "callback,true" });
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");

		// 判断医生id不能为空
		if (StringUtil.isEmpty(medicalRecordUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "病历id为空"), callback);
			return null;
		}

		// 通过uuid得到病历信息
		MedicalRecordModel medicalRecordModel = medicalRecordService.getByUuid(medicalRecordUuid);

		// 患者uuid
		if (medicalRecordModel != null) {
			medicalRecordModel.setDiagnosis(diagnosis);
			// 更新
			medicalRecordService.update(medicalRecordModel);
		}

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 获取常用项列表
	 *
	 * @author xp
	 * @param request
	 * @param response
	 * @param doctorid
	 * @return
	 */
	@RequestMapping(value = "/getCustomerCommonList", method = RequestMethod.GET)
	public String getCustomerCommonList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("doctorid") String doctorid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "doctorid,true" });
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
		List<VisitCustomerCommonModel> list = visitCustomerCommonService.getCustomerCommonListByDoctorid(doctorid);
		List relist = new ArrayList();
		if (list != null && list.size() > 0) {
			for (VisitCustomerCommonModel vcm : list) {
				// 组装返回到客户端的医生信息
				Map<Object, Object> maps = new HashMap<Object, Object>();
				if (vcm != null) {
					maps.put("content", vcm.getContent());
					maps.put("customerCommonUuid", vcm.getUuid());
					relist.add(maps);
				}
			}
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 添加患者病史常用项
	 *
	 * @author xp
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addCustomerCommon", method = RequestMethod.POST)
	public String addCustomerCommon(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("doctorUuid") String doctorUuid, @RequestParam("content") String content) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "doctorUuid,true", "content,true", "callback,true" });
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");

		/************************ 添加患者常用病史信息 *************************/
		VisitCustomerCommonModel vc = new VisitCustomerCommonModel();
		if (!StringUtil.isEmpty(doctorUuid) && !StringUtil.isEmpty(content)) {
			vc.setServiceStatffUuid(doctorUuid);
			vc.setContent(content);
			vc.setCreateTime(DateFormatHelper.getNowTimeStr());
			visitCustomerCommonService.create(vc);
		}
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 删除病史常用项
	 *
	 * @author xp
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/deleteCustomerCommon", method = RequestMethod.GET)
	public String deleteCustomerCommon(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("customerCommonUuid") String customerCommonUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "customerCommonUuid,true", "callback,true" });
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");

		/************************ 删除患者常用病史信息 *************************/
		if (!StringUtil.isEmpty(customerCommonUuid)) {
			visitCustomerCommonService.deleteCustomerCommonModelByUuid(customerCommonUuid);
		}
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 添加病历（门诊/住院）
	 *
	 * @param request
	 * @param response
	 * @param jsonString 1  :门诊 2：住院
	 * @return
	 */
	@RequestMapping(value = "/addOutHallMedicalRecord", method = RequestMethod.GET)
	public String addOutHallMedicalRecord(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("jsonString") String jsonString) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "jsonString,true", "callback,true" });

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");

		if (StringUtil.isEmpty(jsonString)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "请求参数为空"), callback);
			return null;
		}

		JSONObject jsonObject = JSON.parseObject(jsonString);

		String customerUuid = jsonObject.getString("customerUuid");

		String doctorUuid = jsonObject.getString("doctorUuid");

		String caseCategoryType = jsonObject.getString("caseCategoryType");

		String seeDoctorTime = jsonObject.getString("seeDoctorTime");
		String startTime = jsonObject.getString("startTime");
		String endTime = jsonObject.getString("endTime");

		String hospitalUuid = jsonObject.getString("hospitalUuid");
		// 随访编号
		String visitRecordUuid = jsonObject.getString("visitRecordUuid");

		/************************ 创建病历 *************************/
		MedicalRecordModel medicalRecordModel = new MedicalRecordModel();
		// 患者id
		medicalRecordModel.setCustomerUuid(customerUuid);

		/******* 就诊信息 *********/
		// 就诊类型
		medicalRecordModel.setCaseCategoryType(caseCategoryType);
		// 就诊时间
		medicalRecordModel.setSeeDoctorTime(seeDoctorTime);
		// 就诊医院
		medicalRecordModel.setHospitalUuid(hospitalUuid);
		// 就诊医生
		medicalRecordModel.setDoctorUuid(doctorUuid);
		medicalRecordModel.setStartTime(startTime);
		medicalRecordModel.setEndTime(endTime);
		medicalRecordModel.setVisitRecordUuid(visitRecordUuid);
		// 照片
		String img1 = jsonObject.getString("img1");
		String img2 = jsonObject.getString("img2");
		String img3 = jsonObject.getString("img3");
		String img4 = jsonObject.getString("img4");
		String img5 = jsonObject.getString("img5");
		if (!StringUtil.isEmpty(img1)) {
			medicalRecordModel.setImage1(img1);
		}

		if (!StringUtil.isEmpty(img2)) {
			medicalRecordModel.setImage2(img2);
		}

		if (!StringUtil.isEmpty(img3)) {
			medicalRecordModel.setImage3(img3);
		}

		if (!StringUtil.isEmpty(img4)) {
			medicalRecordModel.setImage4(img4);
		}
		if (!StringUtil.isEmpty(img5)) {
			medicalRecordModel.setImage5(img5);
		}

		// 创建
		medicalRecordService.create(medicalRecordModel);
		if (!StringUtil.isEmpty(doctorUuid)) {
			// 创建病例成功，积分+20
			vipclubIntegralLogService.saveVipIntegralLog(doctorUuid, "add", 20, IntegralType.ADD_MEDICALRECORD.getValue(),
					VipclubIntegralLogModel.VIPCLUB_USERTYPE_DOC, "创建病例成功，积分加20","");
			// szr 推送消息 患者收到添加新病例的消息提醒
			String content = MessageHelper.getMessage("addOutHallMedicalRecord.showmessage.newAdd");
			String medicalRecordUuid = medicalRecordModel.getUuid();
			innerMessageService.saveInnerMessageAndPush(doctorUuid, customerUuid, content,
					InnerMessageModel.ACCOUNT_TYPE_STORE, InnerMessageModel.ACCOUNT_TYPE_CUSTOMER,
					PushConst.push_client_customer, "medicalRecordUuid", medicalRecordUuid, InnerMessageTypeEnum.CASE.getValue());
		}
		// 返回病例信息
		jsonMap.put("medicalRecordUuid", medicalRecordModel.getUuid());
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}
	
	/**
	 * 添加基本检查
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addBasicCheck", method = RequestMethod.POST)
	public String addBasicCheck(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("jsonString") String jsonString) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("textml;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut, new String[] { "callback,true" });
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");

		if (StringUtil.isEmpty(jsonString)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "请求参数为空"), callback);
			return null;
		}

		JSONObject jsonObject = JSON.parseObject(jsonString);

		String medicalRecordUuid = jsonObject.getString("medicalRecordUuid");

		// 判断医生id不能为空
		if (StringUtil.isEmpty(medicalRecordUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "病历id为空"), callback);
			return null;
		}

		// 通过uuid得到病历信息
		MedicalRecordModel medicalRecordModel = medicalRecordService.getByUuid(medicalRecordUuid);

		/******* 病历记录 *********/

		String mainsuit = jsonObject.getString("mainsuit");
		String family = jsonObject.getString("family");

		String assistCheck = jsonObject.getString("assistCheck");

		String previous = jsonObject.getString("previous");

		String personal = jsonObject.getString("personal");

		String spiritCheck = jsonObject.getString("spiritCheck");

		String illness = jsonObject.getString("illness");

		String somastate = jsonObject.getString("somastate");

		String process = jsonObject.getString("process");

		//
		String cureCourse = jsonObject.getString("cureCourse");

		String attackNum = jsonObject.getString("attackNum");

		String comorbidity = jsonObject.getString("comorbidity");

		String complication = jsonObject.getString("complication");

		String scaleAppraisal = jsonObject.getString("scaleAppraisal");

		String abnormal = jsonObject.getString("abnormal");
		String diagnosis = jsonObject.getString("diagnosis");
		String preAssistCheck = jsonObject.getString("preAssistCheck");

		String visitPreceptUuid = jsonObject.getString("visitPreceptUuid");

		// 主要诊断图片
		String diagnosisImage = jsonObject.getString("diagnosisImage");
		// 主要 辅助检查
		String assistCheckImage = jsonObject.getString("assistCheckImage");

		// 治疗经过图片
		String cureCourseImage = jsonObject.getString("cureCourseImage");
		// 主要 辅助检查
		String preAssistCheckImage = jsonObject.getString("preAssistCheckImage");
		// 主诉
		if(!StringUtil.isEmpty(family)){
			medicalRecordModel.setFamily(family);
		}
		// 主诉
		if(!StringUtil.isEmpty(mainsuit)){
			medicalRecordModel.setMainsuit(mainsuit);
		}
		if(!StringUtil.isEmpty(illness)){
			// 现病史
			medicalRecordModel.setIllness(illness);
		}

		// 既往史
		if(!StringUtil.isEmpty(previous)){
			medicalRecordModel.setPrevious(previous);
		}
		// 个人史
		if(!StringUtil.isEmpty(personal)){
			medicalRecordModel.setPersonal(personal);
		}
		// 需要关注的躯体状况
		if(!StringUtil.isEmpty(somastate)){
			medicalRecordModel.setSomastate(somastate);
		}

		// 辅助检查
		if(!StringUtil.isEmpty(assistCheck)){
			medicalRecordModel.setAssistCheck(assistCheck);
		}
		if(!StringUtil.isEmpty(assistCheckImage)){
			medicalRecordModel.setAssistCheckImage(assistCheckImage);
		}

		// 精神检查
		if(!StringUtil.isEmpty(spiritCheck)){
			medicalRecordModel.setSpiritCheck(spiritCheck);
		}
		// 异常结果
		if(!StringUtil.isEmpty(abnormal)){
			medicalRecordModel.setAbnormal(abnormal);
		}
		// 诊断
		if(!StringUtil.isEmpty(diagnosis)){
			medicalRecordModel.setDiagnosis(diagnosis);
		}
		if(!StringUtil.isEmpty(diagnosisImage)){
			medicalRecordModel.setDiagnosisImage(diagnosisImage);
		}
		// 病程 治疗经过
		if(!StringUtil.isEmpty(diagnosisImage)){
			medicalRecordModel.setProcess(diagnosisImage);
		}
		if(!StringUtil.isEmpty(cureCourseImage)){
			medicalRecordModel.setCureCourseImage(cureCourseImage);
		}
		// 发作次数
		int num = 0;
		if (!StringUtil.isEmpty(attackNum)) {
			num = Integer.parseInt(attackNum);
		}
		medicalRecordModel.setAttackNum(num);

		// 共病
		if(!StringUtil.isEmpty(comorbidity)){
			medicalRecordModel.setComorbidity(comorbidity);
		}
		// 合并症
		if(!StringUtil.isEmpty(complication)){
			medicalRecordModel.setComplication(complication);
		}
		// 精神科量表测评
		if(!StringUtil.isEmpty(scaleAppraisal)){
			medicalRecordModel.setScaleAppraisal(scaleAppraisal);
		}
		if(!StringUtil.isEmpty(preAssistCheck)){
			medicalRecordModel.setPreAssistCheck(preAssistCheck);
		}
		if(!StringUtil.isEmpty(preAssistCheckImage)){
			medicalRecordModel.setPreAssistCheckImage(preAssistCheckImage);
		}
		if(!StringUtil.isEmpty(visitPreceptUuid)){
			medicalRecordModel.setVisitPreceptUuid(visitPreceptUuid);
		}
		// 照片
		String img1 = jsonObject.getString("img1");
		String img2 = jsonObject.getString("img2");
		String img3 = jsonObject.getString("img3");
		String img4 = jsonObject.getString("img4");
		String img5 = jsonObject.getString("img5");
		if (!StringUtil.isEmpty(img1)) {
			medicalRecordModel.setImage1(img1);
		}

		if (!StringUtil.isEmpty(img2)) {
			medicalRecordModel.setImage2(img2);
		}

		if (!StringUtil.isEmpty(img3)) {
			medicalRecordModel.setImage3(img3);
		}

		if (!StringUtil.isEmpty(img4)) {
			medicalRecordModel.setImage4(img4);
		}
		if (!StringUtil.isEmpty(img5)) {
			medicalRecordModel.setImage5(img5);
		}

		// 更新
		medicalRecordService.update(medicalRecordModel);
		jsonMap.put("medicalRecordUuid", medicalRecordUuid);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 添加治疗方案(重要医嘱)
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addDoctorAdviceModel", method = RequestMethod.POST)
	public String addDoctorAdviceModel(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("type") String type) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("textml;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut, new String[] { "callback,true" });
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 获取回调的名
		Object callback = map.get("callback");

		String medicineUuid = request.getParameter("medicineUuid");
		String visitRecordUuid = request.getParameter("visitRecordUuid");
		String medicalRecordUuid = request.getParameter("medicalRecordUuid");
		// 药品信息不能为空
		if (!StringUtil.isEmpty(type) && "0".equals(type)) {
			if (StringUtil.isEmpty(medicineUuid)) {
				HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "药品信息不能为空"), callback);
				return null;
			}
		}
		VisitRecordModel vr = null;
		if(!StringUtil.isEmpty(visitRecordUuid)){
			vr = visitRecordService.getByUuid(visitRecordUuid);
		}
		MedicalRecordModel mr =null;
		if(!StringUtil.isEmpty(medicalRecordUuid)){

		}

		// 单量
		String dosage = request.getParameter("dosage");
		// 斌率
		String frequency = request.getParameter("frequency");
		// 用法
		String directions = request.getParameter("directions");
		// 单位
		String unit = request.getParameter("unit");
		// 其他治疗说明
		String cureNote = request.getParameter("cureNote");
		DoctorAdviceModel dam = new DoctorAdviceModel();
		dam.setType(type);
		dam.setVisitRecordUuid(visitRecordUuid);
		dam.setMedicineUuid(medicineUuid);
		// 类型 1：其他治疗 0：药物治疗
		if ("1".equals(type)) {
			dam.setCureNote(cureNote);
		} else {
			dam.setDosage(dosage);
			dam.setFrequency(frequency);
			dam.setDirections(directions);
			dam.setUnit(unit);
		}
		String customerUuid ="";
		String doctorUuid ="";
		if(vr !=null ){
			 customerUuid = vr.getCustomerUuid();
			 doctorUuid = vr.getServiceStaffUuid();
		}

		dam.setCreateTime(DateFormatHelper.getNowTimeStr());
		dam.setServiceStaffUuid(doctorUuid);
		dam.setCustomerUuid(customerUuid);
		dam.setMedicineUuid(medicineUuid);
		doctorAdviceService.create(dam);
		if (null != dam) {
			String doctorAdviceUuid = dam.getUuid();
	        innerMessageService.saveInnerMessageAndPush(doctorUuid, customerUuid,
                      MessageHelper.getMessage("doctorAdviceMesg.showmessage.newAdd"),
                      InnerMessageModel.ACCOUNT_TYPE_STORE, InnerMessageModel.ACCOUNT_TYPE_CUSTOMER,
                      PushConst.push_client_customer, customerUuid, doctorAdviceUuid, InnerMessageTypeEnum.DOCTORADVICE.getValue());

			jsonMap.put("doctorAdviceUuid", doctorAdviceUuid);
		}

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;

	}

	/**
	 * 修改治疗方案(重要医嘱)
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/updateDoctorAdvice", method = RequestMethod.POST)
	public String updateDoctorAdvice(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("cureNote") String cureNote,
			@RequestParam("lists") String lists) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("textml;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut, new String[] { "callback,true" });
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		List list = JSONArray.parseArray(lists, Map.class);
		// 获取回调的名
		Object callback = map.get("callback");
		//随访记录id
		String applyUuid =request.getParameter("applyUuid");
		VisitRecordModel vis = null;
		String customerUuid="";
		String doctorId="";
		if(!StringUtil.isEmpty(applyUuid)){
			vis = visitRecordService.getByUuid(applyUuid);
			if(vis !=null){
				customerUuid = vis.getCustomerUuid();
				doctorId = vis.getServiceStaffUuid();
			}
		}
		String doctorUuid ="";
		DoctorAdviceModel dm = null;
		String doctorAdviceUuid ="";
		if(lists !=null && list.size()>0){
			//先获取医嘱 设置为过期
			List<DoctorAdviceModel> dcs= doctorAdviceService.getDoctorAdivceListByCustomerUuidAndDoctorUuid(doctorId, customerUuid);
			if(dcs !=null && dcs.size()>0){
				for(DoctorAdviceModel da:dcs){
					da.setState("1");
					doctorAdviceService.update(da);
				}
			}

			for(int i=0;i<list.size();i++){
				Map<String, String> obj = (Map<String, String>) list.get(i);
				String type = obj.get("type");
				String medicineUuid = obj.get("medicineUuid");
				String directions = obj.get("directions");
				doctorUuid = obj.get("doctorUuid");
				String dosage = obj.get("dosage");
				String frequency = obj.get("frequency");
				String unit = obj.get("unit");
				doctorAdviceUuid = obj.get("doctorAdviceUuid");

				dm = new DoctorAdviceModel();
				dm.setType(type);
				dm.setVisitRecordUuid(applyUuid);
				dm.setCureNote(cureNote);
				dm.setMedicineUuid(medicineUuid);
				dm.setDirections(directions);
				dm.setDosage(dosage);
				dm.setFrequency(frequency);
				dm.setUnit(unit);
				dm.setState("0");
				dm.setCreateTime(DateFormatHelper.getNowTimeStr());
				dm.setServiceStaffUuid(doctorUuid);
				dm.setCustomerUuid(customerUuid);
				doctorAdviceService.create(dm);
			}
		}
		//XP消息推送
        if (null != dm) {
                 customerUuid = dm.getCustomerUuid();
                innerMessageService.saveInnerMessageAndPush(doctorUuid, customerUuid,
                                MessageHelper.getMessage("doctorAdviceMesg.showmessage.newAdd"),
                                InnerMessageModel.ACCOUNT_TYPE_STORE, InnerMessageModel.ACCOUNT_TYPE_CUSTOMER,
                                PushConst.push_client_customer, customerUuid, doctorAdviceUuid, InnerMessageTypeEnum.DOCTORADVICE.getValue());
        }
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;

	}



	/**
	 * 获取所有私人套餐接口
	 *
	 * @author xp
	 */
	@RequestMapping(value = "/getAllPackage", method = RequestMethod.GET)
	public String getAllPackage(HttpServletRequest request, HttpServletResponse response) {

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

		// 获取所有的私人套餐
		List<PackageManagementModel> list = packageManagementService.getAll();

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
  // TODO 		医生群发消息给患者
	/**
	 * 医生群发患者 12/19
	 * @param response response
	 * @return string
	 */
	@RequestMapping(value = "1.0/addInnerMessage", method = RequestMethod.POST)
	public String addInnerMessage(HttpServletResponse response,
								  @RequestParam("content") String content,
								  @RequestParam("doctorUuid") String doctorUuid,
								  @RequestParam("customerUuids") List<String> customerUuids) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 判断id不能为空
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, "医生id不能为空"));
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		for (String customerUuid : customerUuids) {
			innerMessageService.saveInnerMessageAndPush(doctorUuid, customerUuid, content, InnerMessageModel.ACCOUNT_TYPE_STORE, InnerMessageModel.ACCOUNT_TYPE_CUSTOMER, PushConst.push_client_customer, doctorUuid, "", "");
		}
		HttpServletUtils.outJson(response, jsonMap);
		return null;
	}
//	/**
//	 * 医生群发患者 12/19
//	 *
//	 * @author szr
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping(value = "/addInnerMessage", method = RequestMethod.GET)
//	public String addInnerMessage(HttpServletRequest request, HttpServletResponse response,
//			@RequestParam("content") String content, @RequestParam("doctorUuid") String doctorUuid,
//			@RequestParam("customerUuids") List<String> customerUuids) {
//		// 设置返回数据编码
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
//		// 获取参数值并且进行非空判断
//		boolean breakOut = false;// return 标志
//		// 标志 以及必需要传的参数
//		Map<String, String> map = getParam(request, response, breakOut,
//				new String[] { "callback,true", "doctorUuid,true" });
//
//		if ("true".equals(map.get("breakOut"))) {
//			return null;
//		}
//		Object callback = map.get("callback");
//
//		// 判断id不能为空
//		if (StringUtil.isEmpty(doctorUuid)) {
//			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生id不能为空"), callback);
//			return null;
//		}
//
//		// 设置返回信息
//		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
//
//		for (String customerUuid : customerUuids) {
//			innerMessageService.saveInnerMessageAndPush(doctorUuid, customerUuid, content, InnerMessageModel.ACCOUNT_TYPE_STORE, InnerMessageModel.ACCOUNT_TYPE_CUSTOMER, PushConst.push_client_customer, doctorUuid, "", "");
//		}
//
//		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
//		return null;
//	}

	/**
	 *
	 * @Description: (获取病情记录的列表接口)
	 * @author XP
	 * @param request
	 * @param response
	 * @param visitRecordUuid
	 * @return
	 * @date 2016-1-13 上午10:32:32
	 */
	@RequestMapping(value = "/getIllnessRecordByVisitRecordUuid", method = RequestMethod.GET)
	public String getIllnessRecordByvisitRecordUuid(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("visitRecordUuid") String visitRecordUuid) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "visitRecordUuid,true" }); // 标志
		// 以及必需要传的参数
		Object callback = map.get("callback");

		// 判断adUuid是否为空
		if (StringUtil.isEmpty(visitRecordUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "visitRecordUuid不能为空"),
					callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 得到病情记录表
		List<IllnessRecordModel> list = illnessRecordService.getByVisitRecordUuid(visitRecordUuid);

		// 定义返回List
		List<Map<String, Object>> relist = new ArrayList<Map<String, Object>>();
		if (list != null && list.size() > 0) {
			for (IllnessRecordModel pm : list) {
				Map<String, Object> save = new HashMap<String, Object>();
				// 创建时间
				save.put("createTime", pm.getCreateTime());
				// 其他症状
				save.put("note", pm.getNote());
				// 病情变化描述 1 代表痊愈 2代表好转 3代表无效 4 代表其他
				save.put("previons", pm.getPrevions());
				relist.add(save);
			}
		}

		jsonMap.put("relist", relist);// 消息字段

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 *
	 * @Description: (获取服药记录列表)
	 * @author XP
	 * @param request
	 * @param response
	 * @param visitRecordUuid
	 * @return
	 * @date 2016-1-13 上午10:29:59
	 */
	@RequestMapping(value = "/getDoctorAdviceModelByVisitRecordUuid", method = RequestMethod.GET)
	public String getDoctorAdviceModelByVisitRecordUuid(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("visitRecordUuid") String visitRecordUuid) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "visitRecordUuid,true" }); // 标志
		// 以及必需要传的参数
		Object callback = map.get("callback");

		// 判断adUuid是否为空
		if (StringUtil.isEmpty(visitRecordUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "visitRecordUuid不能为空"),
					callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 根据随访表单uuid查到患者病歷列表
		List<DoctorAdviceModel> list = doctorAdviceService.getByVisitRecordUuid(visitRecordUuid);

		// 定义返回List
		List<Map<String, Object>> relist = new ArrayList<Map<String, Object>>();
		if (list != null && list.size() > 0) {
			for (DoctorAdviceModel pm : list) {
				Map<String, Object> save = new HashMap<String, Object>();
				// 创建时间
				save.put("createTime", pm.getCreateTime());
				// 药物名称
				save.put("medicineName", pm.getMedicineUuid());
				// 单量
				save.put("dosage", pm.getDosage());
				// 次数
				save.put("frequency", pm.getFrequency());
				// 用法
				save.put("directions", pm.getDirections());
				// 单位
				save.put("unit", pm.getUnit());
				relist.add(save);
			}
		}

		jsonMap.put("relist", relist);// 消息字段

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 *
	 * 通过患者ID获得患者手机号 12/27
	 *
	 * @author szr
	 * @param request
	 * @param response
	 * @param customerUuid
	 * @return
	 */
	@RequestMapping(value = "/getMobileByCustomerUuid", method = RequestMethod.GET)
	public String getMobileByCustomerUuid(HttpServletRequest request, HttpServletResponse response,
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

		// 判断Uuid是否为空
		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "customerUuid不能为空"),
					callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 根据患者uuid得到患者手机号
		String mobile = customerService.getCustomerMobileByUuid(customerUuid);
		// 判断手机号是否为空
		if (StringUtil.isEmpty(mobile)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "该患者id下无手机号"), callback);
			return null;
		}

		jsonMap.put("mobile", mobile);// 消息字段

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 *
	 * 修改患者申请的预约加号的状态 2016/01/21
	 *
	 * @author szr
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/updateConsultRecordPlusState", method = RequestMethod.GET)
	public String updateConsultRecordPlusState(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("consultRecordUuid") String consultRecordUuid, @RequestParam("state") String state) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "consultRecordUuid,true", "state,true" }); // 标志
		// 以及必需要传的参数
		Object callback = map.get("callback");

		// 判断Uuid是否为空
		if (StringUtil.isEmpty(consultRecordUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "consultRecordUuid不能为空"),
					callback);
			return null;
		}
		// 得到consultRecord
		ConsultRecordModel consultRecordModel = consultRecordService.getByUuid(consultRecordUuid);
		if (consultRecordModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "consultRecordUuid下无信息"),
					callback);
			return null;
		}
		String  customerUuid= consultRecordModel.getCustomerUuid();
		String  doctorUuid= consultRecordModel.getDoctorUuid();
		String doctorName= consultRecordModel.getDocoterName();

		// 0: 未审核 1:审核 2:审核未通过
		consultRecordModel.setDealState(state);
		// 更新
		consultRecordService.update(consultRecordModel);

		if (!StringUtil.isEmpty(customerUuid) && !StringUtil.isEmpty(doctorUuid)) {
			String content = doctorName ;
			if("1".equals(state)){
				content=content+ "已经同意了您的预约加号";
			}else{
				content=content+ "已经拒绝了您的预约加号";
			}
			innerMessageService.saveInnerMessageAndPush(doctorUuid, customerUuid, content,
					InnerMessageModel.ACCOUNT_TYPE_STORE, InnerMessageModel.ACCOUNT_TYPE_CUSTOMER,
					PushConst.push_client_customer, "consultRecordUuid", consultRecordModel.getUuid(), InnerMessageTypeEnum.SUBSCRIBE.getValue());
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 *
	 * 获取我的患者分组展示数量 2016/01/29
	 *
	 * @author xl
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getCustomrGropNums", method = RequestMethod.GET)
	public String getCustomrGropNums(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("doctorUuid") String doctorUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "doctorUuid,true", "state,true" }); // 标志
		// 以及必需要传的参数
		Object callback = map.get("callback");
		int visitNum=0;
		int telNum=0;
		int picNum=0;
		// 判断Uuid是否为空
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生编号不能为空"),
					callback);
			return null;
		}

		List<VisitRecordModel> list=visitRecordService.getAllVisitRecordsByDoctorId(doctorUuid);
		if(list !=null &&list.size()>0){
			visitNum = list.size();
		}
		List<String> onlines =consultRecordService.getByDoctorUuid(doctorUuid,ConsultRecordModel.TYPE_ONLINE);
		if(onlines !=null && onlines.size()>0){
			picNum = onlines.size();
		}
		List<String> tels =consultRecordService.getByDoctorUuid(doctorUuid, ConsultRecordModel.TYPE_TEL);
		if(tels !=null && tels.size()>0){
			telNum = tels.size();
		}

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		jsonMap.put("visitNum", visitNum);
		jsonMap.put("telNum", telNum);
		jsonMap.put("picNum", picNum);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);


		return null;
	}


	/**
	 * 获取我的患者分组展示数量 2016/01/29
	 *
	 * @param request
	 * @param response
	 * @return
	 * @author xl
	 */
	@RequestMapping(value = "/getPatientGrops", method = RequestMethod.GET)
	public String getCustomrGrops(HttpServletRequest request, HttpServletResponse response,
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
		// 判断Uuid是否为空
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生编号不能为空"),
					callback);
			return null;
		}

		
		
		
		//随访患者		
	/*	List<Map<String,Object>> visitPrecptList = visitPrecptSerivce.getVisitpreceptByDoctorid(VisitPreceptStatus.DELFLAG_NORMAL, 
				VisitPreceptStatus.RECOMMEND_CUSTOMER, doctorUuid);*/
		
		List<CustomerDoctorReleModel> customerDoctorReleList = customerDoctorReleService
				.getByDoctorUuid(doctorUuid);
		

		// 定义返回List
		List<Map<String, Object>> visitlist = new ArrayList<Map<String, Object>>();
		if (customerDoctorReleList != null && customerDoctorReleList.size() > 0) {
			for (CustomerDoctorReleModel customerDoctorRele : customerDoctorReleList) {
				Map<String, Object> save = new HashMap<String, Object>();
				String customerUuid =(String) customerDoctorRele.getCustomerUuid();
				CustomerInfoModel customerInfoModel = customerInfoService
						.getCustomerInfoModelByCustomerUuid(customerUuid);
				if (customerInfoModel == null) {
					// 患者id
					save.put("customerUuid", customerUuid);
					// 患者姓名
					save.put("customerName", "");
					// 患者性别
					save.put("sex", "");
					// 患者年龄
					save.put("age", "");
					// 患者时间
					save.put("time", "");
					// 患者头像
					save.put("customerImg", "");
					// 患者信息？
					save.put("customerMessage", "");
					visitlist.add(save);
				} else {
					// 患者id
					save.put("customerUuid", customerUuid);
					// 患者姓名
					save.put("customerName", customerInfoModel.getRealName());
					// 患者性别
					save.put("sex", customerInfoModel.getSex());
					// 患者年龄
					save.put("age", customerInfoModel.getAge());
					// 患者时间
					save.put("time", customerInfoModel.getOpeTime());
					// 患者头像
					save.put("customerImg", customerInfoModel.getImgUrl());
					// 患者病情描述
					save.put("customerMessage", customerInfoModel.getIllnessDescription());
					save.put("customerUuid", customerInfoModel.getCustomerUuid());
					visitlist.add(save);
				}
			}
		}


		//在线咨询
		List<String> onlines = consultRecordService.getByDoctorUuid(doctorUuid, ConsultRecordModel.TYPE_ONLINE);
		// 定义返回List
		List onlinelist = new ArrayList();
		if (onlines != null && onlines.size() > 0) {
			for (String customerUuid : onlines) {
				Map<String, Object> save = new HashMap<String, Object>();
				CustomerInfoModel customerInfoModel = customerInfoService
						.getCustomerInfoModelByCustomerUuid(customerUuid);
				if (customerInfoModel == null) {
					// 患者id
					save.put("customerUuid", customerUuid);
					// 患者姓名
					save.put("customerName", "");
					// 患者性别
					save.put("sex", "");
					// 患者年龄
					save.put("age", "");
					// 患者时间
					save.put("time", "");
					// 患者头像
					save.put("customerImg", "");
					// 患者信息？
					save.put("customerMessage", "");
					onlinelist.add(save);
				} else {
					// 患者id
					save.put("customerUuid", customerUuid);
					// 患者姓名
					save.put("customerName", customerInfoModel.getRealName());
					// 患者性别
					save.put("sex", customerInfoModel.getSex());
					// 患者年龄
					save.put("age", customerInfoModel.getAge());
					// 患者时间
					save.put("time", customerInfoModel.getOpeTime());
					// 患者头像
					save.put("customerImg", customerInfoModel.getImgUrl());
					// 患者病情描述
					save.put("customerMessage", customerInfoModel.getIllnessDescription());
					save.put("customerUuid", customerInfoModel.getCustomerUuid());
					onlinelist.add(save);
				}
			}
		}
		//预约加号
		List<String> appointUuids = consultRecordService.getByDoctorUuid(doctorUuid, ConsultRecordModel.TYPE_ORDER);
		// 定义返回List
		List appointlist = new ArrayList();
		if (appointUuids != null && appointUuids.size() > 0) {
			for (String customerUuid : appointUuids) {
				Map<String, Object> save = new HashMap<String, Object>();
				CustomerInfoModel customerInfoModel = customerInfoService
						.getCustomerInfoModelByCustomerUuid(customerUuid);
				if (customerInfoModel == null) {
					// 患者id
					save.put("customerUuid", customerUuid);
					// 患者姓名
					save.put("customerName", "");
					// 患者性别
					save.put("sex", "");
					// 患者年龄
					save.put("age", "");
					// 患者时间
					save.put("time", "");
					// 患者头像
					save.put("customerImg", "");
					// 患者信息？
					save.put("customerMessage", "");
					appointlist.add(save);
				} else {
					// 患者id
					save.put("customerUuid", customerUuid);
					// 患者姓名
					save.put("customerName", customerInfoModel.getRealName());
					// 患者性别
					save.put("sex", customerInfoModel.getSex());
					// 患者年龄
					save.put("age", customerInfoModel.getAge());
					// 患者时间
					save.put("time", customerInfoModel.getOpeTime());
					// 患者头像
					save.put("customerImg", customerInfoModel.getImgUrl());
					// 患者病情描述
					save.put("customerMessage", customerInfoModel.getIllnessDescription());
					save.put("customerUuid", customerInfoModel.getCustomerUuid());
					appointlist.add(save);
				}
			}
		}

		//电话咨询
		List<String> orderCtuuids = orderMainService.getCustomerUuids("", doctorUuid, 0, 0, "1");
		// 定义返回List
		List orderlist = new ArrayList();
		if (orderCtuuids != null && orderCtuuids.size() > 0) {
			for (String customerUuid : orderCtuuids) {
				Map<String, Object> save = new HashMap<String, Object>();
				CustomerInfoModel customerInfoModel = customerInfoService
						.getCustomerInfoModelByCustomerUuid(customerUuid);
				if (customerInfoModel == null) {
					// 患者id
					save.put("customerUuid", customerUuid);
					// 患者姓名
					save.put("customerName", "");
					// 患者性别
					save.put("sex", "");
					// 患者年龄
					save.put("age", "");
					// 患者时间
					save.put("time", "");
					// 患者头像
					save.put("customerImg", "");
					// 患者信息？
					save.put("customerMessage", "");
					orderlist.add(save);
				} else {
					// 患者id
					save.put("customerUuid", customerUuid);
					// 患者姓名
					save.put("customerName", customerInfoModel.getRealName());
					// 患者性别
					save.put("sex", customerInfoModel.getSex());
					// 患者年龄
					save.put("age", customerInfoModel.getAge());
					// 患者时间
					save.put("time", customerInfoModel.getOpeTime());
					// 患者头像
					save.put("customerImg", customerInfoModel.getImgUrl());
					// 患者病情描述
					save.put("customerMessage", customerInfoModel.getIllnessDescription());
					save.put("customerUuid", customerInfoModel.getCustomerUuid());
					orderlist.add(save);
				}
			}
		}

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		jsonMap.put("visitlist", visitlist);
		jsonMap.put("onlinelist", onlinelist);
		jsonMap.put("appointlist", appointlist);
		jsonMap.put("orderlist", orderlist);

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;
	}

  /**
   *
   * @Description: (根据医生的id和类型获取患者的列表列表)
   * @author XP
   * @param request
   * @param response
   * @param doctorUuid
   * @return
   * @date 2016-1-13 上午10:29:59
   */
  @RequestMapping(value = "/getPatientListByDoctorUuid", method = RequestMethod.GET)
  public String getPatientListByDoctorUuid(HttpServletRequest request, HttpServletResponse response,
                  @RequestParam("doctorUuid") String doctorUuid,
                  @RequestParam("consultType") String consultType) {

        // 设置返回数据编码
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        // 获取参数值并且进行非空判断
        boolean breakOut = false;// return 标志
        Map<String, String> map = getParam(request, response, breakOut,
                        new String[] { "callback,true", "doctorUuid,true" , "consultType,true"}); // 0在线咨询即图文咨询 1电话咨询
        // 以及必需要传的参数
        Object callback = map.get("callback");

        // 判断Uuid是否为空
        if (StringUtil.isEmpty(doctorUuid)) {
                HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "doctorUuid不能为空"),
                                callback);
                return null;
        }
        // 设置返回信息
        Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
        // 根据医生的id和咨询的类型获取患者的列表
       List<String> list = consultRecordService.getByDoctorUuid(doctorUuid, consultType);
        // 定义返回List
        List<Map<String, Object>> relist = new ArrayList<Map<String, Object>>();
        if (list != null && list.size() > 0) {
            for (String uuid : list) {
                Map<String, Object> save = new HashMap<String, Object>();
                if(!StringUtil.isEmpty(uuid)){
                    CustomerInfoModel cm = customerInfoService.getCustomerInfoModelByCustomerUuid(uuid);
                    if(null != cm ){
                       save.put("realName", cm.getRealName());
                       save.put("sex", cm.getSex());
                       save.put("age", cm.getSex());
                       save.put("illnessDescription", cm.getIllnessDescription());
                       save.put("birthday", cm.getBirthday());
                       save.put("uuid", uuid);
                       relist.add(save);
                    }
                }
               }
        }
        jsonMap.put("relist", relist);// 消息字段
        HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
        return null;
  }

}