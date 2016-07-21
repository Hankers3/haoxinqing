package com.hxq.mobile.util.repository;

import com.hxq.mobile.weixin.repository.BaseDao;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractService<T, ID extends Serializable> implements
		BaseService<T, ID> {

	private BaseDao<T, ID> baseDao;


	public void setBaseDao(BaseDao<T, ID> baseDao){
		this.baseDao = baseDao;
	}

	@Override
	public List<T> queryAll() throws Exception{
		return baseDao.queryAll();
	}

	@Override
	public T selectByPK(ID id) throws Exception{
		return baseDao.selectByPK(id);
	}

	@Override
	public List<T> selectByCondition(T record) throws Exception{
		return baseDao.selectByCondition(record);
	}

	@Override
	public int insert(T record) throws Exception{
		return baseDao.insert(record);
	}

	@Override
	public int updateByPK(T record) throws Exception {
		return baseDao.updateByPK(record);
	}

	@Override
	public int deleteByPK(ID id) throws Exception {
		return baseDao.deleteByPK(id);
	}
}
