package com.aebiz.b2b2c.vipclub.giftpackagecouponrel.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.vipclub.giftpackagecouponrel.vo.GiftPackageCouponRelModel;
import com.aebiz.b2b2c.vipclub.giftpackagecouponrel.vo.GiftPackageCouponRelQueryModel;

public interface GiftPackageCouponRelDAO extends
		BaseDAO<GiftPackageCouponRelModel, GiftPackageCouponRelQueryModel> {
	/**
	 * 查询礼包关联的优惠券id
	 * 
	 * @param uuid
	 * @param start
	 * @param showPage
	 * @return
	 */
	public List<String> getCouponIdsByGiftUuid(String uuid);
	
	/**
	 * 
	  * 删除礼包关联优惠券
	 * 
	 * @param uuid
	 *            礼包id
	 * @param couponId
	 *            优惠券id
	 */
	public void removeRelate(String uuid,String couponId);
	
	public List<String> getRemoveRelateUuid(String uuid,String couponId);
}