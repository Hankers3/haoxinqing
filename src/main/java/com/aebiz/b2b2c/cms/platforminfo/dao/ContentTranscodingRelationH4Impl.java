package com.aebiz.b2b2c.cms.platforminfo.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.cms.platforminfo.vo.ContentTranscodingRelation;
import com.aebiz.b2b2c.cms.platforminfo.vo.ContentTranscodingRelationQueryModel;

@Repository
public class ContentTranscodingRelationH4Impl extends BaseH4Impl<ContentTranscodingRelation, ContentTranscodingRelationQueryModel>
		implements ContentTranscodingRelationDAO {

	@Override
	public ContentTranscodingRelation getBySid(String sid) {
		String hql = new String("from ContentTranscodingRelation ctr where ctr.sid =:sid ");
		Query query = this.getH4Session().createQuery(hql);
		query.setString("sid", sid);
		List<ContentTranscodingRelation> list = query.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	
}
