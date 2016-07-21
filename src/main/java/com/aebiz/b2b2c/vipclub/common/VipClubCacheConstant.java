package com.aebiz.b2b2c.vipclub.common;

/**
 * 
 * 会员俱乐部系统的memcache的参数设置
 * 
 * 1.会员俱乐部系统的key设置<br />
 * 2.为了便于扩展，将memcache客户端名称也定义在此
 * 
 * @author cj
 * 
 */
public class VipClubCacheConstant {
	/* 会员俱乐部系统、名称 */
	private static final String VIP_CLUB_NAME = "vipclub";

	/* 会员俱乐部系统,缓存客服端名称 */
	public static final String VIP_CLUB_MEM_CLIENT_NAME = "memCachedClient";

	/* 会员大礼包key */
	public static final String VIP_CLUB_GIFT_PACKAGE = VIP_CLUB_NAME
			+ "/giftpackage";
	
	/* 礼包关联优惠券key */
	public static final String VIP_CLUB_GIFT_PACKAGE_COUPON_REL = VIP_CLUB_NAME
			+ "/giftpackagecouponrel";
	
	/* 会员礼包关联关系key */
	public static final String VIP_CLUB_GIFT_PACKAGE_CUSTOMER_REL = VIP_CLUB_NAME
			+ "/giftpackagecustomerrel";
	
	/* 会员积分日志实体类key */
	public static final String VIP_CLUB_INTEGRAL_LOG = VIP_CLUB_NAME
			+ "/integrallog";
	/* 会员积分日志uuids实体类key */
        public static final String VIP_CLUB_INTEGRAL_LOG_UUIDS = VIP_CLUB_NAME
                        + "/integralloguuids";
	
	/* 会员积分统计表key */
	public static final String VIP_CLUB_INTEGRAL_STAT = VIP_CLUB_NAME
			+ "/integralstat";
}
