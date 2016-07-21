package com.aebiz.b2b2c.servicestaff.doctornotice.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.servicestaff.doctornotice.vo.DoctorNoticeModel;
import com.aebiz.b2b2c.servicestaff.doctornotice.vo.DoctorNoticeQueryModel;

public interface DoctorNoticeDAO extends BaseDAO<DoctorNoticeModel,DoctorNoticeQueryModel>{

	void deleteById(String uuid);
	
	/**
	 * 根据医生的uuid获取医生公告的model
	 * @param doctorId
	 * @return
	 */
         public   DoctorNoticeModel getDoctorNoticeModelByDoctorUuid(String doctorId);
         
         /**
          * 获取医生所有的公告
          * @param doctorUuid
          * @return
          */
         public  List<DoctorNoticeModel> getAllDoctorNoticeList(String doctorUuid);
         /**
          * 
          * @Description: (根据医生的id获取医生公告的uuid)    
          * @author XP  
          * @param doctorId
          * @return
          * @date 2015-12-29 上午11:24:35
          */
         public String getDoctorNoticeUuid(String doctorId);
         /**
          * 
          * @Description: (根据医生的id获取医生公告的uuids)    
          * @author XP  
          * @param doctorUuid
          * @return
          * @date 2015-12-29 上午11:42:26
          */
         public List<String> getUuidsByDoctorUuid(String doctorUuid);
             

}