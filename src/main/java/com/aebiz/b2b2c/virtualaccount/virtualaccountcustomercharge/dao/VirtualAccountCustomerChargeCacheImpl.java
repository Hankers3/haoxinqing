package com.aebiz.b2b2c.virtualaccount.virtualaccountcustomercharge.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.virtualaccount.common.VirtualAccountCacheConstant;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomercharge.vo.VirtualAccountCustomerChargeModel;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomercharge.vo.VirtualAccountCustomerChargeQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class VirtualAccountCustomerChargeCacheImpl
		extends
		BaseMemcachedCache<VirtualAccountCustomerChargeModel, VirtualAccountCustomerChargeQueryModel>
		implements VirtualAccountCustomerChargeDAO {
	@Resource(name = VirtualAccountCacheConstant.VIRTUAL_ACCOUNT_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private VirtualAccountCustomerChargeDAO myDao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(myDao);
	}
	
	@Override
	public void update(VirtualAccountCustomerChargeModel m) {
		myDao.update(m);
		this.mcc.delete(VirtualAccountCacheConstant.VIRTUAL_ACCOUNT_CUSTOMER_CHARGE +m.getUuid());
	}
	public VirtualAccountCustomerChargeCacheImpl() {
		super(VirtualAccountCacheConstant.VIRTUAL_ACCOUNT_CUSTOMER_CHARGE);
	}

	@Override
	public int getChargeCount(String customerUuid, String state) {
		return this.myDao.getChargeCount(customerUuid, state);
	}

	@Override
	public List<VirtualAccountCustomerChargeModel> searchCharge(
			String customerUuid, String state, int fromNum, int pageShow) {
		List<String> uuids = this.myDao.searchChargeUuids(customerUuid, state,
				fromNum, pageShow);
		List<VirtualAccountCustomerChargeModel> listM = new ArrayList<VirtualAccountCustomerChargeModel>();
		List<String> noList = new ArrayList<String>();
		if (uuids != null && uuids.size() > 0) {
			for (String uuid : uuids) {
				Object obj = this.mcc
						.get(VirtualAccountCacheConstant.VIRTUAL_ACCOUNT_CUSTOMER_CHARGE
								+ uuid);
				if (obj != null) {
					VirtualAccountCustomerChargeModel m = (VirtualAccountCustomerChargeModel) obj;
					listM.add(m);
				} else {
					noList.add(uuid);
				}
			}
			if (noList.size() > 0) {
				for (String uuid : noList) {
					VirtualAccountCustomerChargeModel m = this.myDao
							.getByUuid(uuid);
					if (m != null) {
						this.mcc.set(
								VirtualAccountCacheConstant.VIRTUAL_ACCOUNT_CUSTOMER_CHARGE
										+ uuid, m);
						listM.add(m);
					}
				}
			}
		}
		return listM;
	}

	@Override
	public List<String> searchChargeUuids(String customerUuid, String state,
			int fromNum, int pageShow) {
		return this.myDao.searchChargeUuids(customerUuid, state, fromNum,
				pageShow);
	}

	@Override
	public int getAllChargeCount(VirtualAccountCustomerChargeQueryModel qm) {
		
		return  this.myDao.getAllChargeCount(qm);
	}

	/**
	 * 查询某一个会员的所有充值记录Uuids
	 * @param customerUuid
	 * @return
	 */
	@Override
	public List<String> searchUuidsAllCharge(String customerUuid) {
		List<String> uuids = this.myDao.searchUuidsAllCharge(customerUuid);
		return uuids;
	}
	
	/**
	 * 查询某一个会员的所有充值记录
	 * @param customerUuid
	 * @return
	 */
	@Override
	public List<VirtualAccountCustomerChargeModel> searchAllCharge(
			String customerUuid) {
		List<String> uuids  =  this.searchUuidsAllCharge(customerUuid);
		
		List<VirtualAccountCustomerChargeModel> listM = new ArrayList<VirtualAccountCustomerChargeModel>();
		List<String> noUuids = new ArrayList<String>();
		
		if (uuids != null && uuids.size() > 0) {
			for (String uuid : uuids) {
				Object obj = this.mcc.get(VirtualAccountCacheConstant.VIRTUAL_ACCOUNT_CUSTOMER_CHARGE+ uuid);
				if(obj !=null ){
					VirtualAccountCustomerChargeModel va = (VirtualAccountCustomerChargeModel) obj;
					listM.add(va);
				}else{
					noUuids.add(uuid);
				}
			}
			
			if(noUuids.size()>0){
				for(String uuid :noUuids){
					VirtualAccountCustomerChargeModel va =  this.myDao.getByUuid(uuid);
					if (va != null) {
						this.mcc.set(VirtualAccountCacheConstant.VIRTUAL_ACCOUNT_CUSTOMER_CHARGE+ uuid, va);
						listM.add(va);
					}
				}
			}
		}
			
		return listM;
	}

	
	@Override
	public VirtualAccountCustomerChargeModel getVirtualAccountCustomerChargeModelByOrderUuid(
			String orderUuid) {
		Object vc = this.mcc.get(VirtualAccountCacheConstant.VIRTUAL_ACCOUNT_CUSTOMER_CHARGE_MOBILE+orderUuid);
		VirtualAccountCustomerChargeModel vam =null;
		if(vc !=null ){
			 vam = (VirtualAccountCustomerChargeModel) vc;
		}else{
			vam = this.myDao.getVirtualAccountCustomerChargeModelByOrderUuid(orderUuid);
			if(vam !=null ){
				 this.mcc.set(VirtualAccountCacheConstant.VIRTUAL_ACCOUNT_CUSTOMER_CHARGE_MOBILE+orderUuid, vam);
			}
		}
		return vam;
	}



}
