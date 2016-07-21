package com.aebiz.b2b2c.servicestaff.doctortag.dao;

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
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoModel;
import com.aebiz.b2b2c.servicestaff.common.ServicestaffCacheConstant;
import com.aebiz.b2b2c.servicestaff.doctorimport.vo.DoctorImportModel;
import com.aebiz.b2b2c.servicestaff.doctornotice.vo.DoctorNoticeModel;
import com.aebiz.b2b2c.servicestaff.doctorright.vo.DoctorRightModel;
import com.aebiz.b2b2c.servicestaff.doctorright.vo.DoctorRightQueryModel;
import com.aebiz.b2b2c.servicestaff.doctortag.vo.DoctorTagModel;
import com.aebiz.b2b2c.servicestaff.doctortag.vo.DoctorTagQueryModel;
import com.danga.MemCached.MemCachedClient;

/**
 * 
 * @Description: (这里用一句话描述这个方法的作用)    
 * @author XP  
 * @date 2015-12-29 下午12:17:01
 */
@Primary
@Repository
public class DoctorTagCacheImpl extends
		BaseMemcachedCache<DoctorTagModel, DoctorTagQueryModel> implements
		DoctorTagDAO {

	@Resource(name = ServicestaffCacheConstant.SERVICESTAFF_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private DoctorTagDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public DoctorTagCacheImpl() {
		super(ServicestaffCacheConstant.SERVICESTAFF_DOCTORTAG_KEY);
	}
	
	/**
	 * 
	 * @Description: (根据医生的id获取标签的集合)    
	 * @author XP  
	 * @param doctorUuid
	 * @return
	 * @date 2015-12-29 下午1:33:00
	 */
        @Override
        public List<String> getByDoctorUuid(String doctorUuid) {
            List<String>  tags = (List<String>) this.mcc.get(ServicestaffCacheConstant.SERVICESTAFF_DOCTORTAG_KEY+doctorUuid);
            if(tags ==null ||tags.size()<0){
                tags = this.dao.getByDoctorUuid(doctorUuid);
                this.mcc.set(ServicestaffCacheConstant.SERVICESTAFF_DOCTORTAG_KEY+doctorUuid, tags);
            }
             return tags;
        }
        /**
         * 
         * @Description: (根据医生的id删除医生的标签的缓存)    
         * @author XP  
         * @param doctorUuid
         * @date 2015-12-29 下午1:33:29
         */
        @Override
        public void deleteByDoctorUuid(String doctorUuid) {
                dao.deleteByDoctorUuid(doctorUuid);
                mcc.delete(ServicestaffCacheConstant.SERVICESTAFF_DOCTORTAG_KEY+doctorUuid);
            
        }
	
	
	

}
