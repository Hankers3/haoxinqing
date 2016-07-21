package com.hxq.mobile.patient.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.platforminfo.service.PlatFormInfoService;
import com.aebiz.b2b2c.cms.platforminfo.vo.PlatFormInfoModel;

import com.aebiz.b2b2c.websiteoperation.favorite.vo.FavoriteModel;
import com.hxq.mobile.doctor.content.service.FavoriteService;
import com.hxq.mobile.entity.visit.Content;
import com.hxq.mobile.patient.content.service.ContentService;
import com.hxq.mobile.support.service.ImgUploadService;
import com.hxq.mobile.util.CompatibleTools;
import com.hxq.mobile.util.Image4App;
import com.hxq.mobile.util.api.ApiResult;
import com.wxcommon.util.ObjectUtils;

@Controller("com.hxq.mobile.patient.common.controller.FavoriteController")
public class FavoriteController {

	@Resource(name = "com.hxq.mobile.doctor.content.service.FavoriteService")
	private FavoriteService favoriteService;
	@Resource(name = "com.hxq.mobile.patient.content.service.ContentService")
	private ContentService contentService;
	@Resource
	private PlatFormInfoService platFormInfoService;
	
	/* 图片service */
	@Resource(name = "com.hxq.mobile.support.service.ImgUploadService")
	private ImgUploadService imgService;
	
	@Autowired
	private FileService fileService;
	/**
	 * 根据患者的id获取患者的收藏的列表
	 */
	@RequestMapping(value = "app/customer/patient/2.0/getAllCustomerFavoriteList", method = RequestMethod.GET)
	public  @ResponseBody ApiResult getAllCustomerFavoriteList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("customerUuid") String customerUuid) {
		try {
			Image4App imgUrl = null;
			Image4App[] imgUrls = null;
			// 获取随访方案的集合列表
			List<Map<Object, Object>> relist = new ArrayList<Map<Object, Object>>();
			// 根据患者的uuid和收藏状态获取
			 List<Map<String, Object>> list = favoriteService.getFavoriteModelListByCustomerUuidAndState(customerUuid,FavoriteModel.NOTCANCEL);
	
			if (list != null && list.size() > 0) {
				for (Map<String, Object> fm : list) {
					Map<Object, Object> save = new HashMap<Object, Object>();
					if (fm != null) {
						String contentUuid = (String) fm.get("contentUuid");
						String videoUuid = (String) fm.get("videoUuid");
						if (!StringUtil.isEmpty(contentUuid)) {
							// 获取关注人数
							int foucsNum = favoriteService.getNumByContentUuid(contentUuid, "1");
							Content cm = (Content) contentService.select(new Content(contentUuid));
							if (cm != null) {
								save.put("contentTitle", cm.getContentTitle());
								save.put("introduction", cm.getContentNote());
								save.put("url", cm.getUrl());
								//save.put("imageUrl", cm.getImage());
								imgUrl = new Image4App("", "");	
								if (!ObjectUtils.isEmpty(cm.getImage())) {
									imgUrls = CompatibleTools.getImages(imgService, fileService, (String) cm.getImage());
									if (!ObjectUtils.isEmpty(imgUrls)){
										imgUrl = imgUrls[0];
									}
									if(StringUtil.isEmpty(imgUrl.getSmall()) && !StringUtil.isEmpty(imgUrl.getLarge())){
										imgUrl.setSmall(imgUrl.getLarge());
									}
								}
								save.put("imgUrl", imgUrl);
								
								
								save.put("foucsNum", foucsNum);
								save.put("contentUuid", contentUuid);
								save.put("createTime", cm.getCreateTime());
								/* 类型 1：文章 2：视频 */
								save.put("favoriteType", "1");
							}
						}
						if (!StringUtil.isEmpty(videoUuid)) {
							// 获取关注人数
							int foucsNum = favoriteService.getNumByContentUuid(videoUuid, "2");
							PlatFormInfoModel pim = platFormInfoService.getByUuid(videoUuid);
							// int shareType =
							// doctorShareService.getShareTypeByDoctorUuidAndContenUuid(
							// customerUuid, videoUuid);
							// save.put("shareType", shareType);// 0代表未分享 1代表已分享
							if (pim != null) {
								save.put("contentTitle", pim.getVideoModel());
								save.put("introduction", pim.getVideoIntroduction());
								save.put("url", pim.getVideoAddress());
								save.put("imageUrl", pim.getImageUrl());
								save.put("foucsNum", foucsNum);
								save.put("contentUuid", videoUuid);
								save.put("createTime", pim.getCreateTime());
								/* 类型 1：文章 2：视频 */
								save.put("favoriteType", "2");
							}
						}
						save.put("favoriteUuid", fm.get("uuid"));
						relist.add(save);
					}
				}
			}
			return ApiResult.right(relist);
		} catch (Exception e) {
			return null;
		}
	}
}
