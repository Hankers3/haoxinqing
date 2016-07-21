package com.aebiz.b2b2c.servicestaff.doctortag.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.servicestaff.doctortag.vo.DoctorTagModel;
import com.aebiz.b2b2c.servicestaff.doctortag.vo.DoctorTagQueryModel;

public interface DoctorTagDAO extends
		BaseDAO<DoctorTagModel, DoctorTagQueryModel> {
	/**
	 * 通过医生Uuid得到对象
	 * 
	 * @param doctorUuid
	 * @return
	 */
	public List<String> getByDoctorUuid(String doctorUuid);

	/**
	 * 通过医生Uuid删除对象
	 * 
	 * @param doctorUuid
	 * @return
	 */
	public void deleteByDoctorUuid(String doctorUuid);

}