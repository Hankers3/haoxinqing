package com.aebiz.b2b2c.visitprecept.visitpreceptpush.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

import com.aebiz.b2b2c.visitprecept.visitpreceptpush.vo.VisitPreceptPushModel;
import com.aebiz.b2b2c.visitprecept.visitpreceptpush.vo.VisitPreceptPushQueryModel;

public interface VisitPreceptPushService extends BaseService<VisitPreceptPushModel, VisitPreceptPushQueryModel> {
	/**
	 * 随访按周期给患者 进行消息推送
	 */
	public void pushVisitMeaasge();
	/**
	 * 通过随访id得到对象
	 * @param visitPreceptUuid
	 * @param doctorUuid
	 * @param customerUuid
	 * @return
	 */
	public VisitPreceptPushModel getByVisitPreceptUuid(String doctorUuid,String customerUuid);

}
