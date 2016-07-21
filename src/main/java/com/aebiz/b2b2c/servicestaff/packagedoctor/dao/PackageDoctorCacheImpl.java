package com.aebiz.b2b2c.servicestaff.packagedoctor.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.servicestaff.common.ServicestaffCacheConstant;
import com.aebiz.b2b2c.servicestaff.doctorimport.vo.DoctorImportModel;
import com.aebiz.b2b2c.servicestaff.doctorimport.vo.DoctorImportQueryModel;
import com.aebiz.b2b2c.servicestaff.doctorright.vo.DoctorRightModel;
import com.aebiz.b2b2c.servicestaff.hospitalinfo.dao.HospitalInfoDAO;
import com.aebiz.b2b2c.servicestaff.hospitalinfo.vo.HospitalInfoModel;
import com.aebiz.b2b2c.servicestaff.packagedoctor.vo.PackageDoctorModel;
import com.aebiz.b2b2c.servicestaff.packagedoctor.vo.PackageDoctorQueryModel;
import com.danga.MemCached.MemCachedClient;

/**
 * 
 * @Description: (医生的缓存)    
 * @author XP  
 * @date 2015-12-29 下午1:57:31
 */
@Primary
@Repository
public class PackageDoctorCacheImpl extends
		BaseMemcachedCache<PackageDoctorModel, PackageDoctorQueryModel> implements
		PackageDoctorDAO {

	@Resource(name = ServicestaffCacheConstant.SERVICESTAFF_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private PackageDoctorDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public PackageDoctorCacheImpl() {
		super(ServicestaffCacheConstant.SERVICESTAFF_PACKAGEDOCTOR_KEY);
	}
	
	@Override
	public void update(PackageDoctorModel m) {
		dao.update(m);
		this.mcc.delete(ServicestaffCacheConstant.SERVICESTAFF_PACKAGEDOCTOR_KEY+m.getUuid());
	}
	
	/**
	 * 
	 * @Description: (根据医生id获取套餐关联的缓存)    
	 * @author XP  
	 * @param doctorid
	 * @return
	 * @date 2015-12-29 下午2:01:48
	 */
    @Override
    public PackageDoctorModel getByDoctorUuid(String doctorid) {
        Object obj = this.mcc
                .get(ServicestaffCacheConstant.SERVICESTAFF_PACKAGEDOCTOR_KEY
                                + doctorid);
        PackageDoctorModel m = null;
        if (obj != null) {
                m = (PackageDoctorModel) obj;
        } else {
                m = dao.getByDoctorUuid(doctorid);
                if (m != null) {
                        this.mcc.set(ServicestaffCacheConstant.SERVICESTAFF_PACKAGEDOCTOR_KEY
                                        + doctorid, m);
                }
        }
        return m;
    }

    @Override
    public PackageDoctorModel getByDoctorUuidAndPackageUuid(String doctorid, String packageUuid) {
        return dao.getByDoctorUuidAndPackageUuid(doctorid, packageUuid);
    }
    
    @Override
    public List<String> getPackageUuidsByDoctorUuid(String doctorUuid) {
        return dao.getPackageUuidsByDoctorUuid(doctorUuid);
    }
	
}
