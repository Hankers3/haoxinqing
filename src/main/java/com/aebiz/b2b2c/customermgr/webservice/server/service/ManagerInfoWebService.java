package com.aebiz.b2b2c.customermgr.webservice.server.service;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 用户中心外部接口
 * 
 * @author tangyunkai
 * 
 */
@WebService
public interface ManagerInfoWebService {
	
	
	 /**
         * 患者用户接口
         * @param jsonString
         * @return
         */
        public String getManagerInfo(@WebParam(name="jsonString") String jsonString);
        
}
