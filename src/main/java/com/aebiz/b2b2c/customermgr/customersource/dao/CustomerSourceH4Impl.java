package com.aebiz.b2b2c.customermgr.customersource.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.customermgr.customersource.vo.CustomerSourceModel;
import com.aebiz.b2b2c.customermgr.customersource.vo.CustomerSourceQueryModel;

@Repository
public class CustomerSourceH4Impl extends
		BaseH4Impl<CustomerSourceModel, CustomerSourceQueryModel> implements
		CustomerSourceDAO {
	/**
	 * 通过会员编号获取会员来源信息
	 * 
	 * @param customerUuid
	 *            会员编号
	 * @return
	 */
	public CustomerSourceModel getCustomerSourceModelByCustomerUuid(
			String customerUuid) {
		StringBuffer hql = new StringBuffer(
				" from CustomerSourceModel where customerUuid=:customerUuid");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("customerUuid", customerUuid);

		Object obj = query.uniqueResult();

		if (obj != null) {
			return (CustomerSourceModel) obj;
		}

		return null;
	}

	/**
	 * 邀请码列表
	 * @return
	 */
	@Override
	public List<String> getInviteCodes() {
		StringBuffer hql = new StringBuffer(
				"SELECT myInviteCode from CustomerSourceModel GROUP BY myInviteCode HAVING COUNT=1");
		Query query = this.getH4Session().createQuery(hql.toString());

		List<String> list = query.list();

		if (list != null && list.size()>0) {
			return list;
		}else {
			return new ArrayList<String>();
		}
	}
	/**
	 * 根据注册生成的邀请码 MyInviteCode字段查询CustomerSourceModel
	 * @param cashId 注册生成的邀请码 MyInviteCode
	 * @return CustomerSourceModel集合
	 */
	@Override
	public List<CustomerSourceModel> getModelByMyInviteCodes(String cashId) {
		StringBuffer hql = new StringBuffer("from CustomerSourceModel where myInviteCode=:myInviteCode");
		Query query = getH4Session().createQuery(hql.toString());
		query.setString("myInviteCode", cashId);
		
		List<CustomerSourceModel> list=null;
		try {
			list = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
        public String getCustomerSourceIdByCustomerUuid(String customerUuid) {
                StringBuffer hql = new StringBuffer(
                                " select uuid from CustomerSourceModel where customerUuid=:customerUuid");

                Query query = this.getH4Session().createQuery(hql.toString());
                query.setString("customerUuid", customerUuid);

                Object obj = query.uniqueResult();

                if (obj != null) {
                        return (String) obj;
                }

                return "";
        }
	 /**
	  * 获取cutomerSourceUuids
	  */
        @Override
        public List<String> getAllCutomerSourceUuids(String cashId) {
            StringBuffer hql = new StringBuffer(
                    " select uuid from CustomerSourceModel where myInviteCode=:myInviteCode");
            Query query = this.getH4Session().createQuery(hql.toString());
            query.setString("myInviteCode", cashId);
            List<String> list = query.list();
            if(list!=null&&list.size()>0){
                return list;
            }
            return null;
        }
	
}
