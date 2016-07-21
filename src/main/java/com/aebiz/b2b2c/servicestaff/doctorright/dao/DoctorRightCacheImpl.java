package com.aebiz.b2b2c.servicestaff.doctorright.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.servicestaff.common.ServicestaffCacheConstant;
import com.aebiz.b2b2c.servicestaff.doctorright.vo.DoctorRightModel;
import com.aebiz.b2b2c.servicestaff.doctorright.vo.DoctorRightQueryModel;
import com.aebiz.b2b2c.servicestaff.hospitalinfo.vo.HospitalInfoModel;
import com.alibaba.fastjson.JSON;
import com.danga.MemCached.MemCachedClient;

/**
 * 
 * @Description: (医生权限的缓存)    
 * @author XP  
 * @date 2015-12-29 下午1:36:40
 */
@Primary
@Repository
public class DoctorRightCacheImpl extends
		BaseMemcachedCache<DoctorRightModel,DoctorRightQueryModel> implements
		DoctorRightDAO {

	@Resource(name = ServicestaffCacheConstant.SERVICESTAFF_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private DoctorRightDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}
	
	@Override
	public void update(DoctorRightModel m) {
		dao.update(m);
		this.mcc.delete(ServicestaffCacheConstant.SERVICESTAFF_DOCTORRIGHT_KEY + m.getDoctorUuid());
		this.mcc.delete(ServicestaffCacheConstant.SERVICESTAFF_DOCTORRIGHT_KEY + m.getUuid());
	}

	public DoctorRightCacheImpl() {
		super(ServicestaffCacheConstant.SERVICESTAFF_DOCTORRIGHT_KEY);
	}
    
    @Override
    public DoctorRightModel getByDoctorUuid(String doctorUuid) {
    	Object obj = this.mcc.get(ServicestaffCacheConstant.SERVICESTAFF_DOCTORRIGHT_KEY + doctorUuid);
    	DoctorRightModel doctorRightModel = null;
    	System.out.println("=========getByDoctorUuid========");
		if (obj != null) {
	    	System.out.println("=========obj======="+JSON.toJSONString(obj));

			doctorRightModel = (DoctorRightModel) obj;
		} else {
			doctorRightModel = dao.getByDoctorUuid(doctorUuid);
			if(doctorRightModel !=null){
				this.mcc.set(ServicestaffCacheConstant.SERVICESTAFF_DOCTORRIGHT_KEY + doctorUuid, doctorRightModel);
			}
		}
		return doctorRightModel;
	}

    @Override
    public String getExamByDoctorUuid(String doctorUuid) {
    	Object obj = this.mcc.get(ServicestaffCacheConstant.SERVICESTAFF_DOCTORRIGHT_KEY + doctorUuid);
    	DoctorRightModel doctorRightModel = null;
    	String exam="";
		if (obj != null) {
			doctorRightModel = (DoctorRightModel) obj;
			exam = doctorRightModel.getExam();
		} else {
			doctorRightModel = dao.getByDoctorUuid(doctorUuid);
			if(doctorRightModel !=null){
				exam = doctorRightModel.getExam();
				this.mcc.set(ServicestaffCacheConstant.SERVICESTAFF_DOCTORRIGHT_KEY + doctorUuid, doctorRightModel);
			}
		}
        return exam;
    }
    
}
