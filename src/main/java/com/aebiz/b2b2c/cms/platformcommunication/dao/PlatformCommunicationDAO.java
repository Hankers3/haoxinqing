package com.aebiz.b2b2c.cms.platformcommunication.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.cms.platformcommunication.vo.PlatformCommunicationModel;
import com.aebiz.b2b2c.cms.platformcommunication.vo.PlatformCommunicationQueryModel;

public interface PlatformCommunicationDAO extends
		BaseDAO<PlatformCommunicationModel, PlatformCommunicationQueryModel> {
	
	/**
	 * 修改状态
	 * @param uuid
	 * @param state
	 * @return
	 */
	public String updateState(String uuid, String state);

	/**
	 * 通过视频id得到视频中医生的评论信息
	 * 
	 * @param videoUuid
	 * @return
	 */
	public List<PlatformCommunicationModel> getByVideoUuid(String videoUuid);
	
	/**
	 * 通过视频id得到评论信息
	 * 
	 * @param videoUuid
	 * @return
	 */
	public List<String> getUuidsByVideoUuid(String videoUuid);

}