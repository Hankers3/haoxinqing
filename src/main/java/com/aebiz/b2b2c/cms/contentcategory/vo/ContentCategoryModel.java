package com.aebiz.b2b2c.cms.contentcategory.vo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.contentcategory.service.ContentCategoryService;

@Entity
@Table(name = "content_category")
@Component
public class ContentCategoryModel extends BaseModel {

	/* 注入内容分类service */
	@Transient
	private static ContentCategoryService contentCategoryService;

	/* 文件service */
	@Transient
	private static FileService fileService;

	@Autowired
	public void setContentCategoryService(
			ContentCategoryService contentCategoryService) {
		this.contentCategoryService = contentCategoryService;
	}

	@Autowired
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	/* 分类编号 */
	private String categoryNo;

	/* 分类名称 */
	private String categoryName;

	/* 位置 */
	private int position;

	/* 分类图标 */
	private String image;

	/* 分类备注 */
	private String categoryNote;

	/* 分类状态 */
	private String state;

	/* 分类父类uuid */
	private String parentUuid;

	/* 分类fullpath */
	private String fullpath;

	/* 标签 */
	private String tagValue;

	/* 分类链接 */
	private String categoryUrl;

	/* 标签分类uuid */
	private String tagCategoryUuid;

	/* 分类图标路径 */
	@Transient
	private String iconPath;

	/* 所属子分类集合 */
	@Transient
	private List<ContentCategoryModel> subCotentCategorys;

	/* 分类类型*/
	private String categoryType="";//1是患教库分类 ，2咨询分类
	
	
	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	/**
	 * 获取子分类集合方法
	 * 
	 * @return
	 */
	public List<ContentCategoryModel> getSubCotentCategorys() {
		if (!StringUtil.isEmpty(super.getUuid())) {
			return contentCategoryService
					.getSubContentCategoryByParentUuid(super.getUuid());
		}
		return subCotentCategorys;
	}

	public String getIconPath() {
		if (!StringUtil.isEmpty(this.image)) {
			return this.fileService.getOneFileModel(this.image).getRemotePaths();
		}
		return iconPath;
	}

	public void setCategoryName(String obj) {
		this.categoryName = obj;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryNo(String obj) {
		this.categoryNo = obj;
	}

	public String getCategoryNo() {
		return this.categoryNo;
	}

	public void setCategoryNote(String obj) {
		this.categoryNote = obj;
	}

	public String getCategoryNote() {
		return this.categoryNote;
	}

	public void setCategoryUrl(String obj) {
		this.categoryUrl = obj;
	}

	public String getCategoryUrl() {
		return this.categoryUrl;
	}

	public void setFullpath(String obj) {
		this.fullpath = obj;
	}

	public String getFullpath() {
		return this.fullpath;
	}

	public void setParentUuid(String obj) {
		this.parentUuid = obj;
	}

	public String getParentUuid() {
		return this.parentUuid;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public void setState(String obj) {
		this.state = obj;
	}

	public String getState() {
		return this.state;
	}

	public void setTagValue(String obj) {
		this.tagValue = obj;
	}

	public String getTagValue() {
		return this.tagValue;
	}

	public void setTagCategoryUuid(String obj) {
		this.tagCategoryUuid = obj;
	}

	public String getTagCategoryUuid() {
		return this.tagCategoryUuid;
	}

	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[categoryName=" + this.getCategoryName() + ",categoryNo="
				+ this.getCategoryNo() + ",categoryNote="
				+ this.getCategoryNote() + ",categoryUrl="
				+ this.getCategoryUrl() + ",fullpath=" + this.getFullpath()
				+ ",parentUuid=" + this.getParentUuid() + ",position="
				+ this.getPosition() + ",state=" + this.getState()
				+ ",tagValue=" + this.getTagValue() + ",tagCategoryUuid="
				+ this.getTagCategoryUuid() + ",]";
	}
}
