package com.hxq.mobile.patient.visit.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hxq.mobile.entity.weixin.CsZySubject;
import com.hxq.mobile.entity.weixin.CszyAnswer;
import com.hxq.mobile.entity.weixin.ProfessionResult;
import com.hxq.mobile.patient.visit.service.CszyAnswerService;
import com.hxq.mobile.patient.visit.service.CszyEvaluationService;
import com.hxq.mobile.patient.visit.service.CszyResultService;
import com.hxq.mobile.patient.visit.service.ProfessionCalculateService;
import com.hxq.mobile.patient.visit.service.ProfessionGatherService;
import com.hxq.mobile.weixin.service.CsZySubjectService;
import com.wxcommon.util.IdentityHelper;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.*;

/**
 * Created by digger on 15/11/25.
 */
@Service("com.hxq.mobile.patient.visit.service.ProfessionGatherService")
public class ProfessionGatherServiceImpl implements ProfessionGatherService {

    @Resource(name = "com.hxq.mobile.patient.visit.service.ProfessionCalculateService")
    private ProfessionCalculateService professionCalculateService;

    @Resource(name = "com.wxsupport.web.service.CszyResultService")
    private CszyResultService cszyResultService;

    @Resource(name = "com.wxsupport.web.service.CszyEvaluationService")
    private CszyEvaluationService cszyEvaluationService;

    @Resource(name = "com.wxsupport.web.service.CszyAnswerService")
    private CszyAnswerService cszyAnswerService;
    
    @Resource(name = "com.hxq.mobile.weixin.service.csZySubjectService")
    private CsZySubjectService csZySubjectService;

    @Override
    public int gatherAnswers(String openid,String params) {
        JSONObject obj = (JSONObject) JSON.parse(params);
        String subjectId = obj.getString("subjectId");
        JSONArray answers = obj.getJSONArray("answers");

        String resultId = IdentityHelper.uuid2();
        Map<String, String> answersMap = new HashMap<>();
        List<CszyAnswer> list = new ArrayList<>();
        for (int i = 0; i < answers.size(); i++) {
            answersMap.put(String.valueOf(i), answers.getJSONObject(i).getString("answer1"));
            CszyAnswer answer = new CszyAnswer();
            answer.setId(IdentityHelper.uuid2());
            answer.setItemId(answers.getJSONObject(i).getString("itemId"));
            answer.setAnswer1(answers.getJSONObject(i).getString("answer1"));
            answer.setResultId(resultId);
            list.add(answer);
        }
        ProfessionResult result = null;
        try {
            result = professionCalculateService.calculate(subjectId, answersMap);
            result.setId(resultId);
            result.setOpenid(openid);
            result.setCompleteDate(new Date());
            ProfessionResult hisResult = new ProfessionResult();
            hisResult.setOpenid(openid);
            hisResult.setSubjectId(subjectId);
            List<ProfessionResult> hiss = cszyResultService.selectByCondition(hisResult);
            if(hiss.size() > 0){
                result.setIntegral(0);
            }else{
                //查询积分入库
                CsZySubject subject = csZySubjectService.selectByPrimaryKey(subjectId);
                result.setIntegral(subject.getIntegral());
            }
            cszyResultService.insert(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result == null) {
            return 0;
        } else {
            //批量入库
            cszyAnswerService.addCszyAnswerBatch(list);
            return cszyEvaluationService.queryByProfessionResult(result).size() ;
        }

    }
}
