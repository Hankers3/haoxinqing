package com.aebiz.b2b2c.customermgr.customersubscribecontent.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customersubscribecontent.vo.CustomerSubscribeContentModel;
import com.aebiz.b2b2c.customermgr.customersubscribecontent.vo.CustomerSubscribeContentQueryModel;

@Repository
public class CustomerSubscribeContentH4Impl
		extends
		BaseH4Impl<CustomerSubscribeContentModel, CustomerSubscribeContentQueryModel>
		implements CustomerSubscribeContentDAO {

	/**
	 * 检查订阅编号是否存在
	 * 
	 * 当编辑时，需要排除本身的Id
	 */
	public boolean checkSubscribeNoExist(String subscribeNo, String uuid) {
		StringBuffer hql = new StringBuffer(
				"select c.subscribeNo from CustomerSubscribeContentModel c where c.subscribeNo=:subscribeNo ");
		if (!StringUtil.isEmpty(uuid)) {
			hql.append(" and c.uuid <>:uuid ");
		}

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("subscribeNo", subscribeNo);

		if (!StringUtil.isEmpty(uuid)) {
			query.setString("uuid", uuid);
		}

		if (query.list() != null && query.list().size() > 0) {
			return true;
		}
		return false;
	}

	/**
         * 通过uuid得到编号
         */
        @Override
        public String getSubscribeNoByUuid(String uuid) {
                StringBuffer hql = new StringBuffer(
                                " select subscribeNo from CustomerSubscribeContentModel c where c.uuid =:uuid ");

                Query query = this.getH4Session().createQuery(hql.toString());
                query.setString("uuid", uuid);

                Object obj = query.uniqueResult();

                return String.valueOf(obj);
        }

}
