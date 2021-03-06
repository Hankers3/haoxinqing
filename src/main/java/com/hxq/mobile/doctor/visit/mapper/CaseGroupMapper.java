package com.hxq.mobile.doctor.visit.mapper;

import com.hxq.mobile.entity.visit.CaseGroup;
import com.wxcommon.mybatis.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface CaseGroupMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table case_group
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String uuid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table case_group
     *
     * @mbggenerated
     */
    int insert(CaseGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table case_group
     *
     * @mbggenerated
     */
    int insertSelective(CaseGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table case_group
     *
     * @mbggenerated
     */
    CaseGroup selectByPrimaryKey(String uuid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table case_group
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(CaseGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table case_group
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(CaseGroup record);

    List<CaseGroup> selectByDoctorUuid(String doctorUuid);
}