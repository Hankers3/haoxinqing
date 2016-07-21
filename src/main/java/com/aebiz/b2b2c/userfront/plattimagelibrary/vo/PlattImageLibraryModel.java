package com.aebiz.b2b2c.userfront.plattimagelibrary.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 平台的图片库,对平台图片进行管理
 *
 * @author tangyunkai
 *
 * @date 2015年1月5日 下午2:14:35
 *
 */
@Entity
@Table(name = "plat_image_library")
public class PlattImageLibraryModel extends BaseModel {

	/* 所属分类   1表示美学医图，不改变*/
	private String categoryUuid = "";

	/* 图片名称 */
	private String imageName = "";

	/* 图片路径 */
	private String imagePath = "";

	/* 图片尺寸 */
	private String imageSize = "";

	/* 图片大小 */
	private String imageSpace = "";

	/* 后缀 */
	private String suffix = "";
	
	/*上传时间*/
	private String upLoadTime = "";
	
	public String getUpLoadTime() {
		return upLoadTime;
	}
	public void setUpLoadTime(String upLoadTime) {
		this.upLoadTime = upLoadTime;
	}
	public void setCategoryUuid(String obj) {
		this.categoryUuid = obj;
	}
	public String getCategoryUuid() {
		return this.categoryUuid;
	}

	public void setImageName(String obj) {
		this.imageName = obj;
	}
	public String getImageName() {
		return this.imageName;
	}

	public void setImagePath(String obj) {
		this.imagePath = obj;
	}
	public String getImagePath() {
		return this.imagePath;
	}

	public void setImageSize(String obj) {
		this.imageSize = obj;
	}
	public String getImageSize() {
		return this.imageSize;
	}

	public void setImageSpace(String obj) {
		this.imageSpace = obj;
	}
	public String getImageSpace() {
		return this.imageSpace;
	}

	public void setSuffix(String obj) {
		this.suffix = obj;
	}
	public String getSuffix() {
		return this.suffix;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[categoryUuid=" + this.getCategoryUuid() + ",imageName="
				+ this.getImageName() + ",imagePath=" + this.getImagePath()
				+ ",imageSize=" + this.getImageSize() + ",imageSpace="
				+ this.getImageSpace() + ",suffix=" + this.getSuffix() + ",]";
	}
}
