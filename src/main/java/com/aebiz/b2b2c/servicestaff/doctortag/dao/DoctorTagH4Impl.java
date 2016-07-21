package com.aebiz.b2b2c.servicestaff.doctortag.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.servicestaff.doctortag.vo.DoctorTagModel;
import com.aebiz.b2b2c.servicestaff.doctortag.vo.DoctorTagQueryModel;

@Repository
public class DoctorTagH4Impl extends BaseH4Impl<DoctorTagModel, DoctorTagQueryModel> implements DoctorTagDAO {
	/**
	 * 通过医生Uuid得到对象
	 * 
	 * @param doctorUuid
	 * @return
	 */
	@Override
	public List<String> getByDoctorUuid(String doctorUuid) {
		StringBuffer hql = new StringBuffer(" select o.tagUuid from DoctorTagModel as o where 1=1 ");
		hql.append(" and o.doctorUuid =:doctorUuid ");
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("doctorUuid", doctorUuid);
		List<String> list = q.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 通过医生Uuid删除对象
	 * 
	 * @param doctorUuid
	 * @return
	 */
	@Override
	public void deleteByDoctorUuid(String doctorUuid) {
		StringBuffer hql = new StringBuffer(" delete DoctorTagModel as o where 1=1 ");
		hql.append(" and o.doctorUuid =:doctorUuid ");
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("doctorUuid", doctorUuid);
		q.executeUpdate();
	}

}
