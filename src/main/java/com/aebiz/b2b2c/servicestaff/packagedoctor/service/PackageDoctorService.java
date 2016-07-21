package com.aebiz.b2b2c.servicestaff.packagedoctor.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.servicestaff.packagedoctor.vo.PackageDoctorModel;
import com.aebiz.b2b2c.servicestaff.packagedoctor.vo.PackageDoctorQueryModel;

public interface PackageDoctorService extends
		BaseService<PackageDoctorModel, PackageDoctorQueryModel> {

	/**
	 * 通过医生id得到对象
	 * 
	 * @param doctorid
	 * @return
	 */
	public PackageDoctorModel getByDoctorUuid(String doctorid);

	/**
	 * 通过医生id和私人医生id得到对象
	 * 
	 * @param doctorid
	 * @param packageUuid
	 * @return
	 */
	public PackageDoctorModel getByDoctorUuidAndPackageUuid(String doctorid,
			String packageUuid);
	/**
	 * 
	 * @Description: (根据医生的id查询医生开通的私人套餐的id)    
	 * @author XP  
	 * @param doctorUuid
	 * @return
	 * @date 2016-1-18 下午1:28:15
	 */
	public List<String> getPackageUuidsByDoctorUuid(String doctorUuid);

}
