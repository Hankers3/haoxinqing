package com.aebiz.b2b2c.visitprecept.casecategory.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.visitprecept.casecategory.dao.CaseCategoryDAO;
import com.aebiz.b2b2c.visitprecept.casecategory.service.CaseCategoryService;
import com.aebiz.b2b2c.visitprecept.casecategory.vo.CaseCategoryModel;
import com.aebiz.b2b2c.visitprecept.casecategory.vo.CaseCategoryQueryModel;

@Service
@Transactional
public class CaseCategoryServiceImpl extends BaseServiceImpl<CaseCategoryModel,CaseCategoryQueryModel> implements CaseCategoryService {
	private CaseCategoryDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(CaseCategoryDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(CaseCategoryModel m) {
		m.setUuid(us.getNextUuid("CaseCategory",10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(CaseCategoryModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(CaseCategoryModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 通过id得到名字
	 * @param caseCategoryUuid
	 * @return
	 */
	public String getNameByUuid(String caseCategoryUuid) {
		return myDao.getNameByUuid(caseCategoryUuid);
	}
}