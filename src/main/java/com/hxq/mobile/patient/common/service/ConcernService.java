package com.hxq.mobile.patient.common.service;

import java.util.List;
import java.util.Map;

import com.hxq.mobile.entity.common.Concern;
import com.hxq.mobile.util.repository.SimpleEntityService;

public interface ConcernService extends SimpleEntityService {

	/**
	 * 通过患者id得到患者关注医生表
	 * 
	 * @param costomerUuid
	 * @return
	 */
	public List<Map<String, Object>> selectByCustomerUuid(String customerUuid);

	/**
	 * 查看用户是否关注该医生
	 */
	public int selectConcernType(String doctorId, String customerId);

	/**
	 * 获取关注该医生的粉丝数
	 */
	public int selectConcernNumByDoctorId(String doctorId) ;
	/**
	 * 通过医生id 和患者id 查询信息
	 */
	public Concern selectByCustomerAndDoctorUuid(String customerUuid,String doctorUuid);

	public Concern selectConcernByid(String doctorUuid);
}
