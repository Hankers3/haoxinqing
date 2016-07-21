package com.aebiz.b2b2c.servicestaff.bankrelation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;

import com.aebiz.b2b2c.servicestaff.bankrelation.vo.BankRelationModel;
import com.aebiz.b2b2c.servicestaff.bankrelation.vo.BankRelationQueryModel;
import com.aebiz.b2b2c.servicestaff.doctornotice.vo.DoctorNoticeModel;

@Repository
public class BankRelationH4Impl extends
		BaseH4Impl<BankRelationModel, BankRelationQueryModel> implements
		BankRelationDAO {

	/**
	 * 根据医生的Uuid获取医生的所有的银行卡
	 * 
	 * @param doctorUuid
	 * @return
	 */
	@Override
	public List<BankRelationModel> getAllBankByDoctorUuid(String doctorUuid) {
		StringBuffer hql = new StringBuffer(
				" from BankRelationModel as o where o.doctorUuid =:doctorUuid");
		Query q = this.getH4Session().createQuery(hql.toString());
		if (!StringUtil.isEmpty(doctorUuid)) {
			q.setString("doctorUuid", doctorUuid);
		}
		List<BankRelationModel> list = q.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 删除绑定的银行卡
	 * 
	 * @param doctorUuid
	 * @param bankUuid
	 */
	@Override
	public void deleteBankCardByDoctorUuidAndBankUuid(String doctorUuid,
			String bankCode) {
		StringBuffer hql = new StringBuffer(
				"delete  from BankRelationModel as o where o.doctorUuid =:doctorUuid and o.bankCode=:bankCode ");
		Query q = this.getH4Session().createQuery(hql.toString());
		if (!StringUtil.isEmpty(doctorUuid)) {
			q.setString("doctorUuid", doctorUuid);
		}
		if (!StringUtil.isEmpty(bankCode)) {
			q.setString("bankCode", bankCode);
		}
		q.executeUpdate();
	}

	/**
	 * 根据医生的Uuid和BankUuid获得银行卡关联的Model
	 * 
	 * @param bankUuid
	 * @param doctorUuid
	 * @return
	 */
	@Override
	public BankRelationModel getModelByDoctorUuidAndBankUuid(String bankUuid,
			String doctorUuid) {
		StringBuffer hql = new StringBuffer(
				" from BankRelationModel as o where o.doctorUuid =:doctorUuid and o.bankUuid =:bankUuid ");
		Query q = this.getH4Session().createQuery(hql.toString());
		if (!StringUtil.isEmpty(doctorUuid)) {
			q.setString("doctorUuid", doctorUuid);
		}
		if (!StringUtil.isEmpty(bankUuid)) {
			q.setString("bankUuid", bankUuid);
		}
		List<BankRelationModel> list = q.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 
	 * @Description: (根据医生的id获取关联model的uuids)
	 * @author XP
	 * @param doctorUuid
	 * @return
	 * @date 2015-12-29 上午10:09:18
	 */
	@Override
	public List<String> getUuidsByDoctorUuid(String doctorUuid) {
		StringBuffer hql = new StringBuffer(
				" select crm.uuid  from BankRelationModel crm where crm.doctorUuid=:doctorUuid order by crm.createTime desc");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("doctorUuid", doctorUuid);

		List<String> list = query.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 
	 * @Description: (根据医生的id和bankuuid获取关联表的uuid)
	 * @author XP
	 * @param bankUuid
	 * @param doctorUuid
	 * @return
	 * @date 2015-12-29 上午10:36:05
	 */
	@Override
	public String getBankRelationModelUuid(String bankUuid, String doctorUuid) {
		StringBuffer hql = new StringBuffer(
				" select o.uuid from BankRelationModel as o where 1=1 ");
		hql.append(" and o.bankUuid =:bankUuid ");
		hql.append(" and o.doctorUuid =:doctorUuid ");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("bankUuid", bankUuid);
		q.setString("doctorUuid", doctorUuid);

		List list = q.list();
		if (list != null && list.size() > 0) {
			return (String) list.get(0);
		}

		return "";
	}

	/**
	 * 
	 * @Description: (根据医生的id和银行卡的卡号获取)
	 * @author XP
	 * @param doctorUuid
	 * @param bankCode
	 * @return
	 * @date 2015-12-29 上午10:47:56
	 */
	@Override
	public String getBankRelationUuid(String doctorUuid, String bankCode) {
		StringBuffer hql = new StringBuffer(
				" select o.uuid from BankRelationModel as o where 1=1 ");
		hql.append(" and o.bankCode =:bankCode ");
		hql.append(" and o.doctorUuid =:doctorUuid ");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("bankCode", bankCode);
		q.setString("doctorUuid", doctorUuid);

		List list = q.list();
		if (list != null && list.size() > 0) {
			return (String) list.get(0);
		}

		return "";
	}

	/**
	 * 根据卡号查询
	 * 
	 * @param bankCode
	 * @return
	 */
	@Override
	public BankRelationModel getByBankCode(String bankCode) {
		StringBuffer hql = new StringBuffer(
				" select crm from BankRelationModel crm where crm.bankCode=:bankCode order by crm.createTime desc");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("bankCode", bankCode);
		List<BankRelationModel> list = query.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
