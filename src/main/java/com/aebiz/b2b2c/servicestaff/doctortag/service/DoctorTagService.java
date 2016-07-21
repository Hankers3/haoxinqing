package com.aebiz.b2b2c.servicestaff.doctortag.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.servicestaff.doctortag.vo.DoctorTagModel;
import com.aebiz.b2b2c.servicestaff.doctortag.vo.DoctorTagQueryModel;

public interface DoctorTagService extends
		BaseService<DoctorTagModel, DoctorTagQueryModel> {

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
