package com.hxq.mobile.patient.heartoxygenbar.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxq.mobile.common.service.CustomerInfoService;
import com.hxq.mobile.common.service.ServiceStaffInfoService;
import com.hxq.mobile.doctor.common.service.FavoriteInformationSerivce;
import com.hxq.mobile.doctor.common.service.PlatformInfoSerivce;
import com.hxq.mobile.support.service.ImgUploadService;
import com.hxq.mobile.util.api.ApiCode;
import com.hxq.mobile.util.api.ApiResult;
import com.wxcommon.util.ObjectUtils;

@Controller("com.hxq.mobile.patient.heartoxygenbar.controller.OnlineVideoController")
public class OnlineVideoController {
	Logger log = LoggerFactory.getLogger(OnlineVideoController.class);
	
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
    
 
	
	/**
	 * 心灵氧吧-在线视频-查询接口
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/app/customer/patientOfHeart/2.0/getVideos", method = RequestMethod.POST)
	public @ResponseBody ApiResult getVideosByNames(HttpServletRequest request,
				@RequestParam("customerUuid") String customerUuid,
			 	@RequestParam(value ="pageCount",required =false) Integer pageCount,
			    @RequestParam(value ="pageNo",required =false) Integer pageNo) {

		//患者id是否为空
		if(ObjectUtils.isEmpty(customerUuid)) {
			return ApiResult.error(ApiCode.BAD_REQUEST, "患者id为空!");
		}
		
		Map<Object, Object> jsonMap = new HashMap<Object, Object>();
		
		try {
			// 根据名称得到视频列表
			Object[] result = platformInfoSerivce.selectPlatFormInfoByName("", "0", pageCount, pageNo);
			
			List<Map<String, Object>> platFormInfoList = (List<Map<String, Object>>)result[0];
			if (ObjectUtils.isEmpty(platFormInfoList)) {
				result = platformInfoSerivce.selectPlatFormInfoByName(null, "0", pageCount, pageNo);
				platFormInfoList = (List<Map<String, Object>>)result[0];
				jsonMap.put("empty", true);//最新（推荐）
			}
			if(ObjectUtils.isEmpty(platFormInfoList)) {
				return ApiResult.error(ApiCode.BAD_REQUEST, "无视频信息!");
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
						VedNum = favoriteInformationSerivce.selectFavoriteInformationNumByVedioUuid(videoUuid,"2");
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
					collectMap = ObjectUtils.isEmpty(customerUuid) ? null :
						favoriteInformationSerivce.selectCollectInformationByid(customerUuid, videoUuid);
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
						// 简介图片地址
						save.put("img", fileMap.get("remotePaths"));
					}else{
						// 简介图片地址
						save.put("img", "");
					}
					
					// 是否为正在热播视频
					save.put("videoHot", platFormInfoModel.get("videoHot"));

					// 该医生是否收藏该视频 sc 0:未收藏 1：收藏
					if(!ObjectUtils.isEmpty(customerUuid)){
						scMap = favoriteInformationSerivce.selectCollectInformationByCount(customerUuid, videoUuid);
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
		} catch (Exception e) {
			log.error(e.getMessage());
			return ApiResult.error(ApiCode.SERVER_ERROR,"Internal Server Error");
		}
		
		return ApiResult.right(jsonMap);
	}
	
}