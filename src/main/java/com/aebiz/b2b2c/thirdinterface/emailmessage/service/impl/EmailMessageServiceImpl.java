package com.aebiz.b2b2c.thirdinterface.emailmessage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.thirdinterface.emailmessage.dao.EmailMessageDAO;
import com.aebiz.b2b2c.thirdinterface.emailmessage.service.EmailMessageService;
import com.aebiz.b2b2c.thirdinterface.emailmessage.vo.EmailMessageModel;
import com.aebiz.b2b2c.thirdinterface.emailmessage.vo.EmailMessageModelQueryModel;

@Service
@Transactional
public class EmailMessageServiceImpl extends
		BaseServiceImpl<EmailMessageModel, EmailMessageModelQueryModel>
		implements EmailMessageService {
	private EmailMessageDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(EmailMessageDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}
	
	@Override
	public String create(EmailMessageModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}
	
	@Override
	public void update(EmailMessageModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 获取邮件设置信息
	 * 
	 * @return
	 */
	public EmailMessageModel getModel() {
		EmailMessageModelQueryModel qm = new EmailMessageModelQueryModel();
		List<EmailMessageModel> list = this.myDao.getByCondition(qm);
		if(list != null && list.size() > 0){
			EmailMessageModel m = list.get(0);
			if(m != null){
				m.setRepeatPassWord(m.getSmtpPassword());
				return m;
			}
		}
		return null;
	}
}
