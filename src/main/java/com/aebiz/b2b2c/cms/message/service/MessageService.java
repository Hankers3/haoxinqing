package com.aebiz.b2b2c.cms.message.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.cms.message.vo.MessageModel;
import com.aebiz.b2b2c.cms.message.vo.MessageQueryModel;


public interface MessageService extends BaseService<MessageModel,MessageQueryModel>{

	/**
	 * 通过consultRecordUuid得到List
	 * 
	 * @param consultRecordUuid
	 * @return
	 */
	public List<MessageModel> getByConsultRecordUuid(String consultRecordUuid);

}
