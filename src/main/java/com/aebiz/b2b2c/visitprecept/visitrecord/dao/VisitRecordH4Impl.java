package com.aebiz.b2b2c.visitprecept.visitrecord.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.visitprecept.visitrecord.vo.VisitRecordModel;
import com.aebiz.b2b2c.visitprecept.visitrecord.vo.VisitRecordQueryModel;

@Repository
public class VisitRecordH4Impl extends BaseH4Impl<VisitRecordModel, VisitRecordQueryModel> implements VisitRecordDAO {

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
	protected String getAppendHql(VisitRecordQueryModel qm) {
		StringBuffer hql = new StringBuffer(" and o.customerUuid =c.uuid and o.serviceStaffUuid = s.uuid ");
		// 查询有用的数据
		hql.append(" and o.visitState = '1' ");
		// 会员名称查询
		if (!StringUtil.isEmpty(qm.getCustomerName())) {
			hql.append(" and c.customerName like:customerName ");
		}

		// 会员电话查询
		if (!StringUtil.isEmpty(qm.getCustomerMobile())) {
			hql.append(" and c.mobile like:customerMobile ");
		}

		// 医生名称查询
		if (!StringUtil.isEmpty(qm.getDoctoerName())) {
			hql.append(" and s.serviceStaffName like:doctoerName ");
		}

		// 医生电话查询
		if (!StringUtil.isEmpty(qm.getDoctorMobile())) {
			hql.append(" and s.mobile like:doctorMobile ");
		}
		// 随访的状态 0代表随访申请 1代表随访记录
		if (!StringUtil.isEmpty(qm.getVisitType1())) {
			hql.append(" and o.applyState =:applyState ");
		}
		if(!StringUtil.isEmpty(qm.getSortName()) && !"uuid".equals(qm.getSortName())){
			hql.append(" order by o." + qm.getSortName() + " " + qm.getSortType());
		}else{
			hql.append(" order by o.createTime desc");
		}
		
		return hql.toString();
	}

	/**
	 * 设置查询语句的值
	 */
	@Override
	protected void setAppendHqlValue(VisitRecordQueryModel qm, Query q) {

		if (!StringUtil.isEmpty(qm.getCustomerName())) {
			q.setString("customerName", "%" + qm.getCustomerName() + "%");
		}

		if (!StringUtil.isEmpty(qm.getCustomerMobile())) {
			q.setString("customerMobile", "%" + qm.getCustomerMobile() + "%");
		}

		if (!StringUtil.isEmpty(qm.getDoctoerName())) {
			q.setString("doctoerName", "%" + qm.getDoctoerName() + "%");
		}
		if (!StringUtil.isEmpty(qm.getDoctorMobile())) {
			q.setString("doctorMobile", "%" + qm.getDoctorMobile() + "%");
		}
		if (!StringUtil.isEmpty(qm.getVisitType1())) {
			q.setString("applyState", qm.getVisitType1());
		}

	}

	/**
	 * 根据医生编号 获取随访次数
	 * 
	 * @param doctorUuid
	 * @return
	 */
	public int getCountByDoctorUuid(String doctorUuid) {
		if (StringUtil.isEmpty(doctorUuid)) {
			return 0;
		}

		StringBuffer hql = new StringBuffer(
				" select count(o.uuid) from VisitRecordModel as o where o.visitState = '1' ");
		hql.append(" and o.serviceStaffUuid =:serviceStaffUuid");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("serviceStaffUuid", doctorUuid);
		Number count = (Number) q.uniqueResult();

		return count.intValue();
	}

	/**
	 * 
	 * 根据随访方案id 获取用户的ids
	 * 
	 * @param uuid
	 * @return
	 */
	public List<String> getAllByUuid(String uuid) {

		String hql = new String(
				"select v.customerUuid from VisitRecordModel as v where v.preceptUuid=:uuid and v.visitState = '1' ");
		Query query = this.getH4Session().createQuery(hql);
		query.setString("uuid", uuid);
		List<String> list = query.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 根据患者编号 获取随访list
	 * 
	 * @param doctorUuid
	 * @return
	 */
	public List<VisitRecordModel> getByCustomerUuid(String uuid) {
		StringBuffer hql = new StringBuffer(
				"select distinct(v) from VisitRecordModel as v where v.customerUuid=:uuid");
		hql.append(" group by v.serviceStaffUuid  ");
		hql.append(" order by v.createTime  desc ");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("uuid", uuid);
		List<VisitRecordModel> list = query.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 根据医生id 获取随访患者的数量
	 * 
	 * @param doctorId
	 * @return
	 */
	@Override
	public int getVisitRecordNumByDoctorId(String doctorId) {
		StringBuffer hql = new StringBuffer(
				"select count(distinct o.uuid)from VisitRecordModel o where o.serviceStaffUuid =:doctorId and o.visitState = '1' ");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("doctorId", doctorId);
		return ((Number) query.uniqueResult()).intValue();

	}

	/**
	 * 根据随访方案获取随访人数
	 * 
	 * @param preceptUuid
	 * @return
	 */
	@Override
	public int getCustomerNumByPreceptUuid(String doctorUuid,String preceptUuid) {
		StringBuffer hql = new StringBuffer(
				"select count(distinct o.customerUuid)from VisitRecordModel o where  o.preceptUuid =:preceptUuid and o.serviceStaffUuid =:serviceStaffUuid and o.visitState = '1' ");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("preceptUuid", preceptUuid);
		query.setString("serviceStaffUuid", doctorUuid);
		return ((Number) query.uniqueResult()).intValue();
	}

	/**
	 * 
	 * 根据医生ID查找随访记录的list的接口
	 */
	@Override
	public List<VisitRecordModel> getAllListByDoctorId(String doctorid) {
		StringBuffer hql = new StringBuffer(
				" from VisitRecordModel o where o.serviceStaffUuid =:doctorid and o.applyState=:applyState and o.visitState='1'");
		hql.append(" order by o.createTime desc ");
		Query q = getH4Session().createQuery(hql.toString());
		q.setParameter("doctorid", doctorid);
		q.setParameter("applyState", "0");
		List list = q.list();
		if ((list != null) && (list.size() > 0)) {
			return list;
		}
		return null;

	}

	/**
	 * 根据随访方案id，医生ID，获取患者列表
	 * @param uuid
	 * @param preceptUuid
     * @return list
     */
	@Override
	public List<VisitRecordModel> getCustomerVisitRecordByUuid(String uuid,String preceptUuid) {
		StringBuffer hql = new StringBuffer(" from VisitRecordModel o where o.serviceStaffUuid =:doctorid and o.preceptUuid=:preceptUuid and o.visitState = '1' and customerUuid is not null ");
		hql.append(" order by o.createTime desc ");
		Query q = getH4Session().createQuery(hql.toString());
		q.setParameter("doctorid", uuid);
		q.setParameter("preceptUuid", preceptUuid);
		List list = q.list();
		if ((list != null) && (list.size() > 0)) {
			return list;
		}
		return null;

	}
	
	/**
	 * 根据医生的ID和患者的id获取随访申请对象
	 * 
	 * @param customerUuid
	 * @param doctorUuid
	 * @return
	 */
	@Override
	public VisitRecordModel getVisitRecord(String customerUuid, String doctorUuid) {
		StringBuffer hql = new StringBuffer(
				" from VisitRecordModel o where o.serviceStaffUuid =:doctorUuid and o.customerUuid=:customerUuid and o.visitState = '1'  ");
		hql.append(" order by o.createTime asc ");
		Query q = getH4Session().createQuery(hql.toString());
		q.setParameter("doctorUuid", doctorUuid);
		q.setParameter("customerUuid", customerUuid);
		List<VisitRecordModel> list = q.list();
		if ((list != null) && (list.size() > 0)) {
			return list.get(0);
		}
		return null;

	}
	
	/**
	 * 根据医生的ID和患者的id获取随访申请的list集合
	 * 
	 * @param customerUuid
	 * @param doctorUuid
	 * @return
	 */
	@Override
	public List<VisitRecordModel> getVisitRecordByCusAndDoc(String customerUuid, String doctorUuid) {
		StringBuffer hql = new StringBuffer(
				" from VisitRecordModel o where o.serviceStaffUuid =:doctorUuid and o.customerUuid=:customerUuid and o.visitState = '1'  ");
		hql.append(" order by o.createTime desc ");
		Query q = getH4Session().createQuery(hql.toString());
		q.setParameter("doctorUuid", doctorUuid);
		q.setParameter("customerUuid", customerUuid);
		List<VisitRecordModel> list = q.list();
		if ((list != null) && (list.size() > 0)) {
			return list;
		}
		return null;

	}
	

	/**
	 * 删除无用的随访表单
	 */
	public void deleteVisit() {
		StringBuffer hql = new StringBuffer(" delete from VisitRecordModel o where o.visitState ='0' ");
		Query q = getH4Session().createQuery(hql.toString());
		q.executeUpdate();
	}

	/**
	 * 
	 * @Description: (根据随访记录的id获取患者的id)
	 * @author XP
	 * @param visitRecordUuid
	 * @return
	 * @date 2016-1-22 下午2:52:35
	 */
	@Override
	public String getCustomerUuidByUuid(String visitRecordUuid) {
		StringBuffer hql = new StringBuffer(
				" select o.customerUuid from VisitRecordModel o where o.uuid =:visitRecordUuid and o.visitState = '1' ");
		Query q = getH4Session().createQuery(hql.toString());
		q.setParameter("visitRecordUuid", visitRecordUuid);
		List<String> list = q.list();
		if ((list != null) && (list.size() > 0)) {
			return list.get(0);
		}
		return "";
	}

	/**
	 * 根据医生的ID获取所有的随访表单信息
	 * 
	 * @param doctorid
	 * @return
	 */
	@Override
	public List<VisitRecordModel> getAllVisitRecordsByDoctorId(String doctorid) {
		StringBuffer hql = new StringBuffer(
				" from VisitRecordModel o where o.serviceStaffUuid =:doctorid and o.visitState = '1' ");
		hql.append(" order by o.createTime desc ");
		Query q = getH4Session().createQuery(hql.toString());
		q.setParameter("doctorid", doctorid);
		List list = q.list();
		if ((list != null) && (list.size() > 0)) {
			return list;
		}
		return null;
	}
	
	/**
	 * 根据医生ID查找所有随访患者
	 * @param doctorUuid
	 * @return
	 */
	@Override
	public List<String> getCustomerUuidByDoctorUuid(String doctorUuid){
		StringBuffer hql = new StringBuffer(
				" select distinct(o.customerUuid) from VisitRecordModel o where o.serviceStaffUuid =:doctorUuid");
		hql.append(" order by o.createTime desc ");
		Query q = getH4Session().createQuery(hql.toString());
		q.setParameter("doctorUuid", doctorUuid);
		//q.setParameter("applyState", "1");
		List list = q.list();
		if ((list != null) && (list.size() > 0)) {
			return list;
		}
		return null;
	}
	
	
	@Override
	public List<String> getCostomerUuidsByVisitPreceptUuid(String preceptUuid) {
		String hql = new String(" select o.customerUuid from VisitRecordModel o where o.preceptUuid=:preceptUuid ");
		Query query = this.getH4Session().createQuery(hql);
		query.setString("preceptUuid", preceptUuid);
		List<String> list = query.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
}
