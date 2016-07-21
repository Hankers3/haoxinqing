package com.aebiz.b2b2c.cms.interactive.innermessage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageModel;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageQueryModel;
import com.aebiz.b2b2c.cms.interactive.innermessage.dao.InnerMessageInteractiveDAO;
import com.aebiz.b2b2c.cms.interactive.innermessage.service.InnerMessageInteractiveService;
import com.aebiz.b2b2c.cms.interactive.innermessage.vo.InnerMessageInteractiveModel;

@Service
@Transactional
public class InnerMessageInteractiveServiceImpl extends
		BaseServiceImpl<InnerMessageModel, InnerMessageQueryModel> implements
		InnerMessageInteractiveService {
	private InnerMessageInteractiveDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(InnerMessageInteractiveDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	/**
	 * 对外接口：获取当前会员所有未读消息
	 */
	@Override
	public List<InnerMessageInteractiveModel> getUnreadMessageList() {
		return this.myDao.getUnreadMessageList(LoginUserHelper
				.getCustomerLoginUserUuid());
	}

	/**
	 * 对外接口：获取会员所有未读消息数
	 */
	@Override
	public int getUnreadMessageCount() {
		return this.myDao.getUnreadMessageCount(LoginUserHelper
				.getCustomerLoginUserUuid());
	}

}