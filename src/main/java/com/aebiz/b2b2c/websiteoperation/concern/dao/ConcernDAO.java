package com.aebiz.b2b2c.websiteoperation.concern.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.websiteoperation.concern.vo.ConcernModel;
import com.aebiz.b2b2c.websiteoperation.concern.vo.ConcernQueryModel;

public interface ConcernDAO extends BaseDAO<ConcernModel, ConcernQueryModel> {
	/**
	 * 通过患者id得到患者关注医生表
	 * 
	 * @param costomerUuid
	 * @return
	 */
	public List<ConcernModel> getByCustomerUuid(String customerUuid);

	/**
	 * 查看患者是否关注该医生
	 * 
	 * @param doctorId
	 * @param customerId
	 * @return
	 */
	public int getConcernType(String doctorId, String customerId);

	/**
	 * 查看关注该医生的粉丝数
	 * 
	 * @param doctorId
	 * @return
	 */
	public int getConcernNumByDoctorId(String doctorId);

	/**
	 * 通过患者id和医生id得到患者关注医生表
	 * 
	 * @param doctorUuid
	 * @param costomerUuid
	 * @return
	 */
	public ConcernModel getByCustomerAndDoctorUuid(String customerUuid,
			String doctorUuid);
	/**
	 * 根据患者的id获取关注的全部的Uuids
	 * @param customerUuid
	 * @return
	 */
	public List<String> getAllConcernUuids(String customerUuid);

}