package com.aebiz.b2b2c.servicestaff.packagemanagement.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.servicestaff.common.ServicestaffCacheConstant;
import com.aebiz.b2b2c.servicestaff.packagemanagement.vo.PackageManagementModel;
import com.aebiz.b2b2c.servicestaff.packagemanagement.vo.PackageManagementQueryModel;
import com.danga.MemCached.MemCachedClient;

/**
 * 
 * @Description: (医生的缓存)    
 * @author XP  
 * @date 2015-12-29 上午11:06:05
 */
@Primary
@Repository
public class PackageManagementCacheImpl extends
		BaseMemcachedCache<PackageManagementModel, PackageManagementQueryModel>
		implements PackageManagementDAO {

	@Resource(name = ServicestaffCacheConstant.SERVICESTAFF_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private PackageManagementDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public PackageManagementCacheImpl() {
		super(ServicestaffCacheConstant.SERVICESTAFF_PACKAGEMANAGEMENT_KEY);
	}
	
	@Override
	public void update(PackageManagementModel m) {
		dao.update(m);
		this.mcc.delete(ServicestaffCacheConstant.SERVICESTAFF_PACKAGEMANAGEMENT_KEY+m.getUuid());
	}
	
	/**
	 * 
	 * @Description: (这里用一句话描述这个方法的作用)    
	 * @author XP  
	 * @param packageName
	 * @return
	 * @date 2015-12-29 下午2:09:14
	 */
    @Override
    public String checkPackageName(String packageName) {
        return dao.checkPackageName(packageName);
    }
    /**
     * 
     * @Description: (根据uuid获取套餐的名)    
     * @author XP  
     * @param uuid
     * @return
     * @date 2015-12-29 下午2:09:28
     */
    @Override
    public String getPackageName(String uuid) {
        return dao.getPackageName(uuid);
    }
    
    /**
     * 
     * @Description: (根据医生的id获取私人医生套餐的列表)    
     * @author XP  
     * @param doctorUuid
     * @return
     * @date 2016-1-18 下午1:05:00
     */
    @Override
    public List<PackageManagementModel> getPackageListByDoctorUuid(List<String> uuids) {
        return dao.getPackageListByDoctorUuid(uuids);
    }

	
}
