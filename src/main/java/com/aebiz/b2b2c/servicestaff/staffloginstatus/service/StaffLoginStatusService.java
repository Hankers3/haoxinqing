package com.aebiz.b2b2c.servicestaff.staffloginstatus.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.servicestaff.staffloginstatus.vo.StaffLoginStatusModel;
import com.aebiz.b2b2c.servicestaff.staffloginstatus.vo.StaffLoginStatusQueryModel;


public interface StaffLoginStatusService extends BaseService<StaffLoginStatusModel,StaffLoginStatusQueryModel>{
	
	/**
	 * 判断该家政员是否登录
	 * @param serviceStaffUuid
	 * @return
	 */
	public boolean serviceStaffLogin(String serviceStaffUuid);

	/**
	 * 通过家政员编号获取该家政员是否登录信息
	 * @param serviceStaffUuid
	 * @return
	 */
	public StaffLoginStatusModel getByServiceStaffUuid(String serviceStaffUuid);
}
