package com.hxq.mobile.util.mybatis;

import java.io.Serializable;

/**
 * Created by Alice on 2016/4/21 0021.
 * 通用基础mapper
 */
public interface BaseMapper<T, ID extends Serializable> {

    int deleteByPrimaryKey(ID id) throws Exception;

    int insert(T record) throws Exception;

    int insertSelective(T record) throws Exception;

    T selectByPrimaryKey(ID id) throws Exception;

    int updateByPrimaryKeySelective(T record) throws Exception;

    int updateByPrimaryKey(T record) throws Exception;
}
