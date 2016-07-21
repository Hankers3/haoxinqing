package com.aebiz.b2b2c.servicestaff.hospitalinfo.dao;

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
import com.aebiz.b2b2c.servicestaff.hospitalinfo.vo.HospitalInfoModel;
import com.aebiz.b2b2c.servicestaff.hospitalinfo.vo.HospitalInfoQueryModel;
import com.danga.MemCached.MemCachedClient;

/**
 * 医生的缓存
 * 
 * @author szr
 * 
 */
@Primary
@Repository
public class HospitalInfoCacheImpl extends BaseMemcachedCache<HospitalInfoModel, HospitalInfoQueryModel>
		implements HospitalInfoDAO {

	@Resource(name = ServicestaffCacheConstant.SERVICESTAFF_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private HospitalInfoDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public HospitalInfoCacheImpl() {
		super(ServicestaffCacheConstant.SERVICESTAFF_HOSPITALINFO_KEY);
	}

	@Override
	public String create(HospitalInfoModel m) {
		String ret = this.dao.create(m);
		
		this.mcc.delete(ServicestaffCacheConstant.SERVICESTAFF_HOSPITALINFO_KEY + m.getProvince());
		this.mcc.delete(ServicestaffCacheConstant.SERVICESTAFF_HOSPITALINFO_KEY + m.getCity());
		this.mcc.delete(ServicestaffCacheConstant.SERVICESTAFF_HOSPITALINFO_KEY + m.getRegion());
		this.mcc.delete(ServicestaffCacheConstant.SERVICESTAFF_HOSPITALINFO_KEY + "list");

		return ret;
	}

	@Override
	public void delete(HospitalInfoModel m) {
		super.delete(m);
		super.deleteCache(m);
		this.mcc.delete(ServicestaffCacheConstant.SERVICESTAFF_HOSPITALINFO_KEY + m.getProvince());
		this.mcc.delete(ServicestaffCacheConstant.SERVICESTAFF_HOSPITALINFO_KEY + m.getCity());
		this.mcc.delete(ServicestaffCacheConstant.SERVICESTAFF_HOSPITALINFO_KEY + m.getRegion());
		this.mcc.delete(ServicestaffCacheConstant.SERVICESTAFF_HOSPITALINFO_KEY + "list");

	}

	@Override
	public void update(HospitalInfoModel m) {
		dao.update(m);
		this.mcc.delete(ServicestaffCacheConstant.SERVICESTAFF_HOSPITALINFO_KEY + m.getUuid());
		this.mcc.delete(ServicestaffCacheConstant.SERVICESTAFF_HOSPITALINFO_KEY + m.getProvince());
		this.mcc.delete(ServicestaffCacheConstant.SERVICESTAFF_HOSPITALINFO_KEY + m.getCity());
		this.mcc.delete(ServicestaffCacheConstant.SERVICESTAFF_HOSPITALINFO_KEY + m.getRegion());
		this.mcc.delete(ServicestaffCacheConstant.SERVICESTAFF_HOSPITALINFO_KEY + "list");


	}

	/* ——————————缓存—————————— */
	@Override
	public String getHospitalNameByUuid(String uuid) {
		Object obj = this.mcc.get(ServicestaffCacheConstant.SERVICESTAFF_HOSPITALINFO_KEY + uuid);
		HospitalInfoModel hospitalInfoModel = null;
		String hospitalName = "";
		if (obj != null) {
			hospitalInfoModel = (HospitalInfoModel) obj;
			hospitalName = hospitalInfoModel.getHospitalName();
		} else {
			hospitalInfoModel = dao.getByUuid(uuid);
			if (hospitalInfoModel != null) {
				hospitalName = hospitalInfoModel.getHospitalName();
				this.mcc.set(ServicestaffCacheConstant.SERVICESTAFF_HOSPITALINFO_KEY + uuid, hospitalInfoModel);
			}else{
				hospitalInfoModel = new HospitalInfoModel();
				this.mcc.set(ServicestaffCacheConstant.SERVICESTAFF_HOSPITALINFO_KEY + uuid, hospitalInfoModel);
			}

		}
		return hospitalName;
	}

	@Override
	public List<HospitalInfoModel> getHospitals() {
		// 返回对象集合
		List<HospitalInfoModel> models = new ArrayList<HospitalInfoModel>();
		// 所以uuids
		List<String> uuids = this.getAllUuids();
		// 循环
		for (String uuid : uuids) {
			// Model对象
			HospitalInfoModel hospitalInfoModel = new HospitalInfoModel();
			// 从缓存中取值
			Object objModel = this.mcc.get(ServicestaffCacheConstant.SERVICESTAFF_HOSPITALINFO_KEY + uuid);
			if (objModel != null) {
				hospitalInfoModel = (HospitalInfoModel) objModel;
			} else {
				// 获得医生model
				hospitalInfoModel = dao.getByUuid(uuid);
				if (hospitalInfoModel != null) {
					// 将医生model放入缓存
					this.mcc.set(ServicestaffCacheConstant.SERVICESTAFF_HOSPITALINFO_KEY + uuid, hospitalInfoModel);
				}
			}
			// 将model存入返回值
			models.add(hospitalInfoModel);
		}
		// 返回
		return models;
	}

	@Override
	public String getUuidByHospitalName(String hospitalName) {
		Object obj = this.mcc.get(ServicestaffCacheConstant.SERVICESTAFF_HOSPITALUUID_KEY + hospitalName);
		String uuid = "";
		if (obj != null) {
			uuid = (String) obj;
		} else {
			uuid = dao.getHospitalNameByUuid(hospitalName);
			this.mcc.set(ServicestaffCacheConstant.SERVICESTAFF_HOSPITALUUID_KEY + hospitalName, uuid);
		}
		return uuid;
	}

	@Override
	public List<String> getAllUuids() {
		// uuids集合
		List<String> uuids = dao.getAllUuids();

		return uuids;
	}

	@Override
	public List<HospitalInfoModel> getByRegionUuid(String regionUuid) {
		return dao.getByRegionUuid(regionUuid);
	}

	@Override
	public List<String> getAllUuidsByHospitalName(String hospitalName) {
		return dao.getAllUuidsByHospitalName(hospitalName);
	}

	@Override
	public List<HospitalInfoModel> getByCityUuid(String regionUuid) {
		return dao.getByCityUuid(regionUuid);
	}
	
	/**
	 * 查询医院信息
	 * @param provinceUuid
	 * @param cityUuid
	 * @param regionUuid
	 * @return
	 */
	@Override
	public List<HospitalInfoModel> getByProvinceUuid(String provinceUuid,String cityUuid,String regionUuid){
		List<HospitalInfoModel> list =null;
		Object obj =null;
		if(!StringUtil.isEmpty(provinceUuid)){
			obj = this.mcc.get(ServicestaffCacheConstant.SERVICESTAFF_HOSPITALINFO_KEY + provinceUuid);
			if(obj !=null){
				list = (List<HospitalInfoModel>) obj;
			}
		}
		
		if(!StringUtil.isEmpty(cityUuid)){
			obj = this.mcc.get(ServicestaffCacheConstant.SERVICESTAFF_HOSPITALINFO_KEY + cityUuid);
			if(obj !=null){
				list = (List<HospitalInfoModel>) obj;
			}
		}
		if(!StringUtil.isEmpty(regionUuid)){
			obj = this.mcc.get(ServicestaffCacheConstant.SERVICESTAFF_HOSPITALINFO_KEY + regionUuid);
			if(obj !=null){
				list = (List<HospitalInfoModel>) obj;
			}
		}
		
		if(StringUtil.isEmpty(provinceUuid)&&StringUtil.isEmpty(cityUuid)&& StringUtil.isEmpty(regionUuid)){
			obj = this.mcc.get(ServicestaffCacheConstant.SERVICESTAFF_HOSPITALINFO_KEY + "list");
			if(obj !=null){
				list = (List<HospitalInfoModel>) obj;
			}
		}
		
		if(obj ==null){
			list = dao.getByProvinceUuid(provinceUuid, cityUuid, regionUuid);
			if(list !=null && list.size()>0){
				if(!StringUtil.isEmpty(provinceUuid)){
					this.mcc.set(ServicestaffCacheConstant.SERVICESTAFF_HOSPITALINFO_KEY + provinceUuid, list);
				}
				if(!StringUtil.isEmpty(provinceUuid)&&!StringUtil.isEmpty(cityUuid)){
					this.mcc.set(ServicestaffCacheConstant.SERVICESTAFF_HOSPITALINFO_KEY + cityUuid, list);
				}
				if(!StringUtil.isEmpty(cityUuid)&& !StringUtil.isEmpty(regionUuid)){
					this.mcc.set(ServicestaffCacheConstant.SERVICESTAFF_HOSPITALINFO_KEY + regionUuid, list);
				}
				if(StringUtil.isEmpty(provinceUuid)&&StringUtil.isEmpty(cityUuid)&& StringUtil.isEmpty(regionUuid)){
					this.mcc.set(ServicestaffCacheConstant.SERVICESTAFF_HOSPITALINFO_KEY + "list", list);
				}
			}
		}
		
		return list;
	}
}
