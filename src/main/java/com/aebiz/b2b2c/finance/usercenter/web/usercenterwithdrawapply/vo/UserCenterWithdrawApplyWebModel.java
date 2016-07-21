package com.aebiz.b2b2c.finance.usercenter.web.usercenterwithdrawapply.vo;

import com.aebiz.b2b2c.baseframework.basecrud.web.vo.BaseWebModel;

public class UserCenterWithdrawApplyWebModel extends BaseWebModel{
	
	/*查询时间（如：三个月内、三个月前、一年内）*/
	private String time ="";

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
}