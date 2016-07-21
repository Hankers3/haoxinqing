package com.aebiz.b2b2c.servicestaff.servicestaffinterview.dao;

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
import com.danga.MemCached.MemCachedClient;

/**
 * 
 * @Description: (医生的缓存)    
 * @author XP  
 * @date 2015-12-29 上午11:06:05
 */
@Primary
@Repository
public class ServicestaffinterviewCacheImpl extends
		BaseMemcachedCache<ServicestaffinterviewModel,ServicestaffinterviewQueryModel>
		implements ServicestaffinterviewDAO {

	@Resource(name = ServicestaffCacheConstant.SERVICESTAFF_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private ServicestaffinterviewDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public ServicestaffinterviewCacheImpl() {
		super(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFFINTERVIEW_KEY);
	}
        /**
         * 
         * @Description: (根据家政人员的id获取家政基本的信息的对象)    
         * @author XP  
         * @param ServicestaffUuid
         * @return
         * @date 2015-12-29 下午2:42:35
         */
        @Override
        public ServicestaffinterviewModel getServicestaffinterviewModelByServicestaffUuid(String ServicestaffUuid) {
            Object obj = this.mcc.get(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFFSOURCE_KEY
                    + ServicestaffUuid);
                ServicestaffinterviewModel m = null;
                    if (obj != null) {
                            m = (ServicestaffinterviewModel) obj;
                    } else {
                            m = dao.getServicestaffinterviewModelByServicestaffUuid(ServicestaffUuid);
                            if (m != null) {
                                    this.mcc.set(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFFSOURCE_KEY
                                                    + ServicestaffUuid, m);
                            }
                    }
                    return m;
        }
	
	
}
