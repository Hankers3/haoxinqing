package com.aebiz.b2b2c.servicestaff.doctorright.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

import com.aebiz.b2b2c.servicestaff.doctorright.vo.DoctorRightModel;
import com.aebiz.b2b2c.servicestaff.doctorright.vo.DoctorRightQueryModel;

public interface DoctorRightService extends BaseService<DoctorRightModel,DoctorRightQueryModel>{
   
	/**
	 * 获取医生的权限设置信息
	 * @param DoctorUuid
	 * @return
	 */
	public DoctorRightModel getByDoctorUuid(String DoctorUuid);
	/**
	 * 获取医生是否开启审核
	 * @param doctorUuid
	 * @return
	 */
	public String getExamByDoctorUuid(String doctorUuid);
}
