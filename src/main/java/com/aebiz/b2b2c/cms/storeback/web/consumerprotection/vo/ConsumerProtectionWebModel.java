package com.aebiz.b2b2c.cms.storeback.web.consumerprotection.vo;

import java.util.ArrayList;
import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.web.vo.BaseWebModel;
import com.aebiz.b2b2c.cms.consumerprotection.vo.ConsumerProtectionModel;

public class ConsumerProtectionWebModel extends BaseWebModel {

	/* 没有参与保障服务的 */
	List<ConsumerProtectionModel> noList = new ArrayList<ConsumerProtectionModel>();
	
	/* 参与保障服务的 */
	List<ConsumerProtectionModel> list = new ArrayList<ConsumerProtectionModel>();
	
	public List<ConsumerProtectionModel> getNoList() {
		return noList;
	}

	public void setNoList(List<ConsumerProtectionModel> noList) {
		this.noList = noList;
	}

	public List<ConsumerProtectionModel> getList() {
		return list;
	}

	public void setList(List<ConsumerProtectionModel> list) {
		this.list = list;
	}
}
