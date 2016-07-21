package com.aebiz.b2b2c.userfront.platadimagerel.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.userfront.common.UserFrontCacheConstant;
import com.aebiz.b2b2c.userfront.platadimagerel.vo.PlatAdImageRelModel;
import com.aebiz.b2b2c.userfront.platadimagerel.vo.PlatAdImageRelQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class PlatAdImageRelCacheImpl
		extends
		BaseMemcachedCache<PlatAdImageRelModel, PlatAdImageRelQueryModel>
		implements
			PlatAdImageRelDAO {

	@Resource(name = UserFrontCacheConstant.USERFRONT_CLIENT_NAME)
	private MemCachedClient mcc;
	
	@Autowired
	private PlatAdImageRelDAO dao;
	
	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}
	
	public PlatAdImageRelCacheImpl() {
		super(UserFrontCacheConstant.USERFRONT_PLATADIMAGEREL_KEY);
	}
	
	
	

	/**
	 * 根据广告的uuid获取该广告关联的所有关联关系的uuid
	 * @param adUuid
	 * @return 
	 * List<String>
	 */
	public List<String> getPlatAdImageRelModelUuidsByAdUuid(String adUuid){
		return dao.getPlatAdImageRelModelUuidsByAdUuid(adUuid);
		
	}
	
	/**
	 * 根据广告的uuid获取该广告关联的所有的图片
	 * @param adUuid
	 * @return 
	 * List<PlatAdImageRelModel>
	 */
	public List<PlatAdImageRelModel> getPlatAdImageRelModelsByAdUuid(String adUuid){
		
		Object object =  this.mcc.get(UserFrontCacheConstant.USERFRONT_PLATADIMAGEREL_KEY_LIST + adUuid);
		if(object != null){
			return (List<PlatAdImageRelModel>)object;
		}else{
			List<PlatAdImageRelModel> list = new ArrayList<PlatAdImageRelModel>();
			List<String> uuids = this.getPlatAdImageRelModelUuidsByAdUuid(adUuid);
			if(uuids != null && uuids.size() > 0){
				for (String uuid : uuids) {
					PlatAdImageRelModel model =  this.getByUuid(uuid);
					if(model != null){
						list.add(model);
					}
				}
			}
			if(list != null && list.size() > 0){
				this.mcc.add(UserFrontCacheConstant.USERFRONT_PLATADIMAGEREL_KEY_LIST + adUuid, list);
			}
			return list;
		}
	}

	@Override
	protected void updateCache(PlatAdImageRelModel m) {
		this.mcc.delete(UserFrontCacheConstant.USERFRONT_PLATADIMAGEREL_KEY_LIST + m.getAdUuid());
		super.updateCache(m);
	}

	@Override
	public String create(PlatAdImageRelModel m) {
		this.mcc.delete(UserFrontCacheConstant.USERFRONT_PLATADIMAGEREL_KEY_LIST + m.getAdUuid());
		return super.create(m);
	}

	@Override
	public void updateCc(String adUuid) {
		this.mcc.delete(UserFrontCacheConstant.USERFRONT_PLATADIMAGEREL_KEY_LIST +adUuid);
		System.out.println("========USERFRONT_PLATADIMAGEREL_KEY_LIST===========");
	}
	
	

}