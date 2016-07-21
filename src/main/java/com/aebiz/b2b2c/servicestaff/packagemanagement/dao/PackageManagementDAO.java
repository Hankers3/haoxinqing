package com.aebiz.b2b2c.servicestaff.packagemanagement.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.servicestaff.packagemanagement.vo.PackageManagementModel;
import com.aebiz.b2b2c.servicestaff.packagemanagement.vo.PackageManagementQueryModel;

public interface PackageManagementDAO extends
		BaseDAO<PackageManagementModel, PackageManagementQueryModel> {

	/**
	 * 根据传入的套餐信息名判断套餐名是否已经存在
	 * 
	 * @param packageName
	 *            套餐名
	 * @return
	 */
	public  String checkPackageName(String packageName);
	/**
	 * 通过编号 获取套餐名
	 * @param uuid
	 * @return
	 */
	public String getPackageName(String uuid);
	/**
	 * 
	 * @Description: (根据医生的id获取私人医生套餐的列表)    
	 * @author XP  
	 * @param uuids
	 * @return
	 * @date 2016-1-18 下午1:04:18
	 */
	public List<PackageManagementModel> getPackageListByDoctorUuid(List<String> uuids);

}