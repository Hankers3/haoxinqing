package com.hxq.mobile.patient.visit.service;


import java.util.List;
import java.util.Map;

import com.hxq.mobile.entity.common.CustomerDoctorMatterRele;
import com.hxq.mobile.util.repository.SimpleEntityService;

/**
 *	患者提问与医生关联service 
 */
public interface CustomerDoctorMatterReleService extends SimpleEntityService {
	
	/*
	 * 根据医生id和患者id查看  医患是否同时存在
	 */
/*	public CustomerDoctorMatterRele selectCustomerDoctorRele(String customerUuid,String doctorUuid);
	*//**
	 * 根据医生id与分组id得到医患关联数据
	 * @param doctorUuid
	 * @param groupUuid
	 * @return
	 *//*
	public List<CustomerDoctorRele> selectByDoctorUuidAndGroupUuid(String doctorUuid,String groupUuid);
	
	public String selectDoctorid(String uuid);*/
	
	/**
	 * 通过提问id,查询出医生list
	 */
	public List<Map<String, Object>> selectByConsultRecordUuid(String consultRecordUuid);
	
	/**
	 * 添加一条数据
	 * @param cust
	 */
	public int create(CustomerDoctorMatterRele cust);
}
