package com.hxq.mobile.doctor.content.service;

import java.util.List;
import java.util.Map;

import com.hxq.mobile.util.repository.SimpleEntityService;

/**
 * 用药手册service
 */
public interface UseTheManualService  extends SimpleEntityService {

	List<Map<String, Object>> selectProductMainListByName(String medicineName, Integer pageCount, Integer pageNo);

	Map<String, Object> selectProductMainDescriptionByid(String productUuid);
	
}
