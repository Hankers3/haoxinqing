package com.aebiz.b2b2c.customermgr.customersubscriptionlog.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.customermgr.customersubscriptionlog.vo.CustomerSubscriptionLogModel;
import com.aebiz.b2b2c.customermgr.customersubscriptionlog.vo.CustomerSubscriptionLogQueryModel;

@Repository
public class CustomerSubscriptionLogH4Impl
		extends
		BaseH4Impl<CustomerSubscriptionLogModel, CustomerSubscriptionLogQueryModel>
		implements CustomerSubscriptionLogDAO {

	/**
	 * 通过会员编号查询出该会员所有订阅的详细信息
	 */
	public List<CustomerSubscriptionLogModel> getSubscriptionListByCustomerUuid(
			String customerUuid) {
		StringBuffer hql = new StringBuffer(
				" from CustomerSubscriptionLogModel where customerUuid=:uuid ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("uuid", customerUuid);

		Object obj = query.list();
		if (obj != null) {
			return (List<CustomerSubscriptionLogModel>) obj;
		}

		return null;
	}
	/**
         * 
         * @Description: TODO(通过会员编号查询出该会员所有订阅的详细信息)    
         * @author XP  
         * @param customerUuid
         * @return
         * @date 2015-12-28 下午4:39:26
         */
	@Override
        public List<String> getSubscriptionUuidsByCustomerUuid(String customerUuid) {
                StringBuffer hql = new StringBuffer(
                                " select uuid from CustomerSubscriptionLogModel where customerUuid=:uuid ");

                Query query = this.getH4Session().createQuery(hql.toString());
                query.setString("uuid", customerUuid);
                
                List<String> list = query.list();
                if(list != null && list.size()>0){
                    return list;
                }
                return null;
        }
}
