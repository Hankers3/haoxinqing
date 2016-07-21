package com.hxq.mobile.common.mapper;

import com.hxq.mobile.entity.common.BasicdataAreaRegion;
import com.wxcommon.mybatis.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface BasicdataAreaRegionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basicdata_area_region
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String uuid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basicdata_area_region
     *
     * @mbggenerated
     */
    int insert(BasicdataAreaRegion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basicdata_area_region
     *
     * @mbggenerated
     */
    int insertSelective(BasicdataAreaRegion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basicdata_area_region
     *
     * @mbggenerated
     */
    BasicdataAreaRegion selectByPrimaryKey(String uuid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basicdata_area_region
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(BasicdataAreaRegion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basicdata_area_region
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(BasicdataAreaRegion record);

    List<BasicdataAreaRegion> selectByCityId(String cityUuid);
}