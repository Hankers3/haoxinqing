package com.aebiz.b2b2c.visitprecept.visitpreceptpush.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.visitprecept.visitpreceptpush.vo.VisitPreceptPushModel;
import com.aebiz.b2b2c.visitprecept.visitpreceptpush.vo.VisitPreceptPushQueryModel;

@Repository
public class VisitPreceptPushH4Impl extends BaseH4Impl<VisitPreceptPushModel, VisitPreceptPushQueryModel>
		implements VisitPreceptPushDAO {
	/**
	 * 通过随访id得到对象
	 * 
	 * @param visitPreceptUuid
	 * @return
	 */
	@Override
	public VisitPreceptPushModel getByVisitPreceptUuid(String doctorUuid,String customerUuid) {
		StringBuffer hql = new StringBuffer(" from VisitPreceptPushModel o where 1=1 ");
		hql.append(" and o.doctorUuid=:doctorUuid ");
		hql.append(" and o.customerUuid=:customerUuid ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("doctorUuid", doctorUuid);
		query.setString("customerUuid", customerUuid);

		List<VisitPreceptPushModel> list = query.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
