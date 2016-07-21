package com.aebiz.b2b2c.thirdinterface.mobilemessage.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.thirdinterface.mobilemessage.vo.MobileMessageModel;
import com.aebiz.b2b2c.thirdinterface.mobilemessage.vo.MobileMessageQueryModel;

public interface MobileMessageService extends
		BaseService<MobileMessageModel, MobileMessageQueryModel> {
	/**
	 * 获取短信设置信息
	 * 
	 * @return
	 */
	public MobileMessageModel getModel();
}
