package com.hxq.mobile.weixin.service;

import com.hxq.mobile.entity.weixin.ProfessionResult;

import java.util.List;

/**
 * Created by Alice on 2016/4/7 0007.
 */
public interface CsZyResultService {
    List<ProfessionResult> selectByCondition(ProfessionResult professionResult);
}
