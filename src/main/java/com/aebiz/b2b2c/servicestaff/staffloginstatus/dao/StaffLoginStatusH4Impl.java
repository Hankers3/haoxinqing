package com.aebiz.b2b2c.servicestaff.staffloginstatus.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.servicestaff.staffloginstatus.vo.StaffLoginStatusModel;
import com.aebiz.b2b2c.servicestaff.staffloginstatus.vo.StaffLoginStatusQueryModel;


@Repository
public class StaffLoginStatusH4Impl extends BaseH4Impl<StaffLoginStatusModel,StaffLoginStatusQueryModel> implements StaffLoginStatusDAO {

	/**
	 * 通过家政员编号获取该家政员是否登录信息
	 * @param serviceStaffUuid
	 * @return
	 */
	@Override
	public StaffLoginStatusModel getByServiceStaffUuid(String serviceStaffUuid) {
		StringBuffer hql = new StringBuffer(
				"from StaffLoginStatusModel where serviceStaffUuid=:serviceStaffUuid ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("serviceStaffUuid", serviceStaffUuid);

		List<StaffLoginStatusModel> list = query.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
