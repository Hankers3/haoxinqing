package com.aebiz.b2b2c.websiteoperation.customerdiseasere.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;

import com.aebiz.b2b2c.websiteoperation.customerdiseasere.service.CustomerDiseaseReService;
import com.aebiz.b2b2c.websiteoperation.customerdiseasere.dao.CustomerDiseaseReDAO;
import com.aebiz.b2b2c.websiteoperation.customerdiseasere.vo.CustomerDiseaseReModel;
import com.aebiz.b2b2c.websiteoperation.customerdiseasere.vo.CustomerDiseaseReQueryModel;

@Service
@Transactional
public class CustomerDiseaseReServiceImpl extends BaseServiceImpl<CustomerDiseaseReModel,CustomerDiseaseReQueryModel> implements CustomerDiseaseReService {
	private CustomerDiseaseReDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(CustomerDiseaseReDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(CustomerDiseaseReModel m) {
		m.setUuid(us.getNextUuid("CustomerDiseaseRe",10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(CustomerDiseaseReModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(CustomerDiseaseReModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	/**
	 * 获取附件表信息
	 * @param diseaseUuid
	 * @return
	 */
	@Override
	public CustomerDiseaseReModel getCustomerDiseaseReModel(String diseaseUuid) {
		
		return myDao.getCustomerDiseaseReModel(diseaseUuid);
	}
}