package com.aebiz.b2b2c.servicestaff.staffloginstatus.dao;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.servicestaff.staffloginstatus.vo.StaffLoginStatusModel;
import com.aebiz.b2b2c.servicestaff.staffloginstatus.vo.StaffLoginStatusQueryModel;

public interface StaffLoginStatusDAO extends BaseDAO<StaffLoginStatusModel,StaffLoginStatusQueryModel>{

	
	/**
	 * 通过家政员编号获取该家政员是否登录信息
	 * @param serviceStaffUuid
	 * @return
	 */
	public StaffLoginStatusModel getByServiceStaffUuid(String serviceStaffUuid);
}