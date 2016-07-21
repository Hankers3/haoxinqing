package com.hxq.mobile.doctor.common.service;

import java.util.Map;

import com.hxq.mobile.util.repository.SimpleEntityService;

/** 
* @author  作者: 秦靖宇
* @date 
* @version 1.0 
* @parameter  
* @since  
* @return  
*/
public interface PlatformInfoSerivce extends SimpleEntityService{

	Object[] selectPlatFormInfoByName(String name, String videoType, Integer pageCount,Integer pageNo);

	Map<String, Object> selectFileMapByRemotePaths(String imgId);

	int selectCommunicationCount(String coMmonId);

	Object[] selectCommunicationByVideoUuid(String videoUuid, Integer pageCount,Integer pageNo);
	
}
