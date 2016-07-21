package com.aebiz.b2b2c.cms.channel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.cms.channel.dao.ChannelDAO;
import com.aebiz.b2b2c.cms.channel.service.ChannelInteractiveService;
import com.aebiz.b2b2c.cms.channel.vo.ChannelModel;
import com.aebiz.b2b2c.cms.channel.vo.ChannelQueryModel;

@Service
@Transactional
public class ChannelInteractiveServiceImpl extends
		BaseServiceImpl<ChannelModel, ChannelQueryModel> implements
		ChannelInteractiveService {

	private ChannelDAO myDao = null;

	@Autowired
	public void setMyDao(ChannelDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}
	
	/**
	 * 获取平台所有频道
	 */
	@Override
	public List<ChannelModel> getChannels() {
		return myDao.getAll();
	}

}