package com.hxq.mobile.util.repository;

import java.util.List;
import java.util.Map;

import com.wxcommon.repository.AbstractEntity;

/**
 * 简单实体(单表)CURD接口
 *
 */
public interface SimpleEntityService {

	/**
	 * 插入实体对象
	 * @param bean
	 * @return 成功返回1
	 * @throws Exception
	 */
	public int insert(AbstractEntity<?> bean) throws Exception;

	/**
	 * 删除实体对象
	 * @param id 仅包含主键的对象
	 * @return 成功返回1
	 * @throws Exception
	 */
	public int delete(AbstractEntity<?> id) throws Exception;

	/**
	 * 通过主键修改实体对象
	 * @param bean 要修改的实体对象
	 * @return 成功返回1
	 * @throws Exception
	 */
	public int update(AbstractEntity<?> bean) throws Exception;

	/**
	 * 通过主键获取对象
	 * @param id 仅包含主键的对象
	 * @return 成功返回对象
	 * @throws Exception
	 */
	public AbstractEntity<?> select(AbstractEntity<?> id) throws Exception;

	/**
	 * 通过条件查询对象列表
	 * @param form 查询条件
	 * @param isPagination 是否分页
	 * @return List&lt;Map&lt;列名,列值&gt;&gt;
	 * @throws Exception 
	 */
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception;
}
