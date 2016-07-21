package com.hxq.mobile.patient.visit.service;

import com.hxq.mobile.entity.weixin.CszyAnswer;
import com.hxq.mobile.util.repository.BaseService;

import java.util.List;

public interface CszyAnswerService extends BaseService<CszyAnswer, String>{
	int addCszyAnswerBatch(List<CszyAnswer> list);
}
