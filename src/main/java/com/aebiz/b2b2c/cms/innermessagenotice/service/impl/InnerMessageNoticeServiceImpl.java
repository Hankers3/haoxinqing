package com.aebiz.b2b2c.cms.innermessagenotice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.cms.innermessagenotice.dao.InnerMessageNoticeDAO;
import com.aebiz.b2b2c.cms.innermessagenotice.service.InnerMessageNoticeService;
import com.aebiz.b2b2c.cms.innermessagenotice.vo.InnerMessageNoticeModel;
import com.aebiz.b2b2c.cms.innermessagenotice.vo.InnerMessageNoticeQueryModel;


@Service
@Transactional
public class InnerMessageNoticeServiceImpl extends BaseServiceImpl<InnerMessageNoticeModel,InnerMessageNoticeQueryModel> implements InnerMessageNoticeService {
	private InnerMessageNoticeDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(InnerMessageNoticeDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(InnerMessageNoticeModel m) {
		m.setUuid(us.getNextUuid("InnerMessageNotice",10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(InnerMessageNoticeModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(InnerMessageNoticeModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 通过接收人编号获取通知
	 * @param userId
	 * @return
	 */
	@Override
	public List<InnerMessageNoticeModel> getByUserId(String userId,int pageCount,int pageNo) {
		return myDao.getByUserId(userId, pageNo, pageNo);
	}

	@Override
	public InnerMessageNoticeModel getInnerMessageNoticeModel(
			String messageUuid, String userId) {
		
		return myDao.getInnerMessageNoticeModel(messageUuid, userId);
	}

	
	/**
	 * 根据用户Id获取未读消息列表 
	 * @param userId
	 * @return
	 * hedongfei
	 */
	@Override
	public List<InnerMessageNoticeModel> getInnerMessageListByUserId(
			String userId, String readStatus, int pageCount, int pageNo) {
		
		return myDao.getInnerMessageListByUserId(userId, readStatus, pageCount, pageNo);
	}
}