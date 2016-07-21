package com.hxq.mobile.doctor.visit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.hxq.mobile.entity.visit.MedicalRecord;
import com.hxq.mobile.util.repository.SimpleEntityService;
import com.wxcommon.util.BeanUtils;
import com.wxcommon.util.BoolResult;
import com.wxcommon.util.ObjectUtils;
import com.wxcommon.util.StringUtils;

@Controller("com.hxq.mobile.doctor.visit.controller.MedicalRecordController")
@RequestMapping("/mobile/doctor/visit/medicalrecord")
public class MedicalRecordController {
	Logger log = LoggerFactory.getLogger(MedicalRecordController.class);

	@Resource(name="com.hxq.mobile.doctor.visit.service.MedicalRecordService")
	private SimpleEntityService service;

    /**
     * 保存医生填写的患者病例
     */
    @RequestMapping(value="1.0/save", method=RequestMethod.POST)
    public @ResponseBody BoolResult save1(MedicalRecord bean) {
    	BoolResult br = null;
        try {
            BeanUtils.trimStringField(bean);
            String strError = verifySave(bean, true);
            if (ObjectUtils.isEmpty(strError)) {
            	bean.setCreateTime(DateFormatHelper.getNowTimeStr());
            	if(ObjectUtils.isEmpty(bean.getSeeDoctorTime())) {
            		bean.setSeeDoctorTime(DateFormatHelper.getNowTimeStr());
            	}
            	service.insert(bean);
            	return BoolResult.right();
            } else {
            	return BoolResult.error(strError);
            }
        } catch (Exception e) {
            log.error("", e);
            br = BoolResult.error("保存出错，请稍后再试！");
        }
        return br;
    }
    
    /**
     * 查询指定医生填写的某患者的所有病例
     */
    @RequestMapping(value="1.0/search/{doctorUuid}/{customerUuid}", method=RequestMethod.GET)
    public @ResponseBody List<Map<String, Object>> search1(
    		@PathVariable("doctorUuid") String doctorUuid,
    		@PathVariable("customerUuid") String customerUuid) {
    	if(ObjectUtils.isEmpty(doctorUuid) || ObjectUtils.isEmpty(customerUuid)) return null;
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("doctorUuid", doctorUuid);
    	params.put("customerUuid", customerUuid);
		try {
			return service.selectList(params, false);
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
