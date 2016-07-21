package com.aebiz.b2b2c.basicdata.region.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.basicdata.commom.BasicDataCacheConstant;
import com.aebiz.b2b2c.basicdata.region.vo.RegionModel;
import com.aebiz.b2b2c.basicdata.region.vo.RegionQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class RegionCacheImpl extends
		BaseMemcachedCache<RegionModel, RegionQueryModel> implements RegionDAO {

	@Resource(name = BasicDataCacheConstant.BASIC_DATA_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private RegionDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public RegionCacheImpl() {
		super(BasicDataCacheConstant.BASIC_DATA_REGION);
	}

	/**
	 * 根据市的编号,查询所有地区
	 * 
	 * @param cityUuid
	 * @return
	 */
	public List<RegionModel> getRegionModelListByCityUuid(String cityUuid) {

		List<String> cityIds = this.getRegionIdsListByCityUuid(cityUuid);
		// 2：在内存中找 这些uuid对应的对象
		List<RegionModel> listM = new ArrayList<RegionModel>();
		List<String> noUuids = new ArrayList<String>();
		for (String uuid : cityIds) {
			Object obj = mcc.get(BasicDataCacheConstant.BASIC_DATA_REGION
					+ uuid);
			if (obj != null) {
				RegionModel m = (RegionModel) obj;
				listM.add(m);
			} else {
				noUuids.add(uuid);
			}
		}
		// 3：内存中找不到对应对象的uuid，从数据库里面获取，并设置到缓存中
		if (noUuids.size() > 0) {
			for (String uuid : noUuids) {
				RegionModel m = (RegionModel) dao.getByUuid(uuid);
				mcc.set(BasicDataCacheConstant.BASIC_DATA_REGION + uuid, m);
				listM.add(m);
			}
		}

		return listM;
	}

	/**
	 * 根据市的编号,查询所有地区
	 * 
	 * @param cityUuid
	 * @return
	 */
	public List<String> getRegionIdsListByCityUuid(String cityUuid) {
		List<String> list = null;
		Object obj = mcc.get(BasicDataCacheConstant.BASIC_DATA_REGION
				+ cityUuid+"L");
		if(obj !=null){
			return (List<String>) obj;
		}else{
			list = dao.getRegionIdsListByCityUuid(cityUuid);
			if(list !=null && list.size()>0){
				this.mcc.set(BasicDataCacheConstant.BASIC_DATA_REGION
						+ cityUuid+"L", list);
			}
		}
		return list;
	}

	/**
	 * 获取所有区
	 * @return
	 */
	public List<RegionModel> getAll() {
		List<RegionModel> list = null;
		Object obj = mcc.get(BasicDataCacheConstant.BASIC_DATA_REGION + "all");
		if(obj != null){
			return (List<RegionModel>) obj;
		}else{
			list = super.getAll();
			if(list==null){
				list = new ArrayList<RegionModel>();
			}
			mcc.set(BasicDataCacheConstant.BASIC_DATA_REGION + "all", list);
		}
		
		return list;
	}

	@Override
	public String getIdByName(String regionName,String cityUuid) {
		
		return dao.getIdByName(regionName,cityUuid);
	}

	@Override
	public String getWholeAreameById(String regionId) {
		Object obj = this.mcc.get(BasicDataCacheConstant.BASIC_DATA_REGION +regionId);
		String areaName="";
		RegionModel region =null;
		if(obj !=null ){
			region= (RegionModel) obj;
			areaName = region.getRegionName();
		}else{
			region = (RegionModel) dao.getByUuid(regionId);
			if(region !=null){
				areaName = region.getRegionName();
				this.mcc.set(BasicDataCacheConstant.BASIC_DATA_REGION + regionId, region);
			}
		}
		return areaName;
	}

}
