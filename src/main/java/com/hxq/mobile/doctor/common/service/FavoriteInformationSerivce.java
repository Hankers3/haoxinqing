package com.hxq.mobile.doctor.common.service;

import java.util.Map;

import com.hxq.mobile.util.repository.SimpleEntityService;

/** 
* @author  作者: 秦靖宇
* @date 
* @version 1.0 
* @parameter  收藏信息
* @since  
* @return  
*/
public interface FavoriteInformationSerivce extends SimpleEntityService{

	Map<String, Object> selectCollectInformationByid(String doctorUuid, String videoUuid);

	int selectCollectInformationByCount(String doctorUuid, String videoUuid);

	int selectStateCollectInformationByCount(String doctorUuid, String videoUuid);

	int selectFavoriteInformationByContentUuid(String contentUuid);

	Object[] selectFavoriteInformationListById(String doctorid, Integer pageCount, Integer pageNo);

	int selectFavoriteInformationNumByVedioUuid(String videoUuid, String userType);
	
	/**
	 * 关注状态 根据患者id和文章id查关注状态 
	 * @param customerUuid
	 * @param contextUuid
	 * @return
	 */
	String selectUuidByCustomerUuidAndContextUuid(String customerUuid,String contextUuid);

	int selectFavoriteNumByContentUuid(String contentUuid);

}
