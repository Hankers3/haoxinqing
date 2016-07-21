package com.aebiz.b2b2c.servicestaff.doctorright.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.servicestaff.doctorright.vo.DoctorRightModel;
import com.aebiz.b2b2c.servicestaff.doctorright.vo.DoctorRightQueryModel;

@Repository
public class DoctorRightH4Impl extends BaseH4Impl<DoctorRightModel,DoctorRightQueryModel> implements DoctorRightDAO {

	
	
	
	/**
	 * 获取医生的权限设置信息
	 * @param DoctorUuid
	 * @return
	 */
	 public DoctorRightModel getByDoctorUuid(String doctorUuid){
			StringBuffer hql = new StringBuffer(" from DoctorRightModel  as o ");
			hql.append(" where o.doctorUuid =:doctorUuid ");
			
			Query q = this.getH4Session().createQuery(hql.toString());
			q.setString("doctorUuid", doctorUuid);
			
			List<DoctorRightModel> list =q.list();
			if(list !=null && list.size()>0){
				return list.get(0);
			}
			return null;
	 }
	 
	/**
	 * 获取医生是否开启审核
	 * @param doctorUuid
	 * @return
	 */
	@Override
	public String getExamByDoctorUuid(String doctorUuid) {
		StringBuffer hql = new StringBuffer(" select o.exam from DoctorRightModel  as o ");
		hql.append(" where o.doctorUuid =:doctorUuid ");
		
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("doctorUuid", doctorUuid);
		
		List<String> list =q.list();
		if(list !=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

      
		
}
