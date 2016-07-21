package com.aebiz.b2b2c.thirdinterface.thirdInterfaceTemplate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;

import com.aebiz.b2b2c.thirdinterface.thirdInterfaceTemplate.service.ThirdInterfaceTemplateService;
import com.aebiz.b2b2c.thirdinterface.thirdInterfaceTemplate.dao.ThirdInterfaceTemplateDAO;
import com.aebiz.b2b2c.thirdinterface.thirdInterfaceTemplate.vo.ThirdInterfaceTemplateModel;
import com.aebiz.b2b2c.thirdinterface.thirdInterfaceTemplate.vo.ThirdInterfaceTemplateQueryModel;

@Service
@Transactional
public class ThirdInterfaceTemplateServiceImpl
		extends
		BaseServiceImpl<ThirdInterfaceTemplateModel, ThirdInterfaceTemplateQueryModel>
		implements ThirdInterfaceTemplateService {
	private ThirdInterfaceTemplateDAO myDao = null;

	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(ThirdInterfaceTemplateDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(ThirdInterfaceTemplateModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(ThirdInterfaceTemplateModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(ThirdInterfaceTemplateModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 根据模板id取到对象
	 */
	public ThirdInterfaceTemplateModel getThirdInterfaceTemplateModelByTemplateId(
			String templateId) {
		return myDao.getThirdInterfaceTemplateModelByTemplateId(templateId);
	}
}