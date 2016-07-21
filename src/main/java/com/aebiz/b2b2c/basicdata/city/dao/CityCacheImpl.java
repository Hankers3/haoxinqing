package com.aebiz.b2b2c.basicdata.city.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.basicdata.city.vo.CityModel;
import com.aebiz.b2b2c.basicdata.city.vo.CityQueryModel;
import com.aebiz.b2b2c.basicdata.commom.BasicDataCacheConstant;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class CityCacheImpl extends
		BaseMemcachedCache<CityModel, CityQueryModel> implements CityDAO {

	private static Logger log = LoggerFactory
			.getLogger(BaseMemcachedCache.class);

	@Resource(name = BasicDataCacheConstant.BASIC_DATA_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private CityDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public CityCacheImpl() {
		super(BasicDataCacheConstant.BASIC_DATA_CITY);
	}

	/**
	 * 根据省的编号查询所有的城市
	 * 
	 * @param provinceUuid
	 * @return
	 */
	public List<CityModel> getCityModelListByProvinceUuid(String provinceUuid) {

		List<String> cityIds = this.getCityIdsListByProvinceUuid(provinceUuid);

		// 2：在内存中找 这些uuid对应的对象
		List<CityModel> listM = new ArrayList<CityModel>();
		List<String> noUuids = new ArrayList<String>();

		for (String uuid : cityIds) {
			log.info("get  key==" + BasicDataCacheConstant.BASIC_DATA_CITY
					+ uuid);
			Object obj = mcc.get(BasicDataCacheConstant.BASIC_DATA_CITY + uuid);
			if (obj != null) {
				CityModel m = (CityModel) obj;
				log.info("从缓存取到==" + m);
				listM.add(m);
			} else {
				noUuids.add(uuid);
			}
		}
		// 3：内存中找不到对应对象的uuid，从数据库里面获取，并设置到缓存中
		if (noUuids.size() > 0) {
			for (String uuid : noUuids) {
				CityModel m = (CityModel) dao.getByUuid(uuid);
				log.info("从数据库中取到==" + m);
				// 并设置到缓存中
				log.info("sssset  key=="
						+ BasicDataCacheConstant.BASIC_DATA_CITY + uuid);
				mcc.set(BasicDataCacheConstant.BASIC_DATA_CITY + uuid, m);

				log.info("now get===="
						+ mcc.get(BasicDataCacheConstant.BASIC_DATA_CITY + uuid));
				listM.add(m);
			}
		}

		return listM;
	}

	/**
	 * 通过市编号获得市名称
	 * 
	 * @param city
	 * @return
	 */
	public String getCityNameById(String uuid) {

		// 从缓存中取得对象
		CityModel cm = (CityModel) mcc
				.get(BasicDataCacheConstant.BASIC_DATA_CITY + uuid);

		// 如果对象为空，则从数据库中查询
		if (cm == null) {
			cm = (CityModel) dao.getByUuid(uuid);

			// 从数据库中查询，如果存在，则放入到缓存
			if (cm != null) {
				mcc.set(BasicDataCacheConstant.BASIC_DATA_CITY + uuid, cm);
			}
		}

		// 如果为空，则返回空
		if (cm == null) {
			return "";
		}

		// 返回名称
		return cm.getCityName();
	}

	/**
	 * 根据省的编号
	 * 
	 * @param provinceUuid
	 * @return
	 */
	public List<String> getCityIdsListByProvinceUuid(String provinceUuid) {
		List<String> list = null;
		Object obj = mcc.get(BasicDataCacheConstant.BASIC_DATA_CITY + provinceUuid+"L");
		if(obj !=null){
			return (List<String>) obj;
		}else{
			list =  dao.getCityIdsListByProvinceUuid(provinceUuid);
			if(list !=null && list.size()>0){
				this.mcc.set(BasicDataCacheConstant.BASIC_DATA_CITY + provinceUuid+"L",list);
			}
		}
		return list;
	}

	/**
	 * 获取所有城市
	 * @return
	 */
	public List<CityModel> getAll() {
		List<CityModel> list = null;
		Object obj = mcc.get(BasicDataCacheConstant.BASIC_DATA_CITY + "all");
		if(obj != null){
			return (List<CityModel>) obj;
		}else{
			list = super.getAll();
			if(list==null){
				list = new ArrayList<CityModel>();
			}
			mcc.set(BasicDataCacheConstant.BASIC_DATA_CITY + "all", list);
		}
		
		return list;
	}

	@Override
	public String getIdByName(String cityName) {
		return dao.getIdByName(cityName);
	}
}
