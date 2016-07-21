package com.aebiz.b2b2c.product.productimagelibrary.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;

/**
 * 
 * 店铺商品图片库
 * 
 * @author huangpinpin
 *
 */
@Entity
@Table(name = "product_image_library")
@Component
public class ProductImageLibraryModel extends BaseModel {
	@Transient
	private static FileService fileService;

	@Autowired
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}
	
	/* 所属分类 */
	private String categoryUuid;

	/* 图片名称 */
	private String imageName;

	/* 真实名称 */
	private String realName;

	/* 图片地址 */
	@Transient
	private String imagePath;

	/* 图片尺寸 */
	private String imageSize;

	/* 图片大小 */
	private String imageSpace;

	/* 后缀 */
	private String suffix;

	/* 上传时间 */
	private String upLoadTime;

	public String getUpLoadTime() {
		return upLoadTime;
	}
	public void setUpLoadTime(String upLoadTime) {
		this.upLoadTime = upLoadTime;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getRealName() {
		return realName;
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
	public String getImagePath() {
		if(!StringUtil.isEmpty(realName)){
			return fileService.getOneFileUrl(realName);
		}
		return "";
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public void setImageSize(String obj) {
		this.imageSize = obj;
	}
	public String getImageSize() {
		return this.imageSize;
	}

	public void setImageSpace(String imageSpace) {
		this.imageSpace = imageSpace;
	}
	public String getImageSpace() {
		return imageSpace;
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
