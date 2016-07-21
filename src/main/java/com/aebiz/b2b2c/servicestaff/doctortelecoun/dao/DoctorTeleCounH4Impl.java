package com.aebiz.b2b2c.servicestaff.doctortelecoun.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.servicestaff.doctortelecoun.vo.DoctorTeleCounModel;
import com.aebiz.b2b2c.servicestaff.doctortelecoun.vo.DoctorTeleCounQueryModel;

@Repository
public class DoctorTeleCounH4Impl extends BaseH4Impl<DoctorTeleCounModel,DoctorTeleCounQueryModel> implements DoctorTeleCounDAO {
	
	/**
	 * 关联医生信息表查询 
	 */
	@Override
	protected String getMultiModel() {
		
		return " , ServicestaffModel as sm   ";
	}
	
	/**
	 * 拼接hql 语句
	 */
	@Override
	protected String getAppendHql(DoctorTeleCounQueryModel qm) {
		StringBuffer hql = new StringBuffer(" and o.doctorUuid = sm.uuid ");
		
		if(!StringUtil.isEmpty(qm.getDoctorName())){
			hql.append(" and sm.serviceStaffName like:doctorName ");
		}
		
		return hql.toString();
	}
	
	/**
	 * 参数设置
	 */
	@Override
	protected void setAppendHqlValue(DoctorTeleCounQueryModel qm, Query q) {
		if(!StringUtil.isEmpty(qm.getDoctorName())){
			q.setString("doctorName", "%"+qm.getDoctorName()+"%");
		}
	}
	
	
}
