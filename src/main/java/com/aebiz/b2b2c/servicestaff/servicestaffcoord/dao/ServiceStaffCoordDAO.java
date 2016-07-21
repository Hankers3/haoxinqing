package com.aebiz.b2b2c.servicestaff.servicestaffcoord.dao;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.servicestaff.servicestaffcoord.vo.ServiceStaffCoordModel;
import com.aebiz.b2b2c.servicestaff.servicestaffcoord.vo.ServiceStaffCoordQueryModel;

public interface ServiceStaffCoordDAO extends BaseDAO<ServiceStaffCoordModel,ServiceStaffCoordQueryModel>{

	
	/**
	 * 通过家政员编号获取家政员实时坐标信息
	 * @param serviceStaffUuid
	 * @return
	 */
	public ServiceStaffCoordModel getByServiceStaffUuid(String serviceStaffUuid);
	
}