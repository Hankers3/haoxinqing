package com.hxq.mobile.doctor.content.service;


import com.hxq.mobile.util.repository.SimpleEntityService;

public interface DoctorShareService extends  SimpleEntityService{
	/**
	 * 根据医生的id和文章的id获取该文章是否被分享
	 * @param doctorUuid
	 * @param contentUuid
	 * @return
	 */
	public int selectShareTypeByDoctorUuidAndContenUuid(String doctorUuid, String contentUuid);
}
