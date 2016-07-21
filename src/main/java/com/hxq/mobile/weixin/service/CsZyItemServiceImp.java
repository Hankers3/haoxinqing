package com.hxq.mobile.weixin.service;

import com.hxq.mobile.entity.weixin.CsZyItem;
import com.hxq.mobile.weixin.repository.CsZyItemDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alice on 2016/3/17 0017
 */
@Service("com.hxq.mobile.weixin.service.csZyItemService")
public class CsZyItemServiceImp implements CsZyItemService {

    private CsZyItemDao dao;
    @Autowired
    public void setDao(CsZyItemDao dao) {
        this.dao = dao;
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    public int insert(CsZyItem record) {
        return 0;
    }

    @Override
    public int insertSelective(CsZyItem record) {
        return 0;
    }

    @Override
    public CsZyItem selectByPrimaryKey(String id) {
        return null;
    }

    /**
     * 根据专业测试的id查找题目
     * @param subjectId subjectid
     * @return list
     */
    public List<CsZyItem> selectBySubjectId(String subjectId){
        return dao.selectBySubjectId(subjectId);
    };

    @Override
    public int updateByPrimaryKeySelective(CsZyItem record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(CsZyItem record) {
        return 0;
    }
}
