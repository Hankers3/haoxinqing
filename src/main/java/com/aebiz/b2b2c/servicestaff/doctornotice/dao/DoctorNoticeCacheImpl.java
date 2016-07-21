package com.aebiz.b2b2c.servicestaff.doctornotice.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.servicestaff.bankrelation.vo.BankRelationModel;
import com.aebiz.b2b2c.servicestaff.common.ServicestaffCacheConstant;
import com.aebiz.b2b2c.servicestaff.doctornotice.vo.DoctorNoticeModel;
import com.aebiz.b2b2c.servicestaff.doctornotice.vo.DoctorNoticeQueryModel;
import com.danga.MemCached.MemCachedClient;

/**
 * 
 * @Description: (医生的缓存)    
 * @author XP  
 * @date 2015-12-29 上午11:06:05
 */
@Primary
@Repository
public class DoctorNoticeCacheImpl extends
		BaseMemcachedCache<DoctorNoticeModel, DoctorNoticeQueryModel>
		implements DoctorNoticeDAO {

	@Resource(name = ServicestaffCacheConstant.SERVICESTAFF_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private DoctorNoticeDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public DoctorNoticeCacheImpl() {
		super(ServicestaffCacheConstant.SERVICESTAFF_DOCTORNOTICE_KEY);
	}
	
	@Override
	public void update(DoctorNoticeModel m) {
		dao.update(m);
		this.mcc.delete(ServicestaffCacheConstant.SERVICESTAFF_DOCTORNOTICE_KEY+m.getUuid());
	}
	/**
	 * 
	 * @Description: (删除缓存)    
	 * @author XP  
	 * @param uuid
	 * @date 2015-12-29 上午11:18:39
	 */
    @Override
    public void deleteById(String uuid) {
        dao.deleteById(uuid);
        mcc.delete(ServicestaffCacheConstant.SERVICESTAFF_DOCTORNOTICE_KEY+uuid);
    }
    
    /**
     * 
     * @Description: (根据医生的id获取医生公告的model)    
     * @author XP  
     * @param doctorId
     * @return
     * @date 2015-12-29 上午11:23:36
     */
    public String getDoctorNoticeUuid(String doctorId) {
        return dao.getDoctorNoticeUuid(doctorId);
    }
    /**
     * 
     * @Description: (根据医生的id获取医生公告的对象)    
     * @author XP  
     * @param doctorId
     * @return
     * @date 2015-12-29 上午11:20:49
     */
    @Override
    public DoctorNoticeModel getDoctorNoticeModelByDoctorUuid(String doctorId) {
        String uuid = this.getDoctorNoticeUuid(doctorId);
        if (!StringUtil.isEmpty(uuid)) {
                // 从缓存中取得对象
                DoctorNoticeModel cm = (DoctorNoticeModel) mcc
                                .get(ServicestaffCacheConstant.SERVICESTAFF_DOCTORNOTICE_KEY
                                                + uuid);

                // 如果对象为空，则从数据库中查询
                if (cm == null) {
                        cm = (DoctorNoticeModel) dao.getByUuid(uuid);

                        // 从数据库中查询，如果存在，则放入到缓存
                        if (cm != null) {
                                mcc.set(ServicestaffCacheConstant.SERVICESTAFF_DOCTORNOTICE_KEY
                                                + uuid, cm);
                        }
                }
                return cm;
        }
        return null;
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
        List<String> uuids = this.dao.getUuidsByDoctorUuid(doctorUuid);
      
        return uuids;
    }
  
    /**
     * 
     * @Description: (根据医生的id获取医生公告列表的缓存)    
     * @author XP  
     * @param doctorUuid
     * @return
     * @date 2015-12-29 上午11:38:20
     */
    @Override
    public List<DoctorNoticeModel> getAllDoctorNoticeList(String doctorUuid) {
        List<String> uuids = this.getUuidsByDoctorUuid(doctorUuid);
        List<DoctorNoticeModel> listM = new ArrayList<DoctorNoticeModel>();
        List<String> noUuids = new ArrayList<String>();
        if (uuids != null && uuids.size() > 0) {
                for (String uuid : uuids) {
                        Object obj = this.mcc
                                        .get(ServicestaffCacheConstant.SERVICESTAFF_BANKRELATION_KEY
                                                        + uuid);
                        if (obj != null) {
                            DoctorNoticeModel m = (DoctorNoticeModel) obj;
                                listM.add(m);
                        } else {
                                noUuids.add(uuid);
                        }
                }
                if (noUuids.size() > 0) {
                        for (String uuid : noUuids) {
                            DoctorNoticeModel m = dao.getByUuid(uuid);
                                if (m != null) {
                                        this.mcc.set(
                                                ServicestaffCacheConstant.SERVICESTAFF_BANKRELATION_KEY
                                                                        + uuid, m);
                                        listM.add(m);
                                }
                        }
                }
        }
        return listM;
    }

	
}
