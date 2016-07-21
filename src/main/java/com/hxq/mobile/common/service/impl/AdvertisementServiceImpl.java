package com.hxq.mobile.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hxq.mobile.common.service.AdvertisementService;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.util.ObjectUtils;

/**
 * 广告/轮播图
 *
 */
@Service("com.hxq.mobile.common.service.AdvertisementService")
public class AdvertisementServiceImpl extends SpringJdbcSimpleEntityService implements AdvertisementService {

	@Override
	public List<Map<String, Object>> selectAdImgRelByAdId(String adUuid) throws Exception {
		if(ObjectUtils.isEmpty(adUuid)) return null;
		return dao.query(
			"select adUuid,imageUuid,imageUrl,position,imageNote,url from plat_ad_image_rel where adUuid=? order by position asc",
			new Object[]{adUuid}, null, getQueryCache());
	}

	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {
		return null;
	}
	@Override
	protected String getCacheName() {return "h1";}
	@Override
	protected String getQueryCacheName() {return null;}
}