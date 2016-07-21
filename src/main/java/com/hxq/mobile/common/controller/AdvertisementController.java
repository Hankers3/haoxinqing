package com.hxq.mobile.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxq.mobile.common.service.AdvertisementService;
import com.hxq.mobile.util.api.ApiCode;
import com.hxq.mobile.util.api.ApiResult;
import com.wxcommon.util.ObjectUtils;

/**
 * 广告/轮播图
 */
@Controller
public class AdvertisementController {
	Logger log = LoggerFactory.getLogger(AdvertisementController.class);

	@Resource(name = "com.hxq.mobile.common.service.AdvertisementService")
	private AdvertisementService advertisementService;

	/**
	 * 获取讲坛焦点轮播图
	 */
	@RequestMapping(value = "/app/pub/doctor/2.0/getPlatformPic", method = RequestMethod.GET)
	public @ResponseBody ApiResult getPlatformPic(@RequestParam("adUuid") String adUuid) {
		if (ObjectUtils.isEmpty(adUuid)) return ApiResult.error(ApiCode.BAD_REQUEST, "广告Id不能为空");// 判断是否为空

		Map<String, Object> mapReturn = null;
		List<Map<String, Object>> list = null;// 根据广告的id获得讲坛关联图片的集合
		List<Map<String, Object>> relist = new ArrayList<Map<String, Object>>();// 定义返回List

		try {// 获取广告的model
			list = advertisementService.selectAdImgRelByAdId(adUuid);
			if (list != null && list.size() > 0) {
				for (Map<String, Object> pm : list) {
					mapReturn = new HashMap<String, Object>();
					mapReturn.put("imageUuid", pm.get("imageUuid"));// 图片uuid
					mapReturn.put("imageUrl", pm.get("imageUrl"));// 图片的地址
					mapReturn.put("position", pm.get("position"));// 图片位置
					mapReturn.put("imageNote", pm.get("imageNote"));// 图片描述
					mapReturn.put("url", pm.get("url"));// 图片的链接地址
					mapReturn.put("note", pm.get("imageNote"));// 图片的链接地址
					relist.add(mapReturn);
				}
			}
			return ApiResult.right(relist);
		} catch (Exception e) {
			log.error(adUuid, e);
			return ApiResult.error(ApiCode.SERVER_ERROR, e.getMessage());
		}
	}
}
