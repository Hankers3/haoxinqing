package com.aebiz.b2b2c.basicdata.logisticscompany.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.basicdata.commom.BasicDataCacheConstant;
import com.aebiz.b2b2c.basicdata.logisticscompany.vo.LogisticsCompanyModel;
import com.aebiz.b2b2c.basicdata.logisticscompany.vo.LogisticsCompanyQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class LogisticsCompanyCacheImpl extends
		BaseMemcachedCache<LogisticsCompanyModel, LogisticsCompanyQueryModel>
		implements LogisticsCompanyDAO {

	@Resource(name = BasicDataCacheConstant.BASIC_DATA_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private LogisticsCompanyDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public LogisticsCompanyCacheImpl() {
		super(BasicDataCacheConstant.BASIC_DATA_LOGISTICSCP);
	}

	/**
	 * 得到物流公司列表
	 */
	public List<LogisticsCompanyModel> getLogisticsCompanyList() {
		List<String> lcIds = dao.getLogisticsCompanyIdsList();

		// 2：在内存中找 这些uuid对应的对象
		List<LogisticsCompanyModel> listM = new ArrayList<LogisticsCompanyModel>();
		List<String> noUuids = new ArrayList<String>();

		for (String uuid : lcIds) {
			Object obj = mcc.get(BasicDataCacheConstant.BASIC_DATA_LOGISTICSCP
					+ uuid);
			if (obj != null) {
				LogisticsCompanyModel m = (LogisticsCompanyModel) obj;
				listM.add(m);
			} else {
				noUuids.add(uuid);
			}
		}
		// 3：内存中找不到对应对象的uuid，从数据库里面获取，并设置到缓存中
		if (noUuids.size() > 0) {
			for (String uuid : noUuids) {
				LogisticsCompanyModel m = (LogisticsCompanyModel) dao
						.getByUuid(uuid);
				mcc.set(BasicDataCacheConstant.BASIC_DATA_LOGISTICSCP + uuid, m);
				listM.add(m);
			}
		}

		return listM;
	}

	public List<String> getLogisticsCompanyIdsList() {
		return dao.getLogisticsCompanyIdsList();
	}

}
