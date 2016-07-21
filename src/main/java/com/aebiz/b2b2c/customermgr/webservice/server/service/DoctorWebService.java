package com.aebiz.b2b2c.customermgr.webservice.server.service;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 测试题
 * 
 * @author szr
 * 
 */
@WebService
public interface DoctorWebService {

	/**
	 * 获取平台注册医生信息
	 * 
	 * @param jsonString
	 * @return
	 */
	public String getDoctorInfoService(
			@WebParam(name = "jsonString") String jsonString);
}
