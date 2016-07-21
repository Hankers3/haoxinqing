package com.aebiz.b2b2c.cms.tagscategoy.dao;

import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.cms.tagscategoy.vo.TagsCategoryModel;
import com.aebiz.b2b2c.cms.tagscategoy.vo.TagsCategoryQueryModel;

@Repository
public class TagsCategoyH4Impl extends
		BaseH4Impl<TagsCategoryModel, TagsCategoryQueryModel> implements
		TagsCategoyDAO {

}
