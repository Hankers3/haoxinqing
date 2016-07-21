package com.aebiz.b2b2c.servicestaff.telephonecounse.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.servicestaff.telephonecounse.vo.TelephoneCounseModel;
import com.aebiz.b2b2c.servicestaff.telephonecounse.vo.TelephoneCounseQueryModel;

public interface TelephoneCounseService extends BaseService<TelephoneCounseModel,TelephoneCounseQueryModel>{
	
	/**
	 * 获取所有该医生的所有已设置的电话咨询
	 * @param doctorUuid
	 * @return
	 */
	public List<TelephoneCounseModel> getAllTelephoneCounseModels(String doctorUuid);
	
	/**
	 * 根据星期获取电话咨询设置
	 * @param weekDate
	 * @return
	 */
	public TelephoneCounseModel getTeleCounse(String weekDate,String doctorUuid);
	
	/**
	 * 删除
	 * @param qm
	 */
	public void deleteTeleCounse(TelephoneCounseModel qm);
	/**
	 * 
	 * @Description: (多条件查询电话咨询是否存在)    
	 * @author XP  
	 * @param doctorUuid
	 * @param weekDate
	 * @param startTime
	 * @param endTime
	 * @param teleTimeUuid
	 * @param teleCostUuid
	 * @return
	 * @date 2016-1-11 上午11:18:33
	 */
	public TelephoneCounseModel getTeleCounse(String doctorUuid, String weekDate, String startTime,
            String endTime, String teleTimeUuid, String teleCostUuid);
}
