package com.hxq.mobile.support.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hxq.mobile.entity.common.AppPublish;
import com.hxq.mobile.support.service.AppVersionService;
import com.hxq.mobile.util.api.ApiResult;

/**
 * Created by alice on 2016/2/29 0029
 */
@Controller
@RequestMapping("/version")
public class VersionController2 {
	Logger log = LoggerFactory.getLogger(VersionController2.class);

    @Resource(name = "com.hxq.mobile.support.service.AppVersionService")
    private AppVersionService appVersionService;

    @RequestMapping(value = "app/2.0/{type}", method = RequestMethod.GET)
    public ApiResult getApp(@PathVariable("type") String type) {
    	AppPublish appPublish = null;
		try {
			appPublish = appVersionService.selectVersion(type);
		} catch (Exception e) {
			log.error(type, e);
		}
        return ApiResult.right(appPublish);
    }

    @RequestMapping(value = "data/2.0/{type}/{version}", method = RequestMethod.GET)
    public ApiResult getData(
    		@PathVariable("type") String type,
    		@PathVariable("version") String version) {
    	List<AppPublish> result = null;
		try {
			result = appVersionService.selectLast(type, version);
		} catch (Exception e) {
			log.error(type, e);
		}
		return ApiResult.right(result);
    }
}
