package com.aebiz.b2b2c.servicestaff.doctornotice.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

import com.aebiz.b2b2c.servicestaff.doctornotice.vo.DoctorNoticeModel;
import com.aebiz.b2b2c.servicestaff.doctornotice.vo.DoctorNoticeQueryModel;

public interface DoctorNoticeService extends BaseService<DoctorNoticeModel,DoctorNoticeQueryModel>{

	/**
	 * 通过uuid得到查看医生公告信息
	 * @param uuid
	 */
	DoctorNoticeModel getDoctorNoticeModel(String uuid);

	public  void deleteById(String uuid);

	
	/**
         * 通过医生的uuid得到查看医生公告信息
         * @param uuid
         */
        public  DoctorNoticeModel getDoctorNoticeModelByDoctorUuid(String doctorId);
        /**
         * 获取医生所有的公告
         * @param doctorUuid
         * @return
         */
        public  List<DoctorNoticeModel> getAllDoctorNoticeList(String doctorUuid);


}
