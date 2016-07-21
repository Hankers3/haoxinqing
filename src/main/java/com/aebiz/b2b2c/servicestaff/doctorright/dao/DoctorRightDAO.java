package com.aebiz.b2b2c.servicestaff.doctorright.dao;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.servicestaff.doctorright.vo.DoctorRightModel;
import com.aebiz.b2b2c.servicestaff.doctorright.vo.DoctorRightQueryModel;

public interface DoctorRightDAO extends BaseDAO<DoctorRightModel,DoctorRightQueryModel>{
	/**
	 * 获取医生的权限设置信息
	 * @param DoctorUuid
	 * @return
	 */
	public DoctorRightModel getByDoctorUuid(String doctorUuid);
	/**
	 * 获取医生是否开启审核
	 * @param doctorUuid
	 * @return
	 */
	public String getExamByDoctorUuid(String doctorUuid);
	
	
   

}