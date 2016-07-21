package com.hxq.mobile.patient.common.service;

import com.hxq.mobile.entity.common.DoctorNotice;
import com.hxq.mobile.util.repository.SimpleEntityService;

/*
 * 今日推荐
 */
public interface DoctorNoticeService extends SimpleEntityService{
    /**
     * 根据医生uuid来获取用户公告的信息
     * @param doctorId
     * @return
     * @throws Exception
     */
    public DoctorNotice selectDoctorNoticeByDoctorUuid(String doctorId) throws Exception ;
}
