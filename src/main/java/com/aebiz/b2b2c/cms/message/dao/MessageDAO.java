package com.aebiz.b2b2c.cms.message.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.cms.message.vo.MessageModel;
import com.aebiz.b2b2c.cms.message.vo.MessageQueryModel;

public interface MessageDAO extends BaseDAO<MessageModel, MessageQueryModel> {
	/**
	 * 通过consultRecordUuid得到List
	 * 
	 * @param consultRecordUuid
	 * @return
	 */
	public List<MessageModel> getByConsultRecordUuid(String consultRecordUuid);

}