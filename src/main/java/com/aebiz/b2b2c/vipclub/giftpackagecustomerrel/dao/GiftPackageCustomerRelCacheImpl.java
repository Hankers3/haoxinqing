package com.aebiz.b2b2c.vipclub.giftpackagecustomerrel.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.vipclub.common.VipClubCacheConstant;
import com.aebiz.b2b2c.vipclub.giftpackagecustomerrel.vo.GiftPackageCustomerRelModel;
import com.aebiz.b2b2c.vipclub.giftpackagecustomerrel.vo.GiftPackageCustomerRelQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class GiftPackageCustomerRelCacheImpl
		extends
		BaseMemcachedCache<GiftPackageCustomerRelModel, GiftPackageCustomerRelQueryModel>
		implements GiftPackageCustomerRelDAO {
	@Resource(name = VipClubCacheConstant.VIP_CLUB_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private GiftPackageCustomerRelDAO myDao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(myDao);
	}

	public GiftPackageCustomerRelCacheImpl() {
		super(VipClubCacheConstant.VIP_CLUB_GIFT_PACKAGE_CUSTOMER_REL);
	}

}
