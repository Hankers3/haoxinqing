package com.hxq.mobile.common.mapper;

import com.hxq.mobile.entity.common.DepartmentInfo;
import com.wxcommon.mybatis.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface DepartmentInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table department_info
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String uuid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table department_info
     *
     * @mbggenerated
     */
    int insert(DepartmentInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table department_info
     *
     * @mbggenerated
     */
    int insertSelective(DepartmentInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table department_info
     *
     * @mbggenerated
     */
    DepartmentInfo selectByPrimaryKey(String uuid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table department_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(DepartmentInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table department_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(DepartmentInfo record);

    List<DepartmentInfo> selectAllDepartment();
}