package com.hxq.mobile.weixin.service;

import com.hxq.mobile.entity.weixin.ProfessionResult;
import com.hxq.mobile.weixin.repository.CszyResultDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Alice on 2016/4/7 0007.
 */
@Service("com.hxq.mobile.weixin.service.CsZyResultService")
public class CsZyResultServiceImp implements CsZyResultService {

    private CszyResultDao dao;

    @Autowired
    public void setDao(CszyResultDao dao) {
        this.dao = dao;
    }

    @Override
    public List<ProfessionResult> selectByCondition(ProfessionResult professionResult) {
        try {
            return dao.selectByCondition(professionResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
