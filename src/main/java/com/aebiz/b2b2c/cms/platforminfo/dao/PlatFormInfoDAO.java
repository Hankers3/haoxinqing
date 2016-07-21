package com.aebiz.b2b2c.cms.platforminfo.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.cms.platforminfo.vo.PlatFormInfoModel;
import com.aebiz.b2b2c.cms.platforminfo.vo.PlatFormInfoQueryModel;

public interface PlatFormInfoDAO extends
		BaseDAO<PlatFormInfoModel, PlatFormInfoQueryModel> {

	/**
	 * 通过医生id得到预约课程
	 * 
	 * @param userId
	 * @return
	 */
	public List<PlatFormInfoModel> getByUserid(String userId);

	/**
	 * 通过医生id得到预约课程Uuid
	 * 
	 * @param userId
	 * @return
	 */
	public List<String> getUuidsByUserid(String userId);
	
	/**
	 * 通过名称得到视频
	 * @param videoType 
	 * @param userName 
	 * 
	 * @return
	 */
	public List<PlatFormInfoModel> getByName(String name, String videoType);
	/**
     * 根据视频的UUid获取该视频的开讲时间
     * @param vidoUuid
     * @return
     */
    public String getStartTimeByVidoUuid(String vidoUuid);

}