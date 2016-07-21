package com.aebiz.b2b2c.cms.tags.vo;

import java.util.ArrayList;
import java.util.List;

public class TagsQueryModel extends TagsModel {
	// 已经关联标签uuid
	private List<String> stagsUuid = new ArrayList<String>();

	public List<String> getStagsUuid() {
		return stagsUuid;
	}

	public void setStagsUuid(List<String> stagsUuid) {
		this.stagsUuid = stagsUuid;
	}

	@Override
	public String toString() {
		return "Model" + this.getClass().getName() + "," + super.toString()
				+ " ,[]";
	}
}
