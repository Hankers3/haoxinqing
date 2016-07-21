package com.aebiz.b2b2c.visitprecept.medicalrecord.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.visitprecept.medicalrecord.vo.MedicalRecordModel;
import com.aebiz.b2b2c.visitprecept.medicalrecord.vo.MedicalRecordQueryModel;

@Repository
public class MedicalRecordH4Impl extends BaseH4Impl<MedicalRecordModel, MedicalRecordQueryModel>
		implements MedicalRecordDAO {

	/**
	 * 关联表查询
	 */
	@Override
	protected String getMultiModel() {

		return ", ServicestaffModel as s, CustomerInfoModel as cim, CustomerModel as cm ";
	}

	/* 添加sql语句 */
	@Override
	protected String getAppendHql(MedicalRecordQueryModel qm) {
		StringBuffer hql = new StringBuffer("");

		hql.append(" and o.doctorUuid = s.uuid and o.customerUuid = cm.uuid and cim.customerUuid = cm.uuid ");
		if(!StringUtil.isEmpty(qm.getSortName()) && !"uuid".equals(qm.getSortName())){
			hql.append(" order by o." + qm.getSortName() + " " + qm.getSortType());
		}else{
			hql.append(" order by o.createTime desc");
		}
		if (!StringUtil.isEmpty(qm.getCustomerMoblie())) {
			hql.append(" and cm.mobile like:customerMoblie ");
		}
		if (!StringUtil.isEmpty(qm.getCustomerqName())) {
			hql.append(" and cim.realName like:customerName ");
		}
		if (!StringUtil.isEmpty(qm.getDoctorMoblie())) {
			hql.append(" and s.mobile like:doctorMoblie ");
		}
		if (!StringUtil.isEmpty(qm.getDoctorqName())) {
			hql.append(" and s.realName like:doctorName ");
		}
		return hql.toString();
	}

	/* 为sql语句赋值 */
	@Override
	protected void setAppendHqlValue(MedicalRecordQueryModel qm, Query q) {
		if (!StringUtil.isEmpty(qm.getCustomerMoblie())) {
			q.setString("customerMoblie", "%" + qm.getCustomerMoblie() + "%");
		}

		if (!StringUtil.isEmpty(qm.getCustomerqName())) {
			q.setString("customerName", "%" + qm.getCustomerqName() + "%");
		}

		if (!StringUtil.isEmpty(qm.getDoctorMoblie())) {
			q.setString("doctorMoblie", "%" + qm.getDoctorMoblie() + "%");
		}

		if (!StringUtil.isEmpty(qm.getDoctorqName())) {
			q.setString("doctorName", "%" + qm.getDoctorqName() + "%");
		}

	}

	/**
	 * 通过患者id得到病历信息
	 * 
	 * @param hid
	 * @return
	 */
	@Override
	public List<MedicalRecordModel> getByCustomerUuid(String hid) {
		if (StringUtil.isEmpty(hid)) {
			return null;
		}
		StringBuffer hql = new StringBuffer(
				" select o from MedicalRecordModel as o where o.customerUuid=:customerUuid ");
		
		hql.append(" order by o.createTime desc ");
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("customerUuid", hid);
		if (q.list().size() > 0 && q.list() != null) {
			return q.list();
		} else {
			return null;
		}
	}

	/**
	 * 根据患者的id获取医生的所有的uuids
	 */
	@Override
	public List<String> getAllDoctorUuidByCustomerUuid(String customerUuid) {
		StringBuffer hql = new StringBuffer(
				"select distinct(o.doctorUuid) from MedicalRecordModel as o where o.customerUuid =:customerUuid order by o.createTime DESC ");

		Query query = getH4Session().createQuery(hql.toString());
		query.setString("customerUuid", customerUuid);

		List list = query.list();
		if (list != null && list.size() > 0)
			return list;
		else
			return null;
	}

	/**
	 * 根据患者和医生id获取病例
	 * 
	 * @param doctorUuid
	 * @param customerUuid
	 * @return
	 */
	@Override
	public List<MedicalRecordModel> getMedicalRecordListByCustomerUuidAndDoctorUuid(String customerUuid,
			String doctorUuid) {
		if (StringUtil.isEmpty(doctorUuid) && StringUtil.isEmpty(customerUuid)) {
			return null;
		}
		StringBuffer hql = new StringBuffer(
				" from MedicalRecordModel as o where o.customerUuid=:customerUuid and o.doctorUuid=:doctorUuid order by o.createTime desc ");
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("customerUuid", customerUuid);
		q.setString("doctorUuid", doctorUuid);
		if (q.list().size() > 0 && q.list() != null) {
			return q.list();
		} else {
			return null;
		}
	}

	/**
	 * 根据患者id得到最新的一天数据
	 * 
	 * @param customerUuid
	 * @return
	 */
	@Override
	public MedicalRecordModel getNewestByCustomerUuidAndDoctorUuid(String customerUuid, String doctorUuid) {
		if (StringUtil.isEmpty(customerUuid)) {
			return null;
		}
		if (StringUtil.isEmpty(doctorUuid)) {
			return null;
		}
		StringBuffer hql = new StringBuffer(
				" from MedicalRecordModel as o where o.customerUuid=:customerUuid and o.doctorUuid=:doctorUuid order by o.createTime desc ");
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("customerUuid", customerUuid);
		q.setString("doctorUuid", doctorUuid);
		if (q.list().size() > 0 && q.list() != null) {
			return (MedicalRecordModel) q.list().get(0);
		} else {
			return null;
		}
	}
}
