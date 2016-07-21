package com.hxq.mobile.patient.visit.service;


import java.util.List;
import java.util.Map;

import com.hxq.mobile.entity.common.CustomerDoctorRele;
import com.hxq.mobile.util.repository.SimpleEntityService;

/**
 *	医患关联表 
 */
public interface CustomerDoctorReleService extends SimpleEntityService {
	
	/*
	 * 根据医生id和患者id查看  医患是否同时存在
	 */
	public CustomerDoctorRele selectCustomerDoctorRele(String customerUuid,String doctorUuid);
	/**
	 * 根据医生id与分组id得到医患关联数据
	 * @param doctorUuid
	 * @param groupUuid
	 * @return
	 */
	public List<CustomerDoctorRele> selectByDoctorUuidAndGroupUuid(String doctorUuid,String groupUuid);
	
	public String selectDoctorid(String uuid);
	
	/**
	 * 通过患者id 查询医生id
	 */
	public List<Map<String, Object>> selectByDoctorUuid(String customerUuid);
}
