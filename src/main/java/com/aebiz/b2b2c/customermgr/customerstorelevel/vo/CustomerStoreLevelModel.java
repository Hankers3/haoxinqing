package com.aebiz.b2b2c.customermgr.customerstorelevel.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.filemgr.vo.FileModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.storeback.service.customerstorelevel.CustomerStoreLevelService;

@Entity
@Table(name = "customer_store_level")
@Component
public class CustomerStoreLevelModel extends BaseModel {
	@Transient
	private static CustomerStoreLevelService myService;

	@Transient
	private static FileService fileService;

	/* 商户编号 */
	private String storeUuid = "";

	/* 等级名称 */
	private String levelName = "";

	/* 购买金额 */
	private String buyTotalMoney = "";

	/* 购买笔数 */
	private String buyTotalCount = "";

	/* 位置排序 */
	private String position = "";

	/* 等级描述 */
	private String description = "";

	/* 等级图标 */
	private String levelIcon = "";

	/* 得到图片路径 */
	@Transient
	private String imgUrl = "";

	@Autowired
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	@Autowired
	public void setMyService(CustomerStoreLevelService myService) {
		this.myService = myService;
	}

	public void setStoreUuid(String obj) {
		this.storeUuid = obj;
	}

	public String getStoreUuid() {
		return this.storeUuid;
	}

	public void setLevelName(String obj) {
		this.levelName = obj;
	}

	public String getLevelName() {
		return this.levelName;
	}

	public void setBuyTotalMoney(String obj) {
		this.buyTotalMoney = obj;
	}

	public String getBuyTotalMoney() {
		return this.buyTotalMoney;
	}

	public void setBuyTotalCount(String obj) {
		this.buyTotalCount = obj;
	}

	public String getBuyTotalCount() {
		return this.buyTotalCount;
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

	public void setLevelIcon(String obj) {
		this.levelIcon = obj;
	}

	public String getLevelIcon() {
		return this.levelIcon;
	}

	public String getImgUrl() {
		if (StringUtil.isEmpty(this.levelIcon)) {
			return "";
		}
		FileModel file = fileService.getOneFileModel(this.levelIcon);
		if (file != null) {
			return file.getRemotePaths();
		} else {
			return "";
		}
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[storeUuid=" + this.getStoreUuid() + ",levelName="
				+ this.getLevelName() + ",buyTotalMoney="
				+ this.getBuyTotalMoney() + ",buyTotalCount="
				+ this.getBuyTotalCount() + ",position=" + this.getPosition()
				+ ",description=" + this.getDescription() + ",levelIcon="
				+ this.getLevelIcon() + ",]";
	}
}
