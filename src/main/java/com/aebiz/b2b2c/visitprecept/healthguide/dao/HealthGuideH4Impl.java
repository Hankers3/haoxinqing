package com.aebiz.b2b2c.visitprecept.healthguide.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;
import com.aebiz.b2b2c.visitprecept.doctoradvice.vo.DoctorAdviceModel;
import com.aebiz.b2b2c.visitprecept.healthguide.vo.HealthGuideModel;
import com.aebiz.b2b2c.visitprecept.healthguide.vo.HealthGuideQueryModel;

@Repository
public class HealthGuideH4Impl extends
		BaseH4Impl<HealthGuideModel, HealthGuideQueryModel> implements
		HealthGuideDAO {
	/**
	 * 要关联的Model，，
	 */
	@Override
	protected String getMultiModel() {
		return ",ServicestaffModel as s ,CustomerModel as c  ";
	}

	/**
	 * 关联Model的条件，以及查询条件
	 */
	@Override
	protected String getAppendHql(HealthGuideQueryModel qm) {
		StringBuffer hql = new StringBuffer(
				" and o.serviceStaffUuid =s.uuid and o.customerUuid = c.uuid");
		if (!StringUtil.isEmpty(qm.getDoctorName())) {
			hql.append(" and s.serviceStaffName like :serviceStaffName");
		}
		if (!StringUtil.isEmpty(qm.getCustomerName())) {
			hql.append(" and c.customerName like :customerName");
		}
		return hql.toString();
	}

	/**
	 * 赋值
	 */
	@Override
	protected void setAppendHqlValue(HealthGuideQueryModel qm, Query q) {
		if (!StringUtil.isEmpty(qm.getDoctorName())) {
			q.setString("serviceStaffName", "%" + qm.getDoctorName() + "%");
		}
		if (!StringUtil.isEmpty(qm.getCustomerName())) {
			q.setString("customerName", "%" + qm.getCustomerName() + "%");
		}
	}

	 /**
         * 获取所有给这个患者医嘱的医生的Uuid
         * @param customerUuid
         * @return
         */
        @Override
        public List<String> getAllDoctorUuidByCustomerUuid(String customerUuid) {
            StringBuffer hql = new StringBuffer("select distinct(o.serviceStaffUuid) from HealthGuideModel as o where o.customerUuid =:customerUuid order by o.createTime DESC ");
            
            Query query = getH4Session().createQuery(hql.toString());
            query.setString("customerUuid", customerUuid);
            List list = query.list();
            if(list != null && list.size()>0)
                return list;
            else
                return null;
        }
        /**
         * 根据医生和患者的id获取健康指导的对象
         * @param customerUuid
         * @param doctorId
         * @return
         */
        @Override
        public HealthGuideModel getHealthGuideIdAndCustomerUuid(String customerUuid, String doctorId) {
            StringBuffer hql = new StringBuffer(" from HealthGuideModel as o where o.customerUuid =:customerUuid and o.serviceStaffUuid=:doctorId order by o.createTime DESC");
            Query query = getH4Session().createQuery(hql.toString());
            query.setString("customerUuid", customerUuid);
            query.setString("doctorId", doctorId);
            List list = query.list();
            if(list != null && list.size()>0)
                return (HealthGuideModel)list.get(0);
            else
                return null;
        }
        /**
         * 根据医生和患者获取健康指导的列表
         * @param doctorUuid
         * @param customerUuid
         * @return
         */
        @Override
        public List<HealthGuideModel> getHealthGuideListByCustomerUuidAndDoctorUuid(String doctorUuid,
                String customerUuid) {
            StringBuffer hql = new StringBuffer(" from HealthGuideModel as o where o.customerUuid =:customerUuid and o.serviceStaffUuid=:doctorId order by o.createTime DESC");
            Query query = getH4Session().createQuery(hql.toString());
            query.setString("customerUuid", customerUuid);
            query.setString("doctorId", doctorUuid);
            List list = query.list();
            if(list != null && list.size()>0)
                return list;
            else
                return null;
        }
        /**
         * 
         * @Description: (根据医生id和随访记录的id获取医生健康指导)    
         * @author XP  
         * @param doctorUuid
         * @param visitRecordUuid
         * @return
         * @date 2016-1-22 下午2:04:00
         */
        @Override
        public HealthGuideModel getHealthGuideByDoctorUuidAndVisitRecordUuid(String doctorUuid,
                String visitRecordUuid) {
            StringBuffer hql = new StringBuffer(" from HealthGuideModel as o where o.serviceStaffUuid =:doctorUuid and o.notevisitRecordUuid=:visitRecordUuid order by o.createTime DESC");
            Query query = getH4Session().createQuery(hql.toString());
            query.setString("visitRecordUuid", visitRecordUuid);
            query.setString("doctorUuid", doctorUuid);
            List<HealthGuideModel> list = query.list();
            if(list != null && list.size()>0)
                return list.get(0);
            else
                return null;
        }

    @Override
    public List<HealthGuideModel> getHealthGuideByDoctorUuidAndVisitUuid(String doctorUuid,String visitRecordUuid) {
        StringBuffer hql = new StringBuffer(" from HealthGuideModel as o where o.serviceStaffUuid =:doctorUuid and o.notevisitRecordUuid=:visitRecordUuid order by o.createTime DESC");
        Query query = getH4Session().createQuery(hql.toString());
        query.setString("visitRecordUuid", visitRecordUuid);
        query.setString("doctorUuid", doctorUuid);
        List<HealthGuideModel> list = query.list();
        if(list != null && list.size()>0)
            return list;
        else
            return null;
    }
        /**
         * 根据医生和患者获取健康指导的列表
         * @param doctorUuid
         * @param customerUuid
         * @return
         */
		@Override
		public List<HealthGuideModel> getHealthGuideListByVisitRecordUuid(
				String visitRecordUuid) {
				StringBuffer hql = new StringBuffer(" from HealthGuideModel as o where  o.notevisitRecordUuid=:visitRecordUuid order by o.createTime DESC");
	            Query query = getH4Session().createQuery(hql.toString());
	            query.setString("visitRecordUuid", visitRecordUuid);
	            List<HealthGuideModel> list = query.list();
	            if(list != null && list.size()>0)
	                return list;
	            else
	                return null;
		}

}
