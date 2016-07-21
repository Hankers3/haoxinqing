package com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatmenu.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatmenu.service.StorePlatMenuService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;

@Entity
@Table(name = "store_plat_menu")
@Component
public class StorePlatMenuModel extends BaseModel {
	@Transient
	private static StorePlatMenuService storePlatMenuService;

	@Autowired
	public void setMyService(StorePlatMenuService bs) {
		this.storePlatMenuService = bs;
	}

	/* 菜单编号 */
	private String id = "";

	/* 菜单名称 */
	private String name = "";

	/* 跳转地址，只有三级和四级菜单有地址 */
	private String toUrl = "";

	/* 图标地址 */
	private String showIconUrl = "";

	/* 排序 */
	private int showIndex = 0;

	/* 父菜单编号 */
	private String parentMenuUuid = "";

	/* 启用状态 */
	private String state = "1";

	/* 前台组织数据 */
	@Transient
	private String menuUuidLevel1 = "";

	@Transient
	private String menuUuidLevel2 = "";

	@Transient
	private String menuUuidLevel3 = "";

	@Transient
	private String parentMenuName = "";

	/* 是否已经关联 */
	@Transient
	private String checked = "";

	public void setId(String obj) {
		this.id = obj;
	}

	public String getId() {
		return this.id;
	}

	public void setName(String obj) {
		this.name = obj;
	}

	public String getName() {
		return this.name;
	}

	public void setToUrl(String obj) {
		this.toUrl = obj;
	}

	public String getToUrl() {
		return this.toUrl;
	}

	public void setShowIconUrl(String obj) {
		this.showIconUrl = obj;
	}

	public String getShowIconUrl() {
		return this.showIconUrl;
	}

	public void setShowIndex(int obj) {
		this.showIndex = obj;
	}

	public int getShowIndex() {
		return this.showIndex;
	}

	public void setParentMenuUuid(String obj) {
		this.parentMenuUuid = obj;
	}

	public String getParentMenuUuid() {
		return this.parentMenuUuid;
	}

	public void setState(String obj) {
		this.state = obj;
	}

	public String getState() {
		return this.state;
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

	public String getParentMenuName() {
		return storePlatMenuService.getParentNames(this.getParentMenuUuid());
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
				+ "[id=" + this.getId() + ",name=" + this.getName() + ",toUrl="
				+ this.getToUrl() + ",showIconUrl=" + this.getShowIconUrl()
				+ ",showIndex=" + this.getShowIndex() + ",parentMenuUuid="
				+ this.getParentMenuUuid() + ",state=" + this.getState() + ",]";
	}
}
