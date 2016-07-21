package com.hxq.mobile.util.repository;

import java.util.List;
import java.util.Map;

import com.wxcommon.repository.AbstractEntity;

public interface SimpleEntityDao {

	/**
	 * 插入实体对象
	 * @param bean
	 * @return 成功返回1
	 */
	public int insert(AbstractEntity<?> bean);

    /**
     * 通过主键 删除对象
     * @param id 仅包含主键的对象
     * @return
     */
	public int delete(AbstractEntity<?> id);

    /**
     * 通过主键获取对象
     * @param id 仅包含主键的对象
     * @return
     */
	public AbstractEntity<?> select(AbstractEntity<?> id);

    /**
     * 通过主键更新对象
     * @param bean 对象
     * @return
     */
	public int update(AbstractEntity<?> bean);

	/**
	 * 通过条件查询对象列表
	 * @param form 查询条件
	 * @return List&lt;Map&lt;列名,列值&gt;&gt;
	 */
	public List<Map<String, Object>> selectList(Map<String, Object> form);
}
