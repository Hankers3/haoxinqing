package com.hxq.mobile.common.service;

import java.util.List;
import java.util.Map;

import com.hxq.mobile.entity.common.QueryUtil;
import com.hxq.mobile.entity.common.ServiceStaff;
import com.hxq.mobile.entity.common.ServiceStaffInfo;
import com.hxq.mobile.util.repository.SimpleEntityService;

/**
 * 医生注册信息
 *
 */
public interface ServiceStaffService extends SimpleEntityService {
	/**
	 * 获得名医
	 * 
	 * @return
	 */
	List<Map<String,Object>> selectFamousDoctors();

	/**
	 * 通过查询条件得到医生List
	 * 
	 * @return
	 */
	Object[] selectDoctors(Map<String, Object> form);
	
	/**
	 * 查询出符合条件的医生数据
	 * @param serviceStaff
	 * @return
	 */
	List<ServiceStaff> queryServiceStaff(ServiceStaff serviceStaff,QueryUtil query);

	/**
	 * 查询符合条件的数据总条数
	 * @param serviceStaff
	 * @return
	 */
	long queryCount(ServiceStaff serviceStaff);
	/**
	 * 修改、补充医生信息
	 * @param doctor
	 * @param info
	 * @return
	 * @throws Exception
	 */
	int updateDoctorAndInfo(ServiceStaff doctor, ServiceStaffInfo info) throws Exception;

	/**
	 * 更新医生信息
	 * @param doctor
	 * @return
	 * @throws Exception
	 */
	int updateDoctor(ServiceStaff doctor) throws Exception;
	
	/**
	 * 通过手机获取
	 * @param doctor
	 * @param info
	 * @return
	 * @throws Exception
	 */
	ServiceStaff selectServiceStaffModelByMobile(String mobile) throws Exception;
	
	/**
	 * 根据uuid获取医生信息
	 * @param uuid
	 * @return
	 * @throws Exception
	 */
	ServiceStaff getServiceStaffByUuid(String uuid) throws Exception;
	
}
