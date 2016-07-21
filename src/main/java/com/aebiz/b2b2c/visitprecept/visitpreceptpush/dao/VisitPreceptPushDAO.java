package com.aebiz.b2b2c.visitprecept.visitpreceptpush.dao;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.visitprecept.visitpreceptpush.vo.VisitPreceptPushModel;
import com.aebiz.b2b2c.visitprecept.visitpreceptpush.vo.VisitPreceptPushQueryModel;

public interface VisitPreceptPushDAO extends BaseDAO<VisitPreceptPushModel, VisitPreceptPushQueryModel> {
	/**
	 * 通过随访id得到对象
	 * 
	 * @param visitPreceptUuid
	 * @return
	 */
	public VisitPreceptPushModel getByVisitPreceptUuid(String doctorUuid,String customerUuid);

}