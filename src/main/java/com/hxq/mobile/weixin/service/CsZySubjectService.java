package com.hxq.mobile.weixin.service;

import com.hxq.mobile.entity.weixin.CsZySubject;

import java.util.List;

public interface CsZySubjectService {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_zy_subject
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_zy_subject
     *
     * @mbggenerated
     */
    int insert(CsZySubject record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_zy_subject
     *
     * @mbggenerated
     */
    int insertSelective(CsZySubject record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_zy_subject
     *
     * @mbggenerated
     */
    CsZySubject selectByPrimaryKey(String id);

    /**
     * 查找所有的量表
     * @return list
     */
    List<CsZySubject> selectAll(String self);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_zy_subject
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(CsZySubject record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_zy_subject
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(CsZySubject record);

    CsZySubject selectNextSubject(CsZySubject csZySubject);

    List<CsZySubject> queryByPid(String parentId);
}