package com.hxq.mobile.common.service;

import java.util.List;

import com.hxq.mobile.entity.common.Tags;
import com.hxq.mobile.util.repository.SimpleEntityService;

/** 
* @author  作者 E-mail: liuyang
* @date 创建时间：2016年5月28日 上午11:59:28 
* @version 2.0 
* @parameter  
* @since  
* @return  
*/
public interface TagsService extends SimpleEntityService {
	/**
	 * 根据uuid标签对象
	 * @param uuid
	 * @return
	 * @throws Exception
	 */
	Tags getTagsByUuid(String uuid) throws Exception;
	
	/**
	 * 根据uuid字符串,获取标签list
	 * @param uuidStr
	 * @return
	 * @throws Exception
	 */
	List<Tags> getTagsList(String uuidStr) throws Exception;
}
