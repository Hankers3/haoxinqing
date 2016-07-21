package com.aebiz.b2b2c.visitprecept.casegroup.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.visitprecept.casegroup.vo.CaseGroupModel;
import com.aebiz.b2b2c.visitprecept.casegroup.vo.CaseGroupQueryModel;

public interface CaseGroupDAO extends
		BaseDAO<CaseGroupModel, CaseGroupQueryModel> {
	/**
	 * 通过医生id得到对象
	 * 
	 * @param doctorUuid
	 * @return
	 */
	public List<CaseGroupModel> getByDoctorUuid(String doctorUuid);

	/**
	 * 通过医生id得到uuids
	 * 
	 * @param doctorUuid
	 * @return
	 */
	public List<String> getUuidsByDoctorUuid(String doctorUuid);

}