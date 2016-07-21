package com.aebiz.b2b2c.thirdinterface.mobilemessage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.thirdinterface.mobilemessage.dao.MobileMessageDAO;
import com.aebiz.b2b2c.thirdinterface.mobilemessage.service.MobileMessageService;
import com.aebiz.b2b2c.thirdinterface.mobilemessage.vo.MobileMessageModel;
import com.aebiz.b2b2c.thirdinterface.mobilemessage.vo.MobileMessageQueryModel;

@Service
@Transactional
public class MobileMessageServiceImpl extends
		BaseServiceImpl<MobileMessageModel, MobileMessageQueryModel> implements
		MobileMessageService {
	private MobileMessageDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(MobileMessageDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(MobileMessageModel m) {
		m.setUuid(us.getNextUuid("MobileMessage", 10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(MobileMessageModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(MobileMessageModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 获取邮件设置信息
	 * 
	 * @return
	 */
	public MobileMessageModel getModel() {
		MobileMessageQueryModel qm = new MobileMessageQueryModel();
		List<MobileMessageModel> list = this.myDao.getByCondition(qm);
		if (list != null && list.size() > 0) {
			MobileMessageModel m = list.get(0);
			if (m != null) {
				m.setRepeatPassWord(m.getPassword());
				return m;
			}
		}
		return null;
	}
}