package com.aebiz.b2b2c.vipclub.giftpackage.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.vipclub.giftpackage.vo.GiftPackageModel;
import com.aebiz.b2b2c.vipclub.giftpackage.vo.GiftPackageQueryModel;

@Repository
public class GiftPackageH4Impl extends
		BaseH4Impl<GiftPackageModel, GiftPackageQueryModel> implements
		GiftPackageDAO {

	/**
	 * 
	 * 平台礼包列表数据搜索<br/>
	 * 商户id为空代表平台搜索
	 * 
	 */
	@Override
	public String getAppendHql(GiftPackageQueryModel qm) {
		String hql = "";
		if (StringUtil.isEmpty(qm.getStoreUuid())) {
			hql += " and o.storeUuid='' ";
		}

		hql += super.getAppendHql(qm);
		return hql;
	}

	@Override
	public void reduceGiftPackageCount(GiftPackageModel giftPackageModel) {
		StringBuffer hql = new StringBuffer(
				" update GiftPackageModel set packageCount =:packageCount where uuid =:uuid ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setInteger("packageCount", giftPackageModel.getPackageCount() - 1);
		query.setString("uuid", giftPackageModel.getUuid());

		query.executeUpdate();
	}

}
