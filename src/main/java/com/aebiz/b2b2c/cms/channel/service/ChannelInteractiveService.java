package com.aebiz.b2b2c.cms.channel.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

import com.aebiz.b2b2c.cms.channel.vo.ChannelModel;
import com.aebiz.b2b2c.cms.channel.vo.ChannelQueryModel;

public interface ChannelInteractiveService extends BaseService<ChannelModel,ChannelQueryModel>{
	
	/**
	 * 获取平台所有频道
	 * 
	 * @return
	 */
	public List<ChannelModel> getChannels();
}
