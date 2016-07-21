package com.aebiz.b2b2c.cms.channel.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name = "channel")
public class ChannelModel extends BaseModel {
	
	/* 频道名称 */
	private String channelName;
	
	/* 链接地址 */
	private String channelUrl;
	
	/* 所在位置 */
	private Integer position;
	
	/* 创建时间 */
	private String createTime;

	public void setChannelName(String obj) {
		this.channelName = obj;
	}

	public String getChannelName() {
		return this.channelName;
	}

	public void setChannelUrl(String obj) {
		this.channelUrl = obj;
	}

	public String getChannelUrl() {
		return this.channelUrl;
	}

	public void setPosition(Integer obj) {
		this.position = obj;
	}

	public Integer getPosition() {
		return this.position;
	}

	public void setCreateTime(String obj) {
		this.createTime = obj;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[channelName=" + this.getChannelName() + ",channelUrl="
				+ this.getChannelUrl() + ",position=" + this.getPosition()
				+ ",createTime=" + this.getCreateTime() + ",]";
	}
}
