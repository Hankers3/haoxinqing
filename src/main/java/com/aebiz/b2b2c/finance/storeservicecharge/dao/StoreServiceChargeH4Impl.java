package com.aebiz.b2b2c.finance.storeservicecharge.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.finance.storeservicecharge.vo.StoreServiceChargeModel;
import com.aebiz.b2b2c.finance.storeservicecharge.vo.StoreServiceChargeQueryModel;

@Repository
public class StoreServiceChargeH4Impl
		extends
			BaseH4Impl<StoreServiceChargeModel, StoreServiceChargeQueryModel>
		implements
			StoreServiceChargeDAO {

	/**
	 * 查询当前商户的未支付的服务单据
	 * @param accountUuid
	 * @return StoreServiceChargeModel
	 */
	public StoreServiceChargeModel getUnderPayServiceCharge(String accountUuid){
		StoreServiceChargeModel chargeModel = new StoreServiceChargeModel();
		
		StringBuffer hql = new StringBuffer(" from StoreServiceChargeModel s where s.accountUuid =:accountUuid ");
		hql.append(" and s.payStatus =:payStatus");
		
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("accountUuid", accountUuid);
		query.setString("payStatus", StoreServiceChargeModel.UNDER_PAY);
		
		//有可能不为已,取第一个
		List<StoreServiceChargeModel> list = query.list();
		if(list != null && list.size()>0){
			chargeModel = list.get(0);
		}
		return chargeModel;
	}
	
	public String getUnderPayServiceChargeUuid(String accountUuid) {
            String uuid = "";

            StringBuffer hql = new StringBuffer(
                            "select s.uuid from StoreServiceChargeModel s where s.accountUuid =:accountUuid ");
            hql.append(" and s.payStatus =:payStatus");

            Query query = this.getH4Session().createQuery(hql.toString());
            query.setString("accountUuid", accountUuid);
            query.setString("payStatus", StoreServiceChargeModel.UNDER_PAY);

            // 有可能不为已,取第一个
            List<String> list = query.list();
            if (list != null && list.size() > 0) {
                    uuid = list.get(0);
            }
            return uuid;
    }
}
