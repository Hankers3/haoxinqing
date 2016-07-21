package com.aebiz.b2b2c.vipclub.giftpackagecouponrel.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.vipclub.common.VipClubCacheConstant;
import com.aebiz.b2b2c.vipclub.giftpackagecouponrel.vo.GiftPackageCouponRelModel;
import com.aebiz.b2b2c.vipclub.giftpackagecouponrel.vo.GiftPackageCouponRelQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class GiftPackageCouponRelCacheImpl
		extends
		BaseMemcachedCache<GiftPackageCouponRelModel, GiftPackageCouponRelQueryModel>
		implements GiftPackageCouponRelDAO {
	@Resource(name = VipClubCacheConstant.VIP_CLUB_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private GiftPackageCouponRelDAO myDao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(myDao);
	}

	public GiftPackageCouponRelCacheImpl() {
		super(VipClubCacheConstant.VIP_CLUB_GIFT_PACKAGE_COUPON_REL);
	}

	@Override
	public List<String> getCouponIdsByGiftUuid(String uuid) {
		return this.myDao.getCouponIdsByGiftUuid(uuid);
	}

	@Override
	public void removeRelate(String uuid, String couponId) {
		List<String> uuids = this.myDao.getRemoveRelateUuid(uuid, couponId);
		
		this.myDao.removeRelate(uuid, couponId);
		
		if(uuids != null && uuids.size() > 0){
			super.deletes(uuids);
		}
	}

	@Override
	public List<String> getRemoveRelateUuid(String uuid, String couponId) {
		return this.myDao.getRemoveRelateUuid(uuid, couponId);
	}

}
