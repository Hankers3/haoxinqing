package com.aebiz.b2b2c.websiteoperation.favorite.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.common.CustomerCacheConstant;
import com.aebiz.b2b2c.customermgr.consultrecord.vo.ConsultRecordModel;
import com.aebiz.b2b2c.websiteoperation.common.WebsiteOperateCacheConstant;
import com.aebiz.b2b2c.websiteoperation.favorite.vo.FavoriteModel;
import com.aebiz.b2b2c.websiteoperation.favorite.vo.FavoriteQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class FavoriteCacheImpl extends
		BaseMemcachedCache<FavoriteModel, FavoriteQueryModel> implements
		FavoriteDAO {

	@Resource(name = WebsiteOperateCacheConstant.WEBSITE_OPERATE_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private FavoriteDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public FavoriteCacheImpl() {
		super(WebsiteOperateCacheConstant.WEBSITE_FAVORITE_KEY);
	}

	@Override
	public void update(FavoriteModel m) {
		this.dao.update(m);
		this.mcc.delete(WebsiteOperateCacheConstant.WEBSITE_FAVORITE_KEY
				+ m.getUuid());
	}

	/**
	 * 根据医生的id获取收藏的uuids
	 */
	public List<String> getAllFavoriteModelUuids(String doctorid) {
		List<String> uuids = this.dao.getAllFavoriteModelUuids(doctorid);
		return uuids;
	}

	/**
	 * 根据医生id获取收藏的model的所有的列表
	 */
	@Override
	public List<FavoriteModel> getFavoriteModelListByCustomerUuid(
			String doctorid) {
		List<String> uuids = this.getAllFavoriteModelUuids(doctorid);
		List<FavoriteModel> listM = new ArrayList<FavoriteModel>();
		List<String> noUuids = new ArrayList<String>();
		if (uuids != null && uuids.size() > 0) {
			for (String uuid : uuids) {
				Object obj = this.mcc
						.get(WebsiteOperateCacheConstant.WEBSITE_FAVORITE_KEY
								+ uuid);
				if (obj != null) {
					FavoriteModel m = (FavoriteModel) obj;
					listM.add(m);
				} else {
					noUuids.add(uuid);
				}
			}
			if (noUuids.size() > 0) {
				for (String uuid : noUuids) {
					FavoriteModel m = dao.getByUuid(uuid);
					if (m != null) {
						this.mcc.set(
								WebsiteOperateCacheConstant.WEBSITE_FAVORITE_KEY
										+ uuid, m);
						listM.add(m);
					}
				}
			}
		}
		return listM;
	}

	@Override
	public void deleteFavoriteByUuid(String favoriteUuid) {
		dao.deleteFavoriteByUuid(favoriteUuid);
		mcc.delete(WebsiteOperateCacheConstant.WEBSITE_FAVORITE_KEY+favoriteUuid);
	}

	@Override
	public int getStoreTypeByDoctorUuidAndContenUuid(String doctorUuid,
			String uuid) {
		return dao.getStoreTypeByDoctorUuidAndContenUuid(doctorUuid, uuid);
	}

	@Override
	public int getNumByVedioUuid(String vedioUuid) {
		return dao.getNumByVedioUuid(vedioUuid);
	}

	@Override
	public int getcolVideoByDoctorUuidAndContenUuid(String doctorUuid,
			String videoUuid) {
		return dao.getcolVideoByDoctorUuidAndContenUuid(doctorUuid, videoUuid);
	}

	@Override
	public void deleteByDoctorUuidAndVideouuid(String doctorUuid,
			String videoUuid) {
		dao.deleteByDoctorUuidAndVideouuid(doctorUuid, videoUuid);
		String favoriteUuid = dao.getUuidByCustomerUuidAndVideoUuid(doctorUuid, videoUuid);
		mcc.delete(WebsiteOperateCacheConstant.WEBSITE_FAVORITE_KEY+favoriteUuid);
		mcc.delete(WebsiteOperateCacheConstant.WEBSITE_FAVORITE_KEY+doctorUuid+videoUuid);

	}

	@Override
	public List<FavoriteModel> getFavoriteModelListByCustomerUuidAndState(
			String customerUuid, String state) {
		return dao.getFavoriteModelListByCustomerUuidAndState(customerUuid,
				state);
	}

	@Override
	public int getNumByContentUuid(String contentUuid, String type) {
		return dao.getNumByContentUuid(contentUuid, type);
	}

	@Override
	public String getUuidByCustomerUuidAndContextUuid(String customerUuid,
			String contextUuid) {
		Object obj = this.mcc.get(WebsiteOperateCacheConstant.WEBSITE_FAVORITE_KEY
				+ customerUuid+contextUuid);
		String uuid="";
    	if(obj !=null){
    		uuid = (String) obj;
    	}else{
    		uuid = dao.getUuidByCustomerUuidAndContextUuid(customerUuid,
    				contextUuid);
    		if(!StringUtil.isEmpty(uuid)){
    			 this.mcc.set(WebsiteOperateCacheConstant.WEBSITE_FAVORITE_KEY
 						+ customerUuid+contextUuid, uuid);
    		}
    	}
		return uuid;
	}
	
	/**
     * 
     * @Description: (根据医生的id和视频的id获取收藏的uuid)    
     * @author XP  
     * @param doctorUuid
     * @param videoUuid
     * @return
     * @date 2015-12-31 下午5:20:35
     */
    @Override
    public String getUuidByCustomerUuidAndVideoUuid(String doctorUuid, String videoUuid) {
    	Object obj = this.mcc.get(WebsiteOperateCacheConstant.WEBSITE_FAVORITE_KEY
						+ doctorUuid+videoUuid);
    	String uuid="";
    	if(obj !=null){
    		uuid = (String) obj;
    	}else{
    		uuid = dao.getUuidByCustomerUuidAndVideoUuid(doctorUuid, videoUuid);
    		if(!StringUtil.isEmpty(uuid)){
    			 this.mcc.set(WebsiteOperateCacheConstant.WEBSITE_FAVORITE_KEY
 						+ doctorUuid+videoUuid, uuid);
    		}
    	}
    	
        return uuid;
    }

    @Override
    public List<FavoriteModel> getByConditionq(FavoriteQueryModel qm, int start, int pageShow) {
        return dao.getByConditionq(qm, start, pageShow);
    }

}
