package com.aebiz.b2b2c.servicestaff.doctorimport.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.servicestaff.doctorimport.vo.DoctorImportModel;
import com.aebiz.b2b2c.servicestaff.doctorimport.vo.DoctorImportQueryModel;

public interface DoctorImportDAO extends
		BaseDAO<DoctorImportModel, DoctorImportQueryModel> {
	/**
	 * 根据真实姓名查询 医生信息
	 * 
	 * @param realName
	 * @return
	 */
	public List<DoctorImportModel> getDoctorByRealName(String realName);

	/**
	 * 根据真实姓名查询 医生Uuid集合
	 * 
	 * @param realName
	 * @return
	 */
	public List<String> getDoctorImportUuidByRealName(String realName);
}