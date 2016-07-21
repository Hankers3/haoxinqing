package com.aebiz.b2b2c.visitprecept.doctoradvice.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;
import com.aebiz.b2b2c.visitprecept.doctoradvice.vo.DoctorAdviceModel;
import com.aebiz.b2b2c.visitprecept.doctoradvice.vo.DoctorAdviceQueryModel;

@Repository
public class DoctorAdviceH4Impl extends
		BaseH4Impl<DoctorAdviceModel, DoctorAdviceQueryModel> implements
		DoctorAdviceDAO {

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
	protected String getAppendHql(DoctorAdviceQueryModel qm) {
		StringBuffer hql = new StringBuffer(
				" and o.customerUuid =c.uuid and o.serviceStaffUuid = s.uuid ");

		// 会员名称查询
		if (!StringUtil.isEmpty(qm.getCustomerName())) {
			hql.append(" and c.customerName like:customerName ");
		}

		// 医生名称查询
		if (!StringUtil.isEmpty(qm.getServiceStaffName())) {
			hql.append(" and s.serviceStaffName like:serviceStaffName ");
		}

		return hql.toString();
	}

	/**
	 * 设置查询语句的值
	 */
	@Override
	protected void setAppendHqlValue(DoctorAdviceQueryModel qm, Query q) {

		if (!StringUtil.isEmpty(qm.getCustomerName())) {
			q.setString("customerName", "%" + qm.getCustomerName() + "%");
		}

		if (!StringUtil.isEmpty(qm.getServiceStaffName())) {
			q.setString("serviceStaffName", "%" + qm.getServiceStaffName()
					+ "%");
		}

	}

	/**
	 * 根据治疗方案Uuid 获取治疗方案
	 * 
	 * @param uuid
	 * @param type
	 * @return
	 */
	public DoctorAdviceModel getByPreceptUuid(String uuid, String type) {
		StringBuffer hql = new StringBuffer(
				" from DoctorAdviceModel as o where o.uuid =:uuid and o.type=:type order by o.createTime");
		Query query = getH4Session().createQuery(hql.toString());
		query.setString("uuid", uuid);
		query.setString("type", type);
		List list = query.list();
		if (list != null && list.size() > 0)
			return (DoctorAdviceModel) list.get(0);
		else
			return null;
	}

	/**
	 * 通过随访记录Uuid获取治疗方案
	 * 
	 * @param uuid
	 * @return
	 */
	public List<DoctorAdviceModel> getByVisitRecordUuid(String visitRecordUuid,
			String type) {
		StringBuffer hql = new StringBuffer(
				" from DoctorAdviceModel as o where o.visitRecordUuid =:visitRecordUuid and o.type=:type order by o.createTime");
		Query query = getH4Session().createQuery(hql.toString());
		query.setString("visitRecordUuid", visitRecordUuid);
		query.setString("type", type);
		List list = query.list();
		if (list != null && list.size() > 0)
			return (List<DoctorAdviceModel>) list;
		else
			return null;

	}

	/**
	 * 获取所有给这个患者医嘱的医生的Uuid
	 * 
	 * @param customerUuid
	 * @return
	 */
	@Override
	public List<String> getAllDoctorUuidByCustomerUuid(String customerUuid) {
		StringBuffer hql = new StringBuffer(
				"select distinct(o.serviceStaffUuid) from DoctorAdviceModel as o where o.customerUuid =:customerUuid order by o.createTime DESC ");

		Query query = getH4Session().createQuery(hql.toString());
		query.setString("customerUuid", customerUuid);
		List list = query.list();
		if (list != null && list.size() > 0)
			return list;
		else
			return null;

	}

	/**
	 * 根据医生和患者的id得到医嘱
	 * 
	 * @param customerUuid
	 * @param doctorId
	 * @return
	 */
	@Override
	public DoctorAdviceModel getModelByDoctorIdAndCustomerUuid(
			String customerUuid, String doctorId) {
		StringBuffer hql = new StringBuffer(
				" from DoctorAdviceModel as o where o.customerUuid =:customerUuid and o.serviceStaffUuid=:doctorId order by o.createTime DESC");
		Query query = getH4Session().createQuery(hql.toString());
		query.setString("customerUuid", customerUuid);
		query.setString("doctorId", doctorId);
		List list = query.list();
		if (list != null && list.size() > 0)
			return (DoctorAdviceModel) list.get(0);
		else
			return null;
	}

	/**
	 * 根据医生和患者的id获取医生建议的model
	 * 
	 * @param doctorUuid
	 * @param customerUuid
	 * @return
	 */
	@Override
	public List<DoctorAdviceModel> getDoctorAdivceListByCustomerUuidAndDoctorUuid(
			String doctorUuid, String customerUuid) {
		StringBuffer hql = new StringBuffer(
				" from DoctorAdviceModel as o where o.customerUuid =:customerUuid and o.serviceStaffUuid=:doctorId order by o.createTime desc ");
		Query query = getH4Session().createQuery(hql.toString());
		query.setString("customerUuid", customerUuid);
		query.setString("doctorId", doctorUuid);
		List list = query.list();
		if (list != null && list.size() > 0)
			return list;
		else
			return null;
	}
	
	
	/**
	 * 根据医生和患者的id获取医生建议的model
	 * 
	 * @param doctorUuid
	 * @param customerUuid
	 * @return
	 */
	public List<DoctorAdviceModel>  getAllDoctorAdivceListByCustomerUuid(String customerUuid){
		StringBuffer hql = new StringBuffer(
				" from DoctorAdviceModel as o where o.customerUuid =:customerUuid group  by o.serviceStaffUuid order by o.createTime desc ");
		Query query = getH4Session().createQuery(hql.toString());
		query.setString("customerUuid", customerUuid);
		List list = query.list();
		if (list != null && list.size() > 0)
			return list;
		else
			return null;
	}

	/**
	 * 根据病例的id和type类型获取治疗方案的list
	 * 
	 * @param medicalRecordUuid
	 * @param string
	 * @return
	 */
	@Override
	public List<DoctorAdviceModel> getAllDoctorAdivceByUuidAndType(
			String medicalRecordUuid, String type) {
		StringBuffer hql = new StringBuffer(
				" from DoctorAdviceModel as o where o.medicalRecordUuid =:medicalRecordUuid and o.type=:type order by o.createTime DESC");
		Query query = getH4Session().createQuery(hql.toString());
		query.setString("medicalRecordUuid", medicalRecordUuid);
		query.setString("type", type);
		List list = query.list();
		if (list != null && list.size() > 0)
			return list;
		else
			return null;
	}

	/**
	 * 根据随访表单的id和type类型获取治疗方案的list
	 *
	 * @param visitRecordUuid
	 * @return list
	 */
	@Override
	public List<DoctorAdviceModel> getDoctorAdivceByUuidAndType(
			String visitRecordUuid, String type) {
		StringBuffer hql = new StringBuffer(
				" from DoctorAdviceModel as o where o.visitRecordUuid =:visitRecordUuid and o.type=:type order by o.createTime DESC");
		Query query = getH4Session().createQuery(hql.toString());
		query.setString("visitRecordUuid", visitRecordUuid);
		query.setString("type", type);
		List list = query.list();
		if (list != null && list.size() > 0)
			return list;
		else
			return null;
	}

	@Override
	public List<String> getUuidsByVisitRecordUuid(String visitRecordUuid,
			String type) {
		StringBuffer hql = new StringBuffer(
				" select o.uuid from DoctorAdviceModel as o where o.visitRecordUuid =:visitRecordUuid and o.type=:type order by o.createTime");
		Query query = getH4Session().createQuery(hql.toString());
		query.setString("visitRecordUuid", visitRecordUuid);
		query.setString("type", type);
		List list = query.list();
		if (list != null && list.size() > 0)
			return (List<String>) list;
		else
			return null;

	}

	@Override
	public List<String> getUuidsByCustomerUuidAndDoctorUuid(String doctorUuid,
			String customerUuid) {
		StringBuffer hql = new StringBuffer(
				" select o.uuid from DoctorAdviceModel as o where o.customerUuid =:customerUuid and o.serviceStaffUuid=:doctorId order by o.createTime DESC");
		Query query = getH4Session().createQuery(hql.toString());
		query.setString("customerUuid", customerUuid);
		query.setString("doctorId", doctorUuid);
		List list = query.list();
		if (list != null && list.size() > 0)
			return list;
		else
			return null;
	}

	@Override
	public List<String> getUuidsByMedicalRecordUuidAndType(
			String medicalRecordUuid, String type) {
		StringBuffer hql = new StringBuffer(
				" select o.uuid from DoctorAdviceModel as o where o.medicalRecordUuid =:medicalRecordUuid and o.type=:type order by o.createTime DESC");
		Query query = getH4Session().createQuery(hql.toString());
		query.setString("medicalRecordUuid", medicalRecordUuid);
		query.setString("type", type);
		List list = query.list();
		if (list != null && list.size() > 0)
			return list;
		else
			return null;
	}

}
