package com.aebiz.b2b2c.websiteoperation.quizcategory.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.filemgr.vo.FileModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;

/**
 * 测试分类表
 * 
 * @author szr
 * 
 */
@Entity
@Table(name = "quiz_category")
@Component
public class QuizCategoryModel extends BaseModel {
	@Transient
	private static FileService fileService;

	@Autowired
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	/* 分类名称 */
	private String categoryName;
	/* 测试说明 */
	private String note;
	/* 填写说明 */
	private String fillExplain;
	/* 状态 */
	private String state;
	/* 创建时间 */
	private String createTime;

	/* 简图 */
	private String image;

	/* 图片路径 */
	@Transient
	private String imgUrl;

	public String getFillExplain() {
		return fillExplain;
	}

	public void setFillExplain(String fillExplain) {
		this.fillExplain = fillExplain;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

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

	public void setCategoryName(String obj) {
		this.categoryName = obj;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setNote(String obj) {
		this.note = obj;
	}

	public String getNote() {
		return this.note;
	}

	public void setState(String obj) {
		this.state = obj;
	}

	public String getState() {
		return this.state;
	}

	public void setCreateTime(String obj) {
		this.createTime = obj;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[categoryName=" + this.getCategoryName() + ",note="
				+ this.getNote() + ",state=" + this.getState() + ",createTime="
				+ this.getCreateTime() + ",]";
	}
}
