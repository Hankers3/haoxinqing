package com.aebiz.b2b2c.vipclub.vipclubintegrallog.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.vipclub.common.VipClubCacheConstant;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.VipclubIntegralLogModel;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.VipclubIntegralLogQueryModel;
import com.aebiz.b2b2c.virtualaccount.common.VirtualAccountCacheConstant;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomerlog.vo.VirtualAccountCustomerLogModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class VipclubIntegralLogCacheImpl extends
		BaseMemcachedCache<VipclubIntegralLogModel, VipclubIntegralLogQueryModel> implements VipclubIntegralLogDAO {
	@Resource(name = VipClubCacheConstant.VIP_CLUB_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private VipclubIntegralLogDAO myDao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(myDao);
	}

	public VipclubIntegralLogCacheImpl() {
		super(VipClubCacheConstant.VIP_CLUB_INTEGRAL_LOG);
	}

	@Override
	public List<VipclubIntegralLogModel> getByCustomerUuid(String customerUuid) {
		List<String> uuids = this.myDao.getUuidsByCustomerUuid(customerUuid);
		List<VipclubIntegralLogModel> listM = new ArrayList<VipclubIntegralLogModel>();
		List<String> noList = new ArrayList<String>();
		if (uuids != null && uuids.size() > 0) {
			for (String uuid : uuids) {
				Object obj = this.mcc.get(VipClubCacheConstant.VIP_CLUB_INTEGRAL_LOG + uuid);
				if (obj != null) {
					VipclubIntegralLogModel m = (VipclubIntegralLogModel) obj;
					listM.add(m);
				} else {
					noList.add(uuid);
				}
			}
			if (noList.size() > 0) {
				for (String uuid : noList) {
					VipclubIntegralLogModel m = this.myDao.getByUuid(uuid);
					if (m != null) {
						listM.add(m);
						this.mcc.set(VipClubCacheConstant.VIP_CLUB_INTEGRAL_LOG + uuid, m);
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
	public int getTypeIntegralSumByConditon(VipclubIntegralLogQueryModel qm) {
		return this.myDao.getTypeIntegralSumByConditon(qm);
	}

	@Override
	public int getVipclubIntegralLogModelListCountByConditon(VipclubIntegralLogQueryModel qm) {

		return myDao.getVipclubIntegralLogModelListCountByConditon(qm);
	}

	@Override
	public List<VipclubIntegralLogModel> getByCustomerUuid(String customerUuid, int pageCount, int pageNo) {

		return myDao.getByCustomerUuid(customerUuid, pageCount, pageNo);
	}

	@Override
	public List<VipclubIntegralLogModel> getbyDoctorUuid(String doctorUuid) {
		return myDao.getbyDoctorUuid(doctorUuid);
	}

	/**
	 * 跟据患者的id获取患者所有积分记录的uuids
	 * 
	 * @param customerUuid
	 * @return
	 */
	public List<String> getVipclubIntegralLogModelUuids(String customerUuid) {
		List<String> uuids = this.myDao.getVipclubIntegralLogModelUuids(customerUuid);
	
		return uuids;
	}

	@Override
	public List<VipclubIntegralLogModel> getVipclubIntegralLogModelListByUuid(String customerUuid) {
		List<String> uuids = this.getVipclubIntegralLogModelUuids(customerUuid);
		List<VipclubIntegralLogModel> listM = new ArrayList<VipclubIntegralLogModel>();
		List<String> noUuids = new ArrayList<String>();
		if (uuids != null && uuids.size() > 0) {
			for (String uuid : uuids) {
				Object obj = this.mcc.get(VipClubCacheConstant.VIP_CLUB_INTEGRAL_LOG + uuid);
				if (obj != null) {
					VipclubIntegralLogModel m = (VipclubIntegralLogModel) obj;
					listM.add(m);
				} else {
					noUuids.add(uuid);
				}
			}
			if (noUuids.size() > 0) {
				for (String uuid : noUuids) {
					VipclubIntegralLogModel m = myDao.getByUuid(uuid);
					if (m != null) {
						this.mcc.set(VipClubCacheConstant.VIP_CLUB_INTEGRAL_LOG + uuid, m);
						listM.add(m);
					}
				}
			}
		}
		return listM;
	}

	/**
	 * 
	 * @Description: (根据用户id和用户类型以及描述获取积分日志对象)
	 * @author XP
	 * @param doctorid
	 * @param userType
	 * @param description
	 * @return
	 * @date 2016-1-19 下午1:53:14
	 */
	@Override
	public VipclubIntegralLogModel getVipclubIntegralLogByConditions(String doctorid, String userType,
			String intergralType, String description) {
		return myDao.getVipclubIntegralLogByConditions(doctorid, userType, intergralType, description);
	}

	@Override
	public VipclubIntegralLogModel getVipclubIntegralLogByquizResultUuid(String customerUuid, String vipclubUsertypeCus,
			String quizCategoryUuid) {
		return myDao.getVipclubIntegralLogByquizResultUuid(customerUuid, vipclubUsertypeCus, quizCategoryUuid);
	}
	
	public List<VipclubIntegralLogModel> getByCondition(VipclubIntegralLogQueryModel qm, int start, int pageShow) {
	    List<String> uuids = this.myDao.getUuidsByCondition(qm, start, pageShow);
	    
	    List<VipclubIntegralLogModel> listM = new ArrayList();
	    List<String> noUuids = new ArrayList();
	    
	    for (String uuid : uuids) {
	      Object obj = this.mcc.get(VipClubCacheConstant.VIP_CLUB_INTEGRAL_LOG  + uuid);
	      if (obj != null) {
	    	VipclubIntegralLogModel m = (VipclubIntegralLogModel)obj;
	        listM.add(m);
	      } else {
	    	 VipclubIntegralLogModel m = this.myDao.getByUuid(uuid);
		     this.mcc.set(VipClubCacheConstant.VIP_CLUB_INTEGRAL_LOG + uuid, m);
		     listM.add(m);
	        //noUuids.add(uuid);
	      }
	    }
	    
	    /*
	    if (noUuids.size() > 0) {
	      for (String uuid : noUuids) {
	        M m = this.dao.getByUuid(uuid);
	        log.info("从数据库中取到==" + m);
	        
	        log.info("sssset  key==" + this.MEM_PRE + uuid);
	        this.mcc.set(this.MEM_PRE + uuid, m);
	        
	        log.info("now get====" + this.mcc.get(new StringBuilder().append(this.MEM_PRE).append(uuid).toString()));
	        listM.add(m);
	      }
	    }*/
	    
	    return listM;
	  }

}
