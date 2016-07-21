package com.aebiz.b2b2c.cms.remindsub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;

import com.aebiz.b2b2c.cms.remindsub.service.RemindSubService;
import com.aebiz.b2b2c.cms.remindsub.dao.RemindSubDAO;
import com.aebiz.b2b2c.cms.remindsub.vo.RemindSubModel;
import com.aebiz.b2b2c.cms.remindsub.vo.RemindSubQueryModel;

@Service
@Transactional
public class RemindSubServiceImpl extends BaseServiceImpl<RemindSubModel,RemindSubQueryModel> implements RemindSubService {
	private RemindSubDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(RemindSubDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(RemindSubModel m) {
		m.setUuid(us.getNextUuid("RemindSub",10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(RemindSubModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(RemindSubModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	
	/**
	 * 保存商户或者会员消息的设置，需要删除以往设置记录,如果还未设置就不删除
	 * @param accountUuid
	 * @param messageUuid
	 * @param remindSubUuid
	 * @param mobileSend
	 * @param emailSend
	 * @param innerSend
	 */
	public void saveRemindSubModel(String accountUuid, String messageUuid,String remindSubUuid,String mobileSend, String emailSend, String innerSend) {
		RemindSubModel remindSubModel=myDao.getByUuid(remindSubUuid);
		if(remindSubModel==null){
			RemindSubModel model=new RemindSubModel();
			model.setAccountUuid(accountUuid);
			model.setMessageRemindUuid(messageUuid);
			model.setMobileSend(mobileSend);
			model.setEmailSend(emailSend);
			model.setInnerSend(innerSend);
			this.create(model);
		}
		else{
			myDao.deleteAllRemindSubByAccountUuid(accountUuid);
			RemindSubModel model=new RemindSubModel();
			model.setAccountUuid(accountUuid);
			model.setMessageRemindUuid(messageUuid);
			model.setMobileSend(mobileSend);
			model.setEmailSend(emailSend);
			model.setInnerSend(innerSend);
			this.create(model);
		}
	}
}