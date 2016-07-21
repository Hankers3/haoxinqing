package com.aebiz.b2b2c.visitprecept.healthguide.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.visitprecept.healthguide.vo.HealthGuideModel;
import com.aebiz.b2b2c.visitprecept.healthguide.vo.HealthGuideQueryModel;

public interface HealthGuideDAO extends BaseDAO<HealthGuideModel,HealthGuideQueryModel>{
    /**
     * 获取所有给这个患者医嘱的医生的Uuid
     * @param customerUuid
     * @return
     */
   public List<String> getAllDoctorUuidByCustomerUuid(String customerUuid);

   /**
    * 根据医生和患者的id获取健康指导的对象
    * @param customerUuid
    * @param doctorId
    * @return
    */
   public HealthGuideModel getHealthGuideIdAndCustomerUuid(String customerUuid, String doctorId);
   /**
    * 根据医生和患者获取健康指导的列表
    * @param doctorUuid
    * @param customerUuid
    * @return
    */
   public List<HealthGuideModel> getHealthGuideListByCustomerUuidAndDoctorUuid(String doctorUuid,
        String customerUuid);
   /**
    * 
    * @Description: (根据医生id和随访记录的id获取医生健康指导)    
    * @author XP  
    * @param doctorUuid
    * @param visitRecordUuid
    * @return
    * @date 2016-1-22 下午2:04:00
    */
   public HealthGuideModel getHealthGuideByDoctorUuidAndVisitRecordUuid(String doctorUuid, String visitRecordUuid);

   List<HealthGuideModel> getHealthGuideByDoctorUuidAndVisitUuid(String doctorUuid, String visitRecordUuid);
   /**
    * 根据医生和患者获取健康指导的列表
    * @param doctorUuid
    * @param customerUuid
    * @return
    */
   public List<HealthGuideModel> getHealthGuideListByVisitRecordUuid(String visitRecordUuid);
}