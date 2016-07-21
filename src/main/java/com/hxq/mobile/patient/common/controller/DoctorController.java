package com.hxq.mobile.patient.common.controller;

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
import org.springframework.web.context.request.WebRequest;

import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.hxq.mobile.common.service.CustomerService;
import com.hxq.mobile.common.service.ServiceStaffInfoService;
import com.hxq.mobile.common.service.ServiceStaffService;
import com.hxq.mobile.doctor.order.service.OrderMainService;
import com.hxq.mobile.doctor.visit.service.CaseGroupSerivce;
import com.hxq.mobile.entity.common.Customer;
import com.hxq.mobile.entity.common.DepartmentInfo;
import com.hxq.mobile.entity.common.DoctorNotice;
import com.hxq.mobile.entity.common.DoctorRight;
import com.hxq.mobile.entity.common.HospitalInfo;
import com.hxq.mobile.entity.common.ServiceStaff;
import com.hxq.mobile.entity.common.ServiceStaffInfo;
import com.hxq.mobile.entity.visit.PackageManagement;
import com.hxq.mobile.patient.common.service.ConcernService;
import com.hxq.mobile.patient.common.service.ConsultRecordService;
import com.hxq.mobile.patient.common.service.DoctorNoticeService;
import com.hxq.mobile.patient.common.service.DoctorRightService;
import com.hxq.mobile.patient.common.service.PackageManagementService;
import com.hxq.mobile.patient.visit.service.CustomerDoctorReleService;
import com.hxq.mobile.patient.visit.service.VisitRecordService;
import com.hxq.mobile.support.service.ImgUploadService;
import com.hxq.mobile.util.CompatibleTools;
import com.hxq.mobile.util.Image4App;
import com.hxq.mobile.util.api.ApiCode;
import com.hxq.mobile.util.api.ApiResult;
import com.wxcommon.http.RequestUtil;
import com.wxcommon.util.ObjectUtils;

@Controller("com.hxq.mobile.patient.common.controller")
public class DoctorController {
	Logger log = LoggerFactory.getLogger(DoctorController.class);// 日志

	@Autowired
	private FileService fileService;
	/* 注入私人套餐的Service */
	@Resource(name = "com.hxq.mobile.patient.common.service.PackageManagementService")
	private PackageManagementService packageManagementService;

	/* 订单service */
	@Resource(name = "com.hxq.mobile.doctor.order.service.OrderMainService")
	private OrderMainService orderMainService;

	/* 医生权限service */
	@Resource(name = "com.hxq.mobile.patient.common.service.DoctorRightService")
	private DoctorRightService doctorRightService;

	/* 患者关注医生service*/
	@Resource(name = "com.hxq.mobile.patient.common.service.ConcernService")
	private ConcernService concernService;

	/*患者service */
	@Resource(name = "com.hxq.mobile.common.service.CustomerService")
	private CustomerService customerService;
	/* 咨询记录 */
	@Resource(name = "com.hxq.mobile.patient.common.service.ConsultRecordService")
	private ConsultRecordService consultRecordService;

	/* 随访记录信息表 */
	@Resource(name = "com.hxq.mobile.patient.visit.service.VisitRecordService")
	private VisitRecordService visitRecordService;

	/* 医生信息service */
	@Resource(name = "com.hxq.mobile.common.service.ServiceStaffService")
	private ServiceStaffService servicestaffService;

	/* 医生基础信息service */
	@Resource(name = "com.hxq.mobile.common.service.ServiceStaffInfoService")
	private ServiceStaffInfoService servicestaffInfoService;

	/* 今日推荐service */
	@Resource(name = "com.hxq.mobile.patient.common.service.DoctorNoticeService")
	private DoctorNoticeService doctorNoticeService;

	/* 图片service */
	@Resource(name = "com.hxq.mobile.support.service.ImgUploadService")
	private ImgUploadService imgService;
	/*医患关联表service*/
	@Resource(name = "com.hxq.mobile.patient.visit.service.CustomerDoctorReleService")
	private CustomerDoctorReleService customerDoctorReleService;

    @Resource(name = "com.hxq.mobile.doctor.visit.service.impl.CaseGroupSerivceImpl")
    private CaseGroupSerivce caseGroupSerivce;

	/**
	 * 患者端随访的医生1.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/customer/patient/1.0/getVisitDoctors", method = RequestMethod.GET)
	public  @ResponseBody ApiResult getVisitDoctors(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("customerUuid") String customerUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Image4App[] imgUrls = null;
		// 判断参数是否为空
		if (ObjectUtils.isEmpty(customerUuid)) {
			return ApiResult.error(ApiCode.BAD_REQUEST, "患者id为空");
		}
		// 通过医患关联表，查询医生id 通过患者id 查询医生id 条件是delFlag='1' and groupUuid>='0'分组编号：0默认是（未）分组，-1：是临时分组
		List<Map<String, Object>> customerDoctorRele = customerDoctorReleService.selectByDoctorUuid(customerUuid);
		String doctorUuid;
		ServiceStaff sfm = null;
		ServiceStaffInfo sim = null;
		List<Map<String, Object>> relist = new ArrayList<>();
		
		if (!ObjectUtils.isEmpty(customerDoctorRele)) {	
			for (Map<String, Object> map : customerDoctorRele) {
				doctorUuid = (String)map.get("doctorUuid");//获取医生id
				
				try {//查询医生信息
					sfm = (ServiceStaff) servicestaffService.select(new ServiceStaff(doctorUuid));
					sim = servicestaffInfoService.selectByServiceStaffUuid(doctorUuid);
				} catch (Exception e) {
					log.error(doctorUuid, e);
					return ApiResult.error(ApiCode.SERVER_ERROR, e.getMessage());
				}
				
				map.put("doctorUuid", map.get("doctorUuid"));
				map.put("createTime", map.get("createTime"));
				
				if (sfm!=null) {//医生信息
					map.put("doctorName", sfm.getRealName());//医生姓名	
					try {
						HospitalInfo hi = (HospitalInfo) servicestaffService.select(new HospitalInfo((String) sfm.getHospital()));
						map.put("hospitalName", hi != null ? hi.getHospitalName() : sfm.getHospital());// 医院名称
					} catch (Exception e) {
						log.error("", e);
						map.put("hospitalName", sfm.getHospital());
					}
					
					try {
						DepartmentInfo di = (DepartmentInfo) servicestaffService.select(new DepartmentInfo((String) sfm.getDepartment()));
						map.put("departmentName", di != null ? di.getDepartmentName() : sfm.getDepartment());// 医生所在科室
					} catch (Exception e) {
						log.error("", e);
						map.put("departmentName", sfm.getDepartment());
					}
				}
				if (sim!=null) {//医生基本信息	
					if (ObjectUtils.isEmpty(sim.getCertImage()) != true) {
						imgUrls = CompatibleTools.getImages(imgService, fileService, (String) sim.getCertImage());
						map.put("professional", sim.getProfessional());// 职称
						map.put("sex", sim.getSex());
						//map.put("serviceStaffUuid", sim.getServiceStaffUuid());
						map.put("territory", sim.getTerritory());// 医生的擅长
						// 医生的头像地址
						// save.put("imgUrl", sim.getImgUrl());
						if (ObjectUtils.isEmpty(sim.getImage()) != true) {
							imgUrls = CompatibleTools.getImages(imgService, fileService, (String) sim.getImage());
							if (!ObjectUtils.isEmpty(imgUrls))
								map.put("img", imgUrls[0]);
						}
					}
				}
				relist.add(map);
			}
		}
		return ApiResult.right(relist);
	}

	/**
	 * 患者端 我的医生-关注的医生 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/app/customer/patient/2.0/getAttentionDoctors", method = RequestMethod.GET)
	public @ResponseBody ApiResult getAttentionDoctorsTwo(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("customerUuid") String customerUuid) {
		Image4App[] imgUrls = null;
		
		// 判断参数是否为空
		if (ObjectUtils.isEmpty(customerUuid)) {
			return ApiResult.error(ApiCode.BAD_REQUEST, "患者id为空");
		}
		// 通过患者id得到患者关注医生表
		List<Map<String, Object>> concernList = concernService.selectByCustomerUuid(customerUuid);
	
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		
		String doctorUuid = null;
		String createTime = null;
		
		if (concernList != null && concernList.size() > 0) {
			for (Map<String, Object> concernModel : concernList) {
				doctorUuid = (String) concernModel.get("doctorUuid");//医生id
				createTime = (String) concernModel.get("createTime");//
				Map<String, Object> reMap = this.getDoctor(doctorUuid);
				reMap.put("createTime", createTime);
				
				//医生头像
				ServiceStaffInfo doctorList = servicestaffInfoService.selectByServiceStaffUuid(doctorUuid);
				if (doctorList!=null) {	
						if (ObjectUtils.isEmpty(doctorList.getImage()) != true) {
							imgUrls = CompatibleTools.getImages(imgService, fileService, (String) doctorList.getImage());
							if (!ObjectUtils.isEmpty(imgUrls))
								reMap.put("img", imgUrls[0]);
						}
					}

				// 获取该医生的咨询总量(图文资讯的加电话咨询的数量的和)
				int consultNum = consultRecordService.selectConsultNumBydoctorId(doctorUuid);
				int totalTelNum = orderMainService.getTotalTelNumByDoctorId(doctorUuid);
				int totalNum = consultNum + totalTelNum;
				reMap.put("totalNum", totalNum);//咨询总量

				int allOrderNum = consultRecordService.selectAllOrderNumByDoctorId(doctorUuid);//获取医生的接诊量
				int visitRecordNum = visitRecordService.selectVisitRecordNumByDoctorId(doctorUuid);//根据医生id 获取随访患者的数量
				int receTotalNum = consultNum + allOrderNum + totalTelNum + visitRecordNum;
				reMap.put("receTotalNum", receTotalNum);//医生的接诊量
				
				//医生
				reList.add(reMap);
			}
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("reList", reList);
		return ApiResult.right(jsonMap);
	}

	/**
	 * 获取患者端患者未读医生回复信息
	 *
	 * @param 
	 * @param 
	 * @return
	 * @author 
	 */
	@RequestMapping(value = "/app/customer/patient/2.0/getPatientByUnreadConsultRecord", method = RequestMethod.GET)
	public @ResponseBody ApiResult getPatientByUnreadConsultRecord(@RequestParam("customerUuid") String customerUuid) {
		// 判断医生id是否为空
		if (ObjectUtils.isEmpty(customerUuid)) {
	        return ApiResult.error(ApiCode.BAD_REQUEST, "患者编号不能为空");
	    }
		try {
			return ApiResult.right(caseGroupSerivce.selectPatientByUnreadConsultRecord(customerUuid));
		} catch (Exception e) {
			log.error("", e);
			 return ApiResult.error(ApiCode.SERVER_ERROR, null);
		}
	}
	
  /**
	 * 更改患者端患者未读医生回复信息
	 *
	 * @param 
	 * @param 
	 * @return
	 * @author 
	 */
	@RequestMapping(value = "/app/customer/patient/2.0/getPatientByConsultRecord", method = RequestMethod.GET)
	public @ResponseBody ApiResult getPatientByConsultRecord(@RequestParam("doctorUuid") String doctorUuid) {
		// 判断患者id是否为空
		if (ObjectUtils.isEmpty(doctorUuid)) {
	        return ApiResult.error(ApiCode.BAD_REQUEST, "医生编号不能为空");
	    }
		try {
			return ApiResult.right(caseGroupSerivce.selectDoctorConsultInformation(doctorUuid));
		} catch (Exception e) {
			log.error("", e);
			 return ApiResult.error(ApiCode.SERVER_ERROR, null);
		}
	}
	
	/**
	 * 患者端我的医生 服务记录 
	 * 获得以下 1:电话咨询订单 2：私人套餐 0:在线咨询即图文咨询 3预约加号 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/customer/patient/2.0/getServicesByCustomerUuid", method = RequestMethod.GET)
	public @ResponseBody ApiResult getServicesByCustomerUuidTwo(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("customerUuid") String customerUuid,
			@RequestParam(value ="pageCount",required = false) Integer pageCount, 
			@RequestParam(value ="pageNo",required = false) Integer pageNo) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		// 判断患者id是否为空
		if (ObjectUtils.isEmpty(customerUuid)) {
			return ApiResult.error(ApiCode.BAD_REQUEST, "患者主键为空");
		}
		
		//jsonMap.put("telList", telList(customerUuid));//电话
		
       // jsonMap.put("privateList", privateList(customerUuid));//套餐
			
        jsonMap.put("picList",picList(customerUuid) );//图文咨询
		
        //jsonMap.put("plusList",plusList(customerUuid));//预约加号    
        
		return ApiResult.right(jsonMap);
		
	}
	//预约加号
	private List<Map<String, Object>> plusList(String customerUuid) {
		// 返回list 3预约加号
		List<Map<String, Object>> plusList = new ArrayList<Map<String, Object>>();
		// 2预约加号
		List<Map<String, Object>> plusRecordList = consultRecordService.selectByCustomerUuid(customerUuid, "2");
		// 如果plusRecordList不为空，放返回值
		if (plusRecordList != null) {
			for (Map<String, Object> consultRecordModel : plusRecordList) {
				String doctorUuid = (String) consultRecordModel.get("doctorUuid");				
				Map<String, Object> ImageTextMap = this.getDoctor(doctorUuid);
				if (ImageTextMap.isEmpty()) continue;
				ImageTextMap.put("consultRecordUuid", consultRecordModel.get("uuid"));// 加号资讯的主键
				ImageTextMap.put("seeDoctorTime", consultRecordModel.get("seeDoctorTime"));// 加号就诊时间
				ImageTextMap.put("type", "3");// 类型咨询类型 3预约加号
				ImageTextMap.put("opeTime", consultRecordModel.get("createTime"));
				plusList.add(ImageTextMap);
			}
		}
		return plusList;
	}
	
	//图文资讯
	private List<Map<String, Object>> picList(String customerUuid) {
		// 返回list 0:在线咨询即图文咨询
		List<Map<String, Object>> picList = new ArrayList<Map<String, Object>>();
		// 咨询类型 0在线咨询即图文咨询
		List<Map<String, Object>> imageTextRecordList = consultRecordService.selectByCustomerUuid(customerUuid, "0");
		// 如果imageTextRecordList不为空，放返回值
		if (imageTextRecordList != null) {
			for (Map<String, Object> consultRecordModel : imageTextRecordList) {
				String doctorUuid = (String) consultRecordModel.get("doctorUuid");
				Map<String, Object> ImageTextMap = this.getDoctor(doctorUuid);
				if (ImageTextMap.isEmpty()) continue;
				ImageTextMap.put("consultRecordUuid", consultRecordModel.get("uuid"));// 图文资讯的主键
				ImageTextMap.put("type", "0");// 类型咨询类型 0在线咨询即图文咨询
				ImageTextMap.put("opeTime", consultRecordModel.get("createTime"));
		        
			    // 获取该医生的咨询总量(图文资讯的加电话咨询的数量的和)
				int consultNum = consultRecordService.selectConsultNumBydoctorId(doctorUuid);
				int totalTelNum = orderMainService.getTotalTelNumByDoctorId(doctorUuid);
				int totalNum = consultNum + totalTelNum;
				ImageTextMap.put("totalNum", totalNum);//咨询总量

				int allOrderNum = consultRecordService.selectAllOrderNumByDoctorId(doctorUuid);//获取医生的接诊量
				int visitRecordNum = visitRecordService.selectVisitRecordNumByDoctorId(doctorUuid);//根据医生id 获取随访患者的数量
				int receTotalNum = consultNum + allOrderNum + totalTelNum + visitRecordNum;
				ImageTextMap.put("receTotalNum", receTotalNum);//医生的接诊量
				ImageTextMap.put("ifread", consultRecordModel.get("ifread"));//0 未读  1 已读
				
				picList.add(ImageTextMap);
			}
		}
		return picList;
	}
	
	//电话咨询
	private List<Map<String, Object>> telList(String customerUuid) {
		// 返回list 1:电话咨询List
		List<Map<String, Object>> telList = new ArrayList<Map<String, Object>>();
		// 通过查询条件，得到患者的1:电话咨询订单
		List<Map<String, Object>> orderMainList = orderMainService.getOrderList("1", customerUuid);
		if (orderMainList != null && orderMainList.size() > 0) {
			for (Map<String, Object> orderMainModel : orderMainList) {
				String doctorUuid = (String) orderMainModel.get("doctorUuid");
				Map<String, Object> reMap = this.getDoctor(doctorUuid);
				if (reMap.isEmpty()) continue;
				reMap.put("type", orderMainModel.get("orderType"));// 订单类型 1:电话咨询订单 2：私人套餐
				reMap.put("orderUuid", orderMainModel.get("uuid"));// 订单Uuid
				reMap.put("opeTime", orderMainModel.get("orderTime"));
				telList.add(reMap);
			}
		}
		return telList;
	}
	//套餐
	private List<Map<String, Object>> privateList(String customerUuid) {
		PackageManagement packageManagementModel = null;
		// 通过查询条件，得到患者的2：私人套餐
		List<Map<String, Object>> orderMainList1 = orderMainService.getOrderList("2", customerUuid);
		// 返回list 2：私人套餐List
		List<Map<String, Object>> privateList = new ArrayList<Map<String, Object>>();
		if (orderMainList1 != null && orderMainList1.size() > 0) {
			for (Map<String, Object> orderMainModel : orderMainList1) {
				String doctorUuid = (String) orderMainModel.get("doctorUuid");
				Map<String, Object> reMap = this.getDoctor(doctorUuid);
				if (reMap.isEmpty()) continue;
				reMap.put("type", orderMainModel.get("orderType"));// 订单类型 2：私人套餐
				reMap.put("orderUuid", orderMainModel.get("uuid"));// 订单Uuid
				reMap.put("opeTime", orderMainModel.get("orderTime"));
				// 套餐信息
				try {
					packageManagementModel = (PackageManagement) packageManagementService.select(new PackageManagement((String) orderMainModel.get("packageUuid")));
					reMap.put("packageName", packageManagementModel.getPackageName());// 套餐名称
					reMap.put("money", packageManagementModel.getMoney());// 价格
					reMap.put("phoneTimes", packageManagementModel.getPhoneTimes());// 电话咨询次数
					reMap.put("plusTimes", packageManagementModel.getPlusTimes());// 预约加号次数
					reMap.put("createTime", packageManagementModel.getCreateTime());
					reMap.put("endTime", packageManagementModel.getEndTime());
				} catch (Exception e) {
					log.error((String) orderMainModel.get("packageUuid"), e);
				}
				privateList.add(reMap);
			}
		}
		return privateList;
	}
	/**
	 * 患者端我的医生 服务记录 
	 * 获得以下 1:电话咨询订单 2：私人套餐 0:在线咨询即图文咨询 3预约加号 
	 * @param request
	 * @param response
	 * @return
	 */
	/*@RequestMapping(value = "/app/customer/patient/2.0/getServicesByCustomerUuid", method = RequestMethod.GET)
	public @ResponseBody ApiResult getServicesByCustomerUuidTwo(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("customerUuid") String customerUuid,
			@RequestParam("pageCount") int pageCount, @RequestParam("pageNo") int pageNo) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		// 判断患者id是否为空
		if (ObjectUtils.isEmpty(customerUuid)) {
			return ApiResult.error(ApiCode.BAD_REQUEST, "患者主键为空");
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
		List<Map<String, Object>> orderMainList = orderMainService.getOrderList("1", customerUuid, pageCount, pageNo);
		if (orderMainList != null && orderMainList.size() > 0) {
			for (Map<String, Object> orderMainModel : orderMainList) {
				String doctorUuid = (String) orderMainModel.get("doctorUuid");
				Map<String, Object> reMap = this.getDoctor(doctorUuid);
				reMap.put("type", orderMainModel.get("orderType"));// 订单类型 1:电话咨询订单 2：私人套餐
				reMap.put("orderUuid", orderMainModel.get("uuid"));// 订单Uuid
				reMap.put("opeTime", orderMainModel.get("orderTime"));
				telList.add(reMap);
			}
		}
		jsonMap.put("telList", telList);
		
		PackageManagement packageManagementModel = null;
		// 通过查询条件，得到患者的2：私人套餐
		List<Map<String, Object>> orderMainList1 = orderMainService.getOrderList("2", customerUuid, 0, 0);
		if (orderMainList1 != null && orderMainList1.size() > 0) {
			for (Map<String, Object> orderMainModel : orderMainList1) {
				String doctorUuid = (String) orderMainModel.get("doctorUuid");
				Map<String, Object> reMap = this.getDoctor(doctorUuid);
				reMap.put("type", orderMainModel.get("orderType"));// 订单类型 2：私人套餐
				reMap.put("orderUuid", orderMainModel.get("uuid"));// 订单Uuid
				reMap.put("opeTime", orderMainModel.get("orderTime"));
				// 套餐信息
				try {
					packageManagementModel = (PackageManagement) packageManagementService.select(new PackageManagement((String) orderMainModel.get("packageUuid")));
					reMap.put("packageName", packageManagementModel.getPackageName());// 套餐名称
					reMap.put("money", packageManagementModel.getMoney());// 价格
					reMap.put("phoneTimes", packageManagementModel.getPhoneTimes());// 电话咨询次数
					reMap.put("plusTimes", packageManagementModel.getPlusTimes());// 预约加号次数
					reMap.put("createTime", packageManagementModel.getCreateTime());
					reMap.put("endTime", packageManagementModel.getEndTime());
				} catch (Exception e) {
					log.error((String) orderMainModel.get("packageUuid"), e);
				}
				privateList.add(reMap);
			}
		}
		jsonMap.put("privateList", privateList);

		// 咨询类型 0在线咨询即图文咨询
		List<Map<String, Object>> imageTextRecordList = consultRecordService.selectByCustomerUuid(customerUuid, "0");
		// 2预约加号
		List<Map<String, Object>> plusRecordList = consultRecordService.selectByCustomerUuid(customerUuid, "2");
		// 如果imageTextRecordList不为空，放返回值
		if (imageTextRecordList != null) {
			for (Map<String, Object> consultRecordModel : imageTextRecordList) {
				String doctorUuid = (String) consultRecordModel.get("doctorUuid");
				if (ObjectUtils.isEmpty(doctorUuid)){
					continue;
				}
				Map<String, Object> ImageTextMap = this.getDoctor(doctorUuid);
				ImageTextMap.put("consultRecordUuid", consultRecordModel.get("uuid"));// 图文资讯的主键
				ImageTextMap.put("type", "0");// 类型咨询类型 0在线咨询即图文咨询
				ImageTextMap.put("opeTime", consultRecordModel.get("createTime"));
				picList.add(ImageTextMap);
			}
		}
		jsonMap.put("picList", picList);
		// 如果plusRecordList不为空，放返回值
		if (plusRecordList != null) {
			for (Map<String, Object> consultRecordModel : plusRecordList) {
				String doctorUuid = (String) consultRecordModel.get("doctorUuid");
				Map<String, Object> ImageTextMap = this.getDoctor(doctorUuid);
				ImageTextMap.put("consultRecordUuid", consultRecordModel.get("uuid"));// 加号资讯的主键
				ImageTextMap.put("seeDoctorTime", consultRecordModel.get("seeDoctorTime"));// 加号就诊时间
				ImageTextMap.put("type", "3");// 类型咨询类型 3预约加号
				ImageTextMap.put("opeTime", consultRecordModel.get("createTime"));

				plusList.add(ImageTextMap);
			}
		}
		jsonMap.put("plusList", plusList);
		return ApiResult.right(jsonMap);
	}*/


	/**
	 * 找医生-医生列表
	 * 找医生筛选 通过城市id，医院id，医生专长(医生标签id) 
	 * city	城市
	 * territorys 医生标签
	 * hospitalUuids 医院id
	 * pageno	页数
	 * pagesize	条数
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/app/customer/patient/2.0/getDoctorsBySelect", method = RequestMethod.POST)
	public @ResponseBody ApiResult getDoctorsBySelectTwo(WebRequest request) {

		Map<String, Object> form = RequestUtil.cleanParameterMap(request.getParameterMap());
		// 通过查询条件，得到医生
		Object[] result = servicestaffService.selectDoctors(form);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> servicestaffList = (List<Map<String, Object>>)result[0];

		Image4App[] imgUrls = null;
		List<Map<String, Object>> reList = new ArrayList<>();
		if (servicestaffList != null) {
			String uuid=null; //医生id 
			DoctorRight doctorRightModel=null;
			for (Map<String, Object> servicestaff : servicestaffList) {
				
				Map<String, Object> reMap = new HashMap<>();
				uuid = (String) servicestaff.get("uuid");// 医生uuid
				
				reMap.put("doctorName", servicestaff.get("realName"));// 医生名
				reMap.put("doctorUuid", uuid);// 医生uuid
				reMap.put("hospitalName", servicestaff.get("hospitalName"));// 执业医院
				reMap.put("departmentName", servicestaff.get("departmentName"));// 执业科室
				
               // 医生头像
				if (ObjectUtils.isEmpty(servicestaff.get("image")) != true) {
					imgUrls = CompatibleTools.getImages(imgService, fileService, (String) servicestaff.get("image"));
					if (!ObjectUtils.isEmpty(imgUrls)){
						reMap.put("img", imgUrls[0]);
					}else {
						reMap.put("img", "");
					}
				}else {
					reMap.put("img", "");
				}
				// 获取该医生的咨询总量(图文资讯的加电话咨询的数量的和)
				int consultNum = consultRecordService.selectConsultNumBydoctorId(uuid);
				int totalTelNum = orderMainService.getTotalTelNumByDoctorId(uuid);
				int totalNum = consultNum + totalTelNum;
				reMap.put("totalNum", totalNum);//咨询总量
				// 获取关注该医生的粉丝数
				int concernNum = concernService.selectConcernNumByDoctorId(uuid);
				reMap.put("concernNum", concernNum);
				
				
				doctorRightModel = doctorRightService.selectDoctorRight(uuid);//是否开通电话咨询 0：未开通 1：开通
				if (doctorRightModel != null) {
					reMap.put("telState", doctorRightModel.getPhone());// 电话咨询:0：未开通 1：开通
					reMap.put("plus", doctorRightModel.getPlus());// 加号预约 0：未开通 1：开通
					reMap.put("teletext", doctorRightModel.getTeletext());//图文咨询 0：未开通 1：开通
					reMap.put("personal", doctorRightModel.getPersonal());//私人医生 0：未开通 1：开通
				} else {
					reMap.put("telState", "0");
					reMap.put("plus", "0");
					reMap.put("teletext", "0");
					reMap.put("personal", "0");
				}

				reMap.put("sex", servicestaff.get("sex"));// 医生性别
				reMap.put("serviceCount", servicestaff.get("serviceTimes"));// 医生服务次数
				reMap.put("professional", servicestaff.get("professional"));// 医生职称
				reList.add(reMap);
			}
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("pageNo", result[1]);//当前页号
		jsonMap.put("totalPage", result[2]);//总页数
		jsonMap.put("totalRows", result[3]);//总记录数
		jsonMap.put("reList", reList);
		return ApiResult.right(jsonMap);
	}

	/**
	 * 获取医生详情(患者端随访)2.0
	 * @param request
	 * @param response
	 * @param doctorUuid
	 * @return
	 */
	@RequestMapping(value = "/app/customer/patient/2.0/getDoctorDetail", method = RequestMethod.GET)
	public @ResponseBody ApiResult getDoctorDetailTwo(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("doctorUuid") String doctorUuid) {

		Image4App[] imgUrls = null;
		Image4App imgUrl= null;
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取患者id
		String customerUuid = request.getParameter("customerUuid"); // 必须传
																	// 以及必需要传的参数
		// 判断用户是否登陆
		if (ObjectUtils.isEmpty(doctorUuid)) {
			return ApiResult.error(ApiCode.BAD_REQUEST, "该医生不存在");
		}
		// 获取该医生信息和基础信息
		ServiceStaff sfm = null;
		ServiceStaffInfo sim = null;
		DoctorNotice dnm = null;
		try {
			sfm = (ServiceStaff) servicestaffService.select(new ServiceStaff(doctorUuid));// 获取医生基础信息
			sim = servicestaffInfoService.selectByServiceStaffUuid(doctorUuid);
			dnm = doctorNoticeService.selectDoctorNoticeByDoctorUuid(doctorUuid);// 获取医生公告 获取最新的一条
		} catch (Exception e) {
			log.error(doctorUuid, e);
			return ApiResult.error(ApiCode.SERVER_ERROR, e.getMessage());
		}

		// 获该患者是否收藏该医生 0代表没有关注 ，1代表已经关注
		int concernType = 0;
		if (!ObjectUtils.isEmpty(doctorUuid) && !ObjectUtils.isEmpty(customerUuid)) {
			int num = concernService.selectConcernType(doctorUuid, customerUuid);
			if (num != 0) {
				concernType = 1;
			}
		}
		// 获取关注该医生的粉丝数
		int concernNum = concernService.selectConcernNumByDoctorId(doctorUuid);
		// 获取该医生的咨询总量(图文资讯的加电话咨询的数量的和)
		int consultNum = consultRecordService.selectConsultNumBydoctorId(doctorUuid);
		int totalTelNum = orderMainService.getTotalTelNumByDoctorId(doctorUuid);
		int totalNum = consultNum + totalTelNum;

		// 获取医生的接诊量
		int allOrderNum = consultRecordService.selectAllOrderNumByDoctorId(doctorUuid);
		// 根据医生id 获取随访患者的数量
		int visitRecordNum = visitRecordService.selectVisitRecordNumByDoctorId(doctorUuid);
		int receTotalNum = consultNum + allOrderNum + totalTelNum + visitRecordNum;

		// 定义返回List
		List<Map<String, Object>> relist = new ArrayList<>();
		Map<String, Object> save = new HashMap<>();
		if (sim != null) {
			// save.put("certImage", sim.getCertImage());//证件照
			if (ObjectUtils.isEmpty(sim.getCertImage()) != true) {
				imgUrls = CompatibleTools.getImages(imgService, fileService, (String) sim.getCertImage());
				if (!ObjectUtils.isEmpty(imgUrls)) {
					save.put("certImage", imgUrls[0]);
				} else {
					save.put("certImage", sim.getCertImage());
				}
			}
			save.put("professional", sim.getProfessional());// 职称
			save.put("region", sim.getRegion());// 所在地区
			save.put("city", sim.getCity());
			save.put("sex", sim.getSex());
			save.put("province", sim.getProvince());// 所在地省
			save.put("serviceStaffUuid", sim.getServiceStaffUuid());
			save.put("territory", sim.getTerritory());// 医生的擅长
			save.put("professional", sim.getProfessional());// 医生的职称
			save.put("synopsis", sim.getSynopsis());// 医生的个人介绍
			// 医生的头像地址
			// save.put("imgUrl", sim.getImgUrl());
			imgUrl = new Image4App("", "");	
			if (!ObjectUtils.isEmpty(sim.getImage())) {
				imgUrls = CompatibleTools.getImages(imgService, fileService, (String) sim.getImage());
				if (!ObjectUtils.isEmpty(imgUrls)){
					imgUrl = imgUrls[0];
				}
				
				if(StringUtil.isEmpty(imgUrl.getSmall()) && !StringUtil.isEmpty(imgUrl.getLarge())){
					imgUrl.setSmall(imgUrl.getLarge());
				}
			}
			save.put("img", imgUrl);
		}
		if (sfm != null) {

			save.put("certificationTyp", sfm.getSate());// 医生的认证状态 0:未认证 1：认证通过 // 2：认证不通过
			save.put("departmentLine", sfm.getDepartmentLine());// 科室电话
			save.put("realName", sfm.getRealName());// 医生姓名
			save.put("mobile", sfm.getMobile()); //医生电话号码
			// 医院名称
			try {
				HospitalInfo hi = (HospitalInfo) servicestaffService.select(new HospitalInfo((String) sfm.getHospital()));
				save.put("hospitalName", hi != null ? hi.getHospitalName() : sfm.getHospital());
			} catch (Exception e) {
				log.error("", e);
				save.put("hospitalName", sfm.getHospital());
			}
			// 医生所在科室
			try {
				DepartmentInfo di = (DepartmentInfo) servicestaffService.select(new DepartmentInfo((String) sfm.getDepartment()));
				save.put("departmentName", di != null ? di.getDepartmentName() : sfm.getDepartment());
			} catch (Exception e) {
				log.error("", e);
				save.put("departmentName", sfm.getDepartment());
			}
		}
		if (dnm != null) {
			save.put("content", dnm.getContent());// 医生公告内容
		}

		DoctorRight doctorRightModel = doctorRightService.selectDoctorRight(doctorUuid);// 通过uuid查询医生权限管理信息
		if (doctorRightModel != null) {
			save.put("telState", doctorRightModel.getPhone());// 是否开通电话咨询
			save.put("personal", doctorRightModel.getPersonal());// 私人状态
																	// 是否开通私人医生
			save.put("plus", doctorRightModel.getPlus());// 是否开通加号预约
			save.put("teletext", doctorRightModel.getTeletext());// 是否开通图文咨询
		} else {
			save.put("telState", "0");
			save.put("personal", "0");
			save.put("plus", "0");
			save.put("teletext", "0");
		}

		save.put("concernType", concernType);// 获取关注状态 1代表已关注，0代表未关注
		save.put("concernNum", concernNum);// 获取关注该医生的粉丝数
		save.put("totalNum", totalNum);// 获取咨询量的数量
		save.put("receTotalNum", receTotalNum);// 获取接诊的数量

		relist.add(save);
		Map<Object, Object> jsonMap = new HashMap<Object, Object>();
		jsonMap.put("relist", relist);// 消息字段
		return ApiResult.right(jsonMap);
	}

	
	/**
	 * 得到医生或者患者电话号码2.0
	 * @param request
	 * @param response
	 * @param doctorUuid
	 * @return
	 */
	@RequestMapping(value = "/app/customer/patient/2.0/getMobile", method = RequestMethod.GET)
	public @ResponseBody ApiResult getMobile(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("uuid") String uuid,@RequestParam("type") String type) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
																	// 以及必需要传的参数
		// 判断用户是否登陆
		if(ObjectUtils.isEmpty(type)){
			return ApiResult.error(ApiCode.BAD_REQUEST, "type不能为空");
		}else{
			if (ObjectUtils.isEmpty(uuid) && type.equals("0")) {
				return ApiResult.error(ApiCode.BAD_REQUEST, "该医生不存在");
			}else if (ObjectUtils.isEmpty(uuid) && type.equals("1")) {
				return ApiResult.error(ApiCode.BAD_REQUEST, "该患者不存在");
			}
		}
		// 获取该医生信息
		ServiceStaff sfm = null;
		Customer cus = null;
		//电话号码
		String mobile = "";
		//type:0 医生, type:1 患者
		if(type.equals("0")){
			try {
				sfm = (ServiceStaff) servicestaffService.select(new ServiceStaff(uuid));// 获取医生基础信息
		
				if(sfm != null){
					mobile = sfm.getMobile();
				}
			} catch (Exception e) {
				log.error(uuid, e);
				return ApiResult.error(ApiCode.SERVER_ERROR, e.getMessage());
			}
		}else{
			try {
				cus = (Customer) customerService.select(new Customer(uuid));
		
				if(cus != null){
					mobile = cus.getMobile();
				}
			} catch (Exception e) {
				log.error(uuid, e);
				return ApiResult.error(ApiCode.SERVER_ERROR, e.getMessage());
			}
			
		}
/*		// 定义返回List
		List<Map<String, Object>> relist = new ArrayList<>();
		Map<String, Object> save = new HashMap<>();	
		save.put("mobile", mobile);// 职称
*/
		Map<Object, Object> jsonMap = new HashMap<Object, Object>();
		jsonMap.put("mobile", mobile);// 消息字段
		return ApiResult.right(jsonMap);
	}

	/**
	 * 根据医生uuid返回一个通用的map，以便返回到页面
	 * @param doctorUuid
	 * @return
	 */
	private Map<String, Object> getDoctor(String doctorUuid) {
		Map<String, Object> reMap = new HashMap<String, Object>();
		if (ObjectUtils.isEmpty(doctorUuid)) return reMap;

		Image4App[] imgUrls = null;
		ServiceStaff servicestaff = null;
		ServiceStaffInfo servicestaffinfo = null;

		try {
			servicestaff = (ServiceStaff) servicestaffService.select(new ServiceStaff(doctorUuid));
			servicestaffinfo = servicestaffInfoService.selectByServiceStaffUuid(doctorUuid);
		} catch (Exception e) {
			log.error(doctorUuid, e);
			return reMap;
		}

		if (servicestaff == null || servicestaffinfo == null) {
//			reMap.put("error", "该医生Uuid下无医生信息");
			return reMap;
		}
		reMap.put("doctorName", servicestaff.getRealName());// 医生名
		reMap.put("sex", servicestaffinfo.getSex());// 医生性别
		// 医生头像
		// reMap.put("img", servicestaffinfoModel.getImgUrl());
		if (ObjectUtils.isEmpty(servicestaffinfo.getImage()) != true) {
			imgUrls = CompatibleTools.getImages(imgService, fileService, (String) servicestaffinfo.getImage());
			if (!ObjectUtils.isEmpty(imgUrls))
				reMap.put("img", imgUrls[0]);
		}
	
		try {// 医院
			HospitalInfo hi = (HospitalInfo) servicestaffService
					.select(new HospitalInfo((String) servicestaff.getHospital()));
			reMap.put("hospitalName", hi != null ? hi.getHospitalName() : servicestaff.getHospital());
		} catch (Exception e) {
			log.error("", e);
			reMap.put("hospitalName", servicestaff.getHospital());
		}
		try {// 科室
			DepartmentInfo di = (DepartmentInfo) servicestaffService
					.select(new DepartmentInfo((String) servicestaff.getDepartment()));
			reMap.put("departmentName", di != null ? di.getDepartmentName() : servicestaff.getDepartment());
		} catch (Exception e) {
			log.error("", e);
			reMap.put("departmentName", servicestaff.getDepartment());
		}

		reMap.put("territory", servicestaffinfo.getTerritory());// 医生擅长
		reMap.put("doctorUuid", doctorUuid);// 医生uuid
		reMap.put("professional", servicestaffinfo.getProfessional());// 医生职称
		return reMap;
	}
}