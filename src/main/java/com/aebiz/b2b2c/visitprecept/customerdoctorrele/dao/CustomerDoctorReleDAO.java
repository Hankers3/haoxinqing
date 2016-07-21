package com.aebiz.b2b2c.visitprecept.customerdoctorrele.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.visitprecept.customerdoctorrele.vo.CustomerDoctorReleModel;
import com.aebiz.b2b2c.visitprecept.customerdoctorrele.vo.CustomerDoctorReleQueryModel;

public interface CustomerDoctorReleDAO extends
		BaseDAO<CustomerDoctorReleModel, CustomerDoctorReleQueryModel> {
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
	public int getDoctorNumByCustomerUuid(String customerUuid);

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
	 * 根据患者的id获取医生的Uuid
	 * 
	 * @param customerid
	 * @return
	 */
	public String getDoctorUuidByCustomerUuid(String customerid);

	public void deleteByCustomerIdAndGroupId(String customerId, String groupId);

	/**
	 * 判断患者分组下是否有患者
	 * 
	 * @param gid
	 * 
	 * @return
	 */
	public List getByGroupUuid(String gid);

	public List<String> getcostomerUuidsByDoctorUuid(String doctorUuid);

	/**
	 * 根据医生编号获取医患关系uuid集合
	 * 
	 * @param doctorUuid
	 * @return
	 */
	public List<String> getUuidsByDoctorUuid(String doctorUuid);

	/**
	 * 根据医生编号获取医患关系uuid集合
	 * 
	 * @param doctorUuid
	 * @return
	 */
	public List<String> getUuidsByGroupUuid(String gid);

	/**
	 * 通过医生和患者id找到UUid
	 * 
	 * @param customerUuid
	 * @param doctorUuid
	 * @return
	 */
	public String getUuidByCustomerUuidAndDoctorUuid(
			String customerUuid, String doctorUuid);

	/**
	 * 通过医生和患者id找到model
	 * 
	 * @param customerUuid
	 * @param doctorUuid
	 * @return
	 */
	public CustomerDoctorReleModel getByCustomerUuidAndDoctorUuid(
			String customerUuid, String doctorUuid);
	
	/**
	 * 得到uuids通过医生id和分组id
	 * 
	 * @param doctorUuid
	 * @param groupId
	 * @return
	 */
	public List<String> getUuidsByDoctorUuidAndGroupId(String doctorUuid,
			String groupId);

	public String getUuidByCustomerIdAndGroupId(String customerId,
			String groupId);
}