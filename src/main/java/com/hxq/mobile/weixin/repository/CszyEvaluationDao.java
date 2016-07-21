package com.hxq.mobile.weixin.repository;

import com.hxq.mobile.entity.weixin.CszyEvaluation;
import com.hxq.mobile.entity.weixin.ProfessionResult;
import com.wxcommon.mybatis.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface CszyEvaluationDao extends BaseDao<CszyEvaluation, String>{
    public List<CszyEvaluation> queryByProfessionResult(ProfessionResult result);
}
