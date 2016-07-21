package com.hxq.mobile.doctor.visit.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.innermessage.service.InnerMessageService;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageModel;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageTypeEnum;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.HttpServletUtils;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.SysConst;
import com.aebiz.b2b2c.customermgr.mobile.web.push.PushConst;
import com.aebiz.b2b2c.servicestaff.servicestaffcomb.service.ServicestaffcombService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hxq.mobile.common.service.ServiceStaffService;
import com.hxq.mobile.doctor.visit.service.DoctorAdviceService;
import com.hxq.mobile.doctor.visit.service.DoctorVisitMobileService;
import com.hxq.mobile.doctor.visit.service.HealthGuideService;
import com.hxq.mobile.doctor.visit.service.VisitPreceptService;
import com.hxq.mobile.doctor.visit.service.VisitRecordExtendService;
import com.hxq.mobile.entity.common.ServiceStaff;
import com.hxq.mobile.entity.visit.DoctorAdvice;
import com.hxq.mobile.entity.visit.HealthGuide;
import com.hxq.mobile.entity.visit.VisitPrecept;
import com.hxq.mobile.entity.visit.VisitPreceptExtend;
import com.hxq.mobile.entity.weixin.CsZySubject;
import com.hxq.mobile.util.api.ApiCode;
import com.hxq.mobile.util.api.ApiResult;
import com.hxq.mobile.weixin.service.CsZySubjectService;
import com.wxcommon.repository.SimpleBean2DBHelper;
import com.wxcommon.util.IdentityHelper;
import com.wxcommon.util.ObjectUtils;

/**
 * Created by Alice on 2016/4/25 0025.
 * 医生端 随访controller
 */
@Controller("com.hxq.mobile.doctor.visit.controller.VisitPreceptController")
@RequestMapping("/app/pub/doctor")
public class VisitPreceptController {
    private Logger log = LoggerFactory.getLogger(VisitPreceptController.class);
    @Autowired
    @Resource(name = "com.hxq.mobile.doctor.visit.service.DoctorVisitMobileService")
    private DoctorVisitMobileService doctorVisitMobileService;

    //随访方案
    @Resource(name = "com.hxq.mobile.doctor.visit.service.VisitPreceptService")
    private VisitPreceptService visitPreceptService;
    
    //重要医嘱
    @Autowired
    @Resource(name = "com.hxq.mobile.doctor.visit.service.DoctorAdviceService")
    private DoctorAdviceService doctorAdviceService;
    
    // 医生端随访记录
    @Autowired
    @Resource(name = "com.hxq.mobile.doctor.visit.service.VisitRecordExtendService")
    private VisitRecordExtendService visitRecordExtendService;
    
    //健康指导
    @Autowired
    @Resource(name = "com.hxq.mobile.doctor.visit.service.HealthGuideService")
    private HealthGuideService healthGuideService;
    
	@Resource(name = "com.hxq.mobile.weixin.service.csZySubjectService")
	private CsZySubjectService csZySubjectService;
	
	/* 消息service */
	@Autowired
	private InnerMessageService innerMessageService;

	@Autowired
	private ServicestaffcombService servicestaffcombService;

    @Resource(name = "com.hxq.mobile.common.service.ServiceStaffService")
    private ServiceStaffService servicestaffService;

    /**
     * 随访方案管理-查询推荐方案列表
     * @param doctorUuid doctorUuid
     * @return ApiResult
     */
    @RequestMapping(value = "/2.0/getRecommendVisitpreceptByDoctorid", method = RequestMethod.GET)
    public @ResponseBody ApiResult getRecommendVisitpreceptByDoctorid_v2(@RequestParam("doctorUuid") String doctorUuid) {
        // 判断传入参数
        if (ObjectUtils.isEmpty(doctorUuid)) {
            return ApiResult.error(ApiCode.BAD_REQUEST, "医生ID不能为空");
        }
        try {
            return doctorVisitMobileService.getRecommendVisitpreceptByDoctorid_v2(doctorUuid);
        } catch (Exception e) {
            log.error(this.getClass().getName() + e.getMessage());
            return ApiResult.error(ApiCode.SERVER_ERROR, null);
        }
    }


    /**
     * 随访方案管理-查询我的方案列表
     * @param doctorUuid doctorUuid
     * @return ApiResult
     */
    @RequestMapping(value = "/2.0/getMyVisitPreceptList", method = RequestMethod.GET)
    public @ResponseBody ApiResult getMyVisitPreceptList_v2(@RequestParam("doctorUuid") String doctorUuid) {
        // 判断传入参数
        if (ObjectUtils.isEmpty(doctorUuid)) {
            return ApiResult.error(ApiCode.BAD_REQUEST, "医生ID不能为空");
        }
        try {
            return doctorVisitMobileService.getMyVisitPreceptList_v2(doctorUuid);
        } catch (Exception e) {
            log.error(this.getClass().getName() + e.getMessage());
            return ApiResult.error(ApiCode.SERVER_ERROR, null);
        }
    }
    
    
    /**
	 * 查看随访方案的详细信息
	 *
	 * @param response
	 *            response
	 * @param visitUuid
	 *            visitUuid
	 * @return string
	 */
	@RequestMapping(value = "/2.0/visitPreceptDetail", method = RequestMethod.GET)
	public @ResponseBody ApiResult visitPreceptDetail(HttpServletResponse response, @RequestParam("doctorUuid") String doctorUuid,
			@RequestParam("visitUuid") String visitUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 判断用户是否存在
		if (StringUtil.isEmpty(doctorUuid)) {
		 return ApiResult.error(ApiCode.BAD_REQUEST, "医生ID不能为空");
		
		}
		if (StringUtil.isEmpty(visitUuid)) {
			 return ApiResult.error(ApiCode.BAD_REQUEST, "请指定随访方案id");
		}
	/*	// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);*/
		
		Map<String, Object> jsonMap = new HashMap<>();

		// 根据随访方案的的uuid查询出相对应的随访方案对象
		Map<String, Object>	 visitPreceptMap =	visitPreceptService.getByUuid(visitUuid);
		// 返回給app端的参数
		if (visitPreceptMap != null) {
			jsonMap.put("visitUuid", visitUuid);
			jsonMap.put("preceptName", visitPreceptMap.get("preceptName"));
			jsonMap.put("drugTherapy", visitPreceptMap.get("drugTherapy"));
			jsonMap.put("sideEffects", visitPreceptMap.get("sideEffects"));
			jsonMap.put("period", visitPreceptMap.get("period"));
			jsonMap.put("electrocardiogram", visitPreceptMap.get("electrocardiogram"));
			jsonMap.put("bloodRoutine", visitPreceptMap.get("bloodRoutine"));
			jsonMap.put("weight", visitPreceptMap.get("weight"));
			jsonMap.put("hepatic", visitPreceptMap.get("hepatic"));
			String seltTest = (String)visitPreceptMap.get("selfTest");
			if (!ObjectUtils.isEmpty(seltTest)) {
				jsonMap.put("selfTest", this.getList(seltTest));
			}
			String doctorTest = (String)visitPreceptMap.get("doctorTest");
			if (!ObjectUtils.isEmpty(doctorTest)) {
				jsonMap.put("doctorTest", this.getList(doctorTest));
			}
			String delFlag = (String)visitPreceptMap.get("delFlag");
			//delFlag 为空,给设一个默认值1,已完成
			if(ObjectUtils.isEmpty(delFlag)){
				delFlag = "1"; 
			}
			//自评量表周期
			jsonMap.put("selfPeriod", visitPreceptMap.get("selfPeriod"));
			//医评量表周期
			jsonMap.put("doctorPeriod", visitPreceptMap.get("doctorPeriod"));
			
			
			jsonMap.put("delFlag", visitPreceptMap.get("delFlag"));
		} else {
			 return ApiResult.error(ApiCode.BAD_REQUEST, "未能成功的找到随访方案内容，请更换id重试！");
		}

		// 药物信息
		List<Map<String,Object>> doctorAdviceList =(List<Map<String,Object>>)doctorAdviceService.selectListByVisitPreceptAndType(visitUuid,
				"0");
		jsonMap.put("doctorAdvice", doctorAdviceList);

		// 其他方案
		List<Map<String, Object>> preceptExtendMap = visitRecordExtendService.getAllVisitPreceptByPreceptUuid(visitUuid);
		List<Map<String, Object>> preceptExtend = new ArrayList<>();
		if (preceptExtendMap != null && preceptExtendMap.size() > 0) {
			preceptExtend = preceptExtendMap;
		}
		jsonMap.put("otherMap", preceptExtend);

		List<Map<String, Object>> healthGuideMapTemp = healthGuideService
				.getHealthGuideByDoctorUuidAndVisitUuid(doctorUuid, visitUuid);
		List<Map<String, Object>> healthGuideMap = new ArrayList<>();
		if (healthGuideMapTemp != null && healthGuideMapTemp.size() > 0) {
				
			healthGuideMap = healthGuideMapTemp;
		}
		jsonMap.put("healthGuide", healthGuideMap);

		return ApiResult.right(jsonMap);
	}

	
	/**
	 * 医生添加方案接口
	 *
	 * @param request  request
	 * @param response response
	 * @param doctorUuid  医生id
	 * @param preceptName 方案名称
	 * @param drugTherapy 药物不良反应处理
	 * @param sideEffects 其他治疗
	 * @param doctorAdvice 药物信息
	 * @return string
	 */

	@RequestMapping(value = "/2.0/addVisitPrecept", method = RequestMethod.POST)
	public @ResponseBody ApiResult addVisitPrecept(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("doctorUuid") String doctorUuid, @RequestParam("preceptName") String preceptName,
			@RequestParam("drugTherapy") String drugTherapy, @RequestParam("sideEffects") String sideEffects,
			@RequestParam("doctorAdvice") String doctorAdvice,@RequestParam("delFlag")String delFlag) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		if (StringUtil.isEmpty(doctorUuid) && StringUtil.isEmpty(doctorUuid)) {
			 return ApiResult.error(ApiCode.BAD_REQUEST, "医生ID不能为空");
		}
		//其他自定义随访周期
		String otherMap = request.getParameter("otherMap");
		
		VisitPrecept vpm = new VisitPrecept();
		// 随访周期
		vpm.setPeriod(Integer.valueOf(request.getParameter("period")));
		// 心电图检查周期
		vpm.setElectrocardiogram(Integer.valueOf(request.getParameter("electrocardiogram")));
		// 血常规周期
		vpm.setHepatic(Integer.valueOf(request.getParameter("hepatic")));
		// 肝功能周期
		vpm.setBloodRoutine(Integer.valueOf(request.getParameter("bloodRoutine")));
		// 体重功能周期
		vpm.setWeight(Integer.valueOf(request.getParameter("weight")));
		vpm.setServiceStaffUuid(doctorUuid);
		vpm.setVisitUuid(servicestaffcombService.createDoctorNo());
		//自评量表
		vpm.setSelfTest(request.getParameter("selfTest"));
		//医评量表
		vpm.setDoctorTest(request.getParameter("doctorTest"));
		//自评量表周期
		if(!ObjectUtils.isEmpty(request.getParameter("selfPeriod"))){
			vpm.setSelfPeriod(Integer.valueOf(request.getParameter("selfPeriod")));
		}
		//医评量表周期
		if(!ObjectUtils.isEmpty(request.getParameter("doctorPeriod"))){
			vpm.setDoctorPeriod(Integer.valueOf(request.getParameter("doctorPeriod")));
		}
				
		//药物不良反应处理
		vpm.setDrugTherapy(drugTherapy);
		//方案名称
		vpm.setPreceptName(preceptName);
		//其他治疗
		vpm.setSideEffects(sideEffects);
		//方案是否完成(0,未完成,1已完成)
		vpm.setDelFlag(delFlag);
		
		vpm.setUuid(IdentityHelper.uuid2());

	//	vpm.setCreateTime(DateFormatHelper.getNowTimeStr());
		vpm.setRecommend("0");
		try {
			visitPreceptService.insert(vpm);
		} catch (Exception e) {
			log.error("create false! method at" + this.getClass().getName());
		}

		//药物名称:medicineUuid,服药时间:directions,与食物关系:food
		//
		//doctorAdvice:[{"medicineUuid":"","directions":"","food":""}]
		//doctorAdvice:[{"frequency": "123","dosage": "40mg","directions":" ","food":"饭前服用","medicineUuid": "药物名称"}, 
	    //{"frequency": "234","dosage": "60mg","medicineUuid": "也是名称"}]
		String visitid = vpm.getUuid();
		// 药物用量
		Gson gson = new Gson();
		List<Map<String, String>> l = gson.fromJson(doctorAdvice, new TypeToken<List<Map<String, String>>>() {
		}.getType());
		if (l!=null && l.size() > 0) {
			for (Map<String, String> map : l) {
				DoctorAdvice dam = new DoctorAdvice();
				//用法：早，中，晚
				dam.setDirections(map.get("directions"));
				//用量(按天)：40，30，1
				dam.setDosage(map.get("dosage"));
				/*时间(天)：1，2，3*/
				dam.setFrequency(map.get("frequency"));
				/*药物单位：mg,ml,粒,袋*/
				dam.setUnit(map.get("unit"));
				/*与食物关系：饭后服用，饭前服用，不与餐同服*/
				dam.setFood(map.get("food"));
				/* 药物id */
				dam.setMedicineUuid(map.get("medicineUuid"));
				dam.setServiceStaffUuid(doctorUuid);
				dam.setOpeTime(DateFormatHelper.getNowTimeStr());
				dam.setVisitRecordUuid(visitid);
				dam.setType("0");
				try {
					doctorAdviceService.insert(dam);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		//其他检查项周期
		List<Map<String, Object>> otherlist = gson.fromJson(otherMap,
				new TypeToken<List<Map<String, Object>>>() {}.getType());
		if (otherlist!= null && otherlist.size() > 0) {
			for (Map<String, Object> map : otherlist) {
				VisitPreceptExtend  vpem = new VisitPreceptExtend();
				vpem.setVisitRecordUuid(visitid);
				vpem.setName(map.get("name").toString());
				vpem.setPeriod(Integer.valueOf(map.get("period").toString()));
				try {
					visitRecordExtendService.insert(vpem);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		/**
		 * 健康小贴士——no clear
		 */
		String healthGuideString = request.getParameter("healthGuide");

		List<Map<String, Object>> healthGuideModellist = gson.fromJson(healthGuideString,
				new TypeToken<List<Map<String, Object>>>() {
				}.getType());
		if (healthGuideModellist!= null && healthGuideModellist.size() > 0) {
			for (Map<String, Object> map : healthGuideModellist) {
				HealthGuide healthGuide = new HealthGuide();
				//医生uuid
				healthGuide.setServiceStaffUuid(doctorUuid);
				//随uuid
				healthGuide.setNotevisitRecordUuid(visitid);
				
				//其他指导 
				healthGuide.setRest(map.get("rest").toString());
				//suggest
				try {
					//推送周期
					String periodStr = map.get("period").toString();
					int period = 0;
					if(!ObjectUtils.isEmpty(periodStr)){
						period = Integer.valueOf(periodStr);
					}
					healthGuide.setPeriod(period);
				} catch (Exception e) {
					e.getMessage();
				}
				
				
				healthGuide.setCreateTime(DateFormatHelper.getNowTimeStr());
				try {
					healthGuideService.insert(healthGuide);
				} catch (Exception e) {
					e.printStackTrace(System.out);
				}
			}
		}

		ServiceStaff ss = null;
		try {
			ss = (ServiceStaff) servicestaffService.select(new ServiceStaff(vpm.getServiceStaffUuid()));
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		String customerUuid = vpm.getCustomerUuid();
		if (!StringUtil.isEmpty(customerUuid) && !StringUtil.isEmpty(ss.getRealName()) && !StringUtil.isEmpty(doctorUuid)) {
			// String content = doctorName + "医生为您选择了新的随访方案";
			String content = ss.getRealName() + MessageHelper.getMessage("addVisitPrecept.showmessage.newAdd");
			innerMessageService.saveInnerMessageAndPush(doctorUuid, customerUuid, content,
					InnerMessageModel.ACCOUNT_TYPE_STORE, InnerMessageModel.ACCOUNT_TYPE_CUSTOMER,
					PushConst.push_client_customer, "doctorUuid", doctorUuid,
					InnerMessageTypeEnum.VISITDOCTOR.getValue());
		}

		return ApiResult.right("操作成功");
	}
	
	
	/**
	 * 医生编辑随访方案接口
	 * @param request  request
	 * @param response response]
	 * @param visitUuid   方案id
	 * @param doctorUuid  医生id
	 * @param preceptName 方案名称
	 * @param drugTherapy 药物不良反应处理
	 * @param sideEffects 其他治疗
	 * @param doctorAdvice 药物信息
	 * @return string
	 */
	
	@RequestMapping(value = "/2.0/editVisitPrecept", method = RequestMethod.POST)
	public @ResponseBody ApiResult editVisitPrecept(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("visitUuid") String visitUuid, @RequestParam("doctorUuid") String doctorUuid,
			@RequestParam("preceptName") String preceptName, @RequestParam("drugTherapy") String drugTherapy,
			@RequestParam("sideEffects") String sideEffects, @RequestParam("doctorAdvice") String doctorAdvice,@RequestParam("delFlag")String delFlag) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		if (StringUtil.isEmpty(doctorUuid) && StringUtil.isEmpty(doctorUuid)) {
			 return ApiResult.error(ApiCode.BAD_REQUEST, "医生ID不能为空");
		}
		
		if (StringUtil.isEmpty(visitUuid) && StringUtil.isEmpty(visitUuid)) {
			 return ApiResult.error(ApiCode.BAD_REQUEST, "随访方案ID不能为空");
		}
		// 设置返回信息
		//Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		try {
			// 随访方案表表单
			if (!StringUtil.isEmpty(visitUuid)) {
				Gson gson = new Gson();
				Map<String,Object> vpmMap = visitPreceptService.getByUuid(visitUuid);
				
				VisitPrecept vpm = SimpleBean2DBHelper.map2Bean(vpmMap, VisitPrecept.class);
				if (vpm != null) {
					
					
					
					vpm.setUuid(visitUuid);
					// 随访周期
					vpm.setPeriod(Integer.valueOf(request.getParameter("period")));
					// 心电图检查周期
					vpm.setElectrocardiogram(Integer.valueOf(request.getParameter("electrocardiogram")));
					// 血常规周期
					vpm.setHepatic(Integer.valueOf(request.getParameter("hepatic")));
					// 肝功能周期
					vpm.setBloodRoutine(Integer.valueOf(request.getParameter("bloodRoutine")));
					// 体重功能周期
					vpm.setWeight(Integer.valueOf(request.getParameter("weight")));
					//自评量表
					vpm.setSelfTest(request.getParameter("selfTest"));
					//医评量表
					vpm.setDoctorTest(request.getParameter("doctorTest"));
					vpm.setServiceStaffUuid(doctorUuid);
					//方案名称
					vpm.setPreceptName(preceptName);
					//药物不良反应处理
					vpm.setDrugTherapy(drugTherapy);
					//其他治疗
					vpm.setSideEffects(sideEffects);
					
					//自评量表周期
					if(!ObjectUtils.isEmpty(request.getParameter("selfPeriod"))){
						vpm.setSelfPeriod(Integer.valueOf(request.getParameter("selfPeriod")));
					}
					//医评量表周期
					if(!ObjectUtils.isEmpty(request.getParameter("doctorPeriod"))){
						vpm.setDoctorPeriod(Integer.valueOf(request.getParameter("doctorPeriod")));
					}
					
					//方案是否完成(0,未完成,1已完成)
					vpm.setDelFlag(delFlag);
			//		vpm.setCreateTime(DateFormatHelper.getNowTimeStr());
					visitPreceptService.update(vpm);

					//如果修改的是患者的方案,就推送到患者手机端, 如果修改的是医生自己的方案,则不推送
					// 保存消息到数据库，并推送到手机终端
					ServiceStaff ss = null;
					try {
						ss = (ServiceStaff) servicestaffService.select(new ServiceStaff(vpm.getServiceStaffUuid()));
					} catch (Exception e) {
						e.printStackTrace(System.out);
					}
					String customerUuid = vpm.getCustomerUuid();
					if (!StringUtil.isEmpty(customerUuid) && !StringUtil.isEmpty(ss.getRealName())
							&& !StringUtil.isEmpty(doctorUuid)) {
						// String content = doctorName + "医生为您选择了新的随访方案";
						String content = ss.getRealName() + MessageHelper.getMessage("addVisitPrecept.showmessage.newAdd");
						innerMessageService.saveInnerMessageAndPush(doctorUuid, customerUuid, content,
								InnerMessageModel.ACCOUNT_TYPE_STORE, InnerMessageModel.ACCOUNT_TYPE_CUSTOMER,
								PushConst.push_client_customer, "doctorUuid", doctorUuid,
								InnerMessageTypeEnum.VISITDOCTOR.getValue());
					}

					//更新其他检查项,如果从otherMap中取出的uuid不为空就添加,否则就更新
//					String uuid = vpm.getUuid();
					String ortherMap = request.getParameter("otherMap");
					List<Map<String, Object>> otherlist = gson.fromJson(ortherMap,
							new TypeToken<List<Map<String, Object>>>() {}.getType());
					if (otherlist != null && otherlist.size() > 0) {
						for (Map<String, Object> map : otherlist) {
							VisitPreceptExtend vpem = new VisitPreceptExtend();
							vpem.setName(map.get("name").toString());
							vpem.setPeriod(Integer.valueOf(map.get("period").toString()));
							//方案id
							vpem.setVisitRecordUuid(visitUuid);

							if (ObjectUtils.isEmpty(map.get("uuid"))) {
								visitRecordExtendService.insert(vpem);
							} else {
								vpem.setUuid(map.get("uuid").toString());
								visitRecordExtendService.update(vpem);
							}
						}
					}
					
					

					//更新药物用量,如果从doctorAdvice中取出的数据中,uuid不为空,不添加,否则就更新
					// 药物用量
					List<Map<String, String>> l = gson.fromJson(doctorAdvice, new TypeToken<List<Map<String, String>>>() {
					}.getType());
					if (l!=null && l.size() > 0) {
						for (Map<String, String> map : l) {
														
							DoctorAdvice dam = new DoctorAdvice();
							//用法：早，中，晚
							dam.setDirections(map.get("directions"));
							//用量(按天)：40，30，1
							dam.setDosage(map.get("dosage"));
							/*时间(天)：1，2，3*/
							dam.setFrequency(map.get("frequency"));
							/*药物单位：mg,ml,粒,袋*/
							dam.setUnit(map.get("unit"));
							/*与食物关系：饭后服用，饭前服用，不与餐同服*/
							dam.setFood(map.get("food"));
							/* 药物id */
							dam.setMedicineUuid(map.get("medicineUuid"));
							dam.setServiceStaffUuid(doctorUuid);
							dam.setOpeTime(DateFormatHelper.getNowTimeStr());
							dam.setVisitRecordUuid(visitUuid);
							dam.setType("0");
							
							
							if (ObjectUtils.isEmpty(map.get("uuid"))) {
								doctorAdviceService.insert(dam);
							} else {
								dam.setUuid(map.get("uuid"));
								doctorAdviceService.update(dam);
							}
						}
					}

					/**
					 * 健康小贴士——no clear
					 */
					String healthGuideString = request.getParameter("healthGuide");

					List<Map<String, Object>> healthGuideModellist = gson.fromJson(healthGuideString,
							new TypeToken<List<Map<String, Object>>>() {
							}.getType());
					if (healthGuideModellist != null && healthGuideModellist.size() > 0) {
						for (Map<String, Object> map : healthGuideModellist) {
							
							HealthGuide healthGuide = new HealthGuide();
							//医生uuid
							healthGuide.setServiceStaffUuid(doctorUuid);
							//随uuid
							healthGuide.setNotevisitRecordUuid(visitUuid);
							//其他指导 
							healthGuide.setRest(map.get("rest").toString());
							//suggest
							try {
								//推送周期
								String periodStr = map.get("period").toString();
								int period = 0;
								if(!ObjectUtils.isEmpty(periodStr)){
									period = Integer.valueOf(periodStr);
								}
								healthGuide.setPeriod(period);
							} catch (Exception e) {
								e.getMessage();
							}
							
							if (ObjectUtils.isEmpty(map.get("uuid"))) {
								healthGuide.setCreateTime(DateFormatHelper.getNowTimeStr());
								healthGuideService.insert(healthGuide);
							} else {
								healthGuide.setUuid(map.get("uuid").toString());
								healthGuideService.update(healthGuide);
							}

						}
					}

					//删除DoctorAdvice 数据
					String doctorAdviceDelete = request.getParameter("doctorAdviceDelete");
					if (!ObjectUtils.isEmpty(doctorAdviceDelete)) {
						for (String s : doctorAdviceDelete.split(",")) {
							if (!ObjectUtils.isEmpty(s)) {
								DoctorAdvice model = new DoctorAdvice();
								model.setUuid(s);
								try {
									doctorAdviceService.delete(model);
								} catch (Exception e) {
									e.getStackTrace();
								}

							}
						}
					}

					//删除VisitPreceptExtend数据
					String otherMapDelete = request.getParameter("otherMapDelete");
					if (!ObjectUtils.isEmpty(otherMapDelete)) {
						for (String s : otherMapDelete.split(",")) {
							if (!ObjectUtils.isEmpty(s)) {
								VisitPreceptExtend model = new VisitPreceptExtend();
								model.setUuid(s);
								try {
									visitRecordExtendService.delete(model);
								} catch (Exception e) {
									e.getStackTrace();
								}
							}
						}
					}

					//删除HealthGuide数据
					String healthGuideDelete = request.getParameter("healthGuideDelete");
					if (!ObjectUtils.isEmpty(healthGuideDelete)) {
						for (String s : healthGuideDelete.split(",")) {
							if (!ObjectUtils.isEmpty(s)) {
								HealthGuide model = new HealthGuide();
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

		} catch (Exception e) {
			e.printStackTrace();
			log.error("更新随访方案出现异常:"+e);
			return ApiResult.error(ApiCode.SERVER_ERROR, "程序出现异常");
		}
		
		
		return ApiResult.right("操作成功");
	}


	/**
	 * 删除随访方案的接口
	 *
	 * @param response
	 *            response
	 * @param visitUuid
	 *            visitUuid
	 * @return
	 */
	@RequestMapping(value = "/delPreceptDetail", method = RequestMethod.POST)
	public String delPreceptDetail(HttpServletResponse response, @RequestParam("doctorUuid") String doctorUuid,
			@RequestParam("visitUuid") String visitUuid) {
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

			
			List<Map<String,Object>> visitPreceptMap = visitPreceptService.getMyVisitpreceptByDoctorid(doctorUuid);
			if (!ObjectUtils.isEmpty(visitPreceptMap)) {
				for (Map<String,Object> vp : visitPreceptMap) {
					if (visitUuid.equals(vp.get("uuid"))) {
						allow = true;
						break;
					}
				}
			}
			if (allow) {
				
				try {
					VisitPrecept visitPrecept = new VisitPrecept();
					visitPrecept.setUuid(visitUuid);
					visitPreceptService.delete(visitPrecept);
				} catch (Exception e) {
					e.printStackTrace();
					log.error("删除随访出现异常");
					jsonMap = this.getSucJsonMap(SysConst.FAIL, "删除随访方案出现异常！");
				}
			} else {
				jsonMap = this.getSucJsonMap(SysConst.FAIL, "未能找到这个随访方案！");
			}
		}
		HttpServletUtils.outJson(response, jsonMap);
		return null;
	}



	
	/**
	 * 返回错误信息调用的组装jsonMap
	 * @param code
	 * @param message
	 * @return
	 */
	public Map<Object,Object> getFailJsonMap(String code,String message){
		Map<Object,Object> jsonMap = new HashMap<Object,Object>();
		Map<Object,Object> runMap = new HashMap<Object,Object>();
		runMap.put("success", code);
		runMap.put("message", message);
		jsonMap.put("query", runMap);
		return jsonMap;
	}
	

	/**
	 * 返回成功信息调用的组装jsonMap
	 * @param code
	 * @param message
	 * @return
	 */
	public Map<Object,Object> getSucJsonMap(String code,String message){
		Map<Object,Object> jsonMap = new HashMap<Object,Object>();
		Map<Object,Object> runMap = new HashMap<Object,Object>();
		runMap.put("success", code);
		runMap.put("message", message);
		jsonMap.put("query", runMap);
		return jsonMap;
	}
	
	private List<CsZySubject> getList(String str) {
		if (!ObjectUtils.isEmpty(str)) {
			String[] arr;
			if (str.indexOf(",") > 0) {
				arr = str.split(",");
			} else if (str.indexOf("，") > 0) {
				arr = str.split("，");
			} else {
				arr = new String[] { str };
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
    
}
