package com.aebiz.b2b2c.servicestaff.servicestaffinfo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.servicestaff.common.ServicestaffCacheConstant;
import com.aebiz.b2b2c.servicestaff.servicestaffinfo.vo.ServicestaffinfoModel;
import com.aebiz.b2b2c.servicestaff.servicestaffinfo.vo.ServicestaffinfoQueryModel;
import com.danga.MemCached.MemCachedClient;

/**
 * 医生基本信息的缓存
 * 
 * @author szr
 * 
 */
@Primary
@Repository
public class ServicestaffinfoCacheImpl extends
		BaseMemcachedCache<ServicestaffinfoModel, ServicestaffinfoQueryModel>
		implements ServicestaffinfoDAO {

	@Resource(name = ServicestaffCacheConstant.SERVICESTAFF_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private ServicestaffinfoDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public ServicestaffinfoCacheImpl() {
		super(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFFINFO_KEY);
	}

	@Override
	public String create(ServicestaffinfoModel m) {
		String ret = this.dao.create(m);
		return ret;
	}

	@Override
	public void delete(ServicestaffinfoModel m) {
		super.delete(m);
		super.deleteCache(m);
	}

	@Override
	public void update(ServicestaffinfoModel m) {

		dao.update(m);
		this.mcc.delete(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFFINFO_KEY
				+ m.getUuid());
		this.mcc.delete(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFFINFO_KEY
				+ m.getServiceStaffUuid());
	}

	@Override
	public String getServiceStaffinfoSex(String uuid) {
		Object obj = this.mcc
				.get(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFFINFO_KEY
						+ uuid);
		String sex = "";
		ServicestaffinfoModel servicestaffinfoModel = null;
		if (obj != null) {
			servicestaffinfoModel = (ServicestaffinfoModel) obj;
			sex = servicestaffinfoModel.getSex();
		} else {
			servicestaffinfoModel = dao
					.getServicestaffinfoModelByServicestaffUuid(uuid);

			if (servicestaffinfoModel != null) {
				sex = servicestaffinfoModel.getSex();
				this.mcc.set(
						ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFFINFO_KEY
								+ uuid, servicestaffinfoModel);
			}
		}
		return sex;
	}

	@Override
	public String getServiceStaffinfoCertCode(String uuid) {
		Object obj = this.mcc
				.get(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFFINFO_KEY
						+ uuid);
		String certCode = "";
		ServicestaffinfoModel servicestaffinfoModel = null;
		if (obj != null) {
			servicestaffinfoModel = (ServicestaffinfoModel) obj;
			certCode = servicestaffinfoModel.getCertCode();
		} else {
			servicestaffinfoModel = dao
					.getServicestaffinfoModelByServicestaffUuid(uuid);
			if (servicestaffinfoModel != null) {
				certCode = servicestaffinfoModel.getCertCode();
				this.mcc.set(
						ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFFINFO_KEY
								+ uuid, servicestaffinfoModel);
			}
		}
		return certCode;
	}

	@Override
	public String getServiceStaffinfoIndustry(String uuid) {
		Object obj = this.mcc
				.get(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFFINFO_KEY
						+ uuid);
		String industry = "";
		ServicestaffinfoModel servicestaffinfoModel = null;
		if (obj != null) {
			servicestaffinfoModel = (ServicestaffinfoModel) obj;
			industry = servicestaffinfoModel.getIndustry();
		} else {
			servicestaffinfoModel = dao
					.getServicestaffinfoModelByServicestaffUuid(uuid);
			if (servicestaffinfoModel != null) {
				industry = servicestaffinfoModel.getIndustry();

				this.mcc.set(
						ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFFINFO_KEY
								+ uuid, servicestaffinfoModel);
			}
		}
		return industry;
	}

	@Override
	public boolean checkServiceStaffinfoCertCode(String certCode) {
		return dao.checkServiceStaffinfoCertCode(certCode);
	}

	@Override
	public ServicestaffinfoModel getServicestaffinfoModelByServicestaffUuid(
			String servicestaffUuid) {
		Object obj = this.mcc
				.get(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFFINFO_KEY
						+ servicestaffUuid);
		ServicestaffinfoModel servicestaffinfoModel = null;
		if (obj != null) {
			servicestaffinfoModel = (ServicestaffinfoModel) obj;
		} else {
			servicestaffinfoModel = dao
					.getServicestaffinfoModelByServicestaffUuid(servicestaffUuid);
			if (servicestaffinfoModel == null) {
				servicestaffinfoModel = new ServicestaffinfoModel();
			}
			this.mcc.set(
					ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFFINFO_KEY
							+ servicestaffUuid, servicestaffinfoModel);
		}
		return servicestaffinfoModel;
	}

	@Override
	public String checkDoctorCategory(List<String> checkIds) {
		return dao.checkDoctorCategory(checkIds);
	}

	@Override
	public List<ServicestaffinfoModel> getServiceStaffinfoListByRealName(
			String realName) {
		// 返回对象集合
		List<ServicestaffinfoModel> famousDoctors = null;
		// 所以uuids
		List<String> uuids = dao
				.getServiceStaffinfoUuidListByRealName(realName);
		// 循环
		for (String uuid : uuids) {
			// Model对象
			ServicestaffinfoModel servicestaffinfoModel = new ServicestaffinfoModel();
			// 从缓存中取值
			Object objModel = this.mcc
					.get(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFFINFO_KEY
							+ uuid);
			if (objModel != null) {
				servicestaffinfoModel = (ServicestaffinfoModel) objModel;
			} else {
				// 获得医生model
				servicestaffinfoModel = dao
						.getServicestaffinfoModelByServicestaffUuid(uuid);
				if (servicestaffinfoModel != null) {
					// 将医生model放入缓存
					this.mcc.set(
							ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFFINFO_KEY
									+ uuid, servicestaffinfoModel);
				}
			}
			// 将model存入返回值
			famousDoctors.add(servicestaffinfoModel);
		}
		// 返回
		return famousDoctors;
	}

	@Override
	public String getTerritory(String doctorUuid) {
		ServicestaffinfoModel servicestaffinfoModel = new ServicestaffinfoModel();
		Object obj = this.mcc
				.get(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFFINFO_KEY
						+ doctorUuid);

		String territory = "";
		if (obj != null) {
			servicestaffinfoModel = (ServicestaffinfoModel) obj;
			territory = servicestaffinfoModel.getTerritory();
		} else {
			servicestaffinfoModel = dao
					.getServicestaffinfoModelByServicestaffUuid(doctorUuid);
			if (servicestaffinfoModel != null) {
				territory = servicestaffinfoModel.getTerritory();

				this.mcc.set(
						ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFFINFO_KEY
								+ doctorUuid, servicestaffinfoModel);
			}
		}
		return territory;
	}

	@Override
	public List<String> getServiceStaffinfoUuidListByRealName(String realName) {
		// uuids集合
		List<String> uuids = new ArrayList<String>();
		// 从缓存中取值
		Object obj = this.mcc
				.get(ServicestaffCacheConstant.SERVICESTAFF_INFOUUIDS_KEY);
		if (obj != null) {
			uuids = (List<String>) obj;
		} else {
			// 获得所有医生基本信息uuid
			uuids = dao.getServiceStaffinfoUuidListByRealName(realName);
			if (uuids != null && uuids.size() > 0) {
				// 放入缓存
				this.mcc.set(
						ServicestaffCacheConstant.SERVICESTAFF_INFOUUIDS_KEY
								+ realName, uuids);
			}
		}
		return uuids;
	}
}
