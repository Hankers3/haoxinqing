package com.aebiz.b2b2c.servicestaff.packagemanagement.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.servicestaff.packagemanagement.vo.PackageManagementModel;
import com.aebiz.b2b2c.servicestaff.packagemanagement.vo.PackageManagementQueryModel;

public interface PackageManagementService extends
		BaseService<PackageManagementModel, PackageManagementQueryModel> {
	/**
	 * 根据传入的套餐信息名判断套餐名是否已经存在
	 * 
	 * @param packageName
	 *            套餐名
	 * @return
	 */
	public boolean checkPackageName(String packageName);
	
	/**
	 * 通过编号 获取套餐名
	 * @param uuid
	 * @return
	 */
	public String getPackageName(String uuid);
	/**
	 * 
	 * @Description: (根据医生id查询医生开通的私人套餐)    
	 * @author XP  
	 * @param uuids
	 * @return
	 * @date 2016-1-18 下午1:02:20
	 */
	public List<PackageManagementModel> getPackageListByDoctorUuid(List<String> uuids);

}
