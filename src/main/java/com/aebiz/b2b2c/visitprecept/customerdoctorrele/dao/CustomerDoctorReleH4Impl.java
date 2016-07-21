package com.aebiz.b2b2c.visitprecept.customerdoctorrele.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.visitprecept.customerdoctorrele.vo.CustomerDoctorReleModel;
import com.aebiz.b2b2c.visitprecept.customerdoctorrele.vo.CustomerDoctorReleQueryModel;

@Repository
public class CustomerDoctorReleH4Impl extends
		BaseH4Impl<CustomerDoctorReleModel, CustomerDoctorReleQueryModel>
		implements CustomerDoctorReleDAO {
	/**
	 * 根据医生编号获取患者数量
	 * 
	 * @param doctorUuid
	 * @return
	 */
	@Override
	public int getCustomerNumByDoctorUuid(String doctorUuid) {
		if (StringUtil.isEmpty(doctorUuid)) {
			return 0;
		}
		StringBuffer hql = new StringBuffer(
				" select count(o.uuid) from CustomerDoctorReleModel as o where o.doctorUuid =:doctorUuid ");
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("doctorUuid", doctorUuid);
		Number result = (Number) q.uniqueResult();
		return result.intValue();
	}

	/**
	 * 根据患者编号获取医生数量
	 * 
	 * @param doctorUuid
	 * @return
	 */
	@Override
	public int getDoctorNumByCustomerUuid(String customerUuid) {
		if (StringUtil.isEmpty(customerUuid)) {
			return 0;
		}
		StringBuffer hql = new StringBuffer(
				" select count(o.uuid) from CustomerDoctorReleModel as o where o.customerUuid =:customerUuid ");
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("customerUuid", customerUuid);
		Number result = (Number) q.uniqueResult();
		return result.intValue();
	}

	/**
	 * 根据医生编号获取患者信息
	 * 
	 * @param doctorUuid
	 * @return
	 */
	@Override
	public List<CustomerModel> getcostomerListByDoctorUuid(String doctorUuid) {
		if (StringUtil.isEmpty(doctorUuid)) {
			return null;
		}
		StringBuffer hql = new StringBuffer(
				" select c from CustomerModel as c ,ServicestaffModel as s, CustomerDoctorReleModel as o where o.doctorUuid = s.uuid and o.customerUuid = c.uuid and o.doctorUuid=:doctorUuid ");
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("doctorUuid", doctorUuid);
		if (q.list().size() > 0 && q.list() != null) {
			return q.list();
		} else {
			return null;
		}
	}

	@Override
	public List<CustomerDoctorReleModel> getByDoctorUuid(String doctorUuid) {
		if (StringUtil.isEmpty(doctorUuid)) {
			return null;
		}
		StringBuffer hql = new StringBuffer(
				" select o from CustomerDoctorReleModel as o where o.doctorUuid=:doctorUuid ");
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("doctorUuid", doctorUuid);
		if (q.list().size() > 0 && q.list() != null) {
			return q.list();
		} else {
			return null;
		}
	}

	/**
	 * 根据医生编号及分组id获取医患关系信息
	 * 
	 * @param doctorUuid
	 * @param groupId
	 * @return
	 */
	@Override
	public List<CustomerDoctorReleModel> getByDoctorUuidAndGroupId(
			String doctorUuid, String groupId) {
		if (StringUtil.isEmpty(doctorUuid)) {
			return null;
		}
		StringBuffer hql = new StringBuffer(
				" select o from CustomerDoctorReleModel as o where o.doctorUuid=:doctorUuid ");
		if (!StringUtil.isEmpty(groupId)) {
			hql.append("and o.groupUuid=:groupUuid");
		} else {
			hql.append("and (o.groupUuid = '' or o.groupUuid is null) ");
		}
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("doctorUuid", doctorUuid);
		if (!StringUtil.isEmpty(groupId)) {
			q.setString("groupUuid", groupId);
		}
		if (q.list().size() > 0 && q.list() != null) {
			return q.list();
		} else {
			return null;
		}
	}

	/**
	 * 根据患者的id获取医生的Uuid
	 * 
	 * @param customerid
	 * @return
	 */
	@Override
	public String getDoctorUuidByCustomerUuid(String customerid) {
		if (StringUtil.isEmpty(customerid)) {
			return null;
		}
		StringBuffer hql = new StringBuffer(
				" select o.doctorUuid from CustomerDoctorReleModel as o where o.customerUuid=:customerid ");
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("customerid", customerid);

		return (String) q.uniqueResult();
	}

	@Override
	public void deleteByCustomerIdAndGroupId(String customerId, String groupId) {
		StringBuffer hql = new StringBuffer(
				"delete from CustomerDoctorReleModel as o where o.customerUuid=:customerUuid  ");
		if(!StringUtil.isEmpty(groupId)){
			hql.append(" and o.groupUuid=:groupUuid ");
		}
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("customerUuid", customerId);
		if(!StringUtil.isEmpty(groupId)){
			q.setString("groupUuid", groupId);
		}
		q.executeUpdate();
	}

	/**
	 * 判断患者分组下是否有患者
	 * 
	 * @return
	 */
	@Override
	public List getByGroupUuid(String gid) {
		StringBuffer hql = new StringBuffer(
				"select o from CustomerDoctorReleModel as o where o.groupUuid=:groupUuid ");
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("groupUuid", gid);
		if (q.list().size() > 0 && q.list() != null) {
			return q.list();
		} else {
			return null;
		}
	}

	@Override
	public List<String> getcostomerUuidsByDoctorUuid(String doctorUuid) {
		if (StringUtil.isEmpty(doctorUuid)) {
			return null;
		}
		StringBuffer hql = new StringBuffer(
				" select o.customerUuid from CustomerDoctorReleModel as o where o.doctorUuid=:doctorUuid ");
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
				" select o.uuid from CustomerDoctorReleModel as o where o.doctorUuid=:doctorUuid ");
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("doctorUuid", doctorUuid);
		if (q.list().size() > 0 && q.list() != null) {
			return q.list();
		} else {
			return null;
		}
	}

	@Override
	public List<String> getUuidsByGroupUuid(String gid) {
		StringBuffer hql = new StringBuffer(
				"select o.uuid from CustomerDoctorReleModel as o where o.groupUuid=:groupUuid ");
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("groupUuid", gid);
		if (q.list().size() > 0 && q.list() != null) {
			return q.list();
		} else {
			return null;
		}
	}
	/**
	 * 通过医生和患者id找到uuid
	 * 
	 * @param customerUuid
	 * @param doctorUuid
	 * @return
	 */
	public String getUuidByCustomerUuidAndDoctorUuid(
			String customerUuid, String doctorUuid){
		StringBuffer hql = new StringBuffer(
				" select o.uuid from CustomerDoctorReleModel as o where o.doctorUuid=:doctorUuid and o.customerUuid=:customerUuid");
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("doctorUuid", doctorUuid);
		q.setString("customerUuid", customerUuid);
		if (q.list().size() > 0 && q.list() != null) {
			return (String) q.list().get(0);
		} else {
			return "";
		}
	}

	/**
	 * 通过医生和患者id找到model
	 * 
	 * @param customerUuid
	 * @return
	 */
	@Override
	public CustomerDoctorReleModel getByCustomerUuidAndDoctorUuid(
			String customerUuid, String doctorUuid) {
		
		StringBuffer hql = new StringBuffer(
				" select o from CustomerDoctorReleModel as o where o.doctorUuid=:doctorUuid and o.customerUuid=:customerUuid");
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("doctorUuid", doctorUuid);
		q.setString("customerUuid", customerUuid);
		if (q.list().size() > 0 && q.list() != null) {
			return (CustomerDoctorReleModel) q.list().get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<String> getUuidsByDoctorUuidAndGroupId(String doctorUuid,
			String groupId) {
		if (StringUtil.isEmpty(doctorUuid)) {
			return null;
		}
		StringBuffer hql = new StringBuffer(
				" select o.uuid from CustomerDoctorReleModel as o where o.doctorUuid=:doctorUuid and o.groupUuid=:groupUuid");
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("doctorUuid", doctorUuid);
		q.setString("groupUuid", groupId);
		if (q.list().size() > 0 && q.list() != null) {
			return q.list();
		} else {
			return null;
		}
	}

	@Override
	public String getUuidByCustomerIdAndGroupId(String customerId,
			String groupId) {
		StringBuffer hql = new StringBuffer(
				" select o.uuid from CustomerDoctorReleModel as o where o.customerUuid=:customerId and o.groupUuid=:groupUuid");
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("customerId", customerId);
		q.setString("groupUuid", groupId);
		if (q.list().size() > 0 && q.list() != null) {
			return (String) q.list().get(0);
		} else {
			return null;
		}
	}

}
