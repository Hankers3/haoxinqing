package com.aebiz.b2b2c.customermgr.customerfrozenlog.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.customermgr.customerfrozenlog.vo.CustomerFrozenLogModel;
import com.aebiz.b2b2c.customermgr.customerfrozenlog.vo.CustomerFrozenLogQueryModel;

@Repository
public class CustomerFrozenLogH4Impl extends
		BaseH4Impl<CustomerFrozenLogModel, CustomerFrozenLogQueryModel>
		implements CustomerFrozenLogDAO {
	/**
	 * 根据会员编号获取会员最新冻结日志记录
	 */
	@Override
	public CustomerFrozenLogModel getFrozenLog(String customerUuid) {
		StringBuffer hql = new StringBuffer(
				" from CustomerFrozenLogModel where customerUuid=:customerUuid ");
		hql.append(" and operType=:operType ");
		hql.append(" order by opeTime desc ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("customerUuid", customerUuid);
		query.setString("operType", CustomerFrozenLogModel.FROZEN_OPER_FREZON);

		if (query.list() != null && query.list().size() > 0) {
			CustomerFrozenLogModel customerFrozenLogModel = (CustomerFrozenLogModel) (query
					.list().get(0));
			return customerFrozenLogModel;
		}
		return null;
	}

	/**
	 * 根据会员编号获取会员最新解冻日志记录
	 */
	@Override
	public CustomerFrozenLogModel getUnFrozenLog(String customerUuid) {
		StringBuffer hql = new StringBuffer(
				" from CustomerFrozenLogModel where customerUuid=:customerUuid ");
		hql.append(" and operType=:operType ");
		hql.append(" order by opeTime desc ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("customerUuid", customerUuid);
		query.setString("operType", CustomerFrozenLogModel.FROZEN_OPER_UNFREZON);

		if (query.list() != null && query.list().size() > 0) {
			CustomerFrozenLogModel customerFrozenLogModel = (CustomerFrozenLogModel) (query
					.list().get(0));
			return customerFrozenLogModel;
		}
		return null;
	}

	@Override
        public String getFrozenLogUuid(String customerUuid) {
                StringBuffer hql = new StringBuffer(
                                " select uuid from CustomerFrozenLogModel where customerUuid=:customerUuid ");
                hql.append(" and operType=:operType ");
                hql.append(" order by opeTime desc ");

                Query query = this.getH4Session().createQuery(hql.toString());
                query.setString("customerUuid", customerUuid);
                query.setString("operType", CustomerFrozenLogModel.FROZEN_OPER_FREZON);

                List<String> uuids = query.list();
                if (uuids != null && uuids.size() > 0) {
                        return uuids.get(0);
                }
                return "";
        }

        @Override
        public String getUnFrozenLogUuid(String customerUuid) {
                StringBuffer hql = new StringBuffer(
                                " select uuid from CustomerFrozenLogModel where customerUuid=:customerUuid ");
                hql.append(" and operType=:operType ");
                hql.append(" order by opeTime desc ");

                Query query = this.getH4Session().createQuery(hql.toString());
                query.setString("customerUuid", customerUuid);
                query.setString("operType", CustomerFrozenLogModel.FROZEN_OPER_UNFREZON);

                List<String> uuids = query.list();
                if (uuids != null && uuids.size() > 0) {
                        return uuids.get(0);
                }
                return "";
        }


}
