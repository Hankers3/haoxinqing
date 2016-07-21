package com.aebiz.b2b2c.servicestaff.packagedoctor.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.servicestaff.doctorright.vo.DoctorRightModel;
import com.aebiz.b2b2c.servicestaff.packagedoctor.vo.PackageDoctorModel;
import com.aebiz.b2b2c.servicestaff.packagedoctor.vo.PackageDoctorQueryModel;

@Repository
public class PackageDoctorH4Impl extends
		BaseH4Impl<PackageDoctorModel, PackageDoctorQueryModel> implements
		PackageDoctorDAO {
	/**
	 * 通过医生id得到对象
	 * 
	 * @param doctorid
	 * @return
	 */
	@Override
	public PackageDoctorModel getByDoctorUuid(String doctorid) {
		StringBuffer hql = new StringBuffer(" from PackageDoctorModel  as o ");
		hql.append(" where o.doctorUuid =:doctorUuid ");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("doctorUuid", doctorid);

		List<PackageDoctorModel> list = q.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 通过医生id和私人医生id得到对象
	 * 
	 * @param doctorid
	 * @param packageUuid
	 * @return
	 */
	@Override
	public PackageDoctorModel getByDoctorUuidAndPackageUuid(String doctorid,
			String packageUuid) {
		StringBuffer hql = new StringBuffer(" from PackageDoctorModel  as o ");
		hql.append(" where o.doctorUuid =:doctorUuid ");
		hql.append(" and o.packageUuid =:packageUuid ");
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("doctorUuid", doctorid);
		q.setString("packageUuid", packageUuid);
		List<PackageDoctorModel> list = q.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	/**
	 * 
	 * @Description: (根据医生的id查询所有医生开通套餐的uuid)    
	 * @author XP  
	 * @param doctorUuid
	 * @return
	 * @date 2016-1-18 下午1:31:08
	 */
        @Override
        public List<String> getPackageUuidsByDoctorUuid(String doctorUuid) {
            StringBuffer hql = new StringBuffer(" select o.packageUuid from PackageDoctorModel  as o ");
            hql.append(" where o.doctorUuid =:doctorUuid ");
            Query q = this.getH4Session().createQuery(hql.toString());
            q.setString("doctorUuid", doctorUuid);
            List<String> list = q.list();
            if (list != null && list.size() > 0) {
                    return list;
            }
            return null;
        }

}
