package com.aebiz.b2b2c.cms.tagscategoy.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.cms.tagscategoy.vo.TagsCategoryModel;
import com.aebiz.b2b2c.cms.tagscategoy.vo.TagsCategoryQueryModel;

public interface TagsCategoryService extends
		BaseService<TagsCategoryModel, TagsCategoryQueryModel> {
	public String getTagsCategoryNameByUuid(String Uuid);
}
