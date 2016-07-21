package com.aebiz.b2b2c.servicestaff.homevisitset.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.customermgr.common.CustomerCacheConstant;
import com.aebiz.b2b2c.customermgr.customeraddress.vo.CustomerAddressModel;
import com.aebiz.b2b2c.servicestaff.common.ServicestaffCacheConstant;
import com.aebiz.b2b2c.servicestaff.doctorimport.vo.DoctorImportModel;
import com.aebiz.b2b2c.servicestaff.doctortelecoun.vo.DoctorTeleCounModel;
import com.aebiz.b2b2c.servicestaff.doctortelecoun.vo.DoctorTeleCounQueryModel;
import com.aebiz.b2b2c.servicestaff.groupfriends.vo.GroupFriendsModel;
import com.aebiz.b2b2c.servicestaff.groupfriends.vo.GroupFriendsQueryModel;
import com.aebiz.b2b2c.servicestaff.homevisitset.vo.HomeVisitSetModel;
import com.aebiz.b2b2c.servicestaff.homevisitset.vo.HomeVisitSetQueryModel;
import com.danga.MemCached.MemCachedClient;

/**
 * 
 * @Description: (医生的缓存)    
 * @author XP  
 * @date 2015-12-29 下午1:36:40
 */
@Primary
@Repository
public class HomeVisitSetCacheImpl extends
		BaseMemcachedCache<HomeVisitSetModel, HomeVisitSetQueryModel> implements
		HomeVisitSetDAO {

	@Resource(name = ServicestaffCacheConstant.SERVICESTAFF_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private HomeVisitSetDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public HomeVisitSetCacheImpl() {
		super(ServicestaffCacheConstant.SERVICESTAFF_HOMEVISITSET_KEY);
	}
	@Override
	public void update(HomeVisitSetModel m) {
		dao.update(m);
		this.mcc.delete(ServicestaffCacheConstant.SERVICESTAFF_HOMEVISITSET_KEY+m.getUuid());
	}
	
	/**
	 * 
	 * @Description: (根据医生的id获取出诊的uuids)    
	 * @author XP  
	 * @param doctorid
	 * @return
	 * @date 2015-12-29 下午1:48:39
	 */
	@Override
	public List<String> getUuidsByDoctorUuid(String doctorid) {
	    List<String> uuids = this.dao.getUuidsByDoctorUuid(doctorid);
           
        return uuids;
	 }
	/**
	 * 
	 * @Description: (根据医生的id获取出诊时间的集合)    
	 * @author XP  
	 * @param doctorid
	 * @return
	 * @date 2015-12-29 下午1:44:35
	 */
    @Override
    public List<HomeVisitSetModel> getByDoctorUuid(String doctorid) {
        List<String> uuids = this.getUuidsByDoctorUuid(doctorid);
        List<HomeVisitSetModel> listM = new ArrayList<HomeVisitSetModel>();
        List<String> noUuids = new ArrayList<String>();
        if (uuids != null && uuids.size() > 0) {
            for (String uuid : uuids) {
                Object obj = this.mcc.get(ServicestaffCacheConstant.SERVICESTAFF_HOMEVISITSET_KEY + uuid);
                if (obj != null) {
                    HomeVisitSetModel m = (HomeVisitSetModel) obj;
                    listM.add(m);
                } else {
                   noUuids.add(uuid);
                }
            }
            if (noUuids.size() > 0) {
                for (String uuid : noUuids) {
                    HomeVisitSetModel m = dao.getByUuid(uuid);
                    if (m != null) {
                    this.mcc.set(ServicestaffCacheConstant.SERVICESTAFF_HOMEVISITSET_KEY + uuid, m);
                    listM.add(m);
                    }
                }
            }
        }
        return listM;
    }

	@Override
	public HomeVisitSetModel getHomeVisitSet(String weekDate,String doctorUuid) {
		
		return dao.getHomeVisitSet(weekDate,doctorUuid);
	}

        @Override
        public int getPlusNumByDoctorUuidAndWeekDayAndTimeType(String doctorUuid, String weekDay, String timeType) {
            return dao.getPlusNumByDoctorUuidAndWeekDayAndTimeType(doctorUuid, weekDay, timeType);
        }

		@Override
		public List<HomeVisitSetModel> getByDoctorUuid(String doctorid,
				String plusState) {
			return dao.getByDoctorUuid(doctorid, plusState);
		}

	
}
