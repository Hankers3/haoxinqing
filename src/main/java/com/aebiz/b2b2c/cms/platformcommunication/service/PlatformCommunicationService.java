package com.aebiz.b2b2c.cms.platformcommunication.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

import com.aebiz.b2b2c.cms.platformcommunication.vo.PlatformCommunicationModel;
import com.aebiz.b2b2c.cms.platformcommunication.vo.PlatformCommunicationQueryModel;

public interface PlatformCommunicationService extends BaseService<PlatformCommunicationModel,PlatformCommunicationQueryModel>{
	public String updateState(String uuid ,String state);

	/**
	 * 通过视频id得到视频中医生的评论信息
	 * @param videoUuid
	 * @return
	 */
	public List<PlatformCommunicationModel> getByVideoUuid(String videoUuid);
}
