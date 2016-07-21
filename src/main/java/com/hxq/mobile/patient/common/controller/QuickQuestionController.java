package com.hxq.mobile.patient.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.api.conrest.Leancloud;
import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.JsonUtils;
import com.alibaba.fastjson.JSONObject;
import com.hxq.mobile.common.service.CustomerInfoService;
import com.hxq.mobile.common.service.CustomerService;
import com.hxq.mobile.common.service.ServiceStaffInfoService;
import com.hxq.mobile.entity.common.ConsultRecord;
import com.hxq.mobile.entity.common.Customer;
import com.hxq.mobile.entity.common.CustomerDoctorRele;
import com.hxq.mobile.entity.common.CustomerInfo;
import com.hxq.mobile.entity.common.ServiceStaffInfo;
import com.hxq.mobile.patient.common.service.ConsultRecordService;
import com.hxq.mobile.patient.visit.service.CustomerDoctorReleService;
import com.hxq.mobile.support.service.ImgUploadService;
import com.hxq.mobile.util.CompatibleTools;
import com.hxq.mobile.util.Image4App;
import com.hxq.mobile.util.api.ApiCode;
import com.hxq.mobile.util.api.ApiResult;
import com.wxcommon.util.IdentityHelper;
import com.wxcommon.util.ObjectUtils;

/**
 * 免费提问的提交接口
 */
@Controller("com.hxq.mobile.patient.common.controller.QuickQuestionController")
public class QuickQuestionController {
	
	Logger log = LoggerFactory.getLogger(QuickQuestionController.class);
	
	/* 会员账户信息service */
	@Resource(name = "com.hxq.mobile.common.service.CustomerService")
	private CustomerService customerService;

	/*患者详细信息service*/
	@Resource(name = "com.hxq.mobile.common.service.CustomerInfoService")
	private CustomerInfoService customerInfoService;
	/*咨询记录表service*/
	@Resource(name = "com.hxq.mobile.patient.common.service.ConsultRecordService")
	private ConsultRecordService consultRecordService;
	
	@Resource(name = "com.hxq.mobile.patient.visit.service.CustomerDoctorReleService")
	private CustomerDoctorReleService customerDoctorReleService;
	
	/* 医生基础信息service */
	@Resource(name = "com.hxq.mobile.common.service.ServiceStaffInfoService")
	private ServiceStaffInfoService servicestaffInfoService;
	
	/* 图片service */
	@Resource(name = "com.hxq.mobile.support.service.ImgUploadService")
	private ImgUploadService imgService;
	
	@Autowired
	private FileService fileService;
	/**
	 * 免费提交提问信息接口
	 * @param request
	 * @param customerUuid
	 * @param content内容
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/customer/patient/1.0/saveQuickQuestion", method = RequestMethod.POST)
	public @ResponseBody ApiResult saveQuickQuestion(HttpServletRequest request, 
		   @RequestParam("customerUuid") String customerUuid,
		   @RequestParam("content") String content,// 病情描述
		   @RequestParam(value ="customerName",required = false) String customerName,// 患者昵称姓名
		   @RequestParam(value ="sex",required = false) String sex,//// 患者性别
		   @RequestParam(value ="age",required = false) String age,//患者年龄
		   @RequestParam(value = "birthday",required = false) String birthday,//出生日期
			HttpServletResponse response) {
		
		String picture = request.getParameter("imageUrl");// 图片地址
		
		String[] pictureStr = null;
		if(!StringUtil.isEmpty(picture)){
			pictureStr = picture.split(",");
		}
		
//		String picture1 = request.getParameter("imageUrl1");// 图片地址
//		String picture2 = request.getParameter("imageUrl2");// 图片地址
//		String picture3 = request.getParameter("imageUrl3");// 图片地址
//		String picture4 = request.getParameter("imageUrl4");// 图片地址
		try {
			// 判断参数是否为空
			if (ObjectUtils.isEmpty(customerUuid)) {
				return ApiResult.error(ApiCode.BAD_REQUEST,"请登录");
			}
			
			Customer customer = (Customer) customerService.select(new Customer(customerUuid));//通过患者id，查询患者信息
			CustomerInfo customerInfo = customerInfoService.selectByCustomerUuid(customerUuid);//患者基础信息
			
			if(ObjectUtils.isEmpty(customer)){
				return ApiResult.error(ApiCode.BAD_REQUEST,"没有该患者信息");
			}
			
			if(!ObjectUtils.isEmpty(customerName)){
				customer.setCustomerName(customerName);
				customerService.update(customer);
			}
	
			if (!ObjectUtils.isEmpty(customerInfo) ) {
				if (!ObjectUtils.isEmpty(age)) {
					customerInfo.setAge(age);//年龄
				}
				if (!ObjectUtils.isEmpty(sex)){
					customerInfo.setSex(sex);//性别
				}
				if (!ObjectUtils.isEmpty(birthday)){
					customerInfo.setBirthday(birthday);//性别
				}
				customerInfoService.update(customerInfo);//更新患者基本信息
			}else{
				customerInfo = new CustomerInfo(IdentityHelper.uuid2());
				
				customerInfo.setCustomerUuid(customerUuid);
				customerInfo.setAge(age);//年龄
				customerInfo.setSex(sex);//性别 1是男；2是女 
				customerInfo.setBirthday(birthday);
				customerInfoService.insert(customerInfo);//插入患者基本信息
			}
			
			// 保存快速提问信息
			ConsultRecord crm = new ConsultRecord(IdentityHelper.uuid2());//记录表
			crm.setCustomerUuid(customerUuid);
			crm.setCreateTime(DateFormatHelper.getNowTimeStr());

			crm.setContent(content);//内容
			crm.setConsultType("0");//0:图文咨询
			crm.setReply("0");// 0: 未回复 1：回复
			crm.setState("0");// 0: 未审核 1：审核 2：审核未通过 加号管理时，作为平台审核
			crm.setIquestion("1");//新问题 
			if(pictureStr != null && pictureStr.length>0){
				for (int i = 0; i < pictureStr.length; i++) {
					if(i==0){
						crm.setPicture1(pictureStr[i]);
					}else if(i==1){
						crm.setPicture2(pictureStr[i]);
					}else if(i==2){
						crm.setPicture3(pictureStr[i]);
					}else {
						break;
					}
				}
			}
			consultRecordService.insert(crm);
			
//			Map<String, Object> map = new HashMap<String, Object>();
//			//判断信息是否填写全
//			if (ObjectUtils.isEmpty(customerInfo.getAge()) 
//				|| ObjectUtils.isEmpty(customer.getCustomerName())
//				|| ObjectUtils.isEmpty(customerInfo.getSex())) {
//				map.put("complate", "false");
//			}else {
//				map.put("complate", "true");
//			}
//			map.put("consultRecordUuid", crm.getUuid());
//			map.put("customerUuid", customer.getUuid());// 患者Uuid
//			map.put("age", customerInfo.getAge());
//			map.put("customerName", customer.getCustomerName());
//			map.put(sex, customerInfo.getSex());
			return ApiResult.right();
			
		} catch (Exception e) {
			log.error("", e);
			return ApiResult.error(ApiCode.SERVER_ERROR,"提交异常！");
		}
	}
	
	/**
	 * 我的提问list
	 * @param request
	 * @param customerUuid 患者id
	 * @param pageCount 条数
	 * @param pageNo 页数
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/customer/patient/1.0/myQuestion", method = RequestMethod.POST)
	public @ResponseBody ApiResult myQuestion(HttpServletRequest request, 
		   @RequestParam("customerUuid") String customerUuid,
		   @RequestParam(value ="pageCount",required = false) Integer pageCount,
		   @RequestParam(value = "pageNo",required = false) Integer pageNo,
			HttpServletResponse response) {
		try {
			String docid;
			Image4App[] imgUrls = null;
			Image4App imgUrl = null;
			//通过患者id查询患者提问的记录
			Object[] result = consultRecordService.selectByCustomerUuidList(customerUuid,pageCount, pageNo);
			
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> list = (List<Map<String, Object>>)result[0];
			List<Map<String, Object>> relist = new ArrayList<>();
			if(list != null){
				for (Map<String, Object> consultRecord : list) {
					Map<String, Object> reMap = new HashMap<>();
					reMap.put("content", consultRecord.get("content"));//内容
					docid = (String) consultRecord.get("doctorUuid");
					reMap.put("doctorUuid", consultRecord.get("doctorUuid"));//医生id：也许是好多个医生id需要用逗号分隔
					
					reMap.put("createTime", consultRecord.get("createTime"));//创建时间
					reMap.put("doctorName",consultRecord.get("realName"));//医生姓名
					reMap.put("consultRecordUuid", consultRecord.get("uuid"));
					if (!ObjectUtils.isEmpty(docid)) {
						//获取医生头像img
						ServiceStaffInfo doctorList = servicestaffInfoService.selectByServiceStaffUuid(docid);
						if (doctorList!=null) {	
								/*if (ObjectUtils.isEmpty(doctorList.getImage()) != true) {
									imgUrls = CompatibleTools.getImages(imgService, fileService, (String) doctorList.getImage());
									if (!ObjectUtils.isEmpty(imgUrls)){
										reMap.put("img", imgUrls[0]);
									}else {
										reMap.put("img", "");
									}
								}else {
									reMap.put("img", "");
								}*/
							imgUrl = new Image4App("", "");	
							if (!ObjectUtils.isEmpty(doctorList.getImage())) {
								imgUrls = CompatibleTools.getImages(imgService, fileService, (String) doctorList.getImage());
								if (!ObjectUtils.isEmpty(imgUrls)){
									imgUrl = imgUrls[0];
								}
								
								if(StringUtil.isEmpty(imgUrl.getSmall()) && !StringUtil.isEmpty(imgUrl.getLarge())){
									imgUrl.setSmall(imgUrl.getLarge());
								}
							}
							reMap.put("img", imgUrl);
						}
					}
					//返回聊天室id
					//返回问题状态
					relist.add(reMap);
				}
			}
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("reList", relist);
			jsonMap.put("pageNo", result[1]);//当前页号
			jsonMap.put("totalPage", result[2]);//总页数
			jsonMap.put("totalRows", result[3]);//总记录数
			return ApiResult.right(jsonMap);
		} catch (Exception e) {
			log.error("", e);
			return ApiResult.error(ApiCode.SERVER_ERROR,"查询异常！");
		}
	}
	
	
	/**
	 * 问题详情 
	 * @param request
	 * @param customerUuid 患者id
	 * @param pageCount 条数
	 * @param pageNo 页数
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/customer/patient/1.0/myQuestionByCustomerAndDoctor", method = RequestMethod.POST)
	public @ResponseBody ApiResult myQuestionByCustomerAndDoctor(HttpServletRequest request, 
		   @RequestParam("customerUuid") String customerUuid,
		   @RequestParam(value ="doctorUuid") String doctorUuid,
			HttpServletResponse response) {
		try {
			
			

			//通过患者id查询患者提问的记录
			List<Map<String, Object>> result = consultRecordService.selectByCustomerUuidAndDoctorUuid(customerUuid,doctorUuid);

			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("questList", result);

			return ApiResult.right(jsonMap);
		} catch (Exception e) {
			log.error("", e);
			return ApiResult.error(ApiCode.SERVER_ERROR,"查询异常！");
		}
	}
	
	
	@RequestMapping(value = "/app/customer/patient/1.0/myGroupIdByCustomerAndDoctor", method = RequestMethod.GET)
	public @ResponseBody ApiResult myGroupIdByCustomerAndDoctor(HttpServletRequest request, 
		   @RequestParam("customerUuid") String customerUuid,
		   @RequestParam(value ="doctorUuid") String doctorUuid,
			HttpServletResponse response) {
		try {

			//通过患者id查询患者提问的记录
			//查询医生患者关系表
			CustomerDoctorRele rele = customerDoctorReleService.selectCustomerDoctorRele(customerUuid, doctorUuid);
			

			String objectId = "";
			
			if(rele == null){
				rele = new CustomerDoctorRele();
				
			
				rele.setUuid(IdentityHelper.uuid2());
				Integer g = 0;
				rele.setGroupUuid(g.toString());
				
				rele.setDoctorUuid(doctorUuid);
				rele.setCustomerUuid(customerUuid);
				rele.setDelFlag("1");
				rele.setCreateTime(DateFormatHelper.getNowTimeStr());
				objectId = getObjectId(customerUuid, doctorUuid);
				
				rele.setObjectId(objectId);
				customerDoctorReleService.insert(rele);
			}else if(StringUtils.isEmpty(rele.getObjectId())){
				objectId = getObjectId(customerUuid, doctorUuid);
				
				rele.setObjectId(objectId);
				
				customerDoctorReleService.update(rele);
			}else{
				objectId = rele.getObjectId();
			}
			
			
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("objectId", objectId);

			return ApiResult.right(jsonMap);
		} catch (Exception e) {
			log.error("", e);
			return ApiResult.error(ApiCode.SERVER_ERROR,"查询异常！");
		}
	}
	
	/**
	 * 创建 leancloud 聊天组id
	 * @param customerUuid
	 * @param doctorUuid
	 * @return
	 * @throws Exception
	 */
	public static String getObjectId(String customerUuid, String doctorUuid) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "leancloud聊天组");
		map.put("m", new String[]{customerUuid,doctorUuid});
		
		String jsonString = JsonUtils.getJSONString(map);
		
		//创建leancloud聊天组
		String param = Leancloud.sendPost(jsonString);
		JSONObject  jasonObject = JSONObject.parseObject(param);
		Map jsonMap = (Map)jasonObject; 
		String groupId = (String)jsonMap.get("objectId");
		
		return groupId;
		
	}
	
	/**
	 * 未读消息
	 * @param request
	 * @param customerUuid 患者id
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/customer/patient/1.0/myQuestionUnread", method = RequestMethod.GET)
	public @ResponseBody ApiResult myQuestionUnread(HttpServletRequest request, 
		   @RequestParam("customerUuid") String customerUuid,
			HttpServletResponse response) {
			
			try {
				Map<String, Object> jsonMap = new HashMap<String, Object>();
				//通过患者id 查询未读消息
				int cr = consultRecordService.selectUnread(customerUuid);
				if (!ObjectUtils.isEmpty(cr)) {
					jsonMap.put("ifreadNum", cr);
				}else {
					jsonMap.put("ifreadNum", "0");
				}
				return ApiResult.right(jsonMap);
			} catch (Exception e) {
				return ApiResult.error(ApiCode.SERVER_ERROR, "请稍后再试！");
			}
	}
	/** 
	 * 评价接口
	 * 医生id，患者id，评价状态
	 */
	@RequestMapping(value = "/app/customer/patient/1.0/assess",method = RequestMethod.POST)
	public @ResponseBody ApiResult assess(HttpServletRequest request,HttpServletResponse response,
			   //@RequestParam("customerUuid") String customerUuid,
			   //@RequestParam("serviceStaffUuid") String serviceStaffUuid,
			   @RequestParam("consultRecordUuid") String consultRecordUuid){
			
				//通过数据库consultRecord 得到医生id 医生姓名
				//数据库加一个字段 满意 不满意
		return null;
	}
	//我的提问
}
