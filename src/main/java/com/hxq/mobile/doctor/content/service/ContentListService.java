package com.hxq.mobile.doctor.content.service;

import com.hxq.mobile.entity.common.ContentList;
import com.hxq.mobile.util.repository.SimpleEntityService;

/**
 * 资讯service
 */
public interface ContentListService  extends SimpleEntityService {
	
	public ContentList getContentlistByConditions(String doctorUuid, String contentUuid,String doctorEmail);
	
	
}
