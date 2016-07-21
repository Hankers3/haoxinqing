package com.hxq.mobile.entity.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.wxcommon.repository.AbstractEntity;

/**
 * 客户端版本
 */
@SuppressWarnings("serial")
@Entity(name="APP_PUBLISH")
public class AppPublish extends AbstractEntity<String> {
	@Id
	@Column(name="uuid")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	public String id;
	public String type;
	public String version;
	public String url;
	@Column(columnDefinition="default 0")
	public String flag;
	@Column(name="publish_date")
	public Date publishDate;
	//是否弹出,默认是0
	public String popup;
	//是否强制,默认是0,不更新,1的话强制更新
	public String forceUpdate;

	public AppPublish() {super();}
	public AppPublish(String id) {
		super();
		this.id = id;
	}
	@Override
	public String getId() {
		return id;
	}
	@Override
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public String getPopup() {
		return popup;
	}
	public void setPopup(String popup) {
		this.popup = popup;
	}
	public String getForceUpdate() {
		return forceUpdate;
	}
	public void setForceUpdate(String forceUpdate) {
		this.forceUpdate = forceUpdate;
	}
	
	
	
	
}
