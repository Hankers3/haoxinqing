package com.hxq.mobile.patient.visit.service;


import com.hxq.mobile.entity.weixin.CszyEvaluation;
import com.hxq.mobile.entity.weixin.ProfessionResult;
import com.hxq.mobile.util.repository.BaseService;

import java.util.List;

public interface CszyEvaluationService extends BaseService<CszyEvaluation, String>{
    List<CszyEvaluation> queryByProfessionResult(ProfessionResult result);
}
