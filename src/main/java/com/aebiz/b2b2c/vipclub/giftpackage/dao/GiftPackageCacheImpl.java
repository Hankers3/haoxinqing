package com.aebiz.b2b2c.vipclub.giftpackage.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.vipclub.common.VipClubCacheConstant;
import com.aebiz.b2b2c.vipclub.giftpackage.vo.GiftPackageModel;
import com.aebiz.b2b2c.vipclub.giftpackage.vo.GiftPackageQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class GiftPackageCacheImpl extends
		BaseMemcachedCache<GiftPackageModel, GiftPackageQueryModel> implements
		GiftPackageDAO {
	@Resource(name = VipClubCacheConstant.VIP_CLUB_MEM_CLIENT_NAME)
	private MemCachedClient mcc;
	
	@Autowired
	private GiftPackageDAO myDao;
	
	@PostConstruct
	public void init(){
		this.setMcc(mcc);
		this.setDao(myDao);
	}
	
	public GiftPackageCacheImpl() {
		super(VipClubCacheConstant.VIP_CLUB_GIFT_PACKAGE);
	}

	@Override
	public void reduceGiftPackageCount(GiftPackageModel giftPackageModel) {
		this.myDao.reduceGiftPackageCount(giftPackageModel);
		super.updateCache(giftPackageModel);
	}

}
