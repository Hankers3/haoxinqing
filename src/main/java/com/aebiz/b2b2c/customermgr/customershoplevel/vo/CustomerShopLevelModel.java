package com.aebiz.b2b2c.customermgr.customershoplevel.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.filemgr.vo.FileModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customershoplevel.service.CustomerShopLevelService;

@Entity
@Table(name = "customer_shop_level")
@Component
public class CustomerShopLevelModel extends BaseModel {

	@Transient
	private static CustomerShopLevelService myService;

	@Transient
	private static FileService fileService;

	/* 等级名称 */
	private String levelName = "";

	/* 积分开始范围 */
	private int minIntegral ;

	/* 积分结束范围 */
	private int maxIntegral;

	/* 等级图标 */
	private String levelIcon = "";

	/* 位置排序 */
	private String position = "";

	/* 等级描述 */
	private String description = "";

	/* 得到图片路径 */
	@Transient
	private String imgUrl = "";

	@Autowired
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	@Autowired
	public void setMyService(CustomerShopLevelService myService) {
		this.myService = myService;
	}

	public void setLevelName(String obj) {
		this.levelName = obj;
	}

	public String getLevelName() {
		return this.levelName;
	}

	public void setMinIntegral(int obj) {
		this.minIntegral = obj;
	}

	public int getMinIntegral() {
		return this.minIntegral;
	}

	public void setMaxIntegral(int obj) {
		this.maxIntegral = obj;
	}

	public int getMaxIntegral() {
		return this.maxIntegral;
	}

	public void setLevelIcon(String obj) {
		this.levelIcon = obj;
	}

	public String getLevelIcon() {
		return this.levelIcon;
	}

	public void setPosition(String obj) {
		this.position = obj;
	}

	public String getPosition() {
		return this.position;
	}

	public void setDescription(String obj) {
		this.description = obj;
	}

	public String getDescription() {
		return this.description;
	}

	public String getImgUrl() {
		if (StringUtil.isEmpty(this.levelIcon)) {
			return "";
		}
		FileModel file = fileService.getOneFileModel(this.levelIcon);
		if (file != null) {
			return file.getRemotePaths();
		} else {
			return this.getLevelIcon();
		}
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[levelName=" + this.getLevelName() + ",minIntegral="
				+ this.getMinIntegral() + ",maxIntegral="
				+ this.getMaxIntegral() + ",levelIcon=" + this.getLevelIcon()
				+ ",position=" + this.getPosition() + ",description="
				+ this.getDescription() + ",]";
	}
}
