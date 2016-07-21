package com.hxq.mobile.entity.visit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.wxcommon.repository.AbstractEntity;


@SuppressWarnings("serial")
@Entity(name = "content_category")
public class ContentCategory extends AbstractEntity<String> {

	/* 编号 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
    public String uuid;
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

	
	/* 分类类型*/
	private String categoryType="";//1是患教库分类 ，2咨询分类
	
	public ContentCategory() {super();}
	public ContentCategory(String uuid) {
		super();
		this.uuid = uuid;
	}
	@Override
	public String getId() {
		return uuid;
	}
	@Override
	public void setId(String id) {
		this.uuid = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
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
