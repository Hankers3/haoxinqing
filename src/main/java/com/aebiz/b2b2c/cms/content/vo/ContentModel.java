package com.aebiz.b2b2c.cms.content.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.filemgr.vo.FileModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.contentcategory.service.ContentCategoryService;
import com.aebiz.b2b2c.cms.contentcategory.vo.ContentCategoryModel;

@Entity
@Table(name = "content")
@Component
public class ContentModel extends BaseModel {
	@Transient
	private static ContentCategoryService contentCategoryService;

	@Autowired
	public void setContentCategoryService(
			ContentCategoryService contentCategoryService) {
		this.contentCategoryService = contentCategoryService;
	}

	@Transient
	private static FileService fileService;

	@Autowired
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	/* 内容名称 */
	private String contentTitle = "";

	/* 内容分类uuid 1：治疗指南 2：诊断标准 */
	private String contentCategoryUuid = "";
	/* 作者 */
	private String author = "";
	
	/* 出处 */
	private String provenance = "";
	
	/* 分类名称 */
	@Transient
	private String categoryName = "";

	/*  内容描述*/
	private String introduction = "";

	/* 发布状态：0,不发布，1.发布 */
	private String state = "";

	/* 关键字 */
	private String keyword = "";

	/* 标题图片 */
	private String image = "";

	/* 介绍 */
	private String contentNote = "";

	/* 外链地址 */
	private String url = "";

	/* 推荐时间 */
	private String createTime = "";

	/* 资讯类型 */
	private String contentType = "";
	
	/* 文件名称 */
	private String fileName = "";
	
	/*疾病的编号*/
	private String  illnessId;
	
	 /*症状的编号(类别)*/
	private String   symptomId;
	
	/*病种1:DSM-5 2: ICD-10*/
	private String entity;
		
	/*国家*/
	private String country;
	
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/* 医生编号 流水号 */
	private String contentNo;

	public String getContentNo() {
		return contentNo;
	}

	public void setContentNo(String contentNo) {
		this.contentNo = contentNo;
	}

	/* 图片路径 */
	@Transient
	private String imgUrl;

	public String getImgUrl() {
		if (StringUtil.isEmpty(this.image)) {
			return "";
		}
		FileModel file = fileService.getOneFileModel(this.image);
		if (file != null && !StringUtil.isEmpty(file.getRemotePaths())) {
			return file.getRemotePaths();
		} else {
			return this.getImage();
		}
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getContentNote() {
		return contentNote;
	}

	public void setContentNote(String contentNote) {
		this.contentNote = contentNote;
	}

	public String getContentCategoryUuid() {
		return contentCategoryUuid;
	}

	public void setContentCategoryUuid(String contentCategoryUuid) {
		this.contentCategoryUuid = contentCategoryUuid;
	}

	public String getCategoryName() {
		if (!StringUtil.isEmpty(this.getContentCategoryUuid())) {
			ContentCategoryModel ccm = contentCategoryService.getByUuid(this
					.getContentCategoryUuid());
			if (ccm != null) {
				return ccm.getCategoryName();
			}
		}
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setContentTitle(String obj) {
		this.contentTitle = obj;
	}

	public String getContentTitle() {
		return this.contentTitle;
	}

	public void setIntroduction(String obj) {
		this.introduction = obj;
	}

	public String getIntroduction() {
		return this.introduction;
	}

}
