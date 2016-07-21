package com.hxq.mobile.patient.common.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.consultrecord.service.ConsultRecordService;
import com.aebiz.b2b2c.order.ordermain.service.OrderMainService;
import com.aebiz.b2b2c.servicestaff.doctorright.service.DoctorRightService;
import com.aebiz.b2b2c.servicestaff.doctorright.vo.DoctorRightModel;
import com.aebiz.b2b2c.visitprecept.visitrecord.service.VisitRecordService;
import com.aebiz.b2b2c.websiteoperation.concern.service.ConcernService;
import com.hxq.mobile.common.service.ServiceStaffInfoService;
import com.hxq.mobile.common.service.ServiceStaffService;
import com.hxq.mobile.entity.common.DoctorNotice;
import com.hxq.mobile.entity.common.ServiceStaff;
import com.hxq.mobile.entity.common.ServiceStaffInfo;
import com.hxq.mobile.patient.common.service.DoctorNoticeService;
import com.wxcommon.http.RequestUtil;
import com.wxcommon.util.BeanUtils;
import com.wxcommon.util.BoolResult;
import com.wxcommon.util.JsonUtil;
import com.wxcommon.util.ObjectUtils;

/**
 * 医生详情  
 *
 */
@Controller
@RequestMapping("/mobile/doctor/details/")
public class DoctorDetailsController {
	Logger log = LoggerFactory.getLogger(DoctorDetailsController.class);

	@Resource(name="com.hxq.mobile.common.service.ServiceStaffService")
	private ServiceStaffService servicestaffService;
	@Resource(name="com.hxq.mobile.common.service.ServiceStaffInfoService")
	private ServiceStaffInfoService servicestaffinfoService;
	@Resource(name="com.hxq.mobile.patient.common.service.DoctorNoticeService")
	private DoctorNoticeService doctorNoticeService;

	@Autowired
	private ConcernService concernService;
	@Autowired
	private ConsultRecordService consultRecordService;
	@Autowired
	private VisitRecordService visitRecordService;
	@Autowired
	private OrderMainService orderMainService;
	@Autowired
	private DoctorRightService doctorRightService;

	/**
	 * 获取医生详情
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "1.0/getDoctorDetail", method = RequestMethod.GET)
	public ResponseEntity<String> getDoctorDetail(WebRequest request) {
		
		BoolResult br = null;
		Map<String, Object> form = RequestUtil.cleanParameterMap(request.getParameterMap());

		// 获取医生id
		String doctorUuid = RequestUtil.getFormValue(form, "doctorUuid");
		if(ObjectUtils.isEmpty(doctorUuid)) return null;

		// 获取患者id
		String customerUuid = RequestUtil.getFormValue(form, "customerUuid"); // 以及必需要传的参数
		if(ObjectUtils.isEmpty(customerUuid)) return null;

		Map<String, Object> StaffResultmap = new HashMap<String, Object>(0);
		StaffResultmap.put("uuid", doctorUuid);
		try {
			//医生详情
			ServiceStaff ss = (ServiceStaff) servicestaffService.select(new ServiceStaff(doctorUuid));
			if(ObjectUtils.isEmpty(ss)) return null;

			//医生基础信息表
			ServiceStaffInfo ssinfo = servicestaffinfoService.selectByServiceStaffUuid(doctorUuid);
			if (ObjectUtils.isEmpty(ssinfo)) {
				br = BoolResult.error("医生基础信息表为空，请完善医生基础信息表");
				return JsonUtil.ResponseJson(JsonUtil.toJSONString(br));
			}

			StaffResultmap = BeanUtils.bean2Map(ss);
			StaffResultmap.putAll(BeanUtils.bean2Map(ssinfo));
			StaffResultmap.put("uuid", doctorUuid);

			//医生公告
			DoctorNotice notice = doctorNoticeService.selectDoctorNoticeByDoctorUuid(doctorUuid);
			if (ObjectUtils.isEmpty(notice)==false) StaffResultmap.put("content", notice.getContent());
		} catch (Exception e) {
			log.error(doctorUuid, e);
		}

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

		DoctorRightModel doctorRightModel = doctorRightService.getByDoctorUuid(doctorUuid);
		if (doctorRightModel != null) {
			StaffResultmap.put("telState", doctorRightModel.getPhone());
			StaffResultmap.put("personal", doctorRightModel.getPersonal());
			StaffResultmap.put("plus", doctorRightModel.getPlus());
			StaffResultmap.put("teletext", doctorRightModel.getTeletext());
		} else {
			StaffResultmap.put("telState", "0");
			StaffResultmap.put("personal","0");
			StaffResultmap.put("plus", "0");
			StaffResultmap.put("teletext", "0");
		}

		// 获取关注状态 1代表已关注，0代表未关注
		StaffResultmap.put("concernType", concernType);
		StaffResultmap.put("concernNum", concernNum);// 获取关注该医生的粉丝数
		StaffResultmap.put("totalNum", totalNum);// 获取咨询量的数量
		StaffResultmap.put("receTotalNum", receTotalNum);// 获取接诊的数量

		return JsonUtil.ResponseJson(JsonUtil.toJSONString(StaffResultmap, true, true));
	}
}
