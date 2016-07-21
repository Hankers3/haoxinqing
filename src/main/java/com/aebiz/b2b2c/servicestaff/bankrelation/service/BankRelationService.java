package com.aebiz.b2b2c.servicestaff.bankrelation.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

import com.aebiz.b2b2c.servicestaff.bankrelation.vo.BankRelationModel;
import com.aebiz.b2b2c.servicestaff.bankrelation.vo.BankRelationQueryModel;

public interface BankRelationService extends
		BaseService<BankRelationModel, BankRelationQueryModel> {

	/**
	 * 根据医生的Uuid获取医生的所有的银行卡
	 * 
	 * @param doctorUuid
	 * @return
	 */
	public List<BankRelationModel> getAllBankByDoctorUuid(String doctorUuid);

	/**
	 * 删除绑定的银行卡
	 * 
	 * @param doctorUuid
	 * @param bankUuid
	 */
	public void deleteBankCardByDoctorUuidAndBankUuid(String doctorUuid,
			String bankCode);

	/**
	 * 根据医生的Uuid和BankUuid获得银行卡关联的Model
	 * 
	 * @param bankUuid
	 * @param doctorUuid
	 * @return
	 */
	public BankRelationModel getModelByDoctorUuidAndBankUuid(String bankUuid,
			String doctorUuid);

	/**
	 * 根据卡号查询
	 * 
	 * @param bankCode
	 * @return
	 */
	public BankRelationModel getByBankCode(String bankCode);

}
