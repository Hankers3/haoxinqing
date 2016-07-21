package com.aebiz.b2b2c.visitprecept.visitapply.dao;

import java.util.List;

import com.wxcommon.util.ObjectUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.visitprecept.visitapply.vo.VisitApplyModel;
import com.aebiz.b2b2c.visitprecept.visitapply.vo.VisitApplyQueryModel;

@Repository
public class VisitApplyH4Impl extends
		BaseH4Impl<VisitApplyModel, VisitApplyQueryModel> implements
		VisitApplyDAO {

	/**
	 * 关联表查询
	 */
	@Override
	protected String getMultiModel() {

		return ", ServicestaffModel as s, CustomerInfoModel as cim, CustomerModel as cm ";
	}

	/* 添加sql语句 */
	@Override
	protected String getAppendHql(VisitApplyQueryModel qm) {
		StringBuffer hql = new StringBuffer("");

		hql.append(" and o.serviceStaffUuid = s.uuid and o.customerUuid = cm.uuid and cim.customerUuid = cm.uuid ");

		if (!StringUtil.isEmpty(qm.getCustomerMoblie())) {
			hql.append(" and cm.mobile like:customerMoblie ");
		}

		if (!StringUtil.isEmpty(qm.getCustomerqName())) {
			hql.append(" and cim.realName like:customerName ");
		}

		if (!StringUtil.isEmpty(qm.getDoctorMoblie())) {
			hql.append(" and s.mobile like:doctorMoblie ");
		}
		
		if (!StringUtil.isEmpty(qm.getStartTime())) {
			hql.append(" and o.createTime >=:startTime ");
		}
		
		if (!StringUtil.isEmpty(qm.getEndTime())) {
			hql.append(" and o.createTime <:endTime ");
		}
		return hql.toString();
	}

	/* 为sql语句赋值 */
	@Override
	protected void setAppendHqlValue(VisitApplyQueryModel qm, Query q) {
		if (!StringUtil.isEmpty(qm.getCustomerMoblie())) {
			q.setString("customerMoblie", "%" + qm.getCustomerMoblie() + "%");
		}

		if (!StringUtil.isEmpty(qm.getCustomerqName())) {
			q.setString("customerName", "%" + qm.getCustomerqName() + "%");
		}

		if (!StringUtil.isEmpty(qm.getDoctorMoblie())) {
			q.setString("doctorMoblie", "%" + qm.getDoctorMoblie() + "%");
		}

		if (!StringUtil.isEmpty(qm.getDoctorqName())) {
			q.setString("doctorName", "%" + qm.getDoctorqName() + "%");
		}
		
		if (!StringUtil.isEmpty(qm.getStartTime())) {
			q.setString("startTime",qm.getStartTime() );
		}
		
		if (!StringUtil.isEmpty(qm.getEndTime())) {
			q.setString("endTime",qm.getEndTime() );
		}

	}
	
	
	
	/**
	 * 
	 * 根据医生ID查询随访申请的List集合
	 */
	@Override
	public List<VisitApplyModel> getAllListByDoctorId(String doctorid) {
		 StringBuffer hql = new StringBuffer(
			       " from VisitApplyModel o where o.serviceStaffUuid =:doctorid");
			     Query q = getH4Session().createQuery(hql.toString());
			     q.setParameter("doctorid", doctorid);
			     List list = q.list();
			     if ((list != null) && (list.size() > 0))
			     {
			       return list;
			     }
			     return null;
		
	}
	
	
	/**
	 * 获取患者编号
	 * @param preceptUuid
	 * @return
	 */
	public List<String> getCustomerIds(String preceptUuid){
		
		StringBuffer hql = new StringBuffer(" select o.customerUuid  from VisitApplyModel o where o.visitPreceptUuid =:visitPreceptUuid");
		
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("visitPreceptUuid", preceptUuid);
		
		List<String> list = q.list();
		if(list !=null && list.size()>0 ){
			return list;
		}
		
		return null;
	}

	/**
	 * 根据随访方案获取随访人数
	 */
	@Override
	public int getCustomerNumByPreceptUuid(String doctorUuid,String preceptUuid) {
		StringBuffer hql = new StringBuffer(
				"select count(distinct o.customerUuid)from VisitApplyModel o where  o.visitPreceptUuid =:visitPreceptUuid and o.serviceStaffUuid =:serviceStaffUuid and o.applyState = '1' ");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("visitPreceptUuid", preceptUuid);
		query.setString("serviceStaffUuid", doctorUuid);
		return ((Number) query.uniqueResult()).intValue();
	}

	@Override
	public List<VisitApplyModel> getByPreceptUuid(String doctorUuid, String preceptUuid) {
		StringBuilder hql = new StringBuilder("from VisitApplyModel o where  o.visitPreceptUuid =:visitPreceptUuid and o.serviceStaffUuid =:serviceStaffUuid and o.applyState = '1' ");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("visitPreceptUuid", preceptUuid);
		query.setString("serviceStaffUuid", doctorUuid);
		List<VisitApplyModel> list = query.list();
		if (ObjectUtils.isEmpty(list)) {
			return null;
		} else {
			return list;
		}

	}
}
