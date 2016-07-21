package com.aebiz.b2b2c.websiteoperation.concern.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

import com.aebiz.b2b2c.websiteoperation.concern.vo.ConcernModel;
import com.aebiz.b2b2c.websiteoperation.concern.vo.ConcernQueryModel;

public interface ConcernService extends
		BaseService<ConcernModel, ConcernQueryModel> {

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
	 * 获取关注医生的粉丝数
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

}
