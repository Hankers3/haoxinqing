package com.aebiz.b2b2c.cms.platformcommunication.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;

import com.aebiz.b2b2c.cms.platformcommunication.service.PlatformCommunicationService;
import com.aebiz.b2b2c.cms.platformcommunication.dao.PlatformCommunicationDAO;
import com.aebiz.b2b2c.cms.platformcommunication.vo.PlatformCommunicationModel;
import com.aebiz.b2b2c.cms.platformcommunication.vo.PlatformCommunicationQueryModel;

@Service
@Transactional
public class PlatformCommunicationServiceImpl
		extends
		BaseServiceImpl<PlatformCommunicationModel, PlatformCommunicationQueryModel>
		implements PlatformCommunicationService {
	private PlatformCommunicationDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(PlatformCommunicationDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(PlatformCommunicationModel m) {
		m.setUuid(us.getNextUuid("PlatformCommunication", 10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		// 创建时间
		m.setCreateTime(DateFormatHelper.getNowTimeStr());
		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(PlatformCommunicationModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(PlatformCommunicationModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public String updateState(String uuid, String state) {

		return myDao.updateState(uuid, state);
	}

	/**
	 * 通过视频id得到视频中医生的评论信息
	 * 
	 * @param videoUuid
	 * @return
	 */
	@Override
	public List<PlatformCommunicationModel> getByVideoUuid(String videoUuid) {
		return myDao.getByVideoUuid(videoUuid);
	}
}