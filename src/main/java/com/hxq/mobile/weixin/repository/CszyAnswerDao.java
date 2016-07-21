package com.hxq.mobile.weixin.repository;

import com.hxq.mobile.entity.weixin.CszyAnswer;
import com.wxcommon.mybatis.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface CszyAnswerDao extends BaseDao<CszyAnswer, String>{
	int addCszyAnswerBatch(List<CszyAnswer> list);
}
