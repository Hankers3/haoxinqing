package com.aebiz.b2b2c.servicestaff.bankrelation.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.servicestaff.bankrelation.vo.BankRelationModel;
import com.aebiz.b2b2c.servicestaff.bankrelation.vo.BankRelationQueryModel;

public interface BankRelationDAO extends
		BaseDAO<BankRelationModel, BankRelationQueryModel> {
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
	 * @return
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
	 * 
	 * @Description: TODO(根据医生的id获取所有的关联的uuid)
	 * @author XP
	 * @param doctorUuid
	 * @return
	 * @date 2015-12-29 上午10:04:36
	 */
	public List<String> getUuidsByDoctorUuid(String doctorUuid);

	/**
	 * 
	 * @Description: (根据医生id和bankUuid获取关联表的uuid)
	 * @author XP
	 * @param bankUuid
	 * @param doctorUuid
	 * @return
	 * @date 2015-12-29 上午10:33:33
	 */
	public String getBankRelationModelUuid(String bankUuid, String doctorUuid);

	/**
	 * 
	 * @Description: (根据医生的id和银行卡卡号获取关联表的uuid)
	 * @author XP
	 * @param doctorUuid
	 * @param bankCode
	 * @return
	 * @date 2015-12-29 上午10:44:27
	 */
	public String getBankRelationUuid(String doctorUuid, String bankCode);

	/**
	 * 根据卡号查询
	 * 
	 * @param bankCode
	 * @return
	 */
	public BankRelationModel getByBankCode(String bankCode);

}