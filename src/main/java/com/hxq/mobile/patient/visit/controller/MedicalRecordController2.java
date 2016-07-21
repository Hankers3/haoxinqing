package com.hxq.mobile.patient.visit.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.hxq.mobile.common.service.ServiceStaffService;
import com.hxq.mobile.entity.common.ImgUpload;
import com.hxq.mobile.entity.common.ServiceStaff;
import com.hxq.mobile.entity.visit.MedicalRecord;
import com.hxq.mobile.patient.visit.service.MedicalRecordService;
import com.hxq.mobile.support.service.ImgUploadService;
import com.hxq.mobile.util.api.ApiCode;
import com.hxq.mobile.util.api.ApiResult;
import com.wxcommon.http.RequestUtil;
import com.wxcommon.util.BeanUtils;
import com.wxcommon.util.ObjectUtils;
import com.wxcommon.util.StringUtils;

/**
 * 患者病历
 *
 */
@Controller("com.hxq.mobile.patient.visit.controller.MedicalRecordController2")
@RequestMapping("/mobile/patient/medical/record/")
public class MedicalRecordController2 {
	Logger log = LoggerFactory.getLogger(MedicalRecordController2.class);

	@Resource(name="com.hxq.mobile.patient.visit.service.MedicalRecordService")
	private MedicalRecordService medicalRecordService;
	@Resource(name="com.hxq.mobile.support.service.ImgUploadService")
	private ImgUploadService imgUploadService;
    @Resource(name = "com.hxq.mobile.common.service.ServiceStaffService")
    private ServiceStaffService servicestaffService;

	/**
	 * 获取病历列表和随访表单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "2.0/getPatientMedicalRecordList", method = RequestMethod.GET)
	public @ResponseBody ApiResult getPatientMedicalRecordList(WebRequest request, HttpServletResponse response) {
		Map<String, Object> form = RequestUtil.cleanParameterMap(request.getParameterMap());
		if(ObjectUtils.isEmpty(RequestUtil.getFormValue(form,"doctorUuid"))) return null;
		if(ObjectUtils.isEmpty(RequestUtil.getFormValue(form,"customerUuid"))) return null;
		try {
			List<Map<String, Object>> lst = medicalRecordService.selectList(form, null);
			return ApiResult.right(lst);
		} catch (Exception e) {
			log.error("", e);
			return ApiResult.error(ApiCode.SERVER_ERROR, "网络繁忙，请稍后再试！");
		}
	}
	
	/**
	 * 获取患者病历
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "2.0/getPatientMedicalRecord", method = RequestMethod.GET)
	public @ResponseBody ApiResult getPatientMedicalRecord(@RequestParam("medicalRecordUuid") String medicalRecordUuid) {
		//患者病历详情
		Map<String, Object> mapReturn = null;
		try {
			MedicalRecord mr = (MedicalRecord) medicalRecordService.select(new MedicalRecord(medicalRecordUuid));
			String uuid = mr.getDoctorUuid();
			ServiceStaff ss = (ServiceStaff) servicestaffService.select(new ServiceStaff(uuid));
			mapReturn = BeanUtils.bean2Map(mr);
			if(!ObjectUtils.isEmpty(ss)){
				mapReturn.put("realName", ss.getRealName());
			}
			doImgUrl(mapReturn, "image1");
			doImgUrl(mapReturn, "image2");
			doImgUrl(mapReturn, "image3");
			doImgUrl(mapReturn, "image4");
			doImgUrl(mapReturn, "image5");
			return ApiResult.right(mapReturn);
		} catch (Exception e) {
			log.error("", e);
			return ApiResult.error(ApiCode.SERVER_ERROR, "网络繁忙，请稍后再试！");
		}
	}
	
	/**
	 * 根据图片id获取图片url
	 * @param row
	 * @param imgColumn
	 * @throws Exception
	 */
	private void doImgUrl(Map<String, Object> row, String imgColumn) throws Exception {
		if(ObjectUtils.isEmpty(row) || ObjectUtils.isEmpty(imgColumn)) return;
		String id = StringUtils.trimToEmpty(row.get(imgColumn));
		if(id.length() < 1 || id.startsWith("http:") || id.startsWith("https:")) return;
		ImgUpload imginfo = (ImgUpload) imgUploadService.select(new ImgUpload(id));
		if(imginfo != null) row.put(imgColumn, imginfo.imgUrl);
	}
}
