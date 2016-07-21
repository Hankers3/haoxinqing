package com.aebiz.b2b2c.servicestaff.servicestaffentry.dao;

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
import com.danga.MemCached.MemCachedClient;

/**
 * 
 * @Description: (医生的缓存)    
 * @author XP  
 * @date 2015-12-29 上午11:06:05
 */
@Primary
@Repository
public class ServicestaffentryCacheImpl extends
		BaseMemcachedCache<ServicestaffentryModel,ServicestaffentryQueryModel>
		implements ServicestaffentryDAO {

	@Resource(name = ServicestaffCacheConstant.SERVICESTAFF_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private ServicestaffentryDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public ServicestaffentryCacheImpl() {
		super(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFFENTRY_KEY);
	}
	/**
	 * 
	 * @Description: (根据医生id获取对象)    
	 * @author XP  
	 * @param servicestaffUuid
	 * @return
	 * @date 2015-12-29 下午2:29:28
	 */
        @Override
        public ServicestaffentryModel getServicestaffentryModelByServicestaffUuid(String servicestaffUuid) {
            Object obj = this.mcc.get(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFFENTRY_KEY
                    + servicestaffUuid);
                ServicestaffentryModel m = null;
                    if (obj != null) {
                            m = (ServicestaffentryModel) obj;
                    } else {
                            m = dao.getServicestaffentryModelByServicestaffUuid(servicestaffUuid);
                            if (m != null) {
                                    this.mcc.set(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFFENTRY_KEY
                                                    + servicestaffUuid, m);
                            }
                    }
                    return m;
        }
	
	
	
}
