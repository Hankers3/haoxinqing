package com.hxq.mobile.doctor.visit.service.impl;

import com.hxq.mobile.entity.visit.VisitRecord;
import com.hxq.mobile.util.repository.MybatisBaseServiceImp;
import com.hxq.mobile.doctor.visit.mapper.VisitRecordMapper;
import com.hxq.mobile.doctor.visit.service.VisitRecordMybatisService;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Alice on 2016/4/26 0026.
 */
public class VisitRecordMybatisServiceImp extends MybatisBaseServiceImp<VisitRecord, String> implements VisitRecordMybatisService {
    @Autowired
    private VisitRecordMapper mapper;

    @Override
    public List<VisitRecord> selectByCustomerUuid(String customerUuid) {
        return mapper.selectByCustomerUuid(customerUuid);
    }

    @Autowired
    @Override
    public void setMapper() throws Exception {
        super.setBaseMapper(mapper);
    }
}
