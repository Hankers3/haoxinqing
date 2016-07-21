package com.aebiz.b2b2c.basicdata.province.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.basicdata.commom.BasicDataCacheConstant;
import com.aebiz.b2b2c.basicdata.province.vo.ProvinceModel;
import com.aebiz.b2b2c.basicdata.province.vo.ProvinceQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class ProvinceCacheImpl extends
		BaseMemcachedCache<ProvinceModel, ProvinceQueryModel> implements
		ProvinceDAO {

	@Resource(name = BasicDataCacheConstant.BASIC_DATA_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private ProvinceDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public ProvinceCacheImpl() {
		super(BasicDataCacheConstant.BASIC_DATA_PROVINCE);
	}

	/**
	 * 通过省份编号获得省名称
	 * 
	 * @param province
	 * @return
	 */
	public String getProvinceNameById(String province) {

		// 从缓存中取得对象
		ProvinceModel cm = (ProvinceModel) mcc
				.get(BasicDataCacheConstant.BASIC_DATA_PROVINCE + province);

		// 如果对象为空，则从数据库中查询
		if (cm == null) {
			cm = (ProvinceModel) dao.getByUuid(province);

			// 从数据库中查询，如果存在，则放入到缓存
			if (cm != null) {
				mcc.set(BasicDataCacheConstant.BASIC_DATA_PROVINCE + province,
						cm);
			}
		}

		// 如果为空，则返回空
		if (cm == null) {
			return "";
		}

		// 返回名称
		return cm.getProvinceName();
	}

	/**
	 * 获取所有省份
	 * 
	 * @return
	 */
	public List<ProvinceModel> getAll() {
		List<ProvinceModel> list = null;
		Object obj = mcc.get(BasicDataCacheConstant.BASIC_DATA_PROVINCE + "all");
		if(obj != null){
			return (List<ProvinceModel>) obj;
		}else{
			list = super.getAll();
			if(list==null){
				list = new ArrayList<ProvinceModel>();
			}
			mcc.set(BasicDataCacheConstant.BASIC_DATA_PROVINCE + "all", list);
		}
		
		return list;
	}

	@Override
	public String getIdByName(String provinceName) {
	
		return dao.getIdByName(provinceName);
	}
	
	
}
