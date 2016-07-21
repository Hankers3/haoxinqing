package com.hxq.mobile.util.repository;

import java.io.Serializable;

/**
 * Created by Alice on 2016/4/21 0021.
 */
public interface MybatisBaseService<T,ID extends Serializable> {

    void setMapper() throws Exception;

    int insert(T record) throws Exception;

    int insertSelective(T record) throws Exception;

    int deleteByPrimaryKey(ID id) throws Exception;

	int updateByPrimaryKey(T record) throws Exception;

	int updateByPrimaryKeySelective(T record) throws Exception;

	T selectByPrimaryKey(ID id) throws Exception;
}
