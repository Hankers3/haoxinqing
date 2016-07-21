package com.aebiz.b2b2c.cms.channel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;

import com.aebiz.b2b2c.cms.channel.service.ChannelService;
import com.aebiz.b2b2c.cms.channel.dao.ChannelDAO;
import com.aebiz.b2b2c.cms.channel.vo.ChannelModel;
import com.aebiz.b2b2c.cms.channel.vo.ChannelQueryModel;

@Service
@Transactional
public class ChannelServiceImpl extends BaseServiceImpl<ChannelModel,ChannelQueryModel> implements ChannelService {
	private ChannelDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(ChannelDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(ChannelModel m) {
		m.setUuid(us.getNextUuid("channel",10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		m.setCreateTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(ChannelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(ChannelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
}