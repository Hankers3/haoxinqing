package com.hxq.mobile.entity.visit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.wxcommon.repository.AbstractEntity;

/**
 * 内容资讯
 */
@SuppressWarnings("serial")
@Entity(name = "content")
public class Content extends AbstractEntity<String> {
	/* 编号 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
    public String uuid;
	@Column(columnDefinition="default 1")
	public String delFlag;
	public String opeTime;
	public String oper;
	/* 内容名称 */
	public String contentTitle;
	/* 资讯类型 */
	public String contentType;
	/* 内容描述 */
	public String introduction;
	/* 内容分类uuid 1：治疗指南 2：诊断标准 */
	public String contentCategoryUuid;
	/* 内容图标 */
	public String icon;
	/* 外链地址 */
	public String url;
	/* 介绍 */
	public String contentNote;
	/* 标题图片 */
	public String image;
	/* 关键字 */
	public String keyword;
	/* 发布状态：0,不发布，1.发布 */
	public String state;
	/* 推荐时间 */
	public String createTime;
	/* 文件名称 */
	public String fileName;
	public String note;
	/* 医生编号 流水号 */
	public String contentNo;
	/* 作者 */
	public String author;
	/* 出处 */
	public String provenance;
	/*疾病的编号*/
	public String illnessId;
	 /*症状的编号(类别)*/
	public String symptomId;
	/*病种1:DSM-5 2: ICD-10*/
	public String entity;
	/*国家*/
	public String country;

	public Content() {super();}
	public Content(String uuid) {
		super();
		this.uuid = uuid;
	}
	@Override
	public String getId() {
		return uuid;
	}
	@Override
	public void setId(String id) {
		this.uuid = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public String getOpeTime() {
		return opeTime;
	}
	public void setOpeTime(String opeTime) {
		this.opeTime = opeTime;
	}
	public String getOper() {
		return oper;
	}
	public void setOper(String oper) {
		this.oper = oper;
	}
	public String getContentTitle() {
		return contentTitle;
	}
	public void setContentTitle(String contentTitle) {
		this.contentTitle = contentTitle;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getContentCategoryUuid() {
		return contentCategoryUuid;
	}
	public void setContentCategoryUuid(String contentCategoryUuid) {
		this.contentCategoryUuid = contentCategoryUuid;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContentNote() {
		return contentNote;
	}
	public void setContentNote(String contentNote) {
		this.contentNote = contentNote;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getContentNo() {
		return contentNo;
	}
	public void setContentNo(String contentNo) {
		this.contentNo = contentNo;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getProvenance() {
		return provenance;
	}
	public void setProvenance(String provenance) {
		this.provenance = provenance;
	}
	public String getIllnessId() {
		return illnessId;
	}
	public void setIllnessId(String illnessId) {
		this.illnessId = illnessId;
	}
	public String getSymptomId() {
		return symptomId;
	}
	public void setSymptomId(String symptomId) {
		this.symptomId = symptomId;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}
