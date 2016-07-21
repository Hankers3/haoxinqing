package com.aebiz.b2b2c.cms.platforminfo.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.cms.platforminfo.vo.ContentTranscodingRelation;
import com.aebiz.b2b2c.cms.platforminfo.vo.ContentTranscodingRelationQueryModel;

public interface ContentTranscodingRelationService extends BaseService<ContentTranscodingRelation, ContentTranscodingRelationQueryModel> {

	ContentTranscodingRelation getBySid(String sid);

	

}