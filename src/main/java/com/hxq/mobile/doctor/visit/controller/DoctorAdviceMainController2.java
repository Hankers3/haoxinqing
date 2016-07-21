package com.hxq.mobile.doctor.visit.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxq.mobile.doctor.visit.service.DoctorAdviceMainService;
import com.hxq.mobile.entity.visit.DoctorAdviceMain;
import com.hxq.mobile.util.api.ApiCode;
import com.hxq.mobile.util.api.ApiResult;
import com.wxcommon.util.BeanUtils;
import com.wxcommon.util.JsonUtil;
import com.wxcommon.util.ObjectUtils;
import com.wxcommon.util.StringUtils;

@Controller
@RequestMapping("/mobile/doctor/visit/advice")
public class DoctorAdviceMainController2 {
	Logger log = LoggerFactory.getLogger(DoctorAdviceMainController2.class);

	@Resource(name="com.hxq.mobile.doctor.visit.service.DoctorAdviceMainService")
	private DoctorAdviceMainService service;

    /**
     * 获取指定医生填写的某患者的重要医嘱
     */
    @RequestMapping(value="2.0/search/{doctorUuid}/{customerUuid}", method=RequestMethod.GET)
    public @ResponseBody ApiResult search2(
    		@PathVariable("doctorUuid") String doctorUuid,
    		@PathVariable("customerUuid") String customerUuid) {
    	if(ObjectUtils.isEmpty(doctorUuid) || ObjectUtils.isEmpty(customerUuid)) return ApiResult.error(ApiCode.BAD_REQUEST,null);
    	Map<String, Object> map = null;
		try {
			map = service.selectLastDoctorAdviceMain(doctorUuid, customerUuid);
			if(ObjectUtils.isEmpty(map)) {
				map = service.selectDoctorAdviceMainByVisitRecord(doctorUuid, customerUuid);
			}
        } catch (Exception e) {
            log.error("", e);
        }
		return ApiResult.right(map);
    }

    /**
     * 保存医生填写的重要医嘱
     */
    @RequestMapping(value="2.0/save", method=RequestMethod.POST)
    public @ResponseBody ApiResult save2(@RequestBody String json) {
    	ApiResult br = null;
        try {
        	DoctorAdviceMain bean = JsonUtil.parseObject(json, DoctorAdviceMain.class);
            BeanUtils.trimStringField(bean);
            String strError = verifySave(bean);
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
     * 保存校验
     *
     * @param bean  保存对象
     * @param isNew true:新增;false:修改.
     * @return
     */
    private String verifySave(DoctorAdviceMain bean) {
        StringBuffer sbf = new StringBuffer(1000);
        if (ObjectUtils.isEmpty(bean.getServiceStaffUuid()))
            StringUtils.appendBuffer(sbf, "缺少医生信息！", sbf.length() > 0 ? "\r\n" : null);
        if (ObjectUtils.isEmpty(bean.getCustomerUuid()))
            StringUtils.appendBuffer(sbf, "缺少患者信息！", sbf.length() > 0 ? "\r\n" : null);
        if (ObjectUtils.isEmpty(bean.getChild()))
            StringUtils.appendBuffer(sbf, "缺少药物信息！", sbf.length() > 0 ? "\r\n" : null);
        return sbf.toString();
    }
}
