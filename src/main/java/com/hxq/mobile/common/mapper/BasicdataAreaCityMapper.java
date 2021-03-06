package com.hxq.mobile.common.mapper;

import com.hxq.mobile.entity.common.BasicdataAreaCity;
import com.wxcommon.mybatis.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface BasicdataAreaCityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basicdata_area_city
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String uuid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basicdata_area_city
     *
     * @mbggenerated
     */
    int insert(BasicdataAreaCity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basicdata_area_city
     *
     * @mbggenerated
     */
    int insertSelective(BasicdataAreaCity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basicdata_area_city
     *
     * @mbggenerated
     */
    BasicdataAreaCity selectByPrimaryKey(String uuid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basicdata_area_city
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(BasicdataAreaCity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basicdata_area_city
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(BasicdataAreaCity record);

    List<BasicdataAreaCity> selectCityByProvinceUuid(String provinceUuid);
}