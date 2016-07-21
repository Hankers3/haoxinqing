package com.aebiz.b2b2c.product.sysback.web.productcategoryfront.vo;

import java.util.List;


public class AttributeAndValueWebModel {
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
	
	/* 位置*/
	private int position=0;
	
	/* 是否区间*/
	private String type=this.NO;
	
	/* 是否选中*/
	private String select;
	
	/* 属性值集合*/
	private List<AttributeValueWebModel> valueList;
	/* 区间属性值*/
	private List<AttributeValueWebModel> yesValueList;
	
	private int noValueSize=0;
	
	private int yesValueSize=0;
	
	
	public List<AttributeValueWebModel> getYesValueList() {
		return yesValueList;
	}
	public void setYesValueList(List<AttributeValueWebModel> yesValueList) {
		this.yesValueList = yesValueList;
	}
	public int getNoValueSize() {
		if(valueList!=null && valueList.size()>0 && this.NO.equals(type)){
			return valueList.size()-1;
		}
		return noValueSize;
	}
	
	public int getYesValueSize() {
		if(valueList!=null && valueList.size()>0  && this.YES.equals(type)){
			return valueList.size()-1;
		}
		return yesValueSize;
	}
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	
	public void setValueList(List<AttributeValueWebModel> valueList) {
		this.valueList = valueList;
	}
	public List<AttributeValueWebModel> getValueList() {
		return valueList;
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
	
	public void setPosition(int position) {
		this.position = position;
	}
	public int getPosition() {
		return position;
	}
	
	public void setSelect(String select) {
		this.select = select;
	}
	public String getSelect() {
		return select;
	}
}
