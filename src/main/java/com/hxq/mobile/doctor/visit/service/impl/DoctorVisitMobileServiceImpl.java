package com.hxq.mobile.doctor.visit.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hxq.mobile.doctor.visit.mapper.VisitApplyMapper;
import com.hxq.mobile.doctor.visit.mapper.VisitPreceptMapper;
import com.hxq.mobile.doctor.visit.service.DoctorVisitMobileService;
import com.hxq.mobile.entity.visit.VisitPrecept;
import com.hxq.mobile.util.api.ApiResult;
import com.hxq.mobile.util.repository.MybatisSimpleEntityService;
import com.hxq.mobile.util.repository.SimpleEntityDao;
import com.wxcommon.util.ObjectUtils;

/**
 * Created by Alice on 2016/4/26 0026.
 */
@Service("com.hxq.mobile.doctor.visit.service.DoctorVisitMobileService")
public class DoctorVisitMobileServiceImpl extends MybatisSimpleEntityService implements DoctorVisitMobileService {
    @Override
    protected SimpleEntityDao getDao() {
        return null;
    }

    @Override
    protected String getCacheName() {
        return "DoctorVisitMobileService";
    }

    @Autowired
    private VisitPreceptMapper visitPreceptMapper;
    @Autowired
    private VisitApplyMapper visitApplyMapper;

    @Override
    public ApiResult getRecommendVisitpreceptByDoctorid_v2(String doctorUuid) throws Exception {
        List<Map<String, Object>> relist = new ArrayList<>();

        List<VisitPrecept> visitPrecepts = visitPreceptMapper.getRecommendVisitpreceptList();

        if (!ObjectUtils.isEmpty(visitPrecepts) && visitPrecepts.size() > 0) {
            Map<String, Object> temp = new HashMap<>();
            for (VisitPrecept vp : visitPrecepts) {
                Map<String, Object> visitPreceptMap = new HashMap<>();
                if (vp != null) {
                    temp.put("doctorUuid", doctorUuid);
                    temp.put("visitUuid", vp.getUuid());
                    int num = visitApplyMapper.countCustomerByVisitPrecept(temp);
                    visitPreceptMap.put("visitUuid", vp.getUuid());
                    visitPreceptMap.put("preceptName", vp.getPreceptName());
                    visitPreceptMap.put("doctorUuid", doctorUuid);
                    visitPreceptMap.put("num", num);
                    relist.add(visitPreceptMap);
                }
            }
        }
        return ApiResult.right(relist);
    }

    @Override
    public ApiResult getMyVisitPreceptList_v2(String doctorUuid) throws Exception {
        // 获取随访方案的集合列表
        List<Map<String, Object>> relist = new ArrayList<>();
        // 根据医生的uuid查询出所有的方案

        List<VisitPrecept> visitPrecepts = visitPreceptMapper.getMyVisitpreceptByDoctorid(doctorUuid);

        if (!ObjectUtils.isEmpty(visitPrecepts) && visitPrecepts.size() > 0) {
            Map<String, Object> temp = new HashMap<>();
            for (VisitPrecept vp : visitPrecepts) {
                Map<String, Object> visitPreceptMap = new HashMap<>();
                if (vp != null) {
                    temp.put("doctorUuid", doctorUuid);
                    temp.put("visitUuid", vp.getUuid());
                    int num = visitApplyMapper.countCustomerByVisitPrecept(temp);
                    visitPreceptMap.put("visitUuid", vp.getUuid());
                    visitPreceptMap.put("preceptName", vp.getPreceptName());
                    visitPreceptMap.put("doctorUuid", doctorUuid);
                    visitPreceptMap.put("delFlag", vp.getDelFlag());
                    visitPreceptMap.put("num", num);
                    relist.add(visitPreceptMap);
                }
            }
        }
        return ApiResult.right(relist);
    }
}
