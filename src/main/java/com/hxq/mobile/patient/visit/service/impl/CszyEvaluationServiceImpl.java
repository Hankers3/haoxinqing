package com.hxq.mobile.patient.visit.service.impl;

import com.hxq.mobile.weixin.repository.CszyEvaluationDao;
import com.hxq.mobile.entity.weixin.CszyEvaluation;
import com.hxq.mobile.entity.weixin.ProfessionResult;
import com.hxq.mobile.patient.visit.service.CszyEvaluationService;
import com.hxq.mobile.util.repository.AbstractService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("com.wxsupport.web.service.CszyEvaluationService")
public class CszyEvaluationServiceImpl extends AbstractService<CszyEvaluation, String> implements CszyEvaluationService {

	@Autowired
	private CszyEvaluationDao cszyEvaluationDao;

	@Autowired
	public void setBaseDao() {
       super.setBaseDao(cszyEvaluationDao);
	}

	public List<CszyEvaluation> queryByProfessionResult(ProfessionResult result){
		return cszyEvaluationDao.queryByProfessionResult(result);
	}
}
