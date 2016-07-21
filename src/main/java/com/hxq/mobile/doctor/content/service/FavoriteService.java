package com.hxq.mobile.doctor.content.service;

import java.util.List;
import java.util.Map;

import com.hxq.mobile.util.repository.SimpleEntityService;

public interface FavoriteService extends SimpleEntityService {

	/**
	 * 根据type查询收藏的数据条数
	 * @param doctorUuid
	 * @param uuid
	 * @param type
	 * @return
	 */
	public int selectStoreTypeByDoctorUuidAndType(String doctorUuid,String uuid,String type);
	/**
	 * 关注状态 根据患者id和文章id查关注状态 
	 * @param customerUuid
	 * @param contextUuid
	 * @return
	 */
	public String selectUuidByCustomerUuidAndContextUuid(String customerUuid,String contentUuid);

	/**
	 * 根据id和状态获取收藏关注的数量
	 */
	public int getNumByContentUuid(String contentUuid, String type);
	
	public List<Map<String, Object>> getFavoriteModelListByCustomerUuidAndState(String customerUuid, String state);
}
