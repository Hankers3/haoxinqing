package com.aebiz.b2b2c.visitprecept.casegroup.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.visitprecept.casegroup.vo.CaseGroupModel;
import com.aebiz.b2b2c.visitprecept.casegroup.vo.CaseGroupQueryModel;

public interface CaseGroupService extends BaseService<CaseGroupModel,CaseGroupQueryModel>{

	/**
	 * 通过医生id得到对象
	 * @param doctorUuid
	 * @return
	 */
	public List<CaseGroupModel> getByDoctorUuid(String doctorUuid);

}
