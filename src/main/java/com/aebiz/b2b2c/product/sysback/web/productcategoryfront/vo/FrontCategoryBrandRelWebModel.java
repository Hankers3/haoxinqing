package com.aebiz.b2b2c.product.sysback.web.productcategoryfront.vo;

import java.util.ArrayList;
import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.web.vo.BaseWebModel;

public class FrontCategoryBrandRelWebModel  extends BaseWebModel{
	
	private String categoryUuid;
	
	/* 选择值*/
	private String selectValue="";
	
	private String brandName="";
	
	private String searchType="";
	
	private List<String> letters=new ArrayList<String>();
	
	public List<String> getLetters() {
		letters.add("A");letters.add("B");letters.add("C");letters.add("D");letters.add("E");letters.add("F");letters.add("G");
		letters.add("H");letters.add("I");letters.add("J");letters.add("K");letters.add("L");letters.add("M");letters.add("N");
		letters.add("O");letters.add("P");letters.add("Q");letters.add("R");letters.add("S");letters.add("T");letters.add("U");
		letters.add("V");letters.add("W");letters.add("X");letters.add("Y");letters.add("Z");
		return letters;
	}
	
	public void setCategoryUuid(String categoryUuid) {
		this.categoryUuid = categoryUuid;
	}
	public String getCategoryUuid() {
		return categoryUuid;
	}
	
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setSelectValue(String selectValue) {
		this.selectValue = selectValue;
	}
	public String getSelectValue() {
		return selectValue;
	}
	

}
