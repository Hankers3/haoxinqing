package com.aebiz.b2b2c.websiteoperation.doctorshare.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.websiteoperation.doctorshare.vo.DoctorShareModel;
import com.aebiz.b2b2c.websiteoperation.doctorshare.vo.DoctorShareQueryModel;

public interface DoctorShareService extends BaseService<DoctorShareModel,DoctorShareQueryModel>{
    /**
     * 根据医生的id和文章的id获取该文章是否被分享
     * @param doctorUuid
     * @param uuid
     * @return
     */
   public int getShareTypeByDoctorUuidAndContenUuid(String doctorUuid, String uuid);

}
