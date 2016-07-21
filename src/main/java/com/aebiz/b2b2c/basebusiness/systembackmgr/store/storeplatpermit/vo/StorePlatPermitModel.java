package com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatpermit.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatmenu.service.StorePlatMenuService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 商户系统的权限
 * 
 * 后台添加商户系统的权限，可以建立角色,关联不同的菜单和权限给不同类型的商户使用
 * 
 * @author duandeyi
 * 
 */

@Entity
@Table(name = "store_plat_permit")
@Component
public class StorePlatPermitModel extends BaseModel {
	@Transient
	private static StorePlatMenuService spMenuService;

	@Autowired
	public void setMyService(StorePlatMenuService bs) {
		this.spMenuService = bs;
	}

	/* 权限名称 */
	private String name = "";

	/* 权限表达式 */
	private String expression = "";

	/* 权限状态 */
	private String state = "1";

	/* 业务类型 1：菜单权限 2：按钮权限 */
	private String businessType = "";

	/* 业务uuid */
	private String businessUuid = "";

	/* 所属菜单 ,权限所属到最后一级 */
	private String belongToMenuUuid = "";

	/* 前台组织数据 */
	@Transient
	private String menuUuidLevel1 = "";

	@Transient
	private String menuUuidLevel2 = "";

	@Transient
	private String menuUuidLevel3 = "";

	@Transient
	private String menuUuidLevel4 = "";

	@Transient
	private String parentMenuName = "";

	/* 是否已经关联 */
	@Transient
	private String checked = "";

	public void setName(String obj) {
		this.name = obj;
	}

	public String getName() {
		return this.name;
	}

	public void setExpression(String obj) {
		this.expression = obj;
	}

	public String getExpression() {
		return this.expression;
	}

	public void setState(String obj) {
		this.state = obj;
	}

	public String getState() {
		return this.state;
	}

	public void setBusinessType(String obj) {
		this.businessType = obj;
	}

	public String getBusinessType() {
		return this.businessType;
	}

	public void setBusinessUuid(String obj) {
		this.businessUuid = obj;
	}

	public String getBusinessUuid() {
		return this.businessUuid;
	}

	public void setBelongToMenuUuid(String obj) {
		this.belongToMenuUuid = obj;
	}

	public String getBelongToMenuUuid() {
		return this.belongToMenuUuid;
	}

	public String getMenuUuidLevel1() {
		return menuUuidLevel1;
	}

	public void setMenuUuidLevel1(String menuUuidLevel1) {
		this.menuUuidLevel1 = menuUuidLevel1;
	}

	public String getMenuUuidLevel2() {
		return menuUuidLevel2;
	}

	public void setMenuUuidLevel2(String menuUuidLevel2) {
		this.menuUuidLevel2 = menuUuidLevel2;
	}

	public String getMenuUuidLevel3() {
		return menuUuidLevel3;
	}

	public void setMenuUuidLevel3(String menuUuidLevel3) {
		this.menuUuidLevel3 = menuUuidLevel3;
	}

	public String getMenuUuidLevel4() {
		return menuUuidLevel4;
	}

	public void setMenuUuidLevel4(String menuUuidLevel4) {
		this.menuUuidLevel4 = menuUuidLevel4;
	}

	public String getParentMenuName() {
		return spMenuService.getParentNames(this.getBelongToMenuUuid());
	}

	public void setParentMenuName(String parentMenuName) {
		this.parentMenuName = parentMenuName;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[name=" + this.getName() + ",expression="
				+ this.getExpression() + ",state=" + this.getState()
				+ ",businessType=" + this.getBusinessType() + ",businessUuid="
				+ this.getBusinessUuid() + ",belongToMenuUuid="
				+ this.getBelongToMenuUuid() + ",]";
	}
}
