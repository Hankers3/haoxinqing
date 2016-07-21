package com.aebiz.b2b2c.visitprecept.customerdoctorrele.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.visitprecept.customerdoctorrele.vo.CustomerDoctorReleModel;
import com.aebiz.b2b2c.visitprecept.customerdoctorrele.vo.CustomerDoctorReleQueryModel;

public interface CustomerDoctorReleService extends
		BaseService<CustomerDoctorReleModel, CustomerDoctorReleQueryModel> {
	/**
	 * 根据医生编号获取患者数量
	 * 
	 * @param doctorUuid
	 * @return
	 */
	public int getCustomerNumByDoctorUuid(String doctorUuid);

	/**
	 * 根据患者编号获取医生数量
	 * 
	 * @param doctorUuid
	 * @return
	 */
	public int getDoctorNumByCustomerUuid(String customer);

	/**
	 * 根据医生编号获取患者信息
	 * 
	 * @param doctorUuid
	 * @return
	 */
	public List<CustomerModel> getcostomerListByDoctorUuid(String doctorUuid);

	/**
	 * 根据医生编号获取医患关系信息
	 * 
	 * @param doctorUuid
	 * @return
	 */
	public List<CustomerDoctorReleModel> getByDoctorUuid(String doctorUuid);

	/**
	 * 根据医生编号及分组id获取医患关系信息
	 * 
	 * @param doctorUuid
	 * @param groupId
	 * @return
	 */
	public List<CustomerDoctorReleModel> getByDoctorUuidAndGroupId(
			String doctorUuid, String groupId);

	/**
	 * 根据患者的Id获取医生的uuid
	 * 
	 * @param customerid
	 * @return
	 */
	//public String getDoctorUuidByCustomerUuid(String customerid);

	public void deleteByCustomerIdAndGroupId(String customerId, String groupId);

	/**
	 * 判断患者分组下是否有患者
	 * 
	 * @param gid
	 * 
	 * @return
	 */
	public List getByGroupUuid(String gid);

	/**
	 * 通过医生和患者id找到model
	 * 
	 * @param customerUuid
	 * @param doctorUuid
	 * @return
	 */
	public CustomerDoctorReleModel getByCustomerUuidAndDoctorUuid(
			String customerUuid, String doctorUuid);
}
