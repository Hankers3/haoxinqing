package com.hxq.mobile.support.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxq.mobile.entity.common.AppPublish;
import com.hxq.mobile.support.service.AppVersionService;

/**
 * Created by alice on 2016/2/29 0029
 */
@Controller
@RequestMapping("/version")
public class VersionController {
	Logger log = LoggerFactory.getLogger(VersionController.class);

	@Resource(name = "com.hxq.mobile.support.service.AppVersionService")
	private AppVersionService appVersionService;

	@RequestMapping(value = "app/{type}", method = RequestMethod.GET)
	public @ResponseBody AppPublish getApp(@PathVariable("type") String type) {
		AppPublish appPublish = null;
		try {
			appPublish = appVersionService.selectVersion(type);
		} catch (Exception e) {
			log.error(type, e);
		}
		return appPublish;
	}

	@RequestMapping(value = "data/{type}/{version}", method = RequestMethod.GET)
	public @ResponseBody List<AppPublish> getData(@PathVariable("type") String type,
			@PathVariable("version") String version) {
		List<AppPublish> result = null;
		try {
			result = appVersionService.selectLast(type, version);
		} catch (Exception e) {
			log.error(type, e);
		}
		return result;
	}


	@RequestMapping(value = "getAppPushInfo", method = RequestMethod.GET)
	public @ResponseBody Map<String, String> getAppPushInfo(HttpServletRequest request,
			@RequestParam(value="type") String type,@RequestParam(value="version") String version) {
		AppPublish appPublish = null;
		try {
			appPublish = appVersionService.selectVersion(type);
		} catch (Exception e) {
			log.error(type, e);
		}

		Map<String, String> map = new HashMap<String, String>();
		String newest = "false";  //是否是最新版本
		String popup = "false";   //是否弹出信息(IOS用)
		String forceUpdate = "false"; //是否强制更新
		String url = "";   //下载的url
		if (appPublish != null) {
			url = appPublish.getUrl();
			
			//是否弹出信息(IOS用)
			if (appPublish.getPopup().equals("1")) {
				popup = "true";
			}
			//是否强制更新
			if(appPublish.getForceUpdate().equals("1")){
				forceUpdate = "true";
			}
			
			if (!appPublish.getVersion().equals(String.valueOf(version))) {
				appPublish = null;
			} else {
				newest = "true";
				
			}
			
			
		}

		map.put("newest", newest);
		map.put("popup", popup);
		map.put("forceUpdate", forceUpdate);
		map.put("url", url);

		return map;
	}
	
}
