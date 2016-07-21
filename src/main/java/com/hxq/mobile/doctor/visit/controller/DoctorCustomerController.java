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

import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.mobile.web.push.PushConst;
import com.hxq.mobile.common.service.CustomerInfoService;
import com.hxq.mobile.common.service.CustomerService;
import com.hxq.mobile.doctor.visit.service.CaseGroupSerivce;
import com.hxq.mobile.doctor.visit.service.DoctorCustomerService;
import com.hxq.mobile.entity.common.ConsultRecord;
import com.hxq.mobile.entity.common.Customer;
import com.hxq.mobile.entity.common.CustomerInfo;
import com.hxq.mobile.entity.message.InnerMessage;
import com.hxq.mobile.message.InnerMessage.service.InnerMessageService;
import com.hxq.mobile.patient.common.service.ConsultRecordService;
import com.hxq.mobile.support.service.ImgUploadService;
import com.hxq.mobile.util.CompatibleTools;
import com.hxq.mobile.util.Image4App;
import com.hxq.mobile.util.api.ApiCode;
import com.hxq.mobile.util.api.ApiResult;
import com.wxcommon.util.ObjectUtils;

/**
 * Created by Alice on 2016/5/5 0005.
 */
@Controller
public class DoctorCustomerController {
    private Logger log = LoggerFactory.getLogger(DoctorCustomerController.class);

    @Autowired
    @Resource(name = "com.hxq.mobile.common.service.CustomerService")
    private CustomerService customerService;

    @Resource(name = "com.hxq.mobile.doctor.visit.service.impl.DoctorCustomerServiceImpl")
    private DoctorCustomerService doctorCustomerMobileService;

    @Resource(name = "com.hxq.mobile.doctor.visit.service.DoctorCustomerService")
    private DoctorCustomerService customerDoctorReleService;
    
    @Resource(name = "com.hxq.mobile.common.service.CustomerInfoService")
    private CustomerInfoService customerInfoService;

    @Resource(name = "com.hxq.mobile.doctor.visit.service.impl.CaseGroupSerivceImpl")
    private CaseGroupSerivce caseGroupSerivce;
    
	@Resource(name="com.hxq.mobile.message.InnerMessage.service.InnerMessageService")
	private InnerMessageService innerMessageService;
	
    @Resource(name = "com.hxq.mobile.support.service.ImgUploadService")
    private ImgUploadService imgUploadService;
    
    @Resource(name = "com.hxq.mobile.patient.common.service.ConsultRecordService")
    private ConsultRecordService consultRecordService;

    @Autowired
    private FileService fileService;
    
    /**
     * 添加患者-手机号是否存在
     * @param request
     * @param 
     * @return
     */
    @RequestMapping(value = "/app/service/customer/2.0/SelectCustomerMobile", method = RequestMethod.GET)
    public @ResponseBody ApiResult selectCustomerMobile_v2(HttpServletRequest request,
    		@RequestParam("mobile") String mobile, @RequestParam("doctorUuid") String doctorUuid) {
        // 判断手机号不能为空
        if (ObjectUtils.isEmpty(mobile)) {
            return ApiResult.error(ApiCode.BAD_REQUEST, "手机号不能为空");
        }
        Map<String, Object> mapReturn = new HashMap<String, Object>();
        Map<String, Object> cusMap = new HashMap<String, Object>();
        try {
        	Customer customer = customerService.selectCustomerByMobile(mobile);
        	if(customer == null){//手机号是未注册的患者
        		mapReturn.put("id", "3");
        	}else{//手机号已注册的患者
        		Map<String, Object> cusDocReleMap = customerDoctorReleService.selectCustomerDoctorRele_v2(customer.getUuid());
        		CustomerInfo customerInfo = customerInfoService.selectByCustomerUuid(customer.getUuid());
        		String docId = null;
        		if(!ObjectUtils.isEmpty(cusDocReleMap)){
        			docId = (String) cusDocReleMap.get("doctorUuid");
        		}
        		if(ObjectUtils.isEmpty(docId)){
        			mapReturn.put("id", "2");
        			if(!ObjectUtils.isEmpty(customerInfo)){
        				// 患者uuid
            			cusMap.put("customerUuid",customer.getUuid());
            			// 患者姓名
            			cusMap.put("customerName",customerInfo.getRealName());
                        // 患者头像
                        Image4App[] urls = CompatibleTools.getImages(imgUploadService, fileService,customerInfo.getImage());
                        if(!ObjectUtils.isEmpty(urls)){
                        	cusMap.put("customerImg", urls[0]);
                        }
                        // 患者信息
                        cusMap.put("customerMessage",customerInfo.getIllnessDescription());
                        // 患者性别
                        cusMap.put("sex", customerInfo.getSex());
                        // 患者年领
                        cusMap.put("age", customerInfo.getAge());
                        if(!ObjectUtils.isEmpty(cusDocReleMap)){
                        	cusMap.put("applyTime", cusDocReleMap.get("createTime"));
                        }else{
                        	cusMap.put("applyTime", "");
                        }
        			}else{
        				// 患者uuid
            			cusMap.put("customerUuid",customer.getUuid());
        				// 患者姓名
            			cusMap.put("customerName", "");
                        // 患者头像
                        cusMap.put("customerImg", "");
                        // 患者信息
                        cusMap.put("customerMessage", "");
                        // 患者性别
                        cusMap.put("sex", "");
                        // 患者年领
                        cusMap.put("age", "");
                        cusMap.put("applyTime", "");
        			}
        			mapReturn.put("data", cusMap);
        		} else if(docId.equalsIgnoreCase(doctorUuid)) {
        			mapReturn.put("id", "1");
        			mapReturn.put("msg", "此手机号已经是您的随访患者！");
        			if(!ObjectUtils.isEmpty(customerInfo)){
        				// 患者uuid
            			cusMap.put("customerUuid",customer.getUuid());
            			// 患者姓名
            			cusMap.put("customerName",customerInfo.getRealName());
                        // 患者头像
                        Image4App[] urls = CompatibleTools.getImages(imgUploadService, fileService,customerInfo.getImage());
                        if(!ObjectUtils.isEmpty(urls)){
                        	cusMap.put("customerImg", urls[0]);
                        }
                        // 患者信息
                        cusMap.put("customerMessage",customerInfo.getIllnessDescription());
                        // 患者性别
                        cusMap.put("sex", customerInfo.getSex());
                        // 患者年领
                        cusMap.put("age", customerInfo.getAge());
                        if(!ObjectUtils.isEmpty(cusDocReleMap)){
                        	cusMap.put("applyTime", cusDocReleMap.get("createTime"));
                        }else{
                        	cusMap.put("applyTime", "");
                        }
        			}else{
        				// 患者uuid
            			cusMap.put("customerUuid",customer.getUuid());
        				// 患者姓名
            			cusMap.put("customerName", "");
                        // 患者头像
                        cusMap.put("customerImg", "");
                        // 患者信息
                        cusMap.put("customerMessage", "");
                        // 患者性别
                        cusMap.put("sex", "");
                        // 患者年领
                        cusMap.put("age", "");
                        cusMap.put("applyTime", "");
        			}
        			mapReturn.put("data", cusMap);
        		} else {
        			mapReturn.put("id", "4");
        			mapReturn.put("msg", "该患者已有随访医生，暂不能添加该患者!");
        		}
        	}
    		return ApiResult.right(mapReturn);
        } catch (Exception e) {
            log.error(mobile,e);
            return ApiResult.error(ApiCode.SERVER_ERROR, e.getMessage());
        }
    }

    /**
     * 添加患者-保存
     * @param request
     * @param doctorUuid
     * @return
     */
    @RequestMapping(value = "/app/service/customer/2.0/addCustomer", method = RequestMethod.POST)
    public @ResponseBody ApiResult addCustomer_v2(HttpServletRequest request,
    		@RequestParam("id") String id,
            @RequestParam(value="uuid", required=false) String uuid,
            @RequestParam("doctorUuid") String doctorUuid,
            @RequestParam("mobile") String mobile,
            @RequestParam("name") String name,
            @RequestParam("illnessDescription") String illnessDescription,
            @RequestParam(value = "groupId", required = false) String groupId) {
    	if (ObjectUtils.isEmpty(id)) {
            return ApiResult.error(ApiCode.BAD_REQUEST, "ID不能为空！");
        }
        // 判断医生id不能为空
        if (ObjectUtils.isEmpty(doctorUuid)) {
            return ApiResult.error(ApiCode.BAD_REQUEST, "医生ID不能为空！");
        }
        if (ObjectUtils.isEmpty(mobile)) {
        	return ApiResult.error(ApiCode.BAD_REQUEST, "手机号不能为空！");
        }
        if (ObjectUtils.isEmpty(name)) {
        	return ApiResult.error(ApiCode.BAD_REQUEST, "姓名不能为空！");
        }
        if (ObjectUtils.isEmpty(illnessDescription)) {
        	return ApiResult.error(ApiCode.BAD_REQUEST, "病情描述不能为空！");
        }
        try {
        	int success = customerDoctorReleService.addCustomer_v2(id,uuid,doctorUuid,mobile,name,illnessDescription,groupId);
            return ApiResult.right(success);
        } catch (Exception e) {
            log.error(this.getClass().getName() + e.getMessage());
            return ApiResult.error(ApiCode.SERVER_ERROR, null);
        }
    }


    /**
     * 删除患者信息
     */
    @RequestMapping(value = "/app/service/customer/2.0/deleteCustomerByCostomerUuidAndGid", method = RequestMethod.POST)
    public @ResponseBody ApiResult deleteCustomerByCostomerUuidAndGid_v2(
            @RequestParam("groupId") String gid,
            @RequestParam("customerUuid") String customerUuid,
            @RequestParam("doctorUuid") String doctorUuid) {
        // 判断医生id和分组id不能为空
        if (ObjectUtils.isEmpty(customerUuid) || ObjectUtils.isEmpty(doctorUuid)) {
            return ApiResult.error(ApiCode.BAD_REQUEST, "请指定医生与患者id");
        }
        try {
            return doctorCustomerMobileService.deleteCustomerByCustomerUuidAndGid_v2(gid, customerUuid, doctorUuid);
        } catch (Exception e) {
            log.error(this.getClass().getName() + e.getMessage());
            return ApiResult.error(ApiCode.SERVER_ERROR, null);
        }
    }

    /**
     * 根据医生id及分組id获取患者列表
     */
    @RequestMapping(value = "/app/service/customer/2.0/getGroupAndCustomer", method = RequestMethod.GET)
    public @ResponseBody ApiResult getGroupAndCustomer_v2(HttpServletRequest request,
                                                              @RequestParam("doctorUuid") String doctorUuid) {
        // 判断医生id不能为空
        if (StringUtil.isEmpty(doctorUuid)) {
            return ApiResult.error(ApiCode.BAD_REQUEST, "请指定医生id");
        }
        try {
        	ApiResult apiResult =  doctorCustomerMobileService.selectCustomerByDoctorUuidAndGroupUuid_v2(doctorUuid, request.getParameter("groupId"));
        	List<Map<String, Object>> data = (List<Map<String, Object>>) apiResult.getValue();
        	for(Map<String, Object> temp : data) {
        		List<Map<String, Object>> customers = (List<Map<String, Object>>)temp.get("customers");
        		if(customers!= null && customers.size() > 0){
        			for(Map<String, Object> ImgMap : customers){
        				String imgId = (String) ImgMap.get("image");
        				// 患者头像
        				Image4App[] url = CompatibleTools.getImages(imgUploadService, fileService, imgId);
    					if (!ObjectUtils.isEmpty(url)) {
    						ImgMap.put("image", url[0]);
    					}
        			}
        		} 
        	}
        	return ApiResult.right(data);
        } catch (Exception e) {
            log.error(this.getClass().getName() + e.getMessage());
            return ApiResult.error(ApiCode.SERVER_ERROR, null);
        }
    }

/*	*//**
	 * 医生群发患者 12/19
	 * @param response response
	 * @return string
	 *//*
	@RequestMapping(value = "app/service/customer/2.0/addInnerMessage", method = RequestMethod.POST)
	public @ResponseBody ApiResult addInnerMessage(HttpServletResponse response,
								  @RequestParam("content") String content, @RequestParam("doctorUuid") String doctorUuid,
								  @RequestParam("customerUuids") List<String> customerUuids) {
		ApiResult br = null;
		// 判断医生id不能为空
		if(ObjectUtils.isEmpty(doctorUuid)) return ApiResult.error(ApiCode.BAD_REQUEST,null);
		try {
			for (String customerUuid : customerUuids) {
				innerMessageService.insertInnerMessageAndPush(doctorUuid, customerUuid, content, InnerMessage.ACCOUNT_TYPE_STORE, InnerMessage.ACCOUNT_TYPE_CUSTOMER, PushConst.push_client_customer, doctorUuid, "", "");
			}
			return ApiResult.right();
		} catch (Exception e) {
			e.printStackTrace();
			br = ApiResult.error(ApiCode.SERVER_ERROR, "保存出错，请稍后再试！");
		}
		return br;
	}*/
	
	/**
	 * 医生群发患者 12/19
	 * @param response response
	 * @return string
	 */
	@RequestMapping(value = "app/service/customer/2.0/addInnerMessage", method = RequestMethod.POST)
	public @ResponseBody ApiResult addInnerMessage(@RequestParam("customerUuids") String[] customerUuids,
			@RequestParam("doctorUuid") String doctorUuid, @RequestParam("content") String content) {
		// 判断医生id不能为空
		if(ObjectUtils.isEmpty(doctorUuid)){
			return ApiResult.error(ApiCode.BAD_REQUEST,"缺少医生ID！");
		}
		try {
			for (String customerUuid : customerUuids) {
				innerMessageService.insertInnerMessageAndPush(doctorUuid, customerUuid, content,
						InnerMessage.ACCOUNT_TYPE_STORE, InnerMessage.ACCOUNT_TYPE_CUSTOMER,
						PushConst.push_client_customer, doctorUuid, "", "");
			}
			return ApiResult.right();
		} catch (Exception e) {
			log.error(doctorUuid, e);
			return ApiResult.error(ApiCode.SERVER_ERROR, e.getMessage());
		}
	}

    /**
     * 患者分组-添加
     * 添加患者分组信息 ,医生ID，组名称
     */
    @RequestMapping(value = "/app/service/customer/2.0/addCaseGroup", method = RequestMethod.POST)
    public @ResponseBody ApiResult addCaseGroup(@RequestParam("doctorUuid") String doctorUuid,
                                  @RequestParam("groupName") String groupName) {
        // 判断医生id不能为空
        if (ObjectUtils.isEmpty(doctorUuid)) {
            return ApiResult.error(ApiCode.BAD_REQUEST, "医生id不能为空");
        }
        if (StringUtil.isEmpty(groupName)) {
            return ApiResult.error(ApiCode.BAD_REQUEST, "分组名不能为空");
        }
        try {
            return caseGroupSerivce.insertCaseGroup_v2(doctorUuid, groupName);
        } catch (Exception e) {
            log.error(this.getClass().getName() + e.getMessage());
            return ApiResult.error(ApiCode.SERVER_ERROR, null);
        }

    }

	/**
	 * 添加患者-获取所有的分组 clear（ver2.0）
	 * 患者分组列表获取
	 */
	@RequestMapping(value = "/app/service/customer/2.0/getCaseGroupByDoctorId", method = RequestMethod.GET)
	public @ResponseBody ApiResult getCaseGroupByDoctorId(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("doctorUuid") String doctorUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 判断医生id不能为空
		if (ObjectUtils.isEmpty(doctorUuid)) {
			 return ApiResult.error(ApiCode.BAD_REQUEST, "医生id不能为空");
		}
		// 设置返回信息
		// 得到casegroupList对象
		List<Map<String, Object>>  casegroupList= caseGroupSerivce.getByDoctorUuid(doctorUuid);

		// 定义返回List
		List<Map<String,Object>> relist = new ArrayList<>();
		if (casegroupList != null && casegroupList.size() > 0) {
			
				relist = casegroupList;
		}
		
		return ApiResult.right(relist);
	}
    
    /**
     * 修改患者分组
     */
    @RequestMapping(value = "/app/service/customer/2.0/updateCaseGroup", method = RequestMethod.POST)
    public @ResponseBody ApiResult updateCaseGroup_v2(@RequestParam("groupId") String gid, @RequestParam("groupName") String groupName) {
        // 分组id不能为空
        if (ObjectUtils.isEmpty(gid)) {
            return ApiResult.error(ApiCode.BAD_REQUEST, "分组id不能为空");
        }
        try {
            return caseGroupSerivce.updateCaseGroup_v2(gid, groupName);
        } catch (Exception e) {
            log.error(this.getClass().getName() + e.getMessage());
            return ApiResult.error(ApiCode.SERVER_ERROR, null);
        }
    }


    /**
     * 删除患者分组
     */
    @RequestMapping(value = "/app/service/customer/2.0/deleteCaseGroup", method = RequestMethod.POST)
    public @ResponseBody ApiResult deleteCaseGroup(@RequestParam("groupId") String gid) {
        // 分组id不能为空
        if (ObjectUtils.isEmpty(gid)) {
            return ApiResult.error(ApiCode.BAD_REQUEST, "分组id不能为空");
        }
        try {
            return caseGroupSerivce.deleteCaseGroup_v2(gid);
        } catch (Exception e) {
            log.error(this.getClass().getName() + e.getMessage());
            return ApiResult.error(ApiCode.SERVER_ERROR, null);
        }
    }

    /**
	 * 获取我的患者分组展示
	 *
	 * @param 
	 * @param 
	 * @return
	 * @author 
	 */
	@RequestMapping(value = "/app/service/customer/2.0/getPatientGrops", method = RequestMethod.GET)
	public @ResponseBody ApiResult getCustomrGrops(@RequestParam("doctorUuid") String doctorUuid) {
		// 判断医生id是否为空
		if (ObjectUtils.isEmpty(doctorUuid)) {
            return ApiResult.error(ApiCode.BAD_REQUEST, "医生编号不能为空");
        }
		//在线咨询
		List<Map<String, Object>> onlines;
		try {
			onlines = caseGroupSerivce.selectConsultRecordByid(doctorUuid, ConsultRecord.TYPE_ONLINE);
			return ApiResult.right(caseGroupSerivce.selectConsultRecordMap(onlines,doctorUuid));
		} catch (Exception e) {
			log.error("", e);
			 return ApiResult.error(ApiCode.SERVER_ERROR, null);
		}
	}
	
	  /**
	 * 更改医生端未读患者咨询信息
	 *
	 * @param 
	 * @param 
	 * @return
	 * @author 
	 */
	@RequestMapping(value = "/app/service/customer/2.0/getConsultRecord", method = RequestMethod.GET)
	public @ResponseBody ApiResult getConsultRecord(@RequestParam("customerUuid") String customerUuid,@RequestParam("doctorUuid") String doctorUuid ) {
		// 判断患者id是否为空
		if (ObjectUtils.isEmpty(customerUuid)) {
	        return ApiResult.error(ApiCode.BAD_REQUEST, "患者编号不能为空");
	    }
		try {
			caseGroupSerivce.selectPatientConsultInformation(customerUuid,doctorUuid);
			return ApiResult.right("更新信息状态成功");
		} catch (Exception e) {
			log.error("", e);
			 return ApiResult.error(ApiCode.SERVER_ERROR, null);
		}
	}
	
	
	/**
	 * 获取医生的未读消息的内容
	 *
	 * @param 
	 * @param 
	 * @return
	 * @author 
	 */
	@RequestMapping(value = "/app/service/customer/2.0/getDoctorConsultRecordContent", method = RequestMethod.GET)
	public @ResponseBody ApiResult getDoctorConsultRecordContent(@RequestParam("uuid") String uuid,@RequestParam("type") String type) {
		// 判断患者id是否为空
		if (ObjectUtils.isEmpty(uuid)) {
	        return ApiResult.error(ApiCode.BAD_REQUEST, "uuid编号不能为空");
	    }
		if(!(type.trim().equals("1") || type.trim().equals("2"))){
			return ApiResult.error(ApiCode.BAD_REQUEST, "type类型不正确");
		}
		try {
			List<Map<String,Object>> list = consultRecordService.selectConsultRecordByUuidAndConsultType(uuid, type);
			String content = "";
			if(list != null && list.size() > 0 ){
				Map<String,Object> map = list.get(0);
				content = (String)map.get("content");
			}
			
			return ApiResult.right(content);
		} catch (Exception e) {
			log.error("", e);
			 return ApiResult.error(ApiCode.SERVER_ERROR, null);
		}
	}
	
	
	/**
	 * 获取医生未读患者咨询信息
	 *
	 * @param 
	 * @param 
	 * @return
	 * @author 
	 */
	@RequestMapping(value = "/app/service/customer/2.0/getUnreadConsultRecord", method = RequestMethod.GET)
	public @ResponseBody ApiResult getUnreadConsultRecord(@RequestParam("doctorUuid") String doctorUuid) {
		// 判断医生id是否为空
		if (ObjectUtils.isEmpty(doctorUuid)) {
	        return ApiResult.error(ApiCode.BAD_REQUEST, "医生编号不能为空");
	    }
		try {
			return ApiResult.right(caseGroupSerivce.selectUnreadConsultRecord(doctorUuid));
		} catch (Exception e) {
			log.error("", e);
			 return ApiResult.error(ApiCode.SERVER_ERROR, null);
		}
	}
}
