package com.aebiz.b2b2c.servicestaff.bankrelation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;

import com.aebiz.b2b2c.servicestaff.bankrelation.service.BankRelationService;
import com.aebiz.b2b2c.servicestaff.bankrelation.dao.BankRelationDAO;
import com.aebiz.b2b2c.servicestaff.bankrelation.vo.BankRelationModel;
import com.aebiz.b2b2c.servicestaff.bankrelation.vo.BankRelationQueryModel;

@Service
@Transactional
public class BankRelationServiceImpl extends
		BaseServiceImpl<BankRelationModel, BankRelationQueryModel> implements
		BankRelationService {
	private BankRelationDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(BankRelationDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(BankRelationModel m) {
		m.setUuid(us.getNextUuid("BankRelation", 10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(BankRelationModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(BankRelationModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 根据医生的Uuid获取医生的所有的银行卡
	 * 
	 * @param doctorUuid
	 * @return
	 */
	@Override
	public List<BankRelationModel> getAllBankByDoctorUuid(String doctorUuid) {
		return myDao.getAllBankByDoctorUuid(doctorUuid);
	}

	/**
	 * 删除绑定的银行卡
	 * 
	 * @param doctorUuid
	 * @param bankUuid
	 */
	@Override
	public void deleteBankCardByDoctorUuidAndBankUuid(String doctorUuid,
			String bankCode) {
		myDao.deleteBankCardByDoctorUuidAndBankUuid(doctorUuid, bankCode);
	}

	/**
	 * 根据医生的Uuid和BankUuid获得银行卡关联的Model
	 * 
	 * @param bankUuid
	 * @param doctorUuid
	 * @return
	 */
	@Override
	public BankRelationModel getModelByDoctorUuidAndBankUuid(String bankUuid,
			String doctorUuid) {
		return myDao.getModelByDoctorUuidAndBankUuid(bankUuid, doctorUuid);
	}

	/**
	 * 根据卡号查询
	 * 
	 * @param bankCode
	 * @return
	 */
	@Override
	public BankRelationModel getByBankCode(String bankCode) {
		return myDao.getByBankCode(bankCode);
	}
}