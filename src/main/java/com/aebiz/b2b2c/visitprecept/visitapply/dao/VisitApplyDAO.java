package com.aebiz.b2b2c.visitprecept.visitapply.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.visitprecept.visitapply.vo.VisitApplyModel;
import com.aebiz.b2b2c.visitprecept.visitapply.vo.VisitApplyQueryModel;

public interface VisitApplyDAO extends BaseDAO<VisitApplyModel,VisitApplyQueryModel>{

	/**
	 * 根据医生ID查找随访申请的list的接口
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