package com.hxq.mobile.entity.weixin;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.wxcommon.repository.AbstractEntity;
import com.wxcommon.tree.Treeable;

/**
 * 专业测试主题
 *
 */
@SuppressWarnings("serial")
@Entity(name="cs_zy_subject")
public class ProfessionSubject extends AbstractEntity<String> implements Treeable<String> {
	@Id
	public String id;
	public String title;
	@Column(name="parent_id")
	public String parentId;
	public String digest;
	@Column(columnDefinition="LONGVARCHAR")
	public String description;
	@Column(columnDefinition="default 0")
	public Integer integral;
	public String cover;
	public String media;
	public String thumb;
	public String url;
	@Column(columnDefinition="default 0")
	public Integer sort;
	@Column(name="create_date")
	public Date createDate;
	@Transient
	private Treeable<String> parent;
	@Transient
	private Collection<Treeable<String>> children;

	public ProfessionSubject() {
		super();
	}
	public ProfessionSubject(String id) {
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

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getDigest() {
		return digest;
	}
	public void setDigest(String digest) {
		this.digest = digest;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getIntegral() {
		return integral;
	}
	public void setIntegral(Integer integral) {
		this.integral = integral;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getMedia() {
		return media;
	}
	public void setMedia(String media) {
		this.media = media;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public Treeable<String> getParent() {
		return parent;
	}
	@Override
	public void setParent(Treeable<String> parent) {
		this.parent = parent;
	}
	@Override
	public Collection<Treeable<String>> getChildren() {
		return children;
	}
	@Override
	public void setChildren(Collection<Treeable<String>> children) {
		this.children = children;
	}
}
