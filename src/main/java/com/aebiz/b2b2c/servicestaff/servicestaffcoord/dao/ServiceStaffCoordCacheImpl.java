package com.aebiz.b2b2c.servicestaff.servicestaffcoord.dao;

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
import com.danga.MemCached.MemCachedClient;

/**
 * 
 * @Description: (医生的缓存)    
 * @author XP  
 * @date 2015-12-29 上午11:06:05
 */
@Primary
@Repository
public class ServiceStaffCoordCacheImpl extends
		BaseMemcachedCache<ServiceStaffCoordModel,ServiceStaffCoordQueryModel>
		implements ServiceStaffCoordDAO {

	@Resource(name = ServicestaffCacheConstant.SERVICESTAFF_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private ServiceStaffCoordDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public ServiceStaffCoordCacheImpl() {
		super(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFFCOORD_KEY);
	}
	
	/**
	 * 
	 * @Description: (根据患者的id获取model)    
	 * @author XP  
	 * @param serviceStaffUuid
	 * @return
	 * @date 2015-12-29 下午2:17:55
	 */
        @Override
        public ServiceStaffCoordModel getByServiceStaffUuid(String serviceStaffUuid) {
            Object obj = this.mcc.get(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFFCOORD_KEY
                    + serviceStaffUuid);
                 ServiceStaffCoordModel m = null;
                    if (obj != null) {
                            m = (ServiceStaffCoordModel) obj;
                    } else {
                            m = dao.getByServiceStaffUuid(serviceStaffUuid);
                            if (m != null) {
                                    this.mcc.set(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFFCOORD_KEY
                                                    + serviceStaffUuid, m);
                            }
                    }
                    return m;
        }
	
}
