package com.aebiz.b2b2c.vipclub.giftpackagecouponrel.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.vipclub.giftpackagecouponrel.vo.GiftPackageCouponRelModel;
import com.aebiz.b2b2c.vipclub.giftpackagecouponrel.vo.GiftPackageCouponRelQueryModel;

@Repository
public class GiftPackageCouponRelH4Impl extends
		BaseH4Impl<GiftPackageCouponRelModel, GiftPackageCouponRelQueryModel>
		implements GiftPackageCouponRelDAO {
	/**
	 * 查询礼包关联的优惠券id
	 * 
	 * @param uuid
	 * @param start
	 * @param showPage
	 * @return
	 */
	public List<String> getCouponIdsByGiftUuid(String uuid) {
		StringBuffer hql = new StringBuffer(
				"select o.couponUuid from GiftPackageCouponRelModel o where o.giftPackageUuid=:giftPackageUuid");

		Query q = getH4Session().createQuery(hql.toString());
		q.setParameter("giftPackageUuid", uuid);

		return q.list();
	}

	/**
	 * 
	 * 删除礼包关联优惠券
	 * 
	 * @param uuid
	 *            礼包id
	 * @param couponId
	 *            优惠券id
	 */
	public void removeRelate(String uuid, String couponId) {
		StringBuffer hql = new StringBuffer(
				"delete from GiftPackageCouponRelModel o where o.giftPackageUuid=:giftPackageUuid and o.couponUuid=:couponUuid");

		Query q = getH4Session().createQuery(hql.toString());
		q.setParameter("giftPackageUuid", uuid);
		q.setParameter("couponUuid", couponId);

		q.executeUpdate();
	}

	public List<String> getRemoveRelateUuid(String uuid, String couponId) {
		StringBuffer hql = new StringBuffer(
				"select o.uuid from GiftPackageCouponRelModel o where o.giftPackageUuid=:giftPackageUuid and o.couponUuid=:couponUuid");

		Query q = getH4Session().createQuery(hql.toString());
		q.setParameter("giftPackageUuid", uuid);
		q.setParameter("couponUuid", couponId);

		return q.list();
	}
}
