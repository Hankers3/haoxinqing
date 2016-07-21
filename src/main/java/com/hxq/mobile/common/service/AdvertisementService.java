package com.hxq.mobile.common.service;

import java.util.List;
import java.util.Map;

import com.hxq.mobile.util.repository.SimpleEntityService;

/**
 * 广告（轮播）
 *
 */
public interface AdvertisementService extends SimpleEntityService {
	public List<Map<String, Object>> selectAdImgRelByAdId(String adUuid) throws Exception;
}
