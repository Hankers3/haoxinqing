package com.hxq.mobile.doctor.visit.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxq.mobile.doctor.visit.service.MedicalRecordService;
import com.hxq.mobile.entity.visit.MedicalRecord;
import com.hxq.mobile.util.api.ApiCode;
import com.hxq.mobile.util.api.ApiResult;
import com.wxcommon.util.BeanUtils;
import com.wxcommon.util.ObjectUtils;
import com.wxcommon.util.StringUtils;

@Controller
@RequestMapping("/mobile/doctor/visit/medicalrecord")
public class MedicalRecordController2 {
	Logger log = LoggerFactory.getLogger(MedicalRecordController2.class);

	@Resource(name="com.hxq.mobile.doctor.visit.service.MedicalRecordService")
	private MedicalRecordService service;

    
    /**
     * 保存医生填写的患者病例
     */
    @RequestMapping(value="2.0/save", method=RequestMethod.POST)
    public @ResponseBody ApiResult save2(MedicalRecord bean) {
    	ApiResult br = null;
        try {
            BeanUtils.trimStringField(bean);
            String strError = verifySave(bean, true);
            if (ObjectUtils.isEmpty(strError)) {
            	int success = service.insertSave2(bean);
            	return ApiResult.right(success);
            } else {
            	return ApiResult.error(ApiCode.BAD_REQUEST, strError);
            }
        } catch (Exception e) {
            log.error("", e);
            br = ApiResult.error(ApiCode.SERVER_ERROR, "保存出错，请稍后再试！");
        }
        return br;
    }

    /**
     * 查询指定医生填写的某患者的所有病例
     */
    @RequestMapping(value="2.0/search/{doctorUuid}/{customerUuid}", method=RequestMethod.GET)
    public @ResponseBody ApiResult search2(
    		@PathVariable("doctorUuid") String doctorUuid,
    		@PathVariable("customerUuid") String customerUuid) {
    	if(ObjectUtils.isEmpty(doctorUuid) || ObjectUtils.isEmpty(customerUuid)) return ApiResult.error(ApiCode.BAD_REQUEST,null);
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("doctorUuid", doctorUuid);
    	params.put("customerUuid", customerUuid);
		try {
			return ApiResult.right(service.selectList(params, false));
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }
    /**
     * 保存校验
     *
     * @param bean  保存对象
     * @param isNew true:新增;false:修改.
     * @return
     */
    private String verifySave(MedicalRecord bean, boolean isNew) {
        StringBuffer sbf = new StringBuffer(1000);
        if (isNew == true && !ObjectUtils.isEmpty(bean.getId()))
            StringUtils.appendBuffer(sbf, "不能添加已存在病历！", sbf.length() > 0 ? "\r\n" : null);
        if (isNew == false && ObjectUtils.isEmpty(bean.getId()))
            StringUtils.appendBuffer(sbf, "无法修改无主键的病历！", sbf.length() > 0 ? "\r\n" : null);
        if (ObjectUtils.isEmpty(bean.getCustomerUuid()))
            StringUtils.appendBuffer(sbf, "患者信息不能为空！", sbf.length() > 0 ? "\r\n" : null);
        if (ObjectUtils.isEmpty(bean.getDoctorUuid()))
            StringUtils.appendBuffer(sbf, "医生信息不能为空！", sbf.length() > 0 ? "\r\n" : null);
        return sbf.toString();
    }
}
