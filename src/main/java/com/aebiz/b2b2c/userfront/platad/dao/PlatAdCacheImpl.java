package com.aebiz.b2b2c.userfront.platad.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.userfront.common.UserFrontCacheConstant;
import com.aebiz.b2b2c.userfront.platad.vo.PlatAdModel;
import com.aebiz.b2b2c.userfront.platad.vo.PlatAdQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class PlatAdCacheImpl extends BaseMemcachedCache<PlatAdModel,PlatAdQueryModel> implements PlatAdDAO {

	@Resource(name = UserFrontCacheConstant.USERFRONT_CLIENT_NAME)
	private MemCachedClient mcc;
	
	@Autowired
	private PlatAdDAO dao;
	
	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}
	
	public PlatAdCacheImpl() {
		super(UserFrontCacheConstant.USERFRONT_PLATAD_KEY);
	}
	@Override
	public void update(PlatAdModel m) {
		dao.update(m);
		this.mcc.delete(UserFrontCacheConstant.USERFRONT_PLATAD_KEY+m.getUuid());
	}
}
