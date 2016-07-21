package com.aebiz.b2b2c.visitprecept.casegroup.service.impl;

import java.util.List;

import com.wxcommon.util.IdentityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.visitprecept.casegroup.dao.CaseGroupDAO;
import com.aebiz.b2b2c.visitprecept.casegroup.service.CaseGroupService;
import com.aebiz.b2b2c.visitprecept.casegroup.vo.CaseGroupModel;
import com.aebiz.b2b2c.visitprecept.casegroup.vo.CaseGroupQueryModel;

@Service
@Transactional
public class CaseGroupServiceImpl extends
		BaseServiceImpl<CaseGroupModel, CaseGroupQueryModel> implements
		CaseGroupService {
	private CaseGroupDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(CaseGroupDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(CaseGroupModel m) {
		m.setUuid(IdentityHelper.uuid2());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(CaseGroupModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(CaseGroupModel m) {
		/*
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
		*/
		super.delete(m);
	}

	/**
	 * 通过医生id得到对象
	 * 
	 * @param doctorUuid
	 * @return
	 */
	@Override
	public List<CaseGroupModel> getByDoctorUuid(String doctorUuid) {
		return myDao.getByDoctorUuid(doctorUuid);
	}
}