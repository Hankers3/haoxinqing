package com.aebiz.b2b2c.virtualaccount.virtualaccountcustomerlog.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.virtualaccount.common.VirtualAccountCacheConstant;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomerlog.vo.VirtualAccountCustomerLogModel;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomerlog.vo.VirtualAccountCustomerLogQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class VirtualAccountCustomerLogCacheImpl
		extends
		BaseMemcachedCache<VirtualAccountCustomerLogModel, VirtualAccountCustomerLogQueryModel>
		implements VirtualAccountCustomerLogDAO {
	@Resource(name = VirtualAccountCacheConstant.VIRTUAL_ACCOUNT_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private VirtualAccountCustomerLogDAO myDao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(myDao);
	}
	
	@Override
	public void update(VirtualAccountCustomerLogModel m) {
		myDao.update(m);
		this.mcc.delete(VirtualAccountCacheConstant.VIRTUAL_ACCOUNT_CUSTOMER_CHARGE +m.getUuid());
	}
	
	public VirtualAccountCustomerLogCacheImpl() {
		super(VirtualAccountCacheConstant.VIRTUAL_ACCOUNT_CUSTOMER_LOG);
	}

	@Override
	public int getCustomerAccountLogCount(String customerUuid, String operType) {
		return this.myDao.getCustomerAccountLogCount(customerUuid, operType);
	}
	
	@Override
	public VirtualAccountCustomerLogModel getVirtualAccountCustomerLogModelByOrderUuid(
			String orderUuid) {
		Object obj  = this.mcc.get(VirtualAccountCacheConstant.VIRTUAL_ACCOUNT_CUSTOMER_LOG+orderUuid);
		VirtualAccountCustomerLogModel vacm =null;
		if(obj !=null){
			vacm = (VirtualAccountCustomerLogModel) obj;
		}else{
			vacm = this.myDao.getVirtualAccountCustomerLogModelByOrderUuid(orderUuid);
			if(vacm !=null){
				this.mcc.set(VirtualAccountCacheConstant.VIRTUAL_ACCOUNT_CUSTOMER_LOG+orderUuid, vacm);
			}
		}
		return vacm;
	}

	@Override
	public String getCustomerUuidByLogsUuid(String uuid) {
		Object obj  = this.mcc.get(VirtualAccountCacheConstant.VIRTUAL_ACCOUNT_CUSTOMER_LOG_UUID+uuid);
		String customerUuid="";
		if(obj !=null){
			customerUuid=(String) obj;
		}else{
			customerUuid = this.myDao.getCustomerUuidByLogsUuid(uuid);
			if(!StringUtil.isEmpty(customerUuid)){
				this.mcc.set(VirtualAccountCacheConstant.VIRTUAL_ACCOUNT_CUSTOMER_LOG_UUID+uuid, customerUuid);
			}
		}
		return customerUuid;
	}

        @Override
        public List<VirtualAccountCustomerLogModel> getVirtualAccountCustomerLogListByCustomerUuid(
                String customerUuid) {
            return myDao.getVirtualAccountCustomerLogListByCustomerUuid(customerUuid);
        }
	
    	

}
