package com.aebiz.b2b2c.vipclub.giftpackagecouponrel.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.vipclub.giftpackagecouponrel.vo.GiftPackageCouponRelModel;
import com.aebiz.b2b2c.vipclub.giftpackagecouponrel.vo.GiftPackageCouponRelQueryModel;

public interface GiftPackageCouponRelService extends
		BaseService<GiftPackageCouponRelModel, GiftPackageCouponRelQueryModel> {

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
	  * 保存礼包关联优惠券
	 * 
	 * @param uuid
	 *            礼包id
	 * @param couponIds
	 *            优惠券id集合
	 */
	public void saveChooseCoupon(String uuid,List<String> couponIds);
	
	/**
	 * 
	  * 删除礼包关联优惠券
	 * 
	 * @param uuid
	 *            礼包id
	 * @param couponIds
	 *            优惠券id集合
	 */
	public void removeRelate(String uuid,List<String> couponIds);

}
