package com.aebiz.b2b2c.customermgr.customershoplevel.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.customermgr.common.CustomerCacheConstant;
import com.aebiz.b2b2c.customermgr.customershoplevel.vo.CustomerShopLevelModel;
import com.aebiz.b2b2c.customermgr.customershoplevel.vo.CustomerShopLevelQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class CustomerShopLevelCacheImpl extends
		BaseMemcachedCache<CustomerShopLevelModel, CustomerShopLevelQueryModel>
		implements CustomerShopLevelDAO {

	@Resource(name = CustomerCacheConstant.CUSTOMER_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private CustomerShopLevelDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public CustomerShopLevelCacheImpl() {
		super(CustomerCacheConstant.CUSTOMER_CUSTOMERSHOPLEVEL_KEY);
	}

	/**
	 * 得到平台会员等级model的集合
	 */
	@Override
	public List<CustomerShopLevelModel> getShopLevelModelList() {
		List<String> uuids = this.getShopLevelUuids();
		// 2：在内存中找 这些uuid对应的对象
		List<CustomerShopLevelModel> listM = new ArrayList<CustomerShopLevelModel>();
		List<String> noUuids = new ArrayList<String>();

		for (String uuid : uuids) {
			Object obj = mcc
					.get(CustomerCacheConstant.CUSTOMER_CUSTOMERSHOPLEVEL_KEY
							+ uuid);
			if (obj != null) {
				CustomerShopLevelModel m = (CustomerShopLevelModel) obj;
				listM.add(m);
			} else {
				noUuids.add(uuid);
			}
		}
		// 3：内存中找不到对应对象的uuid，从数据库里面获取，并设置到缓存中
		if (noUuids.size() > 0) {
			for (String uuid : noUuids) {
				CustomerShopLevelModel m = (CustomerShopLevelModel) dao
						.getByUuid(uuid);
				// 并设置到缓存中
				mcc.set(CustomerCacheConstant.CUSTOMER_CUSTOMERSHOPLEVEL_KEY
						+ uuid, m);

				listM.add(m);
			}
		}

		return listM;
	}

	/**
	 * 得到平台会员初始等级编号
	 * 
	 * 等级按照position进行排序，排序序号最小的为最低等级
	 * 
	 * @return
	 */
	public String getInitLevelUuid() {
		return dao.getInitLevelUuid();
	}

	/**
	 * 通过编号获取等级名称
	 */
	public String getLevelNameByUuid(String uuid) {
		// 从缓存中取得对象
		CustomerShopLevelModel cm = (CustomerShopLevelModel) mcc
				.get(CustomerCacheConstant.CUSTOMER_CUSTOMERSHOPLEVEL_KEY
						+ uuid);

		// 如果对象为空，则从数据库中查询
		if (cm == null) {
			cm = (CustomerShopLevelModel) dao.getByUuid(uuid);

			// 从数据库中查询，如果存在，则放入到缓存
			if (cm != null) {
				mcc.set(CustomerCacheConstant.CUSTOMER_CUSTOMERSHOPLEVEL_KEY
						+ uuid, cm);
			}
		}

		// 如果为空，则返回空
		if (cm == null) {
			return "";
		}

		return cm.getLevelName();
	}

	/**
	 * 得到平台会员等级名称的集合
	 */
	public List<String> getCustomerShopLevelName() {
		return dao.getCustomerShopLevelName();
	}

	/**
	 * 添加平台会员等级的是否需要校验平台会员等级名称是否存在
	 * 
	 * @return
	 */
	@Override
	public boolean checkLevelNameExist(String levelName, String uuid) {
		return dao.checkLevelNameExist(levelName, uuid);
	}

	@Override
	public List<String> getShopLevelUuids() {
		return dao.getShopLevelUuids();
	}
	/**
	 * 
	 * @Description: (这里用一句话描述这个方法的作用)    
	 * @author XP  
	 * @param intergralcount
	 * @return
	 * @date 2015-12-29 下午3:59:33
	 */
	@Override
	public String getLevelNameByIntergral(int intergralcount) {
		return dao.getLevelNameByIntergral(intergralcount);
	}
	/**
	 * 
	 * @Description: (这里用一句话描述这个方法的作用)    
	 * @author XP  
	 * @param levelName
	 * @return
	 * @date 2015-12-29 下午3:59:37
	 */
	@Override
	public String getUuidByLevelName(String levelName) {
		return dao.getUuidByLevelName(levelName);
	}

}
