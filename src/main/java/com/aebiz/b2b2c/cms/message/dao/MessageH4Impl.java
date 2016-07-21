package com.aebiz.b2b2c.cms.message.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.cms.message.vo.MessageModel;
import com.aebiz.b2b2c.cms.message.vo.MessageQueryModel;
import com.aebiz.b2b2c.cms.platformapply.vo.PlatformApplyModel;

@Repository
public class MessageH4Impl extends BaseH4Impl<MessageModel, MessageQueryModel>
		implements MessageDAO {
	/**
	 * 通过consultRecordUuid得到List
	 * 
	 * @param consultRecordUuid
	 * @return
	 */
	@Override
	public List<MessageModel> getByConsultRecordUuid(String consultRecordUuid) {
		StringBuffer hql = new StringBuffer(
				" from MessageModel as o where o.consultRecordUuid =:consultRecordUuid ");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("consultRecordUuid", consultRecordUuid);
		List list = query.list();
		if (list != null && list.size() > 0) {
			return (List<MessageModel>) list;
		}
		return null;
	}

}
