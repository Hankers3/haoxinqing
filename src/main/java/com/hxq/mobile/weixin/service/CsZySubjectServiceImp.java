package com.hxq.mobile.weixin.service;

import com.hxq.mobile.entity.weixin.CsZySubject;
import com.hxq.mobile.weixin.repository.CsZySubjectDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alice on 2016/3/17 0017
 */
@Service("com.hxq.mobile.weixin.service.csZySubjectService")
public class CsZySubjectServiceImp implements CsZySubjectService {

    private CsZySubjectDao dao;

    @Autowired
    public void setDao(CsZySubjectDao dao) {
        this.dao = dao;
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    public int insert(CsZySubject record) {
        return 0;
    }

    @Override
    public int insertSelective(CsZySubject record) {
        return 0;
    }

    @Override
    public CsZySubject selectByPrimaryKey(String id) {
        return dao.selectByPrimaryKey(id);
    }

    /**
     * 查找所有的量表
     * @return list
     */
    public List<CsZySubject> selectAll(String self){
        return dao.selectAll(self);
    };

    @Override
    public int updateByPrimaryKeySelective(CsZySubject record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(CsZySubject record) {
        return 0;
    }

    @Override
    public CsZySubject selectNextSubject(CsZySubject CsZySubject) {
        return dao.selectNextSubject(CsZySubject);
    }

    @Override
    public List<CsZySubject> queryByPid(String parentId){
        return dao.queryByPid(parentId);
    }
}
