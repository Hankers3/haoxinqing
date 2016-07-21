package com.hxq.mobile.doctor.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.hxq.mobile.doctor.common.service.CommonMediator;
import com.hxq.mobile.entity.common.Customer;
import com.hxq.mobile.entity.common.CustomerInfo;
import com.hxq.mobile.entity.visit.VisitRecord;
import com.hxq.mobile.util.CompatibleTools;
import com.hxq.mobile.util.api.ApiCode;
import com.hxq.mobile.util.api.ApiResult;
import com.wxcommon.http.RequestUtil;
import com.wxcommon.util.ObjectUtils;

@Controller("com.hxq.mobile.doctor.common.controller.CustomerController")
public class CustomerController {
	Logger log = LoggerFactory.getLogger(CustomerController.class);

	
   // @Resource(name = "com.hxq.mobile.doctor.common.service.CommonMediator")
    @Autowired
    private CommonMediator commonMediator;

	/**
	 * 获取患者信息根据患者ID 12/24
	 *
	 * @param request  request
	 * @param response response
	 * @param customerUuid      customerUuid
	 * @return
	 */
	@RequestMapping(value = "/app/service/customer/2.0/getCustomerByCostomerUuid", method = RequestMethod.GET)
	public @ResponseBody ApiResult getCustomerByCostomerUuid(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("customerUuid") String customerUuid, @RequestParam("doctorUuid") String doctorUuid) {
		if(ObjectUtils.isEmpty(doctorUuid) || ObjectUtils.isEmpty(customerUuid))
			return ApiResult.error(ApiCode.BAD_REQUEST, "缺少必要参数！");

		Map<String, Object> mapReturn = new HashMap<String, Object>();
		try{
			Customer customer = (Customer) commonMediator.getCustomerService().select(new Customer(customerUuid));
			if(customer != null) {
				mapReturn.put("mobile", customer.getMobile());
				mapReturn.put("email", customer.getEmail());
				mapReturn.put("time", customer.getCreateTime());
			}

			CustomerInfo cInfo = commonMediator.getCustomerInfoService().selectByCustomerUuid(customerUuid);
			if(null != cInfo) {
				mapReturn.put("nickname", cInfo.getNickName());
				mapReturn.put("realName", cInfo.getRealName());
				mapReturn.put("certCode", cInfo.getCertCode());
				mapReturn.put("marryState", cInfo.getMarryState());
				mapReturn.put("industry", cInfo.getIndustry());
				mapReturn.put("address", cInfo.getAddress());
				mapReturn.put("customrUuid", cInfo.getCustomerUuid());
				mapReturn.put("customerName", cInfo.getRealName());
				mapReturn.put("sex", cInfo.getSex());
				mapReturn.put("age", cInfo.getAge());
				mapReturn.put("customerMessage", cInfo.getIllnessDescription());
				mapReturn.put("customerImg",CompatibleTools.getImages(commonMediator.getImgUploadService(),commonMediator.getFileService(),cInfo.getImage()));
			}

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("doctorUuid", doctorUuid);
			params.put("customerUuid", customerUuid);

			/* ————————— 患者的历史病历————————— */
			Map<String, Object> medicalRecord = null;
			List<Map<String, Object>> lstRow = commonMediator.getSimpleEntityService().selectList(params, false);
			if(!ObjectUtils.isEmpty(lstRow)) {
				List<Map<String, Object>> medicalRecordList = new ArrayList<Map<String, Object>>(lstRow.size());
				for(Map<String, Object> row : lstRow) {
					medicalRecord = new HashMap<String, Object>();
					medicalRecord.put("medicalRecordUuid", row.get("uuid"));
					medicalRecord.put("createTime", row.get("createTime"));
					medicalRecord.put("type", row.get("caseCategoryType"));
					medicalRecordList.add(medicalRecord);
				}
				mapReturn.put("medicalRecordList", medicalRecordList);
			}
			/* ————————— 患者的历史病历————————— */

			/* ————————— 患者的历史随访表单————————— */
			Map<String, Object> visitRecord = null;
			lstRow = commonMediator.getVisitRecordService().selectList(params, false);
			if(!ObjectUtils.isEmpty(lstRow)) {
				List<Map<String, Object>> visitRecordList = new ArrayList<>(lstRow.size());
				for(Map<String, Object> row : lstRow) {
					visitRecord = new HashMap<String, Object>();
					visitRecord.put("visitRecordUuid", row.get("uuid"));
					visitRecord.put("createTime", row.get("createTime"));
					visitRecordList.add(visitRecord);
				}
				mapReturn.put("visitList", visitRecordList);
			}
			return ApiResult.right(mapReturn);
		} catch (Exception e) {
			log.error("", e);
			return ApiResult.error(ApiCode.SERVER_ERROR, "保存出错，请稍后再试！");
		}
	}
	

	
    /**
     * 获取患者填写的病情变化历史记录列表
     */
    @RequestMapping(value="/mobile/doctor/visit/illness/2.0/search", method=RequestMethod.POST)
    public @ResponseBody ApiResult searchIllness(WebRequest request) {
    	ApiResult br = null;
    	Map<String, Object> form = RequestUtil.cleanParameterMap(request.getParameterMap());
		try {
			return ApiResult.right(commonMediator.getIllnessService().selectList(form, false));
        } catch (Exception e) {
            log.error(RequestUtil.getFormValue(form, "serviceStaffUuid"), e);
            br = ApiResult.error(ApiCode.SERVER_ERROR, "保存出错，请稍后再试！");
		}
		return br;
    }
    
    /**
     * 获取患者填写的病情变化历史记录详情
     */
    @RequestMapping(value="/mobile/doctor/visit/illness/2.0/view/{visitRecordUuid}", method=RequestMethod.GET)
    public @ResponseBody ApiResult viewIllness(@PathVariable("visitRecordUuid") String visitRecordUuid) {
    	ApiResult br = null;
    	if(ObjectUtils.isEmpty(visitRecordUuid)) return ApiResult.error(ApiCode.BAD_REQUEST,null);
		try {
			return ApiResult.right((VisitRecord) commonMediator.getIllnessService().select(new VisitRecord(visitRecordUuid)));
        } catch (Exception e) {
            log.error(visitRecordUuid, e);
            br = ApiResult.error(ApiCode.SERVER_ERROR, "保存出错，请稍后再试！");
        }
		return br;
    }
}