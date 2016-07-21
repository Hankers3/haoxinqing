package com.hxq.mobile.doctor.visit.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.cms.innermessage.service.InnerMessageService;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageModel;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageTypeEnum;
import com.aebiz.b2b2c.customermgr.mobile.web.push.PushConst;
import com.hxq.mobile.doctor.visit.service.DoctorAdviceMainService;
import com.hxq.mobile.entity.visit.DoctorAdvice;
import com.hxq.mobile.entity.visit.DoctorAdviceMain;
import com.wxcommon.util.BeanUtils;
import com.wxcommon.util.BoolResult;
import com.wxcommon.util.IdentityHelper;
import com.wxcommon.util.JsonUtil;
import com.wxcommon.util.ObjectUtils;
import com.wxcommon.util.StringUtils;

@Controller
@RequestMapping("/mobile/doctor/visit/advice")
public class DoctorAdviceMainController {
	Logger log = LoggerFactory.getLogger(DoctorAdviceMainController.class);

	@Resource(name="com.hxq.mobile.doctor.visit.service.DoctorAdviceMainService")
	private DoctorAdviceMainService service;

	@Autowired
	private InnerMessageService innerMessageService;

    /**
     * 获取指定医生填写的某患者的重要医嘱
     */
    @RequestMapping(value="1.0/search/{doctorUuid}/{customerUuid}", method=RequestMethod.GET)
    public @ResponseBody Map<String, Object> search1(
    		@PathVariable("doctorUuid") String doctorUuid,
    		@PathVariable("customerUuid") String customerUuid) {
    	if(ObjectUtils.isEmpty(doctorUuid) || ObjectUtils.isEmpty(customerUuid)) return null;
    	Map<String, Object> map = null;
		try {
			map = service.selectLastDoctorAdviceMain(doctorUuid, customerUuid);
			if(ObjectUtils.isEmpty(map)) {
				map = service.selectDoctorAdviceMainByVisitRecord(doctorUuid, customerUuid);
			}
        } catch (Exception e) {
            log.error("", e);
        }
		return map;
    }

    /**
     * 保存医生填写的重要医嘱
     */
    @RequestMapping(value="1.0/save", method=RequestMethod.POST)
    public @ResponseBody BoolResult save1(@RequestBody String json) {
    	BoolResult br = null;
        try {
        	DoctorAdviceMain bean = JsonUtil.parseObject(json, DoctorAdviceMain.class);
            BeanUtils.trimStringField(bean);
            String strError = verifySave(bean);
            if (ObjectUtils.isEmpty(strError)) {
            	bean.setId(IdentityHelper.uuid2());
            	for(DoctorAdvice da : bean.getChild()) {
            		da.setMainUuid(bean.getId());
            	}
            	service.insert(bean);

        		//XP消息推送
            	innerMessageService.saveInnerMessageAndPush(bean.getServiceStaffUuid(), bean.getCustomerUuid(),
            			MessageHelper.getMessage("doctorAdviceMesg.showmessage.newAdd"),
            			InnerMessageModel.ACCOUNT_TYPE_STORE, InnerMessageModel.ACCOUNT_TYPE_CUSTOMER,
            			PushConst.push_client_customer, bean.getCustomerUuid(), bean.getId(),
            			InnerMessageTypeEnum.DOCTORADVICE.getValue());
            	return BoolResult.right("保存成功！");
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
