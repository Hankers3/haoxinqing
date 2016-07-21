package com.aebiz.b2b2c.cms.consumerprotection.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;

@Entity
@Table(name = "consumer_protection")
@Component
public class ConsumerProtectionModel extends BaseModel {

	@Transient
	private static FileService fileService;

	@Autowired
	public void setFileService(FileService fileService) {
		ConsumerProtectionModel.fileService = fileService;
	}

	/* 名字的长度 */
	public static int NAMELENGTH = 100;

	/* 检查协议的长度 */
	public static int PROTOCOLCONTENT = 1000;

	/* 备注的长度 */
	public static int NOTE = 200;

	/* 需要选择商品的标志 */
	public static String NEEDCHOOSEPRODUCT = "1";

	/* 权益名称 */
	private String name;

	/* 权益图标 */
	private String icon;

	/* 是否需要选择商品 */
	private String needChooseProduct;

	/* 是否需要同意协议 */
	private String needAgreeProtocol;

	/* 协议内容 */
	private String protocolContent;

	/* 备注 */
	private String note;

	/* 状态 */
	private String state;

	/* 图片路径 */
	@Transient
	private String iconUrl;

	/* 商品的数量 */
	@Transient
	private int countProductrel;

	public void setName(String obj) {
		this.name = obj;
	}

	public String getName() {
		return this.name;
	}

	public void setIcon(String obj) {
		this.icon = obj;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setNeedChooseProduct(String obj) {
		this.needChooseProduct = obj;
	}

	public String getNeedChooseProduct() {
		return this.needChooseProduct;
	}

	public void setNeedAgreeProtocol(String obj) {
		this.needAgreeProtocol = obj;
	}

	public String getNeedAgreeProtocol() {
		return this.needAgreeProtocol;
	}

	public void setProtocolContent(String obj) {
		this.protocolContent = obj;
	}

	public String getProtocolContent() {
		return this.protocolContent;
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

	public String getIconUrl() {
		if (!StringUtils.isEmpty(icon)) {
			iconUrl = fileService.getOneFileUrl(this.getIcon());
			return iconUrl;
		}
		return "";
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public int getCountProductrel() {
		return countProductrel;
	}

	public void setCountProductrel(int countProductrel) {
		this.countProductrel = countProductrel;
	}

	@Override
	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[name=" + this.getName() + ",icon=" + this.getIcon()
				+ ",needChooseProduct=" + this.getNeedChooseProduct()
				+ ",needAgreeProtocol=" + this.getNeedAgreeProtocol()
				+ ",protocolContent=" + this.getProtocolContent() + ",note="
				+ this.getNote() + ",state=" + this.getState() + ",]";
	}
}
