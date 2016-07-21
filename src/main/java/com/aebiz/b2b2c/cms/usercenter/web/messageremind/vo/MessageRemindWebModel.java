package com.aebiz.b2b2c.cms.usercenter.web.messageremind.vo;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.web.vo.BaseWebModel;

public class MessageRemindWebModel extends BaseWebModel{
	
	/*用于页面展示商户消息集合*/
	private List<CustomerMessageRemind> customerMessageReminds;

	public List<CustomerMessageRemind> getCustomerMessageReminds() {
		return customerMessageReminds;
	}

	public void setCustomerMessageReminds(
			List<CustomerMessageRemind> customerMessageReminds) {
		this.customerMessageReminds = customerMessageReminds;
	}
}