package com.hxq.mobile.weixin.repository;

import com.hxq.mobile.entity.weixin.ProfessionResult;
import com.wxcommon.mybatis.MyBatisRepository;

@MyBatisRepository()
public interface CszyResultDao extends BaseDao<ProfessionResult, String> {
}
