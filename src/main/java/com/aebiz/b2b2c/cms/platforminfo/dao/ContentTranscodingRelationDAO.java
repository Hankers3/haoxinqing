package com.aebiz.b2b2c.cms.platforminfo.dao;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.cms.platforminfo.vo.ContentTranscodingRelation;
import com.aebiz.b2b2c.cms.platforminfo.vo.ContentTranscodingRelationQueryModel;

public interface ContentTranscodingRelationDAO extends
		BaseDAO<ContentTranscodingRelation, ContentTranscodingRelationQueryModel> {

	ContentTranscodingRelation getBySid(String sid);

	

}