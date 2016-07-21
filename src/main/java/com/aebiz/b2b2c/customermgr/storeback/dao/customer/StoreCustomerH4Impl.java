package com.aebiz.b2b2c.customermgr.storeback.dao.customer;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerQueryModel;
import com.aebiz.b2b2c.customermgr.customerstorelevel.vo.CustomerStoreLevelModel;

@Repository
public class StoreCustomerH4Impl extends
		BaseH4Impl<CustomerModel, CustomerQueryModel> implements
		StoreCustomerDAO {
	/**
	 * 通过会员编号查询该会员
	 */
	public CustomerModel getCustomerByCustomerUuid(String customerUuid) {
		StringBuffer hql = new StringBuffer(
				" from CustomerModel where uuid=:customerUuid ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("customerUuid", customerUuid);

		Object obj = query.uniqueResult();

		if (obj != null) {
			return (CustomerModel) obj;
		}
		return null;
	}

	/**
	 * 通过商户会员等级编号查询商户会员等级
	 */
	public CustomerStoreLevelModel getCustomerStoreLevelByUuid(
			String storeLevelUuid) {
		StringBuffer hql = new StringBuffer(
				" from CustomerStoreLevelModel where uuid=:storeLevelUuid");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("storeLevelUuid", storeLevelUuid);

		Object obj = query.uniqueResult();
		if (obj != null) {
			return (CustomerStoreLevelModel) obj;
		}

		return null;
	}

	/**
	 * 通过商户编号获取该商户的所有会员等级
	 */
	@Override
	public List<CustomerStoreLevelModel> getCustomerStoreLevelsByStoreUuid(
			String storeUuid) {
		StringBuffer hql = new StringBuffer(
				" from CustomerStoreLevelModel where storeUuid=:storeUuid");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("storeUuid", storeUuid);

		List<CustomerStoreLevelModel> customerStoreLevelList = query.list();

		return customerStoreLevelList;
	}

	/**
	 * 通过会员编号和商户编号查询会员在这个店铺的等级
	 */
	@Override
	public CustomerStoreLevelModel getCustomerStoreLevel(String customerUuid,
			String storeUuid) {
		StringBuffer hql = new StringBuffer(
				"select cs from CustomerStoreLevelModel cs,CustomerStoreLevelRelModel cr ");
		hql.append(" where cs.uuid=cr.storeLevelUuid");
		hql.append(" and cr.storeUuid=cs.storeUuid");
		hql.append(" and cr.customerUuid=:customerUuid");
		hql.append(" and cr.storeUuid=:storeUuid ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("customerUuid", customerUuid);
		query.setString("storeUuid", storeUuid);

		Object obj = query.uniqueResult();
		if (obj != null) {
			return (CustomerStoreLevelModel) obj;
		}
		return null;
	}

	/**
	 * 需要查询出会员和商户会员等级信息
	 */
	@Override
	protected String getMultiSelect() {
		String hql = ", cs ";
		return hql;
	}

	/**
	 * 多表联合查询
	 */
	@Override
	protected String getMultiModel() {
		String hql = ", CustomerStoreLevelModel cs, CustomerStoreLevelRelModel cr ";
		return hql;
	}
	
	/**
	 * 排序
	 */
	/*
	@Override
	protected String getAppendHql(CustomerQueryModel qm) {		
		String hql = " and o.uuid=cr.customerUuid and cr.storeLevelUuid=cs.uuid ";
		if (!StringUtil.isEmpty(qm.getStoreUuid())) {
			hql += " and cr.storeUuid=:storeUuid ";
		}
		if (!StringUtil.isEmpty(qm.getCustomerStoreLevelUuid())) {
			hql += " and cs.uuid=:uuid ";
		}
		if ("customerStoreLevelModel.levelName".equals(qm.getSortName())) {
			hql += " order by cs.levelName " + qm.getSortType();
		} else {
			hql += super.getAppendHql(qm);
		}
		return hql;
	}
*/
	
	/**
	 * 参数赋值
	 */
	/*
	@Override
	protected void setAppendHqlValue(CustomerQueryModel qm, Query q) {
		if (!StringUtil.isEmpty(qm.getStoreUuid())) {
			q.setParameter("storeUuid", qm.getStoreUuid());
		}
		if (!StringUtil.isEmpty(qm.getCustomerStoreLevelUuid())) {
			q.setParameter("uuid", qm.getCustomerStoreLevelUuid());
		}
	}
	*/
	/**
	 * 需要展示的数据发送到页面
	 */
	@Override
	protected List<CustomerModel> exeResultList(List<Object[]> tempList) {
		List<CustomerModel> customerList = new ArrayList<CustomerModel>();

		if (tempList != null && tempList.size() > 0) {
			for (Object[] obj : tempList) {
				CustomerModel customerModel = (CustomerModel) obj[0];
				CustomerStoreLevelModel customerStoreLevelModel = (CustomerStoreLevelModel) obj[1];

				CustomerStoreLevelModel customerStoreLevel = new CustomerStoreLevelModel();
				customerStoreLevel.setLevelName(customerStoreLevelModel
						.getLevelName());

				// 商户等级model
				//customerModel.setCustomerStoreLevelModel(customerStoreLevel);

				customerList.add(customerModel);
			}
		}
		return customerList;
	}
}
