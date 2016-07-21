package com.aebiz.b2b2c.product.productcategoryfront.vo;

import java.util.List;

public class SelectAttributeJson {
	public static final String YES = "1";
	public static final String NO = "0";
	
	/* 属性uuid*/
	private String attributeUuid;
	
	/* 属性名称*/
	private String attributeName;
	
	/* 属性英文名*/
	private String attributeEnName;
	
	/* 单位*/
	private String unit;
	
	/* 是否区间*/
	private String type;
	
	/* 位置*/
	private int position;
	
	/* 选择的属性值*/
	private List<String> selectValue;

	
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getUnit() {
		return unit;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	
	public String getAttributeUuid() {
		return attributeUuid;
	}

	public void setAttributeUuid(String attributeUuid) {
		this.attributeUuid = attributeUuid;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getAttributeEnName() {
		return attributeEnName;
	}

	public void setAttributeEnName(String attributeEnName) {
		this.attributeEnName = attributeEnName;
	}

	public void setSelectValue(List<String> selectValue) {
		this.selectValue = selectValue;
	}
	
	public List<String> getSelectValue() {
		return selectValue;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}
	public int getPosition() {
		return position;
	}
	
}
