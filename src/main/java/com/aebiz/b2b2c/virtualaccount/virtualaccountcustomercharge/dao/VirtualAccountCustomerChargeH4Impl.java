package com.aebiz.b2b2c.virtualaccount.virtualaccountcustomercharge.dao;

import java.util.List;

import javax.persistence.Transient;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomercharge.vo.VirtualAccountCustomerChargeModel;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomercharge.vo.VirtualAccountCustomerChargeQueryModel;

@Repository
@Component
public class VirtualAccountCustomerChargeH4Impl
		extends
		BaseH4Impl<VirtualAccountCustomerChargeModel, VirtualAccountCustomerChargeQueryModel>
		implements VirtualAccountCustomerChargeDAO {

	@Transient
	private static CustomerService customerService;

	@Autowired
	public void setCustomerService(CustomerService cs) {
		this.customerService = cs;
	}

	/**
	 * 查询充值记录的总数量<br>
	 * 
	 * 如果state为空,则查询的是所有的充值状态的记录
	 * 
	 * @param customerUuid
	 * @param state
	 * @return int
	 */
	public int getChargeCount(String customerUuid, String state) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from VirtualAccountCustomerChargeModel v where 1=1 ");
		hql.append(" and v.customerUuid =:customerUuid");
		if (!StringUtil.isEmpty(state)) {
			hql.append(" and v.chargeState =:chargeState");
		}

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("customerUuid", customerUuid);
		if (!StringUtil.isEmpty(state)) {
			query.setString("chargeState", state);
		}
		List list = query.list();
		if (list != null && list.size() > 0) {
			return list.size();
		}
		return 0;

	}

	/**
	 * 查询充值记录列表,带分页的<br>
	 * 
	 * 如果state为空,则查询的是所有的充值状态的记录
	 * 
	 * @param customerUuid
	 * @param state
	 * @param fromNum
	 * @param pageShow
	 * @return List<VirtualAccountCustomerChargeModel>
	 */
	public List<VirtualAccountCustomerChargeModel> searchCharge(
			String customerUuid, String state, int fromNum, int pageShow) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from VirtualAccountCustomerChargeModel v where 1=1 ");
		hql.append(" and v.customerUuid =:customerUuid");
		if (!StringUtil.isEmpty(state)) {
			hql.append(" and v.chargeState =:chargeState");
		}
		hql.append(" order by v.createTime desc ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("customerUuid", customerUuid);
		if (!StringUtil.isEmpty(state)) {
			query.setString("chargeState", state);
		}
		if (fromNum < 0) {
			query.setFirstResult(fromNum);
		}
		if (pageShow < 0) {
			query.setMaxResults(pageShow);
		}
		return (List<VirtualAccountCustomerChargeModel>) query.list();
	}

	public List<String> searchChargeUuids(String customerUuid, String state,
			int fromNum, int pageShow) {
		StringBuffer hql = new StringBuffer();
		hql.append(" select v.uuid from VirtualAccountCustomerChargeModel v where 1=1 ");
		hql.append(" and v.customerUuid =:customerUuid");
		if (!StringUtil.isEmpty(state)) {
			hql.append(" and v.chargeState =:chargeState");
		}
		hql.append(" order by v.createTime desc ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("customerUuid", customerUuid);
		if (!StringUtil.isEmpty(state)) {
			query.setString("chargeState", state);
		}

		query.setFirstResult(fromNum);
		query.setMaxResults(pageShow);

		return query.list();
	}

	/**
	 * 查询所有充值记录的总数量
	 */
	public int getAllChargeCount(VirtualAccountCustomerChargeQueryModel qm) {
		String hql = "select count(distinct o.customerUuid ) from VirtualAccountCustomerChargeModel o "
				+ getMultiModel() + " where 1=1 ";

		hql = hql + getAppendHql(qm);

		Query q = getH4Session().createQuery(hql);
		setValue(qm, q);
		setAppendHqlValue(qm, q);

		return ((Number) q.uniqueResult()).intValue();
	}

	@Override
	protected String getMultiModel() {
		return " ,CustomerInfoModel as cim , CustomerModel as cm ";
	}

	/**
	 * 拼接搜索时需要的字段
	 */
	@Override
	protected String getAppendHql(VirtualAccountCustomerChargeQueryModel qm) {
		StringBuffer buff = new StringBuffer(
				" and o.customerUuid = cim.customerUuid and o.customerUuid = cm.uuid ");

		/*
		 * if(!StringUtil.isEmpty(qm.getUserName())){
		 * buff.append(" and o.customerUuid in:customerUuid "); }
		 */
		if(!StringUtil.isEmpty(qm.getSortName()) && !"uuid".equals(qm.getSortName())){
			buff.append(" order by o." + qm.getSortName() + " " + qm.getSortType());
		}else{
			buff.append(" order by o.createTime desc");
		}
		if (!StringUtil.isEmpty(qm.getRealName())) {
			buff.append(" and cim.realName like:realName ");
		}
		if (!StringUtil.isEmpty(qm.getMobile())) {
			buff.append(" and cm.mobile like:mobile ");
		}
		if (!StringUtil.isEmpty(qm.getCustomerName())) {
			buff.append(" and cm.customerName like:customerName ");
		}
		return buff.append(" ").toString();

	}

	/**
	 * 查询所有充值记录时需要去掉重复会员的记录
	 * 
	 * @param qm
	 * @return
	 */
	protected String getAppendHql1(VirtualAccountCustomerChargeQueryModel qm) {
		StringBuffer buff = new StringBuffer();
		if (!StringUtil.isEmpty(qm.getUserName())) {
			buff.append(" and o.customerUuid in:customerUuid ");
		}
		if (!StringUtil.isEmpty(qm.getMobile())) {
			buff.append(" and o.mobile =:mobile ");
		}
		return buff.append(
				" group by o.customerUuid order by o.createTime desc ")
				.toString();
	}

	/**
	 * 为搜索查询赋值
	 */
	@Override
	protected void setAppendHqlValue(VirtualAccountCustomerChargeQueryModel qm,
			Query q) {
		/*
		 * if(!StringUtil.isEmpty(qm.getUserName())){ List<String> cusIds =
		 * customerService.getCustomerUuids(qm.getUserName());
		 * q.setParameterList("customerUuid", cusIds); }
		 */
		if (!StringUtil.isEmpty(qm.getRealName())) {
			q.setString("realName", "%" + qm.getRealName() + "%");
		}
		if (!StringUtil.isEmpty(qm.getCustomerName())) {
			q.setString("customerName", "%" + qm.getCustomerName() + "%");
		}

		if (!StringUtil.isEmpty(qm.getMobile())) {
			q.setString("mobile", "%" + qm.getMobile() + "%");
		}
	}

	/**
	 * 查询所有的充值记录
	 */
	/*
	 * @Override public List<VirtualAccountCustomerChargeModel> getByCondition(
	 * VirtualAccountCustomerChargeQueryModel qm, int start, int pageShow) {
	 * String hql = " from VirtualAccountCustomerChargeModel o where 1=1 "; hql
	 * = hql + getAppendHql1(qm); Query query =
	 * this.getH4Session().createQuery(hql.toString());
	 * query.setFirstResult(start); query.setMaxResults(pageShow); setValue(qm,
	 * query); setAppendHqlValue(qm, query); return
	 * (List<VirtualAccountCustomerChargeModel>) query.list(); } /*
	 * 
	 * /** 根据会员id查询该会员的所有充值记录
	 */
	@Override
	public List<VirtualAccountCustomerChargeModel> searchAllCharge(
			String customerUuid) {
		String hql = " from VirtualAccountCustomerChargeModel o where 1=1 ";
		hql += (" and o.customerUuid =:customerUuid");
		hql += (" order by o.createTime desc ");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("customerUuid", customerUuid);
		return (List<VirtualAccountCustomerChargeModel>) query.list();
	}

	/**
	 * 根据会员id查询该会员的所有充值记录
	 */
	@Override
	public List<String> searchUuidsAllCharge(String customerUuid) {
		String hql = " select o.uuid from VirtualAccountCustomerChargeModel o where 1=1 ";
		hql += (" and o.customerUuid =:customerUuid");
		hql += (" order by o.createTime desc ");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("customerUuid", customerUuid);
		return (List<String>) query.list();
	}

	/**
	 * 根据订单id获取对象
	 * 
	 * @param orderUuid
	 * @return
	 */
	@Override
	public VirtualAccountCustomerChargeModel getVirtualAccountCustomerChargeModelByOrderUuid(
			String orderUuid) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer();
		hql.append(" from VirtualAccountCustomerChargeModel v where 1=1 ");
		hql.append(" and v.orderId =:orderId");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("orderId", orderUuid);
		List list = query.list();
		if (list != null && list.size() > 0) {
			VirtualAccountCustomerChargeModel virtualAccountCustomerChargeModel = (VirtualAccountCustomerChargeModel) list
					.get(0);
			return virtualAccountCustomerChargeModel;
		} else {
			return new VirtualAccountCustomerChargeModel();
		}
	}
}
