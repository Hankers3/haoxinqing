package com.aebiz.b2b2c.servicestaff.telephonecounse.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.servicestaff.common.ServicestaffCacheConstant;
import com.aebiz.b2b2c.servicestaff.telephonecounse.vo.TelephoneCounseModel;
import com.aebiz.b2b2c.servicestaff.telephonecounse.vo.TelephoneCounseQueryModel;
import com.danga.MemCached.MemCachedClient;

/**
 * 
 * @Description: (医生的缓存)    
 * @author XP  
 * @date 2015-12-29 上午11:06:05
 */
@Primary
@Repository
public class TelephoneCounseCacheImpl extends
		BaseMemcachedCache<TelephoneCounseModel,TelephoneCounseQueryModel>
		implements TelephoneCounseDAO {

	@Resource(name = ServicestaffCacheConstant.SERVICESTAFF_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private TelephoneCounseDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	@Override
	public void update(TelephoneCounseModel m) {
		dao.update(m);
		this.mcc.delete(ServicestaffCacheConstant.SERVICESTAFF_TELEPHONECOUNSE_KEY+m.getUuid());
	}
	public TelephoneCounseCacheImpl() {
		super(ServicestaffCacheConstant.SERVICESTAFF_TELEPHONECOUNSE_KEY);
	}
	
	/**
     * 
     * @Description: (根据医生的id获取医生公告的uuid的列表)    
     * @author XP  
     * @param doctorUuid
     * @return
     * @date 2015-12-29 上午11:44:16
     */
    @Override
    public List<String> getUuidsByDoctorUuid(String doctorUuid) {
        List<String> uuids  = this.dao.getUuidsByDoctorUuid(doctorUuid);
        return uuids;
    }

	/**
	 * 
	 * @Description: (根据医生的id获取咨询设置的uuid的集合)    
	 * @author XP  
	 * @param doctorUuid
	 * @return
	 * @date 2015-12-29 下午3:06:13
	 */
    @Override
    public List<TelephoneCounseModel> getAllTelephoneCounseModels(String doctorUuid) {
        List<String> uuids = this.getUuidsByDoctorUuid(doctorUuid);
        List<TelephoneCounseModel> listM = new ArrayList<TelephoneCounseModel>();
        List<String> noUuids = new ArrayList<String>();
        if (uuids != null && uuids.size() > 0) {
                for (String uuid : uuids) {
                        Object obj = this.mcc
                                        .get(ServicestaffCacheConstant.SERVICESTAFF_TELEPHONECOUNSE_KEY
                                                        + uuid);
                        if (obj != null) {
                            TelephoneCounseModel m = (TelephoneCounseModel) obj;
                                listM.add(m);
                        } else {
                                noUuids.add(uuid);
                        }
                }
                if (noUuids.size() > 0) {
                        for (String uuid : noUuids) {
                            TelephoneCounseModel m = dao.getByUuid(uuid);
                                if (m != null) {
                                        this.mcc.set(
                                                ServicestaffCacheConstant.SERVICESTAFF_TELEPHONECOUNSE_KEY
                                                                        + uuid, m);
                                        listM.add(m);
                                }
                        }
                }
        }
        return listM;
    }
    
    /**
     * 
     * @Description: (这里用一句话描述这个方法的作用)    
     * @author XP  
     * @param weekDate
     * @return
     * @date 2015-12-29 下午3:05:02
     */
    @Override
    public TelephoneCounseModel getTeleCounse(String weekDate,String doctorUuid) {
        return dao.getTeleCounse(weekDate,doctorUuid);
    }

    @Override
    public TelephoneCounseModel getTeleCounse(String doctorUuid, String weekDate, String startTime,
            String endTime, String teleTimeUuid, String teleCostUuid) {
        return dao. getTeleCounse( doctorUuid,  weekDate,  startTime,
                 endTime, teleTimeUuid,  teleCostUuid);
    }
	
}
