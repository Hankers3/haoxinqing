package com.hxq.mobile.patient.visit.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.hxq.mobile.entity.common.ImgUpload;
import com.hxq.mobile.entity.visit.MedicalRecord;
import com.hxq.mobile.patient.visit.service.MedicalRecordService;
import com.hxq.mobile.support.service.ImgUploadService;
import com.hxq.mobile.util.CompatibleTools;
import com.wxcommon.http.RequestUtil;
import com.wxcommon.util.BeanUtils;
import com.wxcommon.util.JsonUtil;
import com.wxcommon.util.ObjectUtils;
import com.wxcommon.util.StringUtils;

/**
 * 患者病历
 *
 */
@Controller("com.hxq.mobile.patient.visit.controller.MedicalRecordController")
@RequestMapping("/mobile/patient/medical/record/")
public class MedicalRecordController {
	Logger log = LoggerFactory.getLogger(MedicalRecordController.class);

	@Resource(name="com.hxq.mobile.patient.visit.service.MedicalRecordService")
	private MedicalRecordService medicalRecordService;
	@Resource(name="com.hxq.mobile.support.service.ImgUploadService")
	private ImgUploadService imgUploadService;

	/**
	 * 获取病历列表和随访表单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "1.0/getPatientMedicalRecordList", method = RequestMethod.GET)
	public ResponseEntity<String> getPatientMedicalRecordList(WebRequest request, HttpServletResponse response) {
		Map<String, Object> form = RequestUtil.cleanParameterMap(request.getParameterMap());
		if(ObjectUtils.isEmpty(RequestUtil.getFormValue(form,"doctorUuid"))) return null;
		if(ObjectUtils.isEmpty(RequestUtil.getFormValue(form,"customerUuid"))) return null;
		try {
			List<Map<String, Object>> lst = medicalRecordService.selectList(form, null);
			return JsonUtil.ResponseJson(JsonUtil.toJSONString(lst, true, true));
		} catch (Exception e) {
			log.error("", e);
			CompatibleTools.returnJsonByOldVersion(response, "保存出错，请稍后再试！");
			return null;
		}
	}
	
	/**
	 * 获取患者病历
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "1.0/getPatientMedicalRecord", method = RequestMethod.GET)
	public ResponseEntity<String> getPatientMedicalRecord(@RequestParam("medicalRecordUuid") String medicalRecordUuid) {
		//患者病历详情
		Map<String, Object> mapReturn = null;
		try {
			MedicalRecord mr = (MedicalRecord) medicalRecordService.select(new MedicalRecord(medicalRecordUuid));
			mapReturn = BeanUtils.bean2Map(mr);
			doImgUrl(mapReturn, "image1");
			doImgUrl(mapReturn, "image2");
			doImgUrl(mapReturn, "image3");
			doImgUrl(mapReturn, "image4");
			doImgUrl(mapReturn, "image5");
		} catch (Exception e) {
			log.error("", e);
		}
		return JsonUtil.ResponseJson(JsonUtil.toJSONString(mapReturn, true, true));
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
