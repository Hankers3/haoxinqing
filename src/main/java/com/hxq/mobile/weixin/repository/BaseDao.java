package com.hxq.mobile.weixin.repository;

import java.io.Serializable;
import java.util.List;

/*
 * @auther sunchao
 * 通用dao 提供基本CRUD
 * */
public interface BaseDao<T,ID extends Serializable>{
	  List<T> queryAll() throws Exception;
	  T selectByPK(ID id) throws Exception;
	  List<T> selectByCondition(T record) throws Exception;
	  int insert(T record) throws Exception;
	  int updateByPK(T record) throws Exception;
	  int deleteByPK(ID id) throws Exception;
}
