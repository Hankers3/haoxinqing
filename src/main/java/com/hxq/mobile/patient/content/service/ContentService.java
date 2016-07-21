package com.hxq.mobile.patient.content.service;


import java.util.List;
import java.util.Map;


import com.hxq.mobile.util.repository.SimpleEntityService;

public interface ContentService extends SimpleEntityService {
	/**
	 * 资讯的Service
	 */
	Object[] selectAllHeartList(String contentCategoryUuid,Integer pageCount, Integer pageNo);
    
	/**
	 * 资讯的Service
	 */
	List<Map<String,Object>> selectAllContentList(String contentCategoryUuid);
    
	/**
	 * 资讯的Service
	 */
    Map<String,Object> selectContentByUuid(String contentUuid);
}
