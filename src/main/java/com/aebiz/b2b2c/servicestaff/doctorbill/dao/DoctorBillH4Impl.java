package com.aebiz.b2b2c.servicestaff.doctorbill.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.servicestaff.doctorbill.vo.DoctorBillModel;
import com.aebiz.b2b2c.servicestaff.doctorbill.vo.DoctorBillQueryModel;

@Repository
public class DoctorBillH4Impl extends BaseH4Impl<DoctorBillModel,DoctorBillQueryModel> implements DoctorBillDAO {
	
	@Override
	protected String getMultiModel() {
		return " ,ServicestaffModel as s ";
	}
	
	@Override
	protected String getAppendHql(DoctorBillQueryModel qm) {
		StringBuffer hql = new StringBuffer(" and o.doctorUuid = s.uuid ");
		//医生名
		if(!StringUtil.isEmpty(qm.getDoctorName())){
			hql.append(" and s.serviceStaffName like:doctorName ");
		}
		//时间
		if(!StringUtil.isEmpty(qm.getStartTime())&&!StringUtil.isEmpty(qm.getEndTime()) ){
			hql.append(" and o.createTime >=:startTime and o.createTime <=:endTime ");
		}
		if(!StringUtil.isEmpty(qm.getDealType())&&DoctorBillModel.DOCTOR_DEAL_DEPOSIT.equals(qm.getDealType())){
			hql.append(" and o.serviceStaffName like:doctorName ");
		}else if(!StringUtil.isEmpty(qm.getDealType())&&!DoctorBillModel.DOCTOR_DEAL_DEPOSIT.equals(qm.getDealType())){
			hql.append(" and o.serviceStaffName like:doctorName ");
		}
		
		return hql.toString();
	}
	
	@Override
	protected void setAppendHqlValue(DoctorBillQueryModel qm, Query q) {
		//医生名
		if(!StringUtil.isEmpty(qm.getDoctorName())){
			q.setString("doctorName", "%"+qm.getDoctorName()+"%");
		}
		//时间
		if(!StringUtil.isEmpty(qm.getStartTime())&&!StringUtil.isEmpty(qm.getEndTime())){
			q.setString("startTime", qm.getStartTime());
			q.setString("endTime",qm.getEndTime() );
		}
	}
	
}
