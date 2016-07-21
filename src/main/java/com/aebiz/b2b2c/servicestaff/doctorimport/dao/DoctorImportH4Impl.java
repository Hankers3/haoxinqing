package com.aebiz.b2b2c.servicestaff.doctorimport.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;

import com.aebiz.b2b2c.servicestaff.doctorimport.vo.DoctorImportModel;
import com.aebiz.b2b2c.servicestaff.doctorimport.vo.DoctorImportQueryModel;

@Repository
public class DoctorImportH4Impl extends
		BaseH4Impl<DoctorImportModel, DoctorImportQueryModel> implements
		DoctorImportDAO {
	/**
	 * 根据真实姓名查询 医生信息
	 * 
	 * @param realName
	 * @return
	 */
	public List<DoctorImportModel> getDoctorByRealName(String realName) {
		if (StringUtil.isEmpty(realName)) {
			return null;
		}
		StringBuffer hql = new StringBuffer(
				" from DoctorImportModel as o where 1=1 ");
		hql.append(" and o.realName=:realName or o.mobile=:mobile");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("realName", realName);
		q.setString("mobile", realName);
		List<DoctorImportModel> list = q.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public List<String> getDoctorImportUuidByRealName(String realName) {
		if (StringUtil.isEmpty(realName)) {
			return null;
		}
		StringBuffer hql = new StringBuffer(
				" select o.uuid from DoctorImportModel as o where 1=1 ");
		hql.append(" and o.realName like:realName or o.mobile=:mobile");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("realName","%" +realName+"%");
		q.setString("mobile", realName);
		List<String> list = q.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

}
