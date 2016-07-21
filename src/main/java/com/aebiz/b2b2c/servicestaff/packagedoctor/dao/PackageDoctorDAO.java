package com.aebiz.b2b2c.servicestaff.packagedoctor.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.servicestaff.packagedoctor.vo.PackageDoctorModel;
import com.aebiz.b2b2c.servicestaff.packagedoctor.vo.PackageDoctorQueryModel;

public interface PackageDoctorDAO extends
		BaseDAO<PackageDoctorModel, PackageDoctorQueryModel> {
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
	 * @Description: (根据医生的id查询医生所有的私人套餐的id)    
	 * @author XP  
	 * @param doctorUuid
	 * @return
	 * @date 2016-1-18 下午1:29:35
	 */
	public List<String> getPackageUuidsByDoctorUuid(String doctorUuid);

}