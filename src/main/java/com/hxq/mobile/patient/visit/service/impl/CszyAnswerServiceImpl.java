package com.hxq.mobile.patient.visit.service.impl;

import com.hxq.mobile.weixin.repository.CszyAnswerDao;
import com.hxq.mobile.entity.weixin.CszyAnswer;
import com.hxq.mobile.patient.visit.service.CszyAnswerService;
import com.hxq.mobile.util.repository.AbstractService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("com.wxsupport.web.service.CszyAnswerService")
public class CszyAnswerServiceImpl extends AbstractService<CszyAnswer, String> implements CszyAnswerService {

	@Autowired
	private CszyAnswerDao cszyAnswerDao;

	@Autowired
	public void setBaseDao() {
       super.setBaseDao(cszyAnswerDao);
	}

	public int addCszyAnswerBatch(List<CszyAnswer> list){
		return cszyAnswerDao.addCszyAnswerBatch(list);
	}
}
