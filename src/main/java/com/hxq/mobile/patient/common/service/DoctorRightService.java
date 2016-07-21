package com.hxq.mobile.patient.common.service;

import com.hxq.mobile.entity.common.DoctorRight;
import com.hxq.mobile.util.repository.SimpleEntityService;

/**
 * 医生权限service
 *
 */
public interface DoctorRightService extends SimpleEntityService{
	/**
	 * 获取医生的权限设置信息
	 * @param DoctorUuid
	 * @return
	 */
	public DoctorRight selectDoctorRight(String DoctorUuid);
}
