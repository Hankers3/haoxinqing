package com.aebiz.b2b2c.visitprecept.illnessrecord.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;

import com.aebiz.b2b2c.visitprecept.doctoradvice.vo.DoctorAdviceQueryModel;
import com.aebiz.b2b2c.visitprecept.illnessrecord.vo.IllnessRecordModel;
import com.aebiz.b2b2c.visitprecept.illnessrecord.vo.IllnessRecordQueryModel;

@Repository
public class IllnessRecordH4Impl extends
		BaseH4Impl<IllnessRecordModel, IllnessRecordQueryModel> implements
		IllnessRecordDAO {

	/**
	 * 拼接多表查询的对象
	 */
	@Override
	protected String getMultiModel() {

		return " , CustomerModel as c , ServicestaffModel as s  ";
	}

	/**
	 * 拼接查询hql语句
	 */
	@Override
	protected String getAppendHql(IllnessRecordQueryModel qm) {
		StringBuffer hql = new StringBuffer(
				" and o.customerUuid =c.uuid and o.serviceStaffUuid = s.uuid ");

		// 会员名称查询
		if (!StringUtil.isEmpty(qm.getCustomerName())) {
			hql.append(" and c.customerName like:customerName ");
		}

		// 医生名称查询
		if (!StringUtil.isEmpty(qm.getDoctoerName())) {
			hql.append(" and s.serviceStaffName like:doctoerName ");
		}

		return hql.toString();
	}

	/**
	 * 设置查询语句的值
	 */
	@Override
	protected void setAppendHqlValue(IllnessRecordQueryModel qm, Query q) {

		if (!StringUtil.isEmpty(qm.getCustomerName())) {
			q.setString("customerName", "%" + qm.getCustomerName() + "%");
		}

		if (!StringUtil.isEmpty(qm.getDoctoerName())) {
			q.setString("doctoerName", "%" + qm.getDoctoerName() + "%");
		}

	}

	// 根据患者的id获取用户的所有的病情变化
	@Override
	public List<String> getAllNoteByCustomerid(String customerid) {

		StringBuffer hql = new StringBuffer(
				"select o.note from IllnessRecordModel o where o.customerUuid =:customerid order by o.createTime desc ");
		Query q = getH4Session().createQuery(hql.toString());
		q.setParameter("customerid", customerid);
		List list = q.list();
		if ((list != null) && (list.size() > 0)) {
			return list;
		}
		return null;

	}

	/**
	 * 根据随访编号得到病情变化
	 * 
	 * @param visitRecordUuid
	 * @return
	 */
	@Override
	public List<IllnessRecordModel> getByVisitRecordUuid(String visitRecordUuid) {

		StringBuffer hql = new StringBuffer(
				" from IllnessRecordModel o where o.visitRecordUuid =:visitRecordUuid order by o.createTime desc ");
		Query q = getH4Session().createQuery(hql.toString());
		q.setParameter("visitRecordUuid", visitRecordUuid);
		List list = q.list();
		if ((list != null) && (list.size() > 0)) {
			return list;
		}
		return null;

	}
}
