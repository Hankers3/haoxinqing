package com.aebiz.b2b2c.servicestaff.departmentinfo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.servicestaff.common.ServicestaffCacheConstant;
import com.aebiz.b2b2c.servicestaff.departmentinfo.vo.DepartmentInfoModel;
import com.aebiz.b2b2c.servicestaff.departmentinfo.vo.DepartmentInfoQueryModel;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;
import com.danga.MemCached.MemCachedClient;

/**
 * 医生的缓存
 * 
 * @author szr
 * 
 */
@Primary
@Repository
public class DepartmentInfoCacheImpl extends
		BaseMemcachedCache<DepartmentInfoModel, DepartmentInfoQueryModel>
		implements DepartmentInfoDAO {

	@Resource(name = ServicestaffCacheConstant.SERVICESTAFF_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private DepartmentInfoDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public DepartmentInfoCacheImpl() {
		super(ServicestaffCacheConstant.SERVICESTAFF_DEPARTINFO_KEY);
	}

	@Override
	public String create(DepartmentInfoModel m) {
		String ret = this.dao.create(m);
		return ret;
	}

	@Override
	public void delete(DepartmentInfoModel m) {
		super.delete(m);
		super.deleteCache(m);
	}

	/* ——————————缓存—————————— */
	@Override
	public boolean checkDepartmentName(String departmentName, String uuid) {
		return dao.checkDepartmentName(departmentName, uuid);
	}

	@Override
	public String getDepartmentNameByUuid(String uuid) {
		Object obj = this.mcc.get(ServicestaffCacheConstant.SERVICESTAFF_DEPARTMENTNAME_KEY
						+ uuid);
		String departmentName = "";
		if (obj != null) {
			departmentName = (String) obj;
		} else {
			departmentName = dao.getDepartmentNameByUuid(uuid);
			if (!StringUtil.isEmpty(departmentName)) {
				this.mcc.set(
						ServicestaffCacheConstant.SERVICESTAFF_DEPARTMENTNAME_KEY
								+ uuid, departmentName);
			}
		}
		return departmentName;
	}

	@Override
	public String getUuidByDepartmentName(String departmentName) {
		Object obj = this.mcc
				.get(ServicestaffCacheConstant.SERVICESTAFF_HOSPITALUUID_KEY
						+ departmentName);
		String uuid = "";
		if (obj != null) {
			uuid = (String) obj;
		} else {
			uuid = dao.getUuidByDepartmentName(departmentName);
			this.mcc.set(
					ServicestaffCacheConstant.SERVICESTAFF_HOSPITALUUID_KEY
							+ departmentName, uuid);
		}
		return uuid;
	}

	@Override
	public List<Object> updloadExcel(MultipartFile[] files) {
		return dao.updloadExcel(files);
	}

	@Override
	public List<String> getAllUuids() {
		return dao.getAllUuids();
	}
	/**
	 * 获取所有对象元素
	 */
	@Override
	public List<DepartmentInfoModel> getAll() {
		List<String> uuids  = this.getAllUuids();
		List<DepartmentInfoModel> listRt = new ArrayList<DepartmentInfoModel>();
		if(uuids !=null &&uuids.size()>0){
			for(String uuid :uuids){
				DepartmentInfoModel departmentInfoModel = new DepartmentInfoModel();
				Object obj = this.mcc
						.get(ServicestaffCacheConstant.SERVICESTAFF_HOSPITALUUID_KEY
								+ uuid);
				if(obj !=null){
					departmentInfoModel = (DepartmentInfoModel) obj;
					listRt.add(departmentInfoModel);
				}else{
					//缓存没有的从数据库取值
					departmentInfoModel = dao.getByUuid(uuid);
					if(departmentInfoModel !=null){
						listRt.add(departmentInfoModel);
						this.mcc.set(ServicestaffCacheConstant.SERVICESTAFF_HOSPITALUUID_KEY
								+ uuid, departmentInfoModel);
					}
				}
			}
		}
		
		return listRt;
	}
}
