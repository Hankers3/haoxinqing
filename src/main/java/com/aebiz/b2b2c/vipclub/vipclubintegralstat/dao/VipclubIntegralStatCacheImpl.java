package com.aebiz.b2b2c.vipclub.vipclubintegralstat.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.vipclub.common.VipClubCacheConstant;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.VipclubIntegralLogModel;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.vo.VipclubIntegralStatModel;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.vo.VipclubIntegralStatQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class VipclubIntegralStatCacheImpl
		extends
		BaseMemcachedCache<VipclubIntegralStatModel, VipclubIntegralStatQueryModel>
		implements VipclubIntegralStatDAO {
	@Resource(name = VipClubCacheConstant.VIP_CLUB_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private VipclubIntegralStatDAO myDao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(myDao);
	}

	public VipclubIntegralStatCacheImpl() {
		super(VipClubCacheConstant.VIP_CLUB_INTEGRAL_STAT);
	}
	@Override
	public List<VipclubIntegralStatModel> getByCondition(
			VipclubIntegralStatQueryModel qm, int start, int pageShow) {
		
		return myDao.getByCondition(qm, start, pageShow);
	}
	
	@Override
	public List<VipclubIntegralStatModel> getByCustomerUuid(String customerUuid) {
		List<String> uuids = this.myDao.getUuidsByCustomerUuid(customerUuid);
		List<VipclubIntegralStatModel> listM = new ArrayList<VipclubIntegralStatModel>();
		List<String> noList = new ArrayList<String>();
		if (uuids != null && uuids.size() > 0) {
			for (String uuid : uuids) {
				Object obj = this.mcc
						.get(VipClubCacheConstant.VIP_CLUB_INTEGRAL_STAT + uuid);
				if (obj != null) {
					VipclubIntegralStatModel m = (VipclubIntegralStatModel) obj;
					listM.add(m);
				} else {
					noList.add(uuid);
				}
			}
			if (noList.size() > 0) {
				for (String uuid : noList) {
					VipclubIntegralStatModel m = this.myDao.getByUuid(uuid);
					if (m != null) {
						listM.add(m);
						this.mcc.set(
								VipClubCacheConstant.VIP_CLUB_INTEGRAL_STAT
										+ uuid, m);
					}
				}
			}
		}
		return listM;
	}

	@Override
	public List<String> getUuidsByCustomerUuid(String customerUuid) {
		return this.myDao.getUuidsByCustomerUuid(customerUuid);
	}

	@Override
	public VipclubIntegralStatModel getByCustomerUuidAndIntegralType(
			String customerUuid, String integralType) {
		VipclubIntegralStatModel m = null;
		String uuid = this.myDao.getUuidByCustomerUuidAndIntegralType(
				customerUuid, integralType);
		if (!StringUtil.isEmpty(uuid)) {
			Object obj = this.mcc
					.get(VipClubCacheConstant.VIP_CLUB_INTEGRAL_STAT + uuid);
			if (obj != null) {
				m = (VipclubIntegralStatModel) obj;
			} else {
				m = this.myDao.getByUuid(uuid);
				if (m != null) {
					this.mcc.set(VipClubCacheConstant.VIP_CLUB_INTEGRAL_STAT
							+ uuid, m);
				}
			}
		}
		return m;
	}

	@Override
	public String getUuidByCustomerUuidAndIntegralType(String customerUuid,
			String integralType) {
		return this.myDao.getUuidByCustomerUuidAndIntegralType(customerUuid,
				integralType);
	}

	@Override
	public int getUsaleIntegralByCustomerUuid(String customerUuid) {
		return this.myDao.getUsaleIntegralByCustomerUuid(customerUuid);
	}

	@Override
	public int getVipclubIntegralCount(String customerUuid, String integralType) {
		return myDao.getVipclubIntegralCount(customerUuid, integralType);
	}

}
