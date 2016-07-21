package com.aebiz.b2b2c.visitprecept.visitapply.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.visitprecept.visitapply.vo.VisitApplyModel;
import com.aebiz.b2b2c.visitprecept.visitapply.vo.VisitApplyQueryModel;

public interface VisitApplyService extends BaseService<VisitApplyModel,VisitApplyQueryModel>{

	/**
	 * 根据医生的ID获取随访申请的list集合
	 * @param doctorid
	 * @return
	 */
	public  List<VisitApplyModel> getAllListByDoctorId(String doctorid);
	
	/**
	 * 获取患者编号
	 * @param preceptUuid
	 * @return
	 */
	public List<String> getCustomerIds(String preceptUuid);

	/**
	 * 根据随访方案获取随访申请关联的人数
	 */
	int getCustomerNumByPreceptUuid(String doctorUuid,String preceptUuid);

	List<VisitApplyModel> getByPreceptUuid(String doctorUuid,String preceptUuid);
}
