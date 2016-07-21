package com.hxq.mobile.doctor.content.service;

import java.util.List;
import java.util.Map;

import com.hxq.mobile.util.repository.SimpleEntityService;

/**
 * 资讯service
 */
public interface ContentService  extends SimpleEntityService {
	
	/**
	 * 获取资讯,诊断标准的列表 
	 * @param contentCategoryUuid 内容分类uuid
	 * @param pageCount 条数
	 * @param pageNo 页数
	 * @return [查询结果列表, 当前页号, 总页数, 总记录数]
	 */
	public Object[] selectByContentCategoryUuid(String contentCategoryUuid, Integer pageCount, Integer pageNo);

	/**
	 * 获取通过条件筛选后的资讯列表
	 * @param contentCategoryUuid 内容分类id
	 * @param contentName 资讯标题
	 * @param illnessId 病例编号
	 * @param pageCount 条数
	 * @param pageNo 页数
	 * @return [查询结果列表, 当前页号, 总页数, 总记录数]
	 */
	public Object[] selectScreenByContentCategoryUuid(String contentCategoryUuid, String contentName, String illnessId, Integer pageCount,Integer pageNo);

	/**
	 * 获取资讯的详细信息& 获取诊断标准的详细信息
	 * @param contentCategoryUuid 内容分类uuid
	 * @return
	 */
	public List<Map<String, Object>> selectContent(String uuid);
}
