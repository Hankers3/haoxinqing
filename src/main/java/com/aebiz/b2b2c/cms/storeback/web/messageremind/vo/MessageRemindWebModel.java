package com.aebiz.b2b2c.cms.storeback.web.messageremind.vo;

import java.util.List;
import java.util.Map;

import com.aebiz.b2b2c.baseframework.basecrud.web.vo.BaseWebModel;

public class MessageRemindWebModel extends BaseWebModel{
	
	/*商户后台页面展示的消息map*/
	private Map<String, List<StoreMessageRemind>> map;

	public Map<String, List<StoreMessageRemind>> getMap() {
		return map;
	}

	public void setMap(Map<String, List<StoreMessageRemind>> map) {
		this.map = map;
	}

	
}