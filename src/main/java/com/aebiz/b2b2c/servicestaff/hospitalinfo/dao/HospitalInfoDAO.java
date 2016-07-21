package com.aebiz.b2b2c.servicestaff.hospitalinfo.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.servicestaff.hospitalinfo.vo.HospitalInfoModel;
import com.aebiz.b2b2c.servicestaff.hospitalinfo.vo.HospitalInfoQueryModel;

public interface HospitalInfoDAO extends BaseDAO<HospitalInfoModel, HospitalInfoQueryModel> {
	/**
	 * 获取通过编号医院名
	 * 
	 * @param uuid
	 * @return
	 */
	public String getHospitalNameByUuid(String uuid);

	/**
	 * 获取所有医院信息
	 * 
	 * @return
	 */
	public List<HospitalInfoModel> getHospitals();

	/**
	 * 获取编号通过医院名
	 * 
	 * @param uuid
	 * @return
	 */
	public String getUuidByHospitalName(String hospitalName);

	/**
	 * 获取所有uuid
	 * 
	 * @param uuid
	 * @return
	 */
	public List<String> getAllUuids();

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