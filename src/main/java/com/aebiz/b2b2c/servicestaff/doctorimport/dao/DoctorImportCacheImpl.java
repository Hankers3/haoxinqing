package com.aebiz.b2b2c.servicestaff.doctorimport.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.servicestaff.common.ServicestaffCacheConstant;
import com.aebiz.b2b2c.servicestaff.doctorimport.vo.DoctorImportModel;
import com.aebiz.b2b2c.servicestaff.doctorimport.vo.DoctorImportQueryModel;
import com.aebiz.b2b2c.servicestaff.hospitalinfo.dao.HospitalInfoDAO;
import com.aebiz.b2b2c.servicestaff.hospitalinfo.vo.HospitalInfoModel;
import com.danga.MemCached.MemCachedClient;

/**
 * 医生的缓存
 * 
 * @author szr
 * 
 */
@Primary
@Repository
public class DoctorImportCacheImpl extends
		BaseMemcachedCache<DoctorImportModel, DoctorImportQueryModel> implements
		DoctorImportDAO {

	@Resource(name = ServicestaffCacheConstant.SERVICESTAFF_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private DoctorImportDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public DoctorImportCacheImpl() {
		super(ServicestaffCacheConstant.SERVICESTAFF_DOCTORIMPORT_KEY);
	}

	@Override
	public String create(DoctorImportModel m) {
		String ret = this.dao.create(m);
		return ret;
	}

	@Override
	public void delete(DoctorImportModel m) {
		super.delete(m);
		super.deleteCache(m);
	}
	@Override
	public void update(DoctorImportModel m) {
		dao.update(m);
		this.mcc.delete(ServicestaffCacheConstant.SERVICESTAFF_DOCTORIMPORT_KEY+m.getUuid());
	}
	/* ——————————缓存—————————— */
	@Override
	public List<DoctorImportModel> getDoctorByRealName(String realName) {
		// 返回对象集合
		List<DoctorImportModel> models = new ArrayList<DoctorImportModel>();
		// 所以uuids
		List<String> uuids = this.getDoctorImportUuidByRealName(realName);
		if( null != uuids && uuids.size() >0){
		// 循环
		for (String uuid : uuids) {
			// Model对象
			DoctorImportModel doctorImportModel = new DoctorImportModel();
			// 从缓存中取值
			Object objModel = this.mcc
					.get(ServicestaffCacheConstant.SERVICESTAFF_DOCTORIMPORT_KEY
							+ uuid);
			if (objModel != null) {
				doctorImportModel = (DoctorImportModel) objModel;
			} else {
				// 获得医生model
				doctorImportModel = dao.getByUuid(uuid);
				if (doctorImportModel != null) {
					// 将医生model放入缓存
					this.mcc.set(
							ServicestaffCacheConstant.SERVICESTAFF_DOCTORIMPORT_KEY
									+ uuid, doctorImportModel);
				}
			}
			// 将model存入返回值
			models.add(doctorImportModel);
		}
		}
		// 返回
		return models;
	}

	@Override
	public List<String> getDoctorImportUuidByRealName(String realName) {
		// uuids集合
		List<String> uuids = new ArrayList<String>();
		// 获得所以未冻结的名医
		uuids = dao.getDoctorImportUuidByRealName(realName);
		return uuids;
	}

}
