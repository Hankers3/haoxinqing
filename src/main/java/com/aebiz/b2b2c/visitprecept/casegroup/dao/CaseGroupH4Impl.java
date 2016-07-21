package com.aebiz.b2b2c.visitprecept.casegroup.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.visitprecept.casegroup.vo.CaseGroupModel;
import com.aebiz.b2b2c.visitprecept.casegroup.vo.CaseGroupQueryModel;

@Repository
public class CaseGroupH4Impl extends
		BaseH4Impl<CaseGroupModel, CaseGroupQueryModel> implements CaseGroupDAO {
	/**
	 * 通过医生id得到对象
	 * 
	 * @param doctorUuid
	 * @return
	 */
	@Override
	public List<CaseGroupModel> getByDoctorUuid(String doctorUuid) {
		if (StringUtil.isEmpty(doctorUuid)) {
			return null;
		}
		StringBuffer hql = new StringBuffer(
				" select o from CaseGroupModel as o where o.doctorUuid=:doctorUuid order by opeTime desc");
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("doctorUuid", doctorUuid);
		if (q.list().size() > 0 && q.list() != null) {
			return q.list();
		} else {
			return null;
		}
	}

	@Override
	public List<String> getUuidsByDoctorUuid(String doctorUuid) {
		if (StringUtil.isEmpty(doctorUuid)) {
			return null;
		}
		StringBuffer hql = new StringBuffer(
				" select o.uuid from CaseGroupModel as o where o.doctorUuid=:doctorUuid ");
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("doctorUuid", doctorUuid);
		if (q.list().size() > 0 && q.list() != null) {
			return q.list();
		} else {
			return null;
		}
	}

}
