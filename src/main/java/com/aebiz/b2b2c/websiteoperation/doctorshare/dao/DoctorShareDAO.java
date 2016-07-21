package com.aebiz.b2b2c.websiteoperation.doctorshare.dao;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.websiteoperation.doctorshare.vo.DoctorShareModel;
import com.aebiz.b2b2c.websiteoperation.doctorshare.vo.DoctorShareQueryModel;

public interface DoctorShareDAO extends BaseDAO<DoctorShareModel,DoctorShareQueryModel>{
    /**
     * 根据医生的id和文章的id获取该文章是否被分享
     * @param doctorUuid
     * @param uuid
     * @return
     */
  public  int getShareTypeByDoctorUuidAndContenUuid(String doctorUuid, String contentUuid);

}