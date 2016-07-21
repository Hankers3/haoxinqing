package com.aebiz.b2b2c.servicestaff.hospitalinfo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

import com.aebiz.b2b2c.servicestaff.hospitalinfo.vo.HospitalInfoModel;
import com.aebiz.b2b2c.servicestaff.hospitalinfo.vo.HospitalInfoQueryModel;

public interface HospitalInfoService extends BaseService<HospitalInfoModel,HospitalInfoQueryModel>{
	/**
	 * 获取医院名通过编号
	 * @param uuid 
	 * @return
	 */
	public String getHospitalNameByUuid(String uuid);
	/**
	 * 获取编号通过医院名
	 * @param uuid 
	 * @return
	 */
	public String getUuidByHospitalName(String hospitalName);
	
	/**
	 * 获取所有医院信息
	 * @return
	 */
	public List<HospitalInfoModel> getHospitals();
	/**
	 * 从表格中导入医院,并创建数据到表
	 */
	public boolean importHospital(MultipartFile file);

	/**
	 * 通过区id得到医院
	 * 
	 * @param regionUuid
	 * @return
	 */
	public List<HospitalInfoModel> getByRegionUuid(String regionUuid);
	/**
	 * 
	 * @Description: (根据医院的名称获取医院的uuids)    
	 * @author XP  
	 * @param hospitalName
	 * @return
	 * @date 2015-12-31 下午6:37:38
	 */
	public List<String> getAllUuidsByHospitalName(String hospitalName);

	public List<HospitalInfoModel> getByCityUuid(String regionUuid);
	
	/**
	 * 查询医院信息
	 * @param provinceUuid
	 * @param cityUuid
	 * @param regionUuid
	 * @return
	 */
	public List<HospitalInfoModel> getByProvinceUuid(String provinceUuid,String cityUuid,String regionUuid);
}
