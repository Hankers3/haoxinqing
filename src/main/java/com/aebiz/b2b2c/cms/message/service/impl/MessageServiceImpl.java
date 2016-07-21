package com.aebiz.b2b2c.cms.message.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.cms.message.dao.MessageDAO;
import com.aebiz.b2b2c.cms.message.service.MessageService;
import com.aebiz.b2b2c.cms.message.vo.MessageModel;
import com.aebiz.b2b2c.cms.message.vo.MessageQueryModel;

@Service
@Transactional
public class MessageServiceImpl extends
		BaseServiceImpl<MessageModel, MessageQueryModel> implements
		MessageService {
	private MessageDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(MessageDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(MessageModel m) {
		m.setUuid(us.getNextUuid("Message", 10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(MessageModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(MessageModel m) {
		super.delete(m);
	}

	/**
	 * 通过consultRecordUuid得到List
	 * 
	 * @param consultRecordUuid
	 * @return
	 */
	@Override
	public List<MessageModel> getByConsultRecordUuid(String consultRecordUuid) {
		return myDao.getByConsultRecordUuid(consultRecordUuid);
	}
}