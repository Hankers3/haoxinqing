package com.aebiz.b2b2c.servicestaff.servicestafflevel.dao;

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
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.servicestaff.bankrelation.dao.BankRelationDAO;
import com.aebiz.b2b2c.servicestaff.bankrelation.vo.BankRelationModel;
import com.aebiz.b2b2c.servicestaff.common.ServicestaffCacheConstant;
import com.aebiz.b2b2c.servicestaff.doctorbill.vo.DoctorBillModel;
import com.aebiz.b2b2c.servicestaff.doctorbill.vo.DoctorBillQueryModel;
import com.aebiz.b2b2c.servicestaff.packagemanagement.vo.PackageManagementModel;
import com.aebiz.b2b2c.servicestaff.packagemanagement.vo.PackageManagementQueryModel;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;
import com.aebiz.b2b2c.servicestaff.servicestaffcomb.vo.ServicestaffcombModel;
import com.aebiz.b2b2c.servicestaff.servicestaffcomb.vo.ServicestaffcombQueryModel;
import com.aebiz.b2b2c.servicestaff.servicestaffcoord.vo.ServiceStaffCoordModel;
import com.aebiz.b2b2c.servicestaff.servicestaffcoord.vo.ServiceStaffCoordQueryModel;
import com.aebiz.b2b2c.servicestaff.servicestaffentry.vo.ServicestaffentryModel;
import com.aebiz.b2b2c.servicestaff.servicestaffentry.vo.ServicestaffentryQueryModel;
import com.aebiz.b2b2c.servicestaff.servicestaffinterview.vo.ServicestaffinterviewModel;
import com.aebiz.b2b2c.servicestaff.servicestaffinterview.vo.ServicestaffinterviewQueryModel;
import com.aebiz.b2b2c.servicestaff.servicestafflevel.vo.ServicestafflevelModel;
import com.aebiz.b2b2c.servicestaff.servicestafflevel.vo.ServicestafflevelQueryModel;
import com.danga.MemCached.MemCachedClient;

/**
 * 
 * @Description: (医生的缓存)    
 * @author XP  
 * @date 2015-12-29 上午11:06:05
 */
@Primary
@Repository
public class ServicestafflevelCacheImpl extends
		BaseMemcachedCache<ServicestafflevelModel,ServicestafflevelQueryModel>
		implements ServicestafflevelDAO {

	@Resource(name = ServicestaffCacheConstant.SERVICESTAFF_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private ServicestafflevelDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public ServicestafflevelCacheImpl() {
		super(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFFLEVEL_KEY);
	}

        @Override
        public boolean checkLevelNameExist(String levelName, String uuid) {
            return false;
        }
        /**
         * 
         * @Description: (这里用一句话描述这个方法的作用)    
         * @author XP  
         * @param position
         * @param uuid
         * @return
         * @date 2015-12-29 下午2:48:28
         */
        @Override
        public boolean checkpositionExist(String position, String uuid) {
            return dao.checkpositionExist(position, uuid);
        }
        /**
         * 
         * @Description: (这里用一句话描述这个方法的作用)    
         * @author XP  
         * @param serviceStaffLevel
         * @return
         * @date 2015-12-29 下午2:48:33
         */
        @Override
        public String getServiceStaffLevelName(String serviceStaffLevel) {
            return dao.getServiceStaffLevelName(serviceStaffLevel);
        }
        /**
         * 
         * @Description: (这里用一句话描述这个方法的作用)    
         * @author XP  
         * @param serviceStaffLevel
         * @return
         * @date 2015-12-29 下午2:48:48
         */
        @Override
        public ServicestafflevelModel getServicestafflevelModelbyserviceStaffLevel(String serviceStaffLevel) {
            return dao.getServicestafflevelModelbyserviceStaffLevel(serviceStaffLevel);
        }
        /**
         * 
         * @Description: (这里用一句话描述这个方法的作用)    
         * @author XP  
         * @return
         * @date 2015-12-29 下午2:49:03
         */
        @Override
        public List<ServicestafflevelModel> ServicestafflevelModelList() {
            return dao.ServicestafflevelModelList();
        }
        /**
         * 
         * @Description: (这里用一句话描述这个方法的作用)    
         * @author XP  
         * @param levelName
         * @return
         * @date 2015-12-29 下午2:49:24
         */
        @Override
        public List<String> getServicestafflevelModeluuids(String levelName) {
            return dao.getServicestafflevelModeluuids(levelName);
        }
            
	
	
}
