package com.aebiz.b2b2c.servicestaff.servicestaffcoord.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.servicestaff.servicestaffcoord.vo.ServiceStaffCoordModel;
import com.aebiz.b2b2c.servicestaff.servicestaffcoord.vo.ServiceStaffCoordQueryModel;
import com.aebiz.b2b2c.servicestaff.staffloginstatus.vo.StaffLoginStatusModel;


@Repository
public class ServiceStaffCoordH4Impl extends BaseH4Impl<ServiceStaffCoordModel,ServiceStaffCoordQueryModel> implements ServiceStaffCoordDAO {

	
	/**
	 * 通过家政员编号获取家政员实时坐标信息
	 * @param serviceStaffUuid
	 * @return
	 */
	@Override
	public ServiceStaffCoordModel getByServiceStaffUuid(String serviceStaffUuid) {

		StringBuffer hql = new StringBuffer(
				"from ServiceStaffCoordModel where serviceStaffUuid=:serviceStaffUuid ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("serviceStaffUuid", serviceStaffUuid);

		List<ServiceStaffCoordModel> list = query.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
