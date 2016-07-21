package com.aebiz.b2b2c.customermgr.customeraudit.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.customermgr.customeraudit.vo.CustomerAuthModel;
import com.aebiz.b2b2c.customermgr.customeraudit.vo.CustomerAuthQueryModel;

@Repository
public class CustomerAuditH4Impl extends
		BaseH4Impl<CustomerAuthModel, CustomerAuthQueryModel> implements
		CustomerAuditDAO {

	/**
	 * 会员实名认证审核通过
	 */
	@Override
	public void doAuditPass(String customerUuid, String auditReason) {
		StringBuffer hql = new StringBuffer(
				" update CustomerAuthModel set authState=:authState,auditReason=:auditReason,auditTime=:auditTime,oper=:oper ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("authState",
				CustomerAuthModel.CUSTOMERAUTH_AUTHSTATE_AUTHED);
		query.setString("auditReason", auditReason);
		query.setString("auditTime", DateFormatHelper.getNowTimeStr());
		query.setString("oper", LoginUserHelper.getLoginUserUuid());

		query.executeUpdate();

	}

	/**
	 * 会员实名认证审核不通过
	 */
	@Override
	public void doAuditUnPass(String customerUuid, String auditReason) {
		StringBuffer hql = new StringBuffer(
				" update CustomerAuthModel set authState=:authState,auditReason=:auditReason,auditTime=:auditTime,oper=:oper ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("authState",
				CustomerAuthModel.CUSTOMERAUTH_AUTHSTATE_UNAUTHED);
		query.setString("auditReason", auditReason);
		query.setString("auditTime", DateFormatHelper.getNowTimeStr());
		query.setString("oper", LoginUserHelper.getLoginUserUuid());

		query.executeUpdate();
	}

	@Override
        public String getAuditUuidByCustomerUuid(String customerUuid) {
                StringBuffer hql = new StringBuffer(
                                " select uuid from CustomerAuthModel where customerUuid=:customerUuid");

                Query query = this.getH4Session().createQuery(hql.toString());
                query.setString("customerUuid", customerUuid);

                Object obj = query.uniqueResult();
                if (obj != null) {
                        return (String) obj;
                }
                return "";
        }

}
