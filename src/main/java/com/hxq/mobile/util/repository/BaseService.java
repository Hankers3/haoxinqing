package com.hxq.mobile.util.repository;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T,ID extends Serializable> {
	  void setBaseDao() throws Exception;
	  List<T> queryAll() throws Exception;
	  T selectByPK(ID id) throws Exception;
	  List<T> selectByCondition(T record) throws Exception;
	  int insert(T record) throws Exception;
	  int updateByPK(T record) throws Exception;
	  int deleteByPK(ID id) throws Exception;
}
