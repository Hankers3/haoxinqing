package com.aebiz.b2b2c.cms.messageremind.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.cms.messageremind.vo.MessageRemindModel;
import com.aebiz.b2b2c.cms.messageremind.vo.MessageRemindQueryModel;
import com.aebiz.b2b2c.cms.usercenter.web.messageremind.vo.CustomerMessageRemind;

public interface MessageRemindService extends BaseService<MessageRemindModel,MessageRemindQueryModel>{	
	/**
	 * 根据会员uuid的到会员消息内容和会员消息设置
	 * @param customerUuid
	 * @return
	 */
	public List<CustomerMessageRemind> getCoustomerMessageWebModelList(String customerUuid);
}
