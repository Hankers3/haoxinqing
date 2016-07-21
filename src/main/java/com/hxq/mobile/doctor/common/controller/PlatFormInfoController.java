package com.hxq.mobile.doctor.common.controller;

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

import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.HttpServletUtils;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.SysConst;
import com.hxq.mobile.common.service.CustomerInfoService;
import com.hxq.mobile.common.service.ServiceStaffInfoService;
import com.hxq.mobile.doctor.common.service.FavoriteInformationSerivce;
import com.hxq.mobile.doctor.common.service.PlatformInfoSerivce;
import com.hxq.mobile.entity.common.Customer;
import com.hxq.mobile.entity.common.CustomerInfo;
import com.hxq.mobile.entity.common.PlatFormInfo;
import com.hxq.mobile.entity.common.PlatformCommunication;
import com.hxq.mobile.entity.common.ServiceStaff;
import com.hxq.mobile.entity.common.ServiceStaffInfo;
import com.hxq.mobile.support.service.ImgUploadService;
import com.hxq.mobile.util.CompatibleTools;
import com.hxq.mobile.util.Image4App;
import com.wxcommon.util.IdentityHelper;
import com.wxcommon.util.ObjectUtils;

@Controller("com.hxq.mobile.doctor.common.controller.PlatFormInfoController")
public class PlatFormInfoController {
	Logger log = LoggerFactory.getLogger(PlatFormInfoController.class);
	
	@Resource(name = "com.hxq.mobile.doctor.common.service.FavoriteInformationSerivce")
	private FavoriteInformationSerivce favoriteInformationSerivce;

	@Resource(name = "com.hxq.mobile.doctor.common.service.PlatformInfoSerivce")
	private PlatformInfoSerivce platformInfoSerivce;
	
    @Resource(name = "com.hxq.mobile.support.service.ImgUploadService")
    private ImgUploadService imgUploadService;

    @Resource(name = "com.hxq.mobile.common.service.ServiceStaffInfoService")
    private ServiceStaffInfoService serviceStaffInfoService;
   
    @Resource(name = "com.hxq.mobile.common.service.CustomerInfoService")
    private CustomerInfoService customerInfoService;
    
    @Autowired
    private FileService fileService;
	
	/**
	 * 通过讲堂名称获得讲堂课程 这里name可以传空
	 */
	@RequestMapping(value = "/app/service/doctor/1.0/getVideosByName", method = RequestMethod.POST)
	public String getVideosByNames(HttpServletRequest request, HttpServletResponse response,
			 	@RequestParam(value ="pageCount",required =false) Integer pageCount,
			    @RequestParam(value ="pageNo",required =false) Integer pageNo) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = CompatibleTools.getParam(request, response, breakOut, new String[] { "callback,true" });
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}

		Object callback = map.get("callback");
		/*医生编号 */
		 String doctorUuid = request.getParameter("doctorUuid");
		// 设置返回信息
		Map<Object, Object> jsonMap = CompatibleTools.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 视频名字和主讲人名字
		String name = "";
		name = request.getParameter("name");

		// 根据名称得到视频列表
		Object[] result = platformInfoSerivce.selectPlatFormInfoByName(name, "1", pageCount, pageNo);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> platFormInfoList = (List<Map<String, Object>>)result[0];
		if (ObjectUtils.isEmpty(platFormInfoList)) {
			result = platformInfoSerivce.selectPlatFormInfoByName(null, "1", pageCount, pageNo);
			platFormInfoList = (List<Map<String, Object>>)result[0];
			jsonMap.put("empty", true);//最新（推荐）
		}
		if(ObjectUtils.isEmpty(platFormInfoList)) {
			HttpServletUtils.outJsonCallBack(response, CompatibleTools.getFailJsonMap(SysConst.FAIL, "无视频信息"), callback);
			return null;
		}
		
		List<Map<String, Object>> relist = new ArrayList<Map<String, Object>>();
		String videoUuid = null;
		String CoMmonId = null;
		Map<String, Object> save = null;
		int scMap = 0;
		int VedNum = 0;
		int CoMmonCount = 0;
		Map<String, Object> collectMap = null;
		Map<String, Object> fileMap = null;

		if (platFormInfoList != null && platFormInfoList.size() > 0) {
			for (Map<String, Object> platFormInfoModel : platFormInfoList) {
				save = new HashMap<String, Object>();
				if(platFormInfoModel.get("uuid") != null){
					videoUuid = (String) platFormInfoModel.get("uuid");// 得到视频主键
					save.put("uuid", videoUuid);
					VedNum = favoriteInformationSerivce.selectFavoriteInformationNumByVedioUuid(videoUuid,"1");
					save.put("collectNum", VedNum);// 收藏视频的人数
				}else{
					save.put("uuid", "");
					save.put("collectNum", VedNum);// 收藏视频的人数
				}
				if(platFormInfoModel.get("uuid") != null){
					CoMmonId = (String) platFormInfoModel.get("uuid");
					CoMmonCount = platformInfoSerivce.selectCommunicationCount(CoMmonId);
					save.put("CoMmonCount", CoMmonCount);// 评论视频的人数
				}else{
					save.put("CoMmonCount", CoMmonCount);// 评论视频的人数
				}
				// 存入收藏标题
				collectMap = ObjectUtils.isEmpty(doctorUuid) ? null :
					favoriteInformationSerivce.selectCollectInformationByid(doctorUuid, videoUuid);
				save.put("favoriteUuid", null !=collectMap ? collectMap.get("uuid") : "");

				// 存入视频标题
				save.put("title", platFormInfoModel.get("videoModel"));
				// 存入视频简介
				save.put("videoIntroduction", platFormInfoModel.get("videoIntroduction"));
				// 存入发布时间
				save.put("createTime", platFormInfoModel.get("createTime"));
				// 存入发布者
				save.put("userName", platFormInfoModel.get("userName"));
				
				if(platFormInfoModel.get("image") != null){
					fileMap = platformInfoSerivce.selectFileMapByRemotePaths((String) platFormInfoModel.get("image"));
				}
				// 简介图片地址
				save.put("img", fileMap.get("remotePaths"));
				// 是否为正在热播视频
				save.put("videoHot", platFormInfoModel.get("videoHot"));

				// 该医生是否收藏该视频 sc 0:未收藏 1：收藏
				if(!ObjectUtils.isEmpty(doctorUuid)){
					scMap = favoriteInformationSerivce.selectCollectInformationByCount(doctorUuid, videoUuid);
					save.put("sc", scMap);
				}else{
					save.put("sc", scMap);
				}
				relist.add(save);
			}
		}
		jsonMap.put("pageNo", result[1]);
		jsonMap.put("totalPage", result[2]);
		jsonMap.put("totalRows", result[3]);
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}
	
	/**
	 * 讲堂视频详情
	 */
	@RequestMapping(value = "/app/service/doctor/1.0/getVideoByUuid", method = RequestMethod.GET)
	public String getVideoByUuid(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("videoUuid") String videoUuid,@RequestParam(value ="pageCount",required =false) Integer pageCount,
		    @RequestParam(value ="pageNo",required =false) Integer pageNo) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = CompatibleTools.getParam(request, response, breakOut,
				new String[] { "callback,true", "videoUuid,true" });
		Object callback = map.get("callback");
		// 设置返回信息
		Map<Object, Object> jsonMap = CompatibleTools.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		if (ObjectUtils.isEmpty(videoUuid)) {
			HttpServletUtils.outJsonCallBack(response, CompatibleTools.getFailJsonMap(SysConst.FAIL, "无视频主键"), callback);
			return null;
		}
		// 根据uuid得到视频信息							   
		PlatFormInfo platFormInfoModel;
		try {
			platFormInfoModel = (PlatFormInfo)platformInfoSerivce.select(new PlatFormInfo(videoUuid));
			if (platFormInfoModel == null) {
				HttpServletUtils.outJsonCallBack(response, CompatibleTools.getFailJsonMap(SysConst.FAIL, "无视频信息"), callback);
				return null;
			}
		/*	if (!ObjectUtils.isEmpty(doctorUuid)) {
	
				// 观看视频课程记录积分
				vipclubIntegralLogService.saveVipIntegralLog(doctorUuid, "add", 0,
						IntegralType.ADD_SHARE_BY_VIDEO.getValue(), VipclubIntegralLogModel.VIPCLUB_USERTYPE_DOC,
						"分享"+platFormInfoModel.getVideoModel()+"视频成功，积分加0", "");
	
			}*/
			Map<String, Object> save = new HashMap<String, Object>();
			save.put("uuid", videoUuid);// 视频主键
			save.put("title", platFormInfoModel.getVideoModel());// 存入视频标题
			save.put("videoIntroduction", platFormInfoModel.getVideoIntroduction());// 存入视频简介
			save.put("createTime", platFormInfoModel.getCreateTime());// 存入发布时间
			save.put("userName", platFormInfoModel.getUserName());// 存入发布者
			save.put("userSynopsis", platFormInfoModel.getUserIntroduce());// 发布者简介
			
			Map<String, Object> platformMap = platformInfoSerivce.selectFileMapByRemotePaths(platFormInfoModel.getImage());  
			if(platformMap != null){
				save.put("img", platformMap.get("remotePaths"));// 简介图片地址
			}else{
				save.put("img", "");// 简介图片地址
			}
			// 视频url
			save.put("videoUrl", platFormInfoModel.getVideoAddress());
			// 是否为正在热播视频
			save.put("videoHot", platFormInfoModel.getVideoHot());
	
			/* ————————视频评论信息———————— */
			// 通过视频主键得到评论信息
			Object[] result = platformInfoSerivce.selectCommunicationByVideoUuid(videoUuid,pageCount, pageNo);
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> platformCommunicationList = (List<Map<String, Object>>)result[0];
			if (platformCommunicationList != null && platformCommunicationList.size() > 0) {
				for (Map<String, Object> platformCommunicationModel : platformCommunicationList) {
					// 存入评论者类型 1:医生 2:患者
					save.put("userType", platformCommunicationModel.get("userType"));
				}
			}
			jsonMap.put("pageNo", result[1]);
			jsonMap.put("totalPage", result[2]);
			jsonMap.put("totalRows", result[3]);
			jsonMap.put("videoInfo", save);// 消息字段
		} catch (Exception e) {
			log.error("", e);
		}
		/* ————————视频评论信息———————— */
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 评论信息
	 */
	@RequestMapping(value = "/app/service/doctor/1.0/getCommunicationList", method = RequestMethod.GET)
	public String getCommunicationList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("videoUuid") String videoUuid,@RequestParam("Uuid") String uuid,
			@RequestParam(value ="pageCount",required =false) Integer pageCount,
		    @RequestParam(value ="pageNo",required =false) Integer pageNo) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = CompatibleTools.getParam(request, response, breakOut,
				new String[] { "callback,false", "videoUuid,true" });
		Object callback = map.get("callback");
		//String doctorUuid = request.getParameter("doctorUuid");
		// 设置返回信息
		Map<Object, Object> jsonMap = CompatibleTools.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		if (ObjectUtils.isEmpty(videoUuid)) {
			HttpServletUtils.outJsonCallBack(response, CompatibleTools.getFailJsonMap(SysConst.FAIL, "无视频主键"), callback);
			return null;
		}
	
		/* ————————视频评论信息———————— */
		// 通过视频主键得到评论信息
		Map<String, Object> comment = null;
		Object[] result = platformInfoSerivce.selectCommunicationByVideoUuid(videoUuid,pageCount, pageNo);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> platformCommunicationList = (List<Map<String, Object>>)result[0];
		if (platformCommunicationList != null && platformCommunicationList.size() > 0) {
			List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
			for (Map<String, Object> platformCommunicationModel : platformCommunicationList) {
				comment = new HashMap<String, Object>();
				// 评论者
				comment.put("questionerName", platformCommunicationModel.get("questionerName"));
				// 评论时间
				comment.put("questionerTime", platformCommunicationModel.get("createTime"));
				// 评论内容
				comment.put("questionerContext", platformCommunicationModel.get("problemDescription"));
				
				if(!ObjectUtils.isEmpty(platformCommunicationModel.get("questionerUuid"))){
					// 根据医生id得到医生姓名
					ServiceStaffInfo serviceStaffInfo = serviceStaffInfoService.selectByServiceStaffUuid((String) platformCommunicationModel.get("questionerUuid"));
					if(!ObjectUtils.isEmpty(serviceStaffInfo)){
						Image4App[] url = CompatibleTools.getImages(imgUploadService,fileService, (String) serviceStaffInfo.getImage());
						if (!ObjectUtils.isEmpty(url)){
							comment.put("headImg", url[0]);//评论用户头像
						}else{
							comment.put("headImg", "");//评论用户头像
						}
					}else{
						comment.put("headImg", "");//评论用户头像
					}
					// 根据患者id得到患者姓名
					CustomerInfo customerInfo = customerInfoService.selectByCustomerUuid((String) platformCommunicationModel.get("questionerUuid"));
					if(!ObjectUtils.isEmpty(customerInfo)){
						Image4App[] url = CompatibleTools.getImages(imgUploadService,fileService, (String) customerInfo.getImage());
						if (!ObjectUtils.isEmpty(url)){
							comment.put("customerHeadImg", url[0]);//评论用户头像
						}else{
							comment.put("customerHeadImg", "");//评论用户头像
						}
					}else{
						comment.put("customerHeadImg", "");//评论用户头像
					}
				}
				reList.add(comment);
			}
			// 视频评论信息
			jsonMap.put("videoComment", reList);
		}	
			
		jsonMap.put("pageNo", result[1]);
		jsonMap.put("totalPage", result[2]);
		jsonMap.put("totalRows", result[3]);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}
	
	
	/**
	 * 添加视频评论
	 */
	@RequestMapping(value = "/app/service/doctor/1.0/addPlatformCommunication", method = RequestMethod.POST)
	public String addPlatformCommunication(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("doctorUuid") String doctorUuid, @RequestParam("platformUuid") String platformUuid,
			@RequestParam("context") String context, @RequestParam("userType") String userType) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		// 标志以及必需要传的参数
		Map<String, String> map = CompatibleTools.getParam(request, response, breakOut,
				new String[] { "callback,true", "doctorUuid,true", "platformUuid,true", "userType,true" });
		Object callback = map.get("callback");

		// 判断是否为空
		if (ObjectUtils.isEmpty(context)) {
			HttpServletUtils.outJsonCallBack(response, CompatibleTools.getFailJsonMap(SysConst.FAIL, "评论内容不能为空"), callback);
			return null;
		}
		// 判断doctorUuid是否为空
		if (ObjectUtils.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, CompatibleTools.getFailJsonMap(SysConst.FAIL, "医生id为空"), callback);
			return null;
		}
		// 判断platformUuid是否为空
		if (ObjectUtils.isEmpty(platformUuid)) {
			HttpServletUtils.outJsonCallBack(response, CompatibleTools.getFailJsonMap(SysConst.FAIL, "视频id为空"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = CompatibleTools.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		String name = null;
		try {
			if ("1".equals(userType)) {
				// 根据医生id得到医生姓名
				ServiceStaff serviceStaff = (ServiceStaff)platformInfoSerivce.select(new ServiceStaff(doctorUuid));
				name = serviceStaff.getRealName();
				if (ObjectUtils.isEmpty(name)) {
					HttpServletUtils.outJsonCallBack(response, CompatibleTools.getFailJsonMap(SysConst.FAIL, "医生姓名为空，添加评论时没有医生姓名"),
							callback);
					return null;
				}
			} else if ("2".equals(userType)) {
				// 根据患者id得到患者姓名
				Customer customer = (Customer)platformInfoSerivce.select(new Customer(doctorUuid));
				name = customer.getCustomerName();
				if (ObjectUtils.isEmpty(name)) {
					HttpServletUtils.outJsonCallBack(response, CompatibleTools.getFailJsonMap(SysConst.FAIL, "患者姓名为空，添加评论时没有患者姓名"),
							callback);
					return null;
				}
			}
	
			// 添加评论信息
			PlatformCommunication platformCommunicationModel = new PlatformCommunication(IdentityHelper.uuid2());
			// 视频uuid
			platformCommunicationModel.setPlatformUuid(platformUuid);
			// 视频名称
			PlatFormInfo platFormInfoModel = (PlatFormInfo) platformInfoSerivce.select(new PlatFormInfo(platformUuid));
			if (ObjectUtils.isEmpty(platFormInfoModel)) {
				HttpServletUtils.outJsonCallBack(response, CompatibleTools.getFailJsonMap(SysConst.FAIL, "视频id有误"), callback);
				return null;
			}
			platformCommunicationModel.setPlatformrName(platFormInfoModel.getVideoModel());
			platformCommunicationModel.setQuestionerName(name);// 提问人名称
			platformCommunicationModel.setUserType(userType);// 用户类型 1:医生 2:患者
			platformCommunicationModel.setConftimeState("1");// 审核通过
			platformCommunicationModel.setQuestionerUuid(doctorUuid);// 提问人编号
			platformCommunicationModel.setProblemDescription(context);// 问题描述
			platformCommunicationModel.setCreateTime(DateFormatHelper.getNowTimeStr());//创建时间
	
			platformInfoSerivce.insert(platformCommunicationModel);
		} catch (Exception e) {
			log.error("", e);
		}

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}
	
}